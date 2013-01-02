import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JEditorPane;
import javax.swing.Timer;
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
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.Panel;

public class KidlyGUI extends JFrame {

	private JPanel contentPane;
	private Image buffer = null;
	public Graphics bg;

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

	public KidlyGUI() {
		initGui();

	}

	private final Action action = new SwingAction();

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
		mntmExit.setAction(action);
		mnFile.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmImportNewPicture = new JMenuItem("import new picture");
		mnEdit.add(mntmImportNewPicture);

		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

		JMenuItem mntmAboutThis = new JMenuItem("About this");
		mnAbout.add(mntmAboutThis);

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

		JPanel panel = new canvasPanel();
		panel.setBounds(29, 87, 312, 439);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
	}

	public class canvasPanel extends JPanel implements MouseListener {

		Timer timer;
		public int x = 50, y = 50;
		public int rectX = 50, rectY = 50;
		public int mouseX = 0, mouseY = 0;
		boolean flag=false;
		Graphics g;

		public canvasPanel() {

			ActionListener animation = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					repaint();
				}
			};
			timer = new Timer(50, animation);
			timer.start();
			addMouseListener(this);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("e.getX()" + e.getX() + " e.getY()" + e.getY());
			if (e.getX() > x && e.getX() < x + rectX && e.getY() > y
					&& e.getY() < y + rectY) {
				flag=true;
			}
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(flag==true){
				x = e.getX() - rectX / 2;
				y = e.getY() - rectY / 2;
				flag=false;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void paintComponent(Graphics g) {
			buffer = createImage(330, 450);
			bg = buffer.getGraphics();
			// bg.setColor(Color.WHITE);
			bg.drawString("" + x, 20, 20);
			bg.drawString("" + y, 20, 40);
			bg.drawRect(x, y, rectX, rectY);

			g.drawImage(buffer, 0, 0, null);
			try {
				Thread.sleep(33);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Exit");
		}

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
