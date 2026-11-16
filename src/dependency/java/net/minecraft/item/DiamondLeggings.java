package net.minecraft.item;

import client.event.PreMotionSubscriber;
import client.event.PreRenderBroadcaster;
import client.util.SkinsManager;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.awt.image.BufferedImage;
import net.minecraft.util.ResourceLocation;

public class DiamondLeggings implements PreRenderBroadcaster {
   public final PreRenderBroadcaster preRenderBroadcaster;
   public final PreMotionSubscriber preMotionSubscriber;
   public final Type type;
   public final ResourceLocation resourceLocation;
   public final MinecraftProfileTexture minecraftProfileTexture;
   public final SkinsManager skinsManager;

   @Override
   public void updateState() {
      if (this.preRenderBroadcaster != null) {
         this.preRenderBroadcaster.updateState();
      }

      if (this.preMotionSubscriber != null) {
         this.preMotionSubscriber.applyType(this.type, this.resourceLocation, this.minecraftProfileTexture);
      }
   }

   @Override
   public BufferedImage resolveBufferedImage(BufferedImage bufferedImage) {
      BufferedImage bufferedImage2 = bufferedImage;
      if (this.preRenderBroadcaster != null) {
         bufferedImage2 = this.preRenderBroadcaster.resolveBufferedImage(bufferedImage);
      }

      return bufferedImage2;
   }

   public DiamondLeggings(
      SkinsManager skinsManager2, PreRenderBroadcaster preRenderBroadcaster2, PreMotionSubscriber preMotionSubscriber2, Type type2, ResourceLocation location, MinecraftProfileTexture minecraftProfileTexture2
   ) {
      this.skinsManager = skinsManager2;
      this.preRenderBroadcaster = preRenderBroadcaster2;
      this.preMotionSubscriber = preMotionSubscriber2;
      this.type = type2;
      this.resourceLocation = location;
      this.minecraftProfileTexture = minecraftProfileTexture2;
   }
}
