package dog_shopping_proj.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog_shopping_proj.dto.Cart;
import dog_shopping_proj.service.DogCartSearchService;

public class DogCartSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DogCartSearchService dogCartSearchService = new DogCartSearchService();
		
		int startMoney = Integer.parseInt(request.getParameter("startMoney"));
		int endMoney = Integer.parseInt(request.getParameter("endMoney"));
		List<Cart> cartList = dogCartSearchService.getCartSearchList(startMoney,endMoney,request);
		request.setAttribute("cartList", cartList);
		request.setAttribute("startMoney", startMoney);
		request.setAttribute("endMoney", endMoney);
		int totalMoney = 0;
		int money = 0 ;
		
		for(int i = 0; i<cartList.size(); i++) {
			money = cartList.get(i).getPrice()*cartList.get(i).getQty();
			totalMoney+= money;
		}
		
		request.setAttribute("totalMoney", totalMoney);
		ActionForward forward = new ActionForward("/dog/dogCartList.jsp",false);
		return forward;
	}

}
