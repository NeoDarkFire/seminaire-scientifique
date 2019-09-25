package view;

public class Frm_Decrypt
{
	private Window wdw;
	
	Frm_Decrypt(Window wdw)
	{
		this.wdw = wdw;
		initiate();
	}
	
	public void initiate()
	{
		wdw.build3();
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
