package fr.B4D.classes;

import java.io.File;

import javax.swing.UIManager;

import fr.B4D.gui.JFrame_B4D;
import fr.B4D.modules.B4DText;

public final class Bot {
	
    public static final String adminMacAdresse = "24-0A-64-51-AB-D5";		//Adresse mac de l'admin
	public static final File initFile = new File("B4D.init");				//Fichier d'initialisation contenant le chemin vers le fichier de configuration
    public static final World world = new World();                           //Créer la variable de type Monde
    
	public static JFrame_B4D window;										//Fenetre du bot
	
	public static File configurationFile = new File("Configuration.B4D");	//Fichier de configuration par defaut
	public static Configuration configuration = new Configuration();		//Créer la variable de type Configuration
	public static Serialization configurationSerialization;					//Objet de serialisation de la configuration
    
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

			configurationSerialization = new Serialization("B4D", configurationFile);
			if(!configurationFile.exists()) {
				configurationSerialization.Serialize(configuration);
			}
			else
				configuration = configurationSerialization.Deserialize();
			
			window = new JFrame_B4D();
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			window.frame.setVisible(true);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
