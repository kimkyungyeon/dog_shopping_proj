package dog_shopping_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog_shopping_proj.service.DogCartQtyDownService;

public class DogCartQtyDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String kind = request.getParameter("kind");
		DogCartQtyDownService dogCartQtyDownService = new DogCartQtyDownService();
		dogCartQtyDownService.downCartQty(kind, request);
		ActionForward forward = new ActionForward("dogCartList.do",true);
		return forward;
	}

}
