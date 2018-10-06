package fr.B4D.classes;

import java.io.File;

import javax.swing.UIManager;

import fr.B4D.gui.JFrame_B4D;
import fr.B4D.modules.B4DText;

public final class Bot {

    public static final String AdminMacAdresse = "24-0A-64-51-AB-D5";		//Adresse mac de l'admin
	public static final File initFile = new File("B4D.init");				//Fichier d'initialisation contenant le chemin vers le fichier de configuration
	
	public static File configurationFile = new File("Configuration.B4D");	//Fichier de configuration par defaut
	public static Configuration MyConfiguration = new Configuration();		//Créer la variable de type Configuration
    public static Programme MyProgramme;                            	  	//Créer la variable de type Programme
    public static Serialization ConfigurationSerialization;					//Objet de serialisation de la configuration
    
    //public static Monde MonMonde = new Monde();                           //Créer la variable de type Monde
    //public final ThreadProgramme As New Thread(AddressOf Programme_Test)  //Créer le thread pour le programme
    //public final ThreadClavier As New Thread(AddressOf Detection_Clavier)	//Créer le thread pour la detection des touches
    
	public static void main(String[] args) {
		try {
			if(!initFile.exists()) {
				initFile.createNewFile();
				B4DText.write(initFile, configurationFile.getAbsolutePath());
			}
			else
				configurationFile = new File(B4DText.read(initFile));

			ConfigurationSerialization = new Serialization("B4D", configurationFile);
			if(!configurationFile.exists()) {
				ConfigurationSerialization.Serialize(MyConfiguration);
			}
			else
				MyConfiguration = ConfigurationSerialization.Deserialize();
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFrame_B4D window = new JFrame_B4D();
			window.frame.setVisible(true);
						
			/*for(String arg:args)
				System.out.println(arg);*/
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
