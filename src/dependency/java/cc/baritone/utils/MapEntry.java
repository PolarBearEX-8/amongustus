package cc.baritone.utils;

import cc.baritone.api.AuxiliaryItemEntry;
import cc.baritone.api.InternalItemEntry;
import client.util.FloatEntryAdapter;
import java.util.HashMap;
import java.util.Map;

public final class MapEntry extends AuxiliaryItemEntry implements PassiveListener {
   public final Map map = new HashMap();
   public final ClickListenerAdapter clickListenerAdapter;
   public final Listener26Entry listener26Entry;

   @Override
   public final void clear() {
      this.map.clear();
   }

   private boolean isActive() {
      for (BaseCustom baseCustom : new BaseCustom[]{
         BaseCustom.MOVE_FORWARD, BaseCustom.MOVE_BACK, BaseCustom.MOVE_LEFT, BaseCustom.MOVE_RIGHT, BaseCustom.SNEAK, BaseCustom.JUMP
      }) {
         if (this.matchesBaseCustom(baseCustom)) {
            return true;
         }
      }

      return this.baritoneManager.getAlreadyDoingItManager().isActiveAsFlag() || this.baritoneManager != SettingsTxtManager.getExtraListener().get();
   }

   public ClickListenerAdapter getClickListenerAdapter() {
      return this.clickListenerAdapter;
   }

   @Override
   public final boolean matchesBaseCustom(BaseCustom baseCustom) {
      return baseCustom == null ? false : (Boolean)this.map.getOrDefault(baseCustom, false);
   }

   @Override
   public final void applyBaseCustom(BaseCustom baseCustom, boolean flag) {
      this.map.put(baseCustom, flag);
   }

   @Override
   public final void applyInternalItemEntry(InternalItemEntry internalItemEntry) {
      if (internalItemEntry.getInternalOptionType() != InternalOptionType.OUT) {
         if (this.matchesBaseCustom(BaseCustom.CLICK_LEFT)) {
            this.applyBaseCustom(BaseCustom.CLICK_RIGHT, false);
         }

         this.clickListenerAdapter.setEnabled(this.matchesBaseCustom(BaseCustom.CLICK_LEFT));
         this.listener26Entry.setEnabled(this.matchesBaseCustom(BaseCustom.CLICK_RIGHT));
         if (this.isActive()) {
            if (this.staticListener.getEntityPlayerSP().secondaryFloatEntry.getClass() != MapPair.class) {
               this.staticListener.getEntityPlayerSP().secondaryFloatEntry = new MapPair(this);
            }
         } else if (this.staticListener.getEntityPlayerSP().secondaryFloatEntry.getClass() == MapPair.class) {
            this.staticListener.getEntityPlayerSP().secondaryFloatEntry = new FloatEntryAdapter(this.staticListener.getMc().gameSettings);
         }
      }
   }

   public MapEntry(BaritoneManager baritoneManager) {
      super(baritoneManager);
      this.clickListenerAdapter = new ClickListenerAdapter(baritoneManager.getStaticListener());
      this.listener26Entry = new Listener26Entry(baritoneManager.getStaticListener());
   }
}
