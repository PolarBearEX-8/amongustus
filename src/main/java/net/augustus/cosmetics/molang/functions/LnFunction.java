package net.augustus.cosmetics.molang.functions;


import net.augustus.cosmetics.molang.ast.MolangValue;
public class LnFunction extends MolangFunction {

   public LnFunction(MolangValue[] molangValues, String str) throws Exception {
       super(molangValues, str);
   }

   @Override
   public int getRequiredArguments() {
      return 1;
   }

   @Override
   public double get() {
      return Math.log(this.getArg((byte)0));
   }
}
