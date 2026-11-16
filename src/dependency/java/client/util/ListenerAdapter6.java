package client.util;

import client.event.AuxiliaryListener;
import client.event.ExtendedListener;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ListenerAdapter6 implements AuxiliaryListener {
   public final AdvancedMapEntry advancedMapEntry;
   public final ExtendedListener extendedListener;
   public final Map map3 = Maps.newHashMap();
   public final Map map2 = Maps.newHashMap();
   public final Map map = Maps.newHashMap();
   public double value;
   public boolean enabled = true;
   public double value2;

   @Override
   public Collection getCollection() {
      HashSet set = Sets.newHashSet();

      for (int index = 0; index < 3; index++) {
         set.addAll(this.resolveCollection(index));
      }

      return set;
   }

   private Collection resolveCollectionByIndex(int count) {
      HashSet set = Sets.newHashSet(this.resolveCollection(count));

      for (ExtendedListener extendedListener2 = this.extendedListener.getExtendedListener(); extendedListener2 != null; extendedListener2 = extendedListener2.getExtendedListener()) {
         AuxiliaryListener auxiliaryListener = this.advancedMapEntry.get(extendedListener2);
         if (auxiliaryListener != null) {
            set.addAll(auxiliaryListener.resolveCollection(count));
         }
      }

      return set;
   }

   @Override
   public double getDouble() {
      if (this.enabled) {
         this.value2 = this.getValueAsDouble();
         this.enabled = false;
      }

      return this.value2;
   }

   @Override
   public boolean matchesInvalidOperationManager(InvalidOperationManager invalidOperationManager) {
      return this.map.get(invalidOperationManager.getUUID()) != null;
   }

   @Override
   public Collection resolveCollection(int count) {
      return (Collection)this.map3.get(count);
   }

   @Override
   public double getValue() {
      return this.value;
   }

   public void updateState2() {
      this.enabled = true;
      this.advancedMapEntry.applyAuxiliaryListener(this);
   }

   @Override
   public void applyInvalidOperationManagerByInvalidOperation(InvalidOperationManager invalidOperationManager) {
      for (int index = 0; index < 3; index++) {
         ((Set)this.map3.get(index)).remove(invalidOperationManager);
      }

      Set set = (Set)this.map2.get(invalidOperationManager.getLabelText());
      if (set != null) {
         set.remove(invalidOperationManager);
         if (set.isEmpty()) {
            this.map2.remove(invalidOperationManager.getLabelText());
         }
      }

      this.map.remove(invalidOperationManager.getUUID());
      this.updateState2();
   }

   @Override
   public void applyDouble(double offset) {
      if (offset != this.getValue()) {
         this.value = offset;
         this.updateState2();
      }
   }

   @Override
   public void applyInvalidOperationManager(InvalidOperationManager invalidOperationManager) {
      if (this.get(invalidOperationManager.getUUID()) != null) {
         throw new IllegalArgumentException("Modifier is already applied on this attribute!");
      } else {
         Set entry = (Set)this.map2.get(invalidOperationManager.getLabelText());
         if (entry == null) {
            entry = Sets.newHashSet();
            this.map2.put(invalidOperationManager.getLabelText(), entry);
         }

         ((Set)this.map3.get(invalidOperationManager.getCount())).add(invalidOperationManager);
         entry.add(invalidOperationManager);
         this.map.put(invalidOperationManager.getUUID(), invalidOperationManager);
         this.updateState2();
      }
   }

   @Override
   public InvalidOperationManager get(UUID uuid) {
      return (InvalidOperationManager)this.map.get(uuid);
   }

   @Override
   public void updateState() {
      Collection collection = this.getCollection();
      if (collection != null) {
         for (Object invalidOperationManager_o : Lists.newArrayList(collection)) {
            InvalidOperationManager invalidOperationManager = (InvalidOperationManager)invalidOperationManager_o;
            this.applyInvalidOperationManagerByInvalidOperation(invalidOperationManager);
         }
      }
   }

   private double getValueAsDouble() {
      double value3 = this.getValue();

      for (Object invalidOperationManager_o : this.resolveCollectionByIndex(0)) {
         InvalidOperationManager invalidOperationManager = (InvalidOperationManager)invalidOperationManager_o;
         value3 += invalidOperationManager.getValue();
      }

      double offset = value3;

      for (Object invalidOperationManager2_o : this.resolveCollectionByIndex(1)) {
         InvalidOperationManager invalidOperationManager2 = (InvalidOperationManager)invalidOperationManager2_o;
         offset += value3 * invalidOperationManager2.getValue();
      }

      for (Object invalidOperationManager3_o : this.resolveCollectionByIndex(2)) {
         InvalidOperationManager invalidOperationManager3 = (InvalidOperationManager)invalidOperationManager3_o;
         offset *= 1.0 + invalidOperationManager3.getValue();
      }

      return this.extendedListener.computeValue(offset);
   }

   @Override
   public ExtendedListener getExtendedListener() {
      return this.extendedListener;
   }

   public ListenerAdapter6(AdvancedMapEntry advancedMapEntry2, ExtendedListener extendedListener2) {
      this.advancedMapEntry = advancedMapEntry2;
      this.extendedListener = extendedListener2;
      this.value = extendedListener2.getValue();

      for (int index = 0; index < 3; index++) {
         this.map3.put(index, Sets.newHashSet());
      }
   }
}
