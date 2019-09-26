package view;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import mvc.IModel;
import mvc.ISignal;
import mvc.IView;
import signal.*;

import javax.swing.JLabel;

public class fakeMain
{

	public static void main(String[] args)
	{
		final IModel emptyModel = new IModel() {
			@Override public void attachView(IView view) {}
			@Override public void notifyViews(ISignal signal) {}
		};

		SwingUtilities.invokeLater(() -> {
			ViewFacade vf = new ViewFacade();
			vf.getAuth().initiate();
			vf.onModelEvent(emptyModel, new LoginSignal(LoginSignal.Status.SUCCESS));
			//vf.onModelEvent(emptyModel, new LoginSignal(LoginSignal.Status.LOGIN_FAILED));
			//vf.onModelEvent(emptyModel, new LoginSignal(LoginSignal.Status.PASSWORD_FAILED));
			//vf.getDcode().initiate();
			//vf.onModelEvent(emptyModel, new ProgressSignal(0.5));
		});
	}

}
