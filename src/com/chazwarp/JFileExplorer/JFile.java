/**
@author Chaz Kerby
*/
package com.chazwarp.JFileExplorer;

import java.io.File;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.filechooser.FileSystemView;

public class JFile extends JButton {
	
	private static final long serialVersionUID = 1L;
	private Icon myIcon;
	private String myName;
	private File myLocation;
	private FileSystemView fsv = FileSystemView.getFileSystemView();

	public JFile(Icon icon, File location) {
		super();
		
		myIcon = icon;
		myName = fsv.getSystemDisplayName(location);
		myLocation = location;
		
		/**
		myName = myName.replace(" ", "<br/>");
		myName = "<html>" + myName + "</html>";
		*/
	}
	
	public Icon GetIcon() {
		return myIcon;
	}
	
	public String GetName() {
		return myName;
	}
	
	public File GetLocation() {
		return myLocation;
	}
}
