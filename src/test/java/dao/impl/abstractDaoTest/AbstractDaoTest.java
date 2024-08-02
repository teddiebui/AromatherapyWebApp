package dao.impl.abstractDaoTest;


import javax.sql.DataSource;

import org.junit.BeforeClass;

import dao.CrudDAO;
import dao.impl.MockDataSource;

public abstract class AbstractDaoTest {

	protected static DataSource dataSource;

	@BeforeClass
	public static void avoidNamingCollision() throws Exception {
		dataSource = MockDataSource.getDataSource();
	}

}
