package com.revature.services;

import java.util.List;

import com.revature.models.Count;
import com.revature.models.OfferInfo;

public interface OfferService {
	List<OfferInfo> getAll();

	OfferInfo add(OfferInfo o);
	
	List<OfferInfo> getOfferByStatus(Integer status);
	
	Boolean update(Integer offerID, Integer itemID);
	
	Boolean updatePayment(Integer status, Integer userID, Integer itemID);
	
	List<Count> calWeeklyPayment();
}
