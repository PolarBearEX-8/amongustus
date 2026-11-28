package net.augustus.modules.render;

import java.awt.Color;
import java.util.Arrays;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.options.CustomGlintRemoveItemOption;
import net.augustus.events.listeners.RenderListener;
import net.augustus.events.EventItemGlint;
import net.augustus.settings.options.CustomGlintFakeEnchantOption;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.events.listeners.ItemGlintListener;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;

public class CustomGlint extends Module implements ItemGlintListener, RenderListener {
   public final BooleanValue remove;
   public final MultiOptionSetting itemsToRemove;
   public final BooleanValue brighterEnchantments;
   public final BooleanValue customColor;
   public final ColorSetting color2;
   private final MultiOptionSetting fakeEnchant;
   public final DoubleValue speed;
   private int glintColorIndex;

   public CustomGlint() {
       super("CustomGlint", "KundenspezifischesGlitzern", new Color(128, 64, 204), Categorys.RENDER);
      this.remove = new BooleanValue(this, "Remove", false);
      MultiOptionSetting multiOptionSetting = new MultiOptionSetting(this, "ItemsToRemove", CustomGlintRemoveItemOption.values());
      BooleanValue remove2 = this.remove;
      this.itemsToRemove = multiOptionSetting.visibleWhen(() -> remove2.getValue());
      BooleanValue booleanValue2 = new BooleanValue(this, "BrighterEnchantments", false);
      SettingVisibilityCondition condition2 = () -> {
         return !this.remove.getValue() || Arrays.stream(CustomGlintRemoveItemOption.values()).anyMatch(option -> {
            return !option.isSelected();
         });
      };
      this.brighterEnchantments = (BooleanValue)booleanValue2.withVisibility(condition2);
      BooleanValue booleanValue3 = new BooleanValue(this, "CustomColor", false);
      SettingVisibilityCondition condition3 = () -> {
         return !this.remove.getValue() || Arrays.stream(CustomGlintRemoveItemOption.values()).anyMatch(option2 -> {
            return !option2.isSelected();
         });
      };
      this.customColor = (BooleanValue)booleanValue3.withVisibility(condition3);
      this.color2 = (ColorSetting)new ColorSetting(this, "Color", new Color(128, 64, 204)).withVisibility(() -> this.isColorSettingVisible());
      MultiOptionSetting multiOptionSetting2 = new MultiOptionSetting(this, "FakeEnchant", CustomGlintFakeEnchantOption.values());
      SettingVisibilityCondition condition5 = () -> {
         return !this.remove.getValue() || Arrays.stream(CustomGlintRemoveItemOption.values()).anyMatch(option3 -> {
            return !option3.isSelected();
         });
      };
      this.fakeEnchant = multiOptionSetting2.visibleWhen(condition5);
      DoubleValue speedSetting = new DoubleValue(this, "Speed", 1.0, 0.01, 10.0, 2);
      SettingVisibilityCondition condition6 = () -> {
         return !this.remove.getValue() || Arrays.stream(CustomGlintRemoveItemOption.values()).anyMatch(option4 -> {
            return !option4.isSelected();
         });
      };
      this.speed = speedSetting.visibleWhen(condition6);
      this.glintColorIndex = 0;
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(12);
      this.toggleEventSubscription(32);
   }

   @Override
   public void onRender3D(float partialTicks) {
      this.glintColorIndex = 0;
   }

   @Override
   public void onItemGlint(EventItemGlint event) {
      if (this.customColor.getValue()) {
         int glintColorIndex2 = this.glintColorIndex;
         event.setColor(this.color2.getColorInt(glintColorIndex2));
         this.glintColorIndex++;
      }

      if (this.remove.getValue() && CustomGlintRemoveItemOption.shouldRemoveGlint(event.getItemStack())) {
         event.cancel();
      }

      event.setSpeed(this.speed.getValue());
   }

   private boolean isColorSettingVisible() {
      return this.customColor.getValue() && this.customColor.isVisible();
   }


}
