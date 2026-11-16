package net.minecraft.block;

import augustus.modules.render.XRay;
import client.setting.BlockNoneType;
import client.setting.BlockSolidType;
import client.setting.CombatTimeoutProperty;
import client.setting.CombatToggleProperty;
import client.setting.CommonOptionType;
import client.setting.ExtendedOptionType;
import client.setting.InternalOptionType;
import client.setting.MovementProperty;
import client.util.AdapterClientManager;
import client.util.BridgeClientManager;
import client.util.HandlerClientManager;
import client.util.IBlockState;
import client.util.IterableExtensionBridge;
import client.util.LvlManager;
import client.util.ModularClientManager;
import client.util.StatCraftItemManager;
import client.util.StepManager;
import com.google.common.collect.UnmodifiableIterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityXporb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.RegistryNamespacedDefaultedByKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Block {
   public static final ResourceLocation resourceLocation = new ResourceLocation("air");
   public static final IterableExtensionBridge iterableExtensionBridge = new IterableExtensionBridge(resourceLocation);
   public static final RegistryNamespacedDefaultedByKey identityHashMapEntry = new RegistryNamespacedDefaultedByKey();
   public ItemGroupManager itemGroupManager;
   public static final StepManager stepManager17 = new StepManager("stone", 1.0F, 1.0F);
   public static final StepManager stepManager19 = new StepManager("wood", 1.0F, 1.0F);
   public static final StepManager stepManager12 = new StepManager("gravel", 1.0F, 1.0F);
   public static final StepManager stepManager26 = new StepManager("grass", 1.0F, 1.0F);
   public static final StepManager stepManager10 = new StepManager("stone", 1.0F, 1.0F);
   public static final StepManager stepManager24 = new StepManager("stone", 1.0F, 1.5F);
   public static final StepManager stepManager5 = new AdapterClientManager("stone", 1.0F, 1.0F);
   public static final StepManager stepManager13 = new StepManager("cloth", 1.0F, 1.0F);
   public static final StepManager stepManager7 = new StepManager("sand", 1.0F, 1.0F);
   public static final StepManager stepManager27 = new StepManager("snow", 1.0F, 1.0F);
   public static final StepManager stepManager16 = new HandlerClientManager("ladder", 1.0F, 1.0F);
   public static final StepManager stepManager4 = new BridgeClientManager("anvil", 0.3F, 1.0F);
   public static final StepManager stepManager = new ModularClientManager("slime", 1.0F, 1.0F);
   public boolean state18;
   public int indexOffset;
   public boolean state;
   public int indexOffset1;
   public boolean state25;
   public float scaleFactor22;
   public float scaleFactor21;
   public boolean state3 = true;
   public boolean state23;
   public boolean state8;
   public double coordOffset;
   public double coordOffset11;
   public double coordOffset2;
   public double coordOffset9;
   public double coordOffset6;
   public double coordOffset15;
   public StepManager stepManager20 = stepManager17;
   public float scaleFactor14 = 1.0F;
   public final Material coreItemEntry;
   public final MapColor auxiliaryItemArrayEntry;
   public float scaleFactor = 0.6F;
   public final BlockManager blockManager;
   public IBlockState iBlockState;
   public String text;

   public IBlockState processAction(IBlockState blockState) {
      return blockState;
   }

   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player) {
   }

   public Block processAction() {
      this.getBlock5(-1.0F);
      return this;
   }

   public final IBlockState getDefaultState() {
      return this.iBlockState;
   }

   public final void processAction(World world, BlockPos blockPos, IBlockState blockState, int count) {
      this.applyWorld3(world, blockPos, blockState, 1.0F, count);
   }

   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState, float factor, int count) {
      if (!world.state) {
         int count2 = this.processAction(count, world.random);

         for (int index = 0; index < count2; index++) {
            if (world.random.nextFloat() <= factor) {
               Item item = this.processAction(blockState, world.random, count);
               if (item != null) {
                  applyWorld(world, blockPos, new ItemStack(item, 1, this.getMetaFromState(blockState)));
               }
            }
         }
      }
   }

   public boolean processAction(BlockBreakState blockBreakState) {
      return true;
   }

   public boolean processAction(World world, BlockPos blockPos, EnumFacing enumFacing, ItemStack stack) {
      return this.processAction(world, blockPos, enumFacing);
   }

   public void breakBlock(World world, BlockPos blockPos, IBlockState blockState) {
   }

   public void onBlockClicked(World world, BlockPos blockPos, EntityPlayer player) {
   }

   public void processAction2() {
   }

   public BlockSolidType processAction3() {
      return BlockSolidType.SOLID;
   }

   private static void processAction(int count, String text2, Block block) {
      processAction(count, new ResourceLocation(text2), block);
   }

   public int processAction2(IBlockState blockState) {
      return 16777215;
   }

   private boolean matchesVec5(Vec3 vec) {
      return vec != null && vec.xCoord >= -0.3 && vec.xCoord <= 1.3 && vec.zCoord >= -0.3 && vec.zCoord <= 1.3;
   }

   public boolean processAction4() {
      return false;
   }

   public float processAction(Entity entity) {
      return this.scaleFactor21 / 5.0F;
   }

   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
   }

   public final double getCoordOffset11() {
      return this.coordOffset11;
   }

   public String getTextAsText() {
      return "tile." + this.text;
   }

   public AxisAlignedBB processAction(World world, BlockPos blockPos) {
      return new AxisAlignedBB(
         (double)blockPos.getX() + this.coordOffset,
         (double)blockPos.getY() + this.coordOffset11,
         (double)blockPos.getZ() + this.coordOffset2,
         (double)blockPos.getX() + this.coordOffset9,
         (double)blockPos.getY() + this.coordOffset6,
         (double)blockPos.getZ() + this.coordOffset15
      );
   }

   public Block processAction(float factor) {
      this.indexOffset1 = (int)(15.0F * factor);
      return this;
   }

   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
   }

   public int getIndexOffset() {
      return this.indexOffset;
   }

   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase livingEntity, ItemStack stack) {
   }

   public Block processAction(boolean flag) {
      this.state23 = flag;
      return this;
   }

   public final double getCoordOffset() {
      return this.coordOffset;
   }

   public Block processAction(ItemGroupManager itemGroupManager2) {
      this.itemGroupManager = itemGroupManager2;
      return this;
   }

   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return blockState;
   }

   public void processAction2(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      this.updateTick(world, blockPos, blockState, random);
   }

   public void processAction(World world, BlockPos blockPos, Entity entity) {
   }

   public int processAction2(World world, BlockPos blockPos) {
      return this.getMetaFromState(world.getBlockState(blockPos));
   }

   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return this.getIndex(iBlockAccess, blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }

   public boolean processAction5() {
      return true;
   }

   public boolean processAction(IBlockState blockState, boolean flag, boolean flag2) {
      return this.processAction10();
   }

   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return false;
   }

   public void processAction3(World world, BlockPos blockPos) {
   }

   public final int processAction2(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return this.processAction(iBlockAccess, blockPos, 0);
   }

   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return !this.coreItemEntry.isActiveAsFlag();
   }

   public MapColor processAction3(IBlockState blockState) {
      return this.auxiliaryItemArrayEntry;
   }

   private boolean matchesVecByVec3(Vec3 vec) {
      return vec != null && vec.yCoord >= -0.3 && vec.yCoord <= 1.3 && vec.zCoord >= -0.3 && vec.zCoord <= 1.3;
   }

   public int processAction6() {
      return 16777215;
   }

   public int processAction(World world) {
      return 10;
   }

   public IBlockState processAction(int count) {
      return this.getDefaultState();
   }

   public final double getCoordOffset9() {
      return this.coordOffset9;
   }

   public boolean processAction7() {
      return false;
   }

   public ItemGroupManager getItemGroupManager() {
      return this.itemGroupManager;
   }

   public Vec3 processAction(World world, BlockPos blockPos, Entity entity, Vec3 vec) {
      return vec;
   }

   public int getIndex(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      Block block = iBlockAccess.getIBlockState(count, count2, count3).getBlock();
      int count4 = iBlockAccess.computeCount(count, count2, count3, block.getIndexOffset1());
      if (count4 == 0 && block instanceof ExtraBlockAdapter) {
         Block block2 = iBlockAccess.getIBlockState(count, count2 - 1, count3).getBlock();
         return iBlockAccess.computeCount(count, count2 - 1, count3, block2.getIndexOffset1());
      } else {
         return count4;
      }
   }

   public Item processAction4(World world, BlockPos blockPos) {
      return Item.processAction2(this);
   }

   public Block setHardness(float hardness) {
      this.scaleFactor21 = hardness * 3.0F;
      return this;
   }

   public MovingObjectPosition resolveMovingObjectPosition(
      World world, BlockPos blockPos, double posX, double posX2, double posX3, double posX4, double posY, double posZ
   ) {
      this.processAction(world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
      double offset = posX - (double)blockPos.getX();
      double offset2 = posX2 - (double)blockPos.getY();
      double offset3 = posX3 - (double)blockPos.getZ();
      double offset4 = posX4 - (double)blockPos.getX();
      double offset5 = posY - (double)blockPos.getY();
      double offset6 = posZ - (double)blockPos.getZ();
      double offset7 = offset4 - offset;
      double offset8 = offset5 - offset2;
      double offset9 = offset6 - offset3;
      Vec3 vec = this.resolveVecByDouble(offset, offset2, offset3, 0.0, offset7, offset8, offset9);
      Vec3 vec2 = this.resolveVecByDouble(offset, offset2, offset3, 1.0, offset7, offset8, offset9);
      Vec3 vec3 = this.resolveVec2(offset, offset2, offset3, 0.0, offset7, offset8, offset9);
      Vec3 vec4 = this.resolveVec2(offset, offset2, offset3, 1.0, offset7, offset8, offset9);
      Vec3 vec5 = this.resolveVec3(offset, offset2, offset3, 0.0, offset7, offset8, offset9);
      Vec3 vec6 = this.resolveVec3(offset, offset2, offset3, 1.0, offset7, offset8, offset9);
      if (!this.matchesVecByVec3(vec)) {
         vec = null;
      }

      if (!this.matchesVecByVec3(vec2)) {
         vec2 = null;
      }

      if (!this.matchesVec5(vec3)) {
         vec3 = null;
      }

      if (!this.matchesVec5(vec4)) {
         vec4 = null;
      }

      if (!this.matchesVec4(vec5)) {
         vec5 = null;
      }

      if (!this.matchesVec4(vec6)) {
         vec6 = null;
      }

      Vec3 vec7 = null;
      if (vec != null) {
         vec7 = vec;
      }

      if (vec2 != null && (vec7 == null || vec2.getDouble4(offset, offset2, offset3) < vec7.getDouble4(offset, offset2, offset3))) {
         vec7 = vec2;
      }

      if (vec3 != null && (vec7 == null || vec3.getDouble4(offset, offset2, offset3) < vec7.getDouble4(offset, offset2, offset3))) {
         vec7 = vec3;
      }

      if (vec4 != null && (vec7 == null || vec4.getDouble4(offset, offset2, offset3) < vec7.getDouble4(offset, offset2, offset3))) {
         vec7 = vec4;
      }

      if (vec5 != null && (vec7 == null || vec5.getDouble4(offset, offset2, offset3) < vec7.getDouble4(offset, offset2, offset3))) {
         vec7 = vec5;
      }

      if (vec6 != null && (vec7 == null || vec6.getDouble4(offset, offset2, offset3) < vec7.getDouble4(offset, offset2, offset3))) {
         vec7 = vec6;
      }

      if (vec7 == null) {
         return null;
      } else {
         EnumFacing enumFacing = null;
         if (vec7 == vec) {
            enumFacing = EnumFacing.WEST;
         }

         if (vec7 == vec2) {
            enumFacing = EnumFacing.EAST;
         }

         if (vec7 == vec3) {
            enumFacing = EnumFacing.DOWN;
         }

         if (vec7 == vec4) {
            enumFacing = EnumFacing.UP;
         }

         if (vec7 == vec5) {
            enumFacing = EnumFacing.NORTH;
         }

         if (vec7 == vec6) {
            enumFacing = EnumFacing.SOUTH;
         }

         return new MovingObjectPosition(vec7.getVec312((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()), enumFacing, blockPos);
      }
   }

   public float getFactor() {
      if (XRay.activeState3) {
         return 1.0F;
      } else {
         return this.isActive4() ? 0.2F : 1.0F;
      }
   }

   private boolean matchesVec3(Vec3 vec) {
      return vec != null
         && vec.yCoord >= this.coordOffset11
         && vec.yCoord <= this.coordOffset6
         && vec.zCoord >= this.coordOffset2
         && vec.zCoord <= this.coordOffset15;
   }

   public final void setBlockBounds(float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      this.coordOffset = (double)posX;
      this.coordOffset11 = (double)posX2;
      this.coordOffset2 = (double)posX3;
      this.coordOffset9 = (double)posX4;
      this.coordOffset6 = (double)posY;
      this.coordOffset15 = (double)posZ;
   }

   public int processAction5(World world, BlockPos blockPos) {
      return 0;
   }

   public final double getCoordOffset2() {
      return this.coordOffset2;
   }

   public void processAction(World world, Entity entity) {
      entity.motionY = 0.0;
   }

   public boolean processAction6(World world, BlockPos blockPos) {
      return false;
   }

   public Block setUnlocalizedName(String unlocalizedName) {
      this.text = unlocalizedName;
      return this;
   }

   public boolean isActive() {
      return this.processAction14() && !this.state8;
   }

   public Block processAction(StepManager stepManager2) {
      this.stepManager20 = stepManager2;
      return this;
   }

   public void onEntityCollidedWithBlock(World world, BlockPos blockPos, IBlockState blockState, Entity entity) {
   }

   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
   }

   public void applyWorld(World world, BlockPos blockPos, IBlockState blockState, AxisAlignedBB boundingBox, List list, Entity entity) {
      AxisAlignedBB boundingBox2 = this.processAction(world, blockState, blockPos.getX(), blockPos.getY(), blockPos.getZ());
      Block block = world.getBlockState(blockPos).getBlock();
      Block block2 = block;
      AxisAlignedBB axisAlignedBB = BlockCollisionListener.resolveEventBlockCollision(boundingBox2, block2, blockPos).getAxisAlignedBB();
      if (axisAlignedBB != null && boundingBox.intersectsWith(axisAlignedBB)) {
         list.add(axisAlignedBB);
      }
   }

   public boolean isActive4() {
      return this.coreItemEntry.isActiveAsFlag() && this.processAction14();
   }

   public Item processAction(IBlockState blockState, Random random, int count) {
      return Item.processAction2(this);
   }

   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return 0;
   }

   public static void updateState() {
      processAction(0, resourceLocation, new BlockAir().setUnlocalizedName("air"));
      processAction(1, "stone", new BlockStone().getBlock5(1.5F).setHardness(10.0F).processAction(stepManager10).setUnlocalizedName("stone"));
      processAction(2, "grass", new BlockGrass().getBlock5(0.6F).processAction(stepManager26).setUnlocalizedName("grass"));
      processAction(3, "dirt", new BlockDirt().getBlock5(0.5F).processAction(stepManager12).setUnlocalizedName("dirt"));
      Block block = new Block(Material.material4)
         .getBlock5(2.0F)
         .setHardness(10.0F)
         .processAction(stepManager10)
         .setUnlocalizedName("stonebrick")
         .processAction(ItemGroupManager.itemGroupManager10);
      processAction(4, "cobblestone", block);
      Block block2 = new BlockPlanks().getBlock5(2.0F).setHardness(5.0F).processAction(stepManager19).setUnlocalizedName("wood");
      processAction(5, "planks", block2);
      processAction(6, "sapling", new BlockSapling().getBlock5(0.0F).processAction(stepManager26).setUnlocalizedName("sapling"));
      processAction(
         7,
         "bedrock",
         new Block(Material.material4)
            .processAction()
            .setHardness(6000000.0F)
            .processAction(stepManager10)
            .setUnlocalizedName("bedrock")
            .processAction12()
            .processAction(ItemGroupManager.itemGroupManager10)
      );
      processAction(
         8, "flowing_water", new BlockFlowingLava(Material.coreItemEntry1).getBlock5(100.0F).getBlockById(3).setUnlocalizedName("water").processAction12()
      );
      processAction(9, "water", new BlockLava(Material.coreItemEntry1).getBlock5(100.0F).getBlockById(3).setUnlocalizedName("water").processAction12());
      processAction(
         10, "flowing_lava", new BlockFlowingLava(Material.material29).getBlock5(100.0F).processAction(1.0F).setUnlocalizedName("lava").processAction12()
      );
      processAction(11, "lava", new BlockLava(Material.material29).getBlock5(100.0F).processAction(1.0F).setUnlocalizedName("lava").processAction12());
      processAction(12, "sand", new BlockSand().getBlock5(0.5F).processAction(stepManager7).setUnlocalizedName("sand"));
      processAction(13, "gravel", new BlockGravel().getBlock5(0.6F).processAction(stepManager12).setUnlocalizedName("gravel"));
      processAction(14, "gold_ore", new BlockQuartzOre().getBlock5(3.0F).setHardness(5.0F).processAction(stepManager10).setUnlocalizedName("oreGold"));
      processAction(15, "iron_ore", new BlockQuartzOre().getBlock5(3.0F).setHardness(5.0F).processAction(stepManager10).setUnlocalizedName("oreIron"));
      processAction(16, "coal_ore", new BlockQuartzOre().getBlock5(3.0F).setHardness(5.0F).processAction(stepManager10).setUnlocalizedName("oreCoal"));
      processAction(17, "log", new BlockLog().setUnlocalizedName("log"));
      processAction(18, "leaves", new BlockLeaves().setUnlocalizedName("leaves"));
      processAction(19, "sponge", new BlockSponge().getBlock5(0.6F).processAction(stepManager26).setUnlocalizedName("sponge"));
      processAction(20, "glass", new BlockGlass(Material.material26, false).getBlock5(0.3F).processAction(stepManager5).setUnlocalizedName("glass"));
      processAction(21, "lapis_ore", new BlockQuartzOre().getBlock5(3.0F).setHardness(5.0F).processAction(stepManager10).setUnlocalizedName("oreLapis"));
      processAction(
         22,
         "lapis_block",
         new Block(Material.material5, MapColor.mapColor15)
            .getBlock5(3.0F)
            .setHardness(5.0F)
            .processAction(stepManager10)
            .setUnlocalizedName("blockLapis")
            .processAction(ItemGroupManager.itemGroupManager10)
      );
      processAction(23, "dispenser", new BlockDispenser().getBlock5(3.5F).processAction(stepManager10).setUnlocalizedName("dispenser"));
      Block block3 = new BlockSandstone().processAction(stepManager10).getBlock5(0.8F).setUnlocalizedName("sandStone");
      processAction(24, "sandstone", block3);
      processAction(25, "noteblock", new BlockNoteblock().getBlock5(0.8F).setUnlocalizedName("musicBlock"));
      processAction(26, "bed", new BlockBed().processAction(stepManager19).getBlock5(0.2F).setUnlocalizedName("bed").processAction12());
      processAction(27, "golden_rail", new BlockActivatorRail().getBlock5(0.7F).processAction(stepManager24).setUnlocalizedName("goldenRail"));
      processAction(28, "detector_rail", new BlockDetectorRail().getBlock5(0.7F).processAction(stepManager24).setUnlocalizedName("detectorRail"));
      processAction(29, "sticky_piston", new BlockPiston(true).setUnlocalizedName("pistonStickyBase"));
      processAction(30, "web", new BlockWeb().getBlockById(1).getBlock5(4.0F).setUnlocalizedName("web"));
      processAction(31, "tallgrass", new BlockTallgrass().getBlock5(0.0F).processAction(stepManager26).setUnlocalizedName("tallgrass"));
      processAction(32, "deadbush", new BlockDeadbush().getBlock5(0.0F).processAction(stepManager26).setUnlocalizedName("deadbush"));
      processAction(33, "piston", new BlockPiston(false).setUnlocalizedName("pistonBase"));
      processAction(34, "piston_head", new BlockPistonHead().setUnlocalizedName("pistonBase"));
      processAction(35, "wool", new BlockStainedHardenedClay(Material.material19).getBlock5(0.8F).processAction(stepManager13).setUnlocalizedName("cloth"));
      processAction(36, "piston_extension", new BlockPistonExtension());
      processAction(37, "yellow_flower", new BlockYellowFlower().getBlock5(0.0F).processAction(stepManager26).setUnlocalizedName("flower1"));
      processAction(38, "red_flower", new BlockRedFlower().getBlock5(0.0F).processAction(stepManager26).setUnlocalizedName("flower2"));
      Block block4 = new BlockRedMushroom().getBlock5(0.0F).processAction(stepManager26).processAction(0.125F).setUnlocalizedName("mushroom");
      processAction(39, "brown_mushroom", block4);
      Block block5 = new BlockRedMushroom().getBlock5(0.0F).processAction(stepManager26).setUnlocalizedName("mushroom");
      processAction(40, "red_mushroom", block5);
      processAction(
         41,
         "gold_block",
         new Block(Material.material5, MapColor.mapColor23)
            .getBlock5(3.0F)
            .setHardness(10.0F)
            .processAction(stepManager24)
            .setUnlocalizedName("blockGold")
            .processAction(ItemGroupManager.itemGroupManager10)
      );
      processAction(
         42,
         "iron_block",
         new Block(Material.material5, MapColor.mapColor34)
            .getBlock5(5.0F)
            .setHardness(10.0F)
            .processAction(stepManager24)
            .setUnlocalizedName("blockIron")
            .processAction(ItemGroupManager.itemGroupManager10)
      );
      processAction(
         43, "double_stone_slab", new BlockDoubleStoneSlab().getBlock5(2.0F).setHardness(10.0F).processAction(stepManager10).setUnlocalizedName("stoneSlab")
      );
      processAction(44, "stone_slab", new BlockStoneSlab().getBlock5(2.0F).setHardness(10.0F).processAction(stepManager10).setUnlocalizedName("stoneSlab"));
      Block block6 = new Block(Material.material4, MapColor.mapColor5)
         .getBlock5(2.0F)
         .setHardness(10.0F)
         .processAction(stepManager10)
         .setUnlocalizedName("brick")
         .processAction(ItemGroupManager.itemGroupManager10);
      processAction(45, "brick_block", block6);
      processAction(46, "tnt", new BlockTnt().getBlock5(0.0F).processAction(stepManager26).setUnlocalizedName("tnt"));
      processAction(47, "bookshelf", new BlockBookshelf().getBlock5(1.5F).processAction(stepManager19).setUnlocalizedName("bookshelf"));
      processAction(
         48,
         "mossy_cobblestone",
         new Block(Material.material4)
            .getBlock5(2.0F)
            .setHardness(10.0F)
            .processAction(stepManager10)
            .setUnlocalizedName("stoneMoss")
            .processAction(ItemGroupManager.itemGroupManager10)
      );
      processAction(49, "obsidian", new BlockObsidian().getBlock5(50.0F).setHardness(2000.0F).processAction(stepManager10).setUnlocalizedName("obsidian"));
      processAction(50, "torch", new BlockTorch().getBlock5(0.0F).processAction(0.9375F).processAction(stepManager19).setUnlocalizedName("torch"));
      processAction(51, "fire", new BlockFire().getBlock5(0.0F).processAction(1.0F).processAction(stepManager13).setUnlocalizedName("fire").processAction12());
      processAction(52, "mob_spawner", new BlockMobSpawner().getBlock5(5.0F).processAction(stepManager24).setUnlocalizedName("mobSpawner").processAction12());
      processAction(
         53,
         "oak_stairs",
         new BlockRedSandstoneStairs(block2.getDefaultState().resolveIBlockState(BlockPlanks.immutableSetEntry, MovementProperty.OAK))
            .setUnlocalizedName("stairsWood")
      );
      processAction(54, "chest", new BlockTrappedChest(0).getBlock5(2.5F).processAction(stepManager19).setUnlocalizedName("chest"));
      processAction(
         55, "redstone_wire", new BlockRedstoneWire().getBlock5(0.0F).processAction(stepManager17).setUnlocalizedName("redstoneDust").processAction12()
      );
      processAction(56, "diamond_ore", new BlockQuartzOre().getBlock5(3.0F).setHardness(5.0F).processAction(stepManager10).setUnlocalizedName("oreDiamond"));
      processAction(
         57,
         "diamond_block",
         new Block(Material.material5, MapColor.mapColor)
            .getBlock5(5.0F)
            .setHardness(10.0F)
            .processAction(stepManager24)
            .setUnlocalizedName("blockDiamond")
            .processAction(ItemGroupManager.itemGroupManager10)
      );
      processAction(58, "crafting_table", new BlockCraftingTable().getBlock5(2.5F).processAction(stepManager19).setUnlocalizedName("workbench"));
      processAction(59, "wheat", new BlockWheat().setUnlocalizedName("crops"));
      Block block7 = new BlockFarmland().getBlock5(0.6F).processAction(stepManager12).setUnlocalizedName("farmland");
      processAction(60, "farmland", block7);
      processAction(
         61,
         "furnace",
         new BlockLitFurnace(false)
            .getBlock5(3.5F)
            .processAction(stepManager10)
            .setUnlocalizedName("furnace")
            .processAction(ItemGroupManager.itemGroupManager8)
      );
      processAction(
         62, "lit_furnace", new BlockLitFurnace(true).getBlock5(3.5F).processAction(stepManager10).processAction(0.875F).setUnlocalizedName("furnace")
      );
      processAction(63, "standing_sign", new BlockStandingSign().getBlock5(1.0F).processAction(stepManager19).setUnlocalizedName("sign").processAction12());
      processAction(
         64,
         "wooden_door",
         new BlockDarkOakDoor(Material.material).getBlock5(3.0F).processAction(stepManager19).setUnlocalizedName("doorOak").processAction12()
      );
      processAction(65, "ladder", new BlockLadder().getBlock5(0.4F).processAction(stepManager16).setUnlocalizedName("ladder"));
      processAction(66, "rail", new BlockRail().getBlock5(0.7F).processAction(stepManager24).setUnlocalizedName("rail"));
      processAction(67, "stone_stairs", new BlockRedSandstoneStairs(block.getDefaultState()).setUnlocalizedName("stairsStone"));
      processAction(68, "wall_sign", new BlockWallSign().getBlock5(1.0F).processAction(stepManager19).setUnlocalizedName("sign").processAction12());
      processAction(69, "lever", new BlockLever().getBlock5(0.5F).processAction(stepManager19).setUnlocalizedName("lever"));
      processAction(
         70,
         "stone_pressure_plate",
         new BlockWoodenPressurePlate(Material.material4, ExtendedOptionType.MOBS)
            .getBlock5(0.5F)
            .processAction(stepManager10)
            .setUnlocalizedName("pressurePlateStone")
      );
      processAction(
         71,
         "iron_door",
         new BlockDarkOakDoor(Material.material5).getBlock5(5.0F).processAction(stepManager24).setUnlocalizedName("doorIron").processAction12()
      );
      processAction(
         72,
         "wooden_pressure_plate",
         new BlockWoodenPressurePlate(Material.material, ExtendedOptionType.EVERYTHING)
            .getBlock5(0.5F)
            .processAction(stepManager19)
            .setUnlocalizedName("pressurePlateWood")
      );
      processAction(
         73,
         "redstone_ore",
         new BlockLitRedstoneOre(false)
            .getBlock5(3.0F)
            .setHardness(5.0F)
            .processAction(stepManager10)
            .setUnlocalizedName("oreRedstone")
            .processAction(ItemGroupManager.itemGroupManager10)
      );
      processAction(
         74,
         "lit_redstone_ore",
         new BlockLitRedstoneOre(true).processAction(0.625F).getBlock5(3.0F).setHardness(5.0F).processAction(stepManager10).setUnlocalizedName("oreRedstone")
      );
      processAction(75, "unlit_redstone_torch", new BlockRedstoneTorch(false).getBlock5(0.0F).processAction(stepManager19).setUnlocalizedName("notGate"));
      processAction(
         76,
         "redstone_torch",
         new BlockRedstoneTorch(true)
            .getBlock5(0.0F)
            .processAction(0.5F)
            .processAction(stepManager19)
            .setUnlocalizedName("notGate")
            .processAction(ItemGroupManager.itemGroupManager9)
      );
      processAction(77, "stone_button", new BlockStoneButton().getBlock5(0.5F).processAction(stepManager10).setUnlocalizedName("button"));
      processAction(78, "snow_layer", new BlockSnowLayer().getBlock5(0.1F).processAction(stepManager27).setUnlocalizedName("snow").getBlockById(0));
      processAction(79, "ice", new BlockIce().getBlock5(0.5F).getBlockById(3).processAction(stepManager5).setUnlocalizedName("ice"));
      processAction(80, "snow", new BlockSnow().getBlock5(0.2F).processAction(stepManager27).setUnlocalizedName("snow"));
      processAction(81, "cactus", new BlockCactus().getBlock5(0.4F).processAction(stepManager13).setUnlocalizedName("cactus"));
      processAction(82, "clay", new BlockClay().getBlock5(0.6F).processAction(stepManager12).setUnlocalizedName("clay"));
      processAction(83, "reeds", new BlockReeds().getBlock5(0.0F).processAction(stepManager26).setUnlocalizedName("reeds").processAction12());
      processAction(84, "jukebox", new BlockJukebox().getBlock5(2.0F).setHardness(10.0F).processAction(stepManager10).setUnlocalizedName("jukebox"));
      processAction(
         85,
         "fence",
         new BlockAcaciaFence(Material.material, MovementProperty.OAK.getMapColor())
            .getBlock5(2.0F)
            .setHardness(5.0F)
            .processAction(stepManager19)
            .setUnlocalizedName("fence")
      );
      Block block8 = new BlockLitPumpkin().getBlock5(1.0F).processAction(stepManager19).setUnlocalizedName("pumpkin");
      processAction(86, "pumpkin", block8);
      processAction(87, "netherrack", new BlockNetherrack().getBlock5(0.4F).processAction(stepManager10).setUnlocalizedName("hellrock"));
      processAction(88, "soul_sand", new BlockSoulSand().getBlock5(0.5F).processAction(stepManager7).setUnlocalizedName("hellsand"));
      processAction(
         89,
         "glowstone",
         new BlockGlowstone(Material.material26).getBlock5(0.3F).processAction(stepManager5).processAction(1.0F).setUnlocalizedName("lightgem")
      );
      processAction(90, "portal", new BlockPortal().getBlock5(-1.0F).processAction(stepManager5).processAction(0.75F).setUnlocalizedName("portal"));
      processAction(91, "lit_pumpkin", new BlockLitPumpkin().getBlock5(1.0F).processAction(stepManager19).processAction(1.0F).setUnlocalizedName("litpumpkin"));
      processAction(92, "cake", new BlockCake().getBlock5(0.5F).processAction(stepManager13).setUnlocalizedName("cake").processAction12());
      processAction(
         93, "unpowered_repeater", new BlockPoweredRepeater(false).getBlock5(0.0F).processAction(stepManager19).setUnlocalizedName("diode").processAction12()
      );
      processAction(
         94, "powered_repeater", new BlockPoweredRepeater(true).getBlock5(0.0F).processAction(stepManager19).setUnlocalizedName("diode").processAction12()
      );
      processAction(
         95, "stained_glass", new BlockStainedGlass(Material.material26).getBlock5(0.3F).processAction(stepManager5).setUnlocalizedName("stainedGlass")
      );
      processAction(
         96, "trapdoor", new BlockIronTrapdoor(Material.material).getBlock5(3.0F).processAction(stepManager19).setUnlocalizedName("trapdoor").processAction12()
      );
      processAction(97, "monster_egg", new BlockMonsterEgg().getBlock5(0.75F).setUnlocalizedName("monsterStoneEgg"));
      Block block9 = new BlockStonebrick().getBlock5(1.5F).setHardness(10.0F).processAction(stepManager10).setUnlocalizedName("stonebricksmooth");
      processAction(98, "stonebrick", block9);
      processAction(
         99,
         "brown_mushroom_block",
         new BlockRedMushroomBlock(Material.material, MapColor.mapColor4, block4).getBlock5(0.2F).processAction(stepManager19).setUnlocalizedName("mushroom")
      );
      processAction(
         100,
         "red_mushroom_block",
         new BlockRedMushroomBlock(Material.material, MapColor.mapColor5, block5).getBlock5(0.2F).processAction(stepManager19).setUnlocalizedName("mushroom")
      );
      processAction(
         101,
         "iron_bars",
         new BlockGlassPane(Material.material5, true).getBlock5(5.0F).setHardness(10.0F).processAction(stepManager24).setUnlocalizedName("fenceIron")
      );
      processAction(
         102, "glass_pane", new BlockGlassPane(Material.material26, false).getBlock5(0.3F).processAction(stepManager5).setUnlocalizedName("thinGlass")
      );
      Block block10 = new BlockMelonBlock().getBlock5(1.0F).processAction(stepManager19).setUnlocalizedName("melon");
      processAction(103, "melon_block", block10);
      processAction(104, "pumpkin_stem", new BlockMelonStem(block8).getBlock5(0.0F).processAction(stepManager19).setUnlocalizedName("pumpkinStem"));
      processAction(105, "melon_stem", new BlockMelonStem(block10).getBlock5(0.0F).processAction(stepManager19).setUnlocalizedName("pumpkinStem"));
      processAction(106, "vine", new BlockVine().getBlock5(0.2F).processAction(stepManager26).setUnlocalizedName("vine"));
      processAction(
         107,
         "fence_gate",
         new BlockAcaciaFenceGate(MovementProperty.OAK).getBlock5(2.0F).setHardness(5.0F).processAction(stepManager19).setUnlocalizedName("fenceGate")
      );
      processAction(108, "brick_stairs", new BlockRedSandstoneStairs(block6.getDefaultState()).setUnlocalizedName("stairsBrick"));
      processAction(
         109,
         "stone_brick_stairs",
         new BlockRedSandstoneStairs(block9.getDefaultState().resolveIBlockState(BlockStonebrick.immutableSetEntry, CombatTimeoutProperty.DEFAULT))
            .setUnlocalizedName("stairsStoneBrickSmooth")
      );
      processAction(110, "mycelium", new BlockMycelium().getBlock5(0.6F).processAction(stepManager26).setUnlocalizedName("mycel"));
      processAction(111, "waterlily", new BlockWaterlily().getBlock5(0.0F).processAction(stepManager26).setUnlocalizedName("waterlily"));
      Block block11 = new BlockNetherBrick()
         .getBlock5(2.0F)
         .setHardness(10.0F)
         .processAction(stepManager10)
         .setUnlocalizedName("netherBrick")
         .processAction(ItemGroupManager.itemGroupManager10);
      processAction(112, "nether_brick", block11);
      processAction(
         113,
         "nether_brick_fence",
         new BlockAcaciaFence(Material.material4, MapColor.mapColor33)
            .getBlock5(2.0F)
            .setHardness(10.0F)
            .processAction(stepManager10)
            .setUnlocalizedName("netherFence")
      );
      processAction(114, "nether_brick_stairs", new BlockRedSandstoneStairs(block11.getDefaultState()).setUnlocalizedName("stairsNetherBrick"));
      processAction(115, "nether_wart", new BlockNetherWart().setUnlocalizedName("netherStalk"));
      processAction(116, "enchanting_table", new BlockEnchantingTable().getBlock5(5.0F).setHardness(2000.0F).setUnlocalizedName("enchantmentTable"));
      processAction(117, "brewing_stand", new BlockBrewingStand().getBlock5(0.5F).processAction(0.125F).setUnlocalizedName("brewingStand"));
      processAction(118, "cauldron", new BlockCauldron().getBlock5(2.0F).setUnlocalizedName("cauldron"));
      processAction(119, "end_portal", new BlockEndPortal(Material.material17).getBlock5(-1.0F).setHardness(6000000.0F));
      processAction(
         120,
         "end_portal_frame",
         new BlockEndPortalFrame()
            .processAction(stepManager5)
            .processAction(0.125F)
            .getBlock5(-1.0F)
            .setUnlocalizedName("endPortalFrame")
            .setHardness(6000000.0F)
            .processAction(ItemGroupManager.itemGroupManager8)
      );
      processAction(
         121,
         "end_stone",
         new Block(Material.material4, MapColor.mapColor20)
            .getBlock5(3.0F)
            .setHardness(15.0F)
            .processAction(stepManager10)
            .setUnlocalizedName("whiteStone")
            .processAction(ItemGroupManager.itemGroupManager10)
      );
      processAction(
         122,
         "dragon_egg",
         new BlockDragonEgg().getBlock5(3.0F).setHardness(15.0F).processAction(stepManager10).processAction(0.125F).setUnlocalizedName("dragonEgg")
      );
      processAction(
         123,
         "redstone_lamp",
         new BlockLitRedstoneLamp(false)
            .getBlock5(0.3F)
            .processAction(stepManager5)
            .setUnlocalizedName("redstoneLight")
            .processAction(ItemGroupManager.itemGroupManager9)
      );
      processAction(124, "lit_redstone_lamp", new BlockLitRedstoneLamp(true).getBlock5(0.3F).processAction(stepManager5).setUnlocalizedName("redstoneLight"));
      processAction(
         125, "double_wooden_slab", new BlockDoubleWoodenSlab().getBlock5(2.0F).setHardness(5.0F).processAction(stepManager19).setUnlocalizedName("woodSlab")
      );
      processAction(126, "wooden_slab", new BlockWoodenSlab().getBlock5(2.0F).setHardness(5.0F).processAction(stepManager19).setUnlocalizedName("woodSlab"));
      processAction(127, "cocoa", new BlockCocoa().getBlock5(0.2F).setHardness(5.0F).processAction(stepManager19).setUnlocalizedName("cocoa"));
      processAction(
         128,
         "sandstone_stairs",
         new BlockRedSandstoneStairs(block3.getDefaultState().resolveIBlockState(BlockSandstone.immutableSetEntry, InternalOptionType.SMOOTH))
            .setUnlocalizedName("stairsSandStone")
      );
      processAction(129, "emerald_ore", new BlockQuartzOre().getBlock5(3.0F).setHardness(5.0F).processAction(stepManager10).setUnlocalizedName("oreEmerald"));
      processAction(
         130,
         "ender_chest",
         new BlockEnderChest().getBlock5(22.5F).setHardness(1000.0F).processAction(stepManager10).setUnlocalizedName("enderChest").processAction(0.5F)
      );
      processAction(131, "tripwire_hook", new BlockTripwireHook().setUnlocalizedName("tripWireSource"));
      processAction(132, "tripwire", new BlockTripwire().setUnlocalizedName("tripWire"));
      processAction(
         133,
         "emerald_block",
         new Block(Material.material5, MapColor.mapColor27)
            .getBlock5(5.0F)
            .setHardness(10.0F)
            .processAction(stepManager24)
            .setUnlocalizedName("blockEmerald")
            .processAction(ItemGroupManager.itemGroupManager10)
      );
      processAction(
         134,
         "spruce_stairs",
         new BlockRedSandstoneStairs(block2.getDefaultState().resolveIBlockState(BlockPlanks.immutableSetEntry, MovementProperty.SPRUCE))
            .setUnlocalizedName("stairsWoodSpruce")
      );
      processAction(
         135,
         "birch_stairs",
         new BlockRedSandstoneStairs(block2.getDefaultState().resolveIBlockState(BlockPlanks.immutableSetEntry, MovementProperty.BIRCH))
            .setUnlocalizedName("stairsWoodBirch")
      );
      processAction(
         136,
         "jungle_stairs",
         new BlockRedSandstoneStairs(block2.getDefaultState().resolveIBlockState(BlockPlanks.immutableSetEntry, MovementProperty.JUNGLE))
            .setUnlocalizedName("stairsWoodJungle")
      );
      processAction(137, "command_block", new BlockCommandBlock().processAction().setHardness(6000000.0F).setUnlocalizedName("commandBlock"));
      processAction(138, "beacon", new BlockBeacon().setUnlocalizedName("beacon").processAction(1.0F));
      processAction(139, "cobblestone_wall", new BlockCobblestoneWall(block).setUnlocalizedName("cobbleWall"));
      processAction(140, "flower_pot", new BlockFlowerPot().getBlock5(0.0F).processAction(stepManager17).setUnlocalizedName("flowerPot"));
      processAction(141, "carrots", new BlockCarrots().setUnlocalizedName("carrots"));
      processAction(142, "potatoes", new BlockPotatoes().setUnlocalizedName("potatoes"));
      processAction(143, "wooden_button", new BlockWoodenButton().getBlock5(0.5F).processAction(stepManager19).setUnlocalizedName("button"));
      processAction(144, "skull", new BlockSkull().getBlock5(1.0F).processAction(stepManager10).setUnlocalizedName("skull"));
      processAction(145, "anvil", new BlockAnvil().getBlock5(5.0F).processAction(stepManager4).setHardness(2000.0F).setUnlocalizedName("anvil"));
      processAction(146, "trapped_chest", new BlockTrappedChest(1).getBlock5(2.5F).processAction(stepManager19).setUnlocalizedName("chestTrap"));
      processAction(
         147,
         "light_weighted_pressure_plate",
         new BlockHeavyWeightedPressurePlate(Material.material5, 15, MapColor.mapColor23)
            .getBlock5(0.5F)
            .processAction(stepManager19)
            .setUnlocalizedName("weightedPlate_light")
      );
      processAction(
         148,
         "heavy_weighted_pressure_plate",
         new BlockHeavyWeightedPressurePlate(Material.material5, 150).getBlock5(0.5F).processAction(stepManager19).setUnlocalizedName("weightedPlate_heavy")
      );
      processAction(
         149,
         "unpowered_comparator",
         new BlockPoweredComparator(false).getBlock5(0.0F).processAction(stepManager19).setUnlocalizedName("comparator").processAction12()
      );
      processAction(
         150,
         "powered_comparator",
         new BlockPoweredComparator(true).getBlock5(0.0F).processAction(0.625F).processAction(stepManager19).setUnlocalizedName("comparator").processAction12()
      );
      processAction(151, "daylight_detector", new BlockDaylightDetectorInverted(false));
      processAction(
         152,
         "redstone_block",
         new BlockRedstoneBlock(Material.material5, MapColor.mapColor8)
            .getBlock5(5.0F)
            .setHardness(10.0F)
            .processAction(stepManager24)
            .setUnlocalizedName("blockRedstone")
            .processAction(ItemGroupManager.itemGroupManager9)
      );
      processAction(
         153,
         "quartz_ore",
         new BlockQuartzOre(MapColor.mapColor33).getBlock5(3.0F).setHardness(5.0F).processAction(stepManager10).setUnlocalizedName("netherquartz")
      );
      processAction(154, "hopper", new BlockHopper().getBlock5(3.0F).setHardness(8.0F).processAction(stepManager24).setUnlocalizedName("hopper"));
      Block block12 = new BlockQuartzBlock().processAction(stepManager10).getBlock5(0.8F).setUnlocalizedName("quartzBlock");
      processAction(155, "quartz_block", block12);
      processAction(
         156,
         "quartz_stairs",
         new BlockRedSandstoneStairs(block12.getDefaultState().resolveIBlockState(BlockQuartzBlock.immutableSetEntry, CommonOptionType.DEFAULT))
            .setUnlocalizedName("stairsQuartz")
      );
      processAction(157, "activator_rail", new BlockActivatorRail().getBlock5(0.7F).processAction(stepManager24).setUnlocalizedName("activatorRail"));
      processAction(158, "dropper", new BlockDropper().getBlock5(3.5F).processAction(stepManager10).setUnlocalizedName("dropper"));
      processAction(
         159,
         "stained_hardened_clay",
         new BlockStainedHardenedClay(Material.material4)
            .getBlock5(1.25F)
            .setHardness(7.0F)
            .processAction(stepManager10)
            .setUnlocalizedName("clayHardenedStained")
      );
      processAction(160, "stained_glass_pane", new BlockStainedGlassPane().getBlock5(0.3F).processAction(stepManager5).setUnlocalizedName("thinStainedGlass"));
      processAction(161, "leaves2", new AuxiliaryBlockLeaves().setUnlocalizedName("leaves"));
      processAction(162, "log2", new AuxiliaryBlockLog().setUnlocalizedName("log"));
      processAction(
         163,
         "acacia_stairs",
         new BlockRedSandstoneStairs(block2.getDefaultState().resolveIBlockState(BlockPlanks.immutableSetEntry, MovementProperty.ACACIA))
            .setUnlocalizedName("stairsWoodAcacia")
      );
      processAction(
         164,
         "dark_oak_stairs",
         new BlockRedSandstoneStairs(block2.getDefaultState().resolveIBlockState(BlockPlanks.immutableSetEntry, MovementProperty.DARK_OAK))
            .setUnlocalizedName("stairsWoodDarkOak")
      );
      processAction(165, "slime", new BlockSlime().setUnlocalizedName("slime").processAction(stepManager));
      processAction(166, "barrier", new BlockBarrier().setUnlocalizedName("barrier"));
      processAction(
         167,
         "iron_trapdoor",
         new BlockIronTrapdoor(Material.material5).getBlock5(5.0F).processAction(stepManager24).setUnlocalizedName("ironTrapdoor").processAction12()
      );
      processAction(168, "prismarine", new BlockPrismarine().getBlock5(1.5F).setHardness(10.0F).processAction(stepManager10).setUnlocalizedName("prismarine"));
      processAction(
         169,
         "sea_lantern",
         new BlockSeaLantern(Material.material26).getBlock5(0.3F).processAction(stepManager5).processAction(1.0F).setUnlocalizedName("seaLantern")
      );
      processAction(
         170,
         "hay_block",
         new BlockHayBlock().getBlock5(0.5F).processAction(stepManager26).setUnlocalizedName("hayBlock").processAction(ItemGroupManager.itemGroupManager10)
      );
      processAction(171, "carpet", new BlockCarpet().getBlock5(0.1F).processAction(stepManager13).setUnlocalizedName("woolCarpet").getBlockById(0));
      processAction(
         172, "hardened_clay", new BlockHardenedClay().getBlock5(1.25F).setHardness(7.0F).processAction(stepManager10).setUnlocalizedName("clayHardened")
      );
      processAction(
         173,
         "coal_block",
         new Block(Material.material4, MapColor.mapColor29)
            .getBlock5(5.0F)
            .setHardness(10.0F)
            .processAction(stepManager10)
            .setUnlocalizedName("blockCoal")
            .processAction(ItemGroupManager.itemGroupManager10)
      );
      processAction(174, "packed_ice", new BlockPackedIce().getBlock5(0.5F).processAction(stepManager5).setUnlocalizedName("icePacked"));
      processAction(175, "double_plant", new BlockDoublePlant());
      processAction(
         176, "standing_banner", new BlockStandingBanner().getBlock5(1.0F).processAction(stepManager19).setUnlocalizedName("banner").processAction12()
      );
      processAction(177, "wall_banner", new BlockWallBanner().getBlock5(1.0F).processAction(stepManager19).setUnlocalizedName("banner").processAction12());
      processAction(178, "daylight_detector_inverted", new BlockDaylightDetectorInverted(true));
      Block block13 = new BlockRedSandstone().processAction(stepManager10).getBlock5(0.8F).setUnlocalizedName("redSandStone");
      processAction(179, "red_sandstone", block13);
      processAction(
         180,
         "red_sandstone_stairs",
         new BlockRedSandstoneStairs(block13.getDefaultState().resolveIBlockState(BlockRedSandstone.immutableSetEntry, CombatToggleProperty.SMOOTH))
            .setUnlocalizedName("stairsRedSandStone")
      );
      processAction(
         181,
         "double_stone_slab2",
         new AuxiliaryBlockDoubleStoneSlab().getBlock5(2.0F).setHardness(10.0F).processAction(stepManager10).setUnlocalizedName("stoneSlab2")
      );
      processAction(
         182, "stone_slab2", new AuxiliaryBlockStoneSlab().getBlock5(2.0F).setHardness(10.0F).processAction(stepManager10).setUnlocalizedName("stoneSlab2")
      );
      processAction(
         183,
         "spruce_fence_gate",
         new BlockAcaciaFenceGate(MovementProperty.SPRUCE).getBlock5(2.0F).setHardness(5.0F).processAction(stepManager19).setUnlocalizedName("spruceFenceGate")
      );
      processAction(
         184,
         "birch_fence_gate",
         new BlockAcaciaFenceGate(MovementProperty.BIRCH).getBlock5(2.0F).setHardness(5.0F).processAction(stepManager19).setUnlocalizedName("birchFenceGate")
      );
      processAction(
         185,
         "jungle_fence_gate",
         new BlockAcaciaFenceGate(MovementProperty.JUNGLE).getBlock5(2.0F).setHardness(5.0F).processAction(stepManager19).setUnlocalizedName("jungleFenceGate")
      );
      processAction(
         186,
         "dark_oak_fence_gate",
         new BlockAcaciaFenceGate(MovementProperty.DARK_OAK)
            .getBlock5(2.0F)
            .setHardness(5.0F)
            .processAction(stepManager19)
            .setUnlocalizedName("darkOakFenceGate")
      );
      processAction(
         187,
         "acacia_fence_gate",
         new BlockAcaciaFenceGate(MovementProperty.ACACIA).getBlock5(2.0F).setHardness(5.0F).processAction(stepManager19).setUnlocalizedName("acaciaFenceGate")
      );
      processAction(
         188,
         "spruce_fence",
         new BlockAcaciaFence(Material.material, MovementProperty.SPRUCE.getMapColor())
            .getBlock5(2.0F)
            .setHardness(5.0F)
            .processAction(stepManager19)
            .setUnlocalizedName("spruceFence")
      );
      processAction(
         189,
         "birch_fence",
         new BlockAcaciaFence(Material.material, MovementProperty.BIRCH.getMapColor())
            .getBlock5(2.0F)
            .setHardness(5.0F)
            .processAction(stepManager19)
            .setUnlocalizedName("birchFence")
      );
      processAction(
         190,
         "jungle_fence",
         new BlockAcaciaFence(Material.material, MovementProperty.JUNGLE.getMapColor())
            .getBlock5(2.0F)
            .setHardness(5.0F)
            .processAction(stepManager19)
            .setUnlocalizedName("jungleFence")
      );
      processAction(
         191,
         "dark_oak_fence",
         new BlockAcaciaFence(Material.material, MovementProperty.DARK_OAK.getMapColor())
            .getBlock5(2.0F)
            .setHardness(5.0F)
            .processAction(stepManager19)
            .setUnlocalizedName("darkOakFence")
      );
      processAction(
         192,
         "acacia_fence",
         new BlockAcaciaFence(Material.material, MovementProperty.ACACIA.getMapColor())
            .getBlock5(2.0F)
            .setHardness(5.0F)
            .processAction(stepManager19)
            .setUnlocalizedName("acaciaFence")
      );
      processAction(
         193,
         "spruce_door",
         new BlockDarkOakDoor(Material.material).getBlock5(3.0F).processAction(stepManager19).setUnlocalizedName("doorSpruce").processAction12()
      );
      processAction(
         194,
         "birch_door",
         new BlockDarkOakDoor(Material.material).getBlock5(3.0F).processAction(stepManager19).setUnlocalizedName("doorBirch").processAction12()
      );
      processAction(
         195,
         "jungle_door",
         new BlockDarkOakDoor(Material.material).getBlock5(3.0F).processAction(stepManager19).setUnlocalizedName("doorJungle").processAction12()
      );
      processAction(
         196,
         "acacia_door",
         new BlockDarkOakDoor(Material.material).getBlock5(3.0F).processAction(stepManager19).setUnlocalizedName("doorAcacia").processAction12()
      );
      processAction(
         197,
         "dark_oak_door",
         new BlockDarkOakDoor(Material.material).getBlock5(3.0F).processAction(stepManager19).setUnlocalizedName("doorDarkOak").processAction12()
      );
      iterableExtensionBridge.notNull();

      for (Block block14 : (Iterable<Block>)(Iterable<?>)iterableExtensionBridge) {
         if (block14.coreItemEntry == Material.coreItemEntry) {
            block14.state25 = false;
         } else {
            boolean flag = false;
            boolean flag2 = block14 instanceof BlockRedSandstoneStairs;
            boolean flag3 = block14 instanceof ExtraBlockAdapter;
            boolean flag4 = block14 == block7;
            boolean state2 = block14.state;
            boolean flag5 = block14.indexOffset == 0;
            if (flag2 || flag3 || flag4 || state2 || flag5) {
               flag = true;
            }

            block14.state25 = flag;
         }
      }

      for (Block block15 : (Iterable<Block>)(Iterable<?>)iterableExtensionBridge) {
         UnmodifiableIterator iterator = block15.getBlockManager().getImmutableList().iterator();

         while (iterator.hasNext()) {
            IBlockState blockState = (IBlockState)iterator.next();
            int count = iterableExtensionBridge.getIndex(block15) << 4 | block15.getIndex(blockState);
            identityHashMapEntry.applyTarget(blockState, count);
         }
      }
   }

   public Vec3 resolveVecByDouble(double posX, double posX2, double posX3, double posX4, double posX5, double posY, double posZ) {
      if (posX5 * posX5 < 1.0E-7F) {
         return null;
      } else {
         double offset = (posX4 - posX) / posX5;
         return offset >= 0.0 && offset <= 1.0 ? new Vec3(posX + posX5 * offset, posX2 + posY * offset, posX3 + posZ * offset) : null;
      }
   }

   public int processAction8() {
      return this.coreItemEntry.getIndex();
   }

   public Block getBlock5(float factor) {
      this.scaleFactor22 = factor;
      if (this.scaleFactor21 < factor * 5.0F) {
         this.scaleFactor21 = factor * 5.0F;
      }

      return this;
   }

   public final void setIBlockState(IBlockState iBlockState2) {
      this.iBlockState = iBlockState2;
   }

   public boolean processAction(IBlockState blockState, boolean flag) {
      return this.processAction10();
   }

   public void applyWorld(World world, BlockPos blockPos, int count) {
      int count2 = count;
      if (!world.state) {
         while (count2 > 0) {
            int count3 = EntityXporb.computeCount(count2);
            count2 -= count3;
            world.matchesEntity(new EntityXporb(world, (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5, count3));
         }
      }
   }

   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
   }

   public static IBlockState processAction2(int count) {
      int count2 = count & 4095;
      int count3 = count >> 12 & 15;
      return processAction3(count2).processAction(count3);
   }

   public void processAction(Item item, ItemGroupManager itemGroupManager2, List list) {
      list.add(new ItemStack(item, 1, 0));
   }

   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, int count) {
      return 16777215;
   }

   private static void processAction(int count, ResourceLocation location, Block block) {
      iterableExtensionBridge.applyObject2(count, location, block);
   }

   public boolean processAction7(World world, BlockPos blockPos) {
      return world.getBlockState(blockPos).getBlock().coreItemEntry.canExecute();
   }

   public int processAction(Random random) {
      return 1;
   }

   public BlockNoneType processAction9() {
      return BlockNoneType.NONE;
   }

   public boolean processAction(World world, BlockPos blockPos, EnumFacing enumFacing) {
      return this.processAction7(world, blockPos);
   }

   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return false;
   }

   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      return this.processAction(count);
   }

   public static Block getBlockFromName(String text2) {
      ResourceLocation location = new ResourceLocation(text2);
      if (iterableExtensionBridge.containsKey(location)) {
         return (Block)iterableExtensionBridge.getObject(location);
      } else {
         try {
            return (Block)iterableExtensionBridge.getObject3(Integer.parseInt(text2));
         } catch (NumberFormatException numberFormatException) {
            return null;
         }
      }
   }

   public static int getIdFromBlock(Block block) {
      return iterableExtensionBridge.getIndex(block);
   }

   public Vec3 resolveVec2(double posX, double posX2, double posX3, double posX4, double posX5, double posY, double posZ) {
      if (posY * posY < 1.0E-7F) {
         return null;
      } else {
         double offset = (posX4 - posX2) / posY;
         return offset >= 0.0 && offset <= 1.0 ? new Vec3(posX + posX5 * offset, posX2 + posY * offset, posX3 + posZ * offset) : null;
      }
   }

   public void onNeighborBlockChange(World world, BlockPos blockPos, BlockBreakState blockBreakState) {
   }

   public boolean isState8() {
      return this.state8;
   }

   public final void processAction(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      this.coordOffset = posX;
      this.coordOffset11 = posX2;
      this.coordOffset2 = posX3;
      this.coordOffset9 = posX4;
      this.coordOffset6 = posY;
      this.coordOffset15 = posZ;
   }

   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return !this.coreItemEntry.isActiveAsFlag();
   }

   public boolean processAction(IBlockAccess iBlockAccess, BlockPos blockPos, EnumFacing enumFacing) {
      return iBlockAccess.getBlockState(blockPos).getBlock().getMaterial().canExecute2();
   }

   public final double getCoordOffset6() {
      return this.coordOffset6;
   }

   private boolean matchesVec6(Vec3 vec) {
      return vec != null
         && vec.xCoord >= this.coordOffset
         && vec.xCoord <= this.coordOffset9
         && vec.zCoord >= this.coordOffset2
         && vec.zCoord <= this.coordOffset15;
   }

   public void processAction(World world, BlockPos blockPos, Entity entity, float factor) {
      entity.processFloat2(factor, 1.0F);
   }

   public static boolean matchesBlock(Block block, Block block2) {
      return block != null && block2 != null ? (block == block2 ? true : block.matchesBlock(block2)) : false;
   }

   public boolean processAction10() {
      return true;
   }

   public static Block resolveBlock(Item item) {
      return item instanceof ItemBlock ? ((ItemBlock)item).getBlock() : null;
   }

   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
   }

   public static Block processAction3(int count) {
      return (Block)iterableExtensionBridge.getObject3(count);
   }

   public Material getMaterial() {
      return this.coreItemEntry;
   }

   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      return enumFacing == EnumFacing.DOWN && this.coordOffset11 > 0.0
         || enumFacing == EnumFacing.UP && this.coordOffset6 < 1.0
         || enumFacing == EnumFacing.NORTH && this.coordOffset2 > 0.0
         || enumFacing == EnumFacing.SOUTH && this.coordOffset15 < 1.0
         || enumFacing == EnumFacing.WEST && this.coordOffset > 0.0
         || enumFacing == EnumFacing.EAST && this.coordOffset9 < 1.0
         || !iBlockAccess.getIBlockState(count, count2, count3).getBlock().processAction15();
   }

   public int processAction(int count, Random random) {
      return this.processAction(random);
   }

   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[0]);
   }

   public void applyWorld(World world, EntityPlayer player, BlockPos blockPos, IBlockState blockState, TileEntity tileEntity) {
      player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray4[getIdFromBlock(this)]);
      player.applyFloat4(0.025F);
      if (this.isActive() && LvlManager.matchesEntityLivingBase2(player)) {
         ItemStack stack = this.resolveItemStack(blockState);
         if (stack != null) {
            applyWorld(world, blockPos, stack);
         }
      } else {
         int count = LvlManager.computeCountByEntityLivingBase(player);
         this.processAction(world, blockPos, blockState, count);
      }
   }

   public boolean isState25() {
      return this.state25;
   }

   public Block processAction12() {
      this.state3 = false;
      return this;
   }

   public final double getCoordOffset15() {
      return this.coordOffset15;
   }

   public int processAction2(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return 0;
   }

   public int getMetaFromState(IBlockState blockState) {
      return 0;
   }

   public final void processAction(AxisAlignedBB boundingBox) {
      this.coordOffset = boundingBox.minX;
      this.coordOffset11 = boundingBox.minY;
      this.coordOffset2 = boundingBox.minZ;
      this.coordOffset9 = boundingBox.maxX;
      this.coordOffset6 = boundingBox.maxY;
      this.coordOffset15 = boundingBox.maxZ;
   }

   public int processAction13() {
      return 3;
   }

   private boolean matchesVec4(Vec3 vec) {
      return vec != null && vec.xCoord >= -0.3 && vec.xCoord <= 1.3 && vec.yCoord >= -0.3 && vec.yCoord <= 1.3;
   }

   public boolean isState23() {
      return this.state23;
   }

   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return new AxisAlignedBB(
         (double)count + this.coordOffset,
         (double)count2 + this.coordOffset11,
         (double)count3 + this.coordOffset2,
         (double)count + this.coordOffset9,
         (double)count2 + this.coordOffset6,
         (double)count3 + this.coordOffset15
      );
   }

   public Block getBlockById(int count) {
      this.indexOffset = count;
      return this;
   }

   public int getIndex(IBlockState blockState) {
      if (blockState != null && !blockState.getCollection().isEmpty()) {
         throw new IllegalArgumentException("Don't know how to convert " + blockState + " back into data...");
      } else {
         return 0;
      }
   }

   public boolean matchesBlock(Block block) {
      return this == block;
   }

   public BlockManager getBlockManager() {
      return this.blockManager;
   }

   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, int count, int count2) {
      return false;
   }

   public static int processAction5(IBlockState blockState) {
      Block block = blockState.getBlock();
      return getIdFromBlock(block) + (block.getIndex(blockState) << 12);
   }

   public Vec3 resolveVec3(double posX, double posX2, double posX3, double posX4, double posX5, double posY, double posZ) {
      if (posZ * posZ < 1.0E-7F) {
         return null;
      } else {
         double offset = (posX4 - posX3) / posZ;
         return offset >= 0.0 && offset <= 1.0 ? new Vec3(posX + posX5 * offset, posX2 + posY * offset, posX3 + posZ * offset) : null;
      }
   }

   public MovingObjectPosition resolveMovingObjectPositionByWorld(
      World world, int count, int count2, int count3, double posX, double posX2, double posX3, double posX4, double posY, double posZ
   ) {
      this.processAction(world, count, count2, count3);
      double offset = posX - (double)count;
      double offset2 = posX2 - (double)count2;
      double offset3 = posY - (double)count3;
      double offset4 = posZ - (double)count;
      double offset5 = posX3 - (double)count2;
      double offset6 = posX4 - (double)count3;
      double offset7 = offset4 - offset;
      double offset8 = offset5 - offset2;
      double offset9 = offset6 - offset3;
      Vec3 vec = this.resolveVecByDouble(offset, offset2, offset3, this.coordOffset, offset7, offset8, offset9);
      Vec3 vec2 = this.resolveVecByDouble(offset, offset2, offset3, this.coordOffset9, offset7, offset8, offset9);
      Vec3 vec3 = this.resolveVec2(offset, offset2, offset3, this.coordOffset11, offset7, offset8, offset9);
      Vec3 vec4 = this.resolveVec2(offset, offset2, offset3, this.coordOffset6, offset7, offset8, offset9);
      Vec3 vec5 = this.resolveVec3(offset, offset2, offset3, this.coordOffset2, offset7, offset8, offset9);
      Vec3 vec6 = this.resolveVec3(offset, offset2, offset3, this.coordOffset15, offset7, offset8, offset9);
      if (!this.matchesVec3(vec)) {
         vec = null;
      }

      if (!this.matchesVec3(vec2)) {
         vec2 = null;
      }

      if (!this.matchesVec6(vec3)) {
         vec3 = null;
      }

      if (!this.matchesVec6(vec4)) {
         vec4 = null;
      }

      if (!this.matchesVec2(vec5)) {
         vec5 = null;
      }

      if (!this.matchesVec2(vec6)) {
         vec6 = null;
      }

      Vec3 vec7 = null;
      if (vec != null) {
         vec7 = vec;
      }

      if (vec2 != null && (vec7 == null || vec2.getDouble4(offset, offset2, offset3) < vec7.getDouble4(offset, offset2, offset3))) {
         vec7 = vec2;
      }

      if (vec3 != null && (vec7 == null || vec3.getDouble4(offset, offset2, offset3) < vec7.getDouble4(offset, offset2, offset3))) {
         vec7 = vec3;
      }

      if (vec4 != null && (vec7 == null || vec4.getDouble4(offset, offset2, offset3) < vec7.getDouble4(offset, offset2, offset3))) {
         vec7 = vec4;
      }

      if (vec5 != null && (vec7 == null || vec5.getDouble4(offset, offset2, offset3) < vec7.getDouble4(offset, offset2, offset3))) {
         vec7 = vec5;
      }

      if (vec6 != null && (vec7 == null || vec6.getDouble4(offset, offset2, offset3) < vec7.getDouble4(offset, offset2, offset3))) {
         vec7 = vec6;
      }

      if (vec7 == null) {
         return null;
      } else {
         EnumFacing enumFacing = null;
         if (vec7 == vec) {
            enumFacing = EnumFacing.WEST;
         }

         if (vec7 == vec2) {
            enumFacing = EnumFacing.EAST;
         }

         if (vec7 == vec3) {
            enumFacing = EnumFacing.DOWN;
         }

         if (vec7 == vec4) {
            enumFacing = EnumFacing.UP;
         }

         if (vec7 == vec5) {
            enumFacing = EnumFacing.NORTH;
         }

         if (vec7 == vec6) {
            enumFacing = EnumFacing.SOUTH;
         }

         return new MovingObjectPosition(vec7.getVec312((double)count, (double)count2, (double)count3), enumFacing, new BlockPos(count, count2, count3));
      }
   }

   public ItemStack resolveItemStack(IBlockState blockState) {
      int count = 0;
      Item item = Item.processAction2(this);
      if (item != null && item.isState()) {
         count = this.getIndex(blockState);
      }

      return new ItemStack(item, 1, count);
   }

   public void processAction3(World world, BlockPos blockPos, IBlockState blockState, Random random) {
   }

   private boolean matchesVec2(Vec3 vec) {
      return vec != null
         && vec.xCoord >= this.coordOffset
         && vec.xCoord <= this.coordOffset9
         && vec.yCoord >= this.coordOffset11
         && vec.yCoord <= this.coordOffset6;
   }

   public int getIndexOffset1() {
      return this.indexOffset1;
   }

   public static void applyWorld(World world, BlockPos blockPos, ItemStack stack) {
      if (!world.state && world.getDoFireTickManager().matchesText("doTileDrops")) {
         float factor = 0.5F;
         double offset = (double)(world.random.nextFloat() * factor) + (double)(1.0F - factor) * 0.5;
         double offset2 = (double)(world.random.nextFloat() * factor) + (double)(1.0F - factor) * 0.5;
         double offset3 = (double)(world.random.nextFloat() * factor) + (double)(1.0F - factor) * 0.5;
         EntityItem entityItem = new EntityItem(world, (double)blockPos.getX() + offset, (double)blockPos.getY() + offset2, (double)blockPos.getZ() + offset3, stack);
         entityItem.updateState6();
         world.matchesEntity(entityItem);
      }
   }

   public float getScaleFactor(EntityPlayer player, World world, BlockPos blockPos) {
      float factor = this.processAction8(world, blockPos);
      return factor < 0.0F ? 0.0F : (!player.matchesBlock(this) ? player.computeFactor(this) / factor / 100.0F : player.computeFactor(this) / factor / 30.0F);
   }

   public boolean isState18() {
      return this.state18;
   }

   public String getText() {
      return I18n.getString2(this.getTextAsText() + ".name");
   }

   public float processAction8(World world, BlockPos blockPos) {
      return this.scaleFactor22;
   }

   public boolean isActive2() {
      return this.coreItemEntry.isActive3() && this.processAction14() && !this.processAction7();
   }

   public boolean processAction14() {
      return true;
   }

   public boolean isActive3() {
      return this.coreItemEntry.isActiveAsFlag() && this.processAction14();
   }

   public boolean isState() {
      return this.state;
   }

   public boolean processAction15() {
      return true;
   }

   public boolean processAction16() {
      return false;
   }

   public boolean isActiveAsFlag() {
      return !this.coreItemEntry.canExecute2() && !this.coreItemEntry.isActive2();
   }

   public boolean isState3() {
      return this.state3;
   }

   public boolean processAction17() {
      return true;
   }

   public Block(Material material, MapColor mapColor) {
      this.coreItemEntry = material;
      this.auxiliaryItemArrayEntry = mapColor;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      this.state18 = this.processAction15();
      this.indexOffset = this.processAction15() ? 255 : 0;
      this.state = !material.isActive();
      this.blockManager = this.processAction11();
      this.setIBlockState(this.blockManager.get());
   }

   public Block(Material material) {
      this(material, material.getMapColor());
   }

   @Override
   public String toString() {
      return "Block{" + iterableExtensionBridge.getObject2(this) + "}";
   }
}
