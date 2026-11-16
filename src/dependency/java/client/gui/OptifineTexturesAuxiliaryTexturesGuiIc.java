package client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class OptifineTexturesAuxiliaryTexturesGuiIc extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public OptifineTexturesAuxiliaryTexturesGuiIc(int count, int count2, int count3) {
      super(count, count2, count3, 22, 20, "");
   }

   @Override
   public void applyMinecraftByMinecraft(Minecraft mc, int count, int count2) {
      if (this.enabled2) {
         super.applyMinecraftByMinecraft(mc, count, count2);
         ResourceLocation location = new ResourceLocation("optifine/textures/icons.png");
         mc.getTextureManager().applyResourceLocation3(location);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         this.processAction(this.count + 3, this.count3 + 2, 0, 0, 16, 16);
      }
   }
}
