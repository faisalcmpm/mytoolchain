package com.ibsplc.ndcapp.air;

import java.util.Comparator;

import com.ibsplc.ndcapp.air.model.AirShoppingResponse.Offer;

public class AirOfferComparator implements Comparator<Offer> {


	@Override
	public int compare(Offer firstOffer, Offer secondOffer) {
		if(firstOffer == null) {
			return -1;
		}else if (secondOffer == null) {
			return 1;
		}else{
			if(firstOffer.getPrice() > secondOffer.getPrice()) {
				return 1;
			}else if (firstOffer.getPrice() < secondOffer.getPrice()) {
				return -1;
			}else{
				return 0;
			}
		}
		
	}

}
