/*
 * @(#) CustomHttpsSocketFactory.java 0.1 April 11,2016
 *
 * Copyright 2010 IBS Software Services (P) Ltd. All Rights Reserved.
 *
 * This software is the proprietary information of IBS Software
 * Services(P) Ltd.
 * Use is subject to license terms.
 *
 */
package com.ibsplc.ndcapp.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.net.ssl.SSLSocket;

import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;



/**
 * Revision History
 * Revision 	Date 		Author 				Description
 *  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * 0.1		   11-04-2016 	Sibu K Sebastian				First Draft
 *
 *
 */
public class CustomHttpsSocketFactory implements SecureProtocolSocketFactory
{
	/**
	 * String used for logging.
	 */
	private static final String CLASS_NAME = "CustomHttpsSocketFactory";
	/**
	 *
	 */
	private SecureProtocolSocketFactory secureProtocolSocketFactory;
	/**
	 *
	 * @param base
	 */
	public CustomHttpsSocketFactory(ProtocolSocketFactory base)
	{
		if(base == null || !(base instanceof SecureProtocolSocketFactory)) throw new IllegalArgumentException();
		this.secureProtocolSocketFactory = (SecureProtocolSocketFactory) base;
	}

	/**
	 *
	 * @param socket
	 * @return
	 */
	private Socket acceptOnlyTLSOneTwo(Socket socket) {
		if (!(socket instanceof SSLSocket))
			return socket;
		SSLSocket sslSocket = (SSLSocket) socket;

		sslSocket.setEnabledProtocols(sslSocket
				.getSupportedProtocols());
		sslSocket.setEnabledCipherSuites(sslSocket
				.getSupportedCipherSuites());

		return sslSocket;
	}

	/**
	 * @param host
	 * @param port
	 * @return
	 * @throws IOException
	 */
	@Override
	public Socket createSocket(String host, int port) throws IOException
	{
		return acceptOnlyTLSOneTwo(secureProtocolSocketFactory.createSocket(host, port));
	}
	/**
	 * @param host
	 * @param port
	 * @param localAddress
	 * @param localPort
	 * @return
	 * @throws IOException
	 */
	@Override
	public Socket createSocket(String host, int port, InetAddress localAddress, int localPort) throws IOException
	{
		return acceptOnlyTLSOneTwo(secureProtocolSocketFactory.createSocket(host, port, localAddress, localPort));
	}
	/**
	 * @param host
	 * @param port
	 * @param localAddress
	 * @param localPort
	 * @param params
	 * @return
	 * @throws IOException
	 */
	@Override
	public Socket createSocket(String host, int port, InetAddress localAddress, int localPort, HttpConnectionParams params) throws IOException
	{
		return acceptOnlyTLSOneTwo(secureProtocolSocketFactory.createSocket(host, port, localAddress, localPort, params));
	}
	/**
	 * @param socket
	 * @param host
	 * @param port
	 * @param isAutoClose
	 * @return
	 * @throws IOException
	 */
	@Override
	public Socket createSocket(Socket socket, String host, int port, boolean isAutoClose) throws IOException
	{
		return acceptOnlyTLSOneTwo(secureProtocolSocketFactory.createSocket(socket, host, port, isAutoClose));
	}

}

