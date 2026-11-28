package net.minecraft.entity.ai.attributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ModifiableAttributeInstance implements IAttributeInstance {
   private final BaseAttributeMap attributeMap;
   private final IAttribute genericAttribute;
   private final Map mapByOperation = Maps.newHashMap();
   private final Map mapByName = Maps.newHashMap();
   private final Map mapByUUID = Maps.newHashMap();
   private double baseValue;
   private boolean needsUpdate = true;
   private double cachedValue;

   @Override
   public double getBaseValue() {
      return this.baseValue;
   }

   @Override
   public void applyModifier(AttributeModifier attributeModifier) {
      if (this.getModifier(attributeModifier.getID()) != null) {
         throw new IllegalArgumentException("Modifier is already applied on this attribute!");
      } else {
         Set set = (Set)this.mapByName.get(attributeModifier.getName());
         if (set == null) {
            set = Sets.newHashSet();
            this.mapByName.put(attributeModifier.getName(), set);
         }

         ((Set)this.mapByOperation.get(attributeModifier.getOperation())).add(attributeModifier);
         set.add(attributeModifier);
         this.mapByUUID.put(attributeModifier.getID(), attributeModifier);
         this.flagForUpdate();
      }
   }

   public void flagForUpdate() {
      this.needsUpdate = true;
      this.attributeMap.onAttributeModified(this);
   }

   @Override
   public Collection getModifiers() {
      HashSet set = Sets.newHashSet();

      for (int i = 0; i < 3; i++) {
         set.addAll(this.getModifiersByOperation(i));
      }

      return set;
   }

   @Override
   public AttributeModifier getModifier(UUID uuid) {
      return (AttributeModifier)this.mapByUUID.get(uuid);
   }

   public ModifiableAttributeInstance(BaseAttributeMap baseAttributeMap, IAttribute attribute) {
      this.attributeMap = baseAttributeMap;
      this.genericAttribute = attribute;
      this.baseValue = attribute.getDefaultValue();

      for (int i = 0; i < 3; i++) {
         this.mapByOperation.put(i, Sets.newHashSet());
      }
   }

   @Override
   public Collection getModifiersByOperation(int n) {
      return (Collection)this.mapByOperation.get(n);
   }

   private double computeValue() {
      double baseValue2 = this.getBaseValue();

      for (AttributeModifier attributeModifier : (Iterable<AttributeModifier>)(this.getAppliedModifiers(0))) {
         baseValue2 += attributeModifier.getAmount();
      }

      double d = baseValue2;

      for (AttributeModifier attributeModifier2 : (Iterable<AttributeModifier>)(this.getAppliedModifiers(1))) {
         d += baseValue2 * attributeModifier2.getAmount();
      }

      for (AttributeModifier attributeModifier3 : (Iterable<AttributeModifier>)(this.getAppliedModifiers(2))) {
         d *= 1.0 + attributeModifier3.getAmount();
      }

      return this.genericAttribute.clampValue(d);
   }

   @Override
   public void removeAllModifiers() {
      Collection modifiers = this.getModifiers();
      if (modifiers != null) {
         for (AttributeModifier attributeModifier : (Iterable<AttributeModifier>)(Lists.newArrayList(modifiers))) {
            this.removeModifier(attributeModifier);
         }
      }
   }

   @Override
   public void setBaseValue(double d) {
      if (d != this.getBaseValue()) {
         this.baseValue = d;
         this.flagForUpdate();
      }
   }

   private Collection getAppliedModifiers(int n) {
      HashSet set = Sets.newHashSet(this.getModifiersByOperation(n));

      for (IAttribute i = this.genericAttribute.getParent(); i != null; i = i.getParent()) {
         IAttributeInstance attribute = this.attributeMap.getAttribute(i);
         if (attribute != null) {
            set.addAll(attribute.getModifiersByOperation(n));
         }
      }

      return set;
   }

   @Override
   public double getAttributeValue() {
      if (this.needsUpdate) {
         this.cachedValue = this.computeValue();
         this.needsUpdate = false;
      }

      return this.cachedValue;
   }

   @Override
   public boolean hasModifier(AttributeModifier attributeModifier) {
      return this.mapByUUID.get(attributeModifier.getID()) != null;
   }

   @Override
   public void removeModifier(AttributeModifier attributeModifier) {
      for (int i = 0; i < 3; i++) {
         ((Set)this.mapByOperation.get(i)).remove(attributeModifier);
      }

      Set set2 = (Set)this.mapByName.get(attributeModifier.getName());
      if (set2 != null) {
         set2.remove(attributeModifier);
         if (set2.isEmpty()) {
            this.mapByName.remove(attributeModifier.getName());
         }
      }

      this.mapByUUID.remove(attributeModifier.getID());
      this.flagForUpdate();
   }

   @Override
   public IAttribute getAttribute() {
      return this.genericAttribute;
   }
}
