package net.minecraft.client.gui.inventory;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiDispenser extends GuiContainer {
   private static final ResourceLocation dispenserGuiTextures;
   private final InventoryPlayer playerInventory;
   public IInventory dispenserInventory;

   @Override
   public void drawGuiContainerBackgroundLayer(float f, int n, int n2) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(dispenserGuiTextures);
      this.drawTexturedModalRect((this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);
   }

   @Override
   public void drawGuiContainerForegroundLayer(int n, int n2) {
      String unformattedText = this.dispenserInventory.getDisplayName().getUnformattedText();
      this.fontRendererObj.drawString(unformattedText, this.xSize / 2 - this.fontRendererObj.getStringWidth(unformattedText) / 2, 6, 4210752);
      this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
   }

   static {
      String str = "textures/gui/container/dispenser.png";
      dispenserGuiTextures = new ResourceLocation(str);
   }

   public GuiDispenser(InventoryPlayer inventoryPlayer, IInventory inventory) {
      super(new ContainerDispenser(inventoryPlayer, inventory));
      this.playerInventory = inventoryPlayer;
      this.dispenserInventory = inventory;
   }
}
