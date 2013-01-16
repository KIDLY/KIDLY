package dot;

import java.awt.Color;

public class ColorFactory {
	
	private Color mColor = null;
	
	public Color getColor(String code){
		
		if(code.length() == 7){
			int r = Integer.parseInt(code.substring(1, 3),16);
			int g = Integer.parseInt(code.substring(3, 5),16);
			int b = Integer.parseInt(code.substring(5, 7),16);
			mColor = new Color(r,g,b);
		}else
			return null;
		return mColor;
	}
	
}
