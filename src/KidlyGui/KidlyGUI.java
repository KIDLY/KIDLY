package KidlyGui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class KidlyGUI extends JFrame {

	private JPanel contentPane;
	private BufferedImage buffer = null;
	public Graphics2D bg;
	public JSlider skewSlider, scaleSlider;
	public JLabel lbl_scaleNum, lbl_skewNum;

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
		menuBar.setBounds(0, 0, 684, 21);
		contentPane.add(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// mntmExit.setAction(action);
		mnFile.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmImportNewPicture = new JMenuItem("import new picture");
		mntmImportNewPicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
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

		scaleSlider = new JSlider();
		scaleSlider.setValue(100);
		scaleSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					if (IBManager != null) {
						IBManager.holdedBlock.scalePercentage = scaleSlider.getValue();
						lbl_scaleNum.setText("" + IBManager.holdedBlock.scalePercentage);
						IBManager.scaleImage();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		scaleSlider.setMinimum(1);
		scaleSlider.setMaximum(200);
		scaleSlider.setBackground(Color.LIGHT_GRAY);
		scaleSlider.setBounds(457, 174, 180, 21);
		contentPane.add(scaleSlider);

		JLabel label_1 = new JLabel("1%                    100%                  200%");
		label_1.setBounds(457, 205, 191, 15);
		contentPane.add(label_1);

		lbl_scaleNum = new JLabel("100");
		lbl_scaleNum.setForeground(Color.RED);
		lbl_scaleNum.setFont(new Font("新細明體", Font.PLAIN, 13));
		lbl_scaleNum.setBackground(Color.WHITE);
		lbl_scaleNum.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_scaleNum.setBounds(457, 154, 113, 15);
		contentPane.add(lbl_scaleNum);

		JLabel lblRotation = new JLabel("Rotation :");
		lblRotation.setFont(new Font("Arial", Font.PLAIN, 14));
		lblRotation.setBounds(374, 254, 63, 15);
		contentPane.add(lblRotation);

		skewSlider = new JSlider();
		skewSlider.setMinimum(-180);
		skewSlider.setValue(0);
		skewSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					if (IBManager != null) {
						lbl_skewNum.setText("" + IBManager.holdedBlock.degree);
						IBManager.skewImage((float)skewSlider.getValue()/180);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		skewSlider.setMaximum(180);
		skewSlider.setBackground(Color.LIGHT_GRAY);
		skewSlider.setBounds(457, 284, 180, 21);
		contentPane.add(skewSlider);

		lbl_skewNum = new JLabel("100");
		lbl_skewNum.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_skewNum.setForeground(Color.RED);
		lbl_skewNum.setFont(new Font("新細明體", Font.PLAIN, 13));
		lbl_skewNum.setBackground(Color.WHITE);
		lbl_skewNum.setBounds(457, 254, 113, 15);
		contentPane.add(lbl_skewNum);

		JLabel label = new JLabel("-180\u00B0                    0\u00B0                    180\u00B0");
		label.setBounds(460, 318, 177, 15);
		contentPane.add(label);

		JPanel panel = new canvasPanel();
		panel.setBounds(29, 87, 312, 439);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);

		JLabel label_2 = new JLabel("%");
		label_2.setForeground(Color.RED);
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(547, 154, 46, 15);
		contentPane.add(label_2);

		JSeparator separator = new JSeparator();
		separator.setBounds(357, 129, 302, 97);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(357, 230, 302, 97);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(357, 356, 302, 97);
		contentPane.add(separator_2);

		JLabel label_4 = new JLabel("\u00B0");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setForeground(Color.RED);
		label_4.setBounds(547, 254, 46, 15);
		contentPane.add(label_4);
	}

	private ImageBlockManager IBManager;

	public class canvasPanel extends JPanel implements MouseListener, MouseMotionListener {

		Timer timer;
		public int x = 50, y = 50;
		public int rectX = 50, rectY = 50;
		public int mouseX = 0, mouseY = 0;
		boolean flag = false;
		Graphics g;

		public canvasPanel() {
			/* init a ImageBlockManager and load in a picture */
			IBManager = new ImageBlockManager();
			BufferedImage bi = null;
			try {
				bi = ImageIO.read(new File("test1.jpg"));
			} catch (IOException e) {
			}
			ImageBlock ib = new ImageBlock(bi, 0, 0);
			IBManager.addImageBlock(ib);
			try {
				bi = ImageIO.read(new File("test2.jpg"));
			} catch (IOException e) {
			}
			ib = new ImageBlock(bi, 0, 0);
			IBManager.addImageBlock(ib);
			IBManager.selectLayout(0);

			ActionListener animation = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					repaint();
				}
			};
			timer = new Timer(50, animation);
			timer.start();
			addMouseListener(this);
			addMouseMotionListener(this);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			if (IBManager.isCanvasHit(x, y)) {
				flag = true;
				scaleSlider.setValue(IBManager.holdedBlock.scalePercentage);
				lbl_scaleNum.setText("" + IBManager.holdedBlock.scalePercentage);
			}

			/*
			 * if (e.getX() > x && e.getX() < x + rectX && e.getY() > y &&
			 * e.getY() < y + rectY) { flag = true; }
			 */

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (flag == true) {
				int x = e.getX();
				int y = e.getY();
				IBManager.moveImages(x, y);
				flag = false;
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

			buffer = new BufferedImage(330, 450,BufferedImage.TYPE_INT_RGB);
			bg = (Graphics2D) buffer.getGraphics();
			bg.setBackground(Color.WHITE);
			ArrayList<ImageBlock> ibl = IBManager.getBlockList();
			ImageBlock focusBlock = IBManager.getHoldedImageBlock();
			for (int i = ibl.size() - 1; i >= 0; i--) {
				ImageBlock ib = ibl.get(i);
				if (ib == focusBlock) {
					bg.setColor(Color.BLUE);
					bg.drawRect(focusBlock.x - 1, focusBlock.y - 1, focusBlock.width + 2, focusBlock.height + 2);
					bg.setColor(Color.BLACK);
				}
				bg.drawImage(ib.image, ib.x, ib.y, null);
			}
			g.drawImage(buffer, 0, 0, null);
			try {
				Thread.sleep(33);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (flag) {
				IBManager.moveImages(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}
	}

	public class ImageBlockManager {
		private ArrayList<ImageBlock> blockList = new ArrayList<ImageBlock>();
		private ImageBlock holdedBlock = null;
		private int offsetX;
		private int offsetY;

		public ImageBlockManager() {
		}

		/**
		 * add a image block to ImageBlockManager
		 */
		public void addImageBlock(ImageBlock block) {
			block.level = blockList.size();
			this.blockList.add(block);
		}

		/**
		 * skew a image from -180 degree to +180 degree
		 * 
		 * @throws Exception
		 */
		public void skewImage(float degree) throws Exception {
			if (this.holdedBlock != null) {

				BufferedImage tempImg = this.holdedBlock.preImage;
				
				int type = tempImg.getColorModel().getTransparency();
				BufferedImage img = new BufferedImage(tempImg.getWidth(), tempImg.getHeight(), type);
				Graphics2D graphics2d=img.createGraphics();
				
				graphics2d.translate(tempImg.getWidth() / 2, tempImg.getHeight() / 2);
				graphics2d.rotate(degree*Math.PI, tempImg.getWidth() / 2, tempImg.getHeight() / 2);
				graphics2d.drawImage(tempImg, -tempImg.getWidth() / 2,-tempImg.getHeight() / 2, Color.WHITE,null);
				AffineTransform at = (AffineTransform)(graphics2d.getTransform().clone());
				graphics2d.setTransform(at);
				graphics2d.dispose();
				this.holdedBlock.image = img;
				
			} else {
				throw new Exception("None holded block");
			}
		}

		/**
		 * return image skew degree
		 */
		public int getSkewDegree() throws Exception {
			if (this.holdedBlock != null) {
				return this.holdedBlock.degree;
			} else {
				throw new Exception("None holded block");
			}
		}

		/**
		 * set skew degree for image
		 * 
		 * @param:int degree
		 */
		public void setSkewDegree(int degree) {
			/* TODO */
		}

		/**
		 * scale image from 1% to 200%
		 * 
		 * @throws Exception
		 */
		public void scaleImage() throws Exception {
			BufferedImage tempImg = this.holdedBlock.preImage;
			int width = (int) (this.holdedBlock.preWidth * getScalePercentage() / 100);
			int height = (int) (this.holdedBlock.preHeight * getScalePercentage() / 100);

			if (width != 0 && height != 0) {
				tempImg = (BufferedImage) tempImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				this.holdedBlock.image = tempImg;
				this.holdedBlock.height = height;
				this.holdedBlock.width = width;
			}
		}

		/**
		 * return percentage if scale
		 * 
		 * @return:int to presentage
		 */
		public int getScalePercentage() throws Exception {
			if (this.holdedBlock != null) {
				return this.holdedBlock.scalePercentage;
			} else {
				throw new Exception("None holded block");
			}
		}

		/**
		 * set scale percentage for 1 to 200
		 * 
		 * @param:int scale percentage
		 */
		public void setScalePercentage(int scale) {
			/* TODO */
		}

		public void moveImages(int x, int y) {
			if (holdedBlock != null) {
				this.holdedBlock.x = x - this.offsetX;
				this.holdedBlock.y = y - this.offsetY;
			}
		}

		/**
		 * Raise layout level to higher (high Priority to print on canvas
		 */
		public void raiseLayout() {
			int i = this.blockList.indexOf(this.holdedBlock);
			this.holdedBlock.level = i - 1;
			this.blockList.get(i - i).level = i;
			Collections.swap(this.blockList, i, i - 1);
			ArrayList<String> arrayList = new ArrayList<String>();
		}

		/**
		 * reduce layout level to lower (lower Priority to print and may under
		 * other layout
		 */
		public void reduceLayout() {
			int i = this.blockList.indexOf(this.holdedBlock);
			this.holdedBlock.level = i + 1;
			this.blockList.get(i + i).level = i;
			Collections.swap(this.blockList, i, i + 1);
		}

		/**
		 * get level of layout (higher value means higher priority on canvas)
		 * 
		 * @return:int level of layout
		 */
		public int getLayoutLevel() throws Exception {
			if (this.holdedBlock != null) {
				return this.holdedBlock.level;
			} else {
				throw new Exception("None holded block");
			}
		}

		/**
		 * return holded (clicked) image block
		 */
		public ImageBlock getHoldedImageBlock() {
			return this.holdedBlock;
		}

		/**
		 * Varify is user hit in a image block
		 */
		public boolean isCanvasHit(int x, int y) {
			for (ImageBlock ib : this.blockList) {
				if (ib.isHit(x, y)) {
					this.holdedBlock = ib;
					this.offsetX = x - ib.x;
					this.offsetY = y - ib.y;
					return true;
				}
			}
			return false;
		}

		/**
		 * return block list in management for paint on canvas
		 * 
		 * @return:arraylist<ImageBlock>
		 */
		public ArrayList<ImageBlock> getBlockList() {
			return this.blockList;
		}

		/**
		 * return the length of layouts in ImageBlockManager
		 * 
		 * @return:int length of layout
		 */
		public int getLayoutLength() {
			return this.blockList.size();
		}

		/**
		 * use indexer to select a image block
		 */
		private void selectLayout(int index) {
			this.holdedBlock = this.blockList.get(index);
		}

	}

	private class ImageBlock {
		public int scalePercentage = 100;
		public int degree = 0;
		public int x;
		public int y;
		public int preWidth, width;
		public int preHeight, height;
		public int level;
		public BufferedImage preImage, image;

		public ImageBlock(BufferedImage image, int x, int y) {
			this.image = image;
			this.preImage = image;
			this.x = x;
			this.y = y;
			this.width = image.getWidth();
			this.height = image.getHeight();
			this.preWidth = image.getWidth();
			this.preHeight = image.getHeight();
		}

		public boolean isHit(int x, int y) {
			if (x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height) {
				return true;
			}
			return false;
		}
			
			
	}
}