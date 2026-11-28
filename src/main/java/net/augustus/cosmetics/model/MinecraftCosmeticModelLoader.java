package net.augustus.cosmetics.model;

import net.augustus.cosmetics.render.CosmeticBufferedTexture;
import java.awt.image.BufferedImage;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class MinecraftCosmeticModelLoader extends CosmeticModelLoader {

   @Override
   public void registerModelTexture(String str, BufferedImage image) {
      ResourceLocation location = new ResourceLocation("morecosmetics/" + str);
      Minecraft.getMinecraft().getTextureManager().loadTexture(location, new CosmeticBufferedTexture(location, image));
   }

   public MinecraftCosmeticModelLoader() {
       super();
   }




}
