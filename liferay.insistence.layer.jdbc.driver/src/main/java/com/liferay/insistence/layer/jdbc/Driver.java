package com.liferay.insistence.layer.jdbc;

import br.marins.insistence.layer.jdbc.InsistenceLayerDriver;
import com.liferay.portal.kernel.util.PropsUtil;
import java.util.Properties;

public class Driver extends InsistenceLayerDriver {

  public Driver() {
    super(initDelegate());
  }

  private static java.sql.Driver initDelegate() {
    try {
      Properties properties = PropsUtil.getProperties("jdbc.driver.", true);
      String delegateClassName = properties.getProperty("delegate.class");
      Class<?> delegateClass = Class.forName(delegateClassName);
      return (java.sql.Driver) delegateClass.newInstance();
    } catch (Exception ex) {
      throw new IllegalStateException(ex);
    }
  }
}