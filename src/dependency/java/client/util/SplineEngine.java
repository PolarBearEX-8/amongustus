package client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class SplineEngine extends GuiContainer {
   public static final ResourceLocation resourceLocation5;
   public IInventory iInventory2;
   public IInventory iInventory;

   static {
      String text = "textures/gui/container/hopper.png";
      resourceLocation5 = new ResourceLocation(text);
   }

   @Override
   public void applyIndex(int count, int count2) {
      this.fontRenderer.getIndex(this.iInventory.getChatComponent().getString(), 8, 6, 4210752);
      this.fontRenderer.getIndex(this.iInventory2.getChatComponent().getString(), 8, this.count10 - 96 + 2, 4210752);
   }

   @Override
   public void applyFloat(float factor, int count, int count2) {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      net.minecraft.client.Minecraft.getMinecraft().getTextureManager().applyResourceLocation3(resourceLocation5);
      int count3 = (this.width - this.count9) / 2;
      int count4 = (this.height - this.count10) / 2;
      this.processAction(count3, count4, 0, 0, this.count9, this.count10);
   }

   public SplineEngine(InventoryPlayer inventoryPlayer, IInventory iInventory3) {
      super(new InterpolationPipeline(inventoryPlayer, iInventory3, Minecraft.getMinecraft().thePlayer));
      this.iInventory2 = inventoryPlayer;
      this.iInventory = iInventory3;
      this.enabled = false;
      this.count10 = 133;
   }
}
