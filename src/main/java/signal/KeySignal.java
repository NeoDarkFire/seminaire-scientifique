package signal;

import mvc.ISignal;

public class KeySignal implements ISignal {

    public String key;

    public KeySignal() {
        this.key = "";
    }

    public KeySignal(String key) {
        this.key = key;
    }

    @Override
    public Object getData() {
        return key;
    }
}
