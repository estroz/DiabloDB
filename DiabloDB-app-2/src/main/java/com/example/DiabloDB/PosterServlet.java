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

@WebServlet(name = "PosterServlet", urlPatterns = { "/poster" })

public class PosterServlet extends HttpServlet {
	JDBCConnection jdbcc = new JDBCConnection();

	@Override
	// This processes GET requests, ex. get all threads in db (GET from servlet)
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null) {
			switch (action) {
			// Gets
			
			// The getQueriedPosters, deleteComment & createVote do not want to go to POST so added to GET
            case "deleteComment":
                deleteComment(req, res);
                break;
            case "getQueriedThreadsBasedOnComments":
                getQueriedThreadsBasedOnComments(req, res);
                break;
            case "getPostersInAllThreads":
                getPostersInAllThreads(req, res);
                break;
            case "getQueriedPosters":
                getQueriedPosters(req, res);
                break;
            case "getQueriedThreads":
                getQueriedThreads(req, res);
                break;
			case "createVote":
				createVote(req, res);
				break;
			case "getAllPages":
				getAllPages(req, res);
				break;
			case "getUsers":
				getUsers(req, res);
				break;
            case "getThreads":
                getThreads(req, res);
                break;
            case "getComments":
                getComments(req, res);
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
		// comments = jdbcc.getUserComments(posterName);
		// } catch (SQLException e) {
		// Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE,
		// null, e);
		// }

		// req.setAttribute("comments", comments);
		// String nextJsp = "/jsp/comments_list.jsp";
		// RequestDispatcher d =
		// getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);
	}

	private void getUserThreads(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ArrayList<Thread> threads = null;
		// String posterName = req.getParameter("posterName");
		// try {
		// threads = jdbcc.getUserThreads(posterName);
		// } catch (SQLException e) {
		// Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE,
		// null, e);
		// }

		// req.setAttribute("threads", threads);
		// String nextJsp = "/jsp/threads_list.jsp";
		// RequestDispatcher d =
		// getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);
	}

	private void getUserPages(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ArrayList<Page> pages = null;
		// String posterName = req.getParameter("posterName");
		// try {
		// pages = jdbcc.getUserPages(posterName);
		// } catch (SQLException e) {
		// Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE,
		// null, e);
		// }

		// req.setAttribute("pages", pages);
		// String nextJsp = "/jsp/pages_list.jsp";
		// RequestDispatcher d =
		// getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);
	}
    
    private void getPostersInAllThreads(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        ArrayList<Poster> users = null;
        try {
            users = jdbcc.usersInAllThreads();
        } catch (SQLException e) {
            Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        req.setAttribute("users", users);
        String nextJsp = "/jsp/user.jsp";
        RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
        d.forward(req, res);
    }
    
    private void getQueriedThreads(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        ArrayList<Thread> threads = null;
        String select = req.getParameter("select");
        String op = req.getParameter("op");
        try{
            threads = jdbcc.minOrMaxThread(select, op);
        } catch (SQLException e) {
            Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
        }

        req.setAttribute("threads", threads);
        String nextJsp = "/jsp/all_threads.jsp";
        RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
        d.forward(req, res);
    }
    
    private void getQueriedThreadsBasedOnComments(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        ArrayList<Thread> threads = null;
        String op = req.getParameter("op");
        String idAndTitle = req.getParameter("idAndTitle");
        try{
            threads = jdbcc.CommentInfo(idAndTitle, op);
        } catch (SQLException e) {
            Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        String message = "Query comments of threads";
        req.setAttribute("threads", threads);
        req.setAttribute("message", message);
        String nextJsp = "/jsp/all_threads.jsp";
        RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
        d.forward(req, res);
    }
    
    private void getQueriedPosters(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        ArrayList<Poster> users = null;
        String op = req.getParameter("op");
        int arg = Integer.parseInt(req.getParameter("arg"));
        String adminOnly = req.getParameter("adminOnly");
        
        if(adminOnly.equals("true")) {
            try{
                users = jdbcc.selectAdminIDAboveX(op, arg);
            } catch (SQLException e) {
                Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            try{
                users = jdbcc.selectUsersAboveX(op, arg);
            } catch (SQLException e) {
                Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        req.setAttribute("users", users);
        String nextJsp = "/jsp/user.jsp";
        RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
        d.forward(req, res);
    }

	private void getThreadComments(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		ArrayList<Comment> comments = null;
		String threadID = req.getParameter("threadID");
		String title = req.getParameter("title");
		
		// Added message attribute for SQL Exceptions when voting on comments or threads
		String message = null;
		if (req.getAttribute("message") != null)
			message = req.getAttribute("message").toString();

		try {
			comments = jdbcc.getThreadComments(threadID);
		} catch (SQLException e) {
			Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		}

		req.setAttribute("message", message);
		req.setAttribute("comments", comments);
		req.setAttribute("title", title);
		req.setAttribute("threadID", threadID);
		String nextJsp = "/jsp/thread.jsp";
		RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		d.forward(req, res);
	}

	private void getPageThreads(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ArrayList<Thread> threads = null;
		String topicName = req.getParameter("topicName");
		
		// Added message attribute for SQL Exceptions when voting on comments or threads
		String message = null;
		if (req.getAttribute("message") != null)
			message = req.getAttribute("message").toString();

		try {
			threads = jdbcc.getPageThreads(topicName);
		} catch (SQLException e) {
			Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		}

		req.setAttribute("message", message);
		req.setAttribute("threads", threads);
		req.setAttribute("topicName", topicName);
		String nextJsp = "/jsp/page.jsp";
		RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		d.forward(req, res);
	}

	private void getSpecifiedUser(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// ArrayList<ArrayList<Object>> poster = null;
		// String posterName = req.getParameter("posterName");
		// try {
		// poster = jdbcc.getSpecificUser(posterName);
		// } catch (SQLException e) {
		// Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE,
		// null, e);
		// }

		// req.setAttribute("poster", poster);
		// String nextJsp = "/jsp/user.jsp";
		// RequestDispatcher d =
		// getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);

	}

	private void getUsers(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ArrayList<Poster> users = null;
        
        //Added for errors when deleting user
        String message = null;
        if (req.getAttribute("message") != null) message = req.getAttribute("message").toString();
        
		try {
			users = jdbcc.getUsers();
		} catch (SQLException e) {
			Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
		}
        
        req.setAttribute("message", message);
		req.setAttribute("users", users);
		String nextJsp = "/jsp/user.jsp";
		RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		d.forward(req, res);
	}
    
    private void getThreads(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ArrayList<Thread> threads = null;
        
        //Added for errors when deleting user
        String errorMessage = null;
        if (req.getAttribute("errorMessage") != null) errorMessage = req.getAttribute("errorMessage").toString();
        
        try {
            threads = jdbcc.getAllThreads();
        } catch (SQLException e) {
            Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        
        req.setAttribute("errorMessage", errorMessage);
        req.setAttribute("threads", threads);
        String nextJsp = "/jsp/all_threads.jsp";
        RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
        d.forward(req, res);
    }
    
    private void getComments(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ArrayList<Comment> comments = null;
        
        //Added for errors when deleting user
        String message = null;
        if (req.getAttribute("message") != null) message = req.getAttribute("message").toString();

        try {
            comments = jdbcc.getAllComments();
        } catch (SQLException e) {
            Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        
        req.setAttribute("message", message);
        req.setAttribute("comments", comments);
        String nextJsp = "/jsp/all_comments.jsp";
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
		if (action != null) {
			switch (action) {
			// Creates
            case "getQueriedPosters":
                getQueriedPosters(req, res);
                break;
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
            case "deleteComment":
                deleteComment(req, res);
                break;
            case "deleteThread":
                deleteThread(req, res);
                break;
            case "deletePoster":
                deletePoster(req, res);
                break;
			}
		}
	}

	private void createSuggestion(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// String text = req.getParameter("text");
		// String posterName = req.getParameter("posterName");
		// String topicName = req.getParameter("topicName");
		// try {
		// jdbcc.createSuggestion(text, posterName, topicName);
		// } catch (SQLException e) {
		// Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE,
		// null, e);
		// }
		// String message = "You have made a suggestion for page
		// "+topicName+".";
		// String nextJsp = "/jsp/page.jsp";
		// req.setAttribute("message", message);
		// RequestDispatcher d =
		// getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);
	}
    
    private void deleteComment(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String adminPass = req.getParameter("adminPass");
        String commID = req.getParameter("commID");
        String message = null;
        if(adminPass.equals("admin")) {
            try{
                jdbcc.deleteComment(commID);
            } catch(SQLException e) {
                Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
                message = "SQL error";
            }
        } else {
            message = "Admin password incorrect. Comment not deleted";
        }
        req.setAttribute("message", message);
        getComments(req, res);
    }
    
    private void deleteThread(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String adminPass = req.getParameter("adminPass");
        String threadID = req.getParameter("threadID");
        String errorMessage = null;
        if(adminPass.equals("admin")) {
            try{
                jdbcc.deleteThread(threadID);
            } catch(SQLException e) {
                Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
                errorMessage = "SQL error";
            }
        } else {
            errorMessage = "Admin password incorrect. Thread not deleted";
        }
        req.setAttribute("errorMessage", errorMessage);
        getThreads(req, res);
    }
    
    private void deletePoster(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String adminPass = req.getParameter("adminPass");
        String posterName = req.getParameter("posterName");
        String message = null;
        if(adminPass.equals("admin")) {
            try{
                jdbcc.deleteUser(posterName);
            } catch(SQLException e) {
                Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
                message = "SQL error";
            }
        } else {
            message = "Admin password incorrect. User not deleted";
        }
        req.setAttribute("message", message);
        getUsers(req, res);
    }

	private void createVote(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String posterName = req.getParameter("posterName");
		String voteType = req.getParameter("voteType");
		String id = req.getParameter("id");
		String objectType = req.getParameter("isComment");
		String topicName = req.getParameter("topicName");
		String threadID = req.getParameter("threadID");
		String title = req.getParameter("title");
		String message = null;

		// Convert strings to boolean values
		boolean isUpvote = false;
		if (voteType.equals("true"))
			isUpvote = true;
		boolean isComment = true;
		if (objectType.equals("false"))
			isComment = false;
		try {
			jdbcc.createVote(posterName, isUpvote, id, isComment);
		} catch (SQLException e) {
			Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
			//Added message attribute for SQL Exceptions
			message = "Unsuccessful vote. Either the poster does not exist or has already voted";
		}

		req.setAttribute("message", message);
		if (!isComment) {
			req.setAttribute("topicName", topicName);
			getPageThreads(req, res);
		} else {
			req.setAttribute("threadID", threadID);
			req.setAttribute("title", title);
			getThreadComments(req, res);
		}

	}

	private void createComment(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String text = req.getParameter("text");
		String threadID = req.getParameter("threadID");
		String posterName = req.getParameter("posterName");
		String title = req.getParameter("title");
        String message = null;
		try {
			jdbcc.createComment(text, posterName, threadID);
            message = "Your comment has been successfully posted!";
		} catch (SQLException e) {
			Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
            message = "Comment not posted";
		}
		req.setAttribute("message", message);
		req.setAttribute("title", title);
		req.setAttribute("threadID", threadID);
		getThreadComments(req, res);

	}

	private void createThread(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String threadTitle = req.getParameter("title");
		String text = req.getParameter("text");
		String topicName = req.getParameter("topicName");
		String posterName = req.getParameter("posterName");
        String message = null;
		try {
			jdbcc.createThread(threadTitle, text, topicName, posterName);
            message = threadTitle + " has been created in " + topicName + ".";
		} catch (SQLException e) {
			Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
            message = "Thread not created";
		}
		req.setAttribute("message", message);
		req.setAttribute("topicName", topicName);
		getPageThreads(req, res);
	}

	private void createPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String topicName = req.getParameter("topicName");
		String posterName = req.getParameter("posterName");
        String message = null;
		try {
			jdbcc.createPage(topicName, posterName);
            message = topicName + " has been created.";
		} catch (SQLException e) {
			Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
            message = "Page not created";
		}
		req.setAttribute("message", message);
		getAllPages(req, res);
	}

	private void createUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String adminPass = req.getParameter("adminPass");
		String message;
		if (adminPass.equals("admin")) {
			try {
				jdbcc.createUser(userName, false);
				message = "Welcome to DiabloDB, " + userName;
			} catch (SQLException e) {
				message = "User not created.";
				Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE, null, e);
			}
		} else {
			message = "Invalid admin password";
		}

		req.setAttribute("message", message);
		String nextJsp = "/jsp/create_user.jsp";
		RequestDispatcher d = getServletContext().getRequestDispatcher(nextJsp);
		d.forward(req, res);
	}

	@Override
	// This processes PUT requests
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null) {
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
		// jdbcc.adminLockThread(threadID, posterName);
		// } catch (SQLException e){
		// Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE,
		// null, e);
		// }
		// String message = "Thread "+threadID+" is now locked. No users can
		// post in this thread.";
		// req.setAttribute("message", message);
		// String nextJsp = "/jsp/thread.jsp";
		// RequestDispatcher d =
		// getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);
	}

	private void promotePosterToAdmin(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// String posterName = req.getParameter("posterName");
		// try {
		// jdbcc.promotePosterToAdmin(posterName);
		// } catch (SQLException e){
		// Logger.getLogger(PosterServlet.class.getName()).log(Level.SEVERE,
		// null, e);
		// }
		// String message = "Poster "+posterName+" is now an admin.";
		// req.setAttribute("message", message);
		// String nextJsp = "/jsp/user.jsp";
		// RequestDispatcher d =
		// getServletContext().getRequestDispatcher(nextJsp);
		// d.forward(req, res);
	}

}
