package mvc;

public interface IController
{
	/**
	 * Reçoit une notification d'une vue
	 * @param view La vue
	 * @param signal Un signal
	 */
	void onViewEvent(IView view, ISignal signal);
}
