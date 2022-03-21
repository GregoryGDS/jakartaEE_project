package com.mycommerce.project.servlet;


import com.mycommerce.project.dao.DaoFactory;
import com.mycommerce.project.dao.base.CategoryDao;
import com.mycommerce.project.dao.base.ProductDao;
import com.mycommerce.project.model.Category;
import com.mycommerce.project.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/auth/admin/add-product")
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryDao categoryDao = DaoFactory.getCategoryDao();
        List<Category> categoryList = categoryDao.getAll();
        
        req.setAttribute("categoryList", categoryList);
        System.out.println(categoryList);
        for (Category category : categoryList) {
            System.out.println("id: " +category.getId()+ " - name: "+category.getName());
		}
    	RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/addProduct.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("pName");
        String productContent = req.getParameter("pContent");
        String productPriceStr = req.getParameter("pPrice");
        String productCategotyStr = req.getParameter("pCategoryId");
        
        CategoryDao categoryDao = DaoFactory.getCategoryDao();
        Long idCategory = Long.parseLong(productCategotyStr);
        
        try {
            float productPrice = Float.parseFloat(productPriceStr);
            Product newProduct = new Product(productName, productContent, productPrice, categoryDao.findById(idCategory));
            ProductDao productDao = DaoFactory.getProductDao();
            productDao.add(newProduct);
            resp.sendRedirect(req.getContextPath()+ShowProductServlet.URL + "?id=" + newProduct.getId());

        } catch (NumberFormatException e) {
            //TODO
        }
    }
}
