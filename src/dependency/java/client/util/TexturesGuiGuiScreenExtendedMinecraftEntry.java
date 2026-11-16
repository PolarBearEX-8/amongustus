package client.util;

import client.render.WorldRenderer;
import net.minecraft.client.gui.TexturesGuiGuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class TexturesGuiGuiScreenExtendedMinecraftEntry extends ExtendedMinecraftEntry {
   public int count15;
   public final TexturesGuiGuiScreen texturesGuiGuiScreen;

   @Override
   public void updateState2() {
   }

   @Override
   public void applyIndex(int count, boolean flag, int count2, int count3) {
      this.count15 = count;
      this.texturesGuiGuiScreen.renderElement();
      TexturesGuiGuiScreen.resolveInternalTexturesGuiIconsPngM(this.texturesGuiGuiScreen)
         .applyText(
            ((ResourceLocationRecord)TexturesGuiGuiScreen.getList3()
                  .get(TexturesGuiGuiScreen.resolveTexturesGuiGuiScreenExtended(this.texturesGuiGuiScreen).count15))
               .auxiliaryGsonEntry
               .toString()
         );
   }

   public TexturesGuiGuiScreenExtendedMinecraftEntry(TexturesGuiGuiScreen texturesGuiGuiScreen2) {
      super(texturesGuiGuiScreen2.mc, texturesGuiGuiScreen2.width, texturesGuiGuiScreen2.height, 80, texturesGuiGuiScreen2.height - 32, 38);
      this.texturesGuiGuiScreen = texturesGuiGuiScreen2;
      this.count15 = -1;
   }

   @Override
   public boolean checkIndex(int count) {
      return count == this.count15;
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      ResourceLocationRecord resourceLocationRecord = (ResourceLocationRecord)TexturesGuiGuiScreen.getList3().get(count);
      this.applyResourceLocation(count2, count3, resourceLocationRecord.resourceLocation);
      this.texturesGuiGuiScreen.fontRenderer.getIndex(resourceLocationRecord.labelText, count2 + 32 + 10, count3 + 14, 16777215);
   }

   private void applyResourceLocation(int count, int count2, ResourceLocation location) {
      int count3 = count + 5;
      this.texturesGuiGuiScreen.applyIndex(count3 - 1, count3 + 32, count2 - 1, -2039584);
      this.texturesGuiGuiScreen.applyIndex(count3 - 1, count3 + 32, count2 + 32, -6250336);
      this.texturesGuiGuiScreen.applyIndexByIndex(count3 - 1, count2 - 1, count2 + 32, -2039584);
      this.texturesGuiGuiScreen.applyIndexByIndex(count3 + 32, count2 - 1, count2 + 32, -6250336);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().applyResourceLocation3(location);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer((double)(count3 + 0), (double)(count2 + 32), 0.0).getWorldRenderer6(0.0, 1.0).updateState();
      worldRenderer.getWorldRenderer((double)(count3 + 32), (double)(count2 + 32), 0.0).getWorldRenderer6(1.0, 1.0).updateState();
      worldRenderer.getWorldRenderer((double)(count3 + 32), (double)(count2 + 0), 0.0).getWorldRenderer6(1.0, 0.0).updateState();
      worldRenderer.getWorldRenderer((double)(count3 + 0), (double)(count2 + 0), 0.0).getWorldRenderer6(0.0, 0.0).updateState();
      tessellator.updateState();
   }

   @Override
   public int size() {
      return TexturesGuiGuiScreen.getList3().size();
   }
}
