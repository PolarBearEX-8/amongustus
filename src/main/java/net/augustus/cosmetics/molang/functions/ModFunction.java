package net.augustus.cosmetics.molang.functions;


import net.augustus.cosmetics.molang.ast.MolangValue;
public class ModFunction extends MolangFunction {

   @Override
   public int getRequiredArguments() {
      return 2;
   }

   @Override
   public double get() {
      return this.getArg((byte)0) % this.getArg((byte)1);
   }

   public ModFunction(MolangValue[] molangValues, String str) throws Exception {
       super(molangValues, str);
   }
}
