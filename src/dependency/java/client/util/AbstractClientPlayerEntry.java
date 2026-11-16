package client.util;

import java.awt.image.BufferedImage;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;

public class AbstractClientPlayerEntry extends ServiceIntArrayEntry {
   public AbstractClientPlayer abstractClientPlayer;
   public ResourceLocation resourceLocation;
   public boolean enabled;

   @Override
   public void updateState() {
      if (this.abstractClientPlayer != null) {
         this.abstractClientPlayer.setResourceLocation(this.resourceLocation);
         this.abstractClientPlayer.setEnabled25(this.enabled);
      }

      this.updateState2();
   }

   public void updateState2() {
      this.abstractClientPlayer = null;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public BufferedImage resolveBufferedImage(BufferedImage bufferedImage) {
      BufferedImage bufferedImage2 = CapeofManager.getBufferedImage(bufferedImage);
      this.enabled = CapeofManager.matchesBufferedImage(bufferedImage, bufferedImage2);
      return bufferedImage2;
   }

   public AbstractClientPlayerEntry(AbstractClientPlayer player, ResourceLocation location) {
      this.abstractClientPlayer = player;
      this.resourceLocation = location;
   }
}
