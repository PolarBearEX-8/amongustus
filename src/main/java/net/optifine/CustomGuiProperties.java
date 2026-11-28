package net.optifine;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import net.optifine.config.VillagerProfession;
import net.optifine.config.Matches;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IWorldNameable;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.config.Config;
import net.optifine.config.ConnectedParser;
import net.optifine.config.NbtTagValue;
import net.optifine.config.RangeListInt;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;

public class CustomGuiProperties {
   private String fileName = null;
   private String basePath = null;
   private EnumGuiContainer container = null;
   private Map textureLocations = null;
   private NbtTagValue nbtName = null;
   private BiomeGenBase[] biomes = null;
   private RangeListInt heights = null;
   private Boolean large = null;
   private Boolean trapped = null;
   private Boolean christmas = null;
   private Boolean ender = null;
   private RangeListInt levels = null;
   private VillagerProfession[] professions = null;
   private EnumGuiVariant[] variants = null;
   private EnumDyeColor[] colors = null;
   private static final EnumGuiVariant[] VARIANTS_HORSE = new EnumGuiVariant[]{EnumGuiVariant.HORSE, EnumGuiVariant.DONKEY, EnumGuiVariant.MULE, EnumGuiVariant.LLAMA};
   private static final EnumGuiVariant[] VARIANTS_DISPENSER = new EnumGuiVariant[]{EnumGuiVariant.DISPENSER, EnumGuiVariant.DROPPER};
   private static final EnumGuiVariant[] VARIANTS_INVALID = new EnumGuiVariant[0];
   private static final EnumDyeColor[] COLORS_INVALID = new EnumDyeColor[0];
   private static final ResourceLocation ANVIL_TEXTURE = new ResourceLocation("textures/gui/container/anvil.png");
   private static final ResourceLocation BEACON_TEXTURE = new ResourceLocation("textures/gui/container/beacon.png");
   private static final ResourceLocation BREWING_STAND_TEXTURE = new ResourceLocation("textures/gui/container/brewing_stand.png");
   private static final ResourceLocation GENERIC_54_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
   private static final ResourceLocation CRAFTING_TABLE_TEXTURE = new ResourceLocation("textures/gui/container/crafting_table.png");
   private static final ResourceLocation HORSE_TEXTURE = new ResourceLocation("textures/gui/container/horse.png");
   private static final ResourceLocation DISPENSER_TEXTURE = new ResourceLocation("textures/gui/container/dispenser.png");
   private static final ResourceLocation ENCHANTING_TABLE_TEXTURE = new ResourceLocation("textures/gui/container/enchanting_table.png");
   private static final ResourceLocation FURNACE_TEXTURE = new ResourceLocation("textures/gui/container/furnace.png");
   private static final ResourceLocation HOPPER_TEXTURE = new ResourceLocation("textures/gui/container/hopper.png");
   private static final ResourceLocation INVENTORY_TEXTURE = new ResourceLocation("textures/gui/container/inventory.png");
   private static final ResourceLocation SHULKER_BOX_TEXTURE = new ResourceLocation("textures/gui/container/shulker_box.png");
   private static final ResourceLocation VILLAGER_TEXTURE = new ResourceLocation("textures/gui/container/villager.png");

   private static void warn(String str) {
      Config.warn("[CustomGuis] " + str);
   }

   private boolean matchesChest(TileEntityChest tileEntityChest, BlockPos pos, IBlockAccess world) {
      return this.matchesChest(tileEntityChest.adjacentChestXNeg != null || tileEntityChest.adjacentChestXPos != null || tileEntityChest.adjacentChestZNeg != null || tileEntityChest.adjacentChestZPos != null, tileEntityChest.getChestType() == 1, CustomGuis.isChristmas, false);
   }

   private static ResourceLocation parseTextureLocation(String str, String str2) {
      if (str == null) {
         return null;
      } else {
         str = str.trim();
         String str3 = TextureUtils.fixResourcePath(str, str2);
         if (!str3.endsWith(".png")) {
            str3 = str3 + ".png";
         }

         return new ResourceLocation(str2 + "/" + str3);
      }
   }

