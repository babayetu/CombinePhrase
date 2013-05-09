package com.karlliu.combinephrase;

public class Phrase {
	private WordBlock[] wbarray;
	
	public Phrase() {
		wbarray = null;
	}
	
	public WordBlock[] getContent() {
		return wbarray;
	}
	
	public void setContent(WordBlock[] aWbarray) {
		wbarray = aWbarray;
	}
	
	public Phrase(int index) {
		switch (index) {
			case 1:
				wbarray = new WordBlock[]{new WordBlock("this", 1), new WordBlock("is", 2),new WordBlock("a", 3),new WordBlock("test", 4),
										  new WordBlock("phrase.", 5)};
				break;
			case 2:
				wbarray = new WordBlock[]{new WordBlock("what", 1), new WordBlock("'s", 2),new WordBlock("your", 3),new WordBlock("last", 4),
						  				  new WordBlock("name?", 5)};				
				break;
			case 3:
				wbarray = new WordBlock[]{new WordBlock("Can", 1), new WordBlock("you", 2),new WordBlock("help", 3),new WordBlock("me", 4),
						  				  new WordBlock("out?", 5)};				
				break;
			default:
				wbarray = null;
		}
	}
}