package com.ideais.stock.factory;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

public final class QueryFactory {
	
	private static final int DEFAULT_OFFSET_VALUE = 0;
	private static final int DEFAULT_MAX_RESULTS_VALUE = 20;
	private static final int MAXIMUM_MAX_RESULTS_VALUE = 50;

	public static Criteria factory (Criteria criteria, String orderColum, String order, String active, String firstResult, String maxResults) throws HibernateException {
		
		if (orderColum != null) {	
			if ("asc".equals(order)) {
				criteria.addOrder(Property.forName(orderColum).asc());
			}
			if ("desc".equals(order)) {
				criteria.addOrder(Property.forName(orderColum).desc());
			} 
		}
		
		if ( !("all".equals(active)) ){
			criteria.add(Restrictions.like("active", parseStringToBoolean(active)));
		}
		
		criteria.setFirstResult(setFirstResult(firstResult));
		criteria.setMaxResults(setMaxResult(maxResults));
		
		return criteria;
	}
	
	private static Boolean parseStringToBoolean (String string){
		if ("true".equals(string)){
			return true;
		}
		return false;
	}
	
	private static int setFirstResult (String firstResult){
		Integer offsetNumber;
		try {
		    offsetNumber = Integer.parseInt(firstResult);
		    if (offsetNumber < 0) {
		    	return DEFAULT_OFFSET_VALUE;
		    }
		    return offsetNumber;
		} catch (NumberFormatException e) {
		    return DEFAULT_OFFSET_VALUE;
		}
	}
	
	private static int setMaxResult (String maxResult){
		Integer intToTest;
		
		try {
		    intToTest = Integer.parseInt(maxResult);
		    if (intToTest >= MAXIMUM_MAX_RESULTS_VALUE) {
		    	return MAXIMUM_MAX_RESULTS_VALUE;
		    }
		    if (intToTest < 1) {
		    	return DEFAULT_MAX_RESULTS_VALUE;
		    }
		    return intToTest;
		} catch (NumberFormatException e) {
		    return DEFAULT_MAX_RESULTS_VALUE;
		}
	}

}
