package client.util;

import java.io.IOException;
import net.minecraft.client.resources.IResourceManager;

public final class VectorObserver implements Listener68Extension {
   @Override
   public void applyFlag(boolean flag, boolean flag2) {
   }

   @Override
   public void updateState2() {
      AnimManager.updateState3();
   }

   @Override
   public DistanceTransformer getDistanceTransformer() {
      return null;
   }

   @Override
   public void updateState() {
   }

   @Override
   public int getCount() {
      return 0;
   }

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) throws IOException {
   }
}
