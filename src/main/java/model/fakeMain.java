package model;

import java.io.IOException;

public class fakeMain {

	public static void main(String[] args)
	{
		XorEncryption xor = new XorEncryption();
		xor.setKey("awqp".getBytes());
		try {
			System.out.println(new String(xor.decrypt(Files.getContentFrom("C:\\Users\\Aklinar\\Documents\\exia cesi\\projet\\grue\\java\\fichiers_cryptés\\fichier011crypt.txt"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
