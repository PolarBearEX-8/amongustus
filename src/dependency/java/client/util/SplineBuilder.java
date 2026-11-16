package client.util;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;

public class SplineBuilder extends GuiContainer {
   public static final ResourceLocation resourceLocation5;
   public final InventoryPlayer inventoryPlayer;
   public IInventory iInventory;

   private int computeCount(int count) {
      int count2 = this.iInventory.processAction(1);
      if (count2 == 0) {
         count2 = 200;
      }

      return this.iInventory.processAction(0) * count / count2;
   }

   private int computeCountByIndex(int count) {
      int count2 = this.iInventory.processAction(2);
      int count3 = this.iInventory.processAction(3);
      return count3 != 0 && count2 != 0 ? count2 * count / count3 : 0;
   }

   public SplineBuilder(InventoryPlayer inventoryPlayer2, IInventory iInventory2) {
      super(new SecondaryContainerAdapter(inventoryPlayer2, iInventory2));
      this.inventoryPlayer = inventoryPlayer2;
      this.iInventory = iInventory2;
   }

   @Override
   public void applyIndex(int count, int count2) {
      String string = this.iInventory.getChatComponent().getString();
      this.fontRenderer.getIndex(string, this.count9 / 2 - this.fontRenderer.getIndex(string) / 2, 6, 4210752);
      this.fontRenderer.getIndex(this.inventoryPlayer.getChatComponent().getString(), 8, this.count10 - 96 + 2, 4210752);
   }

   @Override
   public void applyFloat(float factor, int count, int count2) {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      net.minecraft.client.Minecraft.getMinecraft().getTextureManager().applyResourceLocation3(resourceLocation5);
      int count3 = (this.width - this.count9) / 2;
      int count4 = (this.height - this.count10) / 2;
      this.processAction(count3, count4, 0, 0, this.count9, this.count10);
      if (TileEntityFurnace.matchesIInventory(this.iInventory)) {
         int count5 = this.computeCount(13);
         this.processAction(count3 + 56, count4 + 36 + 12 - count5, 176, 12 - count5, 14, count5 + 1);
      }

      int count6 = this.computeCountByIndex(24);
      this.processAction(count3 + 79, count4 + 34, 176, 14, count6 + 1, 16);
   }

   static {
      String text = "textures/gui/container/furnace.png";
      resourceLocation5 = new ResourceLocation(text);
   }
}
