package view;

import mvc.ISignal;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

public class ButtonShowFiles extends AbstractAction
{
	private ViewFacade vf;
	private int number = 0;
	static int count = 1;
	
	public ButtonShowFiles(String texte, ViewFacade vf2)
	{
		super(texte);
		
		number = count;
		ButtonShowFiles.count ++;
		
		this.vf = vf2;
	}
 
	public void actionPerformed(ActionEvent e)
	{ 
		if (number != count-1)
		{
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = fc.getSelectedFile();
				vf.source.setText(file.getAbsolutePath());
			} 
		}
		else
		{
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = fc.getSelectedFile();
				vf.cible.setText(file.getAbsolutePath());
			} 
		}
	}
}
