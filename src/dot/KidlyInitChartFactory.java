package dot;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.CategoryToPieDataset;
import org.jfree.util.TableOrder;

public class KidlyInitChartFactory {
	
	private String title = "Default Title";
    private String categoryAxisLabel = "Default Category Label";
    private String valueAxisLabel = "Default Value Label";
    private PlotOrientation orientation = PlotOrientation.VERTICAL;
    private boolean legend = true;
    private boolean tooltips = true;
    private boolean urls = false;

		
	public KidlyInitChartFactory() {
		
		super();
	
	}
	
	public JFreeChart createChart(String chartType,CategoryDataset dataSet,XMLparser.Parser mParser){
		
		JFreeChart chart = null;
		
		if( chartType.equals("Bar Chart") )
			chart = ChartFactory.createBarChart(title,categoryAxisLabel,valueAxisLabel,dataSet,orientation,legend,tooltips,urls);
		else if( chartType.equals("Line Chart") )
			chart = ChartFactory.createLineChart(title,categoryAxisLabel,valueAxisLabel,dataSet,orientation,legend,tooltips,urls);
		else if( chartType.equals("Area Chart") )
			chart = ChartFactory.createAreaChart(title,categoryAxisLabel,valueAxisLabel,dataSet,orientation,legend,tooltips,urls);
		else if( chartType.equals("Pie Chart") ){
			CategoryToPieDataset categoryToPieDataset = new CategoryToPieDataset(dataSet, TableOrder.BY_ROW , 1);
			chart = ChartFactory.createPieChart(mParser.chartTitle,categoryToPieDataset,legend,tooltips,urls);
		
			
			//Set Bg
			if(!mParser.chartBgColor.equals("")){
 
				int r = Integer.valueOf(mParser.chartBgColor.substring(0, 2));
				int g = Integer.valueOf(mParser.chartBgColor.substring(2, 4));
				int b = Integer.valueOf(mParser.chartBgColor.substring(4, 6));
				
				Color mColor = new Color(r,g,b);
				chart.setBackgroundPaint(mColor);
				
			}
			//JFreeChart.setBackgroundImage
			//plot.setBackgroundAlpha
			
			//Set Title
			
		
		}
		else
			return null;
		
		return chart;
		
	}
	
}
	