package com.ideais.stock.util;

public final class Validade {
	
    public static Boolean isValid (String s) {
		Integer intToTest = null;
		try {
		    intToTest = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			// TODO log warning
		    return false;
		}
		
		if (s == null || s.isEmpty()) {
			// TODO log warning
		    return false;
		}
		return true;
    }
}
