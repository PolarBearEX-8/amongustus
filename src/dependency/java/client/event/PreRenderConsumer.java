package client.event;

import client.util.ComplexStringEntry;
import client.util.HEADManager;

public interface PreRenderConsumer {
   void applyHEADManager(HEADManager hEADManager, ComplexStringEntry complexStringEntry);

   void applyHEADManager(HEADManager hEADManager, Exception exception);
}
