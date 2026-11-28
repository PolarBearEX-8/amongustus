package net.minecraft.entity.ai.attributes;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.server.management.LowerStringMap;

public abstract class BaseAttributeMap {
   public final Map attributes = Maps.newHashMap();
   public final Map attributesByName = new LowerStringMap();
   public final Multimap descendantsByParent = HashMultimap.create();

   public void onAttributeModified(IAttributeInstance attributeInstance) {
   }

   public Collection getAllAttributes() {
      return this.attributesByName.values();
   }

   public IAttributeInstance getAttributeInstanceByName(String str) {
      return (IAttributeInstance)this.attributesByName.get(str);
   }

   public void removeAttributeModifiers(Multimap multimap) {
      for (Entry entry : (Iterable<Entry>)(multimap.entries())) {
         IAttributeInstance attributeInstance = this.getAttributeInstanceByName((String)entry.getKey());
         if (attributeInstance != null) {
            attributeInstance.removeModifier((AttributeModifier)entry.getValue());
         }
      }
   }

   public IAttributeInstance getAttribute(IAttribute attribute) {
      return (IAttributeInstance)this.attributes.get(attribute);
   }

   public abstract IAttributeInstance createInstance(IAttribute attribute);

   public IAttributeInstance registerAttribute(IAttribute attribute) {
      if (this.attributesByName.containsKey(attribute.getAttributeUnlocalizedName())) {
         throw new IllegalArgumentException("Attribute is already registered!");
      } else {
         IAttributeInstance attributeInstance = this.createInstance(attribute);
         this.attributesByName.put(attribute.getAttributeUnlocalizedName(), attributeInstance);
         this.attributes.put(attribute, attributeInstance);

         for (IAttribute i = attribute.getParent(); i != null; i = i.getParent()) {
            this.descendantsByParent.put(i, attribute);
         }

         return attributeInstance;
      }
   }

   public void applyAttributeModifiers(Multimap multimap) {
      for (Entry entry : (Iterable<Entry>)(multimap.entries())) {
         IAttributeInstance attributeInstance = this.getAttributeInstanceByName((String)entry.getKey());
         if (attributeInstance != null) {
            attributeInstance.removeModifier((AttributeModifier)entry.getValue());
            attributeInstance.applyModifier((AttributeModifier)entry.getValue());
         }
      }
   }
}
