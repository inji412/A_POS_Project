package data;

import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JButton;

public class CustomMenuItem extends JButton {
	class item{
		String	name;
		int		price;
		HashMap<String, Integer> recipe;
	}
	
	private item itemInfo = new item();
	
	public CustomMenuItem(String _name, int _price) {
		this.setText(_name +"\n"+_price);
		
		this.setPreferredSize(new Dimension(120, 150));
		
		itemInfo.name = _name;
		itemInfo.price = _price;
		itemInfo.recipe = new HashMap<String, Integer>();
	}
	
	public void addReciepe(String _metarial, int _value) {
		itemInfo.recipe.put(_metarial, _value);
	}
	public void setReciepe(HashMap<String, Integer> _hash) {
		itemInfo.recipe.putAll(_hash);
	}
	public item getItemInfo() {return itemInfo;}
}

