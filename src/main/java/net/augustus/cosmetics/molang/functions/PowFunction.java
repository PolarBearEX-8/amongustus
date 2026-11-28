package net.augustus.cosmetics.molang.functions;


import net.augustus.cosmetics.molang.ast.MolangValue;
public class PowFunction extends MolangFunction {

   @Override
   public int getRequiredArguments() {
      return 2;
   }

   @Override
   public double get() {
      return Math.pow(this.getArg((byte)0), this.getArg((byte)1));
   }

   public PowFunction(MolangValue[] molangValues, String str) throws Exception {
       super(molangValues, str);
   }
}
