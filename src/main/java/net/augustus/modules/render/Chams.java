package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.ColorSetting;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.settings.TargetSelectionSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class Chams extends Module {
   private final TargetSelectionSettings entities;
   public final BooleanValue coloredEntityChams;
   public final BooleanValue onlyThroughWallsEntities;
   public final BooleanValue renderTextureEntity;
   public final ColorSetting entityColor;
   public final BooleanValue chests;
   public final BooleanValue coloredChestChams;
   public final BooleanValue onlyThroughWalls;
   public final BooleanValue renderTexture;
   public final ColorSetting chestColor;
   public final ColorSetting enderChestColor;

   public Chams() {
       super("Chams", "Röntgenblick", new Color(91, 152, 222), Categorys.RENDER);
      this.entities = new TargetSelectionSettings(this, TargetFilterPresets.createRenderFilter()).withTargetsDisplayName("Entities");
      this.coloredEntityChams = new BooleanValue(this, "ColoredEntityChams", true);
      BooleanValue booleanValue = new BooleanValue(this, "OnlyThroughWallsEntities", false);
      BooleanValue coloredEntityChams2 = this.coloredEntityChams;
      BooleanValue booleanValue2 = coloredEntityChams2;
      this.onlyThroughWallsEntities = (BooleanValue)booleanValue.withVisibility(() -> booleanValue2.getValue());
      BooleanValue booleanValue3 = new BooleanValue(this, "RenderTextureEntity", false);
      SettingVisibilityCondition condition2 = () -> {
         return this.coloredEntityChams.getValue() && !this.onlyThroughWallsEntities.getValue();
      };
      this.renderTextureEntity = (BooleanValue)booleanValue3.withVisibility(condition2);
      ColorSetting colorSetting = new ColorSetting(this, "ColorEntity", new Color(0, 233, 255, 100));
      SettingVisibilityCondition condition3 = () -> {
         return this.coloredEntityChams.getValue();
      };
      this.entityColor = (ColorSetting)colorSetting.withVisibility(condition3);
      this.chests = new BooleanValue(this, "Chests", false);
      BooleanValue booleanValue4 = new BooleanValue(this, "ColoredChestChams", true);
      coloredEntityChams2 = this.chests;
      BooleanValue booleanValue5 = coloredEntityChams2;
      this.coloredChestChams = (BooleanValue)booleanValue4.withVisibility(() -> booleanValue5.getValue());
      this.onlyThroughWalls = (BooleanValue)new BooleanValue(this, "OnlyThroughWalls", false).withVisibility(() -> this.isChestOnlyThroughWallsVisible());
      this.renderTexture = (BooleanValue)new BooleanValue(this, "RenderTexture", false).withVisibility(() -> this.isChestRenderTextureVisible());
      ColorSetting colorSetting2 = new ColorSetting(this, "ColorChest", new Color(0, 233, 255, 100));
      SettingVisibilityCondition condition7 = () -> {
         return this.coloredChestChams.isVisible() && this.coloredChestChams.getValue();
      };
      this.chestColor = (ColorSetting)colorSetting2.withVisibility(condition7);
      this.enderChestColor = (ColorSetting)new ColorSetting(this, "ColorEnderChest", new Color(0, 233, 255, 100)).withVisibility(() -> this.isEnderChestColorVisible());
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
   }

   public void beginEntityChams(Entity entity) {
      if (!this.coloredEntityChams.getValue() && this.shouldRenderEntityChams(entity)) {
         GL11.glEnable(32823);
         GL11.glPolygonOffset(1.0F, -1100000.0F);
      }
   }

   public void endEntityChams() {
      if (!this.coloredEntityChams.getValue() && GL11.glIsEnabled(32823)) {
         GL11.glDisable(32823);
         GL11.glPolygonOffset(1.0F, 1100000.0F);
      }
   }

   public boolean shouldRenderEntityChams(Entity entity) {
      if (this.entities.getTargetFilter().matches(entity)) {
         if (true) {
            return true;
         }
      }

      return false;
   }

   private boolean isEnderChestColorVisible() {
      return this.coloredChestChams.isVisible() && this.coloredChestChams.getValue();
   }

   private boolean isChestRenderTextureVisible() {
      return this.coloredChestChams.isVisible() && this.coloredChestChams.getValue() && !this.onlyThroughWalls.getValue();
   }

   private boolean isChestOnlyThroughWallsVisible() {
      return this.coloredChestChams.isVisible() && this.coloredChestChams.getValue();
   }



}
