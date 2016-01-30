package com.reni.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.reni.model.Area;

import static com.reni.data.constants.RENIDataConstants.*;
public class AreaRowMapper implements RowMapper<Area> {

	@Override
	public Area mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Area area = new Area();
		area.setAreaCode(rs.getString(AREA_CODE));
		area.setAreaName(rs.getString(AREA_NAME));
		return area;
	}


}
