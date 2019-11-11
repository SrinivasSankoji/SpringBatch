package com.example.demo.batch.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OneLakhDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int columnOne;
	private int columnTwo;
    private int columnThree;

}
