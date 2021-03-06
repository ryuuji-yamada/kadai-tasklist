package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task_DTO;
import utils.DBUtil_DAO;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    EntityManager em = DBUtil_DAO.createEntityManager();


	    //該当のIDのタスク内容１件のみをデータベースから取得
	    Task_DTO t = em.find(Task_DTO.class,Integer.parseInt(request.getParameter("id")));

	    em.close();


	    //タスク内容とセッションIDをリクエストスコープに登録
	    request.setAttribute("task", t);
	    request.setAttribute("_token", request.getSession().getId());


	    //タスク内容のIDをセッションスコープに登録
	    request.getSession().setAttribute("task_id", t.getId());


	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/edit.jsp");
	    rd.forward(request, response);

	}

}
