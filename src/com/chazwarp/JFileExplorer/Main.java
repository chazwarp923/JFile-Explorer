/**
@author Chaz Kerby
*/
package com.chazwarp.JFileExplorer;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

import com.chazwarp.JFileExplorer.JFrame.MainWindow;
import com.chazwarp.JWarpCore.File.FileHelper;

public class Main {

	static JFrame mainWindow = null;
	public static XMLConfiguration config = null;
	static File folder = null;
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		try {
			config = createConfig();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		if(args.length > 0) {
			folder = new File(args[0]);
			createNewWindow(folder);		
		}
		else {
			createNewWindow(null);
		}
	}
	
	private static void createNewWindow(File f) {
		mainWindow = MainWindow.createWindow(f);
		mainWindow.setVisible(true);
	}
	
	private static XMLConfiguration createConfig() throws ConfigurationException {
		
		XMLConfiguration config = new XMLConfiguration();
		config.save(new File(FileHelper.getSaveDirectory("JFile Explorer", "config.xml")));
		
		config.load();
		
		return config;
	}
}
