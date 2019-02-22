package model;

public class sapporo {
	private String sapporoTourID;
	private String sapporoTourName;
	private String sapporoTourClass;
	private int sapporoTourCharge;
	
	public sapporo(String sapporoTourID, String sapporoTourName, String sapporoTourClass, int sapporoTourCharge) {
		super();
		this.sapporoTourID = sapporoTourID;
		this.sapporoTourName = sapporoTourName;
		this.sapporoTourClass = sapporoTourClass;
		this.sapporoTourCharge = sapporoTourCharge;
	}

	public String getSapporoTourID() {
		return sapporoTourID;
	}

	public void setSapporoTourID(String sapporoTourID) {
		this.sapporoTourID = sapporoTourID;
	}

	public String getSapporoTourName() {
		return sapporoTourName;
	}

	public void setSapporoTourName(String sapporoTourName) {
		this.sapporoTourName = sapporoTourName;
	}

	public String getSapporoTourClass() {
		return sapporoTourClass;
	}

	public void setSapporoTourClass(String sapporoTourClass) {
		this.sapporoTourClass = sapporoTourClass;
	}

	public int getSapporoTourCharge() {
		return sapporoTourCharge;
	}

	public void setSapporoTourCharge(int sapporoTourCharge) {
		this.sapporoTourCharge = sapporoTourCharge;
	}
	
	
	
	
	
	
}
