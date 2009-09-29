package it.uniroma3.gaia.test;

import java.util.ArrayList;
import java.util.List;

public class TestDisposizioni {
	
	public static void main(String[] args) {
		int n = 7, k = 5, j = 0;// n e k possono essere cambiati
		List<String> lista = new ArrayList<String>();//qui ci devono essere tanti elementi quanto il valore di n
		lista.add("a");
		lista.add("b");
		lista.add("c");
		lista.add("d");
		lista.add("e");
		lista.add("f");
		lista.add("g");
		List<String> vis = new ArrayList<String>();//qui faccio salvare gli elementi da stampare
		for(int i=0; i<k; i++){
			vis.add("");
		}
		
		List<List> result  = new ArrayList<List>();
		visualizza(n,k,lista,vis,j, result);
		for(List<String> elem: result){
			 for(String s: elem){
				 System.out.print(s);
			 }
			 System.out.println();
		}
		System.out.println(result.size());
		
		List<List> listamix = new ArrayList<List>();
		
		List<String> l1 = new ArrayList<String>();
		l1.add("1");
		l1.add("2");
		
		List<String> l2 = new ArrayList<String>();
		l2.add("3");
		l2.add("4");
		l2.add("5");
		
		List<String> l3 = new ArrayList<String>();
		l3.add("6");
		l3.add("7");
		
		List<String> l4 = new ArrayList<String>();
		l4.add("8");
		l4.add("9");
		l4.add("a");
		l4.add("b");
		
		listamix.add(l1);
		listamix.add(l2);
		listamix.add(l3);
		listamix.add(l4);
		
		List<List> res = combine(listamix);
		for (List<String> list : res) {
			for (String string : list) {
				System.out.print(string);
			}
			System.out.println();
		}
	}
	
	public static int visualizza(int n,int k,List lista,List vis,int j, List<List> result) {
		for(int i=0;i<n;i++){
			if (j != k) {
				vis.set(j, lista.get(i));
				j=visualizza(n, k, lista, vis, ++j, result);
			}
			else {
				for (int controllo=0;controllo<k;controllo++){	//controlla che non ci siano ripetizioni
					for (int controllo2 = controllo+1; controllo2 < k; controllo2++){
						if (vis.get(controllo) == vis.get(controllo2)){
							return --j;//se si verifica una ripetizioni non stampa i caratteri
						}
					}
				}
				
				List res = new ArrayList<String>();
				for (int s = 0; s < k; s++){
					res.add(null);
					res.set(s, vis.get(s));
				}
				result.add(res);
//				for (int s = 0; s < k; s++){
//					System.out.print(vis.get(s));
//				}
//				System.out.println();
				return --j;
			}


		}
		return --j;

	}
	
	public static List<List> combine(List<List> a){
		//TODO da testare
		List<List> res = new ArrayList<List>();
		res.add(new ArrayList());
		for(int i=0; i<a.size(); i++){
			List curList = a.get(i);
			List<List> res1 = new ArrayList<List>();
			for(int j=0; j<curList.size(); j++){
				for(int k=0; k<res.size(); k++){
					List l = new ArrayList();
					l.addAll(res.get(k));
					l.add(curList.get(j));
					res1.add(l);
				}
			}
			res = res1;
		}
		return res;
	}

}
