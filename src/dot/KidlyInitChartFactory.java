package dot;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;

public class KidlyInitChartFactory {

	public KidlyInitChartFactory() {
		
		super();
	
	}
	
	public JFreeChart createChart(String chartType,CategoryDataset dataSet){
		
		JFreeChart chart = null;
		
		if( chartType.equals("Bar Chart") )
			chart = ChartFactory.createBarChart("Label","X","Y",dataSet,PlotOrientation.VERTICAL,true,true,false);
		else if( chartType.equals("Line Chart") )
			chart = ChartFactory.createLineChart("Label","X","Y",dataSet,PlotOrientation.VERTICAL,true,true,false);
		else if( chartType.equals("Area Chart") )
			chart = ChartFactory.createAreaChart("Label","X","Y",dataSet,PlotOrientation.VERTICAL,true,true,false);
		//else if( chartType.equals("Pie Chart") )
		//	chart = ChartFactory.createPieChart("Label","X","Y",dataSet,PlotOrientation.VERTICAL,true,true,false);
		//else if( chartType.equals("Histogram") )
		//	chart = ChartFactory.createHistogram("Label","X","Y",dataSet,PlotOrientation.VERTICAL,true,true,false);
		else
			return null;
		return chart;
		
	}
	
}
	