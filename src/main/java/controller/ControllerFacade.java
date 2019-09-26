package controller;

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
    	final String name = signal.getClass().getSimpleName();
		System.out.printf("Controller received Signal: %s\n", name);
        switch (name) {
	        case "CredentialSignal":
	        	final CredentialSignal credential = (CredentialSignal) signal;
	        	model.selectIDbyloginPassword(credential.login, credential.password);
	        	break;
			case "PathSignal":
				final PathSignal path = (PathSignal) signal;
				model.decrypt(path.in, path.out);
				break;
	        default:
	        	System.err.printf("ERROR on onViewEvent - Unknown Signal: %s\n", name);
	        	break;
        }
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		this.onViewEvent((IView) arg0, (ISignal) arg1);
	}
}
