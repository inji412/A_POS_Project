package layout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dialog.InventoryAddDialog;

public class InventoryPanel extends DefaultPane {
	private JPanel totalPanel		= new JPanel(new BorderLayout());
	
	private JPanel topPanel		= new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel centerPanel	= new JPanel(new BorderLayout());
	
	//top
	private JButton btnAdd			= new JButton("재료 추가");
	private JButton btnDel			= new JButton("재료 삭제");
	private JButton btnUpdate	= new JButton("재료 입고");
	//center
	private JTable inventoryTable	= new JTable();

	public InventoryPanel(MainFrame _parent) {
		super(_parent);
		
		setTop();
		setCenter();
		
		totalPanel.add(topPanel, BorderLayout.NORTH);
		totalPanel.add(centerPanel, BorderLayout.CENTER);
		
		this.setRightComponent(totalPanel);
	}
	private void setTop() {
		btnAdd.addActionListener(action);
		btnDel.addActionListener(action);
		btnUpdate.addActionListener(action);
		
		topPanel.add(btnAdd);
		topPanel.add(btnDel);
		topPanel.add(btnUpdate);
	}
	
	private void setCenter() {
		JScrollPane scroll = new JScrollPane(inventoryTable);
		centerPanel.add(scroll);
	}
	@Override
	public void init(int nowPanelNumber) {
		super.init(nowPanelNumber);
		parent.setTitle("A-POS : 재고관리");
		String[] type = {"g", "kg","ml", "L", "조각", "개"};
		
		
		
		inventoryTable.setModel(parent.inventory_model);
		inventoryTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JComboBox<String>(type)));
		inventoryTable.setColumnSelectionAllowed(true);
	}
	
	private ActionListener action = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(btnAdd.getText())) {
				InventoryAddDialog addDialog = new InventoryAddDialog(parent, 300, 170);
				addDialog.setVisible(true);
			}else if(e.getActionCommand().equals(btnDel.getText())) {
				
			}else if(e.getActionCommand().equals(btnUpdate.getText())) {
				
			}
		}
	};
}
