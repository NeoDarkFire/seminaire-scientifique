package model;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import mvc.IModel;
import mvc.ISignal;
import mvc.IView;
import signal.KeySignal;
import signal.LoginSignal;
import signal.LoginSignal.Status;
import signal.ProgressSignal;

public class ModelFacade extends Observable implements IModel {

	private static final String INITIAL_KEY = "awqpmndf";
	private static final int KEY_SIZE = 12;

	private Thread decryptionThread = new Thread();

	public void stopDecryption() {
		decryptionThread.interrupt();
	}

	public void decrypt(final String inFile, final String outFile) {
		stopDecryption();
		decryptionThread = new Thread(() -> {
			try {
				final byte[] content = Files.getContentFrom(inFile);
				final Optional<byte[]> key = Decrypt.decrypt(content, KEY_SIZE, INITIAL_KEY, (progress) -> {
					notifyViews(new ProgressSignal(progress));
					System.out.printf("Progress: %.2f %%\n", progress * 100.0);
				});

				final Optional<byte[]> decrypted = key.map(k -> new XorEncryption(k).decrypt(content));
				if (decrypted.isPresent()) {
					System.out.println("Writing to file...");
					Files.writeTo(outFile, decrypted.get());
				}

				notifyViews(new KeySignal(key
						.map(String::new)
						.orElse("")));
			} catch (final IOException e) {
				notifyViews(new KeySignal("ER"));
			}
		});
		decryptionThread.start();
	}

	public void encrypt(final String content, final String key, final String outFile) {
		final byte[] encyptedBytes = new XorEncryption(key.getBytes()).encrypt(content.getBytes());
		try {
			Files.writeTo(outFile, encyptedBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void selectIDbyloginPassword(final String login, final String password) {
		final int result = Map_P.selectIDbyloginPassword(login, password);
		switch(result){
			case 0:
				//TODO error
				break;
			case 1:
				notifyViews(new LoginSignal(Status.LOGIN_FAILED));
				break;
			case 2:
				notifyViews(new LoginSignal(Status.PASSWORD_FAILED));
				break;
			case 3:
				notifyViews(new LoginSignal(Status.SUCCESS));
				break;
			default:
				//TODO error
				break;
		}
	}
	
	@Override
    public void attachView(final IView view)
    {
        this.addObserver((Observer) view);
    }

    @Override
    public void notifyViews(final ISignal signal)
    {
        this.setChanged();
        this.notifyObservers(signal);
    }
}
