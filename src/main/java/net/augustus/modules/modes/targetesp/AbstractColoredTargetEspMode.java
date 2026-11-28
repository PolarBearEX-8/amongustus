package net.augustus.modules.modes.targetesp;

import net.augustus.settings.ColorSetting;
import net.augustus.settings.SettingVisibilityCondition;
import java.awt.Color;
import net.augustus.modules.render.TargetESP;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.minecraft.entity.EntityLivingBase;

public abstract class AbstractColoredTargetEspMode extends AbstractTargetEspMode {
   private final ColorSetting color;
   private final BooleanValue hitColorBoolean;
   private final ColorSetting hitColor;

   public AbstractColoredTargetEspMode(TargetESP targetESP, SettingGroup doubleValue, String str) {
       super(targetESP, doubleValue, str);
      String str2 = str + "Color";
      Color color2 = new Color(41, 146, 222, 120);
      String str3 = str2;
      ColorSetting colorSetting = new ColorSetting(targetESP, str3, color2, doubleValue);
      colorSetting = (ColorSetting)colorSetting.withDisplayName("Color");
      this.color = (ColorSetting)colorSetting.withVisibility(() -> this.isSelected());
      this.hitColorBoolean = (BooleanValue)((BooleanValue)new BooleanValue(targetESP, str + "HitColorBoolean", true, doubleValue).withDisplayName("Hit color")).withVisibility(() -> this.isSelected());
      String str6 = str + "HitColor";
      color2 = new Color(255, 0, 0, 120);
      str3 = str6;
      this.hitColor = (ColorSetting)((ColorSetting)new ColorSetting(targetESP, str3, color2, doubleValue).withDisplayName("Hit color")).withVisibility(() -> this.isHitColorVisible());
   }

   public float getInterpolatedRenderX(EntityLivingBase entity) {
      return (float)(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosX());
   }

   public float getInterpolatedRenderY(EntityLivingBase entity) {
      return (float)(entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosY());
   }

   public float getInterpolatedRenderZ(EntityLivingBase entity) {
      return (float)(entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosZ());
   }

   public int getTargetColor(EntityLivingBase entity) {
      int n;
      if (this.hitColorBoolean.getValue() && entity.hurtTime != 0) {
         n = this.hitColor.getColorInt((byte)0);
      } else {
         n = this.color.getColorInt((byte)0);
      }

      return n;
   }

   private boolean isHitColorVisible() {
      return this.isSelected() && this.hitColorBoolean.getValue();
   }

}
