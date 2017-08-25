package com.ibsplc.ndcapp.framework;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ibsplc.ndcapp.air.connector.impl.XQNdcConnector;
import com.ibsplc.ndcapp.airport.util.AirportsUtil;

public class ApplicationStartUp implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("ApplicationStarting up!");
		System.out.println("Geting all airports up!");
		try {
			AirportsUtil.getAllAirports();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Geting all airports completed!");

		try {
			System.out.println("Initializing XQNdcConnector!");
			XQNdcConnector conn = new XQNdcConnector();
			System.out.println(" XQNdcConnector done!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		System.out.println("Shutting down!");
	}
}