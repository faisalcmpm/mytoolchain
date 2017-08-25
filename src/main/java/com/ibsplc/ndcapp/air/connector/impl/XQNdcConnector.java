package com.ibsplc.ndcapp.air.connector.impl;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.Duration;
import javax.xml.namespace.QName;

import org.iata.iata.edist.AgentUserSenderType;
import org.iata.iata.edist.AgentUserType.AgentUserID;
import org.iata.iata.edist.AirShopReqAttributeQueryType;
import org.iata.iata.edist.AirShopReqAttributeQueryType.OriginDestination;
import org.iata.iata.edist.AirShopReqAttributeQueryType.OriginDestination.CalendarDates;
import org.iata.iata.edist.AirShoppingRQ;
import org.iata.iata.edist.AirShoppingRQ.CoreQuery;
import org.iata.iata.edist.AirShoppingRS;
import org.iata.iata.edist.AirShoppingRS.OffersGroup;
import org.iata.iata.edist.AirShoppingRS.OffersGroup.AirlineOffers;
import org.iata.iata.edist.AirShoppingRS.OffersGroup.AirlineOffers.AirlineOffer;
import org.iata.iata.edist.AirShoppingRS.OffersGroup.AirlineOffers.PriceCalendar;
import org.iata.iata.edist.AirlineID;
import org.iata.iata.edist.AnonymousTravelerType;
import org.iata.iata.edist.AugPointType;
import org.iata.iata.edist.CountryCode;
import org.iata.iata.edist.Departure;
import org.iata.iata.edist.ErrorType;
import org.iata.iata.edist.ErrorsType;
import org.iata.iata.edist.FlightArrivalType;
import org.iata.iata.edist.FlightDepartureType;
import org.iata.iata.edist.FlightSegmentReference;
import org.iata.iata.edist.ListOfFlightSegmentType;
import org.iata.iata.edist.MsgDocumentType;
import org.iata.iata.edist.MsgPartiesType;
import org.iata.iata.edist.MsgPartiesType.Recipient;
import org.iata.iata.edist.MsgPartiesType.Sender;
import org.iata.iata.edist.ORAAirlineRecipientType;
import org.iata.iata.edist.PointOfSaleType;
import org.iata.iata.edist.PointOfSaleType.Location;
import org.iata.iata.edist.PricedFlightOfferAssocType;
import org.iata.iata.edist.PricedFlightOfferType.OfferPrice;
import org.iata.iata.edist.TravelerCoreType.PTC;
import org.iata.iata.edist.Travelers;
import org.iata.iata.edist.Travelers.Traveler;
import org.joda.time.Interval;

