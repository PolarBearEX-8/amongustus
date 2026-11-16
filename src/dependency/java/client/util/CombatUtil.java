package client.util;

import client.event.EventCameraTransform;
import client.event.EventCameraTransformCallback;
import client.event.EventChat;
import client.event.EventChatCallback;
import client.event.EventMove;
import client.event.EventRotation;
import client.event.EventTickState;
import client.event.EventTickStateCallback;
import client.event.RotationListener;
import client.render.BoundingBoxDimension;
import client.setting.ArrowDodgerPhase;
import client.setting.BugUpMoveFlyingType;
import client.setting.DisablerUndefinedType;
import client.setting.ModeSetting;
import client.setting.RenderKeybindProperty;
import client.setting.WorldSettingsGameType;
import client.via.ViaLoadingBase;
import de.florianmichael.dietrichevents2.StateTypes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAcaciaFence;
import net.minecraft.block.BlockAcaciaFenceGate;
import net.minecraft.block.BlockCobblestoneWall;
import net.minecraft.block.BlockSlime;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class CombatUtil implements MinecraftAccessor {
   public static final double[] valueArray = new double[]{
      0.42F,
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

   public static double[] onMoveByEntityPlayerSP(
      int count, EntityPlayerSP player, double posX, double posY, double posZ, float factor, EventMove event, boolean flag
   ) {
      double offset = posX;
      double offset2 = posY;
      double offset3 = posZ;
      double motionX = player.motionX;
      double motionY = player.motionY;
      double motionZ = player.motionZ;
      boolean state = event.isState();
      double prevPosX = player.prevPosX;
      double prevPosY = player.prevPosY;
      double prevPosZ = player.prevPosZ;
      int indexOffset = player.getIndexOffset18();
      boolean onGround = player.onGround;
      event.getScaleFactor2();
      event.getScaleFactor7();
      AxisAlignedBB boundingBox = player.getBoundingBox2();
      AxisAlignedBB boundingBox2 = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      boolean isCollidedHorizontally = player.isCollidedHorizontally;
      boolean riding = player.isRiding();
      float fallDistance = player.fallDistance;

      for (int index = 0; index <= count; index++) {
         prevPosX = offset;
         prevPosY = offset2;
         prevPosZ = offset3;
         float scaleFactor = event.getScaleFactor2();
         float scaleFactor2 = event.getScaleFactor7();
         EventCameraTransform eventCameraTransform = EventCameraTransformCallback.getEventCameraTransform(0.2F, 0.2F);
         if (player.isUsingItem() && !player.isSneaking()) {
            scaleFactor *= eventCameraTransform.getScaleFactor2();
            scaleFactor2 *= eventCameraTransform.getScaleFactor();
         }

         if (indexOffset > 0) {
            indexOffset--;
         }

         double offset4 = ViaLoadingBase.processAction2();
         if (Math.abs(motionX) < offset4) {
            motionX = 0.0;
         }

         if (Math.abs(motionY) < offset4) {
            motionY = 0.0;
         }

         if (Math.abs(motionZ) < offset4) {
            motionZ = 0.0;
         }

         if (state) {
            if (onGround && indexOffset == 0) {
               motionY = (double)player.getFloat6();
               if (player.checkPotion(Potion.potion9)) {
                  motionY += (double)((float)(player.getPotionEffect(Potion.potion9).getIndexOffset2() + 1) * 0.1F);
               }

               if (riding) {
                  float factor2 = factor * (float) (Math.PI / 180.0);
                  motionX -= (double)(MathHelper.abs(factor2) * 0.2F);
                  motionZ += (double)(MathHelper.cos(factor2) * 0.2F);
               }

               indexOffset = 10;
            }
         } else {
            indexOffset = 0;
         }

         float factor3 = scaleFactor * 0.98F;
         float factor4 = scaleFactor2 * 0.98F;
         float factor5 = 0.91F;
         if (onGround) {
            factor5 = player.worldObj
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(offset), MathHelper.ceiling_double_int(offset2) - 1, MathHelper.ceiling_double_int(offset3))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         float factor6 = 0.16277136F / (factor5 * factor5 * factor5);
         float factor7;
         if (onGround) {
            factor7 = player.getScaleFactor6() * factor6;
         } else {
            factor7 = player.scaleFactor10;
         }

         EventRotation event2 = RotationListener.resolveEventRotation(factor, factor4, factor3, factor7, BugUpMoveFlyingType.MOVE_FLYING);
         float factor8 = factor3 * factor3 + factor4 * factor4;
         if (factor8 >= 1.0E-4F) {
            float factor9 = MathHelper.sin(factor8);
            if (factor9 < 1.0F) {
               factor9 = 1.0F;
            }

            float factor10 = factor7 / factor9;
            float factor11 = factor3 * factor10;
            float factor12 = factor4 * factor10;
            float factor13 = MathHelper.abs(event2.getScaleFactor() * (float) Math.PI / 180.0F);
            float factor14 = MathHelper.cos(event2.getScaleFactor() * (float) Math.PI / 180.0F);
            motionX += (double)(factor11 * factor14 - factor12 * factor13);
            motionZ += (double)(factor12 * factor14 + factor11 * factor13);
         }

         float factor15 = 0.91F;
         if (onGround) {
            factor15 = MinecraftAccessor.minecraft
                  .theWorld
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(offset), MathHelper.ceiling_double_int(offset2) - 1, MathHelper.ceiling_double_int(offset3))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         double offset5 = motionX;
         double offset6 = motionY;
         double offset7 = motionZ;
         double offset8 = motionX;
         double offset9 = motionZ;
         boolean flag2 = player.isSprinting() && !flag;
         if (onGround && flag2) {
            double offset10;
            for (offset10 = 0.05;
               offset5 != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox2.expand(offset5, -1.0, 0.0)).isEmpty();
               offset8 = offset5
            ) {
               if (offset5 < offset10 && offset5 >= -offset10) {
                  offset5 = 0.0;
               } else if (offset5 > 0.0) {
                  offset5 -= offset10;
               } else {
                  offset5 += offset10;
               }
            }

            for (;
               offset7 != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox2.expand(0.0, -1.0, offset7)).isEmpty();
               offset9 = offset7
            ) {
               if (offset7 < offset10 && offset7 >= -offset10) {
                  offset7 = 0.0;
               } else if (offset7 > 0.0) {
                  offset7 -= offset10;
               } else {
                  offset7 += offset10;
               }
            }

            for (;
               offset5 != 0.0 && offset7 != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox2.expand(offset5, -1.0, offset7)).isEmpty();
               offset9 = offset7
            ) {
               if (offset5 < offset10 && offset5 >= -offset10) {
                  offset5 = 0.0;
               } else if (offset5 > 0.0) {
                  offset5 -= offset10;
               } else {
                  offset5 += offset10;
               }

               offset8 = offset5;
               if (offset7 < offset10 && offset7 >= -offset10) {
                  offset7 = 0.0;
               } else if (offset7 > 0.0) {
                  offset7 -= offset10;
               } else {
                  offset7 += offset10;
               }
            }
         }

         List collidingBoundingBoxes = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox2.contract(offset5, motionY, offset7));
         AxisAlignedBB boundingBox3 = boundingBox2;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset6 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox2, offset6);
         }

         AxisAlignedBB boundingBox4 = boundingBox2.expand(0.0, offset6, 0.0);
         boolean flag3 = onGround || motionY != offset6 && motionY < 0.0;
         Iterator iterator2 = collidingBoundingBoxes.iterator();

         while (iterator2.hasNext()) {
            offset5 = ((AxisAlignedBB)iterator2.next()).getCoordinate2(boundingBox4, offset5);
         }

         AxisAlignedBB boundingBox5 = boundingBox4.expand(offset5, 0.0, 0.0);
         Iterator iterator3 = collidingBoundingBoxes.iterator();

         while (iterator3.hasNext()) {
            offset7 = ((AxisAlignedBB)iterator3.next()).getCoordinate(boundingBox5, offset7);
         }

         boundingBox2 = boundingBox5.expand(0.0, 0.0, offset7);
         EventTickState eventTickState = EventTickStateCallback.getEventTickState(player.stepHeight, StateTypes.PRE);
         if (eventTickState.getScaleFactor() > 0.0F && flag3 && (offset8 != offset5 || offset9 != offset7)) {
            double offset11 = offset5;
            double offset12 = offset6;
            double offset13 = offset7;
            AxisAlignedBB boundingBox6 = boundingBox2;
            double scaleFactor3 = (double)eventTickState.getScaleFactor();
            List collidingBoundingBoxes2 = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox3.contract(offset8, scaleFactor3, offset9));
            AxisAlignedBB boundingBox7 = boundingBox3.contract(offset8, 0.0, offset9);
            double offset14 = scaleFactor3;
            Iterator iterator4 = collidingBoundingBoxes2.iterator();

            while (iterator4.hasNext()) {
               offset14 = ((AxisAlignedBB)iterator4.next()).getCoordinate3(boundingBox7, offset14);
            }

            AxisAlignedBB boundingBox8 = boundingBox3.expand(0.0, offset14, 0.0);
            offset5 = offset8;
            Iterator iterator5 = collidingBoundingBoxes2.iterator();

            while (iterator5.hasNext()) {
               offset5 = ((AxisAlignedBB)iterator5.next()).getCoordinate2(boundingBox8, offset5);
            }

            AxisAlignedBB boundingBox9 = boundingBox8.expand(offset5, 0.0, 0.0);
            offset7 = offset9;
            Iterator iterator6 = collidingBoundingBoxes2.iterator();

            while (iterator6.hasNext()) {
               offset7 = ((AxisAlignedBB)iterator6.next()).getCoordinate(boundingBox9, offset7);
            }

            AxisAlignedBB boundingBox10 = boundingBox9.expand(0.0, 0.0, offset7);
            AxisAlignedBB boundingBox11 = boundingBox3;
            Iterator iterator7 = collidingBoundingBoxes2.iterator();

            while (iterator7.hasNext()) {
               scaleFactor3 = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox11, scaleFactor3);
            }

            AxisAlignedBB boundingBox12 = boundingBox11.expand(0.0, scaleFactor3, 0.0);
            double offset15 = offset8;
            Iterator iterator8 = collidingBoundingBoxes2.iterator();

            while (iterator8.hasNext()) {
               offset15 = ((AxisAlignedBB)iterator8.next()).getCoordinate2(boundingBox12, offset15);
            }

            AxisAlignedBB boundingBox13 = boundingBox12.expand(offset15, 0.0, 0.0);
            double offset16 = offset9;
            Iterator iterator9 = collidingBoundingBoxes2.iterator();

            while (iterator9.hasNext()) {
               offset16 = ((AxisAlignedBB)iterator9.next()).getCoordinate(boundingBox13, offset16);
            }

            AxisAlignedBB boundingBox14 = boundingBox13.expand(0.0, 0.0, offset16);
            double offset17 = offset5 * offset5 + offset7 * offset7;
            double offset18 = offset15 * offset15 + offset16 * offset16;
            AxisAlignedBB boundingBox15;
            if (offset17 > offset18) {
               offset6 = -offset14;
               boundingBox15 = boundingBox10;
            } else {
               offset5 = offset15;
               offset7 = offset16;
               offset6 = -scaleFactor3;
               boundingBox15 = boundingBox14;
            }

            Iterator iterator10 = collidingBoundingBoxes2.iterator();

            while (iterator10.hasNext()) {
               offset6 = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox15, offset6);
            }

            boundingBox2 = boundingBox15.expand(0.0, offset6, 0.0);
            if (offset11 * offset11 + offset13 * offset13 >= offset5 * offset5 + offset7 * offset7) {
               offset5 = offset11;
               offset6 = offset12;
               offset7 = offset13;
               boundingBox2 = boundingBox6;
            }
         }

         offset = (boundingBox2.minX + boundingBox2.maxX) / 2.0;
         offset2 = boundingBox2.minY;
         offset3 = (boundingBox2.minZ + boundingBox2.maxZ) / 2.0;
         isCollidedHorizontally = offset8 != offset5 || offset9 != offset7;
         boolean flag4 = motionY != offset6;
         onGround = flag4 && motionY < 0.0;
         if (!isCollidedHorizontally && !flag4) {
         } else {
         }

         int count2 = MathHelper.ceiling_double_int(offset);
         int count3 = MathHelper.ceiling_double_int(offset2 - 0.2F);
         int count4 = MathHelper.ceiling_double_int(offset3);
         BlockPos blockPos = new BlockPos(count2, count3, count4);
         Block block = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (onGround) {
            if (fallDistance > 0.0F) {
               fallDistance = 0.0F;
            }
         } else if (offset6 < 0.0) {
            fallDistance = (float)((double)fallDistance - offset6);
         }

         if (offset8 != offset5) {
            motionX = 0.0;
         }

         if (offset9 != offset7) {
            motionZ = 0.0;
         }

         if (motionY != offset6) {
            if (block instanceof BlockSlime) {
               if (player.isSprinting()) {
                  motionY = 0.0;
               } else if (motionY < 0.0) {
                  motionY = -motionY;
               }
            } else {
               motionY = 0.0;
            }
         }

         double offset19;
         if (player.worldObj.state
            && (
               !player.worldObj.checkBlockPos12(new BlockPos((int)offset, 0, (int)offset3))
                  || !player.worldObj.getLoggerEntry(new BlockPos((int)offset, 0, (int)offset3)).canExecute2()
            )) {
            if (offset2 > 0.0) {
               offset19 = -0.1;
            } else {
               offset19 = 0.0;
            }
         } else {
            offset19 = motionY - 0.08;
         }

         motionY = offset19 * 0.98F;
         motionX *= (double)factor15;
         motionZ *= (double)factor15;
      }

      return new double[]{offset, offset2, offset3, prevPosX, prevPosY, prevPosZ, motionX, motionY, motionZ, onGround ? 1.0 : 0.0, isCollidedHorizontally ? 1.0 : 0.0, (double)fallDistance};
   }

   public static double[] resolveDoubleArrayByEntityLivingBase(int count, int count2, char character, EntityLivingBase livingEntity, int count3) {
      double offset = livingEntity.posX - livingEntity.lastTickPosX;
      double offset2 = livingEntity.posZ - livingEntity.lastTickPosZ;
      double offset3 = livingEntity.posY - livingEntity.lastTickPosY;
      AxisAlignedBB boundingBox = livingEntity.getBoundingBox2();
      AxisAlignedBB boundingBox2 = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      boolean onGround = livingEntity.onGround;
      double value = livingEntity.value6;
      double value2 = livingEntity.value8;
      double value3 = livingEntity.value15;
      if (livingEntity instanceof EntityOtherPlayerMP) {
         EntityOtherPlayerMP player = (EntityOtherPlayerMP)livingEntity;
         value = player.coordOffset3;
         value2 = player.coordOffset1;
         value3 = player.coordOffset4;
      }

      for (int index = 0; index <= count3; index++) {
         offset = livingEntity.posX - livingEntity.lastTickPosX;
         offset2 = livingEntity.posZ - livingEntity.lastTickPosZ;
         double offset4 = offset;
         double offset5 = livingEntity.scaleFactor21 < 75.0F
               && livingEntity instanceof EntityPlayer
               && ((EntityPlayer)livingEntity).getHeldItem() != null
               && ((EntityPlayer)livingEntity).getHeldItem().getItem() instanceof ItemBlock
            ? 0.0
            : offset3;
         double offset6 = offset2;
         double offset7 = offset5;
         if (offset > 10.0 || offset5 > 10.0 || offset2 > 10.0) {
            return new double[]{value, value2, value3, offset, offset3, offset2, onGround ? 1.0 : 0.0};
         }

         List collidingBoundingBoxes = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.contract(offset, offset5, offset2));
         AxisAlignedBB boundingBox3 = boundingBox2;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset5 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox2, offset5);
         }

         AxisAlignedBB boundingBox4 = boundingBox2.expand(0.0, offset5, 0.0);
         boolean flag = onGround || offset7 != offset5 && offset7 < 0.0;
         Iterator iterator2 = collidingBoundingBoxes.iterator();

         while (iterator2.hasNext()) {
            offset4 = ((AxisAlignedBB)iterator2.next()).getCoordinate2(boundingBox4, offset4);
         }

         AxisAlignedBB boundingBox5 = boundingBox4.expand(offset4, 0.0, 0.0);
         Iterator iterator3 = collidingBoundingBoxes.iterator();

         while (iterator3.hasNext()) {
            offset6 = ((AxisAlignedBB)iterator3.next()).getCoordinate(boundingBox5, offset6);
         }

         boundingBox2 = boundingBox5.expand(0.0, 0.0, offset6);
         if (livingEntity.stepHeight > 0.0F && flag && (offset != offset4 || offset2 != offset6)) {
            double offset8 = offset4;
            double offset9 = offset5;
            double offset10 = offset6;
            AxisAlignedBB boundingBox6 = boundingBox2;
            double stepHeight = (double)livingEntity.stepHeight;
            List collidingBoundingBoxes2 = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox3.contract(offset, stepHeight, offset2));
            AxisAlignedBB boundingBox7 = boundingBox3.contract(offset, 0.0, offset2);
            double offset11 = stepHeight;
            Iterator iterator4 = collidingBoundingBoxes2.iterator();

            while (iterator4.hasNext()) {
               offset11 = ((AxisAlignedBB)iterator4.next()).getCoordinate3(boundingBox7, offset11);
            }

            AxisAlignedBB boundingBox8 = boundingBox3.expand(0.0, offset11, 0.0);
            offset4 = offset;
            Iterator iterator5 = collidingBoundingBoxes2.iterator();

            while (iterator5.hasNext()) {
               offset4 = ((AxisAlignedBB)iterator5.next()).getCoordinate2(boundingBox8, offset4);
            }

            AxisAlignedBB boundingBox9 = boundingBox8.expand(offset4, 0.0, 0.0);
            offset6 = offset2;
            Iterator iterator6 = collidingBoundingBoxes2.iterator();

            while (iterator6.hasNext()) {
               offset6 = ((AxisAlignedBB)iterator6.next()).getCoordinate(boundingBox9, offset6);
            }

            AxisAlignedBB boundingBox10 = boundingBox9.expand(0.0, 0.0, offset6);
            AxisAlignedBB boundingBox11 = boundingBox3;
            Iterator iterator7 = collidingBoundingBoxes2.iterator();

            while (iterator7.hasNext()) {
               stepHeight = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox11, stepHeight);
            }

            AxisAlignedBB boundingBox12 = boundingBox11.expand(0.0, stepHeight, 0.0);
            double offset12 = offset;
            Iterator iterator8 = collidingBoundingBoxes2.iterator();

            while (iterator8.hasNext()) {
               offset12 = ((AxisAlignedBB)iterator8.next()).getCoordinate2(boundingBox12, offset12);
            }

            AxisAlignedBB boundingBox13 = boundingBox12.expand(offset12, 0.0, 0.0);
            double offset13 = offset2;
            Iterator iterator9 = collidingBoundingBoxes2.iterator();

            while (iterator9.hasNext()) {
               offset13 = ((AxisAlignedBB)iterator9.next()).getCoordinate(boundingBox13, offset13);
            }

            AxisAlignedBB boundingBox14 = boundingBox13.expand(0.0, 0.0, offset13);
            double offset14 = offset4 * offset4 + offset6 * offset6;
            double offset15 = offset12 * offset12 + offset13 * offset13;
            AxisAlignedBB boundingBox15;
            if (offset14 > offset15) {
               offset5 = -offset11;
               boundingBox15 = boundingBox10;
            } else {
               offset4 = offset12;
               offset6 = offset13;
               offset5 = -stepHeight;
               boundingBox15 = boundingBox14;
            }

            Iterator iterator10 = collidingBoundingBoxes2.iterator();

            while (iterator10.hasNext()) {
               offset5 = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox15, offset5);
            }

            boundingBox2 = boundingBox15.expand(0.0, offset5, 0.0);
            if (offset8 * offset8 + offset10 * offset10 >= offset4 * offset4 + offset6 * offset6) {
               offset4 = offset8;
               offset5 = offset9;
               offset6 = offset10;
               boundingBox2 = boundingBox6;
            }
         }

         value = (boundingBox2.minX + boundingBox2.maxX) / 2.0;
         value2 = boundingBox2.minY;
         value3 = (boundingBox2.minZ + boundingBox2.maxZ) / 2.0;
         boolean flag2 = offset != offset4 || offset2 != offset6;
         boolean flag3 = offset7 != offset5;
         onGround = flag3 && offset7 < 0.0;
         if (!flag2 && !flag3) {
         } else {
         }

         int count4 = MathHelper.ceiling_double_int(value);
         int count5 = MathHelper.ceiling_double_int(value2 - 0.2F);
         int count6 = MathHelper.ceiling_double_int(value3);
         BlockPos blockPos = new BlockPos(count4, count5, count6);
         Block block = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (offset != offset4) {
            offset = 0.0;
         }

         if (offset2 != offset6) {
            offset2 = 0.0;
         }

         if (offset7 != offset5) {
            if (block instanceof BlockSlime) {
               if (livingEntity.isSprinting()) {
                  offset3 = 0.0;
               } else if (offset3 < 0.0) {
                  offset3 = -offset3;
               }
            } else {
               offset3 = 0.0;
            }
         }

         double offset16;
         if (MinecraftAccessor.minecraft.theWorld.state
            && (
               !MinecraftAccessor.minecraft.theWorld.checkBlockPos12(new BlockPos((int)value, 0, (int)value3))
                  || !MinecraftAccessor.minecraft.theWorld.getLoggerEntry(new BlockPos((int)value, 0, (int)value3)).canExecute2()
            )) {
            if (value2 > 0.0) {
               offset16 = -0.1;
            } else {
               offset16 = 0.0;
            }
         } else {
            offset16 = offset3 - 0.08;
         }

         offset3 = offset16 * 0.98F;
      }

      return new double[]{value, value2, value3, offset, offset3, offset2, onGround ? 1.0 : 0.0};
   }

   public static ArrayList onMove(int count, EntityPlayerSP player, double posX, double posY, double posZ, EventMove event) {
      double motionY = player.motionY;
      return onMove(count, player, posX, posY, posZ, motionY, event);
   }

   public static double[] getCoordOffsetArray(int count, EntityPlayerSP player, EntityLivingBase livingEntity) {
      double motionX = player.motionX;
      double motionY = player.motionY;
      double motionZ = player.motionZ;
      boolean state = player.state31;
      double prevPosX = player.prevPosX;
      double prevPosY = player.prevPosY;
      double prevPosZ = player.prevPosZ;
      int indexOffset = player.getIndexOffset18();
      boolean onGround = player.onGround;
      AxisAlignedBB boundingBox = player.getBoundingBox2();
      AxisAlignedBB boundingBox2 = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      boolean isCollidedHorizontally = player.isCollidedHorizontally;
      boolean riding = player.isRiding();
      double posX = player.posX;
      double posY = player.posY;
      double posZ = player.posZ;
      float yaw = player.rotationYaw;

      for (int index = 0; index <= count; index++) {
         if (livingEntity != null) {
            Vec3 vec = new Vec3(livingEntity.posX, livingEntity.posY, livingEntity.posZ);
            Vec3 vec2 = new Vec3(livingEntity.coordOffset5, livingEntity.coordOffset20, livingEntity.coordOffset2);
            double posX2 = vec.xCoord;
            double posY2 = vec.yCoord;
            double posZ2 = vec.zCoord;
            int indexOffset2 = livingEntity.indexOffset23;

            for (int index2 = 0; index2 < count - 1; index2++) {
               posX2 += (vec2.xCoord - posX2) / (double)Math.max(1, indexOffset2);
               posY2 += (vec2.yCoord - posY2) / (double)Math.max(1, indexOffset2);
               posZ2 += (vec2.zCoord - posZ2) / (double)Math.max(1, indexOffset2);
               indexOffset2--;
            }

            Vec3 vec3 = new Vec3(posX, posY + (double)player.getHeight2(), posZ);
            float factor = livingEntity.width / 2.0F;
            float height = livingEntity.getHeight();
            AxisAlignedBB boundingBox3 = new AxisAlignedBB(
                  posX2 - (double)factor, posY2, posZ2 - (double)factor, posX2 + (double)factor, posY2 + (double)livingEntity.height, posZ2 + (double)factor
               )
               .offset((double)height, (double)height, (double)height);
            double doubleItem = MathHelper.getDouble7(vec3.xCoord, boundingBox3.minX, boundingBox3.maxX);
            double doubleItem2 = MathHelper.getDouble7(vec3.zCoord, boundingBox3.minZ, boundingBox3.maxZ);
            double offset = doubleItem - posX;
            yaw = (float)(MathHelper.getDouble6(doubleItem2 - posZ, offset) * 180.0 / Math.PI - 90.0);
         }

         prevPosX = posX;
         prevPosY = posY;
         prevPosZ = posZ;
         float moveStrafe = player.secondaryFloatEntry.moveStrafe;
         float moveForward = player.secondaryFloatEntry.moveForward;
         EventCameraTransform eventCameraTransform = EventCameraTransformCallback.getEventCameraTransform(0.2F, 0.2F);
         if (player.isUsingItem() && !player.isSneaking()) {
            moveStrafe *= eventCameraTransform.getScaleFactor2();
            moveForward *= eventCameraTransform.getScaleFactor();
         }

         if (indexOffset > 0) {
            indexOffset--;
         }

         double offset2 = ViaLoadingBase.processAction2();
         if (Math.abs(motionX) < offset2) {
            motionX = 0.0;
         }

         if (Math.abs(motionY) < offset2) {
            motionY = 0.0;
         }

         if (Math.abs(motionZ) < offset2) {
            motionZ = 0.0;
         }

         if (state) {
            if (onGround && indexOffset == 0) {
               motionY = (double)player.getFloat6();
               if (player.checkPotion(Potion.potion9)) {
                  motionY += (double)((float)(player.getPotionEffect(Potion.potion9).getIndexOffset2() + 1) * 0.1F);
               }

               if (riding) {
                  float factor2 = yaw * (float) (Math.PI / 180.0);
                  motionX -= (double)(MathHelper.abs(factor2) * 0.2F);
                  motionZ += (double)(MathHelper.cos(factor2) * 0.2F);
               }

               indexOffset = 10;
            }
         } else {
            indexOffset = 0;
         }

         float factor3 = moveStrafe * 0.98F;
         float factor4 = moveForward * 0.98F;
         float factor5 = 0.91F;
         if (onGround) {
            factor5 = player.worldObj
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY) - 1, MathHelper.ceiling_double_int(posZ))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         float factor6 = 0.16277136F / (factor5 * factor5 * factor5);
         float factor7;
         if (onGround) {
            factor7 = player.getScaleFactor6() * factor6;
         } else {
            factor7 = player.scaleFactor10;
         }

         EventRotation event = RotationListener.resolveEventRotation(yaw, factor4, factor3, factor7, BugUpMoveFlyingType.MOVE_FLYING);
         float factor8 = factor3 * factor3 + factor4 * factor4;
         if (factor8 >= 1.0E-4F) {
            float factor9 = MathHelper.sin(factor8);
            if (factor9 < 1.0F) {
               factor9 = 1.0F;
            }

            float factor10 = factor7 / factor9;
            float factor11 = factor3 * factor10;
            float factor12 = factor4 * factor10;
            float factor13 = MathHelper.abs(event.getScaleFactor() * (float) Math.PI / 180.0F);
            float factor14 = MathHelper.cos(event.getScaleFactor() * (float) Math.PI / 180.0F);
            motionX += (double)(factor11 * factor14 - factor12 * factor13);
            motionZ += (double)(factor12 * factor14 + factor11 * factor13);
         }

         float factor15 = 0.91F;
         if (onGround) {
            factor15 = MinecraftAccessor.minecraft
                  .theWorld
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY) - 1, MathHelper.ceiling_double_int(posZ))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         double offset3 = motionX;
         double offset4 = motionY;
         double offset5 = motionZ;
         double offset6 = motionX;
         double offset7 = motionZ;
         boolean sprinting = player.isSprinting();
         if (player.onGround && sprinting) {
            double offset8;
            for (offset8 = 0.05;
               offset3 != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox2.expand(offset3, -1.0, 0.0)).isEmpty();
               offset6 = offset3
            ) {
               if (offset3 < offset8 && offset3 >= -offset8) {
                  offset3 = 0.0;
               } else if (offset3 > 0.0) {
                  offset3 -= offset8;
               } else {
                  offset3 += offset8;
               }
            }

            for (;
               offset5 != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox2.expand(0.0, -1.0, offset5)).isEmpty();
               offset7 = offset5
            ) {
               if (offset5 < offset8 && offset5 >= -offset8) {
                  offset5 = 0.0;
               } else if (offset5 > 0.0) {
                  offset5 -= offset8;
               } else {
                  offset5 += offset8;
               }
            }

            for (;
               offset3 != 0.0 && offset5 != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox2.expand(offset3, -1.0, offset5)).isEmpty();
               offset7 = offset5
            ) {
               if (offset3 < offset8 && offset3 >= -offset8) {
                  offset3 = 0.0;
               } else if (offset3 > 0.0) {
                  offset3 -= offset8;
               } else {
                  offset3 += offset8;
               }

               offset6 = offset3;
               if (offset5 < offset8 && offset5 >= -offset8) {
                  offset5 = 0.0;
               } else if (offset5 > 0.0) {
                  offset5 -= offset8;
               } else {
                  offset5 += offset8;
               }
            }
         }

         List collidingBoundingBoxes = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox2.contract(offset3, motionY, offset5));
         AxisAlignedBB boundingBox4 = boundingBox2;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset4 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox2, offset4);
         }

         AxisAlignedBB boundingBox5 = boundingBox2.expand(0.0, offset4, 0.0);
         boolean flag = onGround || motionY != offset4 && motionY < 0.0;
         Iterator iterator2 = collidingBoundingBoxes.iterator();

         while (iterator2.hasNext()) {
            offset3 = ((AxisAlignedBB)iterator2.next()).getCoordinate2(boundingBox5, offset3);
         }

         AxisAlignedBB boundingBox6 = boundingBox5.expand(offset3, 0.0, 0.0);
         Iterator iterator3 = collidingBoundingBoxes.iterator();

         while (iterator3.hasNext()) {
            offset5 = ((AxisAlignedBB)iterator3.next()).getCoordinate(boundingBox6, offset5);
         }

         boundingBox2 = boundingBox6.expand(0.0, 0.0, offset5);
         EventTickState eventTickState = EventTickStateCallback.getEventTickState(player.stepHeight, StateTypes.PRE);
         if (eventTickState.getScaleFactor() > 0.0F && flag && (offset6 != offset3 || offset7 != offset5)) {
            double offset9 = offset3;
            double offset10 = offset4;
            double offset11 = offset5;
            AxisAlignedBB boundingBox7 = boundingBox2;
            double scaleFactor = (double)eventTickState.getScaleFactor();
            List collidingBoundingBoxes2 = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox4.contract(offset6, scaleFactor, offset7));
            AxisAlignedBB boundingBox8 = boundingBox4.contract(offset6, 0.0, offset7);
            double offset12 = scaleFactor;
            Iterator iterator4 = collidingBoundingBoxes2.iterator();

            while (iterator4.hasNext()) {
               offset12 = ((AxisAlignedBB)iterator4.next()).getCoordinate3(boundingBox8, offset12);
            }

            AxisAlignedBB boundingBox9 = boundingBox4.expand(0.0, offset12, 0.0);
            offset3 = offset6;
            Iterator iterator5 = collidingBoundingBoxes2.iterator();

            while (iterator5.hasNext()) {
               offset3 = ((AxisAlignedBB)iterator5.next()).getCoordinate2(boundingBox9, offset3);
            }

            AxisAlignedBB boundingBox10 = boundingBox9.expand(offset3, 0.0, 0.0);
            offset5 = offset7;
            Iterator iterator6 = collidingBoundingBoxes2.iterator();

            while (iterator6.hasNext()) {
               offset5 = ((AxisAlignedBB)iterator6.next()).getCoordinate(boundingBox10, offset5);
            }

            AxisAlignedBB boundingBox11 = boundingBox10.expand(0.0, 0.0, offset5);
            AxisAlignedBB boundingBox12 = boundingBox4;
            Iterator iterator7 = collidingBoundingBoxes2.iterator();

            while (iterator7.hasNext()) {
               scaleFactor = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox12, scaleFactor);
            }

            AxisAlignedBB boundingBox13 = boundingBox12.expand(0.0, scaleFactor, 0.0);
            double offset13 = offset6;
            Iterator iterator8 = collidingBoundingBoxes2.iterator();

            while (iterator8.hasNext()) {
               offset13 = ((AxisAlignedBB)iterator8.next()).getCoordinate2(boundingBox13, offset13);
            }

            AxisAlignedBB boundingBox14 = boundingBox13.expand(offset13, 0.0, 0.0);
            double offset14 = offset7;
            Iterator iterator9 = collidingBoundingBoxes2.iterator();

            while (iterator9.hasNext()) {
               offset14 = ((AxisAlignedBB)iterator9.next()).getCoordinate(boundingBox14, offset14);
            }

            AxisAlignedBB boundingBox15 = boundingBox14.expand(0.0, 0.0, offset14);
            double offset15 = offset3 * offset3 + offset5 * offset5;
            double offset16 = offset13 * offset13 + offset14 * offset14;
            AxisAlignedBB boundingBox16;
            if (offset15 > offset16) {
               offset4 = -offset12;
               boundingBox16 = boundingBox11;
            } else {
               offset3 = offset13;
               offset5 = offset14;
               offset4 = -scaleFactor;
               boundingBox16 = boundingBox15;
            }

            Iterator iterator10 = collidingBoundingBoxes2.iterator();

            while (iterator10.hasNext()) {
               offset4 = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox16, offset4);
            }

            boundingBox2 = boundingBox16.expand(0.0, offset4, 0.0);
            if (offset9 * offset9 + offset11 * offset11 >= offset3 * offset3 + offset5 * offset5) {
               offset3 = offset9;
               offset4 = offset10;
               offset5 = offset11;
               boundingBox2 = boundingBox7;
            }
         }

         posX = (boundingBox2.minX + boundingBox2.maxX) / 2.0;
         posY = boundingBox2.minY;
         posZ = (boundingBox2.minZ + boundingBox2.maxZ) / 2.0;
         isCollidedHorizontally = offset6 != offset3 || offset7 != offset5;
         boolean flag2 = motionY != offset4;
         onGround = flag2 && motionY < 0.0;
         if (!isCollidedHorizontally && !flag2) {
         } else {
         }

         int count2 = MathHelper.ceiling_double_int(posX);
         int count3 = MathHelper.ceiling_double_int(posY - 0.2F);
         int count4 = MathHelper.ceiling_double_int(posZ);
         BlockPos blockPos = new BlockPos(count2, count3, count4);
         Block block = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (offset6 != offset3) {
            motionX = 0.0;
         }

         if (offset7 != offset5) {
            motionZ = 0.0;
         }

         if (motionY != offset4) {
            if (block instanceof BlockSlime) {
               if (player.isSprinting()) {
                  motionY = 0.0;
               } else if (motionY < 0.0) {
                  motionY = -motionY;
               }
            } else {
               motionY = 0.0;
            }
         }

         double offset17;
         if (player.worldObj.state
            && (
               !player.worldObj.checkBlockPos12(new BlockPos((int)posX, 0, (int)posZ))
                  || !player.worldObj.getLoggerEntry(new BlockPos((int)posX, 0, (int)posZ)).canExecute2()
            )) {
            if (posY > 0.0) {
               offset17 = -0.1;
            } else {
               offset17 = 0.0;
            }
         } else {
            offset17 = motionY - 0.08;
         }

         motionY = offset17 * 0.98F;
         motionX *= (double)factor15;
         motionZ *= (double)factor15;
      }

      return new double[]{posX, posY, posZ, prevPosX, prevPosY, prevPosZ, motionX, motionY, motionZ, onGround ? 1.0 : 0.0, isCollidedHorizontally ? 1.0 : 0.0};
   }

   public static double[] resolveDoubleArray3(EntityLivingBase livingEntity, int count) {
      double offset = livingEntity.posX - livingEntity.lastTickPosX;
      double offset2 = livingEntity.posZ - livingEntity.lastTickPosZ;
      double offset3 = livingEntity.posY - livingEntity.lastTickPosY;
      AxisAlignedBB boundingBox = livingEntity.getBoundingBox2();
      AxisAlignedBB boundingBox2 = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      boolean onGround = livingEntity.onGround;
      double value = livingEntity.value6;
      double value2 = livingEntity.value8;
      double value3 = livingEntity.value15;
      if (livingEntity instanceof EntityOtherPlayerMP) {
         EntityOtherPlayerMP player = (EntityOtherPlayerMP)livingEntity;
         value = player.coordOffset3;
         value2 = player.coordOffset1;
         value3 = player.coordOffset4;
         float factor = player.width / 2.0F;
         boundingBox2 = new AxisAlignedBB(
            value - (double)factor, value2, value3 - (double)factor, value + (double)factor, value2 + (double)player.height, value3 + (double)factor
         );
      }

      for (int index = 0; index <= count; index++) {
         offset = livingEntity.positionVec3.xCoord - livingEntity.positionVec.xCoord;
         offset2 = livingEntity.positionVec3.zCoord - livingEntity.positionVec.zCoord;
         double offset4 = offset;
         double offset5 = livingEntity.scaleFactor21 < 75.0F
               && livingEntity instanceof EntityPlayer
               && ((EntityPlayer)livingEntity).getHeldItem() != null
               && ((EntityPlayer)livingEntity).getHeldItem().getItem() instanceof ItemBlock
            ? 0.0
            : offset3;
         double offset6 = offset2;
         double offset7 = offset5;
         if (offset > 10.0 || offset5 > 10.0 || offset2 > 10.0) {
            return new double[]{value, value2, value3, offset, offset3, offset2, onGround ? 1.0 : 0.0};
         }

         List collidingBoundingBoxes = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.contract(offset, offset5, offset2));
         AxisAlignedBB boundingBox3 = boundingBox2;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset5 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox2, offset5);
         }

         AxisAlignedBB boundingBox4 = boundingBox2.expand(0.0, offset5, 0.0);
         boolean flag = onGround || offset7 != offset5 && offset7 < 0.0;
         Iterator iterator2 = collidingBoundingBoxes.iterator();

         while (iterator2.hasNext()) {
            offset4 = ((AxisAlignedBB)iterator2.next()).getCoordinate2(boundingBox4, offset4);
         }

         AxisAlignedBB boundingBox5 = boundingBox4.expand(offset4, 0.0, 0.0);
         Iterator iterator3 = collidingBoundingBoxes.iterator();

         while (iterator3.hasNext()) {
            offset6 = ((AxisAlignedBB)iterator3.next()).getCoordinate(boundingBox5, offset6);
         }

         boundingBox2 = boundingBox5.expand(0.0, 0.0, offset6);
         if (livingEntity.stepHeight > 0.0F && flag && (offset != offset4 || offset2 != offset6)) {
            double offset8 = offset4;
            double offset9 = offset5;
            double offset10 = offset6;
            AxisAlignedBB boundingBox6 = boundingBox2;
            double stepHeight = (double)livingEntity.stepHeight;
            List collidingBoundingBoxes2 = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox3.contract(offset, stepHeight, offset2));
            AxisAlignedBB boundingBox7 = boundingBox3.contract(offset, 0.0, offset2);
            double offset11 = stepHeight;
            Iterator iterator4 = collidingBoundingBoxes2.iterator();

            while (iterator4.hasNext()) {
               offset11 = ((AxisAlignedBB)iterator4.next()).getCoordinate3(boundingBox7, offset11);
            }

            AxisAlignedBB boundingBox8 = boundingBox3.expand(0.0, offset11, 0.0);
            offset4 = offset;
            Iterator iterator5 = collidingBoundingBoxes2.iterator();

            while (iterator5.hasNext()) {
               offset4 = ((AxisAlignedBB)iterator5.next()).getCoordinate2(boundingBox8, offset4);
            }

            AxisAlignedBB boundingBox9 = boundingBox8.expand(offset4, 0.0, 0.0);
            offset6 = offset2;
            Iterator iterator6 = collidingBoundingBoxes2.iterator();

            while (iterator6.hasNext()) {
               offset6 = ((AxisAlignedBB)iterator6.next()).getCoordinate(boundingBox9, offset6);
            }

            AxisAlignedBB boundingBox10 = boundingBox9.expand(0.0, 0.0, offset6);
            AxisAlignedBB boundingBox11 = boundingBox3;
            Iterator iterator7 = collidingBoundingBoxes2.iterator();

            while (iterator7.hasNext()) {
               stepHeight = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox11, stepHeight);
            }

            AxisAlignedBB boundingBox12 = boundingBox11.expand(0.0, stepHeight, 0.0);
            double offset12 = offset;
            Iterator iterator8 = collidingBoundingBoxes2.iterator();

            while (iterator8.hasNext()) {
               offset12 = ((AxisAlignedBB)iterator8.next()).getCoordinate2(boundingBox12, offset12);
            }

            AxisAlignedBB boundingBox13 = boundingBox12.expand(offset12, 0.0, 0.0);
            double offset13 = offset2;
            Iterator iterator9 = collidingBoundingBoxes2.iterator();

            while (iterator9.hasNext()) {
               offset13 = ((AxisAlignedBB)iterator9.next()).getCoordinate(boundingBox13, offset13);
            }

            AxisAlignedBB boundingBox14 = boundingBox13.expand(0.0, 0.0, offset13);
            double offset14 = offset4 * offset4 + offset6 * offset6;
            double offset15 = offset12 * offset12 + offset13 * offset13;
            AxisAlignedBB boundingBox15;
            if (offset14 > offset15) {
               offset5 = -offset11;
               boundingBox15 = boundingBox10;
            } else {
               offset4 = offset12;
               offset6 = offset13;
               offset5 = -stepHeight;
               boundingBox15 = boundingBox14;
            }

            Iterator iterator10 = collidingBoundingBoxes2.iterator();

            while (iterator10.hasNext()) {
               offset5 = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox15, offset5);
            }

            boundingBox2 = boundingBox15.expand(0.0, offset5, 0.0);
            if (offset8 * offset8 + offset10 * offset10 >= offset4 * offset4 + offset6 * offset6) {
               offset4 = offset8;
               offset5 = offset9;
               offset6 = offset10;
               boundingBox2 = boundingBox6;
            }
         }

         value = (boundingBox2.minX + boundingBox2.maxX) / 2.0;
         value2 = boundingBox2.minY;
         value3 = (boundingBox2.minZ + boundingBox2.maxZ) / 2.0;
         boolean flag2 = offset != offset4 || offset2 != offset6;
         boolean flag3 = offset7 != offset5;
         onGround = flag3 && offset7 < 0.0;
         if (!flag2 && !flag3) {
         } else {
         }

         int count2 = MathHelper.ceiling_double_int(value);
         int count3 = MathHelper.ceiling_double_int(value2 - 0.2F);
         int count4 = MathHelper.ceiling_double_int(value3);
         BlockPos blockPos = new BlockPos(count2, count3, count4);
         Block block = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (offset != offset4) {
            offset = 0.0;
         }

         if (offset2 != offset6) {
            offset2 = 0.0;
         }

         if (offset7 != offset5) {
            if (block instanceof BlockSlime) {
               if (livingEntity.isSprinting()) {
                  offset3 = 0.0;
               } else if (offset3 < 0.0) {
                  offset3 = -offset3;
               }
            } else {
               offset3 = 0.0;
            }
         }

         double offset16;
         if (MinecraftAccessor.minecraft.theWorld.state
            && (
               !MinecraftAccessor.minecraft.theWorld.checkBlockPos12(new BlockPos((int)value, 0, (int)value3))
                  || !MinecraftAccessor.minecraft.theWorld.getLoggerEntry(new BlockPos((int)value, 0, (int)value3)).canExecute2()
            )) {
            if (value2 > 0.0) {
               offset16 = -0.1;
            } else {
               offset16 = 0.0;
            }
         } else {
            offset16 = offset3 - 0.08;
         }

         offset3 = offset16 * 0.98F;
      }

      return new double[]{value, value2, value3, offset, offset3, offset2, onGround ? 1.0 : 0.0};
   }

   public static ArrayList resolveArrayListByEntityLivingBase(EntityLivingBase livingEntity, int count) {
      ArrayList list = new ArrayList();
      double offset = livingEntity.posY - livingEntity.lastTickPosY;
      AxisAlignedBB boundingBox = livingEntity.getBoundingBox2();
      AxisAlignedBB boundingBox2 = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      boolean onGround = livingEntity.onGround;
      if (livingEntity instanceof EntityOtherPlayerMP) {
         EntityOtherPlayerMP player = (EntityOtherPlayerMP)livingEntity;
      }

      for (int index = 0; index <= count; index++) {
         double offset2 = livingEntity.posX - livingEntity.lastTickPosX;
         double offset3 = livingEntity.posZ - livingEntity.lastTickPosZ;
         double offset4 = livingEntity.scaleFactor21 < 75.0F
               && livingEntity instanceof EntityPlayer
               && ((EntityPlayer)livingEntity).getHeldItem() != null
               && ((EntityPlayer)livingEntity).getHeldItem().getItem() instanceof ItemBlock
            ? 0.0
            : offset;
         double offset5 = offset2;
         double offset6 = offset4;
         double offset7 = offset3;
         if (offset2 > 10.0 || offset4 > 10.0 || offset3 > 10.0) {
            return list;
         }

         List collidingBoundingBoxes = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.contract(offset2, offset4, offset3));
         AxisAlignedBB boundingBox3 = boundingBox2;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset4 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox2, offset4);
         }

         AxisAlignedBB boundingBox4 = boundingBox2.expand(0.0, offset4, 0.0);
         boolean flag = onGround || offset6 != offset4 && offset6 < 0.0;
         Iterator iterator2 = collidingBoundingBoxes.iterator();

         while (iterator2.hasNext()) {
            offset2 = ((AxisAlignedBB)iterator2.next()).getCoordinate2(boundingBox4, offset2);
         }

         AxisAlignedBB boundingBox5 = boundingBox4.expand(offset2, 0.0, 0.0);
         Iterator iterator3 = collidingBoundingBoxes.iterator();

         while (iterator3.hasNext()) {
            offset3 = ((AxisAlignedBB)iterator3.next()).getCoordinate(boundingBox5, offset3);
         }

         boundingBox2 = boundingBox5.expand(0.0, 0.0, offset3);
         if (livingEntity.stepHeight > 0.0F && flag && (offset5 != offset2 || offset7 != offset3)) {
            double offset8 = offset2;
            double offset9 = offset4;
            double offset10 = offset3;
            AxisAlignedBB boundingBox6 = boundingBox2;
            double stepHeight = (double)livingEntity.stepHeight;
            List collidingBoundingBoxes2 = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox3.contract(offset5, stepHeight, offset7));
            AxisAlignedBB boundingBox7 = boundingBox3.contract(offset5, 0.0, offset7);
            double offset11 = stepHeight;
            Iterator iterator4 = collidingBoundingBoxes2.iterator();

            while (iterator4.hasNext()) {
               offset11 = ((AxisAlignedBB)iterator4.next()).getCoordinate3(boundingBox7, offset11);
            }

            AxisAlignedBB boundingBox8 = boundingBox3.expand(0.0, offset11, 0.0);
            offset2 = offset5;
            Iterator iterator5 = collidingBoundingBoxes2.iterator();

            while (iterator5.hasNext()) {
               offset2 = ((AxisAlignedBB)iterator5.next()).getCoordinate2(boundingBox8, offset2);
            }

            AxisAlignedBB boundingBox9 = boundingBox8.expand(offset2, 0.0, 0.0);
            offset3 = offset7;
            Iterator iterator6 = collidingBoundingBoxes2.iterator();

            while (iterator6.hasNext()) {
               offset3 = ((AxisAlignedBB)iterator6.next()).getCoordinate(boundingBox9, offset3);
            }

            AxisAlignedBB boundingBox10 = boundingBox9.expand(0.0, 0.0, offset3);
            AxisAlignedBB boundingBox11 = boundingBox3;
            Iterator iterator7 = collidingBoundingBoxes2.iterator();

            while (iterator7.hasNext()) {
               stepHeight = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox11, stepHeight);
            }

            AxisAlignedBB boundingBox12 = boundingBox11.expand(0.0, stepHeight, 0.0);
            double offset12 = offset5;
            Iterator iterator8 = collidingBoundingBoxes2.iterator();

            while (iterator8.hasNext()) {
               offset12 = ((AxisAlignedBB)iterator8.next()).getCoordinate2(boundingBox12, offset12);
            }

            AxisAlignedBB boundingBox13 = boundingBox12.expand(offset12, 0.0, 0.0);
            double offset13 = offset7;
            Iterator iterator9 = collidingBoundingBoxes2.iterator();

            while (iterator9.hasNext()) {
               offset13 = ((AxisAlignedBB)iterator9.next()).getCoordinate(boundingBox13, offset13);
            }

            AxisAlignedBB boundingBox14 = boundingBox13.expand(0.0, 0.0, offset13);
            double offset14 = offset2 * offset2 + offset3 * offset3;
            double offset15 = offset12 * offset12 + offset13 * offset13;
            AxisAlignedBB boundingBox15;
            if (offset14 > offset15) {
               offset4 = -offset11;
               boundingBox15 = boundingBox10;
            } else {
               offset2 = offset12;
               offset3 = offset13;
               offset4 = -stepHeight;
               boundingBox15 = boundingBox14;
            }

            Iterator iterator10 = collidingBoundingBoxes2.iterator();

            while (iterator10.hasNext()) {
               offset4 = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox15, offset4);
            }

            boundingBox2 = boundingBox15.expand(0.0, offset4, 0.0);
            if (offset8 * offset8 + offset10 * offset10 >= offset2 * offset2 + offset3 * offset3) {
               offset2 = offset8;
               offset4 = offset9;
               offset3 = offset10;
               boundingBox2 = boundingBox6;
            }
         }

         double offset16 = (boundingBox2.minX + boundingBox2.maxX) / 2.0;
         double minY = boundingBox2.minY;
         double offset17 = (boundingBox2.minZ + boundingBox2.maxZ) / 2.0;
         boolean flag2 = offset5 != offset2 || offset7 != offset3;
         boolean flag3 = offset6 != offset4;
         onGround = flag3 && offset6 < 0.0;
         if (!flag2 && !flag3) {
         } else {
         }

         int count2 = MathHelper.ceiling_double_int(offset16);
         int count3 = MathHelper.ceiling_double_int(minY - 0.2F);
         int count4 = MathHelper.ceiling_double_int(offset17);
         BlockPos blockPos = new BlockPos(count2, count3, count4);
         Block block = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (offset6 != offset4) {
            if (block instanceof BlockSlime) {
               if (livingEntity.isSprinting()) {
                  offset = 0.0;
               } else if (offset < 0.0) {
                  offset = -offset;
               }
            } else {
               offset = 0.0;
            }
         }

         double offset18;
         if (MinecraftAccessor.minecraft.theWorld.state
            && (
               !MinecraftAccessor.minecraft.theWorld.checkBlockPos12(new BlockPos((int)offset16, 0, (int)offset17))
                  || !MinecraftAccessor.minecraft.theWorld.getLoggerEntry(new BlockPos((int)offset16, 0, (int)offset17)).canExecute2()
            )) {
            if (minY > 0.0) {
               offset18 = -0.1;
            } else {
               offset18 = 0.0;
            }
         } else {
            offset18 = offset - 0.08;
         }

         offset = offset18 * 0.98F;
         list.add(new Vec3(offset16, minY, offset17));
      }

      return list;
   }

   public static Vec3 resolveVec3(boolean flag, Entity entity, double offset, float factor) {
      double motionX = MinecraftAccessor.minecraft.thePlayer.motionX;
      double motionY = MinecraftAccessor.minecraft.thePlayer.motionY;
      double motionZ = MinecraftAccessor.minecraft.thePlayer.motionZ;
      double posX = MinecraftAccessor.minecraft.thePlayer.posX;
      double posY = MinecraftAccessor.minecraft.thePlayer.posY;
      double posZ = MinecraftAccessor.minecraft.thePlayer.posZ;
      EntityPlayerSP player = MinecraftAccessor.minecraft.thePlayer;
      boolean riding = MinecraftAccessor.minecraft.thePlayer.isRiding();
      int indexOffset = MinecraftAccessor.minecraft.thePlayer.getIndexOffset18();
      boolean onGround = MinecraftAccessor.minecraft.thePlayer.onGround;
      boolean state = MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.state;
      AxisAlignedBB boundingBox = new AxisAlignedBB(MinecraftAccessor.minecraft.thePlayer.getBoundingBox2());
      if (flag) {
         float doubleItem = (float)MinecraftAccessor.minecraft.thePlayer.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).getDouble();
         float factor2;
         if (entity instanceof EntityLivingBase) {
            factor2 = LvlManager.getScaleFactor(MinecraftAccessor.minecraft.thePlayer.getItemStack(), ((EntityLivingBase)entity).getUNDEAD());
         } else {
            factor2 = LvlManager.getScaleFactor(MinecraftAccessor.minecraft.thePlayer.getItemStack(), DisablerUndefinedType.UNDEFINED);
         }

         if (doubleItem > 0.0F || factor2 > 0.0F) {
            int index = LvlManager.getIndex(MinecraftAccessor.minecraft.thePlayer);
            if (MinecraftAccessor.minecraft.thePlayer.isRiding()) {
               index++;
            }

            if (entity.matchesDamageSource(DamageSource.resolveDamageSource(MinecraftAccessor.minecraft.thePlayer), doubleItem)) {
               if (index > 0) {
                  EventChat event = EventChatCallback.resolveEventChat(RenderKeybindProperty.FIRST, true);
                  motionX *= event.getValue();
                  motionZ *= event.getValue();
                  riding = event.isEnabled2();
               } else if (offset == -1337.0) {
                  EventChat event2 = EventChatCallback.resolveEventChat(RenderKeybindProperty.SECOND, true);
                  if (event2.isEnabled()) {
                     motionX *= event2.getValue();
                     motionZ *= event2.getValue();
                     riding = event2.isEnabled2();
                  }
               } else {
                  motionX *= offset;
                  motionZ *= offset;
                  riding = false;
               }
            }
         }
      }

      float moveStrafe = player.secondaryFloatEntry.moveStrafe;
      float moveForward = player.secondaryFloatEntry.moveForward;
      EventCameraTransform eventCameraTransform = EventCameraTransformCallback.getEventCameraTransform(0.2F, 0.2F);
      if (player.isUsingItem() && !player.isSneaking()) {
         moveStrafe *= eventCameraTransform.getScaleFactor2();
         moveForward *= eventCameraTransform.getScaleFactor();
      }

      if (indexOffset > 0) {
         indexOffset--;
      }

      double offset2 = ViaLoadingBase.processAction2();
      if (Math.abs(motionX) < offset2) {
         motionX = 0.0;
      }

      if (Math.abs(motionY) < offset2) {
         motionY = 0.0;
      }

      if (Math.abs(motionZ) < offset2) {
         motionZ = 0.0;
      }

      if (state && onGround && indexOffset == 0) {
         motionY = (double)player.getFloat6();
         if (player.checkPotion(Potion.potion9)) {
            motionY += (double)((float)(player.getPotionEffect(Potion.potion9).getIndexOffset2() + 1) * 0.1F);
         }

         if (riding) {
            float factor3 = factor * (float) (Math.PI / 180.0);
            motionX -= (double)(MathHelper.abs(factor3) * 0.2F);
            motionZ += (double)(MathHelper.cos(factor3) * 0.2F);
         }
      }

      float factor4 = moveStrafe * 0.98F;
      float factor5 = moveForward * 0.98F;
      float factor6 = 0.91F;
      if (onGround) {
         factor6 = player.worldObj
               .getBlockState(
                  new BlockPos(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY) - 1, MathHelper.ceiling_double_int(posZ))
               )
               .getBlock()
               .scaleFactor
            * 0.91F;
      }

      float factor7 = 0.16277136F / (factor6 * factor6 * factor6);
      float factor8;
      if (onGround) {
         factor8 = player.getScaleFactor6() * factor7;
      } else {
         factor8 = player.scaleFactor10;
      }

      EventRotation event3 = RotationListener.resolveEventRotation(factor, factor5, factor4, factor8, BugUpMoveFlyingType.MOVE_FLYING);
      float factor9 = factor4 * factor4 + factor5 * factor5;
      if (factor9 >= 1.0E-4F) {
         float factor10 = MathHelper.sin(factor9);
         if (factor10 < 1.0F) {
            factor10 = 1.0F;
         }

         float factor11 = factor8 / factor10;
         float factor12 = factor4 * factor11;
         float factor13 = factor5 * factor11;
         float factor14 = MathHelper.abs(event3.getScaleFactor() * (float) Math.PI / 180.0F);
         float factor15 = MathHelper.cos(event3.getScaleFactor() * (float) Math.PI / 180.0F);
         motionX += (double)(factor12 * factor15 - factor13 * factor14);
         motionZ += (double)(factor13 * factor15 + factor12 * factor14);
      }

      if (onGround) {
         new BlockPos(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY) - 1, MathHelper.ceiling_double_int(posZ));
      }

      double offset3 = motionX;
      double offset4 = motionY;
      double offset5 = motionZ;
      boolean sprinting = player.isSprinting();
      if (onGround && sprinting) {
         double offset6;
         for (offset6 = 0.05;
            motionX != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox.expand(motionX, -1.0, 0.0)).isEmpty();
            offset3 = motionX
         ) {
            if (motionX < offset6 && motionX >= -offset6) {
               motionX = 0.0;
            } else if (motionX > 0.0) {
               motionX -= offset6;
            } else {
               motionX += offset6;
            }
         }

         for (; motionZ != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox.expand(0.0, -1.0, motionZ)).isEmpty(); offset5 = motionZ) {
            if (motionZ < offset6 && motionZ >= -offset6) {
               motionZ = 0.0;
            } else if (motionZ > 0.0) {
               motionZ -= offset6;
            } else {
               motionZ += offset6;
            }
         }

         for (;
            motionX != 0.0 && motionZ != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox.expand(motionX, -1.0, motionZ)).isEmpty();
            offset5 = motionZ
         ) {
            if (motionX < offset6 && motionX >= -offset6) {
               motionX = 0.0;
            } else if (motionX > 0.0) {
               motionX -= offset6;
            } else {
               motionX += offset6;
            }

            offset3 = motionX;
            if (motionZ < offset6 && motionZ >= -offset6) {
               motionZ = 0.0;
            } else if (motionZ > 0.0) {
               motionZ -= offset6;
            } else {
               motionZ += offset6;
            }
         }
      }

      List collidingBoundingBoxes = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox.contract(motionX, motionY, motionZ));
      Iterator iterator = collidingBoundingBoxes.iterator();

      while (iterator.hasNext()) {
         motionY = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox, motionY);
      }

      AxisAlignedBB boundingBox2 = boundingBox.expand(0.0, motionY, 0.0);
      boolean flag2 = onGround || offset4 != motionY && offset4 < 0.0;
      Iterator iterator2 = collidingBoundingBoxes.iterator();

      while (iterator2.hasNext()) {
         motionX = ((AxisAlignedBB)iterator2.next()).getCoordinate2(boundingBox2, motionX);
      }

      AxisAlignedBB boundingBox3 = boundingBox2.expand(motionX, 0.0, 0.0);
      Iterator iterator3 = collidingBoundingBoxes.iterator();

      while (iterator3.hasNext()) {
         motionZ = ((AxisAlignedBB)iterator3.next()).getCoordinate(boundingBox3, motionZ);
      }

      AxisAlignedBB boundingBox4 = boundingBox3.expand(0.0, 0.0, motionZ);
      EventTickState eventTickState = EventTickStateCallback.getEventTickState(player.stepHeight, StateTypes.PRE);
      if (eventTickState.getScaleFactor() > 0.0F && flag2 && (offset3 != motionX || offset5 != motionZ)) {
         double offset7 = motionY;
         AxisAlignedBB boundingBox5 = boundingBox4;
         double scaleFactor = (double)eventTickState.getScaleFactor();
         List collidingBoundingBoxes2 = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox.contract(offset3, scaleFactor, offset5));
         AxisAlignedBB boundingBox6 = boundingBox.contract(offset3, 0.0, offset5);
         double offset8 = scaleFactor;
         Iterator iterator4 = collidingBoundingBoxes2.iterator();

         while (iterator4.hasNext()) {
            offset8 = ((AxisAlignedBB)iterator4.next()).getCoordinate3(boundingBox6, offset8);
         }

         AxisAlignedBB boundingBox7 = boundingBox.expand(0.0, offset8, 0.0);
         double offset9 = offset3;
         Iterator iterator5 = collidingBoundingBoxes2.iterator();

         while (iterator5.hasNext()) {
            offset9 = ((AxisAlignedBB)iterator5.next()).getCoordinate2(boundingBox7, offset9);
         }

         AxisAlignedBB boundingBox8 = boundingBox7.expand(offset9, 0.0, 0.0);
         double offset10 = offset5;
         Iterator iterator6 = collidingBoundingBoxes2.iterator();

         while (iterator6.hasNext()) {
            offset10 = ((AxisAlignedBB)iterator6.next()).getCoordinate(boundingBox8, offset10);
         }

         AxisAlignedBB boundingBox9 = boundingBox8.expand(0.0, 0.0, offset10);
         AxisAlignedBB boundingBox10 = boundingBox;
         Iterator iterator7 = collidingBoundingBoxes2.iterator();

         while (iterator7.hasNext()) {
            scaleFactor = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox10, scaleFactor);
         }

         AxisAlignedBB boundingBox11 = boundingBox10.expand(0.0, scaleFactor, 0.0);
         Iterator iterator8 = collidingBoundingBoxes2.iterator();

         while (iterator8.hasNext()) {
            offset3 = ((AxisAlignedBB)iterator8.next()).getCoordinate2(boundingBox11, offset3);
         }

         AxisAlignedBB boundingBox12 = boundingBox11.expand(offset3, 0.0, 0.0);
         Iterator iterator9 = collidingBoundingBoxes2.iterator();

         while (iterator9.hasNext()) {
            offset5 = ((AxisAlignedBB)iterator9.next()).getCoordinate(boundingBox12, offset5);
         }

         AxisAlignedBB boundingBox13 = boundingBox12.expand(0.0, 0.0, offset5);
         double offset11 = offset9 * offset9 + offset10 * offset10;
         double offset12 = offset3 * offset3 + offset5 * offset5;
         AxisAlignedBB boundingBox14;
         if (offset11 > offset12) {
            motionY = -offset8;
            boundingBox14 = boundingBox9;
         } else {
            offset9 = offset3;
            offset10 = offset5;
            motionY = -scaleFactor;
            boundingBox14 = boundingBox13;
         }

         Iterator iterator10 = collidingBoundingBoxes2.iterator();

         while (iterator10.hasNext()) {
            motionY = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox14, motionY);
         }

         boundingBox4 = boundingBox14.expand(0.0, motionY, 0.0);
         if (motionX * motionX + motionZ * motionZ >= offset9 * offset9 + offset10 * offset10) {
            motionY = offset7;
            boundingBox4 = boundingBox5;
         }
      }

      double offset13 = (boundingBox4.minX + boundingBox4.maxX) / 2.0;
      double minY = boundingBox4.minY;
      double offset14 = (boundingBox4.minZ + boundingBox4.maxZ) / 2.0;
      int count = MathHelper.ceiling_double_int(offset13);
      int count2 = MathHelper.ceiling_double_int(minY - 0.2F);
      int count3 = MathHelper.ceiling_double_int(offset14);
      BlockPos blockPos = new BlockPos(count, count2, count3);
      Block block = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock();
      if (block.getMaterial() == Material.coreItemEntry) {
         Block block2 = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
         if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
            block = block2;
            blockPos.down();
         }
      }

      if (offset4 != motionY && block instanceof BlockSlime) {
         player.isSprinting();
      }

      if (player.worldObj.state && player.worldObj.checkBlockPos12(new BlockPos((int)offset13, 0, (int)offset14))) {
         player.worldObj.getLoggerEntry(new BlockPos((int)offset13, 0, (int)offset14)).canExecute2();
      }

      return new Vec3(offset13, minY, offset14);
   }

   public static double[] resolveDoubleArray(EntityPlayerSP player, ArrayList list, int count, boolean flag) {
      BoundingBoxDimension boundingBoxDimension = (BoundingBoxDimension)list.get(0);
      double posX = boundingBoxDimension.positionVec2.xCoord;
      double posY = boundingBoxDimension.positionVec2.yCoord;
      double posZ = boundingBoxDimension.positionVec2.zCoord;
      double posX2 = boundingBoxDimension.vec3.xCoord;
      double posY2 = boundingBoxDimension.vec3.yCoord;
      double posZ2 = boundingBoxDimension.vec3.zCoord;
      double prevPosX = player.prevPosX;
      double prevPosY = player.prevPosY;
      double prevPosZ = player.prevPosZ;
      boolean enabled = boundingBoxDimension.enabled4;
      AxisAlignedBB axisAlignedBB = boundingBoxDimension.axisAlignedBB;
      AxisAlignedBB boundingBox = new AxisAlignedBB(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
      boolean isCollidedHorizontally = player.isCollidedHorizontally;
      float fallDistance = player.fallDistance;

      for (int index = 0; index <= count; index++) {
         BoundingBoxDimension boundingBoxDimension2 = (BoundingBoxDimension)list.get(Math.min(list.size() - 1, index));
         prevPosX = posX;
         prevPosY = posY;
         prevPosZ = posZ;
         float factor = boundingBoxDimension2.factor3;
         float factor2 = boundingBoxDimension2.factor2;
         boolean enabled2 = boundingBoxDimension2.enabled5;
         boolean enabled3 = boundingBoxDimension2.enabled;
         boolean enabled4 = boundingBoxDimension2.enabled2;
         EventCameraTransform eventCameraTransform = EventCameraTransformCallback.getEventCameraTransform(0.2F, 0.2F);
         if (player.isUsingItem() && !player.isSneaking()) {
            factor *= eventCameraTransform.getScaleFactor2();
            factor2 *= eventCameraTransform.getScaleFactor();
         }

         double offset = ViaLoadingBase.processAction2();
         if (Math.abs(posX2) < offset) {
            posX2 = 0.0;
         }

         if (Math.abs(posY2) < offset) {
            posY2 = 0.0;
         }

         if (Math.abs(posZ2) < offset) {
            posZ2 = 0.0;
         }

         if (enabled3 && enabled) {
            posY2 = (double)player.getFloat6();
            if (player.checkPotion(Potion.potion9)) {
               posY2 += (double)((float)(player.getPotionEffect(Potion.potion9).getIndexOffset2() + 1) * 0.1F);
            }

            if (enabled2) {
               float factor3 = boundingBoxDimension2.factor4 * (float) (Math.PI / 180.0);
               posX2 -= (double)(MathHelper.abs(factor3) * 0.2F);
               posZ2 += (double)(MathHelper.cos(factor3) * 0.2F);
            }
         }

         float factor4 = factor * 0.98F;
         float factor5 = factor2 * 0.98F;
         float factor6 = 0.91F;
         if (enabled) {
            factor6 = player.worldObj
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY) - 1, MathHelper.ceiling_double_int(posZ))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         float factor7 = 0.16277136F / (factor6 * factor6 * factor6);
         float factor8;
         if (enabled) {
            factor8 = boundingBoxDimension2.factor * factor7;
         } else {
            factor8 = player.getScaleFactor2();
            if (enabled2) {
               factor8 = (float)((double)factor8 + (double)player.getScaleFactor2() * 0.3);
            }
         }

         float factor9 = factor4 * factor4 + factor5 * factor5;
         if (factor9 >= 1.0E-4F) {
            float factor10 = MathHelper.sin(factor9);
            if (factor10 < 1.0F) {
               factor10 = 1.0F;
            }

            float factor11 = factor8 / factor10;
            float factor12 = factor4 * factor11;
            float factor13 = factor5 * factor11;
            float factor14 = MathHelper.abs(boundingBoxDimension2.factor4 * (float) Math.PI / 180.0F);
            float factor15 = MathHelper.cos(boundingBoxDimension2.factor4 * (float) Math.PI / 180.0F);
            posX2 += (double)(factor12 * factor15 - factor13 * factor14);
            posZ2 += (double)(factor13 * factor15 + factor12 * factor14);
         }

         float factor16 = 0.91F;
         if (enabled) {
            factor16 = MinecraftAccessor.minecraft
                  .theWorld
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY) - 1, MathHelper.ceiling_double_int(posZ))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         double offset2 = posX2;
         double offset3 = posY2;
         double offset4 = posZ2;
         double offset5 = posX2;
         double offset6 = posZ2;
         boolean flag2 = enabled4 && !flag;
         if (enabled && flag2) {
            double offset7;
            for (offset7 = 0.05;
               offset2 != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox.expand(offset2, -1.0, 0.0)).isEmpty();
               offset5 = offset2
            ) {
               if (offset2 < offset7 && offset2 >= -offset7) {
                  offset2 = 0.0;
               } else if (offset2 > 0.0) {
                  offset2 -= offset7;
               } else {
                  offset2 += offset7;
               }
            }

            for (;
               offset4 != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox.expand(0.0, -1.0, offset4)).isEmpty();
               offset6 = offset4
            ) {
               if (offset4 < offset7 && offset4 >= -offset7) {
                  offset4 = 0.0;
               } else if (offset4 > 0.0) {
                  offset4 -= offset7;
               } else {
                  offset4 += offset7;
               }
            }

            for (;
               offset2 != 0.0 && offset4 != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox.expand(offset2, -1.0, offset4)).isEmpty();
               offset6 = offset4
            ) {
               if (offset2 < offset7 && offset2 >= -offset7) {
                  offset2 = 0.0;
               } else if (offset2 > 0.0) {
                  offset2 -= offset7;
               } else {
                  offset2 += offset7;
               }

               offset5 = offset2;
               if (offset4 < offset7 && offset4 >= -offset7) {
                  offset4 = 0.0;
               } else if (offset4 > 0.0) {
                  offset4 -= offset7;
               } else {
                  offset4 += offset7;
               }
            }
         }

         List collidingBoundingBoxes = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox.contract(offset2, posY2, offset4));
         AxisAlignedBB boundingBox2 = boundingBox;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset3 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox, offset3);
         }

         AxisAlignedBB boundingBox3 = boundingBox.expand(0.0, offset3, 0.0);
         boolean flag3 = enabled || posY2 != offset3 && posY2 < 0.0;
         Iterator iterator2 = collidingBoundingBoxes.iterator();

         while (iterator2.hasNext()) {
            offset2 = ((AxisAlignedBB)iterator2.next()).getCoordinate2(boundingBox3, offset2);
         }

         AxisAlignedBB boundingBox4 = boundingBox3.expand(offset2, 0.0, 0.0);
         Iterator iterator3 = collidingBoundingBoxes.iterator();

         while (iterator3.hasNext()) {
            offset4 = ((AxisAlignedBB)iterator3.next()).getCoordinate(boundingBox4, offset4);
         }

         boundingBox = boundingBox4.expand(0.0, 0.0, offset4);
         EventTickState eventTickState = EventTickStateCallback.getEventTickState(player.stepHeight, StateTypes.PRE);
         if (eventTickState.getScaleFactor() > 0.0F && flag3 && (offset5 != offset2 || offset6 != offset4)) {
            double offset8 = offset2;
            double offset9 = offset3;
            double offset10 = offset4;
            AxisAlignedBB boundingBox5 = boundingBox;
            double scaleFactor = (double)eventTickState.getScaleFactor();
            List collidingBoundingBoxes2 = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox2.contract(offset5, scaleFactor, offset6));
            AxisAlignedBB boundingBox6 = boundingBox2.contract(offset5, 0.0, offset6);
            double offset11 = scaleFactor;
            Iterator iterator4 = collidingBoundingBoxes2.iterator();

            while (iterator4.hasNext()) {
               offset11 = ((AxisAlignedBB)iterator4.next()).getCoordinate3(boundingBox6, offset11);
            }

            AxisAlignedBB boundingBox7 = boundingBox2.expand(0.0, offset11, 0.0);
            offset2 = offset5;
            Iterator iterator5 = collidingBoundingBoxes2.iterator();

            while (iterator5.hasNext()) {
               offset2 = ((AxisAlignedBB)iterator5.next()).getCoordinate2(boundingBox7, offset2);
            }

            AxisAlignedBB boundingBox8 = boundingBox7.expand(offset2, 0.0, 0.0);
            offset4 = offset6;
            Iterator iterator6 = collidingBoundingBoxes2.iterator();

            while (iterator6.hasNext()) {
               offset4 = ((AxisAlignedBB)iterator6.next()).getCoordinate(boundingBox8, offset4);
            }

            AxisAlignedBB boundingBox9 = boundingBox8.expand(0.0, 0.0, offset4);
            AxisAlignedBB boundingBox10 = boundingBox2;
            Iterator iterator7 = collidingBoundingBoxes2.iterator();

            while (iterator7.hasNext()) {
               scaleFactor = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox10, scaleFactor);
            }

            AxisAlignedBB boundingBox11 = boundingBox10.expand(0.0, scaleFactor, 0.0);
            double offset12 = offset5;
            Iterator iterator8 = collidingBoundingBoxes2.iterator();

            while (iterator8.hasNext()) {
               offset12 = ((AxisAlignedBB)iterator8.next()).getCoordinate2(boundingBox11, offset12);
            }

            AxisAlignedBB boundingBox12 = boundingBox11.expand(offset12, 0.0, 0.0);
            double offset13 = offset6;
            Iterator iterator9 = collidingBoundingBoxes2.iterator();

            while (iterator9.hasNext()) {
               offset13 = ((AxisAlignedBB)iterator9.next()).getCoordinate(boundingBox12, offset13);
            }

            AxisAlignedBB boundingBox13 = boundingBox12.expand(0.0, 0.0, offset13);
            double offset14 = offset2 * offset2 + offset4 * offset4;
            double offset15 = offset12 * offset12 + offset13 * offset13;
            AxisAlignedBB boundingBox14;
            if (offset14 > offset15) {
               offset3 = -offset11;
               boundingBox14 = boundingBox9;
            } else {
               offset2 = offset12;
               offset4 = offset13;
               offset3 = -scaleFactor;
               boundingBox14 = boundingBox13;
            }

            Iterator iterator10 = collidingBoundingBoxes2.iterator();

            while (iterator10.hasNext()) {
               offset3 = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox14, offset3);
            }

            boundingBox = boundingBox14.expand(0.0, offset3, 0.0);
            if (offset8 * offset8 + offset10 * offset10 >= offset2 * offset2 + offset4 * offset4) {
               offset2 = offset8;
               offset3 = offset9;
               offset4 = offset10;
               boundingBox = boundingBox5;
            }
         }

         posX = (boundingBox.minX + boundingBox.maxX) / 2.0;
         posY = boundingBox.minY;
         posZ = (boundingBox.minZ + boundingBox.maxZ) / 2.0;
         isCollidedHorizontally = offset5 != offset2 || offset6 != offset4;
         boolean flag4 = posY2 != offset3;
         enabled = flag4 && posY2 < 0.0;
         if (!isCollidedHorizontally && !flag4) {
         } else {
         }

         int count2 = MathHelper.ceiling_double_int(posX);
         int count3 = MathHelper.ceiling_double_int(posY - 0.2F);
         int count4 = MathHelper.ceiling_double_int(posZ);
         BlockPos blockPos = new BlockPos(count2, count3, count4);
         Block block = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (enabled) {
            if (fallDistance > 0.0F) {
               fallDistance = 0.0F;
            }
         } else if (offset3 < 0.0) {
            fallDistance = (float)((double)fallDistance - offset3);
         }

         if (offset5 != offset2) {
            posX2 = 0.0;
         }

         if (offset6 != offset4) {
            posZ2 = 0.0;
         }

         if (posY2 != offset3) {
            if (block instanceof BlockSlime) {
               if (boundingBoxDimension2.enabled2) {
                  posY2 = 0.0;
               } else if (posY2 < 0.0) {
                  posY2 = -posY2;
               }
            } else {
               posY2 = 0.0;
            }
         }

         double offset16;
         if (player.worldObj.state
            && (
               !player.worldObj.checkBlockPos12(new BlockPos((int)posX, 0, (int)posZ))
                  || !player.worldObj.getLoggerEntry(new BlockPos((int)posX, 0, (int)posZ)).canExecute2()
            )) {
            if (posY > 0.0) {
               offset16 = -0.1;
            } else {
               offset16 = 0.0;
            }
         } else {
            offset16 = posY2 - 0.08;
         }

         posY2 = offset16 * 0.98F;
         posX2 *= (double)factor16;
         posZ2 *= (double)factor16;
      }

      return new double[]{posX, posY, posZ, prevPosX, prevPosY, prevPosZ, posX2, posY2, posZ2, enabled ? 1.0 : 0.0, isCollidedHorizontally ? 1.0 : 0.0, (double)fallDistance};
   }

   public static void updateState() {

      for (int index = 0; index < 51; index++) {
         MinecraftAccessor.minecraft
            .getNetHandlerPlayClient()
            .handlePacket2(
               new C04PacketPlayerPosition(
                  MinecraftAccessor.minecraft.thePlayer.posX,
                  MinecraftAccessor.minecraft.thePlayer.posY + 0.06,
                  MinecraftAccessor.minecraft.thePlayer.posZ,
                  false
               )
            );
         MinecraftAccessor.minecraft
            .getNetHandlerPlayClient()
            .handlePacket2(
               new C04PacketPlayerPosition(
                  MinecraftAccessor.minecraft.thePlayer.posX, MinecraftAccessor.minecraft.thePlayer.posY, MinecraftAccessor.minecraft.thePlayer.posZ, false
               )
            );
      }

      MinecraftAccessor.minecraft
         .getNetHandlerPlayClient()
         .handlePacket2(
            new C04PacketPlayerPosition(
               MinecraftAccessor.minecraft.thePlayer.posX, MinecraftAccessor.minecraft.thePlayer.posY, MinecraftAccessor.minecraft.thePlayer.posZ, false
            )
         );
      MinecraftAccessor.minecraft.getNetHandlerPlayClient().handlePacket2(new C03PacketPlayer(true));
   }

   public static double[] resolveDoubleArrayByEntityPlayerSP(int count, EntityPlayerSP player, float factor, boolean flag) {
      long value = 0x2a05b2d538faL;
      double posX = player.posX;
      double posY = player.posY;
      double posZ = player.posZ;
      EventMove event = new EventMove(
         MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.moveStrafe,
         MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.moveForward,
         MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.state,
         MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.state2,
         ArrowDodgerPhase.PRE
      );
      double offset = posZ;
      double offset2 = posY;
      double offset3 = posX;
      return onMoveByEntityPlayerSP(count, player, offset3, offset2, offset, factor, event, flag);
   }

   public static double[] resolveDoubleArrayByEntityLivingBase(EntityLivingBase livingEntity, int count) {
      double doubleItem = MathHelper.getDouble7(livingEntity.coordOffset5 - livingEntity.value12, -20.0, 20.0);
      double doubleItem2 = MathHelper.getDouble7(livingEntity.coordOffset20 - livingEntity.value20, -20.0, 20.0);
      double doubleItem3 = MathHelper.getDouble7(livingEntity.coordOffset2 - livingEntity.value10, -20.0, 20.0);
      double coordOffset = livingEntity.coordOffset5;
      double coordOffset2 = livingEntity.coordOffset20;
      double coordOffset3 = livingEntity.coordOffset2;
      double value = livingEntity.value12;
      double value2 = livingEntity.value20;
      double value3 = livingEntity.value10;
      float factor = livingEntity.width / 2.0F;
      AxisAlignedBB boundingBox = new AxisAlignedBB(
         coordOffset - (double)factor, coordOffset2, coordOffset3 - (double)factor, coordOffset + (double)factor, coordOffset2 + (double)livingEntity.height, coordOffset3 + (double)factor
      );
      boolean flag = SthWentMinecraftAccessor.matchesEntityLivingBase(livingEntity);
      Vec3 vec = null;
      boolean flag2 = !flag;
      if (livingEntity instanceof AbstractClientPlayer) {
         vec = new Vec3(0.0, 0.0, 1.0);
         doubleItem *= 0.91F;
         doubleItem2 *= 0.91F;
      }

      for (int index = 0; index <= count; index++) {
         value = coordOffset;
         value2 = coordOffset2;
         value3 = coordOffset3;
         float factor2 = 0.91F;
         if (livingEntity instanceof AbstractClientPlayer && vec != null) {
            AbstractClientPlayer player = (AbstractClientPlayer)livingEntity;
            float posZ = (float)vec.zCoord;
            float posX = (float)vec.xCoord;
            if (player.isUsingItem() && !player.isSneaking()) {
               posX *= 0.2F;
               posZ *= 0.2F;
            }

            double offset = ViaLoadingBase.processAction2();
            if (Math.abs(doubleItem) < offset) {
               doubleItem = 0.0;
            }

            if (Math.abs(doubleItem3) < offset) {
               doubleItem3 = 0.0;
            }

            if (Math.abs(doubleItem2) < offset) {
               doubleItem2 = 0.0;
            }

            if (flag2 && flag) {
               doubleItem3 = (double)player.getFloat6();
               if (player.checkPotion(Potion.potion9)) {
                  doubleItem3 += (double)((float)(player.getPotionEffect(Potion.potion9).getIndexOffset2() + 1) * 0.1F);
               }

               float factor3 = player.scaleFactor12 * (float) (Math.PI / 180.0);
               doubleItem -= (double)(MathHelper.abs(factor3) * 0.2F);
               doubleItem2 += (double)(MathHelper.cos(factor3) * 0.2F);
            }

            float factor4 = posX * 0.98F;
            float factor5 = posZ * 0.98F;
            if (flag) {
               factor2 = player.worldObj
                     .getBlockState(
                        new BlockPos(MathHelper.ceiling_double_int(coordOffset), MathHelper.ceiling_double_int(coordOffset2) - 1, MathHelper.ceiling_double_int(coordOffset3))
                     )
                     .getBlock()
                     .scaleFactor
                  * 0.91F;
            }

            float factor6 = 0.16277136F / (factor2 * factor2 * factor2);
            float factor7;
            if (flag) {
               factor7 = 0.13000001F * factor6;
            } else {
               factor7 = player.scaleFactor10;
            }

            float factor8 = factor4 * factor4 + factor5 * factor5;
            if (factor8 >= 1.0E-4F) {
               float factor9 = MathHelper.sin(factor8);
               if (factor9 < 1.0F) {
                  factor9 = 1.0F;
               }

               float factor10 = factor7 / factor9;
               float factor11 = factor4 * factor10;
               float factor12 = factor5 * factor10;
               float factor13 = MathHelper.abs(player.rotationYaw * (float) Math.PI / 180.0F);
               float factor14 = MathHelper.cos(player.rotationYaw * (float) Math.PI / 180.0F);
               doubleItem += (double)(factor11 * factor14 - factor12 * factor13);
               doubleItem2 += (double)(factor12 * factor14 + factor11 * factor13);
            }

            factor2 = 0.91F;
            if (flag) {
               factor2 = MinecraftAccessor.minecraft
                     .theWorld
                     .getBlockState(
                        new BlockPos(MathHelper.ceiling_double_int(coordOffset), MathHelper.ceiling_double_int(coordOffset2) - 1, MathHelper.ceiling_double_int(coordOffset3))
                     )
                     .getBlock()
                     .scaleFactor
                  * 0.91F;
            }
         } else {
            doubleItem = livingEntity.coordOffset5 - livingEntity.value12;
            doubleItem2 = livingEntity.coordOffset20 - livingEntity.value20;
         }

         double offset2 = doubleItem;
         double offset3 = livingEntity.scaleFactor21 < 75.0F
               && livingEntity instanceof EntityPlayer
               && ((EntityPlayer)livingEntity).getHeldItem() != null
               && ((EntityPlayer)livingEntity).getHeldItem().getItem() instanceof ItemBlock
            ? 0.0
            : doubleItem3;
         double offset4 = doubleItem2;
         double offset5 = offset3;
         List collidingBoundingBoxes = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox.contract(doubleItem, offset3, doubleItem2));
         AxisAlignedBB boundingBox2 = boundingBox;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset3 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox, offset3);
         }

         AxisAlignedBB boundingBox3 = boundingBox.expand(0.0, offset3, 0.0);
         boolean flag3 = flag || offset5 != offset3 && offset5 < 0.0;
         Iterator iterator2 = collidingBoundingBoxes.iterator();

         while (iterator2.hasNext()) {
            offset2 = ((AxisAlignedBB)iterator2.next()).getCoordinate2(boundingBox3, offset2);
         }

         AxisAlignedBB boundingBox4 = boundingBox3.expand(offset2, 0.0, 0.0);
         Iterator iterator3 = collidingBoundingBoxes.iterator();

         while (iterator3.hasNext()) {
            offset4 = ((AxisAlignedBB)iterator3.next()).getCoordinate(boundingBox4, offset4);
         }

         boundingBox = boundingBox4.expand(0.0, 0.0, offset4);
         if (livingEntity.stepHeight > 0.0F && flag3 && (doubleItem != offset2 || doubleItem2 != offset4)) {
            double offset6 = offset2;
            double offset7 = offset3;
            double offset8 = offset4;
            AxisAlignedBB boundingBox5 = boundingBox;
            double stepHeight = (double)livingEntity.stepHeight;
            List collidingBoundingBoxes2 = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.contract(doubleItem, stepHeight, doubleItem2));
            AxisAlignedBB boundingBox6 = boundingBox2.contract(doubleItem, 0.0, doubleItem2);
            double offset9 = stepHeight;
            Iterator iterator4 = collidingBoundingBoxes2.iterator();

            while (iterator4.hasNext()) {
               offset9 = ((AxisAlignedBB)iterator4.next()).getCoordinate3(boundingBox6, offset9);
            }

            AxisAlignedBB boundingBox7 = boundingBox2.expand(0.0, offset9, 0.0);
            offset2 = doubleItem;
            Iterator iterator5 = collidingBoundingBoxes2.iterator();

            while (iterator5.hasNext()) {
               offset2 = ((AxisAlignedBB)iterator5.next()).getCoordinate2(boundingBox7, offset2);
            }

            AxisAlignedBB boundingBox8 = boundingBox7.expand(offset2, 0.0, 0.0);
            offset4 = doubleItem2;
            Iterator iterator6 = collidingBoundingBoxes2.iterator();

            while (iterator6.hasNext()) {
               offset4 = ((AxisAlignedBB)iterator6.next()).getCoordinate(boundingBox8, offset4);
            }

            AxisAlignedBB boundingBox9 = boundingBox8.expand(0.0, 0.0, offset4);
            AxisAlignedBB boundingBox10 = boundingBox2;
            Iterator iterator7 = collidingBoundingBoxes2.iterator();

            while (iterator7.hasNext()) {
               stepHeight = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox10, stepHeight);
            }

            AxisAlignedBB boundingBox11 = boundingBox10.expand(0.0, stepHeight, 0.0);
            double offset10 = doubleItem;
            Iterator iterator8 = collidingBoundingBoxes2.iterator();

            while (iterator8.hasNext()) {
               offset10 = ((AxisAlignedBB)iterator8.next()).getCoordinate2(boundingBox11, offset10);
            }

            AxisAlignedBB boundingBox12 = boundingBox11.expand(offset10, 0.0, 0.0);
            double offset11 = doubleItem2;
            Iterator iterator9 = collidingBoundingBoxes2.iterator();

            while (iterator9.hasNext()) {
               offset11 = ((AxisAlignedBB)iterator9.next()).getCoordinate(boundingBox12, offset11);
            }

            AxisAlignedBB boundingBox13 = boundingBox12.expand(0.0, 0.0, offset11);
            double offset12 = offset2 * offset2 + offset4 * offset4;
            double offset13 = offset10 * offset10 + offset11 * offset11;
            AxisAlignedBB boundingBox14;
            if (offset12 > offset13) {
               offset3 = -offset9;
               boundingBox14 = boundingBox9;
            } else {
               offset2 = offset10;
               offset4 = offset11;
               offset3 = -stepHeight;
               boundingBox14 = boundingBox13;
            }

            Iterator iterator10 = collidingBoundingBoxes2.iterator();

            while (iterator10.hasNext()) {
               offset3 = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox14, offset3);
            }

            boundingBox = boundingBox14.expand(0.0, offset3, 0.0);
            if (offset6 * offset6 + offset8 * offset8 >= offset2 * offset2 + offset4 * offset4) {
               offset2 = offset6;
               offset3 = offset7;
               offset4 = offset8;
               boundingBox = boundingBox5;
            }
         }

         coordOffset = (boundingBox.minX + boundingBox.maxX) / 2.0;
         coordOffset2 = boundingBox.minY;
         coordOffset3 = (boundingBox.minZ + boundingBox.maxZ) / 2.0;
         boolean flag4 = doubleItem != offset2 || doubleItem2 != offset4;
         boolean flag5 = offset5 != offset3;
         flag = flag5 && offset5 < 0.0;
         if (!flag4 && !flag5) {
         } else {
         }

         int count2 = MathHelper.ceiling_double_int(coordOffset);
         int count3 = MathHelper.ceiling_double_int(coordOffset2 - 0.2F);
         int count4 = MathHelper.ceiling_double_int(coordOffset3);
         BlockPos blockPos = new BlockPos(count2, count3, count4);
         Block block = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (doubleItem != offset2) {
            doubleItem = 0.0;
         }

         if (doubleItem2 != offset4) {
            doubleItem2 = 0.0;
         }

         if (offset5 != offset3) {
            if (block instanceof BlockSlime) {
               if (livingEntity.isSprinting()) {
                  doubleItem3 = 0.0;
               } else if (doubleItem3 < 0.0) {
                  doubleItem3 = -doubleItem3;
               }
            } else {
               doubleItem3 = 0.0;
            }
         }

         double offset14;
         if (MinecraftAccessor.minecraft.theWorld.state
            && (
               !MinecraftAccessor.minecraft.theWorld.checkBlockPos12(new BlockPos((int)coordOffset, 0, (int)coordOffset3))
                  || !MinecraftAccessor.minecraft.theWorld.getLoggerEntry(new BlockPos((int)coordOffset, 0, (int)coordOffset3)).canExecute2()
            )) {
            if (coordOffset2 > 0.0) {
               offset14 = -0.1;
            } else {
               offset14 = 0.0;
            }
         } else {
            offset14 = doubleItem3 - 0.08;
         }

         doubleItem3 = offset14 * 0.98F;
         if (livingEntity instanceof AbstractClientPlayer && vec != null) {
            doubleItem *= (double)factor2;
            doubleItem2 *= (double)factor2;
         }
      }

      return new double[]{coordOffset, coordOffset2, coordOffset3, value, value2, value3, doubleItem, doubleItem3, doubleItem2, flag ? 1.0 : 0.0};
   }

   public static boolean matchesPotion(Potion potion) {
      return MinecraftAccessor.minecraft.thePlayer.getPotionEffect(potion) != null;
   }

   public static double[] getDoubleArray() {
      return new double[]{
         MinecraftAccessor.minecraft.thePlayer.posX,
         MinecraftAccessor.minecraft.thePlayer.posY,
         MinecraftAccessor.minecraft.thePlayer.posZ,
         MinecraftAccessor.minecraft.thePlayer.prevPosX,
         MinecraftAccessor.minecraft.thePlayer.prevPosY,
         MinecraftAccessor.minecraft.thePlayer.prevPosZ,
         MinecraftAccessor.minecraft.thePlayer.motionX,
         MinecraftAccessor.minecraft.thePlayer.motionY,
         MinecraftAccessor.minecraft.thePlayer.motionZ,
         MinecraftAccessor.minecraft.thePlayer.onGround ? 1.0 : 0.0,
         MinecraftAccessor.minecraft.thePlayer.isCollidedHorizontally ? 1.0 : 0.0,
         (double)MinecraftAccessor.minecraft.thePlayer.fallDistance
      };
   }

   public static boolean checkItemStack(ItemStack stack, BlockPos blockPos, EnumFacing enumFacing, Vec3 vec) {
      if (vec != null && blockPos != null && enumFacing != null) {
         float factor = (float)(vec.xCoord - (double)blockPos.getX());
         float factor2 = (float)(vec.yCoord - (double)blockPos.getY());
         float factor3 = (float)(vec.zCoord - (double)blockPos.getZ());
         boolean flag = false;
         if (MinecraftAccessor.minecraft.theWorld.getInternalListEntry().checkBlockPos(blockPos)
            && (blockPos.getY() != 255 || enumFacing != EnumFacing.UP)
            && (blockPos.getY() != 0 || enumFacing != EnumFacing.DOWN)) {
            if (MinecraftAccessor.minecraft.playerController.getWorldSettingsGameType() != WorldSettingsGameType.ADVENTURE) {
               IBlockState blockState = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos);
               if ((!MinecraftAccessor.minecraft.thePlayer.isSprinting() || MinecraftAccessor.minecraft.thePlayer.getItemStack() == null)
                  && blockState.getBlock()
                     .processAction(MinecraftAccessor.minecraft.theWorld, blockPos, blockState, MinecraftAccessor.minecraft.thePlayer, enumFacing, factor, factor2, factor3)) {
                  flag = true;
               }

               if (!flag
                  && stack != null
                  && stack.getItem() instanceof ItemBlock
                  && !((ItemBlock)stack.getItem()).matchesWorld(MinecraftAccessor.minecraft.theWorld, blockPos, enumFacing, MinecraftAccessor.minecraft.thePlayer, stack)) {
                  return false;
               }
            }

            return stack != null && !flag && MinecraftAccessor.minecraft.thePlayer.matchesBlockPos(blockPos, enumFacing, stack);
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static ArrayList onMove(int count, EntityPlayerSP player, double posX, double posX2, double posY, double posZ, EventMove event) {
      double offset = posX;
      double offset2 = posX2;
      double offset3 = posY;
      double offset4 = posZ;
      ArrayList list = new ArrayList();
      double motionX = player.motionX;
      double motionZ = player.motionZ;
      boolean state = event.isState();
      int indexOffset = player.getIndexOffset18();
      boolean onGround = player.onGround;
      event.getScaleFactor2();
      event.getScaleFactor7();
      AxisAlignedBB boundingBox = player.getBoundingBox2();
      AxisAlignedBB boundingBox2 = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      boolean riding = player.isRiding();
      float fallDistance = player.fallDistance;

      for (int index = 0; index <= count; index++) {
         float scaleFactor = event.getScaleFactor2();
         float scaleFactor2 = event.getScaleFactor7();
         EventCameraTransform eventCameraTransform = EventCameraTransformCallback.getEventCameraTransform(0.2F, 0.2F);
         if (player.isUsingItem() && !player.isSneaking()) {
            scaleFactor *= eventCameraTransform.getScaleFactor2();
            scaleFactor2 *= eventCameraTransform.getScaleFactor();
         }

         if (indexOffset > 0) {
            indexOffset--;
         }

         double offset5 = ViaLoadingBase.processAction2();
         if (Math.abs(motionX) < offset5) {
            motionX = 0.0;
         }

         if (Math.abs(offset4) < offset5) {
            offset4 = 0.0;
         }

         if (Math.abs(motionZ) < offset5) {
            motionZ = 0.0;
         }

         if (state) {
            if (onGround && indexOffset == 0) {
               offset4 = (double)player.getFloat6();
               if (player.checkPotion(Potion.potion9)) {
                  offset4 += (double)((float)(player.getPotionEffect(Potion.potion9).getIndexOffset2() + 1) * 0.1F);
               }

               if (riding) {
                  float factor = player.rotationYaw * (float) (Math.PI / 180.0);
                  motionX -= (double)(MathHelper.abs(factor) * 0.2F);
                  motionZ += (double)(MathHelper.cos(factor) * 0.2F);
               }

               indexOffset = 10;
            }
         } else {
            indexOffset = 0;
         }

         float factor2 = scaleFactor * 0.98F;
         float factor3 = scaleFactor2 * 0.98F;
         float factor4 = 0.91F;
         if (onGround) {
            factor4 = player.worldObj
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(offset), MathHelper.ceiling_double_int(offset2) - 1, MathHelper.ceiling_double_int(offset3))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         float factor5 = 0.16277136F / (factor4 * factor4 * factor4);
         float factor6;
         if (onGround) {
            factor6 = player.getScaleFactor6() * factor5;
         } else {
            factor6 = player.scaleFactor10;
         }

         EventRotation event2 = RotationListener.resolveEventRotation(player.rotationYaw, factor3, factor2, factor6, BugUpMoveFlyingType.MOVE_FLYING);
         float factor7 = factor2 * factor2 + factor3 * factor3;
         if (factor7 >= 1.0E-4F) {
            float factor8 = MathHelper.sin(factor7);
            if (factor8 < 1.0F) {
               factor8 = 1.0F;
            }

            float factor9 = factor6 / factor8;
            float factor10 = factor2 * factor9;
            float factor11 = factor3 * factor9;
            float factor12 = MathHelper.abs(event2.getScaleFactor() * (float) Math.PI / 180.0F);
            float factor13 = MathHelper.cos(event2.getScaleFactor() * (float) Math.PI / 180.0F);
            motionX += (double)(factor10 * factor13 - factor11 * factor12);
            motionZ += (double)(factor11 * factor13 + factor10 * factor12);
         }

         float factor14 = 0.91F;
         if (onGround) {
            factor14 = MinecraftAccessor.minecraft
                  .theWorld
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(offset), MathHelper.ceiling_double_int(offset2) - 1, MathHelper.ceiling_double_int(offset3))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         double offset6 = motionX;
         double offset7 = offset4;
         double offset8 = motionZ;
         double offset9 = motionX;
         double offset10 = motionZ;
         boolean sprinting = player.isSprinting();
         if (onGround && sprinting) {
            double offset11;
            for (offset11 = 0.05;
               offset6 != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox2.expand(offset6, -1.0, 0.0)).isEmpty();
               offset9 = offset6
            ) {
               if (offset6 < offset11 && offset6 >= -offset11) {
                  offset6 = 0.0;
               } else if (offset6 > 0.0) {
                  offset6 -= offset11;
               } else {
                  offset6 += offset11;
               }
            }

            for (;
               offset8 != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox2.expand(0.0, -1.0, offset8)).isEmpty();
               offset10 = offset8
            ) {
               if (offset8 < offset11 && offset8 >= -offset11) {
                  offset8 = 0.0;
               } else if (offset8 > 0.0) {
                  offset8 -= offset11;
               } else {
                  offset8 += offset11;
               }
            }

            for (;
               offset6 != 0.0 && offset8 != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox2.expand(offset6, -1.0, offset8)).isEmpty();
               offset10 = offset8
            ) {
               if (offset6 < offset11 && offset6 >= -offset11) {
                  offset6 = 0.0;
               } else if (offset6 > 0.0) {
                  offset6 -= offset11;
               } else {
                  offset6 += offset11;
               }

               offset9 = offset6;
               if (offset8 < offset11 && offset8 >= -offset11) {
                  offset8 = 0.0;
               } else if (offset8 > 0.0) {
                  offset8 -= offset11;
               } else {
                  offset8 += offset11;
               }
            }
         }

         List collidingBoundingBoxes = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox2.contract(offset6, offset4, offset8));
         AxisAlignedBB boundingBox3 = boundingBox2;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset7 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox2, offset7);
         }

         AxisAlignedBB boundingBox4 = boundingBox2.expand(0.0, offset7, 0.0);
         boolean flag = onGround || offset4 != offset7 && offset4 < 0.0;
         Iterator iterator2 = collidingBoundingBoxes.iterator();

         while (iterator2.hasNext()) {
            offset6 = ((AxisAlignedBB)iterator2.next()).getCoordinate2(boundingBox4, offset6);
         }

         AxisAlignedBB boundingBox5 = boundingBox4.expand(offset6, 0.0, 0.0);
         Iterator iterator3 = collidingBoundingBoxes.iterator();

         while (iterator3.hasNext()) {
            offset8 = ((AxisAlignedBB)iterator3.next()).getCoordinate(boundingBox5, offset8);
         }

         boundingBox2 = boundingBox5.expand(0.0, 0.0, offset8);
         EventTickState eventTickState = EventTickStateCallback.getEventTickState(player.stepHeight, StateTypes.PRE);
         if (eventTickState.getScaleFactor() > 0.0F && flag && (offset9 != offset6 || offset10 != offset8)) {
            double offset12 = offset6;
            double offset13 = offset7;
            double offset14 = offset8;
            AxisAlignedBB boundingBox6 = boundingBox2;
            double scaleFactor3 = (double)eventTickState.getScaleFactor();
            List collidingBoundingBoxes2 = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox3.contract(offset9, scaleFactor3, offset10));
            AxisAlignedBB boundingBox7 = boundingBox3.contract(offset9, 0.0, offset10);
            double offset15 = scaleFactor3;
            Iterator iterator4 = collidingBoundingBoxes2.iterator();

            while (iterator4.hasNext()) {
               offset15 = ((AxisAlignedBB)iterator4.next()).getCoordinate3(boundingBox7, offset15);
            }

            AxisAlignedBB boundingBox8 = boundingBox3.expand(0.0, offset15, 0.0);
            offset6 = offset9;
            Iterator iterator5 = collidingBoundingBoxes2.iterator();

            while (iterator5.hasNext()) {
               offset6 = ((AxisAlignedBB)iterator5.next()).getCoordinate2(boundingBox8, offset6);
            }

            AxisAlignedBB boundingBox9 = boundingBox8.expand(offset6, 0.0, 0.0);
            offset8 = offset10;
            Iterator iterator6 = collidingBoundingBoxes2.iterator();

            while (iterator6.hasNext()) {
               offset8 = ((AxisAlignedBB)iterator6.next()).getCoordinate(boundingBox9, offset8);
            }

            AxisAlignedBB boundingBox10 = boundingBox9.expand(0.0, 0.0, offset8);
            AxisAlignedBB boundingBox11 = boundingBox3;
            Iterator iterator7 = collidingBoundingBoxes2.iterator();

            while (iterator7.hasNext()) {
               scaleFactor3 = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox11, scaleFactor3);
            }

            AxisAlignedBB boundingBox12 = boundingBox11.expand(0.0, scaleFactor3, 0.0);
            double offset16 = offset9;
            Iterator iterator8 = collidingBoundingBoxes2.iterator();

            while (iterator8.hasNext()) {
               offset16 = ((AxisAlignedBB)iterator8.next()).getCoordinate2(boundingBox12, offset16);
            }

            AxisAlignedBB boundingBox13 = boundingBox12.expand(offset16, 0.0, 0.0);
            double offset17 = offset10;
            Iterator iterator9 = collidingBoundingBoxes2.iterator();

            while (iterator9.hasNext()) {
               offset17 = ((AxisAlignedBB)iterator9.next()).getCoordinate(boundingBox13, offset17);
            }

            AxisAlignedBB boundingBox14 = boundingBox13.expand(0.0, 0.0, offset17);
            double offset18 = offset6 * offset6 + offset8 * offset8;
            double offset19 = offset16 * offset16 + offset17 * offset17;
            AxisAlignedBB boundingBox15;
            if (offset18 > offset19) {
               offset7 = -offset15;
               boundingBox15 = boundingBox10;
            } else {
               offset6 = offset16;
               offset8 = offset17;
               offset7 = -scaleFactor3;
               boundingBox15 = boundingBox14;
            }

            Iterator iterator10 = collidingBoundingBoxes2.iterator();

            while (iterator10.hasNext()) {
               offset7 = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox15, offset7);
            }

            boundingBox2 = boundingBox15.expand(0.0, offset7, 0.0);
            if (offset12 * offset12 + offset14 * offset14 >= offset6 * offset6 + offset8 * offset8) {
               offset6 = offset12;
               offset7 = offset13;
               offset8 = offset14;
               boundingBox2 = boundingBox6;
            }
         }

         offset = (boundingBox2.minX + boundingBox2.maxX) / 2.0;
         offset2 = boundingBox2.minY;
         offset3 = (boundingBox2.minZ + boundingBox2.maxZ) / 2.0;
         boolean flag2 = offset9 != offset6 || offset10 != offset8;
         boolean flag3 = offset4 != offset7;
         onGround = flag3 && offset4 < 0.0;
         if (!flag2 && !flag3) {
         } else {
         }

         int count2 = MathHelper.ceiling_double_int(offset);
         int count3 = MathHelper.ceiling_double_int(offset2 - 0.2F);
         int count4 = MathHelper.ceiling_double_int(offset3);
         BlockPos blockPos = new BlockPos(count2, count3, count4);
         Block block = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (onGround) {
            if (fallDistance > 0.0F) {
               fallDistance = 0.0F;
            }
         } else if (offset7 < 0.0) {
            fallDistance = (float)((double)fallDistance - offset7);
         }

         if (offset9 != offset6) {
            motionX = 0.0;
         }

         if (offset10 != offset8) {
            motionZ = 0.0;
         }

         if (offset4 != offset7) {
            if (block instanceof BlockSlime) {
               if (player.isSprinting()) {
                  offset4 = 0.0;
               } else if (offset4 < 0.0) {
                  offset4 = -offset4;
               }
            } else {
               offset4 = 0.0;
            }
         }

         if (player.worldObj.state
            && (
               !player.worldObj.checkBlockPos12(new BlockPos((int)offset, 0, (int)offset3))
                  || !player.worldObj.getLoggerEntry(new BlockPos((int)offset, 0, (int)offset3)).canExecute2()
            )) {
            if (offset2 > 0.0) {
               offset4 = -0.1;
            } else {
               offset4 = 0.0;
            }
         } else {
            offset4 -= 0.08;
         }

         offset4 *= 0.98F;
         motionX *= (double)factor14;
         motionZ *= (double)factor14;
         list.add(new Vec3(offset, offset2, offset3));
      }

      return list;
   }

   public static void applyDoubleArray(double[] offsetArray) {
      MinecraftAccessor.minecraft.thePlayer.processDouble2(offsetArray[0], offsetArray[1], offsetArray[2]);
      MinecraftAccessor.minecraft.thePlayer.prevPosX = offsetArray[3];
      MinecraftAccessor.minecraft.thePlayer.prevPosY = offsetArray[4];
      MinecraftAccessor.minecraft.thePlayer.prevPosZ = offsetArray[5];
      MinecraftAccessor.minecraft.thePlayer.motionX = offsetArray[6];
      MinecraftAccessor.minecraft.thePlayer.motionY = offsetArray[7];
      MinecraftAccessor.minecraft.thePlayer.motionZ = offsetArray[8];
      MinecraftAccessor.minecraft.thePlayer.onGround = offsetArray[9] == 1.0;
      MinecraftAccessor.minecraft.thePlayer.isCollidedHorizontally = offsetArray[10] == 1.0;
      MinecraftAccessor.minecraft.thePlayer.fallDistance = (float)offsetArray[11];
   }

   public static double[] getCoordOffsetArray(int count, EntityPlayerSP player, float factor) {
      long value = 0x76aebe6c4bb1L;
      double posX = player.posX;
      double posY = player.posY;
      double posZ = player.posZ;
      EventMove event = new EventMove(
         MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.moveStrafe,
         MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.moveForward,
         MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.state,
         MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.state2,
         ArrowDodgerPhase.PRE
      );
      boolean flag = false;
      EventMove event2 = event;
      double offset = posZ;
      double offset2 = posY;
      double offset3 = posX;
      return onMoveByEntityPlayerSP(count, player, offset3, offset2, offset, factor, event2, flag);
   }

   public static double[] getCoordOffsetArray(EntityLivingBase livingEntity, int count) {
      long value = 0x144242acf703L;
      int count2 = (int)((0x223a9fcaca27L ^ value ^ 0x7e9c54374f35L) << 16 >>> 32);
      int count3 = (int)((0x223a9fcaca27L ^ value ^ 0x7e9c54374f35L) << 48 >>> 48);
      if (CombatTargetFilter.checkEntityLivingBase(count2, count3, livingEntity)) {
         return new double[]{
            livingEntity.posX,
            livingEntity.posY,
            livingEntity.posZ,
            livingEntity.lastTickPosX,
            livingEntity.lastTickPosY,
            livingEntity.lastTickPosZ,
            livingEntity.motionX,
            livingEntity.motionY,
            livingEntity.motionZ,
            livingEntity.onGround ? 1.0 : 0.0
         };
      } else {
         double doubleItem = MathHelper.getDouble7(livingEntity.coordOffset5 - livingEntity.value12, -20.0, 20.0);
         double doubleItem2 = MathHelper.getDouble7(livingEntity.coordOffset20 - livingEntity.value20, -20.0, 20.0);
         double doubleItem3 = MathHelper.getDouble7(livingEntity.coordOffset2 - livingEntity.value10, -20.0, 20.0);
         double coordOffset = livingEntity.coordOffset5;
         double coordOffset2 = livingEntity.coordOffset20;
         double coordOffset3 = livingEntity.coordOffset2;
         double value2 = livingEntity.value12;
         double value3 = livingEntity.value20;
         double value4 = livingEntity.value10;
         float factor = livingEntity.width / 2.0F;
         AxisAlignedBB boundingBox = new AxisAlignedBB(
            coordOffset - (double)factor, coordOffset2, coordOffset3 - (double)factor, coordOffset + (double)factor, coordOffset2 + (double)livingEntity.height, coordOffset3 + (double)factor
         );
         boolean onGround = livingEntity.onGround;
         if (livingEntity.ticksExisted < 20) {
            return new double[]{coordOffset, coordOffset2, coordOffset3, value2, value3, value4, doubleItem, doubleItem3, doubleItem2, onGround ? 1.0 : 0.0};
         } else {
            for (int index = 0; index <= count; index++) {
               value2 = coordOffset;
               value3 = coordOffset2;
               value4 = coordOffset3;
               doubleItem = livingEntity.coordOffset5 - livingEntity.value12;
               doubleItem2 = livingEntity.coordOffset20 - livingEntity.value20;
               double offset = doubleItem;
               double offset2 = livingEntity.scaleFactor21 < 75.0F
                     && livingEntity instanceof EntityPlayer
                     && ((EntityPlayer)livingEntity).getHeldItem() != null
                     && ((EntityPlayer)livingEntity).getHeldItem().getItem() instanceof ItemBlock
                  ? 0.0
                  : doubleItem3;
               double offset3 = doubleItem2;
               double offset4 = offset2;
               List collidingBoundingBoxes = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox.contract(doubleItem, offset2, doubleItem2));
               AxisAlignedBB boundingBox2 = boundingBox;
               Iterator iterator = collidingBoundingBoxes.iterator();

               while (iterator.hasNext()) {
                  offset2 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox, offset2);
               }

               AxisAlignedBB boundingBox3 = boundingBox.expand(0.0, offset2, 0.0);
               boolean flag = onGround || offset4 != offset2 && offset4 < 0.0;
               Iterator iterator2 = collidingBoundingBoxes.iterator();

               while (iterator2.hasNext()) {
                  offset = ((AxisAlignedBB)iterator2.next()).getCoordinate2(boundingBox3, offset);
               }

               AxisAlignedBB boundingBox4 = boundingBox3.expand(offset, 0.0, 0.0);
               Iterator iterator3 = collidingBoundingBoxes.iterator();

               while (iterator3.hasNext()) {
                  offset3 = ((AxisAlignedBB)iterator3.next()).getCoordinate(boundingBox4, offset3);
               }

               boundingBox = boundingBox4.expand(0.0, 0.0, offset3);
               if (livingEntity.stepHeight > 0.0F && flag && (doubleItem != offset || doubleItem2 != offset3)) {
                  double offset5 = offset;
                  double offset6 = offset2;
                  double offset7 = offset3;
                  AxisAlignedBB boundingBox5 = boundingBox;
                  double stepHeight = (double)livingEntity.stepHeight;
                  List collidingBoundingBoxes2 = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.contract(doubleItem, stepHeight, doubleItem2));
                  AxisAlignedBB boundingBox6 = boundingBox2.contract(doubleItem, 0.0, doubleItem2);
                  double offset8 = stepHeight;
                  Iterator iterator4 = collidingBoundingBoxes2.iterator();

                  while (iterator4.hasNext()) {
                     offset8 = ((AxisAlignedBB)iterator4.next()).getCoordinate3(boundingBox6, offset8);
                  }

                  AxisAlignedBB boundingBox7 = boundingBox2.expand(0.0, offset8, 0.0);
                  offset = doubleItem;
                  Iterator iterator5 = collidingBoundingBoxes2.iterator();

                  while (iterator5.hasNext()) {
                     offset = ((AxisAlignedBB)iterator5.next()).getCoordinate2(boundingBox7, offset);
                  }

                  AxisAlignedBB boundingBox8 = boundingBox7.expand(offset, 0.0, 0.0);
                  offset3 = doubleItem2;
                  Iterator iterator6 = collidingBoundingBoxes2.iterator();

                  while (iterator6.hasNext()) {
                     offset3 = ((AxisAlignedBB)iterator6.next()).getCoordinate(boundingBox8, offset3);
                  }

                  AxisAlignedBB boundingBox9 = boundingBox8.expand(0.0, 0.0, offset3);
                  AxisAlignedBB boundingBox10 = boundingBox2;
                  Iterator iterator7 = collidingBoundingBoxes2.iterator();

                  while (iterator7.hasNext()) {
                     stepHeight = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox10, stepHeight);
                  }

                  AxisAlignedBB boundingBox11 = boundingBox10.expand(0.0, stepHeight, 0.0);
                  double offset9 = doubleItem;
                  Iterator iterator8 = collidingBoundingBoxes2.iterator();

                  while (iterator8.hasNext()) {
                     offset9 = ((AxisAlignedBB)iterator8.next()).getCoordinate2(boundingBox11, offset9);
                  }

                  AxisAlignedBB boundingBox12 = boundingBox11.expand(offset9, 0.0, 0.0);
                  double offset10 = doubleItem2;
                  Iterator iterator9 = collidingBoundingBoxes2.iterator();

                  while (iterator9.hasNext()) {
                     offset10 = ((AxisAlignedBB)iterator9.next()).getCoordinate(boundingBox12, offset10);
                  }

                  AxisAlignedBB boundingBox13 = boundingBox12.expand(0.0, 0.0, offset10);
                  double offset11 = offset * offset + offset3 * offset3;
                  double offset12 = offset9 * offset9 + offset10 * offset10;
                  AxisAlignedBB boundingBox14;
                  if (offset11 > offset12) {
                     offset2 = -offset8;
                     boundingBox14 = boundingBox9;
                  } else {
                     offset = offset9;
                     offset3 = offset10;
                     offset2 = -stepHeight;
                     boundingBox14 = boundingBox13;
                  }

                  Iterator iterator10 = collidingBoundingBoxes2.iterator();

                  while (iterator10.hasNext()) {
                     offset2 = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox14, offset2);
                  }

                  boundingBox = boundingBox14.expand(0.0, offset2, 0.0);
                  if (offset5 * offset5 + offset7 * offset7 >= offset * offset + offset3 * offset3) {
                     offset = offset5;
                     offset2 = offset6;
                     offset3 = offset7;
                     boundingBox = boundingBox5;
                  }
               }

               coordOffset = (boundingBox.minX + boundingBox.maxX) / 2.0;
               coordOffset2 = boundingBox.minY;
               coordOffset3 = (boundingBox.minZ + boundingBox.maxZ) / 2.0;
               boolean flag2 = doubleItem != offset || doubleItem2 != offset3;
               boolean flag3 = offset4 != offset2;
               onGround = flag3 && offset4 < 0.0;
               if (!flag2 && !flag3) {
               } else {
               }

               int count4 = MathHelper.ceiling_double_int(coordOffset);
               int count5 = MathHelper.ceiling_double_int(coordOffset2 - 0.2F);
               int count6 = MathHelper.ceiling_double_int(coordOffset3);
               BlockPos blockPos = new BlockPos(count4, count5, count6);
               Block block = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock();
               if (block.getMaterial() == Material.coreItemEntry) {
                  Block block2 = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
                  if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
                     block = block2;
                     blockPos.down();
                  }
               }

               if (doubleItem != offset) {
                  doubleItem = 0.0;
               }

               if (doubleItem2 != offset3) {
                  doubleItem2 = 0.0;
               }

               if (offset4 != offset2) {
                  if (block instanceof BlockSlime) {
                     if (livingEntity.isSprinting()) {
                        doubleItem3 = 0.0;
                     } else if (doubleItem3 < 0.0) {
                        doubleItem3 = -doubleItem3;
                     }
                  } else {
                     doubleItem3 = 0.0;
                  }
               }

               double offset13;
               if (MinecraftAccessor.minecraft.theWorld.state
                  && (
                     !MinecraftAccessor.minecraft.theWorld.checkBlockPos12(new BlockPos((int)coordOffset, 0, (int)coordOffset3))
                        || !MinecraftAccessor.minecraft.theWorld.getLoggerEntry(new BlockPos((int)coordOffset, 0, (int)coordOffset3)).canExecute2()
                  )) {
                  if (coordOffset2 > 0.0) {
                     offset13 = -0.1;
                  } else {
                     offset13 = 0.0;
                  }
               } else {
                  offset13 = doubleItem3 - 0.08;
               }

               doubleItem3 = offset13 * 0.98F;
            }

            return new double[]{coordOffset, coordOffset2, coordOffset3, value2, value3, value4, doubleItem, doubleItem3, doubleItem2, onGround ? 1.0 : 0.0};
         }
      }
   }

   public static Vec3 resolveVec3(boolean flag, Entity entity, float factor) {
      double offset = -1337.0;
      return resolveVec3(flag, entity, offset, factor);
   }

   public static double[] onMove(int count, EntityPlayerSP player, double posX, double posY, double posZ, float factor, EventMove event) {
      return onMoveByEntityPlayerSP(count, player, posX, posY, posZ, factor, event, false);
   }

   public static double[] getCoordOffsetArray(int count, EntityPlayerSP player, float[] factorArray, float[] factorArray2) {
      long value = 0xab758941a40L;
      double posZ = player.posZ;
      double posY = player.posY;
      double posX = player.posX;
      return resolveDoubleArray(count, player, posX, posY, posZ, factorArray, factorArray2);
   }

   public static double[] resolveDoubleArray(int count, EntityPlayerSP player, double posX, double posY, double posZ, float[] factorArray, float[] factorArray2) {
      double offset = posX;
      double offset2 = posY;
      double offset3 = posZ;
      double motionX = player.motionX;
      double motionY = player.motionY;
      double motionZ = player.motionZ;
      boolean state = player.state31;
      double prevPosX = player.prevPosX;
      double prevPosY = player.prevPosY;
      double prevPosZ = player.prevPosZ;
      int indexOffset = player.getIndexOffset18();
      boolean onGround = player.onGround;
      AxisAlignedBB boundingBox = player.getBoundingBox2();
      AxisAlignedBB boundingBox2 = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      boolean isCollidedHorizontally = player.isCollidedHorizontally;
      boolean riding = player.isRiding();

      for (int index = 0; index <= count; index++) {
         prevPosX = offset;
         prevPosY = offset2;
         prevPosZ = offset3;
         float factor = factorArray2[index];
         float factor2 = factorArray[index];
         EventCameraTransform eventCameraTransform = EventCameraTransformCallback.getEventCameraTransform(0.2F, 0.2F);
         if (player.isUsingItem() && !player.isSneaking()) {
            factor *= eventCameraTransform.getScaleFactor2();
            factor2 *= eventCameraTransform.getScaleFactor();
         }

         if (indexOffset > 0) {
            indexOffset--;
         }

         double offset4 = ViaLoadingBase.processAction2();
         if (Math.abs(motionX) < offset4) {
            motionX = 0.0;
         }

         if (Math.abs(motionY) < offset4) {
            motionY = 0.0;
         }

         if (Math.abs(motionZ) < offset4) {
            motionZ = 0.0;
         }

         if (state) {
            if (onGround && indexOffset == 0) {
               motionY = (double)player.getFloat6();
               if (player.checkPotion(Potion.potion9)) {
                  motionY += (double)((float)(player.getPotionEffect(Potion.potion9).getIndexOffset2() + 1) * 0.1F);
               }

               if (riding) {
                  float factor3 = player.rotationYaw * (float) (Math.PI / 180.0);
                  motionX -= (double)(MathHelper.abs(factor3) * 0.2F);
                  motionZ += (double)(MathHelper.cos(factor3) * 0.2F);
               }

               indexOffset = 10;
            }
         } else {
            indexOffset = 0;
         }

         float factor4 = factor * 0.98F;
         float factor5 = factor2 * 0.98F;
         float factor6 = 0.91F;
         if (onGround) {
            factor6 = player.worldObj
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(offset), MathHelper.ceiling_double_int(offset2) - 1, MathHelper.ceiling_double_int(offset3))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         float factor7 = 0.16277136F / (factor6 * factor6 * factor6);
         float factor8;
         if (onGround) {
            factor8 = player.getScaleFactor6() * factor7;
         } else {
            factor8 = player.scaleFactor10;
         }

         EventRotation event = RotationListener.resolveEventRotation(player.rotationYaw, factor5, factor4, factor8, BugUpMoveFlyingType.MOVE_FLYING);
         float factor9 = factor4 * factor4 + factor5 * factor5;
         if (factor9 >= 1.0E-4F) {
            float factor10 = MathHelper.sin(factor9);
            if (factor10 < 1.0F) {
               factor10 = 1.0F;
            }

            float factor11 = factor8 / factor10;
            float factor12 = factor4 * factor11;
            float factor13 = factor5 * factor11;
            float factor14 = MathHelper.abs(event.getScaleFactor() * (float) Math.PI / 180.0F);
            float factor15 = MathHelper.cos(event.getScaleFactor() * (float) Math.PI / 180.0F);
            motionX += (double)(factor12 * factor15 - factor13 * factor14);
            motionZ += (double)(factor13 * factor15 + factor12 * factor14);
         }

         float factor16 = 0.91F;
         if (onGround) {
            factor16 = MinecraftAccessor.minecraft
                  .theWorld
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(offset), MathHelper.ceiling_double_int(offset2) - 1, MathHelper.ceiling_double_int(offset3))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         double offset5 = motionX;
         double offset6 = motionY;
         double offset7 = motionZ;
         double offset8 = motionX;
         double offset9 = motionZ;
         boolean sprinting = player.isSprinting();
         if (player.onGround && sprinting) {
            double offset10;
            for (offset10 = 0.05;
               offset5 != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox2.expand(offset5, -1.0, 0.0)).isEmpty();
               offset8 = offset5
            ) {
               if (offset5 < offset10 && offset5 >= -offset10) {
                  offset5 = 0.0;
               } else if (offset5 > 0.0) {
                  offset5 -= offset10;
               } else {
                  offset5 += offset10;
               }
            }

            for (;
               offset7 != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox2.expand(0.0, -1.0, offset7)).isEmpty();
               offset9 = offset7
            ) {
               if (offset7 < offset10 && offset7 >= -offset10) {
                  offset7 = 0.0;
               } else if (offset7 > 0.0) {
                  offset7 -= offset10;
               } else {
                  offset7 += offset10;
               }
            }

            for (;
               offset5 != 0.0 && offset7 != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox2.expand(offset5, -1.0, offset7)).isEmpty();
               offset9 = offset7
            ) {
               if (offset5 < offset10 && offset5 >= -offset10) {
                  offset5 = 0.0;
               } else if (offset5 > 0.0) {
                  offset5 -= offset10;
               } else {
                  offset5 += offset10;
               }

               offset8 = offset5;
               if (offset7 < offset10 && offset7 >= -offset10) {
                  offset7 = 0.0;
               } else if (offset7 > 0.0) {
                  offset7 -= offset10;
               } else {
                  offset7 += offset10;
               }
            }
         }

         List collidingBoundingBoxes = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox2.contract(offset5, motionY, offset7));
         AxisAlignedBB boundingBox3 = boundingBox2;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset6 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox2, offset6);
         }

         AxisAlignedBB boundingBox4 = boundingBox2.expand(0.0, offset6, 0.0);
         boolean flag = onGround || motionY != offset6 && motionY < 0.0;
         Iterator iterator2 = collidingBoundingBoxes.iterator();

         while (iterator2.hasNext()) {
            offset5 = ((AxisAlignedBB)iterator2.next()).getCoordinate2(boundingBox4, offset5);
         }

         AxisAlignedBB boundingBox5 = boundingBox4.expand(offset5, 0.0, 0.0);
         Iterator iterator3 = collidingBoundingBoxes.iterator();

         while (iterator3.hasNext()) {
            offset7 = ((AxisAlignedBB)iterator3.next()).getCoordinate(boundingBox5, offset7);
         }

         boundingBox2 = boundingBox5.expand(0.0, 0.0, offset7);
         EventTickState eventTickState = EventTickStateCallback.getEventTickState(player.stepHeight, StateTypes.PRE);
         if (eventTickState.getScaleFactor() > 0.0F && flag && (offset8 != offset5 || offset9 != offset7)) {
            double offset11 = offset5;
            double offset12 = offset6;
            double offset13 = offset7;
            AxisAlignedBB boundingBox6 = boundingBox2;
            double scaleFactor = (double)eventTickState.getScaleFactor();
            List collidingBoundingBoxes2 = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox3.contract(offset8, scaleFactor, offset9));
            AxisAlignedBB boundingBox7 = boundingBox3.contract(offset8, 0.0, offset9);
            double offset14 = scaleFactor;
            Iterator iterator4 = collidingBoundingBoxes2.iterator();

            while (iterator4.hasNext()) {
               offset14 = ((AxisAlignedBB)iterator4.next()).getCoordinate3(boundingBox7, offset14);
            }

            AxisAlignedBB boundingBox8 = boundingBox3.expand(0.0, offset14, 0.0);
            offset5 = offset8;
            Iterator iterator5 = collidingBoundingBoxes2.iterator();

            while (iterator5.hasNext()) {
               offset5 = ((AxisAlignedBB)iterator5.next()).getCoordinate2(boundingBox8, offset5);
            }

            AxisAlignedBB boundingBox9 = boundingBox8.expand(offset5, 0.0, 0.0);
            offset7 = offset9;
            Iterator iterator6 = collidingBoundingBoxes2.iterator();

            while (iterator6.hasNext()) {
               offset7 = ((AxisAlignedBB)iterator6.next()).getCoordinate(boundingBox9, offset7);
            }

            AxisAlignedBB boundingBox10 = boundingBox9.expand(0.0, 0.0, offset7);
            AxisAlignedBB boundingBox11 = boundingBox3;
            Iterator iterator7 = collidingBoundingBoxes2.iterator();

            while (iterator7.hasNext()) {
               scaleFactor = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox11, scaleFactor);
            }

            AxisAlignedBB boundingBox12 = boundingBox11.expand(0.0, scaleFactor, 0.0);
            double offset15 = offset8;
            Iterator iterator8 = collidingBoundingBoxes2.iterator();

            while (iterator8.hasNext()) {
               offset15 = ((AxisAlignedBB)iterator8.next()).getCoordinate2(boundingBox12, offset15);
            }

            AxisAlignedBB boundingBox13 = boundingBox12.expand(offset15, 0.0, 0.0);
            double offset16 = offset9;
            Iterator iterator9 = collidingBoundingBoxes2.iterator();

            while (iterator9.hasNext()) {
               offset16 = ((AxisAlignedBB)iterator9.next()).getCoordinate(boundingBox13, offset16);
            }

            AxisAlignedBB boundingBox14 = boundingBox13.expand(0.0, 0.0, offset16);
            double offset17 = offset5 * offset5 + offset7 * offset7;
            double offset18 = offset15 * offset15 + offset16 * offset16;
            AxisAlignedBB boundingBox15;
            if (offset17 > offset18) {
               offset6 = -offset14;
               boundingBox15 = boundingBox10;
            } else {
               offset5 = offset15;
               offset7 = offset16;
               offset6 = -scaleFactor;
               boundingBox15 = boundingBox14;
            }

            Iterator iterator10 = collidingBoundingBoxes2.iterator();

            while (iterator10.hasNext()) {
               offset6 = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox15, offset6);
            }

            boundingBox2 = boundingBox15.expand(0.0, offset6, 0.0);
            if (offset11 * offset11 + offset13 * offset13 >= offset5 * offset5 + offset7 * offset7) {
               offset5 = offset11;
               offset6 = offset12;
               offset7 = offset13;
               boundingBox2 = boundingBox6;
            }
         }

         offset = (boundingBox2.minX + boundingBox2.maxX) / 2.0;
         offset2 = boundingBox2.minY;
         offset3 = (boundingBox2.minZ + boundingBox2.maxZ) / 2.0;
         isCollidedHorizontally = offset8 != offset5 || offset9 != offset7;
         boolean flag2 = motionY != offset6;
         onGround = flag2 && motionY < 0.0;
         if (!isCollidedHorizontally && !flag2) {
         } else {
         }

         int count2 = MathHelper.ceiling_double_int(offset);
         int count3 = MathHelper.ceiling_double_int(offset2 - 0.2F);
         int count4 = MathHelper.ceiling_double_int(offset3);
         BlockPos blockPos = new BlockPos(count2, count3, count4);
         Block block = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (offset8 != offset5) {
            motionX = 0.0;
         }

         if (offset9 != offset7) {
            motionZ = 0.0;
         }

         if (motionY != offset6) {
            if (block instanceof BlockSlime) {
               if (player.isSprinting()) {
                  motionY = 0.0;
               } else if (motionY < 0.0) {
                  motionY = -motionY;
               }
            } else {
               motionY = 0.0;
            }
         }

         double offset19;
         if (player.worldObj.state
            && (
               !player.worldObj.checkBlockPos12(new BlockPos((int)offset, 0, (int)offset3))
                  || !player.worldObj.getLoggerEntry(new BlockPos((int)offset, 0, (int)offset3)).canExecute2()
            )) {
            if (offset2 > 0.0) {
               offset19 = -0.1;
            } else {
               offset19 = 0.0;
            }
         } else {
            offset19 = motionY - 0.08;
         }

         motionY = offset19 * 0.98F;
         motionX *= (double)factor16;
         motionZ *= (double)factor16;
      }

      return new double[]{offset, offset2, offset3, prevPosX, prevPosY, prevPosZ, motionX, motionY, motionZ, onGround ? 1.0 : 0.0, isCollidedHorizontally ? 1.0 : 0.0};
   }

   public static double[] getCoordOffsetArray(int count, EntityPlayerSP player) {
      long value = 0x376241aa3840L;
      float yaw = player.rotationYaw;
      return resolveDoubleArrayByEntityPlayerSP(count, player, yaw, false);
   }

   public static void updateState2() {
      MinecraftAccessor.minecraft.thePlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager30);
      MinecraftAccessor.minecraft.thePlayer.enabled4 = true;
   }

   public static double[] getDoubleArray2(float factor, float factor2, double posX, double posX2, double posX3, double posX4, double posY, double posZ, boolean flag) {
      double offset = posX;
      double offset2 = posX2;
      double offset3 = posX3;
      float factor3 = factor2 * 0.98F;
      float factor4 = factor * 0.98F;
      float factor5 = 0.91F;
      boolean riding = MinecraftAccessor.minecraft.thePlayer.isRiding();
      if (flag && MinecraftAccessor.minecraft.thePlayer.onGround && MinecraftAccessor.minecraft.thePlayer.getIndexOffset18() == 0) {
         offset2 = (double)MinecraftAccessor.minecraft.thePlayer.getFloat6();
         if (MinecraftAccessor.minecraft.thePlayer.checkPotion(Potion.potion9)) {
            offset2 += (double)((float)(MinecraftAccessor.minecraft.thePlayer.getPotionEffect(Potion.potion9).getIndexOffset2() + 1) * 0.1F);
         }

         if (riding) {
            float factor6 = MinecraftAccessor.minecraft.thePlayer.rotationYaw * (float) (Math.PI / 180.0);
            offset = posX - (double)(MathHelper.abs(factor6) * 0.2F);
            offset3 = posX3 + (double)(MathHelper.cos(factor6) * 0.2F);
         }
      }

      if (MinecraftAccessor.minecraft.thePlayer.onGround) {
         factor5 = MinecraftAccessor.minecraft
               .thePlayer
               .worldObj
               .getBlockState(new BlockPos(MathHelper.ceiling_double_int(posX4), MathHelper.ceiling_double_int(posY) - 1, MathHelper.ceiling_double_int(posZ)))
               .getBlock()
               .scaleFactor
            * 0.91F;
      }

      float factor7 = 0.16277136F / (factor5 * factor5 * factor5);
      float factor8;
      if (MinecraftAccessor.minecraft.thePlayer.onGround) {
         factor8 = MinecraftAccessor.minecraft.thePlayer.getScaleFactor6() * factor7;
      } else {
         factor8 = MinecraftAccessor.minecraft.thePlayer.scaleFactor10;
      }

      float factor9 = factor3 * factor3 + factor4 * factor4;
      if (factor9 >= 1.0E-4F) {
         float factor10 = MathHelper.sin(factor9);
         if (factor10 < 1.0F) {
            factor10 = 1.0F;
         }

         float factor11 = factor8 / factor10;
         factor3 *= factor11;
         factor4 *= factor11;
         float factor12 = MathHelper.abs(MinecraftAccessor.minecraft.thePlayer.rotationYaw * (float) Math.PI / 180.0F);
         float factor13 = MathHelper.cos(MinecraftAccessor.minecraft.thePlayer.rotationYaw * (float) Math.PI / 180.0F);
         offset += (double)(factor3 * factor13 - factor4 * factor12);
         offset3 += (double)(factor4 * factor13 + factor3 * factor12);
      }

      double offset4 = posX4 + offset;
      double offset5 = posY + offset2;
      double offset6 = posZ + offset3;
      float factor14 = 0.91F;
      if (MinecraftAccessor.minecraft.thePlayer.onGround) {
         factor14 = MinecraftAccessor.minecraft
               .thePlayer
               .worldObj
               .getBlockState(
                  new BlockPos(
                     MathHelper.ceiling_double_int(offset4),
                     MathHelper.ceiling_double_int(MinecraftAccessor.minecraft.thePlayer.getBoundingBox2().minY) - 1,
                     MathHelper.ceiling_double_int(offset6)
                  )
               )
               .getBlock()
               .scaleFactor
            * 0.91F;
      }

      if (MinecraftAccessor.minecraft.thePlayer.worldObj.state
         && (
            !MinecraftAccessor.minecraft.thePlayer.worldObj.checkBlockPos12(new BlockPos((int)offset4, 0, (int)offset6))
               || !MinecraftAccessor.minecraft.thePlayer.worldObj.getLoggerEntry(new BlockPos((int)offset4, 0, (int)offset6)).canExecute2()
         )) {
         if (offset5 > 0.0) {
            offset2 = -0.1;
         } else {
            offset2 = 0.0;
         }
      } else {
         offset2 -= 0.08;
      }

      offset2 *= 0.98F;
      offset *= (double)factor14;
      offset3 *= (double)factor14;
      return new double[]{offset4, offset5, offset6, offset, offset2, offset3};
   }

   public static double[] getCoordOffsetArray(EntityLivingBase livingEntity) {
      double posX = livingEntity.posX;
      double posY = livingEntity.posY;
      double posZ = livingEntity.posZ;
      if (livingEntity instanceof EntityOtherPlayerMP) {
         EntityOtherPlayerMP player = (EntityOtherPlayerMP)livingEntity;
         if (player.otherPlayerMPPosRotationIncrements > 0) {
            posX = player.posX + (player.coordOffset3 - player.posX) / (double)player.otherPlayerMPPosRotationIncrements;
            posY = player.posY + (player.coordOffset1 - player.posY) / (double)player.otherPlayerMPPosRotationIncrements;
            posZ = player.posZ + (player.coordOffset4 - player.posZ) / (double)player.otherPlayerMPPosRotationIncrements;
         }
      } else if (livingEntity.count22 > 0) {
         posX = livingEntity.posX + (livingEntity.value6 - livingEntity.posX) / (double)livingEntity.count22;
         posY = livingEntity.posY + (livingEntity.value8 - livingEntity.posY) / (double)livingEntity.count22;
         posZ = livingEntity.posZ + (livingEntity.value15 - livingEntity.posZ) / (double)livingEntity.count22;
      }

      return new double[]{posX, posY, posZ};
   }

   public static double[] resolveDoubleArray(EntityLivingBase livingEntity, int count) {
      double offset = livingEntity.prevPosX - livingEntity.posX;
      double offset2 = livingEntity.prevPosZ - livingEntity.posZ;
      double posX = livingEntity.posX;
      double posZ = livingEntity.posZ;

      for (int index = 0; index <= count; index++) {
         posX -= offset * (double)index;
         posZ -= offset2 * (double)index;
      }

      return new double[]{posX, posZ};
   }

   public static boolean checkItemStack2(ItemStack stack) {
      long value = 0x5f8eb4abb6daL;
      if (MinecraftAccessor.minecraft.objectMouseOver != null && MinecraftAccessor.minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
         BlockPos blockPos = MinecraftAccessor.minecraft.objectMouseOver.getBlockPos();
         Vec3 hitVec = MinecraftAccessor.minecraft.objectMouseOver.hitVec;
         EnumFacing sideHit = MinecraftAccessor.minecraft.objectMouseOver.sideHit;
         BlockPos blockPos2 = blockPos;
         if (checkItemStack(stack, blockPos2, sideHit, hitVec)) {
            return true;
         }
      }

      return false;
   }

   public static boolean matchesEntityLivingBaseByEntityLivingBase(int count, EntityLivingBase livingEntity, int count2, short shortValue, short shortValue2) {
      double offset = livingEntity.posY - livingEntity.lastTickPosY;
      AxisAlignedBB boundingBox = livingEntity.getBoundingBox2();
      AxisAlignedBB boundingBox2 = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      boolean onGround = livingEntity.onGround;
      if (livingEntity instanceof EntityOtherPlayerMP) {
         EntityOtherPlayerMP player = (EntityOtherPlayerMP)livingEntity;
      }

      for (int index = 0; index <= count2; index++) {
         double offset2 = livingEntity.posX - livingEntity.lastTickPosX;
         double offset3 = livingEntity.posZ - livingEntity.lastTickPosZ;
         double offset4 = livingEntity.scaleFactor21 < 75.0F
               && livingEntity instanceof EntityPlayer
               && ((EntityPlayer)livingEntity).getHeldItem() != null
               && ((EntityPlayer)livingEntity).getHeldItem().getItem() instanceof ItemBlock
            ? 0.0
            : offset;
         double offset5 = offset2;
         double offset6 = offset4;
         double offset7 = offset3;
         if (offset2 > 10.0 || offset4 > 10.0 || offset3 > 10.0) {
            return true;
         }

         List collidingBoundingBoxes = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.contract(offset2, offset4, offset3));
         AxisAlignedBB boundingBox3 = boundingBox2;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset4 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox2, offset4);
         }

         AxisAlignedBB boundingBox4 = boundingBox2.expand(0.0, offset4, 0.0);
         boolean flag = onGround || offset6 != offset4 && offset6 < 0.0;
         Iterator iterator2 = collidingBoundingBoxes.iterator();

         while (iterator2.hasNext()) {
            offset2 = ((AxisAlignedBB)iterator2.next()).getCoordinate2(boundingBox4, offset2);
         }

         AxisAlignedBB boundingBox5 = boundingBox4.expand(offset2, 0.0, 0.0);
         Iterator iterator3 = collidingBoundingBoxes.iterator();

         while (iterator3.hasNext()) {
            offset3 = ((AxisAlignedBB)iterator3.next()).getCoordinate(boundingBox5, offset3);
         }

         boundingBox2 = boundingBox5.expand(0.0, 0.0, offset3);
         if (livingEntity.stepHeight > 0.0F && flag && (offset5 != offset2 || offset7 != offset3)) {
            double offset8 = offset2;
            double offset9 = offset4;
            double offset10 = offset3;
            AxisAlignedBB boundingBox6 = boundingBox2;
            double stepHeight = (double)livingEntity.stepHeight;
            List collidingBoundingBoxes2 = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox3.contract(offset5, stepHeight, offset7));
            AxisAlignedBB boundingBox7 = boundingBox3.contract(offset5, 0.0, offset7);
            double offset11 = stepHeight;
            Iterator iterator4 = collidingBoundingBoxes2.iterator();

            while (iterator4.hasNext()) {
               offset11 = ((AxisAlignedBB)iterator4.next()).getCoordinate3(boundingBox7, offset11);
            }

            AxisAlignedBB boundingBox8 = boundingBox3.expand(0.0, offset11, 0.0);
            offset2 = offset5;
            Iterator iterator5 = collidingBoundingBoxes2.iterator();

            while (iterator5.hasNext()) {
               offset2 = ((AxisAlignedBB)iterator5.next()).getCoordinate2(boundingBox8, offset2);
            }

            AxisAlignedBB boundingBox9 = boundingBox8.expand(offset2, 0.0, 0.0);
            offset3 = offset7;
            Iterator iterator6 = collidingBoundingBoxes2.iterator();

            while (iterator6.hasNext()) {
               offset3 = ((AxisAlignedBB)iterator6.next()).getCoordinate(boundingBox9, offset3);
            }

            AxisAlignedBB boundingBox10 = boundingBox9.expand(0.0, 0.0, offset3);
            AxisAlignedBB boundingBox11 = boundingBox3;
            Iterator iterator7 = collidingBoundingBoxes2.iterator();

            while (iterator7.hasNext()) {
               stepHeight = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox11, stepHeight);
            }

            AxisAlignedBB boundingBox12 = boundingBox11.expand(0.0, stepHeight, 0.0);
            double offset12 = offset5;
            Iterator iterator8 = collidingBoundingBoxes2.iterator();

            while (iterator8.hasNext()) {
               offset12 = ((AxisAlignedBB)iterator8.next()).getCoordinate2(boundingBox12, offset12);
            }

            AxisAlignedBB boundingBox13 = boundingBox12.expand(offset12, 0.0, 0.0);
            double offset13 = offset7;
            Iterator iterator9 = collidingBoundingBoxes2.iterator();

            while (iterator9.hasNext()) {
               offset13 = ((AxisAlignedBB)iterator9.next()).getCoordinate(boundingBox13, offset13);
            }

            AxisAlignedBB boundingBox14 = boundingBox13.expand(0.0, 0.0, offset13);
            double offset14 = offset2 * offset2 + offset3 * offset3;
            double offset15 = offset12 * offset12 + offset13 * offset13;
            AxisAlignedBB boundingBox15;
            if (offset14 > offset15) {
               offset4 = -offset11;
               boundingBox15 = boundingBox10;
            } else {
               offset2 = offset12;
               offset3 = offset13;
               offset4 = -stepHeight;
               boundingBox15 = boundingBox14;
            }

            Iterator iterator10 = collidingBoundingBoxes2.iterator();

            while (iterator10.hasNext()) {
               offset4 = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox15, offset4);
            }

            boundingBox2 = boundingBox15.expand(0.0, offset4, 0.0);
            if (offset8 * offset8 + offset10 * offset10 >= offset2 * offset2 + offset3 * offset3) {
               offset2 = offset8;
               offset4 = offset9;
               offset3 = offset10;
               boundingBox2 = boundingBox6;
            }
         }

         double offset16 = (boundingBox2.minX + boundingBox2.maxX) / 2.0;
         double minY = boundingBox2.minY;
         double offset17 = (boundingBox2.minZ + boundingBox2.maxZ) / 2.0;
         boolean flag2 = offset5 != offset2 || offset7 != offset3;
         boolean flag3 = offset6 != offset4;
         onGround = flag3 && offset6 < 0.0;
         if (onGround) {
            return true;
         }

         if (!flag2 && !flag3) {
         } else {
         }

         int count3 = MathHelper.ceiling_double_int(offset16);
         int count4 = MathHelper.ceiling_double_int(minY - 0.2F);
         int count5 = MathHelper.ceiling_double_int(offset17);
         BlockPos blockPos = new BlockPos(count3, count4, count5);
         Block block = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (offset6 != offset4) {
            if (block instanceof BlockSlime) {
               if (livingEntity.isSprinting()) {
                  offset = 0.0;
               } else if (offset < 0.0) {
                  offset = -offset;
               }
            } else {
               offset = 0.0;
            }
         }

         double offset18;
         if (MinecraftAccessor.minecraft.theWorld.state
            && (
               !MinecraftAccessor.minecraft.theWorld.checkBlockPos12(new BlockPos((int)offset16, 0, (int)offset17))
                  || !MinecraftAccessor.minecraft.theWorld.getLoggerEntry(new BlockPos((int)offset16, 0, (int)offset17)).canExecute2()
            )) {
            if (minY > 0.0) {
               offset18 = -0.1;
            } else {
               offset18 = 0.0;
            }
         } else {
            offset18 = offset - 0.08;
         }

         offset = offset18 * 0.98F;
      }

      return false;
   }

   public static double[] resolveDoubleArrayByEntityPlayerSP(int count, EntityPlayerSP player, boolean flag) {
      long value = 0x599d7d91f51bL;
      float yaw = player.rotationYaw;
      return resolveDoubleArrayByEntityPlayerSP(count, player, yaw, flag);
   }

   public static void updateState3() {
      MinecraftAccessor.minecraft
         .getNetHandlerPlayClient()
         .handlePacket2(
            new C04PacketPlayerPosition(
               MinecraftAccessor.minecraft.thePlayer.posX,
               MinecraftAccessor.minecraft.thePlayer.posY + 3.001,
               MinecraftAccessor.minecraft.thePlayer.posZ,
               false
            )
         );
      MinecraftAccessor.minecraft
         .getNetHandlerPlayClient()
         .handlePacket2(
            new C04PacketPlayerPosition(
               MinecraftAccessor.minecraft.thePlayer.posX, MinecraftAccessor.minecraft.thePlayer.posY, MinecraftAccessor.minecraft.thePlayer.posZ, false
            )
         );
      MinecraftAccessor.minecraft
         .getNetHandlerPlayClient()
         .handlePacket2(
            new C04PacketPlayerPosition(
               MinecraftAccessor.minecraft.thePlayer.posX, MinecraftAccessor.minecraft.thePlayer.posY, MinecraftAccessor.minecraft.thePlayer.posZ, true
            )
         );
   }

   public static ArrayList resolveArrayList(EntityPlayerSP player, ArrayList list, BoundingBoxDimension boundingBoxDimension, int count, boolean flag) {
      BoundingBoxDimension boundingBoxDimension2 = boundingBoxDimension;
      double posX = boundingBoxDimension.positionVec2.xCoord;
      double posY = boundingBoxDimension.positionVec2.yCoord;
      double posZ = boundingBoxDimension.positionVec2.zCoord;
      double posX2 = boundingBoxDimension.vec3.xCoord;
      double posY2 = boundingBoxDimension.vec3.yCoord;
      double posZ2 = boundingBoxDimension.vec3.zCoord;
      boolean enabled = boundingBoxDimension.enabled4;
      AxisAlignedBB axisAlignedBB = boundingBoxDimension.axisAlignedBB;
      AxisAlignedBB boundingBox = new AxisAlignedBB(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
      float fallDistance = player.fallDistance;
      ArrayList list2 = new ArrayList();

      for (int index = 0; index <= count; index++) {
         if (!list.isEmpty()) {
            boundingBoxDimension2 = (BoundingBoxDimension)list.get(Math.min(list.size() - 1, index));
         }

         double offset = posX;
         double offset2 = posY;
         double offset3 = posZ;
         float factor = boundingBoxDimension2.factor3;
         float factor2 = boundingBoxDimension2.factor2;
         boolean enabled2 = boundingBoxDimension2.enabled5;
         boolean enabled3 = boundingBoxDimension2.enabled;
         boolean enabled4 = boundingBoxDimension2.enabled2;
         EventCameraTransform eventCameraTransform = EventCameraTransformCallback.getEventCameraTransform(0.2F, 0.2F);
         if (player.isUsingItem() && !player.isSneaking()) {
            factor *= eventCameraTransform.getScaleFactor2();
            factor2 *= eventCameraTransform.getScaleFactor();
         }

         double offset4 = ViaLoadingBase.processAction2();
         if (Math.abs(posX2) < offset4) {
            posX2 = 0.0;
         }

         if (Math.abs(posY2) < offset4) {
            posY2 = 0.0;
         }

         if (Math.abs(posZ2) < offset4) {
            posZ2 = 0.0;
         }

         if (enabled3 && enabled) {
            posY2 = (double)player.getFloat6();
            if (player.checkPotion(Potion.potion9)) {
               posY2 += (double)((float)(player.getPotionEffect(Potion.potion9).getIndexOffset2() + 1) * 0.1F);
            }

            if (enabled2) {
               float factor3 = boundingBoxDimension2.factor4 * (float) (Math.PI / 180.0);
               posX2 -= (double)(MathHelper.abs(factor3) * 0.2F);
               posZ2 += (double)(MathHelper.cos(factor3) * 0.2F);
            }
         }

         float factor4 = factor * 0.98F;
         float factor5 = factor2 * 0.98F;
         float factor6 = 0.91F;
         if (enabled) {
            factor6 = player.worldObj
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY) - 1, MathHelper.ceiling_double_int(posZ))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         float factor7 = 0.16277136F / (factor6 * factor6 * factor6);
         float factor8;
         if (enabled) {
            factor8 = boundingBoxDimension2.factor * factor7;
         } else {
            factor8 = player.getScaleFactor2();
            if (enabled2) {
               factor8 = (float)((double)factor8 + (double)player.getScaleFactor2() * 0.3);
            }
         }

         float factor9 = factor4 * factor4 + factor5 * factor5;
         if (factor9 >= 1.0E-4F) {
            float factor10 = MathHelper.sin(factor9);
            if (factor10 < 1.0F) {
               factor10 = 1.0F;
            }

            float factor11 = factor8 / factor10;
            float factor12 = factor4 * factor11;
            float factor13 = factor5 * factor11;
            float factor14 = MathHelper.abs(boundingBoxDimension2.factor4 * (float) Math.PI / 180.0F);
            float factor15 = MathHelper.cos(boundingBoxDimension2.factor4 * (float) Math.PI / 180.0F);
            posX2 += (double)(factor12 * factor15 - factor13 * factor14);
            posZ2 += (double)(factor13 * factor15 + factor12 * factor14);
         }

         float factor16 = 0.91F;
         if (enabled) {
            factor16 = MinecraftAccessor.minecraft
                  .theWorld
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY) - 1, MathHelper.ceiling_double_int(posZ))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         double offset5 = posX2;
         double offset6 = posY2;
         double offset7 = posZ2;
         double offset8 = posX2;
         double offset9 = posZ2;
         boolean flag2 = enabled4 && !flag;
         if (enabled && flag2) {
            double offset10;
            for (offset10 = 0.05;
               offset5 != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox.expand(offset5, -1.0, 0.0)).isEmpty();
               offset8 = offset5
            ) {
               if (offset5 < offset10 && offset5 >= -offset10) {
                  offset5 = 0.0;
               } else if (offset5 > 0.0) {
                  offset5 -= offset10;
               } else {
                  offset5 += offset10;
               }
            }

            for (;
               offset7 != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox.expand(0.0, -1.0, offset7)).isEmpty();
               offset9 = offset7
            ) {
               if (offset7 < offset10 && offset7 >= -offset10) {
                  offset7 = 0.0;
               } else if (offset7 > 0.0) {
                  offset7 -= offset10;
               } else {
                  offset7 += offset10;
               }
            }

            for (;
               offset5 != 0.0 && offset7 != 0.0 && MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox.expand(offset5, -1.0, offset7)).isEmpty();
               offset9 = offset7
            ) {
               if (offset5 < offset10 && offset5 >= -offset10) {
                  offset5 = 0.0;
               } else if (offset5 > 0.0) {
                  offset5 -= offset10;
               } else {
                  offset5 += offset10;
               }

               offset8 = offset5;
               if (offset7 < offset10 && offset7 >= -offset10) {
                  offset7 = 0.0;
               } else if (offset7 > 0.0) {
                  offset7 -= offset10;
               } else {
                  offset7 += offset10;
               }
            }
         }

         List collidingBoundingBoxes = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox.contract(offset5, posY2, offset7));
         AxisAlignedBB boundingBox2 = boundingBox;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset6 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox, offset6);
         }

         AxisAlignedBB boundingBox3 = boundingBox.expand(0.0, offset6, 0.0);
         boolean flag3 = enabled || posY2 != offset6 && posY2 < 0.0;
         Iterator iterator2 = collidingBoundingBoxes.iterator();

         while (iterator2.hasNext()) {
            offset5 = ((AxisAlignedBB)iterator2.next()).getCoordinate2(boundingBox3, offset5);
         }

         AxisAlignedBB boundingBox4 = boundingBox3.expand(offset5, 0.0, 0.0);
         Iterator iterator3 = collidingBoundingBoxes.iterator();

         while (iterator3.hasNext()) {
            offset7 = ((AxisAlignedBB)iterator3.next()).getCoordinate(boundingBox4, offset7);
         }

         boundingBox = boundingBox4.expand(0.0, 0.0, offset7);
         EventTickState eventTickState = EventTickStateCallback.getEventTickState(player.stepHeight, StateTypes.PRE);
         if (eventTickState.getScaleFactor() > 0.0F && flag3 && (offset8 != offset5 || offset9 != offset7)) {
            double offset11 = offset5;
            double offset12 = offset6;
            double offset13 = offset7;
            AxisAlignedBB boundingBox5 = boundingBox;
            double scaleFactor = (double)eventTickState.getScaleFactor();
            List collidingBoundingBoxes2 = MinecraftAccessor.minecraft.theWorld.getCollidingBoundingBoxes(player, boundingBox2.contract(offset8, scaleFactor, offset9));
            AxisAlignedBB boundingBox6 = boundingBox2.contract(offset8, 0.0, offset9);
            double offset14 = scaleFactor;
            Iterator iterator4 = collidingBoundingBoxes2.iterator();

            while (iterator4.hasNext()) {
               offset14 = ((AxisAlignedBB)iterator4.next()).getCoordinate3(boundingBox6, offset14);
            }

            AxisAlignedBB boundingBox7 = boundingBox2.expand(0.0, offset14, 0.0);
            offset5 = offset8;
            Iterator iterator5 = collidingBoundingBoxes2.iterator();

            while (iterator5.hasNext()) {
               offset5 = ((AxisAlignedBB)iterator5.next()).getCoordinate2(boundingBox7, offset5);
            }

            AxisAlignedBB boundingBox8 = boundingBox7.expand(offset5, 0.0, 0.0);
            offset7 = offset9;
            Iterator iterator6 = collidingBoundingBoxes2.iterator();

            while (iterator6.hasNext()) {
               offset7 = ((AxisAlignedBB)iterator6.next()).getCoordinate(boundingBox8, offset7);
            }

            AxisAlignedBB boundingBox9 = boundingBox8.expand(0.0, 0.0, offset7);
            AxisAlignedBB boundingBox10 = boundingBox2;
            Iterator iterator7 = collidingBoundingBoxes2.iterator();

            while (iterator7.hasNext()) {
               scaleFactor = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox10, scaleFactor);
            }

            AxisAlignedBB boundingBox11 = boundingBox10.expand(0.0, scaleFactor, 0.0);
            double offset15 = offset8;
            Iterator iterator8 = collidingBoundingBoxes2.iterator();

            while (iterator8.hasNext()) {
               offset15 = ((AxisAlignedBB)iterator8.next()).getCoordinate2(boundingBox11, offset15);
            }

            AxisAlignedBB boundingBox12 = boundingBox11.expand(offset15, 0.0, 0.0);
            double offset16 = offset9;
            Iterator iterator9 = collidingBoundingBoxes2.iterator();

            while (iterator9.hasNext()) {
               offset16 = ((AxisAlignedBB)iterator9.next()).getCoordinate(boundingBox12, offset16);
            }

            AxisAlignedBB boundingBox13 = boundingBox12.expand(0.0, 0.0, offset16);
            double offset17 = offset5 * offset5 + offset7 * offset7;
            double offset18 = offset15 * offset15 + offset16 * offset16;
            AxisAlignedBB boundingBox14;
            if (offset17 > offset18) {
               offset6 = -offset14;
               boundingBox14 = boundingBox9;
            } else {
               offset5 = offset15;
               offset7 = offset16;
               offset6 = -scaleFactor;
               boundingBox14 = boundingBox13;
            }

            Iterator iterator10 = collidingBoundingBoxes2.iterator();

            while (iterator10.hasNext()) {
               offset6 = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox14, offset6);
            }

            boundingBox = boundingBox14.expand(0.0, offset6, 0.0);
            if (offset11 * offset11 + offset13 * offset13 >= offset5 * offset5 + offset7 * offset7) {
               offset5 = offset11;
               offset6 = offset12;
               offset7 = offset13;
               boundingBox = boundingBox5;
            }
         }

         posX = (boundingBox.minX + boundingBox.maxX) / 2.0;
         posY = boundingBox.minY;
         posZ = (boundingBox.minZ + boundingBox.maxZ) / 2.0;
         boolean flag4 = offset8 != offset5 || offset9 != offset7;
         boolean flag5 = posY2 != offset6;
         enabled = flag5 && posY2 < 0.0;
         if (!flag4 && !flag5) {
         } else {
         }

         int count2 = MathHelper.ceiling_double_int(posX);
         int count3 = MathHelper.ceiling_double_int(posY - 0.2F);
         int count4 = MathHelper.ceiling_double_int(posZ);
         BlockPos blockPos = new BlockPos(count2, count3, count4);
         Block block = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (enabled) {
            if (fallDistance > 0.0F) {
               fallDistance = 0.0F;
            }
         } else if (offset6 < 0.0) {
            fallDistance = (float)((double)fallDistance - offset6);
         }

         if (offset8 != offset5) {
            posX2 = 0.0;
         }

         if (offset9 != offset7) {
            posZ2 = 0.0;
         }

         if (posY2 != offset6) {
            if (block instanceof BlockSlime) {
               if (boundingBoxDimension2.enabled2) {
                  posY2 = 0.0;
               } else if (posY2 < 0.0) {
                  posY2 = -posY2;
               }
            } else {
               posY2 = 0.0;
            }
         }

         double offset19;
         if (player.worldObj.state
            && (
               !player.worldObj.checkBlockPos12(new BlockPos((int)posX, 0, (int)posZ))
                  || !player.worldObj.getLoggerEntry(new BlockPos((int)posX, 0, (int)posZ)).canExecute2()
            )) {
            if (posY > 0.0) {
               offset19 = -0.1;
            } else {
               offset19 = 0.0;
            }
         } else {
            offset19 = posY2 - 0.08;
         }

         posY2 = offset19 * 0.98F;
         posX2 *= (double)factor16;
         posZ2 *= (double)factor16;
         list2.add(new double[]{posX, posY, posZ, offset, offset2, offset3, posX2, posY2, posZ2, enabled ? 1.0 : 0.0, flag4 ? 1.0 : 0.0, (double)fallDistance});
      }

      return list2;
   }

   public static float getScaleFactor(ModeSetting setting, EntityPlayer player) {
      if (MinecraftAccessor.minecraft.theWorld == null) {
         return 0.0F;
      } else if (setting.getSelected().equals("Entity")) {
         return player.getFloat2();
      } else {
         ThePlayerNameManager thePlayerNameManager = MinecraftAccessor.minecraft.theWorld.getThePlayerNameManager();
         if (thePlayerNameManager == null) {
            return 0.0F;
         } else {
            ScoreboardData customItemEntry = thePlayerNameManager.getCustomItemEntry(0);
            return customItemEntry == null ? 0.0F : (float)thePlayerNameManager.getComparatorEntry(player.getGameProfile().getName(), customItemEntry).getIndex();
         }
      }
   }
}
