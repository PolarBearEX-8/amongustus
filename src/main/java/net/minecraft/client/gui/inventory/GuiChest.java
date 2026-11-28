package net.minecraft.client.gui.inventory;

import java.io.IOException;
import net.augustus.ClientManagers;
import net.augustus.settings.AbstractListSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiChest extends GuiContainer {
   private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
   public IInventory upperChestInventory;
   public IInventory lowerChestInventory;
   private int inventoryRows;

   public GuiChest(IInventory inventory, IInventory inventory2) {
      super(new ContainerChest(inventory, inventory2, Minecraft.getMinecraft().thePlayer));
      this.upperChestInventory = inventory;
      this.lowerChestInventory = inventory2;
      this.allowUserInput = false;
      this.inventoryRows = inventory2.getSizeInventory() / 9;
      this.ySize = 114 + this.inventoryRows * 18;
   }

   @Override
   public void initGui() {
      super.initGui();
      if (ClientManagers.moduleManager.chestStealer.whereToSteal.getSelectedValue().equals("CustomChest") || ClientManagers.moduleManager.stealer.whereToSteal.getSelectedValue().equals("CustomChest")) {
         this.buttonList.add(new GuiButton(187, this.guiLeft + this.xSize, this.guiTop, 40, 20, "Add", true));
         this.buttonList.add(new GuiButton(188, this.guiLeft + this.xSize, this.guiTop + 25, 40, 20, "Remove", true));
         this.buttonList.add(new GuiButton(189, this.guiLeft + this.xSize, this.guiTop + 50, 40, 20, "Clear", true));
      }
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float f, int n, int n2) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
      int n3 = (this.width - this.xSize) / 2;
      int n4 = (this.height - this.ySize) / 2;
      this.drawTexturedModalRect(n3, n4, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
      this.drawTexturedModalRect(n3, n4 + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if ((!ClientManagers.moduleManager.chestStealer.isToggled() || !ClientManagers.moduleManager.chestStealer.silent.getValue() || !ClientManagers.moduleManager.chestStealer.shouldStealFromChest(this))
         && (!ClientManagers.moduleManager.stealer.isToggled() || !ClientManagers.moduleManager.stealer.silent.getValue() || !ClientManagers.moduleManager.stealer.shouldStealFromChest(this))) {
         super.drawScreen(mouseX, mouseY, partialTicks);
      }
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      super.actionPerformed(button);
      if (button.id == 187) {
         String name = this.lowerChestInventory.getName();
         if (!((AbstractListSetting)ClientManagers.moduleManager.chestStealer.chestStorage).contains(name)) {
            ((AbstractListSetting)ClientManagers.moduleManager.chestStealer.chestStorage).add(name);
         }

         if (!((AbstractListSetting)ClientManagers.moduleManager.stealer.chestStorage).contains(name)) {
            ((AbstractListSetting)ClientManagers.moduleManager.stealer.chestStorage).add(name);
         }
      } else if (button.id == 188) {
         String name2 = this.lowerChestInventory.getName();
         ((AbstractListSetting)ClientManagers.moduleManager.chestStealer.chestStorage).remove(name2);
         ((AbstractListSetting)ClientManagers.moduleManager.stealer.chestStorage).remove(name2);
      } else if (button.id == 189) {
         ((AbstractListSetting)ClientManagers.moduleManager.chestStealer.chestStorage).clear();
         ((AbstractListSetting)ClientManagers.moduleManager.stealer.chestStorage).clear();
      }
   }

   @Override
   public void drawGuiContainerForegroundLayer(int n, int n2) {
      if (!ClientManagers.moduleManager.antiStrike.shouldHideChestNames()) {
         this.fontRendererObj.drawString(this.lowerChestInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
         this.fontRendererObj.drawString(this.upperChestInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
      }
   }

}
