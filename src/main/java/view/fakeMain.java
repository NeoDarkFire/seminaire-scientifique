package view;

import javax.swing.SwingUtilities;

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
				vf.wdw.build2();
			}
		});
	}

}
