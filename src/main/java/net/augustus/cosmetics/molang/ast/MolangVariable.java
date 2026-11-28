package net.augustus.cosmetics.molang.ast;

public class MolangVariable implements MolangValue {
   private String name;
   private double value;

   @Override
   public double get() {
      return this.value;
   }

   public void setValue(double d) {
      this.value = d;
   }

   public MolangVariable(String str, double d) {
      this.name = str;
      this.value = d;
   }

   @Override
   public String toString() {
      return this.name;
   }

   public String getName() {
      return this.name;
   }
}
