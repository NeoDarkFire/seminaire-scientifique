package view;

import mvc.ISignal;
import signal.CredentialSignal;
import signal.InteruptSignal;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class ButtonBack extends AbstractAction
{
	private ViewFacade vf;
	
	public ButtonBack(ViewFacade vf2)
	{
		super();
		
		this.vf = vf2;
	}
 
	public void actionPerformed(ActionEvent e)
	{ 
		vf.notifyControllers(new InteruptSignal());
		vf.wdw.build2();
	} 
}
