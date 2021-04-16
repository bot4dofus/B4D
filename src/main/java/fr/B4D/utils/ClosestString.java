package fr.B4D.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.debatty.java.stringsimilarity.Levenshtein;

/**
 * The {@code ClosestString} class is used to find the closest string from an other.
 * 
 * @author Lucas
 *
 */
public class ClosestString {
	
	/**
	 * List of accepted strings.
	 */
	private List<String> words;
	
	/**
	 * Constructor of the {@code ClosestString} with no words. 
	 */
	public ClosestString() {
		this.words = new ArrayList<String>();
	}
	
	/**
	 * Constructor of the {@code ClosestString}. 
	 * @param words - Possible words.
	 */
	public ClosestString(String...words) {
		this(Arrays.asList(words));
	}

	/**
	 * Constructor of the {@code ClosestString}. 
	 * @param words - Possible words.
	 */
	public ClosestString(List<String> words) {
		this.words = words;
	}
	
	/**
	 * Returns the closest word from the one passed in argument.
	 * @param word - Word to compare.
	 * @return Closest existing word.
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
