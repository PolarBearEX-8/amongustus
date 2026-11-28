package net.minecraft.entity;

import java.util.Collection;
import java.util.UUID;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SharedMonsterAttributes {
   private static final Logger LOGGER = LogManager.getLogger();
   public static final IAttribute maxHealth = new RangedAttribute((IAttribute)null, "generic.maxHealth", 20.0, 0.0, 1024.0)
      .setDescription("Max Health")
      .setShouldWatch(true);
   public static final IAttribute followRange = new RangedAttribute((IAttribute)null, "generic.followRange", 32.0, 0.0, 2048.0).setDescription("Follow Range");
   public static final IAttribute knockbackResistance = new RangedAttribute((IAttribute)null, "generic.knockbackResistance", 0.0, 0.0, 1.0)
      .setDescription("Knockback Resistance");
   public static final IAttribute movementSpeed = new RangedAttribute((IAttribute)null, "generic.movementSpeed", 0.7F, 0.0, 1024.0)
      .setDescription("Movement Speed")
      .setShouldWatch(true);
   public static final IAttribute attackDamage = new RangedAttribute((IAttribute)null, "generic.attackDamage", 2.0, 0.0, 2048.0);

   private static void applyModifiersToAttributeInstance(IAttributeInstance attributeInstance, NBTTagCompound tag) {
      attributeInstance.setBaseValue(tag.getDouble("Base"));
      if (tag.hasKey("Modifiers", 9)) {
         NBTTagList tagList = tag.getTagList("Modifiers", 10);

         for (int i = 0; i < tagList.tagCount(); i++) {
            AttributeModifier attributeModifier = readAttributeModifierFromNBT(tagList.getCompoundTagAt(i));
            if (attributeModifier != null) {
               AttributeModifier modifier = attributeInstance.getModifier(attributeModifier.getID());
               if (modifier != null) {
                  attributeInstance.removeModifier(modifier);
               }

               attributeInstance.applyModifier(attributeModifier);
            }
         }
      }
   }

   private static NBTTagCompound writeAttributeModifierToNBT(AttributeModifier attributeModifier) {
      NBTTagCompound tag = new NBTTagCompound();
      tag.setString("Name", attributeModifier.getName());
      tag.setDouble("Amount", attributeModifier.getAmount());
      tag.setInteger("Operation", attributeModifier.getOperation());
      tag.setLong("UUIDMost", attributeModifier.getID().getMostSignificantBits());
      tag.setLong("UUIDLeast", attributeModifier.getID().getLeastSignificantBits());
      return tag;
   }

   private static NBTTagCompound writeAttributeInstanceToNBT(IAttributeInstance attributeInstance) {
      NBTTagCompound tag = new NBTTagCompound();
      IAttribute attribute = attributeInstance.getAttribute();
      tag.setString("Name", attribute.getAttributeUnlocalizedName());
      tag.setDouble("Base", attributeInstance.getBaseValue());
      Collection modifiers = attributeInstance.getModifiers();
      if (modifiers != null && !modifiers.isEmpty()) {
         NBTTagList tagList = new NBTTagList();

         for (AttributeModifier attributeModifier : (Iterable<AttributeModifier>)(modifiers)) {
            if (attributeModifier.isSaved()) {
               tagList.appendTag(writeAttributeModifierToNBT(attributeModifier));
            }
         }

         tag.setTag("Modifiers", tagList);
      }

      return tag;
   }

   public static NBTTagList writeBaseAttributeMapToNBT(BaseAttributeMap baseAttributeMap) {
      NBTTagList tagList = new NBTTagList();

      for (IAttributeInstance attributeInstance : (Iterable<IAttributeInstance>)(baseAttributeMap.getAllAttributes())) {
         tagList.appendTag(writeAttributeInstanceToNBT(attributeInstance));
      }

      return tagList;
   }

   public static void setAttributeModifiers(BaseAttributeMap baseAttributeMap, NBTTagList tagList) {
      for (int i = 0; i < tagList.tagCount(); i++) {
         NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
         IAttributeInstance attributeInstance = baseAttributeMap.getAttributeInstanceByName(compoundTagAt.getString("Name"));
         if (attributeInstance != null) {
            applyModifiersToAttributeInstance(attributeInstance, compoundTagAt);
         } else {
            LOGGER.warn("Ignoring unknown attribute '" + compoundTagAt.getString("Name") + "'");
         }
      }
   }

   public static AttributeModifier readAttributeModifierFromNBT(NBTTagCompound tag) {
      UUID uuid = new UUID(tag.getLong("UUIDMost"), tag.getLong("UUIDLeast"));

      try {
         return new AttributeModifier(uuid, tag.getString("Name"), tag.getDouble("Amount"), tag.getInteger("Operation"));
      } catch (Exception e) {
         LOGGER.warn("Unable to create attribute: " + e.getMessage());
         return null;
      }
   }
}
