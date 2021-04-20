package com.revature;

public class Application {
	public static void main (String[] args) {
		String res = printNumberInWord(4);
		System.out.println(res);
		
		String res2 = reverse("abcd");
		System.out.println(res2);
	}
	
	public static String printNumberInWord(int number) {
		String[] arr = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
		String tmp;
		for (int i = 0; i <= 9; i++) {
			if (number == i) {
				tmp = arr[i];
				return tmp;
			}
		}
		return "OTHER";
	}
	
	public static String reverse(String string) {
		String tmp = "";
		for (int i = string.length() - 1; i >= 0; i--) {
			tmp += string.charAt(i);
		}
		return tmp;
	}
}
