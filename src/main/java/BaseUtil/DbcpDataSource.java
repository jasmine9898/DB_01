package BaseUtil;

/**
 * WebBase: utilities and a spring & struts wrappered framework for MVC development, by networkbench.
 */

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.impl.GenericObjectPool;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Proxool implementation of DataSource.
 * @author BurningICE
 *
 */
public class DbcpDataSource implements DataSource {

    private String driverClass;
	private String connectUrl;
	private String username;
	private String password;
    
    public DbcpDataSource(){}
    
    public DbcpDataSource(String driverClass, String connectUrl, String username, String password) {
		super();
		this.driverClass = driverClass;
		this.connectUrl = connectUrl;
		this.username = username;
		this.password = password;
	}

    public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getConnectUrl() {
		return connectUrl;
	}

	public void setConnectUrl(String connectUrl) {
		this.connectUrl = connectUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	///TODO to be implemented
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}
	
	///TODO to be implemented
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}
	
	/**
     * 从DataSource中获取一个数据库连接
     */
    public Connection getConnection() throws SQLException {
    	GenericObjectPool connectionPool = new GenericObjectPool(null);   
    	ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(this.connectUrl, this.username, this.password);   
    	PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,connectionPool,null,null,false,true);   
    	try {   
    		Class.forName(this.driverClass);
    		Class.forName("org.apache.commons.dbcp.PoolingDriver");
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();   
    	}   
    	PoolingDriver driver = null;   
    	try {   
    		driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
    	} catch (SQLException e) {
    		e.printStackTrace();   
    	}   
    	driver.registerPool("example",connectionPool);   
    	Connection conn =null;
    	try {   
    		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:example");
    	} catch (SQLException e) {
    		e.printStackTrace();   
    	}
		return conn; 
    }

	public Connection getConnection(String username, String password) throws SQLException {
		return null;
	}

	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {

	}

	public void setLoginTimeout(int seconds) throws SQLException {

	}

	public int getLoginTimeout() throws SQLException {
		return 0;
	}
}




