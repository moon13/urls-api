package com.url.api.resources;

import java.util.Random;

public class GenerateURL {

	public static void main(String[] args) {
		
		
		Random rand = new Random(); 
		int value = rand.nextInt(9);
		++value;
		System.out.println(value);

	}

}
