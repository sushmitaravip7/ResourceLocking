package project1;

import java.util.ArrayList;

public class TransactionTable {

	String transactionID,transactionState;
	int transactionTS;
	ArrayList<String> itemsLocked;
	
	
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getTransactionState() {
		return transactionState;
	}
	public void setTransactionState(String transactionState) {
		this.transactionState = transactionState;
	}
	public int getTransactionTS() {
		return transactionTS;
	}
	public void setTransactionTS(int transactionTS) {
		this.transactionTS = transactionTS;
	}
	public ArrayList<String> getItemsLocked() {
		return itemsLocked;
	}
	public void setItemsLocked(ArrayList<String> itemsLocked) {
		this.itemsLocked = itemsLocked;
	}
}
