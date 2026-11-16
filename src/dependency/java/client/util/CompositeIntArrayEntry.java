package client.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class CompositeIntArrayEntry extends PassiveFloatEntry {
   public static final int[][] countArray = new int[][]{{4, 3, 2}, {6, 4, 5}, {3, 3, 1}, {1, 2, 1}};
   public static final int[][] countArray2 = new int[][]{{0, 0}, {0, 5}, {0, 14}, {0, 18}};
   public static final int count3 = countArray.length;
   public final BoxesManager[] boxesManagerArray = new BoxesManager[count3];

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);

      for (int index = 0; index < this.boxesManagerArray.length; index++) {
         this.boxesManagerArray[index].applyFloat3(posZ);
      }
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      for (int index = 0; index < this.boxesManagerArray.length; index++) {
         this.boxesManagerArray[index].factor9 = MathHelper.cos(posX3 * 0.9F + (float)index * 0.15F * (float) Math.PI)
            * (float) Math.PI
            * 0.01F
            * (float)(1 + Math.abs(index - 2));
         this.boxesManagerArray[index].factor5 = MathHelper.abs(posX3 * 0.9F + (float)index * 0.15F * (float) Math.PI)
            * (float) Math.PI
            * 0.1F
            * (float)Math.abs(index - 2);
      }
   }

   public CompositeIntArrayEntry() {
      float factor = -3.5F;

      for (int index = 0; index < this.boxesManagerArray.length; index++) {
         this.boxesManagerArray[index] = new BoxesManager(this, countArray2[index][0], countArray2[index][1]);
         this.boxesManagerArray[index]
            .resolveBoxesManager(
               (float)countArray[index][0] * -0.5F, 0.0F, (float)countArray[index][2] * -0.5F, countArray[index][0], countArray[index][1], countArray[index][2]
            );
         this.boxesManagerArray[index].applyFloat(0.0F, (float)(24 - countArray[index][1]), factor);
         if (index < this.boxesManagerArray.length - 1) {
            factor += (float)(countArray[index][2] + countArray[index + 1][2]) * 0.5F;
         }
      }
   }
}
