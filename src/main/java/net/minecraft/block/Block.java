package net.minecraft.block;

import com.google.common.collect.UnmodifiableIterator;
import java.util.List;
import java.util.Random;
import net.augustus.modules.render.XRay;
import net.augustus.events.listeners.BlockBoundingBoxListener;
import net.augustus.events.EventBlockBoundingBox;
import net.augustus.utils.rotation.FullBlockRaytraceFilter;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.RegistryNamespacedDefaultedByKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Block {
   private static final ResourceLocation AIR_ID = new ResourceLocation("air");
   public static final RegistryNamespacedDefaultedByKey blockRegistry = new RegistryNamespacedDefaultedByKey(AIR_ID);
   public static final ObjectIntIdentityMap BLOCK_STATE_IDS = new ObjectIntIdentityMap();
   private CreativeTabs displayOnCreativeTab;
   public static final Block.SoundType soundTypeStone = new Block.SoundType("stone", 1.0F, 1.0F);
   public static final Block.SoundType soundTypeWood = new Block.SoundType("wood", 1.0F, 1.0F);
   public static final Block.SoundType soundTypeGravel = new Block.SoundType("gravel", 1.0F, 1.0F);
   public static final Block.SoundType soundTypeGrass = new Block.SoundType("grass", 1.0F, 1.0F);
   public static final Block.SoundType soundTypePiston = new Block.SoundType("stone", 1.0F, 1.0F);
   public static final Block.SoundType soundTypeMetal = new Block.SoundType("stone", 1.0F, 1.5F);
   public static final Block.SoundType soundTypeGlass = new Block.DefaultSoundType("stone", 1.0F, 1.0F);
   public static final Block.SoundType soundTypeCloth = new Block.SoundType("cloth", 1.0F, 1.0F);
   public static final Block.SoundType soundTypeSand = new Block.SoundType("sand", 1.0F, 1.0F);
   public static final Block.SoundType soundTypeSnow = new Block.SoundType("snow", 1.0F, 1.0F);
   public static final Block.SoundType soundTypeLadder = new Block.DefaultSoundType2("ladder", 1.0F, 1.0F);
   public static final Block.SoundType soundTypeAnvil = new Block.DefaultSoundType3("anvil", 0.3F, 1.0F);
   public static final Block.SoundType SLIME_SOUND = new Block.DefaultSoundType4("slime", 1.0F, 1.0F);
   public boolean fullBlock;
   public int lightOpacity;
   public boolean translucent;
   public int lightValue;
   public boolean useNeighborBrightness;
   public float blockHardness;
   public float blockResistance;
   public boolean enableStats = true;
   public boolean needsRandomTick;
   public boolean isBlockContainer;
   public double minX;
   public double minY;
   public double minZ;
   public double maxX;
   public double maxY;
   public double maxZ;
   public Block.SoundType stepSound = soundTypeStone;
   public float blockParticleGravity = 1.0F;
   public final Material blockMaterial;
   public final MapColor blockMapColor;
   public float slipperiness = 0.6F;
   public final BlockState blockState;
   private IBlockState defaultBlockState;
   private String unlocalizedName;

   public static Block getBlockById(int n) {
      return (Block)blockRegistry.getObjectById(n);
   }

   public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float f, int n) {
      if (!world.isRemote) {
         int n2 = this.quantityDroppedWithBonus(n, world.rand);

         for (int i = 0; i < n2; i++) {
            if (world.rand.nextFloat() <= f) {
               Item itemDropped = this.getItemDropped(state, world.rand, n);
               if (itemDropped != null) {
                  spawnAsEntity(world, pos, new ItemStack(itemDropped, 1, this.damageDropped(state)));
               }
            }
         }
      }
   }

   public int getComparatorInputOverride(World world, BlockPos pos) {
      return 0;
   }

   public boolean getUseNeighborBrightness() {
      return this.useNeighborBrightness;
   }

   public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
   }

   public final double getBlockBoundsMinY() {
      return this.minY;
   }

   public String getLocalizedName() {
      return StatCollector.translateToLocal(this.getUnlocalizedName() + ".name");
   }

   public final void setDefaultState(IBlockState state) {
      this.defaultBlockState = state;
   }

   public boolean canSpawnInBlock() {
      return !this.blockMaterial.isSolid() && !this.blockMaterial.isLiquid();
   }

   private boolean isVecInsideExpandedXYBounds(Vec3 vec) {
      return vec != null && vec.xCoord >= -0.3 && vec.xCoord <= 1.3 && vec.yCoord >= -0.3 && vec.yCoord <= 1.3;
   }

   public boolean isOpaqueCube() {
      return true;
   }

   public boolean hasComparatorInputOverride() {
      return false;
   }

   public static void spawnAsEntity(World world, BlockPos pos, ItemStack stack) {
      if (!world.isRemote && world.getGameRules().getBoolean("doTileDrops")) {
         float f = 0.5F;
         double d = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5;
         double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5;
         double d3 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5;
         EntityItem entityItem = new EntityItem(world, (double)pos.getX() + d, (double)pos.getY() + d2, (double)pos.getZ() + d3, stack);
         entityItem.setDefaultPickupDelay();
         world.spawnEntityInWorld(entityItem);
      }
   }

   public int getBlockColor() {
      return 16777215;
   }

   public void randomTick(World world, BlockPos pos, IBlockState state, Random rand) {
      this.updateTick(world, pos, state, rand);
   }

   public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tileEntity) {
      player.triggerAchievement(StatList.mineBlockStatArray[getIdFromBlock(this)]);
      player.addExhaustion(0.025F);
      if (this.canSilkHarvest() && EnchantmentHelper.getSilkTouchModifier(player)) {
         ItemStack stack = this.createStackedBlock(state);
         if (stack != null) {
            spawnAsEntity(world, pos, stack);
         }
      } else {
         this.dropBlockAsItem(world, pos, state, EnchantmentHelper.getFortuneModifier(player));
      }
   }

   private boolean isVecInsideYZBounds(Vec3 vec) {
      return vec != null && vec.yCoord >= this.minY && vec.yCoord <= this.maxY && vec.zCoord >= this.minZ && vec.zCoord <= this.maxZ;
   }

   public int colorMultiplier(IBlockAccess world, BlockPos pos, int n) {
      return 16777215;
   }

   public final IBlockState getDefaultState() {
      return this.defaultBlockState;
   }

   public void fillWithRain(World world, BlockPos pos) {
   }

   public final void setBlockBounds(float f, float f2, float f3, float f4, float f5, float f6) {
      this.minX = (double)f;
      this.minY = (double)f2;
      this.minZ = (double)f3;
      this.maxX = (double)f4;
      this.maxY = (double)f5;
      this.maxZ = (double)f6;
   }

   public int getMixedBrightnessForBlock(IBlockAccess world, BlockPos pos) {
      return this.getMixedBrightnessForBlock(world, pos.getX(), pos.getY(), pos.getZ());
   }

   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.SOLID;
   }

   public boolean getEnableStats() {
      return this.enableStats;
   }

   public void onLanded(World world, Entity entity) {
      entity.motionY = 0.0;
   }

   public Block setLightOpacity(int n) {
      this.lightOpacity = n;
      return this;
   }

   public float getBlockHardness(World world, BlockPos pos) {
      return this.blockHardness;
   }

   public static void registerBlocks() {
      registerBlock(0, AIR_ID, new BlockAir().setUnlocalizedName("air"));
      registerBlock(1, "stone", new BlockStone().setHardness(1.5F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("stone"));
      registerBlock(2, "grass", new BlockGrass().setHardness(0.6F).setStepSound(soundTypeGrass).setUnlocalizedName("grass"));
      registerBlock(3, "dirt", new BlockDirt().setHardness(0.5F).setStepSound(soundTypeGravel).setUnlocalizedName("dirt"));
      Block block = new Block(Material.rock)
         .setHardness(2.0F)
         .setResistance(10.0F)
         .setStepSound(soundTypePiston)
         .setUnlocalizedName("stonebrick")
         .setCreativeTab(CreativeTabs.tabBlock);
      registerBlock(4, "cobblestone", block);
      Block block2 = new BlockPlanks().setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("wood");
      registerBlock(5, "planks", block2);
      registerBlock(6, "sapling", new BlockSapling().setHardness(0.0F).setStepSound(soundTypeGrass).setUnlocalizedName("sapling"));
      registerBlock(
         7,
         "bedrock",
         new Block(Material.rock)
            .setBlockUnbreakable()
            .setResistance(6000000.0F)
            .setStepSound(soundTypePiston)
            .setUnlocalizedName("bedrock")
            .disableStats()
            .setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(8, "flowing_water", new BlockDynamicLiquid(Material.water).setHardness(100.0F).setLightOpacity(3).setUnlocalizedName("water").disableStats());
      registerBlock(9, "water", new BlockStaticLiquid(Material.water).setHardness(100.0F).setLightOpacity(3).setUnlocalizedName("water").disableStats());
      registerBlock(10, "flowing_lava", new BlockDynamicLiquid(Material.lava).setHardness(100.0F).setLightLevel(1.0F).setUnlocalizedName("lava").disableStats());
      registerBlock(11, "lava", new BlockStaticLiquid(Material.lava).setHardness(100.0F).setLightLevel(1.0F).setUnlocalizedName("lava").disableStats());
      registerBlock(12, "sand", new BlockSand().setHardness(0.5F).setStepSound(soundTypeSand).setUnlocalizedName("sand"));
      registerBlock(13, "gravel", new BlockGravel().setHardness(0.6F).setStepSound(soundTypeGravel).setUnlocalizedName("gravel"));
      registerBlock(14, "gold_ore", new BlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName("oreGold"));
      registerBlock(15, "iron_ore", new BlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName("oreIron"));
      registerBlock(16, "coal_ore", new BlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName("oreCoal"));
      registerBlock(17, "log", new BlockOldLog().setUnlocalizedName("log"));
      registerBlock(18, "leaves", new BlockOldLeaf().setUnlocalizedName("leaves"));
      registerBlock(19, "sponge", new BlockSponge().setHardness(0.6F).setStepSound(soundTypeGrass).setUnlocalizedName("sponge"));
      registerBlock(20, "glass", new BlockGlass(Material.glass, false).setHardness(0.3F).setStepSound(soundTypeGlass).setUnlocalizedName("glass"));
      registerBlock(21, "lapis_ore", new BlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName("oreLapis"));
      registerBlock(
         22,
         "lapis_block",
         new Block(Material.iron, MapColor.lapisColor)
            .setHardness(3.0F)
            .setResistance(5.0F)
            .setStepSound(soundTypePiston)
            .setUnlocalizedName("blockLapis")
            .setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(23, "dispenser", new BlockDispenser().setHardness(3.5F).setStepSound(soundTypePiston).setUnlocalizedName("dispenser"));
      Block block3 = new BlockSandStone().setStepSound(soundTypePiston).setHardness(0.8F).setUnlocalizedName("sandStone");
      registerBlock(24, "sandstone", block3);
      registerBlock(25, "noteblock", new BlockNote().setHardness(0.8F).setUnlocalizedName("musicBlock"));
      registerBlock(26, "bed", new BlockBed().setStepSound(soundTypeWood).setHardness(0.2F).setUnlocalizedName("bed").disableStats());
      registerBlock(27, "golden_rail", new BlockRailPowered().setHardness(0.7F).setStepSound(soundTypeMetal).setUnlocalizedName("goldenRail"));
      registerBlock(28, "detector_rail", new BlockRailDetector().setHardness(0.7F).setStepSound(soundTypeMetal).setUnlocalizedName("detectorRail"));
      registerBlock(29, "sticky_piston", new BlockPistonBase(true).setUnlocalizedName("pistonStickyBase"));
      registerBlock(30, "web", new BlockWeb().setLightOpacity(1).setHardness(4.0F).setUnlocalizedName("web"));
      registerBlock(31, "tallgrass", new BlockTallGrass().setHardness(0.0F).setStepSound(soundTypeGrass).setUnlocalizedName("tallgrass"));
      registerBlock(32, "deadbush", new BlockDeadBush().setHardness(0.0F).setStepSound(soundTypeGrass).setUnlocalizedName("deadbush"));
      registerBlock(33, "piston", new BlockPistonBase(false).setUnlocalizedName("pistonBase"));
      registerBlock(34, "piston_head", new BlockPistonExtension().setUnlocalizedName("pistonBase"));
      registerBlock(35, "wool", new BlockColored(Material.cloth).setHardness(0.8F).setStepSound(soundTypeCloth).setUnlocalizedName("cloth"));
      registerBlock(36, "piston_extension", new BlockPistonMoving());
      registerBlock(37, "yellow_flower", new BlockYellowFlower().setHardness(0.0F).setStepSound(soundTypeGrass).setUnlocalizedName("flower1"));
      registerBlock(38, "red_flower", new BlockRedFlower().setHardness(0.0F).setStepSound(soundTypeGrass).setUnlocalizedName("flower2"));
      Block block4 = new BlockMushroom().setHardness(0.0F).setStepSound(soundTypeGrass).setLightLevel(0.125F).setUnlocalizedName("mushroom");
      registerBlock(39, "brown_mushroom", block4);
      Block block5 = new BlockMushroom().setHardness(0.0F).setStepSound(soundTypeGrass).setUnlocalizedName("mushroom");
      registerBlock(40, "red_mushroom", block5);
      registerBlock(
         41,
         "gold_block",
         new Block(Material.iron, MapColor.goldColor)
            .setHardness(3.0F)
            .setResistance(10.0F)
            .setStepSound(soundTypeMetal)
            .setUnlocalizedName("blockGold")
            .setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(
         42,
         "iron_block",
         new Block(Material.iron, MapColor.ironColor)
            .setHardness(5.0F)
            .setResistance(10.0F)
            .setStepSound(soundTypeMetal)
            .setUnlocalizedName("blockIron")
            .setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(43, "double_stone_slab", new BlockDoubleStoneSlab().setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("stoneSlab"));
      registerBlock(44, "stone_slab", new BlockHalfStoneSlab().setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("stoneSlab"));
      Block block6 = new Block(Material.rock, MapColor.redColor)
         .setHardness(2.0F)
         .setResistance(10.0F)
         .setStepSound(soundTypePiston)
         .setUnlocalizedName("brick")
         .setCreativeTab(CreativeTabs.tabBlock);
      registerBlock(45, "brick_block", block6);
      registerBlock(46, "tnt", new BlockTNT().setHardness(0.0F).setStepSound(soundTypeGrass).setUnlocalizedName("tnt"));
      registerBlock(47, "bookshelf", new BlockBookshelf().setHardness(1.5F).setStepSound(soundTypeWood).setUnlocalizedName("bookshelf"));
      registerBlock(
         48,
         "mossy_cobblestone",
         new Block(Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("stoneMoss").setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(49, "obsidian", new BlockObsidian().setHardness(50.0F).setResistance(2000.0F).setStepSound(soundTypePiston).setUnlocalizedName("obsidian"));
      registerBlock(50, "torch", new BlockTorch().setHardness(0.0F).setLightLevel(0.9375F).setStepSound(soundTypeWood).setUnlocalizedName("torch"));
      registerBlock(51, "fire", new BlockFire().setHardness(0.0F).setLightLevel(1.0F).setStepSound(soundTypeCloth).setUnlocalizedName("fire").disableStats());
      registerBlock(52, "mob_spawner", new BlockMobSpawner().setHardness(5.0F).setStepSound(soundTypeMetal).setUnlocalizedName("mobSpawner").disableStats());
      registerBlock(53, "oak_stairs", new BlockStairs(block2.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK)).setUnlocalizedName("stairsWood"));
      registerBlock(54, "chest", new BlockChest(0).setHardness(2.5F).setStepSound(soundTypeWood).setUnlocalizedName("chest"));
      registerBlock(55, "redstone_wire", new BlockRedstoneWire().setHardness(0.0F).setStepSound(soundTypeStone).setUnlocalizedName("redstoneDust").disableStats());
      registerBlock(56, "diamond_ore", new BlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName("oreDiamond"));
      registerBlock(
         57,
         "diamond_block",
         new Block(Material.iron, MapColor.diamondColor)
            .setHardness(5.0F)
            .setResistance(10.0F)
            .setStepSound(soundTypeMetal)
            .setUnlocalizedName("blockDiamond")
            .setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(58, "crafting_table", new BlockWorkbench().setHardness(2.5F).setStepSound(soundTypeWood).setUnlocalizedName("workbench"));
      registerBlock(59, "wheat", new BlockCrops().setUnlocalizedName("crops"));
      Block block7 = new BlockFarmland().setHardness(0.6F).setStepSound(soundTypeGravel).setUnlocalizedName("farmland");
      registerBlock(60, "farmland", block7);
      registerBlock(
         61,
         "furnace",
         new BlockFurnace(false).setHardness(3.5F).setStepSound(soundTypePiston).setUnlocalizedName("furnace").setCreativeTab(CreativeTabs.tabDecorations)
      );
      registerBlock(62, "lit_furnace", new BlockFurnace(true).setHardness(3.5F).setStepSound(soundTypePiston).setLightLevel(0.875F).setUnlocalizedName("furnace"));
      registerBlock(63, "standing_sign", new BlockStandingSign().setHardness(1.0F).setStepSound(soundTypeWood).setUnlocalizedName("sign").disableStats());
      registerBlock(64, "wooden_door", new BlockDoor(Material.wood).setHardness(3.0F).setStepSound(soundTypeWood).setUnlocalizedName("doorOak").disableStats());
      registerBlock(65, "ladder", new BlockLadder().setHardness(0.4F).setStepSound(soundTypeLadder).setUnlocalizedName("ladder"));
      registerBlock(66, "rail", new BlockRail().setHardness(0.7F).setStepSound(soundTypeMetal).setUnlocalizedName("rail"));
      registerBlock(67, "stone_stairs", new BlockStairs(block.getDefaultState()).setUnlocalizedName("stairsStone"));
      registerBlock(68, "wall_sign", new BlockWallSign().setHardness(1.0F).setStepSound(soundTypeWood).setUnlocalizedName("sign").disableStats());
      registerBlock(69, "lever", new BlockLever().setHardness(0.5F).setStepSound(soundTypeWood).setUnlocalizedName("lever"));
      registerBlock(
         70,
         "stone_pressure_plate",
         new BlockPressurePlate(Material.rock, BlockPressurePlate.Sensitivity.MOBS).setHardness(0.5F).setStepSound(soundTypePiston).setUnlocalizedName("pressurePlateStone")
      );
      registerBlock(71, "iron_door", new BlockDoor(Material.iron).setHardness(5.0F).setStepSound(soundTypeMetal).setUnlocalizedName("doorIron").disableStats());
      registerBlock(
         72,
         "wooden_pressure_plate",
         new BlockPressurePlate(Material.wood, BlockPressurePlate.Sensitivity.EVERYTHING).setHardness(0.5F).setStepSound(soundTypeWood).setUnlocalizedName("pressurePlateWood")
      );
      registerBlock(
         73,
         "redstone_ore",
         new BlockRedstoneOre(false)
            .setHardness(3.0F)
            .setResistance(5.0F)
            .setStepSound(soundTypePiston)
            .setUnlocalizedName("oreRedstone")
            .setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(
         74, "lit_redstone_ore", new BlockRedstoneOre(true).setLightLevel(0.625F).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName("oreRedstone")
      );
      registerBlock(75, "unlit_redstone_torch", new BlockRedstoneTorch(false).setHardness(0.0F).setStepSound(soundTypeWood).setUnlocalizedName("notGate"));
      registerBlock(
         76,
         "redstone_torch",
         new BlockRedstoneTorch(true).setHardness(0.0F).setLightLevel(0.5F).setStepSound(soundTypeWood).setUnlocalizedName("notGate").setCreativeTab(CreativeTabs.tabRedstone)
      );
      registerBlock(77, "stone_button", new BlockButtonStone().setHardness(0.5F).setStepSound(soundTypePiston).setUnlocalizedName("button"));
      registerBlock(78, "snow_layer", new BlockSnow().setHardness(0.1F).setStepSound(soundTypeSnow).setUnlocalizedName("snow").setLightOpacity(0));
      registerBlock(79, "ice", new BlockIce().setHardness(0.5F).setLightOpacity(3).setStepSound(soundTypeGlass).setUnlocalizedName("ice"));
      registerBlock(80, "snow", new BlockSnowBlock().setHardness(0.2F).setStepSound(soundTypeSnow).setUnlocalizedName("snow"));
      registerBlock(81, "cactus", new BlockCactus().setHardness(0.4F).setStepSound(soundTypeCloth).setUnlocalizedName("cactus"));
      registerBlock(82, "clay", new BlockClay().setHardness(0.6F).setStepSound(soundTypeGravel).setUnlocalizedName("clay"));
      registerBlock(83, "reeds", new BlockReed().setHardness(0.0F).setStepSound(soundTypeGrass).setUnlocalizedName("reeds").disableStats());
      registerBlock(84, "jukebox", new BlockJukebox().setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("jukebox"));
      registerBlock(
         85, "fence", new BlockFence(Material.wood, BlockPlanks.EnumType.OAK.getMapColor()).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("fence")
      );
      Block block8 = new BlockPumpkin().setHardness(1.0F).setStepSound(soundTypeWood).setUnlocalizedName("pumpkin");
      registerBlock(86, "pumpkin", block8);
      registerBlock(87, "netherrack", new BlockNetherrack().setHardness(0.4F).setStepSound(soundTypePiston).setUnlocalizedName("hellrock"));
      registerBlock(88, "soul_sand", new BlockSoulSand().setHardness(0.5F).setStepSound(soundTypeSand).setUnlocalizedName("hellsand"));
      registerBlock(89, "glowstone", new BlockGlowstone(Material.glass).setHardness(0.3F).setStepSound(soundTypeGlass).setLightLevel(1.0F).setUnlocalizedName("lightgem"));
      registerBlock(90, "portal", new BlockPortal().setHardness(-1.0F).setStepSound(soundTypeGlass).setLightLevel(0.75F).setUnlocalizedName("portal"));
      registerBlock(91, "lit_pumpkin", new BlockPumpkin().setHardness(1.0F).setStepSound(soundTypeWood).setLightLevel(1.0F).setUnlocalizedName("litpumpkin"));
      registerBlock(92, "cake", new BlockCake().setHardness(0.5F).setStepSound(soundTypeCloth).setUnlocalizedName("cake").disableStats());
      registerBlock(93, "unpowered_repeater", new BlockRedstoneRepeater(false).setHardness(0.0F).setStepSound(soundTypeWood).setUnlocalizedName("diode").disableStats());
      registerBlock(94, "powered_repeater", new BlockRedstoneRepeater(true).setHardness(0.0F).setStepSound(soundTypeWood).setUnlocalizedName("diode").disableStats());
      registerBlock(95, "stained_glass", new BlockStainedGlass(Material.glass).setHardness(0.3F).setStepSound(soundTypeGlass).setUnlocalizedName("stainedGlass"));
      registerBlock(96, "trapdoor", new BlockTrapDoor(Material.wood).setHardness(3.0F).setStepSound(soundTypeWood).setUnlocalizedName("trapdoor").disableStats());
      registerBlock(97, "monster_egg", new BlockSilverfish().setHardness(0.75F).setUnlocalizedName("monsterStoneEgg"));
      Block block9 = new BlockStoneBrick().setHardness(1.5F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("stonebricksmooth");
      registerBlock(98, "stonebrick", block9);
      registerBlock(
         99,
         "brown_mushroom_block",
         new BlockHugeMushroom(Material.wood, MapColor.dirtColor, block4).setHardness(0.2F).setStepSound(soundTypeWood).setUnlocalizedName("mushroom")
      );
      registerBlock(
         100,
         "red_mushroom_block",
         new BlockHugeMushroom(Material.wood, MapColor.redColor, block5).setHardness(0.2F).setStepSound(soundTypeWood).setUnlocalizedName("mushroom")
      );
      registerBlock(101, "iron_bars", new BlockPane(Material.iron, true).setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeMetal).setUnlocalizedName("fenceIron"));
      registerBlock(102, "glass_pane", new BlockPane(Material.glass, false).setHardness(0.3F).setStepSound(soundTypeGlass).setUnlocalizedName("thinGlass"));
      Block block10 = new BlockMelon().setHardness(1.0F).setStepSound(soundTypeWood).setUnlocalizedName("melon");
      registerBlock(103, "melon_block", block10);
      registerBlock(104, "pumpkin_stem", new BlockStem(block8).setHardness(0.0F).setStepSound(soundTypeWood).setUnlocalizedName("pumpkinStem"));
      registerBlock(105, "melon_stem", new BlockStem(block10).setHardness(0.0F).setStepSound(soundTypeWood).setUnlocalizedName("pumpkinStem"));
      registerBlock(106, "vine", new BlockVine().setHardness(0.2F).setStepSound(soundTypeGrass).setUnlocalizedName("vine"));
      registerBlock(107, "fence_gate", new BlockFenceGate(BlockPlanks.EnumType.OAK).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("fenceGate"));
      registerBlock(108, "brick_stairs", new BlockStairs(block6.getDefaultState()).setUnlocalizedName("stairsBrick"));
      registerBlock(
         109,
         "stone_brick_stairs",
         new BlockStairs(block9.getDefaultState().withProperty(BlockStoneBrick.VARIANT, net.minecraft.block.BlockStoneBrick.EnumType.DEFAULT))
            .setUnlocalizedName("stairsStoneBrickSmooth")
      );
      registerBlock(110, "mycelium", new BlockMycelium().setHardness(0.6F).setStepSound(soundTypeGrass).setUnlocalizedName("mycel"));
      registerBlock(111, "waterlily", new BlockLilyPad().setHardness(0.0F).setStepSound(soundTypeGrass).setUnlocalizedName("waterlily"));
      Block block11 = new BlockNetherBrick()
         .setHardness(2.0F)
         .setResistance(10.0F)
         .setStepSound(soundTypePiston)
         .setUnlocalizedName("netherBrick")
         .setCreativeTab(CreativeTabs.tabBlock);
      registerBlock(112, "nether_brick", block11);
      registerBlock(
         113,
         "nether_brick_fence",
         new BlockFence(Material.rock, MapColor.netherrackColor).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("netherFence")
      );
      registerBlock(114, "nether_brick_stairs", new BlockStairs(block11.getDefaultState()).setUnlocalizedName("stairsNetherBrick"));
      registerBlock(115, "nether_wart", new BlockNetherWart().setUnlocalizedName("netherStalk"));
      registerBlock(116, "enchanting_table", new BlockEnchantmentTable().setHardness(5.0F).setResistance(2000.0F).setUnlocalizedName("enchantmentTable"));
      registerBlock(117, "brewing_stand", new BlockBrewingStand().setHardness(0.5F).setLightLevel(0.125F).setUnlocalizedName("brewingStand"));
      registerBlock(118, "cauldron", new BlockCauldron().setHardness(2.0F).setUnlocalizedName("cauldron"));
      registerBlock(119, "end_portal", new BlockEndPortal(Material.portal).setHardness(-1.0F).setResistance(6000000.0F));
      registerBlock(
         120,
         "end_portal_frame",
         new BlockEndPortalFrame()
            .setStepSound(soundTypeGlass)
            .setLightLevel(0.125F)
            .setHardness(-1.0F)
            .setUnlocalizedName("endPortalFrame")
            .setResistance(6000000.0F)
            .setCreativeTab(CreativeTabs.tabDecorations)
      );
      registerBlock(
         121,
         "end_stone",
         new Block(Material.rock, MapColor.sandColor)
            .setHardness(3.0F)
            .setResistance(15.0F)
            .setStepSound(soundTypePiston)
            .setUnlocalizedName("whiteStone")
            .setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(122, "dragon_egg", new BlockDragonEgg().setHardness(3.0F).setResistance(15.0F).setStepSound(soundTypePiston).setLightLevel(0.125F).setUnlocalizedName("dragonEgg"));
      registerBlock(
         123,
         "redstone_lamp",
         new BlockRedstoneLight(false).setHardness(0.3F).setStepSound(soundTypeGlass).setUnlocalizedName("redstoneLight").setCreativeTab(CreativeTabs.tabRedstone)
      );
      registerBlock(124, "lit_redstone_lamp", new BlockRedstoneLight(true).setHardness(0.3F).setStepSound(soundTypeGlass).setUnlocalizedName("redstoneLight"));
      registerBlock(125, "double_wooden_slab", new BlockDoubleWoodSlab().setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("woodSlab"));
      registerBlock(126, "wooden_slab", new BlockHalfWoodSlab().setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("woodSlab"));
      registerBlock(127, "cocoa", new BlockCocoa().setHardness(0.2F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("cocoa"));
      registerBlock(
         128,
         "sandstone_stairs",
         new BlockStairs(block3.getDefaultState().withProperty(BlockSandStone.TYPE, net.minecraft.block.BlockSandStone.EnumType.SMOOTH))
            .setUnlocalizedName("stairsSandStone")
      );
      registerBlock(129, "emerald_ore", new BlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName("oreEmerald"));
      registerBlock(
         130, "ender_chest", new BlockEnderChest().setHardness(22.5F).setResistance(1000.0F).setStepSound(soundTypePiston).setUnlocalizedName("enderChest").setLightLevel(0.5F)
      );
      registerBlock(131, "tripwire_hook", new BlockTripWireHook().setUnlocalizedName("tripWireSource"));
      registerBlock(132, "tripwire", new BlockTripWire().setUnlocalizedName("tripWire"));
      registerBlock(
         133,
         "emerald_block",
         new Block(Material.iron, MapColor.emeraldColor)
            .setHardness(5.0F)
            .setResistance(10.0F)
            .setStepSound(soundTypeMetal)
            .setUnlocalizedName("blockEmerald")
            .setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(
         134,
         "spruce_stairs",
         new BlockStairs(block2.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE)).setUnlocalizedName("stairsWoodSpruce")
      );
      registerBlock(
         135, "birch_stairs", new BlockStairs(block2.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.BIRCH)).setUnlocalizedName("stairsWoodBirch")
      );
      registerBlock(
         136,
         "jungle_stairs",
         new BlockStairs(block2.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.JUNGLE)).setUnlocalizedName("stairsWoodJungle")
      );
      registerBlock(137, "command_block", new BlockCommandBlock().setBlockUnbreakable().setResistance(6000000.0F).setUnlocalizedName("commandBlock"));
      registerBlock(138, "beacon", new BlockBeacon().setUnlocalizedName("beacon").setLightLevel(1.0F));
      registerBlock(139, "cobblestone_wall", new BlockWall(block).setUnlocalizedName("cobbleWall"));
      registerBlock(140, "flower_pot", new BlockFlowerPot().setHardness(0.0F).setStepSound(soundTypeStone).setUnlocalizedName("flowerPot"));
      registerBlock(141, "carrots", new BlockCarrot().setUnlocalizedName("carrots"));
      registerBlock(142, "potatoes", new BlockPotato().setUnlocalizedName("potatoes"));
      registerBlock(143, "wooden_button", new BlockButtonWood().setHardness(0.5F).setStepSound(soundTypeWood).setUnlocalizedName("button"));
      registerBlock(144, "skull", new BlockSkull().setHardness(1.0F).setStepSound(soundTypePiston).setUnlocalizedName("skull"));
      registerBlock(145, "anvil", new BlockAnvil().setHardness(5.0F).setStepSound(soundTypeAnvil).setResistance(2000.0F).setUnlocalizedName("anvil"));
      registerBlock(146, "trapped_chest", new BlockChest(1).setHardness(2.5F).setStepSound(soundTypeWood).setUnlocalizedName("chestTrap"));
      registerBlock(
         147,
         "light_weighted_pressure_plate",
         new BlockPressurePlateWeighted(Material.iron, 15, MapColor.goldColor).setHardness(0.5F).setStepSound(soundTypeWood).setUnlocalizedName("weightedPlate_light")
      );
      registerBlock(
         148,
         "heavy_weighted_pressure_plate",
         new BlockPressurePlateWeighted(Material.iron, 150).setHardness(0.5F).setStepSound(soundTypeWood).setUnlocalizedName("weightedPlate_heavy")
      );
      registerBlock(
         149, "unpowered_comparator", new BlockRedstoneComparator(false).setHardness(0.0F).setStepSound(soundTypeWood).setUnlocalizedName("comparator").disableStats()
      );
      registerBlock(
         150,
         "powered_comparator",
         new BlockRedstoneComparator(true).setHardness(0.0F).setLightLevel(0.625F).setStepSound(soundTypeWood).setUnlocalizedName("comparator").disableStats()
      );
      registerBlock(151, "daylight_detector", new BlockDaylightDetector(false));
      registerBlock(
         152,
         "redstone_block",
         new BlockCompressedPowered(Material.iron, MapColor.tntColor)
            .setHardness(5.0F)
            .setResistance(10.0F)
            .setStepSound(soundTypeMetal)
            .setUnlocalizedName("blockRedstone")
            .setCreativeTab(CreativeTabs.tabRedstone)
      );
      registerBlock(
         153, "quartz_ore", new BlockOre(MapColor.netherrackColor).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName("netherquartz")
      );
      registerBlock(154, "hopper", new BlockHopper().setHardness(3.0F).setResistance(8.0F).setStepSound(soundTypeMetal).setUnlocalizedName("hopper"));
      Block block12 = new BlockQuartz().setStepSound(soundTypePiston).setHardness(0.8F).setUnlocalizedName("quartzBlock");
      registerBlock(155, "quartz_block", block12);
      registerBlock(
         156,
         "quartz_stairs",
         new BlockStairs(block12.getDefaultState().withProperty(BlockQuartz.VARIANT, net.minecraft.block.BlockQuartz.EnumType.DEFAULT))
            .setUnlocalizedName("stairsQuartz")
      );
      registerBlock(157, "activator_rail", new BlockRailPowered().setHardness(0.7F).setStepSound(soundTypeMetal).setUnlocalizedName("activatorRail"));
      registerBlock(158, "dropper", new BlockDropper().setHardness(3.5F).setStepSound(soundTypePiston).setUnlocalizedName("dropper"));
      registerBlock(
         159,
         "stained_hardened_clay",
         new BlockColored(Material.rock).setHardness(1.25F).setResistance(7.0F).setStepSound(soundTypePiston).setUnlocalizedName("clayHardenedStained")
      );
      registerBlock(160, "stained_glass_pane", new BlockStainedGlassPane().setHardness(0.3F).setStepSound(soundTypeGlass).setUnlocalizedName("thinStainedGlass"));
      registerBlock(161, "leaves2", new BlockNewLeaf().setUnlocalizedName("leaves"));
      registerBlock(162, "log2", new BlockNewLog().setUnlocalizedName("log"));
      registerBlock(
         163,
         "acacia_stairs",
         new BlockStairs(block2.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA)).setUnlocalizedName("stairsWoodAcacia")
      );
      registerBlock(
         164,
         "dark_oak_stairs",
         new BlockStairs(block2.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.DARK_OAK)).setUnlocalizedName("stairsWoodDarkOak")
      );
      registerBlock(165, "slime", new BlockSlime().setUnlocalizedName("slime").setStepSound(SLIME_SOUND));
      registerBlock(166, "barrier", new BlockBarrier().setUnlocalizedName("barrier"));
      registerBlock(167, "iron_trapdoor", new BlockTrapDoor(Material.iron).setHardness(5.0F).setStepSound(soundTypeMetal).setUnlocalizedName("ironTrapdoor").disableStats());
      registerBlock(168, "prismarine", new BlockPrismarine().setHardness(1.5F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("prismarine"));
      registerBlock(169, "sea_lantern", new BlockSeaLantern(Material.glass).setHardness(0.3F).setStepSound(soundTypeGlass).setLightLevel(1.0F).setUnlocalizedName("seaLantern"));
      registerBlock(
         170, "hay_block", new BlockHay().setHardness(0.5F).setStepSound(soundTypeGrass).setUnlocalizedName("hayBlock").setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(171, "carpet", new BlockCarpet().setHardness(0.1F).setStepSound(soundTypeCloth).setUnlocalizedName("woolCarpet").setLightOpacity(0));
      registerBlock(172, "hardened_clay", new BlockHardenedClay().setHardness(1.25F).setResistance(7.0F).setStepSound(soundTypePiston).setUnlocalizedName("clayHardened"));
      registerBlock(
         173,
         "coal_block",
         new Block(Material.rock, MapColor.blackColor)
            .setHardness(5.0F)
            .setResistance(10.0F)
            .setStepSound(soundTypePiston)
            .setUnlocalizedName("blockCoal")
            .setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(174, "packed_ice", new BlockPackedIce().setHardness(0.5F).setStepSound(soundTypeGlass).setUnlocalizedName("icePacked"));
      registerBlock(175, "double_plant", new BlockDoublePlant());
      registerBlock(176, "standing_banner", new BlockBanner.BlockBannerStanding().setHardness(1.0F).setStepSound(soundTypeWood).setUnlocalizedName("banner").disableStats());
      registerBlock(177, "wall_banner", new BlockBanner.BlockBannerHanging().setHardness(1.0F).setStepSound(soundTypeWood).setUnlocalizedName("banner").disableStats());
      registerBlock(178, "daylight_detector_inverted", new BlockDaylightDetector(true));
      Block block13 = new BlockRedSandstone().setStepSound(soundTypePiston).setHardness(0.8F).setUnlocalizedName("redSandStone");
      registerBlock(179, "red_sandstone", block13);
      registerBlock(
         180,
         "red_sandstone_stairs",
         new BlockStairs(block13.getDefaultState().withProperty(BlockRedSandstone.TYPE, net.minecraft.block.BlockRedSandstone.EnumType.SMOOTH))
            .setUnlocalizedName("stairsRedSandStone")
      );
      registerBlock(
         181, "double_stone_slab2", new BlockDoubleStoneSlabNew().setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("stoneSlab2")
      );
      registerBlock(182, "stone_slab2", new BlockHalfStoneSlabNew().setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("stoneSlab2"));
      registerBlock(
         183, "spruce_fence_gate", new BlockFenceGate(BlockPlanks.EnumType.SPRUCE).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("spruceFenceGate")
      );
      registerBlock(
         184, "birch_fence_gate", new BlockFenceGate(BlockPlanks.EnumType.BIRCH).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("birchFenceGate")
      );
      registerBlock(
         185, "jungle_fence_gate", new BlockFenceGate(BlockPlanks.EnumType.JUNGLE).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("jungleFenceGate")
      );
      registerBlock(
         186,
         "dark_oak_fence_gate",
         new BlockFenceGate(BlockPlanks.EnumType.DARK_OAK).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("darkOakFenceGate")
      );
      registerBlock(
         187, "acacia_fence_gate", new BlockFenceGate(BlockPlanks.EnumType.ACACIA).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("acaciaFenceGate")
      );
      registerBlock(
         188,
         "spruce_fence",
         new BlockFence(Material.wood, BlockPlanks.EnumType.SPRUCE.getMapColor()).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("spruceFence")
      );
      registerBlock(
         189,
         "birch_fence",
         new BlockFence(Material.wood, BlockPlanks.EnumType.BIRCH.getMapColor()).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("birchFence")
      );
      registerBlock(
         190,
         "jungle_fence",
         new BlockFence(Material.wood, BlockPlanks.EnumType.JUNGLE.getMapColor()).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("jungleFence")
      );
      registerBlock(
         191,
         "dark_oak_fence",
         new BlockFence(Material.wood, BlockPlanks.EnumType.DARK_OAK.getMapColor()).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("darkOakFence")
      );
      registerBlock(
         192,
         "acacia_fence",
         new BlockFence(Material.wood, BlockPlanks.EnumType.ACACIA.getMapColor()).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("acaciaFence")
      );
      registerBlock(193, "spruce_door", new BlockDoor(Material.wood).setHardness(3.0F).setStepSound(soundTypeWood).setUnlocalizedName("doorSpruce").disableStats());
      registerBlock(194, "birch_door", new BlockDoor(Material.wood).setHardness(3.0F).setStepSound(soundTypeWood).setUnlocalizedName("doorBirch").disableStats());
      registerBlock(195, "jungle_door", new BlockDoor(Material.wood).setHardness(3.0F).setStepSound(soundTypeWood).setUnlocalizedName("doorJungle").disableStats());
      registerBlock(196, "acacia_door", new BlockDoor(Material.wood).setHardness(3.0F).setStepSound(soundTypeWood).setUnlocalizedName("doorAcacia").disableStats());
      registerBlock(197, "dark_oak_door", new BlockDoor(Material.wood).setHardness(3.0F).setStepSound(soundTypeWood).setUnlocalizedName("doorDarkOak").disableStats());
      blockRegistry.validateKey();

      for (Block block14 : (Iterable<Block>)(blockRegistry)) {
         if (block14.blockMaterial == Material.air) {
            block14.useNeighborBrightness = false;
         } else {
            boolean flag = false;
            boolean flag2 = block14 instanceof BlockStairs;
            boolean flag3 = block14 instanceof BlockSlab;
            boolean flag4 = block14 == block7;
            boolean translucent2 = block14.translucent;
            boolean flag5 = block14.lightOpacity == 0;
            if (flag2 || flag3 || flag4 || translucent2 || flag5) {
               flag = true;
            }

            block14.useNeighborBrightness = flag;
         }
      }

      for (Block block15 : (Iterable<Block>)(blockRegistry)) {
         UnmodifiableIterator it = block15.getBlockState().getValidStates().iterator();

         while (it.hasNext()) {
            IBlockState state = (IBlockState)it.next();
            int n = blockRegistry.getIDForObject(block15) << 4 | block15.getMetaFromState(state);
            BLOCK_STATE_IDS.put(state, n);
         }
      }
   }

   public final double getBlockBoundsMinX() {
      return this.minX;
   }

   public boolean hasTileEntity() {
      return this.isBlockContainer;
   }

   public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
   }

   public boolean canCollideCheck(IBlockState state, boolean flag, boolean flag2) {
      return this.isCollidable();
   }

   public boolean canSilkHarvest() {
      return this.isFullCube() && !this.isBlockContainer;
   }

   public boolean hasCollisionBox() {
      return true;
   }

   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Item.getItemFromBlock(this);
   }

   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      return this.getStateFromMeta(n);
   }

   public MovingObjectPosition collisionRayTraceExpanded(World world, BlockPos pos, double d, double d2, double d3, double d4, double d5, double d6) {
      this.setBlockBoundsBasedOnState(world, pos.getX(), pos.getY(), pos.getZ());
      if (!FullBlockRaytraceFilter.INSTANCE.isBlockRaytraceable(this)) {
         return null;
      } else {
         d -= (double)pos.getX();
         d2 -= (double)pos.getY();
         d3 -= (double)pos.getZ();
         d4 -= (double)pos.getX();
         d5 -= (double)pos.getY();
         d6 -= (double)pos.getZ();
         double d7 = d4 - d;
         double d8 = d5 - d2;
         double d9 = d6 - d3;
         Vec3 intermediateWithXValue = this.getIntermediateWithXValue(d, d2, d3, 0.0, d7, d8, d9);
         Vec3 intermediateWithXValue2 = this.getIntermediateWithXValue(d, d2, d3, 1.0, d7, d8, d9);
         Vec3 intermediateWithYValue = this.getIntermediateWithYValue(d, d2, d3, 0.0, d7, d8, d9);
         Vec3 intermediateWithYValue2 = this.getIntermediateWithYValue(d, d2, d3, 1.0, d7, d8, d9);
         Vec3 intermediateWithZValue = this.getIntermediateWithZValue(d, d2, d3, 0.0, d7, d8, d9);
         Vec3 intermediateWithZValue2 = this.getIntermediateWithZValue(d, d2, d3, 1.0, d7, d8, d9);
         if (!this.isVecInsideExpandedYZBounds(intermediateWithXValue)) {
            intermediateWithXValue = null;
         }

         if (!this.isVecInsideExpandedYZBounds(intermediateWithXValue2)) {
            intermediateWithXValue2 = null;
         }

         if (!this.isVecInsideExpandedXZBounds(intermediateWithYValue)) {
            intermediateWithYValue = null;
         }

         if (!this.isVecInsideExpandedXZBounds(intermediateWithYValue2)) {
            intermediateWithYValue2 = null;
         }

         if (!this.isVecInsideExpandedXYBounds(intermediateWithZValue)) {
            intermediateWithZValue = null;
         }

         if (!this.isVecInsideExpandedXYBounds(intermediateWithZValue2)) {
            intermediateWithZValue2 = null;
         }

         Vec3 vec = null;
         if (intermediateWithXValue != null && (vec == null || intermediateWithXValue.squareDistanceTo(d, d2, d3) < vec.squareDistanceTo(d, d2, d3))) {
            vec = intermediateWithXValue;
         }

         if (intermediateWithXValue2 != null && (vec == null || intermediateWithXValue2.squareDistanceTo(d, d2, d3) < vec.squareDistanceTo(d, d2, d3))) {
            vec = intermediateWithXValue2;
         }

         if (intermediateWithYValue != null && (vec == null || intermediateWithYValue.squareDistanceTo(d, d2, d3) < vec.squareDistanceTo(d, d2, d3))) {
            vec = intermediateWithYValue;
         }

         if (intermediateWithYValue2 != null && (vec == null || intermediateWithYValue2.squareDistanceTo(d, d2, d3) < vec.squareDistanceTo(d, d2, d3))) {
            vec = intermediateWithYValue2;
         }

         if (intermediateWithZValue != null && (vec == null || intermediateWithZValue.squareDistanceTo(d, d2, d3) < vec.squareDistanceTo(d, d2, d3))) {
            vec = intermediateWithZValue;
         }

         if (intermediateWithZValue2 != null && (vec == null || intermediateWithZValue2.squareDistanceTo(d, d2, d3) < vec.squareDistanceTo(d, d2, d3))) {
            vec = intermediateWithZValue2;
         }

         if (vec == null) {
            return null;
         } else {
            EnumFacing facing = null;
            if (vec == intermediateWithXValue) {
               facing = EnumFacing.WEST;
            }

            if (vec == intermediateWithXValue2) {
               facing = EnumFacing.EAST;
            }

            if (vec == intermediateWithYValue) {
               facing = EnumFacing.DOWN;
            }

            if (vec == intermediateWithYValue2) {
               facing = EnumFacing.UP;
            }

            if (vec == intermediateWithZValue) {
               facing = EnumFacing.NORTH;
            }

            if (vec == intermediateWithZValue2) {
               facing = EnumFacing.SOUTH;
            }

            return new MovingObjectPosition(vec.addVector((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()), facing, pos);
         }
      }
   }

   public void onFallenUpon(World world, BlockPos pos, Entity entity, float f) {
      entity.fall(f, 1.0F);
   }

   public boolean isFullBlock() {
      return this.fullBlock;
   }

   public Block setStepSound(Block.SoundType soundType) {
      this.stepSound = soundType;
      return this;
   }

   public final double getBlockBoundsMaxX() {
      return this.maxX;
   }

   @Override
   public String toString() {
      return "Block{" + blockRegistry.getNameForObject(this) + "}";
   }

   public boolean isFullCube() {
      return true;
   }

   public final void setBlockBounds(AxisAlignedBB box) {
      this.minX = box.minX;
      this.minY = box.minY;
      this.minZ = box.minZ;
      this.maxX = box.maxX;
      this.maxY = box.maxY;
      this.maxZ = box.maxZ;
   }

   public Block disableStats() {
      this.enableStats = false;
      return this;
   }

   public int damageDropped(IBlockState state) {
      return 0;
   }

   public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
   }

   private boolean isVecInsideXZBounds(Vec3 vec) {
      return vec != null && vec.xCoord >= this.minX && vec.xCoord <= this.maxX && vec.zCoord >= this.minZ && vec.zCoord <= this.maxZ;
   }

   public IBlockState getStateForEntityRender(IBlockState state) {
      return state;
   }

   public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
   }

   public static int getIdFromBlock(Block block) {
      return blockRegistry.getIDForObject(block);
   }

   public Material getMaterial() {
      return this.blockMaterial;
   }

   public boolean isCollidable() {
      return true;
   }

   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return new AxisAlignedBB(
         (double)n + this.minX,
         (double)n2 + this.minY,
         (double)n3 + this.minZ,
         (double)n + this.maxX,
         (double)n2 + this.maxY,
         (double)n3 + this.maxZ
      );
   }

   public int getStrongPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      return 0;
   }

   public boolean isVisuallyOpaque() {
      return this.blockMaterial.blocksMovement() && this.isFullCube();
   }

   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return false;
   }

   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      list.add(new ItemStack(item, 1, 0));
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      return state;
   }

   public Block setHardness(float f) {
      this.blockHardness = f;
      if (this.blockResistance < f * 5.0F) {
         this.blockResistance = f * 5.0F;
      }

      return this;
   }

   public void setBlockBoundsForItemRender() {
   }

   public Block setTickRandomly(boolean flag) {
      this.needsRandomTick = flag;
      return this;
   }

   public MapColor getMapColor(IBlockState state) {
      return this.blockMapColor;
   }

   public boolean requiresUpdates() {
      return true;
   }

   public final double getBlockBoundsMaxY() {
      return this.maxY;
   }

   public boolean isTranslucent() {
      return this.translucent;
   }

   public Vec3 modifyAcceleration(World world, BlockPos pos, Entity entity, Vec3 vec) {
      return vec;
   }

   public final void setBlockBounds2(double d, double d2, double d3, double d4, double d5, double d6) {
      this.minX = d;
      this.minY = d2;
      this.minZ = d3;
      this.maxX = d4;
      this.maxY = d5;
      this.maxZ = d6;
   }

   public boolean canDropFromExplosion(Explosion explosion) {
      return true;
   }

   public Block setLightLevel(float f) {
      this.lightValue = (int)(15.0F * f);
      return this;
   }

   public float getAmbientOcclusionLightValue() {
      if (XRay.enabled) {
         return 1.0F;
      } else {
         return this.isBlockNormalCube() ? 0.2F : 1.0F;
      }
   }

   public Block setCreativeTab(CreativeTabs creativeTabs) {
      this.displayOnCreativeTab = creativeTabs;
      return this;
   }

   public Vec3 getIntermediateWithYValue(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
      if (d6 * d6 < 1.0000000116860974E-7) {
         return null;
      } else {
         double d8 = (d4 - d2) / d6;
         return d8 >= 0.0 && d8 <= 1.0 ? new Vec3(d + d5 * d8, d2 + d6 * d8, d3 + d7 * d8) : null;
      }
   }

   public static Block getBlockFromName(String str) {
      ResourceLocation location = new ResourceLocation(str);
      if (blockRegistry.containsKey(location)) {
         return (Block)blockRegistry.getObject(location);
      } else {
         try {
            return (Block)blockRegistry.getObjectById(Integer.parseInt(str));
         } catch (NumberFormatException e) {
            return null;
         }
      }
   }

   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return false;
   }

   public int getWeakPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      return 0;
   }

   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
   }

   public Item getItem(World world, BlockPos pos) {
      return Item.getItemFromBlock(this);
   }

   public int getMobilityFlag() {
      return this.blockMaterial.getMaterialMobility();
   }

   public void onBlockDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
   }

   public Block setResistance(float f) {
      this.blockResistance = f * 3.0F;
      return this;
   }

   public boolean isReplaceable(World world, BlockPos pos) {
      return false;
   }

   private boolean isVecInsideExpandedYZBounds(Vec3 vec) {
      return vec != null && vec.yCoord >= -0.3 && vec.yCoord <= 1.3 && vec.zCoord >= -0.3 && vec.zCoord <= 1.3;
   }

   public Block(Material material) {
      this(material, material.getMaterialMapColor());
   }

   public static Block getBlockFromItem(Item item) {
      return item instanceof ItemBlock ? ((ItemBlock)item).getBlock() : null;
   }

   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
   }

   public MovingObjectPosition collisionRayTrace(World world, int n, int n2, int n3, double d, double d2, double d3, double d4, double d5, double d6) {
      this.setBlockBoundsBasedOnState(world, n, n2, n3);
      if (!FullBlockRaytraceFilter.INSTANCE.isBlockRaytraceable(this)) {
         return null;
      } else {
         d -= (double)n;
         d2 -= (double)n2;
         d5 -= (double)n3;
         d6 -= (double)n;
         d3 -= (double)n2;
         d4 -= (double)n3;
         double d7 = d6 - d;
         double d8 = d3 - d2;
         double d9 = d4 - d5;
         Vec3 intermediateWithXValue = this.getIntermediateWithXValue(d, d2, d5, this.minX, d7, d8, d9);
         Vec3 intermediateWithXValue2 = this.getIntermediateWithXValue(d, d2, d5, this.maxX, d7, d8, d9);
         Vec3 intermediateWithYValue = this.getIntermediateWithYValue(d, d2, d5, this.minY, d7, d8, d9);
         Vec3 intermediateWithYValue2 = this.getIntermediateWithYValue(d, d2, d5, this.maxY, d7, d8, d9);
         Vec3 intermediateWithZValue = this.getIntermediateWithZValue(d, d2, d5, this.minZ, d7, d8, d9);
         Vec3 intermediateWithZValue2 = this.getIntermediateWithZValue(d, d2, d5, this.maxZ, d7, d8, d9);
         if (!this.isVecInsideYZBounds(intermediateWithXValue)) {
            intermediateWithXValue = null;
         }

         if (!this.isVecInsideYZBounds(intermediateWithXValue2)) {
            intermediateWithXValue2 = null;
         }

         if (!this.isVecInsideXZBounds(intermediateWithYValue)) {
            intermediateWithYValue = null;
         }

         if (!this.isVecInsideXZBounds(intermediateWithYValue2)) {
            intermediateWithYValue2 = null;
         }

         if (!this.isVecInsideXYBounds(intermediateWithZValue)) {
            intermediateWithZValue = null;
         }

         if (!this.isVecInsideXYBounds(intermediateWithZValue2)) {
            intermediateWithZValue2 = null;
         }

         Vec3 vec = null;
         if (intermediateWithXValue != null && (vec == null || intermediateWithXValue.squareDistanceTo(d, d2, d5) < vec.squareDistanceTo(d, d2, d5))) {
            vec = intermediateWithXValue;
         }

         if (intermediateWithXValue2 != null && (vec == null || intermediateWithXValue2.squareDistanceTo(d, d2, d5) < vec.squareDistanceTo(d, d2, d5))) {
            vec = intermediateWithXValue2;
         }

         if (intermediateWithYValue != null && (vec == null || intermediateWithYValue.squareDistanceTo(d, d2, d5) < vec.squareDistanceTo(d, d2, d5))) {
            vec = intermediateWithYValue;
         }

         if (intermediateWithYValue2 != null && (vec == null || intermediateWithYValue2.squareDistanceTo(d, d2, d5) < vec.squareDistanceTo(d, d2, d5))) {
            vec = intermediateWithYValue2;
         }

         if (intermediateWithZValue != null && (vec == null || intermediateWithZValue.squareDistanceTo(d, d2, d5) < vec.squareDistanceTo(d, d2, d5))) {
            vec = intermediateWithZValue;
         }

         if (intermediateWithZValue2 != null && (vec == null || intermediateWithZValue2.squareDistanceTo(d, d2, d5) < vec.squareDistanceTo(d, d2, d5))) {
            vec = intermediateWithZValue2;
         }

         if (vec == null) {
            return null;
         } else {
            EnumFacing facing = null;
            if (vec == intermediateWithXValue) {
               facing = EnumFacing.WEST;
            }

            if (vec == intermediateWithXValue2) {
               facing = EnumFacing.EAST;
            }

            if (vec == intermediateWithYValue) {
               facing = EnumFacing.DOWN;
            }

            if (vec == intermediateWithYValue2) {
               facing = EnumFacing.UP;
            }

            if (vec == intermediateWithZValue) {
               facing = EnumFacing.NORTH;
            }

            if (vec == intermediateWithZValue2) {
               facing = EnumFacing.SOUTH;
            }

            vec.xCoord += (double)n;
            vec.yCoord += (double)n2;
            vec.zCoord += (double)n3;
            return new MovingObjectPosition(MovingObjectPosition.MovingObjectType.BLOCK, vec, facing, new BlockPos(n, n2, n3), true);
         }
      }
   }

   public static IBlockState getStateById(int n) {
      int n2 = n & 4095;
      int n3 = n >> 12 & 15;
      return getBlockById(n2).getStateFromMeta(n3);
   }

   public final double getBlockBoundsMaxZ() {
      return this.maxZ;
   }

   public Block setBlockUnbreakable() {
      this.setHardness(-1.0F);
      return this;
   }

   public static boolean isEqualTo(Block block, Block block2) {
      return block != null && block2 != null ? (block == block2 ? true : block.isAssociatedBlock(block2)) : false;
   }

   public boolean isBlockSolid(IBlockAccess world, BlockPos pos, EnumFacing facing) {
      return world.getBlockState(pos).getBlock().getMaterial().isSolid();
   }

   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState();
   }

   public int getDamageValue(World world, BlockPos pos) {
      return this.damageDropped(world.getBlockState(pos));
   }

   public int tickRate(World world) {
      return 10;
   }

   public BlockState getBlockState() {
      return this.blockState;
   }

   public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity) {
   }

   public boolean isPassable(IBlockAccess world, int n, int n2, int n3) {
      return !this.blockMaterial.blocksMovement();
   }

   public float getExplosionResistance(Entity entity) {
      return this.blockResistance / 5.0F;
   }

   public boolean getTickRandomly() {
      return this.needsRandomTick;
   }

   private static void registerBlock(int n, String str, Block block) {
      registerBlock(n, new ResourceLocation(str), block);
   }

   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return world.getBlockState(pos).getBlock().blockMaterial.isReplaceable();
   }

   public boolean canProvidePower() {
      return false;
   }

   public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos) {
      return new AxisAlignedBB(
         (double)pos.getX() + this.minX,
         (double)pos.getY() + this.minY,
         (double)pos.getZ() + this.minZ,
         (double)pos.getX() + this.maxX,
         (double)pos.getY() + this.maxY,
         (double)pos.getZ() + this.maxZ
      );
   }

   public int getMixedBrightnessForBlock(IBlockAccess world, int n, int n2, int n3) {
      Block block = world.getBlockState2(n, n2, n3).getBlock();
      int combinedLight = world.getCombinedLight(n, n2, n3, block.getLightValue());
      if (combinedLight == 0 && block instanceof BlockSlab) {
         block = world.getBlockState2(n, n2 - 1, n3).getBlock();
         return world.getCombinedLight(n, n2 - 1, n3, block.getLightValue());
      } else {
         return combinedLight;
      }
   }

   public int getRenderColor(IBlockState state) {
      return 16777215;
   }

   public boolean onBlockEventReceived(World world, BlockPos pos, IBlockState state, int n, int n2) {
      return false;
   }

   public boolean isFlowerPot() {
      return false;
   }

   public boolean isNormalCube() {
      return this.blockMaterial.isOpaque() && this.isFullCube() && !this.canProvidePower();
   }

   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[0]);
   }

   public Vec3 getIntermediateWithXValue(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
      if (d5 * d5 < 1.0000000116860974E-7) {
         return null;
      } else {
         double d8 = (d4 - d) / d5;
         return d8 >= 0.0 && d8 <= 1.0 ? new Vec3(d + d5 * d8, d2 + d6 * d8, d3 + d7 * d8) : null;
      }
   }

   public Block setUnlocalizedName(String str) {
      this.unlocalizedName = str;
      return this;
   }

   public Block.EnumOffsetType getOffsetType() {
      return Block.EnumOffsetType.NONE;
   }

   public int getMetaFromState(IBlockState state) {
      if (state != null && !state.getPropertyNames().isEmpty()) {
         throw new IllegalArgumentException("Don't know how to convert " + state + " back into data...");
      } else {
         return 0;
      }
   }

   public void breakBlock(World world, BlockPos pos, IBlockState state) {
   }

   public String getUnlocalizedName() {
      return "tile." + this.unlocalizedName;
   }

   public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing facing) {
      return this.canPlaceBlockAt(world, pos);
   }

   public Vec3 getIntermediateWithZValue(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
      if (d7 * d7 < 1.0000000116860974E-7) {
         return null;
      } else {
         double d8 = (d4 - d3) / d7;
         return d8 >= 0.0 && d8 <= 1.0 ? new Vec3(d + d5 * d8, d2 + d6 * d8, d3 + d7 * d8) : null;
      }
   }

   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
   }

   public int getRenderType() {
      return 3;
   }

   public static int getStateId(IBlockState state) {
      Block block = state.getBlock();
      return getIdFromBlock(block) + (block.getMetaFromState(state) << 12);
   }

   public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
   }

   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
   }

   public Block(Material material, MapColor mapColor) {
      this.blockMaterial = material;
      this.blockMapColor = mapColor;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      this.fullBlock = this.isOpaqueCube();
      this.lightOpacity = this.isOpaqueCube() ? 255 : 0;
      this.translucent = !material.blocksLight();
      this.blockState = this.createBlockState();
      this.setDefaultState(this.blockState.getBaseState());
   }

   public final double getBlockBoundsMinZ() {
      return this.minZ;
   }

   public int quantityDropped(Random rand) {
      return 1;
   }

   public boolean canReplace(World world, BlockPos pos, EnumFacing facing, ItemStack stack) {
      return this.canPlaceBlockOnSide(world, pos, facing);
   }

   public int getLightOpacity() {
      return this.lightOpacity;
   }

   public final int colorMultiplier(IBlockAccess world, BlockPos pos) {
      return this.colorMultiplier(world, pos, 0);
   }

   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      return facing == EnumFacing.DOWN && this.minY > 0.0
         || facing == EnumFacing.UP && this.maxY < 1.0
         || facing == EnumFacing.NORTH && this.minZ > 0.0
         || facing == EnumFacing.SOUTH && this.maxZ < 1.0
         || facing == EnumFacing.WEST && this.minX > 0.0
         || facing == EnumFacing.EAST && this.maxX < 1.0
         || !world.getBlockState2(n, n2, n3).getBlock().isOpaqueCube();
   }

   public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB box, List list, Entity entity) {
      AxisAlignedBB collisionBoundingBox = this.getCollisionBoundingBox(world, state, pos.getX(), pos.getY(), pos.getZ());
      collisionBoundingBox = BlockBoundingBoxListener.onBlockBoundingBox(collisionBoundingBox, world.getBlockState(pos).getBlock(), pos).getBoundingBox();
      if (collisionBoundingBox != null && box.intersectsWith(collisionBoundingBox)) {
         list.add(collisionBoundingBox);
      }
   }

   public boolean isBlockNormalCube() {
      return this.blockMaterial.blocksMovement() && this.isFullCube();
   }

   public final void dropBlockAsItem(World world, BlockPos pos, IBlockState state, int n) {
      this.dropBlockAsItemWithChance(world, pos, state, 1.0F, n);
   }

   private static void registerBlock(int n, ResourceLocation location, Block block) {
      blockRegistry.register(n, location, block);
   }

   public boolean canCollideCheck(IBlockState state, boolean flag) {
      return this.isCollidable();
   }

   public void dropXpOnBlockBreak(World world, BlockPos pos, int n) {
      if (!world.isRemote) {
         while (n > 0) {
            int xpSplit = EntityXPOrb.getXPSplit(n);
            n -= xpSplit;
            world.spawnEntityInWorld(new EntityXPOrb(world, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, xpSplit));
         }
      }
   }

   public boolean isPassable(IBlockAccess world, BlockPos pos) {
      return !this.blockMaterial.blocksMovement();
   }

   public int quantityDroppedWithBonus(int n, Random rand) {
      return this.quantityDropped(rand);
   }

   public CreativeTabs getCreativeTabToDisplayOn() {
      return this.displayOnCreativeTab;
   }

   private boolean isVecInsideXYBounds(Vec3 vec) {
      return vec != null && vec.xCoord >= this.minX && vec.xCoord <= this.maxX && vec.yCoord >= this.minY && vec.yCoord <= this.maxY;
   }

   public int getLightValue() {
      return this.lightValue;
   }

   private boolean isVecInsideExpandedXZBounds(Vec3 vec) {
      return vec != null && vec.xCoord >= -0.3 && vec.xCoord <= 1.3 && vec.zCoord >= -0.3 && vec.zCoord <= 1.3;
   }

   public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, BlockPos pos) {
      float blockHardness2 = this.getBlockHardness(world, pos);
      return blockHardness2 < 0.0F
         ? 0.0F
         : (!player.canHarvestBlock(this) ? player.getToolDigEfficiency(this) / blockHardness2 / 100.0F : player.getToolDigEfficiency(this) / blockHardness2 / 30.0F);
   }

   public boolean isAssociatedBlock(Block block) {
      return this == block;
   }

   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
   }

   public ItemStack createStackedBlock(IBlockState state) {
      int n = 0;
      Item itemFromBlock = Item.getItemFromBlock(this);
      if (itemFromBlock != null && itemFromBlock.getHasSubtypes()) {
         n = this.getMetaFromState(state);
      }

      return new ItemStack(itemFromBlock, 1, n);
   }



   public static final class DefaultSoundType extends Block.SoundType {

      public DefaultSoundType(String str, float f, float f2) {
         super(str, f, f2);
      }

      @Override
      public String getPlaceSound() {
         return "step.stone";
      }

      @Override
      public String getBreakSound() {
         return "dig.glass";
      }
   }

   public static final class DefaultSoundType2 extends Block.SoundType {
      public DefaultSoundType2(String str, float f, float f2) {
         super(str, f, f2);
      }

      @Override
      public String getBreakSound() {
         return "dig.wood";
      }
   }

   public static final class DefaultSoundType3 extends Block.SoundType {

      @Override
      public String getBreakSound() {
         return "dig.stone";
      }

      @Override
      public String getPlaceSound() {
         return "random.anvil_land";
      }

      public DefaultSoundType3(String str, float f, float f2) {
         super(str, f, f2);
      }
   }

   public static final class DefaultSoundType4 extends Block.SoundType {

      public DefaultSoundType4(String str, float f, float f2) {
         super(str, f, f2);
      }

      @Override
      public String getPlaceSound() {
         return "mob.slime.big";
      }

      @Override
      public String getStepSound() {
         return "mob.slime.small";
      }

      @Override
      public String getBreakSound() {
         return "mob.slime.big";
      }
   }

   public static class SoundType {
      public final String soundName;
      public final float volume;
      public final float frequency;

      public SoundType(String str, float f, float f2) {
         this.soundName = str;
         this.volume = f;
         this.frequency = f2;
      }

      public String getBreakSound() {
         return "dig." + this.soundName;
      }

      public String getPlaceSound() {
         return this.getBreakSound();
      }

      public float getFrequency() {
         return this.frequency;
      }

      public String getStepSound() {
         return "step." + this.soundName;
      }

      public float getVolume() {
         return this.volume;
      }
   }

   public enum EnumOffsetType {
      NONE,
      XZ,
      XYZ;
   }
}
