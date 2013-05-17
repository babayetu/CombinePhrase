package com.karlliu.combinephrase;

public class PhraseSplit {
	private static String splitToken = " ";

	public static WordBlock[] split(String input) {
		// word block initialization only needs two parameters
		// one is String , the other is index of their position in phrase

		if (input == null)
			return null;

		String[] words = input.split(splitToken);
		WordBlock[] wba = new WordBlock[words.length];
				
		for (int i = 0; i < words.length; i++) {
			wba[i] = new WordBlock(words[i],i);
		}
		
		return wba;
	}
}