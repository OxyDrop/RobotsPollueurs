/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotpollueurs;
import java.util.*;
/**
 *
 * @author Serero
 */
public abstract class Liste {
	protected ArrayList<Object> liste;
	public Liste(){
		liste = new ArrayList<>();
	}
	public boolean isEmpty(){
		return liste.isEmpty();
	}
	public int size(){
		return liste.size();
	}
	public void push(Object o){
		liste.add(o);
	}
}
