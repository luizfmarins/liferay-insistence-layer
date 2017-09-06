package br.marins.insistence.layer.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionWrapper extends ConnectionDelegate {

  private static boolean IS_INSISTENT = false;
  private static final List<Connection> connections = new ArrayList<>();

  public ConnectionWrapper(Connection delegate) {
    super(delegate);
    connections.add(this);
  }

  @Override
  public void setAutoCommit(boolean autoCommit) throws SQLException {}

  @Override
  public boolean getAutoCommit() throws SQLException { return false; }

  @Override
  public void commit() throws SQLException {
    if (isInsistent())
      System.out.println("----> Fake commit");
    else
      super.commit();
  }

  @Override
  public void close() throws SQLException {
    if (!isInsistent())
      super.close();
  }

  private boolean isInsistent() {
    String property = System.getProperty("insistence.layer.enabled");
    if (property == null || property.isEmpty()) return false;

    return Boolean.valueOf(property);
  }

  public static void enableCommit() {
    System.setProperty("insistence.layer.enabled", "false");
  }

  public static void disableCommit() {
    System.setProperty("insistence.layer.enabled", "true");
  }

  public static void rollbackConnection() {
    connections.stream().forEach(connection -> {
      try {
        if (!connection.isClosed())
          connection.rollback();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    });
  }
}