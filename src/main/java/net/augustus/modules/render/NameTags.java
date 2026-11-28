package net.augustus.modules.render;

import java.awt.Color;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.settings.ActionSetting;
import net.augustus.font.FontManager;
import net.augustus.font.FormattedUnicodeFontRenderer;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.settings.SettingChangeListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

public class NameTags extends Module {
   public final StringValue mode;
   public final DoubleValue scale;
   public final DoubleValue height;
   public final BooleanValue outline;
   public final BooleanValue armor;
   public final BooleanValue armorDurability;
   public final BooleanValue armorEnchantments;
   public final BooleanValue potionEffects;
   public final BooleanValue showExtraServerNametag;
   public final StringValue healthMode;
   public final BooleanValue ping;
   public final BooleanValue throughWalls;
   public final BooleanValue ignoreTeammates;
   public final BooleanValue myself;
   public final BooleanValue armorStands;
   public final BooleanValue invisible;
   public final BooleanValue invisibleTeam;
   public final BooleanValue transparent;
   public final BooleanValue shadow;
   public final BooleanValue shadowFont;
   public final BooleanValue blur;
   public final StringValue font;
   public final ActionSetting openCustomFontFolder;
   public final DoubleValue maximumRenderDistance;
   public HashMap enchantmentAbbreviations;
   public FormattedUnicodeFontRenderer customFontRenderer;

   public NameTags() {
       super("NameTags", "Namensschilder", new Color(21, 35, 81), Categorys.RENDER);
      this.mode = new StringValue(this, "Mode", "Custom", new String[]{"None", "Vanilla", "Custom"});
      this.scale = new DoubleValue(this, "Scale", 1.2, 0.0, 3.0, 2).visibleWhen(() -> this.isScaleVisible());
      this.height = new DoubleValue(this, "Height", 0.0, -3.0, 3.0, 2).visibleWhen(() -> this.isHeightVisible());
      this.outline = (BooleanValue)new BooleanValue(this, "Outline", true).withVisibility(() -> this.isOutlineVisible());
      this.armor = (BooleanValue)new BooleanValue(this, "Armor", false).withVisibility(() -> this.isArmorVisible());
      this.armorDurability = (BooleanValue)new BooleanValue(this, "ArmorDurability", false).withVisibility(() -> this.isArmorDurabilityVisible());
      this.armorEnchantments = (BooleanValue)new BooleanValue(this, "ArmorEnchantments", false).withVisibility(() -> this.isArmorEnchantmentsVisible());
      this.potionEffects = new BooleanValue(this, "PotionEffects", false);
      this.showExtraServerNametag = new BooleanValue(this, "ShowExtraServerNametag", false);
      this.healthMode = (StringValue)new StringValue(this, "HealthMode", "None", new String[]{"None", "Entity", "TabList", "NameTags"}).withVisibility(() -> this.isHealthModeVisible());
      this.ping = new BooleanValue(this, "Ping", false);
      this.throughWalls = new BooleanValue(this, "ThroughWalls", true);
      this.ignoreTeammates = new BooleanValue(this, "IgnoreTeammates", false);
      this.myself = new BooleanValue(this, "Myself", true);
      this.armorStands = new BooleanValue(this, "ArmorStands", false);
      this.invisible = new BooleanValue(this, "Invisible", true);
      this.invisibleTeam = new BooleanValue(this, "InvisibleTeam", true);
      this.transparent = new BooleanValue(this, "Transparent", false);
      this.shadow = new BooleanValue(this, "Shadow", true);
      BooleanValue booleanValue5 = new BooleanValue(this, "ShadowFont", true);
      SettingVisibilityCondition condition8 = () -> {
         return this.shadow.getValue() && this.transparent.getValue();
      };
      this.shadowFont = (BooleanValue)booleanValue5.withVisibility(condition8);
      this.blur = (BooleanValue)new BooleanValue(this, "Blur", true).withVisibility(() -> this.isBlurVisible());
      this.font = (StringValue)((StringValue)new StringValue(this, "Font", "Minecraft", new String[]{"Minecraft"}).withChangeListener(() -> this.updateSelectedFont())).withVisibility(() -> this.isFontVisible());
      this.openCustomFontFolder = new ActionSetting(this, "Open custom font folder", () -> openCustomFontFolder());
      this.maximumRenderDistance = new DoubleValue(this, "MaximumRenderDistance", 500.0, 0.0, 500.0, 0);
      this.enchantmentAbbreviations = new HashMap();
   }

