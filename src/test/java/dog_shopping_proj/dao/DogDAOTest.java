package dog_shopping_proj.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import dog_shopping_proj.JdbcUtil;
import dog_shopping_proj.dao.impl.DogDAOImpl;
import dog_shopping_proj.dto.Dog;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DogDAOTest {
	private static DogDAOImpl dao = DogDAOImpl.getInstance();
	private static Connection con  = JdbcUtil.getConnection();
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao.setCon(con);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void test01SelectDogList() {
		System.out.println("01SelectDogList");
		List<Dog> list = dao.selectDogList();
		Assert.assertNotNull(list);
		
		for(Dog d : list) {
			System.out.println(d);
		}
	}

//	@Test
	public void test02SelectDog() {
		System.out.println("02SelectDog");
		Dog selDog = dao.selectDog(1);
		Assert.assertNotNull(selDog);
		System.out.println(selDog);
	}

//	@Test
	public void test03UpdateReadCount() {
		System.out.println("03UpdateReadCount");
		int res = dao.updateReadCount(1);
		Assert.assertEquals(res, 1);
		System.out.println(res);
	}

	@Test
	public void test04InsertDog() {
		System.out.println("04InsertDog");
		Dog newDog = new Dog("시바견",2000,"shiba.jpg","일본",1,20,"귀여움");
		int res = dao.insertDog(newDog);
		System.out.println(res);
		System.out.println(dao.selectDogList());
	}

}
