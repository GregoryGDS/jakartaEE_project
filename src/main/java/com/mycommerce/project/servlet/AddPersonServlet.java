package com.mycommerce.project.servlet;


import com.mycommerce.project.dao.DaoFactory;
import com.mycommerce.project.dao.base.AdminDao;
import com.mycommerce.project.dao.base.CategoryDao;
import com.mycommerce.project.dao.base.UserDao;
import com.mycommerce.project.model.Admin;
import com.mycommerce.project.model.Category;
import com.mycommerce.project.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-person")
public class AddPersonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/addPerson.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminDao adminDao = DaoFactory.getAdminDao();
        UserDao userDao = DaoFactory.getUserDao();

        String pName = req.getParameter("pName");
        String pType = req.getParameter("pType");

        try {
            if(pType.equals("admin")){
                adminDao.add(new Admin(pName));
            }
            if(pType.equals("user")){
                userDao.add(new User(pName));
            }

            resp.sendRedirect(req.getContextPath() + "/add-person");

        } catch (NumberFormatException e) {
            //TODO
        }
    }
}