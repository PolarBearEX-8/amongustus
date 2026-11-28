package net.augustus.cosmetics.molang.functions;


import net.augustus.cosmetics.molang.ast.MolangValue;
public class AbsFunction extends MolangFunction {

   @Override
   public double get() {
      return Math.abs(this.getArg((byte)0));
   }

   public AbsFunction(MolangValue[] molangValues, String str) throws Exception {
       super(molangValues, str);
   }

   @Override
   public int getRequiredArguments() {
      return 1;
   }
}
