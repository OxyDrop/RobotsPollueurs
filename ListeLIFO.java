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
public class ListeLIFO extends Liste {
	public ListeLIFO(){
		super();
	}
	public void pop(){
		if(!liste.isEmpty())
			liste.remove(liste.size()-1);
	}
}
