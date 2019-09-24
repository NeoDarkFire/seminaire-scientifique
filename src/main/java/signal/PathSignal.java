package signal;

import mvc.ISignal;

import java.io.File;

public class PathSignal implements ISignal {

    public File in;
    public File out;

    public PathSignal(final File in, final File out) {
        this.in = in;
        this.out = out;
    }
}