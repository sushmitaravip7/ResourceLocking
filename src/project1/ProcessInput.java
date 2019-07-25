package project1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import jdk.jfr.events.FileWriteEvent;
import project1.TransactionTable;
import project1.LockTable;

public class ProcessInput {
	final String ABORTED 	=	"0";
	final String ACTIVE 	= 	"1";
	final String BLOCKED 	= 	"2";
	//path of the file which will contain waiting transactions
	File waitingTransactions  	= new File("/src/inputs/waitingTransactions.txt");	
	//time-stamp for each new transaction
	int transactionTimestamp	=0;
	//list of all transactions
	ArrayList<TransactionTable> transactions = new ArrayList<TransactionTable>();
	ArrayList<LockTable> lockTable = new ArrayList<LockTable>();

	public void main(String[] args) {
		//	For reading input file
		File inputFile =  new File("/src/inputs/input1.txt");							//path of the input file
		//	TODO:Empty waiting transactions file
		readFile(inputFile);
	}

	private void readFile(File file) {
		String buff;
		BufferedReader iStream;
		try {
			iStream = new BufferedReader(new FileReader(file));

			System.out.println("Parsing Transactions");

			while ((buff = iStream.readLine()) != null) {
				System.out.println("Processing Operation: "+buff+"\n");
				switch(buff.charAt(0)) {
				case 'b':
					// 	Begin Transaction
					//	Increment Time Stamp value
					transactionTimestamp++;
					//	create new transaction entry
					TransactionTable newT = new TransactionTable();
					newT.setTransactionID("T"+buff.charAt(1));					// transaction ID
					newT.setTransactionState(ACTIVE);							// Status is active
					newT.setTransactionTS(transactionTimestamp);				// Set transaction timestamp
					transactions.add(newT);										// Add Transaction to the list
					System.out.println("Transaction "+newT.getTransactionID()+" started");
					break;
				case 'r':
					//	Read Transaction
					String transaction = "T"+buff.charAt(1);
					for(TransactionTable t:transactions) {
						if(t.getTransactionID().equalsIgnoreCase(transaction)) {
							//	Aborted Transaction
							if(t.getTransactionState().equalsIgnoreCase(ABORTED)) {
								System.out.println("Transaction "+transaction+" was ABORTED");
							}
							//	Blocked Transaction
							else if(t.getTransactionState().equalsIgnoreCase(BLOCKED)) {
								//	append transaction to waiting transactions file
								try {
									FileWriter fr = new FileWriter(waitingTransactions,true);
									BufferedWriter br = new BufferedWriter(fr);
									br.newLine();
									br.append(buff);
									br.close();
								}catch(Exception e) {
									e.printStackTrace();
								}
							}
							//	Active Transaction
							else if(t.getTransactionState().equalsIgnoreCase(ACTIVE)) {
								//	check for entry in the lock table for the resource.
								char itemName = buff.charAt(3);
								for (LockTable lock: lockTable) {
									if(lock.getResourceName()==itemName) {
//										check if item has conflicting lock.
										if(lock.getLockType().equalsIgnoreCase("WL")) {
											
										}else if(lock.getLockType().equalsIgnoreCase("RL")) {
											
										}
									}
								}
							}

						}
					}
					break;
				case 'w':
					// Write Transaction
					break;
				case 'e':
					// Commit and End Transaction
					break;
				default: System.out.println("Invalid Operation: "+buff); break;
				}

			}
			// close the input file
			iStream.close();

		} catch (Exception e) {
			System.out.println("Failed to open file");
			e.printStackTrace();
		}
	}

}
