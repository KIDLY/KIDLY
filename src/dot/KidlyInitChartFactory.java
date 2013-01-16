package dot;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.CategoryToPieDataset;
import org.jfree.util.TableOrder;

import XMLparser.AreaChartParser;
import XMLparser.BarChartParser;
import XMLparser.LineChartParser;

public class KidlyInitChartFactory {
	
	private String title = "Default Title";
    private String categoryAxisLabel = "Default Category Label";
    private String valueAxisLabel = "Default Value Label";
    private PlotOrientation orientation = PlotOrientation.VERTICAL;
    private boolean legend = true;
    private boolean tooltips = true;
    private boolean urls = false;
    private ColorFactory colorFactory = null;
		
	public KidlyInitChartFactory() {
		
		super();
	
	}
	
	
	
	
	public JFreeChart createChart(String chartType,CategoryDataset dataSet,XMLparser.Parser mParser) {
		
		JFreeChart chart = null;
		colorFactory = new ColorFactory();
		
		
		if( chartType.equals("Bar Chart") ){
			chart = ChartFactory.createBarChart(title,mParser.xAxisName,mParser.yAxisName,dataSet,orientation,legend,tooltips,urls);
			setBarChart(chart,(BarChartParser) mParser);
		}
		else if( chartType.equals("Line Chart") ){
			chart = ChartFactory.createLineChart(title,mParser.xAxisName,mParser.yAxisName,dataSet,orientation,legend,tooltips,urls);
			setLineChart(chart,(LineChartParser) mParser);
		}
		else if( chartType.equals("Area Chart") ){
			chart = ChartFactory.createAreaChart(title,mParser.xAxisName,mParser.yAxisName,dataSet,orientation,legend,tooltips,urls);
			setAreaChart(chart,(AreaChartParser) mParser);
		}
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
		TextTitle tt = new TextTitle();
		if(!mParser.chartTitle.equals(""))
			tt.setText(mParser.chartTitle);
        
		if(!mParser.titleFont.equals(""))
			tt.setFont(new Font(mParser.titleFont,0,30));
        
		if(!mParser.titleColor.equals(""))	
			tt.setPaint(colorFactory.getColor(mParser.titleColor));
        
		chart.setTitle(tt);
		

	
		//Set Text
		//for (int i=0;i<Array.getLength(mParser.texts);i++){
		    
//		    mParser.texts[i].text
//			mParser.texts[i].size
//			mParser.texts[i].font
//			
//			mParser.texts[i].color
//			mParser.texts[i].posX
//			mParser.texts[i].posY
		//}


		//Set Series - Pie
		if(chartType.equals("Pie Chart")){
			for(int i=0;i<Array.getLength(mParser.serieses);i++){
				//Section Color
				if(!mParser.serieses[i].id.equals("") && !mParser.serieses[i].color.equals("")){
				
					int id = Integer.valueOf(mParser.serieses[i].id);
				
					if(id < dataSet.getColumnCount()){
						PiePlot piePlot = (PiePlot) chart.getPlot();
						piePlot.setSectionPaint(dataSet.getColumnKey(id), colorFactory.getColor(mParser.serieses[i].color));
					}
				}
				//mParser.serieses[i].font???
				//mParser.serieses[i].size??
			}
		}else{
			for(int i=0;i<Array.getLength(mParser.serieses);i++){
				//Series Color
				if(!mParser.serieses[i].id.equals("") && !mParser.serieses[i].color.equals("")){
					int id = Integer.valueOf(mParser.serieses[i].id);
					
					if(id < dataSet.getRowCount()){
						chart.getCategoryPlot().getRenderer().setSeriesPaint(id,colorFactory.getColor(mParser.serieses[i].color)); 	
					}
				}

			}			
		}
			
		return chart;
		
	}
	void setBarChart(JFreeChart chart,BarChartParser mParser){
		CategoryPlot plot = chart.getCategoryPlot();
		CategoryAxis domainAxis = plot.getDomainAxis();
		NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
		
		if(mParser.yUpperBound!="")rangeAxis.setUpperBound(Float.valueOf(mParser.yUpperBound));
		if(mParser.yLowerBound!="")rangeAxis.setLowerBound(Float.valueOf(mParser.yUpperBound));
		//deal with paint
		if(mParser.xAxisLinePaint!="")domainAxis.setAxisLinePaint(colorFactory.getColor(mParser.xAxisLinePaint));
		if(mParser.yAxisLinePaint!="")rangeAxis.setAxisLinePaint(colorFactory.getColor(mParser.yAxisLinePaint));
		
		if(mParser.xAxisLineStroke!="")domainAxis.setAxisLineStroke(new BasicStroke(Integer.valueOf(mParser.xAxisLineStroke)));
		if(mParser.yAxisLineStroke!="")rangeAxis.setAxisLineStroke(new BasicStroke(Integer.valueOf(mParser.yAxisLineStroke)));
		if(mParser.xLowerMargin!="")domainAxis.setLowerMargin(Float.valueOf(mParser.xLowerMargin));
		if(mParser.yLowerMargin!="")rangeAxis.setLowerMargin(Float.valueOf(mParser.yLowerMargin));
		if(mParser.xUpperMargin!="")domainAxis.setUpperMargin(Float.valueOf(mParser.xUpperMargin));
		if(mParser.yUpperMargin!="")rangeAxis.setUpperMargin(Float.valueOf(mParser.yUpperMargin));
		if(mParser.xLabelAngle!=""){
			int angle = Integer.valueOf(mParser.xLabelAngle);
			switch(angle){
			case -90:
				domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);
				break;
			case -45:
				domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
				break;
			case 45:
				domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
				break;
			case 90:
				domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
				break;
			default:
				break;
			}
		}

	}
	void setLineChart(JFreeChart chart,LineChartParser mParser){
		CategoryPlot plot = chart.getCategoryPlot();
		CategoryAxis domainAxis = plot.getDomainAxis();
		NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
		
		if(mParser.yUpperBound!="")rangeAxis.setUpperBound(Float.valueOf(mParser.yUpperBound));
		if(mParser.yLowerBound!="")rangeAxis.setLowerBound(Float.valueOf(mParser.yUpperBound));
		//deal with paint
		if(mParser.xAxisLinePaint!="")domainAxis.setAxisLinePaint(colorFactory.getColor(mParser.xAxisLinePaint));
		if(mParser.yAxisLinePaint!="")rangeAxis.setAxisLinePaint(colorFactory.getColor(mParser.yAxisLinePaint));
		
		if(mParser.xAxisLineStroke!="")domainAxis.setAxisLineStroke(new BasicStroke(Integer.valueOf(mParser.xAxisLineStroke)));
		if(mParser.yAxisLineStroke!="")rangeAxis.setAxisLineStroke(new BasicStroke(Integer.valueOf(mParser.yAxisLineStroke)));
		if(mParser.xLowerMargin!="")domainAxis.setLowerMargin(Float.valueOf(mParser.xLowerMargin));
		if(mParser.yLowerMargin!="")rangeAxis.setLowerMargin(Float.valueOf(mParser.yLowerMargin));
		if(mParser.xUpperMargin!="")domainAxis.setUpperMargin(Float.valueOf(mParser.xUpperMargin));
		if(mParser.yUpperMargin!="")rangeAxis.setUpperMargin(Float.valueOf(mParser.yUpperMargin));
		if(mParser.xLabelAngle!=""){
			int angle = Integer.valueOf(mParser.xLabelAngle);
			switch(angle){
			case -90:
				domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);
				break;
			case -45:
				domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
				break;
			case 45:
				domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
				break;
			case 90:
				domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
				break;
			default:
				break;
			}
		}

	}
	
	void setAreaChart(JFreeChart chart,AreaChartParser mParser){
		CategoryPlot plot = chart.getCategoryPlot();
		CategoryAxis domainAxis = plot.getDomainAxis();
		NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
		
		if(mParser.yUpperBound!="")rangeAxis.setUpperBound(Float.valueOf(mParser.yUpperBound));
		if(mParser.yLowerBound!="")rangeAxis.setLowerBound(Float.valueOf(mParser.yUpperBound));
		//deal with paint
		if(mParser.xAxisLinePaint!="")domainAxis.setAxisLinePaint(colorFactory.getColor(mParser.xAxisLinePaint));
		if(mParser.yAxisLinePaint!="")rangeAxis.setAxisLinePaint(colorFactory.getColor(mParser.yAxisLinePaint));
		
		if(mParser.xAxisLineStroke!="")domainAxis.setAxisLineStroke(new BasicStroke(Integer.valueOf(mParser.xAxisLineStroke)));
		if(mParser.yAxisLineStroke!="")rangeAxis.setAxisLineStroke(new BasicStroke(Integer.valueOf(mParser.yAxisLineStroke)));
		if(mParser.xLowerMargin!="")domainAxis.setLowerMargin(Float.valueOf(mParser.xLowerMargin));
		if(mParser.yLowerMargin!="")rangeAxis.setLowerMargin(Float.valueOf(mParser.yLowerMargin));
		if(mParser.xUpperMargin!="")domainAxis.setUpperMargin(Float.valueOf(mParser.xUpperMargin));
		if(mParser.yUpperMargin!="")rangeAxis.setUpperMargin(Float.valueOf(mParser.yUpperMargin));
		if(mParser.xLabelAngle!=""){
			int angle = Integer.valueOf(mParser.xLabelAngle);
			switch(angle){
			case -90:
				domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);
				break;
			case -45:
				domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
				break;
			case 45:
				domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
				break;
			case 90:
				domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
				break;
			default:
				break;
			}
		}

	}
	
}
	