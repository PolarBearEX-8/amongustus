package net.minecraft.client.gui.inventory;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;

public class GuiFurnace extends GuiContainer {
   private static final ResourceLocation furnaceGuiTextures;
   private final InventoryPlayer playerInventory;
   public IInventory tileFurnace;

   private int getCookProgressScaled(int n) {
      int field = this.tileFurnace.getField(2);
      int field2 = this.tileFurnace.getField(3);
      return field2 != 0 && field != 0 ? field * n / field2 : 0;
   }

   @Override
   public void drawGuiContainerForegroundLayer(int n, int n2) {
      String unformattedText = this.tileFurnace.getDisplayName().getUnformattedText();
      this.fontRendererObj.drawString(unformattedText, this.xSize / 2 - this.fontRendererObj.getStringWidth(unformattedText) / 2, 6, 4210752);
      this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
   }

   static {
      String str = "textures/gui/container/furnace.png";
      furnaceGuiTextures = new ResourceLocation(str);
   }

   private int getBurnLeftScaled(int n) {
      int field = this.tileFurnace.getField(1);
      if (field == 0) {
         field = 200;
      }

      return this.tileFurnace.getField(0) * n / field;
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float f, int n, int n2) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
      int n3 = (this.width - this.xSize) / 2;
      int n4 = (this.height - this.ySize) / 2;
      this.drawTexturedModalRect(n3, n4, 0, 0, this.xSize, this.ySize);
      if (TileEntityFurnace.isBurning(this.tileFurnace)) {
         int burnLeftScaled = this.getBurnLeftScaled(13);
         this.drawTexturedModalRect(n3 + 56, n4 + 36 + 12 - burnLeftScaled, 176, 12 - burnLeftScaled, 14, burnLeftScaled + 1);
      }

      this.drawTexturedModalRect(n3 + 79, n4 + 34, 176, 14, this.getCookProgressScaled(24) + 1, 16);
   }

   public GuiFurnace(InventoryPlayer inventoryPlayer, IInventory inventory) {
      super(new ContainerFurnace(inventoryPlayer, inventory));
      this.playerInventory = inventoryPlayer;
      this.tileFurnace = inventory;
   }
}
