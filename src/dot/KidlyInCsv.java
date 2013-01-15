package dot;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.io.CSV;

import XMLparser.Parser;

public class KidlyInCsv extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel inCsvPanel ;
	private JButton openCsvButton ;
	private JButton openXmlButton ;
	private JButton genChartButton ;
	private JLabel inPathLabel1 ;
	private JLabel inPathLabel2 ;
	private String csvPath ;
	private String xmlPath;
	private Choice chartTypeChoice ;
	private ChartPanel chartPanel ;

	private void initialize() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("¿ï¨úÀÉ®×");
		this.setBounds(100, 100, 700, 600);
		this.setVisible(true);

		inCsvPanel = new JPanel();
		inCsvPanel.setLayout(null);
		this.setContentPane(inCsvPanel);

		// Set Open Csv File Button
		openCsvButton = new JButton("Open Csv File");
		openCsvButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Choose File
				openCsvButton.setVisible(true);
				FileChooser chooser = new FileChooser("csv");
				csvPath = chooser.getPath();
				inPathLabel1.setText("CSV_Path: " + csvPath);
				openCsvButton.setVisible(true);

			}
		});
		openCsvButton.setBounds(10, 5, 200, 30);
		inCsvPanel.add(openCsvButton);

		// Set Open Xml File Button
		openXmlButton = new JButton("Open Xml File");
		openXmlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Choose File
				openXmlButton.setVisible(true);
				FileChooser chooser = new FileChooser("xml");
				xmlPath = chooser.getPath();
				inPathLabel2.setText("XML_Path: " + xmlPath);
				openXmlButton.setVisible(true);

			}
		});
		openXmlButton.setBounds(10, 70, 200, 30);
		inCsvPanel.add(openXmlButton);

		// Set Input File Path Label
		inPathLabel1 = new JLabel("CSV_Path: ");
		inPathLabel1.setFont(new Font("Arial", Font.PLAIN, 14));
		inPathLabel1.setBounds(10, 40, 300, 20);
		inCsvPanel.add(inPathLabel1);

		inPathLabel2 = new JLabel("XML_Path: ");
		inPathLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
		inPathLabel2.setBounds(10, 105, 300, 20);
		inCsvPanel.add(inPathLabel2);

		// Set Generate JFreeChart Button
		genChartButton = new JButton("Generate Chart");
		genChartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Generate Chart
				FileReader reader = null;
				CategoryDataset dataSet = null;

				try {
					new Parser(xmlPath);
					reader = new FileReader(csvPath);

					dataSet = (new CSV(';', '"')).readCategoryDataset(reader);
					reader.close();

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				String selectType = chartTypeChoice.getSelectedItem();
				System.out.println(selectType);

				// Test Generate Line Chart
				KidlyInitChartFactory factory = new KidlyInitChartFactory();
				JFreeChart chart = factory.createChart(selectType, dataSet);

				// JFreeChart chart =
				// ChartFactory.createLineChart("Label","X","Y",dataSet,PlotOrientation.VERTICAL,true,true,false);

				if (chartPanel != null) {
					inCsvPanel.remove(chartPanel);
				}
				chartPanel = new ChartPanel(chart);
				chartPanel.setFillZoomRectangle(true);
				chartPanel.setMouseWheelEnabled(true);
				chartPanel.setPreferredSize(new Dimension(500, 270));

				chartPanel.setBounds(10, 220, 300, 300);
				inCsvPanel.add(chartPanel);
				inCsvPanel.updateUI();

			}
		});
		genChartButton.setBounds(10, 150, 300, 50);
		inCsvPanel.add(genChartButton);

		// Set Choice Kinds of Chart
		chartTypeChoice = new Choice();

		String[] chartType = { "Bar Chart", "Pie Chart", "Line Chart",
				"Area Chart", "Histogram" };

		for (int i = 0; i < chartType.length; i++)
			chartTypeChoice.add(chartType[i]);
		chartTypeChoice.setBounds(250, 5, 200, 30);
		inCsvPanel.add(chartTypeChoice);

	}

	public KidlyInCsv() {

		super();
		initialize();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new KidlyInCsv();
		
	}

}
