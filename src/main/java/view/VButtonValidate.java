package view;

import mvc.ISignal;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class VButtonValidate extends AbstractAction
{
	private ViewFacade vf;
	
	public VButtonValidate(String texte, ViewFacade vf2)
	{
		super(texte);
		
		this.vf = vf2;
	}
 
	public void actionPerformed(ActionEvent e)
	{ 
		vf.notifyControllers(new PathSignal(vf.source, vf.cible));
	} 
}
