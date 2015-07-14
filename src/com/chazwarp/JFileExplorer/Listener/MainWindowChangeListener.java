/**
@author Chaz Kerby
*/
package com.chazwarp.JFileExplorer.Listener;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import com.chazwarp.JFileExplorer.JFrame.MainWindow;

public class MainWindowChangeListener implements WindowStateListener {

	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension screenSize = tk.getScreenSize();
	
	@Override
	public void windowStateChanged(WindowEvent evt) {
		int oldState = evt.getOldState();
        int newState = evt.getNewState();
		
        if ((oldState & Frame.MAXIMIZED_BOTH) == 0 && (newState & Frame.MAXIMIZED_BOTH) != 0) {
            MainWindow.getButtonPanel().setPreferredSize(new Dimension(screenSize.width -8, screenSize.height));
        } 
        else if ((oldState & Frame.MAXIMIZED_BOTH) != 0 && (newState & Frame.MAXIMIZED_BOTH) == 0) {
        	System.out.println("Frame was minimized");
        }
	}
}
