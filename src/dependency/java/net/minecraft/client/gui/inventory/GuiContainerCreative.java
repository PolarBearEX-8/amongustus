package net.minecraft.client.gui.inventory;

import client.ClientGlobals;
import client.module.Module;
import java.util.Collection;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;

public abstract class GuiContainerCreative extends GuiContainer {
   public boolean enabled10;

   @Override
   public void updateState2() {
      super.updateState2();
      this.renderElement5();
   }

   public void renderElement5() {
      if (!this.mc.thePlayer.getCollection().isEmpty()) {
         if (((Module)ClientGlobals.moduleManager.centeredPotionInventory).isToggled()) {
            this.indexOffset1 = (this.width - this.count9) / 2;
         } else {
            this.indexOffset1 = 160 + (this.width - this.count9 - 200) / 2;
         }

         this.enabled10 = true;
      } else {
         this.indexOffset1 = (this.width - this.count9) / 2;
         this.enabled10 = false;
      }
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      super.applyIndex(count, count2, factor);
      if (this.enabled10) {
         this.renderElement6();
      }
   }

   private void renderElement6() {
      int count = this.indexOffset1 - 124;
      int count2 = this.indexOffset2;
      Collection collection = this.mc.thePlayer.getCollection();
      if (!collection.isEmpty()) {
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.applyState3();
         int count3 = 33;
         if (collection.size() > 5) {
            count3 = 132 / (collection.size() - 1);
         }

         for (PotionEffect potionEffect : (Iterable<PotionEffect>) (Iterable) (this.mc.thePlayer.getCollection())) {
            Potion potion = Potion.potionArray[potionEffect.getIndexOffset3()];
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.getTextureManager().applyResourceLocation3(resourceLocation4);
            this.processAction(count, count2, 0, 166, 140, 32);
            if (potion.canExecute()) {
               int indexOffset = potion.getIndexOffset();
               this.processAction(count + 6, count2 + 7, 0 + indexOffset % 8 * 18, 198 + indexOffset / 8 * 18, 18, 18);
            }

            String text = StatCollector.getText(potion.getText(), new Object[0]);
            if (potionEffect.getIndexOffset2() == 1) {
               text = text + " " + StatCollector.getText("enchantment.level.2", new Object[0]);
            } else if (potionEffect.getIndexOffset2() == 2) {
               text = text + " " + StatCollector.getText("enchantment.level.3", new Object[0]);
            } else if (potionEffect.getIndexOffset2() == 3) {
               text = text + " " + StatCollector.getText("enchantment.level.4", new Object[0]);
            }

            this.fontRenderer.getIndex2(text, (float)(count + 10 + 18), (float)(count2 + 6), 16777215);
            String text2 = Potion.getText(potionEffect);
            this.fontRenderer.getIndex2(text2, (float)(count + 10 + 18), (float)(count2 + 6 + 10), 8355711);
            count2 += count3;
         }
      }
   }

   public GuiContainerCreative(Container container) {
      super(container);
   }
}
