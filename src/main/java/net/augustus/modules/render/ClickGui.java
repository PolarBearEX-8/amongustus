package net.augustus.modules.render;

import java.awt.Color;
import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.font.CustomFontUtil;
import net.augustus.ClientManagers;
import net.augustus.settings.ColorSetting;
import net.augustus.settings.SettingNameManager;
import net.augustus.events.listeners.DownloadTerrainListener;
import net.augustus.config.ConfigManager;
import net.augustus.Augustus;
import net.augustus.clickgui.ClickGuiScreen;
import net.augustus.settings.SettingChangeListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingDescriptionManager;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;

public class ClickGui extends Module implements DownloadTerrainListener, TickListener {
   public final StringValue sorting;
   public BooleanValue germanMode;
   public final BooleanValue openInInventory;
   public final BlurShadowSettings clickGui;
   public final BooleanValue outline;
   public final DoubleValue alpha;
   public final DoubleValue dynamicKeyTime;
   public final StringValue descriptions;
   public final StringValue settingNames;
   public final BooleanValue moduleDescriptions;
   public final BooleanValue settingDescriptions;
   public final SettingGroup colors;
   public final ColorSetting backgroundColor;
   public final ColorSetting outlineColor;
   public final ColorSetting headerColor;
   public final ColorSetting textColor;
   public final ColorSetting hoverTextColor;
   public final ColorSetting enabledColor;
   public final ColorSetting disabledColor;

   public ClickGui() {
       super("ClickGui", "KlickbareGrafischeBenutzeroberfläche", Color.BLACK, Categorys.RENDER);
      StringValue setting = new StringValue(this, "Sorting", "Alphabet", new String[]{"Random", "Length", "Alphabet"});
      SettingChangeListener listener = () -> this.sortCategoryModules();
      this.sorting = (StringValue)setting.withChangeListener(listener);
      BooleanValue booleanValue = new BooleanValue(this, "GermanMode", false);
      listener = () -> {

         for (Module module : (Iterable<Module>)(ClientManagers.moduleManager.getModules())) {
            module.setName(this.germanMode.getValue() ? module.getGermanName() : module.getName());
         }
      };
      this.germanMode = (BooleanValue)booleanValue.withChangeListener(listener);
      this.openInInventory = new BooleanValue(this, "OpenInInventory", true);
      this.clickGui = new BlurShadowSettings(this, "ClickGui").withCustomShadowColor(Color.black);
      this.outline = new BooleanValue(this, "Outline", true);
      this.alpha = new DoubleValue(this, "Alpha", 200.0, 0.0, 255.0, 0);
      this.dynamicKeyTime = new DoubleValue(this, "DynamicKeyTime", 500.0, 0.0, 2000.0, 0);
      StringValue setting2 = new StringValue(this, "Descriptions", "Default", new String[]{"Default"});
      listener = () -> this.reloadSettingDescriptionsAsync();
      this.descriptions = (StringValue)setting2.withChangeListener(listener);
      StringValue setting3 = new StringValue(this, "SettingNames", "English_Default", new String[]{"English_Default", "English_New"});
      listener = () -> {
         CompletableFuture.runAsync(() -> this.reloadSettingNames());
      };
      this.settingNames = (StringValue)setting3.withChangeListener(listener);
      this.moduleDescriptions = new BooleanValue(this, "ModuleDescriptions", true);
      this.settingDescriptions = new BooleanValue(this, "SettingDescriptions", true);
      this.colors = new SettingGroup(this, "Colors");
      Color color2 = new Color(40, 39, 39, 200);
      SettingGroup colors2 = this.colors;
      Color color3 = color2;
      String str2 = "BackgroundColor";
      this.backgroundColor = new ColorSetting(this, str2, color3, colors2);
      color2 = new Color(40, 39, 39);
      colors2 = this.colors;
      color3 = color2;
      str2 = "OutlineColor";
      this.outlineColor = new ColorSetting(this, str2, color3, colors2);
      color2 = new Color(34, 34, 34);
      colors2 = this.colors;
      color3 = color2;
      str2 = "HeaderColor";
      this.headerColor = new ColorSetting(this, str2, color3, colors2);
      color2 = new Color(185, 187, 190);
      colors2 = this.colors;
      color3 = color2;
      str2 = "TextColor";
      this.textColor = new ColorSetting(this, str2, color3, colors2);
      color2 = new Color(41, 146, 222);
      colors2 = this.colors;
      color3 = color2;
      str2 = "TextColorHover";
      this.hoverTextColor = new ColorSetting(this, str2, color3, colors2).withClientColorDefault(true).withDefaultClientColorAlpha((short)255);
      color2 = new Color(0, 214, 0);
      colors2 = this.colors;
      color3 = color2;
      str2 = "TrueColor";
      this.enabledColor = new ColorSetting(this, str2, color3, colors2);
      color2 = new Color(214, 0, 0);
      colors2 = this.colors;
      color3 = color2;
      str2 = "FalseColor";
      this.disabledColor = new ColorSetting(this, str2, color3, colors2);
      this.setKeyCode((byte)54);
      this.setToggleSoundEnabled(false);
   }

