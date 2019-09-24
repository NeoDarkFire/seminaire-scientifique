package mvc;

public interface ISignal
{
	/**
	 * Obtient le type du signal
	 * @return Un objet quelconque
	 */
	default Object getType() {
		return this.getClass();
	}
	
	/**
	 * Obtient les données transmises par le signal
	 * @return Un objet quelconque
	 */
	default Object getData() {
		return this;
	}
}
