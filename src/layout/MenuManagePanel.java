package layout;

public class MenuManagePanel extends DefaultPane {

	public MenuManagePanel(MainFrame _parent) {
		super(_parent);
		
	}
	
	@Override
	public void init(int nowPanelNumber) {
		super.init(nowPanelNumber);
		parent.setTitle("A-POS : 메뉴관리");
	}

}
