package net.augustus.cosmetics.molang.functions;


import net.augustus.cosmetics.molang.ast.MolangValue;
public class SqrtFunction extends MolangFunction {

   @Override
   public double get() {
      return Math.sqrt(this.getArg((byte)0));
   }

   @Override
   public int getRequiredArguments() {
      return 1;
   }

   public SqrtFunction(MolangValue[] molangValues, String str) throws Exception {
       super(molangValues, str);
   }
}
