package dog_shopping_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog_shopping_proj.service.DogCartRemoveService;

public class DogCartRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("dogCartRemoveAction");
		String[] kindArray = request.getParameterValues("remove");
		DogCartRemoveService dogCartRemoveService = new DogCartRemoveService();
		dogCartRemoveService.cartRemove(request,kindArray);
		ActionForward forward = new ActionForward("dogCartList.do",true);
		return forward;
	}

}
