/**
@author Chaz Kerby
*/
package com.chazwarp.JFileExplorer.Listener;

import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import com.chazwarp.JFileExplorer.JFile;

public class JFileClickedListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent evt) {
		if(evt.getClickCount() == 2) {
			Desktop dt = Desktop.getDesktop();
			JFile source = (JFile)evt.getSource();
			
			try {
				dt.open(new File(source.GetLocation().getAbsolutePath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
