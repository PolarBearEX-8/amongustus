package client.util;

import client.event.ComplexListener;
import client.gui.CustomGuiScreen;
import client.gui.OptifineGuiContainerManager;
import client.setting.LocalOptionType;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityVillager;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityEnderchest;
import net.minecraft.tileentity.TileEntityTrap;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

public class VectorPool {
   public String labelText = null;
   public String labelText2 = null;
   public AimCollector aimCollector = null;
   public Map map = null;
   public GeometryObserver geometryObserver = null;
   public BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray = null;
   public TargetAggregator targetAggregator = null;
   public Boolean memberRef = null;
   public Boolean memberRef2 = null;
   public Boolean memberRef3 = null;
   public Boolean memberRef4 = null;
   public TargetAggregator targetAggregator2 = null;
   public SpecialIntArrayEntry[] specialIntArrayEntryArray = null;
   public DROPPERHORSEEnum[] dROPPERHORSEEnumArray = null;
   public LocalOptionType[] localOptionTypeArray = null;
   public static final DROPPERHORSEEnum[] dROPPERHORSEEnumArray2 = new DROPPERHORSEEnum[]{
      DROPPERHORSEEnum.HORSE, DROPPERHORSEEnum.DONKEY, DROPPERHORSEEnum.MULE, DROPPERHORSEEnum.LLAMA
   };
   public static final DROPPERHORSEEnum[] dROPPERHORSEEnumArray3 = new DROPPERHORSEEnum[]{DROPPERHORSEEnum.DISPENSER, DROPPERHORSEEnum.DROPPER};
   public static final DROPPERHORSEEnum[] dROPPERHORSEEnumArray4 = new DROPPERHORSEEnum[0];
   public static final LocalOptionType[] localOptionTypeArray2 = new LocalOptionType[0];
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/gui/container/anvil.png");
   public static final ResourceLocation resourceLocation2 = new ResourceLocation("textures/gui/container/beacon.png");
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/gui/container/brewing_stand.png");
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/gui/container/generic_54.png");
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/gui/container/crafting_table.png");
   public static final ResourceLocation resourceLocation6 = new ResourceLocation("textures/gui/container/horse.png");
   public static final ResourceLocation resourceLocation7 = new ResourceLocation("textures/gui/container/dispenser.png");
   public static final ResourceLocation resourceLocation8 = new ResourceLocation("textures/gui/container/enchanting_table.png");
   public static final ResourceLocation resourceLocation9 = new ResourceLocation("textures/gui/container/furnace.png");
   public static final ResourceLocation resourceLocation10 = new ResourceLocation("textures/gui/container/hopper.png");
   public static final ResourceLocation resourceLocation11 = new ResourceLocation("textures/gui/container/inventory.png");
   public static final ResourceLocation resourceLocation12 = new ResourceLocation("textures/gui/container/shulker_box.png");
   public static final ResourceLocation resourceLocation13 = new ResourceLocation("textures/gui/container/villager.png");

   private boolean checkFlag(boolean flag, boolean flag2, boolean flag3, boolean flag4) {
      return this.memberRef != null && this.memberRef != flag
         ? false
         : (
            this.memberRef2 != null && this.memberRef2 != flag2
               ? false
               : (this.memberRef3 != null && this.memberRef3 != flag3 ? false : this.memberRef4 == null || this.memberRef4 == flag4)
         );
   }

