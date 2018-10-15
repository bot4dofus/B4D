package fr.B4D.classes.transports;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import fr.B4D.classes.Bot;
import fr.B4D.classes.PointF;
import fr.B4D.enu.ZaapiType;
import fr.B4D.exceptions.B4DCannotFind;
import fr.B4D.modules.B4DKeyboard;
import fr.B4D.modules.B4DMouse;
import fr.B4D.modules.B4DWait;

public class ZaapiBonta extends Transport{
	
	private static final long serialVersionUID = -8407002185667801637L;

	private ZaapiType zaapiType;
	
	  /****************/
	 /** COLLECTION **/
	/****************/
	
    public final static ZaapiBonta Atelier_Alchimistes = new ZaapiBonta("Atelier des alchimistes", new Point(-29, -53), new PointF(0.2645883, 0.2385229), ZaapiType.Atelier);
    public final static ZaapiBonta Atelier_Bijoutiers = new ZaapiBonta("Atelier des bijoutiers", new Point(-34, -61), new PointF(0.1582734, 0.1337325), ZaapiType.Atelier);
    public final static ZaapiBonta Atelier_Bricoleurs = new ZaapiBonta("Atelier des bricoleurs ", new Point(-26, -60), new PointF(0.7082334, 0.743513), ZaapiType.Atelier);
    public final static ZaapiBonta Atelier_Chasseurs = new ZaapiBonta("Atelier des chasseurs", new Point(-32, -52), new PointF(0.6714628, 0.3662675), ZaapiType.Atelier);
    public final static ZaapiBonta Atelier_Cordonniers = new ZaapiBonta("Atelier des cordonniers", new Point(-29, -58), new PointF(0.6402878, 0.5668663), ZaapiType.Atelier);
    public final static ZaapiBonta Atelier_Forgemages = new ZaapiBonta("Atelier des forgemages", new Point(-32, -55), new PointF(0.4300559, 0.2894212), ZaapiType.Atelier);
    public final static ZaapiBonta Atelier_Forgerons = new ZaapiBonta("Atelier des forgerons", new Point(-28, -55), new PointF(0.3405276, 0.3003992), ZaapiType.Atelier);
    public final static ZaapiBonta Atelier_Mineurs = new ZaapiBonta("Atelier des mineurs", new Point(-27, -57), new PointF(0.5363709, 0.3233533), ZaapiType.Atelier);
    public final static ZaapiBonta Atelier_Paysans = new ZaapiBonta("Atelier des paysans", new Point(-29, -50), new PointF(0.676259, 0.3013972), ZaapiType.Atelier);
    public final static ZaapiBonta Atelier_Pecheurs = new ZaapiBonta("Atelier des pecheurs", new Point(-36, -52), new PointF(0.7729816, 0.244511), ZaapiType.Atelier);
    public final static ZaapiBonta Atelier_Sculpteurs = new ZaapiBonta("Atelier des sculpteurs", new Point(-28, -61), new PointF(0.6083133, 0.1007984), ZaapiType.Atelier);
    public final static ZaapiBonta Atelier_Tailleurs = new ZaapiBonta("Atelier des tailleurs", new Point(-29, -56), new PointF(0.8832934, 0.1756487), ZaapiType.Atelier);
    public final static ZaapiBonta HDV_Animaux = new ZaapiBonta("Hôtel de vente des animaux", new Point(-36, -56), new PointF(0.2965628, 0.1616766), ZaapiType.HDV);
    public final static ZaapiBonta HDV_Alchimistes = new ZaapiBonta("Hôtel de vente des alchimistes", new Point(-30, -53), new PointF(0.7034373, 0.1926148), ZaapiType.HDV);
    public final static ZaapiBonta HDV_Ames = new ZaapiBonta("Hôtel de vente des âmes", new Point(-32, -57), new PointF(0.4060751, 0.2355289), ZaapiType.HDV);
    public final static ZaapiBonta HDV_Bijoutiers = new ZaapiBonta("Hôtel de vente des bijoutiers", new Point(-33, -59), new PointF(0.9104716, 0.2045908), ZaapiType.HDV);
    public final static ZaapiBonta HDV_Bricoleurs = new ZaapiBonta("Hôtel de vente des bricoleurs", new Point(-26, -60), new PointF(0.7034373, 0.7205589), ZaapiType.HDV);
    public final static ZaapiBonta HDV_Bucherons = new ZaapiBonta("Hôtel de vente des bûcherons", new Point(-30, -60), new PointF(0.2589928, 0.1666667), ZaapiType.HDV);
    public final static ZaapiBonta HDV_Chasseurs = new ZaapiBonta("Hôtel de vente des chasseurs", new Point(-32, -51), new PointF(0.4980016, 0.6477046), ZaapiType.HDV);
    public final static ZaapiBonta HDV_Cordonniers = new ZaapiBonta("Hôtel de vente des cordonniers", new Point(-30, -58), new PointF(0.7122302, 0.250499), ZaapiType.HDV);
    public final static ZaapiBonta HDV_Documents = new ZaapiBonta("Hôtel de vente des documents", new Point(-33, -58), new PointF(0.41247, 0.7015968), ZaapiType.HDV);
    public final static ZaapiBonta HDV_Faconneurs = new ZaapiBonta("Hôtel de vente des façonneurs", new Point(-27, -51), new PointF(0.1231015, 0.3622755), ZaapiType.HDV);
    public final static ZaapiBonta HDV_Forgerons = new ZaapiBonta("Hôtel de vente des forgerons", new Point(-28, -55), new PointF(0.4796163, 0.6986028), ZaapiType.HDV);
    public final static ZaapiBonta HDV_Mineurs = new ZaapiBonta("Hôtel de vente des mineurs", new Point(-26, -55), new PointF(0.3653077, 0.3542914), ZaapiType.HDV);
    public final static ZaapiBonta HDV_Parchemins = new ZaapiBonta("Hôtel de vente des parchemins lies", new Point(-33, -56), new PointF(0.8776978, 0.4730539), ZaapiType.HDV);
    public final static ZaapiBonta HDV_Paysans = new ZaapiBonta("Hôtel de vente des paysans", new Point(-28, -52), new PointF(0.3900879, 0.5089821), ZaapiType.HDV);
    public final static ZaapiBonta HDV_Pecheurs = new ZaapiBonta("Hôtel de vente des pêcheurs", new Point(-36, -53), new PointF(0.4388489, 0.6297405), ZaapiType.HDV);
    public final static ZaapiBonta HDV_Ressources = new ZaapiBonta("Hôtel de vente des ressources", new Point(-33, -54), new PointF(0.2486011, 0.2774451), ZaapiType.HDV);
    public final static ZaapiBonta HDV_Runes = new ZaapiBonta("Hôtel de vente des runes", new Point(-32, -55), new PointF(0.4300559, 0.2694611), ZaapiType.HDV);
    public final static ZaapiBonta HDV_Sculpteurs = new ZaapiBonta("Hôtel de vente des sculpteurs", new Point(-29, -59), new PointF(0.3828937, 0.1057884), ZaapiType.HDV);
    public final static ZaapiBonta HDV_Tailleurs = new ZaapiBonta("Hôtel de vente des tailleurs", new Point(-30, -56), new PointF(0.4204636, 0.506986), ZaapiType.HDV);
    public final static ZaapiBonta Arene = new ZaapiBonta("Arene", new Point(-32, -57), new PointF(0.3956835, 0.2265469), ZaapiType.Divers);
    public final static ZaapiBonta Banque = new ZaapiBonta("Banque", new Point(-31, -54), new PointF(0.8073541, 0.5688623), ZaapiType.Divers);
    public final static ZaapiBonta Bibliotheque = new ZaapiBonta("Bibliotheque", new Point(-33, -58), new PointF(0.4068745, 0.7095808), ZaapiType.Divers);
    public final static ZaapiBonta Epicerie = new ZaapiBonta("Epicerie", new Point(-33, -54), new PointF(0.2318145, 0.2654691), ZaapiType.Divers);
    public final static ZaapiBonta Hotel_Metiers = new ZaapiBonta("Hotel des metiers", new Point(-33, -55), new PointF(0.86251, 0.5638723), ZaapiType.Divers);
    public final static ZaapiBonta Milice = new ZaapiBonta("Milice", new Point(-55, -56), new PointF(0.7505995, 0.498004), ZaapiType.Divers);
    public final static ZaapiBonta Place_Marchande = new ZaapiBonta("Place marchande", new Point(-32, -54), new PointF(0.3661071, 0.2734531), ZaapiType.Divers);
    public final static ZaapiBonta Taverne_Alakarte = new ZaapiBonta("Taverne Alakarte", new Point(-29, -57), new PointF(0.7418066, 0.50998), ZaapiType.Divers);
    public final static ZaapiBonta Taverne_Bagrutte = new ZaapiBonta("Taverne de la Bagrutte", new Point(-34, -60), new PointF(0.6003197, 0.6556886), ZaapiType.Divers);
    public final static ZaapiBonta Taverne_Chopenbois = new ZaapiBonta("Taverne de la Chopenbois", new Point(-29, -61), new PointF(0.6506795, 0.5259481), ZaapiType.Divers);
    public final static ZaapiBonta Taverne_Tabasse = new ZaapiBonta("Taverne de la Tabasse", new Point(-34, -50), new PointF(0.6666667, 0.3752495), ZaapiType.Divers);
    public final static ZaapiBonta Taverne_Ferayeur = new ZaapiBonta("Taverne de la Ferayeur", new Point(-26, -58), new PointF(0.656275, 0.5489022), ZaapiType.Divers);
    public final static ZaapiBonta Taverne_Feubuk = new ZaapiBonta("Taverne du Feubuk", new Point(-27, -56), new PointF(0.7130296, 0.3063872), ZaapiType.Divers);
    public final static ZaapiBonta Taverne_Pinchaut = new ZaapiBonta("Taverne du Pinchaut", new Point(-28, -50), new PointF(0.6091127, 0.4361278), ZaapiType.Divers);
    public final static ZaapiBonta Tour_Ordes = new ZaapiBonta("Tour des ordres", new Point(-34, -57), new PointF(0.9008793, 0.3932136), ZaapiType.Divers);
    public final static ZaapiBonta Zaap = new ZaapiBonta("Zaap", new Point(-32, -56), new PointF(0.09352518, 0.1417166), ZaapiType.Divers);
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public ZaapiBonta(String nom, Point position, PointF positionF, ZaapiType zaapiType) {
		super(nom, position, positionF);
		this.zaapiType = zaapiType;
	}
	
