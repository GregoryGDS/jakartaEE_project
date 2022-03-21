package com.mycommerce.project.servlet;

import com.mycommerce.project.dao.DaoFactory;
import com.mycommerce.project.dao.base.ProductDao;
import com.mycommerce.project.dao.base.UserDao;
import com.mycommerce.project.model.Product;
import com.mycommerce.project.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/auth/add-to-basket")
public class AddToBasket extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("productId");
        HttpSession session = req.getSession();
        try {
            ProductDao productDao = DaoFactory.getProductDao();
            UserDao userDao = DaoFactory.getUserDao();
            Long productId = Long.parseLong(idStr);
            Long userId = Long.parseLong((String) session.getAttribute("id"));

            User user = userDao.findById(userId);
            Product prod = productDao.findById(productId);
            //user.addBasket(prod);
            //userDao.update(user);
            prod.addInBasket(user);
            productDao.update(prod);

            resp.sendRedirect(req.getContextPath()+ListProductServlet.URL);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
