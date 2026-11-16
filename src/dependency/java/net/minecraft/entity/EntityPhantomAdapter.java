package net.minecraft.entity;

import client.render.RenderManager;
import client.render.WorldRenderer;
import client.util.BuildManager;
import client.util.NoneManager;
import client.util.PreEndHandManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.world.World;

public class EntityPhantomAdapter extends TertiaryEntityAdapter {
   public Entity entity3;
   public Entity entity2;
   public int count16;
   public int count15;
   public float factor13;
   public RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();

   @Override
   public int getCount4() {
      return 3;
   }

   @Override
   public void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer, Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      NoneManager noneManager = null;
      if (BuildManager.canExecute()) {
         noneManager = PreEndHandManager.noneManager7;
         PreEndHandManager.applyEntity7(this.entity3);
      }

      float factor = ((float)this.count16 + posX) / (float)this.count15;
      float factor2 = factor * factor;
      double posX5 = this.entity3.posX;
      double posY2 = this.entity3.posY;
      double posZ2 = this.entity3.posZ;
      double offset = this.entity2.lastTickPosX + (this.entity2.posX - this.entity2.lastTickPosX) * (double)posX;
      double offset2 = this.entity2.lastTickPosY + (this.entity2.posY - this.entity2.lastTickPosY) * (double)posX + (double)this.factor13;
      double offset3 = this.entity2.lastTickPosZ + (this.entity2.posZ - this.entity2.lastTickPosZ) * (double)posX;
      double offset4 = posX5 + (offset - posX5) * (double)factor2;
      double offset5 = posY2 + (offset2 - posY2) * (double)factor2;
      double offset6 = posZ2 + (offset3 - posZ2) * (double)factor2;
      int count = this.computeCount(posX);
      int count2 = count % 65536;
      int count3 = count / 65536;
      OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, (float)count2 / 1.0F, (float)count3 / 1.0F);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      double offset7 = offset4 - value8;
      double offset8 = offset5 - value6;
      double offset9 = offset6 - value7;
      this.renderManager.renderEntity(this.entity3, (double)((float)offset7), (double)((float)offset8), (double)((float)offset9), this.entity3.rotationYaw, posX);
      if (BuildManager.canExecute()) {
         PreEndHandManager.applyEntity5(null);
         PreEndHandManager.applyNoneManager2(noneManager);
      }
   }

   @Override
   public void updateState() {
      this.count16++;
      if (this.count16 == this.count15) {
         this.updateState9();
      }
   }

   public EntityPhantomAdapter(World world, Entity entity, Entity entity4, float factor) {
      super(world, entity.posX, entity.posY, entity.posZ, entity.motionX, entity.motionY, entity.motionZ);
      this.entity3 = entity;
      this.entity2 = entity4;
      this.count15 = 3;
      this.factor13 = factor;
   }
}
