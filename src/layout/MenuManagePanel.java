package layout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import data.Globar;

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
	private JTextField sourceNameField		= new JTextField(5);
	private JTextField sourceValueField		= new JTextField(5);
	private JComboBox<String> sourceMeasureField	= new JComboBox<String>(Globar.sourceMeasureList);
	private String[] recipeTitle						= {"재료명", "재료량", "단위"};
	private DefaultTableModel recipeModel	= new DefaultTableModel(recipeTitle, 0);
	private JTable recipeList							= new JTable(recipeModel);
	private JButton btnsourceAdd				= new JButton("추가");
	private JButton btnRecipeEditMode		= new JButton("선택 지우기");
	
	
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
		top.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
		top.add(btnAdd);
		top.add(btnDel);
		top.add(btnUpdate);
		
		//center
		JPanel center = new JPanel(new BorderLayout());
		center.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
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
		JPanel bottom = new JPanel(new BorderLayout());		bottom.setBorder(BorderFactory.createTitledBorder("레시피 재료추가"));
		JPanel bottom_top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bottom_center = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		bottom_top.add(new JLabel("재료명"));	bottom_top.add(sourceNameField);
		bottom_top.add(new JLabel("재료량"));	bottom_top.add(sourceValueField);
		bottom_top.add(new JLabel("단위"));	bottom_top.add(sourceMeasureField);
		bottom_top.add(btnsourceAdd);
		
		
		//bottom_add recipe 
		JScrollPane scroll = new JScrollPane(recipeList);
		scroll.setPreferredSize(new Dimension(200, 200));
		bottom_center.add(new JLabel("레시피"));
		bottom_center.add(scroll);
		bottom_center.add(btnRecipeEditMode);
		
		bottom.add(bottom_top, BorderLayout.CENTER);
		bottom.add(bottom_center, BorderLayout.SOUTH);
		
		rightPanel.add(top, BorderLayout.NORTH);
		rightPanel.add(center, BorderLayout.CENTER);
		rightPanel.add(bottom, BorderLayout.SOUTH);
		
		JPanel temp = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		temp.add(rightPanel);
		
		totalPanel.setRightComponent(temp);
	}
	
	

}