import com.ibsplc.iflyres.simpletypes.CurrencyAmountType;
import com.ibsplc.iflyres.simpletypes.OfferPriceAugPoint;
import com.ibsplc.ndcapp.air.connector.ConnectionConstants;
import com.ibsplc.ndcapp.air.connector.NdcConnector;
import com.ibsplc.ndcapp.air.connector.NdcFieldConstants;
import com.ibsplc.ndcapp.air.model.AirShoppingRequest;
import com.ibsplc.ndcapp.air.model.AirShoppingResponse;
import com.ibsplc.ndcapp.air.model.AirShoppingResponse.FlightSegment;
import com.ibsplc.ndcapp.air.model.AirShoppingResponse.Offer;
import com.ibsplc.ndcapp.framework.exception.GenericException;
import com.ibsplc.ndcapp.util.CalendarUtil;
import com.ibsplc.ndcapp.util.HttpPostClient;
import com.ibsplc.ndcapp.util.PropertiesHolder;
import com.ibsplc.wsdl.ndc.NdcResServicePort;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
public class XQNdcConnector implements NdcConnector {
	private static final String VENDOR = "XQ";
	private static String endpointURL = 
			PropertiesHolder.getProperty(
					ConnectionConstants.CNX_PROPERTY_FILE_NAME, VENDOR+"."+ConnectionConstants.END_POINT_URL);
	//	"http://192.168.8.113:6002/iRes_NdcRes_WS/services/NdcResServiceSOAPPort?wsdl";
	static NdcResServicePort port;
	static Marshaller marshaller;
	static Unmarshaller unmarshalller; 
	static {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(AirShoppingRQ.class);
			marshaller = jaxbContext.createMarshaller();
			
			jaxbContext = JAXBContext.newInstance(AirShoppingRS.class, OfferPriceAugPoint.class);
			unmarshalller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public AirShoppingResponse shopAir(AirShoppingRequest request) throws GenericException{
		
		AirShoppingRQ rq = buildRequest(request);
		String requestString = null;
		AirShoppingRS rs = null;
		try {
			StringWriter stringWriter = new StringWriter();
			if (marshaller == null) {
				JAXBContext jaxbContext = JAXBContext.newInstance(AirShoppingRQ.class);
				marshaller = jaxbContext.createMarshaller();
			}
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(rq, stringWriter);
			requestString = stringWriter.toString();
			requestString = requestString.substring(requestString.indexOf("<AirShoppingRQ"), requestString.length());
			String soapHeader =	"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:edis=\"http://www.iata.org/IATA/EDIST\"><soapenv:Header/><soapenv:Body>";
			String soapfooter = "   </soapenv:Body></soapenv:Envelope>";
			requestString = soapHeader+requestString+soapfooter;
			

			HttpPostClient client = new HttpPostClient();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("username",PropertiesHolder.getProperty(
					ConnectionConstants.CNX_PROPERTY_FILE_NAME, VENDOR+"."+ConnectionConstants.HEADER_USERNAME));
			headers.put("password",PropertiesHolder.getProperty(
					ConnectionConstants.CNX_PROPERTY_FILE_NAME, VENDOR+"."+ConnectionConstants.HEADER_PASSWORD));
			String responseMessage = client.post(endpointURL, requestString, 10000, headers);
			responseMessage = removeSoapWrap(responseMessage);
			System.out.println("Stripped Response \r\n"+responseMessage);
			if (unmarshalller == null) {
				JAXBContext jaxbContext = JAXBContext.newInstance(AirShoppingRS.class, OfferPriceAugPoint.class);
				unmarshalller = jaxbContext.createUnmarshaller();
			}
			
			
			rs = (AirShoppingRS) unmarshalller.unmarshal(new StringReader(responseMessage));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new GenericException(GenericException.UNABLE_TO_SHOP_AIR, PropertiesHolder.getProperty(GenericException.ERROR_MESSAGE_PROPERTY, GenericException.UNABLE_TO_SHOP_AIR));
		} 

		AirShoppingResponse response = buildResponse(rs);
		/*if(response == null || response.getOffers() == null || response.getOffers().isEmpty()) {
			throw new GenericException(GenericException.NO_AIR_OFFERS_AVAILABLE, PropertiesHolder.getProperty(GenericException.ERROR_MESSAGE_PROPERTY, GenericException.NO_AIR_OFFERS_AVAILABLE));
		}*/
		return response;
	}
	private String removeSoapWrap(String responseMessage) {
		String soapNameSpace = getSoapNameSpace(responseMessage);
		String HEADER_MARK = "<"+soapNameSpace+":Body>";
		String FOOTER_MARK = "</"+soapNameSpace+":Body>";
		responseMessage = responseMessage.substring(responseMessage.indexOf(HEADER_MARK)+HEADER_MARK.length());
		responseMessage = responseMessage.substring(0, responseMessage.indexOf(FOOTER_MARK));
		return responseMessage;
	}
	private String getSoapNameSpace(String responseMessage) {
		String nameSpace = null;
		String[] namespaces = responseMessage.split("xmlns:");
		for(String namespace : namespaces) {
			if(namespace.indexOf("http://schemas.xmlsoap.org/soap/envelope/") != -1) {
				nameSpace =  namespace.substring(0, namespace.indexOf("=")).trim();
				if (nameSpace.indexOf("=") == -1 && nameSpace.indexOf(">") == -1) {
					break;
				}
			}
		}
		return nameSpace;
	}
	private AirShoppingResponse buildResponse(AirShoppingRS rs) {
		AirShoppingResponse response = new AirShoppingResponse();
		List<Offer> offers = new ArrayList<AirShoppingResponse.Offer>();
		AirShoppingResponse.Offer offer;
		ErrorsType errors = rs.getErrors();
		if (errors != null) {
			for(ErrorType errorType : errors.getError()) {
				response.setErrorCode(errorType.getCode());
				response.setErrorMessage(errorType.getShortText());
			}
		} else if(rs.getSuccess() != null) {
			CurrencyAmountType offerAmount;
			OffersGroup offersgroup = rs.getOffersGroup();
			List<AirlineOffers> allAirlineOffers = offersgroup.getAirlineOffers();
			for(AirlineOffers airlineOffers : allAirlineOffers) {
				List<AirlineOffer> specificAirlineOffers = airlineOffers.getAirlineOffer();
				for (AirlineOffer airlineOffer : specificAirlineOffers) {
					offer = new AirShoppingResponse.Offer();
					offer.setOfferId(airlineOffer.getOfferID().getValue());
					offerAmount = getPrice(airlineOffer); 
					offer.setPrice(offerAmount.getValue().doubleValue());
					offer.setCurrency(offerAmount.getCode());
					offer.setFlights(buildFlights(airlineOffer));
					offer.setDepDate(offer.getFlights().get(0).getDepDate());
					offer.setDepDate(offer.getFlights().get(0).getDepTime());
					offer.setArrDate(offer.getFlights().get(offer.getFlights().size() -1).getArrDate());
					offer.setArrTime(offer.getFlights().get(offer.getFlights().size() -1).getArrTime());
					offer.setJourneyTime(getFullJourenyTime(offer.getFlights()));
					offers.add(offer);
				}
				List<PriceCalendar> pricedCalendars = airlineOffers.getPriceCalendar();
				for (PriceCalendar priceCalendar : pricedCalendars) {
					offer = new AirShoppingResponse.Offer();
					offer.setOfferId(""+System.currentTimeMillis());
					offer.setPrice(priceCalendar.getTotalPrice().getValue().doubleValue());
					offer.setCurrency(priceCalendar.getTotalPrice().getCode());
					offer.setDepDate(CalendarUtil.toStringFormat(priceCalendar.getPriceCalendarDate().get(0).getValue().toGregorianCalendar(),"YYYY-MM-dd"));
					offers.add(offer);
				}
			}
			response.setOffers(offers);
		}
		return response;
	}
	private String getFullJourenyTime(List<FlightSegment> flights) {
		long hours = 0;
		long minutes = 0;
		String[] hm;
		Calendar lastSegmentArrival = null;
		Calendar depCalander = null;
		Calendar arrCalander = null;
		for (FlightSegment flightSegment : flights) {
			hm = flightSegment.getJourenyTime().split("-");
			hours = hours+Integer.parseInt(hm[0]);
			minutes = minutes+Integer.parseInt(hm[1]);
			depCalander = CalendarUtil.toCalendar(flightSegment.getDepDate(), "YYYY-MM-DD");
			depCalander.set(Calendar.HOUR_OF_DAY,Integer.parseInt(flightSegment.getDepDate().split("-")[0]));
			depCalander.set(Calendar.MINUTE,Integer.parseInt(flightSegment.getDepDate().split("-")[1]));
			if (lastSegmentArrival != null) {
//				DateTime depDateTime =   new DateTime(depCalander.getTime());
//				DateTime arrDateTime =   new DateTime(lastSegmentArrival.getTime());
				Interval interval = new Interval(lastSegmentArrival.getTime().getTime(), depCalander.getTime().getTime());
				org.joda.time.Duration duration = interval.toDuration();
				minutes = (long) (minutes + duration.getStandardMinutes());
			}
			arrCalander = CalendarUtil.toCalendar(flightSegment.getArrDate(), "YYYY-MM-DD");
			arrCalander.set(Calendar.HOUR_OF_DAY,Integer.parseInt(flightSegment.getArrDate().split("-")[0]));
			arrCalander.set(Calendar.MINUTE,Integer.parseInt(flightSegment.getArrDate().split("-")[1]));
			lastSegmentArrival = arrCalander;
			
		}
		long addtionalHours = minutes/60;
		if (addtionalHours >= 1) {
			hours = hours +addtionalHours;
			minutes = minutes%60;
		}
		
		return hours+"-"+minutes;
	}
	private List<FlightSegment> buildFlights(AirlineOffer airlineOffer) {
		List<FlightSegment> flightSegments = new ArrayList<AirShoppingResponse.FlightSegment>();
		FlightSegment flightSegment;
		List<PricedFlightOfferAssocType> flightOfferAssocs = airlineOffer.getPricedOffer().getAssociations();
		for (PricedFlightOfferAssocType flightOfferAssocType : flightOfferAssocs) {
			List<FlightSegmentReference> segmentReferences = flightOfferAssocType.getApplicableFlight().getFlightSegmentReference();
			for (FlightSegmentReference segmentReference : segmentReferences) {
				ListOfFlightSegmentType segment = 
						(ListOfFlightSegmentType) segmentReference.getRef();
				flightSegment = new FlightSegment();
				flightSegment.setCarrierCode(segment.getMarketingCarrier().getAirlineID().getValue());
				flightSegment.setFlightNumber(segment.getMarketingCarrier().getFlightNumber().getValue());
				FlightDepartureType.AirportCode depAirportCode = 
						(FlightDepartureType.AirportCode) getFromJAXBElement(segment.getDeparture().getRest(), "AirportCode", "http://www.iata.org/IATA/EDIST");
				flightSegment.setBdAirportCode(depAirportCode.getValue());
				Object date = getFromJAXBElement(segment.getDeparture().getRest(), "Date", "http://www.iata.org/IATA/EDIST");
				if (date.getClass().equals(XMLGregorianCalendarImpl.class)) {
					XMLGregorianCalendarImpl calendar = (XMLGregorianCalendarImpl) date;
					flightSegment.setDepDate(CalendarUtil.toStringFormat(calendar.toGregorianCalendar(), "YYYY-MM-dd"));
				} else if (date.getClass().equals(String.class)) {
					
					String depDateString = (String) date;
					flightSegment.setDepDate(depDateString);
				}
				flightSegment.setDepTime((String) getFromJAXBElement(segment.getDeparture().getRest(), "Time", "http://www.iata.org/IATA/EDIST"));
				flightSegment.setJourenyTime(getDurationString(segment.getFlightDetail().getFlightDuration().getValue()));
				FlightArrivalType.AirportCode arrAirportCode = 
						(FlightArrivalType.AirportCode) getFromJAXBElement(segment.getArrival().getRest(), "AirportCode", "http://www.iata.org/IATA/EDIST");
				flightSegment.setOffAirportCode(arrAirportCode.getValue());
				date = getFromJAXBElement(segment.getArrival().getRest(), "Date", "http://www.iata.org/IATA/EDIST");
				if (date.getClass().equals(XMLGregorianCalendarImpl.class)) {
					XMLGregorianCalendarImpl calendar = (XMLGregorianCalendarImpl) date;
					flightSegment.setArrDate(CalendarUtil.toStringFormat(calendar.toGregorianCalendar(), "YYYY-MM-dd"));
				} else if (date.getClass().equals(String.class)) {
					
					String arrDateString = (String) date;
					flightSegment.setArrDate(arrDateString);
				}
				flightSegment.setArrTime((String) getFromJAXBElement(segment.getArrival().getRest(), "Time", "http://www.iata.org/IATA/EDIST"));
				flightSegments.add(flightSegment);
			}
		}
		return flightSegments;
	}
	private String getDurationString(Duration value) {
		int days = value.getDays();
		int hours = value.getHours();
		int minutes = value.getMinutes();
		int totalHours = days *24 + hours;
		String duration = totalHours+"-"+minutes;
		return duration;
	}
	private Object getFromJAXBElement(List<JAXBElement<?>> rest, String name,
			String nameSpace) {
		QName qName = new QName(nameSpace, name);
		for (JAXBElement<?> element : rest) {
			if(element.getName().equals(qName)) {
				return element.getValue();
			}
		}
		return null;
	}
	private CurrencyAmountType getPrice(AirlineOffer airlineOffer) {
		CurrencyAmountType amountType = null;
		OfferPrice offer = airlineOffer.getPricedOffer().getOfferPrice().get(0);
		OfferPriceAugPoint augPoint;
		for(Object fareObj : offer.getRefs()) {
			if(((AugPointType)fareObj).getAny().getClass().equals(OfferPriceAugPoint.class)) {
				augPoint = (OfferPriceAugPoint) ((AugPointType)fareObj).getAny();
				return augPoint.getDisplayFare();
			}
		}
		return amountType;
	}
	private AirShoppingRQ buildRequest(AirShoppingRequest request) {
		AirShoppingRQ rq = new AirShoppingRQ();		
		rq.setPointOfSale(buildPointOfSaleType(request));
		rq.setDocument(buildMsgDocument(request));
		rq.setParty(buildMsgParty(request));
		rq.setTravelers(buildTravelers(request));
		rq.setCoreQuery(buildCoreQuery(request));
		return rq;
	}
	private CoreQuery buildCoreQuery(AirShoppingRequest request) {
		CoreQuery coreQuery = new CoreQuery();
		AirShopReqAttributeQueryType originDestinations = new AirShopReqAttributeQueryType();
		OriginDestination originDestination = new OriginDestination();
		Departure departure = new Departure();
		FlightDepartureType.AirportCode depAirportCode = new FlightDepartureType.AirportCode();
		depAirportCode.setValue(request.getOriginAirport());
		departure.getRest().add(buildJAXBElement("AirportCode", "http://www.iata.org/IATA/EDIST", FlightDepartureType.AirportCode.class, depAirportCode));
		departure.getRest().add(buildJAXBElement("Date", "http://www.iata.org/IATA/EDIST", String.class, CalendarUtil.toStringFormat(request.getOnwardDate().getTime(), "YYYY-MM-dd")));
		originDestination.setDeparture(departure);
		FlightArrivalType arrival = new FlightArrivalType();
		FlightArrivalType.AirportCode arrAirportCode = new FlightArrivalType.AirportCode();
		arrAirportCode.setValue(request.getDestinationAirport());
		arrival.getRest().add(buildJAXBElement("AirportCode", "http://www.iata.org/IATA/EDIST", FlightArrivalType.AirportCode.class, arrAirportCode));		
		originDestination.setArrival(arrival);
		CalendarDates calendarDates = new CalendarDates();
		if (request.getDaysOut() == -1) {
			calendarDates.setDaysBefore(NdcFieldConstants.NEGATIVE_DAYS_OUT);
			calendarDates.setDaysAfter(NdcFieldConstants.POSTIVE_DAYS_OUT);
		} else {
			calendarDates.setDaysBefore(request.getDaysOut());
			calendarDates.setDaysAfter(request.getDaysOut());
		}
		originDestination.setCalendarDates(calendarDates);
		originDestinations.getOriginDestination().add(originDestination);
		if (request.getReturnDate() != null) {
			originDestination = new OriginDestination();
			departure = new Departure();
			depAirportCode = new FlightDepartureType.AirportCode();
			depAirportCode.setValue(request.getDestinationAirport());
			departure.getRest().add(buildJAXBElement("AirportCode", "http://www.iata.org/IATA/EDIST", FlightDepartureType.AirportCode.class, depAirportCode));
			departure.getRest().add(buildJAXBElement("Date", "http://www.iata.org/IATA/EDIST", Date.class, request.getReturnDate().getTime()));
			originDestination.setDeparture(departure);
			arrival = new FlightArrivalType();
			arrAirportCode = new FlightArrivalType.AirportCode();
			arrAirportCode.setValue(request.getOriginAirport());
			arrival.getRest().add(buildJAXBElement("AirportCode", "http://www.iata.org/IATA/EDIST", FlightArrivalType.AirportCode.class, arrAirportCode));		
			originDestination.setArrival(arrival );
			calendarDates = new CalendarDates();
			calendarDates.setDaysBefore(NdcFieldConstants.NEGATIVE_DAYS_OUT);
			calendarDates.setDaysAfter(NdcFieldConstants.POSTIVE_DAYS_OUT);
			originDestination.setCalendarDates(calendarDates);
			originDestinations.getOriginDestination().add(originDestination);	
		}
		coreQuery.setOriginDestinations(originDestinations);
		return coreQuery;
	}
	private JAXBElement buildJAXBElement(String name,String namespace,
			Class class1, Object object) {
		JAXBElement element = new JAXBElement(new QName(namespace,name),class1,object);		
		return element;
	}
	private Travelers buildTravelers(AirShoppingRequest request) {
		Travelers travelers = new Travelers();
		if (request.getAdultCount() > 0) {
			Traveler traveller = new Traveler();
			AnonymousTravelerType anonymousTravellerType = new AnonymousTravelerType();
			PTC ptc = new PTC();
			ptc.setQuantity(new BigInteger(""+request.getAdultCount()));
			ptc.setValue(NdcFieldConstants.PTC_ADULT);
			anonymousTravellerType.setPTC(ptc);
			traveller.setAnonymousTraveler(anonymousTravellerType);
			travelers.getTraveler().add(traveller);
		}
		if (request.getChildCount() > 0) {
			Traveler traveller = new Traveler();
			AnonymousTravelerType anonymousTravellerType = new AnonymousTravelerType();
			PTC ptc = new PTC();
			ptc.setQuantity(new BigInteger(""+request.getChildCount()));
			ptc.setValue(NdcFieldConstants.PTC_CHILD);
			anonymousTravellerType.setPTC(ptc);
			traveller.setAnonymousTraveler(anonymousTravellerType);
			travelers.getTraveler().add(traveller);
		}
		if (request.getInfantCount() > 0) {
			Traveler traveller = new Traveler();
			AnonymousTravelerType anonymousTravellerType = new AnonymousTravelerType();
			PTC ptc = new PTC();
			ptc.setQuantity(new BigInteger(""+request.getInfantCount()));
			ptc.setValue(NdcFieldConstants.PTC_INFANT);
			anonymousTravellerType.setPTC(ptc);
			traveller.setAnonymousTraveler(anonymousTravellerType);
			travelers.getTraveler().add(traveller);
		}
		return travelers;
	}
	private MsgPartiesType buildMsgParty(AirShoppingRequest request) {
		MsgPartiesType msgPartiesType = new MsgPartiesType();
		msgPartiesType.setSender(buildSender(request));
		msgPartiesType.setRecipient(buildJAXBElement("Recipient", "http://www.iata.org/IATA/EDIST", MsgPartiesType.Recipient.class, buildRecipient()));				
		return msgPartiesType;
	}
	private Recipient buildRecipient() {
		Recipient recipient = new Recipient();
		ORAAirlineRecipientType recipientType = new ORAAirlineRecipientType();
		AirlineID airlineID = new AirlineID();
		airlineID.setValue(VENDOR);
		recipientType.setAirlineID(airlineID);
		recipient.setORARecipient(recipientType);
		return recipient;
	}
	private Sender buildSender(AirShoppingRequest request) {
		Sender sender = new Sender();
		AgentUserSenderType senderType = new AgentUserSenderType();
		AgentUserID agentUserID = new AgentUserID();
		agentUserID.setValue("Guest EN");
		agentUserID.setValue(PropertiesHolder.getProperty(
				ConnectionConstants.CNX_PROPERTY_FILE_NAME, VENDOR+"."+NdcFieldConstants.AGENT_USER_ID));
		senderType.setAgentUserID(agentUserID);
		sender.setAgentUserSender(senderType);
		return sender;
	}
	private MsgDocumentType buildMsgDocument(AirShoppingRequest request) {
		MsgDocumentType documentType = new MsgDocumentType();
		documentType.setName("NDC");
		documentType.setReferenceVersion("15.1.2");
		return documentType;
	}
	private PointOfSaleType buildPointOfSaleType(AirShoppingRequest request) {
		PointOfSaleType pointOfSaleType = new PointOfSaleType();
		Location location = new Location();
		CountryCode countryCode = new CountryCode();
		countryCode.setValue(request.getLocale());
		location.setCountryCode(countryCode);
		pointOfSaleType.setLocation(location);
		return pointOfSaleType;
	}

}
