package logic;

public class Client {
	private String dni;
	private String name;
	private String user;
	private String password;
	private double balance;
	private boolean vip;

	public Client(String dni, String name, String user, String password, double balance,boolean vip) {
		setDni(dni);
		setName(name);
		setUser(user);
		setPassword(password);
		setBalance(balance);
		setVip(vip);
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isVip() {
		return vip;
	}
	public void setVip(boolean vip) {
		this.vip = vip;
	}
}
