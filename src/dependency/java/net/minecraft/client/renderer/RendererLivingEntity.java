package net.minecraft.client.renderer;

import client.Client;
import client.ClientGlobals;
import client.event.EventLivingUpdate;
import client.event.EventLivingUpdateCallback;
import client.event.PreMotionDispatcher;
import client.event.RenderEntityListener;
import client.module.Module;
import client.render.EmissiveTexturesManager;
import client.render.EntitySkinLocation;
import client.render.RenderManager;
import client.render.RenderUtils;
import client.render.WorldRenderer;
import client.setting.ClientSettingsBlackType;
import client.setting.ItemNoneType;
import client.setting.MovementBooleanProperty;
import client.setting.TabListCapeType;
import client.util.AdapterManager;
import client.util.AimSelector;
import client.util.BuildManager;
import client.util.DefaultVertexFormats;
import client.util.FloatEntryRegistry;
import client.util.PassiveFloatEntry;
import client.util.PreEndHandManager;
import client.util.RandomUtil;
import client.util.SecondaryClientManager;
import client.util.SlotManager;
import client.util.VectorMatcher;
import com.google.common.collect.Lists;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.DiamondArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public abstract class RendererLivingEntity extends EntitySkinLocation {
   public static final Logger logger = LogManager.getLogger();
   public static final DynamicTexture dynamicTexture = new DynamicTexture(16, 16);
   public PassiveFloatEntry passiveFloatEntry;
   public FloatBuffer floatBuffer = VectorMatcher.resolveFloatBuffer(4);
   public List list = Lists.newArrayList();
   public boolean enabled4 = false;
   public static float factor6 = 64.0F;
   public static float factor8 = 32.0F;
   public EntityLivingBase entityLivingBase;
   public float factor12;
   public float factor3;
   public float factor11;
   public float factor7;
   public float factor4;
   public float factor9;
   public float factor10;
   public boolean enabled3;
   public boolean enabled;
   public static final boolean enabled2 = Boolean.getBoolean("animate.model.living");

   public void setEnabled4(boolean enabled5) {
      this.enabled4 = enabled5;
   }

   public void applyEntityLivingBase3(EntityLivingBase livingEntity, double posX, double posY, double posZ) {
      if (this.matchesEntityLivingBase(livingEntity) && !ClientGlobals.moduleManager.antiStrike.canExecute9() && livingEntity.isState1()) {
         double coordinate = livingEntity.getCoordinate(this.renderManager.entity);
         float factor = livingEntity.isSprinting() ? factor8 : factor6;
         boolean flag = livingEntity instanceof EntityPlayer && ((Module)ClientGlobals.moduleManager.nameTags).isToggled();
         if (coordinate < (double)(factor * factor) || flag) {
            if (BuildManager.canExecute() && EntityRenderer.state1) {
               PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager6);
            }

            String text = livingEntity.getText5();
            if (livingEntity instanceof AbstractClientPlayer) {
               String name = ((EntityPlayer)livingEntity).getGameProfile().getName();
               if (ClientGlobals.moduleManager.midClick.checkString(name, true) && ((Module)ClientGlobals.moduleManager.midClick).isToggled()) {
                  String text2 = StringUtils.replace(
                     text, name, (ClientGlobals.moduleManager.midClick.settingSimple.isEnabled() ? "§l" : "§a§l") + name
                  );
                  if (!ClientGlobals.moduleManager.midClick.settingSimple.isEnabled()) {
                     text = "§7[§2§lFriend§7]§9 " + text2;
                  } else {
                     text = text2;
                  }
               }

               if (ClientGlobals.moduleManager.murdererFinder.checkString(name)) {
                  text = text + " §7[§4Murderer§7]§9";
               }
            }

            GlStateManager.alphaFunc(516, 0.1F);
            if (livingEntity.isSprinting() && !((Module)Client.getClient().getModuleManager().nameTags).isToggled()) {
               FontRenderer fontRendererAsFontRenderer = this.getFontRendererAsFontRenderer();
               GlStateManager.pushMatrix();
               GlStateManager.translate((float)posX, (float)posY + livingEntity.height + 0.5F - (livingEntity.isActive16() ? livingEntity.height / 2.0F : 0.0F), (float)posZ);
               GL11.glNormal3f(0.0F, 1.0F, 0.0F);
               GlStateManager.rotate(-this.renderManager.scaleFactor, 0.0F, 1.0F, 0.0F);
               GlStateManager.rotate(this.renderManager.scaleFactor7, 1.0F, 0.0F, 0.0F);
               GlStateManager.translate3f3(-0.02666667F, -0.02666667F, 0.02666667F);
               GlStateManager.translate(0.0F, 9.374999F, 0.0F);
               GlStateManager.applyState3();
               GlStateManager.depthMask(false);
               GlStateManager.disableBlend();
               GlStateManager.enableTexture2D();
               GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
               int count = fontRendererAsFontRenderer.getIndex(text) / 2;
               Tessellator tessellator = Tessellator.getTessellator();
               WorldRenderer worldRenderer = tessellator.getWorldRenderer();
               worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry3);
               worldRenderer.getWorldRenderer((double)(-count - 1), -1.0, 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.25F).updateState();
               worldRenderer.getWorldRenderer((double)(-count - 1), 8.0, 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.25F).updateState();
               worldRenderer.getWorldRenderer((double)(count + 1), 8.0, 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.25F).updateState();
               worldRenderer.getWorldRenderer((double)(count + 1), -1.0, 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.25F).updateState();
               tessellator.updateState();
               GlStateManager.disableTexture2D();
               GlStateManager.depthMask(true);
               fontRendererAsFontRenderer.getIndex(text, -count, 0, 553648127);
               GlStateManager.applyState13();
               GlStateManager.enableBlend();
               GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.popMatrix();
            } else {
               this.applyEntity(livingEntity, posX, posY - (livingEntity.isActive16() ? (double)(livingEntity.height / 2.0F) : 0.0), posZ, text, 0.02666667F, coordinate);
            }

            if (BuildManager.canExecute() && EntityRenderer.state1) {
               PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager7);
            }
         }
      }
   }

   public List getList() {
      return this.list;
   }

   public void updateState2() {
      GlStateManager.applyState13();
      GlStateManager.enableState5(OpenGlHelper.lightmapTexUnit);
      GlStateManager.disableTexture2D();
      GlStateManager.enableState5(OpenGlHelper.defaultTexUnit);
      GlStateManager.disableTexture2D();
      GlStateManager.enableState5(OpenGlHelper.lightmapTexUnit);
   }

   public boolean remove(PreMotionDispatcher preMotionDispatcher) {
      return this.list.remove(preMotionDispatcher);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ) {
      this.applyEntityLivingBase3((EntityLivingBase)entity, posX, posY, posZ);
   }

   public boolean matchesEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      return this.matchesEntityLivingBase(livingEntity, factor, true);
   }

   public float computeFactor(EntityLivingBase livingEntity, float factor) {
      return (float)livingEntity.ticksExisted + factor;
   }

   public float computeFactor(float posX, float posY, float posZ) {
      float factor = posY - posX;

      while (factor < -180.0F) {
         factor += 360.0F;
      }

      while (factor >= 180.0F) {
         factor -= 360.0F;
      }

      return posX + posZ * factor;
   }

   public void applyEntityLivingBase(EntityLivingBase livingEntity, double posX, double posY, double posZ, float factor, float factor2) {
      if (((Module)ClientGlobals.moduleManager.chams).isToggled()) {
         ClientGlobals.moduleManager.chams.applyEntity(livingEntity);
      }

      if (enabled2) {
         livingEntity.factor11 = 1.0F;
      }

      GlStateManager.pushMatrix();
      GlStateManager.disableAlpha();
      this.passiveFloatEntry.factor = this.computeFactor2(livingEntity, factor2);
      this.passiveFloatEntry.enabled = livingEntity.isSneaking();
      this.passiveFloatEntry.enabled2 = livingEntity.isActive16();

      try {
         GL11.glPushMatrix();
         float factor5 = this.computeFactor(livingEntity.factor12, livingEntity.scaleFactor8, factor2);
         float factor13 = this.computeFactor(livingEntity.scaleFactor22, livingEntity.scaleFactor12, factor2);
         float factor14 = factor13 - factor5;
         if (this.passiveFloatEntry.enabled && livingEntity.ridingEntity instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase)livingEntity.ridingEntity;
            float factor15 = this.computeFactor(entity.factor12, entity.scaleFactor8, factor2);
            float factor16 = MathHelper.sqrt_float(factor13 - factor15);
            if (factor16 < -85.0F) {
               factor16 = -85.0F;
            }

            if (factor16 >= 85.0F) {
               factor16 = 85.0F;
            }

            factor5 = factor13 - factor16;
            if (factor16 * factor16 > 2500.0F) {
               factor5 += factor16 * 0.2F;
            }

            factor14 = factor13 - factor5;
         }

         float factor17;
         if (livingEntity == this.renderManager.entity) {
            factor17 = livingEntity.scaleFactor + (livingEntity.scaleFactor21 - livingEntity.scaleFactor) * factor2;
         } else {
            factor17 = livingEntity.prevRotationPitch + (livingEntity.rotationPitch - livingEntity.prevRotationPitch) * factor2;
         }

         this.applyEntityLivingBase(livingEntity, posX, posY, posZ);
         float factor18 = this.computeFactor(livingEntity, factor2);
         this.applyEntityLivingBase(livingEntity, factor18, factor5, factor2);
         GlStateManager.disableLighting();
         GlStateManager.translate3f3(-1.0F, -1.0F, 1.0F);
         this.applyEntityLivingBase(livingEntity, factor2);
         float factor19 = 0.0625F;
         GlStateManager.translate(0.0F, -1.5078125F, 0.0F);
         float factor20 = livingEntity.factor9 + (livingEntity.factor11 - livingEntity.factor9) * factor2;
         float factor21 = livingEntity.factor14 - livingEntity.factor11 * (1.0F - factor2);
         if (livingEntity.isActive16()) {
            factor21 *= 3.0F;
         }

         if (factor20 > 1.0F) {
            factor20 = 1.0F;
         }

         GlStateManager.applyState14();
         this.passiveFloatEntry.applyEntityLivingBase(livingEntity, factor21, factor20, factor2);
         this.passiveFloatEntry.applyEntity(factor21, factor20, factor18, factor14, factor17, 0.0625F, livingEntity);
         if (AdapterManager.isEnabled()) {
            this.entityLivingBase = livingEntity;
            this.factor12 = factor21;
            this.factor3 = factor20;
            this.factor11 = factor18;
            this.factor7 = factor14;
            this.factor4 = factor17;
            this.factor9 = factor19;
            this.factor10 = factor2;
         }

         if (this.enabled4) {
            boolean flag = this.matchesEntityLivingBaseByEntityLivingBase(livingEntity);
            this.applyEntityLivingBase(
               livingEntity,
               factor21,
               factor20,
               factor18,
               factor14,
               factor17,
               0.0625F,
               ((Module)ClientGlobals.moduleManager.esp).isToggled() && ClientGlobals.moduleManager.esp.checkEntityLivingBase(livingEntity),
               true
            );
            if (flag) {
               this.updateState2();
            }
         } else {
            boolean flag2 = this.matchesEntityLivingBaseByEntityLivingBase(livingEntity, factor2);
            if (EmissiveTexturesManager.isEnabled2()) {
               EmissiveTexturesManager.renderElement4();
            }

            if (this.enabled3) {
               GlStateManager.pushMatrix();
            }

            if (((Module)ClientGlobals.moduleManager.chams).isToggled()
               && ClientGlobals.moduleManager.chams.checkEntity(livingEntity)
               && ClientGlobals.moduleManager.chams.settingColoredEntityChams.isEnabled()) {
               if (BuildManager.canExecute() && EntityRenderer.state) {
                  PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager6);
               }

               if (ClientGlobals.moduleManager.chams.settingRenderTextureEntity.isEnabled()
                  && !ClientGlobals.moduleManager.chams.settingOnlyThroughWallsEntities.isEnabled()) {
                  this.applyEntityLivingBase(
                     livingEntity,
                     factor21,
                     factor20,
                     factor18,
                     factor14,
                     factor17,
                     0.0625F,
                     ((Module)ClientGlobals.moduleManager.antiInvisibles).isToggled() && ClientGlobals.moduleManager.antiInvisibles.checkEntity(livingEntity),
                     true
                  );
               }

               boolean flag3 = GL11.glIsEnabled(2896);
               boolean flag4 = GL11.glIsEnabled(2929);
               boolean flag5 = GL11.glIsEnabled(3553);
               GlStateManager.disableBlend();
               GlStateManager.tryBlendFuncSeparate(770, 771, 0, 0);
               GL11.glDisable(2896);
               GL11.glDisable(2929);
               RenderUtils.setColor(ClientGlobals.moduleManager.chams.settingColorEntity.getInt12(0));
               GL11.glDisable(3553);
               this.applyEntityLivingBase(
                  livingEntity,
                  factor21,
                  factor20,
                  factor18,
                  factor14,
                  factor17,
                  0.0625F,
                  ((Module)ClientGlobals.moduleManager.antiInvisibles).isToggled() && ClientGlobals.moduleManager.antiInvisibles.checkEntity(livingEntity),
                  false
               );
               GL11.glEnable(2929);
               if (flag3) {
                  GL11.glEnable(2896);
               }

               if (!flag4) {
                  GL11.glDisable(2929);
               }

               if (flag5) {
                  GL11.glEnable(3553);
               }

               GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
               if (ClientGlobals.moduleManager.chams.settingOnlyThroughWallsEntities.isEnabled()) {
                  this.applyEntityLivingBase(
                     livingEntity,
                     factor21,
                     factor20,
                     factor18,
                     factor14,
                     factor17,
                     0.0625F,
                     ((Module)ClientGlobals.moduleManager.antiInvisibles).isToggled() && ClientGlobals.moduleManager.antiInvisibles.checkEntity(livingEntity),
                     true
                  );
               }

               if (BuildManager.canExecute() && EntityRenderer.state) {
                  PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager7);
               }
            } else {
               this.applyEntityLivingBase(
                  livingEntity,
                  factor21,
                  factor20,
                  factor18,
                  factor14,
                  factor17,
                  0.0625F,
                  ((Module)ClientGlobals.moduleManager.antiInvisibles).isToggled() && ClientGlobals.moduleManager.antiInvisibles.checkEntity(livingEntity),
                  true
               );
            }

            if (this.enabled3) {
               GlStateManager.popMatrix();
            }

            if (EmissiveTexturesManager.isEnabled2()) {
               if (EmissiveTexturesManager.isEnabled4()) {
                  this.enabled3 = true;
                  EmissiveTexturesManager.renderElement();
                  GlStateManager.pushMatrix();
                  this.applyEntityLivingBase(livingEntity, factor21, factor20, factor18, factor14, factor17, factor19, false, true);
                  GlStateManager.popMatrix();
                  EmissiveTexturesManager.renderElement3();
               }

               EmissiveTexturesManager.renderElement2();
            }

            if (flag2) {
               this.updateState3();
            }

            GlStateManager.depthMask(true);
            if (!(livingEntity instanceof EntityPlayer) || !((EntityPlayer)livingEntity).isActive23()) {
               this.applyEntityLivingBase(livingEntity, factor21, factor20, factor2, factor18, factor14, factor17, 0.0625F);
            }

            if (AdapterManager.isEnabled()) {
               this.entityLivingBase = null;
            }
         }

         GlStateManager.applyState8();
         GL11.glPopMatrix();
      } catch (Exception exception) {
         logger.error("Couldn't render entity", exception);
      }

      RenderEntityListener.applyEntityLivingBase(livingEntity, this, 22879, factor2, posX, 10970626, (byte)-59, posY, posZ);
      GlStateManager.enableState5(OpenGlHelper.defaultTexUnit);
      GlStateManager.disableTexture2D();
      GlStateManager.enableState5(OpenGlHelper.lightmapTexUnit);
      GlStateManager.enableLighting();
      GlStateManager.popMatrix();
      if (!this.enabled4) {
         super.applyEntity(livingEntity, posX, posY, posZ, factor, factor2);
      }

      if (((Module)ClientGlobals.moduleManager.chams).isToggled()) {
         ClientGlobals.moduleManager.chams.updateState();
      }
   }

   public void applyEntityLivingBase(EntityLivingBase livingEntity, double posX, double posY, double posZ) {
      GlStateManager.translate((float)posX, (float)posY, (float)posZ);
   }

   public PassiveFloatEntry getPassiveFloatEntry() {
      return this.passiveFloatEntry;
   }

   @Override
   public boolean matchesEntityByEntity(Entity entity) {
      return this.matchesEntityLivingBase((EntityLivingBase)entity);
   }

   public boolean add(Object entry) {
      return this.list.add((PreMotionDispatcher)entry);
   }

   public void applyEntityLivingBase(EntityLivingBase livingEntity, float factor) {
   }

   public void updateState3() {
      GlStateManager.enableState5(OpenGlHelper.lightmapTexUnit);
      GlStateManager.disableTexture2D();
      GL11.glTexEnvi(8960, 8704, OpenGlHelper.count5);
      GL11.glTexEnvi(8960, OpenGlHelper.count39, 8448);
      GL11.glTexEnvi(8960, OpenGlHelper.count20, OpenGlHelper.lightmapTexUnit);
      GL11.glTexEnvi(8960, OpenGlHelper.count21, OpenGlHelper.count8);
      GL11.glTexEnvi(8960, OpenGlHelper.count28, 768);
      GL11.glTexEnvi(8960, OpenGlHelper.count31, 768);
      GL11.glTexEnvi(8960, OpenGlHelper.count37, 8448);
      GL11.glTexEnvi(8960, OpenGlHelper.count15, OpenGlHelper.lightmapTexUnit);
      GL11.glTexEnvi(8960, OpenGlHelper.count35, OpenGlHelper.count8);
      GL11.glTexEnvi(8960, OpenGlHelper.count26, 770);
      GL11.glTexEnvi(8960, OpenGlHelper.count40, 770);
      GlStateManager.enableState5(OpenGlHelper.defaultTexUnit);
      GL11.glTexEnvi(8960, 8704, OpenGlHelper.count5);
      GL11.glTexEnvi(8960, OpenGlHelper.count39, 8448);
      GL11.glTexEnvi(8960, OpenGlHelper.count28, 768);
      GL11.glTexEnvi(8960, OpenGlHelper.count31, 768);
      GL11.glTexEnvi(8960, OpenGlHelper.count20, 5890);
      GL11.glTexEnvi(8960, OpenGlHelper.count21, OpenGlHelper.count22);
      GL11.glTexEnvi(8960, OpenGlHelper.count37, 8448);
      GL11.glTexEnvi(8960, OpenGlHelper.count26, 770);
      GL11.glTexEnvi(8960, OpenGlHelper.count15, 5890);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.enableState5(OpenGlHelper.count4);
      GlStateManager.enableTexture2D();
      GlStateManager.setActiveTexture(0);
      GL11.glTexEnvi(8960, 8704, OpenGlHelper.count5);
      GL11.glTexEnvi(8960, OpenGlHelper.count39, 8448);
      GL11.glTexEnvi(8960, OpenGlHelper.count28, 768);
      GL11.glTexEnvi(8960, OpenGlHelper.count31, 768);
      GL11.glTexEnvi(8960, OpenGlHelper.count20, 5890);
      GL11.glTexEnvi(8960, OpenGlHelper.count21, OpenGlHelper.count22);
      GL11.glTexEnvi(8960, OpenGlHelper.count37, 8448);
      GL11.glTexEnvi(8960, OpenGlHelper.count26, 770);
      GL11.glTexEnvi(8960, OpenGlHelper.count15, 5890);
      GlStateManager.enableState5(OpenGlHelper.lightmapTexUnit);
      if (BuildManager.canExecute()) {
         PreEndHandManager.applyFloatByFloat(0.0F, 0.0F, 0.0F, 0.0F);
      }
   }

   public float computeFactor2(EntityLivingBase livingEntity, float factor) {
      return livingEntity.computeFactorByFloat(factor);
   }

   public void updateState() {
   }

   public int computeCount(EntityLivingBase livingEntity, float factor, float factor2) {
      return 0;
   }

   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag, boolean flag2) {
      boolean flag3 = !livingEntity.isActive12();
      boolean flag4 = !flag3 && !livingEntity.matchesEntityPlayer4(Minecraft.getMinecraft().thePlayer);
      if (flag3 || flag4 || flag) {
         if (!this.matchesEntity3(livingEntity)) {
            return;
         }

         if (flag4) {
            GlStateManager.pushMatrix();
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 0.15F);
            GlStateManager.depthMask(false);
            GlStateManager.disableBlend();
            GlStateManager.glStateAction3(770, 771);
            GlStateManager.alphaFunc(516, 0.003921569F);
         }

         this.passiveFloatEntry.applyEntity(livingEntity, posX, posX2, posX3, posX4, posY, posZ, flag2);
         if (flag4) {
            GlStateManager.enableBlend();
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.popMatrix();
            GlStateManager.depthMask(true);
         }
      }
   }

   public boolean matchesEntityLivingBase(EntityLivingBase livingEntity, float factor, boolean flag) {
      float factor2 = 0.3F;
      float factor5 = 0.0F;
      float factor13 = 0.0F;
      float factor14 = 1.0F;
      EventLivingUpdate event = EventLivingUpdateCallback.resolveEventLivingUpdate(livingEntity, factor14, factor13, factor5, factor2);
      float factor15 = livingEntity.computeFactor(factor);
      int count = this.computeCount(livingEntity, factor15, factor);
      boolean flag2 = (count >> 24 & 0xFF) > 0;
      boolean flag3 = livingEntity.hurtTime > 0 || livingEntity.indexOffset29 > 0;
      if (!flag2 && !flag3 && !event.isEnabled()) {
         return false;
      } else if (!flag2 && !flag && !event.isEnabled()) {
         return false;
      } else {
         GlStateManager.enableState5(OpenGlHelper.lightmapTexUnit);
         GlStateManager.disableTexture2D();
         GL11.glTexEnvi(8960, 8704, OpenGlHelper.count5);
         GL11.glTexEnvi(8960, OpenGlHelper.count39, 8448);
         GL11.glTexEnvi(8960, OpenGlHelper.count20, OpenGlHelper.lightmapTexUnit);
         GL11.glTexEnvi(8960, OpenGlHelper.count21, OpenGlHelper.count8);
         GL11.glTexEnvi(8960, OpenGlHelper.count28, 768);
         GL11.glTexEnvi(8960, OpenGlHelper.count31, 768);
         GL11.glTexEnvi(8960, OpenGlHelper.count37, 7681);
         GL11.glTexEnvi(8960, OpenGlHelper.count15, OpenGlHelper.lightmapTexUnit);
         GL11.glTexEnvi(8960, OpenGlHelper.count26, 770);
         GlStateManager.enableState5(OpenGlHelper.defaultTexUnit);
         GlStateManager.disableTexture2D();
         GL11.glTexEnvi(8960, 8704, OpenGlHelper.count5);
         GL11.glTexEnvi(8960, OpenGlHelper.count39, OpenGlHelper.count33);
         GL11.glTexEnvi(8960, OpenGlHelper.count20, OpenGlHelper.count29);
         GL11.glTexEnvi(8960, OpenGlHelper.count21, OpenGlHelper.count22);
         GL11.glTexEnvi(8960, OpenGlHelper.count6, OpenGlHelper.count29);
         GL11.glTexEnvi(8960, OpenGlHelper.count28, 768);
         GL11.glTexEnvi(8960, OpenGlHelper.count31, 768);
         GL11.glTexEnvi(8960, OpenGlHelper.count10, 770);
         GL11.glTexEnvi(8960, OpenGlHelper.count37, 7681);
         GL11.glTexEnvi(8960, OpenGlHelper.count15, OpenGlHelper.count22);
         GL11.glTexEnvi(8960, OpenGlHelper.count26, 770);
         ((Buffer)this.floatBuffer).position(0);
         if (!flag3 && !event.isEnabled()) {
            float factor16 = (float)(count >> 24 & 0xFF) / 255.0F;
            float factor17 = (float)(count >> 16 & 0xFF) / 255.0F;
            float factor18 = (float)(count >> 8 & 0xFF) / 255.0F;
            float factor19 = (float)(count & 0xFF) / 255.0F;
            this.floatBuffer.put(factor17);
            this.floatBuffer.put(factor18);
            this.floatBuffer.put(factor19);
            this.floatBuffer.put(1.0F - factor16);
            if (BuildManager.canExecute()) {
               PreEndHandManager.applyFloatByFloat(factor17, factor18, factor19, 1.0F - factor16);
            }
         } else {
            this.floatBuffer.put(event.getFactor());
            this.floatBuffer.put(event.getFactor3());
            this.floatBuffer.put(event.getFactor4());
            this.floatBuffer.put(event.getFactor2());
            if (BuildManager.canExecute()) {
               PreEndHandManager.applyFloatByFloat(event.getFactor(), event.getFactor3(), event.getFactor4(), event.getFactor2());
            }
         }

         ((Buffer)this.floatBuffer).flip();
         GL11.glTexEnv(8960, 8705, this.floatBuffer);
         GlStateManager.enableState5(OpenGlHelper.count4);
         GlStateManager.disableTexture2D();
         GlStateManager.setActiveTexture(dynamicTexture.getCount());
         GL11.glTexEnvi(8960, 8704, OpenGlHelper.count5);
         GL11.glTexEnvi(8960, OpenGlHelper.count39, 8448);
         GL11.glTexEnvi(8960, OpenGlHelper.count20, OpenGlHelper.count22);
         GL11.glTexEnvi(8960, OpenGlHelper.count21, OpenGlHelper.defaultTexUnit);
         GL11.glTexEnvi(8960, OpenGlHelper.count28, 768);
         GL11.glTexEnvi(8960, OpenGlHelper.count31, 768);
         GL11.glTexEnvi(8960, OpenGlHelper.count37, 7681);
         GL11.glTexEnvi(8960, OpenGlHelper.count15, OpenGlHelper.count22);
         GL11.glTexEnvi(8960, OpenGlHelper.count26, 770);
         GlStateManager.enableState5(OpenGlHelper.lightmapTexUnit);
         return true;
      }
   }

   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      for (int index = 0; index < this.list.size(); index++) {
         PreMotionDispatcher preMotionDispatcher = (PreMotionDispatcher)this.list.get(index);
         boolean flag = this.matchesEntityLivingBase(livingEntity, posX3, preMotionDispatcher.isActive());
         if (EmissiveTexturesManager.isEnabled2()) {
            EmissiveTexturesManager.renderElement4();
         }

         if (this.enabled) {
            GlStateManager.pushMatrix();
         }

         preMotionDispatcher.applyEntityLivingBase(livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ);
         if (this.enabled) {
            GlStateManager.popMatrix();
         }

         if (EmissiveTexturesManager.isEnabled2()) {
            if (EmissiveTexturesManager.isEnabled4()) {
               this.enabled = true;
               EmissiveTexturesManager.renderElement();
               GlStateManager.pushMatrix();
               preMotionDispatcher.applyEntityLivingBase(livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ);
               GlStateManager.popMatrix();
               EmissiveTexturesManager.renderElement3();
            }

            EmissiveTexturesManager.renderElement2();
         }

         if (flag) {
            this.updateState3();
         }
      }
   }

   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
      GlStateManager.rotate(180.0F - posY, 0.0F, 1.0F, 0.0F);
      if (livingEntity.indexOffset29 > 0) {
         float factor = MathHelper.sin(((float)livingEntity.indexOffset29 + posZ - 1.0F) / 20.0F * 1.6F);
         if (factor > 1.0F) {
            factor = 1.0F;
         }

         GlStateManager.rotate(factor * this.computeFactor(livingEntity), 0.0F, 0.0F, 1.0F);
      } else {
         String text = ClientSettingsBlackType.resolveText(livingEntity.getText());
         if (text != null
            && (text.equals("Dinnerbone") || text.equals("Grumm"))
            && (!(livingEntity instanceof EntityPlayer) || ((EntityPlayer)livingEntity).checkTabListCapeType(TabListCapeType.CAPE))) {
            GlStateManager.translate(0.0F, livingEntity.height + 0.1F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
         }
      }
   }

   public boolean matchesEntityLivingBase(EntityLivingBase livingEntity) {
      EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
      boolean flag = livingEntity != player
         || ((Module)ClientGlobals.moduleManager.nameTags).isToggled() && ClientGlobals.moduleManager.nameTags.settingMyself.isEnabled();
      if (livingEntity instanceof EntityPlayer && flag) {
         if (ClientGlobals.moduleManager.antiBot.checkEntity2(livingEntity)) {
            return false;
         }

         SecondaryClientManager secondaryClientManager = livingEntity.getSecondaryClientManager();
         SecondaryClientManager secondaryClientManager2 = player.getSecondaryClientManager();
         if (secondaryClientManager != null) {
            if (((Module)ClientGlobals.moduleManager.nameTags).isToggled() && ClientGlobals.moduleManager.nameTags.settingInvisibleTeam.isEnabled()) {
               return true;
            }

            MovementBooleanProperty movementBooleanProperty = secondaryClientManager.getMovementBooleanProperty2();
            switch (AimSelector.countArray[movementBooleanProperty.ordinal()]) {
               case 1:
                  return true;
               case 2:
                  return false;
               case 3:
                  return secondaryClientManager2 == null || secondaryClientManager.matchesSecondaryClientManager(secondaryClientManager2);
               case 4:
                  return secondaryClientManager2 == null || !secondaryClientManager.matchesSecondaryClientManager(secondaryClientManager2);
               default:
                  return true;
            }
         }
      }

      return Minecraft.isActive2()
         && flag
         && (
            !livingEntity.matchesEntityPlayer4(player)
               || ((Module)ClientGlobals.moduleManager.nameTags).isToggled() && ClientGlobals.moduleManager.nameTags.settingInvisible.isEnabled()
         )
         && livingEntity.entity == null;
   }

   public float computeFactor(EntityLivingBase livingEntity) {
      return 90.0F;
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityLivingBase((EntityLivingBase)entity, posX, posY, posZ, factor, factor2);
   }

   public boolean matchesEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity) {
      int count = 16777215;
      if (livingEntity instanceof EntityPlayer) {
         ScorePlayerTeam secondaryClientManager = (ScorePlayerTeam)livingEntity.getSecondaryClientManager();
         if (secondaryClientManager != null) {
            String text = FontRenderer.resolveTextByText(secondaryClientManager.getText());
            if (text.length() >= 2) {
               count = this.getFontRendererAsFontRenderer().computeCountByChar(text.charAt(1));
            }
         }
      }

      float factor = (float)(count >> 16 & 0xFF) / 255.0F;
      float factor2 = (float)(count >> 8 & 0xFF) / 255.0F;
      float factor5 = (float)(count & 0xFF) / 255.0F;
      GlStateManager.applyState3();
      GlStateManager.enableState5(OpenGlHelper.lightmapTexUnit);
      float factor13 = 1.0F;
      if (((Module)ClientGlobals.moduleManager.esp).isToggled()) {
         if (ClientGlobals.moduleManager.esp.settingVanillaHitColorBool.isEnabled() && livingEntity.hurtTime > 0) {
            int intItem = ClientGlobals.moduleManager.esp.settingVanillaHitColor.getInt12(0);
            factor = RandomUtil.processAction5(intItem);
            factor2 = RandomUtil.processAction4(intItem);
            factor5 = RandomUtil.processAction7(intItem);
         } else if (!ClientGlobals.moduleManager.esp.settingVanillaTeamColor.isEnabled()) {
            int intItem2 = ClientGlobals.moduleManager.esp.settingVanillaColor.getInt12(0);
            factor = RandomUtil.processAction5(intItem2);
            factor2 = RandomUtil.processAction4(intItem2);
            factor5 = RandomUtil.processAction7(intItem2);
         }
      }

      GlStateManager.color4f2(factor, factor2, factor5, factor13);
      GlStateManager.enableTexture2D();
      GlStateManager.enableState5(OpenGlHelper.defaultTexUnit);
      GlStateManager.enableTexture2D();
      GlStateManager.enableState5(OpenGlHelper.lightmapTexUnit);
      return true;
   }

   public void applyEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity, float factor, double posX, double posY, double posZ, boolean flag) {
      try {
         this.passiveFloatEntry.enabled2 = livingEntity.isActive16();
         this.passiveFloatEntry.factor = this.computeFactor2(livingEntity, factor);
         this.passiveFloatEntry.enabled = livingEntity.isSneaking();
         if (this.passiveFloatEntry instanceof FloatEntryRegistry) {
            FloatEntryRegistry passiveFloatEntry2 = (FloatEntryRegistry)this.passiveFloatEntry;
            passiveFloatEntry2.enabled5 = livingEntity.isSprinting();
            ItemStack stack = livingEntity instanceof EntityPlayerSP ? SlotManager.itemStack : livingEntity.getItemStack();
            if (stack == null) {
               passiveFloatEntry2.count5 = 0;
            } else {
               passiveFloatEntry2.count5 = 1;
               if (livingEntity instanceof EntityPlayer && ((EntityPlayer)livingEntity).getEntityId2() > 0) {
                  ItemNoneType itemNoneType = stack.processAction4();
                  if (itemNoneType == ItemNoneType.BLOCK) {
                     passiveFloatEntry2.count5 = 3;
                  } else if (itemNoneType == ItemNoneType.BOW) {
                     passiveFloatEntry2.enabled6 = true;
                  }
               }
            }
         }

         GL11.glPushMatrix();
         float factor2 = this.computeFactor(livingEntity.factor12, livingEntity.scaleFactor8, factor);
         float factor5 = this.computeFactor(livingEntity.scaleFactor22, livingEntity.scaleFactor12, factor);
         float factor13 = factor5 - factor2;
         if (this.passiveFloatEntry.enabled && livingEntity.ridingEntity instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase)livingEntity.ridingEntity;
            float factor14 = this.computeFactor(entity.factor12, entity.scaleFactor8, factor);
            factor13 = factor5 - factor14;
            float factor15 = MathHelper.sqrt_float(factor13);
            if (factor15 < -85.0F) {
               factor15 = -85.0F;
            }

            if (factor15 >= 85.0F) {
               factor15 = 85.0F;
            }

            factor2 = factor5 - factor15;
            if (factor15 * factor15 > 2500.0F) {
               factor2 += factor15 * 0.2F;
            }
         }

         float factor16;
         if (livingEntity == this.renderManager.entity) {
            factor16 = livingEntity.scaleFactor + (livingEntity.scaleFactor21 - livingEntity.scaleFactor) * factor;
         } else {
            factor16 = livingEntity.prevRotationPitch + (livingEntity.rotationPitch - livingEntity.prevRotationPitch) * factor;
         }

         this.applyEntityLivingBase(
            livingEntity, posX - this.renderManager.getRenderPosX(), posY - this.renderManager.getRenderPosY(), posZ - this.renderManager.getRenderPosZ()
         );
         float factor17 = this.computeFactor(livingEntity, factor);
         this.applyEntityLivingBase(livingEntity, factor17, factor2, factor);
         GlStateManager.disableLighting();
         GlStateManager.translate3f3(-1.0F, -1.0F, 1.0F);
         this.applyEntityLivingBase(livingEntity, factor);
         GlStateManager.translate(0.0F, -1.5078125F, 0.0F);
         float factor18 = livingEntity.factor9 + (livingEntity.factor11 - livingEntity.factor9) * factor;
         float factor19 = livingEntity.factor14 - livingEntity.factor11 * (1.0F - factor);
         if (livingEntity.isActive16()) {
            factor19 *= 3.0F;
         }

         if (factor18 > 1.0F) {
            factor18 = 1.0F;
         }

         GlStateManager.applyState14();
         this.passiveFloatEntry.applyEntityLivingBase(livingEntity, factor19, factor18, factor);
         this.passiveFloatEntry.applyEntity(factor19, factor18, factor17, factor13, factor16, 0.0625F, livingEntity);
         if (this.enabled4) {
            boolean flag2 = this.matchesEntityLivingBaseByEntityLivingBase(livingEntity);
            this.applyEntityLivingBase(livingEntity, factor19, factor18, factor17, factor13, factor16, 0.0625F, false, true);
            if (flag2) {
               this.updateState2();
            }
         } else {
            boolean flag3 = this.matchesEntityLivingBaseByEntityLivingBase(livingEntity, factor);
            this.applyEntityLivingBase(livingEntity, factor19, factor18, factor17, factor13, factor16, 0.0625F, false, true);
            if (flag3) {
               this.updateState3();
            }

            GlStateManager.depthMask(true);
            if ((!(livingEntity instanceof EntityPlayer) || !((EntityPlayer)livingEntity).isActive23()) && flag) {
               this.applyEntityLivingBase(livingEntity, factor19, factor18, factor, factor17, factor13, factor16, 0.0625F);
            }
         }

         GlStateManager.applyState8();
         GL11.glPopMatrix();
      } catch (Exception exception) {
         logger.error("Couldn't render entity", exception);
      }
   }

   public RendererLivingEntity(RenderManager renderManager, PassiveFloatEntry passiveFloatEntry2, float factor) {
      super(renderManager);
      this.passiveFloatEntry = passiveFloatEntry2;
      this.factor = factor;
      this.enabled3 = this.passiveFloatEntry instanceof DiamondArmor;
   }

   static {
      int[] countArray = dynamicTexture.getCountArray();

      for (int index = 0; index < 256; index++) {
         countArray[index] = -1;
      }

      dynamicTexture.updateState2();
   }
}
