package com.ideais.stock.util;

import org.apache.log4j.Logger;

public final class Validade {
	static final Logger log = Logger.getLogger(Validade.class);
	
    public static Boolean isValid (String s) {
		@SuppressWarnings("unused")
		Integer intToTest = null;
		if (s == null || s.isEmpty()) {
			log.warn(Validade.class.getClass() + "Passado valor vazio");
		    return false;
		}
		
		try {
		    intToTest = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			log.warn(Validade.class.getClass() + "Passado valor não numérico", e);
		    return false;
		}

		return true;
    }
    
}
