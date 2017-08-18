package com.cts.testing;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.cts.olympicservlets.OlympicAdminServlet;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;

public class OlympicServletTesting {

	@Test
	public void testOlympicAdminServlet() throws IOException, SAXException {
		  WebConversation conversation = new WebConversation();
		  
			 ServletRunner sr = new ServletRunner();
			 sr.registerServlet( "OlympicAdminServlet", OlympicAdminServlet.class.getName() );
			 WebRequest  request = new PostMethodWebRequest( 
			         "http://localhost:8080/OlympicGamesSummer1/OlympicAdminServlet" );		
			 WebResponse response = conversation.getResponse(request);
			 String usernames="lekha";
		     request.setParameter("list", usernames);
		     
		     response = conversation.getResponse( request );
	}

}
