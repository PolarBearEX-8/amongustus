package net.minecraft.entity;

import client.render.WorldRenderer;
import client.util.HitBoxFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EffectRenderer;
import net.minecraft.item.ItemDye;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityTadpoleAdapter extends TertiaryEntityAdapter {
   public int count15;
   public final EffectRenderer effectRenderer;
   public NBTTagList nBTTagList;
   public boolean enabled13;

   private void applyIndexArray(
      double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray, int[] countArray2, boolean flag, boolean flag2
   ) {
      EntityHuskAdapter entityHuskAdapter = new EntityHuskAdapter(this.worldObj, posX, posX2, posX3, posX4, posY, posZ, this.effectRenderer);
      entityHuskAdapter.applyFloat(0.99F);
      entityHuskAdapter.setEnabled15(flag);
      entityHuskAdapter.setEnabled14(flag2);
      int count = this.random.nextInt(countArray.length);
      entityHuskAdapter.applyIndex7(countArray[count]);
      if (countArray2 != null && countArray2.length > 0) {
         entityHuskAdapter.applyIndex6(countArray2[this.random.nextInt(countArray2.length)]);
      }

      this.effectRenderer.applyTertiaryEntityAdapter2(entityHuskAdapter);
   }

   @Override
   public int getCount4() {
      return 0;
   }

   private void applyIndexArray(double offset, int count, int[] countArray, int[] countArray2, boolean flag, boolean flag2) {
      double value = this.posX;
      double value2 = this.posY;
      double value3 = this.posZ;

      for (int index = -count; index <= count; index++) {
         for (int index2 = -count; index2 <= count; index2++) {
            for (int index3 = -count; index3 <= count; index3++) {
               double offset2 = (double)index2 + (this.random.nextDouble() - this.random.nextDouble()) * 0.5;
               double offset3 = (double)index + (this.random.nextDouble() - this.random.nextDouble()) * 0.5;
               double offset4 = (double)index3 + (this.random.nextDouble() - this.random.nextDouble()) * 0.5;
               double offset5 = (double)MathHelper.sqrt_double(offset2 * offset2 + offset3 * offset3 + offset4 * offset4) / offset + this.random.nextGaussian() * 0.05;
               this.applyIndexArray(value, value2, value3, offset2 / offset5, offset3 / offset5, offset4 / offset5, countArray, countArray2, flag, flag2);
               if (index != -count && index != count && index2 != -count && index2 != count) {
                  index3 += count * 2 - 1;
               }
            }
         }
      }
   }

   private void applyIndexArray(int[] countArray, int[] countArray2, boolean flag, boolean flag2) {
      double offset = this.random.nextGaussian() * 0.05;
      double offset2 = this.random.nextGaussian() * 0.05;

      for (int index = 0; index < 70; index++) {
         double offset3 = this.motionX * 0.5 + this.random.nextGaussian() * 0.15 + offset;
         double offset4 = this.motionZ * 0.5 + this.random.nextGaussian() * 0.15 + offset2;
         double offset5 = this.motionY * 0.5 + this.random.nextDouble() * 0.5;
         this.applyIndexArray(this.posX, this.posY, this.posZ, offset3, offset5, offset4, countArray, countArray2, flag, flag2);
      }
   }

   private void applyDoubleArray(double offset, double[][] offsetMatrix, int[] countArray, int[] countArray2, boolean flag, boolean flag2, boolean flag3) {
      double offset2 = offsetMatrix[0][0];
      double offset3 = offsetMatrix[0][1];
      this.applyIndexArray(this.posX, this.posY, this.posZ, offset2 * offset, offset3 * offset, 0.0, countArray, countArray2, flag, flag2);
      float factor = this.random.nextFloat() * (float) Math.PI;
      double offset4 = flag3 ? 0.034 : 0.34;

      for (int index = 0; index < 3; index++) {
         double offset5 = (double)factor + (double)((float)index * (float) Math.PI) * offset4;
         double offset6 = offset2;
         double offset7 = offset3;

         for (int index2 = 1; index2 < offsetMatrix.length; index2++) {
            double offset8 = offsetMatrix[index2][0];
            double offset9 = offsetMatrix[index2][1];

            for (double offset10 = 0.25; offset10 <= 1.0; offset10 += 0.25) {
               double offset11 = (offset6 + (offset8 - offset6) * offset10) * offset;
               double offset12 = (offset7 + (offset9 - offset7) * offset10) * offset;
               double offset13 = offset11 * Math.sin(offset5);
               double offset14 = offset11 * Math.cos(offset5);

               for (double offset15 = -1.0; offset15 <= 1.0; offset15 += 2.0) {
                  this.applyIndexArray(this.posX, this.posY, this.posZ, offset14 * offset15, offset12, offset13 * offset15, countArray, countArray2, flag, flag2);
               }
            }

            offset6 = offset8;
            offset7 = offset9;
         }
      }
   }

   @Override
   public void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer, Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
   }

   private boolean isActive16() {
      Minecraft mc = Minecraft.getMinecraft();
      return mc == null || mc.getRenderViewEntity() == null || mc.getRenderViewEntity().getDouble3(this.posX, this.posY, this.posZ) >= 256.0;
   }

   @Override
   public void updateState() {
      if (this.count15 == 0 && this.nBTTagList != null) {
         boolean active = this.isActive16();
         boolean flag = false;
         if (this.nBTTagList.getInt() >= 3) {
            flag = true;
         } else {
            for (int index = 0; index < this.nBTTagList.getInt(); index++) {
               if (this.nBTTagList.getNBTTagCompound(index).computeByteCode("Type") == 1) {
                  flag = true;
                  break;
               }
            }
         }

         String text = "fireworks." + (flag ? "largeBlast" : "blast") + (active ? "_far" : "");
         this.worldObj.applyText(this.posX, this.posY, this.posZ, text, 20.0F, 0.95F + this.random.nextFloat() * 0.1F, true);
      }

      if (this.count15 % 2 == 0 && this.nBTTagList != null && this.count15 / 2 < this.nBTTagList.getInt()) {
         int count = this.count15 / 2;
         NBTTagCompound nBTTagCompound = this.nBTTagList.getNBTTagCompound(count);
         byte byteValue = nBTTagCompound.computeByteCode("Type");
         boolean flag2 = nBTTagCompound.matchesText("Trail");
         boolean flag3 = nBTTagCompound.matchesText("Flicker");
         int[] countArray = nBTTagCompound.resolveIndexArray("Colors");
         int[] countArray2 = nBTTagCompound.resolveIndexArray("FadeColors");
         if (countArray.length == 0) {
            countArray = new int[]{ItemDye.countArray[0]};
         }

         if (byteValue == 1) {
            this.applyIndexArray(0.5, 4, countArray, countArray2, flag2, flag3);
         } else if (byteValue == 2) {
            this.applyDoubleArray(
               0.5,
               new double[][]{
                  {0.0, 1.0},
                  {0.3455, 0.309},
                  {0.9511, 0.309},
                  {0.3795918367346939, -0.12653061224489795},
                  {0.6122448979591837, -0.8040816326530612},
                  {0.0, -0.35918367346938773}
               },
               countArray,
               countArray2,
               flag2,
               flag3,
               false
            );
         } else if (byteValue == 3) {
            this.applyDoubleArray(
               0.5,
               new double[][]{
                  {0.0, 0.2},
                  {0.2, 0.2},
                  {0.2, 0.6},
                  {0.6, 0.6},
                  {0.6, 0.2},
                  {0.2, 0.2},
                  {0.2, 0.0},
                  {0.4, 0.0},
                  {0.4, -0.6},
                  {0.2, -0.6},
                  {0.2, -0.4},
                  {0.0, -0.4}
               },
               countArray,
               countArray2,
               flag2,
               flag3,
               true
            );
         } else if (byteValue == 4) {
            this.applyIndexArray(countArray, countArray2, flag2, flag3);
         } else {
            this.applyIndexArray(0.25, 2, countArray, countArray2, flag2, flag3);
         }

         int count2 = countArray[0];
         float factor = (float)((count2 & 0xFF0000) >> 16) / 255.0F;
         float factor2 = (float)((count2 & 0xFF00) >> 8) / 255.0F;
         float factor3 = (float)((count2 & 0xFF) >> 0) / 255.0F;
         HitBoxFactory hitBoxFactory = new HitBoxFactory(this.worldObj, this.posX, this.posY, this.posZ);
         hitBoxFactory.applyFloatByFloat(factor, factor2, factor3);
         this.effectRenderer.applyTertiaryEntityAdapter2(hitBoxFactory);
      }

      this.count15++;
      if (this.count15 > this.count14) {
         if (this.enabled13) {
            boolean active2 = this.isActive16();
            String text2 = "fireworks." + (active2 ? "twinkle_far" : "twinkle");
            this.worldObj.applyText(this.posX, this.posY, this.posZ, text2, 20.0F, 0.9F + this.random.nextFloat() * 0.15F, true);
         }

         this.updateState9();
      }
   }

   public EntityTadpoleAdapter(
      World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, EffectRenderer effectRenderer2, NBTTagCompound tagCompound
   ) {
      super(world, posX, posX2, posX3, 0.0, 0.0, 0.0);
      this.motionX = posX4;
      this.motionY = posY;
      this.motionZ = posZ;
      this.effectRenderer = effectRenderer2;
      this.count14 = 8;
      if (tagCompound != null) {
         this.nBTTagList = tagCompound.getExtendedLoggerEntry("Explosions", 10);
         if (this.nBTTagList.getInt() == 0) {
            this.nBTTagList = null;
         } else {
            this.count14 = this.nBTTagList.getInt() * 2 - 1;

            for (int index = 0; index < this.nBTTagList.getInt(); index++) {
               if (this.nBTTagList.getNBTTagCompound(index).matchesText("Flicker")) {
                  this.enabled13 = true;
                  this.count14 += 15;
                  break;
               }
            }
         }
      }
   }
}
