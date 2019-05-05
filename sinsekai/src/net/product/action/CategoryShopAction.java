package net.product.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.action.Action;
import net.action.ActionForward;
import net.product.db.CodexCategoryBean;
import net.product.db.CodexCategoryDAO;
import net.product.db.ProductBean;
import net.product.db.ProductDAO;

public class CategoryShopAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CodexCategoryDAO codexCategoryDAO = new CodexCategoryDAO();
		ProductDAO productDAO = null;
		
		CodexCategoryBean codexCategoryBean = new CodexCategoryBean();
		List<ProductBean> productBeans = null;
		
		codexCategoryBean.setCategoryName(request.getParameter("categoryName"));
		codexCategoryBean = codexCategoryDAO.getCategorycode(codexCategoryBean); // Find categoryCode as categoryName.
		codexCategoryDAO.close();
		if(codexCategoryBean == null) {
			System.err.println("ERROR - Failed get the categoryCode");
			return null;
		}
		
		productDAO = new ProductDAO();
		productBeans = productDAO.getProductsAsCategorycode(codexCategoryBean); // Find Products as categoryCode.
		productDAO.close();
		if(productBeans == null) {
			System.err.println("ERROR - Failed get the products");
			return null;
		}
		
		request.setAttribute("productBeans", productBeans); // Put the result.
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("."); // set at after.
		return forward;
	}

}