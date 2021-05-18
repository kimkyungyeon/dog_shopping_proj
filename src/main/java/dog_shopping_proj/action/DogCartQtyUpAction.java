package dog_shopping_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog_shopping_proj.service.DogCartQtyUpService;

public class DogCartQtyUpAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String kind = request.getParameter("kind");
		DogCartQtyUpService dogCartQtyUpService = new DogCartQtyUpService();
		dogCartQtyUpService.upCartQty(kind, request);
		ActionForward forward = new ActionForward("dogCartList.do",true);
		return forward;
	}

}
