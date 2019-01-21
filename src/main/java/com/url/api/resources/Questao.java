package com.url.api.resources;

import java.util.HashMap;
import java.util.Random;

public class Questao
{
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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] numeros = new int[6];
		int[] combinacao = new int[6];
		HashMap<Integer, Integer[]> resultado = new HashMap<>();
		//Pega os numero informed e adicona no array
		for(int i=0; i<numeros.length; i++){
			numeros[i] = generateRandomInt(56);
		}
		//Faz todas as combinacoes possiveis
		int indiceCombinacao = 0;
		for(int i=0; i<numeros.length; i++){
			combinacao[0] = numeros[i];
			for(int j=0; j<numeros.length; j++){
				combinacao[1] = numeros[j];
				for(int k=0; k<numeros.length; k++){
					combinacao[2] = numeros[k];
					for(int y=0; y<numeros.length; y++){
						combinacao[3] = numeros[y];
					    for(int x=0; x<numeros.length; x++){
							combinacao[4] = numeros[x];	
						    for(int n=0; n<numeros.length; n++){
								indiceCombinacao++;
								combinacao[5] = numeros[n];
							    System.out.println("Combinação " + indiceCombinacao + ": " 
						    			+ combinacao[0] + " - " + combinacao[1]+ " - " + combinacao[2] +" - "+combinacao[3]
						    					+" - "+combinacao[4]+" - "+combinacao[5]);
								resultado.put(indiceCombinacao, new Integer[] {combinacao[0],combinacao[1],combinacao[2],combinacao[3], combinacao[4],combinacao[5]});
						    }
							
					    }	
					}
				}
			}
		}
		
		int sorteio = generateRandomInt(indiceCombinacao);
		
		System.out.println("+ Total de combinações possíveis (Arranjos) :: "+indiceCombinacao);
		System.out.println("+ Numero Sorteio :: "+sorteio);
		System.out.println("+ Dados guardados :: "+resultado.get(sorteio)[0]);
		System.out.println("+ Dados guardados :: "+resultado.get(sorteio)[1]);
		System.out.println("+ Dados guardados :: "+resultado.get(sorteio)[2]);
		System.out.println("+ Dados guardados :: "+resultado.get(sorteio)[3]);
		System.out.println("+ Dados guardados :: "+resultado.get(sorteio)[4]);
		System.out.println("+ Dados guardados :: "+resultado.get(sorteio)[5]);
		
		
		StringBuffer str = new StringBuffer();
		
		
		str.append(genereteOutput(resultado.get(sorteio)[0]));
		str.append(genereteOutput(resultado.get(sorteio)[1]));
		str.append(genereteOutput(resultado.get(sorteio)[2]));
		str.append(genereteOutput(resultado.get(sorteio)[3]));
		str.append(genereteOutput(resultado.get(sorteio)[4]));
		str.append(genereteOutput(resultado.get(sorteio)[5]));
		
		System.out.println(" FInal : "+str);
	}
	
	public static String genereteOutput(int number ) {
		return determineIfAlphabeticChar(number)== true? String.valueOf(retrieveChar(number)) : String.valueOf(number);
	} 
	
	public static int generateRandomInt(int maxNumber) {
		Random rand = new Random(); 
		int value = rand.nextInt(maxNumber);
		++value;
		return value;
	}
	
	public static boolean determineIfAlphabeticChar(int index) {
		return index >=10 && index <= 51; 
	}
	
	public static String retrieveChar(int index) {
		for (int i = 0; i < alpha.length; ++i) {
			if (alpha[i][1].equals(String.valueOf(index))) {
				return alpha[index][0];
			}
		}
		
		return "";
	}
}
