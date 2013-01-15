package XMLparser;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AreaChartParser extends Parser{

	public String materialSizeX;
	public String materialSizeY;
	public String dataBgName;
	public String chartBgName;
	public String x_AxisName;
	public String y_AxisName;
	public String categorySize;
	public String chartTitle;
	public String seriesPaint;
	
	public AreaChartParser(String xmlPath) {
		super(xmlPath);
		setparser(xmlPath);
	}

//	public void setparser(String xmlPath) {
//		try {
//			File setting = new File(xmlPath);
//			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
//					.newInstance();
//			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//			Document doc = dBuilder.parse(setting);
//			doc.getDocumentElement().normalize();
//
//			//System.out.println("root of xml file"
//			//		+ doc.getDocumentElement().getNodeName());
//			NodeList nodes = doc.getElementsByTagName("setting");
//			//System.out.println("==========================");
//
//			for (int i = 0; i < nodes.getLength(); i++) {
//				Node node = nodes.item(0);
//
//				if (node.getNodeType() == Node.ELEMENT_NODE) {
//					Element element = (Element) node;
//					materialSizeX = getValue("materialSizeX", element);
//					materialSizeY = getValue("materialSizeY", element);
//					dataBgName = getValue("dataBgName", element);
//					chartBgName = getValue("chartBgName", element);
//					x_AxisName = getValue("x_AxisName", element);
//					y_AxisName = getValue("y_AxisName", element);
//					categorySize = getValue("categorySize", element);
//					chartTitle = getValue("chartTitle", element);
//					seriesPaint = getValue("seriesPaint", element);
//					
//					System.out.println("materialSizeX: "
//							+ materialSizeX);
//					System.out.println("materialSizeY: "
//							+ materialSizeY);
//					System.out.println("dataBgName: "
//							+ dataBgName);
//					System.out.println("chartBgName: "
//							+ chartBgName);
//					System.out.println("x_AxisName: "
//							+ x_AxisName);
//					System.out.println("y_AxisName: "
//							+ y_AxisName);
//					System.out.println("categorySize: "
//							+ categorySize);
//					System.out.println("chartTitle: "
//							+ chartTitle);
//					System.out.println("seriesPaint: "
//							+ seriesPaint);
//				}
//			}
//		} catch (Exception ex) {
//			System.out.println("Type error");
//			//ex.printStackTrace();
//			
//		}
//	}

	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0)
				.getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}

}