	  /************************/
	 /** METHODES STATIQUES **/
	/************************/
	
    public final static ArrayList<Transport> getAll(){
    	ArrayList<Transport> zaapis = new ArrayList<Transport>();
    	zaapis.add(Atelier_Alchimistes);
        zaapis.add(Atelier_Bijoutiers);
        zaapis.add(Atelier_Bricoleurs);
        zaapis.add(Atelier_Chasseurs);
        zaapis.add(Atelier_Cordonniers);
        zaapis.add(Atelier_Forgemages);
        zaapis.add(Atelier_Forgerons);
        zaapis.add(Atelier_Mineurs);
        zaapis.add(Atelier_Paysans);
        zaapis.add(Atelier_Pecheurs);
        zaapis.add(Atelier_Sculpteurs);
        zaapis.add(Atelier_Tailleurs);
        zaapis.add(HDV_Animaux);
        zaapis.add(HDV_Alchimistes);
        zaapis.add(HDV_Ames);
        zaapis.add(HDV_Bijoutiers);
        zaapis.add(HDV_Bricoleurs);
        zaapis.add(HDV_Bucherons);
        zaapis.add(HDV_Chasseurs);
        zaapis.add(HDV_Cordonniers);
        zaapis.add(HDV_Documents);
        zaapis.add(HDV_Faconneurs);
        zaapis.add(HDV_Forgerons);
        zaapis.add(HDV_Mineurs);
        zaapis.add(HDV_Parchemins);
        zaapis.add(HDV_Paysans);
        zaapis.add(HDV_Pecheurs);
        zaapis.add(HDV_Ressources);
        zaapis.add(HDV_Runes);
        zaapis.add(HDV_Sculpteurs);
        zaapis.add(HDV_Tailleurs);
        zaapis.add(Arene);
        zaapis.add(Banque);
        zaapis.add(Bibliotheque);
        zaapis.add(Epicerie);
        zaapis.add(Hotel_Metiers);
        zaapis.add(Milice);
        zaapis.add(Place_Marchande);
        zaapis.add(Taverne_Alakarte);
        zaapis.add(Taverne_Bagrutte);
        zaapis.add(Taverne_Chopenbois);
        zaapis.add(Taverne_Tabasse);
        zaapis.add(Taverne_Ferayeur);
        zaapis.add(Taverne_Feubuk);
        zaapis.add(Taverne_Pinchaut);
        zaapis.add(Tour_Ordes);
        zaapis.add(Zaap);
        return zaapis;
    }
        
