package com.example.zy.emaildemo.utils;
/*
 * Copyright (c)2016 thatluck
 * 版权所有，违者必究
 */

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
public class MyAuthenticator extends Authenticator {
	String userName = null;
	String password = null;
	public MyAuthenticator() {
	}
	public MyAuthenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}
