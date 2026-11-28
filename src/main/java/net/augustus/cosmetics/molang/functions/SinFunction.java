package net.augustus.cosmetics.molang.functions;


import net.augustus.cosmetics.molang.ast.MolangValue;
public class SinFunction extends MolangFunction {

   @Override
   public int getRequiredArguments() {
      return 1;
   }

   public SinFunction(MolangValue[] molangValues, String str) throws Exception {
       super(molangValues, str);
   }

   @Override
   public double get() {
      return Math.sin(this.getArg((byte)0));
   }
}
