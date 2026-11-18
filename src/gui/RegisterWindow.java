package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Client;
import logic.FileUtil;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.ComponentOrientation;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class RegisterWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNationalID;
	private JTextField txtName;
	private JPasswordField txtPassword;
	private JPasswordField txtRetypePassword;
	private JTextField txtUser;
	private LoginWindow parent = null;
	private ArrayList<Client> clients;
	private JLabel lblNationalID;
	private JLabel lblUser;
	private JLabel lblName;
	private JLabel lblPassword;
	private JLabel lblRetypePassword;
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			RegisterWindow dialog = new RegisterWindow();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public RegisterWindow(LoginWindow lw) {
		parent = lw;
		clients = getClients();
		setTitle("Roulette Wheel - Register Window");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterWindow.class.getResource("/img/roulette.jpg")));
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
				JLabel lblNewLabel = new JLabel("Register form ");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
				lblNewLabel.setForeground(new Color(255, 255, 255));
				pnNorth.add(lblNewLabel);
			}
		}
		{
			JPanel pnCenter = new JPanel();
			pnCenter.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			pnCenter.setBackground(new Color(0, 128, 0));
			contentPanel.add(pnCenter, BorderLayout.CENTER);
			pnCenter.setLayout(new GridLayout(4, 1, 0, 0));
			{
				JPanel pnIDUser = new JPanel();
				pnIDUser.setBackground(new Color(0, 128, 0));
				pnCenter.add(pnIDUser);
				pnIDUser.setLayout(new GridLayout(0, 2, 0, 0));
				{
					JPanel pnID = new JPanel();
					FlowLayout flowLayout = (FlowLayout) pnID.getLayout();
					flowLayout.setVgap(22);
					pnID.setBackground(new Color(0, 128, 0));
					pnIDUser.add(pnID);
					{
						lblNationalID = new JLabel("National ID:   ");
						pnID.add(lblNationalID);
						lblNationalID.setForeground(new Color(255, 255, 255));
						lblNationalID.setFont(new Font("Tahoma", Font.BOLD, 18));
					}
					{
						txtNationalID = new JTextField();
						lblNationalID.setLabelFor(txtNationalID);
						txtNationalID.setToolTipText("Type here your ID");
						pnID.add(txtNationalID);
						txtNationalID.setFont(new Font("Tahoma", Font.BOLD, 18));
						txtNationalID.setColumns(9);
					}
				}
				{
					JPanel pnUser = new JPanel();
					FlowLayout flowLayout = (FlowLayout) pnUser.getLayout();
					flowLayout.setVgap(22);
					pnUser.setBackground(new Color(0, 128, 0));
					pnIDUser.add(pnUser);
					{
						lblUser = new JLabel("User:   ");
						pnUser.add(lblUser);
						lblUser.setForeground(Color.WHITE);
						lblUser.setFont(new Font("Tahoma", Font.BOLD, 18));
					}
					{
						txtUser = new JTextField();
						lblUser.setLabelFor(txtUser);
						txtUser.setToolTipText("Type here the user you want to have");
						pnUser.add(txtUser);
						txtUser.setFont(new Font("Tahoma", Font.BOLD, 18));
						txtUser.setColumns(9);
					}
				}
			}
			{
				JPanel pnNameSurname = new JPanel();
				pnNameSurname.setBackground(new Color(0, 128, 0));
				pnCenter.add(pnNameSurname);
				pnNameSurname.setLayout(new GridLayout(1, 2, 1, 22));
				{
					JPanel pnName = new JPanel();
					FlowLayout flowLayout = (FlowLayout) pnName.getLayout();
					flowLayout.setVgap(22);
					pnName.setBackground(new Color(0, 128, 0));
					pnNameSurname.add(pnName);
					{
						lblName = new JLabel("Full name:   ");
						lblName.setHorizontalAlignment(SwingConstants.LEFT);
						lblName.setForeground(Color.WHITE);
						lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
						pnName.add(lblName);
					}
					{
						txtName = new JTextField();
						txtName.setHorizontalAlignment(SwingConstants.LEFT);
						lblName.setLabelFor(txtName);
						txtName.setToolTipText("Type your name ");
						txtName.setFont(new Font("Tahoma", Font.BOLD, 18));
						txtName.setColumns(25);
						pnName.add(txtName);
					}
				}
			}
			{
				JPanel pnPassword = new JPanel();
				pnPassword.setBackground(new Color(0, 128, 0));
				pnCenter.add(pnPassword);
				pnPassword.setLayout(new GridLayout(0, 2, 0, 0));
				{
					JPanel pnTypePassword = new JPanel();
					FlowLayout fl_pnTypePassword = (FlowLayout) pnTypePassword.getLayout();
					fl_pnTypePassword.setVgap(22);
					pnTypePassword.setBackground(new Color(0, 128, 0));
					pnPassword.add(pnTypePassword);
					{
						lblPassword = new JLabel("Password:   ");
						lblPassword.setForeground(Color.WHITE);
						lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
						pnTypePassword.add(lblPassword);
					}
					{
						txtPassword = new JPasswordField();
						lblPassword.setLabelFor(txtPassword);
						txtPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
						txtPassword.setColumns(10);
						pnTypePassword.add(txtPassword);
					}
				}
				{
					JPanel pnRetypePassword = new JPanel();
					FlowLayout fl_pnRetypePassword = (FlowLayout) pnRetypePassword.getLayout();
					fl_pnRetypePassword.setVgap(22);
					pnRetypePassword.setBackground(new Color(0, 128, 0));
					pnPassword.add(pnRetypePassword);
					{
						lblRetypePassword = new JLabel("Retype password:   ");
						lblRetypePassword.setForeground(Color.WHITE);
						lblRetypePassword.setFont(new Font("Tahoma", Font.BOLD, 18));
						pnRetypePassword.add(lblRetypePassword);
					}
					{
						txtRetypePassword = new JPasswordField();
						lblRetypePassword.setLabelFor(txtRetypePassword);
						txtRetypePassword.setFont(new Font("Tahoma", Font.BOLD, 18));
						txtRetypePassword.setColumns(8);
						pnRetypePassword.add(txtRetypePassword);
					}
				}
			}
			{
				JPanel pnButtons = new JPanel();
				pnButtons.setBackground(new Color(0, 128, 0));
				pnCenter.add(pnButtons);
				pnButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 28));
				{
					JButton btnRegister = new JButton("Register");
					getRootPane().setDefaultButton(btnRegister);
					btnRegister.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if (checkFields())
								dispose();
						}
					});
					btnRegister.setForeground(new Color(255, 255, 255));
					btnRegister.setBackground(new Color(0, 0, 205));
					btnRegister.setFont(new Font("Tahoma", Font.BOLD, 18));
					pnButtons.add(btnRegister);
				}
				{
					JButton btnCancel = new JButton("Cancel and go back");
					btnCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							dispose();
						}
					});
					btnCancel.setForeground(Color.WHITE);
					btnCancel.setFont(new Font("Tahoma", Font.BOLD, 18));
					btnCancel.setBackground(new Color(255, 0, 0));
					pnButtons.add(btnCancel);
				}
			}
		}
		setLocationRelativeTo(null);
		setModal(true);
	}
	
	public LoginWindow getParent() {
		return parent;
	}
	
	public ArrayList<Client> getClients(){
		return parent.getClients();
	}
	
	/**
	 * We try to add a client 
	 * @return true if it could be possible to add him/her, or false if it was not 
	 * possible to add it (dni or usearname already in the "database")
	 */
	private boolean addClient() {
		if (!FileUtil.searchClientByDni(txtNationalID.getText(), clients)) {
			if (!FileUtil.searchClientByUser(txtUser.getText(), clients)) {
				FileUtil.addNewClient(clients, txtNationalID.getText(), txtName.getText(),
						txtUser.getText(), String.valueOf(txtPassword.getPassword()),100,false);
				System.out.println("All correct!");
				JOptionPane.showMessageDialog(this, "Welcome to the Roulette Game "+txtName.getText()+"!\nYou got 100 € to spend for\n"
						+ " being a new user, enjoy!");
				return true;
			} else {
				JOptionPane.showMessageDialog(this, "Error: User already taken, please\nSelect another one.");
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Error: National ID already registered.");
			return false;
		}
	}
		
	/**
	 * Monster method that checks field by field, giving exact feedback of which field/s is/are wrong 
	 * @return
	 */
		private boolean checkFields() {
			if (!txtNationalID.getText().isEmpty() && isNationalIDCorrect()) {
				if (!txtUser.getText().isEmpty() && txtUser.getText().length() <= 6) {
					if (!txtName.getText().isEmpty()) {
							if (txtPassword.getPassword().length != 0) {
								if (txtRetypePassword.getPassword().length != 0) {
									if (passwordsMatch()) {
										//All basic fields are correct
										return addClient();
									} else {
										JOptionPane.showMessageDialog(this, "Error, passwords must match");
										return false;
									}
								} else {
									JOptionPane.showMessageDialog(this, "Error, retype password field is empty");
									return false;
								}
							} else {
								JOptionPane.showMessageDialog(this, "Error, password field is empty");
								return false;
							}
					} else {
						JOptionPane.showMessageDialog(this, "Error, name field is empty");
						return false;
					}
				} else {
					JOptionPane.showMessageDialog(this, "Error, user field is empty and it must be from 1 to 6 charachters long");
					return false;
				}
			} else {
				JOptionPane.showMessageDialog(this, "Error, National ID field is not correct.\nRemember to put it in the next format:\n"
						+ "11111111, 8 numbers");
				return false;
			}
		} 

		private boolean isNationalIDCorrect() {
			//Check if it is not empty and it has the correct length
			if (!txtNationalID.getText().isEmpty() && txtNationalID.getText().toCharArray().length == 8) {
				String numbers = txtNationalID.getText().substring(0, 7); //get numbers
//				String letter = txtNationalID.getText().substring(8, 8).toUpperCase(); //get letter	
				if (numbers.matches(".*\\d.*") /*&& letter.matches("^[a-zA-Z]*$")*/)
						return true;
				}
				return false;	
			}

		private boolean passwordsMatch() {
			return String.valueOf(txtPassword.getPassword()).equals(String.valueOf(txtRetypePassword.getPassword()));
		}
}
