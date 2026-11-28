package net.augustus.modules.player;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.modules.combat.KillAura;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.utils.world.BlockUtil;
import net.augustus.utils.rotation.RayTraceUtil;
import net.augustus.events.EventAttackEntity;
import net.augustus.events.listeners.AttackEntityListener;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.MovingObjectPosition;

public class AutoTool extends Module implements SilentSlotListener, AttackEntityListener {
   private final BooleanValue tools;
   private final BooleanValue sword;
   private final RandomizedRangeSettings delayBeforeSwitch;
   private final RandomizedRangeSettings delayAtItemSwitch;
   private final RandomizedRangeSettings backSwitchDelay;
   private final BooleanValue onlyAtSneak;
   private final BooleanValue silent;
   private int selectedSlot;
   private int previousBestSlot;
   private int ticksSinceToolNeeded;
   private int ticksWithBestTool;
   private int ticksSinceSlotChange;
   private final TimeHelper lastAttackTimer;
   private final TimeHelper lastClickTimer;
   public static boolean forceToolSwitch = false;

   public AutoTool() {
       super("AutoTool", "AutoWerkzeug", new Color(237, 237, 0), Categorys.PLAYER);
      this.tools = new BooleanValue(this, "Tools", true);
      this.sword = new BooleanValue(this, "Sword", false);
      this.delayBeforeSwitch = new RandomizedRangeSettings(this, "DelayBeforeSwitch", 0.0, 0.0, 0.0, 5.0, 0);
      this.delayAtItemSwitch = new RandomizedRangeSettings(this, "DelayAtItemSwitch", 0.0, 0.0, 0.0, 5.0, 0);
      this.backSwitchDelay = new RandomizedRangeSettings(this, "BackSwitchDelay", 2.0, 3.0, 0.0, 20.0, 0);
      this.onlyAtSneak = new BooleanValue(this, "OnlyAtSneak", false);
      this.silent = new BooleanValue(this, "Silent", true);
      this.selectedSlot = 0;
      this.previousBestSlot = 0;
      this.ticksSinceToolNeeded = 0;
      this.ticksWithBestTool = 0;
      this.ticksSinceSlotChange = 0;
      this.lastAttackTimer = new TimeHelper();
      this.lastClickTimer = new TimeHelper();
   }

