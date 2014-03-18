package com.ideais.stock.util;

public final class Validade {
	
    public static Boolean isValid (String s) {
	Integer intToTest = null;
	try {
	    intToTest = Integer.parseInt(s);
	} catch (NumberFormatException e) {
	    return false;
	}
	
	if (s == null || s.isEmpty()) {
	    return false;
	}
	return true;
    }

}
