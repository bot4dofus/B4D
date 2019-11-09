package fr.B4D.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.debatty.java.stringsimilarity.Levenshtein;

/** La classe {@code ClosestString} permet de déduire la chaine de caractère la plus proche d'une autre.<br><br>
 */
public class ClosestString {
	
	private List<String> words;
	
	/** Constructeur de la classe {@code ClosestString}. 
	 */
	public ClosestString() {
		this.words = new ArrayList<String>();
	}
	
	/** Constructeur de la classe {@code ClosestString}. 
	 * @param words - Mots possibles.
	 */
	public ClosestString(String...words) {
		this(Arrays.asList(words));
	}
	
	/** Constructeur de la classe {@code ClosestString}. 
	 * @param words - Mots possibles.
	 */
	public ClosestString(List<String> words) {
		this.words = words;
	}
	
	/** Permet d'ajouter de nouveaux mots.
	 * @param matchs - Nouveaux mots.
	 */
	public void addAll(String...matchs) {
		for(int i=0; i<matchs.length; i++)
			add(matchs[i]);
	}
	
	/** Permet d'ajouter de nouveaux mots.
	 * @param matchs - Nouveaux mots.
	 */
	public void addAll(List<String>matchs) {
		for(String match:matchs)
			add(match);
	}
	
	/** Permet d'ajouter un nouveau mot.
	 * @param match - Nouveau mot.
	 */
	public void add(String match) {
		if(!words.contains(match))
			words.add(match);
	}
	
	/** Permet de calculer le mot le plus proche de celui passé en paramètre.
	 * @param word - Mot à comparer.
	 * @return Mot existant le plus proche.
	 */
	public String getClosest(String word) {
		Levenshtein l = new Levenshtein();
		
		double min = -1;
		String closest = null;
		
		for(String w:words) {
			double distance = l.distance(w, word);
			if(distance < min || min == -1) {
				min = distance;
				closest = w;
			}
		}
		return closest;
	}
}
