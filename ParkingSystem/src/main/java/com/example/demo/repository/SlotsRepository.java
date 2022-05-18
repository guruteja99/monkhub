package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Slots;

public interface SlotsRepository extends JpaRepository<Slots, Long> {

	Optional<Slots> getBySlotNumber(String slotNumber);
	
	Optional<Slots> getByCarNumber(String carNumber);
	
	void deleteBySlotNumber(String slotNumber);
}
