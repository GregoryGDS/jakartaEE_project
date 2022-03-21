package com.mycommerce.project.servlet;


import com.mycommerce.project.dao.DaoFactory;
import com.mycommerce.project.dao.base.CategoryDao;
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

@WebServlet("/auth/remove-from-basket")
public class RemoveFromBasketServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("productId");
        String indexListStr = req.getParameter("indexList");
        HttpSession session = req.getSession();
        try {
            ProductDao productDao = DaoFactory.getProductDao();
            UserDao userDao = DaoFactory.getUserDao();
            Long productId = Long.parseLong(idStr);
            Long userId = Long.parseLong((String) session.getAttribute("id"));

            User user = userDao.findById(userId);
            Product prod = productDao.findById(userId);
            //user.removeProduct(productDao.findById(productId));
            
            Integer indexList = Integer.parseInt(indexListStr);
            // si plusieurs prod identique dans le panier
            //prod.removeInBasket(indexList);
            
			System.out.println("indexListStr: "+indexListStr);
			System.out.println("indexList: "+indexList);

            
            System.out.println("index remove: "+indexList +" - valeur: "+user.getBasket().get(indexList).getName());
            for (Product prodB : user.getBasket()) {
				System.out.println("avant removeBasket: "+prodB.getName());
			}
            System.out.println("-----------------------------");
            user.removeBasket(indexList);
            for (Product prodB : user.getBasket()) {
				System.out.println("après removeBasket: "+prodB.getName());
			}
            //productDao.update(prod);
            userDao.update(user);
            
            resp.sendRedirect(req.getContextPath()+ListShowBasketServlet.URL);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
