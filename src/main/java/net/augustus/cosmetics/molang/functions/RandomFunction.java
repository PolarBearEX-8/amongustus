package net.augustus.cosmetics.molang.functions;

import net.augustus.cosmetics.molang.ast.MolangValue;
import java.util.Random;

public class RandomFunction extends MolangFunction {
   public Random random;

   public double get() {
      double d = 0.0;
      if (this.args.length >= 3) {
         this.random.setSeed((long)this.getArg((byte)2));
         d = this.random.nextDouble();
      } else {
         d = Math.random();
      }

      if (this.args.length >= 2) {
         double arg = this.getArg((byte)0);
         double arg2 = this.getArg((byte)1);
         double d2 = Math.min(arg, arg2);
         d = d * (Math.max(arg, arg2) - d2) + d2;
      } else if (this.args.length >= 1) {
         d *= this.getArg((byte)0);
      }

      return d;
   }

   public RandomFunction(MolangValue[] molangValues, String str) throws Exception {
       super(molangValues, str);
      this.random = new Random();
   }




}
