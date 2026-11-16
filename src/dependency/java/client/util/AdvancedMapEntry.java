package client.util;

import client.event.AuxiliaryListener;
import client.event.ExtendedListener;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AdvancedMapEntry {
   public final Map map = Maps.newHashMap();
   public final Map map2 = new MapAdapter();
   public final Multimap multimap = HashMultimap.create();

   public void applyMultimapByMultimap(Multimap multimap2) {
      for (Entry entry : (Iterable<Entry>)(Iterable<?>)multimap2.entries()) {
         AuxiliaryListener auxiliaryListener = this.get((String)entry.getKey());
         if (auxiliaryListener != null) {
            auxiliaryListener.applyInvalidOperationManagerByInvalidOperation((InvalidOperationManager)entry.getValue());
            auxiliaryListener.applyInvalidOperationManager((InvalidOperationManager)entry.getValue());
         }
      }
   }

   public abstract AuxiliaryListener resolveAuxiliaryListener(ExtendedListener extendedListener);

   public AuxiliaryListener get(ExtendedListener extendedListener) {
      return (AuxiliaryListener)this.map.get(extendedListener);
   }

   public void applyAuxiliaryListener(AuxiliaryListener auxiliaryListener) {
   }

   public Collection values() {
      return this.map2.values();
   }

   public AuxiliaryListener get(String text) {
      return (AuxiliaryListener)this.map2.get(text);
   }

   public void applyMultimap(Multimap multimap2) {
      for (Entry entry : (Iterable<Entry>)(Iterable<?>)multimap2.entries()) {
         AuxiliaryListener auxiliaryListener = this.get((String)entry.getKey());
         if (auxiliaryListener != null) {
            auxiliaryListener.applyInvalidOperationManagerByInvalidOperation((InvalidOperationManager)entry.getValue());
         }
      }
   }

   public AuxiliaryListener resolveAuxiliaryListenerByExtendedListener(ExtendedListener extendedListener) {
      if (this.map2.containsKey(extendedListener.getLabelText())) {
         throw new IllegalArgumentException("Attribute is already registered!");
      } else {
         AuxiliaryListener auxiliaryListener = this.resolveAuxiliaryListener(extendedListener);
         this.map2.put(extendedListener.getLabelText(), auxiliaryListener);
         this.map.put(extendedListener, auxiliaryListener);

         for (ExtendedListener extendedListener2 = extendedListener.getExtendedListener(); extendedListener2 != null; extendedListener2 = extendedListener2.getExtendedListener()) {
            this.multimap.put(extendedListener2, extendedListener);
         }

         return auxiliaryListener;
      }
   }
}
