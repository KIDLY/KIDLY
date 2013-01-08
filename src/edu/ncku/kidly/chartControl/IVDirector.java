package edu.ncku.kidly.chartControl;

import java.util.ArrayList;

interface IVDraw {
	public void paint();
}
interface IVBuilder {
	void buildType(String a);
	void buildBackground();
	void buildTitle();
	void buildXY();
}


public class IVDirector {
	IVDirector(ArrayList<String> data,IVBuilder builder){
		
	}
}
