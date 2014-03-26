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
				System.out.println("---------------------- >>> asc");
			}
			if (order != null && order.equals("desc")) {
				criteria.addOrder(Property.forName(orderColum).desc());
				System.out.println("---------------------- >>> desc");
			} 
		}
		
		if (active != null && !active.equals("all")){
			criteria.add(Restrictions.like("active", parseStringToBoolean(active)));
		}
		
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

}
