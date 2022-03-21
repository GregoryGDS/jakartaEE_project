package com.mycommerce.project.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycommerce.project.dao.DaoFactory;
import com.mycommerce.project.dao.base.AdminDao;
import com.mycommerce.project.dao.base.UserDao;
import com.mycommerce.project.model.Admin;
import com.mycommerce.project.model.User;

import java.io.IOException;

@WebServlet(LoginServlet.URL)
public class LoginServlet extends HttpServlet {

    public final static String URL = "/login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //création admin 
        Admin admin = new Admin("admin");
        AdminDao adminDao = DaoFactory.getAdminDao();
               
        if (adminDao.findByName("admin") == null) {
        	adminDao.add(admin);
		}
        
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/login.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        AdminDao adminDao = DaoFactory.getAdminDao();
        UserDao userDao = DaoFactory.getUserDao();
        
        Admin admin = adminDao.findByName(username);
        User user = userDao.findByName(username);
        
        if (username != null) {
        	if(admin != null) {
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("user", admin.getName());
                httpSession.setAttribute("id", admin.getId());
                httpSession.setAttribute("isValid", String.valueOf(admin.getIsValid()));
                httpSession.setAttribute("type", "admin");

            }else if(user != null) {
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("user", user.getName());
                httpSession.setAttribute("id", String.valueOf(user.getId()));
                httpSession.setAttribute("isValid", String.valueOf(user.getIsValid()));
                httpSession.setAttribute("type", "user");
            }
        	
            resp.sendRedirect(req.getContextPath()+ListProductServlet.URL);
        } else {
            //TODO
        }

    }
}
