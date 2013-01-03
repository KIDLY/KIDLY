package KidlyGui;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class ImageSet {

	public ArrayList<KidlyImg> ImageList;

	public ImageSet() {
		ImageList = new ArrayList<KidlyImg>();
	}
	
	
	/**
	 * Add Image into ArrayList.
	 * Put parameters when you declare the KidlyImg.
	 * The parameter url is default.
	 * 
	 * @param url
	 */
	public void addImage(String url){
		KidlyImg img=new KidlyImg(url);
		ImageList.add(img);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
