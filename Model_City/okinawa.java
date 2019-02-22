package model;

public class okinawa {
	private String okinawaTourID;
	private String okinawaTourName;
	private String okinawaTourClass;
	private int okinawaTourCharge;
	
	public okinawa(String okinawaTourID, String okinawaTourName, String okinawaTourClass, int okinawaTourCharge) {
		super();
		this.okinawaTourID = okinawaTourID;
		this.okinawaTourName = okinawaTourName;
		this.okinawaTourClass = okinawaTourClass;
		this.okinawaTourCharge = okinawaTourCharge;
	}

	public String getOkinawaTourID() {
		return okinawaTourID;
	}

	public void setOkinawaTourID(String okinawaTourID) {
		this.okinawaTourID = okinawaTourID;
	}

	public String getOkinawaTourName() {
		return okinawaTourName;
	}

	public void setOkinawaTourName(String okinawaTourName) {
		this.okinawaTourName = okinawaTourName;
	}

	public String getOkinawaTourClass() {
		return okinawaTourClass;
	}

	public void setOkinawaTourClass(String okinawaTourClass) {
		this.okinawaTourClass = okinawaTourClass;
	}

	public int getOkinawaTourCharge() {
		return okinawaTourCharge;
	}

	public void setOkinawaTourCharge(int okinawaTourCharge) {
		this.okinawaTourCharge = okinawaTourCharge;
	}
	
	
}
