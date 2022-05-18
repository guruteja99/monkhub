package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Slots;
import com.example.demo.repository.SlotsRepository;

@RestController
public class ParkingController {

	Map<String, String> map = new HashMap<String, String>();

	@Autowired
	SlotsRepository repository;

	@GetMapping("parkAcar/{carNumber}")
	public String parkCar(@PathVariable String carNumber) {
		if (repository.findAll().size() == 5) { // As the available slots are 5 , we use 5

			Slots slot = null;
			for (int i = 0; i < 5; i++) {
				if (null == map.get("SL00"+i)) {
					slot = new Slots(carNumber, "SL00" + i, LocalDateTime.now());
					break;
				}
			}
			Slots resource = repository.save(slot);
			if (resource.getSlotNumber() != null) {
				map.put(resource.getSlotNumber(), resource.getCarNumber())
				return resource.getSlotNumber();
			}
		}
		return "All slots are full";

	}

	@GetMapping("unparkAcar/{slotNumber}")
	public String unparkCar(@PathVariable String slotNumber) {
		if (map.get(slotNumber) == null) {
			if (repository.getBySlotNumber(slotNumber).get() != null) {
				repository.deleteBySlotNumber(slotNumber);
				map.put(slotNumber, null);
			}

		}
		return slotNumber;
	}

	@GetMapping("getCarDetails")
	public Slots getCarDetailsbySlot(@RequestParam("slotNum") String slotNumber) throws Exception {
		if (repository.getBySlotNumber(slotNumber).get() != null) {
			return repository.getBySlotNumber(slotNumber).get();
		} else {
			throw new Exception("Slot is empty");
		}

	}

	@GetMapping("getCarDetails")
	public Slots getCarDetailsbyCar(@RequestParam("carNum") String carNumber) throws Exception {
		if (repository.getBySlotNumber(carNumber).get() != null) {
			return repository.getBySlotNumber(carNumber).get();
		} else {
			throw new Exception("car is not parked on our slots");
		}
	}
}
