package client.util;

import com.google.common.base.Predicate;
import com.google.common.primitives.Floats;
import net.minecraft.client.gui.OptionsCustomizeTitleGuiScreen;

public class OptionsCustomizeTitlFilter implements Predicate {
   public final OptionsCustomizeTitleGuiScreen optionsCustomizeTitleGuiScreen;

   public OptionsCustomizeTitlFilter(OptionsCustomizeTitleGuiScreen optionsCustomizeTitleGuiScreen2) {
      this.optionsCustomizeTitleGuiScreen = optionsCustomizeTitleGuiScreen2;
   }

   public boolean matchesText(String text) {
      Float factor = Floats.tryParse(text);
      return text.length() == 0 || factor != null && Floats.isFinite(factor) && factor >= 0.0F;
   }

   public boolean apply(Object entry) {
      return this.matchesText((String)entry);
   }
}
