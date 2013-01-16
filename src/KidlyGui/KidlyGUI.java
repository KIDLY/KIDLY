package KidlyGui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class KidlyGUI extends JFrame {

	private JPanel contentPane;
	private BufferedImage buffer = null;
	private Graphics2D bg;
	private JSlider skewSlider, scaleSlider;
	private JSpinner spinner;
	private JLabel lbl_scaleNum, lbl_skewNum;
	Boolean isBlockChanged = false;

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

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					if (IBManager.holdedBlock != null) {
						IBManager.changeLayout((int) spinner.getValue());
						spinner.setValue(IBManager.holdedBlock.level);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		spinner.setBounds(457, 129, 134, 32);
		contentPane.add(spinner);

		JLabel lblPriority = new JLabel("Priority :");
		lblPriority.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPriority.setBounds(374, 129, 63, 18);
		contentPane.add(lblPriority);

		JLabel lblSize = new JLabel("Scale :");
		lblSize.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSize.setBounds(374, 195, 46, 15);
		contentPane.add(lblSize);

		scaleSlider = new JSlider();
		scaleSlider.setValue(100);
		scaleSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					if (IBManager != null) {
						IBManager.scaleImage(scaleSlider.getValue());
						lbl_scaleNum.setText("" + IBManager.holdedBlock.scalePercentage);
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
		scaleSlider.setBounds(457, 216, 180, 21);
		contentPane.add(scaleSlider);

		JLabel label_1 = new JLabel("1%                    100%                  200%");
		label_1.setBounds(457, 247, 191, 15);
		contentPane.add(label_1);

		lbl_scaleNum = new JLabel("100");
		lbl_scaleNum.setForeground(Color.RED);
		lbl_scaleNum.setFont(new Font("�s�ө���", Font.PLAIN, 13));
		lbl_scaleNum.setBackground(Color.WHITE);
		lbl_scaleNum.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_scaleNum.setBounds(457, 196, 113, 15);
		contentPane.add(lbl_scaleNum);

		JLabel lblRotation = new JLabel("Rotation :");
		lblRotation.setFont(new Font("Arial", Font.PLAIN, 14));
		lblRotation.setBounds(374, 296, 63, 15);
		contentPane.add(lblRotation);

		skewSlider = new JSlider();
		skewSlider.setMinimum(-180);
		skewSlider.setValue(0);
		skewSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					if (IBManager != null) {
						lbl_skewNum.setText("" + IBManager.holdedBlock.degree);
						IBManager.skewImage(skewSlider.getValue());
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		skewSlider.setMaximum(180);
		skewSlider.setBackground(Color.LIGHT_GRAY);
		skewSlider.setBounds(457, 326, 180, 21);
		contentPane.add(skewSlider);

		lbl_skewNum = new JLabel("100");
		lbl_skewNum.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_skewNum.setForeground(Color.RED);
		lbl_skewNum.setFont(new Font("�s�ө���", Font.PLAIN, 13));
		lbl_skewNum.setBackground(Color.WHITE);
		lbl_skewNum.setBounds(457, 296, 113, 15);
		contentPane.add(lbl_skewNum);

		JLabel label = new JLabel("-180\u00B0                    0\u00B0                    180\u00B0");
		label.setBounds(460, 360, 177, 15);
		contentPane.add(label);

		canvasPanel panel = new canvasPanel();
		panel.setBounds(29, 107, 312, 439);
		panel.setBackground(Color.WHITE);
		contentPane.add((canvasPanel)panel);
        this.canvas = panel;

		JLabel label_2 = new JLabel("%");
		label_2.setForeground(Color.RED);
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(547, 196, 46, 15);
		contentPane.add(label_2);

		JLabel label_4 = new JLabel("\u00B0");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setForeground(Color.RED);
		label_4.setBounds(547, 296, 46, 15);
		contentPane.add(label_4);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 20, 513, 69);
		contentPane.add(toolBar);

		JButton btnAddText = new JButton("Add Kidly Chart");
		toolBar.add(btnAddText);
		btnAddText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddText.setIcon(new ImageIcon(KidlyGUI.class.getResource("/res/add_graph.png")));

		JButton btnAddNewText = new JButton("Add New Image");
		toolBar.add(btnAddNewText);
		btnAddNewText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser jFileOpen = new JFileChooser();
					jFileOpen.setDialogTitle("Add New Image");
					jFileOpen.setMultiSelectionEnabled(false);
					jFileOpen.showOpenDialog(null);
					File file = jFileOpen.getSelectedFile();
					System.out.println("Open:" + file.getAbsolutePath().toString());
					/* TODO got the file path */
				} catch (Exception ef) {
					ef.getStackTrace();
				}
			}
		});
		btnAddNewText.setIcon(new ImageIcon(KidlyGUI.class.getResource("/res/add_image.png")));

		JButton btnAddText_1 = new JButton("Add Text");
		toolBar.add(btnAddText_1);
		btnAddText_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnAddText_1.setIcon(new ImageIcon(KidlyGUI.class.getResource("/res/add-text.png")));

		JSeparator separator = new JSeparator();
		separator.setBounds(357, 171, 302, 97);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(357, 272, 302, 97);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(357, 398, 302, 97);
		contentPane.add(separator_2);

		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setBounds(513, 20, 171, 69);
		contentPane.add(toolBar_1);

		JButton btnOutput = new JButton("Save as ...");
		btnOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                System.out.println("hello");
                saveImage();
			}
		});
		btnOutput.setIcon(new ImageIcon(KidlyGUI.class.getResource("/res/save_file.png")));
		toolBar_1.add(btnOutput);
		
		JButton btnNewButton = new JButton("Cancel Image");
		btnNewButton.setIcon(new ImageIcon(KidlyGUI.class.getResource("/res/cancel.png")));
		btnNewButton.setBounds(374, 424, 139, 23);
		contentPane.add(btnNewButton);
	}
    public void saveImage(){
        try
        {
            BufferedImage image = this.canvas.getCanvasImage();
            ImageIO.write(image,"jpeg", new File("/home/lucas/a.jpg"));
        }
        catch(Exception exception)
        {
            //code
        }
    }

	private ImageBlockManager IBManager;
    private canvasPanel canvas;

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
            IBManager.removeImageBlock();

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
				skewSlider.setValue(IBManager.holdedBlock.degree);
				lbl_skewNum.setText("" + IBManager.holdedBlock.degree);
				spinner.setValue(IBManager.holdedBlock.level);
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

			buffer = new BufferedImage(330, 450, BufferedImage.TYPE_INT_RGB);
			bg = buffer.createGraphics();
			bg.setPaint(Color.WHITE);
			bg.fillRect(0, 0, 330, 450);
			ArrayList<ImageBlock> ibl = IBManager.getBlockList();
			ImageBlock focusBlock = IBManager.getHoldedImageBlock();
			for (int i = ibl.size() - 1; i >= 0; i--) {
				ImageBlock ib = ibl.get(i);
				if (focusBlock == ib) {
					ib.paintOnGraphics2D(bg);
				}
				ib.paintOnGraphics2D(bg);
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

        public BufferedImage getCanvasImage(){
            if (buffer != null) {
                return buffer;
            }else{
            	return null;
            }
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
		 * 
		 * raise the spinner upper bounds. @author Dotto
		 */
		public void addImageBlock(ImageBlock block) {
			block.level = this.blockList.size();
			this.blockList.add(block);
			spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), new Integer(this.blockList.size() - 1), new Integer(1)));
		}

		/**
		 * skew a image from -180 degree to +180 degree
		 * 
		 * @throws Exception
		 */
		public void skewImage(int degree) throws Exception {
			if (this.holdedBlock != null) {
				this.holdedBlock.degree = degree;
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
		public void scaleImage(int scalePercentage) throws Exception {
			if (holdedBlock != null) {
				this.holdedBlock.scalePercentage = scalePercentage;
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
		 * Manage the Layout event.Raise or reduce its level according to
		 * spinnerNum.
		 * 
		 * @param spinnerNum
		 */
		public void changeLayout(int spinnerNum) {
			/* TODO */
			if (spinnerNum < this.holdedBlock.level) {
				raiseLayout();
			}
			if (spinnerNum > this.holdedBlock.level) {
				reduceLayout();
			}

		}

		/**
		 * Raise layout level to higher (high Priority to print on canvas
		 */
		public void raiseLayout() {
			int i = this.blockList.indexOf(this.holdedBlock);
			this.holdedBlock.level = i - 1;
			this.blockList.get(i - 1).level = i;
			Collections.swap(this.blockList, i, i - 1);
			// ArrayList<String> arrayList = new ArrayList<String>();
		}

		/**
		 * reduce layout level to lower (lower Priority to print and may under
		 * other layout
		 */
		public void reduceLayout() {
			int i = this.blockList.indexOf(this.holdedBlock);
			this.holdedBlock.level = i + 1;
			this.blockList.get(i + 1).level = i;
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
         * remove indexed image block
         */
        public ImageBlock removeImageBlock(){
            if (this.holdedBlock != null) {
                int i = this.blockList.indexOf(this.holdedBlock);
                ImageBlock remove = this.blockList.remove(i);
                this.rearrangeLevel();
                return remove;
            }else{
                return null;
            }
        }

		/**
		 * use indexer to select a image block
		 */
		private void selectLayout(int index) {
			this.holdedBlock = this.blockList.get(index);
		}

		/**
		 * rearrange level in block list
		 */
		private void rearrangeLevel() {
			for (int i = 0; i < this.blockList.size(); i++) {
				this.blockList.get(i).level = i;
			}
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
			int ox = this.x - (this.width - this.preWidth) / 2;
			int oy = this.y - (this.height - this.preHeight) / 2;
			if (x > ox && x < ox + this.width && y > oy && y < oy + this.height) {
				return true;
			}
			return false;
		}

		public void paintOnGraphics2D(Graphics2D bg) {
			this.width = (int) this.preWidth * this.scalePercentage / 100;
			this.height = (int) this.preHeight * this.scalePercentage / 100;
			AffineTransform at = new AffineTransform();
			at.translate(this.x, this.y);
			at.translate(this.preWidth / 2, this.preHeight / 2);
			at.rotate(Math.PI * this.degree / 180);
			at.scale((float) this.scalePercentage / 100, (float) this.scalePercentage / 100);
			at.translate(-this.preWidth / 2, -this.preHeight / 2);
			bg.drawImage(this.image, at, null);
		}

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
