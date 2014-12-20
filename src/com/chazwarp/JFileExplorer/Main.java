/**
@author Chaz Kerby
*/
package com.chazwarp.JFileExplorer;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.chazwarp.JFileExplorer.JFrame.MainWindow;

public class Main {

	static JFrame mainWindow = null;
	
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		CreateNewWindow();
	}
	
	private static void CreateNewWindow() {
		mainWindow = MainWindow.CreateWindow();
		mainWindow.setVisible(true);
	}

}
