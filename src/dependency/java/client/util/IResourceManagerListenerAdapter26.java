package client.util;

import client.render.AllocateTextureMapManager;
import net.minecraft.client.resources.IResourceManager;

public class IResourceManagerListenerAdapter26 extends ListenerAdapter26 {
   public IResourceManagerListenerAdapter26() {
      this.applyIResourceManager((IResourceManager)null);
   }

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) {
      int[] countArray = AllocateTextureMapManager.resolveIndexArray(1, -1);
      AllocateTextureMapManager.applyDistanceTransformer(this.getDistanceTransformer(), countArray, 1, 1, false, false);
   }
}
