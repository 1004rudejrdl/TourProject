package model;

public class osaka {
	private String osakaTourID;
	private String osakaTourName;
	private String osakaTourClass;
	private int osakaTourCharge;
	
	public osaka(String osakaTourID, String osakaTourName, String osakaTourClass, int osakaTourCharge) {
		super();
		this.osakaTourID = osakaTourID;
		this.osakaTourName = osakaTourName;
		this.osakaTourClass = osakaTourClass;
		this.osakaTourCharge = osakaTourCharge;
	}

	public String getOsakaTourID() {
		return osakaTourID;
	}

	public void setOsakaTourID(String osakaTourID) {
		this.osakaTourID = osakaTourID;
	}

	public String getOsakaTourName() {
		return osakaTourName;
	}

	public void setOsakaTourName(String osakaTourName) {
		this.osakaTourName = osakaTourName;
	}

	public String getOsakaTourClass() {
		return osakaTourClass;
	}

	public void setOsakaTourClass(String osakaTourClass) {
		this.osakaTourClass = osakaTourClass;
	}

	public int getOsakaTourCharge() {
		return osakaTourCharge;
	}

	public void setOsakaTourCharge(int osakaTourCharge) {
		this.osakaTourCharge = osakaTourCharge;
	}
	
	

	
}
