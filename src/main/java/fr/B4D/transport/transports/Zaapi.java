package fr.B4D.transport.transports;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.transport.Transport;
import fr.B4D.utils.PointF;

/**
 * The {@code Zaapi} class represents a zaapi.
 * Its implementation is almost the same as {@link Zaap}.
 * <br><br>
 * This class extends {@link Transport}.
 * A zaapi is defined by a type.
 * 
 * @author Lucas
 *
 */
public class Zaapi extends Transport implements Serializable{

	private static final long serialVersionUID = -1579035196209575267L;
	
	/**
	 * Type of the zaapi.
	 */
	private ZaapiType zaapiType;
	
	/** BONTA **/
	
    /**
     * The zaapi of the Bonta Alchemist workshop.
     */
    public final static Zaapi Atelier_Alchimistes_Bonta = new Zaapi("Atelier des alchimistes", new Point(-29, -53), new PointF(0.2645883, 0.2385229), ZaapiType.Atelier);
    
    /**
     * The zaapi of the Bonta Jeweller workshop.
     */
    public final static Zaapi Atelier_Bijoutiers_Bonta = new Zaapi("Atelier des bijoutiers", new Point(-34, -61), new PointF(0.1582734, 0.1337325), ZaapiType.Atelier);
    
    /**
     * The zaapi of the Bonta Handyman workshop.
     */
    public final static Zaapi Atelier_Bricoleurs_Bonta = new Zaapi("Atelier des bricoleurs ", new Point(-26, -60), new PointF(0.7082334, 0.743513), ZaapiType.Atelier);
    
    /**
     * The zaapi of the Bonta Hunter workshop.
     */
    public final static Zaapi Atelier_Chasseurs_Bonta = new Zaapi("Atelier des chasseurs", new Point(-32, -52), new PointF(0.6714628, 0.3662675), ZaapiType.Atelier);
    
    /**
     * The zaapi of the Bonta Shoemaker workshop.
     */
    public final static Zaapi Atelier_Cordonniers_Bonta = new Zaapi("Atelier des cordonniers", new Point(-29, -58), new PointF(0.6402878, 0.5668663), ZaapiType.Atelier);
    
    /**
     * The zaapi of the Bonta Forgemage workshop.
     */
    public final static Zaapi Atelier_Forgemages_Bonta = new Zaapi("Atelier des forgemages", new Point(-32, -55), new PointF(0.4300559, 0.2894212), ZaapiType.Atelier);
    
    /**
     * The zaapi of the Bonta Blacksmith workshop.
     */
    public final static Zaapi Atelier_Forgerons_Bonta = new Zaapi("Atelier des forgerons", new Point(-28, -55), new PointF(0.3405276, 0.3003992), ZaapiType.Atelier);
    
    /**
     * The zaapi of the Bonta Miner workshop.
     */
    public final static Zaapi Atelier_Mineurs_Bonta = new Zaapi("Atelier des mineurs", new Point(-27, -57), new PointF(0.5363709, 0.3233533), ZaapiType.Atelier);
    
    /**
     * The zaapi of the Bonta Farmer workshop.
     */
    public final static Zaapi Atelier_Paysans_Bonta = new Zaapi("Atelier des paysans", new Point(-29, -50), new PointF(0.676259, 0.3013972), ZaapiType.Atelier);
    
    /**
     * The zaapi of the Bonta Fisher workshop.
     */
    public final static Zaapi Atelier_Pecheurs_Bonta = new Zaapi("Atelier des pecheurs", new Point(-36, -52), new PointF(0.7729816, 0.244511), ZaapiType.Atelier);
    
    /**
     * The zaapi of the Bonta Sculptor workshop.
     */
    public final static Zaapi Atelier_Sculpteurs_Bonta = new Zaapi("Atelier des sculpteurs", new Point(-28, -61), new PointF(0.6083133, 0.1007984), ZaapiType.Atelier);
    
