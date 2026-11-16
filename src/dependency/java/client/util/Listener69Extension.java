package client.util;

import java.util.List;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;

public interface Listener69Extension extends IResourceManager {
   void applyIResourceManagerReloadList(IResourceManagerReloadListener iResourceManagerReloadListener);

   void applyList(List list);
}
