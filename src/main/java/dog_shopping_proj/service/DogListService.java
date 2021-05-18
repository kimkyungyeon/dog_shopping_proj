package dog_shopping_proj.service;

import java.sql.Connection;
import java.util.List;

import dog_shopping_proj.dao.impl.DogDAOImpl;
import dog_shopping_proj.ds.JndiDS;
import dog_shopping_proj.dto.Dog;

public class DogListService {
	DogDAOImpl dao = DogDAOImpl.getInstance();
	Connection con = JndiDS.getConnection();
	
	public DogListService() {
		dao.setCon(con);
	}
	
	public List<Dog> getDogList(){
		List<Dog> dogList = dao.selectDogList();
		return dogList;
	}
	
	
}
