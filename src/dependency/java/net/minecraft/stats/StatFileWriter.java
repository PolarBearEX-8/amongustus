package net.minecraft.stats;

import client.event.PreRenderInterceptor;
import client.util.AuxiliaryDuplicateStatIdManagerAdapter;
import client.util.DuplicateStatIdManager;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.StaticItemEntry;

public class StatFileWriter {
   public final Map map = Maps.newConcurrentMap();

   public void applyEntityPlayer(EntityPlayer player, DuplicateStatIdManager duplicateStatIdManager, int count) {
      StaticItemEntry staticItemEntry = (StaticItemEntry)this.map.get(duplicateStatIdManager);
      if (staticItemEntry == null) {
         staticItemEntry = new StaticItemEntry();
         this.map.put(duplicateStatIdManager, staticItemEntry);
      }

      staticItemEntry.setCount(count);
   }

   public int computeCount(AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter) {
      if (this.matchesAuxiliaryDuplicateStatIdMaByAuxiliaryDuplica(auxiliaryDuplicateStatIdManagerAdapter)) {
         return 0;
      } else {
         int count = 0;

         for (AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter2 = auxiliaryDuplicateStatIdManagerAdapter.auxiliaryDuplicateStatIdManagerAdapter;
            auxiliaryDuplicateStatIdManagerAdapter2 != null && !this.matchesAuxiliaryDuplicateStatIdMaByAuxiliaryDuplica(auxiliaryDuplicateStatIdManagerAdapter2);
            count++
         ) {
            auxiliaryDuplicateStatIdManagerAdapter2 = auxiliaryDuplicateStatIdManagerAdapter2.auxiliaryDuplicateStatIdManagerAdapter;
         }

         return count;
      }
   }

   public PreRenderInterceptor resolvePreRenderInterceptor(DuplicateStatIdManager duplicateStatIdManager) {
      StaticItemEntry staticItemEntry = (StaticItemEntry)this.map.get(duplicateStatIdManager);
      return staticItemEntry != null ? staticItemEntry.getPreRenderInterceptor() : null;
   }

   public boolean matchesAuxiliaryDuplicateStatIdMa(AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter) {
      return auxiliaryDuplicateStatIdManagerAdapter.auxiliaryDuplicateStatIdManagerAdapter == null
         || this.matchesAuxiliaryDuplicateStatIdMaByAuxiliaryDuplica(auxiliaryDuplicateStatIdManagerAdapter.auxiliaryDuplicateStatIdManagerAdapter);
   }

   public void applyEntityPlayer2(EntityPlayer player, DuplicateStatIdManager duplicateStatIdManager, int count) {
      if (!duplicateStatIdManager.isActive() || this.matchesAuxiliaryDuplicateStatIdMa((AuxiliaryDuplicateStatIdManagerAdapter)duplicateStatIdManager)) {
         this.applyEntityPlayer(player, duplicateStatIdManager, this.computeCount(duplicateStatIdManager) + count);
      }
   }

   public PreRenderInterceptor resolvePreRenderInterceptor(DuplicateStatIdManager duplicateStatIdManager, PreRenderInterceptor preRenderInterceptor) {
      StaticItemEntry staticItemEntry = (StaticItemEntry)this.map.get(duplicateStatIdManager);
      if (staticItemEntry == null) {
         staticItemEntry = new StaticItemEntry();
         this.map.put(duplicateStatIdManager, staticItemEntry);
      }

      staticItemEntry.setPreRenderInterceptor(preRenderInterceptor);
      return preRenderInterceptor;
   }

   public int computeCount(DuplicateStatIdManager duplicateStatIdManager) {
      StaticItemEntry staticItemEntry = (StaticItemEntry)this.map.get(duplicateStatIdManager);
      return staticItemEntry == null ? 0 : staticItemEntry.getCount();
   }

   public boolean matchesAuxiliaryDuplicateStatIdMaByAuxiliaryDuplica(AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter) {
      return this.computeCount((DuplicateStatIdManager)auxiliaryDuplicateStatIdManagerAdapter) > 0;
   }
}
