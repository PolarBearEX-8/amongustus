package client.util;

import client.event.ModularListener;
import client.event.PreRenderNotifier;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import java.awt.Rectangle;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;

public class TooltipManager implements PreRenderNotifier {
   @Override
   public String[] resolveTextArray(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter, int count) {
      return !(auxiliaryTexturesGuiIconsPngManagerAdapter instanceof ModularListener) ? null : resolveTextArray(((ModularListener)auxiliaryTexturesGuiIconsPngManagerAdapter).getFactoryOptionType2().getLabelText());
   }

   public static String[] resolveTextArray(String text) {
      ArrayList list = new ArrayList();

      for (int index = 0; index < 10; index++) {
         String text2 = GeneratorDefaultManager.resolveText(text + ".tooltip." + (index + 1), (String)null);
         if (text2 == null) {
            break;
         }

         list.add(text2);
      }

      return list.size() <= 0 ? null : (String[])list.toArray(new String[list.size()]);
   }

   @Override
   public Rectangle resolveRectangle(GuiScreen screen, int count, int count2) {
      int count3 = screen.width / 2 - 150;
      int count4 = screen.height / 6 - 7;
      if (count2 <= count4 + 98) {
         count4 += 105;
      }

      int count5 = count3 + 150 + 150;
      int count6 = count4 + 84 + 10;
      return new Rectangle(count3, count4, count5 - count3, count6 - count4);
   }

   @Override
   public boolean isActive() {
      return false;
   }
}
