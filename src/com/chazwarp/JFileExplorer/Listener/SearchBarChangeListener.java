/**
@author Chaz Kerby
*/
package com.chazwarp.JFileExplorer.Listener;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.chazwarp.JFileExplorer.JFile;
import com.chazwarp.JFileExplorer.JFolder;
import com.chazwarp.JFileExplorer.JFrame.MainWindow;

public class SearchBarChangeListener implements DocumentListener {

	@Override
	public void changedUpdate(DocumentEvent de) {
		Search();
	}

	@Override
	public void insertUpdate(DocumentEvent de) {
		Search();
	}

	@Override
	public void removeUpdate(DocumentEvent de) {
		Search();
	}
	
	public void Search() {
		JTextField source = MainWindow.GetSearchBar();
		Object[] tempArray = MainWindow.GetFileArray();
		
		if(source.getText() != "Search" && !(source.getText().length() <= 0)) {
			for(int i=0; i < MainWindow.GetFileArray().length; i++) {
				if(tempArray[i] instanceof JFile) {
					JFile tempFile = (JFile)tempArray[i];

					if(!tempFile.getText().toLowerCase().contains(source.getText().toLowerCase())) {
						tempFile.setEnabled(false);
					}
				}
				else if(tempArray[i] instanceof JFolder) {
					JFolder tempFolder = (JFolder)tempArray[i];
					
					if(!tempFolder.getText().toLowerCase().contains(source.getText().toLowerCase())) {
						tempFolder.setEnabled(false);
					}
				}
			}
		}
		else if(source.getText() == "Search" || source.getText().length() <= 0) {
			for(int i=0; i < MainWindow.GetFileArray().length; i++) {
				if(tempArray[i] instanceof JFile) {
					JFile tempFile = (JFile)tempArray[i];
					tempFile.setEnabled(true);
				}
				else if(tempArray[i] instanceof JFolder) {
					JFolder tempFolder = (JFolder)tempArray[i];
					tempFolder.setEnabled(true);
				}
			}
		}
	}
}
