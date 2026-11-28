package net.minecraft.entity.ai.attributes;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.server.management.LowerStringMap;

public class ServersideAttributeMap extends BaseAttributeMap {
   private final Set attributeInstanceSet = Sets.newHashSet();
   public final Map descriptionToAttributeInstanceMap = new LowerStringMap();

   @Override
   public IAttributeInstance registerAttribute(IAttribute attribute) {
      IAttributeInstance attributeInstance = super.registerAttribute(attribute);
      if (attribute instanceof RangedAttribute && ((RangedAttribute)attribute).getDescription() != null) {
         this.descriptionToAttributeInstanceMap.put(((RangedAttribute)attribute).getDescription(), attributeInstance);
      }

      return attributeInstance;
   }

   public ModifiableAttributeInstance getAttributeInstance(IAttribute attribute) {
      return (ModifiableAttributeInstance)super.getAttribute(attribute);
   }

   @Override
   public void onAttributeModified(IAttributeInstance attributeInstance) {
      if (attributeInstance.getAttribute().getShouldWatch()) {
         this.attributeInstanceSet.add(attributeInstance);
      }

      for (IAttribute attribute : (Iterable<IAttribute>)(this.descendantsByParent.get(attributeInstance.getAttribute()))) {
         ModifiableAttributeInstance attributeInstance2 = this.getAttributeInstance(attribute);
         if (attributeInstance2 != null) {
            attributeInstance2.flagForUpdate();
         }
      }
   }

   public Set getAttributeInstanceSet() {
      return this.attributeInstanceSet;
   }

   @Override
   public IAttributeInstance createInstance(IAttribute attribute) {
      return new ModifiableAttributeInstance(this, attribute);
   }

   @Override
   public IAttributeInstance getAttribute(IAttribute attribute) {
      return this.getAttributeInstance(attribute);
   }

   public Collection getWatchedAttributes() {
      HashSet set = Sets.newHashSet();

      for (IAttributeInstance attributeInstance : (Iterable<IAttributeInstance>)(this.getAllAttributes())) {
         if (attributeInstance.getAttribute().getShouldWatch()) {
            set.add(attributeInstance);
         }
      }

      return set;
   }

   @Override
   public IAttributeInstance getAttributeInstanceByName(String str) {
      return this.getModifiableAttributeInstanceByName(str);
   }

   public ModifiableAttributeInstance getModifiableAttributeInstanceByName(String str) {
      IAttributeInstance attributeInstance = super.getAttributeInstanceByName(str);
      if (attributeInstance == null) {
         attributeInstance = (IAttributeInstance)this.descriptionToAttributeInstanceMap.get(str);
      }

      return (ModifiableAttributeInstance)attributeInstance;
   }
}
