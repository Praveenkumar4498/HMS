package com.cg.hms.exceptions;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {
	private Date date;
	private String message;
	private String statusCode;
	
	

}
