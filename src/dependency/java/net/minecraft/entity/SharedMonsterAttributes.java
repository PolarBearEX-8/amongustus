package net.minecraft.entity;

import client.event.AuxiliaryListener;
import client.event.ExtendedListener;
import client.util.AdvancedMapEntry;
import client.util.InvalidOperationManager;
import client.util.NativeDoubleEntry;
import java.util.Collection;
import java.util.UUID;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SharedMonsterAttributes {
   public static final Logger logger = LogManager.getLogger();
   public static final ExtendedListener extendedListener5 = new NativeDoubleEntry((ExtendedListener)null, "generic.maxHealth", 20.0, 0.0, 1024.0)
      .withLabelText2("Max Health")
      .withEnabled(true);
   public static final ExtendedListener extendedListener4 = new NativeDoubleEntry((ExtendedListener)null, "generic.followRange", 32.0, 0.0, 2048.0)
      .withLabelText2("Follow Range");
   public static final ExtendedListener extendedListener2 = new NativeDoubleEntry((ExtendedListener)null, "generic.knockbackResistance", 0.0, 0.0, 1.0)
      .withLabelText2("Knockback Resistance");
   public static final ExtendedListener extendedListener3 = new NativeDoubleEntry((ExtendedListener)null, "generic.movementSpeed", 0.7F, 0.0, 1024.0)
      .withLabelText2("Movement Speed")
      .withEnabled(true);
   public static final ExtendedListener extendedListener = new NativeDoubleEntry((ExtendedListener)null, "generic.attackDamage", 2.0, 0.0, 2048.0);

   private static void applyAuxiliaryListener(AuxiliaryListener auxiliaryListener, NBTTagCompound tagCompound) {
      auxiliaryListener.applyDouble(tagCompound.computeValue("Base"));
      if (tagCompound.matchesText("Modifiers", 9)) {
         NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Modifiers", 10);

         for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
            InvalidOperationManager invalidOperationManager = resolveInvalidOperationManager(extendedLoggerEntry.getNBTTagCompound(index));
            if (invalidOperationManager != null) {
               InvalidOperationManager invalidOperationManager2 = auxiliaryListener.get(invalidOperationManager.getUUID());
               if (invalidOperationManager2 != null) {
                  auxiliaryListener.applyInvalidOperationManagerByInvalidOperation(invalidOperationManager2);
               }

               auxiliaryListener.applyInvalidOperationManager(invalidOperationManager);
            }
         }
      }
   }

   private static NBTTagCompound resolveNBTTagCompound(AuxiliaryListener auxiliaryListener) {
      NBTTagCompound tagCompound = new NBTTagCompound();
      ExtendedListener extendedListener6 = auxiliaryListener.getExtendedListener();
      tagCompound.put("Name", extendedListener6.getLabelText());
      tagCompound.put("Base", auxiliaryListener.getValue());
      Collection collection = auxiliaryListener.getCollection();
      if (collection != null && !collection.isEmpty()) {
         NBTTagList nBTTagList = new NBTTagList();

         for (InvalidOperationManager invalidOperationManager : (Iterable<InvalidOperationManager>) (Iterable) collection) {
            if (invalidOperationManager.isEnabled()) {
               nBTTagList.applyLISTManager(resolveNBTTagCompound(invalidOperationManager));
            }
         }

         tagCompound.applyText("Modifiers", nBTTagList);
      }

      return tagCompound;
   }

   public static InvalidOperationManager resolveInvalidOperationManager(NBTTagCompound tagCompound) {
      UUID uuid = new UUID(tagCompound.computeTimestamp("UUIDMost"), tagCompound.computeTimestamp("UUIDLeast"));

      try {
         return new InvalidOperationManager(uuid, tagCompound.resolveText("Name"), tagCompound.computeValue("Amount"), tagCompound.computeCount("Operation"));
      } catch (Exception exception) {
         logger.warn("Unable to create attribute: " + exception.getMessage());
         return null;
      }
   }

   public static void applyAdvancedMapEntry(AdvancedMapEntry advancedMapEntry, NBTTagList nBTTagList) {
      for (int index = 0; index < nBTTagList.getInt(); index++) {
         NBTTagCompound nBTTagCompound = nBTTagList.getNBTTagCompound(index);
         AuxiliaryListener auxiliaryListener = advancedMapEntry.get(nBTTagCompound.resolveText("Name"));
         if (auxiliaryListener != null) {
            applyAuxiliaryListener(auxiliaryListener, nBTTagCompound);
         } else {
            logger.warn("Ignoring unknown attribute '" + nBTTagCompound.resolveText("Name") + "'");
         }
      }
   }

   public static NBTTagList resolveNBTTagList(AdvancedMapEntry advancedMapEntry) {
      NBTTagList nBTTagList = new NBTTagList();

      for (AuxiliaryListener auxiliaryListener : (Iterable<AuxiliaryListener>) (Iterable) advancedMapEntry.values()) {
         nBTTagList.applyLISTManager(resolveNBTTagCompound(auxiliaryListener));
      }

      return nBTTagList;
   }

   private static NBTTagCompound resolveNBTTagCompound(InvalidOperationManager invalidOperationManager) {
      NBTTagCompound tagCompound = new NBTTagCompound();
      tagCompound.put("Name", invalidOperationManager.getLabelText());
      tagCompound.put("Amount", invalidOperationManager.getValue());
      tagCompound.put("Operation", invalidOperationManager.getCount());
      tagCompound.put("UUIDMost", invalidOperationManager.getUUID().getMostSignificantBits());
      tagCompound.put("UUIDLeast", invalidOperationManager.getUUID().getLeastSignificantBits());
      return tagCompound;
   }
}
