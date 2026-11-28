package net.minecraft.client.resources;

import java.util.UUID;
import net.minecraft.util.ResourceLocation;

public class DefaultPlayerSkin {
   private static final ResourceLocation TEXTURE_STEVE = new ResourceLocation("textures/entity/steve.png");
   private static final ResourceLocation TEXTURE_ALEX = new ResourceLocation("textures/entity/alex.png");

   public static String getSkinType(UUID uuid) {
      return isSlimSkin(uuid) ? "slim" : "default";
   }

   private static boolean isSlimSkin(UUID uuid) {
      return (uuid.hashCode() & 1) == 1;
   }

   public static ResourceLocation getDefaultSkin(UUID uuid) {
      return isSlimSkin(uuid) ? TEXTURE_ALEX : TEXTURE_STEVE;
   }

   public static ResourceLocation getDefaultSkinLegacy() {
      return TEXTURE_STEVE;
   }
}
