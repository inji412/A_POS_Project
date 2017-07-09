package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import layout.MainFrame;

public class InventoryAddDialog extends DefaultDialog {
	private JPanel topPanel		= new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel centerPanel	= new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel bottomPanel	= new JPanel(new BorderLayout());
	
	//top
	private JTextField nameField	= new JTextField(10);
	//center
	private JTextField standardField	= new JTextField(10);
	private String[] type = {"g", "kg","ml", "L", "조각", "개"};						//warning linked in inventory panel... 
	private JComboBox<String> typeCombo = new JComboBox<String>(type);
	
	//bottom
	private JTextField nowField		= new JTextField(10);
	private JButton btnAdd				= new JButton("추가");
	private JButton btnCancel			= new JButton("취소");
	
	public InventoryAddDialog(MainFrame _parent, int _width, int _height) {
		super(_parent, _width, _height);
	}

	@Override
	public void setVisible(boolean b) {
		setTop();
		setCenter();
		setBottom();
		
		this.add(topPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		super.setVisible(b);
	}
	
	private void setTop() {
		topPanel.add(new JLabel("재료이름"));
		topPanel.add(nameField);
	}
	private void setCenter() {
		centerPanel.add(new JLabel("기준재고"));
		centerPanel.add(standardField);
		centerPanel.add(typeCombo);
	}
	private void setBottom() {
		JPanel tempTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tempTop.add(new JLabel("현재재고"));
		tempTop.add(nowField);
		
		JPanel tempBottom = new JPanel(new FlowLayout());
		btnAdd.addActionListener(action);
		btnCancel.addActionListener(action);
		tempBottom.add(btnAdd);
		tempBottom.add(btnCancel);
		bottomPanel.add(tempTop, BorderLayout.NORTH);
		bottomPanel.add(tempBottom, BorderLayout.SOUTH);
	}
	
	private ActionListener action = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(btnAdd.getText())) {
				
			}else if(e.getActionCommand().equals(btnCancel.getText())) {
				dispose();
			}
		}
	};
}
