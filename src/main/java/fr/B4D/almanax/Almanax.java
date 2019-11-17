package fr.B4D.almanax;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import fr.B4D.bot.B4DException;
import fr.B4D.dofus.Dofus;
import fr.B4D.dofus.items.Item;
import fr.B4D.dofus.items.Stack;

/** The {@code Almanax} is used to get the {@code Stack} corresponding to a given date.
 * @author Lucas
 *
 */
public class Almanax {

	  /***************/
	 /** CONSTANTS **/
	/***************/
	
	public static final String FRENCH_LANGUAGE = "fr";
	public static final String ENGLISH_LANGUAGE = "en";
	public static final String GERMAN_LANGUAGE = "de";
	public static final String SPANISH_LANGUAGE = "es";
	public static final String ITALIAN_LANGUAGE = "it";
	//public static final String PORTUGUESE_LANGUAGE = "pt";
	
	private static final String FRENCH_REGEX = ".* Récupérer ([0-9]*) (.*) et rapporter l'offrande à .*";
	private static final String ENGLISH_REGEX = ".* Find ([0-9]*) (.*) and take the offering to .*";
	private static final String GERMAN_REGEX = ".* Sich ([0-9]*) (.*) beschaffen und als Opfergabe bei .*";
	private static final String SPANISH_REGEX = ".* Recolectar ([0-9]*) (.*) y llevárselo a .*";
	private static final String ITALIAN_REGEX = ".* Ottieni ([0-9]*) (.*) e porta l'offerta a .*";
	//private static final String PORTUGUESE_REGEX = ".* Find ([0-9]*) (.*) and take the offering to .*";	The portuguese webpage is in english...

	  /**************/
	 /** ATRIBUTS **/
	/**************/
	
	private String language;
	private String regex;

	  /*************/
	 /** BUILDER **/
	/*************/
	
	/** Constructs an {@code Almanax} object with a specific language.
	 * @param language - The language you want to use.<br><br>
	 * To construct a french alamanax object use:
	 * <blockquote><pre>
	 * Almanax almanax = new Almanax(Almanax.FRENCH_LANGUAGE);
	 * </pre></blockquote>
	 * @throws B4DException
	 */
	public Almanax(String language) throws B4DException {
		
		Map<String, String> regexMap = new HashMap<String, String>();
		regexMap.put(FRENCH_LANGUAGE, FRENCH_REGEX);
		regexMap.put(ENGLISH_LANGUAGE, ENGLISH_REGEX);
		regexMap.put(GERMAN_LANGUAGE, GERMAN_REGEX);
		regexMap.put(SPANISH_LANGUAGE, SPANISH_REGEX);
		regexMap.put(ITALIAN_LANGUAGE, ITALIAN_REGEX);
		//regexMap.put(PORTUGUESE_LANGUAGE, PORTUGUESE_REGEX);
		
		if(!regexMap.containsKey(language))
			throw new B4DException("Almanax language '" + language + "' does not exist.");
		
		this.language = language;
		this.regex = regexMap.get(language);
	}

	  /************/
	 /** PUBLIC **/
	/************/
	
	/** Return the today's {@code Stack} offering.
	 * @return Stack to offer today.
	 * @throws B4DException if impossible to get the today's offering.
	 */
	public Stack getStack() throws B4DException {
		return getStack(LocalDate.now());
	}
	
	/** Return the corresponding date {@code Stack} offering.
	 * @param year - Year of the offering.
	 * @param month - Month of the offering.
	 * @param day - Day of the offering.
	 * @return Stack to offer.
	 * @throws B4DException if impossible to get the offering.
	 */
	public Stack getStack(int year, int month, int day) throws B4DException {
		return this.getStack(LocalDate.of(year, month, day));
	}

	/** Return the corresponding date {@code Stack} offering.
	 * @param date - Date of the offering.
	 * @return Stack to offer.
	 * @throws B4DException if impossible to get the offering.
	 */
	public Stack getStack(LocalDate date) throws B4DException {
		try {
			String stringDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String url = "http://www.krosmoz.com/" + this.language + "/almanax/" + stringDate + "?game=dofus";
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.getElementsByClass("more-infos-content");
			
			if(elements.size() == 0)
				throw new B4DException("Couldn't scrap the almanax webpage.");
			
			String text = elements.get(0).childNode(3).outerHtml();
			Pattern pattern = Pattern.compile(this.regex);
			Matcher matcher = pattern.matcher(text);
			
			if(!matcher.matches())
				throw new B4DException("Couldn't scrap the almanax description.");
				
			int amount = Integer.parseInt(matcher.group(1));
			String name = matcher.group(2);
			
			List<Item> items = Dofus.getInstance().getDatabase().findItemsByName(name);
			
			if(items.size() == 0)
				throw new B4DException("Couldn't find the item in the database.");
			
			if(items.size() > 1)
				throw new B4DException("More than one item found in the database.");
			
			return new Stack(items.get(0), amount);
		} catch (IOException e) {
			throw new B4DException(e);
		}
	}
}
