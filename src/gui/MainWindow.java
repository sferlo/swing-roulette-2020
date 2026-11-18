package gui;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import logic.Client;
import logic.FileUtil;
import logic.Game;
import logic.MyPlay;
import logic.RouletteWheel;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenuBar;
import java.awt.FlowLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.InputEvent;

import javax.help.*;
import java.net.*;
import java.io.*;

public class MainWindow extends JFrame {

	class MyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			int number = Integer.parseInt(b.getActionCommand());
			currentPlay = number;
			txtSelectedBox.setText(b.getActionCommand());
			disableNumbers(pn1, false);
			disableNumbers(pn2, false);
			disableNumbers(pn3, false);
			disableNumbers(pn4, false);
			writeSpecialValues(number);
			txtGame.setText("Place your bets!");
			if (isBetReady()) {
				enableBetButtons(true);
			}
		}
	}
	
	
	private int[] redPositions = new int[] { 1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36 };
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LoginWindow loginWindow;
	private Client actualClient;
	private JPanel pnNorth;
	private JPanel pnNorthWest;
	private JPanel pnNorthCenter;
	private JLabel lblTitle;
	private JTextField txtChips;
	private JLabel lblBalance;
	private JButton btnAddMoney;
	private JButton btnAddChips;
	private JPanel pnTitle;
	private JPanel pnWest;
	private JLabel lblRoulette;
	private ImageIcon picture = null;
	private JLabel lblPicture;
	private JPanel pnCenter;
	private JPanel pnSouth;
	private JPanel pnSouthWestUp;
	private JPanel pnSouthEast;
	private JPanel pnEast;
	private JPanel pnChip5;
	private JPanel pnChip10;
	private JPanel pnChip20;
	private JPanel pnChip50;
	private JPanel pnChip100;
	private JPanel pnAddBet;
	private JButton btnChip5;
	private JButton btnChip10;
	private JButton btnChip20;
	private JButton btnChip50;
	private JButton btnChip100;
	private JButton btnConfirmBet;
	private JScrollPane scBets;
	private JList<MyPlay> listBets;
	private JPanel pnBetButtons;
	private JPanel pnPlayButtons;
	private JButton btnStartGame;
	private JButton btnCancelBet;
	private JButton btnCancelAllBets;
	private JLabel lblBetAmount;
	private JPanel pnNorthEast;
	private JTextField txtGame;
	private JTextField txtBetAmount;
	private JPanel pnSouthWestDown1;
	private JPanel pnSouthWest;
	private JPanel pnSouthWestDown2;
	private JPanel pnSouthWestDown;
	private JLabel lblSelectedNumber;
	private JTextField txtSelectedBox;
	private JButton btnCancelBet_1;
	private MyButtonListener mbl = new MyButtonListener();
	private Game game;
	private JPanel pn1;
	private JPanel pn2;
	private JPanel pn3;
	private JPanel pn4;
	private JPanel pnGetDrinks;
	private JLabel lblEmpty;
	private int currentPlay;
	private DefaultListModel<MyPlay> modelPlay = new DefaultListModel<MyPlay>();
	private ArrayList<MyPlay> myPlays;
	private JButton btnDrinks;
	private ArrayList<Client> clients;
	private JTextField txtBalance;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel lblChips;
	private JLabel lblUserName;
	private JPanel pnw1;
	private double totalBet;
	private double chips;
	private double prize;
	private JMenuBar menuBar;
	private JMenuBar menu;
	private JMenu mnUser;
	private JMenu mnHelp;
	private JMenuItem itAddMoney;
	private JMenuItem itWithdrawMoney;
	private JMenuItem itAddChips;
	private JSeparator separator;
	private JMenuItem itBuyDrinks;
	private JSeparator separator_1;
	private JMenuItem itLogout;
	private JMenuItem itWithdrawChips;
	private JMenuItem mntmHelpContent;
	private JMenuItem mntmAbout;
	
	
	/**
	 * Create the frame.
	 */
	public MainWindow(LoginWindow lw) {
		clients = new ArrayList<Client>();
		FileUtil.loadClients("files/customers.dat","files/customersVIP.dat", clients);
		myPlays = new ArrayList<MyPlay>();
		this.loginWindow = lw;
		actualClient = lw.getActualClient();
		setTitle("Roulette Wheel - Main Window");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/roulette.jpg")));
		setBounds(100, 100, 1322, 713);
		setJMenuBar(getMenuBar_2());
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnNorth(), BorderLayout.NORTH);
		contentPane.add(getPnWest(), BorderLayout.WEST);
		contentPane.add(getPnCenter(), BorderLayout.CENTER);
		contentPane.add(getPnSouth(), BorderLayout.SOUTH);
		contentPane.add(getPnEast(), BorderLayout.EAST);
		UIManager.put("ToolTip.font", new FontUIResource("Tahoma", Font.BOLD, 18));
		if (actualClient.isVip()) {
			lblUserName.setText(actualClient.getUser() + " - [VIP]");
			lblUserName.setForeground(Color.BLACK);
			pnw1.setBackground(Color.ORANGE);
		}
		else
			lblUserName.setText(actualClient.getUser());
		//To manage the "X" button
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				int n = JOptionPane.showConfirmDialog(
			            null,
			            "Do you really want to exit the Roulette Wheel Game?",
			            "Exit",
			            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			        if((n == 0 /*JOptionPane.YES_OPTION*/)){
			        	withdrawMoneyBeforeExit();
			        	openLoginWindow();
			        }
			        else
			        	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			}
		});
		loadHelp();
		btnStartGame.setEnabled(false);
		btnCancelBet.setEnabled(false);
		btnCancelAllBets.setEnabled(false);
		this.totalBet = 0;
		this.chips = 0;
		this.prize = 0;
	}
	
	private void loadHelp(){

		   URL hsURL;
		   HelpSet hs;

		    try {
			    	File fichero = new File("help/ayuda.hs");
			    	hsURL = fichero.toURI().toURL();
			        hs = new HelpSet(null, hsURL);
			      }

		    catch (Exception e){
		      System.out.println("Help not found");
		      return;
		   }

		   HelpBroker hb = hs.createHelpBroker();

		   hb.enableHelpKey(getRootPane(),"intro", hs);
		   hb.enableHelp(btnAddMoney, "addMoney", hs);
		   hb.enableHelpOnButton(mntmHelpContent, "intro", hs);
		 }
	
	private JPanel getPnNorth() {
		if (pnNorth == null) {
			pnNorth = new JPanel();
			pnNorth.setBackground(new Color(0, 0, 139));
			pnNorth.setLayout(new GridLayout(0, 3, 0, 0));
			pnNorth.add(getPnNorthWest());
			pnNorth.add(getPnNorthCenter());
			pnNorth.add(getPnNorthEast());
		}
		return pnNorth;
	}
	private JPanel getPnNorthWest() {
		if (pnNorthWest == null) {
			pnNorthWest = new JPanel();
			pnNorthWest.setBackground(new Color(0, 0, 139));
			pnNorthWest.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			pnNorthWest.add(getLblChips());
			pnNorthWest.add(getTxtChips());
			pnNorthWest.add(getBtnAddChips());
			pnNorthWest.add(getTxtBalance());
			pnNorthWest.add(getLblBalance());
			pnNorthWest.add(getBtnAddMoney());
			pnNorthWest.add(getLabel());
			pnNorthWest.add(getLabel_1_1());
			pnNorthWest.add(getLabel_2());
			pnNorthWest.add(getLabel_3());
			pnNorthWest.add(getLabel_4());
		}
		return pnNorthWest;
	}
	private JPanel getPnNorthCenter() {
		if (pnNorthCenter == null) {
			pnNorthCenter = new JPanel();
			pnNorthCenter.setBackground(new Color(0, 0, 139));
			pnNorthCenter.setLayout(new GridLayout(0, 1, 0, 0));
			pnNorthCenter.add(getPnTitle());
		}
		return pnNorthCenter;
	}
	private JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("Roulette Wheel");
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitle.setForeground(new Color(255, 255, 255));
			lblTitle.setFont(new Font("Tahoma", Font.BOLD, 40));
		}
		return lblTitle;
	}
	
	private JTextField getTxtChips() {
		if (txtChips == null) {
			txtChips = new JTextField();
			txtChips.setText("0");
			txtChips.setHorizontalAlignment(SwingConstants.RIGHT);
			txtChips.setFont(new Font("Tahoma", Font.BOLD, 18));
			txtChips.setToolTipText("Actual chips amount");
			txtChips.setEditable(false);
			txtChips.setColumns(5);
		}
		return txtChips;
	}
	private JLabel getLblBalance() {
		if (lblBalance == null) {
			lblBalance = new JLabel("\u20AC");
			lblBalance.setForeground(Color.WHITE);
			lblBalance.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblBalance;
	}
	private JButton getBtnAddMoney() {
		if (btnAddMoney == null) {
			btnAddMoney = new JButton("+\u20AC");
			btnAddMoney.setMnemonic('e');
			btnAddMoney.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openBalanceWindow();
				}
			});
			btnAddMoney.setToolTipText("Add money (Alt + E)");
			btnAddMoney.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnAddMoney.setBackground(Color.YELLOW);
		}
		return btnAddMoney;
	}
	
	private void openChipsWindow() {
		ChipsWindow c = new ChipsWindow(this);
		c.setLocationRelativeTo(this);
		c.setVisible(true);
		c.setModal(true); //Chips
		this.dispose();
	}
	
	private JButton getBtnAddChips() {
		if (btnAddChips == null) {
			btnAddChips = new JButton("Add chips");
			btnAddChips.setMnemonic('c');
			btnAddChips.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openChipsWindow();				// Open a new dialog to select the desired amount of chips
				}
			});
			btnAddChips.setToolTipText("Add chips");
			btnAddChips.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnAddChips.setBackground(Color.YELLOW);
		}
		return btnAddChips;
	}
	private JPanel getPnTitle() {
		if (pnTitle == null) {
			pnTitle = new JPanel();
			pnTitle.setBackground(Color.BLACK);
			pnTitle.setLayout(new GridLayout(0, 1, 0, 0));
			pnTitle.add(getLblTitle());
		}
		return pnTitle;
	}
	private JPanel getPnWest() {
		if (pnWest == null) {
			pnWest = new JPanel();
			pnWest.setBackground(new Color(0, 128, 0));
			pnWest.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnWest.add(getLblRoulette());
			pnWest.add(newButton(0));
		}
		return pnWest;
	}
	private JLabel getLblRoulette() {
		if (lblRoulette == null) {
			lblRoulette = new JLabel();
			lblRoulette.setHorizontalAlignment(SwingConstants.CENTER);
			ImageIcon roulette = new ImageIcon(MainWindow.class.getResource("/img/bigroulette.jpg")); // load the image to a imageIcon
			Image image = roulette.getImage(); // transform it 
			Image newRoulette = image.getScaledInstance(350, 350,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			roulette = new ImageIcon(newRoulette);
			lblRoulette.setIcon(roulette);
		}
		return lblRoulette;
	}
	
class Adapt extends ComponentAdapter {
		
		@Override
		public void componentResized(ComponentEvent e) {
			Image imgOriginal = picture.getImage();
			Image imgScaled = imgOriginal.getScaledInstance((int) (60), (int) (60),
					Image.SCALE_FAST);
			getLblPicture().setIcon(new ImageIcon(imgScaled));
		}
	}

	private JLabel getLblPicture() {
		if (lblPicture == null) {
			lblPicture = new JLabel("");
			lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
			lblPicture.setBackground(Color.WHITE);
		}
		return lblPicture;
	}
	private JPanel getPnCenter() {
		if (pnCenter == null) {
			pnCenter = new JPanel();
			pnCenter.setBackground(new Color(0, 128, 0));
			pnCenter.setLayout(new GridLayout(4, 1, 0, 0));
			pnCenter.add(getPn1());
			pnCenter.add(getPn2());
			pnCenter.add(getPn3());
			pnCenter.add(getPn4());
		}
		return pnCenter;
	}
	private JPanel getPnSouth() {
		if (pnSouth == null) {
			pnSouth = new JPanel();
			pnSouth.setBackground(new Color(0, 128, 0));
			pnSouth.setLayout(new GridLayout(0, 2, 0, 0));
			pnSouth.add(getPnSouthWest());
			pnSouth.add(getPnSouthEast());
		}
		return pnSouth;
	}
	private JPanel getPnSouthWestUp() {
		if (pnSouthWestUp == null) {
			pnSouthWestUp = new JPanel();
			pnSouthWestUp.setBackground(new Color(0, 128, 0));
			pnSouthWestUp.setLayout(new GridLayout(0, 5, 0, 0));
			pnSouthWestUp.add(getPnChip5());
			pnSouthWestUp.add(getPnChip10());
			pnSouthWestUp.add(getPnChip20());
			pnSouthWestUp.add(getPnChip50());
			pnSouthWestUp.add(getPnChip100());
		}
		return pnSouthWestUp;
	}
	private JPanel getPnSouthEast() {
		if (pnSouthEast == null) {
			pnSouthEast = new JPanel();
			pnSouthEast.setBackground(new Color(0, 128, 0));
			pnSouthEast.setLayout(new GridLayout(0, 2, 0, 0));
			pnSouthEast.add(getScBets());
			pnSouthEast.add(getPnBetButtons());
		}
		return pnSouthEast;
	}
	private JPanel getPnEast() {
		if (pnEast == null) {
			pnEast = new JPanel();
			pnEast.setBackground(new Color(0, 128, 0));
			pnEast.setLayout(new GridLayout(3, 1, 0, 0));
		}
		return pnEast;
	}
	private JPanel getPnChip5() {
		if (pnChip5 == null) {
			pnChip5 = new JPanel();
			pnChip5.setBackground(new Color(0, 128, 0));
			pnChip5.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
			pnChip5.add(getBtnChip5());
		}
		return pnChip5;
	}
	private JPanel getPnChip10() {
		if (pnChip10 == null) {
			pnChip10 = new JPanel();
			pnChip10.setBackground(new Color(0, 128, 0));
			pnChip10.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnChip10.add(getBtnChip10());
		}
		return pnChip10;
	}
	private JPanel getPnChip20() {
		if (pnChip20 == null) {
			pnChip20 = new JPanel();
			pnChip20.setBackground(new Color(0, 128, 0));
			pnChip20.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnChip20.add(getBtnChip20());
		}
		return pnChip20;
	}
	private JPanel getPnChip50() {
		if (pnChip50 == null) {
			pnChip50 = new JPanel();
			pnChip50.setBackground(new Color(0, 128, 0));
			pnChip50.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnChip50.add(getBtnChip50());
		}
		return pnChip50;
	}
	private JPanel getPnChip100() {
		if (pnChip100 == null) {
			pnChip100 = new JPanel();
			pnChip100.setBackground(new Color(0, 128, 0));
			pnChip100.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnChip100.add(getBtnChip100());
		}
		return pnChip100;
	}
	private JPanel getPnAddBet() {
		if (pnAddBet == null) {
			pnAddBet = new JPanel();
			pnAddBet.setBackground(new Color(0, 128, 0));
			pnAddBet.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnAddBet.add(getTxtBetAmount());
		}
		return pnAddBet;
	}
	private JButton getBtnChip5() {
		if (btnChip5 == null) {
			btnChip5 = new JButton("");
			btnChip5.setMnemonic('1');
			btnChip5.setToolTipText("Bet 5 chips (Alt + 1)");
			btnChip5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (!txtChips.getText().isEmpty() && Double.parseDouble(txtChips.getText()) >= 5) {
						txtBetAmount.setText("5 €");
						changeChipButtonsState(false);
						double newAmount = Double.parseDouble(txtChips.getText()) - 5;
						txtChips.setText(String.valueOf(newAmount));
						if (isBetReady()) {
							enableBetButtons(true);
						}
					}else {
						JOptionPane.showMessageDialog(null, "You must buy more chips to play that amount");
					}
				}
			});
			btnChip5.setIcon(new ImageIcon(MainWindow.class.getResource("/img/Chip5.PNG")));
		}
		return btnChip5;
	}
	private JButton getBtnChip10() {
		if (btnChip10 == null) {
			btnChip10 = new JButton("");
			btnChip10.setMnemonic('2');
			btnChip10.setToolTipText("Bet 10 chips (Alt + 2)");
			btnChip10.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!txtChips.getText().isEmpty() && Double.parseDouble(txtChips.getText()) >= 10) {
						txtBetAmount.setText("10 €");
						changeChipButtonsState(false);
						double newAmount = Double.parseDouble(txtChips.getText()) - 10;
						txtChips.setText(String.valueOf(newAmount));
						if (isBetReady()) {
							enableBetButtons(true);
						}
					}else {
						JOptionPane.showMessageDialog(null, "You must buy more chips to play that amount");
					}
				}
			});
			btnChip10.setIcon(new ImageIcon(MainWindow.class.getResource("/img/Chip10.PNG")));
			
		}
		return btnChip10;
	}
	private JButton getBtnChip20() {
		if (btnChip20 == null) {
			btnChip20 = new JButton("");
			btnChip20.setMnemonic('3');
			btnChip20.setToolTipText("Bet 20 chips (Alt + 3)");
			btnChip20.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!txtChips.getText().isEmpty() && Double.parseDouble(txtChips.getText()) >= 20) {
						txtBetAmount.setText("20 €");
						changeChipButtonsState(false);
						double newAmount = Double.parseDouble(txtChips.getText()) - 20;
						txtChips.setText(String.valueOf(newAmount));
						if (isBetReady()) {
							enableBetButtons(true);
						}
					}else {
						JOptionPane.showMessageDialog(null, "You must buy more chips to play that amount");
					}
				}
			});
			btnChip20.setIcon(new ImageIcon(MainWindow.class.getResource("/img/Chip20.PNG")));
		}
		return btnChip20;
	}
	private JButton getBtnChip50() {
		if (btnChip50 == null) {
			btnChip50 = new JButton("");
			btnChip50.setMnemonic('4');
			btnChip50.setToolTipText("Bet 50 chips (Alt + 4)");
			btnChip50.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!txtChips.getText().isEmpty() && Double.parseDouble(txtChips.getText()) >= 50) {
						txtBetAmount.setText("50 €");
						changeChipButtonsState(false);
						double newAmount = Double.parseDouble(txtChips.getText()) - 50;
						txtChips.setText(String.valueOf(newAmount));
						if (isBetReady()) {
							enableBetButtons(true);
						}
					}else {
						JOptionPane.showMessageDialog(null, "You must buy more chips to play that amount");
					}
				}
			});
			btnChip50.setIcon(new ImageIcon(MainWindow.class.getResource("/img/Chip50.PNG")));
		}
		return btnChip50;
	}
	private JButton getBtnChip100() {
		if (btnChip100 == null) {
			btnChip100 = new JButton("");
			btnChip100.setMnemonic('5');
			btnChip100.setToolTipText("Bet 100 chips (Alt + 5)");
			btnChip100.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!txtChips.getText().isEmpty() && Double.parseDouble(txtChips.getText()) >= 100) {
						txtBetAmount.setText("100 €");
						changeChipButtonsState(false);
						double newAmount = Double.parseDouble(txtChips.getText()) - 100;
						txtChips.setText(String.valueOf(newAmount));
						if (isBetReady()) {
							enableBetButtons(true);
						}
					}else {
						JOptionPane.showMessageDialog(null, "You must buy more chips to play that amount");
					}
				}
			});
			btnChip100.setIcon(new ImageIcon(MainWindow.class.getResource("/img/Chip100.PNG")));
		}
		return btnChip100;
	}
	private JButton getBtnConfirmBet() {
		if (btnConfirmBet == null) {
			btnConfirmBet = new JButton("Confirm Bet");
			btnConfirmBet.setEnabled(false);
			btnConfirmBet.setToolTipText("Click to confirm box and amount selected");
			btnConfirmBet.setMnemonic('f');
			btnConfirmBet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (!txtSelectedBox.getText().isEmpty() && !txtBetAmount.getText().isEmpty()) {
						String[] parts = txtBetAmount.getText().split(" ");
						myPlays.add(new MyPlay(currentPlay, txtSelectedBox.getText(), Double.parseDouble(parts[0])));
						modelPlay.addElement(new MyPlay(getCurrentPlay(), txtSelectedBox.getText(), Double.parseDouble(parts[0])));
						changeChipButtonsState(true);
						disableNumbers(pn1, true);
						disableNumbers(pn2, true);
						disableNumbers(pn3, true);
						disableNumbers(pn4, true);
						btnStartGame.setEnabled(true);
						btnCancelBet.setEnabled(true);
						btnCancelAllBets.setEnabled(true);
						totalBet += Double.parseDouble(parts[0]);
						setChips(chips -= Double.parseDouble(parts[0]));
						if (isBetReady()) 
							enableBetButtons(false);
						txtBetAmount.setText("");
						txtSelectedBox.setText("");
					}else
						JOptionPane.showMessageDialog(null, "Please, select a box and a chip to make the bet.");
				}
			});
			btnConfirmBet.setFont(new Font("Tahoma", Font.BOLD, 18));
			
		}
		return btnConfirmBet;
	}
	private JScrollPane getScBets() {
		if (scBets == null) {
			scBets = new JScrollPane();
			scBets.setViewportView(getListBets());
		}
		return scBets;
	}
	private JList<MyPlay> getListBets() {
		if (listBets == null) {
			listBets = new JList<MyPlay>(modelPlay);
			listBets.setFont(new Font("Tahoma", Font.BOLD, 18));
			listBets.setBackground(new Color(135, 206, 250));
		}
		return listBets;
	}
	private JPanel getPnBetButtons() {
		if (pnBetButtons == null) {
			pnBetButtons = new JPanel();
			pnBetButtons.setBackground(new Color(0, 128, 0));
			pnBetButtons.setLayout(new GridLayout(0, 2, 0, 0));
			pnBetButtons.add(getPnPlayButtons());
			pnBetButtons.add(getPnGetDrinks());
		}
		return pnBetButtons;
	}
	private JPanel getPnPlayButtons() {
		if (pnPlayButtons == null) {
			pnPlayButtons = new JPanel();
			pnPlayButtons.setBackground(new Color(0, 128, 0));
			pnPlayButtons.setLayout(new GridLayout(3, 0, 0, 0));
			pnPlayButtons.add(getBtnStartGame());
			pnPlayButtons.add(getBtnCancelBet());
			pnPlayButtons.add(getBtnCancelAllBets());
		}
		return pnPlayButtons;
	}
	private JButton getBtnStartGame() {
		if (btnStartGame == null) {
			btnStartGame = new JButton("Start game");
			btnStartGame.setMnemonic('s');
			btnStartGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (modelPlay.getSize() == 0)
						JOptionPane.showMessageDialog(null, "Please, select a box and an amount to bet to start playing.");
					else {
						int result = RouletteWheel.getValue(); //0-36
						txtGame.setText("Result: "+result);
						for (MyPlay p : myPlays) {
							game = new Game(p.getBetAmount(),result);
							game.play(p.getBoxNumber());
							if (game.getTotalChips()-p.getBetAmount() > 0) //we got a prize
								prize += game.getTotalChips()-p.getBetAmount();
						}
						if (prize > 0) {
							JOptionPane.showMessageDialog(null, "Number "+result+" was out. You won "+prize+"€!");
							enablePlayingButtons(false);
						}
						else {
							JOptionPane.showMessageDialog(null, "Number "+result+" was out. You lost.");
							enablePlayingButtons(false);
						}
						chips += prize;
						updateChipsText();
						myPlays.removeAll(myPlays);
						modelPlay.removeAllElements();
					}
				}
			});
			btnStartGame.setToolTipText("Click this button to start playing the roulette game!");
			btnStartGame.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return btnStartGame;
	}
	
	private JButton getBtnCancelBet() {
		if (btnCancelBet == null) {
			btnCancelBet = new JButton("Cancel Bet");
			btnCancelBet.setMnemonic('b');
			btnCancelBet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (MyPlay o : listBets.getSelectedValuesList()) {
						modelPlay.removeElement(o);
						myPlays.remove(o);
						totalBet -= o.getBetAmount();
						double refund = o.getBetAmount() + Double.parseDouble(txtChips.getText());
						txtChips.setText(String.valueOf(refund));
					}
					if (modelPlay.getSize() == 0) {
						btnStartGame.setEnabled(false);
						btnCancelBet.setEnabled(false);
						btnCancelAllBets.setEnabled(false);
					}
					enableBetButtons(false);
				}
			});
			btnCancelBet.setToolTipText("Select a bet on the list to remove it ");
			btnCancelBet.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return btnCancelBet;
	}
	private JButton getBtnCancelAllBets() {
		if (btnCancelAllBets == null) {
			btnCancelAllBets = new JButton("Cancel Bets");
			btnCancelAllBets.setMnemonic('l');
			btnCancelAllBets.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int n = JOptionPane.showConfirmDialog(
				            null,
				            "Would you like to cancel all bets?",
				            "Removing all bets",
				            JOptionPane.YES_NO_OPTION);

				        if((n == JOptionPane.YES_OPTION && modelPlay.getSize() > 0)){
				        	btnStartGame.setEnabled(false);
				        	for (MyPlay o : myPlays) {
				        		double refund = o.getBetAmount() + Double.parseDouble(txtChips.getText());
								txtChips.setText(String.valueOf(refund));
							}
				        	myPlays.removeAll(myPlays);
				        	modelPlay.removeAllElements();
				        	btnCancelAllBets.setEnabled(false);
				        	btnCancelBet.setEnabled(false);
				        	btnStartGame.setEnabled(false);
				        }
				}
			});
			btnCancelAllBets.setToolTipText("Click to cancel all bets");
			btnCancelAllBets.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return btnCancelAllBets;
	}
	private JLabel getLblBetAmount() {
		if (lblBetAmount == null) {
			lblBetAmount = new JLabel("Total bet amount:");
			lblBetAmount.setForeground(new Color(255, 255, 255));
			lblBetAmount.setHorizontalAlignment(SwingConstants.CENTER);
			lblBetAmount.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblBetAmount;
	}
	private JPanel getPnNorthEast() {
		if (pnNorthEast == null) {
			pnNorthEast = new JPanel();
			pnNorthEast.setLayout(new GridLayout(0, 2, 0, 0));
			pnNorthEast.add(getPnw1());
			pnNorthEast.add(getTxtGame());
		}
		return pnNorthEast;
	}
	private JTextField getTxtGame() {
		if (txtGame == null) {
			txtGame = new JTextField();
			txtGame.setEditable(false);
			txtGame.setText("Welcome!");
			txtGame.setHorizontalAlignment(SwingConstants.CENTER);
			txtGame.setFont(new Font("Tahoma", Font.BOLD, 20));
			txtGame.setColumns(10);
		}
		return txtGame;
	}
	private JTextField getTxtBetAmount() {
		if (txtBetAmount == null) {
			txtBetAmount = new JTextField();
			txtBetAmount.setFont(new Font("Tahoma", Font.BOLD, 18));
			txtBetAmount.setEditable(false);
			txtBetAmount.setColumns(6);
		}
		return txtBetAmount;
	}

	private JPanel getPnSouthWestDown1() {
		if (pnSouthWestDown1 == null) {
			pnSouthWestDown1 = new JPanel();
			pnSouthWestDown1.setBackground(new Color(0, 128, 0));
			pnSouthWestDown1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnSouthWestDown1.add(getLabel_1());
			pnSouthWestDown1.add(getTxtSelectedBox());
		}
		return pnSouthWestDown1;
	}
	private JPanel getPnSouthWest() {
		if (pnSouthWest == null) {
			pnSouthWest = new JPanel();
			pnSouthWest.setLayout(new GridLayout(2, 1, 0, 0));
			pnSouthWest.add(getPnSouthWestUp());
			pnSouthWest.add(getPnSouthWestDown());
		}
		return pnSouthWest;
	}
	
	private void changeChipButtonsState(boolean state){
		btnChip5.setEnabled(state);
		btnChip10.setEnabled(state);
		btnChip20.setEnabled(state);
		btnChip50.setEnabled(state);
		btnChip100.setEnabled(state);
	}
	
	private boolean disableNumbers(JPanel panel, boolean state) {
		for (int i = 0; i < panel.getComponentCount(); i++)
			((JButton)(panel.getComponent(i))).setEnabled(state);
		return state;
	}
	
	private JPanel getPnSouthWestDown2() {
		if (pnSouthWestDown2 == null) {
			pnSouthWestDown2 = new JPanel();
			pnSouthWestDown2.setBackground(new Color(0, 128, 0));
			pnSouthWestDown2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnSouthWestDown2.add(getLblBetAmount());
			pnSouthWestDown2.add(getPnAddBet());
			pnSouthWestDown2.add(getBtnConfirmBet());
			pnSouthWestDown2.add(getBtnCancelBet_1());
		}
		return pnSouthWestDown2;
	}
	private JPanel getPnSouthWestDown() {
		if (pnSouthWestDown == null) {
			pnSouthWestDown = new JPanel();
			pnSouthWestDown.setBackground(new Color(0, 128, 0));
			pnSouthWestDown.setLayout(new GridLayout(0, 1, 0, 0));
			pnSouthWestDown.add(getPnSouthWestDown1());
			pnSouthWestDown.add(getPnSouthWestDown2());
		}
		return pnSouthWestDown;
	}
	private JLabel getLabel_1() {
		if (lblSelectedNumber == null) {
			lblSelectedNumber = new JLabel("Selected box:");
			lblSelectedNumber.setHorizontalAlignment(SwingConstants.CENTER);
			lblSelectedNumber.setForeground(Color.WHITE);
			lblSelectedNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblSelectedNumber;
	}
	private JTextField getTxtSelectedBox() {
		if (txtSelectedBox == null) {
			txtSelectedBox = new JTextField();
			txtSelectedBox.setFont(new Font("Tahoma", Font.BOLD, 18));
			txtSelectedBox.setEditable(false);
			txtSelectedBox.setColumns(6);
		}
		return txtSelectedBox;
	}
	private JButton getBtnCancelBet_1() {
		if (btnCancelBet_1 == null) {
			btnCancelBet_1 = new JButton("Cancel selection");
			btnCancelBet_1.setEnabled(false);
			btnCancelBet_1.setToolTipText("Resets box and selected chip");
			btnCancelBet_1.setMnemonic('c');
			btnCancelBet_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (isBetReady()) {
						updateChipsText();
						txtBetAmount.setText("");
						txtSelectedBox.setText("");
						changeChipButtonsState(true);
						disableNumbers(pn1, true);
						disableNumbers(pn2, true);
						disableNumbers(pn3, true);
						disableNumbers(pn4, true);
						enableBetButtons(false);
					}
				}
			});
			btnCancelBet_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return btnCancelBet_1;
	}
	
	private JButton newButton(int pos) {
		JButton btnZero = new JButton();
		btnZero.setText(String.valueOf(pos));
		btnZero.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnZero.setHorizontalTextPosition(SwingConstants.CENTER);
		btnZero.setActionCommand(String.valueOf(pos));
		btnZero.addActionListener(mbl);
		if (pos == 0) 
			btnZero.setForeground(Color.BLUE);
		
		if (isInArrayRange(redPositions, pos))
			btnZero.setForeground(Color.RED);
			
		return btnZero;
	}
	
	private JButton newNamedButton(String name, int pos) {
		JButton b = new JButton();
		b.setText(name);
		b.setFont(new Font("Tahoma", Font.BOLD, 15));
		b.setHorizontalTextPosition(SwingConstants.CENTER);
		b.setActionCommand(String.valueOf(pos));
		b.addActionListener(mbl);
		return b;
	}
	
	public boolean isInArrayRange(int[] array, int result) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == result)
				return true;
		}
		return false;
	}
	
	private JPanel getPn1() {
		if (pn1 == null) {
			pn1 = new JPanel();
			pn1.setBackground(new Color(0, 128, 0));
			pn1.setLayout(new GridLayout(1, 13, 0, 5));
			int iterator = 3;
			for (int i = 1; i <= 13; i++) { //From 1-13
				if (i == 13)
					pn1.add(newNamedButton("C3", 39));
				else {
					if (i == 1)
						pn1.add(newButton(iterator));
					else {
						iterator += 3;
						pn1.add(newButton(iterator));
					}
				}
			}
		}
		return pn1;
	}
	private JPanel getPn2() {
		if (pn2 == null) {
			pn2 = new JPanel();
			pn2.setBackground(new Color(0, 128, 0));
			pn2.setLayout(new GridLayout(1, 13, 0, 5));
			int iterator = 2;
			for (int i = 1; i <= 13; i++) { //From 1-13
				if (i == 13)
					pn2.add(newNamedButton("C2", 38));
				else {
					if (i == 1)
						pn2.add(newButton(iterator));
					else {
						iterator += 3;
						pn2.add(newButton(iterator));
					}
				}
			}
		}
		return pn2;
	}
	private JPanel getPn3() {
		if (pn3 == null) {
			pn3 = new JPanel();
			pn3.setBackground(new Color(0, 128, 0));
			pn3.setLayout(new GridLayout(1, 13, 0, 0));
			int iterator = 1;
			for (int i = 1; i <= 13; i++) { //From 1-13
				if (i == 13)
					pn3.add(newNamedButton("C1", 37));
				else {
					if (i == 1)
						pn3.add(newButton(iterator));
					else {
						iterator += 3;
						pn3.add(newButton(iterator));
					}
				}
			}
		}
		return pn3;
	}
	
	/**
	 * Column of special values
	 * @return
	 */
	private JPanel getPn4() {
		if (pn4 == null) {
			pn4 = new JPanel();
			pn4.setBackground(new Color(0, 128, 0));
			pn4.setLayout(new GridLayout(1, 9, 0, 0));
			for (int i = 40; i <= 48 ; i++) {
				switch(i) {
				case 40:
					pn4.add(newNamedButton("1-12",i));
					break;
				case 41:
					pn4.add(newNamedButton("13-24",i));
					break;
				case 42:
					pn4.add(newNamedButton("25-36",i));
					break;
				case 43:
					pn4.add(newNamedButton("1-18",i));
					break;
				case 44:
					pn4.add(newNamedButton("19-36",i));
					break;
				case 45:
					pn4.add(newNamedButton("Even",i));
					break;
				case 46:
					pn4.add(newNamedButton("Odd",i));
					break;
				case 47:
					pn4.add(newNamedButton("Black",i));
					break;
				case 48:
					pn4.add(newNamedButton("Red",i));
					break;
				}
			}
		}
		return pn4;
	}
	

	private void writeSpecialValues(int number) {
		switch (number) {
		case 37:
			txtSelectedBox.setText("Column 1");
			break;
		case 38:
			txtSelectedBox.setText("Column 2");
			break;
		case 39:
			txtSelectedBox.setText("Column 3");
			break;
		case 40:
			txtSelectedBox.setText("1st Dozen");
			break;
		case 41:
			txtSelectedBox.setText("2nd Dozen");
			break;
		case 42:
			txtSelectedBox.setText("3rd Dozen");
			break;
		case 43:
			txtSelectedBox.setText("1-18");
			break;
		case 44:
			txtSelectedBox.setText("19-36");
			break;
		case 45:
			txtSelectedBox.setText("Even");
			break;
		case 46:
			txtSelectedBox.setText("Odd");
			break;
		case 47:
			txtSelectedBox.setText("Black");
			break;
		case 48:
			txtSelectedBox.setText("Red");
			break;
		default:
			txtSelectedBox.setText(String.valueOf(number));
			break;
		}
	}
	
	private JPanel getPnGetDrinks() {
		if (pnGetDrinks == null) {
			pnGetDrinks = new JPanel();
			pnGetDrinks.setBackground(new Color(0, 128, 0));
			pnGetDrinks.setLayout(new GridLayout(3, 1, 0, 0));
			pnGetDrinks.add(getLblEmpty());
			pnGetDrinks.add(getBtnDrinks());
		}
		return pnGetDrinks;
	}
	private JLabel getLblEmpty() {
		if (lblEmpty == null) {
			lblEmpty = new JLabel("");
		}
		return lblEmpty;
	}
	
	public int getCurrentPlay() {
		return currentPlay;
	}
	private JButton getBtnDrinks() {
		if (btnDrinks == null) {
			btnDrinks = new JButton("Drinks");
			btnDrinks.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openDrinksWindow();
				}
			});
			btnDrinks.setToolTipText("Click here to order a drink");
			btnDrinks.setMnemonic('r');
			btnDrinks.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnDrinks.setBackground(Color.ORANGE);
		}
		return btnDrinks;
	}
	
	private void openDrinksWindow() {
		DrinksWindow d = new DrinksWindow(this);
		d.setLocationRelativeTo(this);
		d.setVisible(true);
	}
	
	private void openLoginWindow() {
		//open login and close the frame
		loginWindow = new LoginWindow();
		loginWindow.setVisible(true);
		this.dispose();
	}
	
	private void withdrawMoneyBeforeExit() {
		if (!txtChips.getText().isEmpty()) {
			double valueChips = Double.parseDouble(txtChips.getText());
			double actualBalance = Double.parseDouble(txtBalance.getText());
			double total = valueChips + actualBalance;
			actualClient.setBalance(total);
			txtBalance.setText(String.valueOf(actualClient.getBalance()));
			txtChips.setText("0");
		}
		for (int i = 0 ;i < clients.size() ; i++) {
			if (clients.get(i).getUser().equals(actualClient.getUser()))
				clients.set(i, actualClient);
		}
		FileUtil.saveClient("customers", clients);
	}
	
	private void openBalanceWindow() {
		BalanceWindow cw = new BalanceWindow(this);
		cw.setLocationRelativeTo(this);
		cw.setModal(true);
		cw.setVisible(true);
	}
	
	private JTextField getTxtBalance() {
		if (txtBalance == null) {
			txtBalance = new JTextField();
			txtBalance.setHorizontalAlignment(SwingConstants.RIGHT);
			txtBalance.setToolTipText("Actual balance");
			txtBalance.setFont(new Font("Tahoma", Font.BOLD, 18));
			txtBalance.setEditable(false);
			txtBalance.setColumns(5);
			txtBalance.setText(String.valueOf(actualClient.getBalance()));
		}
		return txtBalance;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
		}
		return label;
	}
	private JLabel getLabel_1_1() {
		if (label_1 == null) {
			label_1 = new JLabel("");
		}
		return label_1;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("");
		}
		return label_2;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("");
		}
		return label_3;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("");
		}
		return label_4;
	}
	private JLabel getLblChips() {
		if (lblChips == null) {
			lblChips = new JLabel("Chips:");
			lblChips.setForeground(Color.WHITE);
			lblChips.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return lblChips;
	}
	
	public Client getActualClient() {
		return this.actualClient;
	}
	
	public void updateTxtMoney(double amount) {
		double actualAmount = Double.parseDouble(txtBalance.getText());
		txtBalance.setText(String.valueOf(actualAmount + amount));
	}
	
//	public void buyDrink(double amount) {
//		double actualAmount = Double.parseDouble(txtBalance.getText());
//		txtBalance.setText(String.valueOf(actualAmount - amount));
//	}
	
	private JLabel getLblUserName() {
		if (lblUserName == null) {
			lblUserName = new JLabel("");
			lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
			lblUserName.setForeground(new Color(255, 255, 255));
			lblUserName.setFont(new Font("Tahoma", Font.BOLD, 20));
		}
		return lblUserName;
	}
	private JPanel getPnw1() {
		if (pnw1 == null) {
			pnw1 = new JPanel();
			pnw1.setBackground(new Color(0, 0, 255));
			pnw1.setLayout(new GridLayout(0, 1, 0, 0));
			pnw1.add(getLblUserName());
		}
		return pnw1;
	}
	
	public void updateBalanceText() {
		txtBalance.setText(String.valueOf(actualClient.getBalance()));
	}
	
	public void setChips(double amount) {
		this.chips = amount;
	}
	
	public double getChips() {
		return this.chips;
	}
	
	public void setTotalBet(double value) {
		this.totalBet = value;
	}
	
	public double getTotalBet() {
		return this.totalBet;
	}

	public void updateChipsText() {
		txtChips.setText(String.valueOf(chips));
	}
	private JMenuBar getMenuBar_2() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMenu());
		}
		return menuBar;
	}
	private JMenuBar getMenu() {
		if (menu == null) {
			menu = new JMenuBar();
			menu.add(getMnUser());
			menu.add(getMnHelp());
		}
		return menu;
	}
	private JMenu getMnUser() {
		if (mnUser == null) {
			mnUser = new JMenu("User");
			mnUser.setMnemonic('U');
			mnUser.setHorizontalAlignment(SwingConstants.CENTER);
			mnUser.setForeground(Color.BLACK);
			mnUser.setFont(new Font("Tahoma", Font.BOLD, 18));
			mnUser.setBackground(Color.YELLOW);
			mnUser.add(getItAddMoney());
			mnUser.add(getMenuItem_1());
			mnUser.add(getMenuItem_2());
			mnUser.add(getItWithdrawChips());
			mnUser.add(getSeparator());
			mnUser.add(getItBuyDrinks());
			mnUser.add(getSeparator_1());
			mnUser.add(getItLogout());
		}
		return mnUser;
	}
	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setMnemonic('H');
			mnHelp.setHorizontalAlignment(SwingConstants.CENTER);
			mnHelp.setForeground(Color.BLACK);
			mnHelp.setFont(new Font("Tahoma", Font.BOLD, 18));
			mnHelp.setBackground(Color.YELLOW);
			mnHelp.add(getMntmHelpContent());
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}
	private JMenuItem getItAddMoney() {
		if (itAddMoney == null) {
			itAddMoney = new JMenuItem("Add money");
			itAddMoney.setMnemonic('a');
			itAddMoney.setForeground(new Color(0, 0, 0));
			itAddMoney.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
			itAddMoney.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					openBalanceWindow();
				}
			});
			itAddMoney.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return itAddMoney;
	}
	private JMenuItem getMenuItem_1() {
		if (itWithdrawMoney == null) {
			itWithdrawMoney = new JMenuItem("Withdraw money");
			itWithdrawMoney.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					actualClient.setBalance(0);
					updateBalanceText();
					JOptionPane.showMessageDialog(null, "Your money has been put back to your bank account.");
				}
			});
			itWithdrawMoney.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
			itWithdrawMoney.setMnemonic('t');
			itWithdrawMoney.setFont(new Font("Tahoma", Font.BOLD, 15));
			itWithdrawMoney.setForeground(new Color(0, 0, 0));
		}
		return itWithdrawMoney;
	}
	private JMenuItem getMenuItem_2() {
		if (itAddChips == null) {
			itAddChips = new JMenuItem("Add chips");
			itAddChips.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openChipsWindow();
				}
			});
			itAddChips.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
			itAddChips.setMnemonic('c');
			itAddChips.setFont(new Font("Tahoma", Font.BOLD, 15));
			itAddChips.setForeground(new Color(0, 0, 0));
		}
		return itAddChips;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenuItem getItBuyDrinks() {
		if (itBuyDrinks == null) {
			itBuyDrinks = new JMenuItem("Buy drinks");
			itBuyDrinks.setMnemonic('d');
			itBuyDrinks.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					openDrinksWindow();
				}
			});
			itBuyDrinks.setFont(new Font("Segoe UI", Font.BOLD, 16));
			itBuyDrinks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		}
		return itBuyDrinks;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	private JMenuItem getItLogout() {
		if (itLogout == null) {
			itLogout = new JMenuItem("Log out");
			itLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int n = JOptionPane.showConfirmDialog(
				            null,
				            "Do you really want to exit the Roulette Wheel Game?",
				            "Exit",
				            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				        if((n == 0 /*JOptionPane.YES_OPTION*/)){
				        	withdrawMoneyBeforeExit();
				        	openLoginWindow();
				        }
				        else
				        	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			});
			itLogout.setMnemonic('o');
			itLogout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
			itLogout.setFont(new Font("Segoe UI", Font.BOLD, 16));
		}
		return itLogout;
	}
	private JMenuItem getItWithdrawChips() {
		if (itWithdrawChips == null) {
			itWithdrawChips = new JMenuItem("Withdraw Chips");
			itWithdrawChips.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
			itWithdrawChips.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualClient.setBalance(actualClient.getBalance()+chips);
					JOptionPane.showMessageDialog(null, chips+" chips moved to your balance.");
					chips = 0;
					updateChipsText();
					updateBalanceText();
				}
			});
			itWithdrawChips.setMnemonic('r');
			itWithdrawChips.setFont(new Font("Segoe UI", Font.BOLD, 15));
			itWithdrawChips.setForeground(new Color(0, 0, 0));
		}
		return itWithdrawChips;
	}
	private JMenuItem getMntmHelpContent() {
		if (mntmHelpContent == null) {
			mntmHelpContent = new JMenuItem("Help content");
			mntmHelpContent.setMnemonic('h');
			mntmHelpContent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		}
		return mntmHelpContent;
	}
	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.setMnemonic('a');
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Roulette Wheel Game\nAuthor:Sergio Fernández López\nEII Uniovi");
				}
			});
		}
		return mntmAbout;
	}
	private boolean isBetReady() {
		return !txtBetAmount.getText().isEmpty() && !txtSelectedBox.getText().isEmpty();
	}
	
	private void enableBetButtons(boolean value) {
		btnConfirmBet.setEnabled(value);
		btnCancelBet_1.setEnabled(value);
	}
	
	private void enablePlayingButtons(boolean value) {
		btnStartGame.setEnabled(value);
		btnCancelBet.setEnabled(value);
		btnCancelAllBets.setEnabled(value);
	}
}