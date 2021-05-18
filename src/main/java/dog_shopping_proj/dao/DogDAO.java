package dog_shopping_proj.dao;

import java.util.List;

import dog_shopping_proj.dto.Dog;

public interface DogDAO {
	List<Dog> selectDogList();
	
	Dog selectDog(int id);
	
	int updateReadCount(int id);
	
	int insertDog(Dog dog);
}
