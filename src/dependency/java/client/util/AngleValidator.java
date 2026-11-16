package client.util;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;

public final class AngleValidator implements IResourceManagerReloadListener {
   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) {
      InternalPngManager.applyIResourceManager(iResourceManager);
   }
}
