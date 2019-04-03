package robotpollueurs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class TestMonde {
	public static void main(String[] args){
		
		SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
				try{
					int dimension = Integer.parseInt((String)JOptionPane.showInputDialog(null,"Choississez la dimension du monde","Choix dimension monde",JOptionPane.INFORMATION_MESSAGE));
					int nbRob = Integer.parseInt((String)JOptionPane.showInputDialog(null,"Choisissez le nombre de robots à ajouter au monde, un seul d'entre eux sera pollueur","Choix nombre robots",JOptionPane.PLAIN_MESSAGE));
					JFrame.setDefaultLookAndFeelDecorated(true);
					Graphe fen = new Graphe(dimension, nbRob);
					fen.setOpacity(0.90f);
					fen.setVisible(true);
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Entrée incorrecte, réessayez");
					System.exit(0);
				}
				
			}
		});
		Scanner scan = new Scanner(System.in);
		ArrayList<Robot> robotis = new ArrayList<>();
		System.out.print("Choississez la dimension du monde : ");
		int dimension = scan.nextInt();
		System.out.println();
		for(int i=0;i<5;i++){
			if(i==0)
				robotis.add(new pollueur((int)(Math.random()*dimension),(int)(Math.random()*dimension)));
			else
				robotis.add(new nettoyeur((int)(Math.random()*dimension),(int)(Math.random()*dimension)));
		}
		Monde mt = new Monde(dimension, robotis);
		System.out.println(mt.toStringConsole());
		for(int i=0;i<5;i++){
			mt.nextRound();
			System.out.println(mt.toStringConsole());
		}
	}
}

