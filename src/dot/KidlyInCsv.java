package dot;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.io.CSV;

import XMLparser.AreaChartParser;
import XMLparser.BarChartParser;
import XMLparser.LineChartParser;
import XMLparser.PieChartParser;

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
	private String csvPath = "test.csv";
	private String xmlPath = "testdata\\AreaSetting.xml";
	private Choice chartTypeChoice ;
	private ChartPanel chartPanel ;
	private BufferedImage finalImg;

	private void initialize() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Generate Chart");
		this.setBounds(100, 100, 550, 650);
		this.setVisible(true);

		inCsvPanel = new JPanel();
		inCsvPanel.setLayout(null);
		this.setContentPane(inCsvPanel);

		// Set Open Csv File Button
		openCsvButton = new JButton("Open Csv File");
		openCsvButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Choose Files
				openCsvButton.setVisible(true);
				FileChooser chooser = new FileChooser("csv");
				csvPath = chooser.getPath();
				inPathLabel1.setText("CSV_Path: " + csvPath);
				openCsvButton.setVisible(true);

			}
		});
		openCsvButton.setBounds(10, 40, 200, 30);
		

		// Set Open Xml File Button
		openXmlButton = new JButton("Open Xml File");
		openXmlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Choose File
				openXmlButton.setVisible(true);
				FileChooser chooser = new FileChooser("xml");
				xmlPath = chooser.getPath();
				System.out.println(xmlPath);
				inPathLabel2.setText("XML_Path: " + xmlPath);
				openXmlButton.setVisible(true);

			}
		});
		openXmlButton.setBounds(10, 80, 200, 30);
		

		// Set Input File Path Label
		inPathLabel1 = new JLabel("CSV_Path: ");
		inPathLabel1.setFont(new Font("Arial", Font.PLAIN, 14));
		inPathLabel1.setBounds(230, 40, 900, 20);
		

		inPathLabel2 = new JLabel("XML_Path: ");
		inPathLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
		inPathLabel2.setBounds(230, 80, 900, 20);
		

		// Set Generate JFreeChart Button
		genChartButton = new JButton("Generate Chart");
		genChartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Generate Chart
				FileReader reader = null;
				CategoryDataset dataSet = null;

				try {
					
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
		        
		       XMLparser.Parser mParser = null;
				if( selectType.equals("Bar Chart") ){
					mParser = new BarChartParser(xmlPath);
			
					}
				else if( selectType.equals("Line Chart") )
					mParser = new LineChartParser(xmlPath);
				else if( selectType.equals("Area Chart") )
					mParser = new AreaChartParser(xmlPath);
				else if( selectType.equals("Pie Chart") )
					mParser = new PieChartParser(xmlPath);
		        
		        if(mParser.check == true){
		        	//Generate Chart
			        KidlyInitChartFactory factory = new KidlyInitChartFactory();
			        JFreeChart chart = factory.createChart(selectType,dataSet,mParser);
			        
			        finalImg = chart.createBufferedImage(500, 400);
			        				
			        if(chartPanel != null){
			        	inCsvPanel.remove(chartPanel);
			        }
					chartPanel = new ChartPanel(chart);
					chartPanel.setFillZoomRectangle(true);
					chartPanel.setMouseWheelEnabled(true);
					chartPanel.setPreferredSize(new Dimension(500, 270));
	
					chartPanel.setBounds(10, 190, 500, 400);
					inCsvPanel.add(chartPanel);
					inCsvPanel.updateUI();
		        }
		        else{
		        	JOptionPane.showMessageDialog(chartPanel,
						    "Eggs are not supposed to be green.",
						    "Inane error",
						    JOptionPane.ERROR_MESSAGE);
		        }

			}
		});
		genChartButton.setBounds(10, 120, 300, 50);
		
		
		//Final Image Create
		final JButton finalButton = new JButton("Create!!");
		finalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KidlyInCsv.this.dispose();

			}
		});
		finalButton.setBounds(350, 120, 100, 30);
		

		// Set Choice Kinds of Chart
		chartTypeChoice = new Choice();
		
        String [] chartType = {"Bar Chart", "Pie Chart", "Line Chart","Area Chart"};
        
        for( int i=0 ; i<chartType.length ; i++ )
            chartTypeChoice.add(chartType[i]);
        chartTypeChoice.setBounds(10, 5, 150, 30);
        
        inCsvPanel.add(openCsvButton);
        inCsvPanel.add(openXmlButton);
        inCsvPanel.add(inPathLabel1);
        inCsvPanel.add(inPathLabel2);
        inCsvPanel.add(genChartButton);
        inCsvPanel.add(finalButton);
        inCsvPanel.add(chartTypeChoice);
        
     
	}

	public KidlyInCsv() {
		super();
		initialize();
	}
	
	public BufferedImage getImg(){
		return finalImg;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new KidlyInCsv();
	}

}
