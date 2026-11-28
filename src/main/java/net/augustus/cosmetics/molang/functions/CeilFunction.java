package net.augustus.cosmetics.molang.functions;


import net.augustus.cosmetics.molang.ast.MolangValue;
public class CeilFunction extends MolangFunction {

   @Override
   public int getRequiredArguments() {
      return 1;
   }

   public CeilFunction(MolangValue[] molangValues, String str) throws Exception {
       super(molangValues, str);
   }

   @Override
   public double get() {
      return Math.ceil(this.getArg((byte)0));
   }
}
