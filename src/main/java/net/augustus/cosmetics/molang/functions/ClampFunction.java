package net.augustus.cosmetics.molang.functions;


import net.augustus.cosmetics.molang.MolangMathUtil;
import net.augustus.cosmetics.molang.ast.MolangValue;
public class ClampFunction extends MolangFunction {

   public ClampFunction(MolangValue[] molangValues, String str) throws Exception {
       super(molangValues, str);
   }

   public double get() {
      return MolangMathUtil.clamp(this.getArg((byte)0), this.getArg((byte)1), this.getArg((byte)2));
   }

   @Override
   public int getRequiredArguments() {
      return 3;
   }
}
