package robotpollueurs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
/* classe implementant une répresentation graphique du monde des robots, cette representation s'affiche sous la forme d'une JFrame
	La fenetre devra comporter 2 panneaux, pour dessiner qui implemente le gestionnaire GridLayout afin d'afficher le monde
	L'autre panneau placé en bas de la fenetre possede 1 boutons pour passer au tour de marche suivant (nextRound())
	Deux JTextField permettent d'ajouter de nouveaux robots au monde avec un bouton OK pour confirmer la saisie
	Le monde sera dessiné à l'aide de g.drawString et peut-être g.drawLine
	
	*/
public class TestGraphe extends JFrame{
	private JPanel panneauMonde;
	private JTextArea mToString;
	private ArrayList<Robot> robots;
	private Monde m;
	/*----------------------------------*/
	public TestGraphe(int dimension, int nbRob){
		/*Actions fenetre */
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
				System.exit(0);
		}});
		/* Initialisation tableau des robots */
		robots = new ArrayList<>();
		for(int i=0;i<nbRob;i++){
			if(i==0)
				robots.add(new pollueur((int)(Math.random()*dimension),(int)(Math.random()*dimension)));
			else
				robots.add(new nettoyeur((int)(Math.random()*20),(int)(Math.random()*20)));
		}
		/* INSTANCIATION */
		setTitle("Robots Pollueurs"); setSize(500,500);
		Container contenu = getContentPane();
		m = new Monde(20,robots);
		mToString = new JTextArea(m.toString());
		panneauMonde = new JPanel(new BorderLayout()); 
		panneauMonde.add(mToString,"North");
		/* Ajouts conteneur */
		contenu.add(panneauMonde); 
		panneauMonde.setBackground(Color.white);
	}
	public Monde getMonde(){
		return m;
	}
}

