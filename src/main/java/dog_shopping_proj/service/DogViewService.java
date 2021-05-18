package dog_shopping_proj.service;

import java.sql.Connection;
import java.sql.SQLException;

import dog_shopping_proj.dao.impl.DogDAOImpl;
import dog_shopping_proj.ds.JndiDS;
import dog_shopping_proj.dto.Dog;

public class DogViewService {
	private DogDAOImpl dao = DogDAOImpl.getInstance();
	private Connection con = JndiDS.getConnection();

	public DogViewService() {
		dao.setCon(con);
	}

	public Dog getDogView(int id) throws SQLException {
		Dog dog = null;
		try {
			con.setAutoCommit(false);
			dao.setCon(con);
			dao.updateReadCount(id);
			dog = dao.selectDog(id);

			JndiDS.commit(con);
		} catch (SQLException e) {
			JndiDS.rollback(con);
			e.printStackTrace();
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return dog;
	}

}
