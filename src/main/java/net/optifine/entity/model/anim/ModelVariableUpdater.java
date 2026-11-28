package net.optifine.entity.model.anim;

import net.optifine.config.Config;
import net.optifine.expr.ExpressionParser;
import net.optifine.expr.IExpressionFloat;
import net.optifine.expr.ParseException;

public class ModelVariableUpdater {
   private String modelVariableName;
   private String expressionText;
   private ModelVariableFloat modelVariable;
   private IExpressionFloat expression;

   public boolean initialize(IModelResolver modelResolver) {
      this.modelVariable = modelResolver.getModelVariable(this.modelVariableName);
      if (this.modelVariable == null) {
         Config.warn("Model variable not found: " + this.modelVariableName);
         return false;
      } else {
         try {
            this.expression = new ExpressionParser(modelResolver).parseFloat(this.expressionText);
            return true;
         } catch (ParseException e) {
            Config.warn("Error parsing expression: " + this.expressionText);
            Config.warn(e.getClass().getName() + ": " + e.getMessage());
            return false;
         }
      }
   }

   public void update() {
      float f = this.expression.eval();
      this.modelVariable.setValue(f);
   }

   public ModelVariableUpdater(String str, String str2) {
      this.modelVariableName = str;
      this.expressionText = str2;
   }
}
