package view;

public class Frm_Select
{
	private Window wdw;
	
	Frm_Select(Window wdw)
	{
		this.wdw = wdw;
		initiate();
	}
	
	public void initiate()
	{
		wdw.build2();
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
