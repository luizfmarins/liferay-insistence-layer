package br.marins.insistence.layer.jdbc;

import br.marins.insistence.layer.connection.ConnectionWrapper;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

public abstract class InsistenceLayerDriver extends DriverDelegate {

  public InsistenceLayerDriver(Driver delegate) {
    super(delegate);
  }

  @Override
  public Connection connect(String url, Properties info) throws SQLException {
    Connection realConnection = super.connect(url, info);
    return new ConnectionWrapper(realConnection);
  }
}