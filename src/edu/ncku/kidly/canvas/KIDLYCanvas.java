package edu.ncku.kidly.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class KIDLYCanvas extends Canvas{
	BufferedImage mImage;
	int begin_x,width,begin_y,height;
	public KIDLYCanvas(){
		super();
		
	}
	public KIDLYCanvas(GraphicsConfiguration g){
		super(g);
	}
	//依指定大小繪製素材
	public void setImage(int scalewidth,int scaleheight,Image i){
		mImage = new BufferedImage(scalewidth,
				scaleheight,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = mImage.createGraphics();
		
        g2.drawImage((BufferedImage)i, 0,0,scalewidth,scaleheight, null);
//		this.mImage = (BufferedImage) i;
	}
	public void setRange(int beginx,int beginy,int width,int height){
		this.begin_x = beginx;
		this.width = width;
		this.begin_y = beginy;
		this.height = height;
	}
	public void paint(Graphics g)
	{
		Image img=null;
	    try {img=ImageIO.read(new File("bin/right.png"));}
	    catch(IOException e)
	      {System.out.println("ok");}
	    int nowx = 0,nowy = 0;
	    while(nowy < this.getHeight()){
	    	while(nowx < this.getWidth()){
	    		g.drawImage(mImage,nowx,nowy,this);
	    		nowx += mImage.getWidth();
	    	}
	    	nowy += mImage.getHeight();
	    	nowx = 0;
	    	
	    }

	}

}
