package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import model.ModelFacade;
import mvc.IController;
import mvc.ISignal;
import mvc.IView;
import signal.CredentialSignal;
import signal.PathSignal;

public class ControllerFacade implements Observer, IController {

    private final ModelFacade model;

    public ControllerFacade(final ModelFacade model) {
        this.model = model;
    }

    @Override
    public void onViewEvent(final IView view, final ISignal signal) {
        switch(signal.getClass().getSimpleName()) {
	        case "CredentialSignal":
	        	CredentialSignal credential = (CredentialSignal) signal;
	        	model.selectIDbyloginPassword(credential.login, credential.password);
	        	break;
	        case "KeySignal":
	        	PathSignal path = (PathSignal) signal;
	        	model.decrypt(path.in, path.out);
	        	break;
	        default:
	        	System.out.println("ERROR on onViewEvent");
	        	break;
        }
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		this.onViewEvent((IView) arg0, (ISignal) arg1);
	}
}
