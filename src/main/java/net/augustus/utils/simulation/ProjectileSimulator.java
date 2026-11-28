package net.augustus.utils.simulation;

import net.augustus.ClientManagers;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.augustus.modules.combat.BowAimbot;
import net.augustus.modules.legit.AutoRod;
import net.augustus.modules.legit.AutoRodNew;
import net.augustus.utils.interfaces.MC;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.chunk.Chunk;

public class ProjectileSimulator implements MC {
   private double prevPosX;
   private Random random = new Random();
   private MovingObjectPosition hitResult;
   private boolean inWater;
   private boolean firstUpdate;
   private double bbMinY;
   private double posZ;
   private double motionY;
   private EntityThrowable throwableEntity;
   private double prevPosY;
   private float height;
   private double bbMinX;
   private double bbMaxY;
   private AxisAlignedBB boundingBox;
   private int ticksInGround;
   private double bbMaxZ;
   private EntityLivingBase shooter;
   private float width = 0.25F;
   private int shakeTicks;
   private int ticksInAir;
   public int hitTick;
   private double posY;
   private double prevPosZ;
   private boolean inGround;
   private float fallDistance;
   private double motionX;
   private EntityArrow arrowEntity;
   private double bbMaxX;
   private ItemStack itemStack;
   private double bbMinZ;
   private double motionZ;
   private Vec3 tilePos;
   private Block inTile;
   private double posX;

