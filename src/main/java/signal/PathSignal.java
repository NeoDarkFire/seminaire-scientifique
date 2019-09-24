package signal;

import mvc.ISignal;

import java.io.File;

public class PathSignal implements ISignal {

    public String in;
    public String out;

    public PathSignal(final String in, final String out) {
        this.in = in;
        this.out = out;
    }
}