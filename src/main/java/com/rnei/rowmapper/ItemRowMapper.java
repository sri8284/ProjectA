package com.rnei.rowmapper;

import static com.rnei.service.constants.RENIDataConstants.CURRENT_RATE;
import static com.rnei.service.constants.RENIDataConstants.ITEM_CODE;
import static com.rnei.service.constants.RENIDataConstants.ITEM_NAME;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rnei.model.Item;

public class ItemRowMapper implements RowMapper<Item> {

	@Override
	public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Item item = new Item();
		item.setItemCode(rs.getString(ITEM_CODE));
		item.setItemName(rs.getString(ITEM_NAME));
		item.setCurrentRate(rs.getFloat(CURRENT_RATE));
		return item;
	}

}
