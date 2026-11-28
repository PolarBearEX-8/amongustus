package net.minecraft.client.resources;

import java.util.List;

public interface IReloadableResourceManager extends IResourceManager {
   void registerReloadListener(IResourceManagerReloadListener listener);

   void reloadResources(List list);
}
