package dog_shopping_proj.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dog_shopping_proj.dto.Cart;

public class DogCartSearchService {
	
	public List<Cart> getCartSearchList(int start_money, int end_money, 
			HttpServletRequest request){
		HttpSession session = request.getSession();
		List<Cart> oldCartList = (List<Cart>)session.getAttribute("cartList");
		List<Cart> cartList = new ArrayList<Cart>();
		
		for(int i = 0; i<oldCartList.size();i++) {
			if(oldCartList.get(i).getPrice()>=start_money &&
					oldCartList.get(i).getPrice()<=end_money) {
				cartList.add(oldCartList.get(i));
			}
		}
		return cartList;
	}
}
