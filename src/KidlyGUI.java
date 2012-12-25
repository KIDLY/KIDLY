import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JEditorPane;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import java.awt.Canvas;
import javax.swing.JSpinner;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JSlider;

public class KidlyGUI extends JFrame implements Runnable {

	private JPanel contentPane;
	private Image bufferPage = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		KidlyGUI frame = new KidlyGUI();
		frame.setVisible(true);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	MouseGraphicsComponent _display = new MouseGraphicsComponent();
	public KidlyGUI() {
		initGui();
		
	}

	KidlyGUI_canvas canvas;

	public void initGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 676, 21);
		contentPane.add(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmImportNewPicture = new JMenuItem("import new picture");
		mnEdit.add(mntmImportNewPicture);

		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

		JMenuItem mntmAboutThis = new JMenuItem("About this");
		mnAbout.add(mntmAboutThis);

		canvas = new KidlyGUI_canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(29, 87, 312, 439);
		contentPane.add(canvas);

		JSpinner spinner = new JSpinner();
		spinner.setBounds(457, 87, 134, 32);
		contentPane.add(spinner);

		JLabel lblPriority = new JLabel("Priority :");
		lblPriority.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPriority.setBounds(374, 87, 63, 18);
		contentPane.add(lblPriority);

		JLabel lblSize = new JLabel("Scale :");
		lblSize.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSize.setBounds(374, 153, 46, 15);
		contentPane.add(lblSize);

		JSlider slider = new JSlider();
		slider.setMinimum(1);
		slider.setMaximum(200);
		slider.setBackground(Color.LIGHT_GRAY);
		slider.setBounds(457, 147, 180, 21);
		contentPane.add(slider);

		JLabel lblRotation = new JLabel("Rotation :");
		lblRotation.setFont(new Font("Arial", Font.PLAIN, 14));
		lblRotation.setBounds(374, 236, 63, 15);
		contentPane.add(lblRotation);

		JSlider slider_1 = new JSlider();
		slider_1.setMinimum(-180);
		slider_1.setMaximum(180);
		slider_1.setBackground(Color.LIGHT_GRAY);
		slider_1.setBounds(457, 230, 180, 21);
		contentPane.add(slider_1);

		JLabel label = new JLabel(
				"-180\u00B0                    0\u00B0                    180\u00B0");
		label.setBounds(460, 264, 177, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel(
				"1%                    100%                  200%");
		label_1.setBounds(457, 178, 191, 15);
		contentPane.add(label_1);

	}

	public class KidlyGUI_canvas extends Canvas implements MouseListener {

		public KidlyGUI_canvas() {
			// TODO Auto-generated constructor stub
		}

		public void main(String[] args) {
			KidlyGUI_canvas canvas = new KidlyGUI_canvas();

		}

		public boolean flag = true;
		public int x = 10, y = 50;

		public void toggle() {
			flag = false;
		}

		public void paint(Graphics g) {

			g.drawRect(x, x, y, y);

		}

		@Override
		public void mouseClicked(MouseEvent e) {

			if (e.getX() >= 10 && e.getY() >= 10 && e.getX() <= 50
					&& e.getY() <= 50) {
				x = 10;
				y = 100;
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
	public void update(Graphics g){
		paint(g);
	
	}

	@Override
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
			}
		}
	}

}
