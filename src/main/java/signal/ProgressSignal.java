package signal;

import mvc.ISignal;

public class ProgressSignal implements ISignal {

    public double progress;

    public ProgressSignal(double progress) {
        this.progress = progress;
    }

    @Override
    public Object getData() {
        return progress;
    }
}