package dog_shopping_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog_shopping_proj.dto.Dog;
import dog_shopping_proj.service.DogCartAddService;

public class DogCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DogCartAddAction()");
		DogCartAddService dogCartAddService = new DogCartAddService();
		int id = Integer.parseInt(request.getParameter("id"));
		Dog cartDog = dogCartAddService.getCartDog(id);
		dogCartAddService.addCart(request, cartDog);
		ActionForward forward = new ActionForward("dogCartList.do", true);
		return forward;
	}

}
