package view;

public class Frm_Decrypt
{
	private Window wdw;
	
	Frm_Decrypt(Window wdw)
	{
		this.wdw = wdw;
	}
	
	public void initiate()
	{
		wdw.setVisible(false);
		wdw.build3();
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
