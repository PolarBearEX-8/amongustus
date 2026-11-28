package net.augustus.modules.legit;

import net.minecraft.util.MovingObjectPosition;
import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.modules.player.InvManager;
import net.augustus.events.listeners.ClickListener;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.EventClick;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.settings.BooleanValue;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class AutoWater extends Module implements ClickListener, SilentSlotListener {
   private final BooleanValue autoSlot;
   private final BooleanValue silentSlotSwitching;
   private int bucketHotbarSlot;
   private int ticksSinceLastUse;
   private BlockPos lastAimedBlockPos;

   public AutoWater() {
       super("AutoWater", "AutomatischesWasser", new Color(66, 135, 245), Categorys.LEGIT);
      this.autoSlot = (BooleanValue)(new BooleanValue(this, "AutoSlot", true)).markVisualOnly();
      this.silentSlotSwitching = new BooleanValue(this, "SilentSlotSwitching", true);
      this.bucketHotbarSlot = -1;
      this.lastAimedBlockPos = null;
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(34);
   }

   public void onPostSilentSlot() {
      this.bucketHotbarSlot = -1;
      this.ticksSinceLastUse++;
      if (mc.thePlayer != null
         && ClientManagers.rotationController.hasNoActiveOwner()
         && mc.objectMouseOver != null
         && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
         && this.lastAimedBlockPos != null
         && this.lastAimedBlockPos.equalsPos(mc.objectMouseOver.getBlockPos())) {
         BlockPos pos = mc.objectMouseOver.getBlockPos().offset(mc.objectMouseOver.sideHit);
         AxisAlignedBB box = new AxisAlignedBB(
            (double)pos.getX(),
            (double)pos.getY(),
            (double)pos.getZ(),
            (double)(pos.getX() + 1),
            (double)pos.getY() + 0.9,
            (double)(pos.getZ() + 1)
         );
         if (box.intersectsWith(mc.thePlayer.getEntityBoundingBox().expand(-0.1, 0.0, -0.1)) && mc.thePlayer.isBurning()) {
            int n;
            if (this.autoSlot.getValue()) {
               n = this.findBucketHotbarSlot();
            } else {
               n = this.checkBucketSlot(mc.thePlayer.inventory.currentItem + 36);
            }

            this.bucketHotbarSlot = n;
         }

         boolean flag = false;
         IBlockState blockState = mc.theWorld.getBlockState(pos);
         if (blockState.getBlock() instanceof BlockLiquid) {
            flag = (Integer)blockState.getValue(BlockLiquid.LEVEL) == 0;
         }

         boolean flag2 = false;
         box = new AxisAlignedBB(
            (double)pos.getX(),
            (double)pos.getY(),
            (double)pos.getZ(),
            (double)(pos.getX() + 1),
            (double)pos.getY() + 4.5,
            (double)(pos.getZ() + 1)
         );
         if (box.intersectsWith(mc.thePlayer.getEntityBoundingBox())) {
            flag2 = true;
         }

         if ((mc.thePlayer.fallDistance > 3.0F && mc.objectMouseOver.sideHit == EnumFacing.UP && flag2 || mc.thePlayer.fallDistance == 0.0F && mc.thePlayer.isInWater() && flag)
            && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            int n4;
            if (this.autoSlot.getValue()) {
               n4 = this.findBucketHotbarSlot();
            } else {
               n4 = this.checkBucketSlot(mc.thePlayer.inventory.currentItem + 36);
            }

            this.bucketHotbarSlot = n4;
         }
      }

      this.lastAimedBlockPos = mc.objectMouseOver == null ? null : mc.objectMouseOver.getBlockPos();
      if (this.autoSlot.getValue() && this.bucketHotbarSlot != -1) {
         SilentSlotManager.requestSlot(this, (byte)5, this.bucketHotbarSlot, this.silentSlotSwitching.getValue());
      }
   }

   public void onClick(EventClick event) {
      if (this.bucketHotbarSlot != -1 && this.ticksSinceLastUse > 2) {
         event.addRightClicks((byte)1);
         this.ticksSinceLastUse = 0;
      }
   }

   private int findBucketHotbarSlot() {

      for (int i = 36; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
         ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
         if (stack != null
            && (
               stack.getItem() == Item.getItemById(326) && !mc.thePlayer.isInWater()
                  || stack.getItem() == Item.getItemById(325) && mc.thePlayer.fallDistance < 1.0F && mc.thePlayer.isInWater()
            )) {
            return i - 36;
         }
      }

      return -1;
   }

   private int checkBucketSlot(int n) {
      ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(n).getStack();
      return stack == null
            || (stack.getItem() != Item.getItemById(326) || mc.thePlayer.isInWater())
               && (stack.getItem() != Item.getItemById(325) || !(mc.thePlayer.fallDistance < 1.0F) || !mc.thePlayer.isInWater())
         ? -1
         : n - 36;
   }

}
