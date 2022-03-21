package com.mycommerce.project.servlet;

import com.mycommerce.project.dao.DaoFactory;
import com.mycommerce.project.dao.base.CategoryDao;
import com.mycommerce.project.dao.base.ProductDao;
import com.mycommerce.project.dao.exception.UnknownProductException;
import com.mycommerce.project.model.Category;
import com.mycommerce.project.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ShowCategoryServlet.URL)
public class ShowCategoryServlet extends HttpServlet {

    public static final String URL = "/auth/admin/category-details";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");

        try {
            Long id = Long.parseLong(idStr);
            CategoryDao categoryDao = DaoFactory.getCategoryDao();
            Category category = categoryDao.findById(id);

            req.setAttribute("category", category);

        } catch (NumberFormatException e) {
            req.setAttribute("ERROR_TYPE_ID_PRODUCT", true);
        } catch (UnknownProductException e) {
            req.setAttribute("ERROR_UNKNOWN_PRODUCT", true);
        } finally {
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/showCategory.jsp");
            rd.forward(req, resp);
        }
    }
}
