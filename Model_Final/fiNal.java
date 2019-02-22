package model;

public class fiNal {
	private String finalTourID;
	private String finalTourName;
	private String finalTourClass;
	private int finalTourCharge;
	
	public fiNal(String finalTourID, String finalTourName, String finalTourClass, int finalTourCharge) {
		super();
		this.finalTourID = finalTourID;
		this.finalTourName = finalTourName;
		this.finalTourClass = finalTourClass;
		this.finalTourCharge = finalTourCharge;
	}

	public String getFinalTourID() {
		return finalTourID;
	}

	public void setFinalTourID(String finalTourID) {
		this.finalTourID = finalTourID;
	}

	public String getFinalTourName() {
		return finalTourName;
	}

	public void setFinalTourName(String finalTourName) {
		this.finalTourName = finalTourName;
	}

	public String getFinalTourClass() {
		return finalTourClass;
	}

	public void setFinalTourClass(String finalTourClass) {
		this.finalTourClass = finalTourClass;
	}

	public int getFinalTourCharge() {
		return finalTourCharge;
	}

	public void setFinalTourCharge(int finalTourCharge) {
		this.finalTourCharge = finalTourCharge;
	}
	
	

}
