package xml.controller;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import streamalert.util.LiveTestProxy;
import xml.utils.*;

/**
 * Servlet implementation class ReaderServlet
 */
@WebServlet("/ReaderServlet")
public class ReaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReaderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		
		int markup = Integer.parseInt(request.getParameter("markup"));
		int parserType = Integer.parseInt(request.getParameter("parser"));
		String fileName = request.getParameter("fileName");
		String uri = request.getParameter("uri");
		String searchTerm = request.getParameter("searchTerm");;
		
		XMLParserProxy proxy = new XMLParserProxy();
		String output = proxy.parseXML(markup, uri, parserType, searchTerm);
		LiveTestProxy streamAlertProxy = new LiveTestProxy();
		streamAlertProxy.XMLLiveTest(fileName, output);
		
		response.getWriter().append(output);
	}

}
