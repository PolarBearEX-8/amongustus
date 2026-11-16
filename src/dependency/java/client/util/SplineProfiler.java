package client.util;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class SplineProfiler extends GuiContainer {
   public static final ResourceLocation resourceLocation5;
   public final InventoryPlayer inventoryPlayer;
   public IInventory iInventory;

   public SplineProfiler(InventoryPlayer inventoryPlayer2, IInventory iInventory2) {
      super(new InterpolationBridge(inventoryPlayer2, iInventory2));
      this.inventoryPlayer = inventoryPlayer2;
      this.iInventory = iInventory2;
   }

   static {
      String text = "textures/gui/container/dispenser.png";
      resourceLocation5 = new ResourceLocation(text);
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
   }
}
