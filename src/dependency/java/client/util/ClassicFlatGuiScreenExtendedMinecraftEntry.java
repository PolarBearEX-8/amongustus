package client.util;

import client.gui.TexturesGuiIconsPngManager;
import client.render.WorldRenderer;
import net.minecraft.client.gui.ClassicFlatGuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;

public class ClassicFlatGuiScreenExtendedMinecraftEntry extends ExtendedMinecraftEntry {
   public int count15;
   public final ClassicFlatGuiScreen classicFlatGuiScreen;

   @Override
   public void applyIndex(int count, boolean flag, int count2, int count3) {
      this.count15 = count;
      this.classicFlatGuiScreen.renderElement();
      ClassicFlatGuiScreen.resolveInternalTexturesGuiIconsPngM(this.classicFlatGuiScreen)
         .applyText(
            ((ItemRecord)ClassicFlatGuiScreen.getList3().get(ClassicFlatGuiScreen.resolveClassicFlatGuiScreenExtended(this.classicFlatGuiScreen).count15)).labelText2
         );
   }

   public ClassicFlatGuiScreenExtendedMinecraftEntry(ClassicFlatGuiScreen classicFlatGuiScreen2) {
      super(classicFlatGuiScreen2.mc, classicFlatGuiScreen2.width, classicFlatGuiScreen2.height, 80, classicFlatGuiScreen2.height - 37, 24);
      this.classicFlatGuiScreen = classicFlatGuiScreen2;
      this.count15 = -1;
   }

   @Override
   public void updateState2() {
   }

   @Override
   public boolean checkIndex(int count) {
      return count == this.count15;
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      ItemRecord itemRecord = (ItemRecord)ClassicFlatGuiScreen.getList3().get(count);
      this.applyItem(count2, count3, itemRecord.item, itemRecord.count);
      this.classicFlatGuiScreen.fontRenderer.getIndex(itemRecord.labelText, count2 + 18 + 5, count3 + 6, 16777215);
   }

   private void applyIndex2(int count, int count2, int count3, int count4) {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().applyResourceLocation3(TexturesGuiIconsPngManager.resourceLocation3);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer((double)(count + 0), (double)(count2 + 18), (double)this.classicFlatGuiScreen.factor)
         .getWorldRenderer6((double)((float)(count3 + 0) * 0.0078125F), (double)((float)(count4 + 18) * 0.0078125F))
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + 18), (double)(count2 + 18), (double)this.classicFlatGuiScreen.factor)
         .getWorldRenderer6((double)((float)(count3 + 18) * 0.0078125F), (double)((float)(count4 + 18) * 0.0078125F))
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + 18), (double)(count2 + 0), (double)this.classicFlatGuiScreen.factor)
         .getWorldRenderer6((double)((float)(count3 + 18) * 0.0078125F), (double)((float)(count4 + 0) * 0.0078125F))
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + 0), (double)(count2 + 0), (double)this.classicFlatGuiScreen.factor)
         .getWorldRenderer6((double)((float)(count3 + 0) * 0.0078125F), (double)((float)(count4 + 0) * 0.0078125F))
         .updateState();
      tessellator.updateState();
   }

   private void applyIndex3(int count, int count2) {
      this.applyIndex2(count, count2, 0, 0);
   }

   @Override
   public int size() {
      return ClassicFlatGuiScreen.getList3().size();
   }

   private void applyItem(int count, int count2, Item item, int count3) {
      this.applyIndex3(count + 1, count2 + 1);
      GlStateManager.disableLighting();
      RenderHelper.enableGUIStandardItemLighting();
      this.classicFlatGuiScreen.renderItem.renderItemAndEffectIntoGUI(new ItemStack(item, 1, count3), count + 2, count2 + 2);
      RenderHelper.disableStandardItemLighting();
      GlStateManager.applyState8();
   }
}
