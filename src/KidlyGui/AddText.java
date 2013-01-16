package KidlyGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class AddText extends JDialog {

	private JPanel contentPanel = new JPanel();
	private BufferedImage buffer = null;
	private Graphics2D bg;
	private TextPanel result_panel;
	private JTextField textArea;
	private JTextField textField;
	private JToggleButton italicToggleButton, UnderlineToggleButton, boldToggleButton;
	private Font textFont;
	private int textSize;
	private String textType;
	private JComboBox comboBox_Size, comboBox_Type;
	public int geti(){
		return 1;
	}

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
		lblText.setBounds(25, 28, 65, 15);
		contentPanel.add(lblText);

		JLabel lblNewLabel = new JLabel("Font Size:");
		lblNewLabel.setBounds(26, 92, 65, 15);
		contentPanel.add(lblNewLabel);
		
		
		
		comboBox_Size = new JComboBox();
		comboBox_Size.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				textSize = Integer.valueOf(comboBox_Size.getSelectedItem().toString());

			}
		});
		comboBox_Size.setModel(new DefaultComboBoxModel(new String[] { "8", "10", "12", "14", "16", "18", "20", "36", "48", "72" }));
		comboBox_Size.setSelectedIndex(2);
		comboBox_Size.setBounds(101, 89, 65, 21);
		contentPanel.add(comboBox_Size);

		JLabel lblFontType = new JLabel("Font Type:");
		lblFontType.setBounds(26, 122, 65, 15);
		contentPanel.add(lblFontType);

		comboBox_Type = new JComboBox();
		comboBox_Type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textType = comboBox_Type.getSelectedItem().toString();
			}
		});
		comboBox_Type.setModel(new DefaultComboBoxModel(new String[] {"SansSerif", "Serif", "Dialog", "DialogInput", "Monospaced"}));
		comboBox_Type.setBounds(101, 123, 108, 21);
		contentPanel.add(comboBox_Type);

		boldToggleButton = new JToggleButton("");
		boldToggleButton.addActionListener(btnAction);
	
		boldToggleButton.setIcon(new ImageIcon(AddText.class.getResource("/res/bold.png")));
		boldToggleButton.setBounds(352, 92, 26, 23);
		contentPanel.add(boldToggleButton);

		italicToggleButton = new JToggleButton("");
		italicToggleButton.addActionListener(btnAction);
		
		italicToggleButton.setIcon(new ImageIcon(AddText.class.getResource("/res/italic.png")));
		italicToggleButton.setBounds(388, 92, 26, 23);
		contentPanel.add(italicToggleButton);

		UnderlineToggleButton = new JToggleButton("");
		UnderlineToggleButton.addActionListener(btnAction);
		
		UnderlineToggleButton.setIcon(new ImageIcon(AddText.class.getResource("/res/underline.png")));
		UnderlineToggleButton.setBounds(424, 92, 26, 23);
		contentPanel.add(UnderlineToggleButton);

		JLabel lblFontStyle = new JLabel("Font Style:");
		lblFontStyle.setBounds(262, 92, 80, 15);
		contentPanel.add(lblFontStyle);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 28, 350, 40);
		contentPanel.add(scrollPane);

		textArea = new JTextField();
		scrollPane.setViewportView(textArea);
		textFont=new Font("SansSerif",Font.PLAIN,12);
		textArea.setFont(textFont);
		textSize=12;
		textType="SansSerif";
		
		result_panel = new TextPanel();
		result_panel.setBounds(80, 200, 348, 38);
		contentPanel.add(result_panel);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 167, 494, 92);
		contentPanel.add(separator);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFont = new Font(textType, textArea.getFont().getStyle(), textSize); /* TODO */
				textArea.setFont(textFont);
				System.out.println(textArea.getFont().getStyle());
				System.out.println(textSize);
				result_panel.repaint();
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
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	ActionListener btnAction=new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (italicToggleButton.isSelected()) {
				textFont = new Font(textType, Font.ITALIC, textSize); /* TODO */
				textArea.setFont(textFont);
			}
			if (UnderlineToggleButton.isSelected()) {
				textFont = new Font(textType, Font.CENTER_BASELINE, textSize); /* TODO */
				textArea.setFont(textFont);
			}
			if (boldToggleButton.isSelected()) {
				textFont = new Font(textType, Font.BOLD, textSize); /* TODO */
				textArea.setFont(textFont);
			}
		}
	};

	public class TextPanel extends JPanel {
		public TextPanel() {

		}

		public void getFont(Font font) {
			bg.setFont(font);
		}

		public void paintComponent(Graphics g) {
			buffer = new BufferedImage(textArea.getWidth(), textArea.getHeight(), BufferedImage.TYPE_INT_RGB);
			buffer=ScreenImage.createImage(textArea);
			/*
			bg = buffer.createGraphics();
			bg.setPaint(Color.WHITE);
			bg.fillRect(0, 0, textArea.getWidth(), textArea.getHeight());
			bg.setPaint(Color.BLACK);
			bg.drawString(textArea.getText(), 0, 20);
			 */
			/*
			 * StringTokenizer st=new StringTokenizer(textArea.getText()); int
			 * lineCount=0; while(st.hasMoreTokens()){
			 * //.drawString(st.nextToken(),0, 0+lineCount * 10); lineCount++; }
			 */
			g.drawImage(buffer, 0, 0, null);
			try {
				Thread.sleep(33);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
