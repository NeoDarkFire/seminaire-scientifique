import controller.ControllerFacade;
import model.ModelFacade;
import view.ViewFacade;

public class Main {

    public static void main(final String[] args) {
        System.out.println("running...");

        ModelFacade model = new ModelFacade();
        ViewFacade view = new ViewFacade();
        ControllerFacade controller = new ControllerFacade(model);

        model.attachView(view);
        view.attachController(controller);

        System.out.println("done!");
    }

}
