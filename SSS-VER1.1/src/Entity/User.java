package Entity;

public class User {
    private String userId;
    private String name;
    private String userEmail;
    private String credit; //0没事   1 黑名单 需交钱 
    private String borrow;//0 借过，1 没借
	public User(String userId, String name, String userEmail, String credit, String borrow) {
		super();
		this.userId = userId;
		this.name = name;
		this.userEmail = userEmail;
		this.credit = credit;
		this.borrow = borrow;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getBorrow() {
		return borrow;
	}
	public void setBorrow(String borrow) {
		this.borrow = borrow;
	}
    
}
