package com.kh.springdi.owner;

import com.kh.springdi.pet.Pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Owner {
	private String name;
	private int age;
	private String gender;
	private Pet pet;
	
	
}
