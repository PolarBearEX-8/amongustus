package net.augustus.cosmetics.molang.functions;


import net.augustus.cosmetics.molang.ast.MolangValue;
public class MaxFunction extends MolangFunction {

   public MaxFunction(MolangValue[] molangValues, String str) throws Exception {
       super(molangValues, str);
   }

   @Override
   public double get() {
      return Math.max(this.getArg((byte)0), this.getArg((byte)1));
   }

   @Override
   public int getRequiredArguments() {
      return 2;
   }
}
