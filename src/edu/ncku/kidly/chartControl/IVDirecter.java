package edu.ncku.kidly.chartControl;

import java.util.ArrayList;

interface IVDraw {
	public void paint();
}
interface IVBuilder {
	void buildType();
	void buildBackground();
	void buildTitle();
	void buildXY();
	IVDraw showImage();
}
public class IVDirecter {
	IVDirecter(ArrayList<String> data,IVBuilder builder){
		
	}
}
