package view;

import mvc.ISignal;
import signal.*;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.AbstractAction;
import javax.swing.JLabel;

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
		if(!vf.cible.getText().equals("") && !vf.source.getText().equals("") && Files.exists(Paths.get(vf.source.getText())) && Files.exists(Paths.get(vf.cible.getText())))
		{
			vf.notifyControllers(new PathSignal(vf.source.getText(), vf.cible.getText()));
			vf.wdw.build3();
		}
		else
		{
			Component component = vf.wdw.getPanel().getComponent(vf.wdw.getPanel().getComponentCount()-1);
			if (!((JLabel)component).getText().equals("Fichiers introuvables"))
			{
				vf.wdw.addText(vf.wdw.getPanel(), "Fichiers introuvables");
			}
		}
	} 
}
