package org.yinet.dao.clazz;

import java.util.Date;
public class User {
 private Integer id;
 private String username;
 private String password;
 private Date time;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public Date getTime() {
	return time;
}
public void setTime(Date time) {
	this.time = time;
}
@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", password=" + password + ", time=" + time + "]";
}

}
