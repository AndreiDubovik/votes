package by.ormedia.vote.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import by.ormedia.vote.entity.Item;
import by.ormedia.vote.entity.Subject;

public interface IItemDAO {
	public Serializable addItem(Item item) throws SQLException;
	public boolean updateItem(Item item) throws SQLException;
	public boolean deleteItem(Item item) throws SQLException;
	public Item getItemById(long id) throws SQLException;
	public List<Item> getItemsBySubject(Subject subject) throws SQLException;
}
