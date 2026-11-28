package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.ColorSetting;
import net.augustus.utils.math.NumberFormatUtil;
import net.augustus.render.RenderUtil;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import org.lwjgl.util.glu.Sphere;

public class TntESP extends Module implements RenderListener, TickListener {
   private final java.util.ArrayList visibleTntEntities;
   private final ColorSetting radiusColor;
   private final BooleanValue deathCircle;
   private final ColorSetting deathCircleColor;
   private final DoubleValue smoothness;
   private final BooleanValue displayDamage;
   private final DoubleValue xCoord;
   private final DoubleValue yCoord;
   private double estimatedTotalDamage;
   private final Frustum frustum;
   private final DamageSource explosionDamageSource;

   public TntESP() {
       super("TntESP", "TntESP", new Color(109, 199, 117), Categorys.RENDER);
      this.visibleTntEntities = new java.util.ArrayList();
      this.radiusColor = new ColorSetting(this, "Color", new Color(255, 255, 255, 40));
      this.deathCircle = new BooleanValue(this, "DeathCircle", true);
      ColorSetting colorSetting = new ColorSetting(this, "DeathColor", new Color(255, 0, 0, 40));
      BooleanValue deathCircle2 = this.deathCircle;
      BooleanValue booleanValue = deathCircle2;
      this.deathCircleColor = (ColorSetting)colorSetting.withVisibility(() -> booleanValue.getValue());
      this.smoothness = new DoubleValue(this, "Smoothness", 32.0, 8.0, 60.0, 0);
      this.displayDamage = new BooleanValue(this, "DisplayDamage", true);
      DoubleValue xCoordSetting = new DoubleValue(this, "X-Coord", 50.0, 0.0, 100.0, 2);
      deathCircle2 = this.displayDamage;
      BooleanValue booleanValue2 = deathCircle2;
      this.xCoord = xCoordSetting.visibleWhen(() -> booleanValue2.getValue());
      DoubleValue yCoordSetting = new DoubleValue(this, "Y-Coord", 45.0, 0.0, 100.0, 2);
      deathCircle2 = this.displayDamage;
      BooleanValue booleanValue3 = deathCircle2;
      this.yCoord = yCoordSetting.visibleWhen(() -> booleanValue3.getValue());
      this.estimatedTotalDamage = 0.0;
      this.frustum = new Frustum();
      this.explosionDamageSource = new DamageSource("explosion");
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onPreTick() {
      this.visibleTntEntities.clear();
      this.estimatedTotalDamage = 0.0;
      if (mc.theWorld != null && mc.thePlayer != null) {
         for (Entity entity : (Iterable<Entity>)(mc.theWorld.loadedEntityList)) {
            if (entity instanceof EntityTNTPrimed) {
               EntityTNTPrimed entityTNTPrimed = (EntityTNTPrimed)entity;
               this.frustum.setPosition(mc.getRenderViewEntity().posX, mc.getRenderViewEntity().posY, mc.getRenderViewEntity().posZ);
               if (this.frustum.isBoundingBoxInFrustum(entity.getEntityBoundingBox().expand(9.0, 9.0, 9.0)) && !this.visibleTntEntities.contains(entity)) {
                  this.visibleTntEntities.add(entityTNTPrimed);
                  this.estimatedTotalDamage = this.estimatedTotalDamage + this.calculateExplosionDamage(entityTNTPrimed);
               }
            }
         }
      }
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      if (this.displayDamage.getValue() && this.estimatedTotalDamage > 0.0) {
         String str = "§cDamage: §7" + NumberFormatUtil.formatDecimal(this.estimatedTotalDamage, 3);
         int n = (int)((double)(sr.getScaledHeight() - mc.fontRendererObj.FONT_HEIGHT) / 100.0 * this.yCoord.getValue());
         int n2 = (int)((double)(sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(str)) / 100.0 * this.xCoord.getValue());
         mc.fontRendererObj.drawString(str, n2, n, Color.white.getRGB());
      }

      RenderUtil.resetGlColorState();
   }

   @Override
   public void onRender3D(float partialTicks) {
      RenderUtil.begin3DRender();
      int value = (int)this.smoothness.getValue();
      int colorInt = this.radiusColor.getColorInt((byte)0);
      int n;
      if (this.deathCircle.getValue()) {
         n = this.deathCircleColor.getColorInt((byte)0);
      } else {
         n = 0;
      }

      int n2 = n;

      for (EntityTNTPrimed entityTNTPrimed : (Iterable<EntityTNTPrimed>)(this.visibleTntEntities)) {
         GlStateManager.pushMatrix();
         float f2 = entityTNTPrimed.height / 16.0F;
         float f3 = (float)(entityTNTPrimed.lastTickPosX + (entityTNTPrimed.posX - entityTNTPrimed.lastTickPosX) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosX());
         float f4 = (float)(
            entityTNTPrimed.lastTickPosY
               + (double)f2
               + (entityTNTPrimed.posY + (double)f2 - (entityTNTPrimed.lastTickPosY + (double)f2)) * (double)mc.getTimer().renderPartialTicks
               - mc.getRenderManager().getRenderPosY()
         );
         GlStateManager.translate(f3, f4, (float)(entityTNTPrimed.lastTickPosZ + (entityTNTPrimed.posZ - entityTNTPrimed.lastTickPosZ) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosZ()));
         Sphere sphere = new Sphere();
         sphere.setDrawStyle(100011);
         Sphere sphere2 = new Sphere();
         sphere2.setDrawStyle(100012);
         if (this.deathCircle.getValue()) {
            RenderUtil.setGlColor(n2);
            float f6 = (float)this.calculateLethalRadius();
            sphere.draw(f6, value, value);
            sphere2.draw(f6, value, value);
         }

         RenderUtil.setGlColor(colorInt);
         sphere.draw(8.0F, value, value);
         sphere2.draw(8.0F, value, value);
         GlStateManager.popMatrix();
      }

      RenderUtil.end3DRender();
   }

   private double calculateLethalRadius() {
      float f = 8.0F;
      if (!mc.thePlayer.isImmuneToExplosions()) {
         for (float i = 0.0F; i < 8.0F; i += 0.1F) {
            double d = (double)i / (double)f;
            if (d <= 1.0) {
               double d2 = (1.0 - d) * 1.0;
               if ((mc.thePlayer.getCurrentHealth() - this.calculateFinalDamage(this.explosionDamageSource.setDifficultyScaled().setExplosion(), (float)((int)((d2 * d2 + d2) / 2.0 * 8.0 * (double)f + 1.0)), true)) > 0.0F) {
                  return (double)i;
               }
            }
         }
      }

      return 0.0;
   }

   public double calculateExplosionDamage(EntityTNTPrimed entityTNTPrimed) {
      float f = 8.0F;
      double d = entityTNTPrimed.posY + (double)(entityTNTPrimed.height / 16.0F);
      if (!mc.thePlayer.isImmuneToExplosions()) {
         double d2 = mc.thePlayer.getDistance(entityTNTPrimed.posX, d, entityTNTPrimed.posZ) / (double)f;
         if (d2 <= 1.0) {
            double d3 = mc.thePlayer.posX - entityTNTPrimed.posX;
            double d4 = mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight() - d;
            double d5 = mc.thePlayer.posZ - entityTNTPrimed.posZ;
            if (((double)MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5)) != 0.0) {
               double blockDensity = (double)mc.theWorld.getBlockDensity(entityTNTPrimed.posX, d, entityTNTPrimed.posZ, mc.thePlayer.getEntityBoundingBox());
               double d7 = (1.0 - d2) * blockDensity;
               return (double)this.calculateFinalDamage(this.explosionDamageSource.setDifficultyScaled().setExplosion(), (float)((int)((d7 * d7 + d7) / 2.0 * 8.0 * (double)f + 1.0)), true);
            }
         }
      }

      return 0.0;
   }

