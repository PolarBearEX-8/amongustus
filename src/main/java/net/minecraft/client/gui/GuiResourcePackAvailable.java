package net.minecraft.client.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class GuiResourcePackAvailable extends GuiResourcePackList {
   public GuiResourcePackAvailable(Minecraft mc2, int n, int n2, List list2) {
      super(mc2, n, n2, list2);
   }

   @Override
   public String getListHeader() {
      return I18n.format("resourcePack.available.title", new Object[0]);
   }
}
