/**
@author Chaz Kerby
*/
package com.chazwarp.JFileExplorer.Helper;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class IconHelper {

	public static void setWindowIcon(JFrame currentFrame, String iconPath) {
		
		try {
			currentFrame.setIconImage(CreateImageIcon(iconPath).getImage());
		}
		catch(NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public static ImageIcon CreateImageIcon(String path) {
		java.net.URL imgURL = IconHelper.class.getResource(path);
		if(imgURL != null) {
			return new ImageIcon(imgURL);
		}
		else {
			System.err.println("Couldn't Find File: " + path);
			return null;
		}
	}
	
}
