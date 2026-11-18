package gui;

import java.awt.BorderLayout;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Drink;
import logic.FileUtil;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.ComponentOrientation;
import javax.swing.JCheckBox;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class DrinksWindow extends JDialog {

	class MyButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			int number = Integer.parseInt(b.getActionCommand());
			currentDrink = drinksList.get(number);
			String name = currentDrink.getName();
			String price = String.valueOf(currentDrink.getPrice());
			String actualImagePath = "/img/" + drinksList.get(number).getReference() +".png";
			Image imgOriginal = new ImageIcon(getClass().getResource(actualImagePath)).getImage();
			Image imgScaled = imgOriginal.getScaledInstance(pnImageDrink.getWidth(), pnImageDrink.getHeight(), Image.SCALE_SMOOTH);    //Scale the image according to the button
			ImageIcon icon = new ImageIcon(imgScaled);
			lblDrinkImage.setIcon(icon);
			txtDetails.setText(name + "   -   "+ price + "€");
			btnAddDrink.setEnabled(true);
		}
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	@SuppressWarnings("unused")
	private MainWindow mainWindow;
	private ArrayList<Drink> drinksList;
	private MyButtonListener mbl = new MyButtonListener();
	private JPanel pnDrinks;
	private Drink currentDrink;
	private JList<Drink> listDrinks;
	private DefaultListModel<Drink> selectedDrinksModel = new DefaultListModel<Drink>();
	private JTextField txtDetails;
	public JLabel lblDrinkImage;
	String actualImagePath;
	private JPanel pnImageDrink;
	private JButton btnAddDrink;
	private JPanel pnSelectedOrders;
	private JScrollPane scOrders;
	private JPanel pnTotalPrice;
	private JTextField txtTotalPrice;
	private double drinksAmount;
	private JButton btnOrder;
	private JButton btnRemoveDrinks;
	private JCheckBox ckAlcoholic;
	private JCheckBox ckNonAlcoholic;
	
	/**
	 * Create the dialog.
	 */
	public DrinksWindow(MainWindow mw) {
		this.drinksAmount = 0;
		setFont(new Font("Dialog", Font.BOLD, 13));
		if (mw.getActualClient().isVip())
			setTitle("Roulette Game - Drinks - Now with a 10% discount on your order for being a VIP user!");
		else
			setTitle("Roulette Game - Drinks - Become VIP to get a 10% discount on your orders!");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DrinksWindow.class.getResource("/img/BE07.png")));
		this.mainWindow = mw;
		setModal(true);
		drinksList = new ArrayList<Drink>();
		//Load drinks
		FileUtil.loadDrinks("drinks", drinksList, mw.getActualClient().isVip());
		setBounds(100, 100, 952, 560);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 128, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnNorth = new JPanel();
			pnNorth.setBackground(new Color(0, 0, 128));
			contentPanel.add(pnNorth, BorderLayout.NORTH);
			{
				JLabel lblDrinks = new JLabel("Drinks");
				lblDrinks.setForeground(new Color(255, 255, 255));
				lblDrinks.setFont(new Font("Tahoma", Font.BOLD, 30));
				pnNorth.add(lblDrinks);
				if (mw.getActualClient().isVip())
					lblDrinks.setText("Drinks - VIP (10% discount)");
				else
					lblDrinks.setText("Drinks");
			}
		}
		{
			JPanel pnSouth = new JPanel();
			pnSouth.setBackground(new Color(0, 128, 0));
			contentPanel.add(pnSouth, BorderLayout.SOUTH);
			pnSouth.setLayout(new GridLayout(0, 2, 0, 0));
			{
				pnSelectedOrders = new JPanel();
				pnSelectedOrders.setBackground(new Color(135, 206, 235));
				pnSouth.add(pnSelectedOrders);
				pnSelectedOrders.setLayout(new GridLayout(0, 1, 0, 0));
				{
					scOrders = new JScrollPane();
					scOrders.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					pnSelectedOrders.add(scOrders);
					listDrinks = new JList<Drink>(selectedDrinksModel);
					listDrinks.addContainerListener(new ContainerAdapter() {
						@Override
						public void componentRemoved(ContainerEvent arg0) {
							if (selectedDrinksModel.getSize() == 0)
								btnRemoveDrinks.setEnabled(false);
						}
					});
					listDrinks.setFont(new Font("Tahoma", Font.BOLD, 16));
					listDrinks.setToolTipText("Your actual order");
					listDrinks.setVisibleRowCount(5);
					listDrinks.setBackground(new Color(135, 206, 235));
					scOrders.add(listDrinks);
					scOrders.setViewportView(listDrinks);
				}
			}
			{
				JPanel pnButtons = new JPanel();
				pnSouth.add(pnButtons);
				{
					btnOrder = new JButton("Order");
					btnOrder.setEnabled(false);
					btnOrder.setMnemonic('O');
					btnOrder.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (!selectedDrinksModel.isEmpty()) { //Selected a drink
								JOptionPane.showMessageDialog(null, "Success! Your order is on the way!");
								mw.getActualClient().setBalance(mw.getActualClient().getBalance() - drinksAmount);
								mw.updateBalanceText();
								dispose();
							}else { 
								JOptionPane.showMessageDialog(null, "Please, select a drink to make the order");
							}
						}
					});
					pnButtons.setLayout(new GridLayout(0, 3, 0, 0));
					{
						btnRemoveDrinks = new JButton("Remove Drinks");
						btnRemoveDrinks.setEnabled(false);
						btnRemoveDrinks.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								for (Drink d : listDrinks.getSelectedValuesList()) {
									selectedDrinksModel.removeElement(d); //remove the drink
									drinksAmount -= d.getPrice();
									drinksAmount = Math.floor((drinksAmount)* 100) / 100;
									txtTotalPrice.setText(String.valueOf(drinksAmount));
								}
								if (selectedDrinksModel.getSize() == 0) {
									btnOrder.setEnabled(false);
									btnRemoveDrinks.setEnabled(false);
								}
							}
						});
						btnRemoveDrinks.setToolTipText("Remove one or more drinks from the order list");
						btnRemoveDrinks.setMnemonic('r');
						btnRemoveDrinks.setFont(new Font("Tahoma", Font.BOLD, 15));
						pnButtons.add(btnRemoveDrinks);
					}
					btnOrder.setToolTipText("Order selected drink");
					btnOrder.setFont(new Font("Tahoma", Font.BOLD, 16));
					pnButtons.add(btnOrder);
				}
				{
					JButton btnCancel = new JButton("Cancel");
					btnCancel.setMnemonic('c');
					btnCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int opt = JOptionPane.showConfirmDialog(null, "Do you want to cancel the drinks purchase?", "Cancel drinks purchase",2);
							if (opt == JOptionPane.YES_OPTION) {
								mw.setVisible(true);
								dispose();
							}
						}
					});
					btnCancel.setToolTipText("Cancel the order and go back to the game");
					btnCancel.setFont(new Font("Tahoma", Font.BOLD, 16));
					pnButtons.add(btnCancel);
				}
			}
		}
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				int n = JOptionPane.showConfirmDialog(
			            null,
			            "Do you really want to exit without buying any drink?",
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
		{
			JScrollPane scCentral = new JScrollPane();
			contentPanel.add(scCentral, BorderLayout.CENTER);
			{
				pnDrinks = new JPanel();
				pnDrinks.setBackground(new Color(0, 128, 0));
				scCentral.setViewportView(pnDrinks);
				pnDrinks.setLayout(new GridLayout(5, 5, 0, 0));
				//Add buttons here
				for (int i = 0; i < drinksList.size(); i++) {
					pnDrinks.add(newButton(i,drinksList.get(i)));
				}
			}
		}
		{
			JPanel pnDetails = new JPanel();
			pnDetails.setBackground(new Color(0, 128, 0));
			contentPanel.add(pnDetails, BorderLayout.EAST);
			pnDetails.setLayout(new GridLayout(2, 0, 0, 0));
			{
				pnImageDrink = new JPanel();
				pnImageDrink.setBackground(new Color(0, 128, 0));
				pnDetails.add(pnImageDrink);
				pnImageDrink.setLayout(new GridLayout(0, 1, 0, 0));
				{
					lblDrinkImage = new JLabel("");
					lblDrinkImage.setHorizontalAlignment(SwingConstants.CENTER);
					pnImageDrink.add(lblDrinkImage);
				}
			}
			{
				JPanel pnAddDrink = new JPanel();
				pnAddDrink.setBackground(new Color(0, 128, 0));
				pnDetails.add(pnAddDrink);
				pnAddDrink.setLayout(new GridLayout(2, 1, 0, 0));
				{
					JPanel pnDescriptionDrink = new JPanel();
					pnAddDrink.add(pnDescriptionDrink);
					pnDescriptionDrink.setLayout(new GridLayout(0, 1, 0, 0));
					{
						txtDetails = new JTextField();
						txtDetails.setHorizontalAlignment(SwingConstants.CENTER);
						txtDetails.setFont(new Font("Tahoma", Font.BOLD, 14));
						txtDetails.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
						txtDetails.setScrollOffset(2);
						txtDetails.setEditable(false);
						txtDetails.setBackground(new Color(135, 206, 235));
						pnDescriptionDrink.add(txtDetails);
						txtDetails.setColumns(15);
					}
				}
				{
					JPanel pnAddRemoveButtons = new JPanel();
					pnAddRemoveButtons.setBackground(new Color(0, 128, 0));
					pnAddDrink.add(pnAddRemoveButtons);
					pnAddRemoveButtons.setLayout(new GridLayout(0, 1, 0, 0));
					{
						JPanel pnFilters = new JPanel();
						pnAddRemoveButtons.add(pnFilters);
						{
							ckAlcoholic = new JCheckBox("Alcoholic");
							ckAlcoholic.addItemListener(new ItemListener() {
								public void itemStateChanged(ItemEvent e) {
									if (e.getStateChange() == 1) {
										//First, remove actual buttons
										if (ckNonAlcoholic.isSelected())
											ckNonAlcoholic.setSelected(false);
										pnDrinks.removeAll();
										//Then, add just the alcoholic drinks
										for (int i = 0; i < drinksList.size(); i++) {
											if (drinksList.get(i).isAlcoholic())
												pnDrinks.add(newButton(i,drinksList.get(i)));
										}
										validate();
										repaint();
									}else {
										pnDrinks.removeAll();
										for (int i = 0; i < drinksList.size(); i++) {
											pnDrinks.add(newButton(i,drinksList.get(i)));
										}
										validate();
										repaint();
									}
								}
							});
							
							ckAlcoholic.setHorizontalAlignment(SwingConstants.CENTER);
							ckAlcoholic.setMnemonic('h');
							ckAlcoholic.setFont(new Font("Tahoma", Font.BOLD, 14));
							ckAlcoholic.setToolTipText("Shows just the alcoholic drinks");
							pnFilters.add(ckAlcoholic);
						}
						{
							ckNonAlcoholic = new JCheckBox("Non Alcoholic");
							ckNonAlcoholic.addItemListener(new ItemListener() {
								public void itemStateChanged(ItemEvent e) {
									if (e.getStateChange() == 1) {
										//First, remove actual buttons and deselect the alcoholic option if selected
										if (ckAlcoholic.isSelected())
											ckAlcoholic.setSelected(false);
										pnDrinks.removeAll();
										//Then, add just the alcoholic drinks
										for (int i = 0; i < drinksList.size(); i++) {
											if (!drinksList.get(i).isAlcoholic())
												pnDrinks.add(newButton(i,drinksList.get(i)));
										}
										validate();
										repaint();
									}else {
										pnDrinks.removeAll();
										for (int i = 0; i < drinksList.size(); i++) {
											pnDrinks.add(newButton(i,drinksList.get(i)));
										}
										validate();
										repaint();
									}
								}
							});
							ckNonAlcoholic.setToolTipText("Shows just the alcoholic drinks");
							ckNonAlcoholic.setMnemonic('n');
							ckNonAlcoholic.setHorizontalAlignment(SwingConstants.CENTER);
							ckNonAlcoholic.setFont(new Font("Tahoma", Font.BOLD, 14));
							pnFilters.add(ckNonAlcoholic);
						}
					}
					{
						JPanel pnButtonAdd = new JPanel();
						pnAddRemoveButtons.add(pnButtonAdd);
						{
							btnAddDrink = new JButton("Add");
							btnAddDrink.setBackground(new Color(192, 192, 192));
							btnAddDrink.setForeground(new Color(0, 0, 0));
//							UIManager.put("Button.disabledText", Color.BLACK);
							btnAddDrink.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									if (currentDrink.getPrice() + drinksAmount <= mw.getActualClient().getBalance()) {
										selectedDrinksModel.addElement(currentDrink);
										drinksAmount += currentDrink.getPrice();
										txtTotalPrice.setText(String.valueOf(drinksAmount));
										btnOrder.setEnabled(true);
										btnRemoveDrinks.setEnabled(true);
									}
									else
										JOptionPane.showMessageDialog(null, "Sorry, but you'll need to add more money into your account to buy more drinks.");
									repaint();
								}
							});
							pnAddRemoveButtons.setLayout(new GridLayout(0, 1, 0, 0));
							pnButtonAdd.setLayout(new GridLayout(0, 1, 0, 0));
							btnAddDrink.setMnemonic('a');
							btnAddDrink.setEnabled(false);
							btnAddDrink.setFont(new Font("Tahoma", Font.BOLD, 14));
							pnButtonAdd.add(btnAddDrink);
					}
				}
					{
						pnTotalPrice = new JPanel();
						pnAddRemoveButtons.add(pnTotalPrice);
						{
							JLabel lblNewLabel = new JLabel("Total:");
							lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
							lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
							lblNewLabel.setForeground(new Color(0, 0, 0));
							pnTotalPrice.add(lblNewLabel);
						}
						{
							txtTotalPrice = new JTextField();
							txtTotalPrice.setHorizontalAlignment(SwingConstants.CENTER);
							txtTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
							txtTotalPrice.setEditable(false);
							pnTotalPrice.add(txtTotalPrice);
							txtTotalPrice.setColumns(10);
						}
						{
							JLabel lblNewLabel_1 = new JLabel("\u20AC");
							lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
							pnTotalPrice.add(lblNewLabel_1);
						}
					}
				}
			}
		}
	}
	
	private JButton newButton(int pos, Drink d) {
		JButton b = new JButton();
		b.setActionCommand(String.valueOf(pos));
		String actualImagePath = "/img/" + drinksList.get(pos).getReference() +".png";
		b.setToolTipText(d.getName());
		b.setVerticalAlignment(SwingConstants.TOP);
		b.setVerticalTextPosition(AbstractButton.BOTTOM);
		b.setIcon(new ImageIcon(MainWindow.class.getResource(actualImagePath)));
		b.addActionListener(mbl);
		b.setBounds(new Rectangle(75, 75));
		Image imgOriginal = new ImageIcon(getClass().getResource(actualImagePath)).getImage();
		Image imgScaled = imgOriginal.getScaledInstance(b.getWidth(), b.getHeight(), Image.SCALE_SMOOTH);    //Scale the image according to the button
		ImageIcon icon = new ImageIcon(imgScaled);
		b.setIcon(icon);
		if (!d.isAvailable())
			b.setEnabled(false);
		return b;
	}
	
}
