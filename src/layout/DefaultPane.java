package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import layout.MainFrame;

public class DefaultPane extends JSplitPane {
	public MainFrame parent = null;
	
	private JButton backButton = null;
	private JPanel top = new JPanel(new BorderLayout());
	private JLabel noticLabel = new JLabel();
	private JOptionPane msgPane = new JOptionPane();
	
	public DefaultPane(MainFrame _parent) {
		parent = _parent;
		
		backButton = new JButton("X");			//go main menu
		backButton.setBounds(0, 0, 20, 30);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(parent.isLogin && parent.nowPanelNumber == parent.FRAMENUMBER_MAINMENU) {
					//logout?
					if(showConfirmDialog("로그아웃 하시겠습니까?") == 0) {
						parent.dbConn.dbClose();
						parent.isLogin = false;
					}
				}
				else
					parent.changePanel(parent.FRAMENUMBER_MAINMENU);
			}
		});
		
		top.setBackground(Color.GRAY);
		top.add(noticLabel, BorderLayout.WEST);
		top.add(backButton, BorderLayout.EAST);
		
		this.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.setDividerLocation(backButton.getHeight());
		this.setDividerSize(1);
		this.setLeftComponent(top);
		this.setRightComponent(null);			//이곳을 매 페이지 마다 설정하면서 페이지 변화를 준다.
		this.setSize(parent.getSize());
	}
	
	public void setAlert(boolean _isAlert) {
		
		if(_isAlert) {
			noticLabel.setFont(new Font("고딕",Font.BOLD, 15));
			noticLabel.setText("부족한 재료가 있으니 확인해주세요.");
			noticLabel.setForeground(Color.WHITE);
			
			top.setBackground(Color.red);
		}else {
			top.setBackground(Color.GRAY);
		}
	}
	
	public void showMessageDialog(String msg){
		msgPane.showMessageDialog(parent, msg);
	}
	public int showConfirmDialog(String msg) {
		return msgPane.showConfirmDialog(parent, msg, "LogOut", JOptionPane.YES_NO_OPTION);
	}
	
	public void init(int nowPanelNumber) {parent.nowPanelNumber = nowPanelNumber;}
}
