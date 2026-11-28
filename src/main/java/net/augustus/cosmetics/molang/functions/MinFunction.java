package net.augustus.cosmetics.molang.functions;


import net.augustus.cosmetics.molang.ast.MolangValue;
public class MinFunction extends MolangFunction {

   @Override
   public double get() {
      return Math.min(this.getArg((byte)0), this.getArg((byte)1));
   }

   public MinFunction(MolangValue[] molangValues, String str) throws Exception {
       super(molangValues, str);
   }

   @Override
   public int getRequiredArguments() {
      return 2;
   }



}
