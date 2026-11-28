package net.augustus.cosmetics.molang.ast;

import net.augustus.cosmetics.molang.MolangParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class MolangValueExpression extends MolangExpression {
   public boolean returns;
   public MolangValue value;

   @Override
   public JsonElement toJson() {
      return (JsonElement)(this.value instanceof ConstantExpression ? new JsonPrimitive(this.value.get()) : super.toJson());
   }

   public double get() {
      return this.value.get();
   }

   public MolangExpression addReturn() {
      this.returns = true;
      return this;
   }

   public MolangValueExpression(MolangParser molangParser, MolangValue molangValue) {
      super(molangParser);
      this.value = molangValue;
   }

   @Override
   public String toString() {
      return (this.returns ? "return " : "") + this.value.toString();
   }

}
