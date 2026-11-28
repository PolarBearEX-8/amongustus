package net.optifine.player;

import java.awt.image.BufferedImage;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.util.ResourceLocation;

public class CapeImageBuffer extends ImageBufferDownload {
   private AbstractClientPlayer player;
   private ResourceLocation resourceLocation;
   private boolean elytraOfCape;

   @Override
   public void skinAvailable() {
      if (this.player != null) {
         this.player.setLocationOfCape(this.resourceLocation);
         this.player.setElytraOfCape(this.elytraOfCape);
      }

      this.cleanup();
   }

   @Override
   public BufferedImage parseUserSkin(BufferedImage image) {
      BufferedImage image2 = CapeUtils.parseCape(image);
      this.elytraOfCape = CapeUtils.isElytraCape(image, image2);
      return image2;
   }

   public void cleanup() {
      this.player = null;
   }

   public CapeImageBuffer(AbstractClientPlayer clientPlayer, ResourceLocation location) {
      this.player = clientPlayer;
      this.resourceLocation = location;
   }

   public boolean isElytraOfCape() {
      return this.elytraOfCape;
   }
}
