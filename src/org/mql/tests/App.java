package org.mql.tests;

public class App {

	public static boolean  vowels(char c) {
		if (c >= 'A' && c <= 'Z')
			c += 32;
		switch (c) {
		case 'a':
		case 'e':
		case 'i':
		case 'o':
		case 'u':
			return true;
		default:
			return false;
		}
	}
	
	 public static StringBuilder extractReversedVoyels(String s){
	        StringBuilder s2 = new StringBuilder();
	        for(int i = 0; i < s.length(); i++){
	            char c = s.charAt(i);
	            if(vowels(c)) s2.append(c);
	        }
	        return s2.reverse();
	 }
	 
	 public static String reverseVowels(String s) {
	        StringBuilder temp = new StringBuilder(s);
	        StringBuilder reversed = extractReversedVoyels(s);
	        int j = 0;
	        for(int i = 0; i < temp.length(); i++){
	            char c = temp.charAt(i);
	            if(vowels(c)){
	                temp.setCharAt(i, reversed.charAt(j));
	                j++;
	            }
	            
	        }
	        return new String(temp);
	    }

	public static void main(String[] args) {
		System.out.println(reverseVowels("hello"));
	}
}
