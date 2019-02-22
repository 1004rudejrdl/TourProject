package model;

public class inforTour {	
	private String tourID;
	private String tourName;
	private String tourAddr;
	private String tourWay;
	private String tourExplain;
	private String tourImage;
	
	public inforTour(String tourID, String tourName, String tourAddr, String tourWay, String tourExplain,
			String tourImage) {
		super();
		this.tourID = tourID;
		this.tourName = tourName;
		this.tourAddr = tourAddr;
		this.tourWay = tourWay;
		this.tourExplain = tourExplain;
		this.tourImage = tourImage;
	}

	public String getTourID() {
		return tourID;
	}

	public void setTourID(String tourID) {
		this.tourID = tourID;
	}

	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	public String getTourAddr() {
		return tourAddr;
	}

	public void setTourAddr(String tourAddr) {
		this.tourAddr = tourAddr;
	}

	public String getTourWay() {
		return tourWay;
	}

	public void setTourWay(String tourWay) {
		this.tourWay = tourWay;
	}

	public String getTourExplain() {
		return tourExplain;
	}

	public void setTourExplain(String tourExplain) {
		this.tourExplain = tourExplain;
	}

	public String getTourImage() {
		return tourImage;
	}

	public void setTourImage(String tourImage) {
		this.tourImage = tourImage;
	}

	
}
