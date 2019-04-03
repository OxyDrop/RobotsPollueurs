package robotpollueurs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;


public class Graphe extends JFrame implements ActionListener{
	private JTextField nettoyeur, pollueur;
	private final JButton arretBouton, nextBouton, ajouterBouton, cinquanteBoucle, slower, faster;
	private JPanel panneauMonde, panneauBouton;
	private final JLabel pol, net;
	private JTextArea mToString;
	private ArrayList<Robot> robots;
	private final Monde m;
	private Timer timer;
	private static int i;
	static final private Color[] COLOR = {Color.red, Color.blue, Color.PINK, Color.GREEN, Color.ORANGE, Color.WHITE, Color.magenta};
	/*---------------CONSTRUCTEUR-------------------*/
	public Graphe(int dimension, int nbRob){
		
		/*-------- Initialisation tableau des robots --------*/
		robots = new ArrayList<>();
		for(int j=0;j<nbRob;j++){
			if(j==0) 
				robots.add(new pollueur((int)(Math.random()*dimension),(int)(Math.random()*dimension)));
			else 
				robots.add(new nettoyeur((int)(Math.random()*dimension),(int)(Math.random()*dimension)));
		}
		/*------------- INSTANCIATION ------------------*/
		setTitle("Robots Pollueurs"); setSize(800,900); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contenu = getContentPane();
		m = new Monde(dimension,robots);
		nettoyeur = new JTextField(5); 
		pollueur = new JTextField(5);
		arretBouton = new JButton("Arreter"); 
		nextBouton = new JButton("NextRound"); 
		ajouterBouton = new JButton("Ajouter robots");
		cinquanteBoucle = new JButton("Automatique");
		slower = new JButton("Slower");
		faster = new JButton("Faster");
		mToString = new JTextArea(m.toString());
		mToString.setBackground(Color.black);
		mToString.setForeground(Color.white);
		mToString.setEditable(false);
		pol = new JLabel("Pollueur :");
		net = new JLabel("Nettoyeur :");
		panneauBouton = new JPanel(new FlowLayout());
		panneauMonde = new JPanel(); 
		/*----------- Ajouts panneaux ---------------*/
		panneauBouton.add(net);
		panneauBouton.add(nettoyeur);
		panneauBouton.add(pol);
		panneauBouton.add(pollueur);
		panneauBouton.add(ajouterBouton);
		panneauBouton.add(nextBouton);
		panneauBouton.add(cinquanteBoucle);
		panneauBouton.add(slower);
		panneauBouton.add(faster);
		panneauBouton.add(arretBouton);
		panneauMonde.add(mToString);
		/*--------------- Ajouts conteneur ------------- */
		contenu.add(panneauMonde); 
		contenu.add(panneauBouton,"South");
		///////////AJOUT ECOUTEUR EVENEMENT////////////////
		nextBouton.addActionListener(this); 
		ajouterBouton.addActionListener(this); 
		arretBouton.addActionListener(this);
		cinquanteBoucle.addActionListener(this);
		slower.addActionListener(this);
		faster.addActionListener(this);
		//--------------------AUTRES --------------------
		panneauMonde.setBackground(Color.black);
		panneauMonde.setFocusable(true);
		slower.setEnabled(false);
		faster.setEnabled(false);
		panneauMonde.addKeyListener(new KeyAdapter(){ //Si une touche+alt est pressé, arrete le timer
				public void keyPressed(KeyEvent ke){ 
					if(timer != null && timer.isRunning()) //si le timer est instancié et est en cours
					{ 
						timer.stop();
						faster.setEnabled(false);
						slower.setEnabled(false);
					}
			}});
	}	
	@Override
	public void actionPerformed(ActionEvent ev){ //Evenements qui se declenchent lors de l'appui sur un bouton
		
		Object source = (JButton)ev.getSource();
		
		if(source == nextBouton) //Prochain tour de jeu
		{ 
			if(i==COLOR.length) 
				i=0;
			m.nextRound();
			mToString.setText(m.toString());
			mToString.setForeground(COLOR[i++]);
			panneauMonde.revalidate();
			try{
				SoundsUtils.tone(5000,100);
			}catch(LineUnavailableException e){}
		}
		if(source==cinquanteBoucle) //Démarre le timer ou l'arrete
		{
			if(timer != null && timer.isRunning())
			{
				nextBouton.setEnabled(true);
				faster.setEnabled(false);
				slower.setEnabled(false);
				timer.stop();
			}
			else
			{
				nextBouton.setEnabled(false);
				faster.setEnabled(true);
				slower.setEnabled(true);
				timer = new Timer(500,new TimeClass(mToString,panneauMonde,m)); 
				timer.start();
			}
		}
		if(source == ajouterBouton) //Ajout de robots dans le tableau
		{ 
			try{
				int polleurs=Integer.parseInt(pollueur.getText());
				int nettoyeurs=Integer.parseInt(nettoyeur.getText());
				
				for(int j=0;j<polleurs;j++){
					robots.add(new pollueur((int)(Math.random()*m.getDimension()),(int)(Math.random()*m.getDimension())));
				}
				for(int j=0;j<nettoyeurs;j++){
					robots.add(new nettoyeur((int)(Math.random()*m.getDimension()),(int)(Math.random()*m.getDimension())));
				}
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(this, "Valeur entrée incorrecte ", "Entrée incorrecte", JOptionPane.WARNING_MESSAGE);
			}
		}
		if(source==slower)
			timer.setDelay(timer.getDelay()+100);
		if(source==faster)
			timer.setDelay(timer.getDelay()-100);
		if(source == arretBouton) //Arret programme
			System.exit(0);
	}
}
class TimeClass implements ActionListener{
	private JTextArea mto;
	private JPanel pan;
	private Monde m;
	int i,som;
	static final private Color[] COLOR = {Color.red, Color.blue, Color.PINK, Color.GREEN, Color.ORANGE, Color.WHITE, Color.magenta};
	public TimeClass(JTextArea mto, JPanel pan, Monde m){
		this.mto=mto;
		this.pan=pan;
		this.m=m;
	}
	@Override
	public void actionPerformed(ActionEvent e){
			if(i==COLOR.length) 
				i=0;
			m.nextRound();
			mto.setText(m.toString());
			mto.setForeground(COLOR[i++]);
			pan.revalidate();
			pan.requestFocus();
			try{
				SoundsUtils.tone(i*1000+200,100);
			}catch(LineUnavailableException ex){}
	}
}
