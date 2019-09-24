package signal;

import mvc.ISignal;

public class LoginSignal implements ISignal {

    public enum Status {
        SUCCESS,
        LOGIN_FAILED,
        PASSWORD_FAILED
    }

    public Status status;

    public LoginSignal(Status status) {
        this.status = status;
    }

    @Override
    public Object getData() {
        return status;
    }
}
