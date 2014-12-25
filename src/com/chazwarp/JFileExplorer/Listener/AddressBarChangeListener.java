/**
@author Chaz Kerby
*/
package com.chazwarp.JFileExplorer.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JTextField;

public class AddressBarChangeListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent ae) {
		JTextField source = (JTextField)ae.getSource();
		File tempFile = new File(source.getText());
		
		if(!tempFile.isDirectory()) {
			tempFile = tempFile.getParentFile();
		}
		if(tempFile.exists()) {
			
		}
	}

}
