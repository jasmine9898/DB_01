package BaseUtil;

/**
 * WebBase: utilities and a spring & struts wrappered framework for MVC development, by networkbench.
 */


import com.mchange.v2.c3p0.DataSources;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Proxool implementation of DataSource.
 * @author BurningICE
 *
 */
public class C3p0DataSource implements DataSource {

    private String driverClass;
	private String connectUrl;
	private String username;
	private String password;
    
    public C3p0DataSource(){}
    
    public C3p0DataSource(String driverClass, String connectUrl, String username, String password) {
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

	/**
     * 从DataSource中获取一个数据库连接
     */
    public Connection getConnection() throws SQLException {
    	try {
			Class.forName(this.driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}   
		DataSource unpooled =
			DataSources.unpooledDataSource(this.connectUrl, this.username, this.password);
		DataSource pooled = DataSources.pooledDataSource( unpooled );
		Connection conn = pooled.getConnection();
		return conn; 
    }

	public Connection getConnection(String username, String password) throws SQLException {
		return null;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
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

