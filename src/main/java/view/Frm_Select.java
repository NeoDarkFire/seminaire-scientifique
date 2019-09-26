package view;

public class Frm_Select
{
	private Window wdw;
	
	Frm_Select(Window wdw)
	{
		this.wdw = wdw;
	}
	
	public void initiate()
	{
		wdw.setVisible(false);
		wdw.build2();
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
