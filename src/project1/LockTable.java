package project1;

import java.util.ArrayList;

public class LockTable {

	String lockType;
	char resourceName;
	ArrayList<String> lockingTransactions, waitingTransactions;
	
	public LockTable() {
		lockingTransactions = new ArrayList<>();
		waitingTransactions = new ArrayList<>();
		lockType 			= "";
	}

	public char getResourceName() {
		return resourceName;
	}

	public void setResourceName(char resourceName) {
		this.resourceName = resourceName;
	}

	public String getLockType() {
		return lockType;
	}

	public void setLockType(String lockType) {
		this.lockType = lockType;
	}

	public ArrayList<String> getLockingTransactions() {
		return lockingTransactions;
	}

	public void setLockingTransactions(ArrayList<String> lockingTransactions) {
		this.lockingTransactions = lockingTransactions;
	}

	public ArrayList<String> getWaitingTransactions() {
		return waitingTransactions;
	}

	public void setWaitingTransactions(ArrayList<String> waitingTransactions) {
		this.waitingTransactions = waitingTransactions;
	}
}
