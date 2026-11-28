package net.augustus.cosmetics.molang.functions;


import net.augustus.cosmetics.molang.MolangInterpolations;
import net.augustus.cosmetics.molang.ast.MolangValue;
public class LerpRotateFunction extends MolangFunction {

   public LerpRotateFunction(MolangValue[] molangValues, String str) throws Exception {
       super(molangValues, str);
   }

   @Override
   public int getRequiredArguments() {
      return 3;
   }

   public double get() {
      return MolangInterpolations.lerpYaw(this.getArg((byte)0), this.getArg((byte)1), this.getArg((byte)2));
   }
}
