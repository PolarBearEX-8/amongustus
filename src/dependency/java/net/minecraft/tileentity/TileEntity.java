package net.minecraft.tileentity;

import client.util.IBlockState;
import client.util.Packet;
import client.util.SplineTracker;
import client.util.TargetCounter;
import client.util.TargetRanker;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class TileEntity {
   public static final Logger logger = LogManager.getLogger();
   public static Map map2 = Maps.newHashMap();
   public static Map map = Maps.newHashMap();
   public World world;
   public BlockPos blockPos = BlockPos.origin;
   public boolean enabled;
   public int count = -1;
   public Block block;

   public Block getBlock() {
      if (this.block == null) {
         this.block = this.world.getBlockState(this.blockPos).getBlock();
      }

      return this.block;
   }

   public double computeValue(double posX, double posY, double posZ) {
      double offset = (double)this.blockPos.getX() + 0.5 - posX;
      double offset2 = (double)this.blockPos.getY() + 0.5 - posY;
      double offset3 = (double)this.blockPos.getZ() + 0.5 - posZ;
      return offset * offset + offset2 * offset2 + offset3 * offset3;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public double getValue() {
      return 4096.0;
   }

   public void updateState5() {
      this.enabled = false;
   }

   public void applyCrashReportCategory(CrashReportCategory crashReportCategory) {
      crashReportCategory.applyText("Name", new TargetRanker(this));
      if (this.world != null) {
         CrashReportCategory.applyCrashReportCategory(crashReportCategory, this.blockPos, this.getBlock(), this.getCount());
         crashReportCategory.applyText("Actual block type", new SplineTracker(this));
         crashReportCategory.applyText("Actual block data value", new TargetCounter(this));
      }
   }

   public static TileEntity resolveTileEntity(NBTTagCompound tagCompound) {
      TileEntity tileEntity = null;

      try {
         Class clazz = (Class)map2.get(tagCompound.resolveText("id"));
         if (clazz != null) {
            tileEntity = (TileEntity)clazz.newInstance();
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }

      if (tileEntity != null) {
         tileEntity.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      } else {
         logger.warn("Skipping BlockEntity with id " + tagCompound.resolveText("id"));
      }

      return tileEntity;
   }

   public void updateState3() {
      this.enabled = true;
   }

   public boolean isActiveAsFlag() {
      return false;
   }

   public static Map getMap() {
      return map;
   }

   public boolean isActive() {
      return this.world != null;
   }

   public void setBlockPos(BlockPos blockPos2) {
      this.blockPos = blockPos2;
   }

   public boolean checkIndex(int count2, int count3) {
      return false;
   }

   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      this.blockPos = new BlockPos(tagCompound.computeCount("x"), tagCompound.computeCount("y"), tagCompound.computeCount("z"));
   }

   private static void applyClass(Class clazz, String text) {
      if (map2.containsKey(text)) {
         throw new IllegalArgumentException("Duplicate id: " + text);
      } else {
         map2.put(text, clazz);
         map.put(clazz, text);
      }
   }

   public World getWorld() {
      return this.world;
   }

   public Packet getPacket() {
      return null;
   }

   public void setWorld(World world2) {
      this.world = world2;
   }

   public void updateState4() {
      this.block = null;
      this.count = -1;
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public int getCount() {
      if (this.count == -1) {
         IBlockState blockState = this.world.getBlockState(this.blockPos);
         this.count = blockState.getBlock().getIndex(blockState);
      }

      return this.count;
   }

   public void processAction5() {
      if (this.world != null) {
         IBlockState blockState = this.world.getBlockState(this.blockPos);
         this.count = blockState.getBlock().getIndex(blockState);
         this.world.applyBlockPosByBlockPos(this.blockPos, this);
         if (this.getBlock() != Blocks.air) {
            this.world.applyBlockPos(this.blockPos, this.getBlock());
         }
      }
   }

   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      String text = (String)map.get(this.getClass());
      if (text == null) {
         throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
      } else {
         tagCompound.put("id", text);
         tagCompound.put("x", this.blockPos.getX());
         tagCompound.put("y", this.blockPos.getY());
         tagCompound.put("z", this.blockPos.getZ());
      }
   }

   static {
      applyClass(TileEntityFurnace.class, "Furnace");
      applyClass(TileEntityChest.class, "Chest");
      applyClass(TileEntityEnderchest.class, "EnderChest");
      applyClass(TileEntityRecordplayer.class, "RecordPlayer");
      applyClass(TileEntityTrap.class, "Trap");
      applyClass(TileEntityDropper.class, "Dropper");
      applyClass(TileEntitySign.class, "Sign");
      applyClass(TileEntityMobspawner.class, "MobSpawner");
      applyClass(TileEntityMusic.class, "Music");
      applyClass(TileEntityPiston.class, "Piston");
      applyClass(TileEntityCauldron.class, "Cauldron");
      applyClass(TileEntityEnchanttable.class, "EnchantTable");
      applyClass(TileEntityAirportal.class, "Airportal");
      applyClass(TileEntityControl.class, "Control");
      applyClass(TileEntityBeacon.class, "Beacon");
      applyClass(TileEntitySkull.class, "Skull");
      applyClass(TileEntityDldetector.class, "DLDetector");
      applyClass(TileEntityHopper.class, "Hopper");
      applyClass(TileEntityComparator.class, "Comparator");
      applyClass(TileEntityFlowerpot.class, "FlowerPot");
      applyClass(TileEntityBanner.class, "Banner");
   }
}
