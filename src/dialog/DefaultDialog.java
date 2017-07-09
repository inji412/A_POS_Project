package dialog;

import java.awt.BorderLayout;

import javax.swing.JDialog;

import layout.MainFrame;

public class DefaultDialog extends JDialog {
	MainFrame parent = null;
	
	private int screen_width = 0;
	private int screen_height = 0;
	
	public DefaultDialog(MainFrame _parent, int _width, int _height) {
		parent = _parent;
		
		screen_width = _width;
		screen_height = _height;
		
		setModal(true);
		setLayout(new BorderLayout());
		setSize(screen_width, screen_height);
		setResizable(false);
	}
	
}
