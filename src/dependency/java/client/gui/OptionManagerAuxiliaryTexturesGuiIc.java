package client.gui;

import client.setting.OptionManager;

public class OptionManagerAuxiliaryTexturesGuiIc extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public OptionManager optionManager = null;

   public boolean isActive() {
      return true;
   }

   public OptionManager getOptionManager() {
      return this.optionManager;
   }

   public OptionManagerAuxiliaryTexturesGuiIc(int count, int count2, int count3, int count4, int count5, OptionManager optionManager2, String text) {
      super(count, count2, count3, count4, count5, text);
      this.optionManager = optionManager2;
   }

   public void renderElement() {
   }
}
