package com.ibsplc.ndcapp.air.connector;

import com.ibsplc.ndcapp.air.model.AirShoppingResponse;
import com.ibsplc.ndcapp.air.model.AirShoppingRequest;
import com.ibsplc.ndcapp.framework.exception.GenericException;

public interface NdcConnector {
	public AirShoppingResponse	shopAir(AirShoppingRequest request) throws GenericException;
}
