package com.mycommerce.project.servlet;

import com.mycommerce.project.dao.DaoFactory;
import com.mycommerce.project.dao.base.ProductDao;
import com.mycommerce.project.dao.base.UserDao;
import com.mycommerce.project.model.Product;
import com.mycommerce.project.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(ListShowBasketServlet.URL)
public class ListShowBasketServlet extends HttpServlet {

    public final static String URL = "/auth/list-show-basket";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Fetching all productss
        HttpSession session = req.getSession();
        UserDao userDao = DaoFactory.getUserDao();
        Long userId = Long.parseLong((String) session.getAttribute("id"));
        User user = userDao.findById(userId);

        // Passing data in view      
        req.setAttribute("basket", user.getBasket());

        // Forwarding/Displaying listProduct JSP
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/listShowBasket.jsp");
        rd.forward(req, resp);

    }
}
