package dot;

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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.io.CSV;


public class KidlyInCsv extends JFrame{

	private JPanel inCsvPanel = null;
	private JButton openFileButton = null;
	private JButton genChartButton = null;
	private JLabel inPathLabel1 = null;
	private JLabel inPathLabel2 = null;
	private String path = null;
	
	private void initialize() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("¿ï¨úÀÉ®×");
		this.setBounds(100, 100, 700, 600);
		this.setVisible(true);
		
		inCsvPanel = new JPanel();
		inCsvPanel.setLayout(null);
		this.setContentPane(inCsvPanel);
		
		//Set Open File Button 
		openFileButton = new JButton("OpenFile");
		openFileButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				// Choose File
				openFileButton.setVisible(false);
				FileChooser chooser = new FileChooser();
				path = chooser.getPath();
				inPathLabel2.setText(path);
				openFileButton.setVisible(true);
				
			}
		});
		openFileButton.setBounds(10, 0, 300, 50);
		inCsvPanel.add(openFileButton);
		
		//Set Input File Path Label
		inPathLabel1 = new JLabel("Input File Path :");
		inPathLabel1.setFont(new Font("Arial", Font.PLAIN, 14));
		inPathLabel1.setBounds(10, 50, 300, 50);
		inCsvPanel.add(inPathLabel1);

		inPathLabel2 = new JLabel("Test Path");
		inPathLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
		inPathLabel2.setBounds(10, 100, 300, 50);
		inCsvPanel.add(inPathLabel2);
		
		//Set Generate JFreeChart Button
		genChartButton = new JButton("Generate Chart");
		genChartButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				//Generate Chart
				FileReader reader = null;
				CategoryDataset dataSet = null;
				
				try {
					
					reader = new FileReader(path);
					
					dataSet = (new CSV(';','"')).readCategoryDataset(reader);
					reader.close();	
					
				} catch (FileNotFoundException e1){
					e1.printStackTrace();
				} catch (IOException e1){
					e1.printStackTrace();
				}
				  
			
				//Test Generate Line Chart
				JFreeChart chart = ChartFactory.createLineChart("Label","X","Y",dataSet,PlotOrientation.VERTICAL,true,true,false);
				
				ChartPanel chartPanel = new ChartPanel(chart);
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