   public float calculateFinalDamage(DamageSource damageSource, float f, boolean flag) {
      if (!damageSource.isUnblockable() && mc.thePlayer.isBlocking() && f > 0.0F) {
         f = (1.0F + f) * 0.5F;
      }

      f = this.applyArmorDamageReduction(damageSource, f, mc.thePlayer);
      f = this.applyPotionAndEnchantmentReduction(damageSource, f, flag);
      return Math.max(f - mc.thePlayer.getAbsorptionAmount(), 0.0F);
   }

   public float applyPotionAndEnchantmentReduction(DamageSource damageSource, float f, boolean flag) {
      if (damageSource.isDamageAbsolute()) {
         return f;
      } else {
         if (mc.thePlayer.isPotionActive(Potion.resistance) && damageSource != DamageSource.outOfWorld) {
            f = (f * (float)(25 - ((mc.thePlayer.getActivePotionEffect(Potion.resistance).getAmplifier() + 1) * 5))) / 25.0F;
         }

         if (f <= 0.0F) {
            return 0.0F;
         } else {
            int n3 = 0;
            EnchantmentHelper.enchantmentModifierDamage.source = damageSource;

            for (int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
               ItemStack stack = mc.thePlayer.inventory.armorInventory[i];
               if (stack != null && stack.getItem() instanceof ItemArmor) {
                  float f3 = 0.0F;
                  if (EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, stack) > 0) {
                     int damageModifier = EnchantmentHelper.enchantmentModifierDamage
                        .getDamageModifier(Enchantment.protection, EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, stack));
                     f3 = (float)(damageModifier + 1 >> 1) + (float)((damageModifier >> 1) + 1) / (flag ? 1.0F : 2.0F);
                  }

                  n3 = (int)((float)n3 + f3);
               }
            }

            if (n3 > 20) {
               n3 = 20;
            }

            if (n3 > 0) {
               f = (f * (float)(25 - n3)) / 25.0F;
            }

            return f;
         }
      }
   }

   private float applyArmorDamageReduction(DamageSource damageSource, float f, EntityPlayer player) {
      if (!damageSource.isUnblockable()) {
         f = (f * (25.0F - this.getTotalArmorPoints())) / 25.0F;
      }

      return f;
   }

   public float getTotalArmorPoints() {
      float f = 0.0F;

      for (int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
         ItemStack stack = mc.thePlayer.inventory.armorInventory[i];
         if (stack != null && stack.getItem() instanceof ItemArmor) {
            float damageReduceAmount = (float)((ItemArmor)stack.getItem()).damageReduceAmount;
            f += damageReduceAmount;
         }
      }

      return f;
   }

}
