package com.reni.rowmapper;

import static com.reni.data.constants.RENIDataConstants.CURRENT_RATE;
import static com.reni.data.constants.RENIDataConstants.ITEM_DESC;
import static com.reni.data.constants.RENIDataConstants.ITEM_ID;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.reni.model.Item;

public class ItemRowMapper implements RowMapper<Item> {

	@Override
	public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Item item = new Item();
		item.setItemId(rs.getString(ITEM_ID));
		item.setItemDesc(rs.getString(ITEM_DESC));
		item.setCurrentRate(rs.getFloat(CURRENT_RATE));
		return item;
	}

}
