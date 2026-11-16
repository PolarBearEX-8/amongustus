package client.util;

import client.gui.TexturesGuiIconsPngManager;
import client.render.WorldRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.CreateWorldCustomizeGuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.StatCollector;

public class AirCreateWorldExtendedMinecraftEntry extends ExtendedMinecraftEntry {
   public int count15;
   public final CreateWorldCustomizeGuiScreen createWorldCustomizeGuiScreen;

   private void applyItemStack(int count, int count2, ItemStack stack) {
      this.applyIndex3(count + 1, count2 + 1);
      GlStateManager.disableLighting();
      if (stack != null && stack.getItem() != null) {
         RenderHelper.enableGUIStandardItemLighting();
         this.createWorldCustomizeGuiScreen.renderItem.renderItemAndEffectIntoGUI(stack, count + 2, count2 + 2);
         RenderHelper.disableStandardItemLighting();
      }

      GlStateManager.applyState8();
   }

   private void applyIndex2(int count, int count2, int count3, int count4) {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().applyResourceLocation3(TexturesGuiIconsPngManager.resourceLocation3);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer((double)(count + 0), (double)(count2 + 18), (double)this.createWorldCustomizeGuiScreen.factor)
         .getWorldRenderer6((double)((float)(count3 + 0) * 0.0078125F), (double)((float)(count4 + 18) * 0.0078125F))
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + 18), (double)(count2 + 18), (double)this.createWorldCustomizeGuiScreen.factor)
         .getWorldRenderer6((double)((float)(count3 + 18) * 0.0078125F), (double)((float)(count4 + 18) * 0.0078125F))
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + 18), (double)(count2 + 0), (double)this.createWorldCustomizeGuiScreen.factor)
         .getWorldRenderer6((double)((float)(count3 + 18) * 0.0078125F), (double)((float)(count4 + 0) * 0.0078125F))
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + 0), (double)(count2 + 0), (double)this.createWorldCustomizeGuiScreen.factor)
         .getWorldRenderer6((double)((float)(count3 + 0) * 0.0078125F), (double)((float)(count4 + 0) * 0.0078125F))
         .updateState();
      tessellator.updateState();
   }

   public AirCreateWorldExtendedMinecraftEntry(CreateWorldCustomizeGuiScreen createWorldCustomizeGuiScreen2) {
      super(createWorldCustomizeGuiScreen2.mc, createWorldCustomizeGuiScreen2.width, createWorldCustomizeGuiScreen2.height, 43, createWorldCustomizeGuiScreen2.height - 60, 24);
      this.createWorldCustomizeGuiScreen = createWorldCustomizeGuiScreen2;
      this.count15 = -1;
   }

   @Override
   public void updateState2() {
   }

   @Override
   public boolean checkIndex(int count) {
      return count == this.count15;
   }

   private void applyIndex3(int count, int count2) {
      this.applyIndex2(count, count2, 0, 0);
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      NullManager nullManager = (NullManager)CreateWorldCustomizeGuiScreen.resolveVillageManager(this.createWorldCustomizeGuiScreen)
         .getList()
         .get(CreateWorldCustomizeGuiScreen.resolveVillageManager(this.createWorldCustomizeGuiScreen).getList().size() - count - 1);
      IBlockState iBlockState = nullManager.getIBlockState();
      Block block = iBlockState.getBlock();
      Item item = Item.processAction2(block);
      ItemStack stack = block != Blocks.air && item != null ? new ItemStack(item, 1, block.getIndex(iBlockState)) : null;
      String text = stack == null ? "Air" : item.resolveText(stack);
      if (item == null) {
         if (block == Blocks.blockLava2 || block == Blocks.blockFlowingLava) {
            item = Items.waterBucket;
         } else if (block == Blocks.blockLava || block == Blocks.blockFlowingLava2) {
            item = Items.lavaBucket;
         }

         if (item != null) {
            stack = new ItemStack(item, 1, block.getIndex(iBlockState));
            text = block.getText();
         }
      }

      this.applyItemStack(count2, count3, stack);
      this.createWorldCustomizeGuiScreen.fontRenderer.getIndex(text, count2 + 18 + 5, count3 + 3, 16777215);
      String text2;
      if (count == 0) {
         text2 = StatCollector.getText("createWorld.customize.flat.layer.top", new Object[]{nullManager.getCount2()});
      } else if (count == CreateWorldCustomizeGuiScreen.resolveVillageManager(this.createWorldCustomizeGuiScreen).getList().size() - 1) {
         text2 = StatCollector.getText("createWorld.customize.flat.layer.bottom", new Object[]{nullManager.getCount2()});
      } else {
         text2 = StatCollector.getText("createWorld.customize.flat.layer", new Object[]{nullManager.getCount2()});
      }

      this.createWorldCustomizeGuiScreen
         .fontRenderer
         .getIndex(text2, count2 + 2 + 213 - this.createWorldCustomizeGuiScreen.fontRenderer.getIndex(text2), count3 + 3, 16777215);
   }

   @Override
   public int getCount22() {
      return this.count9 - 70;
   }

   @Override
   public void applyIndex(int count, boolean flag, int count2, int count3) {
      this.count15 = count;
      this.createWorldCustomizeGuiScreen.renderElement();
   }

   @Override
   public int size() {
      return CreateWorldCustomizeGuiScreen.resolveVillageManager(this.createWorldCustomizeGuiScreen).getList().size();
   }
}
