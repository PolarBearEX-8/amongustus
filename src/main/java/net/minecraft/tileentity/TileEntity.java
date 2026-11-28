package net.minecraft.tileentity;

import java.util.concurrent.Callable;
import net.minecraft.block.BlockJukebox;
import com.google.common.collect.Maps;
import java.util.Map;
import net.augustus.render.TileEntityEspBounds;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class TileEntity {
   private static final Logger LOGGER = LogManager.getLogger();
   private static Map nameToClassMap = Maps.newHashMap();
   private static Map classToNameMap = Maps.newHashMap();
   public final TileEntityEspBounds espBounds = TileEntityEspBounds.of(this);
   public World worldObj;
   public BlockPos pos = BlockPos.ORIGIN;
   public boolean tileEntityInvalid;
   private int blockMetadata = -1;
   public Block blockType;

   public double getDistanceSq(double d, double d2, double d3) {
      double d4 = (double)this.pos.getX() + 0.5 - d;
      double d5 = (double)this.pos.getY() + 0.5 - d2;
      double d6 = (double)this.pos.getZ() + 0.5 - d3;
      return d4 * d4 + d5 * d5 + d6 * d6;
   }

   public int getBlockMetadata() {
      if (this.blockMetadata == -1) {
         IBlockState blockState = this.worldObj.getBlockState(this.pos);
         this.blockMetadata = blockState.getBlock().getMetaFromState(blockState);
      }

      return this.blockMetadata;
   }

   public World getWorldObj() {
      return this.worldObj;
   }

   public void markDirty() {
      if (this.worldObj != null) {
         IBlockState blockState = this.worldObj.getBlockState(this.pos);
         this.blockMetadata = blockState.getBlock().getMetaFromState(blockState);
         this.worldObj.markChunkDirty(this.pos, this);
         if (this.getBlockType() != Blocks.air) {
            this.worldObj.updateComparatorOutputLevel(this.pos, this.getBlockType());
         }
      }
   }

   public boolean onlyOpsCanSetNbt() {
      return false;
   }

   private static void addMapping(Class clazz, String str) {
      if (nameToClassMap.containsKey(str)) {
         throw new IllegalArgumentException("Duplicate id: " + str);
      } else {
         nameToClassMap.put(str, clazz);
         classToNameMap.put(clazz, str);
      }
   }

   public Block getBlockType() {
      if (this.blockType == null) {
         this.blockType = this.worldObj.getBlockState(this.pos).getBlock();
      }

      return this.blockType;
   }

   public static TileEntity createAndLoadEntity(NBTTagCompound tag) {
      TileEntity tileEntity = null;

      try {
         Class clazz = (Class)nameToClassMap.get(tag.getString("id"));
         if (clazz != null) {
            tileEntity = (TileEntity)clazz.newInstance();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      if (tileEntity != null) {
         tileEntity.readFromNBT(tag);
      } else {
         LOGGER.warn("Skipping BlockEntity with id " + tag.getString("id"));
      }

      return tileEntity;
   }

   public void invalidate() {
      this.tileEntityInvalid = true;
   }

   public void readFromNBT(NBTTagCompound tag) {
      this.pos = new BlockPos(tag.getInteger("x"), tag.getInteger("y"), tag.getInteger("z"));
   }

   public void setPos(BlockPos pos2) {
      this.pos = pos2;
   }

   public void addInfoToCrashReport(CrashReportCategory crashReportCategory) {
      crashReportCategory.addCrashSectionCallable("Name", new TileEntity.ValueCallable(this));
      if (this.worldObj != null) {
         CrashReportCategory.addBlockInfo(crashReportCategory, this.pos, this.getBlockType(), this.getBlockMetadata());
         crashReportCategory.addCrashSectionCallable("Actual block type", new TileEntity.ValueCallable2(this));
         crashReportCategory.addCrashSectionCallable("Actual block data value", new TileEntity.ValueCallable3(this));
      }
   }

   public void setWorldObj(World world) {
      this.worldObj = world;
   }

   public boolean receiveClientEvent(int n, int n2) {
      return false;
   }

   public void validate() {
      this.tileEntityInvalid = false;
   }

   public void updateContainingBlockInfo() {
      this.blockType = null;
      this.blockMetadata = -1;
   }

   public boolean hasWorldObj() {
      return this.worldObj != null;
   }

   public boolean isInvalid() {
      return this.tileEntityInvalid;
   }

   public void writeToNBT(NBTTagCompound tag) {
      String str = (String)classToNameMap.get(this.getClass());
      if (str == null) {
         throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
      } else {
         tag.setString("id", str);
         tag.setInteger("x", this.pos.getX());
         tag.setInteger("y", this.pos.getY());
         tag.setInteger("z", this.pos.getZ());
      }
   }

   static {
      addMapping(TileEntityFurnace.class, "Furnace");
      addMapping(TileEntityChest.class, "Chest");
      addMapping(TileEntityEnderChest.class, "EnderChest");
      addMapping(BlockJukebox.TileEntityJukebox.class, "RecordPlayer");
      addMapping(TileEntityDispenser.class, "Trap");
      addMapping(TileEntityDropper.class, "Dropper");
      addMapping(TileEntitySign.class, "Sign");
      addMapping(TileEntityMobSpawner.class, "MobSpawner");
      addMapping(TileEntityNote.class, "Music");
      addMapping(TileEntityPiston.class, "Piston");
      addMapping(TileEntityBrewingStand.class, "Cauldron");
      addMapping(TileEntityEnchantmentTable.class, "EnchantTable");
      addMapping(TileEntityEndPortal.class, "Airportal");
      addMapping(TileEntityCommandBlock.class, "Control");
      addMapping(TileEntityBeacon.class, "Beacon");
      addMapping(TileEntitySkull.class, "Skull");
      addMapping(TileEntityDaylightDetector.class, "DLDetector");
      addMapping(TileEntityHopper.class, "Hopper");
      addMapping(TileEntityComparator.class, "Comparator");
      addMapping(TileEntityFlowerPot.class, "FlowerPot");
      addMapping(TileEntityBanner.class, "Banner");
   }

   public BlockPos getPos() {
      return this.pos;
   }

   public static Map accessClassToNameMap() {
      return classToNameMap;
   }

   public double getMaxRenderDistanceSquared() {
      return 4096.0;
   }

   public Packet getDescriptionPacket() {
      return null;
   }


   public static class ValueCallable implements Callable {
      public final TileEntity tileEntity;

      public ValueCallable(TileEntity tileEntity2) {
         this.tileEntity = tileEntity2;
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public String callAsString() throws Exception {
         return (String)TileEntity.accessClassToNameMap().get(this.tileEntity.getClass()) + " // " + this.tileEntity.getClass().getCanonicalName();
      }
   }

   public static class ValueCallable2 implements Callable {
      public final TileEntity tileEntity;

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public String callAsString() throws Exception {
         int idFromBlock = Block.getIdFromBlock(this.tileEntity.worldObj.getBlockState(this.tileEntity.pos).getBlock());

         try {
            return String.format("ID #%d (%s // %s)", idFromBlock, Block.getBlockById(idFromBlock).getUnlocalizedName(), Block.getBlockById(idFromBlock).getClass().getCanonicalName());
         } catch (Throwable e) {
            return "ID #" + idFromBlock;
         }
      }

      public ValueCallable2(TileEntity tileEntity2) {
         this.tileEntity = tileEntity2;
      }
   }

   public static class ValueCallable3 implements Callable {
      public final TileEntity tileEntity;

      public String callAsString() throws Exception {
         IBlockState blockState = this.tileEntity.worldObj.getBlockState(this.tileEntity.pos);
         int metaFromState = blockState.getBlock().getMetaFromState(blockState);
         if (metaFromState < 0) {
            return "Unknown? (Got " + metaFromState + ")";
         } else {
            String str = String.format("%4s", Integer.toBinaryString(metaFromState)).replace(" ", "0");
            return String.format("%1$d / 0x%1$X / 0b%2$s", metaFromState, str);
         }
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public ValueCallable3(TileEntity tileEntity2) {
         this.tileEntity = tileEntity2;
      }
   }
}
