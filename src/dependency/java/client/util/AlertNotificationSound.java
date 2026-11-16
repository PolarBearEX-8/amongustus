package client.util;

import client.event.BlockPlaceListener;
import client.event.ServiceListener;
import client.setting.CombatConfigProperty;
import client.setting.CombatGroupProperty;
import client.setting.EnumFacingPositiveType;
import client.setting.MovementFactorProperty;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AlertNotificationSound {
   public static final Logger logger = LogManager.getLogger();
   public final InternalCharArrayEntry[] internalCharArrayEntryArray = new InternalCharArrayEntry[16];
   public final byte[] byteFlagArray = new byte[256];
   public final int[] countArray = new int[256];
   public final boolean[] enabledArray = new boolean[256];
   public boolean enabled5;
   public final World world;
   public final int[] countArray2;
   public final int indexOffset1;
   public final int indexOffset;
   public boolean enabled6;
   public final Map map = Maps.newHashMap();
   public final DonTKnowHowToSearchForAbstractSet[] donTKnowHowToSearchForAbstractSetArray;
   public boolean enabled7;
   public boolean enabled3;
   public boolean enabled4;
   public boolean enabled;
   public boolean enabled2;
   public long timestamp2;
   public int count2;
   public long timestamp;
   public int count = 4096;
   public ConcurrentLinkedQueue concurrentLinkedQueue = Queues.newConcurrentLinkedQueue();

   public int computeCount(int count3, int count4, int count5, int count6) {
      int count7 = count3 & 15;
      int count8 = count5 & 15;
      InternalCharArrayEntry internalCharArrayEntry = this.internalCharArrayEntryArray[count4 >> 4];
      if (internalCharArrayEntry != null) {
         int count9 = this.world.secondaryFloatArrayEntry.isEnabled() ? 0 : internalCharArrayEntry.computeCount2(count7, count4 & 15, count8);
         count9 -= count6;
         count7 = internalCharArrayEntry.computeCount(count7, count4 & 15, count8);
         if (count7 > count9) {
            count9 = count7;
         }

         return count9;
      } else {
         return !this.world.secondaryFloatArrayEntry.isEnabled() && count6 < CombatConfigProperty.SKY.defaultLightValue
            ? CombatConfigProperty.SKY.defaultLightValue - count6
            : 0;
      }
   }

   public void updateState7() {
      this.enabled7 = true;
      this.enabled3 = true;
      BlockPos blockPos = new BlockPos(this.indexOffset1 << 4, 0, this.indexOffset << 4);
      if (!this.world.secondaryFloatArrayEntry.isEnabled()) {
         if (this.world.matchesBlockPos(blockPos.add(-1, 0, -1), blockPos.add(16, this.world.getIndexOffset1(), 16))) {
            outerLoop:
            for (int index = 0; index < 16; index++) {
               for (int index2 = 0; index2 < 16; index2++) {
                  if (!this.checkIndexByIndex(index, index2)) {
                     this.enabled3 = false;
                     break outerLoop;
                  }
               }
            }

            if (this.enabled3) {
               for (Object element : MovementFactorProperty.HORIZONTAL) {
                  int count3 = ((EnumFacing)element).getEnumFacingPositiveType() == EnumFacingPositiveType.POSITIVE ? 16 : 1;
                  this.world.getLoggerEntry(blockPos.getBlockPos((EnumFacing)element, count3)).applyEnumFacing(((EnumFacing)element).getEnumFacing5());
               }

               this.updateState4();
            }
         } else {
            this.enabled3 = false;
         }
      }
   }

   public IBlockState resolveIBlockState(BlockPos blockPos) {
      if (this.world.getInfoManager() == InfoManager.infoManager4) {
         IBlockState blockState = null;
         if (blockPos.getY() == 60) {
            blockState = Blocks.block145.getDefaultState();
         }

         if (blockPos.getY() == 70) {
            blockState = AngleProvider.resolveIBlockState(blockPos.getX(), blockPos.getZ());
         }

         return blockState == null ? Blocks.air.getDefaultState() : blockState;
      } else {
         try {
            if (blockPos.getY() >= 0 && blockPos.getY() >> 4 < this.internalCharArrayEntryArray.length) {
               InternalCharArrayEntry internalCharArrayEntry = this.internalCharArrayEntryArray[blockPos.getY() >> 4];
               if (internalCharArrayEntry != null) {
                  int count3 = blockPos.getX() & 15;
                  int count4 = blockPos.getY() & 15;
                  int count5 = blockPos.getZ() & 15;
                  return internalCharArrayEntry.resolveIBlockState(count3, count4, count5);
               }
            }

            return Blocks.air.getDefaultState();
         } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Getting block state");
            crashReport.resolveCrashReportCategory("Block being got").applyText("Location", new AngleFilter(this, blockPos));
            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         }
      }
   }

   private void applyIndexByIndex(int count3, int count4, int count5) {
      int coordY = this.world.resolveBlockPos2(new BlockPos(count3, 0, count4)).getY();
      if (coordY > count5) {
         this.applyIndex(count3, count4, count5, coordY + 1);
      } else if (coordY < count5) {
         this.applyIndex(count3, count4, coordY, count5 + 1);
      }
   }

   public void applyEntity3(Entity entity) {
      this.applyEntityByEntity(entity, entity.count2);
   }

   private void setEnabled(boolean enabled8) {
      this.world.uNKNOWNManager.applyTextByText("recheckGaps");
      if (this.world.checkIndex(this.indexOffset1 * 16 + 8, 0, this.indexOffset * 16 + 8, 16)) {
         for (int index = 0; index < 16; index++) {
            for (int index2 = 0; index2 < 16; index2++) {
               if (this.enabledArray[index + index2 * 16]) {
                  this.enabledArray[index + index2 * 16] = false;
                  int count3 = this.computeCount(index, index2);
                  int count4 = this.indexOffset1 * 16 + index;
                  int count5 = this.indexOffset * 16 + index2;
                  int count6 = Integer.MAX_VALUE;

                  for (Object element : MovementFactorProperty.HORIZONTAL) {
                     count6 = Math.min(count6, this.world.computeCountByIndex(count4 + ((EnumFacing)element).getInt2(), count5 + ((EnumFacing)element).getInt()));
                  }

                  this.applyIndexByIndex(count4, count5, count6);

                  for (Object element2 : MovementFactorProperty.HORIZONTAL) {
                     this.applyIndexByIndex(count4 + ((EnumFacing)element2).getInt2(), count5 + ((EnumFacing)element2).getInt(), count3);
                  }

                  if (enabled8) {
                     this.world.uNKNOWNManager.updateState();
                     return;
                  }
               }
            }
         }

         this.enabled6 = false;
      }

      this.world.uNKNOWNManager.updateState();
   }

   public void updateState3() {
      int count3 = this.indexOffset1 << 4;
      int count4 = this.indexOffset << 4;

      for (int index = 0; index < 8; index++) {
         if (this.count >= 4096) {
            return;
         }

         int count5 = this.count % 16;
         int count6 = this.count / 16 % 16;
         int count7 = this.count / 256;
         this.count++;

         for (int index2 = 0; index2 < 16; index2++) {
            int count8 = count3 + count6;
            int count9 = 0 + (count5 << 4) + index2;
            int count10 = count4 + count7;
            boolean flag = index2 == 0 || index2 == 15 || count6 == 0 || count6 == 15 || count7 == 0 || count7 == 15;
            if (this.internalCharArrayEntryArray[count5] == null && flag
               || this.internalCharArrayEntryArray[count5] != null
                  && this.internalCharArrayEntryArray[count5].resolveBlock(count6, index2, count7).getMaterial() == Material.coreItemEntry) {
               for (EnumFacing enumFacing : EnumFacing.getEntries()) {
                  int count11 = count8 + enumFacing.getInt2();
                  int count12 = count9 + enumFacing.getInt3();
                  int count13 = count10 + enumFacing.getInt();
                  if (this.world.getIBlockState(count11, count12, count13).getBlock().getIndexOffset1() > 0) {
                     this.world.checkIndexByIndex(count11, count12, count13);
                  }
               }

               this.world.checkIndexByIndex(count8, count9, count10);
            }
         }
      }
   }

   public World getWorld() {
      return this.world;
   }

   public void applyBlockPos(BlockPos blockPos) {
      if (this.enabled5) {
         TileEntity tileEntity = (TileEntity)this.map.remove(blockPos);
         if (tileEntity != null) {
            tileEntity.updateState3();
         }
      }
   }

   public void applyClass(Class clazz, AxisAlignedBB boundingBox, List list, Predicate predicate) {
      int count3 = MathHelper.ceiling_double_int((boundingBox.minY - 2.0) / 16.0);
      int count4 = MathHelper.ceiling_double_int((boundingBox.maxY + 2.0) / 16.0);
      count3 = MathHelper.getInt12(count3, 0, this.donTKnowHowToSearchForAbstractSetArray.length - 1);

      for (int index = MathHelper.getInt12(count4, 0, this.donTKnowHowToSearchForAbstractSetArray.length - 1); count3 <= index; count3++) {
         for (Entity entity : (Iterable<Entity>)(Iterable<?>)this.donTKnowHowToSearchForAbstractSetArray[count3].resolveIterable(clazz)) {
            if (entity.getBoundingBox2().intersectsWith(boundingBox) && (predicate == null || predicate.apply(entity))) {
               list.add(entity);
            }
         }
      }
   }

   private void applyIndex(int count3, int count4) {
      this.enabledArray[count3 + count4 * 16] = true;
      this.enabled6 = true;
   }

   public boolean canExecute() {
      return false;
   }

   public void updateState2() {
      this.enabled5 = false;

      for (TileEntity tileEntity : (Iterable<TileEntity>)(Iterable<?>)this.map.values()) {
         this.world.add(tileEntity);
      }

      for (int index = 0; index < this.donTKnowHowToSearchForAbstractSetArray.length; index++) {
         this.world.addAll(this.donTKnowHowToSearchForAbstractSetArray[index]);
      }
   }

   public boolean checkIndex(int count3, int count4) {
      if (count3 < 0) {
         count3 = 0;
      }

      if (count4 >= 256) {
         count4 = 255;
      }

      for (int index = count3; index <= count4; index += 16) {
         InternalCharArrayEntry internalCharArrayEntry = this.internalCharArrayEntryArray[index >> 4];
         if (internalCharArrayEntry != null && !internalCharArrayEntry.isActiveAsFlag()) {
            return false;
         }
      }

      return true;
   }

   private int computeCount(int count3, int count4, int count5) {
      if (count4 >> 4 >= this.internalCharArrayEntryArray.length) {
         return 0;
      } else {
         InternalCharArrayEntry internalCharArrayEntry = this.internalCharArrayEntryArray[count4 >> 4];
         return internalCharArrayEntry != null ? internalCharArrayEntry.computeCountByIndex(count3, count4 & 15, count5) : 0;
      }
   }

   public void applyTileEntity(TileEntity tileEntity) {
      this.applyBlockPos(tileEntity.getBlockPos(), tileEntity);
      if (this.enabled5) {
         this.world.matchesTileEntity(tileEntity);
      }
   }

   public int computeCount(CombatConfigProperty combatConfigProperty, int count3, int count4, int count5) {
      int count6 = count3 & 15;
      int count7 = count5 & 15;
      InternalCharArrayEntry internalCharArrayEntry = this.internalCharArrayEntryArray[count4 >> 4];
      return internalCharArrayEntry == null
         ? (this.checkIndex(count3, count4, count5) ? combatConfigProperty.defaultLightValue : 0)
         : (
            combatConfigProperty == CombatConfigProperty.SKY
               ? (this.world.secondaryFloatArrayEntry.isEnabled() ? 0 : internalCharArrayEntry.computeCount2(count6, count4 & 15, count7))
               : (combatConfigProperty == CombatConfigProperty.BLOCK ? internalCharArrayEntry.computeCount(count6, count4 & 15, count7) : combatConfigProperty.defaultLightValue)
         );
   }

   public void setEnabled3(boolean enabled8) {
      this.enabled3 = enabled8;
   }

   public void applyIChunkProvider(IChunkProvider iChunkProvider, IChunkProvider iChunkProvider2, int count3, int count4) {
      boolean flag = iChunkProvider.checkIndex(count3, count4 - 1);
      boolean flag2 = iChunkProvider.checkIndex(count3 + 1, count4);
      boolean flag3 = iChunkProvider.checkIndex(count3, count4 + 1);
      boolean flag4 = iChunkProvider.checkIndex(count3 - 1, count4);
      boolean flag5 = iChunkProvider.checkIndex(count3 - 1, count4 - 1);
      boolean flag6 = iChunkProvider.checkIndex(count3 + 1, count4 + 1);
      boolean flag7 = iChunkProvider.checkIndex(count3 - 1, count4 + 1);
      boolean flag8 = iChunkProvider.checkIndex(count3 + 1, count4 - 1);
      if (flag2 && flag3 && flag6) {
         if (!this.enabled7) {
            iChunkProvider.applyIChunkProvider(iChunkProvider2, count3, count4);
         } else {
            iChunkProvider.matchesIChunkProvider(iChunkProvider2, this, count3, count4);
         }
      }

      if (flag4 && flag3 && flag7) {
         AlertNotificationSound alertNotificationSound = iChunkProvider.resolveAlertNotificationSound(count3 - 1, count4);
         if (!alertNotificationSound.enabled7) {
            iChunkProvider.applyIChunkProvider(iChunkProvider2, count3 - 1, count4);
         } else {
            iChunkProvider.matchesIChunkProvider(iChunkProvider2, alertNotificationSound, count3 - 1, count4);
         }
      }

      if (flag && flag2 && flag8) {
         AlertNotificationSound alertNotificationSound2 = iChunkProvider.resolveAlertNotificationSound(count3, count4 - 1);
         if (!alertNotificationSound2.enabled7) {
            iChunkProvider.applyIChunkProvider(iChunkProvider2, count3, count4 - 1);
         } else {
            iChunkProvider.matchesIChunkProvider(iChunkProvider2, alertNotificationSound2, count3, count4 - 1);
         }
      }

      if (flag5 && flag && flag4) {
         AlertNotificationSound alertNotificationSound3 = iChunkProvider.resolveAlertNotificationSound(count3 - 1, count4 - 1);
         if (!alertNotificationSound3.enabled7) {
            iChunkProvider.applyIChunkProvider(iChunkProvider2, count3 - 1, count4 - 1);
         } else {
            iChunkProvider.matchesIChunkProvider(iChunkProvider2, alertNotificationSound3, count3 - 1, count4 - 1);
         }
      }
   }

   public int computeCount(BlockPos blockPos) {
      return this.computeCount(blockPos.getX() & 15, blockPos.getZ() & 15);
   }

   public void updateState() {
      this.count = 0;
   }

   public void setEnabled2(boolean enabled8) {
      this.enabled2 = enabled8;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public InternalCharArrayEntry[] getInternalCharArrayEntryArray() {
      return this.internalCharArrayEntryArray;
   }

   public void applyInternalCharArrayEntryArra(InternalCharArrayEntry[] internalCharArrayEntryArray2) {
      if (this.internalCharArrayEntryArray.length != internalCharArrayEntryArray2.length) {
         logger.warn("Could not set level chunk sections, array length is " + internalCharArrayEntryArray2.length + " instead of " + this.internalCharArrayEntryArray.length);
      } else {
         System.arraycopy(internalCharArrayEntryArray2, 0, this.internalCharArrayEntryArray, 0, this.internalCharArrayEntryArray.length);
      }
   }

   public void updateState5() {
      this.enabled = true;
   }

   public void applyByteArray(byte[] byteValueArray, int count3, boolean flag) {
      int count4 = 0;
      boolean flag2 = !this.world.secondaryFloatArrayEntry.isEnabled();

      for (int index = 0; index < this.internalCharArrayEntryArray.length; index++) {
         if ((count3 & 1 << index) != 0) {
            if (this.internalCharArrayEntryArray[index] == null) {
               this.internalCharArrayEntryArray[index] = new InternalCharArrayEntry(index << 4, flag2);
            }

            char[] charCodeArray = this.internalCharArrayEntryArray[index].getCharCodeArray();

            for (int index2 = 0; index2 < charCodeArray.length; index2++) {
               charCodeArray[index2] = (char)((byteValueArray[count4 + 1] & 255) << 8 | byteValueArray[count4] & 255);
               count4 += 2;
            }
         } else if (flag && this.internalCharArrayEntryArray[index] != null) {
            this.internalCharArrayEntryArray[index] = null;
         }
      }

      for (int index3 = 0; index3 < this.internalCharArrayEntryArray.length; index3++) {
         if ((count3 & 1 << index3) != 0 && this.internalCharArrayEntryArray[index3] != null) {
            SpecialByteArrayEntry specialByteArrayEntry = this.internalCharArrayEntryArray[index3].getSpecialByteArrayEntry2();
            System.arraycopy(byteValueArray, count4, specialByteArrayEntry.getByteFlagArray(), 0, specialByteArrayEntry.getByteFlagArray().length);
            count4 += specialByteArrayEntry.getByteFlagArray().length;
         }
      }

      if (flag2) {
         for (int index4 = 0; index4 < this.internalCharArrayEntryArray.length; index4++) {
            if ((count3 & 1 << index4) != 0 && this.internalCharArrayEntryArray[index4] != null) {
               SpecialByteArrayEntry specialByteArrayEntry2 = this.internalCharArrayEntryArray[index4].getSpecialByteArrayEntry();
               System.arraycopy(byteValueArray, count4, specialByteArrayEntry2.getByteFlagArray(), 0, specialByteArrayEntry2.getByteFlagArray().length);
               count4 += specialByteArrayEntry2.getByteFlagArray().length;
            }
         }
      }

      if (flag) {
         System.arraycopy(byteValueArray, count4, this.byteFlagArray, 0, this.byteFlagArray.length);
         int length = this.byteFlagArray.length;
      }

      for (int index5 = 0; index5 < this.internalCharArrayEntryArray.length; index5++) {
         if (this.internalCharArrayEntryArray[index5] != null && (count3 & 1 << index5) != 0) {
            this.internalCharArrayEntryArray[index5].updateState();
         }
      }

      this.enabled3 = true;
      this.enabled7 = true;
      this.updateState9();

      for (TileEntity tileEntity : (Iterable<TileEntity>)(Iterable<?>)this.map.values()) {
         tileEntity.updateState4();
      }
   }

   public boolean isEnabled7() {
      return this.enabled7;
   }

   public void setTimestamp2(long timestamp3) {
      this.timestamp2 = timestamp3;
   }

   private void applyEnumFacing(EnumFacing enumFacing) {
      if (this.enabled7) {
         if (enumFacing == EnumFacing.EAST) {
            for (int index = 0; index < 16; index++) {
               this.checkIndexByIndex(15, index);
            }
         } else if (enumFacing == EnumFacing.WEST) {
            for (int index2 = 0; index2 < 16; index2++) {
               this.checkIndexByIndex(0, index2);
            }
         } else if (enumFacing == EnumFacing.SOUTH) {
            for (int index3 = 0; index3 < 16; index3++) {
               this.checkIndexByIndex(index3, 15);
            }
         } else if (enumFacing == EnumFacing.NORTH) {
            for (int index4 = 0; index4 < 16; index4++) {
               this.checkIndexByIndex(index4, 0);
            }
         }
      }
   }

   public int getCount() {
      for (int index = this.internalCharArrayEntryArray.length - 1; index >= 0; index--) {
         if (this.internalCharArrayEntryArray[index] != null) {
            return this.internalCharArrayEntryArray[index].getCount3();
         }
      }

      return 0;
   }

   private int computeCountByIndex(int count3, int count4, int count5) {
      return this.resolveBlockByIndex(count3, count4, count5).getIndexOffset();
   }

   public boolean checkFlag(boolean flag) {
      if (flag) {
         if (this.enabled2 && this.world.getTimestamp2() != this.timestamp2 || this.enabled) {
            return true;
         }
      } else if (this.enabled2 && this.world.getTimestamp2() >= this.timestamp2 + 600L) {
         return true;
      }

      return this.enabled;
   }

   public void setEnabled5(boolean enabled8) {
      this.enabled5 = enabled8;
   }

   public BlockPos resolveBlockPos(BlockPos blockPos) {
      int count3 = blockPos.getX() & 15;
      int count4 = blockPos.getZ() & 15;
      count3 |= count4 << 4;
      count4 = blockPos.getX();
      int count5 = this.countArray[count3];
      int coordZ = blockPos.getZ();
      if (count5 == -999) {
         count5 = this.getCount() + 15;
         int count6 = -1;

         while (count5 > 0 && count6 == -1) {
            Material material = this.resolveBlock(count4, count5, coordZ).getMaterial();
            if (!material.isActiveAsFlag() && !material.isActive2()) {
               count5--;
            } else {
               count6 = count5 + 1;
            }
         }

         this.countArray[count3] = count6;
      }

      return new BlockPos(blockPos.getX(), this.countArray[count3], blockPos.getZ());
   }

   private boolean checkIndexByIndex(int count3, int count4) {
      int count5 = this.getCount();
      boolean flag = false;
      boolean flag2 = false;
      int count6 = (this.indexOffset1 << 4) + count3;
      int count7 = 0;
      int count8 = (this.indexOffset << 4) + count4;

      for (int index = count5 + 16 - 1; index > this.world.getIndexOffset1() || index > 0 && !flag2; index--) {
         count7 = index;
         int count9 = this.computeCount2(count6, index, count8);
         if (count9 == 255 && index < this.world.getIndexOffset1()) {
            flag2 = true;
         }

         if (!flag && count9 > 0) {
            flag = true;
         } else if (flag && count9 == 0 && !this.world.checkIndexByIndex(count6, index, count8)) {
            return false;
         }
      }

      for (int index2 = count7; index2 > 0; index2--) {
         if (this.resolveBlock(count6, index2, count8).getIndexOffset1() > 0) {
            this.world.checkIndexByIndex(count6, index2, count8);
         }
      }

      return true;
   }

   private void applyIndex(int count3, int count4, int count5) {
      int count6 = this.countArray2[count5 << 4 | count3] & 0xFF;
      int count7 = count6;
      if (count4 > count6) {
         count7 = count4;
      }

      while (count7 > 0 && this.computeCountByIndex(count3, count7 - 1, count5) == 0) {
         count7--;
      }

      if (count7 != count6) {
         this.world.applyIndex(count3 + this.indexOffset1 * 16, count5 + this.indexOffset * 16, count7, count6);
         this.countArray2[count5 << 4 | count3] = count7;
         int count8 = this.indexOffset1 * 16 + count3;
         int count9 = this.indexOffset * 16 + count5;
         if (!this.world.secondaryFloatArrayEntry.isEnabled()) {
            if (count7 < count6) {
               for (int index = count7; index < count6; index++) {
                  InternalCharArrayEntry internalCharArrayEntry = this.internalCharArrayEntryArray[index >> 4];
                  if (internalCharArrayEntry != null) {
                     internalCharArrayEntry.applyIndex(count3, index & 15, count5, 15);
                     this.world.applyIndex((this.indexOffset1 << 4) + count3, index, (this.indexOffset << 4) + count5);
                  }
               }
            } else {
               for (int index2 = count6; index2 < count7; index2++) {
                  InternalCharArrayEntry internalCharArrayEntry2 = this.internalCharArrayEntryArray[index2 >> 4];
                  if (internalCharArrayEntry2 != null) {
                     internalCharArrayEntry2.applyIndex(count3, index2 & 15, count5, 0);
                     this.world.applyIndex((this.indexOffset1 << 4) + count3, index2, (this.indexOffset << 4) + count5);
                  }
               }
            }

            int count10 = 15;

            while (count7 > 0 && count10 > 0) {
               int count11 = this.computeCountByIndex(count3, --count7, count5);
               if (count11 == 0) {
                  count11 = 1;
               }

               count10 -= count11;
               if (count10 < 0) {
                  count10 = 0;
               }

               InternalCharArrayEntry internalCharArrayEntry3 = this.internalCharArrayEntryArray[count7 >> 4];
               if (internalCharArrayEntry3 != null) {
                  internalCharArrayEntry3.applyIndex(count3, count7 & 15, count5, count10);
               }
            }
         }

         int count12 = this.countArray2[count5 << 4 | count3];
         int count13 = count6;
         count7 = count12;
         if (count12 < count6) {
            count13 = count12;
            count7 = count6;
         }

         if (count12 < this.count2) {
            this.count2 = count12;
         }

         if (!this.world.secondaryFloatArrayEntry.isEnabled()) {
            for (Object element : MovementFactorProperty.HORIZONTAL) {
               this.applyIndex(count8 + ((EnumFacing)element).getInt2(), count9 + ((EnumFacing)element).getInt(), count13, count7);
            }

            this.applyIndex(count8, count9, count13, count7);
         }

         this.enabled = true;
      }
   }

   public void applyIndexArray(int[] countArray3) {
      if (this.countArray2.length != countArray3.length) {
         logger.warn("Could not set level chunk heightmap, array length is " + countArray3.length + " instead of " + this.countArray2.length);
      } else {
         for (int index = 0; index < this.countArray2.length; index++) {
            this.countArray2[index] = countArray3[index];
         }
      }
   }

   private void applyIndex(int count3, int count4, int count5, int count6) {
      if (count6 > count5 && this.world.checkIndex(count3, 0, count4, 16)) {
         for (int index = count5; index < count6; index++) {
            this.world.matchesCombatConfigProperty(CombatConfigProperty.SKY, count3, index, count4);
         }

         this.enabled = true;
      }
   }

   public IBlockState resolveIBlockState(int count3, int count4, int count5) {
      if (count4 >= 0 && count4 >> 4 < this.internalCharArrayEntryArray.length) {
         InternalCharArrayEntry internalCharArrayEntry = this.internalCharArrayEntryArray[count4 >> 4];
         if (internalCharArrayEntry != null) {
            int count6 = count3 & 15;
            int count7 = count4 & 15;
            int count8 = count5 & 15;
            return internalCharArrayEntry.resolveIBlockState(count6, count7, count8);
         }
      }

      return Blocks.air.getDefaultState();
   }

   public void updateState8() {
      int count3 = this.getCount();
      this.count2 = Integer.MAX_VALUE;

      for (int index = 0; index < 16; index++) {
         for (int index2 = 0; index2 < 16; index2++) {
            this.countArray[index + (index2 << 4)] = -999;

            for (int index3 = count3 + 16; index3 > 0; index3--) {
               if (this.computeCountByIndex(index, index3 - 1, index2) != 0) {
                  this.countArray2[index2 << 4 | index] = index3;
                  if (index3 < this.count2) {
                     this.count2 = index3;
                  }
                  break;
               }
            }

            if (!this.world.secondaryFloatArrayEntry.isEnabled()) {
               int count4 = 15;
               int count5 = count3 + 16 - 1;

               while (true) {
                  int count6 = this.computeCountByIndex(index, count5, index2);
                  if (count6 == 0 && count4 != 15) {
                     count6 = 1;
                  }

                  count4 -= count6;
                  if (count4 > 0) {
                     InternalCharArrayEntry internalCharArrayEntry = this.internalCharArrayEntryArray[count5 >> 4];
                     if (internalCharArrayEntry != null) {
                        internalCharArrayEntry.applyIndex(index, count5 & 15, index2, count4);
                        this.world.applyIndex((this.indexOffset1 << 4) + index, count5, (this.indexOffset << 4) + index2);
                     }
                  }

                  if (--count5 <= 0 || count4 <= 0) {
                     break;
                  }
               }
            }
         }
      }

      this.enabled = true;
   }

   public boolean isActive() {
      return this.enabled4 && this.enabled7 && this.enabled3;
   }

   public void applyBlockPos(BlockPos blockPos, TileEntity tileEntity) {
      tileEntity.setWorld(this.world);
      tileEntity.setBlockPos(blockPos);
      if (this.resolveBlock(blockPos) instanceof ServiceListener) {
         if (this.map.containsKey(blockPos)) {
            ((TileEntity)this.map.get(blockPos)).updateState3();
         }

         tileEntity.updateState5();
         this.map.put(blockPos, tileEntity);
      }
   }

   public void applyEntityByEntity(Entity entity, int count3) {
      if (count3 < 0) {
         count3 = 0;
      }

      if (count3 >= this.donTKnowHowToSearchForAbstractSetArray.length) {
         count3 = this.donTKnowHowToSearchForAbstractSetArray.length - 1;
      }

      this.donTKnowHowToSearchForAbstractSetArray[count3].remove(entity);
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   public void setTimestamp(long timestamp3) {
      this.timestamp = timestamp3;
   }

   public boolean checkIndex2(int count3, int count4) {
      return count3 == this.indexOffset1 && count4 == this.indexOffset;
   }

   private Block resolveBlockByIndex(int count3, int count4, int count5) {
      Block air = Blocks.air;
      if (count4 >= 0 && count4 >> 4 < this.internalCharArrayEntryArray.length) {
         InternalCharArrayEntry internalCharArrayEntry = this.internalCharArrayEntryArray[count4 >> 4];
         if (internalCharArrayEntry != null) {
            try {
               air = internalCharArrayEntry.resolveBlock(count3, count4 & 15, count5);
            } catch (Throwable throwable) {
               CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Getting block");
               throw new SecondaryRuntimeExceptionAdapter(crashReport);
            }
         }
      }

      return air;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public void updateState9() {
      int count3 = this.getCount();
      this.count2 = Integer.MAX_VALUE;

      for (int index = 0; index < 16; index++) {
         for (int index2 = 0; index2 < 16; index2++) {
            this.countArray[index + (index2 << 4)] = -999;

            for (int index3 = count3 + 16; index3 > 0; index3--) {
               if (this.resolveBlockByIndex(index, index3 - 1, index2).getIndexOffset() != 0) {
                  this.countArray2[index2 << 4 | index] = index3;
                  if (index3 < this.count2) {
                     this.count2 = index3;
                  }
                  break;
               }
            }
         }
      }

      this.enabled = true;
   }

   public void applyEntityByEntity(Entity entity, AxisAlignedBB boundingBox, List list, Predicate predicate) {
      int count3 = MathHelper.ceiling_double_int((boundingBox.minY - 2.0) / 16.0);
      int count4 = MathHelper.ceiling_double_int((boundingBox.maxY + 2.0) / 16.0);
      count3 = MathHelper.getInt12(count3, 0, this.donTKnowHowToSearchForAbstractSetArray.length - 1);

      for (int index = MathHelper.getInt12(count4, 0, this.donTKnowHowToSearchForAbstractSetArray.length - 1); count3 <= index; count3++) {
         if (!this.donTKnowHowToSearchForAbstractSetArray[count3].isEmpty()) {
            for (Entity entity2 : (Iterable<Entity>)(Iterable<?>)this.donTKnowHowToSearchForAbstractSetArray[count3]) {
               if (entity2.getBoundingBox2().intersectsWith(boundingBox) && entity2 != entity) {
                  if (predicate == null || predicate.apply(entity2)) {
                     list.add(entity2);
                  }

                  Entity[] entityArray = entity2.getEntityArray();
                  if (entityArray != null) {
                     for (int index2 = 0; index2 < entityArray.length; index2++) {
                        entity2 = entityArray[index2];
                        if (entity2 != entity && entity2.getBoundingBox2().intersectsWith(boundingBox) && (predicate == null || predicate.apply(entity2))) {
                           list.add(entity2);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public boolean checkIndex(int count3, int count4, int count5) {
      int count6 = count3 & 15;
      int count7 = count5 & 15;
      return count4 >= this.countArray2[count7 << 4 | count6];
   }

   public int getCount2() {
      return this.count2;
   }

   public void applyCombatConfigProperty(CombatConfigProperty combatConfigProperty, int count3, int count4, int count5, int count6) {
      int count7 = count3 & 15;
      int count8 = count5 & 15;
      InternalCharArrayEntry internalCharArrayEntry = this.internalCharArrayEntryArray[count4 >> 4];
      if (internalCharArrayEntry == null) {
         internalCharArrayEntry = this.internalCharArrayEntryArray[count4 >> 4] = new InternalCharArrayEntry(count4 >> 4 << 4, !this.world.secondaryFloatArrayEntry.isEnabled());
         this.updateState8();
      }

      this.enabled = true;
      if (combatConfigProperty == CombatConfigProperty.SKY) {
         if (!this.world.secondaryFloatArrayEntry.isEnabled()) {
            internalCharArrayEntry.applyIndex(count7, count4 & 15, count8, count6);
         }
      } else if (combatConfigProperty == CombatConfigProperty.BLOCK) {
         internalCharArrayEntry.applyIndexByIndex(count7, count4 & 15, count8, count6);
      }
   }

   public void setEnabled4(boolean enabled8) {
      if (this.enabled6 && !this.world.secondaryFloatArrayEntry.isEnabled() && !enabled8) {
         this.setEnabled(this.world.state);
      }

      this.enabled4 = true;
      if (!this.enabled3 && this.enabled7) {
         this.updateState7();
      }

      while (!this.concurrentLinkedQueue.isEmpty()) {
         BlockPos blockPos = (BlockPos)this.concurrentLinkedQueue.poll();
         if (this.resolveTileEntity(blockPos, CombatGroupProperty.CHECK) == null && this.resolveBlock(blockPos).isState8()) {
            TileEntity tileEntity = this.resolveTileEntity(blockPos);
            this.world.applyBlockPos(blockPos, tileEntity);
            this.world.applyBlockPos(blockPos, blockPos);
         }
      }
   }

   public void updateState6() {
      this.enabled5 = true;
      this.world.applyCollectionByCollection(this.map.values());

      for (int index = 0; index < this.donTKnowHowToSearchForAbstractSetArray.length; index++) {
         Iterator iterator = this.donTKnowHowToSearchForAbstractSetArray[index].iterator();

         while (iterator.hasNext()) {
            ((Entity)iterator.next()).updateState23();
         }

         this.world.applyCollection(this.donTKnowHowToSearchForAbstractSetArray[index]);
      }
   }

   public Map getMap() {
      return this.map;
   }

   public void applyByteArray(byte[] byteValueArray) {
      if (this.byteFlagArray.length != byteValueArray.length) {
         logger.warn("Could not set level chunk biomes, array length is " + byteValueArray.length + " instead of " + this.byteFlagArray.length);
      } else {
         for (int index = 0; index < this.byteFlagArray.length; index++) {
            this.byteFlagArray[index] = byteValueArray[index];
         }
      }
   }

   public int computeCount2(int count3, int count4, int count5) {
      return this.resolveBlock(count3, count4, count5).getIndexOffset();
   }

   public int computeCountByBlockPos(BlockPos blockPos) {
      return this.computeCount(blockPos.getX() & 15, blockPos.getY(), blockPos.getZ() & 15);
   }

   private TileEntity resolveTileEntity(BlockPos blockPos) {
      Block block = this.resolveBlock(blockPos);
      return !block.isState8() ? null : ((ServiceListener)block).resolveTileEntityByWorld(this.world, this.computeCountByBlockPos(blockPos));
   }

   public Block resolveBlock(BlockPos blockPos) {
      try {
         return this.resolveBlockByIndex(blockPos.getX() & 15, blockPos.getY(), blockPos.getZ() & 15);
      } catch (SecondaryRuntimeExceptionAdapter exception) {
         exception.getCrashReport().resolveCrashReportCategory("Block being got").applyText("Location", new SplineValidator(this, blockPos));
         throw exception;
      }
   }

   public DonTKnowHowToSearchForAbstractSet[] getDonTKnowHowToSearchForAbstractSetArray() {
      return this.donTKnowHowToSearchForAbstractSetArray;
   }

   public boolean canExecute2() {
      return this.enabled5;
   }

   public void setEnabled7(boolean enabled8) {
      this.enabled7 = enabled8;
   }

   public IBlockState resolveIBlockState(BlockPos blockPos, IBlockState blockState) {
      int count3 = blockPos.getX() & 15;
      int coordY = blockPos.getY();
      int count4 = blockPos.getZ() & 15;
      int count5 = count4 << 4 | count3;
      if (coordY >= this.countArray[count5] - 1) {
         this.countArray[count5] = -999;
      }

      count5 = this.countArray2[count5];
      IBlockState blockState2 = this.resolveIBlockState(blockPos);
      if (blockState2 == blockState) {
         return null;
      } else {
         Block block = blockState.getBlock();
         Block block2 = blockState2.getBlock();
         InternalCharArrayEntry internalCharArrayEntry = this.internalCharArrayEntryArray[coordY >> 4];
         boolean flag = false;
         if (internalCharArrayEntry == null) {
            if (block == Blocks.air) {
               BlockPlaceListener.applyIBlockState(blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockState);
               return null;
            }

            internalCharArrayEntry = this.internalCharArrayEntryArray[coordY >> 4] = new InternalCharArrayEntry(coordY >> 4 << 4, !this.world.secondaryFloatArrayEntry.isEnabled());
            flag = coordY >= count5;
         }

         internalCharArrayEntry.applyIBlockState(count3, coordY & 15, count4, blockState);
         if (block2 != block) {
            if (!this.world.state) {
               block2.onBlockAdded(this.world, blockPos, blockState2);
            } else if (block2 instanceof ServiceListener) {
               this.world.applyBlockPosByBlockPos(blockPos);
            }
         }

         if (internalCharArrayEntry.resolveBlock(count3, coordY & 15, count4) != block) {
            BlockPlaceListener.applyIBlockState(blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockState);
            return null;
         } else {
            if (flag) {
               this.updateState8();
            } else {
               int indexOffset2 = block.getIndexOffset();
               int indexOffset3 = block2.getIndexOffset();
               if (indexOffset2 > 0) {
                  if (coordY >= count5) {
                     this.applyIndex(count3, coordY + 1, count4);
                  }
               } else if (coordY == count5 - 1) {
                  this.applyIndex(count3, coordY, count4);
               }

               if (indexOffset2 != indexOffset3
                  && (
                     indexOffset2 < indexOffset3
                        || this.computeCount(CombatConfigProperty.SKY, blockPos.getX(), blockPos.getY(), blockPos.getZ()) > 0
                        || this.computeCount(CombatConfigProperty.BLOCK, blockPos.getX(), blockPos.getY(), blockPos.getZ()) > 0
                  )) {
                  this.applyIndex(count3, count4);
               }
            }

            if (block2 instanceof ServiceListener) {
               TileEntity tileEntity = this.resolveTileEntity(blockPos, CombatGroupProperty.CHECK);
               if (tileEntity != null) {
                  tileEntity.updateState4();
               }
            }

            if (!this.world.state && block2 != block) {
               block.processAction3(this.world, blockPos, blockState);
            }

            if (block instanceof ServiceListener) {
               TileEntity tileEntity2 = this.resolveTileEntity(blockPos, CombatGroupProperty.CHECK);
               if (tileEntity2 == null) {
                  tileEntity2 = ((ServiceListener)block).resolveTileEntityByWorld(this.world, block.getIndex(blockState));
                  this.world.applyBlockPos(blockPos, tileEntity2);
               }

               if (tileEntity2 != null) {
                  tileEntity2.updateState4();
               }
            }

            this.enabled = true;
            BlockPlaceListener.applyIBlockState(blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockState);
            return blockState2;
         }
      }
   }

   public TileEntity resolveTileEntity(BlockPos blockPos, CombatGroupProperty combatGroupProperty) {
      TileEntity tileEntity = (TileEntity)this.map.get(blockPos);
      if (tileEntity == null) {
         if (combatGroupProperty == CombatGroupProperty.IMMEDIATE) {
            tileEntity = this.resolveTileEntity(blockPos);
            this.world.applyBlockPos(blockPos, tileEntity);
         } else if (combatGroupProperty == CombatGroupProperty.QUEUED) {
            this.concurrentLinkedQueue.add(blockPos);
         }
      } else if (tileEntity.isEnabled()) {
         this.map.remove(blockPos);
         return null;
      }

      return tileEntity;
   }

   public Block resolveBlock(int count3, int count4, int count5) {
      try {
         return this.resolveBlockByIndex(count3 & 15, count4, count5 & 15);
      } catch (SecondaryRuntimeExceptionAdapter exception) {
         exception.getCrashReport().resolveCrashReportCategory("Block being got").applyText("Location", new TargetWriter(this, count3, count4, count5));
         throw exception;
      }
   }

   public int getInt6(int count3, int count4) {
      int count5 = count3 & 15;
      int count6 = count4 & 15;
      count5 |= count6 << 4;
      count6 = this.countArray[count5];
      if (count6 == -999) {
         count6 = this.getCount() + 15;
         int count7 = -1;

         while (count6 > 0 && count7 == -1) {
            Material material = this.resolveBlock(count3, count6, count4).getMaterial();
            if (!material.isActiveAsFlag() && !material.isActive2()) {
               count6--;
            } else {
               count7 = count6 + 1;
            }
         }

         this.countArray[count5] = count7;
      }

      return this.countArray[count5];
   }

   public Random getRandom() {
      long value = 987234911L;
      return new Random(
         this.world.getTimestamp()
               + (long)(this.indexOffset1 * this.indexOffset1 * 4987142)
               + (long)(this.indexOffset1 * 5947611)
               + (long)(this.indexOffset * this.indexOffset) * 4392871L
               + (long)(this.indexOffset * 389711)
            ^ value
      );
   }

   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(int count3, int count4, InvalidBiomeIdManager invalidBiomeIdManager) {
      int count5 = count3 & 15;
      int count6 = count4 & 15;
      int count7 = this.byteFlagArray[count6 << 4 | count5] & 255;
      if (count7 == 255) {
         BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = invalidBiomeIdManager.resolveBiomeIDIsOutOfBoundsManager(count3, count4, BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35);
         count7 = biomeIDIsOutOfBoundsManager.count;
         this.byteFlagArray[count6 << 4 | count5] = (byte)(count7 & 0xFF);
      }

      BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager2 = BiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager2(count7);
      return biomeIDIsOutOfBoundsManager2 == null ? BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35 : biomeIDIsOutOfBoundsManager2;
   }

   private void updateState4() {
      for (int index = 0; index < this.enabledArray.length; index++) {
         this.enabledArray[index] = true;
      }

      this.setEnabled(false);
   }

   public int computeCount(int count3, int count4) {
      return this.countArray2[count4 << 4 | count3];
   }

   public InterpolationCollector getInterpolationCollector() {
      return new InterpolationCollector(this.indexOffset1, this.indexOffset);
   }

   public void setEnabledByFlag(boolean enabledByFlag) {
      this.enabled = enabledByFlag;
   }

   public int[] getCountArray2() {
      return this.countArray2;
   }

   public void applyEntityByEntity(Entity entity) {
      this.enabled2 = true;
      int count3 = MathHelper.ceiling_double_int(entity.posX / 16.0);
      int count4 = MathHelper.ceiling_double_int(entity.posZ / 16.0);
      if (count3 != this.indexOffset1 || count4 != this.indexOffset) {
         logger.warn(
            "Wrong location! (" + count3 + ", " + count4 + ") should be (" + this.indexOffset1 + ", " + this.indexOffset + "), " + entity, new Object[]{entity}
         );
         entity.updateState9();
      }

      count3 = MathHelper.ceiling_double_int(entity.posY / 16.0);
      if (count3 < 0) {
         count3 = 0;
      }

      if (count3 >= this.donTKnowHowToSearchForAbstractSetArray.length) {
         count3 = this.donTKnowHowToSearchForAbstractSetArray.length - 1;
      }

      entity.enabled9 = true;
      entity.count7 = this.indexOffset1;
      entity.count2 = count3;
      entity.count8 = this.indexOffset;
      this.donTKnowHowToSearchForAbstractSetArray[count3].add(entity);
   }

   public AlertNotificationSound(World world2, ShortArrayEntry shortArrayEntry, int count3, int count4) {
      this(world2, count3, count4);
      boolean flag = !world2.secondaryFloatArrayEntry.isEnabled();

      for (int index = 0; index < 16; index++) {
         for (int index2 = 0; index2 < 16; index2++) {
            for (int index3 = 0; index3 < 256; index3++) {
               int count5 = index * 256 * 16 | index2 * 256 | index3;
               IBlockState blockState = shortArrayEntry.resolveIBlockState(count5);
               if (blockState.getBlock().getMaterial() != Material.coreItemEntry) {
                  count5 = index3 >> 4;
                  if (this.internalCharArrayEntryArray[count5] == null) {
                     this.internalCharArrayEntryArray[count5] = new InternalCharArrayEntry(count5 << 4, flag);
                  }

                  this.internalCharArrayEntryArray[count5].applyIBlockState(index, index3 & 15, index2, blockState);
               }
            }
         }
      }
   }

   public AlertNotificationSound(World world2, int count3, int count4) {
      this.donTKnowHowToSearchForAbstractSetArray = new DonTKnowHowToSearchForAbstractSet[16];
      this.world = world2;
      this.indexOffset1 = count3;
      this.indexOffset = count4;
      this.countArray2 = new int[256];

      for (int index = 0; index < this.donTKnowHowToSearchForAbstractSetArray.length; index++) {
         this.donTKnowHowToSearchForAbstractSetArray[index] = new DonTKnowHowToSearchForAbstractSet(Entity.class);
      }

      Arrays.fill(this.countArray, -999);
      Arrays.fill(this.byteFlagArray, (byte)-1);
   }
}
