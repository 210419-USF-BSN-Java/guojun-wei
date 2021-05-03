package com.revature.services;

import java.util.List;

import com.revature.models.OfferInfo;

public interface OfferService {
	List<OfferInfo> getAll();

	OfferInfo add(OfferInfo o);
}
