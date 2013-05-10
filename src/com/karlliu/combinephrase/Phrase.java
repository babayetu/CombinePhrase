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
				wbarray = new WordBlock[]{new WordBlock("this", 0), new WordBlock("is", 1),new WordBlock("a", 2),new WordBlock("test", 3),
										  new WordBlock("phrase.", 4)};
				break;
			case 2:
				wbarray = new WordBlock[]{new WordBlock("what", 0), new WordBlock("'s", 1),new WordBlock("your", 2),new WordBlock("last", 3),
						  				  new WordBlock("name?", 4)};				
				break;
			case 3:
				wbarray = new WordBlock[]{new WordBlock("A", 0), new WordBlock("red", 1),new WordBlock("fox", 2),new WordBlock("can", 3),
						  				  new WordBlock("jump", 4), new WordBlock("over", 5), new WordBlock("another", 6), new WordBlock("brown", 7),
						  				new WordBlock("pig", 8), new WordBlock("in", 9), new WordBlock("forest", 10)};				
				break;
			default:
				wbarray = null;
		}
	}
}