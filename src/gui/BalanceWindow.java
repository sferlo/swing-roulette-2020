package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Client;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;

public class BalanceWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCardNumber;
	private JTextField txtCompleteName;
	private JSpinner spAmount;
	private JLabel lblCardNumber;
	private JLabel lblCompleteName;
	private JLabel lblAmount;
	private Client actualClient;

	/**
	 * Create the dialog.
	 */
	public BalanceWindow(MainWindow mw) {
		this.actualClient = mw.getActualClient();
		setTitle("Add more money");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BalanceWindow.class.getResource("/img/roulette.jpg")));
		setBounds(100, 100, 500, 517);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnNorth = new JPanel();
			pnNorth.setBackground(new Color(0, 128, 0));
			contentPanel.add(pnNorth, BorderLayout.NORTH);
			{
				JLabel lblAddMoney = new JLabel("Get more Balance");
				lblAddMoney.setForeground(new Color(255, 255, 255));
				lblAddMoney.setFont(new Font("Tahoma", Font.BOLD, 30));
				pnNorth.add(lblAddMoney);
			}
		}
		{
			{
				JPanel pnSouth = new JPanel();
				pnSouth.setBackground(new Color(0, 128, 0));
				contentPanel.add(pnSouth, BorderLayout.SOUTH);
			}
			{
				JPanel pnCenter = new JPanel();
				pnCenter.setBackground(new Color(0, 128, 0));
				contentPanel.add(pnCenter, BorderLayout.CENTER);
				pnCenter.setLayout(new GridLayout(4, 0, 0, 0));
				{
					JPanel pn1 = new JPanel();
					pn1.setBackground(new Color(0, 128, 0));
					pnCenter.add(pn1);
					pn1.setLayout(new GridLayout(0, 3, 0, 0));
					{
						JPanel panel1_1 = new JPanel();
						panel1_1.setBackground(new Color(0, 128, 0));
						pn1.add(panel1_1);
						panel1_1.setLayout(new GridLayout(3, 1, 0, 0));
						{
							JLabel label = new JLabel("");
							panel1_1.add(label);
						}
						{
							lblAmount = new JLabel("Amount:");
							lblAmount.setDisplayedMnemonic('a');
							panel1_1.add(lblAmount);
							lblAmount.setHorizontalAlignment(SwingConstants.CENTER);
							lblAmount.setForeground(new Color(255, 255, 255));
							lblAmount.setFont(new Font("Tahoma", Font.BOLD, 20));
						}
						{
							JLabel label = new JLabel("");
							panel1_1.add(label);
						}
					}
					{
						JPanel pnSpinner = new JPanel();
						pnSpinner.setBackground(new Color(0, 128, 0));
						pn1.add(pnSpinner);
						pnSpinner.setLayout(new GridLayout(3, 3, 0, 0));
						{
							JLabel lblNewLabel = new JLabel("");
							pnSpinner.add(lblNewLabel);
						}
						{
							spAmount = new JSpinner();
							lblAmount.setLabelFor(spAmount);
							spAmount.setToolTipText("Select the amount to add (10\u20AC minimum)");
							spAmount.setModel(new SpinnerNumberModel(0, 0, 100000, 10));
							spAmount.setFont(new Font("Tahoma", Font.BOLD, 18));
							spAmount.setBackground(new Color(34, 139, 34));
							pnSpinner.add(spAmount);
						}
					}
					{
						JPanel pn1_3 = new JPanel();
						pn1_3.setBackground(new Color(0, 128, 0));
						pn1.add(pn1_3);
						pn1_3.setLayout(new BorderLayout(0, 0));
						{
							JLabel lblEuro = new JLabel(" \u20AC");
							lblEuro.setHorizontalAlignment(SwingConstants.LEFT);
							lblEuro.setForeground(Color.WHITE);
							lblEuro.setFont(new Font("Tahoma", Font.BOLD, 20));
							lblEuro.setDisplayedMnemonic('a');
							pn1_3.add(lblEuro);
						}
					}
				}
				{
					JPanel pn2 = new JPanel();
					pn2.setBackground(new Color(0, 128, 0));
					pnCenter.add(pn2);
					pn2.setLayout(new GridLayout(3, 0, 0, 0));
					{
						JLabel label = new JLabel("");
						pn2.add(label);
					}
					{
						JPanel pnCreditCard = new JPanel();
//						FlowLayout flowLayout = (FlowLayout) pnCreditCard.getLayout();
						pnCreditCard.setBackground(new Color(0, 128, 0));
						pn2.add(pnCreditCard);
						{
							lblCardNumber = new JLabel("Card ");
							lblCardNumber.setDisplayedMnemonic('c');
							lblCardNumber.setHorizontalAlignment(SwingConstants.LEFT);
							lblCardNumber.setForeground(Color.WHITE);
							lblCardNumber.setFont(new Font("Tahoma", Font.BOLD, 20));
							pnCreditCard.add(lblCardNumber);
						}
						{
							txtCardNumber = new JTextField();
							lblCardNumber.setLabelFor(txtCardNumber);
							txtCardNumber.setToolTipText("Write here your credit card number");
							txtCardNumber.setBackground(new Color(255, 255, 255));
							txtCardNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
							pnCreditCard.add(txtCardNumber);
							txtCardNumber.setColumns(20);
						}
					}
				}
				{
					JPanel pn3 = new JPanel();
					pn3.setBackground(new Color(0, 128, 0));
					pnCenter.add(pn3);
					pn3.setLayout(new GridLayout(3, 0, 0, 0));
					{
						JPanel pnCardName = new JPanel();
						FlowLayout flowLayout = (FlowLayout) pnCardName.getLayout();
						flowLayout.setAlignment(FlowLayout.LEFT);
						pnCardName.setBackground(new Color(0, 128, 0));
						pn3.add(pnCardName);
						{
							lblCompleteName = new JLabel("Card Name");
							lblCompleteName.setDisplayedMnemonic('n');
							lblCompleteName.setHorizontalAlignment(SwingConstants.LEFT);
							lblCompleteName.setForeground(Color.WHITE);
							lblCompleteName.setFont(new Font("Tahoma", Font.BOLD, 20));
							pnCardName.add(lblCompleteName);
						}
						{
							txtCompleteName = new JTextField();
							lblCompleteName.setLabelFor(txtCompleteName);
							txtCompleteName.setToolTipText("Write here your card name");
							txtCompleteName.setBackground(new Color(255, 255, 255));
							txtCompleteName.setFont(new Font("Tahoma", Font.BOLD, 18));
							txtCompleteName.setColumns(20);
							pnCardName.add(txtCompleteName);
						}
					}
					{
						JLabel lblEmpty = new JLabel("");
						pn3.add(lblEmpty);
					}
				}
				{
					JPanel pn4 = new JPanel();
					pn4.setBackground(new Color(0, 128, 0));
					pnCenter.add(pn4);
					pn4.setLayout(new GridLayout(0, 5, 0, 0));
					{
						JLabel lblEmpty1 = new JLabel("");
						pn4.add(lblEmpty1);
					}
					{
						JButton btnOk = new JButton("Ok");
						btnOk.setMnemonic('k');
						btnOk.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(checkFields()) {
									//All correct, add chips
									actualClient.setBalance(actualClient.getBalance()+Integer.parseInt(spAmount.getValue().toString()));
									mw.updateTxtMoney(Integer.parseInt(spAmount.getValue().toString()));
									JOptionPane.showMessageDialog(null, "Money successfully added.");
									dispose();
								}else
									JOptionPane.showMessageDialog(null, "Please, check that all fields are filled and the card number has 20 digits.");
							}
						});
						btnOk.setBackground(new Color(0, 0, 139));
						btnOk.setForeground(new Color(255, 255, 255));
						btnOk.setFont(new Font("Tahoma", Font.BOLD, 20));
						pn4.add(btnOk);
						getRootPane().setDefaultButton(btnOk);
					}
					{
						JLabel label = new JLabel("");
						pn4.add(label);
					}
					{
						JButton btnCancel = new JButton("Back");
						btnCancel.setMnemonic('b');
						btnCancel.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								int opt = JOptionPane.showConfirmDialog(null, "Do you want to cancel money addition?", "Cancel money operation",2);
								if (opt == JOptionPane.YES_OPTION) {
									mw.setVisible(true);
									dispose();
								}
							}
						});
						btnCancel.setForeground(Color.WHITE);
						btnCancel.setFont(new Font("Tahoma", Font.BOLD, 20));
						btnCancel.setBackground(new Color(255, 0, 0));
						pn4.add(btnCancel);
					}
				}
			}
		}
		setModal(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				int n = JOptionPane.showConfirmDialog(
			            null,
			            "Do you really want to exit without adding money?",
			            "Exit",
			            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			        if((n == JOptionPane.YES_OPTION)){
			        	dispose();
			        	mw.setVisible(true);
			        }
			        else
			        	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			}
		});
	}
	
	private boolean checkFields() {
		int value = Integer.parseInt(spAmount.getValue().toString());
		int cardLength = txtCardNumber.getText().split("").length;
		return (value != 0 && cardLength == 20 && !txtCompleteName.getText().isEmpty());
	}
}
