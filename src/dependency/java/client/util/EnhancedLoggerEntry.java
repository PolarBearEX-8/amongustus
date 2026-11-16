package client.util;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.item.GoldenIngot;
import net.minecraft.item.InternalItemArrayEntry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnhancedLoggerEntry {
   public static final Logger logger = LogManager.getLogger();
   public static Map map = Maps.newHashMap();
   public static Map map2 = Maps.newHashMap();
   public static Map map3 = Maps.newHashMap();
   public static Map map4 = Maps.newHashMap();

   private static void applyClass(Class clazz, String text) {
      map.put(text, clazz);
      map2.put(clazz, text);
   }

   static {
      applyClass(TargetBuffer.class, "Mineshaft");
      applyClass(TargetFactory.class, "Village");
      applyClass(TargetSynchronizer.class, "Fortress");
      applyClass(TargetDispatcher.class, "Stronghold");
      applyClass(TargetPool.class, "Temple");
      applyClass(AuxiliarySetEntry.class, "Monument");
      GlobalListEntry.updateState();
      AngleService.updateState();
      InternalItemArrayEntry.updateState();
      ClassEntry3ArrayEntry.updateState2();
      AngleAdapter.updateState();
      SplineAdapter.updateState();
   }

   public static GoldenIngot resolveGoldenIngot(NBTTagCompound tagCompound, World world) {
      GoldenIngot goldenIngot = null;

      try {
         Class clazz = (Class)map3.get(tagCompound.resolveText("id"));
         if (clazz != null) {
            goldenIngot = (GoldenIngot)clazz.newInstance();
         }
      } catch (Exception exception) {
         logger.warn("Failed Piece with id " + tagCompound.resolveText("id"));
         exception.printStackTrace();
      }

      if (goldenIngot != null) {
         goldenIngot.applyWorld(world, tagCompound);
      } else {
         logger.warn("Skipping Piece with id " + tagCompound.resolveText("id"));
      }

      return goldenIngot;
   }

   public static AuxiliaryLinkedListEntry resolveAuxiliaryLinkedListEntry(NBTTagCompound tagCompound, World world) {
      AuxiliaryLinkedListEntry auxiliaryLinkedListEntry = null;

      try {
         Class clazz = (Class)map.get(tagCompound.resolveText("id"));
         if (clazz != null) {
            auxiliaryLinkedListEntry = (AuxiliaryLinkedListEntry)clazz.newInstance();
         }
      } catch (Exception exception) {
         logger.warn("Failed Start with id " + tagCompound.resolveText("id"));
         exception.printStackTrace();
      }

      if (auxiliaryLinkedListEntry != null) {
         auxiliaryLinkedListEntry.applyWorld(world, tagCompound);
      } else {
         logger.warn("Skipping Structure with id " + tagCompound.resolveText("id"));
      }

      return auxiliaryLinkedListEntry;
   }

   public static void applyClassByClass(Class clazz, String text) {
      map3.put(text, clazz);
      map4.put(clazz, text);
   }

   public static String resolveText(GoldenIngot goldenIngot) {
      return (String)map4.get(goldenIngot.getClass());
   }

   public static String resolveText(AuxiliaryLinkedListEntry auxiliaryLinkedListEntry) {
      return (String)map2.get(auxiliaryLinkedListEntry.getClass());
   }
}
