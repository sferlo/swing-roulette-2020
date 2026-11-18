package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class FileUtil {
	
public static void loadClients (String fileName, String vipFile, List<Client> clients) {
		
	    String line;
	    String[] clientData = null;	   
	    
	    try {
	    	   BufferedReader file = new BufferedReader(new FileReader(fileName));
	    	   BufferedReader fileVIP;// = new BufferedReader(new FileReader(vipFile));
	    	   while (file.ready()) {
	    		   	fileVIP = new BufferedReader(new FileReader(vipFile));
	    			line = file.readLine();
	    			clientData = line.split("@");
	    			boolean isVIP = false;
	    			while (fileVIP.ready()) {
	    				isVIP = false;
	    				String[] lineVIP = fileVIP.readLine().split("@");
						if (clientData[0].equals(lineVIP[0])) { //Integer.parseInt(clientData[0]) == Integer.parseInt(lineVIP[0])
							isVIP = true;
							break;
						}
	    			}
	    			if (isVIP)
	    				clients.add(new Client(clientData[0],clientData[1],clientData[2],clientData[3],Double.parseDouble(clientData[4]),isVIP));
	    			else
	    				clients.add(new Client(clientData[0],clientData[1],clientData[2],clientData[3],Double.parseDouble(clientData[4]),isVIP));
	    			fileVIP.close();
	    	   }
	    		file.close();
	    }
	    catch (FileNotFoundException fnfe) {
	      System.out.println("File not found.");
	    }
	    catch (IOException ioe) {
	      new RuntimeException("I/O Error.");
	    } 
	  }

public static void loadDrinks (String fileName, List<Drink> drinks, boolean vip) {
	
    String line;
    String[] drinksData = null;	   
    
    try {
    	   BufferedReader file = new BufferedReader(new FileReader("files/"+fileName+".dat"));
    		while (file.ready()) {
    			line = file.readLine();
    			drinksData = line.split("@");
    			if (vip) {
    				if (drinksData[4].equals("S"))
    					drinks.add(new Drink(drinksData[0],drinksData[1],Integer.parseInt(drinksData[2]),Math.floor(Double.parseDouble(drinksData[3])*0.9 * 100) / 100, true));
    				else
    					drinks.add(new Drink(drinksData[0],drinksData[1],Integer.parseInt(drinksData[2]),Math.floor(Double.parseDouble(drinksData[3])*0.9 * 100) / 100, false));
    			}	
    			else {
    				if (drinksData[4].equals("S"))
    					drinks.add(new Drink(drinksData[0],drinksData[1],Integer.parseInt(drinksData[2]),Math.floor(Double.parseDouble(drinksData[3])* 100) / 100, true));
    				else
    					drinks.add(new Drink(drinksData[0],drinksData[1],Integer.parseInt(drinksData[2]),Math.floor(Double.parseDouble(drinksData[3])* 100) / 100, false));
    			}
    				
    		}
    		file.close();
    }
    catch (FileNotFoundException fnfe) {
      System.out.println("File not found.");
    }
    catch (IOException ioe) {
      new RuntimeException("I/O Error.");
    } 
  }
	
	public static void saveToFile(String fileName, List<Client> orderList ){
		try {
		        BufferedWriter file = new BufferedWriter(new FileWriter("files/" + fileName + ".dat"));
		        String line = orderList.toString();
		        file.write(line);
		        file.close();
			}
		catch (FileNotFoundException fnfe) {
		      System.out.println("The file could not be saved.");
		    }
		catch (IOException ioe) {
		      new RuntimeException("I/O Error.");
		}
	  }
	
	public static void addNewClient(List<Client> clients, String dni, String name, String user, 
			String password, double balance, boolean vip) {
		clients.add(new Client(dni, name, user, password, balance,vip));
		saveClient("customers", clients); //update file with the new user
	}
	
	public static void saveClient(String fileName, List<Client> clients ){
		try {
		        BufferedWriter file = new BufferedWriter(new FileWriter("files/" + fileName + ".dat"));
		        for (Client c : clients) {
		        	String line = c.getDni()+"@"+c.getName()+"@"+c.getUser()+"@"+c.getPassword()+"@"+c.getBalance()+"\n";
		        	file.append(line);
		        }
		        file.close();
			}

		catch (FileNotFoundException fnfe) {
		      System.out.println("The file could not be saved.");
		    }
		catch (IOException ioe) {
		      new RuntimeException("I/O Error.");
		}
	  }
	
	public static void saveClientBalance(String fileName, List<Client> clients, Client client, double newBalance) {
		try {
	        BufferedWriter file = new BufferedWriter(new FileWriter("files/" + fileName + ".dat"));
	        for (Client c : clients) {
	        	if (c.getUser().equals(client.getUser())) {
	        		c.setBalance(newBalance);
	        		String line = c.getDni()+"@"+c.getName()+"@"+c.getUser()+"@"+c.getPassword()+"@"+c.getBalance()+"\n";
		        	file.append(line);
	        	}else {
	        		String line = c.getDni()+"@"+c.getName()+"@"+c.getUser()+"@"+c.getPassword()+"@"+c.getBalance()+"\n";
		        	file.append(line);
	        	}
	        }
	        file.close();
		}

	catch (FileNotFoundException fnfe) {
	      System.out.println("The file could not be saved.");
	    }
	catch (IOException ioe) {
	      new RuntimeException("I/O Error.");
	}
	}
	
	/**
	 * Searches for an user with a specific dni or user name 
	 * @param clients the list with users 
	 * @param userName the name of the user to be searched
	 * @return true if there is already an user with that user name 
	 */
	public static boolean searchClientByUser(String userName, List<Client> clients) {
		for (Client c : clients) 
			if (c.getUser().equals(userName))
				return true;
		return false;
	}
	
	/**
	 * Searches for an user with a specific dni or user name 
	 * @param clients the list with users 
	 * @param dni the dni 
	 * @return true if there is already an user with that dni
	 */
	public static boolean searchClientByDni(String dni, List<Client> clients) {
		for (Client c : clients) 
			if (c.getDni().equals(dni))
				return true;
		return false;
	}
	
}
