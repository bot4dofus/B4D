package fr.B4D.transport.transports;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import fr.B4D.bot.B4D;
import fr.B4D.bot.statics.Keyboard;
import fr.B4D.bot.statics.Mouse;
import fr.B4D.dofus.B4DCannotFind;
import fr.B4D.modules.B4DWait;
import fr.B4D.transport.B4DWrongPosition;
import fr.B4D.transport.Transport;
import fr.B4D.transport.ZaapiType;
import fr.B4D.utils.PointF;

public class ZaapiBrakmar extends Transport implements Serializable{
	
	private static final long serialVersionUID = -5452793287981409711L;

	private ZaapiType zaapiType;
	
	  /****************/
	 /** COLLECTION **/
	/****************/
	
    public final static ZaapiBrakmar Atelier_Alchimistes = new ZaapiBrakmar("Atelier des alchimistes", new Point(-23, 40), new PointF(0.6741, 0.0731), ZaapiType.Atelier);
    public final static ZaapiBrakmar Atelier_Bijoutiers = new ZaapiBrakmar("Atelier des bijoutiers", new Point(-28, 39), new PointF(0.1075, 0.2154), ZaapiType.Atelier);
    public final static ZaapiBrakmar Atelier_Bricoleurs = new ZaapiBrakmar("Atelier des bricoleurs", new Point(-30, 35), new PointF(0.1629, 0.1693), ZaapiType.Atelier);
    public final static ZaapiBrakmar Atelier_Chasseurs = new ZaapiBrakmar("Atelier des chasseurs", new Point(-29, 32), new PointF(0.0473, 0.2826), ZaapiType.Atelier);
    public final static ZaapiBrakmar Atelier_Cordonniers = new ZaapiBrakmar("Atelier des cordonniers", new Point(-23, 36), new PointF(0.947, 0.523), ZaapiType.Atelier);
    public final static ZaapiBrakmar Atelier_Forgemages = new ZaapiBrakmar("Atelier des forgemages", new Point(-28, 36), new PointF(0.9278, 0.2164), ZaapiType.Atelier);
    public final static ZaapiBrakmar Atelier_Forgerons = new ZaapiBrakmar("Atelier des forgerons", new Point(-21, 34), new PointF(0.5931, 0.3056), ZaapiType.Atelier);
    public final static ZaapiBrakmar Atelier_Mineurs = new ZaapiBrakmar("Atelier des mineurs", new Point(-20, 34), new PointF(0.1958, 0.7124), ZaapiType.Atelier);
    public final static ZaapiBrakmar Atelier_Paysans = new ZaapiBrakmar("Atelier des paysans", new Point(-23, 32), new PointF(0.0883, 0.2685), ZaapiType.Atelier);
    public final static ZaapiBrakmar Atelier_Pecheurs = new ZaapiBrakmar("Atelier des pecheurs", new Point(-20, 39), new PointF(0.5955, 0.1934), ZaapiType.Atelier);
    public final static ZaapiBrakmar Atelier_Sculpteurs = new ZaapiBrakmar("Atelier des sculpteurs", new Point(-21, 40), new PointF(0.5698, 0.488), ZaapiType.Atelier);
    public final static ZaapiBrakmar Atelier_Tailleurs = new ZaapiBrakmar("Atelier des tailleurs", new Point(-24, 36), new PointF(0.2937, 0.1252), ZaapiType.Atelier);
    public final static ZaapiBrakmar HDV_Animaux = new ZaapiBrakmar("Hôtel de vente des animaux", new Point(-32, 37), new PointF(0.7632, 0.1593), ZaapiType.HDV);
    public final static ZaapiBrakmar HDV_Alchimistes = new ZaapiBrakmar("Hôtel de vente des alchimistes", new Point(-24, 40), new PointF(0.768, 0.4028), ZaapiType.HDV);
    public final static ZaapiBrakmar HDV_Ames = new ZaapiBrakmar("Hôtel de vente des ames", new Point(-27, 38), new PointF(0.1934, 0.1303), ZaapiType.HDV);
    public final static ZaapiBrakmar HDV_Bijoutiers = new ZaapiBrakmar("Hôtel de vente des bijoutiers", new Point(-27, 39), new PointF(0.6228, 0.3577), ZaapiType.HDV);
    public final static ZaapiBrakmar HDV_Bricoleurs = new ZaapiBrakmar("Hôtel de vente des bricoleurs", new Point(-29, 34), new PointF(0.7656, 0.5811), ZaapiType.HDV);
    public final static ZaapiBrakmar HDV_Bucherons = new ZaapiBrakmar("Hôtel de vente des bucherons", new Point(-22, 40), new PointF(0.3748, 0.1543), ZaapiType.HDV);
    public final static ZaapiBrakmar HDV_Chasseurs = new ZaapiBrakmar("Hôtel de vente des chasseurs", new Point(-27, 32), new PointF(0.9061, 0.3126), ZaapiType.HDV);
    public final static ZaapiBrakmar HDV_Cordonniers = new ZaapiBrakmar("Hôtel de vente des cordonniers", new Point(-23, 37), new PointF(0.9374, 0.1553), ZaapiType.HDV);
    public final static ZaapiBrakmar HDV_Documents = new ZaapiBrakmar("Hôtel de vente des documents", new Point(-28, 37), new PointF(0.9053, 0.6152), ZaapiType.HDV);
    public final static ZaapiBrakmar HDV_Faconneurs = new ZaapiBrakmar("Hôtel de vente des faconneurs", new Point(-22, 32), new PointF(0.5506, 0.5311), ZaapiType.HDV);
    public final static ZaapiBrakmar HDV_Forgerons = new ZaapiBrakmar("Hôtel de vente des forgerons", new Point(-21, 35), new PointF(0.577, 0.0401), ZaapiType.HDV);
    public final static ZaapiBrakmar HDV_Milice = new ZaapiBrakmar("Hôtel de vente des mineurs", new Point(-20, 35), new PointF(0.7327, 0.1743), ZaapiType.HDV);
    public final static ZaapiBrakmar HDV_Parchemins_Lies = new ZaapiBrakmar("Hôtel de vente des parchemins lies", new Point(-26, 36), new PointF(0.9029, 0.2745), ZaapiType.HDV);
    public final static ZaapiBrakmar HDV_Paysans = new ZaapiBrakmar("Hôtel de vente des paysans", new Point(-24, 32), new PointF(0.2103, 0.2274), ZaapiType.HDV);
    public final static ZaapiBrakmar HDV_Pecheurs = new ZaapiBrakmar("Hôtel de vente des pecheurs", new Point(-19, 39), new PointF(0.455, 0.513), ZaapiType.HDV);
    public final static ZaapiBrakmar HDV_Ressources = new ZaapiBrakmar("Hôtel de vente des ressources", new Point(-25, 34), new PointF(0.3323, 0.3657), ZaapiType.HDV);
    public final static ZaapiBrakmar HDV_Runes = new ZaapiBrakmar("Hôtel de vente des runes", new Point(-28, 36), new PointF(0.9229, 0.2134), ZaapiType.HDV);
    public final static ZaapiBrakmar HDV_Sculpteurs = new ZaapiBrakmar("Hôtel de vente des sculptures", new Point(-21, 39), new PointF(0.1035, 0.1603), ZaapiType.HDV);
    public final static ZaapiBrakmar HDV_Taileurs = new ZaapiBrakmar("Hôtel de vente des tailleurs", new Point(-24, 37), new PointF(0.1918, 0.3327), ZaapiType.HDV);
    public final static ZaapiBrakmar Arene = new ZaapiBrakmar("Arene", new Point(-27, 38), new PointF(0.4582, 0.3417), ZaapiType.Divers);
    public final static ZaapiBrakmar Banque = new ZaapiBrakmar("Banque", new Point(-27, 35), new PointF(0.2632, 0.1052), ZaapiType.Divers);
    public final static ZaapiBrakmar Bibliotheque = new ZaapiBrakmar("Bibliotheque", new Point(-28, 37), new PointF(0.9101, 0.6292), ZaapiType.Divers);
    public final static ZaapiBrakmar Enclos_Public = new ZaapiBrakmar("Enclos public", new Point(-32, 37), new PointF(0.7769, 0.1513), ZaapiType.Divers);
    public final static ZaapiBrakmar Hotel_Metier = new ZaapiBrakmar("Hotel des metiers", new Point(-27, 34), new PointF(0.6845, 0.5351), ZaapiType.Divers);
    public final static ZaapiBrakmar Milice = new ZaapiBrakmar("Milice", new Point(-26, 36), new PointF(0.9029, 0.2745), ZaapiType.Divers);
    public final static ZaapiBrakmar Place_Marchande = new ZaapiBrakmar("Place Marchande", new Point(-27, 36), new PointF(0.2287, 0.1844), ZaapiType.Divers);
    public final static ZaapiBrakmar Sortie = new ZaapiBrakmar("Sortie", new Point(-26, 41), new PointF(0.8371, 0.3888), ZaapiType.Divers);
    public final static ZaapiBrakmar Taverne_Atolmond = new ZaapiBrakmar("Taverne Atolmond", new Point(-29, 32), new PointF(0.0506, 0.2846), ZaapiType.Divers);
    public final static ZaapiBrakmar Taverne_Djaul = new ZaapiBrakmar("Taverne de Djaul", new Point(-24, 35), new PointF(0.7913, 0.6503), ZaapiType.Divers);
    public final static ZaapiBrakmar Taverne_Misere = new ZaapiBrakmar("Taverne de la Misere", new Point(-22, 39), new PointF(0.4543, 0.0832), ZaapiType.Divers);
    public final static ZaapiBrakmar Taverne_Bwork = new ZaapiBrakmar("Taverne de Bwork", new Point(-22, 31), new PointF(0.6998, 0.4208), ZaapiType.Divers);
    public final static ZaapiBrakmar Taverne_Chabrule = new ZaapiBrakmar("Taverne de Chabrule", new Point(-26, 41), new PointF(0.8371, 0.3888), ZaapiType.Divers);
    public final static ZaapiBrakmar Tour_Brakmar = new ZaapiBrakmar("Tour de Brakmar", new Point(-26, 34), new PointF(0.939, 0.5641), ZaapiType.Divers);
    public final static ZaapiBrakmar Zaap = new ZaapiBrakmar("Zaap", new Point(-26, 35), new PointF(0.7713, 0.3627), ZaapiType.Divers);
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public ZaapiBrakmar(String name, Point position, PointF positionF, ZaapiType zaapiType) {
		super(name, position, positionF, zaapiBrakmarCost);
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
        zaapis.add(HDV_Milice);
        zaapis.add(HDV_Parchemins_Lies);
        zaapis.add(HDV_Paysans);
        zaapis.add(HDV_Pecheurs);
        zaapis.add(HDV_Ressources);
        zaapis.add(HDV_Runes);
        zaapis.add(HDV_Sculpteurs);
        zaapis.add(HDV_Taileurs);
        zaapis.add(Arene);
        zaapis.add(Banque);
        zaapis.add(Bibliotheque);
        zaapis.add(Enclos_Public);
        zaapis.add(Hotel_Metier);
        zaapis.add(Milice);
        zaapis.add(Place_Marchande);
        zaapis.add(Sortie);
        zaapis.add(Taverne_Atolmond);
        zaapis.add(Taverne_Djaul);
        zaapis.add(Taverne_Misere);
        zaapis.add(Taverne_Bwork);
        zaapis.add(Taverne_Chabrule);
        zaapis.add(Tour_Brakmar);
        zaapis.add(Zaap);
        return zaapis;
    }
        
