package layout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MenuManagePanel extends DefaultPane {

	private JSplitPane totalPanel		= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	
	private JPanel leftPanel				= new JPanel(new BorderLayout());				//table
	private JPanel rightPanel				= new JPanel(new BorderLayout());				//detail info
	
	//left
	private JTable menuTable		= new JTable();
	//right
	//right-top
	private JButton btnAdd			= new JButton("메뉴추가");
	private JButton btnDel			= new JButton("메뉴삭제");
	private JButton btnUpdate	= new JButton("메뉴수정");
	
	//right-center
	private JTextField menuNumField	= new JTextField(5);
	private JTextField menuNameField	= new JTextField(10);
	private JTextField menuPriceField	= new JTextField(10);
	
	//right-bottom
	private JTextField meterialNameField	= new JTextField(5);
	private JTextField meterialValueField	= new JTextField(5);
	private JTextArea recipeArea				= new JTextArea(15, 10);
	private JButton btnMeterialAdd			= new JButton("추가");
	private JButton btnRecipeEditMode	= new JButton("수정");
	
	
	public MenuManagePanel(MainFrame _parent) {
		super(_parent);
		setLeft();
		setRight();
		
		totalPanel.setDividerSize(0);
		totalPanel.setResizeWeight(1);
		this.setRightComponent(totalPanel);
	}
	
	@Override
	public void init(int nowPanelNumber) {
		super.init(nowPanelNumber);
		parent.setTitle("A-POS : 메뉴관리");
		
		menuTable.setModel(parent.menu_model);
	}
	
	private void setLeft() {
		
		JScrollPane scroll = new JScrollPane(menuTable);
		leftPanel.add(scroll);
		
		totalPanel.setLeftComponent(leftPanel);
	}
	private void setRight() {
		//top
		JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		top.add(btnAdd);
		top.add(btnDel);
		top.add(btnUpdate);
		
		//center
		JPanel center = new JPanel(new BorderLayout());
		JPanel center_top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel center_center = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel center_bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		menuNumField.setEditable(false);
		center_top.add(new JLabel("메뉴번호"));
		center_top.add(menuNumField);
		center_center.add(new JLabel("메뉴이름"));
		center_center.add(menuNameField);
		center_bottom.add(new JLabel("메뉴가격"));
		center_bottom.add(menuPriceField);
		
		center.add(center_top, BorderLayout.NORTH);
		center.add(center_center, BorderLayout.CENTER);
		center.add(center_bottom, BorderLayout.SOUTH);
		
		//bottom
		JPanel bottom = new JPanel(new BorderLayout());
		JPanel bottom_top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bottom_center = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		bottom_top.add(new JLabel("재료명"));	bottom_top.add(meterialNameField);
		bottom_top.add(new JLabel("재료량"));	bottom_top.add(meterialValueField);
		bottom_top.add(btnMeterialAdd);
		
		bottom_center.add(new JLabel("레시피"));
		bottom_center.add(recipeArea);
		bottom_center.add(btnRecipeEditMode);
		
		bottom.add(bottom_top, BorderLayout.NORTH);
		bottom.add(bottom_center, BorderLayout.CENTER);
		
		rightPanel.add(top, BorderLayout.NORTH);
		rightPanel.add(center, BorderLayout.CENTER);
		rightPanel.add(bottom, BorderLayout.SOUTH);
		
		JPanel temp = new JPanel(new FlowLayout(FlowLayout.RIGHT, 50, 30));
		temp.add(rightPanel);
		
		totalPanel.setRightComponent(temp);
	}
	
	

}