   @Override
   public void onEnable() {
      if (mc.currentScreen instanceof GuiContainer && !this.openInInventory.getValue()) {
         this.toggle();
      } else {
         Augustus.getInstance().getConfigManager().snapshotAll();
         Augustus.getInstance().getDiscordRpcManager().start();
         if (mc.thePlayer != null && mc.theWorld != null) {
            if (mc.currentScreen != null) {
               mc.thePlayer.closeScreen();
            }

            mc.displayGuiScreen(Augustus.getInstance().getClickGui());
         }
      }
   }

   @Override
   public void onDisable() {
      Augustus.getInstance().getConfigManager().snapshotAll();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(8);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onTick() {
      if (!(mc.currentScreen instanceof ClickGuiScreen) && this.isToggled()) {
         this.toggle();
      }
   }

   @Override
   public void onDownloadTerrain() {

      for (Module module : (Iterable<Module>)(ClientManagers.moduleManager.getModules())) {
         module.setName(this.germanMode.getValue() ? module.getGermanName() : module.getName());
      }
   }

   private void sortCategoryModules() {
      switch (this.sorting.getSelectedValue()) {
         case "Random":
            for (Categorys categorys2 : Categorys.values()) {
               if (!categorys2.hidden) {
                  categorys2.getModules().clear();
                  categorys2.getModules().addAll(categorys2.getDisplayModules());
               }
            }
            break;
         case "Alphabet":
            for (Categorys categorys3 : Categorys.values()) {
               if (!categorys3.hidden) {
                  categorys3.getModules().sort(Comparator.comparing(Module::getModuleName));
               }
            }
            break;
         case "Length":
            for (Categorys categorys4 : Categorys.values()) {
               if (!categorys4.hidden) {
                  categorys4.getModules().sort(Comparator.comparingDouble(obj -> getModuleNameLengthSortKey((Module)obj)));
               }
            }
      }
   }

   private static double getModuleNameLengthSortKey(Module module) {
      CustomFontUtil moduleFont = Augustus.getInstance().getClickGui().getMainWindow().getModuleFont();
      String str = module.getModuleName();
      return (double)(1000.0F - moduleFont.getStringWidth(str));
   }

   private void reloadSettingNames() {
      Augustus.getInstance().getSettingNameManager().loadLanguageFile(this.settingNames.getSelectedValue());
   }

   private void reloadSettingDescriptionsAsync() {
      CompletableFuture.runAsync(() -> this.reloadSettingDescriptions());
   }

   private void reloadSettingDescriptions() {
      Augustus.getInstance().getSettingDescriptionManager().loadDescriptions(this.descriptions.getSelectedValue());
   }




}
