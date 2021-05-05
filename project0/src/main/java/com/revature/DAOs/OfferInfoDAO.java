package com.revature.DAOs;

import java.util.Date;
import java.util.List;

import com.revature.models.Count;
import com.revature.models.OfferInfo;

public interface OfferInfoDAO extends GenericDAO<OfferInfo>{

	List<OfferInfo> getAll();
	
	OfferInfo add(OfferInfo o);
	
	List<OfferInfo> getOfferByStatus(Integer status);

	Boolean update(Integer offerID, Integer itemID);
	
	Boolean updatePayment(Integer status, Integer userID, Integer itemID);
	
	//Integer calMonthlyPayment(Double price, Integer paymentStatus, Date timeStamp);
	
	List<Count> calWeeklyPayment();
	
}
