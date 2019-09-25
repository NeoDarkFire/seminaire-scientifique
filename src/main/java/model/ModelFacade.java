package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import mvc.IModel;
import mvc.ISignal;
import mvc.IView;
import signal.LoginSignal;
import signal.LoginSignal.Status;

public class ModelFacade extends Observable implements IModel {
	
	
	public void decrypt(String in, String out) {
		//TODO Alexis
	}
	
	public void selectIDbyloginPassword(String login, String password) {
		int result = Map_P.selectIDbyloginPassword(login, password);
		switch(result){
			case 0:
				//TODO error
				break;
			case 1:
				notifyViews(new LoginSignal(Status.LOGIN_FAILED));
				break;
			case 2:
				notifyViews(new LoginSignal(Status.PASSWORD_FAILED));
				break;
			case 3:
				notifyViews(new LoginSignal(Status.SUCCESS));
				break;
			default:
				//TODO error
				break;
		}
	}
	
	@Override
    public void attachView(IView view)
    {
        this.addObserver((Observer) view);
        view.onModelEvent(this, null);
    }

    @Override
    public void notifyViews(ISignal signal)
    {
        this.setChanged();
        this.notifyObservers(signal);
    }
}
