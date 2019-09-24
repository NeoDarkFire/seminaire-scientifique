package view;

import mvc.ISignal;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class ButtonSendLogin extends AbstractAction
{
	private ViewFacade vf;
	
	public ButtonSendLogin(String texte, ViewFacade vf2)
	{
		super(texte);
		
		this.vf = vf2;
	}
 
	public void actionPerformed(ActionEvent e)
	{ 
		System.out.println(vf.login.getText());
		System.out.println(vf.mdp.getPassword());
	} 
}