    public final static ZaapiBrakmar getZaap(Point position) throws B4DCannotFind{
    	return (ZaapiBrakmar) getAll().stream().filter(z -> z.getPosition().equals(position)).findFirst().orElseThrow(B4DCannotFind::new);
    }
	
	public static ZaapiBrakmar getZaap(String nom) throws B4DCannotFind {
		return (ZaapiBrakmar) getAll().stream().filter(z -> z.getName().equals(nom)).findFirst().orElseThrow(B4DCannotFind::new);
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public void goTo(Point destination) throws AWTException, B4DCannotFind, B4DWrongPosition {
		if (!B4D.getTeam().get(0).getPosition().equals(this.getPosition()))
			throw new B4DWrongPosition();
		
		Mouse.leftClick(super.getPositionF(), false);
	    B4DWait.waitForColor(new PointF(0.4472, 0.7367), new Color(186, 125, 0), new Color(255, 255, 50), 10);
	    switch(this.zaapiType) {
		 	case Atelier:
		   		Mouse.leftClick(new PointF(0.3253, 0.1626), false, 0.1);
		   		break;
		   	case HDV:
		   		Mouse.leftClick(new PointF(0.3253, 0.1626), false, 0.1);
		   		break;
		   	case Divers:
		   		Mouse.leftClick(new PointF(0.6027, 0.1626), false, 0.1);
		   		break;
		   	default:
				break;
	    }
	    
	    Mouse.leftClick(new PointF(0.6062, 0.2013), false, 0.2);
	    Keyboard.writeKeyboard(getZaap(destination).getName());
	    Mouse.doubleLeftClick(new PointF(0.4736, 0.2891), false);
	    
	    B4DWait.waitForMap();
	    B4D.getTeam().get(0).setPosition(destination);
	}
}
