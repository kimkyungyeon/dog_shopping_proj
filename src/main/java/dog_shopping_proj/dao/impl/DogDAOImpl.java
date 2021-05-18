package dog_shopping_proj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dog_shopping_proj.JdbcUtil;
import dog_shopping_proj.dao.DogDAO;
import dog_shopping_proj.dto.Dog;

public class DogDAOImpl implements DogDAO {
	private static final DogDAOImpl instance = new DogDAOImpl();
	private Connection con;

	public void setCon(Connection con) {
		this.con = con;
	}

	private DogDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public static DogDAOImpl getInstance() {
		return instance;
	}

	@Override
	public List<Dog> selectDogList() {
		String sql = "select id, kind, price, image, " + "country, height, weight, content, readcount " + "from dog";
		try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Dog> list = new ArrayList<Dog>();
				do {
					list.add(getDog(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Dog getDog(ResultSet rs) throws SQLException {
		// id, kind, price, image, country, height, weight, content, readcount
		int id = rs.getInt("id");
		String kind = rs.getString("kind");
		int price = rs.getInt("price");
		String image = rs.getString("image");
		String country = rs.getString("country");
		int height = rs.getInt("height");
		int weight = rs.getInt("weight");
		String content = rs.getString("content");
		int readCount = rs.getInt("readcount");

		return new Dog(id, kind, price, image, country, height, weight, content, readCount);
	}

	@Override
	public Dog selectDog(int id) {
		String sql = "select id, kind, price, image, " + "country, height, weight, content, readcount " + "from dog "
				+ "where id = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getDog(rs);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateReadCount(int id) {
		String sql = "update dog set readcount = readcount+1 where id = ?";
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insertDog(Dog dog) {
		String sql = "insert into dog "
				+ " (kind, price, image, country, "
				+ "height, weight, content) "
				+ "values ( ? , ? , ? , ? , ? , ? , ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, dog.getKind());
			pstmt.setInt(2, dog.getPrice());
			pstmt.setString(3, dog.getImage());
			pstmt.setString(4, dog.getCountry());
			pstmt.setInt(5, dog.getHeight());
			pstmt.setInt(6, dog.getWeight());
			pstmt.setString(7, dog.getContent());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
