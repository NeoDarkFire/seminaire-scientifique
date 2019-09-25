package model;

import java.util.ArrayList;

import mvc.IModel;
import mvc.ISignal;
import mvc.IView;

public class ModelFacade implements IModel {
	
	public void decrypt(String in, String out) {
		//TODO a refaire
	}
	
	public void selectIDbyloginPassword(String login, String password) {
		int result = Map_P.selectIDbyloginPassword(login, password);
		//TODO renvoyer un signal
	}
	
    @Override
    public void attachView(final IView view) {
        // TODO: method stub
    }

    @Override
    public void notifyViews(final ISignal signal) {
        // TODO: method stub
    }
}
