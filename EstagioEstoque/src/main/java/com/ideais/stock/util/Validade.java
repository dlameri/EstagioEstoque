package com.ideais.stock.util;

import org.apache.log4j.Logger;

public final class Validade {
	static final Logger log = Logger.getLogger(Validade.class);
	
    public static Boolean isValid (String s) {
		@SuppressWarnings("unused")
		Integer intToTest = null;
		if (s == null) {
		    return false;
		}
		
		if (s.isEmpty()) {
			log.warn("Passado valor vazio");
		    return false;
		}
		
		try {
		    intToTest = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			log.warn("Passado valor não numérico ("+s+")");
		    return false;
		}

		return true;
    }
    
}
