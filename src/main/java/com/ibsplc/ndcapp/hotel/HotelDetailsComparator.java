package com.ibsplc.ndcapp.hotel;

import java.util.Comparator;

import com.ibsplc.ndcapp.hotel.vo.HotelDetailsVO;

public class HotelDetailsComparator implements Comparator<HotelDetailsVO> {

	@Override
	public int compare(HotelDetailsVO firstHotelDetailsVO, HotelDetailsVO secondHotelDetailsVO) {
		if(firstHotelDetailsVO == null) {
			return -1;
		}else if (secondHotelDetailsVO == null) {
			return 1;
		}else{
			if(firstHotelDetailsVO.getPrice() > secondHotelDetailsVO.getPrice()) {
				return 1;
			}else if (firstHotelDetailsVO.getPrice() < secondHotelDetailsVO.getPrice()) {
				return -1;
			}else{
				if(firstHotelDetailsVO.getHotelName() == null) {
					return -1;
				}else if(secondHotelDetailsVO.getHotelName() == null) {
					return 1;
				} else{
					return firstHotelDetailsVO.getHotelName().compareTo(secondHotelDetailsVO.getHotelName());
				}
			}
		}
		
	}

}
