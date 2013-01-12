package dot;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.CategoryToPieDataset;
import org.jfree.util.TableOrder;

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
		else if( chartType.equals("Pie Chart") ){
			CategoryToPieDataset categoryToPieDataset = new CategoryToPieDataset(dataSet, TableOrder.BY_ROW , 1);
			chart = ChartFactory.createPieChart("Label",categoryToPieDataset,true,true,false);
		}//else if( chartType.equals("Histogram") )
		//	chart = ChartFactory.createHistogram("Label","X","Y",dataSet,PlotOrientation.VERTICAL,true,true,false);
		else
			return null;
		return chart;
		
	}
	
}
	