/**
 * 
 */
package dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import dao.CrudDAO;
import dao.impl.abstractDaoTest.AbstractDaoTest;
import model.Model;

/**
 * This class is for testing the functionality
 * of EmployeeDao
 */
public class EmployeeDaoTest extends AbstractDaoTest{
	
	private static CrudDAO dao;

	/**
	 * Setup EmployeeDao object injected dataSource
	 * from it's parent
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void init() throws Exception {
		dao = new EmployeeDao(dataSource);
	}
	/**
	 * Test function getAll()
	 * Expected: return non-empty list 
	 * of Employee object
	 * @throws Exception 
	 */
	@Test
	public void testGetAll_returnList() throws Exception {
		List<Model> list = dao.getAll();
		
		for (Model model: list) {
			System.out.println(model.toString());
		}
		
		assertTrue(!list.isEmpty());
	}
	
	/**
	 * Test the method get() of EmployeeDao
	 * Parameter: int id: existing model id
	 * Expected result: model not null 
	 * @throws Exception 
	 */
	@Test
	public void testGetModel_validInputId_returnModel() throws Exception {
		Model model = dao.findById(1);
		System.out.println(model);
		assertTrue(model != null);
	}
	
	/**
	 * Test the method get() of EmployeeDao
	 * Parameter: int id: non-existing model id
	 * Expected result: null
	 * @throws Exception 
	 */
	@Test
	public void testGetModel_invalidInputId_returnNull() throws Exception {
		Model model = dao.findById(10);
		Model expected = null;
		assertTrue(model == expected);
	}

}