   private static IWorldNameable getWorldNameable(GuiScreen screen) {
      if (screen instanceof GuiBeacon) {
         return ((GuiBeacon)screen).tileBeacon;
      } else if (screen instanceof GuiBrewingStand) {
         return ((GuiBrewingStand)screen).tileBrewingStand;
      } else if (screen instanceof GuiChest) {
         return ((GuiChest)screen).lowerChestInventory;
      } else if (screen instanceof GuiDispenser) {
         return ((GuiDispenser)screen).dispenserInventory;
      } else if (screen instanceof GuiEnchantment) {
         return ((GuiEnchantment)screen).nameable;
      } else if (screen instanceof GuiFurnace) {
         return ((GuiFurnace)screen).tileFurnace;
      } else {
         return screen instanceof GuiHopper ? ((GuiHopper)screen).hopperInventory : null;
      }
   }

   public static String getName(GuiScreen screen) {
      IWorldNameable worldNameable = getWorldNameable(screen);
      return worldNameable == null ? null : worldNameable.getDisplayName().getUnformattedText();
   }

   private static Map parseTextureLocations(Properties properties, String str, EnumGuiContainer enumGuiContainer, String str2, String str3) {
      HashMap map = new HashMap();
      String property = properties.getProperty(str);
      if (property != null) {
         ResourceLocation textureLocation2 = getTextureLocation2(enumGuiContainer);
         ResourceLocation location = parseTextureLocation(property, str3);
         if (textureLocation2 != null && location != null) {
            map.put(textureLocation2, location);
         }
      }

      String str4 = str + ".";

      for (Object obj : properties.keySet()) {
         String str5 = (String)obj;
         if (str5.startsWith(str4)) {
            String str6 = str5.substring(str4.length());
            str6 = str6.replace('\\', '/');
            str6 = StrUtils.removePrefixSuffix(str6, "/", ".png");
            String str7 = str2 + str6 + ".png";
            String property2 = properties.getProperty(str5);
            map.put(new ResourceLocation(str7), parseTextureLocation(property2, str3));
         }
      }

      return map;
   }

   private boolean matchesEnderChest(TileEntityEnderChest tileEntityEnderChest, BlockPos pos, IBlockAccess world) {
      return this.matchesChest(false, false, false, true);
   }

   @Override
   public String toString() {
      return "name: " + this.fileName + ", container: " + this.container + ", textures: " + this.textureLocations;
   }

   private static EnumDyeColor parseEnumDyeColor(String str) {
      if (str == null) {
         return null;
      } else {
         EnumDyeColor[] values = EnumDyeColor.values();

         for (int i = 0; i < values.length; i++) {
            EnumDyeColor enumDyeColor = values[i];
            if (enumDyeColor.getName().equals(str)) {
               return enumDyeColor;
            }

            if (enumDyeColor.getUnlocalizedName().equals(str)) {
               return enumDyeColor;
            }
         }

         return null;
      }
   }

   private boolean matchesChest(BlockPos pos, IBlockAccess world) {
      TileEntity tileEntity = world.getTileEntity(pos);
      if (tileEntity instanceof TileEntityChest) {
         return this.matchesChest((TileEntityChest)tileEntity, pos, world);
      } else if (tileEntity instanceof TileEntityEnderChest) {
         return this.matchesEnderChest((TileEntityEnderChest)tileEntity, pos, world);
      } else {
         return false;
      }
   }

