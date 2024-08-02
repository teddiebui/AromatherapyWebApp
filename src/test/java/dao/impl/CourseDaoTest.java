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
 * This class is to test the functionality of
 * CourseDao
 */
public class CourseDaoTest extends AbstractDaoTest{
	
	private static CrudDAO dao;
	
	/**
	 * Setup the CourseDao object using injected
	 * dataSource from super class
	 */
	@BeforeClass
	public static void init() {
		dao = new CourseDao(dataSource);
	}
	/**
	 * Test the method getAll() of CourseDao
	 * Expected result: non-empty list of Course objects
	 * @throws Exception 
	 */
	@Test
	public void testGetAll_returnList() throws Exception {
		List<Model> courseList = dao.getAll();
		
		for (Model model: courseList) {
			System.out.println(model.toString());
		}
		assertTrue(!courseList.isEmpty());
	}
	
	/**
	 * Test the method get() of CourseDao
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
	 * Test the method get() of CourseDao
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
