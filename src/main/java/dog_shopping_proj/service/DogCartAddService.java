package dog_shopping_proj.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dog_shopping_proj.dao.impl.DogDAOImpl;
import dog_shopping_proj.ds.JndiDS;
import dog_shopping_proj.dto.Cart;
import dog_shopping_proj.dto.Dog;

public class DogCartAddService {
	private DogDAOImpl dao = DogDAOImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public DogCartAddService() {
		dao.setCon(con);
	}
	
	public Dog getCartDog(int id) {
		return dao.selectDog(id);
	}
	
	public void addCart(HttpServletRequest request, Dog cartDog) {
		HttpSession session = request.getSession();
		List<Cart> cartList = (List<Cart>)session.getAttribute("cartList");
		
		if(cartList == null) {
			cartList = new ArrayList<Cart>();
			session.setAttribute("cartList", cartList);
		}
		
		boolean isNewCart = true;
		
		for(int i = 0; i<cartList.size(); i++) {
			if(cartDog.getKind().equals(cartList.get(i).getKind())) {
				isNewCart = false;
				cartList.get(i).setQty(cartList.get(i).getQty()+1);
				break;
			}
		}
		
		if(isNewCart) {
			Cart cart = new Cart();
			cart.setImage(cartDog.getImage());
			cart.setKind(cartDog.getKind());
			cart.setPrice(cartDog.getPrice());
			cart.setQty(1);
			cartList.add(cart);
		}
	}
}
