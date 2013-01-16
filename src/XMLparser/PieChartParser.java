package XMLparser;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PieChartParser extends Parser{

	
	boolean checkPrivate;
	public String xAxisName,yAxisName;
	public String xAxisRange,yAxisRange;
	public String yUpperBound,yLowerBound;
	public String xAxisLinePaint,yAxisLinePaint,xAxisLineStroke,yAxisLineStroke;
	public String xLowerMargin,xUpperMargin,yLowerMargin,yUpperMargin;
	public String xLabelAngle,yLabelAngle;
	public String outlineStroke;
	
	
	
	public PieChartParser(String xmlPath) {
		super(xmlPath);
		setparser(xmlPath);
		checkPrivate = parsePrivate(super.pNodes);
		
	}

	private boolean parsePrivate(NodeList nodelist){
		Node node = nodelist.item(0);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			xAxisName = getValue("XAxisName",element);
			yAxisName = getValue("YAxisName",element);
			xAxisRange = getValue("XAxisRange",element);
			yAxisRange = getValue("YAxisRange",element);
			yUpperBound =  getValue("YUpperBound",element);
			yLowerBound = getValue("YLowerBound",element);
			xAxisLinePaint = getValue("XAxisLinePaint",element);
			yAxisLinePaint = getValue("YAxisLinePaint",element);
			xAxisLineStroke = getValue("XAxisLineStroke",element);
			yAxisLineStroke = getValue("YAxisLineStroke",element);
			xLowerMargin = getValue("XLowerMargin",element);
			xUpperMargin = getValue("XUpperMargin",element);
			yLowerMargin = getValue("YLowerMargin",element);
			yUpperMargin = getValue("YUpperMargin",element);
			xLabelAngle = getValue("XLabelAngle",element);
			yLabelAngle = getValue("YLabelAngle",element);
			outlineStroke = getValue("OutlineStroke",element);
			
			System.out.println("finishPrivate"+outlineStroke);
		}
		
		return true;
	}


}
