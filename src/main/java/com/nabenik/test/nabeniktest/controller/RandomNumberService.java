package com.nabenik.test.nabeniktest.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;

/**
 * Simple CDI service aimed for random numbers generation
 * @author tuxtor
 *
 */
@RequestScoped
public class RandomNumberService {

	/**
	 * Returns a random set of numbers greater than 50
	 * @return List of random numbers
	 */
	public List<Integer> generateNumbers(){
		List<Integer> numbersList = new ArrayList<>();
		Random randomGenerator = new Random();
		for (int i = 0; i < 10000; i++) {
			numbersList.add(randomGenerator.nextInt(100));
		}
		Collections.sort(numbersList);
		
		return numbersList.stream()
				.filter(x -> x > 50)
				.collect(Collectors.toList());
	}
	/**
	 * Returns a random set of numbers lower than 50
	 * @return List of random numbers
	 */
	public List<Integer> generateNumbers2(){
		List<Integer> numbersList = new ArrayList<>();
		Random randomGenerator = new Random();
		for (int i = 0; i < 10000; i++) {
			numbersList.add(randomGenerator.nextInt(100));
		}
		Collections.sort(numbersList);
		return numbersList.stream()
				.filter(x -> x < 50)
				.collect(Collectors.toList());
	}
}