    public final static ZaapiBonta getZaap(Point position) throws B4DCannotFind{
    	return (ZaapiBonta) getAll().stream().filter(z -> z.getPosition().equals(position)).findFirst().orElseThrow(B4DCannotFind::new);
    }
	
	public static ZaapiBonta getZaap(String nom) throws B4DCannotFind {
		return (ZaapiBonta) getAll().stream().filter(z -> z.getName().equals(nom)).findFirst().orElseThrow(B4DCannotFind::new);
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public void goTo(Point destination) throws AWTException, B4DCannotFind {
		if (Bot.configuration.persons.get(0).position.equals(this.getPosition())){
			B4DMouse.leftClick(super.getPositionF(), false);

	        if (B4DWait.waitForColor(new PointF(0.4472, 0.7367), new Color(186, 125, 0), new Color(255, 255, 50), 10)) {
	        	switch(this.zaapiType) {
		        	case Atelier:
		        		B4DMouse.leftClick(new PointF(0.3253, 0.1626), false, 0.1);
		        		break;
		        	case HDV:
		        		B4DMouse.leftClick(new PointF(0.3253, 0.1626), false, 0.1);
		        		break;
		        	case Divers:
		        		B4DMouse.leftClick(new PointF(0.6027, 0.1626), false, 0.1);
		        		break;
					default:
						break;
	          	}
	        }
	        B4DMouse.leftClick(new PointF(0.6062, 0.2013), false, 0.2);
	        B4DKeyboard.writeKeyboard(getZaap(destination).getName());
	        B4DMouse.doubleLeftClick(new PointF(0.4736, 0.2891), false);
	        B4DWait.waitForMap();
	        Bot.configuration.persons.get(0).position = destination;
		}
	}
}
