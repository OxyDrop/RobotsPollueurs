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
public abstract class Robot {
	private int x,y;
	private static int cpt=0;
	private int id;
	public Robot(int x, int y){
		id=cpt;
		cpt++;
		this.x=x;
		this.y=y;
	}
	protected void move(int dx, int dy){
		x+=dx;
		y+=dy;
	}
	public int getId(){
		return id;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	@Override
	public String toString(){
		return "x="+x+" y="+y;
	}
	public abstract String afficher();
	public abstract void move(Monde m);
	public abstract void action (Monde m);
}
