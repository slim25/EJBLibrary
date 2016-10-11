package com.pdp.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pdp.business.LibraryPersistentBeanRemote;
import com.pdp.business.LoggingSystemBeanRemote;
import com.pdp.model.Book;
import com.pdp.model.Log;
import com.pdp.model.LogLevel;


@WebServlet("/LibraryServlet")
public class LibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB private LibraryPersistentBeanRemote library;
	@EJB private LoggingSystemBeanRemote logger;
	
	 @Resource(mappedName = "java:jboss/exported/jms/queue/BookQueue")
     private Queue libraryQueue;

     @Resource(mappedName = "java:/ConnectionFactory")
     private ConnectionFactory cf;
	
    public LibraryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		logger.log(LogLevel.DEBUG,request.getRequestedSessionId(), "action is : " + action);
		switch (action) {
		case "addBook":
			String bookName = request.getParameter("bookName");
			logger.log(LogLevel.INFO,request.getRequestedSessionId(), "bookName is : " + bookName);
//			library.addBook(new Book(bookName));
			addBookMessage(bookName);
			response.sendRedirect("/pdp_ejb_web/");
			break;
		case "getBooks":
			List<Book> books = library.getBooks();
			logger.log(LogLevel.INFO,request.getRequestedSessionId(), "books is : " + books);
			response.getWriter().append("Library size : " + books.size() + "\n");
			for(Book book : books){
				response.getWriter().append("Book id : " + book.getId() + "; book name : " + book.getName() + "\n");
			}
			
			break;
		case "printLogs":
			logger.log(LogLevel.INFO,request.getRequestedSessionId(), "printLogs ");
			Collection<Log> allLogs = logger.getAllLogs();
			response.getWriter().append("All logs : \n");
			for(Log log : allLogs){
				response.getWriter().append("Log Level : " + log.getLogLevel() + "; session id : " +
						log.getSessionId() + "; message : " + log.getMessage() + "\n");
			}
			
			break;

		default:
			break;
		}
		
	}
	private void addBookMessage(String bookName){
		 Connection connection = null;
	       try {
	             connection = cf.createConnection();
	            Session session = connection.createSession(false,
	                    Session.AUTO_ACKNOWLEDGE);
	            MessageProducer publisher = null;
	            publisher = session.createProducer(libraryQueue);
	            connection.start();
	            TextMessage message = session
	                    .createTextMessage(bookName);
	            publisher.send(message);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (connection != null)
	                try {
	                    connection.close();
	                } catch (JMSException e) {
	                    e.printStackTrace();
	                }
	        }
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
