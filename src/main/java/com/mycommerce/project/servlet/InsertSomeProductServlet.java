package com.mycommerce.project.servlet;


import com.mycommerce.project.dao.DaoFactory;
import com.mycommerce.project.dao.base.CategoryDao;
import com.mycommerce.project.dao.base.ProductDao;
import com.mycommerce.project.model.Category;
import com.mycommerce.project.model.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth/admin/basic-insert")
public class InsertSomeProductServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CategoryDao categoryDao = DaoFactory.getCategoryDao();
        categoryDao.add(new Category("Fruits et légumes"));
        categoryDao.add(new Category("Produits laitiers"));
        categoryDao.add(new Category("Viande, poisson et fruits de mer"));
        categoryDao.add(new Category("Produits céréaliers et légumineuses"));

        ProductDao productDao = DaoFactory.getProductDao();
        productDao.add(new Product("Bouteille de lait","Bouteille 2L, lait demi-ecreme",
                2.5f,categoryDao.findById(2L)));

        productDao.add(new Product("Corn flakes","Paquet de 1kg",
                5f,categoryDao.findById(4L)));

        productDao.add(new Product("Grappe de raisin","250g",
                1f,categoryDao.findById(1L)));

        resp.sendRedirect(req.getContextPath() + ListProductServlet.URL );


    }
}