   private boolean matchesVillager(Entity entity, IBlockAccess world) {
      if (!(entity instanceof EntityVillager)) {
         return false;
      } else {
         EntityVillager entityVillager = (EntityVillager)entity;
         if (this.professions != null) {
            int profession = entityVillager.getProfession();
            int careerId = entityVillager.careerId;
            if (careerId < 0) {
               return false;
            }

            boolean flag = false;

            for (int i = 0; i < this.professions.length; i++) {
               if (this.professions[i].matches(profession, careerId)) {
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

   private boolean matchesBeacon(BlockPos pos, IBlockAccess world) {
      TileEntity tileEntity = world.getTileEntity(pos);
      if (!(tileEntity instanceof TileEntityBeacon)) {
         return false;
      } else {
         TileEntityBeacon tileEntityBeacon = (TileEntityBeacon)tileEntity;
         if (this.levels != null) {
            NBTTagCompound tag = new NBTTagCompound();
            tileEntityBeacon.writeToNBT(tag);
            int integer = tag.getInteger("Levels");
            if (!this.levels.isInRange(integer)) {
               return false;
            }
         }

         return true;
      }
   }

   private static EnumDyeColor[] parseEnumDyeColors(String str) {
      if (str == null) {
         return null;
      } else {
         str = str.toLowerCase();
         String[] strings = Config.tokenize(str, " ");
         EnumDyeColor[] enumDyeColors = new EnumDyeColor[strings.length];

         for (int i = 0; i < strings.length; i++) {
            String str2 = strings[i];
            EnumDyeColor enumDyeColor = parseEnumDyeColor(str2);
            if (enumDyeColor == null) {
               warn("Invalid color: " + str2);
               return COLORS_INVALID;
            }

            enumDyeColors[i] = enumDyeColor;
         }

         return enumDyeColors;
      }
   }

   private boolean matchesGeneral(EnumGuiContainer enumGuiContainer, BlockPos pos, IBlockAccess world) {
      if (this.container != enumGuiContainer) {
         return false;
      } else {
         if (this.biomes != null) {
            if (!Matches.biome(world.getBiomeGenForCoords(pos), this.biomes)) {
               return false;
            }
         }

         return this.heights == null || this.heights.isInRange(pos.getY());
      }
   }

   public ResourceLocation getTextureLocation(ResourceLocation location) {
      ResourceLocation location2 = (ResourceLocation)this.textureLocations.get(location);
      return location2 == null ? location : location2;
   }

   private static ResourceLocation getTextureLocation2(EnumGuiContainer enumGuiContainer) {
      if (enumGuiContainer == null) {
         return null;
      } else {
         switch (enumGuiContainer) {
            case ANVIL:
               return ANVIL_TEXTURE;
            case BEACON:
               return BEACON_TEXTURE;
            case BREWING_STAND:
               return BREWING_STAND_TEXTURE;
            case CHEST:
               return GENERIC_54_TEXTURE;
            case CRAFTING:
               return CRAFTING_TABLE_TEXTURE;
            case CREATIVE:
               return null;
            case DISPENSER:
               return DISPENSER_TEXTURE;
            case ENCHANTMENT:
               return ENCHANTING_TABLE_TEXTURE;
            case FURNACE:
               return FURNACE_TEXTURE;
            case HOPPER:
               return HOPPER_TEXTURE;
            case HORSE:
               return HORSE_TEXTURE;
            case INVENTORY:
               return INVENTORY_TEXTURE;
            case SHULKER_BOX:
               return SHULKER_BOX_TEXTURE;
            case VILLAGER:
               return VILLAGER_TEXTURE;
            default:
               return null;
         }
      }
   }

   private EnumGuiVariant getHorseVariant(EntityHorse entityHorse) {
      switch (entityHorse.getHorseType()) {
         case 0:
            return EnumGuiVariant.HORSE;
         case 1:
            return EnumGuiVariant.DONKEY;
         case 2:
            return EnumGuiVariant.MULE;
         default:
            return null;
      }
   }

   private boolean matchesHorse(Entity entity, IBlockAccess world) {
      if (!(entity instanceof EntityHorse)) {
         return false;
      } else {
         EntityHorse entityHorse = (EntityHorse)entity;
         if (this.variants != null) {
            if (!Config.equalsOne2(this.getHorseVariant(entityHorse), this.variants)) {
               return false;
            }
         }

         return true;
      }
   }

   private EnumGuiVariant getDispenserVariant(TileEntityDispenser tileEntityDispenser) {
      return tileEntityDispenser instanceof TileEntityDropper ? EnumGuiVariant.DROPPER : EnumGuiVariant.DISPENSER;
   }

   private boolean matchesDispenser(BlockPos pos, IBlockAccess world) {
      TileEntity tileEntity = world.getTileEntity(pos);
      if (!(tileEntity instanceof TileEntityDispenser)) {
         return false;
      } else {
         TileEntityDispenser tileEntityDispenser = (TileEntityDispenser)tileEntity;
         if (this.variants != null) {
            if (!Config.equalsOne2(this.getDispenserVariant(tileEntityDispenser), this.variants)) {
               return false;
            }
         }

         return true;
      }
   }

   public CustomGuiProperties(Properties properties, String str) {
      ConnectedParser connectedParser = new ConnectedParser("CustomGuis");
      this.fileName = connectedParser.parseName(str);
      this.basePath = connectedParser.parseBasePath(str);
      this.container = (EnumGuiContainer)connectedParser.parseEnum(properties.getProperty("container"), EnumGuiContainer.values(), "container");
      this.textureLocations = parseTextureLocations(properties, "texture", this.container, "textures/gui/", this.basePath);
      this.nbtName = connectedParser.parseNbtTagValue("name", properties.getProperty("name"));
      this.biomes = connectedParser.parseBiomes(properties.getProperty("biomes"));
      this.heights = connectedParser.parseRangeListInt(properties.getProperty("heights"));
      this.large = connectedParser.parseBooleanObject(properties.getProperty("large"));
      this.trapped = connectedParser.parseBooleanObject(properties.getProperty("trapped"));
      this.christmas = connectedParser.parseBooleanObject(properties.getProperty("christmas"));
      this.ender = connectedParser.parseBooleanObject(properties.getProperty("ender"));
      this.levels = connectedParser.parseRangeListInt(properties.getProperty("levels"));
      this.professions = connectedParser.parseProfessions(properties.getProperty("professions"));
      EnumGuiVariant[] containerVariants = getContainerVariants(this.container);
      this.variants = (EnumGuiVariant[])connectedParser.parseEnums(properties.getProperty("variants"), containerVariants, "variants", VARIANTS_INVALID);
      this.colors = parseEnumDyeColors(properties.getProperty("colors"));
   }

   public EnumGuiContainer getContainer() {
      return this.container;
   }

   private boolean matchesChest(boolean flag, boolean flag2, boolean flag3, boolean flag4) {
      return this.large != null && this.large != flag
         ? false
         : (this.trapped != null && this.trapped != flag2 ? false : (this.christmas != null && this.christmas != flag3 ? false : this.ender == null || this.ender == flag4));
   }

   private static EnumGuiVariant[] getContainerVariants(EnumGuiContainer enumGuiContainer) {
      return enumGuiContainer == EnumGuiContainer.HORSE ? VARIANTS_HORSE : (enumGuiContainer == EnumGuiContainer.DISPENSER ? VARIANTS_DISPENSER : new EnumGuiVariant[0]);
   }

   public boolean isValid(String str) {
      if (this.fileName == null || this.fileName.length() <= 0) {
         warn("No name found: " + str);
         return false;
      } else if (this.basePath == null) {
         warn("No base path found: " + str);
         return false;
      } else if (this.container == null) {
         warn("No container found: " + str);
         return false;
      } else if (this.textureLocations.isEmpty()) {
         warn("No texture found: " + str);
         return false;
      } else if (this.professions == ConnectedParser.PROFESSIONS_INVALID) {
         warn("Invalid professions or careers: " + str);
         return false;
      } else if (this.variants == VARIANTS_INVALID) {
         warn("Invalid variants: " + str);
         return false;
      } else if (this.colors == COLORS_INVALID) {
         warn("Invalid colors: " + str);
         return false;
      } else {
         return true;
      }
   }

   public boolean matchesEntity(EnumGuiContainer enumGuiContainer, Entity entity, IBlockAccess world) {
      if (!this.matchesGeneral(enumGuiContainer, entity.getPosition(), world)) {
         return false;
      } else {
         if (this.nbtName != null) {
            String name = entity.getName();
            if (!this.nbtName.matchesValue(name)) {
               return false;
            }
         }

         switch (enumGuiContainer) {
            case HORSE:
               return this.matchesHorse(entity, world);
            case VILLAGER:
               return this.matchesVillager(entity, world);
            default:
               return true;
         }
      }
   }

   public boolean matchesPos(EnumGuiContainer enumGuiContainer, BlockPos pos, IBlockAccess world, GuiScreen screen) {
      if (!this.matchesGeneral(enumGuiContainer, pos, world)) {
         return false;
      } else {
         if (this.nbtName != null) {
            String name = getName(screen);
            if (!this.nbtName.matchesValue(name)) {
               return false;
            }
         }

         switch (enumGuiContainer) {
            case BEACON:
               return this.matchesBeacon(pos, world);
            case CHEST:
               return this.matchesChest(pos, world);
            case DISPENSER:
               return this.matchesDispenser(pos, world);
            default:
               return true;
         }
      }
   }

}
