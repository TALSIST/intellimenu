package com.sist.collertor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Test {
	  public static void main(String[] args) {
		System.out.println(new SimpleDateFormat("dd MMM yyyy", new Locale("en", "US")).format(new Date()));
	}
}
