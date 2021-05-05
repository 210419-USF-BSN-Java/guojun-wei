package com.revature.services;

import java.util.List;

import com.revature.DAOs.OfferInfoDAO;
import com.revature.DAOs.OfferInfoDAOPostgres;
import com.revature.models.Count;
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

	@Override
	public List<OfferInfo> getOfferByStatus(Integer status) {
		List<OfferInfo> offers = offerD.getOfferByStatus(status);
		return offers;
	}

	@Override
	public Boolean update(Integer offerID, Integer itemID) {
		Boolean update = offerD.update(offerID, itemID);
		return update;
	}
	
	@Override
	public Boolean updatePayment(Integer status, Integer userID, Integer itemID) {
		Boolean updatePay = offerD.updatePayment(status, userID, itemID);
		return updatePay;
	}

	@Override
	public List<Count> calWeeklyPayment() {
		List<Count> weeklyPayment = offerD.calWeeklyPayment();
		return weeklyPayment;
	}
	
	
}
