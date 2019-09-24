package view;

import mvc.ISignal;
import signal.*;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class ButtonValidate extends AbstractAction
{
	private ViewFacade vf;
	
	public ButtonValidate(String texte, ViewFacade vf2)
	{
		super(texte);
		
		this.vf = vf2;
	}
 
	public void actionPerformed(ActionEvent e)
	{ 
		vf.notifyControllers(new PathSignal(vf.source.getText(), vf.cible.getText()));
		System.out.println(vf.source.getText()+" & "+vf.cible.getText());
	} 
}
