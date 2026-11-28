package net.augustus.modules.combat;

import java.awt.Color;
import java.util.ArrayList;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.utils.TuplePair;
import net.augustus.events.EventAttackEntity;
import net.augustus.utils.player.InventoryUtil;
import net.augustus.events.listeners.AttackEntityListener;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.settings.BooleanValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class GommeMode extends Module implements AttackEntityListener, SilentSlotListener {
   private final BooleanValue silentSlotSwitching;
   private Entity lastAttackedEntity;

   public GommeMode() {
       super("GommeMode", "GommeModus", new Color(167, 85, 250), Categorys.COMBAT);
      this.silentSlotSwitching = new BooleanValue(this, "SilentSlotSwitching", true);
      this.lastAttackedEntity = null;
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(1);
      this.toggleEventSubscription(34);
   }

   public void onAttackEntity(EventAttackEntity event) {
      this.lastAttackedEntity = event.getTarget();
   }

   public void onPostSilentSlot() {
      if (this.lastAttackedEntity != null && this.lastAttackedEntity instanceof EntityLivingBase) {
         if (((EntityLivingBase)this.lastAttackedEntity).hurtTime < 6) {
            this.lastAttackedEntity = null;
         } else {
            ItemStack currentEquippedItem = mc.thePlayer.getCurrentEquippedItem();
            if ((currentEquippedItem == null || currentEquippedItem.getItem() instanceof ItemSword || !mc.thePlayer.isUsingItem()) && !mc.playerController.isHittingBlock) {
               ArrayList sortedWeapons = InventoryUtil.getSortedWeapons(mc.thePlayer.inventoryContainer, (byte)36);
               if (!sortedWeapons.isEmpty()) {
                  TuplePair tuplePair = (TuplePair)sortedWeapons.get(0);
                  if ((Integer)tuplePair.getSecond() - 36 != mc.thePlayer.inventory.currentItem) {
                     SilentSlotManager.requestSlot(this, (byte)8, (Integer)tuplePair.getSecond() - 36, this.silentSlotSwitching.getValue());
                  }
               }
            }
         }
      }
   }

}
