package client.util;

import client.event.AuxiliaryListener;
import client.event.ExtendedListener;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SetEntry extends AdvancedMapEntry {
   public final Set set = Sets.newHashSet();
   public final Map map3 = new MapAdapter();

   public Set getSet() {
      return this.set;
   }

   @Override
   public AuxiliaryListener resolveAuxiliaryListener(ExtendedListener extendedListener) {
      return new ListenerAdapter6(this, extendedListener);
   }

   public ListenerAdapter6 resolveListenerAdapter6(String text) {
      AuxiliaryListener auxiliaryListener = super.get(text);
      if (auxiliaryListener == null) {
         auxiliaryListener = (AuxiliaryListener)this.map3.get(text);
      }

      return (ListenerAdapter6)auxiliaryListener;
   }

   @Override
   public AuxiliaryListener get(ExtendedListener extendedListener) {
      return this.resolveListenerAdapter6(extendedListener);
   }

   @Override
   public void applyAuxiliaryListener(AuxiliaryListener auxiliaryListener) {
      if (auxiliaryListener.getExtendedListener().isEnabled()) {
         this.set.add(auxiliaryListener);
      }

      for (ExtendedListener extendedListener : (Iterable<ExtendedListener>)(Object)(this.multimap.get(auxiliaryListener.getExtendedListener()))) {
         ListenerAdapter6 listenerAdapter = this.resolveListenerAdapter6(extendedListener);
         if (listenerAdapter != null) {
            listenerAdapter.updateState2();
         }
      }
   }

   @Override
   public AuxiliaryListener get(String text) {
      return this.resolveListenerAdapter6(text);
   }

   public ListenerAdapter6 resolveListenerAdapter6(ExtendedListener extendedListener) {
      return (ListenerAdapter6)super.get(extendedListener);
   }

   public Collection getCollection() {
      HashSet set2 = Sets.newHashSet();

      for (AuxiliaryListener auxiliaryListener : (Iterable<AuxiliaryListener>)(Object)(this.values())) {
         if (auxiliaryListener.getExtendedListener().isEnabled()) {
            set2.add(auxiliaryListener);
         }
      }

      return set2;
   }

   @Override
   public AuxiliaryListener resolveAuxiliaryListenerByExtendedListener(ExtendedListener extendedListener) {
      AuxiliaryListener auxiliaryListener = super.resolveAuxiliaryListenerByExtendedListener(extendedListener);
      if (extendedListener instanceof NativeDoubleEntry && ((NativeDoubleEntry)extendedListener).getLabelText2() != null) {
         this.map3.put(((NativeDoubleEntry)extendedListener).getLabelText2(), auxiliaryListener);
      }

      return auxiliaryListener;
   }
}
