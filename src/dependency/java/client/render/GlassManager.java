package client.render;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class GlassManager extends PassiveFloatEntry {
   public BoxesManager boxesManager;
   public BoxesManager boxesManager3 = new BoxesManager(this, "glass");
   public BoxesManager boxesManager2;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      GlStateManager.pushMatrix();
      GlStateManager.translate3f3(2.0F, 2.0F, 2.0F);
      GlStateManager.translate(0.0F, -0.5F, 0.0F);
      if (this.boxesManager2 != null) {
         this.boxesManager2.applyFloat3(posZ);
      }

      GlStateManager.rotate(posX2, 0.0F, 1.0F, 0.0F);
      GlStateManager.translate(0.0F, 0.8F + posX3, 0.0F);
      GlStateManager.rotate(60.0F, 0.7071F, 0.0F, 0.7071F);
      this.boxesManager3.applyFloat3(posZ);
      float factor = 0.875F;
      GlStateManager.translate3f3(factor, factor, factor);
      GlStateManager.rotate(60.0F, 0.7071F, 0.0F, 0.7071F);
      GlStateManager.rotate(posX2, 0.0F, 1.0F, 0.0F);
      this.boxesManager3.applyFloat3(posZ);
      GlStateManager.translate3f3(factor, factor, factor);
      GlStateManager.rotate(60.0F, 0.7071F, 0.0F, 0.7071F);
      GlStateManager.rotate(posX2, 0.0F, 1.0F, 0.0F);
      this.boxesManager.applyFloat3(posZ);
      GlStateManager.popMatrix();
   }

   public GlassManager(float factor, boolean flag) {
      this.boxesManager3.resolveBoxesManagerByIndex(0, 0).resolveBoxesManager(-4.0F, -4.0F, -4.0F, 8, 8, 8);
      this.boxesManager = new BoxesManager(this, "cube");
      this.boxesManager.resolveBoxesManagerByIndex(32, 0).resolveBoxesManager(-4.0F, -4.0F, -4.0F, 8, 8, 8);
      if (flag) {
         this.boxesManager2 = new BoxesManager(this, "base");
         this.boxesManager2.resolveBoxesManagerByIndex(0, 16).resolveBoxesManager(-6.0F, 0.0F, -6.0F, 12, 4, 12);
      }
   }
}
