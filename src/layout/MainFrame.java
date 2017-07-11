package layout;

import java.awt.MenuItem;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import data.CustomMenuItem;
import data.DBConnection;

public class MainFrame extends JFrame {
	//panels
	public static final int FRAMENUMBER_MAINMENU			= 0;
	public static final int FRAMENUMBER_SELLSTART			= 1;
	public static final int FRAMENUMBER_MENUMANAGE	= 2;
	public static final int FRAMENUMBER_INVENTORY			= 3;
	
	//Frame
	public int nowPanelNumber = 0;
	
	public ArrayList<DefaultPane> panels = new ArrayList<DefaultPane>();

	//DB
	public DBConnection dbConn = null;
	
	//login
	public boolean isLogin = false;
	private String ID = null;
	private String PW = null;
	
	//inventory db model
	public String[] inventory_title = {"번호","재료명","단위","기준재고","현재재고","부족재고"};
	public DefaultTableModel inventory_model = new DefaultTableModel(inventory_title,0);
	
	//alert inventory
	public boolean isAlert = false;
	
	//menu db model
	public String[] menu_title = {"번호", "메뉴", "가격", "레시피"};
	public DefaultTableModel menu_model = new DefaultTableModel(menu_title, 0);
	
	public static ArrayList<CustomMenuItem> itemList = new ArrayList<CustomMenuItem>();
	
	public MainFrame() {
		setSize(1024, 720);
		
		panels.add(new MainMenuPanel(this));
		panels.add(new SellStartPanel(this));
		panels.add(new MenuManagePanel(this));
		panels.add(new InventoryPanel(this));
		
		add(panels.get(FRAMENUMBER_MAINMENU));
		
		setTitle("A-Pos");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void changePanel(int panelNumber) {
		getContentPane().removeAll();
		getContentPane().add(panels.get(panelNumber));
		revalidate();
		repaint();

		panels.get(panelNumber).init(panelNumber);
	}
	
	public void DBConnect() {
		dbConn = new DBConnection();
	}
	
	public void setID(String _id){ID = _id;}
	public String getID(){return ID;}
	public void setPW(String _pw){PW = _pw;}
	public String getPW(){return PW;}
	
	public static void main(String[] args) {
		new MainFrame();
	}

}
