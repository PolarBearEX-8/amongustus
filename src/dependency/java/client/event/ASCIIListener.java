package client.event;

import client.util.AngleBuilder;
import client.util.BuildManager;
import client.util.UsersManager;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.minecraft.item.SpecialItemArrayEntry;

public class ASCIIListener implements PostMotionConsumer {
   public String labelText = null;

   @Override
   public void applyText(String text, byte[] byteValueArray, Throwable throwable) {
      if (byteValueArray != null) {
         try {
            String text2 = new String(byteValueArray, "ASCII");
            JsonElement jsonElement = new JsonParser().parse(text2);
            SpecialItemArrayEntry specialItemArrayEntry = new AngleBuilder(this.labelText).resolveSpecialItemArrayEntry(jsonElement);
            if (specialItemArrayEntry != null) {
               specialItemArrayEntry.setEnabled(true);
               UsersManager.applyText(this.labelText, specialItemArrayEntry);
            }
         } catch (Exception exception) {
            BuildManager.applyText4("Error parsing configuration: " + text + ", " + exception.getClass().getName() + ": " + exception.getMessage());
         }
      }
   }

   public ASCIIListener(String text) {
      this.labelText = text;
   }
}
