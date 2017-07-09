package layout;

public class SellStartPanel extends DefaultPane {

	public SellStartPanel(MainFrame _parent) {
		super(_parent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(int nowPanelNumber) {
		super.init(nowPanelNumber);
		parent.setTitle("A-POS : 판매");
	}
}
