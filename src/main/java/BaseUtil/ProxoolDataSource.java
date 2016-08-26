/**
 * WebBase: utilities and a spring & struts wrappered framework for MVC development, by networkbench.
 */
package BaseUtil;

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
public class ProxoolDataSource implements DataSource {
    private String poolAlias;
    
    public ProxoolDataSource(){}
    /**
     * @return Returns the poolAlias.
     */
    public String getPoolAlias() {
        return poolAlias;
    }
    /**
     * @param poolAlias The poolAlias to set.
     */
    public void setPoolAlias(String poolAlias) {
        this.poolAlias = poolAlias;
    }
    
    public ProxoolDataSource(String poolAlias) {
        this.poolAlias = poolAlias;
    }
	
	/**
     * 从DataSource中获取一个数据库连接
     */
    public Connection getConnection() throws SQLException {
        Connection connection = null;
        
        if(this.poolAlias == null || this.poolAlias.length() == 0 ){
            //未取到连接池名称
            return null;
        }
        
        //从proxool中取得连接
        try
        {
            connection = DriverManager.getConnection("proxool." + this.poolAlias);
        }catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        
        return connection;
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

