package client.util;

import client.render.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class HitBoxFactory extends TertiaryEntityAdapter {
   @Override
   public void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer, Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      float factor = 7.1F * MathHelper.abs(((float)this.count13 + posX - 1.0F) * 0.25F * (float) Math.PI);
      this.factor9 = 0.6F - ((float)this.count13 + posX - 1.0F) * 0.25F * 0.5F;
      float factor2 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)posX - value8);
      float factor3 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)posX - value6);
      float factor4 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)posX - value7);
      int count = this.computeCount(posX);
      int count2 = count >> 16 & 65535;
      int count3 = count & 65535;
      worldRenderer.getWorldRenderer((double)(factor2 - posX2 * factor - posY * factor), (double)(factor3 - posX3 * factor), (double)(factor4 - posX4 * factor - posZ * factor))
         .getWorldRenderer6(0.5, 0.375)
         .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, this.factor9)
         .resolveWorldRenderer(count2, count3)
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor2 - posX2 * factor + posY * factor), (double)(factor3 + posX3 * factor), (double)(factor4 - posX4 * factor + posZ * factor))
         .getWorldRenderer6(0.5, 0.125)
         .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, this.factor9)
         .resolveWorldRenderer(count2, count3)
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor2 + posX2 * factor + posY * factor), (double)(factor3 + posX3 * factor), (double)(factor4 + posX4 * factor + posZ * factor))
         .getWorldRenderer6(0.25, 0.125)
         .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, this.factor9)
         .resolveWorldRenderer(count2, count3)
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor2 + posX2 * factor - posY * factor), (double)(factor3 - posX3 * factor), (double)(factor4 + posX4 * factor - posZ * factor))
         .getWorldRenderer6(0.25, 0.375)
         .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, this.factor9)
         .resolveWorldRenderer(count2, count3)
         .updateState();
   }

   public HitBoxFactory(World world, double posX, double posY, double posZ) {
      super(world, posX, posY, posZ);
      this.count14 = 4;
   }
}
