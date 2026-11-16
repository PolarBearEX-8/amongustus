package client.util;

import client.render.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.EntityPigzombie;
import net.minecraft.util.ResourceLocation;

public class AuxiliaryResourceLocationEntryBridge extends CoreResourceLocationEntry {
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/entity/zombie_pigman.png");

   public ResourceLocation resolveResourceLocation(EntityPigzombie entityPigzombie) {
      return resourceLocation4;
   }

   @Override
   public ResourceLocation resolveResourceLocationByEntityMob(EntityMob entityMob) {
      return this.resolveResourceLocation((EntityPigzombie)entityMob);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityPigzombie)entity);
   }

   public AuxiliaryResourceLocationEntryBridge(RenderManager renderManager) {
      super(renderManager, new InternalFloatEntryBridge(), 0.5F, 1.0F);
      this.add(new TertiaryListenerBridge(this));
      this.add(new AuxiliaryClientManagerBridge(this, this));
   }
}
