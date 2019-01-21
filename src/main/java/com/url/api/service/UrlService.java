package com.url.api.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.api.model.URL;
import com.url.api.persistence.UrlRepository;

/**
 * <p> This is a service class to code business logic in order to manipulate and created {@link URL} objects </p>
 * 
 * 
 * @author Fernanda
 *
 */
@Service
public class UrlService {

	/* 
	 * Array for mapping the Alphabethic characters in order to build the short url
	 */
	static String[][] alpha = new String [][] {{"A","0"},
												{"B","1"},{"C","2"},{"D","3"},{"E","4"},
												{"F","5"},{"G","6"},{"H","7"},{"I","8"},
												{"J","9"},{"K","10"},{"L","11"},{"M","12"},
												{"N","13"},{"O","14"},{"P","15"},
												{"Q","16"},{"R","17"},{"S","18"},{"T","19"},
												{"U","20"},{"V","21"},{"W","22"},
												{"X","23"},{"Y","24"},{"Z","25"},
												{"a","26"},
												{"b","27"},{"c","28"},{"d","29"},{"e","30"},
												{"f","31"},{"g","32"},{"h","33"},{"i","34"},
												{"j","35"},{"k","36"},{"l","37"},{"m","38"},
												{"n","39"},{"o","40"},{"p","41"},
												{"q","42"},{"r","43"},{"s","44"},{"t","45"},
												{"u","46"},{"v","47"},{"w","48"},
												{"x","49"},{"y","50"},{"z","51"}}; 
				
	/* url prefix */ 											
	private static final String HTTP = "http://short.url/"; 
	
	
	@Autowired
	UrlRepository repo;

	/**
	 * <p> Method wich lists all URL documents on the database </p>
	 * @return
	 */
	public List<URL> listar(){
		return repo.findAll();
	} 
												
	/**
	 * <p> Method wich creates a short url by an algorith that creates the possible combinations
	 * of six positions which could be alphabethic characters or numbers. In order to get one of
	 * these combinations, a random number is generated and used a  key to get the combination from
	 * the Hashmap <code> resultado </code>. After, based on the array <code>alpha</code> we know
	 * wich char to turn into a number or not. In the final stage, all these characters are put together
	 * to form the unique short url. 
	 * 
	 * @return URL object with the short url.
	 */
	public URL generateNewURL(String longUrl) {

		int[] numbersForCombination = new int[6];
		int[] combination = new int[6];
		HashMap<Integer, Integer[]> resultHashMap = new HashMap<>();
		
		for (int i = 0; i < numbersForCombination.length; i++) {
			numbersForCombination[i] = generateRandomInt(56);
		}
		// all possible combinations
		int totalOfCombinations = 0;
		for (int i = 0; i < numbersForCombination.length; i++) {
			combination[0] = numbersForCombination[i];
			for (int j = 0; j < numbersForCombination.length; j++) {
				combination[1] = numbersForCombination[j];
				for (int k = 0; k < numbersForCombination.length; k++) {
					combination[2] = numbersForCombination[k];
					for (int y = 0; y < numbersForCombination.length; y++) {
						combination[3] = numbersForCombination[y];
						for (int x = 0; x < numbersForCombination.length; x++) {
							combination[4] = numbersForCombination[x];
							for (int n = 0; n < numbersForCombination.length; n++) {
								totalOfCombinations++;
								combination[5] = numbersForCombination[n];
								System.out.println("Combination " + totalOfCombinations + ": " + combination[0] + " - "
										+ combination[1] + " - " + combination[2] + " - " + combination[3] + " - "
										+ combination[4] + " - " + combination[5]);
								resultHashMap.put(totalOfCombinations, new Integer[] { combination[0], combination[1],
										combination[2], combination[3], combination[4], combination[5] });
							}

						}
					}
				}
			}
		}

		int chosenNumber = generateRandomInt(totalOfCombinations);

		System.out.println("+ Total of possible combinations :: " + totalOfCombinations);
		System.out.println("+ Chosen Number :: " + chosenNumber);
		System.out.println("+ Dados guardados :: " + resultHashMap.get(chosenNumber)[0]);
		System.out.println("+ Dados guardados :: " + resultHashMap.get(chosenNumber)[1]);
		System.out.println("+ Dados guardados :: " + resultHashMap.get(chosenNumber)[2]);
		System.out.println("+ Dados guardados :: " + resultHashMap.get(chosenNumber)[3]);
		System.out.println("+ Dados guardados :: " + resultHashMap.get(chosenNumber)[4]);
		System.out.println("+ Dados guardados :: " + resultHashMap.get(chosenNumber)[5]);

		StringBuffer shorUrlStr = new StringBuffer();
		
		shorUrlStr.append(HTTP);
		
		shorUrlStr.append(genereteOutput(resultHashMap.get(chosenNumber)[0]));
		shorUrlStr.append(genereteOutput(resultHashMap.get(chosenNumber)[1]));
		shorUrlStr.append(genereteOutput(resultHashMap.get(chosenNumber)[2]));
		shorUrlStr.append(genereteOutput(resultHashMap.get(chosenNumber)[3]));
		shorUrlStr.append(genereteOutput(resultHashMap.get(chosenNumber)[4]));
		shorUrlStr.append(genereteOutput(resultHashMap.get(chosenNumber)[5]));


		System.out.println(" Final : " + shorUrlStr);
		
		URL urlRetorno = saveUrl(shorUrlStr.toString(),longUrl,new Date());
		
		return urlRetorno;
	}

