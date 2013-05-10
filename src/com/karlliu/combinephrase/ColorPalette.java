package com.karlliu.combinephrase;

import java.util.Random;

public class ColorPalette {	
	//every color needs to put 0xff instead of actually RGB color
	//it means not transparent
	private int index;
	
	private final int[] colorStore = new int[] {0xffffcc66,  //sand yellow 
			 0xff66cc33,	// green
			 0xffccccff,	//light purple
			 0xffff9966,	//orange
			 0xffff99cc,	//pink
			 0xff66ccff,	//blue
			 0xffcccc66,	// OK
			 0xff6666ff,	//deep blue
			 0xffff0000,	//red
			 0xff333399,	//blue
			 0xffcccc00	//brown			 
			 };
	
	//randomly copy color from colorStore to randomColor array
	private int[] randomColor = new int[] {0,0,0,0,0,0,0,0,0,0,0};
	
	public ColorPalette() {
		//random the array
		index = 0;
		Random r = new Random();
		for (int i =0; i< randomColor.length; i++) {
			randomColor[i] = colorStore[r.nextInt(colorStore.length)];
		}		
	}
	
	public int getColor() {
		if (index < randomColor.length -1)
		{
			index++;
			return randomColor[index];
		}
		else {
			index = index % (randomColor.length - 1);
			return randomColor[index];
		}			
	}	
}