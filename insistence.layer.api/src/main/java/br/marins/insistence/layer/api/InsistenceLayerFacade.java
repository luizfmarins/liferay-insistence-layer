package br.marins.insistence.layer.api;

import br.marins.insistence.layer.connection.ConnectionWrapper;

public final class InsistenceLayerFacade {

  private InsistenceLayerFacade() {}

  public static void enableInsistenceLayer() {
    ConnectionWrapper.disableCommit();
  }

  public static void disableInsistenceLayer() {
    ConnectionWrapper.enableCommit();
  }

  public static void rollback() {
    ConnectionWrapper.rollbackConnection();
  }
}
