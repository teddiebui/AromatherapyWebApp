package dao;

import java.util.List;

import model.Model;

public interface CrudDAO {
	/**
	 * Returns a list of all models.
	 * @return list of models or empty list by default
	 * @throws Exception
	 */
	List<Model> getAll() throws Exception;
	
	/**
	 * Finds model by id.
	 * 
	 * @param id of expected model
	 * @return a model or null by default
	 * @throws Exception
	 */
	Model findById(int id) throws Exception;
	
	/**
	 * Create a model and persist to database.
	 * 
	 * 
	 * @param model
	 * @return id
	 * @throws Exception
	 * 
	 * 
	 */
	default int create(Model model) throws Exception {
		return 0;
	}
	
	/**
	 * Update a model and persist to database.
	 * 
	 * @param model
	 * @throws Exception
	 */
	default void update(Model model) throws Exception {}
	
	/**
	 * Delete a model from database.
	 * 
	 * @param id
	 * @throws Exception
	 */
	default void delete(int id) throws Exception {}
}
