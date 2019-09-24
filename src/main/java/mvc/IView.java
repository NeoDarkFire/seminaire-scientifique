package mvc;

public interface IView
{

	/**
	 * Ajoute un controller en tant qu'observer
	 * @param controller Un controleur
	 */
	void attachController(IController controller);
	
	/**
	 * Notifie les controlleurs d'une action de l'utilisateur
	 * @param signal Un signal
	 */
	void notifyControllers(ISignal signal);

	/**
	 * Reçoit une notification d'un modèle
	 * @param model Le modèle
	 * @param signal Un signal quelconque
	 */
	void onModelEvent(IModel model, ISignal signal);
}
