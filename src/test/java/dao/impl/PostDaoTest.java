package dao.impl;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;


import org.junit.BeforeClass;
import org.junit.Test;

import dao.CrudDAO;
import dao.impl.abstractDaoTest.AbstractDaoTest;
import model.Model;
import model.Post;

/**
 * This test class is for testing the function of
 * PostDao class
 */
public class PostDaoTest extends AbstractDaoTest {
	private static CrudDAO dao;
	
	/**
	 * Set up DAO object with injected dataSource
	 * from it's super class
	 */
	@BeforeClass
	public static void init() {
		dao = new PostDao(dataSource);
	}
	
	/**
	 * Test the function "getAll"() of PostDao
	 * expected result: non-empty list of Post object
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		List<Model> postList = dao.getAll();
		postList.sort(new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				Timestamp post1 = (Timestamp)((Post)o1).getPostCreateTime();
				Timestamp post2 = (Timestamp)((Post)o2).getPostCreateTime();
				return post2.compareTo(post1);
			}
		});
		
		for (Model post : postList) {
			System.out.println((post.toString()));
		}
		assertTrue(!postList.isEmpty());
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
