package net.augustus.modules.modes.fly;

import net.augustus.Augustus;
import net.augustus.events.EventClick;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.player.ContainerSlotQuery;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.utils.rotation.RotationController;
import net.augustus.utils.world.BlockUtil;
import java.util.OptionalInt;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import net.augustus.modules.movement.Fly;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class AirPlaceFlyMode extends FlyMode {
   private int targetYLevel = 0;
   private int verticalStepDir = 0;

   public AirPlaceFlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   @Override
   public void onEnable() {
      this.targetYLevel = (int)mc.thePlayer.lastGroundY;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreUpdate() {
   }

   @Override
   public void onMoveInput(EventMoveInput event) {
      if (mc.thePlayer.airTicks == 1) {
         this.targetYLevel = this.targetYLevel + this.verticalStepDir;
         System.out.println(this.verticalStepDir);
      }

      this.verticalStepDir = 0;
      if (event.isJump()) {
         this.verticalStepDir = 1;
      } else if (event.isSneak()) {
         event.setSneak(false);
         this.verticalStepDir = -1;
      }

      event.setJump(true);
   }

   @Override
   public void onClick(EventClick event) {
      event.setBlockAllClicks(true);
      event.setIgnoreVanillaClicks(true);
      if (mc.thePlayer.posY - 0.5 < (double)this.targetYLevel && !mc.thePlayer.onGround && mc.thePlayer.motionY < 0.0) {
         double d = mc.thePlayer.posX + mc.thePlayer.lastMotionX * 2.0;
         double d2 = mc.thePlayer.posZ + mc.thePlayer.lastMotionZ * 2.0;
         BlockPos pos = new BlockPos(d, (double)(this.targetYLevel - 1), d2);
         if (mc.theWorld.getBlockState(pos).getBlock().getMaterial() == Material.air
            && mc.thePlayer.getCurrentEquippedItem() != null
            && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBlock
            && mc.playerController
               .onPlayerRightClick(mc.thePlayer, mc.theWorld, mc.thePlayer.getCurrentEquippedItem(), pos, EnumFacing.UP, new Vec3(d, (double)pos.getY(), d2))
            )
          {
            mc.thePlayer.swingItem();
         }
      }
   }

   @Override
   public void onEarlyTick() {
      RotationController rotationController = Augustus.getInstance().getRotationController();
      Fly fly2 = Augustus.getInstance().getModuleManager().fly;
      rotationController.requestPitch(90.0F, (byte)-100, fly2);
   }

   @Override
   public void onMotion(EventMotion event) {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void onSilentSlot() {
      int n = this.findBlockHotbarSlot();
      if (n != -1) {
         SilentSlotManager.requestSlot(Augustus.getInstance().getModuleManager().fly, (short)-1000, n);
      }
   }

   private int findBlockHotbarSlot() {
      ContainerSlotQuery containerSlotQuery = ContainerSlotQuery.of(mc.thePlayer.inventoryContainer);
      containerSlotQuery = containerSlotQuery.fromSlot((byte)36);
      containerSlotQuery = containerSlotQuery.filterItem(obj -> obj instanceof ItemBlock);
      OptionalInt optionalInt = containerSlotQuery.filterStackAndSlot((obj2, obj3) -> isValidPlaceableBlock((ItemStack)obj2, (Integer)obj3)).findFirstSlotIndex();
      return optionalInt.isPresent() ? optionalInt.getAsInt() - 36 : -1;
   }

   private static boolean isValidPlaceableBlock(ItemStack stack, Integer num) {
      return stack != null && stack.stackSize > 0 && BlockUtil.isValidScaffoldBlock(stack);
   }
}
