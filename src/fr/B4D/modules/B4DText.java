package fr.B4D.modules;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class B4DText {
	
	public static void write(File file, String text) throws IOException {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write (text);
			writer.close();
	}
	
	public static String read(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String text = reader.readLine();
		reader.close();
		return text;
}
}
