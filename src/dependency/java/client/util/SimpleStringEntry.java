package client.util;

import java.util.List;
import net.minecraft.client.Minecraft;

public abstract class SimpleStringEntry {
   public final String labelText;

   public int getCount() {
      int count = Minecraft.getMinecraft().fontRendererObj.indexOffset + 1;
      return 0 + count * 2 + count + this.asList2().size() * count + count + count + this.asList().size() * count;
   }

   public abstract List asList2();

   public String getLabelText() {
      return this.labelText;
   }

   public abstract List asList();

   public SimpleStringEntry(String text) {
      this.labelText = text;
   }
}
