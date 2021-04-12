package server.jdbc;

public class JdbcPoolConfig {
	private String jdbcDriver;
	private String jdbcUrl;
	private String dbUser;
	private String dbPass;
	private String validationQuery;
	private String minIdle;
	private String maxTotal;
	private String poolName;
	
	public JdbcPoolConfig() {
		this.jdbcDriver = "com.mysql.cj.jdbc.Driver";
		this.jdbcUrl = "jdbc:mysql://localhost:3306/device?characterEncoding=utf8&serverTimezone=UTC";
		this.dbUser = "jspexam";
		this.dbPass = "jsppw";
		this.validationQuery = "select 1";
		this.minIdle = "3";
		this.maxTotal = "30";
		this.poolName = "device";
	}

	public String getJdbcDriver() {
		return jdbcDriver;
	}

	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPass() {
		return dbPass;
	}

	public void setDbPass(String dbPass) {
		this.dbPass = dbPass;
	}

	public String getValidationQuery() {
		return validationQuery;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	public String getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(String minIdle) {
		this.minIdle = minIdle;
	}

	public String getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(String maxTotal) {
		this.maxTotal = maxTotal;
	}

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

}
