package client.util;

import client.setting.MovementBooleanProperty;
import java.util.Collection;

public abstract class SecondaryClientManager {
   public abstract String resolveText(String text);

   public abstract Collection getCollection();

   public abstract MovementBooleanProperty getMovementBooleanProperty();

   public abstract String getLabelText2();

   public abstract boolean isEnabled2();

   public boolean matchesSecondaryClientManager(SecondaryClientManager secondaryClientManager) {
      return secondaryClientManager == null ? false : this == secondaryClientManager;
   }

   public abstract MovementBooleanProperty getMovementBooleanProperty2();

   public abstract boolean isEnabled();
}
