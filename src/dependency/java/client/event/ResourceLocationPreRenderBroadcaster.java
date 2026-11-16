package client.event;

import client.util.CapeofManager;
import client.util.TimeHelper;
import java.awt.image.BufferedImage;
import java.lang.ref.WeakReference;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationPreRenderBroadcaster implements PreRenderBroadcaster {
   public final ResourceLocation resourceLocation;
   public final WeakReference weakReference;

   public ResourceLocationPreRenderBroadcaster(AbstractClientPlayer player, ResourceLocation location) {
      this.weakReference = new WeakReference<>(player);
      this.resourceLocation = location;
   }

   @Override
   public void updateState() {
      AbstractClientPlayer player = (AbstractClientPlayer)this.weakReference.get();
      if (player != null) {
         player.setResourceLocation(this.resourceLocation);
      }
   }

   @Override
   public BufferedImage resolveBufferedImage(BufferedImage bufferedImage) {
      return CapeofManager.getBufferedImage(bufferedImage);
   }
}
