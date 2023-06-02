package domain;

public class MemberVO {

	private String id;
	private String password;
	private String email;
	private int age;
	private String reg_date;
	private String last_login;
	
	public MemberVO() {}
	
	//login : id, password
	public MemberVO(String id, String passowrd) {
		this.id=id;
		this.password=passowrd;
	}
	
	//join : id, password, email, age
	public MemberVO(String id, String password, String email, int age) {
		this(id, password);
		this.age=age;
		this.email=email;
	}
		
	//list : id, email, age, reg_date, last_login
	public MemberVO(String id, String email, int age, String reg_date, String last_login) {
		this.id=id;
		this.email=email;
		this.age=age;
		this.reg_date=reg_date;
		this.last_login=last_login;
	}
		
	//modify : password, email, age
	public MemberVO(String password, String email, int age) {
		this.password=password;
		this.email=email;
		this.age=age;
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getLast_login() {
		return last_login;
	}

	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
