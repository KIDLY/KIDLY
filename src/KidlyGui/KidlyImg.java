package KidlyGui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class KidlyImg implements GUIRequest {
	public Image img;
	public int width;
	public int height;
	public int angle = 0;
	public float scaleSize = 0;
	public int priority = 0;
	   
	/**
	 * Initialize the image type.
	 * Default that image is read by using toolkit.(It depends on how we get the Image.)
	 * 
	 * @param image_url
	 */
	public KidlyImg(String image_url) {

		Image image = Toolkit.getDefaultToolkit().getImage(image_url);
		ImageIcon icon = new ImageIcon(image);
		this.img = image;
		this.width = icon.getIconWidth();
		this.height = icon.getIconHeight();
		this.angle = angle;
		this.scaleSize = scaleSize;
		this.priority = priority;
		
	}

	/**
	 * Default the rotate method of KidlyImage.
	 * 
	 * @param angle
	 */
	@Override
	public void rotate(int angle) {
		// TODO Auto-generated method stub

	}

	/**
	 * Default the scale method of KidlyImage.
	 * 
	 * @param scaleSize
	 */
	@Override
	public void scale(float scaleSize) {
		width=(int) (width*scaleSize);
		height=(int)(height*scaleSize);
		img=(BufferedImage) img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}

	/**
	 * Default the setPriority method of KidlyImage.
	 * 
	 * @param priority
	 */
	@Override
	public void setPriority(int priority) {
		this.priority = priority;
	}

}
