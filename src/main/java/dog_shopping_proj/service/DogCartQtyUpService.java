package dog_shopping_proj.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dog_shopping_proj.dto.Cart;

public class DogCartQtyUpService {
	public void upCartQty(String kind, HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<Cart> cartList = (List<Cart>)session.getAttribute("cartList");
		
		for(int i =0; i<cartList.size();i++) {
			if(cartList.get(i).getKind().equals(kind)) {
				cartList.get(i).setQty(cartList.get(i).getQty()+1);
			}
		}
	}
}
