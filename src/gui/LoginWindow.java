package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Client;
import logic.FileUtil;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class LoginWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblLogin;
	private JTextField txUser;
	private JPasswordField txPassword;
	private JLabel lblUser;
	private JLabel lblPassword;
	
	private ArrayList<Client> clients;
	private RegisterWindow registerWindow = null;
	private Client actualClient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginWindow dialog = new LoginWindow();
			dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginWindow() {
		clients = new ArrayList<Client>();
		FileUtil.loadClients("files/customers.dat", "files/customersVIP.dat", clients);
		setTitle("Roulette Wheel - Login Window");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginWindow.class.getResource("/img/roulette.jpg")));
		setBounds(100, 100, 696, 484);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 128, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnNorth = new JPanel();
			pnNorth.setBackground(new Color(0, 128, 0));
			contentPanel.add(pnNorth, BorderLayout.NORTH);
			pnNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel lblWelcome = new JLabel("Welcome to the Roulette Wheel Game!");
				lblWelcome.setBackground(new Color(0, 128, 0));
				lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 30));
				lblWelcome.setForeground(new Color(255, 255, 255));
				pnNorth.add(lblWelcome);
			}
		}
		{
			JPanel pnCenter = new JPanel();
			pnCenter.setBackground(new Color(0, 128, 0));
			contentPanel.add(pnCenter, BorderLayout.CENTER);
			pnCenter.setLayout(new GridLayout(3, 1, 0, 0));
			pnCenter.add(getLblLogin());
			{
				JPanel pnLogin = new JPanel();
				pnLogin.setBackground(new Color(0, 128, 0));
				pnCenter.add(pnLogin);
				pnLogin.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JPanel pnUser = new JPanel();
					pnUser.setBackground(new Color(0, 128, 0));
					pnLogin.add(pnUser);
					{
						lblUser = new JLabel("User:            ");
						lblUser.setToolTipText("Write your user name");
						lblUser.setDisplayedMnemonic('u');
						pnUser.add(lblUser);
						lblUser.setFont(new Font("Tahoma", Font.BOLD, 18));
						lblUser.setForeground(new Color(255, 255, 255));
					}
					{
						txUser = new JTextField();
						txUser.setToolTipText("Write your user name here");
						lblUser.setLabelFor(txUser);
						txUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
						pnUser.add(txUser);
						txUser.setColumns(10);
					}
				}
				{
					JPanel pnPassword = new JPanel();
					pnPassword.setBackground(new Color(0, 128, 0));
					pnLogin.add(pnPassword);
					{
						lblPassword = new JLabel("Password:   ");
						lblPassword.setToolTipText("Write your password ");
						lblPassword.setDisplayedMnemonic('p');
						pnPassword.add(lblPassword);
						lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
						lblPassword.setForeground(new Color(255, 255, 255));
					}
					{
						txPassword = new JPasswordField();
						txPassword.setToolTipText("Write your password here");
						lblPassword.setLabelFor(txPassword);
						txPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
						txPassword.setColumns(10);
						pnPassword.add(txPassword);
					}
				}
				{
					JPanel pnEnterCancel = new JPanel();
					pnEnterCancel.setBackground(new Color(0, 128, 0));
					pnLogin.add(pnEnterCancel);
					{
						JButton btnEnter = new JButton("Enter");
						btnEnter.setToolTipText("Enter the game ");
						getRootPane().setDefaultButton(btnEnter);
						btnEnter.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								boolean correct = false;
								if (!txUser.getText().isEmpty() && txUser.getText().length() <= 12) {
									for (Client c : clients) {
										if (c.getUser().equals(txUser.getText()) 
												&& c.getPassword().equals(String.valueOf(txPassword.getPassword()))) {
											setActualClient(c);
											openMainWindow();
											correct = true;
										}
									}
									if (correct == false) {
										txPassword.setText("");
										txUser.setText("");
										txUser.grabFocus();
										JOptionPane.showMessageDialog(null, "Please, write an already registered user name and password.");
									}
								}else {
									JOptionPane.showMessageDialog(null, "Please, write your user name and password.");
								}
							}
						});
						btnEnter.setMnemonic('e');
						btnEnter.setForeground(Color.WHITE);
						btnEnter.setFont(new Font("Tahoma", Font.BOLD, 18));
						btnEnter.setBackground(new Color(50, 205, 50));
						pnEnterCancel.add(btnEnter);
					}
					{
						JButton btnCancel = new JButton("Cancel");
						btnCancel.setToolTipText("Erase login data");
						btnCancel.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								txUser.setText("");
								txPassword.setText("");
							}
						});
						btnCancel.setMnemonic('c');
						btnCancel.setForeground(Color.WHITE);
						btnCancel.setFont(new Font("Tahoma", Font.BOLD, 18));
						btnCancel.setBackground(Color.RED);
						pnEnterCancel.add(btnCancel);
					}
				}
			}
			{
				JPanel pnRegister = new JPanel();
				pnRegister.setBackground(new Color(0, 128, 0));
				pnCenter.add(pnRegister);
				pnRegister.setLayout(new GridLayout(2, 1, 0, 0));
				{
					JLabel lblAlreadyNotLogged = new JLabel("Already not logged in?");
					lblAlreadyNotLogged.setHorizontalAlignment(SwingConstants.CENTER);
					lblAlreadyNotLogged.setForeground(new Color(255, 255, 255));
					lblAlreadyNotLogged.setFont(new Font("Tahoma", Font.BOLD, 18));
					pnRegister.add(lblAlreadyNotLogged);
				}
				{
					JPanel panel = new JPanel();
					panel.setBackground(new Color(0, 128, 0));
					FlowLayout flowLayout = (FlowLayout) panel.getLayout();
					flowLayout.setVgap(1);
					flowLayout.setHgap(1);
					pnRegister.add(panel);
					{
						JButton btnRegister = new JButton("Register");
						btnRegister.setToolTipText("Register to get an account and play!");
						btnRegister.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								openRegisterWindow();
							}
						});
						btnRegister.setMnemonic('r');
						btnRegister.setBackground(new Color(0, 0, 205));
						btnRegister.setFont(new Font("Tahoma", Font.BOLD, 18));
						btnRegister.setForeground(new Color(255, 255, 255));
						panel.add(btnRegister);
					}
				}
			}
		}
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
//				JOptionPane.showMessageDialog(null, "We hope to see you soon! :D");
				System.exit(0);
			}
		});
	}
	public ArrayList<Client> getClients() {
		return clients;
	}

	private JLabel getLblLogin() {
		if (lblLogin == null) {
			lblLogin = new JLabel("Login");
			lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
			lblLogin.setForeground(Color.WHITE);
			lblLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
			lblLogin.setBackground(new Color(0, 128, 0));
		}
		return lblLogin;
	}
	
	private void openRegisterWindow() {
		registerWindow = new RegisterWindow(this);
		registerWindow.setLocationRelativeTo(this);
		registerWindow.setModal(true);
		registerWindow.setVisible(true);
	}
	
	private void openMainWindow() {
		txUser.setText("");
		txPassword.setText("");
		MainWindow mw = new MainWindow(this);
		mw.setLocationRelativeTo(this);
		mw.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.dispose();
		mw.setVisible(true);
	}
	
	private void setActualClient(Client actualClient) {
		this.actualClient = actualClient;
	}
	
	public Client getActualClient() {
		return actualClient;
	}
}
