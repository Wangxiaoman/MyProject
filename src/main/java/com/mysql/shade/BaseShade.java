package com.mysql.shade;

import java.util.List;
import java.util.Map;

public interface BaseShade {
	/**
	 * 增删改的方法
	 * @param sql
	 * @param args
	 * @return
	 */
	int executeUpdate(String sql,Object[] args);
	
	List<Map<String,Object>> executeQuery(String sql,Object[] args);
	
}
