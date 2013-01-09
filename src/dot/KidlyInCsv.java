package dot;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class KidlyInCsv extends JFrame{

	private JPanel inCsvPanel = null;
	private JButton openFileButton = null;
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
		
		
		openFileButton = new JButton("OpenFile");
		openFileButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
		
		inPathLabel1 = new JLabel("Input File Path :");
		inPathLabel1.setFont(new Font("Arial", Font.PLAIN, 14));
		inPathLabel1.setBounds(10, 50, 300, 50);
		inCsvPanel.add(inPathLabel1);

		inPathLabel2 = new JLabel("Test Path");
		inPathLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
		inPathLabel2.setBounds(10, 100, 300, 50);
		inCsvPanel.add(inPathLabel2);
		
		
		
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
