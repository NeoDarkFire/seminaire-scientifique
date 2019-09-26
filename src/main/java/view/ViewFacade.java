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
		wdw.setVisible(true);
		auth = new Frm_Auth(wdw);
		dcode = new Frm_Decrypt(wdw);
		select = new Frm_Select(wdw);
		auth.initiate();
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
		String name = ((Class) signal.getType()).getSimpleName();
		System.out.printf("View received Signal: %s\n", name);
		switch (name)
		{
			case "LoginSignal":
				if (wdw.getTitle().equals("Connexion"))
				{
					Component component = wdw.getPanel().getComponent(wdw.getPanel().getComponentCount()-1);
					switch ((LoginSignal.Status)signal.getData())
					{
						case SUCCESS:
					  		select.initiate();
						break;
						case LOGIN_FAILED:
							if (!((JLabel)component).getText().equals("    Erreur identifiant"))
							{
								((JLabel)component).setText("    Erreur identifiant");
							}
						break;
						case PASSWORD_FAILED:
							if (!((JLabel)component).getText().equals("       Erreur mot de passe"))
							{
								((JLabel)component).setText("       Erreur mot de passe");
							}
						break;
					}
				}
			break;
			case "ProgressSignal":
				if (wdw.getTitle().equals("Traitement du cryptage"))
				{
					Component component2 = wdw.getPanel().getComponent(wdw.getPanel().getComponentCount()-1);
					double value = (Double)signal.getData();
					String Int = String.format("Progression : %.2f %%  ", value);
					for (int i=0; i<value*100.0; i+=5)
					{
						Int += "■";
					}
					for (int i=0; i<(1-value)*100.0; i+=5)
					{
						Int += "□";
					}
					((JLabel)component2).setText(Int);
				break;
				}
			case "KeySignal":
				if (wdw.getTitle().equals("Traitement du cryptage"))
				{
					Component component3 = wdw.getPanel().getComponent(wdw.getPanel().getComponentCount()-1);
					switch ((String)signal.getData())
					{
						case "":
							String CharDeCourse = "Impossible de décrypter la clé";
							((JLabel)component3).setText(CharDeCourse);
						break;
						case "ER":
							String Charette = "Fichiers de lecture et d'écriture corrompus, veuillez réessayer";
							((JLabel)component3).setText(Charette);
						break;
						default:
							String Char = "Décryptage réussi, clé: ";
							Char += (String)signal.getData();
							((JLabel)component3).setText(Char);
						break;
					}
				}
				
			break;
		} 
	}

	@Override
	public void update(Observable o, Object arg)
	{
		this.onModelEvent((IModel) o, (ISignal) arg);
	}
}
