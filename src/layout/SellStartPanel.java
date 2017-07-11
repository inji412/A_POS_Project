package layout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import data.CustomMenuItem;

public class SellStartPanel extends DefaultPane {
	private JSplitPane totalPanel		= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	
	private JPanel leftPanel				= new JPanel(new BorderLayout());				//table
	private JPanel rightPanel				= new JPanel(new BorderLayout());				//detail info
	
	//left
	private JLabel timer				= new JLabel("시간");
	private String[] titles				= {"메뉴", "수량", "가격"};
	private DefaultTableModel model = new DefaultTableModel(titles, 0);
	private JTable sellListTable	= new JTable(model);
	private JLabel resultMoney	= new JLabel("합계 : ");
	
	//right
	private JPanel buttonsPanel	= new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JButton btnPay			= new JButton("결제");
	
	
	public SellStartPanel(MainFrame _parent) {
		super(_parent);
		
		
		
		totalPanel.setDividerSize(0);
		this.setRightComponent(totalPanel);
	}

	@Override
	public void init(int nowPanelNumber) {
		super.init(nowPanelNumber);
		parent.setTitle("A-POS : 판매");

		setLeft();
		setRight();
		
	}
	
	private void setLeft() {
		Font font = new Font("고딕", Font.BOLD, 15);
		//top
		JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
		timer.setFont(font);
		top.add(timer);

		//center
		JPanel center = new JPanel(new BorderLayout());
		JScrollPane scroll	= new JScrollPane(sellListTable);
		center.add(scroll);
		
		//bottom
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
		resultMoney.setFont(font);
		bottom.add(resultMoney);
		
		leftPanel.add(top, BorderLayout.NORTH);
		leftPanel.add(center, BorderLayout.CENTER);
		leftPanel.add(bottom, BorderLayout.SOUTH);
		totalPanel.setLeftComponent(leftPanel);
	}
	private void setRight() {
		for(int i = 0 ; i < 12 ; i++)
			parent.itemList.add(new CustomMenuItem("test"+i, 3000));
		
		//button add
		for(int i = 0 ; i < parent.itemList.size() ; i++) {
			buttonsPanel.add(parent.itemList.get(i));
		}
		
		rightPanel.add(buttonsPanel, BorderLayout.CENTER);
		rightPanel.add(btnPay, BorderLayout.SOUTH);
		
		totalPanel.setRightComponent(rightPanel);
	}
	
}
