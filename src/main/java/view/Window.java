package view;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Window extends JFrame
{

	private ViewFacade vf;
	
	public Window(ViewFacade vf2)
	{
		super();
		this.vf = vf2;
		build();
	}
	
	void build(){
		setTitle("Connexion"); //On donne un titre à l'application
		setSize(500,320); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		setResizable(true); //On interdit la redimensionnement de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		JPanel panel = new JPanel();
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                          ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                          ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                          ");
		addText(panel, "Identifiant");
		vf.login = addType(panel);
		addText(panel, "             ");
		addText(panel, "Mot De Passe");
		vf.mdp = addPassword(panel);
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                          ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                          ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                          ");
		addText(panel, "      ");
		addButtonLog(panel, "Se connecter");

		addText(panel, "         ");
	}
	
	void build2(){
		setTitle("Connexion2"); //On donne un titre à l'application
		setSize(1920,1080); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		setResizable(false); //On interdit la redimensionnement de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		JPanel panel = new JPanel();
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "       ");
		addText(panel, "Fichier source: ");
		vf.source = addType(panel);
		vf.source.setColumns(50);
		addButtonFile(panel, "");
		addText(panel, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
		addText(panel, "Fichier destination: ");
		vf.cible = addType(panel);
		vf.cible.setColumns(50);
		addButtonFile(panel, "");
	}
	
	private boolean addText(JPanel panel, String s)
	{
		
		panel.setLayout(new FlowLayout());
		JLabel label = new JLabel(s);
		 
		panel.add(label);
		
		setContentPane(panel);
		return true;
	}
	
	private boolean addText(JPanel panel, String s, int x, int y)
	{
		panel.setLayout(new FlowLayout());
		JLabel label = new JLabel(s);
		 
		panel.add(label);
		

		setContentPane(panel);
		return true;
	}
	
	private boolean addButtonLog(JPanel panel, String s)
	{
		panel.setLayout(new FlowLayout());
		
		JButton bouton = new JButton(new ButtonSendLogin(s, vf));
		panel.add(bouton);
		
		setContentPane(panel);
		return true;
	}
	
	private boolean addButtonLog(JPanel panel, String s, int x, int y)
	{
		panel.setLayout(new FlowLayout());
		
		JButton bouton = new JButton(new ButtonSendLogin(s, vf));
		panel.add(bouton);
		
		setContentPane(panel);
		return true;
	}
	
	private boolean addButtonFile(JPanel panel, String s)
	{
		panel.setLayout(new FlowLayout());
		
		JButton bouton = new JButton(new ButtonShowFiles(s, vf));
		bouton.setIcon(new ImageIcon("C:/Users/Aklinar/eclipse-workspace/seminaire-scientifique/seminaire-scientifique/src/main/img/folderM.png"));
		panel.add(bouton);
		
		setContentPane(panel);
		return true;
	}
	
	private boolean addButtonFile(JPanel panel, String s, int x, int y)
	{
		panel.setLayout(new FlowLayout());
		
		JButton bouton = new JButton(new ButtonShowFiles(s, vf));
		panel.add(bouton);
		
		setContentPane(panel);
		return true;
	}
	
	private JTextField addType(JPanel panel)
	{
		panel.setLayout(new FlowLayout());
		
		JTextField textField = new JTextField();
		textField.setColumns(10);
		panel.add(textField);

		setContentPane(panel);
		return textField;
	}
	
	private JTextField addType(JPanel panel, int x, int y)
	{
		panel.setLayout(new FlowLayout());
		
		JTextField textField = new JTextField();
		textField.setColumns(10);
		panel.add(textField);

		setContentPane(panel);
		return textField;
	}
	
	private JPasswordField addPassword(JPanel panel)
	{
		panel.setLayout(new FlowLayout());
		
		JPasswordField textField = new JPasswordField();
		textField.setColumns(10);
		panel.add(textField);

		setContentPane(panel);
		return textField;
	}
	
	private JPasswordField addPassword(JPanel panel, int x, int y)
	{
		panel.setLayout(new FlowLayout());
		
		JPasswordField textField = new JPasswordField();
		textField.setColumns(10);
		panel.add(textField);
		vf.mdp = textField;

		setContentPane(panel);
		return textField;
	}
}
