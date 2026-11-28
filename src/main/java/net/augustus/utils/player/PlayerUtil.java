package net.augustus.utils.player;

import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.WorldSettings;
import net.augustus.events.AttackSlowdownStage;
import net.augustus.events.EventAttackSlowdown;
import net.augustus.events.EventMove;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventSlowDown;
import net.augustus.events.EventStep;
import net.augustus.events.MoveEventType;
import net.augustus.events.MoveInputStage;
import net.augustus.events.listeners.AttackSlowdownListener;
import net.augustus.events.listeners.MoveListener;
import net.augustus.events.listeners.SlowDownListener;
import net.augustus.events.listeners.StepListener;
import net.augustus.utils.simulation.EntityMovementPredictor;
import net.augustus.utils.simulation.PlayerInputPredictor;
import net.augustus.utils.simulation.PlayerTickSnapshot;
import net.augustus.viaversion.ViaAugustus;
import de.florianmichael.dietrichevents2.StateTypes;
import java.util.ArrayList;
import java.util.List;
import net.augustus.settings.StringValue;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockSlime;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class PlayerUtil implements MC {
   public static final double[] JUMP_Y_OFFSETS = new double[]{
      0.41999998688697815,
      0.7531999805212015,
      1.001335979112147,
      1.1661092609382138,
      1.249187078744681,
      1.249187078744681,
      1.1707870772188018,
      1.0155550727021998,
      0.7850277037892361,
      0.48071087633169185,
      0.10408037809303661
   };

   public static boolean hasPotionEffect(Potion potion) {
      return mc.thePlayer.getActivePotionEffect(potion) != null;
   }

   public static boolean canPlaceHeldItemAtMouseOver(ItemStack stack) {
      if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
         BlockPos blockPos = mc.objectMouseOver.getBlockPos();
         Vec3 hitVec = mc.objectMouseOver.hitVec;
         if (canPlaceBlockAt(stack, blockPos, mc.objectMouseOver.sideHit, hitVec)) {
            return true;
         }
      }

      return false;
   }

   public static double[] predictTargetPosition(EntityLivingBase entity, int n) {
      if (EntityMovementPredictor.hasInvalidPositionHistory(entity)) {
         return new double[]{
            entity.posX, entity.posY, entity.posZ, entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ, entity.motionX, entity.motionY, entity.motionZ, entity.onGround ? 1.0 : 0.0
         };
      } else {
         double d = MathHelper.clamp_double(entity.realPosX - entity.predictedPosX, -20.0, 20.0);
         double d2 = MathHelper.clamp_double(entity.realPosY - entity.predictedPosY, -20.0, 20.0);
         double d3 = MathHelper.clamp_double(entity.realPosZ - entity.predictedPosZ, -20.0, 20.0);
         double realPosX = entity.realPosX;
         double realPosY = entity.realPosY;
         double realPosZ = entity.realPosZ;
         double predictedPosX = entity.predictedPosX;
         double predictedPosY = entity.predictedPosY;
         double predictedPosZ = entity.predictedPosZ;
         float f = entity.width / 2.0F;
         AxisAlignedBB box = new AxisAlignedBB(
            realPosX - (double)f, realPosY, realPosZ - (double)f, realPosX + (double)f, realPosY + (double)entity.height, realPosZ + (double)f
         );
         boolean onGround = entity.onGround;
         boolean isCollidedHorizontally = entity.isCollidedHorizontally;
         boolean isCollidedVertically = entity.isCollidedVertically;
         if (entity.ticksExisted < 20) {
            return new double[]{realPosX, realPosY, realPosZ, predictedPosX, predictedPosY, predictedPosZ, d, d3, d2, onGround ? 1.0 : 0.0};
         } else {
            for (int i = 0; i <= n; i++) {
               predictedPosX = realPosX;
               predictedPosY = realPosY;
               predictedPosZ = realPosZ;
               d = entity.realPosX - entity.predictedPosX;
               d2 = entity.realPosY - entity.predictedPosY;
               double d4 = d;
               double d5 = entity.rotationPitchHead < 75.0F
                     && entity instanceof EntityPlayer
                     && ((EntityPlayer)entity).getCurrentEquippedItem() != null
                     && ((EntityPlayer)entity).getCurrentEquippedItem().getItem() instanceof ItemBlock
                  ? 0.0
                  : d3;
               double d6 = d2;
               double d7 = d5;
               List list = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box.addCoord(d, d5, d2));
               AxisAlignedBB box2 = box;

               for (AxisAlignedBB box3 : (Iterable<AxisAlignedBB>)(list)) {
                  d5 = box3.calculateYOffset(box, d5);
               }

               box = box.offset(0.0, d5, 0.0);
               boolean flag = onGround || d7 != d5 && d7 < 0.0;

               for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list)) {
                  d4 = box4.calculateXOffset(box, d4);
               }

               box = box.offset(d4, 0.0, 0.0);

               for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list)) {
                  d6 = box5.calculateZOffset(box, d6);
               }

               box = box.offset(0.0, 0.0, d6);
               if (entity.stepHeight > 0.0F && flag && (d != d4 || d2 != d6)) {
                  double d8 = d4;
                  double d9 = d5;
                  double d10 = d6;
                  AxisAlignedBB box6 = box;
                  d5 = (double)entity.stepHeight;
                  List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.addCoord(d, d5, d2));
                  AxisAlignedBB box7 = box2.addCoord(d, 0.0, d2);
                  double d11 = d5;

                  for (AxisAlignedBB box8 : (Iterable<AxisAlignedBB>)(list2)) {
                     d11 = box8.calculateYOffset(box7, d11);
                  }

                  AxisAlignedBB box9 = box2.offset(0.0, d11, 0.0);
                  double d12 = d;

                  for (AxisAlignedBB box10 : (Iterable<AxisAlignedBB>)(list2)) {
                     d12 = box10.calculateXOffset(box9, d12);
                  }

                  box9 = box9.offset(d12, 0.0, 0.0);
                  double d13 = d2;

                  for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list2)) {
                     d13 = box11.calculateZOffset(box9, d13);
                  }

                  box9 = box9.offset(0.0, 0.0, d13);
                  AxisAlignedBB box12 = box2;
                  double d14 = d5;

                  for (AxisAlignedBB box13 : (Iterable<AxisAlignedBB>)(list2)) {
                     d14 = box13.calculateYOffset(box12, d14);
                  }

                  box12 = box12.offset(0.0, d14, 0.0);
                  double d15 = d;

                  for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list2)) {
                     d15 = box14.calculateXOffset(box12, d15);
                  }

                  box12 = box12.offset(d15, 0.0, 0.0);
                  double d16 = d2;

                  for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list2)) {
                     d16 = box15.calculateZOffset(box12, d16);
                  }

                  box12 = box12.offset(0.0, 0.0, d16);
                  if ((d12 * d12 + d13 * d13) > (d15 * d15 + d16 * d16)) {
                     d4 = d12;
                     d6 = d13;
                     d5 = -d11;
                     box = box9;
                  } else {
                     d4 = d15;
                     d6 = d16;
                     d5 = -d14;
                     box = box12;
                  }

                  for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list2)) {
                     d5 = box16.calculateYOffset(box, d5);
                  }

                  box = box.offset(0.0, d5, 0.0);
                  if (d8 * d8 + d10 * d10 >= d4 * d4 + d6 * d6) {
                     d4 = d8;
                     d5 = d9;
                     d6 = d10;
                     box = box6;
                  }
               }

               realPosX = (box.minX + box.maxX) / 2.0;
               realPosY = box.minY;
               realPosZ = (box.minZ + box.maxZ) / 2.0;
               isCollidedHorizontally = d != d4 || d2 != d6;
               isCollidedVertically = d7 != d5;
               onGround = isCollidedVertically && d7 < 0.0;
               if (!isCollidedHorizontally && !isCollidedVertically) {
                  boolean flag2 = false;
               } else {
                  boolean flag3 = true;
               }

               int n2 = MathHelper.floor_double(realPosX);
               int n3 = MathHelper.floor_double(realPosY - 0.20000000298023224);
               int n4 = MathHelper.floor_double(realPosZ);
               BlockPos pos = new BlockPos(n2, n3, n4);
               Block block = mc.theWorld.getBlockState(pos).getBlock();
               if (block.getMaterial() == Material.air) {
                  Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
                  if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
                     block = block2;
                     pos = pos.down();
                  }
               }

               if (d != d4) {
                  d = 0.0;
               }

               if (d2 != d6) {
                  d2 = 0.0;
               }

               if (d7 != d5) {
                  if (block instanceof BlockSlime) {
                     if (entity.isSneaking()) {
                        d3 = 0.0;
                     } else if (d3 < 0.0) {
                        d3 = -d3;
                     }
                  } else {
                     d3 = 0.0;
                  }
               }

               if (mc.theWorld.isRemote
                  && (
                     !mc.theWorld.isBlockLoaded(new BlockPos((int)realPosX, 0, (int)realPosZ))
                        || !mc.theWorld.getChunkFromBlockCoords(new BlockPos((int)realPosX, 0, (int)realPosZ)).isLoaded()
                  )) {
                  if (realPosY > 0.0) {
                     d3 = -0.1;
                  } else {
                     d3 = 0.0;
                  }
               } else {
                  d3 -= 0.08;
               }

               d3 *= 0.9800000190734863;
            }

            return new double[]{realPosX, realPosY, realPosZ, predictedPosX, predictedPosY, predictedPosZ, d, d3, d2, onGround ? 1.0 : 0.0};
         }
      }
   }

   public static double[] predictPlayerWithInputArrays(int n, EntityPlayerSP player, float[] floats, float[] floats2) {
      double posZ = player.posZ;
      double posY = player.posY;
      return predictPlayerWithInputArrays(n, player, player.posX, posY, posZ, floats, floats2);
   }

   public static boolean willEntityLandOnGround(EntityLivingBase entity, int n) {
      double d = entity.posX - entity.lastTickPosX;
      double d2 = entity.posZ - entity.lastTickPosZ;
      double d3 = entity.posY - entity.lastTickPosY;
      AxisAlignedBB entityBoundingBox = entity.getEntityBoundingBox();
      AxisAlignedBB box = new AxisAlignedBB(entityBoundingBox.minX, entityBoundingBox.minY, entityBoundingBox.minZ, entityBoundingBox.maxX, entityBoundingBox.maxY, entityBoundingBox.maxZ);
      boolean onGround = entity.onGround;
      boolean isCollidedHorizontally = entity.isCollidedHorizontally;
      boolean isCollidedVertically = entity.isCollidedVertically;
      double newPosX = entity.newPosX;
      double newPosY = entity.newPosY;
      double newPosZ = entity.newPosZ;
      if (entity instanceof EntityOtherPlayerMP) {
         EntityOtherPlayerMP entityOtherPlayerMP = (EntityOtherPlayerMP)entity;
         newPosX = entityOtherPlayerMP.otherPlayerMPX;
         newPosY = entityOtherPlayerMP.otherPlayerMPY;
         newPosZ = entityOtherPlayerMP.otherPlayerMPZ;
      }

      for (int i = 0; i <= n; i++) {
         d = entity.posX - entity.lastTickPosX;
         d2 = entity.posZ - entity.lastTickPosZ;
         double d4 = d;
         double d5 = entity.rotationPitchHead < 75.0F
               && entity instanceof EntityPlayer
               && ((EntityPlayer)entity).getCurrentEquippedItem() != null
               && ((EntityPlayer)entity).getCurrentEquippedItem().getItem() instanceof ItemBlock
            ? 0.0
            : d3;
         double d6 = d2;
         double d7 = d5;
         if (Math.abs(d) > 10.0 || Math.abs(d5) > 10.0 || Math.abs(d2) > 10.0) {
            return true;
         }

         List list = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box.addCoord(d, d5, d2));
         AxisAlignedBB box2 = box;

         for (AxisAlignedBB box3 : (Iterable<AxisAlignedBB>)(list)) {
            d5 = box3.calculateYOffset(box, d5);
         }

         box = box.offset(0.0, d5, 0.0);
         boolean flag = onGround || d7 != d5 && d7 < 0.0;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list)) {
            d4 = box4.calculateXOffset(box, d4);
         }

         box = box.offset(d4, 0.0, 0.0);

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list)) {
            d6 = box5.calculateZOffset(box, d6);
         }

         box = box.offset(0.0, 0.0, d6);
         if (entity.stepHeight > 0.0F && flag && (d != d4 || d2 != d6)) {
            double d8 = d4;
            double d9 = d5;
            double d10 = d6;
            AxisAlignedBB box6 = box;
            d5 = (double)entity.stepHeight;
            List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.addCoord(d, d5, d2));
            AxisAlignedBB box7 = box2.addCoord(d, 0.0, d2);
            double d11 = d5;

            for (AxisAlignedBB box8 : (Iterable<AxisAlignedBB>)(list2)) {
               d11 = box8.calculateYOffset(box7, d11);
            }

            AxisAlignedBB box9 = box2.offset(0.0, d11, 0.0);
            double d12 = d;

            for (AxisAlignedBB box10 : (Iterable<AxisAlignedBB>)(list2)) {
               d12 = box10.calculateXOffset(box9, d12);
            }

            box9 = box9.offset(d12, 0.0, 0.0);
            double d13 = d2;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list2)) {
               d13 = box11.calculateZOffset(box9, d13);
            }

            box9 = box9.offset(0.0, 0.0, d13);
            AxisAlignedBB box12 = box2;
            double d14 = d5;

            for (AxisAlignedBB box13 : (Iterable<AxisAlignedBB>)(list2)) {
               d14 = box13.calculateYOffset(box12, d14);
            }

            box12 = box12.offset(0.0, d14, 0.0);
            double d15 = d;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list2)) {
               d15 = box14.calculateXOffset(box12, d15);
            }

            box12 = box12.offset(d15, 0.0, 0.0);
            double d16 = d2;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list2)) {
               d16 = box15.calculateZOffset(box12, d16);
            }

            box12 = box12.offset(0.0, 0.0, d16);
            if ((d12 * d12 + d13 * d13) > (d15 * d15 + d16 * d16)) {
               d4 = d12;
               d6 = d13;
               d5 = -d11;
               box = box9;
            } else {
               d4 = d15;
               d6 = d16;
               d5 = -d14;
               box = box12;
            }

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list2)) {
               d5 = box16.calculateYOffset(box, d5);
            }

            box = box.offset(0.0, d5, 0.0);
            if (d8 * d8 + d10 * d10 >= d4 * d4 + d6 * d6) {
               d4 = d8;
               d5 = d9;
               d6 = d10;
               box = box6;
            }
         }

         newPosX = (box.minX + box.maxX) / 2.0;
         newPosY = box.minY;
         newPosZ = (box.minZ + box.maxZ) / 2.0;
         isCollidedHorizontally = d != d4 || d2 != d6;
         isCollidedVertically = d7 != d5;
         onGround = isCollidedVertically && d7 < 0.0;
         if (onGround) {
            return true;
         }

         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag2 = false;
         } else {
         }

         int n2 = MathHelper.floor_double(newPosX);
         int n3 = MathHelper.floor_double(newPosY - 0.20000000298023224);
         int n4 = MathHelper.floor_double(newPosZ);
         BlockPos pos = new BlockPos(n2, n3, n4);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (d != d4) {
            d = 0.0;
         }

         if (d2 != d6) {
            d2 = 0.0;
         }

         if (d7 != d5) {
            if (block instanceof BlockSlime) {
               if (entity.isSneaking()) {
                  d3 = 0.0;
               } else if (d3 < 0.0) {
                  d3 = -d3;
               }
            } else {
               d3 = 0.0;
            }
         }

         if (mc.theWorld.isRemote
            && (
               !mc.theWorld.isBlockLoaded(new BlockPos((int)newPosX, 0, (int)newPosZ))
                  || !mc.theWorld.getChunkFromBlockCoords(new BlockPos((int)newPosX, 0, (int)newPosZ)).isLoaded()
            )) {
            if (newPosY > 0.0) {
               d3 = -0.1;
            } else {
               d3 = 0.0;
            }
         } else {
            d3 -= 0.08;
         }

         d3 *= 0.9800000190734863;
      }

      return false;
   }

   public static double[] extrapolateEntityXZ(EntityLivingBase entity, int n) {
      double d = entity.prevPosX - entity.posX;
      double d2 = entity.prevPosZ - entity.posZ;
      double posX = entity.posX;
      double posZ = entity.posZ;

      for (int i = 0; i <= n; i++) {
         posX -= d * (double)i;
         posZ -= d2 * (double)i;
      }

      return new double[]{posX, posZ};
   }

   public static boolean canPlaceBlockAt(ItemStack stack, BlockPos pos, EnumFacing facing, Vec3 vec) {
      if (vec != null && pos != null && facing != null) {
         float f = (float)(vec.xCoord - (double)pos.getX());
         float f2 = (float)(vec.yCoord - (double)pos.getY());
         float f3 = (float)(vec.zCoord - (double)pos.getZ());
         boolean flag = false;
         if (mc.theWorld.getWorldBorder().contains(pos) && (pos.getY() != 255 || facing != EnumFacing.UP) && (pos.getY() != 0 || facing != EnumFacing.DOWN)) {
            if (mc.playerController.getCurrentGameType() != WorldSettings.GameType.ADVENTURE) {
               IBlockState blockState = mc.theWorld.getBlockState(pos);
               if ((!mc.thePlayer.isSneaking() || mc.thePlayer.getHeldItem() == null)
                  && blockState.getBlock().canBlockBeActivated(mc.theWorld, pos, blockState, mc.thePlayer, facing, f, f2, f3)) {
                  flag = true;
               }

               if (!flag && stack != null && stack.getItem() instanceof ItemBlock) {
                  if (!((ItemBlock)stack.getItem()).canPlaceBlockOnSide(mc.theWorld, pos, facing, mc.thePlayer, stack)) {
                     return false;
                  }
               }
            }

            return stack != null && !flag && mc.thePlayer.canPlayerEdit(pos, facing, stack);
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static double[] predictPlayerPosition(int n, EntityPlayerSP player, float f, boolean flag) {
      double posX = player.posX;
      double posY = player.posY;
      double posZ = player.posZ;
      EventMoveInput event = new EventMoveInput(
         mc.thePlayer.movementInput.moveStrafe,
         mc.thePlayer.movementInput.moveForward,
         mc.thePlayer.movementInput.jump,
         mc.thePlayer.movementInput.sneak,
         MoveInputStage.PRE
      );
      return predictPlayerPosition(n, player, posX, posY, posZ, f, event, flag);
   }

   public static Vec3 predictNextTickWithAttack(boolean flag, Entity entity, float f) {
      return predictNextTickWithAttack(flag, entity, -1337.0, f);
   }

   public static double[] predictPlayerPosition(int n, EntityPlayerSP player, double d, double d2, double d3, float f, EventMoveInput event, boolean flag) {
      double motionX = player.motionX;
      double motionY = player.motionY;
      double motionZ = player.motionZ;
      boolean isJump = event.isJump();
      double prevPosX = player.prevPosX;
      double prevPosY = player.prevPosY;
      double prevPosZ = player.prevPosZ;
      int jumpTicks = player.getJumpTicks();
      boolean onGround = player.onGround;
      float moveStrafe = event.getMoveStrafe();
      float moveForward = event.getMoveForward();
      AxisAlignedBB entityBoundingBox = player.getEntityBoundingBox();
      AxisAlignedBB box = new AxisAlignedBB(entityBoundingBox.minX, entityBoundingBox.minY, entityBoundingBox.minZ, entityBoundingBox.maxX, entityBoundingBox.maxY, entityBoundingBox.maxZ);
      boolean isCollidedHorizontally = player.isCollidedHorizontally;
      boolean isCollidedVertically = player.isCollidedVertically;
      boolean isSprinting = player.isSprinting();
      float fallDistance = player.fallDistance;

      for (int i = 0; i <= n; i++) {
         prevPosX = d;
         prevPosY = d2;
         prevPosZ = d3;
         moveStrafe = event.getMoveStrafe();
         moveForward = event.getMoveForward();
         EventSlowDown event2 = SlowDownListener.onSlowDown(0.2F, 0.2F);
         if (player.isUsingItem() && !player.isRiding()) {
            moveStrafe *= event2.getStrafeMultiplier();
            moveForward *= event2.getForwardMultiplier();
         }

         if (jumpTicks > 0) {
            jumpTicks--;
         }

         double motionZeroThreshold = ViaAugustus.getMotionZeroThreshold();
         if (Math.abs(motionX) < motionZeroThreshold) {
            motionX = 0.0;
         }

         if (Math.abs(motionY) < motionZeroThreshold) {
            motionY = 0.0;
         }

         if (Math.abs(motionZ) < motionZeroThreshold) {
            motionZ = 0.0;
         }

         if (isJump) {
            if (onGround && jumpTicks == 0) {
               motionY = (double)player.getJumpUpwardsMotion();
               if (player.isPotionActive(Potion.jump)) {
                  motionY += (double)((float)(player.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
               }

               if (isSprinting) {
                  float f2 = f * 0.017453292F;
                  motionX -= (double)(MathHelper.sin(f2) * 0.2F);
                  motionZ += (double)(MathHelper.cos(f2) * 0.2F);
               }

               jumpTicks = 10;
            }
         } else {
            jumpTicks = 0;
         }

         moveStrafe *= 0.98F;
         moveForward *= 0.98F;
         float f3 = 0.91F;
         if (onGround) {
            f3 = player.entityWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(d), MathHelper.floor_double(d2) - 1, MathHelper.floor_double(d3)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         float f4 = 0.16277136F / (f3 * f3 * f3);
         float f5;
         if (onGround) {
            f5 = player.getAIMoveSpeed() * f4;
         } else {
            f5 = player.jumpMovementFactor;
         }

         EventMove event3 = MoveListener.onMove(f, moveForward, moveStrafe, f5, MoveEventType.MOVE_FLYING);
         float f6 = moveStrafe * moveStrafe + moveForward * moveForward;
         if (f6 >= 1.0E-4F) {
            f6 = MathHelper.sqrt_float(f6);
            if (f6 < 1.0F) {
               f6 = 1.0F;
            }

            f6 = f5 / f6;
            moveStrafe *= f6;
            moveForward *= f6;
            float f7 = MathHelper.sin(event3.getYaw() * 3.1415927F / 180.0F);
            float f8 = MathHelper.cos(event3.getYaw() * 3.1415927F / 180.0F);
            motionX += (double)(moveStrafe * f8 - moveForward * f7);
            motionZ += (double)(moveForward * f8 + moveStrafe * f7);
         }

         f3 = 0.91F;
         if (onGround) {
            f3 = mc.theWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(d), MathHelper.floor_double(d2) - 1, MathHelper.floor_double(d3)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         double d4 = motionX;
         double d5 = motionY;
         double d6 = motionZ;
         double d7 = motionX;
         double d8 = motionZ;
         boolean flag2 = player.isSneaking() && !flag;
         if (onGround && flag2) {
            double d9;
            for (d9 = 0.05; d4 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(player, box.offset(d4, -1.0, 0.0)).isEmpty(); d7 = d4) {
               if (d4 < d9 && d4 >= -d9) {
                  d4 = 0.0;
               } else if (d4 > 0.0) {
                  d4 -= d9;
               } else {
                  d4 += d9;
               }
            }

            for (; d6 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(player, box.offset(0.0, -1.0, d6)).isEmpty(); d8 = d6) {
               if (d6 < d9 && d6 >= -d9) {
                  d6 = 0.0;
               } else if (d6 > 0.0) {
                  d6 -= d9;
               } else {
                  d6 += d9;
               }
            }

            for (; d4 != 0.0 && d6 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(player, box.offset(d4, -1.0, d6)).isEmpty(); d8 = d6) {
               if (d4 < d9 && d4 >= -d9) {
                  d4 = 0.0;
               } else if (d4 > 0.0) {
                  d4 -= d9;
               } else {
                  d4 += d9;
               }

               d7 = d4;
               if (d6 < d9 && d6 >= -d9) {
                  d6 = 0.0;
               } else if (d6 > 0.0) {
                  d6 -= d9;
               } else {
                  d6 += d9;
               }
            }
         }

         List list = mc.theWorld.getCollidingBoundingBoxesCapped(player, box.addCoord(d4, motionY, d6));
         AxisAlignedBB box2 = box;

         for (AxisAlignedBB box3 : (Iterable<AxisAlignedBB>)(list)) {
            d5 = box3.calculateYOffset(box, d5);
         }

         box = box.offset(0.0, d5, 0.0);
         boolean flag4 = onGround || motionY != d5 && motionY < 0.0;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list)) {
            d4 = box4.calculateXOffset(box, d4);
         }

         box = box.offset(d4, 0.0, 0.0);

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list)) {
            d6 = box5.calculateZOffset(box, d6);
         }

         box = box.offset(0.0, 0.0, d6);
         EventStep event4 = StepListener.onStep(player.stepHeight, StateTypes.PRE);
         if (event4.getStepHeight() > 0.0F && flag4 && (d7 != d4 || d8 != d6)) {
            double d10 = d4;
            double d11 = d5;
            double d12 = d6;
            AxisAlignedBB box6 = box;
            d5 = (double)event4.getStepHeight();
            List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(player, box2.addCoord(d7, d5, d8));
            AxisAlignedBB box7 = box2.addCoord(d7, 0.0, d8);
            double d13 = d5;

            for (AxisAlignedBB box8 : (Iterable<AxisAlignedBB>)(list2)) {
               d13 = box8.calculateYOffset(box7, d13);
            }

            AxisAlignedBB box9 = box2.offset(0.0, d13, 0.0);
            double d14 = d7;

            for (AxisAlignedBB box10 : (Iterable<AxisAlignedBB>)(list2)) {
               d14 = box10.calculateXOffset(box9, d14);
            }

            box9 = box9.offset(d14, 0.0, 0.0);
            double d15 = d8;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list2)) {
               d15 = box11.calculateZOffset(box9, d15);
            }

            box9 = box9.offset(0.0, 0.0, d15);
            AxisAlignedBB box12 = box2;
            double d16 = d5;

            for (AxisAlignedBB box13 : (Iterable<AxisAlignedBB>)(list2)) {
               d16 = box13.calculateYOffset(box12, d16);
            }

            box12 = box12.offset(0.0, d16, 0.0);
            double d17 = d7;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list2)) {
               d17 = box14.calculateXOffset(box12, d17);
            }

            box12 = box12.offset(d17, 0.0, 0.0);
            double d18 = d8;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list2)) {
               d18 = box15.calculateZOffset(box12, d18);
            }

            box12 = box12.offset(0.0, 0.0, d18);
            if ((d14 * d14 + d15 * d15) > (d17 * d17 + d18 * d18)) {
               d4 = d14;
               d6 = d15;
               d5 = -d13;
               box = box9;
            } else {
               d4 = d17;
               d6 = d18;
               d5 = -d16;
               box = box12;
            }

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list2)) {
               d5 = box16.calculateYOffset(box, d5);
            }

            box = box.offset(0.0, d5, 0.0);
            if (d10 * d10 + d12 * d12 >= d4 * d4 + d6 * d6) {
               d4 = d10;
               d5 = d11;
               d6 = d12;
               box = box6;
            }
         }

         d = (box.minX + box.maxX) / 2.0;
         d2 = box.minY;
         d3 = (box.minZ + box.maxZ) / 2.0;
         isCollidedHorizontally = d7 != d4 || d8 != d6;
         isCollidedVertically = motionY != d5;
         onGround = isCollidedVertically && motionY < 0.0;
         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag5 = false;
         } else {
         }

         int n2 = MathHelper.floor_double(d);
         int n3 = MathHelper.floor_double(d2 - 0.20000000298023224);
         int n4 = MathHelper.floor_double(d3);
         BlockPos pos = new BlockPos(n2, n3, n4);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (onGround) {
            if (fallDistance > 0.0F) {
               fallDistance = 0.0F;
            }
         } else if (d5 < 0.0) {
            fallDistance = (float)((double)fallDistance - d5);
         }

         if (d7 != d4) {
            motionX = 0.0;
         }

         if (d8 != d6) {
            motionZ = 0.0;
         }

         if (motionY != d5) {
            if (block instanceof BlockSlime) {
               if (player.isSneaking()) {
                  motionY = 0.0;
               } else if (motionY < 0.0) {
                  motionY = -motionY;
               }
            } else {
               motionY = 0.0;
            }
         }

         if (player.entityWorld.isRemote
            && (
               !player.entityWorld.isBlockLoaded(new BlockPos((int)d, 0, (int)d3))
                  || !player.entityWorld.getChunkFromBlockCoords(new BlockPos((int)d, 0, (int)d3)).isLoaded()
            )) {
            if (d2 > 0.0) {
               motionY = -0.1;
            } else {
               motionY = 0.0;
            }
         } else {
            motionY -= 0.08;
         }

         motionY *= 0.9800000190734863;
         motionX *= (double)f3;
         motionZ *= (double)f3;
      }

      return new double[]{d, d2, d3, prevPosX, prevPosY, prevPosZ, motionX, motionY, motionZ, onGround ? 1.0 : 0.0, isCollidedHorizontally ? 1.0 : 0.0, (double)fallDistance};
   }

   public static double[] predictPlayerPosition(int n, EntityPlayerSP player) {
      return predictPlayerPosition(n, player, player.rotationYaw, false);
   }

   public static void triggerJumpStat() {
      mc.thePlayer.triggerAchievement(StatList.jumpStat);
      mc.thePlayer.isAirBorne = true;
   }

   public static double[] predictPlayerWithInputArrays(int n, EntityPlayerSP player, double d, double d2, double d3, float[] floats, float[] floats2) {
      double motionX = player.motionX;
      double motionY = player.motionY;
      double motionZ = player.motionZ;
      boolean jumping = player.jumping;
      double prevPosX = player.prevPosX;
      double prevPosY = player.prevPosY;
      double prevPosZ = player.prevPosZ;
      int jumpTicks = player.getJumpTicks();
      boolean onGround = player.onGround;
      float moveStrafe = player.movementInput.moveStrafe;
      float moveForward = player.movementInput.moveForward;
      AxisAlignedBB entityBoundingBox = player.getEntityBoundingBox();
      AxisAlignedBB box = new AxisAlignedBB(entityBoundingBox.minX, entityBoundingBox.minY, entityBoundingBox.minZ, entityBoundingBox.maxX, entityBoundingBox.maxY, entityBoundingBox.maxZ);
      boolean isCollidedHorizontally = player.isCollidedHorizontally;
      boolean isCollidedVertically = player.isCollidedVertically;
      boolean isSprinting = player.isSprinting();

      for (int i = 0; i <= n; i++) {
         prevPosX = d;
         prevPosY = d2;
         prevPosZ = d3;
         moveStrafe = floats2[i];
         moveForward = floats[i];
         EventSlowDown event = SlowDownListener.onSlowDown(0.2F, 0.2F);
         if (player.isUsingItem() && !player.isRiding()) {
            moveStrafe *= event.getStrafeMultiplier();
            moveForward *= event.getForwardMultiplier();
         }

         if (jumpTicks > 0) {
            jumpTicks--;
         }

         double motionZeroThreshold = ViaAugustus.getMotionZeroThreshold();
         if (Math.abs(motionX) < motionZeroThreshold) {
            motionX = 0.0;
         }

         if (Math.abs(motionY) < motionZeroThreshold) {
            motionY = 0.0;
         }

         if (Math.abs(motionZ) < motionZeroThreshold) {
            motionZ = 0.0;
         }

         if (jumping) {
            if (onGround && jumpTicks == 0) {
               motionY = (double)player.getJumpUpwardsMotion();
               if (player.isPotionActive(Potion.jump)) {
                  motionY += (double)((float)(player.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
               }

               if (isSprinting) {
                  float f = player.rotationYaw * 0.017453292F;
                  motionX -= (double)(MathHelper.sin(f) * 0.2F);
                  motionZ += (double)(MathHelper.cos(f) * 0.2F);
               }

               jumpTicks = 10;
            }
         } else {
            jumpTicks = 0;
         }

         moveStrafe *= 0.98F;
         moveForward *= 0.98F;
         float f2 = 0.91F;
         if (onGround) {
            f2 = player.entityWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(d), MathHelper.floor_double(d2) - 1, MathHelper.floor_double(d3)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         float f3 = 0.16277136F / (f2 * f2 * f2);
         float f4;
         if (onGround) {
            f4 = player.getAIMoveSpeed() * f3;
         } else {
            f4 = player.jumpMovementFactor;
         }

         EventMove event2 = MoveListener.onMove(player.rotationYaw, moveForward, moveStrafe, f4, MoveEventType.MOVE_FLYING);
         float f5 = moveStrafe * moveStrafe + moveForward * moveForward;
         if (f5 >= 1.0E-4F) {
            f5 = MathHelper.sqrt_float(f5);
            if (f5 < 1.0F) {
               f5 = 1.0F;
            }

            f5 = f4 / f5;
            moveStrafe *= f5;
            moveForward *= f5;
            float f6 = MathHelper.sin(event2.getYaw() * 3.1415927F / 180.0F);
            float f7 = MathHelper.cos(event2.getYaw() * 3.1415927F / 180.0F);
            motionX += (double)(moveStrafe * f7 - moveForward * f6);
            motionZ += (double)(moveForward * f7 + moveStrafe * f6);
         }

         f2 = 0.91F;
         if (onGround) {
            f2 = mc.theWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(d), MathHelper.floor_double(d2) - 1, MathHelper.floor_double(d3)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         double d4 = motionX;
         double d5 = motionY;
         double d6 = motionZ;
         double d7 = motionX;
         double d8 = motionZ;
         boolean isSneaking = player.isSneaking();
         if (player.onGround && isSneaking) {
            double d9;
            for (d9 = 0.05; d4 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(player, box.offset(d4, -1.0, 0.0)).isEmpty(); d7 = d4) {
               if (d4 < d9 && d4 >= -d9) {
                  d4 = 0.0;
               } else if (d4 > 0.0) {
                  d4 -= d9;
               } else {
                  d4 += d9;
               }
            }

            for (; d6 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(player, box.offset(0.0, -1.0, d6)).isEmpty(); d8 = d6) {
               if (d6 < d9 && d6 >= -d9) {
                  d6 = 0.0;
               } else if (d6 > 0.0) {
                  d6 -= d9;
               } else {
                  d6 += d9;
               }
            }

            for (; d4 != 0.0 && d6 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(player, box.offset(d4, -1.0, d6)).isEmpty(); d8 = d6) {
               if (d4 < d9 && d4 >= -d9) {
                  d4 = 0.0;
               } else if (d4 > 0.0) {
                  d4 -= d9;
               } else {
                  d4 += d9;
               }

               d7 = d4;
               if (d6 < d9 && d6 >= -d9) {
                  d6 = 0.0;
               } else if (d6 > 0.0) {
                  d6 -= d9;
               } else {
                  d6 += d9;
               }
            }
         }

         List list = mc.theWorld.getCollidingBoundingBoxesCapped(player, box.addCoord(d4, motionY, d6));
         AxisAlignedBB box2 = box;

         for (AxisAlignedBB box3 : (Iterable<AxisAlignedBB>)(list)) {
            d5 = box3.calculateYOffset(box, d5);
         }

         box = box.offset(0.0, d5, 0.0);
         boolean flag2 = onGround || motionY != d5 && motionY < 0.0;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list)) {
            d4 = box4.calculateXOffset(box, d4);
         }

         box = box.offset(d4, 0.0, 0.0);

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list)) {
            d6 = box5.calculateZOffset(box, d6);
         }

         box = box.offset(0.0, 0.0, d6);
         EventStep event3 = StepListener.onStep(player.stepHeight, StateTypes.PRE);
         if (event3.getStepHeight() > 0.0F && flag2 && (d7 != d4 || d8 != d6)) {
            double d10 = d4;
            double d11 = d5;
            double d12 = d6;
            AxisAlignedBB box6 = box;
            d5 = (double)event3.getStepHeight();
            List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(player, box2.addCoord(d7, d5, d8));
            AxisAlignedBB box7 = box2.addCoord(d7, 0.0, d8);
            double d13 = d5;

            for (AxisAlignedBB box8 : (Iterable<AxisAlignedBB>)(list2)) {
               d13 = box8.calculateYOffset(box7, d13);
            }

            AxisAlignedBB box9 = box2.offset(0.0, d13, 0.0);
            double d14 = d7;

            for (AxisAlignedBB box10 : (Iterable<AxisAlignedBB>)(list2)) {
               d14 = box10.calculateXOffset(box9, d14);
            }

            box9 = box9.offset(d14, 0.0, 0.0);
            double d15 = d8;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list2)) {
               d15 = box11.calculateZOffset(box9, d15);
            }

            box9 = box9.offset(0.0, 0.0, d15);
            AxisAlignedBB box12 = box2;
            double d16 = d5;

            for (AxisAlignedBB box13 : (Iterable<AxisAlignedBB>)(list2)) {
               d16 = box13.calculateYOffset(box12, d16);
            }

            box12 = box12.offset(0.0, d16, 0.0);
            double d17 = d7;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list2)) {
               d17 = box14.calculateXOffset(box12, d17);
            }

            box12 = box12.offset(d17, 0.0, 0.0);
            double d18 = d8;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list2)) {
               d18 = box15.calculateZOffset(box12, d18);
            }

            box12 = box12.offset(0.0, 0.0, d18);
            if ((d14 * d14 + d15 * d15) > (d17 * d17 + d18 * d18)) {
               d4 = d14;
               d6 = d15;
               d5 = -d13;
               box = box9;
            } else {
               d4 = d17;
               d6 = d18;
               d5 = -d16;
               box = box12;
            }

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list2)) {
               d5 = box16.calculateYOffset(box, d5);
            }

            box = box.offset(0.0, d5, 0.0);
            if (d10 * d10 + d12 * d12 >= d4 * d4 + d6 * d6) {
               d4 = d10;
               d5 = d11;
               d6 = d12;
               box = box6;
            }
         }

         d = (box.minX + box.maxX) / 2.0;
         d2 = box.minY;
         d3 = (box.minZ + box.maxZ) / 2.0;
         isCollidedHorizontally = d7 != d4 || d8 != d6;
         isCollidedVertically = motionY != d5;
         onGround = isCollidedVertically && motionY < 0.0;
         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag3 = false;
         } else {
         }

         int n2 = MathHelper.floor_double(d);
         int n3 = MathHelper.floor_double(d2 - 0.20000000298023224);
         int n4 = MathHelper.floor_double(d3);
         BlockPos pos = new BlockPos(n2, n3, n4);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (d7 != d4) {
            motionX = 0.0;
         }

         if (d8 != d6) {
            motionZ = 0.0;
         }

         if (motionY != d5) {
            if (block instanceof BlockSlime) {
               if (player.isSneaking()) {
                  motionY = 0.0;
               } else if (motionY < 0.0) {
                  motionY = -motionY;
               }
            } else {
               motionY = 0.0;
            }
         }

         if (player.entityWorld.isRemote
            && (
               !player.entityWorld.isBlockLoaded(new BlockPos((int)d, 0, (int)d3))
                  || !player.entityWorld.getChunkFromBlockCoords(new BlockPos((int)d, 0, (int)d3)).isLoaded()
            )) {
            if (d2 > 0.0) {
               motionY = -0.1;
            } else {
               motionY = 0.0;
            }
         } else {
            motionY -= 0.08;
         }

         motionY *= 0.9800000190734863;
         motionX *= (double)f2;
         motionZ *= (double)f2;
      }

      return new double[]{d, d2, d3, prevPosX, prevPosY, prevPosZ, motionX, motionY, motionZ, onGround ? 1.0 : 0.0, isCollidedHorizontally ? 1.0 : 0.0};
   }

   public static double[] predictEntitySimplePosition(EntityLivingBase entity, int n) {
      double d = entity.posX - entity.lastTickPosX;
      double d2 = entity.posZ - entity.lastTickPosZ;
      double d3 = entity.posY - entity.lastTickPosY;
      AxisAlignedBB entityBoundingBox = entity.getEntityBoundingBox();
      AxisAlignedBB box = new AxisAlignedBB(entityBoundingBox.minX, entityBoundingBox.minY, entityBoundingBox.minZ, entityBoundingBox.maxX, entityBoundingBox.maxY, entityBoundingBox.maxZ);
      boolean onGround = entity.onGround;
      boolean isCollidedHorizontally = entity.isCollidedHorizontally;
      boolean isCollidedVertically = entity.isCollidedVertically;
      double newPosX = entity.newPosX;
      double newPosY = entity.newPosY;
      double newPosZ = entity.newPosZ;
      if (entity instanceof EntityOtherPlayerMP) {
         EntityOtherPlayerMP entityOtherPlayerMP = (EntityOtherPlayerMP)entity;
         newPosX = entityOtherPlayerMP.otherPlayerMPX;
         newPosY = entityOtherPlayerMP.otherPlayerMPY;
         newPosZ = entityOtherPlayerMP.otherPlayerMPZ;
      }

      for (int i = 0; i <= n; i++) {
         d = entity.posX - entity.lastTickPosX;
         d2 = entity.posZ - entity.lastTickPosZ;
         double d4 = d;
         double d5 = entity.rotationPitchHead < 75.0F
               && entity instanceof EntityPlayer
               && ((EntityPlayer)entity).getCurrentEquippedItem() != null
               && ((EntityPlayer)entity).getCurrentEquippedItem().getItem() instanceof ItemBlock
            ? 0.0
            : d3;
         double d6 = d2;
         double d7 = d5;
         if (d > 10.0 || d5 > 10.0 || d2 > 10.0) {
            return new double[]{newPosX, newPosY, newPosZ, d, d3, d2, onGround ? 1.0 : 0.0};
         }

         List list = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box.addCoord(d, d5, d2));
         AxisAlignedBB box2 = box;

         for (AxisAlignedBB box3 : (Iterable<AxisAlignedBB>)(list)) {
            d5 = box3.calculateYOffset(box, d5);
         }

         box = box.offset(0.0, d5, 0.0);
         boolean flag = onGround || d7 != d5 && d7 < 0.0;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list)) {
            d4 = box4.calculateXOffset(box, d4);
         }

         box = box.offset(d4, 0.0, 0.0);

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list)) {
            d6 = box5.calculateZOffset(box, d6);
         }

         box = box.offset(0.0, 0.0, d6);
         if (entity.stepHeight > 0.0F && flag && (d != d4 || d2 != d6)) {
            double d8 = d4;
            double d9 = d5;
            double d10 = d6;
            AxisAlignedBB box6 = box;
            d5 = (double)entity.stepHeight;
            List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.addCoord(d, d5, d2));
            AxisAlignedBB box7 = box2.addCoord(d, 0.0, d2);
            double d11 = d5;

            for (AxisAlignedBB box8 : (Iterable<AxisAlignedBB>)(list2)) {
               d11 = box8.calculateYOffset(box7, d11);
            }

            AxisAlignedBB box9 = box2.offset(0.0, d11, 0.0);
            double d12 = d;

            for (AxisAlignedBB box10 : (Iterable<AxisAlignedBB>)(list2)) {
               d12 = box10.calculateXOffset(box9, d12);
            }

            box9 = box9.offset(d12, 0.0, 0.0);
            double d13 = d2;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list2)) {
               d13 = box11.calculateZOffset(box9, d13);
            }

            box9 = box9.offset(0.0, 0.0, d13);
            AxisAlignedBB box12 = box2;
            double d14 = d5;

            for (AxisAlignedBB box13 : (Iterable<AxisAlignedBB>)(list2)) {
               d14 = box13.calculateYOffset(box12, d14);
            }

            box12 = box12.offset(0.0, d14, 0.0);
            double d15 = d;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list2)) {
               d15 = box14.calculateXOffset(box12, d15);
            }

            box12 = box12.offset(d15, 0.0, 0.0);
            double d16 = d2;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list2)) {
               d16 = box15.calculateZOffset(box12, d16);
            }

            box12 = box12.offset(0.0, 0.0, d16);
            if ((d12 * d12 + d13 * d13) > (d15 * d15 + d16 * d16)) {
               d4 = d12;
               d6 = d13;
               d5 = -d11;
               box = box9;
            } else {
               d4 = d15;
               d6 = d16;
               d5 = -d14;
               box = box12;
            }

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list2)) {
               d5 = box16.calculateYOffset(box, d5);
            }

            box = box.offset(0.0, d5, 0.0);
            if (d8 * d8 + d10 * d10 >= d4 * d4 + d6 * d6) {
               d4 = d8;
               d5 = d9;
               d6 = d10;
               box = box6;
            }
         }

         newPosX = (box.minX + box.maxX) / 2.0;
         newPosY = box.minY;
         newPosZ = (box.minZ + box.maxZ) / 2.0;
         isCollidedHorizontally = d != d4 || d2 != d6;
         isCollidedVertically = d7 != d5;
         onGround = isCollidedVertically && d7 < 0.0;
         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag2 = false;
         } else {
         }

         int n2 = MathHelper.floor_double(newPosX);
         int n3 = MathHelper.floor_double(newPosY - 0.20000000298023224);
         int n4 = MathHelper.floor_double(newPosZ);
         BlockPos pos = new BlockPos(n2, n3, n4);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (d != d4) {
            d = 0.0;
         }

         if (d2 != d6) {
            d2 = 0.0;
         }

         if (d7 != d5) {
            if (block instanceof BlockSlime) {
               if (entity.isSneaking()) {
                  d3 = 0.0;
               } else if (d3 < 0.0) {
                  d3 = -d3;
               }
            } else {
               d3 = 0.0;
            }
         }

         if (mc.theWorld.isRemote
            && (
               !mc.theWorld.isBlockLoaded(new BlockPos((int)newPosX, 0, (int)newPosZ))
                  || !mc.theWorld.getChunkFromBlockCoords(new BlockPos((int)newPosX, 0, (int)newPosZ)).isLoaded()
            )) {
            if (newPosY > 0.0) {
               d3 = -0.1;
            } else {
               d3 = 0.0;
            }
         } else {
            d3 -= 0.08;
         }

         d3 *= 0.9800000190734863;
      }

      return new double[]{newPosX, newPosY, newPosZ, d, d3, d2, onGround ? 1.0 : 0.0};
   }

   public static Vec3 predictNextTickWithAttack(boolean flag, Entity entity, double d, float f, boolean flag2) {
      double motionX = mc.thePlayer.motionX;
      double motionY = mc.thePlayer.motionY;
      double motionZ = mc.thePlayer.motionZ;
      double posX = mc.thePlayer.posX;
      double posY = mc.thePlayer.posY;
      double posZ = mc.thePlayer.posZ;
      EntityPlayerSP thePlayer = mc.thePlayer;
      boolean flag3 = flag2;
      int jumpTicks = mc.thePlayer.getJumpTicks();
      boolean onGround = mc.thePlayer.onGround;
      boolean jump = mc.thePlayer.movementInput.jump;
      AxisAlignedBB box = new AxisAlignedBB(mc.thePlayer.getEntityBoundingBox());
      if (flag) {
         float attributeValue = (float)mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
         float f2 = 0.0F;
         if (entity instanceof EntityLivingBase) {
            f2 = EnchantmentHelper.getModifierForCreature(mc.thePlayer.getHeldItem(), ((EntityLivingBase)entity).getCreatureAttribute());
         } else {
            f2 = EnchantmentHelper.getModifierForCreature(mc.thePlayer.getHeldItem(), EnumCreatureAttribute.UNDEFINED);
         }

         if (attributeValue > 0.0F || f2 > 0.0F) {
            int knockbackModifier = EnchantmentHelper.getKnockbackModifier(mc.thePlayer);
            if (flag2) {
               knockbackModifier++;
            }

            if (entity.attackEntityFrom(DamageSource.causePlayerDamage(mc.thePlayer), attributeValue)) {
               if (knockbackModifier > 0) {
                  EventAttackSlowdown event = AttackSlowdownListener.onAttackSlowdown(AttackSlowdownStage.FIRST, true);
                  motionX *= event.getSlowDownFactor();
                  motionZ *= event.getSlowDownFactor();
                  flag3 = event.isSprint();
               } else if (d == -1337.0) {
                  EventAttackSlowdown event2 = AttackSlowdownListener.onAttackSlowdown(AttackSlowdownStage.SECOND, true);
                  if (event2.isApplySecondStage()) {
                     motionX *= event2.getSlowDownFactor();
                     motionZ *= event2.getSlowDownFactor();
                     flag3 = event2.isSprint();
                  }
               } else {
                  motionX *= d;
                  motionZ *= d;
                  flag3 = false;
               }
            }
         }
      }

      float moveStrafe = thePlayer.movementInput.moveStrafe;
      float moveForward = thePlayer.movementInput.moveForward;
      EventSlowDown event3 = SlowDownListener.onSlowDown(0.2F, 0.2F);
      if (thePlayer.isUsingItem() && !thePlayer.isRiding()) {
         moveStrafe *= event3.getStrafeMultiplier();
         moveForward *= event3.getForwardMultiplier();
      }

      if (jumpTicks > 0) {
         jumpTicks--;
      }

      double motionZeroThreshold = ViaAugustus.getMotionZeroThreshold();
      if (Math.abs(motionX) < motionZeroThreshold) {
         motionX = 0.0;
      }

      if (Math.abs(motionY) < motionZeroThreshold) {
         motionY = 0.0;
      }

      if (Math.abs(motionZ) < motionZeroThreshold) {
         motionZ = 0.0;
      }

      if (jump && onGround && jumpTicks == 0) {
         motionY = (double)thePlayer.getJumpUpwardsMotion();
         if (thePlayer.isPotionActive(Potion.jump)) {
            motionY += (double)((float)(thePlayer.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
         }

         if (flag3) {
            float f3 = f * 0.017453292F;
            motionX -= (double)(MathHelper.sin(f3) * 0.2F);
            motionZ += (double)(MathHelper.cos(f3) * 0.2F);
         }
      }

      moveStrafe *= 0.98F;
      moveForward *= 0.98F;
      float f4 = 0.91F;
      if (onGround) {
         f4 = thePlayer.entityWorld
               .getBlockState(new BlockPos(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ)))
               .getBlock()
               .slipperiness
            * 0.91F;
      }

      float f5 = 0.16277136F / (f4 * f4 * f4);
      float f6;
      if (onGround) {
         f6 = thePlayer.getAIMoveSpeed() * f5;
      } else {
         f6 = thePlayer.jumpMovementFactor;
      }

      EventMove event4 = MoveListener.onMove(f, moveForward, moveStrafe, f6, MoveEventType.MOVE_FLYING);
      float f7 = moveStrafe * moveStrafe + moveForward * moveForward;
      if (f7 >= 1.0E-4F) {
         f7 = MathHelper.sqrt_float(f7);
         if (f7 < 1.0F) {
            f7 = 1.0F;
         }

         f7 = f6 / f7;
         moveStrafe *= f7;
         moveForward *= f7;
         float f8 = MathHelper.sin(event4.getYaw() * 3.1415927F / 180.0F);
         float f9 = MathHelper.cos(event4.getYaw() * 3.1415927F / 180.0F);
         motionX += (double)(moveStrafe * f9 - moveForward * f8);
         motionZ += (double)(moveForward * f9 + moveStrafe * f8);
      }

      f4 = 0.91F;
      if (onGround) {
         f4 = mc.theWorld
               .getBlockState(new BlockPos(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ)))
               .getBlock()
               .slipperiness
            * 0.91F;
      }

      double d2 = motionX;
      double d3 = motionY;
      double d4 = motionZ;
      double d5 = motionX;
      double d6 = motionZ;
      boolean isSneaking = thePlayer.isSneaking();
      if (onGround && isSneaking) {
         double d7;
         for (d7 = 0.05; d2 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(thePlayer, box.offset(d2, -1.0, 0.0)).isEmpty(); d5 = d2) {
            if (d2 < d7 && d2 >= -d7) {
               d2 = 0.0;
            } else if (d2 > 0.0) {
               d2 -= d7;
            } else {
               d2 += d7;
            }
         }

         for (; d4 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(thePlayer, box.offset(0.0, -1.0, d4)).isEmpty(); d6 = d4) {
            if (d4 < d7 && d4 >= -d7) {
               d4 = 0.0;
            } else if (d4 > 0.0) {
               d4 -= d7;
            } else {
               d4 += d7;
            }
         }

         for (; d2 != 0.0 && d4 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(thePlayer, box.offset(d2, -1.0, d4)).isEmpty(); d6 = d4) {
            if (d2 < d7 && d2 >= -d7) {
               d2 = 0.0;
            } else if (d2 > 0.0) {
               d2 -= d7;
            } else {
               d2 += d7;
            }

            d5 = d2;
            if (d4 < d7 && d4 >= -d7) {
               d4 = 0.0;
            } else if (d4 > 0.0) {
               d4 -= d7;
            } else {
               d4 += d7;
            }
         }
      }

      List list = mc.theWorld.getCollidingBoundingBoxesCapped(thePlayer, box.addCoord(d2, motionY, d4));

      for (AxisAlignedBB box2 : (Iterable<AxisAlignedBB>)(list)) {
         d3 = box2.calculateYOffset(box, d3);
      }

      box = box.offset(0.0, d3, 0.0);
      boolean flag8 = onGround || motionY != d3 && motionY < 0.0;

      for (AxisAlignedBB box3 : (Iterable<AxisAlignedBB>)(list)) {
         d2 = box3.calculateXOffset(box, d2);
      }

      box = box.offset(d2, 0.0, 0.0);

      for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list)) {
         d4 = box4.calculateZOffset(box, d4);
      }

      box = box.offset(0.0, 0.0, d4);
      EventStep event5 = StepListener.onStep(thePlayer.stepHeight, StateTypes.PRE);
      if (event5.getStepHeight() > 0.0F && flag8 && (d5 != d2 || d6 != d4)) {
         double d8 = d2;
         double d9 = d3;
         double d10 = d4;
         AxisAlignedBB box5 = box;
         d3 = (double)event5.getStepHeight();
         List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(thePlayer, box.addCoord(d5, d3, d6));
         AxisAlignedBB box6 = box.addCoord(d5, 0.0, d6);
         double d11 = d3;

         for (AxisAlignedBB box7 : (Iterable<AxisAlignedBB>)(list2)) {
            d11 = box7.calculateYOffset(box6, d11);
         }

         AxisAlignedBB box8 = box.offset(0.0, d11, 0.0);
         double d12 = d5;

         for (AxisAlignedBB box9 : (Iterable<AxisAlignedBB>)(list2)) {
            d12 = box9.calculateXOffset(box8, d12);
         }

         box8 = box8.offset(d12, 0.0, 0.0);
         double d13 = d6;

         for (AxisAlignedBB box10 : (Iterable<AxisAlignedBB>)(list2)) {
            d13 = box10.calculateZOffset(box8, d13);
         }

         box8 = box8.offset(0.0, 0.0, d13);
         AxisAlignedBB box11 = box;
         double d14 = d3;

         for (AxisAlignedBB box12 : (Iterable<AxisAlignedBB>)(list2)) {
            d14 = box12.calculateYOffset(box11, d14);
         }

         box11 = box11.offset(0.0, d14, 0.0);
         double d15 = d5;

         for (AxisAlignedBB box13 : (Iterable<AxisAlignedBB>)(list2)) {
            d15 = box13.calculateXOffset(box11, d15);
         }

         box11 = box11.offset(d15, 0.0, 0.0);
         double d16 = d6;

         for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list2)) {
            d16 = box14.calculateZOffset(box11, d16);
         }

         box11 = box11.offset(0.0, 0.0, d16);
         if ((d12 * d12 + d13 * d13) > (d15 * d15 + d16 * d16)) {
            d2 = d12;
            d4 = d13;
            d3 = -d11;
            box = box8;
         } else {
            d2 = d15;
            d4 = d16;
            d3 = -d14;
            box = box11;
         }

         for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list2)) {
            d3 = box15.calculateYOffset(box, d3);
         }

         box = box.offset(0.0, d3, 0.0);
         if (d8 * d8 + d10 * d10 >= d2 * d2 + d4 * d4) {
            d2 = d8;
            d3 = d9;
            d4 = d10;
            box = box5;
         }
      }

      posX = (box.minX + box.maxX) / 2.0;
      posY = box.minY;
      posZ = (box.minZ + box.maxZ) / 2.0;
      int n = MathHelper.floor_double(posX);
      int n2 = MathHelper.floor_double(posY - 0.20000000298023224);
      int n3 = MathHelper.floor_double(posZ);
      BlockPos pos = new BlockPos(n, n2, n3);
      Block block = mc.theWorld.getBlockState(pos).getBlock();
      if (block.getMaterial() == Material.air) {
         Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
         if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
            block = block2;
            pos = pos.down();
         }
      }

      if (d5 != d2) {
         motionX = 0.0;
      }

      if (d6 != d4) {
         motionZ = 0.0;
      }

      if (motionY != d3) {
         if (block instanceof BlockSlime) {
            if (thePlayer.isSneaking()) {
               motionY = 0.0;
            } else if (motionY < 0.0) {
               motionY = -motionY;
            }
         } else {
            motionY = 0.0;
         }
      }

      if (thePlayer.entityWorld.isRemote
         && (
            !thePlayer.entityWorld.isBlockLoaded(new BlockPos((int)posX, 0, (int)posZ))
               || !thePlayer.entityWorld.getChunkFromBlockCoords(new BlockPos((int)posX, 0, (int)posZ)).isLoaded()
         )) {
         if (posY > 0.0) {
            motionY = -0.1;
         } else {
            motionY = 0.0;
         }
      } else {
         motionY -= 0.08;
      }

      motionY *= 0.9800000190734863;
      motionX *= (double)f4;
      motionZ *= (double)f4;
      return new Vec3(posX, posY, posZ);
   }

   public static double[] simulateSingleTickMotion(
      float f, float f2, double d, double d2, double d3, double d4, double d5, double d6, boolean flag
   ) {
      f2 *= 0.98F;
      f *= 0.98F;
      float f3 = 0.91F;
      boolean isSprinting = mc.thePlayer.isSprinting();
      if (flag && mc.thePlayer.onGround && mc.thePlayer.getJumpTicks() == 0) {
         d2 = (double)mc.thePlayer.getJumpUpwardsMotion();
         if (mc.thePlayer.isPotionActive(Potion.jump)) {
            d2 += (double)((float)(mc.thePlayer.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
         }

         if (isSprinting) {
            float f4 = mc.thePlayer.rotationYaw * 0.017453292F;
            d -= (double)(MathHelper.sin(f4) * 0.2F);
            d3 += (double)(MathHelper.cos(f4) * 0.2F);
         }
      }

      if (mc.thePlayer.onGround) {
         f3 = mc.thePlayer
               .entityWorld
               .getBlockState(new BlockPos(MathHelper.floor_double(d4), MathHelper.floor_double(d5) - 1, MathHelper.floor_double(d6)))
               .getBlock()
               .slipperiness
            * 0.91F;
      }

      float f5 = 0.16277136F / (f3 * f3 * f3);
      float f6;
      if (mc.thePlayer.onGround) {
         f6 = mc.thePlayer.getAIMoveSpeed() * f5;
      } else {
         f6 = mc.thePlayer.jumpMovementFactor;
      }

      float f7 = f2 * f2 + f * f;
      if (f7 >= 1.0E-4F) {
         f7 = MathHelper.sqrt_float(f7);
         if (f7 < 1.0F) {
            f7 = 1.0F;
         }

         f7 = f6 / f7;
         f2 *= f7;
         f *= f7;
         float f8 = MathHelper.sin(mc.thePlayer.rotationYaw * 3.1415927F / 180.0F);
         float f9 = MathHelper.cos(mc.thePlayer.rotationYaw * 3.1415927F / 180.0F);
         d += (double)(f2 * f9 - f * f8);
         d3 += (double)(f * f9 + f2 * f8);
      }

      d4 += d;
      d5 += d2;
      d6 += d3;
      f3 = 0.91F;
      if (mc.thePlayer.onGround) {
         f3 = mc.thePlayer
               .entityWorld
               .getBlockState(
                  new BlockPos(
                     MathHelper.floor_double(d4), MathHelper.floor_double(mc.thePlayer.getEntityBoundingBox().minY) - 1, MathHelper.floor_double(d6)
                  )
               )
               .getBlock()
               .slipperiness
            * 0.91F;
      }

      if (mc.thePlayer.entityWorld.isRemote
         && (
            !mc.thePlayer.entityWorld.isBlockLoaded(new BlockPos((int)d4, 0, (int)d6))
               || !mc.thePlayer.entityWorld.getChunkFromBlockCoords(new BlockPos((int)d4, 0, (int)d6)).isLoaded()
         )) {
         if (d5 > 0.0) {
            d2 = -0.1;
         } else {
            d2 = 0.0;
         }
      } else {
         d2 -= 0.08;
      }

      d2 *= 0.9800000190734863;
      d *= (double)f3;
      d3 *= (double)f3;
      return new double[]{d4, d5, d6, d, d2, d3};
   }

   public static void damageSelfShort() {
      mc.getNetHandler().addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 3.001, mc.thePlayer.posZ, false));
      mc.getNetHandler().addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
      mc.getNetHandler().addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
   }

   public static double[] predictPlayerTowardsTarget(int n, EntityPlayerSP player, EntityLivingBase entity) {
      double motionX = player.motionX;
      double motionY = player.motionY;
      double motionZ = player.motionZ;
      boolean jumping = player.jumping;
      double prevPosX = player.prevPosX;
      double prevPosY = player.prevPosY;
      double prevPosZ = player.prevPosZ;
      int jumpTicks = player.getJumpTicks();
      boolean onGround = player.onGround;
      float moveStrafe = player.movementInput.moveStrafe;
      float moveForward = player.movementInput.moveForward;
      AxisAlignedBB entityBoundingBox = player.getEntityBoundingBox();
      AxisAlignedBB box = new AxisAlignedBB(entityBoundingBox.minX, entityBoundingBox.minY, entityBoundingBox.minZ, entityBoundingBox.maxX, entityBoundingBox.maxY, entityBoundingBox.maxZ);
      boolean isCollidedHorizontally = player.isCollidedHorizontally;
      boolean isCollidedVertically = player.isCollidedVertically;
      boolean isSprinting = player.isSprinting();
      double posX = player.posX;
      double posY = player.posY;
      double posZ = player.posZ;
      float rotationYaw = player.rotationYaw;

      for (int i = 0; i <= n; i++) {
         if (entity != null) {
            Vec3 vec = new Vec3(entity.posX, entity.posY, entity.posZ);
            Vec3 vec2 = new Vec3(entity.realPosX, entity.realPosY, entity.realPosZ);
            double xCoord = vec.xCoord;
            double yCoord = vec.yCoord;
            double zCoord = vec.zCoord;
            int realPosRotationIncrements = entity.realPosRotationIncrements;

            for (int j = 0; j < n - 1; j++) {
               xCoord += (vec2.xCoord - xCoord) / (double)Math.max(1, realPosRotationIncrements);
               yCoord += (vec2.yCoord - yCoord) / (double)Math.max(1, realPosRotationIncrements);
               zCoord += (vec2.zCoord - zCoord) / (double)Math.max(1, realPosRotationIncrements);
               realPosRotationIncrements--;
            }

            Vec3 vec3 = new Vec3(posX, posY + (double)player.getEyeHeight(), posZ);
            float f = entity.width / 2.0F;
            float collisionBorderSize = entity.getCollisionBorderSize();
            AxisAlignedBB box2 = new AxisAlignedBB(
                  xCoord - (double)f, yCoord, zCoord - (double)f, xCoord + (double)f, yCoord + (double)entity.height, zCoord + (double)f
               )
               .expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
            double d = MathHelper.clamp_double(vec3.xCoord, box2.minX, box2.maxX);
            double d2 = MathHelper.clamp_double(vec3.zCoord, box2.minZ, box2.maxZ);
            double d3 = d - posX;
            rotationYaw = (float)(MathHelper.atan2(d2 - posZ, d3) * 180.0 / 3.141592653589793 - 90.0);
         }

         prevPosX = posX;
         prevPosY = posY;
         prevPosZ = posZ;
         moveStrafe = player.movementInput.moveStrafe;
         moveForward = player.movementInput.moveForward;
         EventSlowDown event = SlowDownListener.onSlowDown(0.2F, 0.2F);
         if (player.isUsingItem() && !player.isRiding()) {
            moveStrafe *= event.getStrafeMultiplier();
            moveForward *= event.getForwardMultiplier();
         }

         if (jumpTicks > 0) {
            jumpTicks--;
         }

         double motionZeroThreshold = ViaAugustus.getMotionZeroThreshold();
         if (Math.abs(motionX) < motionZeroThreshold) {
            motionX = 0.0;
         }

         if (Math.abs(motionY) < motionZeroThreshold) {
            motionY = 0.0;
         }

         if (Math.abs(motionZ) < motionZeroThreshold) {
            motionZ = 0.0;
         }

         if (jumping) {
            if (onGround && jumpTicks == 0) {
               motionY = (double)player.getJumpUpwardsMotion();
               if (player.isPotionActive(Potion.jump)) {
                  motionY += (double)((float)(player.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
               }

               if (isSprinting) {
                  float f2 = rotationYaw * 0.017453292F;
                  motionX -= (double)(MathHelper.sin(f2) * 0.2F);
                  motionZ += (double)(MathHelper.cos(f2) * 0.2F);
               }

               jumpTicks = 10;
            }
         } else {
            jumpTicks = 0;
         }

         moveStrafe *= 0.98F;
         moveForward *= 0.98F;
         float f3 = 0.91F;
         if (onGround) {
            f3 = player.entityWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         float f4 = 0.16277136F / (f3 * f3 * f3);
         float f5;
         if (onGround) {
            f5 = player.getAIMoveSpeed() * f4;
         } else {
            f5 = player.jumpMovementFactor;
         }

         EventMove event2 = MoveListener.onMove(rotationYaw, moveForward, moveStrafe, f5, MoveEventType.MOVE_FLYING);
         float f6 = moveStrafe * moveStrafe + moveForward * moveForward;
         if (f6 >= 1.0E-4F) {
            f6 = MathHelper.sqrt_float(f6);
            if (f6 < 1.0F) {
               f6 = 1.0F;
            }

            f6 = f5 / f6;
            moveStrafe *= f6;
            moveForward *= f6;
            float f7 = MathHelper.sin(event2.getYaw() * 3.1415927F / 180.0F);
            float f8 = MathHelper.cos(event2.getYaw() * 3.1415927F / 180.0F);
            motionX += (double)(moveStrafe * f8 - moveForward * f7);
            motionZ += (double)(moveForward * f8 + moveStrafe * f7);
         }

         f3 = 0.91F;
         if (onGround) {
            f3 = mc.theWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         double d5 = motionX;
         double d6 = motionY;
         double d7 = motionZ;
         double d8 = motionX;
         double d9 = motionZ;
         boolean isSneaking = player.isSneaking();
         if (player.onGround && isSneaking) {
            double d10;
            for (d10 = 0.05; d5 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(player, box.offset(d5, -1.0, 0.0)).isEmpty(); d8 = d5) {
               if (d5 < d10 && d5 >= -d10) {
                  d5 = 0.0;
               } else if (d5 > 0.0) {
                  d5 -= d10;
               } else {
                  d5 += d10;
               }
            }

            for (; d7 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(player, box.offset(0.0, -1.0, d7)).isEmpty(); d9 = d7) {
               if (d7 < d10 && d7 >= -d10) {
                  d7 = 0.0;
               } else if (d7 > 0.0) {
                  d7 -= d10;
               } else {
                  d7 += d10;
               }
            }

            for (; d5 != 0.0 && d7 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(player, box.offset(d5, -1.0, d7)).isEmpty(); d9 = d7) {
               if (d5 < d10 && d5 >= -d10) {
                  d5 = 0.0;
               } else if (d5 > 0.0) {
                  d5 -= d10;
               } else {
                  d5 += d10;
               }

               d8 = d5;
               if (d7 < d10 && d7 >= -d10) {
                  d7 = 0.0;
               } else if (d7 > 0.0) {
                  d7 -= d10;
               } else {
                  d7 += d10;
               }
            }
         }

         List list = mc.theWorld.getCollidingBoundingBoxesCapped(player, box.addCoord(d5, motionY, d7));
         AxisAlignedBB box3 = box;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list)) {
            d6 = box4.calculateYOffset(box, d6);
         }

         box = box.offset(0.0, d6, 0.0);
         boolean flag2 = onGround || motionY != d6 && motionY < 0.0;

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list)) {
            d5 = box5.calculateXOffset(box, d5);
         }

         box = box.offset(d5, 0.0, 0.0);

         for (AxisAlignedBB box6 : (Iterable<AxisAlignedBB>)(list)) {
            d7 = box6.calculateZOffset(box, d7);
         }

         box = box.offset(0.0, 0.0, d7);
         EventStep event3 = StepListener.onStep(player.stepHeight, StateTypes.PRE);
         if (event3.getStepHeight() > 0.0F && flag2 && (d8 != d5 || d9 != d7)) {
            double d11 = d5;
            double d12 = d6;
            double d13 = d7;
            AxisAlignedBB box7 = box;
            d6 = (double)event3.getStepHeight();
            List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(player, box3.addCoord(d8, d6, d9));
            AxisAlignedBB box8 = box3.addCoord(d8, 0.0, d9);
            double d14 = d6;

            for (AxisAlignedBB box9 : (Iterable<AxisAlignedBB>)(list2)) {
               d14 = box9.calculateYOffset(box8, d14);
            }

            AxisAlignedBB box10 = box3.offset(0.0, d14, 0.0);
            double d15 = d8;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list2)) {
               d15 = box11.calculateXOffset(box10, d15);
            }

            box10 = box10.offset(d15, 0.0, 0.0);
            double d16 = d9;

            for (AxisAlignedBB box12 : (Iterable<AxisAlignedBB>)(list2)) {
               d16 = box12.calculateZOffset(box10, d16);
            }

            box10 = box10.offset(0.0, 0.0, d16);
            AxisAlignedBB box13 = box3;
            double d17 = d6;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list2)) {
               d17 = box14.calculateYOffset(box13, d17);
            }

            box13 = box13.offset(0.0, d17, 0.0);
            double d18 = d8;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list2)) {
               d18 = box15.calculateXOffset(box13, d18);
            }

            box13 = box13.offset(d18, 0.0, 0.0);
            double d19 = d9;

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list2)) {
               d19 = box16.calculateZOffset(box13, d19);
            }

            box13 = box13.offset(0.0, 0.0, d19);
            if ((d15 * d15 + d16 * d16) > (d18 * d18 + d19 * d19)) {
               d5 = d15;
               d7 = d16;
               d6 = -d14;
               box = box10;
            } else {
               d5 = d18;
               d7 = d19;
               d6 = -d17;
               box = box13;
            }

            for (AxisAlignedBB box17 : (Iterable<AxisAlignedBB>)(list2)) {
               d6 = box17.calculateYOffset(box, d6);
            }

            box = box.offset(0.0, d6, 0.0);
            if (d11 * d11 + d13 * d13 >= d5 * d5 + d7 * d7) {
               d5 = d11;
               d6 = d12;
               d7 = d13;
               box = box7;
            }
         }

         posX = (box.minX + box.maxX) / 2.0;
         posY = box.minY;
         posZ = (box.minZ + box.maxZ) / 2.0;
         isCollidedHorizontally = d8 != d5 || d9 != d7;
         isCollidedVertically = motionY != d6;
         onGround = isCollidedVertically && motionY < 0.0;
         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag3 = false;
         } else {
         }

         int n3 = MathHelper.floor_double(posX);
         int n4 = MathHelper.floor_double(posY - 0.20000000298023224);
         int n5 = MathHelper.floor_double(posZ);
         BlockPos pos = new BlockPos(n3, n4, n5);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (d8 != d5) {
            motionX = 0.0;
         }

         if (d9 != d7) {
            motionZ = 0.0;
         }

         if (motionY != d6) {
            if (block instanceof BlockSlime) {
               if (player.isSneaking()) {
                  motionY = 0.0;
               } else if (motionY < 0.0) {
                  motionY = -motionY;
               }
            } else {
               motionY = 0.0;
            }
         }

         if (player.entityWorld.isRemote
            && (
               !player.entityWorld.isBlockLoaded(new BlockPos((int)posX, 0, (int)posZ))
                  || !player.entityWorld.getChunkFromBlockCoords(new BlockPos((int)posX, 0, (int)posZ)).isLoaded()
            )) {
            if (posY > 0.0) {
               motionY = -0.1;
            } else {
               motionY = 0.0;
            }
         } else {
            motionY -= 0.08;
         }

         motionY *= 0.9800000190734863;
         motionX *= (double)f3;
         motionZ *= (double)f3;
      }

      return new double[]{posX, posY, posZ, prevPosX, prevPosY, prevPosZ, motionX, motionY, motionZ, onGround ? 1.0 : 0.0, isCollidedHorizontally ? 1.0 : 0.0};
   }

   public static void damageSelfPacket() {

      for (int i = 0; i < 51; i++) {
         mc.getNetHandler().addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.06, mc.thePlayer.posZ, false));
         mc.getNetHandler().addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
      }

      mc.getNetHandler().addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
      mc.getNetHandler().addToSendQueueUnchecked(new C03PacketPlayer(true));
   }

   public static ArrayList predictEntityTrajectory(EntityLivingBase entity, int n) {
      ArrayList list = new ArrayList();
      double d = entity.posX - entity.lastTickPosX;
      double d2 = entity.posZ - entity.lastTickPosZ;
      double d3 = entity.posY - entity.lastTickPosY;
      AxisAlignedBB entityBoundingBox = entity.getEntityBoundingBox();
      AxisAlignedBB box = new AxisAlignedBB(entityBoundingBox.minX, entityBoundingBox.minY, entityBoundingBox.minZ, entityBoundingBox.maxX, entityBoundingBox.maxY, entityBoundingBox.maxZ);
      boolean onGround = entity.onGround;
      boolean isCollidedHorizontally = entity.isCollidedHorizontally;
      boolean isCollidedVertically = entity.isCollidedVertically;
      double newPosX = entity.newPosX;
      double newPosY = entity.newPosY;
      double newPosZ = entity.newPosZ;
      if (entity instanceof EntityOtherPlayerMP) {
         EntityOtherPlayerMP entityOtherPlayerMP = (EntityOtherPlayerMP)entity;
         newPosX = entityOtherPlayerMP.otherPlayerMPX;
         newPosY = entityOtherPlayerMP.otherPlayerMPY;
         newPosZ = entityOtherPlayerMP.otherPlayerMPZ;
      }

      for (int i = 0; i <= n; i++) {
         d = entity.posX - entity.lastTickPosX;
         d2 = entity.posZ - entity.lastTickPosZ;
         double d4 = d;
         double d5 = entity.rotationPitchHead < 75.0F
               && entity instanceof EntityPlayer
               && ((EntityPlayer)entity).getCurrentEquippedItem() != null
               && ((EntityPlayer)entity).getCurrentEquippedItem().getItem() instanceof ItemBlock
            ? 0.0
            : d3;
         double d6 = d2;
         double d7 = d5;
         if (d > 10.0 || d5 > 10.0 || d2 > 10.0) {
            return list;
         }

         List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box.addCoord(d, d5, d2));
         AxisAlignedBB box2 = box;

         for (AxisAlignedBB box3 : (Iterable<AxisAlignedBB>)(list2)) {
            d5 = box3.calculateYOffset(box, d5);
         }

         box = box.offset(0.0, d5, 0.0);
         boolean flag = onGround || d7 != d5 && d7 < 0.0;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list2)) {
            d4 = box4.calculateXOffset(box, d4);
         }

         box = box.offset(d4, 0.0, 0.0);

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list2)) {
            d6 = box5.calculateZOffset(box, d6);
         }

         box = box.offset(0.0, 0.0, d6);
         if (entity.stepHeight > 0.0F && flag && (d != d4 || d2 != d6)) {
            double d8 = d4;
            double d9 = d5;
            double d10 = d6;
            AxisAlignedBB box6 = box;
            d5 = (double)entity.stepHeight;
            List list3 = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.addCoord(d, d5, d2));
            AxisAlignedBB box7 = box2.addCoord(d, 0.0, d2);
            double d11 = d5;

            for (AxisAlignedBB box8 : (Iterable<AxisAlignedBB>)(list3)) {
               d11 = box8.calculateYOffset(box7, d11);
            }

            AxisAlignedBB box9 = box2.offset(0.0, d11, 0.0);
            double d12 = d;

            for (AxisAlignedBB box10 : (Iterable<AxisAlignedBB>)(list3)) {
               d12 = box10.calculateXOffset(box9, d12);
            }

            box9 = box9.offset(d12, 0.0, 0.0);
            double d13 = d2;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list3)) {
               d13 = box11.calculateZOffset(box9, d13);
            }

            box9 = box9.offset(0.0, 0.0, d13);
            AxisAlignedBB box12 = box2;
            double d14 = d5;

            for (AxisAlignedBB box13 : (Iterable<AxisAlignedBB>)(list3)) {
               d14 = box13.calculateYOffset(box12, d14);
            }

            box12 = box12.offset(0.0, d14, 0.0);
            double d15 = d;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list3)) {
               d15 = box14.calculateXOffset(box12, d15);
            }

            box12 = box12.offset(d15, 0.0, 0.0);
            double d16 = d2;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list3)) {
               d16 = box15.calculateZOffset(box12, d16);
            }

            box12 = box12.offset(0.0, 0.0, d16);
            if ((d12 * d12 + d13 * d13) > (d15 * d15 + d16 * d16)) {
               d4 = d12;
               d6 = d13;
               d5 = -d11;
               box = box9;
            } else {
               d4 = d15;
               d6 = d16;
               d5 = -d14;
               box = box12;
            }

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list3)) {
               d5 = box16.calculateYOffset(box, d5);
            }

            box = box.offset(0.0, d5, 0.0);
            if (d8 * d8 + d10 * d10 >= d4 * d4 + d6 * d6) {
               d4 = d8;
               d5 = d9;
               d6 = d10;
               box = box6;
            }
         }

         newPosX = (box.minX + box.maxX) / 2.0;
         newPosY = box.minY;
         newPosZ = (box.minZ + box.maxZ) / 2.0;
         isCollidedHorizontally = d != d4 || d2 != d6;
         isCollidedVertically = d7 != d5;
         onGround = isCollidedVertically && d7 < 0.0;
         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag2 = false;
         } else {
         }

         int n2 = MathHelper.floor_double(newPosX);
         int n3 = MathHelper.floor_double(newPosY - 0.20000000298023224);
         int n4 = MathHelper.floor_double(newPosZ);
         BlockPos pos = new BlockPos(n2, n3, n4);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (d != d4) {
            d = 0.0;
         }

         if (d2 != d6) {
            d2 = 0.0;
         }

         if (d7 != d5) {
            if (block instanceof BlockSlime) {
               if (entity.isSneaking()) {
                  d3 = 0.0;
               } else if (d3 < 0.0) {
                  d3 = -d3;
               }
            } else {
               d3 = 0.0;
            }
         }

         if (mc.theWorld.isRemote
            && (
               !mc.theWorld.isBlockLoaded(new BlockPos((int)newPosX, 0, (int)newPosZ))
                  || !mc.theWorld.getChunkFromBlockCoords(new BlockPos((int)newPosX, 0, (int)newPosZ)).isLoaded()
            )) {
            if (newPosY > 0.0) {
               d3 = -0.1;
            } else {
               d3 = 0.0;
            }
         } else {
            d3 -= 0.08;
         }

         d3 *= 0.9800000190734863;
         list.add(new Vec3(newPosX, newPosY, newPosZ));
      }

      return list;
   }

   public static double[] predictPlayerPosition(int n, EntityPlayerSP player, float f) {
      double posX = player.posX;
      double posY = player.posY;
      double posZ = player.posZ;
      EventMoveInput event = new EventMoveInput(
         mc.thePlayer.movementInput.moveStrafe,
         mc.thePlayer.movementInput.moveForward,
         mc.thePlayer.movementInput.jump,
         mc.thePlayer.movementInput.sneak,
         MoveInputStage.PRE
      );
      return predictPlayerPosition(n, player, posX, posY, posZ, f, event, false);
   }

   public static ArrayList predictPlayerTrajectory(int n, EntityPlayerSP player, double d, double d2, double d3, EventMoveInput event) {
      return predictPlayerTrajectory(n, player, d, d2, d3, player.motionY, event);
   }

   public static double[] capturePlayerState() {
      return new double[]{
         mc.thePlayer.posX,
         mc.thePlayer.posY,
         mc.thePlayer.posZ,
         mc.thePlayer.prevPosX,
         mc.thePlayer.prevPosY,
         mc.thePlayer.prevPosZ,
         mc.thePlayer.motionX,
         mc.thePlayer.motionY,
         mc.thePlayer.motionZ,
         mc.thePlayer.onGround ? 1.0 : 0.0,
         mc.thePlayer.isCollidedHorizontally ? 1.0 : 0.0,
         (double)mc.thePlayer.fallDistance
      };
   }

   public static Vec3 predictNextTickWithAttack(boolean flag, Entity entity, double d, float f) {
      return predictNextTickWithAttack(flag, entity, d, f, mc.thePlayer.isSprinting());
   }

   public static double[] predictEntityFromServerMotion(EntityLivingBase entity, int n) {
      double d = entity.posX - entity.lastTickPosX;
      double d2 = entity.posZ - entity.lastTickPosZ;
      double d3 = entity.posY - entity.lastTickPosY;
      AxisAlignedBB entityBoundingBox = entity.getEntityBoundingBox();
      AxisAlignedBB box = new AxisAlignedBB(entityBoundingBox.minX, entityBoundingBox.minY, entityBoundingBox.minZ, entityBoundingBox.maxX, entityBoundingBox.maxY, entityBoundingBox.maxZ);
      boolean onGround = entity.onGround;
      boolean isCollidedHorizontally = entity.isCollidedHorizontally;
      boolean isCollidedVertically = entity.isCollidedVertically;
      double newPosX = entity.newPosX;
      double newPosY = entity.newPosY;
      double newPosZ = entity.newPosZ;
      if (entity instanceof EntityOtherPlayerMP) {
         EntityOtherPlayerMP entityOtherPlayerMP = (EntityOtherPlayerMP)entity;
         newPosX = entityOtherPlayerMP.otherPlayerMPX;
         newPosY = entityOtherPlayerMP.otherPlayerMPY;
         newPosZ = entityOtherPlayerMP.otherPlayerMPZ;
         float f = entityOtherPlayerMP.width / 2.0F;
         box = new AxisAlignedBB(
            newPosX - (double)f, newPosY, newPosZ - (double)f, newPosX + (double)f, newPosY + (double)entityOtherPlayerMP.height, newPosZ + (double)f
         );
      }

      for (int i = 0; i <= n; i++) {
         d = entity.interpolatedServerPos.xCoord - entity.prevInterpolatedServerPos.xCoord;
         d2 = entity.interpolatedServerPos.zCoord - entity.prevInterpolatedServerPos.zCoord;
         double d4 = d;
         double d5 = entity.rotationPitchHead < 75.0F
               && entity instanceof EntityPlayer
               && ((EntityPlayer)entity).getCurrentEquippedItem() != null
               && ((EntityPlayer)entity).getCurrentEquippedItem().getItem() instanceof ItemBlock
            ? 0.0
            : d3;
         double d6 = d2;
         double d7 = d5;
         if (d > 10.0 || d5 > 10.0 || d2 > 10.0) {
            return new double[]{newPosX, newPosY, newPosZ, d, d3, d2, onGround ? 1.0 : 0.0};
         }

         List list = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box.addCoord(d, d5, d2));
         AxisAlignedBB box2 = box;

         for (AxisAlignedBB box3 : (Iterable<AxisAlignedBB>)(list)) {
            d5 = box3.calculateYOffset(box, d5);
         }

         box = box.offset(0.0, d5, 0.0);
         boolean flag = onGround || d7 != d5 && d7 < 0.0;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list)) {
            d4 = box4.calculateXOffset(box, d4);
         }

         box = box.offset(d4, 0.0, 0.0);

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list)) {
            d6 = box5.calculateZOffset(box, d6);
         }

         box = box.offset(0.0, 0.0, d6);
         if (entity.stepHeight > 0.0F && flag && (d != d4 || d2 != d6)) {
            double d8 = d4;
            double d9 = d5;
            double d10 = d6;
            AxisAlignedBB box6 = box;
            d5 = (double)entity.stepHeight;
            List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.addCoord(d, d5, d2));
            AxisAlignedBB box7 = box2.addCoord(d, 0.0, d2);
            double d11 = d5;

            for (AxisAlignedBB box8 : (Iterable<AxisAlignedBB>)(list2)) {
               d11 = box8.calculateYOffset(box7, d11);
            }

            AxisAlignedBB box9 = box2.offset(0.0, d11, 0.0);
            double d12 = d;

            for (AxisAlignedBB box10 : (Iterable<AxisAlignedBB>)(list2)) {
               d12 = box10.calculateXOffset(box9, d12);
            }

            box9 = box9.offset(d12, 0.0, 0.0);
            double d13 = d2;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list2)) {
               d13 = box11.calculateZOffset(box9, d13);
            }

            box9 = box9.offset(0.0, 0.0, d13);
            AxisAlignedBB box12 = box2;
            double d14 = d5;

            for (AxisAlignedBB box13 : (Iterable<AxisAlignedBB>)(list2)) {
               d14 = box13.calculateYOffset(box12, d14);
            }

            box12 = box12.offset(0.0, d14, 0.0);
            double d15 = d;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list2)) {
               d15 = box14.calculateXOffset(box12, d15);
            }

            box12 = box12.offset(d15, 0.0, 0.0);
            double d16 = d2;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list2)) {
               d16 = box15.calculateZOffset(box12, d16);
            }

            box12 = box12.offset(0.0, 0.0, d16);
            if ((d12 * d12 + d13 * d13) > (d15 * d15 + d16 * d16)) {
               d4 = d12;
               d6 = d13;
               d5 = -d11;
               box = box9;
            } else {
               d4 = d15;
               d6 = d16;
               d5 = -d14;
               box = box12;
            }

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list2)) {
               d5 = box16.calculateYOffset(box, d5);
            }

            box = box.offset(0.0, d5, 0.0);
            if (d8 * d8 + d10 * d10 >= d4 * d4 + d6 * d6) {
               d4 = d8;
               d5 = d9;
               d6 = d10;
               box = box6;
            }
         }

         newPosX = (box.minX + box.maxX) / 2.0;
         newPosY = box.minY;
         newPosZ = (box.minZ + box.maxZ) / 2.0;
         isCollidedHorizontally = d != d4 || d2 != d6;
         isCollidedVertically = d7 != d5;
         onGround = isCollidedVertically && d7 < 0.0;
         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag2 = false;
         } else {
         }

         int n2 = MathHelper.floor_double(newPosX);
         int n3 = MathHelper.floor_double(newPosY - 0.20000000298023224);
         int n4 = MathHelper.floor_double(newPosZ);
         BlockPos pos = new BlockPos(n2, n3, n4);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (d != d4) {
            d = 0.0;
         }

         if (d2 != d6) {
            d2 = 0.0;
         }

         if (d7 != d5) {
            if (block instanceof BlockSlime) {
               if (entity.isSneaking()) {
                  d3 = 0.0;
               } else if (d3 < 0.0) {
                  d3 = -d3;
               }
            } else {
               d3 = 0.0;
            }
         }

         if (mc.theWorld.isRemote
            && (
               !mc.theWorld.isBlockLoaded(new BlockPos((int)newPosX, 0, (int)newPosZ))
                  || !mc.theWorld.getChunkFromBlockCoords(new BlockPos((int)newPosX, 0, (int)newPosZ)).isLoaded()
            )) {
            if (newPosY > 0.0) {
               d3 = -0.1;
            } else {
               d3 = 0.0;
            }
         } else {
            d3 -= 0.08;
         }

         d3 *= 0.9800000190734863;
      }

      return new double[]{newPosX, newPosY, newPosZ, d, d3, d2, onGround ? 1.0 : 0.0};
   }

   public static ArrayList predictPlayerTrajectory(int n, EntityPlayerSP player, double d, double d2, double d3, double d4, EventMoveInput event) {
      ArrayList list = new ArrayList();
      double motionX = player.motionX;
      double motionZ = player.motionZ;
      boolean isJump = event.isJump();
      double prevPosX = player.prevPosX;
      double prevPosY = player.prevPosY;
      double prevPosZ = player.prevPosZ;
      int jumpTicks = player.getJumpTicks();
      boolean onGround = player.onGround;
      float moveStrafe = event.getMoveStrafe();
      float moveForward = event.getMoveForward();
      AxisAlignedBB entityBoundingBox = player.getEntityBoundingBox();
      AxisAlignedBB box = new AxisAlignedBB(entityBoundingBox.minX, entityBoundingBox.minY, entityBoundingBox.minZ, entityBoundingBox.maxX, entityBoundingBox.maxY, entityBoundingBox.maxZ);
      boolean isCollidedHorizontally = player.isCollidedHorizontally;
      boolean isCollidedVertically = player.isCollidedVertically;
      boolean isSprinting = player.isSprinting();
      float fallDistance = player.fallDistance;

      for (int i = 0; i <= n; i++) {
         moveStrafe = event.getMoveStrafe();
         moveForward = event.getMoveForward();
         EventSlowDown event2 = SlowDownListener.onSlowDown(0.2F, 0.2F);
         if (player.isUsingItem() && !player.isRiding()) {
            moveStrafe *= event2.getStrafeMultiplier();
            moveForward *= event2.getForwardMultiplier();
         }

         if (jumpTicks > 0) {
            jumpTicks--;
         }

         double motionZeroThreshold = ViaAugustus.getMotionZeroThreshold();
         if (Math.abs(motionX) < motionZeroThreshold) {
            motionX = 0.0;
         }

         if (Math.abs(d4) < motionZeroThreshold) {
            d4 = 0.0;
         }

         if (Math.abs(motionZ) < motionZeroThreshold) {
            motionZ = 0.0;
         }

         if (isJump) {
            if (onGround && jumpTicks == 0) {
               d4 = (double)player.getJumpUpwardsMotion();
               if (player.isPotionActive(Potion.jump)) {
                  d4 += (double)((float)(player.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
               }

               if (isSprinting) {
                  float f = player.rotationYaw * 0.017453292F;
                  motionX -= (double)(MathHelper.sin(f) * 0.2F);
                  motionZ += (double)(MathHelper.cos(f) * 0.2F);
               }

               jumpTicks = 10;
            }
         } else {
            jumpTicks = 0;
         }

         moveStrafe *= 0.98F;
         moveForward *= 0.98F;
         float f2 = 0.91F;
         if (onGround) {
            f2 = player.entityWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(d), MathHelper.floor_double(d2) - 1, MathHelper.floor_double(d3)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         float f3 = 0.16277136F / (f2 * f2 * f2);
         float f4;
         if (onGround) {
            f4 = player.getAIMoveSpeed() * f3;
         } else {
            f4 = player.jumpMovementFactor;
         }

         EventMove event3 = MoveListener.onMove(player.rotationYaw, moveForward, moveStrafe, f4, MoveEventType.MOVE_FLYING);
         float f5 = moveStrafe * moveStrafe + moveForward * moveForward;
         if (f5 >= 1.0E-4F) {
            f5 = MathHelper.sqrt_float(f5);
            if (f5 < 1.0F) {
               f5 = 1.0F;
            }

            f5 = f4 / f5;
            moveStrafe *= f5;
            moveForward *= f5;
            float f6 = MathHelper.sin(event3.getYaw() * 3.1415927F / 180.0F);
            float f7 = MathHelper.cos(event3.getYaw() * 3.1415927F / 180.0F);
            motionX += (double)(moveStrafe * f7 - moveForward * f6);
            motionZ += (double)(moveForward * f7 + moveStrafe * f6);
         }

         f2 = 0.91F;
         if (onGround) {
            f2 = mc.theWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(d), MathHelper.floor_double(d2) - 1, MathHelper.floor_double(d3)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         double d5 = motionX;
         double d6 = d4;
         double d7 = motionZ;
         double d8 = motionX;
         double d9 = motionZ;
         boolean isSneaking = player.isSneaking();
         if (onGround && isSneaking) {
            double d10;
            for (d10 = 0.05; d5 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(player, box.offset(d5, -1.0, 0.0)).isEmpty(); d8 = d5) {
               if (d5 < d10 && d5 >= -d10) {
                  d5 = 0.0;
               } else if (d5 > 0.0) {
                  d5 -= d10;
               } else {
                  d5 += d10;
               }
            }

            for (; d7 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(player, box.offset(0.0, -1.0, d7)).isEmpty(); d9 = d7) {
               if (d7 < d10 && d7 >= -d10) {
                  d7 = 0.0;
               } else if (d7 > 0.0) {
                  d7 -= d10;
               } else {
                  d7 += d10;
               }
            }

            for (; d5 != 0.0 && d7 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(player, box.offset(d5, -1.0, d7)).isEmpty(); d9 = d7) {
               if (d5 < d10 && d5 >= -d10) {
                  d5 = 0.0;
               } else if (d5 > 0.0) {
                  d5 -= d10;
               } else {
                  d5 += d10;
               }

               d8 = d5;
               if (d7 < d10 && d7 >= -d10) {
                  d7 = 0.0;
               } else if (d7 > 0.0) {
                  d7 -= d10;
               } else {
                  d7 += d10;
               }
            }
         }

         List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(player, box.addCoord(d5, d4, d7));
         AxisAlignedBB box2 = box;

         for (AxisAlignedBB box3 : (Iterable<AxisAlignedBB>)(list2)) {
            d6 = box3.calculateYOffset(box, d6);
         }

         box = box.offset(0.0, d6, 0.0);
         boolean flag2 = onGround || d4 != d6 && d4 < 0.0;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list2)) {
            d5 = box4.calculateXOffset(box, d5);
         }

         box = box.offset(d5, 0.0, 0.0);

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list2)) {
            d7 = box5.calculateZOffset(box, d7);
         }

         box = box.offset(0.0, 0.0, d7);
         EventStep event4 = StepListener.onStep(player.stepHeight, StateTypes.PRE);
         if (event4.getStepHeight() > 0.0F && flag2 && (d8 != d5 || d9 != d7)) {
            double d11 = d5;
            double d12 = d6;
            double d13 = d7;
            AxisAlignedBB box6 = box;
            d6 = (double)event4.getStepHeight();
            List list3 = mc.theWorld.getCollidingBoundingBoxesCapped(player, box2.addCoord(d8, d6, d9));
            AxisAlignedBB box7 = box2.addCoord(d8, 0.0, d9);
            double d14 = d6;

            for (AxisAlignedBB box8 : (Iterable<AxisAlignedBB>)(list3)) {
               d14 = box8.calculateYOffset(box7, d14);
            }

            AxisAlignedBB box9 = box2.offset(0.0, d14, 0.0);
            double d15 = d8;

            for (AxisAlignedBB box10 : (Iterable<AxisAlignedBB>)(list3)) {
               d15 = box10.calculateXOffset(box9, d15);
            }

            box9 = box9.offset(d15, 0.0, 0.0);
            double d16 = d9;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list3)) {
               d16 = box11.calculateZOffset(box9, d16);
            }

            box9 = box9.offset(0.0, 0.0, d16);
            AxisAlignedBB box12 = box2;
            double d17 = d6;

            for (AxisAlignedBB box13 : (Iterable<AxisAlignedBB>)(list3)) {
               d17 = box13.calculateYOffset(box12, d17);
            }

            box12 = box12.offset(0.0, d17, 0.0);
            double d18 = d8;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list3)) {
               d18 = box14.calculateXOffset(box12, d18);
            }

            box12 = box12.offset(d18, 0.0, 0.0);
            double d19 = d9;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list3)) {
               d19 = box15.calculateZOffset(box12, d19);
            }

            box12 = box12.offset(0.0, 0.0, d19);
            if ((d15 * d15 + d16 * d16) > (d18 * d18 + d19 * d19)) {
               d5 = d15;
               d7 = d16;
               d6 = -d14;
               box = box9;
            } else {
               d5 = d18;
               d7 = d19;
               d6 = -d17;
               box = box12;
            }

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list3)) {
               d6 = box16.calculateYOffset(box, d6);
            }

            box = box.offset(0.0, d6, 0.0);
            if (d11 * d11 + d13 * d13 >= d5 * d5 + d7 * d7) {
               d5 = d11;
               d6 = d12;
               d7 = d13;
               box = box6;
            }
         }

         d = (box.minX + box.maxX) / 2.0;
         d2 = box.minY;
         d3 = (box.minZ + box.maxZ) / 2.0;
         isCollidedHorizontally = d8 != d5 || d9 != d7;
         isCollidedVertically = d4 != d6;
         onGround = isCollidedVertically && d4 < 0.0;
         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag3 = false;
         } else {
         }

         int n2 = MathHelper.floor_double(d);
         int n3 = MathHelper.floor_double(d2 - 0.20000000298023224);
         int n4 = MathHelper.floor_double(d3);
         BlockPos pos = new BlockPos(n2, n3, n4);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (onGround) {
            if (fallDistance > 0.0F) {
               fallDistance = 0.0F;
            }
         } else if (d6 < 0.0) {
            fallDistance = (float)((double)fallDistance - d6);
         }

         if (d8 != d5) {
            motionX = 0.0;
         }

         if (d9 != d7) {
            motionZ = 0.0;
         }

         if (d4 != d6) {
            if (block instanceof BlockSlime) {
               if (player.isSneaking()) {
                  d4 = 0.0;
               } else if (d4 < 0.0) {
                  d4 = -d4;
               }
            } else {
               d4 = 0.0;
            }
         }

         if (player.entityWorld.isRemote
            && (
               !player.entityWorld.isBlockLoaded(new BlockPos((int)d, 0, (int)d3))
                  || !player.entityWorld.getChunkFromBlockCoords(new BlockPos((int)d, 0, (int)d3)).isLoaded()
            )) {
            if (d2 > 0.0) {
               d4 = -0.1;
            } else {
               d4 = 0.0;
            }
         } else {
            d4 -= 0.08;
         }

         d4 *= 0.9800000190734863;
         motionX *= (double)f2;
         motionZ *= (double)f2;
         list.add(new Vec3(d, d2, d3));
      }

      return list;
   }

   public static float getPlayerHealthByMode(StringValue booleansSetting, EntityPlayer player) {
      if (mc.theWorld == null) {
         return 0.0F;
      } else if (booleansSetting.getSelectedValue().equals("Entity")) {
         return player.getCurrentHealth();
      } else {
         Scoreboard scoreboard = mc.theWorld.getScoreboard();
         if (scoreboard == null) {
            return 0.0F;
         } else if (booleansSetting.getSelectedValue().equals("TabList")) {
            ScoreObjective objectiveInDisplaySlot = scoreboard.getObjectiveInDisplaySlot(0);
            return objectiveInDisplaySlot == null ? 0.0F : (float)scoreboard.getValueFromObjective(player.getGameProfile().getName(), objectiveInDisplaySlot).getScorePoints();
         } else {
            ScoreObjective objectiveInDisplaySlot2 = scoreboard.getObjectiveInDisplaySlot(2);
            return objectiveInDisplaySlot2 == null ? 0.0F : (float)scoreboard.getValueFromObjective(player.getGameProfile().getName(), objectiveInDisplaySlot2).getScorePoints();
         }
      }
   }


   public static void restorePlayerState(double[] doubles) {
      mc.thePlayer.setPosition(doubles[0], doubles[1], doubles[2]);
      mc.thePlayer.prevPosX = doubles[3];
      mc.thePlayer.prevPosY = doubles[4];
      mc.thePlayer.prevPosZ = doubles[5];
      mc.thePlayer.motionX = doubles[6];
      mc.thePlayer.motionY = doubles[7];
      mc.thePlayer.motionZ = doubles[8];
      mc.thePlayer.onGround = doubles[9] == 1.0;
      mc.thePlayer.isCollidedHorizontally = doubles[10] == 1.0;
      mc.thePlayer.fallDistance = (float)doubles[11];
   }

   public static double[] predictPlayerPosition(int n, EntityPlayerSP player, double d, double d2, double d3, float f, EventMoveInput event) {
      return predictPlayerPosition(n, player, d, d2, d3, f, event, false);
   }

   public static double[] getInterpolatedEntityPosition(EntityLivingBase entity) {
      double posX = entity.posX;
      double posY = entity.posY;
      double posZ = entity.posZ;
      if (entity instanceof EntityOtherPlayerMP) {
         EntityOtherPlayerMP entityOtherPlayerMP = (EntityOtherPlayerMP)entity;
         if (entityOtherPlayerMP.otherPlayerMPPosRotationIncrements > 0) {
            posX = entityOtherPlayerMP.posX + (entityOtherPlayerMP.otherPlayerMPX - entityOtherPlayerMP.posX) / (double)entityOtherPlayerMP.otherPlayerMPPosRotationIncrements;
            posY = entityOtherPlayerMP.posY + (entityOtherPlayerMP.otherPlayerMPY - entityOtherPlayerMP.posY) / (double)entityOtherPlayerMP.otherPlayerMPPosRotationIncrements;
            posZ = entityOtherPlayerMP.posZ + (entityOtherPlayerMP.otherPlayerMPZ - entityOtherPlayerMP.posZ) / (double)entityOtherPlayerMP.otherPlayerMPPosRotationIncrements;
         }
      } else if (entity.newPosRotationIncrements > 0) {
         posX = entity.posX + (entity.newPosX - entity.posX) / (double)entity.newPosRotationIncrements;
         posY = entity.posY + (entity.newPosY - entity.posY) / (double)entity.newPosRotationIncrements;
         posZ = entity.posZ + (entity.newPosZ - entity.posZ) / (double)entity.newPosRotationIncrements;
      }

      return new double[]{posX, posY, posZ};
   }

   public static double[] predictTargetPhysicsPosition(EntityLivingBase entity, int n) {
      double d = MathHelper.clamp_double(entity.realPosX - entity.predictedPosX, -20.0, 20.0);
      double d2 = MathHelper.clamp_double(entity.realPosY - entity.predictedPosY, -20.0, 20.0);
      double d3 = MathHelper.clamp_double(entity.realPosZ - entity.predictedPosZ, -20.0, 20.0);
      double realPosX = entity.realPosX;
      double realPosY = entity.realPosY;
      double realPosZ = entity.realPosZ;
      double predictedPosX = entity.predictedPosX;
      double predictedPosY = entity.predictedPosY;
      double predictedPosZ = entity.predictedPosZ;
      float f = entity.width / 2.0F;
      AxisAlignedBB box = new AxisAlignedBB(
         realPosX - (double)f, realPosY, realPosZ - (double)f, realPosX + (double)f, realPosY + (double)entity.height, realPosZ + (double)f
      );
      boolean isLikelyOnGround = PlayerInputPredictor.isLikelyOnGround(entity);
      boolean isCollidedHorizontally = entity.isCollidedHorizontally;
      boolean isCollidedVertically = entity.isCollidedVertically;
      Vec3 vec = null;
      boolean flag = !isLikelyOnGround;
      if (entity instanceof AbstractClientPlayer) {
         vec = new Vec3(0.0, 0.0, 1.0);
         d *= 0.9100000262260437;
         d2 *= 0.9100000262260437;
      }

      for (int i = 0; i <= n; i++) {
         predictedPosX = realPosX;
         predictedPosY = realPosY;
         predictedPosZ = realPosZ;
         float f2 = 0.91F;
         if (entity instanceof AbstractClientPlayer && vec != null) {
            AbstractClientPlayer clientPlayer = (AbstractClientPlayer)entity;
            float zCoord = (float)vec.zCoord;
            float xCoord = (float)vec.xCoord;
            if (clientPlayer.isUsingItem() && !clientPlayer.isRiding()) {
               xCoord *= 0.2F;
               zCoord *= 0.2F;
            }

            double motionZeroThreshold = ViaAugustus.getMotionZeroThreshold();
            if (Math.abs(d) < motionZeroThreshold) {
               d = 0.0;
            }

            if (Math.abs(d3) < motionZeroThreshold) {
               d3 = 0.0;
            }

            if (Math.abs(d2) < motionZeroThreshold) {
               d2 = 0.0;
            }

            if (flag && isLikelyOnGround) {
               d3 = (double)clientPlayer.getJumpUpwardsMotion();
               if (clientPlayer.isPotionActive(Potion.jump)) {
                  d3 += (double)((float)(clientPlayer.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
               }

               float f3 = clientPlayer.rotationYawHead * 0.017453292F;
               d -= (double)(MathHelper.sin(f3) * 0.2F);
               d2 += (double)(MathHelper.cos(f3) * 0.2F);
            }

            xCoord *= 0.98F;
            zCoord *= 0.98F;
            if (isLikelyOnGround) {
               f2 = clientPlayer.entityWorld
                     .getBlockState(new BlockPos(MathHelper.floor_double(realPosX), MathHelper.floor_double(realPosY) - 1, MathHelper.floor_double(realPosZ)))
                     .getBlock()
                     .slipperiness
                  * 0.91F;
            }

            float f4 = 0.16277136F / (f2 * f2 * f2);
            float f5;
            if (isLikelyOnGround) {
               f5 = 0.13000001F * f4;
            } else {
               f5 = clientPlayer.jumpMovementFactor;
            }

            float f6 = xCoord * xCoord + zCoord * zCoord;
            if (f6 >= 1.0E-4F) {
               f6 = MathHelper.sqrt_float(f6);
               if (f6 < 1.0F) {
                  f6 = 1.0F;
               }

               f6 = f5 / f6;
               xCoord *= f6;
               zCoord *= f6;
               float f7 = MathHelper.sin(clientPlayer.rotationYaw * 3.1415927F / 180.0F);
               float f8 = MathHelper.cos(clientPlayer.rotationYaw * 3.1415927F / 180.0F);
               d += (double)(xCoord * f8 - zCoord * f7);
               d2 += (double)(zCoord * f8 + xCoord * f7);
            }

            f2 = 0.91F;
            if (isLikelyOnGround) {
               f2 = mc.theWorld
                     .getBlockState(new BlockPos(MathHelper.floor_double(realPosX), MathHelper.floor_double(realPosY) - 1, MathHelper.floor_double(realPosZ)))
                     .getBlock()
                     .slipperiness
                  * 0.91F;
            }
         } else {
            d = entity.realPosX - entity.predictedPosX;
            d2 = entity.realPosY - entity.predictedPosY;
         }

         double d4 = d;
         double d5 = entity.rotationPitchHead < 75.0F
               && entity instanceof EntityPlayer
               && ((EntityPlayer)entity).getCurrentEquippedItem() != null
               && ((EntityPlayer)entity).getCurrentEquippedItem().getItem() instanceof ItemBlock
            ? 0.0
            : d3;
         double d6 = d2;
         double d7 = d5;
         List list = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box.addCoord(d, d5, d2));
         AxisAlignedBB box2 = box;

         for (AxisAlignedBB box3 : (Iterable<AxisAlignedBB>)(list)) {
            d5 = box3.calculateYOffset(box, d5);
         }

         box = box.offset(0.0, d5, 0.0);
         boolean flag2 = isLikelyOnGround || d7 != d5 && d7 < 0.0;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list)) {
            d4 = box4.calculateXOffset(box, d4);
         }

         box = box.offset(d4, 0.0, 0.0);

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list)) {
            d6 = box5.calculateZOffset(box, d6);
         }

         box = box.offset(0.0, 0.0, d6);
         if (entity.stepHeight > 0.0F && flag2 && (d != d4 || d2 != d6)) {
            double d8 = d4;
            double d9 = d5;
            double d10 = d6;
            AxisAlignedBB box6 = box;
            d5 = (double)entity.stepHeight;
            List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.addCoord(d, d5, d2));
            AxisAlignedBB box7 = box2.addCoord(d, 0.0, d2);
            double d11 = d5;

            for (AxisAlignedBB box8 : (Iterable<AxisAlignedBB>)(list2)) {
               d11 = box8.calculateYOffset(box7, d11);
            }

            AxisAlignedBB box9 = box2.offset(0.0, d11, 0.0);
            double d12 = d;

            for (AxisAlignedBB box10 : (Iterable<AxisAlignedBB>)(list2)) {
               d12 = box10.calculateXOffset(box9, d12);
            }

            box9 = box9.offset(d12, 0.0, 0.0);
            double d13 = d2;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list2)) {
               d13 = box11.calculateZOffset(box9, d13);
            }

            box9 = box9.offset(0.0, 0.0, d13);
            AxisAlignedBB box12 = box2;
            double d14 = d5;

            for (AxisAlignedBB box13 : (Iterable<AxisAlignedBB>)(list2)) {
               d14 = box13.calculateYOffset(box12, d14);
            }

            box12 = box12.offset(0.0, d14, 0.0);
            double d15 = d;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list2)) {
               d15 = box14.calculateXOffset(box12, d15);
            }

            box12 = box12.offset(d15, 0.0, 0.0);
            double d16 = d2;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list2)) {
               d16 = box15.calculateZOffset(box12, d16);
            }

            box12 = box12.offset(0.0, 0.0, d16);
            if ((d12 * d12 + d13 * d13) > (d15 * d15 + d16 * d16)) {
               d4 = d12;
               d6 = d13;
               d5 = -d11;
               box = box9;
            } else {
               d4 = d15;
               d6 = d16;
               d5 = -d14;
               box = box12;
            }

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list2)) {
               d5 = box16.calculateYOffset(box, d5);
            }

            box = box.offset(0.0, d5, 0.0);
            if (d8 * d8 + d10 * d10 >= d4 * d4 + d6 * d6) {
               d4 = d8;
               d5 = d9;
               d6 = d10;
               box = box6;
            }
         }

         realPosX = (box.minX + box.maxX) / 2.0;
         realPosY = box.minY;
         realPosZ = (box.minZ + box.maxZ) / 2.0;
         isCollidedHorizontally = d != d4 || d2 != d6;
         isCollidedVertically = d7 != d5;
         isLikelyOnGround = isCollidedVertically && d7 < 0.0;
         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag3 = false;
         } else {
         }

         int n2 = MathHelper.floor_double(realPosX);
         int n3 = MathHelper.floor_double(realPosY - 0.20000000298023224);
         int n4 = MathHelper.floor_double(realPosZ);
         BlockPos pos = new BlockPos(n2, n3, n4);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (d != d4) {
            d = 0.0;
         }

         if (d2 != d6) {
            d2 = 0.0;
         }

         if (d7 != d5) {
            if (block instanceof BlockSlime) {
               if (entity.isSneaking()) {
                  d3 = 0.0;
               } else if (d3 < 0.0) {
                  d3 = -d3;
               }
            } else {
               d3 = 0.0;
            }
         }

         if (mc.theWorld.isRemote
            && (
               !mc.theWorld.isBlockLoaded(new BlockPos((int)realPosX, 0, (int)realPosZ))
                  || !mc.theWorld.getChunkFromBlockCoords(new BlockPos((int)realPosX, 0, (int)realPosZ)).isLoaded()
            )) {
            if (realPosY > 0.0) {
               d3 = -0.1;
            } else {
               d3 = 0.0;
            }
         } else {
            d3 -= 0.08;
         }

         d3 *= 0.9800000190734863;
         if (entity instanceof AbstractClientPlayer && vec != null) {
            d *= (double)f2;
            d2 *= (double)f2;
         }
      }

      return new double[]{realPosX, realPosY, realPosZ, predictedPosX, predictedPosY, predictedPosZ, d, d3, d2, isLikelyOnGround ? 1.0 : 0.0};
   }

   public static ArrayList simulatePlayerSnapshotsHistory(EntityPlayerSP player, ArrayList list, PlayerTickSnapshot playerTickSnapshot, int n, boolean flag) {
      PlayerTickSnapshot playerTickSnapshot2 = playerTickSnapshot;
      double xCoord = playerTickSnapshot.position.xCoord;
      double yCoord = playerTickSnapshot.position.yCoord;
      double zCoord = playerTickSnapshot.position.zCoord;
      double xCoord2 = playerTickSnapshot.motion.xCoord;
      double yCoord2 = playerTickSnapshot.motion.yCoord;
      double zCoord2 = playerTickSnapshot.motion.zCoord;
      boolean jumping = playerTickSnapshot.jumping;
      double prevPosX = player.prevPosX;
      double prevPosY = player.prevPosY;
      double prevPosZ = player.prevPosZ;
      boolean onGround = playerTickSnapshot.onGround;
      float moveForward = playerTickSnapshot.moveForward;
      float moveStrafe = playerTickSnapshot.moveStrafe;
      AxisAlignedBB boundingBox = playerTickSnapshot.boundingBox;
      AxisAlignedBB box = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      boolean isCollidedHorizontally = player.isCollidedHorizontally;
      boolean isCollidedVertically = player.isCollidedVertically;
      boolean sprinting = playerTickSnapshot.sprinting;
      float fallDistance = player.fallDistance;
      ArrayList list2 = new ArrayList();

      for (int i = 0; i <= n; i++) {
         if (!list.isEmpty()) {
            playerTickSnapshot2 = (PlayerTickSnapshot)list.get(Math.min(list.size() - 1, i));
         }

         prevPosX = xCoord;
         prevPosY = yCoord;
         prevPosZ = zCoord;
         moveForward = playerTickSnapshot2.moveForward;
         moveStrafe = playerTickSnapshot2.moveStrafe;
         sprinting = playerTickSnapshot2.sprinting;
         jumping = playerTickSnapshot2.jumping;
         boolean sneaking = playerTickSnapshot2.sneaking;
         EventSlowDown event = SlowDownListener.onSlowDown(0.2F, 0.2F);
         if (player.isUsingItem() && !player.isRiding()) {
            moveForward *= event.getStrafeMultiplier();
            moveStrafe *= event.getForwardMultiplier();
         }

         double motionZeroThreshold = ViaAugustus.getMotionZeroThreshold();
         if (Math.abs(xCoord2) < motionZeroThreshold) {
            xCoord2 = 0.0;
         }

         if (Math.abs(yCoord2) < motionZeroThreshold) {
            yCoord2 = 0.0;
         }

         if (Math.abs(zCoord2) < motionZeroThreshold) {
            zCoord2 = 0.0;
         }

         if (jumping && onGround) {
            yCoord2 = (double)player.getJumpUpwardsMotion();
            if (player.isPotionActive(Potion.jump)) {
               yCoord2 += (double)((float)(player.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
            }

            if (sprinting) {
               float f = playerTickSnapshot2.yaw * 0.017453292F;
               xCoord2 -= (double)(MathHelper.sin(f) * 0.2F);
               zCoord2 += (double)(MathHelper.cos(f) * 0.2F);
            }
         }

         moveForward *= 0.98F;
         moveStrafe *= 0.98F;
         float f2 = 0.91F;
         if (onGround) {
            f2 = player.entityWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(xCoord), MathHelper.floor_double(yCoord) - 1, MathHelper.floor_double(zCoord)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         float f3 = 0.16277136F / (f2 * f2 * f2);
         float f4;
         if (onGround) {
            f4 = playerTickSnapshot2.aiMoveSpeed * f3;
         } else {
            f4 = player.getSpeedInAir();
            if (sprinting) {
               f4 = (float)((double)f4 + (double)player.getSpeedInAir() * 0.3);
            }
         }

         float f5 = moveForward * moveForward + moveStrafe * moveStrafe;
         if (f5 >= 1.0E-4F) {
            f5 = MathHelper.sqrt_float(f5);
            if (f5 < 1.0F) {
               f5 = 1.0F;
            }

            f5 = f4 / f5;
            moveForward *= f5;
            moveStrafe *= f5;
            float f6 = MathHelper.sin(playerTickSnapshot2.yaw * 3.1415927F / 180.0F);
            float f7 = MathHelper.cos(playerTickSnapshot2.yaw * 3.1415927F / 180.0F);
            xCoord2 += (double)(moveForward * f7 - moveStrafe * f6);
            zCoord2 += (double)(moveStrafe * f7 + moveForward * f6);
         }

         f2 = 0.91F;
         if (onGround) {
            f2 = mc.theWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(xCoord), MathHelper.floor_double(yCoord) - 1, MathHelper.floor_double(zCoord)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         double d = xCoord2;
         double d2 = yCoord2;
         double d3 = zCoord2;
         double d4 = xCoord2;
         double d5 = zCoord2;
         boolean flag2 = sneaking && !flag;
         if (onGround && flag2) {
            double d6;
            for (d6 = 0.05; d != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(player, box.offset(d, -1.0, 0.0)).isEmpty(); d4 = d) {
               if (d < d6 && d >= -d6) {
                  d = 0.0;
               } else if (d > 0.0) {
                  d -= d6;
               } else {
                  d += d6;
               }
            }

            for (; d3 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(player, box.offset(0.0, -1.0, d3)).isEmpty(); d5 = d3) {
               if (d3 < d6 && d3 >= -d6) {
                  d3 = 0.0;
               } else if (d3 > 0.0) {
                  d3 -= d6;
               } else {
                  d3 += d6;
               }
            }

            for (; d != 0.0 && d3 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(player, box.offset(d, -1.0, d3)).isEmpty(); d5 = d3) {
               if (d < d6 && d >= -d6) {
                  d = 0.0;
               } else if (d > 0.0) {
                  d -= d6;
               } else {
                  d += d6;
               }

               d4 = d;
               if (d3 < d6 && d3 >= -d6) {
                  d3 = 0.0;
               } else if (d3 > 0.0) {
                  d3 -= d6;
               } else {
                  d3 += d6;
               }
            }
         }

         List list3 = mc.theWorld.getCollidingBoundingBoxesCapped(player, box.addCoord(d, yCoord2, d3));
         AxisAlignedBB box2 = box;

         for (AxisAlignedBB box3 : (Iterable<AxisAlignedBB>)(list3)) {
            d2 = box3.calculateYOffset(box, d2);
         }

         box = box.offset(0.0, d2, 0.0);
         boolean flag4 = onGround || yCoord2 != d2 && yCoord2 < 0.0;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list3)) {
            d = box4.calculateXOffset(box, d);
         }

         box = box.offset(d, 0.0, 0.0);

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list3)) {
            d3 = box5.calculateZOffset(box, d3);
         }

         box = box.offset(0.0, 0.0, d3);
         EventStep event2 = StepListener.onStep(player.stepHeight, StateTypes.PRE);
         if (event2.getStepHeight() > 0.0F && flag4 && (d4 != d || d5 != d3)) {
            double d7 = d;
            double d8 = d2;
            double d9 = d3;
            AxisAlignedBB box6 = box;
            d2 = (double)event2.getStepHeight();
            List list4 = mc.theWorld.getCollidingBoundingBoxesCapped(player, box2.addCoord(d4, d2, d5));
            AxisAlignedBB box7 = box2.addCoord(d4, 0.0, d5);
            double d10 = d2;

            for (AxisAlignedBB box8 : (Iterable<AxisAlignedBB>)(list4)) {
               d10 = box8.calculateYOffset(box7, d10);
            }

            AxisAlignedBB box9 = box2.offset(0.0, d10, 0.0);
            double d11 = d4;

            for (AxisAlignedBB box10 : (Iterable<AxisAlignedBB>)(list4)) {
               d11 = box10.calculateXOffset(box9, d11);
            }

            box9 = box9.offset(d11, 0.0, 0.0);
            double d12 = d5;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list4)) {
               d12 = box11.calculateZOffset(box9, d12);
            }

            box9 = box9.offset(0.0, 0.0, d12);
            AxisAlignedBB box12 = box2;
            double d13 = d2;

            for (AxisAlignedBB box13 : (Iterable<AxisAlignedBB>)(list4)) {
               d13 = box13.calculateYOffset(box12, d13);
            }

            box12 = box12.offset(0.0, d13, 0.0);
            double d14 = d4;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list4)) {
               d14 = box14.calculateXOffset(box12, d14);
            }

            box12 = box12.offset(d14, 0.0, 0.0);
            double d15 = d5;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list4)) {
               d15 = box15.calculateZOffset(box12, d15);
            }

            box12 = box12.offset(0.0, 0.0, d15);
            if ((d11 * d11 + d12 * d12) > (d14 * d14 + d15 * d15)) {
               d = d11;
               d3 = d12;
               d2 = -d10;
               box = box9;
            } else {
               d = d14;
               d3 = d15;
               d2 = -d13;
               box = box12;
            }

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list4)) {
               d2 = box16.calculateYOffset(box, d2);
            }

            box = box.offset(0.0, d2, 0.0);
            if (d7 * d7 + d9 * d9 >= d * d + d3 * d3) {
               d = d7;
               d2 = d8;
               d3 = d9;
               box = box6;
            }
         }

         xCoord = (box.minX + box.maxX) / 2.0;
         yCoord = box.minY;
         zCoord = (box.minZ + box.maxZ) / 2.0;
         isCollidedHorizontally = d4 != d || d5 != d3;
         isCollidedVertically = yCoord2 != d2;
         onGround = isCollidedVertically && yCoord2 < 0.0;
         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag5 = false;
         } else {
         }

         int n2 = MathHelper.floor_double(xCoord);
         int n3 = MathHelper.floor_double(yCoord - 0.20000000298023224);
         int n4 = MathHelper.floor_double(zCoord);
         BlockPos pos = new BlockPos(n2, n3, n4);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (onGround) {
            if (fallDistance > 0.0F) {
               fallDistance = 0.0F;
            }
         } else if (d2 < 0.0) {
            fallDistance = (float)((double)fallDistance - d2);
         }

         if (d4 != d) {
            xCoord2 = 0.0;
         }

         if (d5 != d3) {
            zCoord2 = 0.0;
         }

         if (yCoord2 != d2) {
            if (block instanceof BlockSlime) {
               if (playerTickSnapshot2.sneaking) {
                  yCoord2 = 0.0;
               } else if (yCoord2 < 0.0) {
                  yCoord2 = -yCoord2;
               }
            } else {
               yCoord2 = 0.0;
            }
         }

         if (player.entityWorld.isRemote
            && (
               !player.entityWorld.isBlockLoaded(new BlockPos((int)xCoord, 0, (int)zCoord))
                  || !player.entityWorld.getChunkFromBlockCoords(new BlockPos((int)xCoord, 0, (int)zCoord)).isLoaded()
            )) {
            if (yCoord > 0.0) {
               yCoord2 = -0.1;
            } else {
               yCoord2 = 0.0;
            }
         } else {
            yCoord2 -= 0.08;
         }

         yCoord2 *= 0.9800000190734863;
         xCoord2 *= (double)f2;
         zCoord2 *= (double)f2;
         list2.add(new double[]{xCoord, yCoord, zCoord, prevPosX, prevPosY, prevPosZ, xCoord2, yCoord2, zCoord2, onGround ? 1.0 : 0.0, isCollidedHorizontally ? 1.0 : 0.0, (double)fallDistance});
      }

      return list2;
   }

   public static double[] predictPlayerPosition(int n, EntityPlayerSP player, boolean flag) {
      return predictPlayerPosition(n, player, player.rotationYaw, flag);
   }

   public static double[] simulatePlayerSnapshotsFinal(EntityPlayerSP player, ArrayList list, int n, boolean flag) {
      PlayerTickSnapshot playerTickSnapshot = (PlayerTickSnapshot)list.get(0);
      double xCoord = playerTickSnapshot.position.xCoord;
      double yCoord = playerTickSnapshot.position.yCoord;
      double zCoord = playerTickSnapshot.position.zCoord;
      double xCoord2 = playerTickSnapshot.motion.xCoord;
      double yCoord2 = playerTickSnapshot.motion.yCoord;
      double zCoord2 = playerTickSnapshot.motion.zCoord;
      boolean jumping = playerTickSnapshot.jumping;
      double prevPosX = player.prevPosX;
      double prevPosY = player.prevPosY;
      double prevPosZ = player.prevPosZ;
      boolean onGround = playerTickSnapshot.onGround;
      float moveForward = playerTickSnapshot.moveForward;
      float moveStrafe = playerTickSnapshot.moveStrafe;
      AxisAlignedBB boundingBox = playerTickSnapshot.boundingBox;
      AxisAlignedBB box = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      boolean isCollidedHorizontally = player.isCollidedHorizontally;
      boolean isCollidedVertically = player.isCollidedVertically;
      boolean sprinting = playerTickSnapshot.sprinting;
      float fallDistance = player.fallDistance;

      for (int i = 0; i <= n; i++) {
         playerTickSnapshot = (PlayerTickSnapshot)list.get(Math.min(list.size() - 1, i));
         prevPosX = xCoord;
         prevPosY = yCoord;
         prevPosZ = zCoord;
         moveForward = playerTickSnapshot.moveForward;
         moveStrafe = playerTickSnapshot.moveStrafe;
         sprinting = playerTickSnapshot.sprinting;
         jumping = playerTickSnapshot.jumping;
         boolean sneaking = playerTickSnapshot.sneaking;
         EventSlowDown event = SlowDownListener.onSlowDown(0.2F, 0.2F);
         if (player.isUsingItem() && !player.isRiding()) {
            moveForward *= event.getStrafeMultiplier();
            moveStrafe *= event.getForwardMultiplier();
         }

         double motionZeroThreshold = ViaAugustus.getMotionZeroThreshold();
         if (Math.abs(xCoord2) < motionZeroThreshold) {
            xCoord2 = 0.0;
         }

         if (Math.abs(yCoord2) < motionZeroThreshold) {
            yCoord2 = 0.0;
         }

         if (Math.abs(zCoord2) < motionZeroThreshold) {
            zCoord2 = 0.0;
         }

         if (jumping && onGround) {
            yCoord2 = (double)player.getJumpUpwardsMotion();
            if (player.isPotionActive(Potion.jump)) {
               yCoord2 += (double)((float)(player.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
            }

            if (sprinting) {
               float f = playerTickSnapshot.yaw * 0.017453292F;
               xCoord2 -= (double)(MathHelper.sin(f) * 0.2F);
               zCoord2 += (double)(MathHelper.cos(f) * 0.2F);
            }
         }

         moveForward *= 0.98F;
         moveStrafe *= 0.98F;
         float f2 = 0.91F;
         if (onGround) {
            f2 = player.entityWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(xCoord), MathHelper.floor_double(yCoord) - 1, MathHelper.floor_double(zCoord)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         float f3 = 0.16277136F / (f2 * f2 * f2);
         float f4;
         if (onGround) {
            f4 = playerTickSnapshot.aiMoveSpeed * f3;
         } else {
            f4 = player.getSpeedInAir();
            if (sprinting) {
               f4 = (float)((double)f4 + (double)player.getSpeedInAir() * 0.3);
            }
         }

         float f5 = moveForward * moveForward + moveStrafe * moveStrafe;
         if (f5 >= 1.0E-4F) {
            f5 = MathHelper.sqrt_float(f5);
            if (f5 < 1.0F) {
               f5 = 1.0F;
            }

            f5 = f4 / f5;
            moveForward *= f5;
            moveStrafe *= f5;
            float f6 = MathHelper.sin(playerTickSnapshot.yaw * 3.1415927F / 180.0F);
            float f7 = MathHelper.cos(playerTickSnapshot.yaw * 3.1415927F / 180.0F);
            xCoord2 += (double)(moveForward * f7 - moveStrafe * f6);
            zCoord2 += (double)(moveStrafe * f7 + moveForward * f6);
         }

         f2 = 0.91F;
         if (onGround) {
            f2 = mc.theWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(xCoord), MathHelper.floor_double(yCoord) - 1, MathHelper.floor_double(zCoord)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         double d = xCoord2;
         double d2 = yCoord2;
         double d3 = zCoord2;
         double d4 = xCoord2;
         double d5 = zCoord2;
         boolean flag2 = sneaking && !flag;
         if (onGround && flag2) {
            double d6;
            for (d6 = 0.05; d != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(player, box.offset(d, -1.0, 0.0)).isEmpty(); d4 = d) {
               if (d < d6 && d >= -d6) {
                  d = 0.0;
               } else if (d > 0.0) {
                  d -= d6;
               } else {
                  d += d6;
               }
            }

            for (; d3 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(player, box.offset(0.0, -1.0, d3)).isEmpty(); d5 = d3) {
               if (d3 < d6 && d3 >= -d6) {
                  d3 = 0.0;
               } else if (d3 > 0.0) {
                  d3 -= d6;
               } else {
                  d3 += d6;
               }
            }

            for (; d != 0.0 && d3 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(player, box.offset(d, -1.0, d3)).isEmpty(); d5 = d3) {
               if (d < d6 && d >= -d6) {
                  d = 0.0;
               } else if (d > 0.0) {
                  d -= d6;
               } else {
                  d += d6;
               }

               d4 = d;
               if (d3 < d6 && d3 >= -d6) {
                  d3 = 0.0;
               } else if (d3 > 0.0) {
                  d3 -= d6;
               } else {
                  d3 += d6;
               }
            }
         }

         List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(player, box.addCoord(d, yCoord2, d3));
         AxisAlignedBB box2 = box;

         for (AxisAlignedBB box3 : (Iterable<AxisAlignedBB>)(list2)) {
            d2 = box3.calculateYOffset(box, d2);
         }

         box = box.offset(0.0, d2, 0.0);
         boolean flag4 = onGround || yCoord2 != d2 && yCoord2 < 0.0;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list2)) {
            d = box4.calculateXOffset(box, d);
         }

         box = box.offset(d, 0.0, 0.0);

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list2)) {
            d3 = box5.calculateZOffset(box, d3);
         }

         box = box.offset(0.0, 0.0, d3);
         EventStep event2 = StepListener.onStep(player.stepHeight, StateTypes.PRE);
         if (event2.getStepHeight() > 0.0F && flag4 && (d4 != d || d5 != d3)) {
            double d7 = d;
            double d8 = d2;
            double d9 = d3;
            AxisAlignedBB box6 = box;
            d2 = (double)event2.getStepHeight();
            List list3 = mc.theWorld.getCollidingBoundingBoxesCapped(player, box2.addCoord(d4, d2, d5));
            AxisAlignedBB box7 = box2.addCoord(d4, 0.0, d5);
            double d10 = d2;

            for (AxisAlignedBB box8 : (Iterable<AxisAlignedBB>)(list3)) {
               d10 = box8.calculateYOffset(box7, d10);
            }

            AxisAlignedBB box9 = box2.offset(0.0, d10, 0.0);
            double d11 = d4;

            for (AxisAlignedBB box10 : (Iterable<AxisAlignedBB>)(list3)) {
               d11 = box10.calculateXOffset(box9, d11);
            }

            box9 = box9.offset(d11, 0.0, 0.0);
            double d12 = d5;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list3)) {
               d12 = box11.calculateZOffset(box9, d12);
            }

            box9 = box9.offset(0.0, 0.0, d12);
            AxisAlignedBB box12 = box2;
            double d13 = d2;

            for (AxisAlignedBB box13 : (Iterable<AxisAlignedBB>)(list3)) {
               d13 = box13.calculateYOffset(box12, d13);
            }

            box12 = box12.offset(0.0, d13, 0.0);
            double d14 = d4;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list3)) {
               d14 = box14.calculateXOffset(box12, d14);
            }

            box12 = box12.offset(d14, 0.0, 0.0);
            double d15 = d5;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list3)) {
               d15 = box15.calculateZOffset(box12, d15);
            }

            box12 = box12.offset(0.0, 0.0, d15);
            if ((d11 * d11 + d12 * d12) > (d14 * d14 + d15 * d15)) {
               d = d11;
               d3 = d12;
               d2 = -d10;
               box = box9;
            } else {
               d = d14;
               d3 = d15;
               d2 = -d13;
               box = box12;
            }

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list3)) {
               d2 = box16.calculateYOffset(box, d2);
            }

            box = box.offset(0.0, d2, 0.0);
            if (d7 * d7 + d9 * d9 >= d * d + d3 * d3) {
               d = d7;
               d2 = d8;
               d3 = d9;
               box = box6;
            }
         }

         xCoord = (box.minX + box.maxX) / 2.0;
         yCoord = box.minY;
         zCoord = (box.minZ + box.maxZ) / 2.0;
         isCollidedHorizontally = d4 != d || d5 != d3;
         isCollidedVertically = yCoord2 != d2;
         onGround = isCollidedVertically && yCoord2 < 0.0;
         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag5 = false;
         } else {
         }

         int n2 = MathHelper.floor_double(xCoord);
         int n3 = MathHelper.floor_double(yCoord - 0.20000000298023224);
         int n4 = MathHelper.floor_double(zCoord);
         BlockPos pos = new BlockPos(n2, n3, n4);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (onGround) {
            if (fallDistance > 0.0F) {
               fallDistance = 0.0F;
            }
         } else if (d2 < 0.0) {
            fallDistance = (float)((double)fallDistance - d2);
         }

         if (d4 != d) {
            xCoord2 = 0.0;
         }

         if (d5 != d3) {
            zCoord2 = 0.0;
         }

         if (yCoord2 != d2) {
            if (block instanceof BlockSlime) {
               if (playerTickSnapshot.sneaking) {
                  yCoord2 = 0.0;
               } else if (yCoord2 < 0.0) {
                  yCoord2 = -yCoord2;
               }
            } else {
               yCoord2 = 0.0;
            }
         }

         if (player.entityWorld.isRemote
            && (
               !player.entityWorld.isBlockLoaded(new BlockPos((int)xCoord, 0, (int)zCoord))
                  || !player.entityWorld.getChunkFromBlockCoords(new BlockPos((int)xCoord, 0, (int)zCoord)).isLoaded()
            )) {
            if (yCoord > 0.0) {
               yCoord2 = -0.1;
            } else {
               yCoord2 = 0.0;
            }
         } else {
            yCoord2 -= 0.08;
         }

         yCoord2 *= 0.9800000190734863;
         xCoord2 *= (double)f2;
         zCoord2 *= (double)f2;
      }

      return new double[]{xCoord, yCoord, zCoord, prevPosX, prevPosY, prevPosZ, xCoord2, yCoord2, zCoord2, onGround ? 1.0 : 0.0, isCollidedHorizontally ? 1.0 : 0.0, (double)fallDistance};
   }

}
