package client.util;

import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;

public class SpecialHashMapEntry {
   public static final HashMap hashMap = new HashMap();

   public static ModuleDisableRule get(String text) {
      return (ModuleDisableRule)hashMap.get(text);
   }

   public static void applyDirectStringEntry(DirectStringEntry directStringEntry) {
      ModuleDisableRule moduleDisableRule = (ModuleDisableRule)hashMap.get(directStringEntry.getLabelText());
      if (moduleDisableRule != null) {
         HashMap entry = (HashMap)moduleDisableRule.getEntry();
         if (entry != null) {
            entry.remove(directStringEntry.getCount());
            if (entry.isEmpty()) {
               hashMap.remove(directStringEntry.getLabelText());
            }
         }
      }
   }

   public static NativeStringEntry resolveNativeStringEntryByEntityPlayer(EntityPlayer player, int count) {
      if (player.getGameProfile() == null) {
         return null;
      } else {
         ModuleDisableRule moduleDisableRule = get(player.getGameProfile().getName());
         if (moduleDisableRule == null) {
            return null;
         } else {
            HashMap entry = (HashMap)moduleDisableRule.getEntry();
            if (entry != null) {
               int entry2 = (Integer)moduleDisableRule.getEntry2();
               String entry3 = (String)moduleDisableRule.getEntry3();
               int count2 = entry2;
               if (AutoReportCache.checkEntityPlayer(player, count2, entry3)) {
                  return (NativeStringEntry)entry.get(count);
               }
            }

            return null;
         }
      }
   }

   public static void applyGenericStringEntry(GenericStringEntry genericStringEntry) {
      ModuleDisableRule moduleDisableRule = (ModuleDisableRule)hashMap.get(genericStringEntry.getLabelText3());
      if (moduleDisableRule == null) {
         moduleDisableRule = new ModuleDisableRule(genericStringEntry.getCount2(), genericStringEntry.getLabelText4(), null);
      }

      HashMap entry = (HashMap)moduleDisableRule.getEntry();
      if (entry == null) {
         entry = new HashMap();
      }

      entry.put(genericStringEntry.getCount(), new NativeStringEntry(genericStringEntry.getLabelText3(), genericStringEntry.getLabelText2(), genericStringEntry.getLabelText(), genericStringEntry.getCount()));
      hashMap.put(genericStringEntry.getLabelText3(), new ModuleDisableRule(genericStringEntry.getCount2(), genericStringEntry.getLabelText4(), entry));
   }

   public static void clear() {
      hashMap.clear();
   }
}
