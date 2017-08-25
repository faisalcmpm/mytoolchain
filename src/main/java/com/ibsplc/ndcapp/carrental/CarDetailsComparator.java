package com.ibsplc.ndcapp.carrental;

import java.util.Comparator;

import com.ibsplc.ndcapp.carrental.vo.CarDetailsVO;



public class CarDetailsComparator implements Comparator<CarDetailsVO> {	
	
	public int compare(CarDetailsVO firstCarDetailsVO, CarDetailsVO secondCarDetailsVO) {
		
		if(firstCarDetailsVO == null) {
			return -1;
		}else if (secondCarDetailsVO == null) {
			return 1;
		}else{
			if(firstCarDetailsVO.getTotalCost() > secondCarDetailsVO.getTotalCost()) {
				return 1;
			}else if (firstCarDetailsVO.getTotalCost() < secondCarDetailsVO.getTotalCost()) {
				return -1;
			}else {
				if(firstCarDetailsVO.getVehicleMake() == null) {
					return -1;
				}else if (secondCarDetailsVO.getVehicleMake() == null) {
					return 1;
				}else {
					return firstCarDetailsVO.getVehicleMake().compareTo(secondCarDetailsVO.getVehicleMake());
				}
			}
		}
	}

}
