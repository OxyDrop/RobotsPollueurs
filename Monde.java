package robotpollueurs;
import java.util.ArrayList;

public class Monde {
	private final int dimension;
	private final ListeLIFO[][] matrice;
	private final ArrayList<Robot> robots;
        
	public Monde(int dimension, ArrayList<Robot> robots){
		this.dimension=dimension;
		this.robots=robots;
		matrice=new ListeLIFO[dimension][dimension];
		for(int i=0;i<dimension;i++){
			for(int j=0;j<dimension;j++){
				int x = (int)(Math.random()*3);
				matrice[i][j]=new ListeLIFO();
				for(int k=0;k<x;k++){
					matrice[i][j].push(new PapierGras());
				}
			}
		}
	}
	public ListeLIFO getListeLIFO(int x, int y){ //retourne la pile à l'emplacement x et y de la matrice
		return matrice[x][y];
	}
	public int getDimension(){ //retourne la taille du monde
		return dimension;
	}
	public int getSize(int x, int y){ //retourne la taille de la pile à l'emplacement x et y de la matrice
		return getListeLIFO(x,y).size();
	}
	public String afficheRobots(){
		String s = "";
		int i=0;
		for(Robot r : robots)
		{	
			if(i%5==0 && i!=0) //affiche 4 robots par ligne
				s+=r.afficher()+" "+r.toString()+"\n";
			else
				s+=r.afficher()+" "+r.toString()+" | ";
			i++;
		}
		return s;
	}
	public boolean positionegales(Robot r, int x, int y){ // retourne vrai si les coordonnées d'un robot sont égales au couple (x,y)
		return (r.getX()==x && r.getY()==y);
	}
	
	@Override
	public String toString(){
		String s=afficheRobots()+"\n";
        boolean flag=false;
		for(int i=0;i<dimension;i++){
			s+="\n";
			for(int j=0;j<dimension;j++)
			{
                flag = false;
                for(Robot r : robots)
				{
                    if(positionegales(r, i, j))
					{
                        s+=" "+r.afficher();
                        flag=true;
                        break;
                    }
                }
                if(getSize(i,j)==0 && !flag) 
					s+="    *";
                else if(getSize(i,j)!=0 && !flag)
					s+="   "+getSize(i,j);
			}
		}
		return s;
	}
	public String toStringConsole(){
		String s=afficheRobots()+"\n";
        boolean flag=false;
		for(int i=0;i<dimension;i++){
			s+="\n";
			for(int j=0;j<dimension;j++){
                flag = false;
                for(Robot r : robots){
                    if(positionegales(r, i, j)){
                        s+=" "+r.afficher();
                        flag=true;
                        break;
                    }
                }
                if(getSize(i,j)==0 && !flag) 
					s+="  *";
                else if(getSize(i,j)!=0 && !flag)
					s+="  "+getSize(i,j);
			}
		}
		return s;
	}
	public void nextRound(){
		for(Robot r : robots){
			r.action(this);
			r.move(this);
		}
	}
	public ArrayList<Robot> getListeRobot(){
		return robots;
	}
}
