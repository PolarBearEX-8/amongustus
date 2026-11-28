package net.augustus.cosmetics.molang.functions;


import net.augustus.cosmetics.molang.ast.MolangValue;
public class TruncFunction extends MolangFunction {

   public double get() {
      double arg = this.getArg((byte)0);
      return arg < 0.0 ? Math.ceil(arg) : Math.floor(arg);
   }

   public TruncFunction(MolangValue[] molangValues, String str) throws Exception {
       super(molangValues, str);
   }

   @Override
   public int getRequiredArguments() {
      return 1;
   }



}
