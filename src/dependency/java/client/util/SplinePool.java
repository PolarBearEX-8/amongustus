package client.util;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class SplinePool extends GuiContainer {
   public static final ResourceLocation resourceLocation5;
   public final InventoryPlayer inventoryPlayer;
   public IInventory iInventory;

   @Override
   public void applyFloat(float factor, int count, int count2) {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      net.minecraft.client.Minecraft.getMinecraft().getTextureManager().applyResourceLocation3(resourceLocation5);
      int count3 = (this.width - this.count9) / 2;
      int count4 = (this.height - this.count10) / 2;
      this.processAction(count3, count4, 0, 0, this.count9, this.count10);
      int count5 = this.iInventory.processAction(0);
      if (count5 > 0) {
         int count6 = (int)(28.0F * (1.0F - (float)count5 / 400.0F));
         if (count6 > 0) {
            this.processAction(count3 + 97, count4 + 16, 176, 0, 9, count6);
         }

         switch (count5 / 2 % 7) {
            case 0:
               count6 = 29;
               break;
            case 1:
               count6 = 24;
               break;
            case 2:
               count6 = 20;
               break;
            case 3:
               count6 = 16;
               break;
            case 4:
               count6 = 11;
               break;
            case 5:
               count6 = 6;
               break;
            case 6:
               count6 = 0;
         }

         if (count6 > 0) {
            this.processAction(count3 + 65, count4 + 14 + 29 - count6, 185, 29 - count6, 12, count6);
         }
      }
   }

   public SplinePool(InventoryPlayer inventoryPlayer2, IInventory iInventory2) {
      super(new InternalContainerAdapter(inventoryPlayer2, iInventory2));
      this.inventoryPlayer = inventoryPlayer2;
      this.iInventory = iInventory2;
   }

   @Override
   public void applyIndex(int count, int count2) {
      String string = this.iInventory.getChatComponent().getString();
      this.fontRenderer.getIndex(string, this.count9 / 2 - this.fontRenderer.getIndex(string) / 2, 6, 4210752);
      this.fontRenderer.getIndex(this.inventoryPlayer.getChatComponent().getString(), 8, this.count10 - 96 + 2, 4210752);
   }

   static {
      String text = "textures/gui/container/brewing_stand.png";
      resourceLocation5 = new ResourceLocation(text);
   }
}
