package com.spring.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class OpeSQLTemplate {
	@Resource(name = "maslowDataSource")
	private DataSource dataSource;
	private static JdbcTemplate jdbcTemplate;

	private synchronized JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null)
			return new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}

	public int insertRow(String sql) {
		try {
			return getJdbcTemplate().update(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void saveRow(String sql, Object[] args) {
		try {
			getJdbcTemplate().update(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertBatch(String[] sqls) {
		try {
			getJdbcTemplate().batchUpdate(sqls);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据sql和对象类型，直接返回该对象列表，对象只能为java的基本数据对象
	 * 
	 * @param sql
	 * @param elementType
	 * @return
	 */
	public <T> List<T> queryForList(String sql, Class<T> elementType) {
		List<T> list = getJdbcTemplate().queryForList(sql, elementType);
		return list;
	}

	/**
	 * 根据sql和对象类型
	 * 
	 * @param sql
	 * @param elementType
	 * @return
	 */
	public List<Map<String, Object>> queryForList(String sql) {
		List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql);
		return list;
	}

	/**
	 * 根据sql和对象类型
	 * 
	 * @param sql
	 * @param elementType
	 * @return
	 */
	public List<Map<String, Object>> queryForList(String sql, Object[] args) {
		List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql,
				args);
		return list;
	}

	/**
	 * 获取对象
	 * 
	 * @param sql
	 * @param elementType
	 * @return
	 */
	public <T> T queryForObject(String sql, Class<T> elementType) {
		T t = getJdbcTemplate().queryForObject(sql, elementType);
		return t;
	}

	/**
	 * 获取对象
	 * 
	 * @param sql
	 * @param elementType
	 * @return
	 */
	public <T> T queryForObject(String sql, Class<T> elementType, Object[] args) {
		T t = getJdbcTemplate().queryForObject(sql, elementType, args);
		return t;
	}

	// ////////////////////////////下面定义jdbc的forObject方法，匹配传入对象的类型///////////////////////////////////////////
	// /////////////////////////////////需要传入对象的每个属性和sql的查询的字段名（别名一致）///////////////////////////////////
	// ///////////////////////////////////注意：参数中，class最好是public的，如果是package的，需要在统一包内////////////////////
	/**
	 * 获取对象
	 * 
	 * @param sql
	 * @param elementType
	 * @return
	 */
	public <T> T queryForOwnObject(String sql, Class<T> elementType,
			Object[] args) {
		return getJdbcTemplate().queryForObject(sql, args,
				getRowMapper(elementType));
	}

	/**
	 * 获取对象
	 * 
	 * @param sql
	 * @param elementType
	 * @return
	 */
	public <T> List<T> queryForOwnList(String sql, Class<T> elementType,
			Object[] args) {
		return getJdbcTemplate().query(sql, args, getRowMapper(elementType));
	}

	private <T> RowMapper<T> getRowMapper(final Class<T> elementType) {
		RowMapper<T> mapper = new RowMapper<T>() {
			// 获取类的属性和setter
			Map<Field, Method> attrMethods = getObjectAttrs(elementType);

			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				try {
					T t = elementType.newInstance();
					for (Entry<Field, Method> entry : attrMethods.entrySet()) {
						String attrName = entry.getKey().getName();
						String fieldType = entry.getKey().getType()
								.getSimpleName();
						Method attrSetter = entry.getValue();
						// System.out.println("attrName:"+attrName+" attrSetter:"+attrSetter.getName()+"feild type:"+fieldType);
						// 反射调用，set属性
						if ("String".equals(fieldType)) {
							attrSetter.invoke(
									t,
									rs.getString(attrName) == null ? "" : rs
											.getString(attrName));
						} else if ("Date".equals(fieldType)) {
							attrSetter.invoke(t,
									rs.getDate(attrName) == null ? "1970-01-01"
											: rs.getDate(attrName));
						} else if ("Integer".equals(fieldType)
								|| "int".equals(fieldType)) {
							attrSetter.invoke(t, rs.getInt(attrName));
						} else if ("Long".equals(fieldType)
								|| "long".equals(fieldType)) {
							attrSetter.invoke(t, rs.getLong(attrName));
						} else if ("Double".equals(fieldType)
								|| "double".equals(fieldType)) {
							attrSetter.invoke(t, rs.getDouble(attrName));
						} else if ("Boolean".equals(fieldType)) {
							attrSetter.invoke(t, rs.getBoolean(attrName));
						}

					}
					return t;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				return null;
			}
		};

		return mapper;
	}

	/**
	 * 获取一个类的属性列表,key为属性名称，value为set方法
	 * 
	 * @param cla
	 * @return
	 */
	private static <T> Map<Field, Method> getObjectAttrs(Class<T> cla) {
		Field[] fields = cla.getDeclaredFields();
		Method[] methods = cla.getMethods();

		Map<Field, Method> result = new HashMap<Field, Method>();
		for (Field f : fields) {
			String attr = f.getName();
			if (!attr.contains("this$")) {
				for (Method m : methods) {
					if (m.getName().startsWith("set")
							&& m.getName().substring(3).equalsIgnoreCase(attr)) {
						result.put(f, m);
					}
				}
			}
		}
		return result;
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////批量提交测试/////////////////////////////////////////////////////////

	// 批量操作参数 rewriteBatchedStatements=true
	private static String url = "jdbc:mysql://192.168.2.202:3306/analytics?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&rewriteBatchedStatements=true";
	private static String user = "wasp";
	private static String password = "sankuai123";
	private static String driver = "com.mysql.jdbc.Driver";

	/**
	 * testMysqlBatchInsert
	 */
	public static void testMysqlBatchInsert() {
		long startTime = System.currentTimeMillis();

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into t1(value,ts3) values(?,1.0)";
		int batchNum = 1000;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			for (int i = 1; i <= 100000; i++) {
				pstmt.setInt(1, i);
				pstmt.addBatch();
				if (i % batchNum == 0) {
					pstmt.executeBatch();
					conn.commit();
				}
			}
			pstmt.executeBatch();
			conn.commit();

			System.out.println("mysql 批量插入数据：100000 用时(/ms)："
					+ (System.currentTimeMillis() - startTime));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != pstmt) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (null != conn) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * testMysqlInsert
	 */
	public static void testMysqlInsert() {
		long startTime = System.currentTimeMillis();

		Connection conn = null;

		PreparedStatement pstmt = null;
		String sql = "insert into t1(value,ts3) values(?,1.0)";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);

			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			for (int i = 1; i <= 100000; i++) {
				pstmt.setInt(1, i);
				pstmt.execute();
			}
			conn.commit();
			System.out.println("mysql 普通插入数据：100000 用时(/ms)："
					+ (System.currentTimeMillis() - startTime));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != pstmt) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (null != conn) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		testMysqlBatchInsert();

		// testMysqlInsert();
	}

}
