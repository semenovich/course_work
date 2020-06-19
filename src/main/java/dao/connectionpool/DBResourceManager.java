package dao.connectionpool;

import java.util.ResourceBundle;

public class DBResourceManager {
    private static final String CONFIG_SQL_FILE = "config";

    private static final DBResourceManager instance = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle(CONFIG_SQL_FILE);

    /**
     * Returns the instance of the DBResourceManager.
     * @return instance of the DBResourceManager
     */
    public static DBResourceManager getInstance() {
        return instance;
    }

    /**
     * Returns a value of a connection pool parameter.
     * @param key - a name of a connection pool parameter.
     * @return value - a value of a connection pool parameter.
     */
    public String getValue(String key){
        return bundle.getString(key);
    }
}
