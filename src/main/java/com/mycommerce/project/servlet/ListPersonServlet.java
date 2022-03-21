package com.mycommerce.project.servlet;

import com.mycommerce.project.dao.DaoFactory;
import com.mycommerce.project.dao.base.AdminDao;
import com.mycommerce.project.dao.base.ProductDao;
import com.mycommerce.project.dao.base.UserDao;
import com.mycommerce.project.model.Admin;
import com.mycommerce.project.model.Product;
import com.mycommerce.project.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(ListPersonServlet.URL)
public class ListPersonServlet extends HttpServlet {

    public final static String URL = "/auth/admin/list-person";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Fetching all products
        UserDao userDao = DaoFactory.getUserDao();
        List<User> userList = userDao.getAll();

        AdminDao adminDao = DaoFactory.getAdminDao();
        List<Admin> adminList = adminDao.getAll();

        for (User u: userList) {
            System.out.println(u.getIsValid());
        }

        // Passing data in view
        req.setAttribute("userList", userList);
        req.setAttribute("adminList", adminList);


        // Forwarding/Displaying listProduct JSP
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/listPerson.jsp");
        rd.forward(req, resp);

    }
}
