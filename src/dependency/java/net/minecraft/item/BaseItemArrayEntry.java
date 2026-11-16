package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class BaseItemArrayEntry extends PassiveFloatEntry {
   public BoxesManager[] boxesManagerArray = new BoxesManager[7];
   public BoxesManager[] boxesManagerArray2;
   public float[] factorArray = new float[7];
   public static final int[][] countArray2 = new int[][]{{3, 2, 2}, {4, 3, 2}, {6, 4, 3}, {3, 3, 3}, {2, 2, 3}, {2, 1, 2}, {1, 1, 2}};
   public static final int[][] countArray = new int[][]{{0, 0}, {0, 4}, {0, 9}, {0, 16}, {0, 22}, {11, 0}, {13, 4}};

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);

      for (int index = 0; index < this.boxesManagerArray.length; index++) {
         this.boxesManagerArray[index].applyFloat3(posZ);
      }

      for (int index2 = 0; index2 < this.boxesManagerArray2.length; index2++) {
         this.boxesManagerArray2[index2].applyFloat3(posZ);
      }
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      for (int index = 0; index < this.boxesManagerArray.length; index++) {
         this.boxesManagerArray[index].factor9 = MathHelper.cos(posX3 * 0.9F + (float)index * 0.15F * (float) Math.PI)
            * (float) Math.PI
            * 0.05F
            * (float)(1 + Math.abs(index - 2));
         this.boxesManagerArray[index].factor5 = MathHelper.abs(posX3 * 0.9F + (float)index * 0.15F * (float) Math.PI)
            * (float) Math.PI
            * 0.2F
            * (float)Math.abs(index - 2);
      }

      this.boxesManagerArray2[0].factor9 = this.boxesManagerArray[2].factor9;
      this.boxesManagerArray2[1].factor9 = this.boxesManagerArray[4].factor9;
      this.boxesManagerArray2[1].factor5 = this.boxesManagerArray[4].factor5;
      this.boxesManagerArray2[2].factor9 = this.boxesManagerArray[1].factor9;
      this.boxesManagerArray2[2].factor5 = this.boxesManagerArray[1].factor5;
   }

   public BaseItemArrayEntry() {
      float factor = -3.5F;

      for (int index = 0; index < this.boxesManagerArray.length; index++) {
         this.boxesManagerArray[index] = new BoxesManager(this, countArray[index][0], countArray[index][1]);
         this.boxesManagerArray[index]
            .resolveBoxesManager(
               (float)countArray2[index][0] * -0.5F, 0.0F, (float)countArray2[index][2] * -0.5F, countArray2[index][0], countArray2[index][1], countArray2[index][2]
            );
         this.boxesManagerArray[index].applyFloat(0.0F, (float)(24 - countArray2[index][1]), factor);
         this.factorArray[index] = factor;
         if (index < this.boxesManagerArray.length - 1) {
            factor += (float)(countArray2[index][2] + countArray2[index + 1][2]) * 0.5F;
         }
      }

      this.boxesManagerArray2 = new BoxesManager[3];
      this.boxesManagerArray2[0] = new BoxesManager(this, 20, 0);
      this.boxesManagerArray2[0].resolveBoxesManager(-5.0F, 0.0F, (float)countArray2[2][2] * -0.5F, 10, 8, countArray2[2][2]);
      this.boxesManagerArray2[0].applyFloat(0.0F, 16.0F, this.factorArray[2]);
      this.boxesManagerArray2[1] = new BoxesManager(this, 20, 11);
      this.boxesManagerArray2[1].resolveBoxesManager(-3.0F, 0.0F, (float)countArray2[4][2] * -0.5F, 6, 4, countArray2[4][2]);
      this.boxesManagerArray2[1].applyFloat(0.0F, 20.0F, this.factorArray[4]);
      this.boxesManagerArray2[2] = new BoxesManager(this, 20, 18);
      this.boxesManagerArray2[2].resolveBoxesManager(-3.0F, 0.0F, (float)countArray2[4][2] * -0.5F, 6, 5, countArray2[1][2]);
      this.boxesManagerArray2[2].applyFloat(0.0F, 19.0F, this.factorArray[1]);
   }
}
