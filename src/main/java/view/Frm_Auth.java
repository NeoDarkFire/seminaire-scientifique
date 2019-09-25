package view;

public class Frm_Auth
{
	private Window wdw;
	
	Frm_Auth(Window wdw)
	{
		this.wdw = wdw;
		initiate();
	}
	
	public void initiate()
	{
		wdw.build();
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
