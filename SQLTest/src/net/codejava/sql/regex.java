package net.codejava.sql;

import java.util.regex.*;

public class regex {
	public static void main(String[] args) {
		String book_regex = "^[A-Z]+\\d{3}$";
		String[] bookID_list = {"A003", "B67", "c873", "C082"};
		Pattern p = Pattern.compile(book_regex);
		
		System.out.print("Suitable book ID: ");
		for (int i = 0; i < bookID_list.length; ++i) {
			Matcher m = p.matcher(bookID_list[i]);
			if (m.matches()) {
				System.out.print(bookID_list[i] + ", ");
			}
		}
}
}
