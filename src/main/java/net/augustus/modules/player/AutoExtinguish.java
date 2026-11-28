package net.augustus.modules.player;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ClickListener;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.EventClick;
import net.augustus.utils.rotation.MoveFixMode;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class AutoExtinguish extends Module implements TickListener, ClickListener, SilentSlotListener, LoadWorldListener, MoveInputListener {
   private final RandomizedRangeSettings verticalSpeed;
   private final BooleanValue randomizeYaw;
   private final BooleanValue notWhileKillAura;
   private final BooleanValue silentSlotSwitching;
   private int bucketHotbarSlot;
   private int previousBucketHotbarSlot;
   private Vec3 waterTargetPos;
   private BlockPos placedWaterBlockPos;
   private int ticksSinceWaterPlaced;

   public AutoExtinguish() {
       super("AutoExtinguish", "AutomatischesLöschen", new Color(252, 152, 3), Categorys.PLAYER);
      this.verticalSpeed = new RandomizedRangeSettings(this, "Vertical Speed", 15.0, 30.0, 0.0, 180.0, 0);
      this.randomizeYaw = new BooleanValue(this, "RandomizeYaw", true);
      this.notWhileKillAura = new BooleanValue(this, "NotWhileKillAura", false);
      this.silentSlotSwitching = new BooleanValue(this, "SilentSlotSwitching", true);
      this.bucketHotbarSlot = -1;
      this.previousBucketHotbarSlot = -1;
      this.waterTargetPos = null;
      this.placedWaterBlockPos = null;
      this.ticksSinceWaterPlaced = 0;
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      this.bucketHotbarSlot = -1;
      this.previousBucketHotbarSlot = -1;
      this.waterTargetPos = null;
      this.placedWaterBlockPos = null;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(34);
      this.toggleEventSubscription(17);
      this.toggleEventSubscription(21);
   }

   @Override
   public void onClick(EventClick event) {
      if (this.canOperate()) {
         this.ticksSinceWaterPlaced++;
         if (this.bucketHotbarSlot >= 0 && this.previousBucketHotbarSlot >= 0) {
            if (this.bucketHotbarSlot == mc.thePlayer.inventory.currentItem) {
               if (ClientManagers.rotationController.getPreviousRotation().pitch > 88.0F && this.waterTargetPos == null) {
                  if (this.isBurningWithoutProtection()) {
                     event.addRightClicks((byte)1);
                     event.setRightClickAction(() -> this.placeWaterAndRecordTarget());
                     return;
                  }
               }

               if (this.waterTargetPos != null) {
                  Vec3 vec = this.findNearbyWaterSourcePos((int)mc.thePlayer.lastGroundY);
                  if (vec == null && this.ticksSinceWaterPlaced > 2) {
                     this.waterTargetPos = null;
                     return;
                  }

                  if (vec != null) {
                     this.waterTargetPos = vec;
                  }

                  if (this.waterTargetPos != null && this.ticksSinceWaterPlaced > 0) {
                     if (!this.isBurningWithoutProtection()
                        && mc.objectMouseOver != null
                        && mc.previousObjectMouseOver != null
                        && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
                        && mc.previousObjectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
                        && this.placedWaterBlockPos != null) {
                        BlockPos pos = new BlockPos(this.waterTargetPos.xCoord, this.waterTargetPos.yCoord, this.waterTargetPos.zCoord);
                        if (pos.equalsPos(mc.objectMouseOver.getBlockPos()) && pos.equalsPos(mc.previousObjectMouseOver.getBlockPos())) {
                           event.addRightClicks((byte)1);
                           this.waterTargetPos = null;
                           this.ticksSinceWaterPlaced = 0;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public void onPostSilentSlot() {
      if (this.canOperate()) {
         this.previousBucketHotbarSlot = this.bucketHotbarSlot;
         if (this.isBurningWithoutProtection() || this.waterTargetPos != null) {
            this.bucketHotbarSlot = this.findBucketHotbarSlot();
            if (this.bucketHotbarSlot >= 0) {
               SilentSlotManager.requestSlot(this, (byte)-2, this.bucketHotbarSlot, this.silentSlotSwitching.getValue());
            }
         }
      }
   }

   @Override
   public void onEarlyTick() {
      if (this.canOperate()) {
         if ((this.isBurningWithoutProtection() || this.waterTargetPos != null) && this.bucketHotbarSlot >= 0) {
            if (this.waterTargetPos == null) {
               float f = RotationUtil.updatePitchWithGcd(ClientManagers.rotationController.getPreviousRotation().pitch, 90.0F, (float)this.verticalSpeed.nextValue());
               if (f != ClientManagers.rotationController.getPreviousRotation().pitch) {
                  Rotation previousRotation = ClientManagers.rotationController.getPreviousRotation();
                  previousRotation.pitch = f;
                  if (this.randomizeYaw.getValue() && f < 88.0F) {
                     float f2 = 2.0F;
                     float f3 = -2.0F;
                     previousRotation.yaw = previousRotation.yaw + DefaultRandomGenerator.instance.nextFloat(f3, f2);
                  }

                  previousRotation = RotationUtil.applyGcdFix(previousRotation, ClientManagers.rotationController.getPreviousRotation());
                  ClientManagers.rotationController.requestRotation(previousRotation, (byte)-2, this);
               }
            } else {
               Rotation rotationsToPos = RotationUtil.getRotationsToPos(this.waterTargetPos.xCoord, this.waterTargetPos.yCoord, this.waterTargetPos.zCoord, ClientManagers.rotationController.getPreviousRotation(), (float)this.verticalSpeed.nextValue(), (float)this.verticalSpeed.nextValue(), true);
               rotationsToPos = RotationUtil.applyGcdFix(rotationsToPos, ClientManagers.rotationController.getPreviousRotation());
               ClientManagers.rotationController.requestRotation(rotationsToPos, (byte)-2, this);
            }
         }
      }
   }

   private boolean canOperate() {
      return mc.isNotInGui() && (!this.notWhileKillAura.getValue() || !ClientManagers.moduleManager.killAura.isToggled() || ClientManagers.moduleManager.killAura.target == null);
   }

   private boolean isBurningWithoutProtection() {
      return mc.thePlayer != null && mc.thePlayer.isBurning() && !ClientManagers.moduleManager.scaffold.isToggled() && !mc.thePlayer.isPotionActive(Potion.fireResistance);
   }

   private int findBucketHotbarSlot() {

      for (int i = 36; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
         ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
         if (stack != null && (this.waterTargetPos == null ? stack.getItem().equals(Items.water_bucket) : stack.getItem().equals(Items.bucket))) {
            return i - 36;
         }
      }

      return -1;
   }

   private Vec3 getBlockFaceCenter(int n, int n2, int n3, EnumFacing facing) {
      return new Vec3(
         (double)n + (facing.getFrontOffsetX() == 0 ? 0.5 : (double)facing.getFrontOffsetX()),
         (double)n2 + (facing.getFrontOffsetY() == 0 ? 0.5 : (double)facing.getFrontOffsetY()),
         (double)n3 + (facing.getFrontOffsetZ() == 0 ? 0.5 : (double)facing.getFrontOffsetZ())
      );
   }

   private Vec3 getBlockFaceCenterFromPos(BlockPos pos, EnumFacing facing) {
      int x = pos.getX();
      int z = pos.getZ();
      return this.getBlockFaceCenter(x, pos.getY(), z, facing);
   }

   private Vec3 getHitFaceCenter(MovingObjectPosition hit) {
      EnumFacing sideHit = hit.sideHit;
      return this.getBlockFaceCenterFromPos(hit.getBlockPos(), sideHit);
   }

   private Vec3 findNearbyWaterSourcePos(int n) {

      for (int i = -10; i <= 10; i++) {
         for (int j = -10; j <= 10; j++) {
            IBlockState blockState2 = mc.theWorld
               .getBlockState2(MathHelper.floor_double(mc.thePlayer.posX + (double)i), n, MathHelper.floor_double(mc.thePlayer.posZ + (double)j));
            Block block = blockState2.getBlock();
            if (block instanceof BlockLiquid && (Integer)blockState2.getValue(BlockLiquid.LEVEL) == 0 && block.getMaterial() == Material.water) {
               return this.findAdjacentSolidFace(MathHelper.floor_double(mc.thePlayer.posX + (double)i), n, MathHelper.floor_double(mc.thePlayer.posZ + (double)j));
            }
         }
      }

      return null;
   }

   private Vec3 findAdjacentSolidFace(int n, int n2, int n3) {
      EnumFacing[] values = EnumFacing.getValues();
      int length = values.length;

      for (int i = 0; i < length; i++) {
         EnumFacing facing = values[i];
         if (mc.theWorld.getBlockState2(n + facing.getFrontOffsetX(), n2 + facing.getFrontOffsetY(), n3 + facing.getFrontOffsetZ()).getBlock().getMaterial() != Material.air) {
            return this.getBlockFaceCenter(n, n2, n3, facing);
         }
      }

      return null;
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.bucketHotbarSlot = -1;
      this.previousBucketHotbarSlot = -1;
      this.waterTargetPos = null;
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (ClientManagers.rotationController.isOwnedBy(this)) {
         event.setMoveFixMode(MoveFixMode.DEFAULT);
      }
   }

   private void placeWaterAndRecordTarget() {
      if (mc.objectMouseOver != null
         && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
         && mc.previousObjectMouseOver != null
         && mc.previousObjectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
         && mc.objectMouseOver.getBlockPos().equalsPos(mc.previousObjectMouseOver.getBlockPos())) {
         mc.rightClickMouse();
         this.waterTargetPos = this.getHitFaceCenter(mc.objectMouseOver);
         this.placedWaterBlockPos = mc.objectMouseOver.getBlockPos();
         this.ticksSinceWaterPlaced = 0;
      }
   }

}
