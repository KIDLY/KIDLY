package KidlyGui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.Action;

import java.util.Collections;

import java.awt.Panel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

	public class canvasPanel extends JPanel implements MouseListener, MouseMotionListener{

		Timer timer;
		public int x = 50, y = 50;
		public int rectX = 50, rectY = 50;
		public int mouseX = 0, mouseY = 0;
		boolean flag = false;
		Graphics g;

        private ImageBlockManager IBManager;

		public canvasPanel() {
            /* init a ImageBlockManager and load in a picture*/
            this.IBManager = new ImageBlockManager();
            BufferedImage bi = null;
            try{
                bi = ImageIO.read(new File("test1.jpg"));
            }catch(IOException e){
            }
            ImageBlock ib = new ImageBlock(bi, 0, 0); 
            this.IBManager.addImageBlock(ib);
            try{
                bi = ImageIO.read(new File("test2.jpg"));
            }catch(IOException e){
            }
            ib = new ImageBlock(bi, 0, 0); 
            this.IBManager.addImageBlock(ib);
            this.IBManager.selectLayout(0);

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
            }
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if (flag == true) {
				int x = e.getX();
				int y = e.getY();
                this.IBManager.moveImages(x, y);
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
			buffer = createImage(330, 450);
			bg = buffer.getGraphics();
            ArrayList<ImageBlock> ibl = this.IBManager.getBlockList();
            ImageBlock focusBlock = this.IBManager.getHoldedImageBlock();
            for (int i = ibl.size()-1; i>=0; i--) {
                ImageBlock ib = ibl.get(i);
                if (ib == focusBlock) {
                    bg.drawRect(focusBlock.x-1, focusBlock.y-1, focusBlock.width+2, focusBlock.height+2);
                }
                bg.drawImage(ib.image, ib.x,ib.y,null);
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
			if(flag){
				this.IBManager.moveImages(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
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
    public class ImageBlockManager{
        private ArrayList<ImageBlock> blockList = new ArrayList<ImageBlock>();
        private ImageBlock holdedBlock = null;
        private int offsetX;
        private int offsetY;

        public ImageBlockManager(){
        }

        /**
         * add a image block to ImageBlockManager
         */
        public void addImageBlock(ImageBlock block){
            block.level = blockList.size();
            this.blockList.add(block);
        }

        /**
         * skew a image from -180 degree to +180 degree
         */
        public void skewImage(int degree){
            /* TODO */
        }

        /**
         * return image skew degree
         */
        public int getSkewDegree() throws Exception{
            if (this.holdedBlock != null) {
                return this.holdedBlock.degree;
            }else{
                throw new Exception("None holded block");
            }
        }

        /**
         * set skew degree for image
         *  @param:int degree
         */
        public void setSkewDegree(int degree){
            /* TODO */
        }
        
        /**
         * scale image from 1% to 200%
         */
        public void scaleImage(){
            /*TODO*/
        }

        /**
         * return percentage if scale
         *  @return:int to presentage
         */
        public int getScalePercentage() throws Exception{
            if (this.holdedBlock != null) {
                return this.holdedBlock.scalePercentage;
            }else{
                throw new Exception("None holded block");
            }
        }

        /**
         * set scale percentage for 1 to 200
         *  @param:int scale percentage
         */
        public void setScalePercentage(int scale){
            /* TODO */
        }
        
        public void moveImages(int x, int y){
            if (holdedBlock != null) {
                this.holdedBlock.x = x - this.offsetX;
                this.holdedBlock.y = y - this.offsetY;
            }
        }

        /**
         * Raise layout level to higher
         * (high Priority to print on canvas
         */
        public void raiseLayout(){
            int i = this.blockList.indexOf(this.holdedBlock);
            this.holdedBlock.level = i-1;
            this.blockList.get(i-i).level = i;
            Collections.swap(this.blockList, i, i-1);
            ArrayList<String> arrayList = new ArrayList<String>();
        }

        /**
         * reduce layout level to lower
         * (lower Priority to print and may under other layout
         */
        public void reduceLayout(){
            int i = this.blockList.indexOf(this.holdedBlock);
            this.holdedBlock.level = i+1;
            this.blockList.get(i+i).level = i;
            Collections.swap(this.blockList, i, i+1);
        }

        /**
         * get level of layout
         * (higher value means higher priority on canvas)
         *  @return:int level of layout
         */
        public int getLayoutLevel() throws Exception{
            if (this.holdedBlock != null) {
                return this.holdedBlock.level;
            }else{
                throw new Exception("None holded block");
            }
        }

        /**
         * return holded (clicked) image block
         */
        public ImageBlock getHoldedImageBlock(){
            return this.holdedBlock;
        }

        /**
         * Varify is user hit in a image block
         */
        public boolean isCanvasHit(int x, int y){
        	for (ImageBlock ib: this.blockList ) {
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
         *  @return:arraylist<ImageBlock>
         */
        public ArrayList<ImageBlock> getBlockList(){
            return this.blockList;
        }

        /**
         * return the length of layouts in ImageBlockManager
         *  @return:int length of layout
         */
        public int getLayoutLength(){
            return this.blockList.size();
        }

        /**
         * use indexer to select a image block
         */
        private void selectLayout(int index){
            this.holdedBlock = this.blockList.get(index);
        }

    }
    private class ImageBlock{
        public int scalePercentage = 100;
        public int degree = 0;
        public int x;
        public int y;
        public int width;
        public int height;
        public int level;
        public BufferedImage image;
        public ImageBlock(BufferedImage image, int x, int y){
            this.image = image;
            this.x = x;
            this.y = y;
            this.width = image.getWidth();
            this.height = image.getHeight();
        }
        public boolean isHit(int x, int y){
            if (x > this.x && x < this.x+this.width && y > this.y && y < this.y+this.height) {
                return true;
            }
			return false;
        }
    } 
}
