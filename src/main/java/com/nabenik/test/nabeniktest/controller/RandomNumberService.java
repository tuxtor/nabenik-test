package com.nabenik.test.nabeniktest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;

/**
 * Simple CDI service aimed for random numbers generation
 * @author tuxtor
 */
@RequestScoped
public class RandomNumberService {

	/**
	 * Returns a random set of numbers
	 * @return List of random numbers
	 */
	public List<Integer> generateNumbers(){
		List<Integer> numbersList = new ArrayList<>();
		Random randomGenerator = new Random();
		for (int i = 0; i < 10000; i++) {
			numbersList.add(randomGenerator.nextInt(100));
		}
		return numbersList;
	}

}
