package client.util;

import client.event.ASCIIListener;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.SpecialItemArrayEntry;

public class UsersManager {
   public static Map map = null;
   public static boolean enabled = Boolean.getBoolean("player.models.reload");
   public static long timestamp = System.currentTimeMillis();

   public static synchronized void applyText(String text, SpecialItemArrayEntry specialItemArrayEntry) {
      getMap().put(text, specialItemArrayEntry);
   }

   public static synchronized SpecialItemArrayEntry resolveSpecialItemArrayEntryByAbstractClientPl(AbstractClientPlayer player) {
      if (enabled && System.currentTimeMillis() > timestamp + 5000L) {
         EntityPlayerSP player2 = Minecraft.getMinecraft().thePlayer;
         if (player2 != null) {
            applyText(player2.getLabelText(), (SpecialItemArrayEntry)null);
            timestamp = System.currentTimeMillis();
         }
      }

      String labelText = player.getLabelText();
      if (labelText == null) {
         return null;
      } else {
         SpecialItemArrayEntry specialItemArrayEntry = (SpecialItemArrayEntry)getMap().get(labelText);
         if (specialItemArrayEntry == null) {
            specialItemArrayEntry = new SpecialItemArrayEntry();
            getMap().put(labelText, specialItemArrayEntry);
            ASCIIListener aSCIIListener = new ASCIIListener(labelText);
            String text = PlayerModelsLocalManager.getText() + "/users/" + labelText + ".cfg";
            new TertiaryThreadAdapter(text, aSCIIListener).start();
         }

         return specialItemArrayEntry;
      }
   }

   public static void applyFloatEntryRegistryByFloatEntryRegist(FloatEntryRegistry floatEntryRegistry, AbstractClientPlayer player, float factor, float factor2) {
      SpecialItemArrayEntry specialItemArrayEntry = resolveSpecialItemArrayEntryByAbstractClientPl(player);
      if (specialItemArrayEntry != null) {
         specialItemArrayEntry.applyFloatEntryRegistryByFloatEntryRegist(floatEntryRegistry, player, factor, factor2);
      }
   }

   private static Map getMap() {
      if (map == null) {
         map = new HashMap();
      }

      return map;
   }
}
