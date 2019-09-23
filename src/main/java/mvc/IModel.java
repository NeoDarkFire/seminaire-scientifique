package mvc;

public interface IModel
{

	/**
	 * Ajoute une vue en tant qu'observer
	 * @param view La vue
	 */
	void attachView(IView view);
	
	/**
	 * Notifie les vues d'un changement
	 * @param signal Un signal
	 */
	void notifyViews(ISignal signal);

}
