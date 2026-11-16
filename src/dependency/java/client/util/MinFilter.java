package client.util;

import com.google.common.base.Predicate;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerAdapter;

public final class MinFilter implements Predicate {
   public final Map map;

   public MinFilter(Map map2) {
      this.map = map2;
   }

   public boolean apply(Object entry) {
      return this.matchesEntity((Entity)entry);
   }

   public boolean matchesEntity(Entity entity) {
      ThePlayerNameManager thePlayerNameManager = VanillaRunnable.getVanillaRunnable().resolveWorldServer(0).getThePlayerNameManager();

      for (Object entry_o : this.map.entrySet()) {
         Entry entry = (Entry)entry_o;
         String key = (String)entry.getKey();
         boolean flag = false;
         if (key.endsWith("_min") && key.length() > 4) {
            flag = true;
            key = key.substring(0, key.length() - 4);
         }

         ScoreboardData scoreboardData = thePlayerNameManager.get(key);
         if (scoreboardData == null) {
            return false;
         }

         String text = entity instanceof EntityPlayerAdapter ? entity.getText() : entity.getEntityUniqueID().toString();
         if (!thePlayerNameManager.matchesText(text, scoreboardData)) {
            return false;
         }

         int index = thePlayerNameManager.getComparatorEntry(text, scoreboardData).getIndex();
         if (index < (Integer)entry.getValue() && flag) {
            return false;
         }

         if (index > (Integer)entry.getValue() && !flag) {
            return false;
         }
      }

      return true;
   }
}
