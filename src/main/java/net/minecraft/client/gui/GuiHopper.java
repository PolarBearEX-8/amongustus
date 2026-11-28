package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiHopper extends GuiContainer {
   private static final ResourceLocation HOPPER_GUI_TEXTURE;
   private IInventory playerInventory;
   public IInventory hopperInventory;

   @Override
   public void drawGuiContainerForegroundLayer(int n, int n2) {
      this.fontRendererObj.drawString(this.hopperInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
      this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
   }

   public GuiHopper(InventoryPlayer inventoryPlayer, IInventory inventory) {
      super(new ContainerHopper(inventoryPlayer, inventory, Minecraft.getMinecraft().thePlayer));
      this.playerInventory = inventoryPlayer;
      this.hopperInventory = inventory;
      this.allowUserInput = false;
      this.ySize = 133;
   }

   static {
      String str = "textures/gui/container/hopper.png";
      HOPPER_GUI_TEXTURE = new ResourceLocation(str);
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float f, int n, int n2) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(HOPPER_GUI_TEXTURE);
      this.drawTexturedModalRect((this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);
   }
}
