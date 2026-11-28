package net.minecraft.client.gui.inventory;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiBrewingStand extends GuiContainer {
   private static final ResourceLocation brewingStandGuiTextures;
   private final InventoryPlayer playerInventory;
   public IInventory tileBrewingStand;

   static {
      String str = "textures/gui/container/brewing_stand.png";
      brewingStandGuiTextures = new ResourceLocation(str);
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float f, int n, int n2) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(brewingStandGuiTextures);
      int n3 = (this.width - this.xSize) / 2;
      int n4 = (this.height - this.ySize) / 2;
      this.drawTexturedModalRect(n3, n4, 0, 0, this.xSize, this.ySize);
      int field = this.tileBrewingStand.getField(0);
      if (field > 0) {
         int n5 = (int)(28.0F * (1.0F - (float)field / 400.0F));
         if (n5 > 0) {
            this.drawTexturedModalRect(n3 + 97, n4 + 16, 176, 0, 9, n5);
         }

         switch (field / 2 % 7) {
            case 0:
               n5 = 29;
               break;
            case 1:
               n5 = 24;
               break;
            case 2:
               n5 = 20;
               break;
            case 3:
               n5 = 16;
               break;
            case 4:
               n5 = 11;
               break;
            case 5:
               n5 = 6;
               break;
            case 6:
               n5 = 0;
         }

         if (n5 > 0) {
            this.drawTexturedModalRect(n3 + 65, n4 + 14 + 29 - n5, 185, 29 - n5, 12, n5);
         }
      }
   }

   @Override
   public void drawGuiContainerForegroundLayer(int n, int n2) {
      String unformattedText = this.tileBrewingStand.getDisplayName().getUnformattedText();
      this.fontRendererObj.drawString(unformattedText, this.xSize / 2 - this.fontRendererObj.getStringWidth(unformattedText) / 2, 6, 4210752);
      this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
   }

   public GuiBrewingStand(InventoryPlayer inventoryPlayer, IInventory inventory) {
      super(new ContainerBrewingStand(inventoryPlayer, inventory));
      this.playerInventory = inventoryPlayer;
      this.tileBrewingStand = inventory;
   }
}
