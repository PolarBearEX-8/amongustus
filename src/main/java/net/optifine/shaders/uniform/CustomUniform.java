package net.optifine.shaders.uniform;

import net.optifine.shaders.SMCLog;
import net.optifine.expr.IExpression;

public class CustomUniform {
   private String name;
   private UniformType type;
   private IExpression expression;
   private ShaderUniformBase shaderUniform;

   public void reset() {
      this.shaderUniform.reset();
   }

   public void setProgram(int n) {
      this.shaderUniform.setProgram(n);
   }

   public void update() {
      if (this.shaderUniform.isDefined()) {
         try {
            this.type.updateUniform(this.expression, this.shaderUniform);
         } catch (RuntimeException e) {
            SMCLog.severe("Error updating custom uniform: " + this.shaderUniform.getName());
            SMCLog.severe(e.getClass().getName() + ": " + e.getMessage());
            this.shaderUniform.disable();
            SMCLog.severe("Custom uniform disabled: " + this.shaderUniform.getName());
         }
      }
   }

   public UniformType getType() {
      return this.type;
   }

   public ShaderUniformBase getShaderUniform() {
      return this.shaderUniform;
   }

   public String getName() {
      return this.name;
   }

   @Override
   public String toString() {
      return this.type.name().toLowerCase() + " " + this.name;
   }

   public CustomUniform(String str, UniformType uniformType, IExpression expression2) {
      this.name = str;
      this.type = uniformType;
      this.expression = expression2;
      this.shaderUniform = uniformType.makeShaderUniform(str);
   }

   public IExpression getExpression() {
      return this.expression;
   }
}
