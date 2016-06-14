package com.example.DiabloDB;

import java.util.List;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
		name = "PosterServlet",
		urlPatterns = {"/poster"})

public class PosterServlet extends HttpServlet {
	JDBCConnection jdbcc = new JDBCConnection();
	
	@Override
	// This processes GET requests, ex. get all threads in db (GET from servlet)
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null) {
			switch (action) {
			// Gets
			case "getAllPages":
				getAllPages(req, res);
				break;
			case "getUsers":
				getUsers(req, res);
				break;
			case "getSpecifiedUser":
				getSpecifiedUser(req, res);
				break;
			case "getPageThreads":
				getPageThreads(req, res);
				break;
			case "getThreadComments":
				getThreadComments(req, res);
				break;
			case "getUserPages":
				getUserPages(req, res);
				break;
			case "getUserThreads":
				getUserThreads(req, res);
				break;
			case "getUserComments":
				getUserComments(req, res);
				break;
			}
		} else {
			getAllPages(req, res);
		}
		
	}
	

	private void getUserComments(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ArrayList<Comment> comments = null;
		// String posterName = req.getParameter("posterName");
		// try {
		// 	comments = jdbcc.getUserComments(posterName);
		// } catch (SQLException e) {
		// 	Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		// }
		
		// req.setAttribute("comments", comments);
		// String nextJsp = "/jsp/comments_list.jsp";
		// RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);	
	}	


	private void getUserThreads(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ArrayList<Thread> threads = null;
		// String posterName = req.getParameter("posterName");
		// try {
		// 	threads = jdbcc.getUserThreads(posterName);
		// } catch (SQLException e) {
		// 	Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		// }
		
		// req.setAttribute("threads", threads);
		// String nextJsp = "/jsp/threads_list.jsp";
		// RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);
	}


	private void getUserPages(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ArrayList<Page> pages = null;
		// String posterName = req.getParameter("posterName");
		// try {
		// 	pages = jdbcc.getUserPages(posterName);
		// } catch (SQLException e) {
		// 	Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		// }
		
		// req.setAttribute("pages", pages);
		// String nextJsp = "/jsp/pages_list.jsp";
		// RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);
	}


	private void getThreadComments(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ArrayList<Comment> comments = null;
		String threadID = req.getParameter("threadID");
		try {
			comments = jdbcc.getThreadComments(threadID);
		} catch (SQLException e) {
			Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		}
		
		req.setAttribute("comments", comments);
		String nextJsp = "/jsp/thread.jsp";
		RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		d.forward(req, res);	
	}

	private void getPageThreads(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ArrayList<Thread> threads = null;
		String topicName = req.getParameter("topicName");
		try {
			threads = jdbcc.getPageThreads(topicName);
		} catch (SQLException e) {
			Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		}
		
		req.setAttribute("threads", threads);
		String nextJsp = "/jsp/page.jsp";
		RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		d.forward(req, res);
	}

	private void getSpecifiedUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ArrayList<ArrayList<Object>> poster = null;
		// String posterName = req.getParameter("posterName");
		// try {
		// 	poster = jdbcc.getSpecificUser(posterName);
		// } catch (SQLException e) {
		// 	Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		// }
		
		// req.setAttribute("poster", poster);
		// String nextJsp = "/jsp/user.jsp";
		// RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);
		
	}

	private void getUsers(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ArrayList<Poster> users = null;
		try {
			users = jdbcc.getUsers();
		} catch (SQLException e) {
			Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		}
		req.setAttribute("users", users);
		String nextJsp = "/jsp/user.jsp";
		RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		d.forward(req, res);
	}

	private void getAllPages(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ArrayList<Page> pages = null;
		try {
			pages = jdbcc.getAllPages();
		} catch (SQLException e) {
			Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		}
		
		req.setAttribute("pages", pages);
		String nextJsp = "/jsp/index.jsp";
		RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		d.forward(req, res);
	}

	@Override
	// This processes POST requests, ex. create a new thread (POST to servlet)
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null){
			switch (action) {
			// Creates
			case "createUser":
				createUser(req, res);
				break;
			case "createPage":
				createPage(req, res);
				break;
			case "createThread":
				createThread(req, res);
				break;
			case "createComment":
				createComment(req, res);
				break;
			case "createVote":
				createVote(req, res);
				break;
			case "createSuggestion":
				createSuggestion(req, res);
				break;
			}
		}
	}
	
	private void createSuggestion(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// String text = req.getParameter("text");
		// String posterName = req.getParameter("posterName");
		// String topicName = req.getParameter("topicName");
		// try {
		// 	jdbcc.createSuggestion(text, posterName, topicName);
		// } catch (SQLException e) {
		// 	Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		// }
		// String message = "You have made a suggestion for page "+topicName+".";
		// String nextJsp = "/jsp/page.jsp";
		// req.setAttribute("message", message);
		// RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);
	}

	private void createVote(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// String posterName = req.getParameter("posterName");
		// String voteType = req.getParameter("isUpvote");
		// String id = req.getParameter("id");
		// String objectType = req.getParameter("isComment");
		// // Convert strings to boolean values
		// boolean isUpvote = false;
		// if (voteType.equals("true")) isUpvote = true;
		// boolean isComment = true;
		// if (objectType.equals("false")) isComment = false;
		// try {
		// 	jdbcc.createVote(posterName, isUpvote, id, isComment);
		// } catch (SQLException e) {
		// 	Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		// }
		// String message = "You have successfully voted!";
		// req.setAttribute("message", message);
		// // Load comment (most common) or thread depending on vote type
		// String nextJsp = "/jsp/comment.jsp";
		// if (!isComment) nextJsp = "/jsp/thread.jsp";
		// RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);
	}

	private void createComment(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// String text = req.getParameter("text");
		// String threadID = req.getParameter("posterName");
		// String posterName = req.getParameter("posterName");
		// try {
		// 	jdbcc.createComment(text, posterName, threadID);
		// } catch (SQLException e) {
		// 	Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		// }
		// String message = "Your comment has been successfully posted!";
		// req.setAttribute("message", message);
		// String nextJsp = "/jsp/thread.jsp";
		// RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);
	}

	private void createThread(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// String threadTitle = req.getParameter("posterName");
		// String text = req.getParameter("text");
		// String topicName = req.getParameter("topicName");
		// String posterName = req.getParameter("posterName");
		// try {
		// 	jdbcc.createThread(threadTitle, text, topicName, posterName);
		// } catch (SQLException e) {
		// 	Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		// }
		// String message = threadTitle + " has been created in " + topicName + ".";
		// req.setAttribute("message", message);
		// String nextJsp = "/jsp/thread.jsp";
		// RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);
	}

	private void createPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String topicName = req.getParameter("topicName");
		String posterName = req.getParameter("posterName");
		System.out.println(topicName);
		System.out.println(posterName);
		try {
			jdbcc.createPage(topicName, posterName);
		} catch (SQLException e) {
			Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		}
		String message = topicName + " has been created.";
		req.setAttribute("message", message);
		// String nextJsp = "/jsp/index.jsp";
		// RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);
		getAllPages(req, res);
	}

	private void createUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// String userName = req.getParameter("userName");
		// try {
		// 	jdbcc.createUser(userName, false);
		// } catch (SQLException e) {
		// 	Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		// }
		// String message = "Welcome to DiabloDB, " + userName;
		// req.setAttribute("message", message);
		// String nextJsp = "/jsp/user.jsp";
		// RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);
	}

	@Override
	// This processes PUT requests
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null){
			switch (action) {
			// Updates
			case "adminLockThread":
				adminLockThread(req, res);
				break;
			case "promotePosterToAdmin":
				promotePosterToAdmin(req, res);
				break;
			}
		}
	}

	private void adminLockThread(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// String threadID = req.getParameter("threadID");
		// String posterName = req.getParameter("posterName");
		// try {
		// 	jdbcc.adminLockThread(threadID, posterName);
		// } catch (SQLException e){
		// 	Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		// }
		// String message = "Thread "+threadID+" is now locked. No users can post in this thread.";
		// req.setAttribute("message", message);
		// String nextJsp = "/jsp/thread.jsp";
		// RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);
	}
	
	private void promotePosterToAdmin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// String posterName = req.getParameter("posterName");
		// try {
		// 	jdbcc.promotePosterToAdmin(posterName);
		// } catch (SQLException e){
		// 	Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		// }
		// String message = "Poster "+posterName+" is now an admin.";
		// req.setAttribute("message", message);
		// String nextJsp = "/jsp/user.jsp";
		// RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);
	}	
	
}
