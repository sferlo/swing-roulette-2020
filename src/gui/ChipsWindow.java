package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.SpinnerNumberModel;

public class ChipsWindow extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private MainWindow mw;
	String[] values;
	private double remainingAmount;
	private int chipsAmount;
	private ArrayList<JSpinner> jspinners = new ArrayList<JSpinner>();
	private JTextField txtChipsAmount;
	private JTextField txtRemainingAmount;

	/**
	 * Create the dialog.
	 */
	public ChipsWindow(MainWindow main) {
		mw = main;
		this.remainingAmount = mw.getActualClient().getBalance();
		this.chipsAmount = 0;
		values = new String[] {"5","10","20","50","100"};
		setTitle("Roulette Wheel - Add chips to bet");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ChipsWindow.class.getResource("/img/Chip20.PNG")));
		setBounds(100, 100, 500, 464);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 128, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			{
				JLabel lblLbltitle = new JLabel("Select the amount of chips");
				lblLbltitle.setFont(new Font("Tahoma", Font.BOLD, 28));
				lblLbltitle.setForeground(new Color(255, 250, 250));
				lblLbltitle.setHorizontalTextPosition(SwingConstants.CENTER);
				lblLbltitle.setHorizontalAlignment(SwingConstants.CENTER);
				contentPanel.add(lblLbltitle, BorderLayout.NORTH);
			}
			{
				JPanel pnData = new JPanel();
				pnData.setBackground(new Color(0, 128, 0));
				contentPanel.add(pnData, BorderLayout.CENTER);
				pnData.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JPanel pnChips = new JPanel();
					pnChips.setBackground(new Color(0, 128, 0));
					pnData.add(pnChips);
					pnChips.setLayout(new GridLayout(1, 1, 0, 0));
					{
						JPanel pnButtons = new JPanel();
						pnButtons.setBackground(new Color(0, 128, 0));
						pnChips.add(pnButtons);
						pnButtons.setLayout(new GridLayout(1, 5, 0, 0));
						for (int i = 0; i < 5; i++)
							pnButtons.add(newButtonPanel(i));
					}
				}
				{
					JPanel pnActualMoney = new JPanel();
					pnActualMoney.setBackground(new Color(0, 128, 0));
					pnData.add(pnActualMoney);
					pnActualMoney.setLayout(new GridLayout(0, 1, 3, 3));
					{
						JPanel pnMoneySpent = new JPanel();
						pnMoneySpent.setBackground(new Color(0, 128, 0));
						pnActualMoney.add(pnMoneySpent);
						{
							JButton btnNewButton = new JButton("Add chips");
							btnNewButton.setMnemonic('a');
							btnNewButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									for (int i = 0; i < jspinners.size(); i++) {
										chipsAmount += (int)jspinners.get(i).getValue() * Integer.parseInt(values[i]);
										remainingAmount -= (int)jspinners.get(i).getValue() * Integer.parseInt(values[i]);
									}
									//Valid amount?
									if (remainingAmount >= 0 && chipsAmount > 0) {
										txtChipsAmount.setText(String.valueOf(chipsAmount));
//										remainingAmount -= chipsAmount;
										txtRemainingAmount.setText(String.valueOf(remainingAmount));
									}
									else {
										JOptionPane.showMessageDialog(null, "Please, select a correct chips amount.");
										for (int i = 0; i < jspinners.size(); i++) {
											chipsAmount -= (int)jspinners.get(i).getValue() * Integer.parseInt(values[i]);
											remainingAmount += (int)jspinners.get(i).getValue() * Integer.parseInt(values[i]);
										}
										for (JSpinner sp : jspinners)
											sp.setValue(0);
									}
								}
							});
							btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
							btnNewButton.setBackground(new Color(0, 255, 0));
							pnMoneySpent.add(btnNewButton);
						}
						{
							JButton btnResetSelection = new JButton("Reset selection");
							btnResetSelection.setMnemonic('r');
							btnResetSelection.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									for (JSpinner sp : jspinners)
										sp.setValue(0);
									remainingAmount += chipsAmount;
									txtRemainingAmount.setText(String.valueOf(remainingAmount));
									chipsAmount = 0;
									txtChipsAmount.setText("");
								}
							});
							pnMoneySpent.add(btnResetSelection);
							btnResetSelection.setForeground(new Color(255, 255, 255));
							btnResetSelection.setFont(new Font("Tahoma", Font.BOLD, 15));
							btnResetSelection.setBackground(new Color(255, 0, 0));
						}
					}
					{
						JPanel pnAmountMoneySpent = new JPanel();
						pnAmountMoneySpent.setBackground(new Color(0, 128, 0));
						pnActualMoney.add(pnAmountMoneySpent);
						{
							JLabel lblNewLabel = new JLabel("Total amount:");
							lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
							lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
							lblNewLabel.setForeground(new Color(255, 255, 255));
							pnAmountMoneySpent.add(lblNewLabel);
						}
						{
							txtChipsAmount = new JTextField();
							txtChipsAmount.setEditable(false);
							txtChipsAmount.setForeground(new Color(255, 255, 255));
							txtChipsAmount.setFont(new Font("Tahoma", Font.BOLD, 18));
							txtChipsAmount.setBackground(new Color(0, 128, 0));
							pnAmountMoneySpent.add(txtChipsAmount);
							txtChipsAmount.setColumns(10);
						}
						{
							JLabel lblNewLabel_1 = new JLabel("\u20AC");
							lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
							lblNewLabel_1.setForeground(new Color(255, 255, 255));
							pnAmountMoneySpent.add(lblNewLabel_1);
						}
					}
					{
						JPanel pnAmountRemainingMoney = new JPanel();
						pnAmountRemainingMoney.setBackground(new Color(0, 128, 0));
						pnActualMoney.add(pnAmountRemainingMoney);
						{
							JLabel lblReamainingAmount = new JLabel("Reamaining amount:");
							lblReamainingAmount.setHorizontalAlignment(SwingConstants.CENTER);
							lblReamainingAmount.setForeground(Color.WHITE);
							lblReamainingAmount.setFont(new Font("Tahoma", Font.BOLD, 18));
							pnAmountRemainingMoney.add(lblReamainingAmount);
						}
						{
							txtRemainingAmount = new JTextField();
							txtRemainingAmount.setForeground(Color.WHITE);
							txtRemainingAmount.setFont(new Font("Tahoma", Font.BOLD, 18));
							txtRemainingAmount.setEditable(false);
							txtRemainingAmount.setColumns(10);
							txtRemainingAmount.setBackground(new Color(0, 128, 0));
							txtRemainingAmount.setText(String.valueOf(remainingAmount));
							pnAmountRemainingMoney.add(txtRemainingAmount);
						}
						{
							JLabel lblNewLabel_1 = new JLabel("\u20AC");
							lblNewLabel_1.setForeground(Color.WHITE);
							lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
							pnAmountRemainingMoney.add(lblNewLabel_1);
						}
					}
				}
			}
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					int n = JOptionPane.showConfirmDialog(
				            null,
				            "Do you really want to exit without buying chips?",
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
		{
			JPanel pnOkCancel = new JPanel();
			pnOkCancel.setBackground(new Color(0, 128, 0));
			pnOkCancel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(pnOkCancel, BorderLayout.SOUTH);
			{
				JButton btnConfirm = new JButton("Confirm");
				btnConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mw.setChips(chipsAmount);
						mw.getActualClient().setBalance(mw.getActualClient().getBalance()-chipsAmount);
						mw.updateBalanceText();
						mw.updateChipsText();
						JOptionPane.showMessageDialog(null, "Chips successfully added");
						dispose();
						mw.setVisible(true);
						getRootPane().setDefaultButton(btnConfirm);
					}
				});
				btnConfirm.setToolTipText("Enter the game ");
				btnConfirm.setMnemonic('c');
				btnConfirm.setForeground(Color.WHITE);
				btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnConfirm.setBackground(new Color(50, 205, 50));
				pnOkCancel.add(btnConfirm);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opt = JOptionPane.showConfirmDialog(null, "Do you want to cancel the chips purchase?", "Cancel chips purchase",2);
						if (opt == JOptionPane.YES_OPTION) {
							remainingAmount += chipsAmount;
							chipsAmount = 0;
							mw.setVisible(true);
							dispose();
						}
					}
				});
				btnCancel.setToolTipText("Enter the game ");
				btnCancel.setMnemonic('n');
				btnCancel.setForeground(Color.WHITE);
				btnCancel.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnCancel.setBackground(new Color(255, 0, 0));
				pnOkCancel.add(btnCancel);
			}
		}
	}
	
	private JLabel newLabel(int pos) {
		JLabel b = new JLabel();
		String path = "/img/Chip"+values[pos]+".PNG";
		b.setIcon(new ImageIcon(ChipsWindow.class.getResource(path)));
		return b;
	}
	
//	private JLabel newLabelUnits(int pos) {
//		JLabel l = new JLabel();
//		l.setText("x 0");
//		l.setForeground(new Color(255, 250, 250));
//		l.setFont(new Font("Tahoma", Font.BOLD, 24));
//		l.setHorizontalTextPosition(SwingConstants.LEFT);
//		l.setHorizontalAlignment(SwingConstants.LEFT);
//		return l;
//	}
	
	private JPanel newButtonPanel(int pos) {
		JPanel p = new JPanel();
		p.setBackground(new Color(0, 128, 0));
		p.setLayout(new GridLayout(2, 1, 0, 0));
		p.add(newLabel(pos));
		jspinners.add(new JSpinner());
		jspinners.get(pos).setToolTipText("Add "+values[pos] +" chips");
		jspinners.get(pos).setModel(new SpinnerNumberModel(0, 0, 100, 1));
		jspinners.get(pos).setFont(new Font("Tahoma", Font.BOLD, 16));
		p.add(jspinners.get(pos));
		return p;
	}
	
	public boolean isValid() {
		return (chipsAmount <= remainingAmount && chipsAmount > 0);
	}

}
