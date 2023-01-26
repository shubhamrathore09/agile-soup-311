package com.masai.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingId;

	@JsonIgnore
	private LocalDate bookingDate;
	private String BookingMobileNumber;
	@JsonIgnore
	private String userId;
	
//	@OneToOne(cascade = CascadeType.ALL)
	private String BusNumber;
	private Integer quantity;
	private LocalDate doj;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private paymentDTO payment;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private PackageModule packM;
}
