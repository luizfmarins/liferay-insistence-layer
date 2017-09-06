package br.marins.insistence.layer.jdbc;

import br.marins.insistence.layer.connection.ConnectionWrapper;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class InsistenceLayerDriver extends DriverDelegate {

  public InsistenceLayerDriver(Driver delegate) {
    super(delegate);
  }

  private static ConnectionWrapper connection;

  @Override
  public Connection connect(String url, Properties info) throws SQLException {
    if (connection == null || connection.isClosed()) {
      Connection realConnection = super.connect(url, info);
      realConnection.setAutoCommit(false);
      connection = new ConnectionWrapper(realConnection);
    }
    return connection;
  }
}