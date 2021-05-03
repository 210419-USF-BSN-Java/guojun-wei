package com.revature.DAOs;

import java.util.List;

import com.revature.models.OfferInfo;

public interface OfferInfoDAO extends GenericDAO<OfferInfo>{

	List<OfferInfo> getAll();
	
	OfferInfo add(OfferInfo o);
}
