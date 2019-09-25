package controller;

import model.ModelFacade;
import mvc.IController;
import mvc.ISignal;
import mvc.IView;

public class ControllerFacade implements IController {

    private final ModelFacade model;

    public ControllerFacade(final ModelFacade model) {
        this.model = model;
    }

    @Override
    public void onViewEvent(final IView view, final ISignal signal) {
        // TODO: method stub
    }
}
