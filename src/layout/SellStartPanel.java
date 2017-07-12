package layout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SellStartPanel extends DefaultPane implements Runnable{
	private JSplitPane totalPanel		= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	
	private JPanel leftPanel				= new JPanel(new BorderLayout());				//table
	private JPanel rightPanel				= new JPanel(new BorderLayout());				//detail info
	
	//left
	private Thread timerThread	= new Thread(this);
	private JLabel timer				= new JLabel("시간");
	private String[] titles				= {"메뉴", "수량", "가격"};
	private DefaultTableModel model = new DefaultTableModel(titles, 0);
	private JTable sellListTable	= new JTable(model);
	private JLabel resultMoney	= new JLabel("합계 : ");
	
	//right
	private JScrollPane scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
																				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);			// horizontal move fix
	private JPanel topButtonsPanel = new JPanel(new GridLayout(0, 4,5,5));
	
	//right_bottom
	private JButton btnPay			= new JButton("결제");
	
	
	public SellStartPanel(MainFrame _parent) {
		super(_parent);
		
		setLeft();
		setRight();
		
		totalPanel.setDividerSize(0);
		this.setRightComponent(totalPanel);
	}

	@Override
	public void init(int nowPanelNumber) {
		super.init(nowPanelNumber);
		parent.setTitle("A-POS : 판매");
		timerThread.start();
		
		//이전에 등록된 컴포넌트 제거
		topButtonsPanel.removeAll();
	
		//버튼들을 Panel에 등록
		for(int i = 0 ; i < parent.itemList.size() ; i++) {
			parent.itemList.get(i).addActionListener(action);
			topButtonsPanel.add(parent.itemList.get(i));
		}
		scroll.setViewportView(topButtonsPanel);
	}
	
	@Override
	public void close() {
		timerThread.interrupt();
		super.close();
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
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		btnPay.addActionListener(action);
		bottom.add(btnPay);
		
		rightPanel.add(scroll, BorderLayout.CENTER);
		rightPanel.add(bottom, BorderLayout.SOUTH);
		totalPanel.setRightComponent(rightPanel);
	}
	
	private ActionListener action = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// pay button action
			if(e.getActionCommand().equals(btnPay.getText())) {
				
			}
			else {		//menu buttons
				for(int i = 0 ; i < parent.itemList.size() ; i++) {
					if(e.getActionCommand().equals(parent.itemList.get(i).getText())) {
						
					}
				}
			}
		}
	};
	
	//show timer
		@Override
		public void run() {
			while(true) {
				Calendar cal = Calendar.getInstance();
				
				String strYMD = ""+cal.get(Calendar.YEAR)+"년 "+(cal.get(Calendar.MONTH)+1)+"월 "+cal.get(Calendar.DATE)+"일 ";
				Long lTime = cal.getTime().getTime();
				
				DateFormat df = new SimpleDateFormat("hh:mm:ss");
				String nowTime = df.format(lTime);
				timer.setText(strYMD + nowTime);
				revalidate();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
}
