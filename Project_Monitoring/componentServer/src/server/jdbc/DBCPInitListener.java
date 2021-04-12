package server.jdbc;

import java.sql.DriverManager;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;


public class DBCPInitListener {
	private JdbcPoolConfig poolConfig;
	
	public DBCPInitListener() {
		poolConfig = new JdbcPoolConfig();
	}
	
	public DBCPInitListener(JdbcPoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}
	
	public void DBCPStart() {
		System.out.println("[DBCPInitListener] ===> DBCPStart");
		loadJDBCDriver(poolConfig);
		initConnectionPool(poolConfig);
	}

	private void loadJDBCDriver(JdbcPoolConfig poolConfig) {
		System.out.println("[DBCPInitListener] ===> loadJDBCDriver");
		String driverClass = poolConfig.getJdbcDriver();
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("fail to load JDBC Driver", ex);
		}
	}

	private void initConnectionPool(JdbcPoolConfig poolConfig) {
		System.out.println("[DBCPInitListener] ===> initConnectionPool");
		try {
			String jdbcUrl = poolConfig.getJdbcUrl();
			String username = poolConfig.getDbUser();
			String pw = poolConfig.getDbPass();
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, username, pw);
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			String validationQuery = poolConfig.getValidationQuery();
			if(validationQuery != null && !validationQuery.isEmpty()) {
				poolableConnFactory.setValidationQuery(validationQuery);
			}
			GenericObjectPoolConfig objectPoolConfig = new GenericObjectPoolConfig();
			objectPoolConfig.setTimeBetweenEvictionRunsMillis(1000L*60L*5L);
			objectPoolConfig.setTestWhileIdle(true);
			int minIdle = Integer.parseInt(poolConfig.getMinIdle());
			objectPoolConfig.setMinIdle(minIdle);
			int maxTotal = Integer.parseInt(poolConfig.getMaxTotal());
			objectPoolConfig.setMaxTotal(maxTotal);
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory, objectPoolConfig);
			poolableConnFactory.setPool(connectionPool);
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			String poolName = poolConfig.getPoolName();
			driver.registerPool(poolName, connectionPool);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
