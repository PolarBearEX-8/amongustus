package net.optifine.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.optifine.shaders.gui.GuiButtonShaderOption;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.settings.GameSettings;
import net.optifine.Lang;
import net.optifine.config.Config;
import net.optifine.shaders.config.ShaderOption;
import net.optifine.util.StrUtils;

public class TooltipProviderShaderOptions extends TooltipProviderOptions {

   private String[] makeTooltipLines(int n, List list) {
      FontRenderer fontRendererObj = Config.getMc().fontRendererObj;
      ArrayList list2 = new ArrayList();

      for (int i = 0; i < list.size(); i++) {
         String str = (String)list.get(i);
         if (str != null && str.length() > 0) {
            for (String str2 : (Iterable<String>)(fontRendererObj.listFormattedStringToWidth(str, n))) {
               list2.add(str2);
            }
         }
      }

      return (String[])list2.toArray(new String[list2.size()]);
   }

   @Override
   public String[] getTooltipLines(GuiButton button, int n) {
      if (!(button instanceof GuiButtonShaderOption)) {
         return null;
      } else {
         return this.makeTooltipLines2(((GuiButtonShaderOption)button).getShaderOption(), n);
      }
   }

   private String[] makeTooltipLines2(ShaderOption shaderOption, int n) {
      String nameText = shaderOption.getNameText();
      String[] strings = this.splitDescription(Config.normalize(shaderOption.getDescriptionText()).trim());
      GameSettings gameSettings = Config.getGameSettings();
      String str2 = null;
      if (!nameText.equals(shaderOption.getName()) && gameSettings.advancedItemTooltips) {
         str2 = "§8" + Lang.get("of.general.id") + ": " + shaderOption.getName();
      }

      String str3 = null;
      if (shaderOption.getPaths() != null && gameSettings.advancedItemTooltips) {
         str3 = "§8" + Lang.get("of.general.from") + ": " + Config.arrayToString3(shaderOption.getPaths());
      }

      String str4 = null;
      if (shaderOption.getValueDefault() != null && gameSettings.advancedItemTooltips) {
         String str5 = shaderOption.isEnabled() ? shaderOption.getValueText(shaderOption.getValueDefault()) : Lang.get("of.general.ambiguous");
         str4 = "§8" + Lang.getDefault() + ": " + str5;
      }

      ArrayList list = new ArrayList();
      list.add(nameText);
      list.addAll(Arrays.asList(strings));
      if (str2 != null) {
         list.add(str2);
      }

      if (str3 != null) {
         list.add(str3);
      }

      if (str4 != null) {
         list.add(str4);
      }

      return this.makeTooltipLines(n, list);
   }

   private String[] splitDescription(String str) {
      if (str.length() <= 0) {
         return new String[0];
      } else {
         str = StrUtils.removePrefix(str, "//");
         String[] strings = str.split("\\. ");

         for (int i = 0; i < strings.length; i++) {
            strings[i] = "- " + strings[i].trim();
            strings[i] = StrUtils.removeSuffix2(strings[i], ".");
         }

         return strings;
      }
   }
}
