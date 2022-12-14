package com.cafe24.shoppingmall.frontend.vo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVo {
	private Long no;

	@Pattern(regexp = "(^[a-zA-Z]{1}[a-zA-Z0-9_]{4,13}$)", message = "잘못된 아이디 형식입니다.")
	private String id;

	private int passwordQuestion;

	@Length(min = 2, message = "답변은 2글자 이상을 입력해주세요.")
	private String passwordAnswer;

//	@ValidPhoneNumber
	@Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "잘못된 전화번호 형식입니다.")
	private String phoneNumber;

	@NotEmpty
	@Length(min = 2, max = 8)
	private String name;

	@Email(message = "잘못된 이메일 형식입니다.")
	@NotEmpty(message = "이메일을 입력해주세요.")
	private String email;

	//
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@!*#?])[A-Za-z\\d@!*#?]{8,}$", message = "잘못된 비밀번호 형식입니다.")
	@NotEmpty
	private String password;

	@NotEmpty
	private String gender;

	private String role = "ROLE_USER";

	private String birthDate;

	private String point;

	public UserVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserVo(Long no, String id, int passwordQuestion, String passwordAnswer, String phoneNumber, String name,
			String email, String password, String gender, String role, String birthDate, String point) {
		super();
		this.no = no;
		this.id = id;
		this.passwordQuestion = passwordQuestion;
		this.passwordAnswer = passwordAnswer;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.role = role;
		this.birthDate = birthDate;
		this.point = point;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPasswordQuestion() {
		return passwordQuestion;
	}

	public void setPasswordQuestion(int passwordQuestion) {
		this.passwordQuestion = passwordQuestion;
	}

	public String getPasswordAnswer() {
		return passwordAnswer;
	}

	public void setPasswordAnswer(String passwordAnswer) {
		this.passwordAnswer = passwordAnswer;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getBirthDate() { 
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "UserVo [no=" + no + ", id=" + id + ", passwordQuestion=" + passwordQuestion + ", passwordAnswer="
				+ passwordAnswer + ", phoneNumber=" + phoneNumber + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", gender=" + gender + ", role=" + role + ", birthDate=" + birthDate
				+ ", point=" + point + "]";
	}

}
