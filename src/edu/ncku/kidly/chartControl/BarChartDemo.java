package edu.ncku.kidly.chartControl;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.VerticalAlignment;
import org.jfree.util.Log;
import org.jfree.util.PrintStreamLogTarget;

import edu.ncku.kidly.canvas.KIDLYCanvas;

/**
 * A simple demonstration application showing how to create an Bar chart using data from a
 * {@link CategoryDataset}.
 */
public class BarChartDemo extends ApplicationFrame {

	KIDLYCanvas mCanvas;
	JPanel mPanel;
	BufferedImage BI;
    /**
     * Creates a new demo application.
     *
     * @param title  the frame title.
     */
    public BarChartDemo(final String title) {

        super(title);

        // create a dataset...
        final double[][] data = new double[][] {
            {1.0, 4.0, 3.0, 5.0, 5.0, 7.0, 7.0, 8.0},
            {5.0, 7.0, 6.0, 8.0, 4.0, 4.0, 2.0, 1.0},
            {4.0, 3.0, 2.0, 3.0, 6.0, 3.0, 4.0, 3.0}
        };

        final CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
            "Series ", "Type ", data
        );
        //此Panel內部元件不可變動大小
        mPanel = new JPanel(new GridBagLayout()); 
        
        // create the chart...
        
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 400));
        chartPanel.setMaximumSize(new Dimension(500, 400));
        chartPanel.setMinimumSize(new Dimension(500, 400));
        chartPanel.setEnforceFileExtensions(false);
        
        JPanel buttonPanel = new JPanel();
        JButton changeBackButton = new JButton("Change Back");
        buttonPanel.add(changeBackButton);
        changeBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	

            	
            	try {
					chart.setBackgroundImage(ImageIO.read(new File("img/taiwan.png")));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}// Set the background colour of the chart  
            	chart.getTitle().setPaint(Color.blue); // Adjust the colour of the title
            	CategoryPlot p = chart.getCategoryPlot(); // Get the Plot object for a bar graph
            	p.setBackgroundPaint(Color.black);// Modify the plot background  
                p.setRangeGridlinePaint(Color.red); // Modify the colour of the plot gridlines
                
                
                
                File background = new File("img/back.png");
                
                int width = 300;
                int height = 400;
            	BufferedImage image = new BufferedImage(width,
                        height,BufferedImage.TYPE_INT_ARGB);
                
                try {
                	image = ImageIO.read(background);
             	} catch (IOException ex) {
             	   System.err.println(ex.getMessage());
             	   ex.printStackTrace();
             	}
//                p.setBackgroundImage(image);
                
                //更改圖片工具
                Graphics2D g2 = image.createGraphics();
                g2.fillRect(20, 20, 100, 60);
                g2.setColor(Color.white);
                g2.drawImage(image, null,0,0);
                
                
                //改變區域顏色
                CategoryItemRenderer renderer = p.getRenderer(); 
                renderer.setSeriesPaint(0, Color.cyan);
                
                mCanvas.setBounds(65, 170, 10, 50);
                chartPanel.add(mCanvas);
                
                //輸出影像
                
                
            }
        });
        
        
        JButton createImageButton = new JButton("Create Image");
        buttonPanel.add(createImageButton);
        createImageButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
        			ScreenImage.writeImage(ScreenImage.createImage(mPanel),"img/myChart.png");
        			BI = ImageIO.read(new File("img/taiwan.png"));
//					ScreenImage.writeImage(mPanel.getGraphics(),"myChart.png");
        		} catch (IOException e1) {
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}
			}
		});
        
        
        Canvas cc;
        mCanvas = new KIDLYCanvas();
        cc = new KIDLYCanvas();
        try {
			mCanvas.setImage(50,50,ImageIO.read(new File("img/piece.jpg")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
        
        mPanel.add(chartPanel);
        this.add(mPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        
    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Creates a chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset) {
                
//        final JFreeChart chart = ChartFactory.createBarChart(
//            "Bar Chart",             // chart title
//            "Category",               // domain axis label
//            "Value",                  // range axis label
//            dataset,                  // data
//            PlotOrientation.VERTICAL, // orientation
//            true,                     // include legend
//            true,                     // tooltips
//            false                     // urls
//        );
        final JFreeChart chart = ChartFactory.createBarChart(
                "Bar Chart",             // chart title
                "Category",               // domain axis label
                "Value",                  // range axis label
                dataset,                  // data
                PlotOrientation.VERTICAL, // orientation
                true,                     // include legend
                true,                     // tooltips
                false                     // urls
            );
 
        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
//        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setAnchor(StandardLegend.SOUTH);

        chart.setBackgroundPaint(Color.white);
        final TextTitle subtitle = new TextTitle("An Bar chart demonstration.  We use this "
            + "subtitle as an example of what happens when you get a really long title or "
            + "subtitle.");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitle.setPosition(RectangleEdge.TOP);
//        subtitle.setSpacer(new Spacer(Spacer.RELATIVE, 0.05, 0.05, 0.05, 0.05));
        subtitle.setVerticalAlignment(VerticalAlignment.BOTTOM);
        chart.addSubtitle(subtitle);

        
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setForegroundAlpha(0.5f);
        
  //      plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);
        
        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        domainAxis.addCategoryLabelToolTip("Type 1", "The first type.");
        domainAxis.addCategoryLabelToolTip("Type 2", "The second type.");
        domainAxis.addCategoryLabelToolTip("Type 3", "The third type.");
        
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLabelAngle(0 * Math.PI / 2.0);
        rangeAxis.setAxisLinePaint(Color.cyan);
//        rangeAxis.setUpperBound(7.0);
//        rangeAxis.setLowerBound(2.0);
        rangeAxis.setRangeAboutValue(5.0, 10.0);
        
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
        
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

        Log.getInstance().addTarget(new PrintStreamLogTarget());
        final BarChartDemo demo = new BarChartDemo("Bar Chart Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
