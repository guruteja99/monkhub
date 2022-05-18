package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Slots {

	@Id
	@GeneratedValue
	private Long id;
	private String carNumber;
	private String slotNumber;
	private LocalDateTime creationTimeStamp;
	public Slots(String carNumber, String slotNumber, LocalDateTime creationTimeStamp) {
		super();
		this.carNumber = carNumber;
		this.slotNumber = slotNumber;
		this.creationTimeStamp = creationTimeStamp;
	}
	
	
}
