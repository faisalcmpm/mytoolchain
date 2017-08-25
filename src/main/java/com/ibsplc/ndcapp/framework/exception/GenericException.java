package com.ibsplc.ndcapp.framework.exception;

public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public static final String ERROR_MESSAGE_PROPERTY = "ErrorMessages";
	public static final String UNABLE_TO_SHOP_AIR = "GE_1001";
	public static final String UNABLE_FIND_CABS = "GE_1002";
	public static final String UNABLE_TO_LOCATE = "GE_1003";
	public static final String UNABLE_TO_LOCATE_THE_IMAGE = "GE_1004";
	public static final String UNABLE_TO_LOCATE_DEST_AIRPORT = "GE_1005";
	public static final String UNABLE_GUESS_LOCATION = "GE_1008";
	public static final String UNABLE_TO_FIND_HOTELS = "GE_1006";
	public static final String NO_AIR_OFFERS_AVAILABLE = "GE_1007";

	private String errCode;
	private String errMsg;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public GenericException(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
}
