package net.minecraft.world.gen.structure;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MapGenStructureIO {
   private static final Logger LOGGER = LogManager.getLogger();
   private static Map startNameToClassMap = Maps.newHashMap();
   private static Map startClassToNameMap = Maps.newHashMap();
   private static Map componentNameToClassMap = Maps.newHashMap();
   private static Map componentClassToNameMap = Maps.newHashMap();

   private static void registerStructure(Class clazz, String str) {
      startNameToClassMap.put(str, clazz);
      startClassToNameMap.put(clazz, str);
   }

   public static void registerStructureComponent(Class clazz, String str) {
      componentNameToClassMap.put(str, clazz);
      componentClassToNameMap.put(clazz, str);
   }

   public static StructureStart getStructureStart(NBTTagCompound tag, World world) {
      StructureStart structureStart = null;

      try {
         Class clazz = (Class)startNameToClassMap.get(tag.getString("id"));
         if (clazz != null) {
            structureStart = (StructureStart)clazz.newInstance();
         }
      } catch (Exception e) {
         LOGGER.warn("Failed Start with id " + tag.getString("id"));
         e.printStackTrace();
      }

      if (structureStart != null) {
         structureStart.readStructureComponentsFromNBT(world, tag);
      } else {
         LOGGER.warn("Skipping Structure with id " + tag.getString("id"));
      }

      return structureStart;
   }

   public static String getStructureStartName(StructureStart structureStart) {
      return (String)startClassToNameMap.get(structureStart.getClass());
   }

   public static String getStructureComponentName(StructureComponent structureComponent) {
      return (String)componentClassToNameMap.get(structureComponent.getClass());
   }

   static {
      registerStructure(StructureMineshaftStart.class, "Mineshaft");
      registerStructure(MapGenVillage.Start.class, "Village");
      registerStructure(MapGenNetherBridge.Start.class, "Fortress");
      registerStructure(MapGenStronghold.Start.class, "Stronghold");
      registerStructure(MapGenScatteredFeature.Start.class, "Temple");
      registerStructure(StructureOceanMonument.StartMonument.class, "Monument");
      StructureMineshaftPieces.registerStructurePieces();
      StructureVillagePieces.registerVillagePieces();
      StructureNetherBridgePieces.registerNetherFortressPieces();
      StructureStrongholdPieces.registerStrongholdPieces();
      ComponentScatteredFeaturePieces.registerScatteredFeaturePieces();
      StructureOceanMonumentPieces.registerOceanMonumentPieces();
   }

   public static StructureComponent getStructureComponent(NBTTagCompound tag, World world) {
      StructureComponent structureComponent = null;

      try {
         Class clazz = (Class)componentNameToClassMap.get(tag.getString("id"));
         if (clazz != null) {
            structureComponent = (StructureComponent)clazz.newInstance();
         }
      } catch (Exception e) {
         LOGGER.warn("Failed Piece with id " + tag.getString("id"));
         e.printStackTrace();
      }

      if (structureComponent != null) {
         structureComponent.readStructureBaseNBT(world, tag);
      } else {
         LOGGER.warn("Skipping Piece with id " + tag.getString("id"));
      }

      return structureComponent;
   }
}
