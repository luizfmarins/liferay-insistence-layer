package br.marins.insistence.layer.connection;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionWrapper extends ConnectionDelegate {

  private static boolean IS_INSISTENT = false;

  public ConnectionWrapper(Connection delegate) {
    super(delegate);
  }

  @Override
  public void setAutoCommit(boolean autoCommit) throws SQLException {}

  @Override
  public boolean getAutoCommit() throws SQLException { return false; }

  @Override
  public void commit() throws SQLException {
    if (IS_INSISTENT)
      System.out.println("----> Fake commit");
    else
      super.commit();
  }

  public static void enableCommit() {
    IS_INSISTENT = false;
  }

  public static void disableCommit() {
    IS_INSISTENT = true;
  }
}