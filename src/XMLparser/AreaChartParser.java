package XMLparser;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AreaChartParser extends Parser{

	boolean checkPrivate;
	
	public AreaChartParser(String xmlPath) {
		super(xmlPath);
		setparser(xmlPath);
		parsePrivate(super.pNodes);
	}

	private boolean parsePrivate(NodeList nodelist){
		Node node = nodelist.item(0);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			
		}
		
		return true;
	}
}
