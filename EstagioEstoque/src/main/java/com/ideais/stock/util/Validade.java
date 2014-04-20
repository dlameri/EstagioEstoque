package com.ideais.stock.util;

import org.apache.log4j.Logger;

public final class Validade {
	static final Logger LOG = Logger.getLogger(Validade.class);
	
    public static Boolean isValid (String s) {
		@SuppressWarnings("unused")
		Integer intToTest = null;
		if (s == null) {
		    return false;
		}
		
		if (s.isEmpty()) {
			LOG.warn("Passado valor vazio");
		    return false;
		}
		
		try {
		    intToTest = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			LOG.warn("Passado valor não numérico ("+s+")");
		    return false;
		}

		return true;
    }
    
}
