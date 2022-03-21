package com.mycommerce.project.servlet;

import com.mycommerce.project.dao.DaoFactory;
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

@WebServlet(PayBasketServlet.URL)
public class PayBasketServlet extends HttpServlet {

    public final static String URL = "/pay-basket";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UserDao userDao = DaoFactory.getUserDao();
        Long userId = Long.parseLong((String) session.getAttribute("id"));
        User user = userDao.findById(userId);

        // Passing data in view
        req.setAttribute("user", user);
        req.setAttribute("basket", user.getBasket());
        int totalPrice = 0;
        for(Product p: user.getBasket()){
            totalPrice += p.getPrice();
        }

        req.setAttribute("totalPrice", totalPrice);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/payBasket.jsp");
        rd.forward(req, resp);
    }
}
