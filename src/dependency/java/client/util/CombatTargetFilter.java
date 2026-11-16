package client.util;

import client.via.ViaLoadingBase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAcaciaFence;
import net.minecraft.block.BlockAcaciaFenceGate;
import net.minecraft.block.BlockCobblestoneWall;
import net.minecraft.block.BlockSlime;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class CombatTargetFilter implements MinecraftAccessor {
   private static ArrayList resolveArrayList(EntityLivingBase livingEntity, int count, int count2, int count3) {
      ArrayList list = new ArrayList();
      double offset = (double)livingEntity.indexOffset2 / 32.0;
      double offset2 = (double)livingEntity.indexOffset3 / 32.0;
      double offset3 = (double)livingEntity.indexOffset / 32.0;
      double offset4 = (livingEntity.coordOffset5 - livingEntity.value12) / 2.0;
      double offset5 = (livingEntity.coordOffset20 - livingEntity.value20) / 2.0;
      double offset6 = (livingEntity.coordOffset2 - livingEntity.value10) / 2.0;
      boolean enabled = livingEntity.onGround;
      float factor = livingEntity.width / 2.0F;
      AxisAlignedBB boundingBox = new AxisAlignedBB(
         offset - (double)factor, offset2, offset3 - (double)factor, offset + (double)factor, offset2 + (double)livingEntity.height, offset3 + (double)factor
      );
      AxisAlignedBB boundingBox2 = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      float factor2 = 0.91F;
      if (enabled) {
         factor2 = livingEntity.worldObj
               .getBlockState(new BlockPos(MathHelper.ceiling_double_int(offset), MathHelper.ceiling_double_int(offset2) - 1, MathHelper.ceiling_double_int(offset3)))
               .getBlock()
               .scaleFactor
            * 0.91F;
      }

      double offset7;
      if (livingEntity.worldObj.state
         && (
            !livingEntity.worldObj.checkBlockPos12(new BlockPos((int)offset, 0, (int)offset3))
               || !livingEntity.worldObj.getLoggerEntry(new BlockPos((int)offset, 0, (int)offset3)).canExecute2()
         )) {
         if (offset2 > 0.0) {
            offset7 = -0.1;
         } else {
            offset7 = 0.0;
         }
      } else {
         offset7 = offset5 - 0.08;
      }

      double offset8 = offset7 * 0.98F;
      double offset9 = offset4 * (double)factor2;
      double offset10 = offset6 * (double)factor2;

      for (int index = 0; index < count3; index++) {
         float factor3 = (float)count2;
         float factor4 = (float)count;
         if (livingEntity instanceof EntityPlayer && ((EntityPlayer)livingEntity).isUsingItem() && !livingEntity.isSneaking()) {
            factor3 *= 0.2F;
            factor4 *= 0.2F;
         }

         double offset11 = ViaLoadingBase.processAction2();
         if (Math.abs(offset9) < offset11) {
            offset9 = 0.0;
         }

         if (Math.abs(offset8) < offset11) {
            offset8 = 0.0;
         }

         if (Math.abs(offset10) < offset11) {
            offset10 = 0.0;
         }

         float factor5 = factor3 * 0.98F;
         float factor6 = factor4 * 0.98F;
         float factor7 = 0.91F;
         if (enabled) {
            factor7 = livingEntity.worldObj
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(offset), MathHelper.ceiling_double_int(offset2) - 1, MathHelper.ceiling_double_int(offset3))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         float factor8 = 0.16277136F / (factor7 * factor7 * factor7);
         float factor9;
         if (enabled) {
            factor9 = (float)(minecraft.thePlayer.getValue2() * (double)factor8);
         } else {
            factor9 = livingEntity.scaleFactor10;
         }

         float factor10 = factor5 * factor5 + factor6 * factor6;
         if (factor10 >= 1.0E-4F) {
            float factor11 = MathHelper.sin(factor10);
            if (factor11 < 1.0F) {
               factor11 = 1.0F;
            }

            float factor12 = factor9 / factor11;
            float factor13 = factor5 * factor12;
            float factor14 = factor6 * factor12;
            float factor15 = MathHelper.abs(livingEntity.scaleFactor12 * (float) Math.PI / 180.0F);
            float factor16 = MathHelper.cos(livingEntity.scaleFactor12 * (float) Math.PI / 180.0F);
            offset9 += (double)(factor13 * factor16 - factor14 * factor15);
            offset10 += (double)(factor14 * factor16 + factor13 * factor15);
         }

         float factor17 = 0.91F;
         if (enabled) {
            factor17 = minecraft.theWorld
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(offset), MathHelper.ceiling_double_int(offset2) - 1, MathHelper.ceiling_double_int(offset3))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         if (offset9 > 10.0 || offset8 > 10.0 || offset10 > 10.0) {
            return list;
         }

         double offset12 = offset9;
         double offset13 = offset8;
         double offset14 = offset10;
         double offset15 = offset9;
         double offset16 = offset10;
         boolean sprinting = livingEntity.isSprinting();
         if (livingEntity.onGround && sprinting) {
            double offset17;
            for (offset17 = 0.05; offset12 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(offset12, -1.0, 0.0)).isEmpty(); offset15 = offset12) {
               if (offset12 < offset17 && offset12 >= -offset17) {
                  offset12 = 0.0;
               } else if (offset12 > 0.0) {
                  offset12 -= offset17;
               } else {
                  offset12 += offset17;
               }
            }

            for (; offset14 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(0.0, -1.0, offset14)).isEmpty(); offset16 = offset14) {
               if (offset14 < offset17 && offset14 >= -offset17) {
                  offset14 = 0.0;
               } else if (offset14 > 0.0) {
                  offset14 -= offset17;
               } else {
                  offset14 += offset17;
               }
            }

            for (; offset12 != 0.0 && offset14 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(offset12, -1.0, offset14)).isEmpty(); offset16 = offset14) {
               if (offset12 < offset17 && offset12 >= -offset17) {
                  offset12 = 0.0;
               } else if (offset12 > 0.0) {
                  offset12 -= offset17;
               } else {
                  offset12 += offset17;
               }

               offset15 = offset12;
               if (offset14 < offset17 && offset14 >= -offset17) {
                  offset14 = 0.0;
               } else if (offset14 > 0.0) {
                  offset14 -= offset17;
               } else {
                  offset14 += offset17;
               }
            }
         }

         List collidingBoundingBoxes = minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.contract(offset12, offset8, offset14));
         AxisAlignedBB boundingBox3 = boundingBox2;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset13 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox2, offset13);
         }

         AxisAlignedBB boundingBox4 = boundingBox2.expand(0.0, offset13, 0.0);
         boolean flag = enabled || offset8 != offset13 && offset8 < 0.0;
         Iterator iterator2 = collidingBoundingBoxes.iterator();

         while (iterator2.hasNext()) {
            offset12 = ((AxisAlignedBB)iterator2.next()).getCoordinate2(boundingBox4, offset12);
         }

         AxisAlignedBB boundingBox5 = boundingBox4.expand(offset12, 0.0, 0.0);
         Iterator iterator3 = collidingBoundingBoxes.iterator();

         while (iterator3.hasNext()) {
            offset14 = ((AxisAlignedBB)iterator3.next()).getCoordinate(boundingBox5, offset14);
         }

         boundingBox2 = boundingBox5.expand(0.0, 0.0, offset14);
         if (livingEntity.stepHeight > 0.0F && flag && (offset15 != offset12 || offset16 != offset14)) {
            double offset18 = offset12;
            double offset19 = offset13;
            double offset20 = offset14;
            AxisAlignedBB boundingBox6 = boundingBox2;
            double factor18 = (double)livingEntity.stepHeight;
            List collidingBoundingBoxes2 = minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox3.contract(offset15, factor18, offset16));
            AxisAlignedBB boundingBox7 = boundingBox3.contract(offset15, 0.0, offset16);
            double offset21 = factor18;
            Iterator iterator4 = collidingBoundingBoxes2.iterator();

            while (iterator4.hasNext()) {
               offset21 = ((AxisAlignedBB)iterator4.next()).getCoordinate3(boundingBox7, offset21);
            }

            AxisAlignedBB boundingBox8 = boundingBox3.expand(0.0, offset21, 0.0);
            offset12 = offset15;
            Iterator iterator5 = collidingBoundingBoxes2.iterator();

            while (iterator5.hasNext()) {
               offset12 = ((AxisAlignedBB)iterator5.next()).getCoordinate2(boundingBox8, offset12);
            }

            AxisAlignedBB boundingBox9 = boundingBox8.expand(offset12, 0.0, 0.0);
            offset14 = offset16;
            Iterator iterator6 = collidingBoundingBoxes2.iterator();

            while (iterator6.hasNext()) {
               offset14 = ((AxisAlignedBB)iterator6.next()).getCoordinate(boundingBox9, offset14);
            }

            AxisAlignedBB boundingBox10 = boundingBox9.expand(0.0, 0.0, offset14);
            AxisAlignedBB boundingBox11 = boundingBox3;
            Iterator iterator7 = collidingBoundingBoxes2.iterator();

            while (iterator7.hasNext()) {
               factor18 = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox11, factor18);
            }

            AxisAlignedBB boundingBox12 = boundingBox11.expand(0.0, factor18, 0.0);
            double offset22 = offset15;
            Iterator iterator8 = collidingBoundingBoxes2.iterator();

            while (iterator8.hasNext()) {
               offset22 = ((AxisAlignedBB)iterator8.next()).getCoordinate2(boundingBox12, offset22);
            }

            AxisAlignedBB boundingBox13 = boundingBox12.expand(offset22, 0.0, 0.0);
            double offset23 = offset16;
            Iterator iterator9 = collidingBoundingBoxes2.iterator();

            while (iterator9.hasNext()) {
               offset23 = ((AxisAlignedBB)iterator9.next()).getCoordinate(boundingBox13, offset23);
            }

            AxisAlignedBB boundingBox14 = boundingBox13.expand(0.0, 0.0, offset23);
            double offset24 = offset12 * offset12 + offset14 * offset14;
            double offset25 = offset22 * offset22 + offset23 * offset23;
            AxisAlignedBB boundingBox15;
            if (offset24 > offset25) {
               offset13 = -offset21;
               boundingBox15 = boundingBox10;
            } else {
               offset12 = offset22;
               offset14 = offset23;
               offset13 = -factor18;
               boundingBox15 = boundingBox14;
            }

            Iterator iterator10 = collidingBoundingBoxes2.iterator();

            while (iterator10.hasNext()) {
               offset13 = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox15, offset13);
            }

            boundingBox2 = boundingBox15.expand(0.0, offset13, 0.0);
            if (offset18 * offset18 + offset20 * offset20 >= offset12 * offset12 + offset14 * offset14) {
               offset12 = offset18;
               offset13 = offset19;
               offset14 = offset20;
               boundingBox2 = boundingBox6;
            }
         }

         offset = (boundingBox2.minX + boundingBox2.maxX) / 2.0;
         offset2 = boundingBox2.minY;
         offset3 = (boundingBox2.minZ + boundingBox2.maxZ) / 2.0;
         boolean flag2 = offset15 != offset12 || offset16 != offset14;
         boolean flag3 = offset8 != offset13;
         enabled = flag3 && offset8 < 0.0;
         if (!flag2 && !flag3) {
         } else {
         }

         int count4 = MathHelper.ceiling_double_int(offset);
         int count5 = MathHelper.ceiling_double_int(offset2 - 0.2F);
         int count6 = MathHelper.ceiling_double_int(offset3);
         BlockPos blockPos = new BlockPos(count4, count5, count6);
         Block block = minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (offset15 != offset12) {
            offset9 = 0.0;
         }

         if (offset16 != offset14) {
            offset10 = 0.0;
         }

         if (offset8 != offset13) {
            if (block instanceof BlockSlime) {
               if (livingEntity.isSprinting()) {
                  offset8 = 0.0;
               } else if (offset8 < 0.0) {
                  offset8 = -offset8;
               }
            } else {
               offset8 = 0.0;
            }
         }

         double offset26;
         if (livingEntity.worldObj.state
            && (
               !livingEntity.worldObj.checkBlockPos12(new BlockPos((int)offset, 0, (int)offset3))
                  || !livingEntity.worldObj.getLoggerEntry(new BlockPos((int)offset, 0, (int)offset3)).canExecute2()
            )) {
            if (offset2 > 0.0) {
               offset26 = -0.1;
            } else {
               offset26 = 0.0;
            }
         } else {
            offset26 = offset8 - 0.08;
         }

         offset8 = offset26 * 0.98F;
         offset9 *= (double)factor17;
         offset10 *= (double)factor17;
         list.add(new Vec3(offset, offset2, offset3));
      }

      return list;
   }

   private static Vec3 resolveVec3(EntityLivingBase livingEntity, int count, int count2, int count3) {
      double value = livingEntity.value12;
      double value2 = livingEntity.value20;
      double value3 = livingEntity.value10;
      double doubleItem = MathHelper.getDouble7((livingEntity.value12 - livingEntity.value16) / 2.0, -10.0, 10.0);
      double doubleItem2 = MathHelper.getDouble7((livingEntity.value20 - livingEntity.value7) / 2.0, -10.0, 10.0);
      double doubleItem3 = MathHelper.getDouble7((livingEntity.value10 - livingEntity.value17) / 2.0, -10.0, 10.0);
      boolean enabled = livingEntity.onGround;
      float factor = livingEntity.width / 2.0F;
      AxisAlignedBB boundingBox = new AxisAlignedBB(
         value - (double)factor, value2, value3 - (double)factor, value + (double)factor, value2 + (double)livingEntity.height, value3 + (double)factor
      );
      AxisAlignedBB boundingBox2 = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      livingEntity.isRiding();

      for (int index = 0; index < count3; index++) {
         float factor2 = (float)count2;
         float factor3 = (float)count;
         if (livingEntity instanceof EntityPlayer && ((EntityPlayer)livingEntity).isUsingItem() && !livingEntity.isSneaking()) {
            factor2 *= 0.2F;
            factor3 *= 0.2F;
         }

         double offset = ViaLoadingBase.processAction2();
         if (Math.abs(doubleItem) < offset) {
            doubleItem = 0.0;
         }

         if (Math.abs(doubleItem2) < offset) {
            doubleItem2 = 0.0;
         }

         if (Math.abs(doubleItem3) < offset) {
            doubleItem3 = 0.0;
         }

         float factor4 = factor2 * 0.98F;
         float factor5 = factor3 * 0.98F;
         float factor6 = 0.91F;
         if (enabled) {
            factor6 = livingEntity.worldObj
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(value), MathHelper.ceiling_double_int(value2) - 1, MathHelper.ceiling_double_int(value3))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         float factor7 = 0.16277136F / (factor6 * factor6 * factor6);
         float factor8;
         if (enabled) {
            factor8 = livingEntity.getScaleFactor6() * factor7;
         } else {
            factor8 = livingEntity.scaleFactor10;
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
            float factor14 = MathHelper.abs(livingEntity.scaleFactor22 * (float) Math.PI / 180.0F);
            float factor15 = MathHelper.cos(livingEntity.scaleFactor22 * (float) Math.PI / 180.0F);
            doubleItem += (double)(factor12 * factor15 - factor13 * factor14);
            doubleItem3 += (double)(factor13 * factor15 + factor12 * factor14);
         }

         float factor16 = 0.91F;
         if (enabled) {
            factor16 = minecraft.theWorld
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(value), MathHelper.ceiling_double_int(value2) - 1, MathHelper.ceiling_double_int(value3))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         double offset2 = doubleItem;
         double offset3 = doubleItem2;
         double offset4 = doubleItem3;
         double offset5 = doubleItem;
         double offset6 = doubleItem3;
         if (doubleItem > 100.0 || doubleItem2 > 100.0 || doubleItem3 > 100.0) {
            return new Vec3(value, value2, value3);
         }

         boolean sprinting = livingEntity.isSprinting();
         if (livingEntity.onGround && sprinting) {
            double offset7;
            for (offset7 = 0.05; offset2 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(offset2, -1.0, 0.0)).isEmpty(); offset5 = offset2) {
               if (offset2 < offset7 && offset2 >= -offset7) {
                  offset2 = 0.0;
               } else if (offset2 > 0.0) {
                  offset2 -= offset7;
               } else {
                  offset2 += offset7;
               }
            }

            for (; offset4 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(0.0, -1.0, offset4)).isEmpty(); offset6 = offset4) {
               if (offset4 < offset7 && offset4 >= -offset7) {
                  offset4 = 0.0;
               } else if (offset4 > 0.0) {
                  offset4 -= offset7;
               } else {
                  offset4 += offset7;
               }
            }

            for (; offset2 != 0.0 && offset4 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(offset2, -1.0, offset4)).isEmpty(); offset6 = offset4) {
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

         List collidingBoundingBoxes = minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.contract(offset2, doubleItem2, offset4));
         AxisAlignedBB boundingBox3 = boundingBox2;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset3 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox2, offset3);
         }

         AxisAlignedBB boundingBox4 = boundingBox2.expand(0.0, offset3, 0.0);
         boolean flag = enabled || doubleItem2 != offset3 && doubleItem2 < 0.0;
         Iterator iterator2 = collidingBoundingBoxes.iterator();

         while (iterator2.hasNext()) {
            offset2 = ((AxisAlignedBB)iterator2.next()).getCoordinate2(boundingBox4, offset2);
         }

         AxisAlignedBB boundingBox5 = boundingBox4.expand(offset2, 0.0, 0.0);
         Iterator iterator3 = collidingBoundingBoxes.iterator();

         while (iterator3.hasNext()) {
            offset4 = ((AxisAlignedBB)iterator3.next()).getCoordinate(boundingBox5, offset4);
         }

         boundingBox2 = boundingBox5.expand(0.0, 0.0, offset4);
         if (livingEntity.stepHeight > 0.0F && flag && (offset5 != offset2 || offset6 != offset4)) {
            double offset8 = offset2;
            double offset9 = offset3;
            double offset10 = offset4;
            AxisAlignedBB boundingBox6 = boundingBox2;
            double factor17 = (double)livingEntity.stepHeight;
            List collidingBoundingBoxes2 = minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox3.contract(offset5, factor17, offset6));
            AxisAlignedBB boundingBox7 = boundingBox3.contract(offset5, 0.0, offset6);
            double offset11 = factor17;
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
            offset4 = offset6;
            Iterator iterator6 = collidingBoundingBoxes2.iterator();

            while (iterator6.hasNext()) {
               offset4 = ((AxisAlignedBB)iterator6.next()).getCoordinate(boundingBox9, offset4);
            }

            AxisAlignedBB boundingBox10 = boundingBox9.expand(0.0, 0.0, offset4);
            AxisAlignedBB boundingBox11 = boundingBox3;
            Iterator iterator7 = collidingBoundingBoxes2.iterator();

            while (iterator7.hasNext()) {
               factor17 = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox11, factor17);
            }

            AxisAlignedBB boundingBox12 = boundingBox11.expand(0.0, factor17, 0.0);
            double offset12 = offset5;
            Iterator iterator8 = collidingBoundingBoxes2.iterator();

            while (iterator8.hasNext()) {
               offset12 = ((AxisAlignedBB)iterator8.next()).getCoordinate2(boundingBox12, offset12);
            }

            AxisAlignedBB boundingBox13 = boundingBox12.expand(offset12, 0.0, 0.0);
            double offset13 = offset6;
            Iterator iterator9 = collidingBoundingBoxes2.iterator();

            while (iterator9.hasNext()) {
               offset13 = ((AxisAlignedBB)iterator9.next()).getCoordinate(boundingBox13, offset13);
            }

            AxisAlignedBB boundingBox14 = boundingBox13.expand(0.0, 0.0, offset13);
            double offset14 = offset2 * offset2 + offset4 * offset4;
            double offset15 = offset12 * offset12 + offset13 * offset13;
            AxisAlignedBB boundingBox15;
            if (offset14 > offset15) {
               offset3 = -offset11;
               boundingBox15 = boundingBox10;
            } else {
               offset2 = offset12;
               offset4 = offset13;
               offset3 = -factor17;
               boundingBox15 = boundingBox14;
            }

            Iterator iterator10 = collidingBoundingBoxes2.iterator();

            while (iterator10.hasNext()) {
               offset3 = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox15, offset3);
            }

            boundingBox2 = boundingBox15.expand(0.0, offset3, 0.0);
            if (offset8 * offset8 + offset10 * offset10 >= offset2 * offset2 + offset4 * offset4) {
               offset2 = offset8;
               offset3 = offset9;
               offset4 = offset10;
               boundingBox2 = boundingBox6;
            }
         }

         value = (boundingBox2.minX + boundingBox2.maxX) / 2.0;
         value2 = boundingBox2.minY;
         value3 = (boundingBox2.minZ + boundingBox2.maxZ) / 2.0;
         boolean flag2 = offset5 != offset2 || offset6 != offset4;
         boolean flag3 = doubleItem2 != offset3;
         enabled = flag3 && doubleItem2 < 0.0;
         if (!flag2 && !flag3) {
         } else {
         }

         int count4 = MathHelper.ceiling_double_int(value);
         int count5 = MathHelper.ceiling_double_int(value2 - 0.2F);
         int count6 = MathHelper.ceiling_double_int(value3);
         BlockPos blockPos = new BlockPos(count4, count5, count6);
         Block block = minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (offset5 != offset2) {
            doubleItem = 0.0;
         }

         if (offset6 != offset4) {
            doubleItem3 = 0.0;
         }

         if (doubleItem2 != offset3) {
            if (block instanceof BlockSlime) {
               if (livingEntity.isSprinting()) {
                  doubleItem2 = 0.0;
               } else if (doubleItem2 < 0.0) {
                  doubleItem2 = -doubleItem2;
               }
            } else {
               doubleItem2 = 0.0;
            }
         }

         double offset16;
         if (livingEntity.worldObj.state
            && (
               !livingEntity.worldObj.checkBlockPos12(new BlockPos((int)value, 0, (int)value3))
                  || !livingEntity.worldObj.getLoggerEntry(new BlockPos((int)value, 0, (int)value3)).canExecute2()
            )) {
            if (value2 > 0.0) {
               offset16 = -0.1;
            } else {
               offset16 = 0.0;
            }
         } else {
            offset16 = doubleItem2 - 0.08;
         }

         doubleItem2 = offset16 * 0.98F;
         doubleItem *= (double)factor16;
         doubleItem3 *= (double)factor16;
      }

      return new Vec3(value, value2, value3);
   }

   private static Vec3 resolveVec3(EntityLivingBase livingEntity, int count, int count2, int count3, boolean flag) {
      int count4 = flag ? 1 : 0;
      Vec3 object = (Vec3)livingEntity.backtrackRecord.getObject3(count4);
      int count5 = 1 + count4;
      Vec3 object2 = (Vec3)livingEntity.backtrackRecord.getObject3(count5);
      double posX = object.xCoord;
      double posY = object.yCoord;
      double posZ = object.zCoord;
      double offset = object.yCoord - object2.yCoord;
      boolean enabled = livingEntity.onGround;
      float factor = livingEntity.width / 2.0F;
      AxisAlignedBB boundingBox = new AxisAlignedBB(
         posX - (double)factor, posY, posZ - (double)factor, posX + (double)factor, posY + (double)livingEntity.height, posZ + (double)factor
      );
      AxisAlignedBB boundingBox2 = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      livingEntity.isRiding();

      for (int index = 0; index < count3; index++) {
         double offset2 = object.xCoord - object2.xCoord;
         double offset3 = object.zCoord - object2.zCoord;
         float factor2 = (float)count2;
         float factor3 = (float)count;
         if (livingEntity instanceof EntityPlayer && ((EntityPlayer)livingEntity).isUsingItem() && !livingEntity.isSneaking()) {
            factor2 *= 0.2F;
            factor3 *= 0.2F;
         }

         double offset4 = ViaLoadingBase.processAction2();
         if (Math.abs(offset2) < offset4) {
            offset2 = 0.0;
         }

         if (Math.abs(offset) < offset4) {
            offset = 0.0;
         }

         if (Math.abs(offset3) < offset4) {
            offset3 = 0.0;
         }

         float factor4 = factor2 * 0.98F;
         float factor5 = factor3 * 0.98F;
         float factor6 = 0.91F;
         if (enabled) {
            factor6 = livingEntity.worldObj
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
            factor8 = livingEntity.getScaleFactor6() * factor7;
         } else {
            factor8 = livingEntity.scaleFactor10;
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
            float factor14 = MathHelper.abs(livingEntity.scaleFactor22 * (float) Math.PI / 180.0F);
            float factor15 = MathHelper.cos(livingEntity.scaleFactor22 * (float) Math.PI / 180.0F);
            offset2 += (double)(factor12 * factor15 - factor13 * factor14);
            offset3 += (double)(factor13 * factor15 + factor12 * factor14);
         }

         if (enabled) {
            new BlockPos(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY) - 1, MathHelper.ceiling_double_int(posZ));
         }

         double offset5 = offset;
         double offset6 = offset2;
         double offset7 = offset3;
         if (offset2 > 100.0 || offset > 100.0 || offset3 > 100.0) {
            return new Vec3(posX, posY, posZ);
         }

         boolean sprinting = livingEntity.isSprinting();
         if (livingEntity.onGround && sprinting) {
            double offset8;
            for (offset8 = 0.05; offset2 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(offset2, -1.0, 0.0)).isEmpty(); offset6 = offset2) {
               if (offset2 < offset8 && offset2 >= -offset8) {
                  offset2 = 0.0;
               } else if (offset2 > 0.0) {
                  offset2 -= offset8;
               } else {
                  offset2 += offset8;
               }
            }

            for (; offset3 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(0.0, -1.0, offset3)).isEmpty(); offset7 = offset3) {
               if (offset3 < offset8 && offset3 >= -offset8) {
                  offset3 = 0.0;
               } else if (offset3 > 0.0) {
                  offset3 -= offset8;
               } else {
                  offset3 += offset8;
               }
            }

            for (; offset2 != 0.0 && offset3 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(offset2, -1.0, offset3)).isEmpty(); offset7 = offset3) {
               if (offset2 < offset8 && offset2 >= -offset8) {
                  offset2 = 0.0;
               } else if (offset2 > 0.0) {
                  offset2 -= offset8;
               } else {
                  offset2 += offset8;
               }

               offset6 = offset2;
               if (offset3 < offset8 && offset3 >= -offset8) {
                  offset3 = 0.0;
               } else if (offset3 > 0.0) {
                  offset3 -= offset8;
               } else {
                  offset3 += offset8;
               }
            }
         }

         List collidingBoundingBoxes = minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.contract(offset2, offset, offset3));
         AxisAlignedBB boundingBox3 = boundingBox2;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset5 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox2, offset5);
         }

         AxisAlignedBB boundingBox4 = boundingBox2.expand(0.0, offset5, 0.0);
         boolean flag2 = enabled || offset != offset5 && offset < 0.0;
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
         if (livingEntity.stepHeight > 0.0F && flag2 && (offset6 != offset2 || offset7 != offset3)) {
            double offset9 = offset2;
            double offset10 = offset5;
            double offset11 = offset3;
            AxisAlignedBB boundingBox6 = boundingBox2;
            double factor16 = (double)livingEntity.stepHeight;
            List collidingBoundingBoxes2 = minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox3.contract(offset6, factor16, offset7));
            AxisAlignedBB boundingBox7 = boundingBox3.contract(offset6, 0.0, offset7);
            double offset12 = factor16;
            Iterator iterator4 = collidingBoundingBoxes2.iterator();

            while (iterator4.hasNext()) {
               offset12 = ((AxisAlignedBB)iterator4.next()).getCoordinate3(boundingBox7, offset12);
            }

            AxisAlignedBB boundingBox8 = boundingBox3.expand(0.0, offset12, 0.0);
            offset2 = offset6;
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
               factor16 = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox11, factor16);
            }

            AxisAlignedBB boundingBox12 = boundingBox11.expand(0.0, factor16, 0.0);
            double offset13 = offset6;
            Iterator iterator8 = collidingBoundingBoxes2.iterator();

            while (iterator8.hasNext()) {
               offset13 = ((AxisAlignedBB)iterator8.next()).getCoordinate2(boundingBox12, offset13);
            }

            AxisAlignedBB boundingBox13 = boundingBox12.expand(offset13, 0.0, 0.0);
            double offset14 = offset7;
            Iterator iterator9 = collidingBoundingBoxes2.iterator();

            while (iterator9.hasNext()) {
               offset14 = ((AxisAlignedBB)iterator9.next()).getCoordinate(boundingBox13, offset14);
            }

            AxisAlignedBB boundingBox14 = boundingBox13.expand(0.0, 0.0, offset14);
            double offset15 = offset2 * offset2 + offset3 * offset3;
            double offset16 = offset13 * offset13 + offset14 * offset14;
            AxisAlignedBB boundingBox15;
            if (offset15 > offset16) {
               offset5 = -offset12;
               boundingBox15 = boundingBox10;
            } else {
               offset2 = offset13;
               offset3 = offset14;
               offset5 = -factor16;
               boundingBox15 = boundingBox14;
            }

            Iterator iterator10 = collidingBoundingBoxes2.iterator();

            while (iterator10.hasNext()) {
               offset5 = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox15, offset5);
            }

            boundingBox2 = boundingBox15.expand(0.0, offset5, 0.0);
            if (offset9 * offset9 + offset11 * offset11 >= offset2 * offset2 + offset3 * offset3) {
               offset2 = offset9;
               offset5 = offset10;
               offset3 = offset11;
               boundingBox2 = boundingBox6;
            }
         }

         posX = (boundingBox2.minX + boundingBox2.maxX) / 2.0;
         posY = boundingBox2.minY;
         posZ = (boundingBox2.minZ + boundingBox2.maxZ) / 2.0;
         boolean flag3 = offset6 != offset2 || offset7 != offset3;
         boolean flag4 = offset != offset5;
         enabled = flag4 && offset < 0.0;
         if (!flag3 && !flag4) {
         } else {
         }

         int count6 = MathHelper.ceiling_double_int(posX);
         int count7 = MathHelper.ceiling_double_int(posY - 0.2F);
         int count8 = MathHelper.ceiling_double_int(posZ);
         BlockPos blockPos = new BlockPos(count6, count7, count8);
         Block block = minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (offset != offset5) {
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

         double offset17;
         if (livingEntity.worldObj.state
            && (
               !livingEntity.worldObj.checkBlockPos12(new BlockPos((int)posX, 0, (int)posZ))
                  || !livingEntity.worldObj.getLoggerEntry(new BlockPos((int)posX, 0, (int)posZ)).canExecute2()
            )) {
            if (posY > 0.0) {
               offset17 = -0.1;
            } else {
               offset17 = 0.0;
            }
         } else {
            offset17 = offset - 0.08;
         }

         offset = offset17 * 0.98F;
      }

      return new Vec3(posX, posY, posZ);
   }

   public static ArrayList resolveArrayListByEntityLivingBase(EntityLivingBase livingEntity, int count) {
      if (checkEntityLivingBase(1222936913, 29201, livingEntity)) {
         return new ArrayList();
      } else {
         int[] indexOffsetArray = getIndexOffsetArray(livingEntity);
         int count4 = indexOffsetArray[0];
         int count5 = indexOffsetArray[1];
         int count6 = count4;
         return resolveArrayList(livingEntity, count6, count5, count);
      }
   }

   public static Vec3 getVec3(EntityLivingBase livingEntity, int count) {
      if (livingEntity == null) {
         return new Vec3(0.0, 0.0, 0.0);
      } else {
         double[] offsetArray = MoveUtil.resolveDoubleArray(0.15, 0.0F, 1.0F, livingEntity.rotationYaw);
         double offset = livingEntity.posX + (livingEntity.posX - livingEntity.lastTickPosX);
         double offset2 = livingEntity.posZ + (livingEntity.posZ - livingEntity.lastTickPosZ);

         for (int index = 0; index < count; index++) {
            offset += offsetArray[0];
            offset2 += offsetArray[1];
         }

         return new Vec3(offset, livingEntity.posY, offset2);
      }
   }

   public static int[] resolveIndexArray(EntityLivingBase livingEntity) {
      if (checkEntityLivingBase(1222936913, 29201, livingEntity)) {
         return new int[]{0, 0};
      } else {
         double offset = 1000.0;
         int[] countArray = new int[2];

         for (int index = -1; index <= 1; index++) {
            for (int index2 = -1; index2 <= 1; index2++) {
               double doubleItem = resolveVecByEntityLivingBase(livingEntity, index, index2, 1, true).getDouble4(livingEntity.posX, livingEntity.posY, livingEntity.posZ);
               if (offset > doubleItem) {
                  offset = doubleItem;
                  countArray = new int[]{index, index2};
               }

               if (doubleItem < 0.01 && index == 0 && index2 == 0) {
                  countArray = new int[2];
                  offset = 0.0;
               }
            }
         }

         return countArray;
      }
   }

   public static int[] getIndexOffsetArray(EntityLivingBase livingEntity) {
      if (checkEntityLivingBase(1222936913, 29201, livingEntity)) {
         return new int[]{0, 0};
      } else {
         double offset = 1000.0;
         int[] countArray = new int[2];

         for (int index = -1; index <= 1; index++) {
            for (int index2 = -1; index2 <= 1; index2++) {
               double doubleItem = resolveVec3(livingEntity, index, index2, 2).getDouble4(livingEntity.coordOffset5, livingEntity.coordOffset20, livingEntity.coordOffset2);
               if (offset > doubleItem) {
                  offset = doubleItem;
                  countArray = new int[]{index, index2};
               }

               if (doubleItem < 0.01 && index == 0 && index2 == 0) {
                  countArray = new int[2];
                  offset = 0.0;
               }
            }
         }

         return countArray;
      }
   }

   public static ArrayList resolveArrayListByEntityLivingBase(short shortValue, int count, EntityLivingBase livingEntity, int count2, int count3) {
      if (checkEntityLivingBase(1222936913, 29201, livingEntity)) {
         return new ArrayList();
      } else {
         int[] indexOffsetArray = getIndexOffsetArray(livingEntity);
         int count7 = indexOffsetArray[0];
         int count8 = indexOffsetArray[1];
         int count9 = count7;
         return resolveArrayListByEntityLivingBase(livingEntity, count9, count8, count2);
      }
   }

   private static ArrayList resolveArrayListByEntityLivingBase(EntityLivingBase livingEntity, int count, int count2, int count3) {
      ArrayList list = new ArrayList();
      double posX = livingEntity.posX;
      double posY = livingEntity.posY;
      double posZ = livingEntity.posZ;
      double lastTickPosX = livingEntity.lastTickPosX;
      double lastTickPosY = livingEntity.lastTickPosY;
      double lastTickPosZ = livingEntity.lastTickPosZ;
      double offset = posY - lastTickPosY;
      boolean enabled = livingEntity.onGround;
      float factor = livingEntity.width / 2.0F;
      AxisAlignedBB boundingBox = new AxisAlignedBB(
         posX - (double)factor, posY, posZ - (double)factor, posX + (double)factor, posY + (double)livingEntity.height, posZ + (double)factor
      );
      AxisAlignedBB boundingBox2 = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      if (enabled) {
         new BlockPos(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY) - 1, MathHelper.ceiling_double_int(posZ));
      }

      double offset2;
      if (livingEntity.worldObj.state
         && (
            !livingEntity.worldObj.checkBlockPos12(new BlockPos((int)posX, 0, (int)posZ))
               || !livingEntity.worldObj.getLoggerEntry(new BlockPos((int)posX, 0, (int)posZ)).canExecute2()
         )) {
         if (posY > 0.0) {
            offset2 = -0.1;
         } else {
            offset2 = 0.0;
         }
      } else {
         offset2 = offset - 0.08;
      }

      double offset3 = offset2 * 0.98F;

      for (int index = 0; index < count3; index++) {
         double offset4 = posX - lastTickPosX;
         double offset5 = posZ - lastTickPosZ;
         float factor2 = (float)count2;
         float factor3 = (float)count;
         if (livingEntity instanceof EntityPlayer && ((EntityPlayer)livingEntity).isUsingItem() && !livingEntity.isSneaking()) {
            factor2 *= 0.2F;
            factor3 *= 0.2F;
         }

         double offset6 = ViaLoadingBase.processAction2();
         if (Math.abs(offset4) < offset6) {
            offset4 = 0.0;
         }

         if (Math.abs(offset3) < offset6) {
            offset3 = 0.0;
         }

         if (Math.abs(offset5) < offset6) {
            offset5 = 0.0;
         }

         float factor4 = factor2 * 0.98F;
         float factor5 = factor3 * 0.98F;
         float factor6 = 0.91F;
         if (enabled) {
            factor6 = livingEntity.worldObj
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
            factor8 = (float)(minecraft.thePlayer.getValue2() * (double)factor7);
         } else {
            factor8 = livingEntity.scaleFactor10;
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
            float factor14 = MathHelper.abs(livingEntity.scaleFactor12 * (float) Math.PI / 180.0F);
            float factor15 = MathHelper.cos(livingEntity.scaleFactor12 * (float) Math.PI / 180.0F);
            offset4 += (double)(factor12 * factor15 - factor13 * factor14);
            offset5 += (double)(factor13 * factor15 + factor12 * factor14);
         }

         if (enabled) {
            new BlockPos(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY) - 1, MathHelper.ceiling_double_int(posZ));
         }

         if (offset4 > 10.0 || offset3 > 10.0 || offset5 > 10.0) {
            return list;
         }

         double offset7 = offset3;
         double offset8 = offset4;
         double offset9 = offset5;
         boolean sprinting = livingEntity.isSprinting();
         if (livingEntity.onGround && sprinting) {
            double offset10;
            for (offset10 = 0.05; offset4 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(offset4, -1.0, 0.0)).isEmpty(); offset8 = offset4) {
               if (offset4 < offset10 && offset4 >= -offset10) {
                  offset4 = 0.0;
               } else if (offset4 > 0.0) {
                  offset4 -= offset10;
               } else {
                  offset4 += offset10;
               }
            }

            for (; offset5 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(0.0, -1.0, offset5)).isEmpty(); offset9 = offset5) {
               if (offset5 < offset10 && offset5 >= -offset10) {
                  offset5 = 0.0;
               } else if (offset5 > 0.0) {
                  offset5 -= offset10;
               } else {
                  offset5 += offset10;
               }
            }

            for (; offset4 != 0.0 && offset5 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(offset4, -1.0, offset5)).isEmpty(); offset9 = offset5) {
               if (offset4 < offset10 && offset4 >= -offset10) {
                  offset4 = 0.0;
               } else if (offset4 > 0.0) {
                  offset4 -= offset10;
               } else {
                  offset4 += offset10;
               }

               offset8 = offset4;
               if (offset5 < offset10 && offset5 >= -offset10) {
                  offset5 = 0.0;
               } else if (offset5 > 0.0) {
                  offset5 -= offset10;
               } else {
                  offset5 += offset10;
               }
            }
         }

         List collidingBoundingBoxes = minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.contract(offset4, offset3, offset5));
         AxisAlignedBB boundingBox3 = boundingBox2;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset7 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox2, offset7);
         }

         AxisAlignedBB boundingBox4 = boundingBox2.expand(0.0, offset7, 0.0);
         boolean flag = enabled || offset3 != offset7 && offset3 < 0.0;
         Iterator iterator2 = collidingBoundingBoxes.iterator();

         while (iterator2.hasNext()) {
            offset4 = ((AxisAlignedBB)iterator2.next()).getCoordinate2(boundingBox4, offset4);
         }

         AxisAlignedBB boundingBox5 = boundingBox4.expand(offset4, 0.0, 0.0);
         Iterator iterator3 = collidingBoundingBoxes.iterator();

         while (iterator3.hasNext()) {
            offset5 = ((AxisAlignedBB)iterator3.next()).getCoordinate(boundingBox5, offset5);
         }

         boundingBox2 = boundingBox5.expand(0.0, 0.0, offset5);
         if (livingEntity.stepHeight > 0.0F && flag && (offset8 != offset4 || offset9 != offset5)) {
            double offset11 = offset4;
            double offset12 = offset7;
            double offset13 = offset5;
            AxisAlignedBB boundingBox6 = boundingBox2;
            double factor16 = (double)livingEntity.stepHeight;
            List collidingBoundingBoxes2 = minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox3.contract(offset8, factor16, offset9));
            AxisAlignedBB boundingBox7 = boundingBox3.contract(offset8, 0.0, offset9);
            double offset14 = factor16;
            Iterator iterator4 = collidingBoundingBoxes2.iterator();

            while (iterator4.hasNext()) {
               offset14 = ((AxisAlignedBB)iterator4.next()).getCoordinate3(boundingBox7, offset14);
            }

            AxisAlignedBB boundingBox8 = boundingBox3.expand(0.0, offset14, 0.0);
            offset4 = offset8;
            Iterator iterator5 = collidingBoundingBoxes2.iterator();

            while (iterator5.hasNext()) {
               offset4 = ((AxisAlignedBB)iterator5.next()).getCoordinate2(boundingBox8, offset4);
            }

            AxisAlignedBB boundingBox9 = boundingBox8.expand(offset4, 0.0, 0.0);
            offset5 = offset9;
            Iterator iterator6 = collidingBoundingBoxes2.iterator();

            while (iterator6.hasNext()) {
               offset5 = ((AxisAlignedBB)iterator6.next()).getCoordinate(boundingBox9, offset5);
            }

            AxisAlignedBB boundingBox10 = boundingBox9.expand(0.0, 0.0, offset5);
            AxisAlignedBB boundingBox11 = boundingBox3;
            Iterator iterator7 = collidingBoundingBoxes2.iterator();

            while (iterator7.hasNext()) {
               factor16 = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox11, factor16);
            }

            AxisAlignedBB boundingBox12 = boundingBox11.expand(0.0, factor16, 0.0);
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
            double offset17 = offset4 * offset4 + offset5 * offset5;
            double offset18 = offset15 * offset15 + offset16 * offset16;
            AxisAlignedBB boundingBox15;
            if (offset17 > offset18) {
               offset7 = -offset14;
               boundingBox15 = boundingBox10;
            } else {
               offset4 = offset15;
               offset5 = offset16;
               offset7 = -factor16;
               boundingBox15 = boundingBox14;
            }

            Iterator iterator10 = collidingBoundingBoxes2.iterator();

            while (iterator10.hasNext()) {
               offset7 = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox15, offset7);
            }

            boundingBox2 = boundingBox15.expand(0.0, offset7, 0.0);
            if (offset11 * offset11 + offset13 * offset13 >= offset4 * offset4 + offset5 * offset5) {
               offset4 = offset11;
               offset7 = offset12;
               offset5 = offset13;
               boundingBox2 = boundingBox6;
            }
         }

         posX = (boundingBox2.minX + boundingBox2.maxX) / 2.0;
         posY = boundingBox2.minY;
         posZ = (boundingBox2.minZ + boundingBox2.maxZ) / 2.0;
         boolean flag2 = offset8 != offset4 || offset9 != offset5;
         boolean flag3 = offset3 != offset7;
         enabled = flag3 && offset3 < 0.0;
         if (!flag2 && !flag3) {
         } else {
         }

         int count4 = MathHelper.ceiling_double_int(posX);
         int count5 = MathHelper.ceiling_double_int(posY - 0.2F);
         int count6 = MathHelper.ceiling_double_int(posZ);
         BlockPos blockPos = new BlockPos(count4, count5, count6);
         Block block = minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (offset3 != offset7) {
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

         double offset19;
         if (livingEntity.worldObj.state
            && (
               !livingEntity.worldObj.checkBlockPos12(new BlockPos((int)posX, 0, (int)posZ))
                  || !livingEntity.worldObj.getLoggerEntry(new BlockPos((int)posX, 0, (int)posZ)).canExecute2()
            )) {
            if (posY > 0.0) {
               offset19 = -0.1;
            } else {
               offset19 = 0.0;
            }
         } else {
            offset19 = offset3 - 0.08;
         }

         offset3 = offset19 * 0.98F;
         list.add(new Vec3(posX, posY, posZ));
      }

      return list;
   }

   public static Vec3 resolveVecByEntityLivingBase(EntityLivingBase livingEntity, Vec3Entry vec3Entry, int count) {
      if (checkEntityLivingBase(1222936913, 29201, livingEntity)) {
         return new Vec3(livingEntity.posX, livingEntity.posY, livingEntity.posZ);
      } else {
         int[] countArray = resolveIndexArray(livingEntity, vec3Entry);
         int count4 = countArray[0];
         int count5 = countArray[1];
         int count6 = count4;
         return resolveVec3(livingEntity, vec3Entry, count6, count5, count, false);
      }
   }

   public static double[] getCoordOffsetArray(EntityLivingBase livingEntity, int count) {
      if (checkEntityLivingBase(1222936913, 29201, livingEntity)) {
         return new double[]{
            livingEntity.posX,
            livingEntity.posY,
            livingEntity.posZ,
            livingEntity.prevPosX,
            livingEntity.prevPosY,
            livingEntity.prevPosZ,
            livingEntity.motionX,
            livingEntity.motionY,
            livingEntity.motionZ,
            livingEntity.onGround ? 1.0 : 0.0,
            livingEntity.isCollidedHorizontally ? 1.0 : 0.0
         };
      } else {
         int[] indexOffsetArray = getIndexOffsetArray(livingEntity);
         int count4 = indexOffsetArray[0];
         int count5 = indexOffsetArray[1];
         int count6 = count4;
         return resolveDoubleArray(livingEntity, count6, count5, count);
      }
   }

   public static int[] resolveIndexArray(EntityLivingBase livingEntity, Vec3Entry vec3Entry) {
      if (checkEntityLivingBase(1222936913, 29201, livingEntity)) {
         return new int[]{0, 0};
      } else {
         double offset = 1000.0;
         int[] countArray = new int[2];

         for (int index = -1; index <= 1; index++) {
            for (int index2 = -1; index2 <= 1; index2++) {
               double offset2 = resolveVec3(livingEntity, vec3Entry, index, index2, 1, true).dotProduct(vec3Entry.vec3);
               if (offset > offset2) {
                  offset = offset2;
                  countArray = new int[]{index, index2};
               }

               if (offset2 < 0.01 && index == 0 && index2 == 0) {
                  countArray = new int[2];
                  offset = 0.0;
               }
            }
         }

         return countArray;
      }
   }

   public static Vec3 resolveVec3(byte byteValue, int count, EntityLivingBase livingEntity, int count2, int count3) {
      if (checkEntityLivingBase(1222936913, 29201, livingEntity)) {
         return new Vec3(livingEntity.posX, livingEntity.posY, livingEntity.posZ);
      } else {
         int[] countArray = resolveIndexArray(livingEntity);
         int count8 = countArray[0];
         int count9 = countArray[1];
         int count10 = count8;
         return resolveVecByEntityLivingBase(livingEntity, count10, count9, count2, false);
      }
   }

   public static ArrayList resolveArrayListByEntityLivingBase(
      EntityLivingBase livingEntity, char character, Vec3Entry vec3Entry, int count, double posX, double posY, double posZ, int count2, char character2
   ) {
      return resolveArrayListByEntityLivingBase(livingEntity, vec3Entry, count, posX, posY, posZ, false);
   }

   private static double[] resolveDoubleArray(EntityLivingBase livingEntity, int count, int count2, int count3) {
      double coordOffset = livingEntity.coordOffset5;
      double coordOffset2 = livingEntity.coordOffset20;
      double coordOffset3 = livingEntity.coordOffset2;
      double doubleItem = MathHelper.getDouble7((livingEntity.coordOffset5 - livingEntity.value12) / 2.0, -10.0, 10.0);
      double doubleItem2 = MathHelper.getDouble7((livingEntity.coordOffset20 - livingEntity.value20) / 2.0, -10.0, 10.0);
      double doubleItem3 = MathHelper.getDouble7((livingEntity.coordOffset2 - livingEntity.value10) / 2.0, -10.0, 10.0);
      double value = livingEntity.prevPosX;
      double value2 = livingEntity.prevPosY;
      double value3 = livingEntity.prevPosZ;
      boolean enabled = livingEntity.onGround;
      float factor = livingEntity.width / 2.0F;
      AxisAlignedBB boundingBox = new AxisAlignedBB(
         coordOffset - (double)factor, coordOffset2, coordOffset3 - (double)factor, coordOffset + (double)factor, coordOffset2 + (double)livingEntity.height, coordOffset3 + (double)factor
      );
      AxisAlignedBB boundingBox2 = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      boolean enabled2 = livingEntity.isCollidedHorizontally;
      float factor2 = 0.91F;
      if (enabled) {
         factor2 = livingEntity.worldObj.getIBlockState2(coordOffset, coordOffset2 - 1.0, coordOffset3).getBlock().scaleFactor * 0.91F;
      }

      double offset;
      if (livingEntity.worldObj.state
         && (
            !livingEntity.worldObj.checkBlockPos12(new BlockPos((int)coordOffset, 0, (int)coordOffset3))
               || !livingEntity.worldObj.getLoggerEntry(new BlockPos((int)coordOffset, 0, (int)coordOffset3)).canExecute2()
         )) {
         if (coordOffset2 > 0.0) {
            offset = -0.1;
         } else {
            offset = 0.0;
         }
      } else {
         offset = doubleItem2 - 0.08;
      }

      double offset2 = offset * 0.98F;
      double offset3 = doubleItem * (double)factor2;
      double offset4 = doubleItem3 * (double)factor2;

      for (int index = 0; index < count3; index++) {
         float factor3 = (float)count2;
         float factor4 = (float)count;
         if (livingEntity instanceof EntityPlayer && ((EntityPlayer)livingEntity).isUsingItem() && !livingEntity.isSneaking()) {
            factor3 *= 0.2F;
            factor4 *= 0.2F;
         }

         double offset5 = ViaLoadingBase.processAction2();
         if (Math.abs(offset3) < offset5) {
            offset3 = 0.0;
         }

         if (Math.abs(offset2) < offset5) {
            offset2 = 0.0;
         }

         if (Math.abs(offset4) < offset5) {
            offset4 = 0.0;
         }

         float factor5 = factor3 * 0.98F;
         float factor6 = factor4 * 0.98F;
         float factor7 = 0.91F;
         if (enabled) {
            factor7 = livingEntity.worldObj
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(coordOffset), MathHelper.ceiling_double_int(coordOffset2) - 1, MathHelper.ceiling_double_int(coordOffset3))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         float factor8 = 0.16277136F / (factor7 * factor7 * factor7);
         float factor9;
         if (enabled) {
            factor9 = (float)(minecraft.thePlayer.getValue2() * (double)factor8);
         } else {
            factor9 = livingEntity.scaleFactor10;
         }

         float factor10 = factor5 * factor5 + factor6 * factor6;
         if (factor10 >= 1.0E-4F) {
            float factor11 = MathHelper.sin(factor10);
            if (factor11 < 1.0F) {
               factor11 = 1.0F;
            }

            float factor12 = factor9 / factor11;
            float factor13 = factor5 * factor12;
            float factor14 = factor6 * factor12;
            float factor15 = MathHelper.abs(livingEntity.scaleFactor12 * (float) Math.PI / 180.0F);
            float factor16 = MathHelper.cos(livingEntity.scaleFactor12 * (float) Math.PI / 180.0F);
            offset3 += (double)(factor13 * factor16 - factor14 * factor15);
            offset4 += (double)(factor14 * factor16 + factor13 * factor15);
         }

         float factor17 = 0.91F;
         if (enabled) {
            factor17 = minecraft.theWorld
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(coordOffset), MathHelper.ceiling_double_int(coordOffset2) - 1, MathHelper.ceiling_double_int(coordOffset3))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         double offset6 = offset3;
         double offset7 = offset2;
         double offset8 = offset4;
         double offset9 = offset3;
         double offset10 = offset4;
         boolean sprinting = livingEntity.isSprinting();
         if (livingEntity.onGround && sprinting) {
            double offset11;
            for (offset11 = 0.05; offset6 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(offset6, -1.0, 0.0)).isEmpty(); offset9 = offset6) {
               if (offset6 < offset11 && offset6 >= -offset11) {
                  offset6 = 0.0;
               } else if (offset6 > 0.0) {
                  offset6 -= offset11;
               } else {
                  offset6 += offset11;
               }
            }

            for (; offset8 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(0.0, -1.0, offset8)).isEmpty(); offset10 = offset8) {
               if (offset8 < offset11 && offset8 >= -offset11) {
                  offset8 = 0.0;
               } else if (offset8 > 0.0) {
                  offset8 -= offset11;
               } else {
                  offset8 += offset11;
               }
            }

            for (; offset6 != 0.0 && offset8 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(offset6, -1.0, offset8)).isEmpty(); offset10 = offset8) {
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

         List collidingBoundingBoxes = minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.contract(offset6, offset2, offset8));
         AxisAlignedBB boundingBox3 = boundingBox2;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset7 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox2, offset7);
         }

         AxisAlignedBB boundingBox4 = boundingBox2.expand(0.0, offset7, 0.0);
         boolean flag = enabled || offset2 != offset7 && offset2 < 0.0;
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
         if (livingEntity.stepHeight > 0.0F && flag && (offset9 != offset6 || offset10 != offset8)) {
            double offset12 = offset6;
            double offset13 = offset7;
            double offset14 = offset8;
            AxisAlignedBB boundingBox6 = boundingBox2;
            double factor18 = (double)livingEntity.stepHeight;
            List collidingBoundingBoxes2 = minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox3.contract(offset9, factor18, offset10));
            AxisAlignedBB boundingBox7 = boundingBox3.contract(offset9, 0.0, offset10);
            double offset15 = factor18;
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
               factor18 = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox11, factor18);
            }

            AxisAlignedBB boundingBox12 = boundingBox11.expand(0.0, factor18, 0.0);
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
               offset7 = -factor18;
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

         coordOffset = (boundingBox2.minX + boundingBox2.maxX) / 2.0;
         coordOffset2 = boundingBox2.minY;
         coordOffset3 = (boundingBox2.minZ + boundingBox2.maxZ) / 2.0;
         enabled2 = offset9 != offset6 || offset10 != offset8;
         boolean flag2 = offset2 != offset7;
         enabled = flag2 && offset2 < 0.0;
         if (!enabled2 && !flag2) {
         } else {
         }

         int count4 = MathHelper.ceiling_double_int(coordOffset);
         int count5 = MathHelper.ceiling_double_int(coordOffset2 - 0.2F);
         int count6 = MathHelper.ceiling_double_int(coordOffset3);
         BlockPos blockPos = new BlockPos(count4, count5, count6);
         Block block = minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (offset9 != offset6) {
            offset3 = 0.0;
         }

         if (offset10 != offset8) {
            offset4 = 0.0;
         }

         if (offset2 != offset7) {
            if (block instanceof BlockSlime) {
               if (livingEntity.isSprinting()) {
                  offset2 = 0.0;
               } else if (offset2 < 0.0) {
                  offset2 = -offset2;
               }
            } else {
               offset2 = 0.0;
            }
         }

         double offset20;
         if (livingEntity.worldObj.state
            && (
               !livingEntity.worldObj.checkBlockPos12(new BlockPos((int)coordOffset, 0, (int)coordOffset3))
                  || !livingEntity.worldObj.getLoggerEntry(new BlockPos((int)coordOffset, 0, (int)coordOffset3)).canExecute2()
            )) {
            if (coordOffset2 > 0.0) {
               offset20 = -0.1;
            } else {
               offset20 = 0.0;
            }
         } else {
            offset20 = offset2 - 0.08;
         }

         offset2 = offset20 * 0.98F;
         offset3 *= (double)factor17;
         offset4 *= (double)factor17;
      }

      return new double[]{coordOffset, coordOffset2, coordOffset3, value, value2, value3, offset3, offset2, offset4, enabled ? 1.0 : 0.0, enabled2 ? 1.0 : 0.0};
   }

   private static Vec3 resolveVec3(EntityLivingBase livingEntity, Vec3Entry vec3Entry, int count, int count2, int count3, boolean flag) {
      Vec3 vec = flag ? vec3Entry.vec4 : vec3Entry.vec3;
      Vec3 vec2 = flag ? vec3Entry.vec2 : vec3Entry.vec4;
      double posX = vec.xCoord;
      double posY = vec.yCoord;
      double posZ = vec.zCoord;
      double offset = vec.yCoord - vec2.yCoord;
      boolean enabled = vec3Entry.enabled;
      float factor = livingEntity.width / 2.0F;
      AxisAlignedBB boundingBox = new AxisAlignedBB(
         posX - (double)factor, posY, posZ - (double)factor, posX + (double)factor, posY + (double)livingEntity.height, posZ + (double)factor
      );
      AxisAlignedBB boundingBox2 = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      livingEntity.isRiding();

      for (int index = 0; index < count3; index++) {
         double offset2 = vec.xCoord - vec2.xCoord;
         double offset3 = vec.zCoord - vec2.zCoord;
         if (livingEntity instanceof EntityPlayer && ((EntityPlayer)livingEntity).isUsingItem()) {
            livingEntity.isSneaking();
         }

         double offset4 = ViaLoadingBase.processAction2();
         if (Math.abs(offset2) < offset4) {
            offset2 = 0.0;
         }

         if (Math.abs(offset) < offset4) {
            offset = 0.0;
         }

         if (Math.abs(offset3) < offset4) {
            offset3 = 0.0;
         }

         if (enabled) {
            new BlockPos(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY) - 1, MathHelper.ceiling_double_int(posZ));
         }

         if (enabled) {
            livingEntity.getScaleFactor6();
         }

         if (enabled) {
            new BlockPos(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY) - 1, MathHelper.ceiling_double_int(posZ));
         }

         double offset5 = offset;
         double offset6 = offset2;
         double offset7 = offset3;
         if (offset2 > 100.0 || offset > 100.0 || offset3 > 100.0) {
            return new Vec3(posX, posY, posZ);
         }

         boolean enabled2 = vec3Entry.enabled2;
         if (vec3Entry.enabled && enabled2) {
            double offset8;
            for (offset8 = 0.05; offset2 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(offset2, -1.0, 0.0)).isEmpty(); offset6 = offset2) {
               if (offset2 < offset8 && offset2 >= -offset8) {
                  offset2 = 0.0;
               } else if (offset2 > 0.0) {
                  offset2 -= offset8;
               } else {
                  offset2 += offset8;
               }
            }

            for (; offset3 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(0.0, -1.0, offset3)).isEmpty(); offset7 = offset3) {
               if (offset3 < offset8 && offset3 >= -offset8) {
                  offset3 = 0.0;
               } else if (offset3 > 0.0) {
                  offset3 -= offset8;
               } else {
                  offset3 += offset8;
               }
            }

            for (; offset2 != 0.0 && offset3 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(offset2, -1.0, offset3)).isEmpty(); offset7 = offset3) {
               if (offset2 < offset8 && offset2 >= -offset8) {
                  offset2 = 0.0;
               } else if (offset2 > 0.0) {
                  offset2 -= offset8;
               } else {
                  offset2 += offset8;
               }

               offset6 = offset2;
               if (offset3 < offset8 && offset3 >= -offset8) {
                  offset3 = 0.0;
               } else if (offset3 > 0.0) {
                  offset3 -= offset8;
               } else {
                  offset3 += offset8;
               }
            }
         }

         List collidingBoundingBoxes = minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.contract(offset2, offset, offset3));
         AxisAlignedBB boundingBox3 = boundingBox2;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset5 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox2, offset5);
         }

         AxisAlignedBB boundingBox4 = boundingBox2.expand(0.0, offset5, 0.0);
         boolean flag2 = enabled || offset != offset5 && offset < 0.0;
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
         if (livingEntity.stepHeight > 0.0F && flag2 && (offset6 != offset2 || offset7 != offset3)) {
            double offset9 = offset2;
            double offset10 = offset5;
            double offset11 = offset3;
            AxisAlignedBB boundingBox6 = boundingBox2;
            double factor2 = (double)livingEntity.stepHeight;
            List collidingBoundingBoxes2 = minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox3.contract(offset6, factor2, offset7));
            AxisAlignedBB boundingBox7 = boundingBox3.contract(offset6, 0.0, offset7);
            double offset12 = factor2;
            Iterator iterator4 = collidingBoundingBoxes2.iterator();

            while (iterator4.hasNext()) {
               offset12 = ((AxisAlignedBB)iterator4.next()).getCoordinate3(boundingBox7, offset12);
            }

            AxisAlignedBB boundingBox8 = boundingBox3.expand(0.0, offset12, 0.0);
            offset2 = offset6;
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
               factor2 = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox11, factor2);
            }

            AxisAlignedBB boundingBox12 = boundingBox11.expand(0.0, factor2, 0.0);
            double offset13 = offset6;
            Iterator iterator8 = collidingBoundingBoxes2.iterator();

            while (iterator8.hasNext()) {
               offset13 = ((AxisAlignedBB)iterator8.next()).getCoordinate2(boundingBox12, offset13);
            }

            AxisAlignedBB boundingBox13 = boundingBox12.expand(offset13, 0.0, 0.0);
            double offset14 = offset7;
            Iterator iterator9 = collidingBoundingBoxes2.iterator();

            while (iterator9.hasNext()) {
               offset14 = ((AxisAlignedBB)iterator9.next()).getCoordinate(boundingBox13, offset14);
            }

            AxisAlignedBB boundingBox14 = boundingBox13.expand(0.0, 0.0, offset14);
            double offset15 = offset2 * offset2 + offset3 * offset3;
            double offset16 = offset13 * offset13 + offset14 * offset14;
            AxisAlignedBB boundingBox15;
            if (offset15 > offset16) {
               offset5 = -offset12;
               boundingBox15 = boundingBox10;
            } else {
               offset2 = offset13;
               offset3 = offset14;
               offset5 = -factor2;
               boundingBox15 = boundingBox14;
            }

            Iterator iterator10 = collidingBoundingBoxes2.iterator();

            while (iterator10.hasNext()) {
               offset5 = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox15, offset5);
            }

            boundingBox2 = boundingBox15.expand(0.0, offset5, 0.0);
            if (offset9 * offset9 + offset11 * offset11 >= offset2 * offset2 + offset3 * offset3) {
               offset2 = offset9;
               offset5 = offset10;
               offset3 = offset11;
               boundingBox2 = boundingBox6;
            }
         }

         posX = (boundingBox2.minX + boundingBox2.maxX) / 2.0;
         posY = boundingBox2.minY;
         posZ = (boundingBox2.minZ + boundingBox2.maxZ) / 2.0;
         boolean flag3 = offset6 != offset2 || offset7 != offset3;
         boolean flag4 = offset != offset5;
         enabled = flag4 && offset < 0.0;
         if (!flag3 && !flag4) {
         } else {
         }

         int count4 = MathHelper.ceiling_double_int(posX);
         int count5 = MathHelper.ceiling_double_int(posY - 0.2F);
         int count6 = MathHelper.ceiling_double_int(posZ);
         BlockPos blockPos = new BlockPos(count4, count5, count6);
         Block block = minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (offset != offset5) {
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

         double offset17;
         if (livingEntity.worldObj.state
            && (
               !livingEntity.worldObj.checkBlockPos12(new BlockPos((int)posX, 0, (int)posZ))
                  || !livingEntity.worldObj.getLoggerEntry(new BlockPos((int)posX, 0, (int)posZ)).canExecute2()
            )) {
            if (posY > 0.0) {
               offset17 = -0.1;
            } else {
               offset17 = 0.0;
            }
         } else {
            offset17 = offset - 0.08;
         }

         offset = offset17 * 0.98F;
      }

      return new Vec3(posX, posY, posZ);
   }

   private static ArrayList resolveArrayListByEntityLivingBase(
      EntityLivingBase livingEntity, Vec3Entry vec3Entry, int count, int count2, int count3, boolean flag, double posX, double posY, double posZ, boolean flag2
   ) {
      Vec3 vec = flag ? vec3Entry.vec4 : vec3Entry.vec3;
      Vec3 vec2 = flag ? vec3Entry.vec2 : vec3Entry.vec4;
      double posX2 = vec.xCoord;
      double posY2 = vec.yCoord;
      double posZ2 = vec.zCoord;
      if (flag2) {
         posX2 = (double)livingEntity.count13 / 32.0;
         posY2 = (double)livingEntity.count18 / 32.0;
         posZ2 = (double)livingEntity.count19 / 32.0;
      }

      double offset = posY;
      boolean enabled = vec3Entry.enabled;
      float factor = livingEntity.width / 2.0F;
      AxisAlignedBB boundingBox = new AxisAlignedBB(
         posX2 - (double)factor, posY2, posZ2 - (double)factor, posX2 + (double)factor, posY2 + (double)livingEntity.height, posZ2 + (double)factor
      );
      AxisAlignedBB boundingBox2 = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      livingEntity.isRiding();
      ArrayList list = new ArrayList();

      for (int index = 0; index < count3; index++) {
         double offset2 = posX;
         double offset3 = posZ;
         if (livingEntity instanceof EntityPlayer && ((EntityPlayer)livingEntity).isUsingItem()) {
            livingEntity.isSneaking();
         }

         double offset4 = ViaLoadingBase.processAction2();
         if (Math.abs(posX) < offset4) {
            offset2 = 0.0;
         }

         if (Math.abs(offset) < offset4) {
            offset = 0.0;
         }

         if (Math.abs(posZ) < offset4) {
            offset3 = 0.0;
         }

         if (enabled) {
            new BlockPos(MathHelper.ceiling_double_int(posX2), MathHelper.ceiling_double_int(posY2) - 1, MathHelper.ceiling_double_int(posZ2));
         }

         if (enabled) {
            livingEntity.getScaleFactor6();
         }

         if (enabled) {
            new BlockPos(MathHelper.ceiling_double_int(posX2), MathHelper.ceiling_double_int(posY2) - 1, MathHelper.ceiling_double_int(posZ2));
         }

         double offset5 = offset;
         double offset6 = offset2;
         double offset7 = offset3;
         if (Math.abs(offset2) > 100.0 || Math.abs(offset) > 100.0 || Math.abs(offset3) > 100.0) {
            list.add(new Vec3(posX2, posY2, posZ2));
            break;
         }

         boolean enabled2 = vec3Entry.enabled2;
         if (vec3Entry.enabled && enabled2) {
            double offset8;
            for (offset8 = 0.05; offset2 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(offset2, -1.0, 0.0)).isEmpty(); offset6 = offset2) {
               if (offset2 < offset8 && offset2 >= -offset8) {
                  offset2 = 0.0;
               } else if (offset2 > 0.0) {
                  offset2 -= offset8;
               } else {
                  offset2 += offset8;
               }
            }

            for (; offset3 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(0.0, -1.0, offset3)).isEmpty(); offset7 = offset3) {
               if (offset3 < offset8 && offset3 >= -offset8) {
                  offset3 = 0.0;
               } else if (offset3 > 0.0) {
                  offset3 -= offset8;
               } else {
                  offset3 += offset8;
               }
            }

            for (; offset2 != 0.0 && offset3 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(offset2, -1.0, offset3)).isEmpty(); offset7 = offset3) {
               if (offset2 < offset8 && offset2 >= -offset8) {
                  offset2 = 0.0;
               } else if (offset2 > 0.0) {
                  offset2 -= offset8;
               } else {
                  offset2 += offset8;
               }

               offset6 = offset2;
               if (offset3 < offset8 && offset3 >= -offset8) {
                  offset3 = 0.0;
               } else if (offset3 > 0.0) {
                  offset3 -= offset8;
               } else {
                  offset3 += offset8;
               }
            }
         }

         List collidingBoundingBoxes = minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.contract(offset2, offset, offset3));
         AxisAlignedBB boundingBox3 = boundingBox2;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset5 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox2, offset5);
         }

         AxisAlignedBB boundingBox4 = boundingBox2.expand(0.0, offset5, 0.0);
         boolean flag3 = enabled || offset != offset5 && offset < 0.0;
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
         if (livingEntity.stepHeight > 0.0F && flag3 && (offset6 != offset2 || offset7 != offset3)) {
            double offset9 = offset2;
            double offset10 = offset5;
            double offset11 = offset3;
            AxisAlignedBB boundingBox6 = boundingBox2;
            double factor2 = (double)livingEntity.stepHeight;
            List collidingBoundingBoxes2 = minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox3.contract(offset6, factor2, offset7));
            AxisAlignedBB boundingBox7 = boundingBox3.contract(offset6, 0.0, offset7);
            double offset12 = factor2;
            Iterator iterator4 = collidingBoundingBoxes2.iterator();

            while (iterator4.hasNext()) {
               offset12 = ((AxisAlignedBB)iterator4.next()).getCoordinate3(boundingBox7, offset12);
            }

            AxisAlignedBB boundingBox8 = boundingBox3.expand(0.0, offset12, 0.0);
            offset2 = offset6;
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
               factor2 = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox11, factor2);
            }

            AxisAlignedBB boundingBox12 = boundingBox11.expand(0.0, factor2, 0.0);
            double offset13 = offset6;
            Iterator iterator8 = collidingBoundingBoxes2.iterator();

            while (iterator8.hasNext()) {
               offset13 = ((AxisAlignedBB)iterator8.next()).getCoordinate2(boundingBox12, offset13);
            }

            AxisAlignedBB boundingBox13 = boundingBox12.expand(offset13, 0.0, 0.0);
            double offset14 = offset7;
            Iterator iterator9 = collidingBoundingBoxes2.iterator();

            while (iterator9.hasNext()) {
               offset14 = ((AxisAlignedBB)iterator9.next()).getCoordinate(boundingBox13, offset14);
            }

            AxisAlignedBB boundingBox14 = boundingBox13.expand(0.0, 0.0, offset14);
            double offset15 = offset2 * offset2 + offset3 * offset3;
            double offset16 = offset13 * offset13 + offset14 * offset14;
            AxisAlignedBB boundingBox15;
            if (offset15 > offset16) {
               offset5 = -offset12;
               boundingBox15 = boundingBox10;
            } else {
               offset2 = offset13;
               offset3 = offset14;
               offset5 = -factor2;
               boundingBox15 = boundingBox14;
            }

            Iterator iterator10 = collidingBoundingBoxes2.iterator();

            while (iterator10.hasNext()) {
               offset5 = ((AxisAlignedBB)iterator10.next()).getCoordinate3(boundingBox15, offset5);
            }

            boundingBox2 = boundingBox15.expand(0.0, offset5, 0.0);
            if (offset9 * offset9 + offset11 * offset11 >= offset2 * offset2 + offset3 * offset3) {
               offset2 = offset9;
               offset5 = offset10;
               offset3 = offset11;
               boundingBox2 = boundingBox6;
            }
         }

         posX2 = (boundingBox2.minX + boundingBox2.maxX) / 2.0;
         posY2 = boundingBox2.minY;
         posZ2 = (boundingBox2.minZ + boundingBox2.maxZ) / 2.0;
         boolean flag4 = offset6 != offset2 || offset7 != offset3;
         boolean flag5 = offset != offset5;
         enabled = flag5 && offset < 0.0;
         if (!flag4 && !flag5) {
         } else {
         }

         int count4 = MathHelper.ceiling_double_int(posX2);
         int count5 = MathHelper.ceiling_double_int(posY2 - 0.2F);
         int count6 = MathHelper.ceiling_double_int(posZ2);
         BlockPos blockPos = new BlockPos(count4, count5, count6);
         Block block = minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (offset != offset5) {
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

         double offset17;
         if (livingEntity.worldObj.state
            && (
               !livingEntity.worldObj.checkBlockPos12(new BlockPos((int)posX2, 0, (int)posZ2))
                  || !livingEntity.worldObj.getLoggerEntry(new BlockPos((int)posX2, 0, (int)posZ2)).canExecute2()
            )) {
            if (posY2 > 0.0) {
               offset17 = -0.1;
            } else {
               offset17 = 0.0;
            }
         } else {
            offset17 = offset - 0.08;
         }

         offset = offset17 * 0.98F;
         list.add(new Vec3(posX2, posY2, posZ2));
      }

      return list;
   }

   public static Vec3 resolveVec2(EntityLivingBase livingEntity, int count) {
      if (checkEntityLivingBase(1222936913, 29201, livingEntity)) {
         return new Vec3(livingEntity.posX, livingEntity.posY, livingEntity.posZ);
      } else {
         int[] indexOffsetArray = getIndexOffsetArray(livingEntity);
         int count4 = indexOffsetArray[0];
         int count5 = indexOffsetArray[1];
         int count6 = count4;
         double[] offsetArray = resolveDoubleArray(livingEntity, count6, count5, count);
         return new Vec3(offsetArray[0], offsetArray[1], offsetArray[2]);
      }
   }

   public static Vec3 getVec3(int count, EntityLivingBase livingEntity, int count2) {
      if (checkEntityLivingBase(1222936913, 29201, livingEntity)) {
         return new Vec3(livingEntity.posX, livingEntity.posY, livingEntity.posZ);
      } else {
         int[] countArray = resolveIndexArray(livingEntity);
         int count6 = countArray[0];
         int count7 = countArray[1];
         int count8 = count6;
         return resolveVec3(livingEntity, count8, count7, count2, false);
      }
   }

   private static Vec3 resolveVecByEntityLivingBase(EntityLivingBase livingEntity, int count, int count2, int count3, boolean flag) {
      int count4 = flag ? 1 : 0;
      Vec3 object = (Vec3)livingEntity.backtrackRecord.getObject3(count4);
      int count5 = 1 + count4;
      Vec3 object2 = (Vec3)livingEntity.backtrackRecord.getObject3(count5);
      double posX = object.xCoord;
      double posY = object.yCoord;
      double posZ = object.zCoord;
      double offset = object.xCoord - object2.xCoord;
      double offset2 = object.yCoord - object2.yCoord;
      double offset3 = object.zCoord - object2.zCoord;
      boolean enabled = livingEntity.onGround;
      float factor = livingEntity.width / 2.0F;
      AxisAlignedBB boundingBox = new AxisAlignedBB(
         posX - (double)factor, posY, posZ - (double)factor, posX + (double)factor, posY + (double)livingEntity.height, posZ + (double)factor
      );
      AxisAlignedBB boundingBox2 = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      livingEntity.isRiding();

      for (int index = 0; index < count3; index++) {
         float factor2 = (float)count2;
         float factor3 = (float)count;
         if (livingEntity instanceof EntityPlayer && ((EntityPlayer)livingEntity).isUsingItem() && !livingEntity.isSneaking()) {
            factor2 *= 0.2F;
            factor3 *= 0.2F;
         }

         double offset4 = ViaLoadingBase.processAction2();
         if (Math.abs(offset) < offset4) {
            offset = 0.0;
         }

         if (Math.abs(offset2) < offset4) {
            offset2 = 0.0;
         }

         if (Math.abs(offset3) < offset4) {
            offset3 = 0.0;
         }

         float factor4 = factor2 * 0.98F;
         float factor5 = factor3 * 0.98F;
         float factor6 = 0.91F;
         if (enabled) {
            factor6 = livingEntity.worldObj
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
            factor8 = livingEntity.getScaleFactor6() * factor7;
         } else {
            factor8 = livingEntity.scaleFactor10;
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
            float factor14 = MathHelper.abs(livingEntity.scaleFactor22 * (float) Math.PI / 180.0F);
            float factor15 = MathHelper.cos(livingEntity.scaleFactor22 * (float) Math.PI / 180.0F);
            offset += (double)(factor12 * factor15 - factor13 * factor14);
            offset3 += (double)(factor13 * factor15 + factor12 * factor14);
         }

         float factor16 = 0.91F;
         if (enabled) {
            factor16 = minecraft.theWorld
                  .getBlockState(
                     new BlockPos(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY) - 1, MathHelper.ceiling_double_int(posZ))
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         double offset5 = offset;
         double offset6 = offset2;
         double offset7 = offset3;
         double offset8 = offset;
         double offset9 = offset3;
         if (offset > 100.0 || offset2 > 100.0 || offset3 > 100.0) {
            return new Vec3(posX, posY, posZ);
         }

         boolean sprinting = livingEntity.isSprinting();
         if (livingEntity.onGround && sprinting) {
            double offset10;
            for (offset10 = 0.05; offset5 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(offset5, -1.0, 0.0)).isEmpty(); offset8 = offset5) {
               if (offset5 < offset10 && offset5 >= -offset10) {
                  offset5 = 0.0;
               } else if (offset5 > 0.0) {
                  offset5 -= offset10;
               } else {
                  offset5 += offset10;
               }
            }

            for (; offset7 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(0.0, -1.0, offset7)).isEmpty(); offset9 = offset7) {
               if (offset7 < offset10 && offset7 >= -offset10) {
                  offset7 = 0.0;
               } else if (offset7 > 0.0) {
                  offset7 -= offset10;
               } else {
                  offset7 += offset10;
               }
            }

            for (; offset5 != 0.0 && offset7 != 0.0 && minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.expand(offset5, -1.0, offset7)).isEmpty(); offset9 = offset7) {
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

         List collidingBoundingBoxes = minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox2.contract(offset5, offset2, offset7));
         AxisAlignedBB boundingBox3 = boundingBox2;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset6 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox2, offset6);
         }

         AxisAlignedBB boundingBox4 = boundingBox2.expand(0.0, offset6, 0.0);
         boolean flag2 = enabled || offset2 != offset6 && offset2 < 0.0;
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
         if (livingEntity.stepHeight > 0.0F && flag2 && (offset8 != offset5 || offset9 != offset7)) {
            double offset11 = offset5;
            double offset12 = offset6;
            double offset13 = offset7;
            AxisAlignedBB boundingBox6 = boundingBox2;
            double factor17 = (double)livingEntity.stepHeight;
            List collidingBoundingBoxes2 = minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox3.contract(offset8, factor17, offset9));
            AxisAlignedBB boundingBox7 = boundingBox3.contract(offset8, 0.0, offset9);
            double offset14 = factor17;
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
               factor17 = ((AxisAlignedBB)iterator7.next()).getCoordinate3(boundingBox11, factor17);
            }

            AxisAlignedBB boundingBox12 = boundingBox11.expand(0.0, factor17, 0.0);
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
               offset6 = -factor17;
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

         posX = (boundingBox2.minX + boundingBox2.maxX) / 2.0;
         posY = boundingBox2.minY;
         posZ = (boundingBox2.minZ + boundingBox2.maxZ) / 2.0;
         boolean flag3 = offset8 != offset5 || offset9 != offset7;
         boolean flag4 = offset2 != offset6;
         enabled = flag4 && offset2 < 0.0;
         if (!flag3 && !flag4) {
         } else {
         }

         int count6 = MathHelper.ceiling_double_int(posX);
         int count7 = MathHelper.ceiling_double_int(posY - 0.2F);
         int count8 = MathHelper.ceiling_double_int(posZ);
         BlockPos blockPos = new BlockPos(count6, count7, count8);
         Block block = minecraft.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            Block block2 = minecraft.theWorld.getBlockState(blockPos.down()).getBlock();
            if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
               block = block2;
               blockPos.down();
            }
         }

         if (offset8 != offset5) {
            offset = 0.0;
         }

         if (offset9 != offset7) {
            offset3 = 0.0;
         }

         if (offset2 != offset6) {
            if (block instanceof BlockSlime) {
               if (livingEntity.isSprinting()) {
                  offset2 = 0.0;
               } else if (offset2 < 0.0) {
                  offset2 = -offset2;
               }
            } else {
               offset2 = 0.0;
            }
         }

         double offset19;
         if (livingEntity.worldObj.state
            && (
               !livingEntity.worldObj.checkBlockPos12(new BlockPos((int)posX, 0, (int)posZ))
                  || !livingEntity.worldObj.getLoggerEntry(new BlockPos((int)posX, 0, (int)posZ)).canExecute2()
            )) {
            if (posY > 0.0) {
               offset19 = -0.1;
            } else {
               offset19 = 0.0;
            }
         } else {
            offset19 = offset2 - 0.08;
         }

         offset2 = offset19 * 0.98F;
         offset *= (double)factor16;
         offset3 *= (double)factor16;
      }

      return new Vec3(posX, posY, posZ);
   }

   public static boolean checkEntityLivingBase(int count, int count2, EntityLivingBase livingEntity) {
      return livingEntity == null
         || livingEntity.value16 == Double.MAX_VALUE
         || livingEntity.value7 == Double.MAX_VALUE
         || livingEntity.value17 == Double.MAX_VALUE
         || livingEntity.coordOffset5 == Double.MAX_VALUE
         || livingEntity.coordOffset20 == Double.MAX_VALUE
         || livingEntity.coordOffset2 == Double.MAX_VALUE
         || livingEntity.value12 == Double.MAX_VALUE
         || livingEntity.value20 == Double.MAX_VALUE
         || livingEntity.value10 == Double.MAX_VALUE;
   }

   public static ArrayList resolveArrayListByEntityLivingBase(
      EntityLivingBase livingEntity, Vec3Entry vec3Entry, int count, double posX, double posY, double posZ, boolean flag
   ) {
      if (checkEntityLivingBase(1222936913, 29201, livingEntity)) {
         ArrayList list = new ArrayList();
         list.add(new Vec3(livingEntity.posX, livingEntity.posY, livingEntity.posZ));
         return list;
      } else {
         int[] countArray = resolveIndexArray(livingEntity, vec3Entry);
         int count4 = countArray[0];
         int count5 = countArray[1];
         int count6 = count4;
         return resolveArrayListByEntityLivingBase(livingEntity, vec3Entry, count6, count5, count, false, posX, posY, posZ, flag);
      }
   }
}
