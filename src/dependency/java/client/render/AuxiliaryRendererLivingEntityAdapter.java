package client.render;

import client.util.BoxesManagerEntry;
import client.util.ExtendedClientManagerAdapter;
import client.util.ListenerAdapter5;
import client.util.PassiveFloatEntry;
import client.util.TertiaryListenerBridge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArmorstand;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class AuxiliaryRendererLivingEntityAdapter extends RendererLivingEntity {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/armorstand/wood.png");

   public BoxesManagerEntry getBoxesManagerEntry() {
      return (BoxesManagerEntry)super.getPassiveFloatEntry();
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return this.getBoxesManagerEntry();
   }

   @Override
   public boolean matchesEntityByEntity(Entity entity) {
      return this.matchesEntityArmorstand((EntityArmorstand)entity);
   }

   public ResourceLocation resolveResourceLocation(EntityArmorstand entityArmorstand) {
      return resourceLocation3;
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
      this.applyEntityArmorstand((EntityArmorstand)livingEntity, posX, posY, posZ);
   }

   @Override
   public boolean matchesEntityLivingBase(EntityLivingBase livingEntity) {
      return this.matchesEntityArmorstand((EntityArmorstand)livingEntity);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityArmorstand)entity);
   }

   public void applyEntityArmorstand(EntityArmorstand entityArmorstand, float posX, float posY, float posZ) {
      GlStateManager.rotate(180.0F - posY, 0.0F, 1.0F, 0.0F);
   }

   public boolean matchesEntityArmorstand(EntityArmorstand entityArmorstand) {
      return entityArmorstand.isActive6();
   }

   public AuxiliaryRendererLivingEntityAdapter(RenderManager renderManager) {
      super(renderManager, new BoxesManagerEntry(), 0.0F);
      ExtendedClientManagerAdapter extendedClientManagerAdapter = new ExtendedClientManagerAdapter(this, this);
      this.add(extendedClientManagerAdapter);
      this.add(new TertiaryListenerBridge(this));
      this.add(new ListenerAdapter5(this.getBoxesManagerEntry().boxesManager3));
   }
}
