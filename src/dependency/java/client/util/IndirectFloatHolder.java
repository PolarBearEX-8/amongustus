package client.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

public class IndirectFloatHolder {
   public float factor;
   public float factor2;
   public final StaticFloatEntry staticFloatEntry = new StaticFloatEntry(0.0F, 0.0F, 0.0F);
   public final StaticFloatEntry staticFloatEntry2;

   public IndirectFloatHolder resolveIndirectFloatHolder(double[] offsetArray) {
      Validate.validIndex(ArrayUtils.toObject(offsetArray), 1);
      return new IndirectFloatHolder(this.staticFloatEntry2, (float)offsetArray[0], (float)offsetArray[1]);
   }

   public IndirectFloatHolder resolveIndirectFloatHolder(float factor3, float factor4) {
      return new IndirectFloatHolder(this.staticFloatEntry2, factor3, factor4);
   }

   public IndirectFloatHolder(double posX, double posY, double posZ) {
      this.staticFloatEntry2 = new StaticFloatEntry((float)posX, (float)posY, (float)posZ);
   }

   public IndirectFloatHolder(float posX, float posY, float posZ) {
      this.staticFloatEntry2 = new StaticFloatEntry(posX, posY, posZ);
   }

   public IndirectFloatHolder(StaticFloatEntry staticFloatEntry3, float factor3, float factor4) {
      this.staticFloatEntry2 = staticFloatEntry3;
      this.factor2 = factor3;
      this.factor = factor4;
   }
}
