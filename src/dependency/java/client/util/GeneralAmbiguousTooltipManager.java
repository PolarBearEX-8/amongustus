package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.OptionManagerAuxiliaryTexturesGuiIc;
import client.setting.GameSettings;
import client.setting.OptionManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;

public class GeneralAmbiguousTooltipManager extends TooltipManager {
   private String[] resolveTextArrayByText(String text) {
      if (text.length() <= 0) {
         return new String[0];
      } else {
         String text2 = SplineCollector.resolveText2(text, "//");
         String[] textArray = text2.split("\\. ");

         for (int index = 0; index < textArray.length; index++) {
            textArray[index] = "- " + textArray[index].trim();
            textArray[index] = SplineCollector.resolveText3(textArray[index], ".");
         }

         return textArray;
      }
   }

   @Override
   public String[] resolveTextArray(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter, int count) {
      if (!(auxiliaryTexturesGuiIconsPngManagerAdapter instanceof OptionManagerAuxiliaryTexturesGuiIc)) {
         return null;
      } else {
         OptionManager optionManager = ((OptionManagerAuxiliaryTexturesGuiIc)auxiliaryTexturesGuiIconsPngManagerAdapter).getOptionManager();
         return this.resolveTextArray(optionManager, count);
      }
   }

   private String[] resolveTextArray(OptionManager optionManager, int count) {
      String textAsText = optionManager.getTextAsText();
      String text = BuildManager.resolveTextByText(optionManager.getText()).trim();
      String[] textArray = this.resolveTextArrayByText(text);
      GameSettings gameSettings = BuildManager.getGameSettings();
      String text2 = null;
      if (!textAsText.equals(optionManager.getLabelText()) && gameSettings.state136) {
         text2 = "§8" + GeneratorDefaultManager.resolveText("of.general.id") + ": " + optionManager.getLabelText();
      }

      String text3 = null;
      if (optionManager.getStringArray2() != null && gameSettings.state136) {
         text3 = "§8" + GeneratorDefaultManager.resolveText("of.general.from") + ": " + BuildManager.resolveText(optionManager.getStringArray2());
      }

      String text4 = null;
      if (optionManager.getLabelText7() != null && gameSettings.state136) {
         String text5 = optionManager.isEnabled() ? optionManager.resolveTextByText(optionManager.getLabelText7()) : GeneratorDefaultManager.resolveText("of.general.ambiguous");
         text4 = "§8" + GeneratorDefaultManager.getText3() + ": " + text5;
      }

      ArrayList list = new ArrayList();
      list.add(textAsText);
      list.addAll(Arrays.asList(textArray));
      if (text2 != null) {
         list.add(text2);
      }

      if (text3 != null) {
         list.add(text3);
      }

      if (text4 != null) {
         list.add(text4);
      }

      return this.resolveTextArray(count, list);
   }

   private String[] resolveTextArray(int count, List list) {
      FontRenderer fontRendererObj = BuildManager.getMc().fontRendererObj;
      ArrayList list2 = new ArrayList();

      for (int index = 0; index < list.size(); index++) {
         String text = (String)list.get(index);
         if (text != null && text.length() > 0) {
            for (String text2 : (Iterable<String>)(fontRendererObj.getList(text, count))) {
               list2.add(text2);
            }
         }
      }

      return ((String[])list2.toArray(new String[list2.size()]));
   }
}
