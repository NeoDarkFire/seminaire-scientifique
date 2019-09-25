package signal;


import mvc.ISignal;

import java.io.File;

public class CredentialSignal implements ISignal {

    public String login;
    public String password;

    public CredentialSignal(final String login, final String password) {
        this.login = login;
        this.password = password;
    }
}