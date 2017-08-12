package com.cts.olympicpojo;

public class OlympicUserPojo {
private String firstName;
private String lastName;
private String userName;
private String datePicker;
private String email;
private String phoneNumber;
private String password;
private String role;
private int status;
private int display;
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getDatePicker() {
	return datePicker;
}
public void setDatePicker(String datePicker) {
	this.datePicker = datePicker;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public OlympicUserPojo(String firstName, String lastName, String userName,
		String datePicker, String email, String phoneNumber, String password,
		String role, int status,int display) {

	this.firstName = firstName;
	this.lastName = lastName;
	this.userName = userName;
	this.datePicker = datePicker;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.password = password;
	this.role = role;
	this.status = status;
	this.display=display;
}
 public OlympicUserPojo(String firstName, String lastName, String userName,
			String datePicker, String email, String phoneNumber, String password) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.datePicker = datePicker;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.role = "user";
		this.status =0 ;
		this.display=1;
	}
public int getDisplay() {
	return display;
}
public void setDisplay(int display) {
	this.display = display;
}
}
