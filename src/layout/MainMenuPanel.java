package layout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dialog.JoinDialog;

public class MainMenuPanel extends DefaultPane {
	private JPanel totalPanel = new JPanel(new BorderLayout());
	private JPanel topPane = new JPanel(new FlowLayout());
	private JPanel centerPane = new JPanel(new FlowLayout());
	private JPanel bottomPane = new JPanel(new GridLayout(1, 0, 50, 50));
	
	//top location
	private JTextField				inputID		= new JTextField(16);
	private JPasswordField	inputPW	= new JPasswordField(16);
	private JButton					btnLogin	= new JButton("로그인");
	private JButton					btnJoin	= new JButton("관리자/직원 등록");
	
	//bottom location
	private ArrayList<JButton> btnList = new ArrayList<JButton>();
	
	//dialog
	JoinDialog joinDialog 	= null;

	public MainMenuPanel(MainFrame _parent) {
		super(_parent);
		
		//rogin component
		setTopComponent();
		//menu buttons component
		setBottomComponent();
		
		//center image
		JLabel temp = new JLabel();
		temp.setIcon(new ImageIcon("./image/pos.png"));
		temp.setPreferredSize(new Dimension(300, 250));
		centerPane.add(temp);
		
		totalPanel.add(topPane, BorderLayout.NORTH);
		totalPanel.add(centerPane, BorderLayout.CENTER);
		totalPanel.add(bottomPane, BorderLayout.SOUTH);
		
		this.setRightComponent(totalPanel);
	}
	
	private void setTopComponent() {
		inputID.setPreferredSize(new Dimension(200, 30));
		inputPW.setPreferredSize(new Dimension(200, 30));
		btnJoin.setPreferredSize(new Dimension(200, 30));
		
		btnLogin.addActionListener(acion);
		btnJoin.addActionListener(acion);
		
		topPane.setBorder(BorderFactory.createEmptyBorder(50, 30, 30, 30));
		
		topPane.add(new JLabel("ID "));
		topPane.add(inputID);
		topPane.add(new JLabel("PW "));
		topPane.add(inputPW);
		topPane.add(btnLogin);
		topPane.add(btnJoin);
	}
	
	private void setBottomComponent() {
		btnList.add(new JButton("판매(영업시작)"));
		btnList.add(new JButton("메뉴관리"));
		btnList.add(new JButton("재고관리"));
		btnList.add(new JButton("매출관리"));
		
		Font font = new Font("고딕",Font.BOLD, 20);
		for(int i = 0 ; i < btnList.size() ; i++) {
			btnList.get(i).setFont(font);
			btnList.get(i).addActionListener(acion);
			btnList.get(i).setPreferredSize(new Dimension(200, 200));
			bottomPane.add(btnList.get(i));
		}
		btnList.get(btnList.size()-1).setEnabled(false);
		
		bottomPane.setBorder(BorderFactory.createEmptyBorder(0, 100, 100, 100));
	}
	
	private ActionListener acion = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(btnLogin.getText()) && !parent.isLogin) {
				//login
				parent.isLogin = true;
				return;
				
			}else if(e.getActionCommand().equals(btnJoin.getText())){
				//join
				//call join dialog
				joinDialog = new JoinDialog(parent,  300, 180);
				joinDialog.setVisible(true);
			} else if(!parent.isLogin) {
				// change panel
				for(int i = 0 ; i < btnList.size() ; i++) {
					if(e.getActionCommand().equals(btnList.get(i).getText())){
						parent.changePanel(i+1);
					}
				}
			}
			
		}
	};

	@Override
	public void init(int nowPanelNumber) {
		super.init(nowPanelNumber);
		parent.setTitle("A-POS : 메인");
	}
}
