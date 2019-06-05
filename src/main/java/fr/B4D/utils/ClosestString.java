package fr.B4D.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.debatty.java.stringsimilarity.Levenshtein;

public class ClosestString {
	
	private List<String> words;
	
	public ClosestString() {
		this.words = new ArrayList<String>();
	}
	
	public ClosestString(String...words) {
		this(Arrays.asList(words));
	}
	
	public ClosestString(List<String> words) {
		this.words = words;
	}
	
	public void addAll(String...matchs) {
		for(int i=0; i<matchs.length; i++)
			add(matchs[i]);
	}
	
	public void addAll(List<String>matchs) {
		for(String match:matchs)
			add(match);
	}
	
	public void add(String match) {
		if(!words.contains(match))
			words.add(match);
	}
	
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
