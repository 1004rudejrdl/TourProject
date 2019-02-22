package model;

public class memberShip {
	private String memberID;
	private String memberPassWord;
	private String memberName;
	private String memberGender;
	private String memberPhone;
	private String memberEmail;
	
	public memberShip() {
		super();
	}
	
	public memberShip(String memberID) {
		this.memberID = memberID;
	}
	
	public memberShip(String memberID, String memberPassWord, String memberName, String memberGender,
			String memberPhone, String memberEmail) {
		super();
		this.memberID = memberID;
		this.memberPassWord = memberPassWord;
		this.memberName = memberName;
		this.memberGender = memberGender;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getMemberPassWord() {
		return memberPassWord;
	}

	public void setMemberPassWord(String memberPassWord) {
		this.memberPassWord = memberPassWord;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	
	

}
