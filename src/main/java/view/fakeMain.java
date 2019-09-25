package view;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import javax.swing.JLabel;

public class fakeMain
{

	public static void main(String[] args)
	{
		
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{	
				ViewFacade vf = new ViewFacade();
				vf.wdw.setVisible(true);
				vf.getDcode().initiate();
				vf.wdw.addText(vf.wdw.getPanel(), "dsfdhkjbgkjdf");
				String Int = "";
				System.out.println(Int += "");//vf.getDcode().initiate();
			}
		});
	}

}
