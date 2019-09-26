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
			vf.getDcode().initiate();
			vf.onModelEvent(emptyModel, new ProgressSignal(0.00));
			vf.onModelEvent(emptyModel, new ProgressSignal(0.10));
			vf.onModelEvent(emptyModel, new ProgressSignal(0.20));
			vf.onModelEvent(emptyModel, new ProgressSignal(0.30));
			vf.onModelEvent(emptyModel, new ProgressSignal(0.50));
			vf.onModelEvent(emptyModel, new ProgressSignal(0.20));
		});
	}

}
