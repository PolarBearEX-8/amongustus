package net.augustus.cosmetics.molang.functions;


import net.augustus.cosmetics.molang.ast.MolangValue;
public class CosDegreesFunction extends MolangFunction {

   @Override
   public int getRequiredArguments() {
      return 1;
   }

   @Override
   public double get() {
      return Math.cos(this.getArg((byte)0) / 180.0 * Math.PI);
   }

   public CosDegreesFunction(MolangValue[] molangValues, String str) throws Exception {
       super(molangValues, str);
   }
}
