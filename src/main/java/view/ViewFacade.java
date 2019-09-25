package view;

import java.awt.Component;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mvc.IController;
import mvc.IModel;
import mvc.ISignal;
import mvc.IView;
import signal.*;

public class ViewFacade extends Observable implements IView, Observer
{	
	private Frm_Auth auth;
	private Frm_Select select;
	private Frm_Decrypt dcode;
	Window wdw;	
	JTextField login;
	JPasswordField mdp;
	JTextField source;
	JTextField cible;
	
	
	public ViewFacade()
	{
		wdw = new Window(this);
		auth = new Frm_Auth(wdw);
		dcode = new Frm_Decrypt(wdw);
	}
	
	public Frm_Auth getAuth()
	{
		return auth;
	}

	public void setAuth(Frm_Auth auth)
	{
		this.auth = auth;
	}

	public Frm_Decrypt getDcode()
	{
		return dcode;
	}

	public void setDcode(Frm_Decrypt dcode)
	{
		this.dcode = dcode;
	}
	
	public Frm_Select getSelect()
	{
		return select;
	}

	public void setSelect(Frm_Select select)
	{
		this.select = select;
	}
	
	@Override
	public void attachController(IController controller)
	{
		this.addObserver((Observer)controller); 
	}

	@Override
	public void notifyControllers(ISignal signal)
	{
		this.setChanged();
		this.notifyObservers(signal);
	}

	@Override
	public void onModelEvent(IModel model, ISignal signal)
	{
		switch (((Class)signal.getType()).getName())
		{
			case "LoginSignal":
				Component component = wdw.getPanel().getComponent(wdw.getPanel().getComponentCount()-1);
				switch ((LoginSignal.Status)signal.getData())
				{
					case SUCCESS:
				  		wdw.build2();
					break;
					case LOGIN_FAILED:
						if (((JLabel)component).getText()!="Erreur identifiant")
						{
							((JLabel)component).setText("Erreur identifiant");
						}
					break;
					case PASSWORD_FAILED:
						if (((JLabel)component).getText()!="Erreur mot de passe")
						{
							((JLabel)component).setText("Erreur mot de passe");
						}
					break;
				}
			break;
			case "ProgressSignal":
				Component component2 = wdw.getPanel().getComponent(wdw.getPanel().getComponentCount()-1);
				String Int = "Progression : ";
				for (int i=0; i<(int)signal.getData(); i+=5)
				{
					Int += "■";
				}
				Int = "%";
				((JLabel)component2).setText(Int);
			break;
			case "KeySignal":
				wdw.addText(wdw.getPanel(), (String)signal.getData());
			break;
		} 
	}

	@Override
	public void update(Observable o, Object arg)
	{
		this.onModelEvent((IModel) o, (ISignal) arg);
	}
}
