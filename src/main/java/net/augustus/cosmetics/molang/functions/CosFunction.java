package net.augustus.cosmetics.molang.functions;


import net.augustus.cosmetics.molang.ast.MolangValue;
public class CosFunction extends MolangFunction {

   @Override
   public int getRequiredArguments() {
      return 1;
   }

   public CosFunction(MolangValue[] molangValues, String str) throws Exception {
       super(molangValues, str);
   }

   @Override
   public double get() {
      return Math.cos(this.getArg((byte)0));
   }
}
