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
		
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{	
				ViewFacade vf = new ViewFacade();
				vf.getDcode().initiate();
				vf.onModelEvent((new IModel()
				{

					@Override
					public void attachView(IView view) 
					{
						// TODO Auto-generated method stub
						
					}

					@Override
					public void notifyViews(ISignal signal)
					{
						// TODO Auto-generated method stub
						
					}
				}),(new ProgressSignal(0.0)));
				vf.onModelEvent((new IModel()
				{

					@Override
					public void attachView(IView view) 
					{
						// TODO Auto-generated method stub
						
					}

					@Override
					public void notifyViews(ISignal signal)
					{
						// TODO Auto-generated method stub
						
					}
				}),(new ProgressSignal(0.10)));
				
				vf.onModelEvent((new IModel()
				{

					@Override
					public void attachView(IView view) 
					{
						// TODO Auto-generated method stub
						
					}

					@Override
					public void notifyViews(ISignal signal)
					{
						// TODO Auto-generated method stub
						
					}
				}),(new ProgressSignal(0.20)));
				
				vf.onModelEvent((new IModel()
				{

					@Override
					public void attachView(IView view) 
					{
						// TODO Auto-generated method stub
						
					}

					@Override
					public void notifyViews(ISignal signal)
					{
						// TODO Auto-generated method stub
						
					}
				}),(new ProgressSignal(0.30)));
				
				vf.onModelEvent((new IModel()
				{

					@Override
					public void attachView(IView view) 
					{
						// TODO Auto-generated method stub
						
					}

					@Override
					public void notifyViews(ISignal signal)
					{
						// TODO Auto-generated method stub
						
					}
				}),(new ProgressSignal(0.50)));
				
				vf.onModelEvent((new IModel()
				{

					@Override
					public void attachView(IView view) 
					{
						// TODO Auto-generated method stub
						
					}

					@Override
					public void notifyViews(ISignal signal)
					{
						// TODO Auto-generated method stub
						
					}
				}),(new ProgressSignal(0.20)));
			}
		});
	}

}
