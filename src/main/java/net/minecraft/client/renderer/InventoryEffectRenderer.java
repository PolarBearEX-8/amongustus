package net.minecraft.client.renderer;

import java.util.Collection;
import net.augustus.modules.Module;
import net.augustus.ClientManagers;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public abstract class InventoryEffectRenderer extends GuiContainer {
   private boolean hasActivePotionEffects;

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      super.drawScreen(mouseX, mouseY, partialTicks);
      if (this.hasActivePotionEffects) {
         this.drawActivePotionEffects();
      }
   }

   public void updateActivePotionEffects() {
      if (!this.mc.thePlayer.getActivePotionEffects().isEmpty()) {
         if (((Module)ClientManagers.moduleManager.centeredPotionInventory).isToggled()) {
            this.guiLeft = (this.width - this.xSize) / 2;
         } else {
            this.guiLeft = 160 + (this.width - this.xSize - 200) / 2;
         }

         this.hasActivePotionEffects = true;
      } else {
         this.guiLeft = (this.width - this.xSize) / 2;
         this.hasActivePotionEffects = false;
      }
   }

   @Override
   public void initGui() {
      super.initGui();
      this.updateActivePotionEffects();
   }

   private void drawActivePotionEffects() {
      int n = this.guiLeft - 124;
      int guiTop2 = this.guiTop;
      Collection activePotionEffects = this.mc.thePlayer.getActivePotionEffects();
      if (!activePotionEffects.isEmpty()) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableLighting();
         int n2 = 33;
         if (activePotionEffects.size() > 5) {
            n2 = 132 / (activePotionEffects.size() - 1);
         }

         for (PotionEffect potionEffect : (Iterable<PotionEffect>)(this.mc.thePlayer.getActivePotionEffects())) {
            Potion potion = Potion.potionTypes[potionEffect.getPotionID()];
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.getTextureManager().bindTexture(inventoryBackground);
            this.drawTexturedModalRect(n, guiTop2, 0, 166, 140, 32);
            if (potion.hasStatusIcon()) {
               int statusIconIndex = potion.getStatusIconIndex();
               this.drawTexturedModalRect(n + 6, guiTop2 + 7, 0 + statusIconIndex % 8 * 18, 198 + statusIconIndex / 8 * 18, 18, 18);
            }

            String str = I18n.format(potion.getName(), new Object[0]);
            if (potionEffect.getAmplifier() == 1) {
               str = str + " " + I18n.format("enchantment.level.2", new Object[0]);
            } else if (potionEffect.getAmplifier() == 2) {
               str = str + " " + I18n.format("enchantment.level.3", new Object[0]);
            } else if (potionEffect.getAmplifier() == 3) {
               str = str + " " + I18n.format("enchantment.level.4", new Object[0]);
            }

            this.fontRendererObj.drawStringWithShadow(str, (float)(n + 10 + 18), (float)(guiTop2 + 6), 16777215);
            String durationString = Potion.getDurationString(potionEffect);
            this.fontRendererObj.drawStringWithShadow(durationString, (float)(n + 10 + 18), (float)(guiTop2 + 6 + 10), 8355711);
            guiTop2 += n2;
         }
      }
   }

   public InventoryEffectRenderer(Container container) {
      super(container);
   }
}
