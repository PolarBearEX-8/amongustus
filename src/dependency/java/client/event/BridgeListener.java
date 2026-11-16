package client.event;

import client.util.DistanceTransformer;
import java.io.IOException;
import net.minecraft.client.resources.IResourceManager;

public interface BridgeListener {
   void applyFlag(boolean flag, boolean flag2);

   DistanceTransformer getDistanceTransformer();

   void updateState();

   int getCount();

   void applyIResourceManager(IResourceManager iResourceManager) throws IOException;
}
