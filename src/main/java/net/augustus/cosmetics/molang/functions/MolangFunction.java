package net.augustus.cosmetics.molang.functions;



import net.augustus.cosmetics.molang.ast.MolangValue;
public abstract class MolangFunction implements MolangValue {
   public MolangValue[] args;
   public String name;

   public String getName() {
      return this.name;
   }

   public MolangFunction(MolangValue[] molangValues, String str) throws Exception {
      if (molangValues.length < this.getRequiredArguments()) {
         String str2 = String.format("Function '%s' requires at least %s arguments. %s are given!", this.getName(), this.getRequiredArguments(), molangValues.length);
         throw new Exception(str2);
      } else {
         this.args = molangValues;
         this.name = str;
      }
   }

   public int getRequiredArguments() {
      return 0;
   }

   public double getArg(int n) {
      return n >= 0 && n < this.args.length ? this.args[n].get() : 0.0;
   }

   @Override
   public String toString() {
      String str = "";

      for (int i = 0; i < this.args.length; i++) {
         str = str + this.args[i].toString();
         if (i < this.args.length - 1) {
            str = str + ", ";
         }
      }

      return this.getName() + "(" + str + ")";
   }





}
