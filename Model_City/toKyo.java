package model;

public class toKyo {
	private String tokyoTourID;
	private String tokyoTourName;
	private String tokyoTourClass;
	private int tokyoTourCharge;
	
	public toKyo(String tokyoTourID, String tokyoTourName, String tokyoTourClass, int tokyoTourCharge) {
		super();
		this.tokyoTourID = tokyoTourID;
		this.tokyoTourName = tokyoTourName;
		this.tokyoTourClass = tokyoTourClass;
		this.tokyoTourCharge = tokyoTourCharge;
	}

	public String getTokyoTourID() {
		return tokyoTourID;
	}

	public void setTokyoTourID(String tokyoTourID) {
		this.tokyoTourID = tokyoTourID;
	}

	public String getTokyoTourName() {
		return tokyoTourName;
	}

	public void setTokyoTourName(String tokyoTourName) {
		this.tokyoTourName = tokyoTourName;
	}

	public String getTokyoTourClass() {
		return tokyoTourClass;
	}

	public void setTokyoTourClass(String tokyoTourClass) {
		this.tokyoTourClass = tokyoTourClass;
	}

	public int getTokyoTourCharge() {
		return tokyoTourCharge;
	}

	public void setTokyoTourCharge(int tokyoTourCharge) {
		this.tokyoTourCharge = tokyoTourCharge;
	}
	
	
}