   @Override
   public void onEnable() {
      int[] ints = new int[]{
         Enchantment.protection.effectId,
         Enchantment.sharpness.effectId,
         Enchantment.unbreaking.effectId,
         Enchantment.knockback.effectId,
         Enchantment.power.effectId,
         Enchantment.punch.effectId,
         Enchantment.fireAspect.effectId,
         Enchantment.thorns.effectId,
         Enchantment.fireProtection.effectId,
         Enchantment.flame.effectId,
         Enchantment.infinity.effectId
      };
      this.enchantmentAbbreviations.clear();

      for (int n : ints) {
         String str = StatCollector.translateToLocal(Enchantment.getEnchantmentById(n).getName());

         for (int i = 1; i <= str.length(); i++) {
            String str2 = str.substring(0, i).toLowerCase();
            if (!this.enchantmentAbbreviations.containsValue(str2)) {
               this.enchantmentAbbreviations.put(n, str2);
               break;
            }
         }
      }

      FontManager.reloadCustomFonts();
      this.refreshAvailableFonts();
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
   }

   public float getPlayerHealth(EntityPlayer player) {
      return PlayerUtil.getPlayerHealthByMode(this.healthMode, player);
   }

   public int getPlayerPing(AbstractClientPlayer clientPlayer) {
      return clientPlayer.getPlayerInfo() == null ? -1 : clientPlayer.getPlayerInfo().getResponseTime();
   }

   private void refreshAvailableFonts() {
      HashSet set = new HashSet<>(Arrays.asList(this.font.getOptions()));
      set.addAll(new java.util.ArrayList(FontManager.customDynamicFonts.keySet()));
      java.util.ArrayList list2 = new java.util.ArrayList(set);
      Collections.sort(list2);
      this.font.setOptions((String[])list2.toArray(new String[0]));
      this.customFontRenderer = (FormattedUnicodeFontRenderer)FontManager.customFormattedFonts.get(this.font.getSelectedValue());
   }

   private void updateSelectedFont() {
      this.customFontRenderer = (FormattedUnicodeFontRenderer)FontManager.customFormattedFonts.get(this.font.getSelectedValue());
   }

   public boolean isRenderableEntity(Entity entity) {
      return entity instanceof EntityPlayer || this.armorStands.getValue() && entity instanceof EntityArmorStand;
   }

   private static void openCustomFontFolder() {
      ClientFileUtil.openFolder(FontManager.CLIENT_DATA_DIR + File.separator + "fonts");
   }

   private boolean isFontVisible() {
      return this.mode.getSelectedValue().equals("Custom");
   }

   private boolean isBlurVisible() {
      return this.mode.getSelectedValue().equals("Custom");
   }

   private boolean isHealthModeVisible() {
      return !this.mode.getSelectedValue().equals("None");
   }

   private boolean isArmorEnchantmentsVisible() {
      return this.armor.isVisible() && this.armor.getValue();
   }

   private boolean isArmorDurabilityVisible() {
      return this.armor.isVisible() && this.armor.getValue();
   }

   private boolean isArmorVisible() {
      return !this.mode.getSelectedValue().equals("None");
   }

   private boolean isOutlineVisible() {
      return this.mode.getSelectedValue().equals("Custom");
   }

   private boolean isHeightVisible() {
      return !this.mode.getSelectedValue().equals("None");
   }

   private boolean isScaleVisible() {
      return !this.mode.getSelectedValue().equals("None");
   }


}
