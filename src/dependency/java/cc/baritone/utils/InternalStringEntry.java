package cc.baritone.utils;

import net.minecraft.util.StatCollector;

public class InternalStringEntry extends CORERECOMMENDEDManager {
   public final String labelText2;

   @Override
   public String getText() {
      return StatCollector.getText(this.labelText2, new Object[0]);
   }

   public InternalStringEntry(String text, String text2) {
      super(text);
      this.labelText2 = text2;
   }
}
