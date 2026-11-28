package net.augustus.modules.combat;

import java.awt.Color;
import java.util.Comparator;
import java.util.OptionalInt;
import java.util.stream.Stream;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ClickListener;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.EventClick;
import net.augustus.settings.options.TestModuleTargetOption;
import net.augustus.utils.combat.CombatTargetUtil;
import net.augustus.utils.rotation.RayTraceUtil;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.player.ContainerSlotQuery;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.utils.rotation.Rotation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class Test extends Module implements TickListener, ClickListener, SilentSlotListener {
   private EntityLivingBase target;
   private BlockPos targetBlockPos;
   private BlockPos previousBlockPos;
   private boolean lavaPlaced;
   private int ticksSinceClick;

   public Test() {
       super("Test", "test", Color.black, Categorys.COMBAT);
      this.target = null;
      this.targetBlockPos = null;
      this.previousBlockPos = null;
      this.lavaPlaced = false;
      this.ticksSinceClick = 0;
   }

   @Override
   public void onEnable() {
      this.lavaPlaced = false;
      this.ticksSinceClick = 0;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(34);
   }

   @Override
   public void onEarlyTick() {
      EntityLivingBase target2 = this.target;
      this.target = null;
      this.target = this.findTarget();
      if (this.target != target2) {
         this.lavaPlaced = false;
         this.ticksSinceClick = 0;
      }

      if (this.target != null) {
         if (this.hasRequiredBucket()) {
            Vec3 rawServerPos = this.target.rawServerPos;
            this.previousBlockPos = this.targetBlockPos;
            this.targetBlockPos = this.lavaPlaced ? this.targetBlockPos : new BlockPos(rawServerPos.xCoord, rawServerPos.yCoord - 0.75, rawServerPos.zCoord);
            Block block = mc.theWorld.getBlockState(this.targetBlockPos).getBlock();
            if (block.getMaterial() != Material.air) {
               AxisAlignedBB box = new AxisAlignedBB(
                  (double)this.targetBlockPos.getX() + 0.05,
                  (double)this.targetBlockPos.getY() + block.getBlockBoundsMaxY(),
                  (double)this.targetBlockPos.getZ() + 0.05,
                  (double)this.targetBlockPos.getX() + 0.95,
                  (double)this.targetBlockPos.getY() + block.getBlockBoundsMaxY(),
                  (double)this.targetBlockPos.getZ() + 0.95
               );
               Vec3 nearestPointInBox = RotationUtil.getNearestPointInBox(box);
               double xCoord = nearestPointInBox.xCoord;
               double yCoord = nearestPointInBox.yCoord;
               double zCoord = nearestPointInBox.zCoord;
               Rotation previousRotation = ClientManagers.rotationController.getPreviousRotation();
               float f = 180.0F;
               Rotation rotationsToPos = RotationUtil.getRotationsToPos(xCoord, yCoord, zCoord, previousRotation, 180.0F, f, false);
               rotationsToPos = RotationUtil.applyGcdFix(rotationsToPos, ClientManagers.rotationController.getPreviousRotation());
               rotationsToPos = this.findValidBlockRotation(rotationsToPos);
               if (rotationsToPos == null) {
                  return;
               }

               ClientManagers.rotationController.requestRotation(rotationsToPos, (byte)-10, this);
            }
         }
      }
   }

   @Override
   public void onPostSilentSlot() {
      if (this.target != null && ClientManagers.rotationController.isOwnedBy(this)) {
         ContainerSlotQuery containerSlotQuery = ContainerSlotQuery.of(mc.thePlayer.inventoryContainer);
         containerSlotQuery = containerSlotQuery.fromSlot((byte)36);
         OptionalInt optionalInt = containerSlotQuery.filterItemExact(Item.getItemById(this.lavaPlaced ? 325 : 327)).findFirstSlotIndex();
         if (optionalInt.isPresent()) {
            SilentSlotManager.requestSlot(this, (byte)-10, optionalInt.getAsInt() - 36);
         }
      }
   }

   @Override
   public void onClick(EventClick event) {
      if (this.target != null
         && ClientManagers.rotationController.isOwnedBy(this)
         && this.previousBlockPos != null
         && this.targetBlockPos != null
         && this.previousBlockPos.equalsPos(this.targetBlockPos)
         && mc.objectMouseOver != null
         && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
         && mc.objectMouseOver.getBlockPos().equals(this.targetBlockPos)
         && mc.previousObjectMouseOver != null
         && mc.previousObjectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
         && mc.previousObjectMouseOver.getBlockPos().equals(this.targetBlockPos)) {
         event.addRightClicks((byte)1);
         event.setRightMouseDown(true);
         if (this.lavaPlaced) {
            this.toggle();
         }

         this.lavaPlaced = true;
         this.ticksSinceClick = 0;
      }

      this.ticksSinceClick++;
   }

   private Rotation findValidBlockRotation(Rotation rotation) {
      MovingObjectPosition hit = RayTraceUtil.rayTraceDefaultReach(1.0F, rotation.yaw, rotation.pitch);
      if (hit != null && hit.getBlockPos() != null && hit.getBlockPos().equalsPos(this.targetBlockPos)) {
         return rotation;
      } else {
         for (float i = (float)this.targetBlockPos.getX() + 0.03F; (double)i < (double)this.targetBlockPos.getX() + 0.97; i += 0.05F) {
            for (float j = (float)this.targetBlockPos.getZ() + 0.03F; (double)j < (double)this.targetBlockPos.getZ() + 0.97; j += 0.05F) {
               double d = (double)i;
               double d2 = (double)(this.targetBlockPos.getY() + 1);
               double d3 = (double)j;
               Rotation previousRotation = ClientManagers.rotationController.getPreviousRotation();
               float f = 180.0F;
               Rotation rotationsToPos = RotationUtil.getRotationsToPos(d, d2, d3, previousRotation, 180.0F, f, false);
               rotationsToPos = RotationUtil.applyGcdFix(rotationsToPos, ClientManagers.rotationController.getPreviousRotation());
               hit = RayTraceUtil.rayTraceDefaultReach(1.0F, rotationsToPos.yaw, rotationsToPos.pitch);
               if (hit != null && hit.getBlockPos() != null && hit.getBlockPos().equals(this.targetBlockPos)) {
                  return rotationsToPos;
               }
            }
         }

         return null;
      }
   }

   private EntityLivingBase findTarget() {
      return (EntityLivingBase)mc.theWorld.loadedEntityList.stream().filter(obj -> {
         if (obj instanceof EntityLivingBase && !(obj instanceof EntityPlayerSP) && mc.thePlayer != null && mc.thePlayer.getDistanceToEntity((Entity)obj) < 8.0F) {
            boolean isOptionSelected = TestModuleTargetOption.isOptionSelected(TestModuleTargetOption.PLAYER);
            boolean isOptionSelected2 = TestModuleTargetOption.isOptionSelected(TestModuleTargetOption.MOB);
            boolean isSelected = TestModuleTargetOption.ANIMAL.isSelected();
            boolean isOptionSelected3 = TestModuleTargetOption.isOptionSelected(TestModuleTargetOption.VILLAGER);
            boolean isOptionSelected4 = TestModuleTargetOption.isOptionSelected(TestModuleTargetOption.INVISIBLE);
            boolean isOptionSelected5 = TestModuleTargetOption.isOptionSelected(TestModuleTargetOption.DEAD);
            if (CombatTargetUtil.isValidTarget((Entity)obj, isOptionSelected, isOptionSelected2, isSelected, isOptionSelected3, false, isOptionSelected4, true, true, true, isOptionSelected5, true)) {
               return true;
            }
         }

         return false;
      }).min(Comparator.comparingDouble(obj2 -> (double)mc.thePlayer.getDistanceToEntity((Entity)obj2))).orElse(null);
   }

   private boolean hasRequiredBucket() {
      ContainerSlotQuery containerSlotQuery = ContainerSlotQuery.of(mc.thePlayer.inventoryContainer);
      containerSlotQuery = containerSlotQuery.fromSlot((byte)36);
      return containerSlotQuery.hasAnyOfItem(Item.getItemById(this.lavaPlaced ? 325 : 327));
   }

}
