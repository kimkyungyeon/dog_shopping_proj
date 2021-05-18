package dog_shopping_proj.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog_shopping_proj.dto.Dog;
import dog_shopping_proj.service.DogViewService;

public class DogViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DogViewAction()");
		DogViewService dogViewService = new DogViewService();
		int id = Integer.parseInt(request.getParameter("id"));
		Dog dog = dogViewService.getDogView(id);
		request.setAttribute("dog", dog);
		Cookie todayImageCookie = new Cookie("today"+id,dog.getImage());
		todayImageCookie.setMaxAge(60*60*24);
		response.addCookie(todayImageCookie);
		ActionForward forward = new ActionForward("dog/dogView.jsp", false);
		return forward;
	}

}
