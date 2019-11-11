package com.example.demo.batch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Builder;
import lombok.Data;

@Data
@Entity(name = "ONELAKH")
@Builder
public class OneLakh implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LAKH_SEQ")
    @SequenceGenerator(sequenceName = "ONELAKH_SEQ", allocationSize = 1, name = "LAKH_SEQ")
    private Long onelakh_Id;
	
	@Column(name = "COLUMN_ONE")
    private int columnOne;
    
	@Column(name = "COLUMN_TWO")
    private int columnTwo;
    
	@Column(name = "COLUMN_THREE")
    private int columnThree;
    
}
