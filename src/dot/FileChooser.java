package dot;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

	private JFileChooser chooser = null;
	private String format = null;
	//private int retVal = 0;
	
	public FileChooser(String format) {
		
		super();
		this.format = format;
		initialize();
	
	}

	private void initialize() {
		
		chooser = new JFileChooser();

		FileNameExtensionFilter filter = new FileNameExtensionFilter(format + " Format Only", format);
		chooser.setFileFilter(filter);

		int retVal = chooser.showOpenDialog(null);
		
	}
	

	/* Get Choosed File Path.
	 * 
	 * return String path
	 * 
	 * */
	public String getPath() {

		try{
			
			//if (retVal == JFileChooser.APPROVE_OPTION)
			//	System.out.println("OutputFilePath: " + chooser.getSelectedFile().getAbsolutePath());
			//else
			//	System.out.println("No OutputFilePath.");
			
			String path = chooser.getSelectedFile().getAbsolutePath();
			return path;
		
		}
		catch (NullPointerException e) {
			
			return null;
		}

	}
	
}