   public void initFromShooter(EntityLivingBase entity, ItemStack stack) {
      this.tilePos = new Vec3(-1.0, -1.0, -1.0);
      this.itemStack = stack;
      Item item = stack.getItem();
      if (item instanceof ItemBow) {
         this.width = 0.5F;
         this.height = 0.5F;
      }

      float f;
      float f2;
      this.shooter = entity;
      f = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * mc.getTimer().renderPartialTicks;
      f2 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * mc.getTimer().renderPartialTicks;
      boolean useControllerRot =
         (ClientManagers.moduleManager.bowAimbot.isToggled() && ClientManagers.moduleManager.bowAimbot.getTarget() != null && ClientManagers.rotationController.isOwnedBy(ClientManagers.moduleManager.bowAimbot))
         || (ClientManagers.moduleManager.autoRod.isToggled() && ClientManagers.moduleManager.autoRod.getTarget() != null && ClientManagers.rotationController.isOwnedBy(ClientManagers.moduleManager.autoRod))
         || (ClientManagers.moduleManager.autoRodNew.isToggled() && ClientManagers.moduleManager.autoRodNew.getTarget() != null && ClientManagers.rotationController.isOwnedBy(ClientManagers.moduleManager.autoRodNew));
      if (useControllerRot) {
         f = ClientManagers.rotationController.getPreviousRotation().yaw + (ClientManagers.rotationController.getCurrentRotation().yaw - ClientManagers.rotationController.getPreviousRotation().yaw) * mc.getTimer().renderPartialTicks;
         f2 = ClientManagers.rotationController.getPreviousRotation().pitch + (ClientManagers.rotationController.getCurrentRotation().pitch - ClientManagers.rotationController.getPreviousRotation().pitch) * mc.getTimer().renderPartialTicks;
      }

      this.posX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)mc.getTimer().renderPartialTicks;
      this.posY = entity.lastTickPosY + (double)entity.getEyeHeight() + (entity.posY - entity.lastTickPosY) * (double)mc.getTimer().renderPartialTicks;
      this.posZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)mc.getTimer().renderPartialTicks;
      float f3 = this.width / 2.0F;
      float height2 = this.height;
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.boundingBox = new AxisAlignedBB(
         this.posX - (double)f3, this.posY, this.posZ - (double)f3, this.posX + (double)f3, this.posY + (double)height2, this.posZ + (double)f3
      );
      this.posX = this.posX + (double)(MathHelper.cos(f / 180.0F * 3.1415927F) * 0.16F * -1.0F);
      this.posY -= 0.10000000149011612;
      this.posZ = this.posZ + (double)(MathHelper.sin(f / 180.0F * 3.1415927F) * 0.16F * -1.0F);
      this.boundingBox = new AxisAlignedBB(
         this.posX - (double)f3, this.posY, this.posZ - (double)f3, this.posX + (double)f3, this.posY + (double)height2, this.posZ + (double)f3
      );
      this.copyBoundingBoxBounds(this.boundingBox);
      float f4 = item instanceof ItemBow ? 1.0F : 0.4F;
      this.motionX = (double)(-MathHelper.sin(f / 180.0F * 3.1415927F) * MathHelper.cos(f2 / 180.0F * 3.1415927F) * f4);
      this.motionY = (double)(-MathHelper.sin((f2 + this.getPitchOffset(stack)) / 180.0F * 3.1415927F) * f4);
      this.motionZ = (double)(MathHelper.cos(f / 180.0F * 3.1415927F) * MathHelper.cos(f2 / 180.0F * 3.1415927F) * f4);
      this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.getInitialVelocity(stack), 1.0F);
   }

   public boolean willArrowHitTarget(int n, EntityLivingBase entity, AxisAlignedBB box, double d, double d2) {

      for (int i = 0; i < n; i++) {
         if (this.motionY < 0.0 && this.posY < box.minY) {
            return false;
         }

         this.handleWaterMovement();
         IBlockState blockState = mc.theWorld.getBlockState(this.tilePos.xCoord, this.tilePos.yCoord, this.tilePos.zCoord);
         Block block = blockState.getBlock();
         if (block.getMaterial() != Material.air) {
            int n2 = MathHelper.floor_double(this.tilePos.xCoord);
            int n3 = MathHelper.floor_double(this.tilePos.yCoord);
            int n4 = MathHelper.floor_double(this.tilePos.zCoord);
            block.setBlockBoundsBasedOnState(mc.theWorld, n2, n3, n4);
            AxisAlignedBB collisionBoundingBox = block.getCollisionBoundingBox(mc.theWorld, blockState, n2, n3, n4);
            if (collisionBoundingBox != null && collisionBoundingBox.isVecInside(this.posX, this.posY, this.posZ)) {
               this.inGround = true;
            }
         }

         if (this.shakeTicks > 0) {
            this.shakeTicks--;
         }

         if (this.inGround) {
            if (this.ticksInGround > 2) {
               return false;
            }
         } else {
            MovingObjectPosition hit = mc.theWorld
               .rayTraceBlocksFast(this.posX, this.posY, this.posZ, this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ, false, true, false);
            Vec3 vec = new Vec3(this.posX, this.posY, this.posZ);
            Vec3 vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            if (hit != null) {
               this.hitResult = hit;
            }

            Entity entity2 = null;
            List list = mc.theWorld
               .getEntitiesWithinAABBExcludingEntity(this.arrowEntity, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0, 1.0, 1.0));
            double d3 = 0.0;

            for (int j = 0; j < list.size(); j++) {
               Entity entity3 = (Entity)list.get(j);
               if (this.shooter != null && entity3.canBeCollidedWith() && entity3 != this.shooter) {
                  float f = 0.3F;
                  MovingObjectPosition hit2 = entity3.getEntityBoundingBox().expand((double)f, (double)f, (double)f).calculateIntercept(vec2, vec);
                  if (hit2 != null) {
                     double d4 = vec2.squareDistanceTo(hit2.hitVec);
                     if (d4 < d3 || d3 == 0.0) {
                        entity2 = entity3;
                        d3 = d4;
                     }
                  }
               }
            }

            if (entity2 != null) {
               hit = new MovingObjectPosition(entity2);
               this.hitResult = hit;
               return entity2.getEntityId() == entity.getEntityId();
            }

            if (hit != null && hit.entityHit == null) {
               return false;
            }
         }

         this.posX = this.posX + this.motionX;
         this.posY = this.posY + this.motionY;
         this.posZ = this.posZ + this.motionZ;
         float f2 = 0.99F;
         float f3 = 0.05F;
         if (this.inWater) {
            f2 = 0.6F;
         }

         this.motionX *= (double)f2;
         this.motionY *= (double)f2;
         this.motionZ *= (double)f2;
         this.motionY -= (double)f3;
         double posZ2 = this.posZ;
         double posY2 = this.posY;
         this.updateBoundingBox(this.posX, posY2, posZ2);
         if (this.posY < 0.0) {
            i = n;
         }
      }

      return false;
   }

   public ArrayList simulateFishingHookTrajectory(int n) {
      ArrayList list = new ArrayList();

      for (int i = 0; i < n; i++) {
         this.prevPosX = this.posX;
         this.prevPosY = this.posY;
         this.prevPosZ = this.posZ;
         this.handleWaterMovement();
         if (this.inGround) {
            if (this.ticksInGround > 2) {
               i = n;
            }
         } else {
            MovingObjectPosition hit = mc.theWorld
               .rayTraceBlocksFast(this.posX, this.posY, this.posZ, this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ, false, true, false);
            Vec3 vec = new Vec3(this.posX, this.posY, this.posZ);
            Vec3 vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            if (hit != null) {
               vec2 = new Vec3(hit.hitVec.xCoord, hit.hitVec.yCoord, hit.hitVec.zCoord);
               this.hitResult = hit;
               i = n;
            }

            Entity entity = null;
            List list2 = mc.theWorld
               .getEntitiesWithinAABBExcludingEntity(this.arrowEntity, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0, 1.0, 1.0));
            double d = 0.0;

            for (int j = 0; j < list2.size(); j++) {
               Entity entity2 = (Entity)list2.get(j);
               if (this.shooter != null && entity2.canBeCollidedWith() && entity2 != this.shooter) {
                  float f = 0.3F;
                  MovingObjectPosition hit2 = entity2.getEntityBoundingBox().expand((double)f, (double)f, (double)f).calculateIntercept(vec2, vec);
                  if (hit2 != null) {
                     double d2 = vec2.squareDistanceTo(hit2.hitVec);
                     if (d2 < d || d == 0.0) {
                        entity = entity2;
                        d = d2;
                     }
                  }
               }
            }

            if (entity != null) {
               hit = new MovingObjectPosition(entity);
               this.hitResult = hit;
               i = n;
            }

            this.posX = this.posX + this.motionX;
            this.posY = this.posY + this.motionY;
            this.posZ = this.posZ + this.motionZ;
            float f2 = 0.92F;
            byte b = 5;
            double d3 = 0.0;

            for (int k = 0; k < b; k++) {
               AxisAlignedBB boundingBox2 = this.boundingBox;
               double d4 = boundingBox2.maxY - boundingBox2.minY;
               double d5 = boundingBox2.minY + d4 * (double)k / (double)b;
               double d6 = boundingBox2.minY + d4 * (double)(k + 1) / (double)b;
               AxisAlignedBB box2 = new AxisAlignedBB(boundingBox2.minX, d5, boundingBox2.minZ, boundingBox2.maxX, d6, boundingBox2.maxZ);
               if (mc.theWorld.isAABBInMaterial(box2, Material.water)) {
                  d3 += 1.0 / (double)b;
               }
            }

            double d7 = d3 * 2.0 - 1.0;
            this.motionY += 0.03999999910593033 * d7;
            if (d3 > 0.0) {
               f2 = (float)((double)f2 * 0.9);
               this.motionY = this.motionY + this.motionY * 0.8;
            }

            this.motionX *= (double)f2;
            this.motionY *= (double)f2;
            this.motionZ *= (double)f2;
            double posZ2 = this.posZ;
            double posY2 = this.posY;
            this.updateBoundingBox(this.posX, posY2, posZ2);
            list.add(new Vec3(this.posX, this.posY, this.posZ));
            if (this.posY < 0.0) {
               i = n;
            }
         }
      }

      return list;
   }

   private void updateBoundingBox(double d, double d2, double d3) {
      float f = this.width / 2.0F;
      float height2 = this.height;
      this.boundingBox = new AxisAlignedBB(d - (double)f, d2, d3 - (double)f, d + (double)f, d2 + (double)height2, d3 + (double)f);
   }

   public void initFromPosAndAngles(EntityLivingBase entity, ItemStack stack, float f, float f2, double d, double d2, double d3) {
      this.tilePos = new Vec3(-1.0, -1.0, -1.0);
      this.itemStack = stack;
      Item item = stack.getItem();
      if (item instanceof ItemBow) {
         this.width = 0.5F;
         this.height = 0.5F;
      }

      this.shooter = entity;
      this.posX = d;
      this.posY = d2 + (double)entity.getEyeHeight();
      this.posZ = d3;
      float f3 = this.width / 2.0F;
      float height2 = this.height;
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.boundingBox = new AxisAlignedBB(
         this.posX - (double)f3, this.posY, this.posZ - (double)f3, this.posZ + (double)f3, this.posY + (double)height2, this.posZ + (double)f3
      );
      this.posX = this.posX + (double)(MathHelper.cos(f / 180.0F * 3.1415927F) * 0.16F * -1.0F);
      this.posY -= 0.10000000149011612;
      this.posZ = this.posZ + (double)(MathHelper.sin(f / 180.0F * 3.1415927F) * 0.16F * -1.0F);
      this.boundingBox = new AxisAlignedBB(
         this.posX - (double)f3, this.posY, this.posZ - (double)f3, this.posZ + (double)f3, this.posY + (double)height2, this.posZ + (double)f3
      );
      this.copyBoundingBoxBounds(this.boundingBox);
      float f4 = item instanceof ItemBow ? 1.0F : 0.4F;
      this.motionX = (double)(-MathHelper.sin(f / 180.0F * 3.1415927F) * MathHelper.cos(f2 / 180.0F * 3.1415927F) * f4);
      this.motionY = (double)(-MathHelper.sin((f2 + this.getPitchOffset(stack)) / 180.0F * 3.1415927F) * f4);
      this.motionZ = (double)(MathHelper.cos(f / 180.0F * 3.1415927F) * MathHelper.cos(f2 / 180.0F * 3.1415927F) * f4);
      this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.getInitialVelocity(stack), 1.0F);
   }

   public int simulateArrowHitTick(int n, EntityLivingBase entity, AxisAlignedBB box) {

      for (int i = 0; i < n; i++) {
         this.prevPosX = this.posX;
         this.prevPosY = this.posY;
         this.prevPosZ = this.posZ;
         this.handleWaterMovementBounds();
         IBlockState blockState = mc.theWorld.getBlockState(this.tilePos.xCoord, this.tilePos.yCoord, this.tilePos.zCoord);
         Block block = blockState.getBlock();
         if (block.getMaterial() != Material.air) {
            int n2 = MathHelper.floor_double(this.tilePos.xCoord);
            int n3 = MathHelper.floor_double(this.tilePos.yCoord);
            int n4 = MathHelper.floor_double(this.tilePos.zCoord);
            block.setBlockBoundsBasedOnState(mc.theWorld, n2, n3, n4);
            AxisAlignedBB collisionBoundingBox = block.getCollisionBoundingBox(mc.theWorld, blockState, n2, n3, n4);
            if (collisionBoundingBox != null && collisionBoundingBox.isVecInside(this.posX, this.posY, this.posZ)) {
               this.inGround = true;
            }
         }

         if (this.shakeTicks > 0) {
            this.shakeTicks--;
         }

         if (this.inGround) {
            return 2147483647;
         }

         MovingObjectPosition hit = mc.theWorld
            .rayTraceBlocksFast(this.posX, this.posY, this.posZ, this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ, false, true, false);
         Vec3 vec = new Vec3(this.posX, this.posY, this.posZ);
         Vec3 vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         if (this.shooter != null && entity.canBeCollidedWith() && entity != this.shooter) {
            MovingObjectPosition hit2 = box.calculateIntercept(vec2, vec);
            if (hit2 != null) {
               if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && vec.distanceTo(hit2.hitVec) > vec.distanceTo(hit.hitVec)) {
                  return 2147483647;
               }

               return i;
            }
         }

         if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && mc.theWorld.getBlockState(hit.getBlockPos()).getBlock().isCollidable()) {
            return 2147483647;
         }

         this.posX = this.posX + this.motionX;
         this.posY = this.posY + this.motionY;
         this.posZ = this.posZ + this.motionZ;
         float f = 0.99F;
         float f2 = 0.05F;
         if (this.inWater) {
            f = 0.6F;
         }

         this.motionX *= (double)f;
         this.motionY *= (double)f;
         this.motionZ *= (double)f;
         this.motionY -= (double)f2;
         double posZ2 = this.posZ;
         double posY2 = this.posY;
         this.updateBoundingBoxBounds(this.posX, posY2, posZ2);
         if (this.posY < box.minY && this.motionY < 0.0) {
            return 2147483647;
         }
      }

      return 2147483647;
   }

   private boolean handleWaterMovement() {
      if (this.boundingBox != null) {
         if (this.handleMaterialAcceleration(this.boundingBox.minX + 0.001, this.boundingBox.minY + 0.4000000059604645 + 0.001, this.boundingBox.minZ + 0.001, this.boundingBox.maxX - 0.001, this.boundingBox.maxY - 0.4000000059604645 - 0.001, this.boundingBox.maxZ - 0.001, Material.water)) {
            this.fallDistance = 0.0F;
            this.inWater = true;
            return this.inWater;
         }
      }

      this.inWater = false;
      return this.inWater;
   }

   private void setThrowableHeading(double d, double d2, double d3, float f, float f2) {
      float f3 = MathHelper.sqrt_double(d * d + d2 * d2 + d3 * d3);
      d /= (double)f3;
      d2 /= (double)f3;
      d3 /= (double)f3;
      d *= (double)f;
      d2 *= (double)f;
      d3 *= (double)f;
      this.motionX = d;
      this.motionY = d2;
      this.motionZ = d3;
      this.ticksInGround = 0;
   }

   private Vec3 modifyAcceleration(Block block, int n, Vec3 vec, byte b, int n2, BlockPos pos) {
      if (block instanceof BlockLiquid) {
         vec.add(((BlockLiquid)block).getFlowVector(mc.theWorld, pos));
      }

      return vec;
   }

   public int simulateFishingHookHitTick(int n, EntityLivingBase entity, AxisAlignedBB box) {

      for (int i = 0; i < n; i++) {
         this.handleWaterMovement();
         if (this.inGround) {
            return 2147483647;
         }

         MovingObjectPosition hit = mc.theWorld
            .rayTraceBlocksFast(this.posX, this.posY, this.posZ, this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ, false, true, false);
         Vec3 vec = new Vec3(this.posX, this.posY, this.posZ);
         Vec3 vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         if (hit != null) {
            this.hitResult = hit;
            return 2147483647;
         }

         EntityLivingBase entity2 = null;
         double d = 0.0;
         if (this.shooter != null && entity.canBeCollidedWith() && entity != this.shooter) {
            float f = 0.3F;
            MovingObjectPosition hit2 = box.expand((double)f, (double)f, (double)f).calculateIntercept(vec2, vec);
            if (hit2 != null) {
               if (vec2.squareDistanceTo(hit2.hitVec) < d || d == 0.0) {
                  entity2 = entity;
               }
            }
         }

         if (entity2 != null) {
            return i;
         }

         this.posX = this.posX + this.motionX;
         this.posY = this.posY + this.motionY;
         this.posZ = this.posZ + this.motionZ;
         float f2 = 0.92F;
         byte b = 5;
         double d3 = 0.0;

         for (int j = 0; j < b; j++) {
            AxisAlignedBB boundingBox2 = this.boundingBox;
            double d4 = boundingBox2.maxY - boundingBox2.minY;
            double d5 = boundingBox2.minY + d4 * (double)j / (double)b;
            double d6 = boundingBox2.minY + d4 * (double)(j + 1) / (double)b;
            AxisAlignedBB box3 = new AxisAlignedBB(boundingBox2.minX, d5, boundingBox2.minZ, boundingBox2.maxX, d6, boundingBox2.maxZ);
            if (mc.theWorld.isAABBInMaterial(box3, Material.water)) {
               d3 += 1.0 / (double)b;
            }
         }

         double d7 = d3 * 2.0 - 1.0;
         this.motionY += 0.03999999910593033 * d7;
         if (d3 > 0.0) {
            f2 = (float)((double)f2 * 0.9);
            this.motionY = this.motionY + this.motionY * 0.8;
         }

         this.motionX *= (double)f2;
         this.motionY *= (double)f2;
         this.motionZ *= (double)f2;
         double posZ2 = this.posZ;
         double posY2 = this.posY;
         this.updateBoundingBox(this.posX, posY2, posZ2);
         if (this.posY < box.minY && this.motionY < 0.0) {
            return 2147483647;
         }
      }

      return 2147483647;
   }

   private float getInitialVelocity(ItemStack stack) {
      Item item = stack.getItem();
      if (item.equals(Items.experience_bottle)) {
         return 0.7F;
      } else if (item.equals(Items.potionitem) && ItemPotion.isSplash(stack.getMetadata())) {
         return 0.5F;
      } else if (item instanceof ItemBow) {
         float f = (float)(((ItemBow)item).getMaxItemUseDuration(stack) - mc.thePlayer.getItemInUseCount()) / 20.0F;
         f = (f * f + f * 2.0F) / 3.0F;
         if ((double)f < 0.1) {
            return 0.0F;
         } else {
            if (f > 1.0F) {
               f = 1.0F;
            }

            return f * 2.0F * 1.5F;
         }
      } else {
         return 1.5F;
      }
   }

   private boolean handleMaterialAcceleration(double d, double d2, double d3, double d4, double d5, double d6, Material material) {
      int n = MathHelper.floor_double(d);
      int n2 = MathHelper.floor_double(d4 + 1.0);
      int n3 = MathHelper.floor_double(d2);
      int n4 = MathHelper.floor_double(d5 + 1.0);
      int n5 = MathHelper.floor_double(d3);
      int n6 = MathHelper.floor_double(d6 + 1.0);
      if (!mc.theWorld.isAreaLoaded(n, n3, n5, n2, n4, n6, true)) {
         return false;
      } else {
         boolean flag = false;
         double d7 = 0.0;
         double d8 = 0.0;
         double d9 = 0.0;
         int n7 = 8000;
         int n8 = n >> 4;
         int n9 = n5 >> 4;
         Chunk chunkFromChunkCoords = mc.theWorld.getChunkFromChunkCoords(n8, n9);

         outerLoop:
         for (int i = n; i < n2; i++) {
            for (int j = n5; j < n6; j++) {
               int n10 = i >> 4;
               int n11 = j >> 4;
               if (n10 != n8 || n11 != n9) {
                  chunkFromChunkCoords = mc.theWorld.getChunkFromChunkCoords(n10, n11);
                  n8 = n10;
                  n9 = n11;
               }

               for (int k = n3; k < n4; k++) {
                  IBlockState blockState = chunkFromChunkCoords.getBlockState(i, k, j);
                  Block block = blockState.getBlock();
                  if (block.getMaterial() == material) {
                     if ((double)n4 >= ((double)((float)(k + 1) - BlockLiquid.getLiquidHeightPercent((Integer)blockState.getValue(BlockLiquid.LEVEL))))) {
                        flag = true;
                        if (block instanceof BlockLiquid) {
                           Vec3 flowVector = ((BlockLiquid)block).getFlowVector(mc.theWorld, blockState, chunkFromChunkCoords, i, k, j);
                           d7 += flowVector.xCoord;
                           d8 += flowVector.yCoord;
                           d9 += flowVector.zCoord;
                        }
                     }
                  }

                  if (--n7 == 0) {
                     break outerLoop;
                  }
               }
            }
         }

         if (this.vectorLength(d7, d8, d9) > 0.0) {
            double d11 = this.vectorLength(d7, d8, d9);
            if (d11 < 1.0E-4) {
               d7 = 0.0;
               d8 = 0.0;
               d9 = 0.0;
            } else {
               d7 /= d11;
               d8 /= d11;
               d9 /= d11;
            }

            double d12 = 0.014;
            this.motionX += d7 * d12;
            this.motionY += d8 * d12;
            this.motionZ += d9 * d12;
         }

         return flag;
      }
   }

   public void initFromArrow(EntityArrow entityArrow) {
      this.shooter = mc.thePlayer;
      this.posX = entityArrow.posX;
      this.posY = entityArrow.posY;
      this.posZ = entityArrow.posZ;
      this.prevPosX = entityArrow.lastTickPosX;
      this.prevPosY = entityArrow.lastTickPosY;
      this.prevPosZ = entityArrow.lastTickPosZ;
      this.motionX = entityArrow.motionX;
      this.motionY = entityArrow.motionY;
      this.motionZ = entityArrow.motionZ;
      this.tilePos = new Vec3((double)entityArrow.getXTile(), (double)entityArrow.getYTile(), (double)entityArrow.getZTile());
      this.boundingBox = new AxisAlignedBB(
         entityArrow.getEntityBoundingBox().minX,
         entityArrow.getEntityBoundingBox().minY,
         entityArrow.getEntityBoundingBox().minZ,
         entityArrow.getEntityBoundingBox().maxX,
         entityArrow.getEntityBoundingBox().maxY,
         entityArrow.getEntityBoundingBox().maxZ
      );
      this.copyBoundingBoxBounds(this.boundingBox);
      this.ticksInGround = entityArrow.getTicksInGround();
      this.inWater = entityArrow.isInWater();
      this.firstUpdate = entityArrow.firstUpdate;
      this.fallDistance = entityArrow.fallDistance;
      this.inTile = entityArrow.getInTile();
      this.ticksInAir = entityArrow.getTicksInAir();
      this.inGround = entityArrow.isInGround();
      this.shakeTicks = entityArrow.arrowShake;
      this.width = entityArrow.width;
      this.height = entityArrow.height;
      this.arrowEntity = entityArrow;
   }

   private boolean handleWaterMovementBounds() {
      if (this.handleMaterialAcceleration(this.bbMinX + 0.001, this.bbMinY + 0.4000000059604645 + 0.001, this.bbMinZ + 0.001, this.bbMaxX - 0.001, this.bbMaxY - 0.4000000059604645 - 0.001, this.bbMaxZ - 0.001, Material.water)) {
         this.fallDistance = 0.0F;
         this.inWater = true;
      } else {
         this.inWater = false;
      }

      return this.inWater;
   }

   public ArrayList simulateArrowTrajectoryWithExpand(int n, float f) {
      if (this.arrowEntity == null || !this.arrowEntity.onGround && !this.arrowEntity.isInGround()) {
         ArrayList list = new ArrayList();

         for (int i = 0; i < n; i++) {
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;
            this.handleWaterMovement();
            IBlockState blockState = mc.theWorld.getBlockState(this.tilePos.xCoord, this.tilePos.yCoord, this.tilePos.zCoord);
            Block block = blockState.getBlock();
            if (block.getMaterial() != Material.air) {
               int n2 = MathHelper.floor_double(this.tilePos.xCoord);
               int n3 = MathHelper.floor_double(this.tilePos.yCoord);
               int n4 = MathHelper.floor_double(this.tilePos.zCoord);
               block.setBlockBoundsBasedOnState(mc.theWorld, n2, n3, n4);
               AxisAlignedBB collisionBoundingBox = block.getCollisionBoundingBox(mc.theWorld, blockState, n2, n3, n4);
               if (collisionBoundingBox != null && collisionBoundingBox.isVecInside(this.posX, this.posY, this.posZ)) {
                  this.inGround = true;
               }
            }

            if (this.shakeTicks > 0) {
               this.shakeTicks--;
            }

            if (this.inGround) {
               if (this.ticksInGround > 2) {
                  break;
               }
            } else {
               MovingObjectPosition hit = mc.theWorld
                  .rayTraceBlocksFast(this.posX, this.posY, this.posZ, this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ, false, true, false);
               Vec3 vec = new Vec3(this.posX, this.posY, this.posZ);
               Vec3 vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
               if (this.hitResult == null && hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                  this.hitResult = hit;
               }

               Entity entity = null;
               List list2 = mc.theWorld
                  .getEntitiesWithinAABBExcludingEntity(this.arrowEntity, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0, 1.0, 1.0));
               double d = 0.0;

               for (int j = 0; j < list2.size(); j++) {
                  Entity entity2 = (Entity)list2.get(j);
                  if (this.shooter != null && entity2.canBeCollidedWith()) {
                     float f2 = 0.3F + (entity2 == this.shooter ? f : 0.1F);
                     MovingObjectPosition hit2 = entity2.getEntityBoundingBox().expand((double)f2, (double)f2, (double)f2).calculateIntercept(vec2, vec);
                     if (hit2 != null) {
                        double d2 = vec2.squareDistanceTo(hit2.hitVec);
                        if (d2 < d || d == 0.0) {
                           entity = entity2;
                           d = d2;
                        }
                     }
                  }
               }

               if (entity != null) {
                  this.hitResult = new MovingObjectPosition(entity);
                  this.hitTick = i;
               }
            }

            this.posX = this.posX + this.motionX;
            this.posY = this.posY + this.motionY;
            this.posZ = this.posZ + this.motionZ;
            float f3 = 0.99F;
            float f4 = 0.05F;
            if (this.inWater) {
               f3 = 0.6F;
            }

            this.motionX *= (double)f3;
            this.motionY *= (double)f3;
            this.motionZ *= (double)f3;
            this.motionY -= (double)f4;
            double posZ2 = this.posZ;
            double posY2 = this.posY;
            this.updateBoundingBox(this.posX, posY2, posZ2);
            list.add(new Vec3(this.posX, this.posY, this.posZ));
            if (this.posY < 0.0) {
               i = n;
            }
         }

         return list;
      } else {
         return null;
      }
   }

   private void copyBoundingBoxBounds(AxisAlignedBB box) {
      this.bbMinX = box.minX;
      this.bbMinY = box.minY;
      this.bbMinZ = box.minZ;
      this.bbMaxX = box.maxX;
      this.bbMaxY = box.maxY;
      this.bbMaxZ = box.maxZ;
   }

   public void stepThrowableTicks(int n) {

      for (int i = 0; i < n; i++) {
         this.prevPosX = this.posX;
         this.prevPosY = this.posY;
         this.prevPosZ = this.posZ;
         this.handleWaterMovementBounds();
         if (this.inGround) {
            if (mc.theWorld.getBlockState(this.tilePos.xCoord, this.tilePos.yCoord, this.tilePos.zCoord).getBlock() == this.inTile) {
               this.ticksInGround++;
               if (this.ticksInGround > 2) {
                  i = n;
               }
            }

            this.inGround = false;
            this.motionX = this.motionX * (double)(this.random.nextFloat() * 0.2F);
            this.motionY = this.motionY * (double)(this.random.nextFloat() * 0.2F);
            this.motionZ = this.motionZ * (double)(this.random.nextFloat() * 0.2F);
            this.ticksInGround = 0;
            this.ticksInAir = 0;
         } else {
            this.ticksInAir++;
         }

         MovingObjectPosition hit = mc.theWorld
            .rayTraceBlocksFast(this.posX, this.posY, this.posZ, this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         if (hit != null
            && (
               hit.getBlockPos() == null
                  || !(mc.theWorld.getBlockState2(hit.getBlockPos().getX(), hit.getBlockPos().getY(), hit.getBlockPos().getZ()).getBlock() instanceof BlockLiquid)
            )) {
            this.hitResult = hit;
            i = n;
         }

         this.posX = this.posX + this.motionX;
         this.posY = this.posY + this.motionY;
         this.posZ = this.posZ + this.motionZ;
         float f = 0.99F;
         float f2;
         if (this.throwableEntity == null) {
            f2 = this.getGravityVelocity(this.itemStack);
         } else {
            f2 = this.throwableEntity.getGravityVelocity();
         }

         float f3 = f2;
         if (this.inWater) {
            f = 0.8F;
         }

         this.motionX *= (double)f;
         this.motionY *= (double)f;
         this.motionZ *= (double)f;
         this.motionY -= (double)f3;
         double posZ2 = this.posZ;
         double posY2 = this.posY;
         this.updateBoundingBoxBounds(this.posX, posY2, posZ2);
         if (this.posY < 0.0) {
            i = n;
         }
      }
   }

   public void initFromThrowable(EntityThrowable entityThrowable) {
      this.shooter = mc.thePlayer;
      this.posX = entityThrowable.posX;
      this.posY = entityThrowable.posY;
      this.posZ = entityThrowable.posZ;
      this.prevPosX = entityThrowable.lastTickPosX;
      this.prevPosY = entityThrowable.lastTickPosY;
      this.prevPosZ = entityThrowable.lastTickPosZ;
      this.motionX = entityThrowable.motionX;
      this.motionY = entityThrowable.motionY;
      this.motionZ = entityThrowable.motionZ;
      this.tilePos = new Vec3((double)entityThrowable.getXTile(), (double)entityThrowable.getYTile(), (double)entityThrowable.getZTile());
      this.boundingBox = new AxisAlignedBB(
         entityThrowable.getEntityBoundingBox().minX,
         entityThrowable.getEntityBoundingBox().minY,
         entityThrowable.getEntityBoundingBox().minZ,
         entityThrowable.getEntityBoundingBox().maxX,
         entityThrowable.getEntityBoundingBox().maxY,
         entityThrowable.getEntityBoundingBox().maxZ
      );
      this.copyBoundingBoxBounds(this.boundingBox);
      this.ticksInGround = entityThrowable.getTicksInGround();
      this.inWater = entityThrowable.isInWater();
      this.firstUpdate = entityThrowable.firstUpdate;
      this.fallDistance = entityThrowable.fallDistance;
      this.inTile = entityThrowable.getInTile();
      this.ticksInAir = entityThrowable.getTicksInAir();
      this.inGround = entityThrowable.isInGround();
      this.shakeTicks = entityThrowable.throwableShake;
      this.width = entityThrowable.width;
      this.height = entityThrowable.height;
      this.throwableEntity = entityThrowable;
   }

   private int estimateTicksToHeight(double d, double d2, double d3) {

      for (int i = 0; i < 300; i++) {
         d *= d3;
         if (d + 2.0 > d2 && d - 2.0 < d2) {
            return i;
         }

         d3 = d3 * 0.9900000095367432 - 0.05000000074505806;
      }

      return -1;
   }

   private float getPitchOffset(ItemStack stack) {
      Item item = stack.getItem();
      return !item.equals(Items.experience_bottle) && (!item.equals(Items.potionitem) || !ItemPotion.isSplash(stack.getMetadata())) ? 0.0F : -20.0F;
   }

   public void initWithAngles(EntityLivingBase entity, ItemStack stack, double d, double d2, double d3, float f, float f2) {
      this.tilePos = new Vec3(-1.0, -1.0, -1.0);
      this.itemStack = stack;
      Item item = stack.getItem();
      if (item instanceof ItemBow) {
         this.width = 0.5F;
         this.height = 0.5F;
      }

      this.shooter = entity;
      this.posX = entity.posX;
      this.posY = entity.posY + (double)entity.getEyeHeight();
      this.posZ = entity.posZ;
      float f3 = this.width / 2.0F;
      float height2 = this.height;
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.posX = this.posX + (double)(MathHelper.cos(f / 180.0F * 3.1415927F) * 0.16F * -1.0F);
      this.posY -= 0.10000000149011612;
      this.posZ = this.posZ + (double)(MathHelper.sin(f / 180.0F * 3.1415927F) * 0.16F * -1.0F);
      this.boundingBox = new AxisAlignedBB(
         this.posX - (double)f3, this.posY, this.posZ - (double)f3, this.posX + (double)f3, this.posY + (double)height2, this.posZ + (double)f3
      );
      this.copyBoundingBoxBounds(this.boundingBox);
      float f4 = item instanceof ItemBow ? 1.0F : 0.4F;
      this.motionX = (double)(-MathHelper.sin(f / 180.0F * 3.1415927F) * MathHelper.cos(f2 / 180.0F * 3.1415927F) * f4);
      this.motionY = (double)(-MathHelper.sin((f2 + this.getPitchOffset(stack)) / 180.0F * 3.1415927F) * f4);
      this.motionZ = (double)(MathHelper.cos(f / 180.0F * 3.1415927F) * MathHelper.cos(f2 / 180.0F * 3.1415927F) * f4);
      this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.getInitialVelocity(stack), 1.0F);
   }

   public ArrayList simulateArrowTrajectory(int n) {
      if (this.arrowEntity == null || !this.arrowEntity.onGround && !this.arrowEntity.isInGround()) {
         ArrayList list = new ArrayList();

         for (int i = 0; i < n; i++) {
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;
            this.handleWaterMovement();
            IBlockState blockState = mc.theWorld.getBlockState(this.tilePos.xCoord, this.tilePos.yCoord, this.tilePos.zCoord);
            Block block = blockState.getBlock();
            if (block.getMaterial() != Material.air) {
               int n2 = MathHelper.floor_double(this.tilePos.xCoord);
               int n3 = MathHelper.floor_double(this.tilePos.yCoord);
               int n4 = MathHelper.floor_double(this.tilePos.zCoord);
               block.setBlockBoundsBasedOnState(mc.theWorld, n2, n3, n4);
               AxisAlignedBB collisionBoundingBox = block.getCollisionBoundingBox(mc.theWorld, blockState, n2, n3, n4);
               if (collisionBoundingBox != null && collisionBoundingBox.isVecInside(this.posX, this.posY, this.posZ)) {
                  this.inGround = true;
               }
            }

            if (this.shakeTicks > 0) {
               this.shakeTicks--;
            }

            if (this.inGround) {
               if (this.ticksInGround > 2) {
                  break;
               }
            } else {
               MovingObjectPosition hit = mc.theWorld
                  .rayTraceBlocksFast(this.posX, this.posY, this.posZ, this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ, false, true, false);
               Vec3 vec = new Vec3(this.posX, this.posY, this.posZ);
               Vec3 vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
               if (hit != null) {
                  this.hitResult = hit;
                  break;
               }

               Entity entity = null;
               List list2 = mc.theWorld
                  .getEntitiesWithinAABBExcludingEntity(this.arrowEntity, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0, 1.0, 1.0));
               double d = 0.0;

               for (int j = 0; j < list2.size(); j++) {
                  Entity entity2 = (Entity)list2.get(j);
                  if (this.shooter != null && entity2.canBeCollidedWith() && entity2 != this.shooter) {
                     float f = 0.3F;
                     MovingObjectPosition hit2 = entity2.getEntityBoundingBox().expand((double)f, (double)f, (double)f).calculateIntercept(vec2, vec);
                     if (hit2 != null) {
                        double d2 = vec2.squareDistanceTo(hit2.hitVec);
                        if (d2 < d || d == 0.0) {
                           entity = entity2;
                           d = d2;
                        }
                     }
                  }
               }

               if (entity != null) {
                  hit = new MovingObjectPosition(entity);
                  this.hitResult = hit;
                  i = n;
               }

               if (hit != null && hit.entityHit == null) {
                  BlockPos blockPos = hit.getBlockPos();
                  this.tilePos = new Vec3((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ());
                  this.inTile = mc.theWorld.getBlockState2(blockPos.getX(), blockPos.getY(), blockPos.getZ()).getBlock();
                  this.motionX = (double)((float)(hit.hitVec.xCoord - this.posX));
                  this.motionY = (double)((float)(hit.hitVec.yCoord - this.posY));
                  this.motionZ = (double)((float)(hit.hitVec.zCoord - this.posZ));
                  float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                  this.posX = this.posX + this.motionX / (double)f2 * 0.05000000074505806 * -1.0;
                  this.posY = this.posY + this.motionY / (double)f2 * 0.05000000074505806 * -1.0;
                  this.posZ = this.posZ + this.motionZ / (double)f2 * 0.05000000074505806 * -1.0;
                  this.inGround = true;
                  this.shakeTicks = 7;
               }
            }

            this.posX = this.posX + this.motionX;
            this.posY = this.posY + this.motionY;
            this.posZ = this.posZ + this.motionZ;
            float f3 = 0.99F;
            float f4 = 0.05F;
            if (this.inWater) {
               f3 = 0.6F;
            }

            this.motionX *= (double)f3;
            this.motionY *= (double)f3;
            this.motionZ *= (double)f3;
            this.motionY -= (double)f4;
            double posZ2 = this.posZ;
            double posY2 = this.posY;
            this.updateBoundingBox(this.posX, posY2, posZ2);
            list.add(new Vec3(this.posX, this.posY, this.posZ));
            if (this.posY < 0.0) {
               i = n;
            }
         }

         return list;
      } else {
         return new ArrayList();
      }
   }

   public void initFromShooterAngles(EntityLivingBase entity, ItemStack stack, float f, float f2) {
      double posZ2 = entity.posZ;
      double posY2 = entity.posY;
      this.initWithAngles(entity, stack, entity.posX, posY2, posZ2, f, f2);
   }

   public int simulateThrowableHitTick(int n, EntityLivingBase entity, AxisAlignedBB box) {

      for (int i = 0; i < n; i++) {
         this.prevPosX = this.posX;
         this.prevPosY = this.posY;
         this.prevPosZ = this.posZ;
         this.handleWaterMovement();
         if (this.shakeTicks > 0) {
            this.shakeTicks--;
         }

         if (this.inGround) {
            return 2147483647;
         }

         this.ticksInAir++;
         if (this.ticksInGround > 2) {
            return 2147483647;
         }

         MovingObjectPosition hit = mc.theWorld
            .rayTraceBlocksFast(this.posX, this.posY, this.posZ, this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ, false, true, false);
         Vec3 vec = new Vec3(this.posX, this.posY, this.posZ);
         Vec3 vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         EntityLivingBase entity2 = null;
         if (this.shooter != null && entity.canBeCollidedWith() && entity != this.shooter) {
            if (box.calculateIntercept(vec2, vec) != null) {
               entity2 = entity;
            }
         }

         if (entity2 != null) {
            return i;
         }

         if (hit != null && hit.entityHit == null) {
            return 2147483647;
         }

         this.posX = this.posX + this.motionX;
         this.posY = this.posY + this.motionY;
         this.posZ = this.posZ + this.motionZ;
         float f = 0.99F;
         float f2;
         if (this.throwableEntity == null) {
            f2 = this.getGravityVelocity(this.itemStack);
         } else {
            f2 = this.throwableEntity.getGravityVelocity();
         }

         float f3 = f2;
         if (this.inWater) {
            f = 0.8F;
         }

         this.motionX *= (double)f;
         this.motionY *= (double)f;
         this.motionZ *= (double)f;
         this.motionY -= (double)f3;
         double posZ2 = this.posZ;
         double posY2 = this.posY;
         this.updateBoundingBox(this.posX, posY2, posZ2);
         if (this.posY < box.minY && this.motionY < 0.0) {
            i = n;
         }
      }

      return 2147483647;
   }

   public boolean willFishingHookHitTarget(int n, EntityLivingBase entity, AxisAlignedBB box) {

      for (int i = 0; i < n; i++) {
         this.handleWaterMovement();
         if (this.inGround) {
            return false;
         }

         MovingObjectPosition hit = mc.theWorld
            .rayTraceBlocksFast(this.posX, this.posY, this.posZ, this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ, false, true, false);
         Vec3 vec = new Vec3(this.posX, this.posY, this.posZ);
         Vec3 vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         if (hit != null) {
            this.hitResult = hit;
            return false;
         }

         EntityLivingBase entity2 = null;
         double d = 0.0;
         if (this.shooter != null && entity.canBeCollidedWith() && entity != this.shooter) {
            float f = 0.3F;
            MovingObjectPosition hit2 = box.calculateIntercept(vec2, vec);
            if (hit2 != null) {
               if (vec2.squareDistanceTo(hit2.hitVec) < d || d == 0.0) {
                  entity2 = entity;
               }
            }
         }

         if (entity2 != null) {
            return true;
         }

         this.posX = this.posX + this.motionX;
         this.posY = this.posY + this.motionY;
         this.posZ = this.posZ + this.motionZ;
         float f2 = 0.92F;
         byte b = 5;
         double d3 = 0.0;

         for (int j = 0; j < b; j++) {
            AxisAlignedBB boundingBox2 = this.boundingBox;
            double d4 = boundingBox2.maxY - boundingBox2.minY;
            double d5 = boundingBox2.minY + d4 * (double)j / (double)b;
            double d6 = boundingBox2.minY + d4 * (double)(j + 1) / (double)b;
            AxisAlignedBB box2 = new AxisAlignedBB(boundingBox2.minX, d5, boundingBox2.minZ, boundingBox2.maxX, d6, boundingBox2.maxZ);
            if (mc.theWorld.isAABBInMaterial(box2, Material.water)) {
               d3 += 1.0 / (double)b;
            }
         }

         double d7 = d3 * 2.0 - 1.0;
         this.motionY += 0.03999999910593033 * d7;
         if (d3 > 0.0) {
            f2 = (float)((double)f2 * 0.9);
            this.motionY = this.motionY + this.motionY * 0.8;
         }

         this.motionX *= (double)f2;
         this.motionY *= (double)f2;
         this.motionZ *= (double)f2;
         double posZ2 = this.posZ;
         double posY2 = this.posY;
         this.updateBoundingBox(this.posX, posY2, posZ2);
         if (this.posY < box.minY && this.motionY < 0.0) {
            return false;
         }
      }

      return false;
   }

   public MovingObjectPosition getHitResult() {
      return this.hitResult;
   }

   private double computeFallbackOffset(double d, double d2) {
      return 0.0;
   }

   public ArrayList simulateThrowableTrajectory(int n) {
      ArrayList list = new ArrayList();

      for (int i = 0; i < n; i++) {
         this.prevPosX = this.posX;
         this.prevPosY = this.posY;
         this.prevPosZ = this.posZ;
         this.handleWaterMovement();
         if (this.shakeTicks > 0) {
            this.shakeTicks--;
         }

         if (this.inGround) {
            if (mc.theWorld.getBlockState(this.tilePos.xCoord, this.tilePos.yCoord, this.tilePos.zCoord).getBlock() == this.inTile) {
               this.ticksInGround++;
               if (this.ticksInGround > 2) {
                  i = n;
               }
            }

            this.inGround = false;
            this.motionX = this.motionX * (double)(this.random.nextFloat() * 0.2F);
            this.motionY = this.motionY * (double)(this.random.nextFloat() * 0.2F);
            this.motionZ = this.motionZ * (double)(this.random.nextFloat() * 0.2F);
            this.ticksInGround = 0;
            this.ticksInAir = 0;
         } else {
            this.ticksInAir++;
         }

         MovingObjectPosition hit = mc.theWorld
            .rayTraceBlocksFast(this.posX, this.posY, this.posZ, this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         Vec3 vec = new Vec3(this.posX, this.posY, this.posZ);
         Vec3 vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         if (hit != null
            && (
               hit.getBlockPos() == null
                  || !(mc.theWorld.getBlockState2(hit.getBlockPos().getX(), hit.getBlockPos().getY(), hit.getBlockPos().getZ()).getBlock() instanceof BlockLiquid)
            )) {
            vec2 = new Vec3(hit.hitVec.xCoord, hit.hitVec.yCoord, hit.hitVec.zCoord);
            this.hitResult = hit;
            i = n;
         }

         Entity entity = null;
         List list2 = mc.theWorld.getEntitiesWithinAABBExcludingEntity(this.throwableEntity, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0, 1.0, 1.0));
         double d = 0.0;
         Object obj = this.throwableEntity == null ? mc.thePlayer : this.throwableEntity.getThrower();
         if (obj != null) {
            for (Entity entity2 : (Iterable<Entity>)(list2)) {
               if (entity2.canBeCollidedWith() && (entity2 != obj || this.ticksInAir >= 5)) {
                  float f = 0.3F;
                  MovingObjectPosition hit2 = entity2.getEntityBoundingBox().expand((double)f, (double)f, (double)f).calculateIntercept(vec, vec2);
                  if (hit2 != null) {
                     double d2 = vec.squareDistanceTo(hit2.hitVec);
                     if (d2 < d || d == 0.0) {
                        entity = entity2;
                        d = d2;
                     }
                  }
               }
            }
         }

         if (entity != null) {
            hit = new MovingObjectPosition(entity);
            this.hitResult = hit;
            i = n;
         }

         this.posX = this.posX + this.motionX;
         this.posY = this.posY + this.motionY;
         this.posZ = this.posZ + this.motionZ;
         float f2 = 0.99F;
         float f3;
         if (this.throwableEntity == null) {
            f3 = this.getGravityVelocity(this.itemStack);
         } else {
            f3 = this.throwableEntity.getGravityVelocity();
         }

         float f4 = f3;
         if (this.inWater) {
            f2 = 0.8F;
         }

         this.motionX *= (double)f2;
         this.motionY *= (double)f2;
         this.motionZ *= (double)f2;
         this.motionY -= (double)f4;
         double posZ2 = this.posZ;
         double posY2 = this.posY;
         this.updateBoundingBox(this.posX, posY2, posZ2);
         list.add(new Vec3(this.posX, this.posY, this.posZ));
         if (this.posY < 0.0) {
            i = n;
         }
      }

      return list;
   }

   public ProjectileSimulator() {
      this.height = 0.25F;
      this.hitResult = null;
   }

   private float getGravityVelocity(ItemStack stack) {
      Item item = stack.getItem();
      if (item.equals(Items.experience_bottle)) {
         return 0.07F;
      } else {
         return item.equals(Items.potionitem) && ItemPotion.isSplash(stack.getMetadata()) ? 0.05F : 0.03F;
      }
   }

   private void updateBoundingBoxBounds(double d, double d2, double d3) {
      float f = this.width / 2.0F;
      float height2 = this.height;
      this.bbMinX = d - (double)f;
      this.bbMinY = d2;
      this.bbMinZ = d3 - (double)f;
      this.bbMaxX = d + (double)f;
      this.bbMaxY = d2 + (double)height2;
      this.bbMaxZ = d3 + (double)f;
   }

   public double vectorLength(double d, double d2, double d3) {
      return (double)MathHelper.sqrt_double(d * d + d2 * d2 + d3 * d3);
   }




}
