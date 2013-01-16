package KidlyGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class AddText extends JDialog {

	private JPanel contentPanel = new JPanel();
	private BufferedImage buffer = null;
	private Graphics2D bg;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddText dialog = new AddText();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddText() {
		setTitle("Add New Text");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddText.class.getResource("/res/add-text.png")));
		setResizable(false);
		setBounds(100, 100, 500, 330);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblText = new JLabel("Text:");
		lblText.setBounds(10, 28, 46, 15);
		contentPanel.add(lblText);
		
		JLabel lblNewLabel = new JLabel("Font Size:");
		lblNewLabel.setBounds(261, 28, 80, 15);
		contentPanel.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"8", "10", "12", "14", "16", "18", "20", "36", "48", "72"}));
		comboBox.setBounds(351, 28, 65, 21);
		contentPanel.add(comboBox);
		
		JLabel lblFontType = new JLabel("Font Type:");
		lblFontType.setBounds(261, 58, 80, 15);
		contentPanel.add(lblFontType);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"SansSerif", "Serif", "Dialog", "DialogInput", "Monospaced"}));
		comboBox_1.setBounds(351, 59, 108, 21);
		contentPanel.add(comboBox_1);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("");
		tglbtnNewToggleButton.setIcon(new ImageIcon(AddText.class.getResource("/res/bold.png")));
		tglbtnNewToggleButton.setBounds(351, 91, 26, 23);
		contentPanel.add(tglbtnNewToggleButton);
		
		JToggleButton toggleButton = new JToggleButton("");
		toggleButton.setIcon(new ImageIcon(AddText.class.getResource("/res/italic.png")));
		toggleButton.setBounds(387, 91, 26, 23);
		contentPanel.add(toggleButton);
		
		JToggleButton toggleButton_1 = new JToggleButton("");
		toggleButton_1.setIcon(new ImageIcon(AddText.class.getResource("/res/underline.png")));
		toggleButton_1.setBounds(423, 91, 26, 23);
		contentPanel.add(toggleButton_1);
		
		JLabel lblFontStyle = new JLabel("Font Style:");
		lblFontStyle.setBounds(261, 91, 80, 15);
		contentPanel.add(lblFontStyle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 28, 207, 89);
		contentPanel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 179, 448, 80);
		contentPanel.add(scrollPane_1);
		
		JPanel result_panel = new TextPanel();
		scrollPane_1.setViewportView(result_panel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 167, 494, 92);
		contentPanel.add(separator);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCreate.setBounds(397, 134, 87, 23);
		contentPanel.add(btnCreate);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public class TextPanel extends JPanel{
		public TextPanel(){
			
		}
		public void paintComponent(Graphics g) {
			buffer = new BufferedImage(330, 450, BufferedImage.TYPE_INT_RGB);
			bg = buffer.createGraphics();
			bg.setPaint(Color.WHITE);
			bg.fillRect(0, 0, 330, 450);
			bg.setPaint(Color.BLACK);
			bg.drawString("123", 20, 20);
			g.drawImage(buffer, 0, 0, null);
			try {
				Thread.sleep(33);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