	/**
	 * <p> Method wich persists in the MongoDB the URL document</p>
	 * @param shortUrl the short url
	 * @param longUrl the long url 
	 * @param cratedAt the date the url is being created
	 * @return URL object filled with data
	 */
	public URL saveUrl(String shortUrl, String longUrl, Date cratedAt) {
		URL url = new URL();
		url.setShortForm(shortUrl);
		url.setLongForm(longUrl);
		url.setCreatedAt(cratedAt);

		URL  urlRetorno =  repo.save(url);
		return urlRetorno;
	}

	//------------------------------- private methods --------------------------------------------------------
	
	/**
	 * <p> Method with determines if the char to compose the short url is a  number or a letter.</p>
	 * @param number the number from the combination
	 * @return The number or letter in string format
	 */
	private String genereteOutput(int number) {
		return determineIfAlphabeticChar(number) == true ? String.valueOf(retrieveChar(number))
				: String.valueOf(number);
	}

	/**
	 * <p> Method wich generates a random integer number based on the max number <code>maxNumber</code>
	 * @param maxNumber the max number out of a random number
	 * @return the number
	 */
	private int generateRandomInt(int maxNumber) {
		Random rand = new Random();
		int value = rand.nextInt(maxNumber);
		++value;
		return value;
	}

	/**
	 * <p> Method that determines if a number generated from the combination algorith is whithin the range 
	 * of the <code>alpha</code> array so that it means this number will be converted in the corresponding 
	 * letter of the <code>alpha</code> array.
	 * @param index number generated from the combination algorithm
	 * @return true if the number has a corresponding letter in the <code>alpha</code> array 
	 */
	private boolean determineIfAlphabeticChar(int index) {
		return index >= 10 && index <= 51;
	}

	/**
	 * <p>Method that retrieves the corresponding letter from the <code>alpha</code> array. This correspondence
	 * is bound by the <code>index</code> variable which indicates the number from the combination algorithm  
	 * that is related to the letter inside the <code>alpha</code>array. 
	 * @param index number generated from the combination algorithm  
	 * @return letter corresponding to informed <code>index</code> if it is within the range of the <code>alpha</code>array. 
	 */
	private String retrieveChar(int index) {
		for (int i = 0; i < alpha.length; ++i) {
			if (alpha[i][1].equals(String.valueOf(index))) {
				return alpha[index][0];
			}
		}

		return "";
	}
}
