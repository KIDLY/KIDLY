package edu.ncku.kidly.canvas;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class KIDLYDraw {
	private Font font = null; //字體
	private Graphics2D g = null;
	private BufferedImage mImage;
	int begin_x,width,begin_y,height;
	private int fontsize = 0;
	private int x = 0;
	private int y = 0;
	
	public KIDLYDraw(){
		
	}

	//讀取本機圖片
	public BufferedImage loadImageLocal(String imgName)
	{
		try
		{
			return ImageIO.read(new File(imgName));
		} catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}

	//讀取網路圖片
	public BufferedImage loadImageUrl(String imgName)
	{
		try
		{
			URL url = new URL(imgName);
			return ImageIO.read(url);
		} catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}

	//產生圖片
	public void writeImageLocal(String newImage, BufferedImage img)
	{
		if (newImage != null && img != null)
		{
			try
			{
				File outputfile = new File(newImage);
				ImageIO.write(img, "gif", outputfile);
			} catch (IOException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}

	//設定字體
	public void setFont(String fontStyle, int fontSize)
	{
		this.fontsize = fontSize;
		this.font = new Font(fontStyle, Font.PLAIN, fontSize);
	}

	//修改圖片
	public BufferedImage modifyImage(BufferedImage img, Object content, int x,int y)
	{
	
		try
		{
			int w = img.getWidth();
			int h = img.getHeight();
			g = img.createGraphics();
			g.setBackground(Color.WHITE);
			g.setColor(Color.RED);
			if (this.font != null)
			g.setFont(this.font);

			if (x >= h || y >= w)
			{
				this.x = h - this.fontsize + 2;
				this.y = w;
			} else
			{
				this.x = x;
				this.y = y;
			}
			if (content != null)
			{
				g.drawString(content.toString(), this.x, this.y);
			}
			g.dispose();
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	
		return img;
	}
	
	//依指定大小繪製素材,縮放的意思
	public void setImage(int scalewidth,int scaleheight,String url){
		Image i = null;
		try {
			i = ImageIO.read(new File(url));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mImage = new BufferedImage(scalewidth,
				scaleheight,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = mImage.createGraphics();
		//將讀入的新image放入指定大小的Image框框
        g2.drawImage((BufferedImage)i, 0,0,scalewidth,scaleheight, null);
	}
	
	//設定範圍
	public void setRange(int beginx,int beginy,int width,int height){
		this.begin_x = beginx;
		this.width = width;
		this.begin_y = beginy;
		this.height = height;
	}
	
	//繪製在指定圖片上
	public void drawOn(Graphics g)
	{
	    int nowx = begin_x,nowy = begin_y;
	    while(nowy < begin_y+ this.height){
	    	while(nowx < begin_x + this.width){
	    		g.drawImage(mImage,nowx,nowy,null);
	    		nowx += mImage.getWidth();
	    	}
	    	nowy += mImage.getHeight();
	    	nowx = begin_x;
	    	
	    }

	}
	
}