    /**
     * The zaapi of the Bonta Tailor workshop.
     */
    public final static Zaapi Atelier_Tailleurs_Bonta = new Zaapi("Atelier des tailleurs", new Point(-29, -56), new PointF(0.8832934, 0.1756487), ZaapiType.Atelier);
    
    @Deprecated
    public final static Zaapi HDV_Animaux_Bonta = new Zaapi("Hôtel de vente des animaux", new Point(-36, -56), new PointF(0.2965628, 0.1616766), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Alchimistes_Bonta = new Zaapi("Hôtel de vente des alchimistes", new Point(-30, -53), new PointF(0.7034373, 0.1926148), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Ames_Bonta = new Zaapi("Hôtel de vente des âmes", new Point(-32, -57), new PointF(0.4060751, 0.2355289), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Bijoutiers_Bonta = new Zaapi("Hôtel de vente des bijoutiers", new Point(-33, -59), new PointF(0.9104716, 0.2045908), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Bricoleurs_Bonta = new Zaapi("Hôtel de vente des bricoleurs", new Point(-26, -60), new PointF(0.7034373, 0.7205589), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Bucherons_Bonta = new Zaapi("Hôtel de vente des bûcherons", new Point(-30, -60), new PointF(0.2589928, 0.1666667), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Chasseurs_Bonta = new Zaapi("Hôtel de vente des chasseurs", new Point(-32, -51), new PointF(0.4980016, 0.6477046), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Cordonniers_Bonta = new Zaapi("Hôtel de vente des cordonniers", new Point(-30, -58), new PointF(0.7122302, 0.250499), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Documents_Bonta = new Zaapi("Hôtel de vente des documents", new Point(-33, -58), new PointF(0.41247, 0.7015968), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Faconneurs_Bonta = new Zaapi("Hôtel de vente des façonneurs", new Point(-27, -51), new PointF(0.1231015, 0.3622755), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Forgerons_Bonta = new Zaapi("Hôtel de vente des forgerons", new Point(-28, -55), new PointF(0.4796163, 0.6986028), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Mineurs_Bonta = new Zaapi("Hôtel de vente des mineurs", new Point(-26, -55), new PointF(0.3653077, 0.3542914), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Parchemins_Bonta = new Zaapi("Hôtel de vente des parchemins lies", new Point(-33, -56), new PointF(0.8776978, 0.4730539), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Paysans_Bonta = new Zaapi("Hôtel de vente des paysans", new Point(-28, -52), new PointF(0.3900879, 0.5089821), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Pecheurs_Bonta = new Zaapi("Hôtel de vente des pêcheurs", new Point(-36, -53), new PointF(0.4388489, 0.6297405), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Ressources_Bonta = new Zaapi("Hôtel de vente des ressources", new Point(-33, -54), new PointF(0.2486011, 0.2774451), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Runes_Bonta = new Zaapi("Hôtel de vente des runes", new Point(-32, -55), new PointF(0.4300559, 0.2694611), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Sculpteurs_Bonta = new Zaapi("Hôtel de vente des sculpteurs", new Point(-29, -59), new PointF(0.3828937, 0.1057884), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Tailleurs_Bonta = new Zaapi("Hôtel de vente des tailleurs", new Point(-30, -56), new PointF(0.4204636, 0.506986), ZaapiType.HDV);
    
    /**
     * The zaapi of the Bonta arena.
     */
    public final static Zaapi Arene_Bonta = new Zaapi("Arene", new Point(-32, -57), new PointF(0.3956835, 0.2265469), ZaapiType.Divers);
    
    /**
     * The zaapi of the Bonta bank.
     */
    public final static Zaapi Banque_Bonta = new Zaapi("Banque", new Point(-31, -54), new PointF(0.8073541, 0.5688623), ZaapiType.Divers);
    
    /**
     * The zaapi of the Bonta library.
     */
    public final static Zaapi Bibliotheque_Bonta = new Zaapi("Bibliotheque", new Point(-33, -58), new PointF(0.4068745, 0.7095808), ZaapiType.Divers);
    
    /**
     * The zaapi of the Bonta grocery.
     */
    public final static Zaapi Epicerie_Bonta = new Zaapi("Epicerie", new Point(-33, -54), new PointF(0.2318145, 0.2654691), ZaapiType.Divers);
    
    /**
     * The zaapi of the Bonta job office.
     */
    public final static Zaapi Hotel_Metiers_Bonta = new Zaapi("Hotel des metiers", new Point(-33, -55), new PointF(0.86251, 0.5638723), ZaapiType.Divers);
    
    /**
     * The zaapi of the Bonta militia.
     */
    public final static Zaapi Milice_Bonta = new Zaapi("Milice", new Point(-33, -56), new PointF(0.7505995, 0.498004), ZaapiType.Divers);
    
    /**
     * The zaapi of the Bonta merchand square.
     */
    public final static Zaapi Place_Marchande_Bonta = new Zaapi("Place marchande", new Point(-32, -54), new PointF(0.3661071, 0.2734531), ZaapiType.Divers);
    
    /**
     * The zaapi of the Bonta Alakarte tavern.
     */
    public final static Zaapi Taverne_Alakarte_Bonta = new Zaapi("Taverne Alakarte", new Point(-29, -57), new PointF(0.7418066, 0.50998), ZaapiType.Divers);
    
    /**
     * The zaapi of the Bonta Bagrutte tavern.
     */
    public final static Zaapi Taverne_Bagrutte_Bonta = new Zaapi("Taverne de la Bagrutte", new Point(-34, -60), new PointF(0.6003197, 0.6556886), ZaapiType.Divers);
    
    /**
     * The zaapi of the Bonta Chopenbois tavern.
     */
    public final static Zaapi Taverne_Chopenbois_Bonta = new Zaapi("Taverne de la Chopenbois", new Point(-29, -61), new PointF(0.6506795, 0.5259481), ZaapiType.Divers);
    
    /**
     * The zaapi of the Bonta Tabasse tavern.
     */
    public final static Zaapi Taverne_Tabasse_Bonta = new Zaapi("Taverne de la Tabasse", new Point(-34, -50), new PointF(0.6666667, 0.3752495), ZaapiType.Divers);
    
    /**
     * The zaapi of the Bonta Ferayeur tavern.
     */
    public final static Zaapi Taverne_Ferayeur_Bonta = new Zaapi("Taverne de la Ferayeur", new Point(-26, -58), new PointF(0.656275, 0.5489022), ZaapiType.Divers);
    
    /**
     * The zaapi of the Bonta Feubuk tavern.
     */
    public final static Zaapi Taverne_Feubuk_Bonta = new Zaapi("Taverne du Feubuk", new Point(-27, -56), new PointF(0.7130296, 0.3063872), ZaapiType.Divers);
    
    /**
     * The zaapi of the Bonta Pinchaut tavern.
     */
    public final static Zaapi Taverne_Pinchaut_Bonta = new Zaapi("Taverne du Pinchaut", new Point(-28, -50), new PointF(0.6091127, 0.4361278), ZaapiType.Divers);
    
    /**
     * The zaapi of the Bonta orders tower.
     */
    public final static Zaapi Tour_Ordes_Bonta = new Zaapi("Tour des ordres", new Point(-34, -57), new PointF(0.9008793, 0.3932136), ZaapiType.Divers);
    
    /**
     * The zaapi of the Bonta zaap.
     */
    public final static Zaapi Zaap_Bonta = new Zaapi("Zaap", new Point(-32, -56), new PointF(0.09352518, 0.1417166), ZaapiType.Divers);
	
    /** BRAKMAR **/
    
    /**
     * The zaapi of the Brakmar Alchemist workshop.
     */
    public final static Zaapi Atelier_Alchimistes_Brakmar = new Zaapi("Atelier des alchimistes", new Point(-23, 40), new PointF(0.6741, 0.0731), ZaapiType.Atelier);
    /**
     * The zaapi of the Brakmar Jeweller workshop.
     */
    public final static Zaapi Atelier_Bijoutiers_Brakmar = new Zaapi("Atelier des bijoutiers", new Point(-28, 39), new PointF(0.1075, 0.2154), ZaapiType.Atelier);
    /**
     * The zaapi of the Brakmar Handyman workshop.
     */
    public final static Zaapi Atelier_Bricoleurs_Brakmar = new Zaapi("Atelier des bricoleurs", new Point(-30, 35), new PointF(0.1629, 0.1693), ZaapiType.Atelier);
    /**
     * The zaapi of the Brakmar hunter workshop.
     */
    public final static Zaapi Atelier_Chasseurs_Brakmar = new Zaapi("Atelier des chasseurs", new Point(-29, 32), new PointF(0.0473, 0.2826), ZaapiType.Atelier);
    /**
     * The zaapi of the Brakmar Shoemaker workshop.
     */
    public final static Zaapi Atelier_Cordonniers_Brakmar = new Zaapi("Atelier des cordonniers", new Point(-23, 36), new PointF(0.947, 0.523), ZaapiType.Atelier);
    /**
     * The zaapi of the Brakmar Forgemage workshop.
     */
    public final static Zaapi Atelier_Forgemages_Brakmar = new Zaapi("Atelier des forgemages", new Point(-28, 36), new PointF(0.9278, 0.2164), ZaapiType.Atelier);
    /**
     * The zaapi of the Brakmar Blacksmith workshop.
     */
    public final static Zaapi Atelier_Forgerons_Brakmar = new Zaapi("Atelier des forgerons", new Point(-21, 34), new PointF(0.5931, 0.3056), ZaapiType.Atelier);
    /**
     * The zaapi of the Brakmar Miner workshop.
     */
    public final static Zaapi Atelier_Mineurs_Brakmar = new Zaapi("Atelier des mineurs", new Point(-20, 34), new PointF(0.1958, 0.7124), ZaapiType.Atelier);
    /**
     * The zaapi of the Brakmar Farmer workshop.
     */
    public final static Zaapi Atelier_Paysans_Brakmar = new Zaapi("Atelier des paysans", new Point(-23, 32), new PointF(0.0883, 0.2685), ZaapiType.Atelier);
    /**
     * The zaapi of the Brakmar Fisher workshop.
     */
    public final static Zaapi Atelier_Pecheurs_Brakmar = new Zaapi("Atelier des pecheurs", new Point(-20, 39), new PointF(0.5955, 0.1934), ZaapiType.Atelier);
    /**
     * The zaapi of the Brakmar Sculptor workshop.
     */
    public final static Zaapi Atelier_Sculpteurs_Brakmar = new Zaapi("Atelier des sculpteurs", new Point(-21, 40), new PointF(0.5698, 0.488), ZaapiType.Atelier);
    /**
     * The zaapi of the Brakmar Tailor workshop.
     */
    public final static Zaapi Atelier_Tailleurs_Brakmar = new Zaapi("Atelier des tailleurs", new Point(-24, 36), new PointF(0.2937, 0.1252), ZaapiType.Atelier);
    
    @Deprecated
    public final static Zaapi HDV_Animaux_Brakmar = new Zaapi("Hôtel de vente des animaux", new Point(-32, 37), new PointF(0.7632, 0.1593), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Alchimistes_Brakmar = new Zaapi("Hôtel de vente des alchimistes", new Point(-24, 40), new PointF(0.768, 0.4028), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Ames_Brakmar = new Zaapi("Hôtel de vente des ames", new Point(-27, 38), new PointF(0.1934, 0.1303), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Bijoutiers_Brakmar = new Zaapi("Hôtel de vente des bijoutiers", new Point(-27, 39), new PointF(0.6228, 0.3577), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Bricoleurs_Brakmar = new Zaapi("Hôtel de vente des bricoleurs", new Point(-29, 34), new PointF(0.7656, 0.5811), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Bucherons_Brakmar = new Zaapi("Hôtel de vente des bucherons", new Point(-22, 40), new PointF(0.3748, 0.1543), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Chasseurs_Brakmar = new Zaapi("Hôtel de vente des chasseurs", new Point(-27, 32), new PointF(0.9061, 0.3126), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Cordonniers_Brakmar = new Zaapi("Hôtel de vente des cordonniers", new Point(-23, 37), new PointF(0.9374, 0.1553), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Documents_Brakmar = new Zaapi("Hôtel de vente des documents", new Point(-28, 37), new PointF(0.9053, 0.6152), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Faconneurs_Brakmar = new Zaapi("Hôtel de vente des faconneurs", new Point(-22, 32), new PointF(0.5506, 0.5311), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Forgerons_Brakmar = new Zaapi("Hôtel de vente des forgerons", new Point(-21, 35), new PointF(0.577, 0.0401), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Milice_Brakmar = new Zaapi("Hôtel de vente des mineurs", new Point(-20, 35), new PointF(0.7327, 0.1743), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Parchemins_Lies_Brakmar = new Zaapi("Hôtel de vente des parchemins lies", new Point(-26, 36), new PointF(0.9029, 0.2745), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Paysans_Brakmar = new Zaapi("Hôtel de vente des paysans", new Point(-24, 32), new PointF(0.2103, 0.2274), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Pecheurs_Brakmar = new Zaapi("Hôtel de vente des pecheurs", new Point(-19, 39), new PointF(0.455, 0.513), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Ressources_Brakmar = new Zaapi("Hôtel de vente des ressources", new Point(-25, 34), new PointF(0.3323, 0.3657), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Runes_Brakmar = new Zaapi("Hôtel de vente des runes", new Point(-28, 36), new PointF(0.9229, 0.2134), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Sculpteurs_Brakmar = new Zaapi("Hôtel de vente des sculptures", new Point(-21, 39), new PointF(0.1035, 0.1603), ZaapiType.HDV);
    
    @Deprecated
    public final static Zaapi HDV_Taileurs_Brakmar = new Zaapi("Hôtel de vente des tailleurs", new Point(-24, 37), new PointF(0.1918, 0.3327), ZaapiType.HDV);
    
    /**
     * The zaapi of the Brakmar arena.
     */
    public final static Zaapi Arene_Brakmar = new Zaapi("Arene", new Point(-27, 38), new PointF(0.4582, 0.3417), ZaapiType.Divers);
    /**
     * The zaapi of the Brakmar bank.
     */
    public final static Zaapi Banque_Brakmar = new Zaapi("Banque", new Point(-27, 35), new PointF(0.2632, 0.1052), ZaapiType.Divers);
    /**
     * The zaapi of the Brakmar library.
     */
    public final static Zaapi Bibliotheque_Brakmar = new Zaapi("Bibliotheque", new Point(-28, 37), new PointF(0.9101, 0.6292), ZaapiType.Divers);
    /**
     * The zaapi of the Brakmar pubic enclose.
     */
    public final static Zaapi Enclos_Public_Brakmar = new Zaapi("Enclos public", new Point(-32, 37), new PointF(0.7769, 0.1513), ZaapiType.Divers);
    /**
     * The zaapi of the Brakmar job office.
     */
    public final static Zaapi Hotel_Metier_Brakmar = new Zaapi("Hotel des metiers", new Point(-27, 34), new PointF(0.6845, 0.5351), ZaapiType.Divers);
    /**
     * The zaapi of the Brakmar militia.
     */
    public final static Zaapi Milice_Brakmar = new Zaapi("Milice", new Point(-26, 36), new PointF(0.9029, 0.2745), ZaapiType.Divers);
    /**
     * The zaapi of the Brakmar merchand square.
     */
    public final static Zaapi Place_Marchande_Brakmar = new Zaapi("Place Marchande", new Point(-27, 36), new PointF(0.2287, 0.1844), ZaapiType.Divers);
    /**
     * The zaapi of the Brakmar exit.
     */
    public final static Zaapi Sortie_Brakmar = new Zaapi("Sortie", new Point(-26, 41), new PointF(0.8371, 0.3888), ZaapiType.Divers);
    /**
     * The zaapi of the Brakmar Atolmond tavern.
     */
    public final static Zaapi Taverne_Atolmond_Brakmar = new Zaapi("Taverne Atolmond", new Point(-29, 32), new PointF(0.0506, 0.2846), ZaapiType.Divers);
    /**
     * The zaapi of the Brakmar Atolmond tavern.
     */
    public final static Zaapi Taverne_Djaul_Brakmar = new Zaapi("Taverne de Djaul", new Point(-24, 35), new PointF(0.7913, 0.6503), ZaapiType.Divers);
    /**
     * The zaapi of the Brakmar Djaul tavern.
     */
    public final static Zaapi Taverne_Misere_Brakmar = new Zaapi("Taverne de la Misere", new Point(-22, 39), new PointF(0.4543, 0.0832), ZaapiType.Divers);
    /**
     * The zaapi of the Brakmar Bwork tavern.
     */
    public final static Zaapi Taverne_Bwork_Brakmar = new Zaapi("Taverne de Bwork", new Point(-22, 31), new PointF(0.6998, 0.4208), ZaapiType.Divers);
    /**
     * The zaapi of the Brakmar Chabrule tavern.
     */
    public final static Zaapi Taverne_Chabrule_Brakmar = new Zaapi("Taverne de Chabrule", new Point(-26, 41), new PointF(0.8371, 0.3888), ZaapiType.Divers);
    /**
     * The zaapi of the Brakmar tower.
     */
    public final static Zaapi Tour_Brakmar_Brakmar = new Zaapi("Tour de Brakmar", new Point(-26, 34), new PointF(0.939, 0.5641), ZaapiType.Divers);
    /**
     * The zaapi of the Brakmar zaap.
     */
    public final static Zaapi Zaap_Brakmar = new Zaapi("Zaap", new Point(-26, 35), new PointF(0.7713, 0.3627), ZaapiType.Divers);
	
	/**
	 * Constructor of the {@code Zaapi} class.
	 * @param name - Name of the zaapi.
	 * @param position - Location of the zaapi on the map.
	 * @param positionF - Location of the zaapi on the screen in relative coordinates.
	 * @param zaapiType - Type of the zaapi.
	 */
	public Zaapi(String name, Point position, PointF positionF, ZaapiType zaapiType) {
		super(name, position, positionF, ZAAPI_COST);
		this.zaapiType = zaapiType;
	}
	
    /**
     * Returns the list of all the zaaps in Bonta.
     * @return List of zaapis.
     */
    public final static ArrayList<Transport> getAllBonta(){
    	ArrayList<Transport> zaapis = new ArrayList<Transport>();
    	zaapis.add(Atelier_Alchimistes_Bonta );
    	zaapis.add(Atelier_Bijoutiers_Bonta );
    	zaapis.add(Atelier_Bricoleurs_Bonta );
    	zaapis.add(Atelier_Chasseurs_Bonta );
    	zaapis.add(Atelier_Cordonniers_Bonta );
    	zaapis.add(Atelier_Forgemages_Bonta );
    	zaapis.add(Atelier_Forgerons_Bonta );
    	zaapis.add(Atelier_Mineurs_Bonta );
    	zaapis.add(Atelier_Paysans_Bonta );
    	zaapis.add(Atelier_Pecheurs_Bonta );
    	zaapis.add(Atelier_Sculpteurs_Bonta );
    	zaapis.add(Atelier_Tailleurs_Bonta );
    	zaapis.add(HDV_Animaux_Bonta );
    	zaapis.add(HDV_Alchimistes_Bonta );
    	zaapis.add(HDV_Ames_Bonta );
    	zaapis.add(HDV_Bijoutiers_Bonta );
    	zaapis.add(HDV_Bricoleurs_Bonta );
    	zaapis.add(HDV_Bucherons_Bonta );
    	zaapis.add(HDV_Chasseurs_Bonta );
    	zaapis.add(HDV_Cordonniers_Bonta );
    	zaapis.add(HDV_Documents_Bonta );
    	zaapis.add(HDV_Faconneurs_Bonta );
    	zaapis.add(HDV_Forgerons_Bonta );
    	zaapis.add(HDV_Mineurs_Bonta );
    	zaapis.add(HDV_Parchemins_Bonta );
    	zaapis.add(HDV_Paysans_Bonta );
    	zaapis.add(HDV_Pecheurs_Bonta );
    	zaapis.add(HDV_Ressources_Bonta );
    	zaapis.add(HDV_Runes_Bonta );
    	zaapis.add(HDV_Sculpteurs_Bonta );
    	zaapis.add(HDV_Tailleurs_Bonta );
    	zaapis.add(Arene_Bonta );
    	zaapis.add(Banque_Bonta );
    	zaapis.add(Bibliotheque_Bonta );
    	zaapis.add(Epicerie_Bonta );
    	zaapis.add(Hotel_Metiers_Bonta );
    	zaapis.add(Milice_Bonta );
    	zaapis.add(Place_Marchande_Bonta );
    	zaapis.add(Taverne_Alakarte_Bonta );
    	zaapis.add(Taverne_Bagrutte_Bonta );
    	zaapis.add(Taverne_Chopenbois_Bonta );
    	zaapis.add(Taverne_Tabasse_Bonta );
    	zaapis.add(Taverne_Ferayeur_Bonta );
    	zaapis.add(Taverne_Feubuk_Bonta );
    	zaapis.add(Taverne_Pinchaut_Bonta );
    	zaapis.add(Tour_Ordes_Bonta );
    	zaapis.add(Zaap_Bonta );
        return zaapis;
    }

    /**
     * Returns the list of all the zaaps in Brakmar.
     * @return List of zaapis.
     */
    public final static ArrayList<Transport> getAllBrakmar(){
    	ArrayList<Transport> zaapis = new ArrayList<Transport>();
    	zaapis.add(Atelier_Alchimistes_Brakmar);
        zaapis.add(Atelier_Bijoutiers_Brakmar);
        zaapis.add(Atelier_Bricoleurs_Brakmar);
        zaapis.add(Atelier_Chasseurs_Brakmar);
        zaapis.add(Atelier_Cordonniers_Brakmar);
        zaapis.add(Atelier_Forgemages_Brakmar);
        zaapis.add(Atelier_Forgerons_Brakmar);
        zaapis.add(Atelier_Mineurs_Brakmar);
        zaapis.add(Atelier_Paysans_Brakmar);
        zaapis.add(Atelier_Pecheurs_Brakmar);
        zaapis.add(Atelier_Sculpteurs_Brakmar);
        zaapis.add(Atelier_Tailleurs_Brakmar);
        zaapis.add(HDV_Animaux_Brakmar);
        zaapis.add(HDV_Alchimistes_Brakmar);
        zaapis.add(HDV_Ames_Brakmar);
        zaapis.add(HDV_Bijoutiers_Brakmar);
        zaapis.add(HDV_Bricoleurs_Brakmar);
        zaapis.add(HDV_Bucherons_Brakmar);
        zaapis.add(HDV_Chasseurs_Brakmar);
        zaapis.add(HDV_Cordonniers_Brakmar);
        zaapis.add(HDV_Documents_Brakmar);
        zaapis.add(HDV_Faconneurs_Brakmar);
        zaapis.add(HDV_Forgerons_Brakmar);
        zaapis.add(HDV_Milice_Brakmar);
        zaapis.add(HDV_Parchemins_Lies_Brakmar);
        zaapis.add(HDV_Paysans_Brakmar);
        zaapis.add(HDV_Pecheurs_Brakmar);
        zaapis.add(HDV_Ressources_Brakmar);
        zaapis.add(HDV_Runes_Brakmar);
        zaapis.add(HDV_Sculpteurs_Brakmar);
        zaapis.add(HDV_Taileurs_Brakmar);
        zaapis.add(Arene_Brakmar);
        zaapis.add(Banque_Brakmar);
        zaapis.add(Bibliotheque_Brakmar);
        zaapis.add(Enclos_Public_Brakmar);
        zaapis.add(Hotel_Metier_Brakmar);
        zaapis.add(Milice_Brakmar);
        zaapis.add(Place_Marchande_Brakmar);
        zaapis.add(Sortie_Brakmar);
        zaapis.add(Taverne_Atolmond_Brakmar);
        zaapis.add(Taverne_Djaul_Brakmar);
        zaapis.add(Taverne_Misere_Brakmar);
        zaapis.add(Taverne_Bwork_Brakmar);
        zaapis.add(Taverne_Chabrule_Brakmar);
        zaapis.add(Tour_Brakmar_Brakmar);
        zaapis.add(Zaap_Brakmar);
        return zaapis;
    }

    /**
     * Returns the list of all the zaaps in the game.
     * @return List of zaapis.
     */
    public final static ArrayList<Transport> getAll(){
		ArrayList<Transport> zaapis = getAllBonta();
		zaapis.addAll(getAllBrakmar());
		return zaapis;
    }
    
    /**
     * Finds a zaapi from its location on the map.
     * @param position - Location of the zaap.
     * @return Corresponding zaapi, {@code null} if no zaapi exists at this location.
     */
    public final static Zaapi getZaapi(Point position){
		for(Transport zaapi: getAll()) {
			if(zaapi.getPosition().equals(position))
				return (Zaapi) zaapi;
		}
		return null;
    }

    /**
     * Finds a zaapi from its name.
     * @param name - Name of the zaapi.
     * @return Corresponding zaapi, {@code null} if no zaapi has this name.
     */
	public static Zaapi getZaapi(String name) {
		for(Transport zaapi: getAll()) {
			if(zaapi.getName().equals(name))
				return (Zaapi) zaapi;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see fr.B4D.transport.TransportInterface#goTo(java.awt.Point)
	 */
	public void goTo(Point destination) throws B4DException, StopProgramException, CancelProgramException {			
		B4D.mouse.leftClick(super.getPositionF(), false);
		B4D.screen.waitForColor(new PointF(0.4432, 0.7365), new Color(170, 200, 0), new Color(210, 255, 50), 10000);
		
	    switch(this.zaapiType) {
	    	case Atelier:
		        B4D.mouse.leftClick(new PointF(0.232,0.1627), false, 100);
		        break;
		    case HDV:
		    	B4D.mouse.leftClick(new PointF(0.232,0.1627), false, 100);
		    	break;
		    case Divers:
		    	B4D.mouse.leftClick(new PointF(0.6584,0.1627), false, 100);
		    	break;
			default:
				break;
	    }
	    B4D.mouse.leftClick(new PointF(0.6632,0.2006), false, 200);
	    B4D.keyboard.writeKeyboard(getZaapi(destination).getName());
	    B4D.mouse.doubleLeftClick(new PointF(0.46,0.2884), false);
	    B4D.screen.waitForMap(20000);
	}
}
