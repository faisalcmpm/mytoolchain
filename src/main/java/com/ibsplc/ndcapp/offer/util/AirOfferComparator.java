package com.ibsplc.ndcapp.offer.util;

import java.util.Comparator;

import com.ibsplc.ndcapp.air.model.AirShoppingResponse.Offer;

public class AirOfferComparator implements Comparator<Offer> {
	
	public int compare(Offer firstOffer, Offer secondOffer) {
		if(firstOffer == null) {
			return -1;
		}else if (secondOffer == null) {
			return 1;
		}else {
			if(firstOffer.getPrice() > secondOffer.getPrice()) {
				return 1;
			}else if (firstOffer.getPrice() < secondOffer.getPrice()) {
				return -1;
			}else{
				return firstOffer.getOfferId().compareTo(secondOffer.getOfferId());
			}
		}
	}

}
