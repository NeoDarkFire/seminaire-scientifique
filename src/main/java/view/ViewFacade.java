package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mvc.IController;
import mvc.IModel;
import mvc.ISignal;
import mvc.IView;

public class ViewFacade extends Observable implements IView, Observer
{	
	private Frm_Auth auth;
	private Frm_Decrypt dcode;
	Window wdw;	
	JTextField login;
	JPasswordField mdp;
	JTextField source;
	JTextField cible;
	
	
	public ViewFacade()
	{
		auth = new Frm_Auth();
		dcode = new Frm_Decrypt();
		wdw = new Window(this);
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
	public void onModelEvent(IModel model, Object arg)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Observable o, Object arg)
	{
		this.onModelEvent((IModel)o, arg);
	}
}
