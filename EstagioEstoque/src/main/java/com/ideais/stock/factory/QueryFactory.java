package com.ideais.stock.factory;

import java.sql.SQLException;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

public final class QueryFactory {
	
	public static Criteria factory (Criteria criteria, String orderColum, String order, String active, String firstResult, String maxResults) throws SQLException {
		
		if (orderColum != null) {	
			if (order != null && order.equals("asc")) {
				criteria.addOrder(Property.forName(orderColum).asc());
			}
			if (order != null && order.equals("desc")) {
				criteria.addOrder(Property.forName(orderColum).desc());
			} 
		}
		
		if (active != null && !active.equals("all")){
			criteria.add(Restrictions.like("active", parseStringToBoolean(active)));
		}
		
		criteria.setFirstResult(setFirstResult(firstResult));
		criteria.setMaxResults(setMaxResult(maxResults));
		
		return criteria;
	
	}
	
	private static Boolean parseStringToBoolean (String string){
		if (string == null) {
			return false;
		}
		if (string.equals("true")){
			return true;
		}
		return false;
	}
	
	private static int setFirstResult (String firstResult){
		Integer intToTest = null;
		try {
		    intToTest = Integer.parseInt(firstResult);
		    if (intToTest < 0) {
		    	return 1;
		    }
		    return intToTest;
		} catch (NumberFormatException e) {
		    return 20;
		}
	}
	
	private static int setMaxResult (String maxResult){
		Integer intToTest = null;
		try {
		    intToTest = Integer.parseInt(maxResult);
		    if (intToTest > 51) {
		    	return 50;
		    }
		    if (intToTest <= 0) {
		    	return 1;
		    }
		    return intToTest;
		} catch (NumberFormatException e) {
		    return 20;
		}
	}

}
