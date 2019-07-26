package by.ormedia.vote.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.ormedia.vote.dao.IItemDAO;
import by.ormedia.vote.entity.Item;

@Service
public class ItemService implements IItemService{

	@Autowired
	private IItemDAO itemDAO;
	
	@Override
	public boolean vote(long itemId) {
		try {
			Item item = itemDAO.getItemById(itemId);
			if(item==null)return false;
			item.vote();
			itemDAO.updateItem(item);
			return true;
		} catch (SQLException e) {
			return false;
		}
		
	}

}
