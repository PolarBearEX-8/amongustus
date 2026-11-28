package net.optifine.entity.model.anim;

import net.minecraft.client.model.ModelRenderer;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpressionFloat;

public class ModelVariableFloat implements IExpressionFloat {
   private String name;
   private ModelRenderer modelRenderer;
   private ModelVariableType enumModelVariable;

   @Override
   public ExpressionType getFloatValue() {
      return ExpressionType.FLOAT;
   }

   public float getValue() {
      return this.enumModelVariable.getFloat(this.modelRenderer);
   }

   public void setValue(float f) {
      this.enumModelVariable.setFloat(this.modelRenderer, f);
   }

   @Override
   public float eval() {
      return this.getValue();
   }

   public ModelVariableFloat(String str, ModelRenderer modelRenderer2, ModelVariableType modelVariableType) {
      this.name = str;
      this.modelRenderer = modelRenderer2;
      this.enumModelVariable = modelVariableType;
   }

   @Override
   public String toString() {
      return this.name;
   }
}
