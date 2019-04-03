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
public class TestLIFO {
	public static void main(String[] args){
		ListeLIFO l1 = new ListeLIFO();
		System.out.println("size : "+l1.size());
		System.out.println(l1.isEmpty());
		l1.push(l1);
		System.out.println("size : "+l1.size());
		System.out.println(l1.isEmpty());
		l1.pop();
		System.out.println("size : "+l1.size());
		System.out.println(l1.isEmpty());
	}
}
