package com.jio.ngo.threat.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class AuthenticationRequest implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private List<Object[]> jsonData;

}