   private boolean matchesBlockPosByBlockPos(BlockPos blockPos, IBlockAccess iBlockAccess) {
      TileEntity tileEntity = iBlockAccess.resolveTileEntity(blockPos);
      if (!(tileEntity instanceof TileEntityBeacon)) {
         return false;
      } else {
         TileEntityBeacon tileEntityBeacon = (TileEntityBeacon)tileEntity;
         if (this.targetAggregator2 != null) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            tileEntityBeacon.applyNBTTagCompound(tagCompound);
            int count = tagCompound.computeCount("Levels");
            if (!this.targetAggregator2.checkIndex(count)) {
               return false;
            }
         }

         return true;
      }
   }

   private DROPPERHORSEEnum resolveDROPPERHORSEEnum(TileEntityTrap tileEntityTrap) {
      return tileEntityTrap instanceof TileEntityDropper ? DROPPERHORSEEnum.DROPPER : DROPPERHORSEEnum.DISPENSER;
   }

   public boolean matchesText(String text) {
      if (this.labelText == null || this.labelText.length() <= 0) {
         applyText("No name found: " + text);
         return false;
      } else if (this.labelText2 == null) {
         applyText("No base path found: " + text);
         return false;
      } else if (this.aimCollector == null) {
         applyText("No container found: " + text);
         return false;
      } else if (this.map.isEmpty()) {
         applyText("No texture found: " + text);
         return false;
      } else if (this.specialIntArrayEntryArray == NetherManager.specialIntArrayEntryArray) {
         applyText("Invalid professions or careers: " + text);
         return false;
      } else if (this.dROPPERHORSEEnumArray == dROPPERHORSEEnumArray4) {
         applyText("Invalid variants: " + text);
         return false;
      } else if (this.localOptionTypeArray == localOptionTypeArray2) {
         applyText("Invalid colors: " + text);
         return false;
      } else {
         return true;
      }
   }

   private boolean matchesBlockPos2(BlockPos blockPos, IBlockAccess iBlockAccess) {
      TileEntity tileEntity = iBlockAccess.resolveTileEntity(blockPos);
      if (tileEntity instanceof TileEntityChest) {
         TileEntityChest tileEntityChest = (TileEntityChest)tileEntity;
         return this.matchesTileEntityChest(tileEntityChest, blockPos, iBlockAccess);
      } else if (tileEntity instanceof TileEntityEnderchest) {
         TileEntityEnderchest tileEntityEnderchest = (TileEntityEnderchest)tileEntity;
         return this.matchesTileEntityEnderchest(tileEntityEnderchest, blockPos, iBlockAccess);
      } else {
         return false;
      }
   }

   public boolean matchesAimCollectorByAimCollector(AimCollector aimCollector2, BlockPos blockPos, IBlockAccess iBlockAccess, GuiScreen screen) {
      if (!this.matchesAimCollector(aimCollector2, blockPos, iBlockAccess)) {
         return false;
      } else {
         if (this.geometryObserver != null) {
            String text = resolveText(screen);
            if (!this.geometryObserver.matchesText(text)) {
               return false;
            }
         }

         switch (VectorPoolAimCollectorSwitchMap.countArray[aimCollector2.ordinal()]) {
            case 2:
               return this.matchesBlockPosByBlockPos(blockPos, iBlockAccess);
            case 4:
               return this.matchesBlockPos2(blockPos, iBlockAccess);
            case 7:
               return this.matchesBlockPos(blockPos, iBlockAccess);
            default:
               return true;
         }
      }
   }

   public ResourceLocation get(ResourceLocation location) {
      ResourceLocation location2 = (ResourceLocation)this.map.get(location);
      return location2 == null ? location : location2;
   }

   private boolean matchesTileEntityChest(TileEntityChest tileEntityChest, BlockPos blockPos, IBlockAccess iBlockAccess) {
      boolean flag = tileEntityChest.tileEntityChest != null || tileEntityChest.tileEntityChest3 != null || tileEntityChest.tileEntityChest2 != null || tileEntityChest.tileEntityChest1 != null;
      boolean flag2 = tileEntityChest.getCountAsIndex() == 1;
      boolean enabled = OptifineGuiContainerManager.enabled;
      return this.checkFlag(flag, flag2, enabled, false);
   }

   private static LocalOptionType resolveLocalOptionType(String text) {
      if (text == null) {
         return null;
      } else {
         LocalOptionType[] values = LocalOptionType.values();

         for (int index = 0; index < values.length; index++) {
            LocalOptionType localOptionType = values[index];
            if (localOptionType.getText().equals(text)) {
               return localOptionType;
            }

            if (localOptionType.getLabelText().equals(text)) {
               return localOptionType;
            }
         }

         return null;
      }
   }

   private DROPPERHORSEEnum resolveDROPPERHORSEEnum(EntityHorse entityHorse) {
      switch (entityHorse.getCount12()) {
         case 0:
            return DROPPERHORSEEnum.HORSE;
         case 1:
            return DROPPERHORSEEnum.DONKEY;
         case 2:
            return DROPPERHORSEEnum.MULE;
         default:
            return null;
      }
   }

   private static LocalOptionType[] resolveLocalOptionTypeArray(String text) {
      if (text == null) {
         return null;
      } else {
         String text2 = text.toLowerCase();
         String[] textArray = BuildManager.resolveTextArray(text2, " ");
         LocalOptionType[] localOptionTypeArray3 = new LocalOptionType[textArray.length];

         for (int index = 0; index < textArray.length; index++) {
            String text3 = textArray[index];
            LocalOptionType localOptionType = resolveLocalOptionType(text3);
            if (localOptionType == null) {
               applyText("Invalid color: " + text3);
               return localOptionTypeArray2;
            }

            localOptionTypeArray3[index] = localOptionType;
         }

         return localOptionTypeArray3;
      }
   }

   private static ResourceLocation resolveResourceLocation(AimCollector aimCollector2) {
      if (aimCollector2 == null) {
         return null;
      } else {
         switch (VectorPoolAimCollectorSwitchMap.countArray[aimCollector2.ordinal()]) {
            case 1:
               return resourceLocation;
            case 2:
               return resourceLocation2;
            case 3:
               return resourceLocation3;
            case 4:
               return resourceLocation4;
            case 5:
               return resourceLocation5;
            case 6:
               return null;
            case 7:
               return resourceLocation7;
            case 8:
               return resourceLocation8;
            case 9:
               return resourceLocation9;
            case 10:
               return resourceLocation10;
            case 11:
               return resourceLocation6;
            case 12:
               return resourceLocation11;
            case 13:
               return resourceLocation12;
            case 14:
               return resourceLocation13;
            default:
               return null;
         }
      }
   }

   private boolean matchesTileEntityEnderchest(TileEntityEnderchest tileEntityEnderchest, BlockPos blockPos, IBlockAccess iBlockAccess) {
      return this.checkFlag(false, false, false, true);
   }

   private boolean matchesEntity(Entity entity, IBlockAccess iBlockAccess) {
      if (!(entity instanceof EntityHorse)) {
         return false;
      } else {
         EntityHorse entityHorse = (EntityHorse)entity;
         return this.dROPPERHORSEEnumArray == null || BuildManager.matchesTarget(this.resolveDROPPERHORSEEnum(entityHorse), this.dROPPERHORSEEnumArray);
      }
   }

   private static ResourceLocation resolveResourceLocation(String text, String text2) {
      if (text == null) {
         return null;
      } else {
         String text3 = text.trim();
         String text4 = InternalPngManager.resolveText(text3, text2);
         if (!text4.endsWith(".png")) {
            text4 = text4 + ".png";
         }

         return new ResourceLocation(text2 + "/" + text4);
      }
   }

   private static ComplexListener resolveComplexListener(GuiScreen screen) {
      if (screen instanceof CurveEvaluator) {
         return ((CurveEvaluator)screen).iInventory;
      } else if (screen instanceof SplinePool) {
         return ((SplinePool)screen).iInventory;
      } else if (screen instanceof CustomGuiScreen) {
         return ((CustomGuiScreen)screen).iInventory2;
      } else if (screen instanceof SplineProfiler) {
         return ((SplineProfiler)screen).iInventory;
      } else if (screen instanceof SplineService) {
         return ((SplineService)screen).complexListener;
      } else if (screen instanceof SplineBuilder) {
         return ((SplineBuilder)screen).iInventory;
      } else {
         return screen instanceof SplineEngine ? ((SplineEngine)screen).iInventory : null;
      }
   }

   public static String resolveText(GuiScreen screen) {
      ComplexListener complexListener = resolveComplexListener(screen);
      return complexListener == null ? null : complexListener.getChatComponent().getString();
   }

   private static DROPPERHORSEEnum[] resolveDROPPERHORSEEnumArray(AimCollector aimCollector2) {
      return aimCollector2 == AimCollector.HORSE ? dROPPERHORSEEnumArray2 : (aimCollector2 == AimCollector.DISPENSER ? dROPPERHORSEEnumArray3 : new DROPPERHORSEEnum[0]);
   }

   private boolean matchesAimCollector(AimCollector aimCollector2, BlockPos blockPos, IBlockAccess iBlockAccess) {
      if (this.aimCollector != aimCollector2) {
         return false;
      } else {
         return this.biomeIDIsOutOfBoundsManagerArray != null
               && !GeometrySelector.matchesBiomeIDIsOutOfBoundsManage(iBlockAccess.resolveBiomeIDIsOutOfBoundsManager(blockPos), this.biomeIDIsOutOfBoundsManagerArray)
            ? false
            : this.targetAggregator == null || this.targetAggregator.checkIndex(blockPos.getY());
      }
   }

   private static void applyText(String text) {
      BuildManager.applyText3("[CustomGuis] " + text);
   }

   private static Map resolveMap(Properties properties, String text, AimCollector aimCollector2, String text2, String text3) {
      HashMap map2 = new HashMap();
      String property = properties.getProperty(text);
      if (property != null) {
         ResourceLocation location = resolveResourceLocation(aimCollector2);
         ResourceLocation location2 = resolveResourceLocation(property, text3);
         if (location != null && location2 != null) {
            map2.put(location, location2);
         }
      }

      String text4 = text + ".";

      for (String text5 : (Iterable<String>)(Iterable<?>)properties.keySet()) {
         if (text5.startsWith(text4)) {
            String text6 = SplineCollector.resolveText3(text5.substring(text4.length()).replace('\\', '/'), "/", ".png");
            String text7 = text2 + text6 + ".png";
            String property2 = properties.getProperty(text5);
            ResourceLocation location3 = new ResourceLocation(text7);
            ResourceLocation location4 = resolveResourceLocation(property2, text3);
            map2.put(location3, location4);
         }
      }

      return map2;
   }

   private boolean matchesBlockPos(BlockPos blockPos, IBlockAccess iBlockAccess) {
      TileEntity tileEntity = iBlockAccess.resolveTileEntity(blockPos);
      if (!(tileEntity instanceof TileEntityTrap)) {
         return false;
      } else {
         TileEntityTrap tileEntityTrap = (TileEntityTrap)tileEntity;
         return this.dROPPERHORSEEnumArray == null || BuildManager.matchesTarget(this.resolveDROPPERHORSEEnum(tileEntityTrap), this.dROPPERHORSEEnumArray);
      }
   }

   public boolean matchesAimCollectorByAimCollector(AimCollector aimCollector2, Entity entity, IBlockAccess iBlockAccess) {
      if (!this.matchesAimCollector(aimCollector2, entity.getBlockPos(), iBlockAccess)) {
         return false;
      } else {
         if (this.geometryObserver != null) {
            String text = entity.getText();
            if (!this.geometryObserver.matchesText(text)) {
               return false;
            }
         }

         switch (VectorPoolAimCollectorSwitchMap.countArray[aimCollector2.ordinal()]) {
            case 11:
               return this.matchesEntity(entity, iBlockAccess);
            case 14:
               return this.matchesEntityByEntity(entity, iBlockAccess);
            default:
               return true;
         }
      }
   }

   private boolean matchesEntityByEntity(Entity entity, IBlockAccess iBlockAccess) {
      if (!(entity instanceof EntityVillager)) {
         return false;
      } else {
         EntityVillager entityVillager = (EntityVillager)entity;
         if (this.specialIntArrayEntryArray != null) {
            int count = entityVillager.getCount10();
            int count2 = entityVillager.count36;
            if (count2 < 0) {
               return false;
            }

            boolean flag = false;

            for (int index = 0; index < this.specialIntArrayEntryArray.length; index++) {
               if (this.specialIntArrayEntryArray[index].checkIndex(count, count2)) {
                  flag = true;
                  break;
               }
            }

            if (!flag) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return "name: " + this.labelText + ", container: " + this.aimCollector + ", textures: " + this.map;
   }

   public VectorPool(Properties properties, String text) {
      NetherManager netherManager = new NetherManager("CustomGuis");
      this.labelText = netherManager.resolveText(text);
      this.labelText2 = netherManager.resolveTextByText(text);
      this.aimCollector = (AimCollector)netherManager.resolveEnum(properties.getProperty("container"), AimCollector.values(), "container");
      this.map = resolveMap(properties, "texture", this.aimCollector, "textures/gui/", this.labelText2);
      this.geometryObserver = netherManager.resolveGeometryObserver("name", properties.getProperty("name"));
      this.biomeIDIsOutOfBoundsManagerArray = netherManager.resolveBiomeIDIsOutOfBoundsManagerA(properties.getProperty("biomes"));
      this.targetAggregator = netherManager.resolveTargetAggregator(properties.getProperty("heights"));
      this.memberRef = netherManager.resolveBoolean(properties.getProperty("large"));
      this.memberRef2 = netherManager.resolveBoolean(properties.getProperty("trapped"));
      this.memberRef3 = netherManager.resolveBoolean(properties.getProperty("christmas"));
      this.memberRef4 = netherManager.resolveBoolean(properties.getProperty("ender"));
      this.targetAggregator2 = netherManager.resolveTargetAggregator(properties.getProperty("levels"));
      this.specialIntArrayEntryArray = netherManager.resolveSpecialIntArrayEntryArray(properties.getProperty("professions"));
      DROPPERHORSEEnum[] dROPPERHORSEEnumArray5 = resolveDROPPERHORSEEnumArray(this.aimCollector);
      this.dROPPERHORSEEnumArray = (DROPPERHORSEEnum[])netherManager.resolveEnumArray(properties.getProperty("variants"), dROPPERHORSEEnumArray5, "variants", dROPPERHORSEEnumArray4);
      this.localOptionTypeArray = resolveLocalOptionTypeArray(properties.getProperty("colors"));
   }

   public AimCollector getAimCollector() {
      return this.aimCollector;
   }
}
