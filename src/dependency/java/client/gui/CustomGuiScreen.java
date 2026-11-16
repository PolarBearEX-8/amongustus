package client.gui;

import client.ClientGlobals;
import client.module.Module;
import client.setting.ItemListSetting;
import client.util.AdvancedContainerAdapter;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class CustomGuiScreen extends GuiContainer {
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/gui/container/generic_54.png");
   public IInventory iInventory;
   public IInventory iInventory2;
   public int count12;

   @Override
   public void updateState2() {
      super.updateState2();
      if (ClientGlobals.moduleManager.chestStealer.settingWhereToSteal.getSelected().equals("CustomChest")) {
         this.list.add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(187, this.indexOffset1 + this.count9, this.indexOffset2, 40, 20, "Add", true));
         this.list.add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(188, this.indexOffset1 + this.count9, this.indexOffset2 + 25, 40, 20, "Remove", true));
         this.list.add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(189, this.indexOffset1 + this.count9, this.indexOffset2 + 50, 40, 20, "Clear", true));
      }
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      super.applyAuxiliaryTexturesGuiIconsP(auxiliaryTexturesGuiIconsPngManagerAdapter);
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 187) {
         ItemListSetting settingChestStorage = ClientGlobals.moduleManager.chestStealer.settingChestStorage;
         String text = this.iInventory2.getText();
         if (!settingChestStorage.matchesTargetByTarget(text)) {
            settingChestStorage = ClientGlobals.moduleManager.chestStealer.settingChestStorage;
            String text2 = this.iInventory2.getText();
            settingChestStorage.applyTarget(text2);
            return;
         }
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 188) {
         ItemListSetting settingChestStorage2 = ClientGlobals.moduleManager.chestStealer.settingChestStorage;
         String text3 = this.iInventory2.getText();
         settingChestStorage2.matchesTarget(text3);
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 189) {
         ClientGlobals.moduleManager.chestStealer.settingChestStorage.updateState7();
      }
   }

   @Override
   public void applyIndex(int count, int count2) {
      if (!ClientGlobals.moduleManager.antiStrike.canExecute10()) {
         this.fontRenderer.getIndex(this.iInventory2.getChatComponent().getString(), 8, 6, 4210752);
         this.fontRenderer.getIndex(this.iInventory.getChatComponent().getString(), 8, this.count10 - 96 + 2, 4210752);
      }
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      if (!((Module)ClientGlobals.moduleManager.chestStealer).isToggled()
         || !ClientGlobals.moduleManager.chestStealer.settingSilent.isEnabled()
         || !ClientGlobals.moduleManager.chestStealer.checkCustomGuiScreen(this)) {
         super.applyIndex(count, count2, factor);
      }
   }

   @Override
   public void applyFloat(float factor, int count, int count2) {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().applyResourceLocation3(resourceLocation5);
      int count3 = (this.width - this.count9) / 2;
      int count4 = (this.height - this.count10) / 2;
      this.processAction(count3, count4, 0, 0, this.count9, this.count12 * 18 + 17);
      this.processAction(count3, count4 + this.count12 * 18 + 17, 0, 126, this.count9, 96);
   }

   public CustomGuiScreen(IInventory iInventory3, IInventory iInventory4) {
      super(new AdvancedContainerAdapter(iInventory3, iInventory4, Minecraft.getMinecraft().thePlayer));
      this.iInventory = iInventory3;
      this.iInventory2 = iInventory4;
      this.enabled = false;
      this.count12 = iInventory4.processAction4() / 9;
      this.count10 = 114 + this.count12 * 18;
   }
}
