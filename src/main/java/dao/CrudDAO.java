package dao;

import java.util.List;

import model.Model;

public interface CrudDAO {
	
	public List<Model> getAll() throws Exception;
	
	public Model findById(int id) throws Exception;
	
	public default void create(Model model) throws Exception {}
	
	public default void update(Model model) throws Exception {}
	
	public default void delete(Model model) throws Exception {}
}
