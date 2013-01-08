package edu.ncku.kidly.chartControl;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RefineryUtilities;
/**
 * @author John B. Matthews
 */
public class test extends JFrame {
    
    private static final int MAX = 8;
    private static final Random random = new Random();

    //UI
    JFreeChart chart;
    ChartPanel chartPanel;
    /**
     * Construct a new frame 
     *
     * @param title the frame title
     */
    public test(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("Series0", createSeries(0));
        dataset.addSeries("Series1", createSeries(1));
        chart = createChart(dataset);
        
        chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(640, 480));
        
        this.add(chartPanel, BorderLayout.CENTER);
 
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Series");
        buttonPanel.add(addButton);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int n = dataset.getSeriesCount();
                dataset.addSeries("Series" + n, createSeries(n));
            }
        });
        JButton remButton = new JButton("Remove Series");
        buttonPanel.add(remButton);
        remButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int n = dataset.getSeriesCount() - 1;
                dataset.removeSeries("Series" + n);
            }
        });
        JButton changeBackButton = new JButton("Change Back");
        buttonPanel.add(changeBackButton);
        changeBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	chart.setBackgroundPaint(Color.yellow);// Set the background colour of the chart  
            	chart.getTitle().setPaint(Color.blue); // Adjust the colour of the title
            	XYPlot p = chart.getXYPlot(); // Get the Plot object for a bar graph
            	p.setBackgroundPaint(Color.black);// Modify the plot background  
                p.setRangeGridlinePaint(Color.red); // Modify the colour of the plot gridlines
                
            }
        });
        JButton refreshButton = new JButton("Refresh");
        buttonPanel.add(refreshButton);
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Create a series
     * 
     * @ return the series
     */
    private double[][] createSeries(int mean) {
        double[][] series = new double[2][MAX];
        for (int i = 0; i < MAX; i++) {
            series[0][i] = (double) i;
            series[1][i] = mean + random.nextGaussian() / 2;
            
        }
        return series;
    }

    /**
     * Create a chart.
     *
     * @param dataset the dataset
     * @return the chart
     */
    private JFreeChart createChart(XYDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createXYLineChart(
            "Serial Data", // chart title
            "Domain", // domain axis label
            "Range", // range axis label
            dataset,  // initial series
            PlotOrientation.VERTICAL, // orientation
            true, // include legend
            true, // tooltips?
            false // URLs?
            );

        // set chart background
        chart.setBackgroundPaint(Color.white);

        // set a few custom plot features
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(0xffffe0));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);

        // set the plot's axes to display integers
        TickUnitSource ticks = NumberAxis.createIntegerTickUnits();
        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setStandardTickUnits(ticks);
        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setStandardTickUnits(ticks);

        // render shapes and lines
        XYLineAndShapeRenderer renderer =
            new XYLineAndShapeRenderer(true, true);
        plot.setRenderer(renderer);
        renderer.setBaseShapesVisible(true);
        renderer.setBaseShapesFilled(true);

        // set the renderer's stroke
        Stroke stroke = new BasicStroke(
            3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
        renderer.setBaseOutlineStroke(stroke);

        // label the points
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(2);
        XYItemLabelGenerator generator =
            new StandardXYItemLabelGenerator(
                StandardXYItemLabelGenerator.DEFAULT_ITEM_LABEL_FORMAT,
                format, format);
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);

        return chart;
    }

    /** Main method */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                test demo = new test("JFreeChartDemo");
                demo.pack();
                demo.setLocationRelativeTo(null);
                demo.setVisible(true);
            }
         });
    }
}
