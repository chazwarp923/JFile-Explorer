/**
@author Chaz Kerby
*/
package com.chazwarp.JFileExplorer.JFrame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import sun.awt.shell.ShellFolder;

import com.chazwarp.JFileExplorer.JFile;
import com.chazwarp.JFileExplorer.JFolder;
import com.chazwarp.JFileExplorer.Helper.IconHelper;
import com.chazwarp.JFileExplorer.Helper.Strings;
import com.chazwarp.JFileExplorer.Listener.AddressBarChangeListener;
import com.chazwarp.JFileExplorer.Listener.JFileClickedListener;
import com.chazwarp.JFileExplorer.Listener.SearchBarChangeListener;

public class MainWindow {

	static JFrame mainWindow = new JFrame("JFile Explorer");
	static JPanel mainPanel = new JPanel();
	static JPanel textFieldPanel = new JPanel(new FlowLayout());
	static JPanel buttonPanel = new JPanel(new FlowLayout());
	static JScrollPane scrollBars = new JScrollPane(mainPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	static Toolkit tk = Toolkit.getDefaultToolkit();
	static Dimension screenSize = tk.getScreenSize();
	static Object[] fileArray;
	static File currentFolder = new File(System.getProperty("user.home"));
	static JTextField addressBar = new JTextField();
	static JTextField searchBar = new JTextField("Search");
	
	public static JFrame CreateWindow() {
		
		IconHelper.setWindowIcon(mainWindow, Strings.RESOURCE_LOCATION + "folder-8x.png");
		
		currentFolder = new File("C:\\Users\\Chazk_000\\Desktop\\Games");
		CreateFiles();
		
		Dimension tempDim = addressBar.getPreferredSize();
		textFieldPanel.setMaximumSize(new Dimension(screenSize.width, tempDim.height));
		buttonPanel.setPreferredSize(new Dimension(screenSize.width -8, screenSize.height));
		addressBar.setText(currentFolder.getAbsolutePath());
		addressBar.addActionListener(new AddressBarChangeListener());
		addressBar.setMinimumSize(addressBar.getSize());
		searchBar.getDocument().addDocumentListener(new SearchBarChangeListener());
		searchBar.setMinimumSize(searchBar.getSize());
		textFieldPanel.add(addressBar);
		textFieldPanel.add(searchBar);
		
		mainPanel.add(textFieldPanel);
		mainPanel.add(buttonPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		scrollBars.getVerticalScrollBar().setUnitIncrement(60);
		mainWindow.add(scrollBars);
		
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension minSize = new Dimension(screenSize.width/2, screenSize.height/2);
		mainWindow.setMinimumSize(minSize);
		mainWindow.setLocationRelativeTo(null);//Centers The Window
		
		return mainWindow;
	}
	
	private static void CreateFiles() {
		
		File[] fileList = currentFolder.listFiles();
		fileArray = new Object[fileList.length];
		
		for(int i=0; i < fileList.length; i++) {
			if(fileList[i].isFile() && !fileList[i].isHidden()) {
				fileArray[i] = new JFile(GetFileIcon(fileList[i].getAbsolutePath()), new File(fileList[i].getAbsolutePath()));
			}
			else if(fileList[i].isDirectory()) {
				fileArray[i] = new JFolder(GetFileIcon(fileList[i].getAbsolutePath()), new File(fileList[i].getAbsolutePath()));
			}
		}
		
		for(int i=0; i < fileArray.length; i++) {
			if(fileArray[i] instanceof JFile) {
				JFile tempFile = (JFile)fileArray[i];
				tempFile.setText(tempFile.GetName());
				tempFile.setOpaque(false);
				tempFile.setPreferredSize(new Dimension(120, 120));
				tempFile.setIcon(GetResizedIcon(tempFile.GetIcon()));
				tempFile.addMouseListener(new JFileClickedListener());
				tempFile.setMargin(new Insets(0,0,0,0));
				tempFile.setVerticalTextPosition(SwingConstants.BOTTOM);
				tempFile.setHorizontalTextPosition(SwingConstants.CENTER);
				buttonPanel.add(tempFile);
			}
			else if(fileArray[i] instanceof JFolder) {
				JFolder tempFolder = (JFolder)fileArray[i];
				tempFolder.setText(tempFolder.GetName());
				tempFolder.setOpaque(false);
				tempFolder.setPreferredSize(new Dimension(120, 120));
				tempFolder.setIcon(GetResizedIcon(tempFolder.GetIcon()));
				tempFolder.setMargin(new Insets(0,0,0,0));
				tempFolder.setVerticalTextPosition(SwingConstants.BOTTOM);
				tempFolder.setHorizontalTextPosition(SwingConstants.CENTER);
				buttonPanel.add(tempFolder);
			}
		}
	}
	
	public static Object[] GetFileArray() {
		return fileArray;
	}
	
	public static JTextField GetSearchBar() {
		return searchBar;
	}
	
	private static Icon GetFileIcon(String path) {
		ShellFolder sf = null;
		try {
			sf = ShellFolder.getShellFolder(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Icon icon = new ImageIcon(sf.getIcon(true), sf.getFolderType());
		
		return icon;
	}
	
	private static Icon GetResizedIcon(Icon i) {
		ImageIcon tempIcon = (ImageIcon)i;
		Image tempImage = tempIcon.getImage();
		
		return new ImageIcon(tempImage.getScaledInstance(tempIcon.getIconHeight(), tempIcon.getIconWidth(), 0));
	}
}
