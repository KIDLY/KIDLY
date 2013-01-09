package dot;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

	private JFileChooser chooser = null;
	//private int retVal = 0;
	
	public FileChooser() {
		
		super();
		initialize();
	
	}

	private void initialize() {
		
		chooser = new JFileChooser();

		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Format Only", "csv");
		chooser.setFileFilter(filter);

		//retVal = chooser.showOpenDialog(null);
		
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
