package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.masai.enums.userType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class CurrentLoginSession {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
		
	private String userMobile;
	
	private String userKey;

	@Enumerated(EnumType.STRING)
	private userType UserType;
}