   public void onEnable() {
      if (mc.thePlayer != null && mc.theWorld != null) {
         this.selectedSlot = -1;
         this.ticksSinceToolNeeded = 0;
         this.ticksWithBestTool = 0;
         this.ticksSinceSlotChange = 0;
         this.delayBeforeSwitch.nextValue();
         this.delayAtItemSwitch.nextValue();
         this.backSwitchDelay.nextValue();
      }
   }

   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(34);
      this.toggleEventSubscription(1);
   }

   public void onAttackEntity(EventAttackEntity event) {
      this.lastAttackTimer.reset();
   }

   public void onPostSilentSlot() {
      if (mc.gameSettings.keyBindAttack.getPressTime() > 0) {
         this.lastClickTimer.reset();
      }

      double d = 0.0;
      MovingObjectPosition objectMouseOver = mc.objectMouseOver;
      MovingObjectPosition hit = ClientManagers.moduleManager.fucker.overrideRayTraceResult(objectMouseOver);
      int n = -1;
      if (hit != null && mc.playerController.isNotCreative()) {
         d = 1.0;
         ItemStack stack = this.silent.getValue() ? SilentSlotManager.heldItemStack : mc.thePlayer.inventoryContainer.getSlot(SilentSlotManager.clientHotbarSlot + 36).getStack();
         if (!mc.thePlayer.isUsingItem()) {
            for (int i = 36; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
               ItemStack stack2 = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
               if (stack2 != null) {
                  Item item = stack2.getItem();
                  if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
                     && hit.getBlockPos() != null
                     && this.tools.getValue()
                     && (forceToolSwitch || mc.gameSettings.keyBindAttack.isKeyDown())
                     && (
                        item instanceof ItemTool
                           || item instanceof ItemSword && mc.theWorld.getBlockState(hit.getBlockPos()).getBlock().equals(Blocks.web)
                           || item instanceof ItemShears
                     )) {
                     double d2 = this.scoreMiningTool(stack2, hit);
                     double d3 = this.scoreMiningTool(stack, hit);
                     if (d2 > 1.0 && d2 > d3 && d2 > d) {
                        n = i - 36;
                        d = d2;
                     }
                  }
               }
            }
         }

         if (this.shouldSwitchToWeapon(hit)) {
            for (int j = 36; j < mc.thePlayer.inventoryContainer.inventorySlots.size(); j++) {
               ItemStack stack3 = mc.thePlayer.inventoryContainer.getSlot(j).getStack();
               if (stack3 != null) {
                  double d4 = this.scoreWeapon(stack3);
                  double d5 = this.scoreWeapon(stack) + 0.01;
                  if (d4 > 1.0 && d4 > d5 && d4 > d) {
                     n = j - 36;
                     d = d4;
                  }
               }
            }
         }
      }

      if (n < 0) {
         this.ticksWithBestTool = 0;
         if (this.previousBestSlot >= 0) {
            this.delayBeforeSwitch.nextValue();
         }
      } else {
         this.ticksWithBestTool++;
      }

      boolean flag = false;
      if (n != this.selectedSlot && this.selectedSlot > 0 && n > 0) {
         if (this.ticksSinceSlotChange < this.delayAtItemSwitch.getLastIntValue()) {
            flag = true;
         }

         this.ticksSinceSlotChange++;
      } else {
         if (this.ticksSinceSlotChange > 0) {
            this.delayAtItemSwitch.nextValue();
         }

         this.ticksSinceSlotChange = 0;
      }

      if (n != -1) {
         if (!flag) {
            this.selectedSlot = n;
         }

         this.resetBackSwitchTimer();
      }

      if (BlockUtil.isScaffoldActiveAndValid() || this.onlyAtSneak.getValue() && !mc.gameSettings.keyBindSneak.isKeyDown()) {
         this.selectedSlot = -1;
      }

      if (n >= 0 && this.ticksWithBestTool < this.delayBeforeSwitch.getLastIntValue() + 1) {
         this.selectedSlot = -1;
      }

      if (n < 0 && this.ticksSinceToolNeeded > this.backSwitchDelay.getLastIntValue()) {
         this.selectedSlot = -1;
      }

      if (this.selectedSlot > -1 && this.selectedSlot < 9) {
         SilentSlotManager.requestSlot(this, (byte)8, this.selectedSlot, this.silent.getValue());
      }

      this.ticksSinceToolNeeded++;
      this.previousBestSlot = n;
   }

   private boolean shouldSwitchToWeapon(MovingObjectPosition hit) {
      if (this.sword.getValue()) {
         if (ClientManagers.moduleManager.killAura.isToggled()) {
            KillAura killAura = ClientManagers.moduleManager.killAura;
            if (ClientManagers.rotationController.isOwnedBy(killAura) && ClientManagers.moduleManager.killAura.target != null && !mc.thePlayer.isUsingItem()) {
               return true;
            }
         }

         if (mc.gameSettings.keyBindAttack.getPressTime() <= 0 && !mc.gameSettings.keyBindAttack.isKeyDown()) {
            if (this.lastAttackTimer.reached(650L)) {
               if (this.lastClickTimer.reached(250L)) {
                  return false;
               }
            }
         }

         if (hit == null || hit.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) {
            Rotation previousRotation = ClientManagers.rotationController.getPreviousRotation();
            double d = 1.0;
            double d2 = 6.0;
            MovingObjectPosition hit2 = RayTraceUtil.rayTraceEntity(1.0F, previousRotation, d2, d);
            return hit2 != null && hit2.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && hit2.entityHit instanceof EntityLivingBase;
         }
      }

      return false;
   }

   private void resetBackSwitchTimer() {
      if (this.ticksSinceToolNeeded > 1) {
         this.backSwitchDelay.nextValue();
      }

      this.ticksSinceToolNeeded = 0;
   }

   private double scoreMiningTool(ItemStack stack, MovingObjectPosition hit) {
      double d = 0.0;
      if (stack != null && (stack.getItem() instanceof ItemTool || stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemShears)) {
         double strVsBlock = (double)stack.getItem().getStrVsBlock(stack, mc.theWorld.getBlockState(hit.getBlockPos()).getBlock());
         Item item = stack.getItem();
         if (!(item instanceof ItemAxe) && !(item instanceof ItemPickaxe) && !(stack.getItem() instanceof ItemSpade)) {
            d = strVsBlock;
         } else {
            d += strVsBlock + (double)(strVsBlock > 1.0 ? EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack) : 0);
         }

         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 11.0;
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, stack) / 11.0;
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) / 33.0;
         return d - (double)stack.getItemDamage() / 10000.0;
      } else {
         return 1.0;
      }
   }

   private double scoreWeapon(ItemStack stack) {
      double d = 0.0;
      if (stack != null) {
         if (stack.getItem() instanceof ItemSword) {
            d += (double)(((ItemSword)stack.getItem()).getAttackDamage() + (float)EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) * 1.25F);
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, stack) / 11.0;
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, stack) / 11.0;
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 11.0;
            d += 0.001;
         } else if (stack.getItem() instanceof ItemTool) {
            d += (double)(((ItemTool)stack.getItem()).getDamageVsEntity() + (float)EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) * 1.25F);
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, stack) / 11.0;
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, stack) / 11.0;
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 11.0;
         }
      }

      return d;
   }

}
