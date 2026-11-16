package client.util;

import client.event.PassiveListener;
import net.minecraft.client.renderer.GlStateManager;

public class AuxiliaryListenerAdapter2 implements PassiveListener {
   public static AuxiliaryListenerAdapter2 auxiliaryListenerAdapter2 = new AuxiliaryListenerAdapter2();

   @Override
   public void applyFloat(float posX, float posY, float posZ) {
      GlStateManager.rotate(posZ, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(posY, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(posX, 1.0F, 0.0F, 0.0F);
   }

   @Override
   public void applyFloat(float factor) {
      GlStateManager.rotate(factor, 0.0F, 0.0F, 1.0F);
   }

   @Override
   public void updateState() {
      GlStateManager.popMatrix();
   }

   @Override
   public void applyFloat2(float posX, float posY, float posZ) {
      GlStateManager.translate3f3(posX, posY, posZ);
   }

   @Override
   public void applyFloat2(float factor) {
      GlStateManager.rotate(factor, 0.0F, 1.0F, 0.0F);
   }

   public static AuxiliaryListenerAdapter2 getAuxiliaryListenerAdapter2() {
      return auxiliaryListenerAdapter2;
   }

   @Override
   public void applyFloatByFloat(float factor) {
      GlStateManager.rotate(factor, 1.0F, 0.0F, 0.0F);
   }

   @Override
   public void updateState2() {
      GlStateManager.pushMatrix();
   }

   @Override
   public void applyFloatByFloat(float posX, float posY, float posZ) {
      GlStateManager.translate(posX, posY, posZ);
   }

   private AuxiliaryListenerAdapter2() {
   }
}
