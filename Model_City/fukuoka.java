package model;

public class fukuoka {
	private String fukuokaTourID;
	private String fukuokaTourName;
	private String fukuokaTourClass;
	private int fukuokaTourCharge;
	
	public fukuoka(String fukuokaTourID, String fukuokaTourName, String fukuokaTourClass, int fukuokaTourCharge) {
		super();
		this.fukuokaTourID = fukuokaTourID;
		this.fukuokaTourName = fukuokaTourName;
		this.fukuokaTourClass = fukuokaTourClass;
		this.fukuokaTourCharge = fukuokaTourCharge;
	}

	public String getFukuokaTourID() {
		return fukuokaTourID;
	}

	public void setFukuokaTourID(String fukuokaTourID) {
		this.fukuokaTourID = fukuokaTourID;
	}

	public String getFukuokaTourName() {
		return fukuokaTourName;
	}

	public void setFukuokaTourName(String fukuokaTourName) {
		this.fukuokaTourName = fukuokaTourName;
	}

	public String getFukuokaTourClass() {
		return fukuokaTourClass;
	}

	public void setFukuokaTourClass(String fukuokaTourClass) {
		this.fukuokaTourClass = fukuokaTourClass;
	}

	public int getFukuokaTourCharge() {
		return fukuokaTourCharge;
	}

	public void setFukuokaTourCharge(int fukuokaTourCharge) {
		this.fukuokaTourCharge = fukuokaTourCharge;
	}
	
	
}
