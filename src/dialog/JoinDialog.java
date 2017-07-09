package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import layout.MainFrame;

public class JoinDialog extends DefaultDialog {
	private JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel centerPanel = new JPanel(new BorderLayout());
	private JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	// top
//	private JTextField member_index = new JTextField(10);
	
	//center
	private JTextField nameField		= new JTextField(10);
	private String[] type_list				= {"직원", "관리자"};
	private JComboBox<String> type	= new JComboBox<String>(type_list) ;
	private JTextField idField				= new JTextField(20);
	private JPasswordField pwField	= new JPasswordField(20);
	
	//bottom
	private JButton btnJoin			= new JButton("가입");
	private JButton btnCancel		= new JButton("취소");

	public JoinDialog(MainFrame _parent, int _width, int _height) {
		super( _parent, _width, _height);
		setTitle("가입");
	}
	
//	private void setTop() {
//		member_index.setEditable(false);
//		
//		topPanel.add(new JLabel("사원번호"));
//		topPanel.add(member_index);
//	}
	private void setCenter() {
		JPanel center_topPane 	= new JPanel(new FlowLayout(FlowLayout.LEFT));
		center_topPane.add(new JLabel("이름"));
		center_topPane.add(nameField);
		center_topPane.add(new JLabel("유형"));
		center_topPane.add(type);
		
		JPanel center_centerPane 	= new JPanel(new FlowLayout(FlowLayout.LEFT));
		center_centerPane.add(new JLabel("ID  "));
		center_centerPane.add(idField);
		
		JPanel center_bottomPane 	= new JPanel(new FlowLayout(FlowLayout.LEFT));
		center_bottomPane.add(new JLabel("PW "));
		center_bottomPane.add(pwField);
		
		centerPanel.add(center_topPane, BorderLayout.NORTH);
		centerPanel.add(center_centerPane, BorderLayout.CENTER);
		centerPanel.add(center_bottomPane, BorderLayout.SOUTH);
	}
	private void setBottom() {
		btnJoin.addActionListener(action);
		btnCancel.addActionListener(action);
		
		bottomPanel.add(btnJoin);
		bottomPanel.add(btnCancel);
	}
	
	@Override
	public void setVisible(boolean b) {
//		setTop();
		setCenter();
		setBottom();
		
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		super.setVisible(b);
	}
	
	private ActionListener action = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(btnCancel.getText())) {
				dispose();
			}else if(e.getActionCommand().equals(btnJoin.getText())) {
				JOptionPane msgPane = new JOptionPane();
				//join complete
				boolean isJoined	= false;
				//db connect & save member in table
				String	name	= nameField.getText();
				int		nType	= type.getSelectedIndex(); 
				String	id			= idField.getText();
				String pw		= new String();
				
				// convert password to string
				for(int i = 0 ; i < pwField.getPassword().length ; i++)
					pw += pwField.getPassword()[i];
				
				//load member table & check same id
				ArrayList<String> saved_IDlist = new ArrayList<String>();
				
				
				
				
				for(int i = 0 ; i < saved_IDlist.size() ; i++) 
					if(saved_IDlist.get(i).equals(name)) 
						isJoined = true;
			
				if(!isJoined) {
					//save in table
					
					msgPane.showMessageDialog(parent, "가입이 완료되었습니다.");
					dispose();
				}else {
					msgPane.showMessageDialog(parent, "이미 존재하는 ID 입니다..");
					clearComponent();
				}
			}
		}
	};
	
	private void clearComponent() {
		nameField.setText("");
		idField.setText("");
		pwField.setText("");
	}
	
}
