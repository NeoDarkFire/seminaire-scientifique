import controller.ControllerFacade;
import model.ModelFacade;
import view.ViewFacade;

import javax.swing.*;

public class Main {

    public static void main(final String[] args) {
        System.out.println("running...");

        final ModelFacade model = new ModelFacade();
        final ControllerFacade controller = new ControllerFacade(model);
        SwingUtilities.invokeLater(() -> {
            final ViewFacade view = new ViewFacade();
            view.attachController(controller);
            model.attachView(view);
        });

        System.out.println("launched!");
    }

}
