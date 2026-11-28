package net.optifine.entity.model.anim;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpressionBool;

public enum RenderEntityParameterBool implements IExpressionBool {
   IS_ALIVE("is_alive"),
   IS_BURNING("is_burning"),
   IS_CHILD("is_child"),
   IS_GLOWING("is_glowing"),
   IS_HURT("is_hurt"),
   IS_IN_LAVA("is_in_lava"),
   IS_IN_WATER("is_in_water"),
   IS_INVISIBLE("is_invisible"),
   IS_ON_GROUND("is_on_ground"),
   IS_RIDDEN("is_ridden"),
   IS_RIDING("is_riding"),
   IS_SNEAKING("is_sneaking"),
   IS_SPRINTING("is_sprinting"),
   IS_WET("is_wet");

private String name;
   private RenderManager renderManager;
   private static final RenderEntityParameterBool[] VALUES = values();
@Override
   public ExpressionType getFloatValue() {
      return ExpressionType.BOOL;
   }

   public static RenderEntityParameterBool parse(String str) {
      if (str == null) {
         return null;
      } else {
         for (int i = 0; i < VALUES.length; i++) {
            RenderEntityParameterBool bool = VALUES[i];
            if (bool.getName().equals(str)) {
               return bool;
            }
         }

         return null;
      }
   }

   private RenderEntityParameterBool(String str) {
      this.name = str;
      this.renderManager = Minecraft.getMinecraft().getRenderManager();
   }

   public String getName() {
      return this.name;
   }

   @Override
   public boolean eval() {
      Render renderRender = this.renderManager.renderRender;
      if (renderRender == null) {
         return false;
      } else {
         if (renderRender instanceof RendererLivingEntity) {
            EntityLivingBase renderEntity = ((RendererLivingEntity)renderRender).renderEntity;
            if (renderEntity == null) {
               return false;
            }

            switch (this) {
               case IS_ALIVE:
                  return renderEntity.isEntityAlive();
               case IS_BURNING:
                  return renderEntity.isBurning();
               case IS_CHILD:
                  return renderEntity.isChild();
               case IS_HURT:
                  return renderEntity.hurtTime > 0;
               case IS_IN_LAVA:
                  return renderEntity.isInLava();
               case IS_IN_WATER:
                  return renderEntity.isInWater();
               case IS_INVISIBLE:
                  return renderEntity.isInvisible();
               case IS_ON_GROUND:
                  return renderEntity.onGround;
               case IS_RIDDEN:
                  return renderEntity.riddenByEntity != null;
               case IS_RIDING:
                  return renderEntity.isRiding();
               case IS_SNEAKING:
                  return renderEntity.isSneaking();
               case IS_SPRINTING:
                  return renderEntity.isSprinting();
               case IS_WET:
                  return renderEntity.isWet();
            }
         }

         return false;
      }
   }

}
