/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotpollueurs;

/**
 *
 * @author Serero
 */
public class pollueur extends Robot{
	boolean switcher = false;
	public pollueur(int x, int y){
		super(x,y);
	}
	@Override
	public void move(Monde m){
		 //drapeau qui indique le sens d'orientation du robot, de droite à gauche ou de gauche à droite
			if(!switcher && this.getY()<m.getDimension()-1)
			{
				this.move(0,1); //bouge a droite
				if(this.getY()==m.getDimension()-1) //fin de ligne
					switcher=true;
			}
			else if(switcher && this.getY()>0)
			{
				this.move(0,-1);  //va à gauche
				if(this.getY()==0) //debut de ligne
					switcher=false;
			}
	}
	@Override
	public void action(Monde m){
		m.getListeLIFO(this.getX(), this.getY()).push(new PapierGras());
	}
	@Override
	public String afficher(){
		return "P"+getId();
	}
}
