package client.event;

import client.util.InvalidOperationManager;
import java.util.Collection;
import java.util.UUID;

public interface AuxiliaryListener {
   Collection getCollection();

   double getDouble();

   boolean matchesInvalidOperationManager(InvalidOperationManager invalidOperationManager);

   Collection resolveCollection(int count);

   double getValue();

   void applyInvalidOperationManagerByInvalidOperation(InvalidOperationManager invalidOperationManager);

   void updateState();

   InvalidOperationManager get(UUID uuid);

   void applyDouble(double offset);

   void applyInvalidOperationManager(InvalidOperationManager invalidOperationManager);

   ExtendedListener getExtendedListener();
}
