package client.util;

import client.event.PreUpdateObserver;
import client.render.ShadersManager;
import net.minecraft.client.shader.ShaderUniform;

public class VectorQueue {
   public String labelText;
   public SmoothService smoothService;
   public PreUpdateObserver preUpdateObserver;
   public ShaderUniform shaderUniform;

   public void applyIndex(int count) {
      this.shaderUniform.applyIndexByIndex(count);
   }

   public void updateState() {
      if (this.shaderUniform.isActive()) {
         try {
            this.smoothService.applyPreUpdateObserver(this.preUpdateObserver, this.shaderUniform);
         } catch (RuntimeException exception) {
            ShadersManager.applyTextByText("Error updating custom uniform: " + this.shaderUniform.getLabelText());
            ShadersManager.applyTextByText(exception.getClass().getName() + ": " + exception.getMessage());
            this.shaderUniform.updateState3();
            ShadersManager.applyTextByText("Custom uniform disabled: " + this.shaderUniform.getLabelText());
         }
      }
   }

   public String getLabelText() {
      return this.labelText;
   }

   public PreUpdateObserver getPreUpdateObserver() {
      return this.preUpdateObserver;
   }

   public SmoothService getSmoothService() {
      return this.smoothService;
   }

   public VectorQueue(String text, SmoothService smoothService2, PreUpdateObserver preUpdateObserver2) {
      this.labelText = text;
      this.smoothService = smoothService2;
      this.preUpdateObserver = preUpdateObserver2;
      this.shaderUniform = smoothService2.resolveShaderUniform(text);
   }

   public void updateState2() {
      this.shaderUniform.updateState5();
   }

   public ShaderUniform getShaderUniform() {
      return this.shaderUniform;
   }

   @Override
   public String toString() {
      return this.smoothService.name().toLowerCase() + " " + this.labelText;
   }
}
