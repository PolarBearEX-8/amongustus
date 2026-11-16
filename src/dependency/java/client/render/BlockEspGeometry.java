package client.render;

import client.util.AimBuilder;
import client.util.PrimaryCustom;
import client.util.RandomUtil;
import java.nio.FloatBuffer;

public class BlockEspGeometry {
   public float factor3;
   public float factor10;
   public boolean enabled;
   public FloatBuffer floatBuffer;
   public float factor4;
   public int count;
   public float factor9;
   public float factor2;
   public float factor6;
   public ScoreboardRow scoreboardRow;
   public float factor8;
   public float factor5;
   public float factor;
   public float factor7 = 0.0F;

   public BlockEspGeometry resolveBlockEspGeometryByFloat(float posX, float posY, float posZ) {
      if (posX != this.factor2 || posY != this.factor6 || posZ != this.factor) {
         this.factor2 = posX;
         this.factor6 = posY;
         this.factor = posZ;
         this.enabled = true;
      }

      return this;
   }

   private void applyFloatBuffer(FloatBuffer buffer, float posX, float posY, float posZ) {
      buffer.put(posX).put(posY).put(posZ).put(this.factor3).put(this.factor4).put(this.factor5).put(this.factor8);
   }

   public ScoreboardRow getScoreboardRow() {
      return this.scoreboardRow;
   }

   public BlockEspGeometry resolveBlockEspGeometry(float posX, float posY, float posZ) {
      if (posX != this.factor7 || posY != this.factor9 || posZ != this.factor10) {
         this.factor7 = posX;
         this.factor9 = posY;
         this.factor10 = posZ;
         this.enabled = true;
      }

      return this;
   }

   public void setCount(int count2) {
      this.count = count2;
   }

   public FloatBuffer getFloatBuffer() {
      return this.floatBuffer;
   }

   private void applyFloatBuffer(FloatBuffer buffer, float posX, float posX2, float posX3, float posX4, float posY, float posZ, AimBuilder aimBuilder) {
      switch (PrimaryCustom.countArray[aimBuilder.ordinal()]) {
         case 1:
            this.applyFloatBuffer(buffer, posX, posX3, posY, posX2, posX3, posY, posX2, posX4, posY, posX, posX4, posY);
            break;
         case 2:
            this.applyFloatBuffer(buffer, posX, posX3, posY, posX, posX3, posZ, posX, posX4, posZ, posX, posX4, posY);
            break;
         case 3:
            this.applyFloatBuffer(buffer, posX, posX3, posY, posX2, posX3, posY, posX2, posX3, posZ, posX, posX3, posZ);
      }
   }

   public BlockEspGeometry resolveBlockEspGeometry(float posX, float posX2, float posY, float posZ) {
      if (this.factor3 != posX || this.factor4 != posX2 || this.factor5 != posY || this.factor8 != posZ) {
         this.factor3 = posX;
         this.factor4 = posX2;
         this.factor5 = posY;
         this.factor8 = posZ;
         this.enabled = true;
      }

      return this;
   }

   public BlockEspGeometry getExtendedFloatEntry(int count2) {
      float factor11 = RandomUtil.processAction5(count2);
      float factor12 = RandomUtil.processAction4(count2);
      float factor13 = RandomUtil.processAction7(count2);
      float factor14 = RandomUtil.processAction2(count2);
      return this.resolveBlockEspGeometry(factor11, factor12, factor13, factor14);
   }

