package com.cg.hms.controllers;

import java.util.Date;


public class SaveResponse {
	private Date date;
	private String message;
	private String statusCode;
	public SaveResponse(Date date, String message, String statusCode) {
		super();
		this.date = date;
		this.message = message;
		this.statusCode = statusCode;
	}
	

}
