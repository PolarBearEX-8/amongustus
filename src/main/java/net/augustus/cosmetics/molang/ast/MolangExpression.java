package net.augustus.cosmetics.molang.ast;

import net.augustus.cosmetics.molang.MolangOperation;
import net.augustus.cosmetics.molang.MolangParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public abstract class MolangExpression implements MolangValue {
   public MolangParser context;

   public static boolean isConstant(MolangExpression molangExpression) {
      if (molangExpression instanceof MolangValueExpression) {
         return ((MolangValueExpression)molangExpression).value instanceof ConstantExpression;
      } else {
         return false;
      }
   }

   public MolangExpression(MolangParser molangParser) {
      this.context = molangParser;
   }

   public static boolean isOne(MolangExpression molangExpression) {
      return isExpressionConstant(molangExpression, 1.0);
   }

   public static boolean isExpressionConstant(MolangExpression molangExpression, double d) {
      if (!(molangExpression instanceof MolangValueExpression)) {
         return false;
      } else {
         MolangValueExpression molangValueExpression = (MolangValueExpression)molangExpression;
         return molangValueExpression.value instanceof ConstantExpression && MolangOperation.equals(molangValueExpression.value.get(), d);
      }
   }

   public JsonElement toJson() {
      return new JsonPrimitive(this.toString());
   }

   public static boolean isZero(MolangExpression molangExpression) {
      return isExpressionConstant(molangExpression, 0.0);
   }



}
