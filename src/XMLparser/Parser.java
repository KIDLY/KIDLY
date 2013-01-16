package XMLparser;

import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Parser {

	public String materialSizeX;
	public String materialSizeY;
	public String dataBgImg,dataBgColor,dataBgAlpha;
	public String chartBgImg,chartBgColor,chartBgAlpha;
	public String x_AxisName;
	public String y_AxisName;
	public String categorySize;
	public String chartTitle,titleFont,titleColor;
	public String titleSize,titleX,titleY;
	public String seriesPaint;
	public boolean check;
	public TextStruct[] texts = null;
	public SeriesStruct[] serieses = null;
	public NodeList pNodes;
	
	
	public Parser(String xmlPath) {
		
		check = setparser(xmlPath);
	}

	public boolean setparser(String xmlPath) {
		try {
			File setting = new File(xmlPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(setting);
			doc.getDocumentElement().normalize();

			//System.out.println("root of xml file"
			//		+ doc.getDocumentElement().getNodeName());
			NodeList nodes = doc.getElementsByTagName("CommonSetting");
			pNodes = doc.getElementsByTagName("PrivateSetting");
			//System.out.println("==========================");
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(0);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					
					materialSizeX = getValue("materialSizeX", element);
					materialSizeY = getValue("materialSizeY", element);
					
					//BackGround
					dataBgImg = getValue("dataBgImg", element);
					chartBgImg = getValue("chartBgImg", element);
					dataBgColor = getValue("dataBgColor",element);
					chartBgColor = getValue("chartBgColor",element);
					dataBgAlpha = getValue("dataBgAlpha", element);
					chartBgAlpha = getValue("chartBgAlpha", element);
					
					//Title
					chartTitle = getValue("chartTitle", element);
					titleColor = getValue("titleColor", element);
					titleFont = getValue("titleFont", element);
					titleSize = getValue("titleSize", element);
					titleX = getValue("titleX", element);
					titleY = getValue("titleY", element);
					
					//Text
					NodeList textNode = element.getElementsByTagName("Texts");
					texts = new TextStruct[textNode.getLength()];
					for(int ia = 0; ia<textNode.getLength() ; ia++){
						
						Element te = (Element)textNode.item(ia);
						texts[ia] = new TextStruct();
						texts[ia].color = getValue("Color",te);
						texts[ia].font = getValue("Font",te);
						texts[ia].size = getValue("Size",te);
						texts[ia].text = getValue("Text",te);
						texts[ia].posX = getValue("PosX",te);
						texts[ia].posY = getValue("PosY",te);
					}
					
					//Series
					NodeList seNode = element.getElementsByTagName("Serieses");
					serieses = new SeriesStruct[seNode.getLength()];
					for(int ia = 0; ia<seNode.getLength() ; ia++){
						Element se = (Element)seNode.item(ia);
						serieses[ia] = new SeriesStruct();
						serieses[ia].color = getValue("Color",se);
						serieses[ia].font = getValue("Font",se);
						serieses[ia].size = getValue("Size",se);
						serieses[ia].id = getValue("ID",se);
					}
					
					
					
//					x_AxisName = getValue("x_AxisName", element);
//					y_AxisName = getValue("y_AxisName", element);
//					categorySize = getValue("categorySize", element);
					
//					seriesPaint = getValue("seriesPaint", element);
					
					System.out.println("materialSizeX: "
							+ materialSizeX);
					System.out.println("materialSizeY: "
							+ materialSizeY);
					System.out.println("dataBgName: "
							+ dataBgImg);
					System.out.println("chartBgName: "
							+ chartBgImg);
					System.out.println("x_AxisName: "
							+ x_AxisName);
					System.out.println("y_AxisName: "
							+ y_AxisName);
					System.out.println("categorySize: "
							+ categorySize);
					System.out.println("chartTitle: "
							+ chartTitle);
					System.out.println("seriesPaint: "
							+ seriesPaint);
				}
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Type error");
			return false;
		}
	}

	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0)
				.getChildNodes();
		System.out.println("getValue"+nodes.item(0));
		if(nodes.item(0)!= null){
			Node node = (Node) nodes.item(0);
			System.out.println("getValueNode"+node.getNodeValue());
			return node.getNodeValue();
		}
		else{
			return "";
		}
			
		
	}

}
