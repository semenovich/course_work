package dao.connectionpool;

import exception.ConnectionPoolException;
import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger logger = org.apache.log4j.Logger.getLogger(ConnectionPool.class);

    private static ConnectionPool instance;

    private static final int DEFAULT_POOL_SIZE = 5;

    private static Lock lock;
    private static AtomicBoolean isInitialized;

    private BlockingQueue<Connection> freeConnectionQueue;
    private BlockingQueue<Connection> busyConnectionQueue;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    static {
        lock = new ReentrantLock();
        isInitialized = new AtomicBoolean();
    }

    private ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getValue(DBParameterList.DATABASE_DRIVER);
        this.url = dbResourceManager.getValue(DBParameterList.DATABASE_URL);
        this.user = dbResourceManager.getValue(DBParameterList.DATABASE_USER);
        this.password = dbResourceManager.getValue(DBParameterList.DATABASE_PASSWORD);

        try {
            this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameterList.DATABASE_POOL_SIZE));
            initPoolData();
        } catch (NumberFormatException e) {
            poolSize = DEFAULT_POOL_SIZE;
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPool exception into ConnectionPool", e);
        }
    }

    /**
     * Returns the instance of the ConnectionPool.
     *
     * @return instance of the ConnectionPool.
     */
    public static ConnectionPool getInstance() {
        if (!isInitialized.get()) {
            lock.lock();
            try {
                if (!isInitialized.get()) {
                    instance = new ConnectionPool();
                    isInitialized.set(true);
                }
            } finally {
                lock.unlock();
            }
        }

        return instance;
    }

    /**
     * Returns a connection for working with database.
     *
     * @return connection for working with database.
     * @throws ConnectionPoolException - if an error occurred with connecting to database.
     */
    public Connection getConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = freeConnectionQueue.take();
            busyConnectionQueue.add(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Error connecting to the data source.", e);
        }
        return connection;
    }

    public void closeConnection(){
        try {
            freeConnectionQueue.add(busyConnectionQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initPoolData() throws ConnectionPoolException {
        try {
            Class.forName(driverName);
            busyConnectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
            freeConnectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                System.out.println(driverName+" "+url+" "+user+" "+password);
                Connection connection = DriverManager.getConnection(url, user, password);
                freeConnectionQueue.add(connection);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("SQLException in ConnectionPool", e);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Can't find database driver class", e);
        }
    }
}
