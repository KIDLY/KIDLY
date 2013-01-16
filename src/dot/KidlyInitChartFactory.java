package dot;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
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
	
	
	
	
	public JFreeChart createChart(String chartType,CategoryDataset dataSet,XMLparser.Parser mParser) {
		
		JFreeChart chart = null;
		ColorFactory colorFactory = new ColorFactory();
		
		
		if( chartType.equals("Bar Chart") )
			chart = ChartFactory.createBarChart(title,categoryAxisLabel,valueAxisLabel,dataSet,orientation,legend,tooltips,urls);
		else if( chartType.equals("Line Chart") )
			chart = ChartFactory.createLineChart(title,categoryAxisLabel,valueAxisLabel,dataSet,orientation,legend,tooltips,urls);
		else if( chartType.equals("Area Chart") )
			chart = ChartFactory.createAreaChart(title,categoryAxisLabel,valueAxisLabel,dataSet,orientation,legend,tooltips,urls);
		else if( chartType.equals("Pie Chart") ){
			CategoryToPieDataset categoryToPieDataset = new CategoryToPieDataset(dataSet, TableOrder.BY_ROW , 1);
			chart = ChartFactory.createPieChart(title,categoryToPieDataset,legend,tooltips,urls);
		
			System.out.println(mParser.chartBgColor);
			System.out.println(mParser.chartBgColor.substring(0, 2));
		}
		else
			return null;
		
		// Set Bg
		
		//chartBgImg
		if(!mParser.chartBgColor.equals(""))
			chart.setBackgroundPaint(colorFactory.getColor(mParser.chartBgColor));
		
		//chartBgColor
		if (!mParser.chartBgImg.equals("")){
			
			BufferedImage image = null;
			
			try {
				image = ImageIO.read(new File(mParser.chartBgImg));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chart.setBackgroundImage(image);
		}
		
		//chartBgAlpha
		if(!mParser.chartBgAlpha.equals(""))
			chart.setBackgroundImageAlpha(Float.valueOf(mParser.chartBgAlpha));
		

		//dataBgImg
		if(!mParser.dataBgColor.equals(""))
			chart.getPlot().setBackgroundPaint(colorFactory.getColor(mParser.dataBgColor));
		
		//dataBgColor
		if (!mParser.dataBgImg.equals("")){
			
			BufferedImage image = null;
			
			try {
				image = ImageIO.read(new File(mParser.dataBgImg));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chart.getPlot().setBackgroundImage(image);
		}
		
		//dataBgAlpha
		if(!mParser.dataBgAlpha.equals(""))
			chart.getPlot().setBackgroundImageAlpha(Float.valueOf(mParser.dataBgAlpha));

		
		//Set Title
		TextTitle tt = new TextTitle(mParser.chartTitle);
        tt.setBackgroundPaint(colorFactory.getColor(mParser.titleColor));
        tt.setFont(new Font(mParser.titleFont,0,30));
        
        tt.setPaint(Color.blue);
        
        chart.setTitle(tt);
		
//		<chartTitle>Test chart</chartTitle>
//		<titleFont></titleFont>
//		<titleSize></titleSize>
//		<titleColor></titleColor>
//		<titleX></titleX>
//		<titleY></titleY>
//		
//		
//		//Set Text
//		<!-- Text -->
//		<Texts>
//		    <Text>
//		        <Text></Text>
//		        <Font></Font>
//		        <Size></Size>
//		        <Color></Color>
//		        <PosX></PosX>
//		        <PosY></PosY>
//		    </Text>
//		    <!-- Lots of Text -->
//		</Texts>
//		
//		//Set Series
//		<!-- Series -->
//		<Serieses>
//		    <Series>
//		        <ID></ID>
//		        <Font></Font>
//		        <Size></Size>
//		        <Color></Color>
//		    </Series>
//		    <!-- Lots of Series ..... -->
//		</Serieses>
		
		
		
		
		return chart;
		
	}
	
	
}
	