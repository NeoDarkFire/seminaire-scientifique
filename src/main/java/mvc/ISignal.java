package mvc;

public interface ISignal
{
	/**
	 * Obtient le type du signal
	 * @return Un objet quelconque
	 */
	Object getType();
	
	/**
	 * Obtient les données transmises par le signal
	 * @return Un objet quelconque
	 */
	Object getData();
}
