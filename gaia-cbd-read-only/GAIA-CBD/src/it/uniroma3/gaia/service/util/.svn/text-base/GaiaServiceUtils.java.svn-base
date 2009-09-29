package it.uniroma3.gaia.service.util;

import java.util.ArrayList;
import java.util.List;

public class GaiaServiceUtils {
	/* Data una lista di liste ne restituisce il prodotto cartesiano n-ario */
	public static List<List> combine(List<List> a){
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
	
	/* Calcola le disposizioni di n elementi con k posizioni disponibili */
	public static List<List> calcolaDisposizioni(int n,int k,List lista) {
		List<List> result = new ArrayList<List>();
		List<String> vis = new ArrayList<String>();//qui faccio salvare gli elementi da stampare
		for(int i=0; i<k; i++){
			vis.add("");
		}
		calcolaDisposizioni(n, k, lista, vis, 0, result);
		return result;
	}
	
	private static int calcolaDisposizioni(int n,int k,List lista,List vis,int j, List<List> result) {
		for(int i=0;i<n;i++){
			if (j != k) {
				vis.set(j, lista.get(i));
				j=calcolaDisposizioni(n, k, lista, vis, ++j, result);
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
}
