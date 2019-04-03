
package robotpollueurs;
public class nettoyeur extends Robot{
	public nettoyeur(int x, int y){
		super(x,y);
	}
	@Override
	public void move(Monde m){
			if(this.getX()==m.getDimension()-1 && this.getY()==m.getDimension()-1)
				this.move(-m.getDimension()+1,-m.getDimension()+1); //retour à 0
			else if(this.getX()<m.getDimension()-1 && this.getY()==m.getDimension()-1)
				this.move(1,-m.getDimension()+1); //retour ligne
			else
				this.move(0,1);	 //deplacement horizontal
	}
	@Override
	public void action(Monde m){
		m.getListeLIFO(this.getX(),this.getY()).pop();
	}
	@Override
	public String afficher(){
		return "N"+getId();
	}
}
