package com.revature.services;

import java.util.List;

import com.revature.DAOs.OfferInfoDAO;
import com.revature.DAOs.OfferInfoDAOPostgres;
import com.revature.models.OfferInfo;

public class OfferServiceImplementation implements OfferService{

private OfferInfoDAO offerD;
	
	public OfferServiceImplementation() {
		offerD = new OfferInfoDAOPostgres();
	}
	
	@Override
	public List<OfferInfo> getAll() {
		List<OfferInfo> offerInfos = offerD.getAll();
		return offerInfos;
	}
	

	@Override
	public OfferInfo add(OfferInfo o) {
		OfferInfo offer = offerD.add(o);
		return offer;
	}
	
	
}
