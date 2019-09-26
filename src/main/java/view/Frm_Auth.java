package view;

public class Frm_Auth
{
	private Window wdw;
	
	Frm_Auth(Window wdw)
	{
		this.wdw = wdw;
	}
	
	public void initiate()
	{
		wdw.setVisible(false);
		wdw.build();
		wdw.setVisible(true);
	}

	public Window getWdw()
	{
		return wdw;
	}

	public void setWdw(Window wdw)
	{
		this.wdw = wdw;
	}

}