   public void applyFloatBuffer(FloatBuffer buffer) {
      float factor11 = this.factor7 + this.factor2;
      float factor12 = this.factor9 + this.factor6;
      float factor13 = this.factor10 + this.factor;
      float factor14 = this.factor10 + this.factor;
      AimBuilder aimBuilder = AimBuilder.Z;
      float factor15 = factor14;
      float factor16 = factor13;
      float factor17 = factor12;
      float factor18 = this.factor9;
      float factor19 = factor11;
      float factor20 = this.factor7;
      this.applyFloatBuffer(buffer, factor20, factor19, factor18, factor17, factor16, factor15, aimBuilder);
      factor11 = this.factor7 + this.factor2;
      factor12 = this.factor9 + this.factor6;
      AimBuilder aimBuilder2 = AimBuilder.Z;
      float factor21 = this.factor10;
      float factor22 = this.factor10;
      float factor23 = factor12;
      float factor24 = this.factor9;
      float factor25 = factor11;
      float factor26 = this.factor7;
      this.applyFloatBuffer(buffer, factor26, factor25, factor24, factor23, factor22, factor21, aimBuilder2);
      factor12 = this.factor9 + this.factor6;
      factor14 = this.factor10 + this.factor;
      AimBuilder aimBuilder3 = AimBuilder.X;
      float factor27 = factor14;
      float factor28 = this.factor10;
      float factor29 = factor12;
      float factor30 = this.factor9;
      float factor31 = this.factor7;
      float factor32 = this.factor7;
      this.applyFloatBuffer(buffer, factor32, factor31, factor30, factor29, factor28, factor27, aimBuilder3);
      float factor33 = this.factor7 + this.factor2;
      factor11 = this.factor7 + this.factor2;
      factor12 = this.factor9 + this.factor6;
      factor14 = this.factor10 + this.factor;
      AimBuilder aimBuilder4 = AimBuilder.X;
      float factor34 = factor14;
      float factor35 = this.factor10;
      float factor36 = factor12;
      float factor37 = this.factor9;
      float factor38 = factor11;
      float factor39 = factor33;
      this.applyFloatBuffer(buffer, factor39, factor38, factor37, factor36, factor35, factor34, aimBuilder4);
      factor11 = this.factor7 + this.factor2;
      float factor40 = this.factor9 + this.factor6;
      factor12 = this.factor9 + this.factor6;
      factor14 = this.factor10 + this.factor;
      AimBuilder aimBuilder5 = AimBuilder.Y;
      float factor41 = factor14;
      float factor42 = this.factor10;
      float factor43 = factor12;
      float factor44 = factor40;
      float factor45 = factor11;
      float factor46 = this.factor7;
      this.applyFloatBuffer(buffer, factor46, factor45, factor44, factor43, factor42, factor41, aimBuilder5);
      factor11 = this.factor7 + this.factor2;
      factor14 = this.factor10 + this.factor;
      AimBuilder aimBuilder6 = AimBuilder.Y;
      float factor47 = factor14;
      float factor48 = this.factor10;
      float factor49 = this.factor9;
      float factor50 = this.factor9;
      float factor51 = factor11;
      float factor52 = this.factor7;
      this.applyFloatBuffer(buffer, factor52, factor51, factor50, factor49, factor48, factor47, aimBuilder6);
   }

   public void applyScoreboardRow(ScoreboardRow scoreboardRow2) {
      this.scoreboardRow = scoreboardRow2;
      this.floatBuffer = scoreboardRow2.resolveFloatBuffer(1);
      this.enabled = false;
   }

   public void updateState() {
      if (this.enabled && this.scoreboardRow != null) {
         this.scoreboardRow.applyBlockEspGeometry(this);
         this.enabled = false;
      }
   }

   private void applyFloatBuffer(
      FloatBuffer buffer,
      float posX,
      float posX2,
      float posX3,
      float posX4,
      float posX5,
      float posX6,
      float posX7,
      float posX8,
      float posX9,
      float posX10,
      float posY,
      float posZ
   ) {
      this.applyFloatBuffer(buffer, posX, posX2, posX3);
      this.applyFloatBuffer(buffer, posX4, posX5, posX6);
      this.applyFloatBuffer(buffer, posX7, posX8, posX9);
      this.applyFloatBuffer(buffer, posX, posX2, posX3);
      this.applyFloatBuffer(buffer, posX7, posX8, posX9);
      this.applyFloatBuffer(buffer, posX10, posY, posZ);
   }

   public int getCount() {
      return this.count;
   }

   public BlockEspGeometry(float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      this.factor3 = 1.0F;
      this.factor4 = 0.0F;
      this.factor5 = 0.0F;
      this.factor8 = 0.7F;
      this.enabled = false;
      this.floatBuffer = null;
      this.factor7 = posX;
      this.factor9 = posX2;
      this.factor10 = posX3;
      this.factor2 = posX4;
      this.factor6 = posY;
      this.factor = posZ;
   }

   public BlockEspGeometry(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      this.factor3 = 1.0F;
      this.factor4 = 0.0F;
      this.factor5 = 0.0F;
      this.factor8 = 0.7F;
      this.enabled = false;
      this.floatBuffer = null;
      this.factor7 = (float)posX;
      this.factor9 = (float)posX2;
      this.factor10 = (float)posX3;
      this.factor2 = (float)posX4;
      this.factor6 = (float)posY;
      this.factor = (float)posZ;
   }
}
