package client.util;

import client.event.CompositeListener;
import client.setting.MovementThresholdProperty;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.StandardBlockEntry;
import net.minecraft.block.CustomBlockAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class ExtendedRandomEntry implements IChunkProvider {
   public Random random;
   public ClientManagerService clientManagerService;
   public ClientManagerService clientManagerService2;
   public ClientManagerService clientManagerService3;
   public AuxiliaryClientManagerService auxiliaryClientManagerService;
   public ClientManagerService clientManagerService4;
   public ClientManagerService clientManagerService5;
   public ClientManagerService clientManagerService6;
   public World world;
   public final boolean enabled;
   public InfoManager infoManager;
   public final double[] valueArray;
   public final float[] factorArray;
   public GlobalFloatEntry globalFloatEntry;
   public Block block = Blocks.blockLava2;
   public double[] valueArray2 = new double[256];
   public TertiaryRandomEntry tertiaryRandomEntry = new ExtendedRandomEntryAdapter();
   public DynamicListEntry dynamicListEntry = new DynamicListEntry();
   public ActiveListEntry activeListEntry = new ActiveListEntry();
   public DefaultDoubleEntry defaultDoubleEntry = new DefaultDoubleEntry();
   public BezierFilter bezierFilter = new BezierFilter();
   public TertiaryRandomEntry tertiaryRandomEntry2 = new RandomEntryAdapter();
   public StaticListEntry staticListEntry = new StaticListEntry();
   public BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray;
   public double[] valueArray3;
   public double[] valueArray4;
   public double[] valueArray5;
   public double[] valueArray6;

   @Override
   public void applyAlertNotificationSound(AlertNotificationSound alertNotificationSound, int count, int count2) {
      if (this.globalFloatEntry.enabled && this.enabled) {
         this.defaultDoubleEntry.applyIChunkProvider(this, this.world, count, count2, (ShortArrayEntry)null);
      }

      if (this.globalFloatEntry.enabled2 && this.enabled) {
         this.activeListEntry.applyIChunkProvider(this, this.world, count, count2, (ShortArrayEntry)null);
      }

      if (this.globalFloatEntry.enabled3 && this.enabled) {
         this.dynamicListEntry.applyIChunkProvider(this, this.world, count, count2, (ShortArrayEntry)null);
      }

      if (this.globalFloatEntry.enabled4 && this.enabled) {
         this.bezierFilter.applyIChunkProvider(this, this.world, count, count2, (ShortArrayEntry)null);
      }

      if (this.globalFloatEntry.enabled5 && this.enabled) {
         this.staticListEntry.applyIChunkProvider(this, this.world, count, count2, (ShortArrayEntry)null);
      }
   }

   @Override
   public AlertNotificationSound resolveAlertNotificationSound(int count, int count2) {
      this.random.setSeed((long)count * 341873128712L + (long)count2 * 132897987541L);
      ShortArrayEntry shortArrayEntry = new ShortArrayEntry();
      this.applyShortArrayEntry(count, count2, shortArrayEntry);
      this.biomeIDIsOutOfBoundsManagerArray = this.world
         .getInvalidBiomeIdManager()
         .resolveBiomeIDIsOutOfBoundsManagerA2(this.biomeIDIsOutOfBoundsManagerArray, count * 16, count2 * 16, 16, 16);
      this.applyShortArrayEntry(count, count2, shortArrayEntry, this.biomeIDIsOutOfBoundsManagerArray);
      if (this.globalFloatEntry.enabled6) {
         this.tertiaryRandomEntry.applyIChunkProvider(this, this.world, count, count2, shortArrayEntry);
      }

      if (this.globalFloatEntry.enabled7) {
         this.tertiaryRandomEntry2.applyIChunkProvider(this, this.world, count, count2, shortArrayEntry);
      }

      if (this.globalFloatEntry.enabled && this.enabled) {
         this.defaultDoubleEntry.applyIChunkProvider(this, this.world, count, count2, shortArrayEntry);
      }

      if (this.globalFloatEntry.enabled2 && this.enabled) {
         this.activeListEntry.applyIChunkProvider(this, this.world, count, count2, shortArrayEntry);
      }

      if (this.globalFloatEntry.enabled3 && this.enabled) {
         this.dynamicListEntry.applyIChunkProvider(this, this.world, count, count2, shortArrayEntry);
      }

      if (this.globalFloatEntry.enabled4 && this.enabled) {
         this.bezierFilter.applyIChunkProvider(this, this.world, count, count2, shortArrayEntry);
      }

      if (this.globalFloatEntry.enabled5 && this.enabled) {
         this.staticListEntry.applyIChunkProvider(this, this.world, count, count2, shortArrayEntry);
      }

      AlertNotificationSound alertNotificationSound = new AlertNotificationSound(this.world, shortArrayEntry, count, count2);
      byte[] byteFlagArray = alertNotificationSound.getByteFlagArray();

      for (int index = 0; index < byteFlagArray.length; index++) {
         byteFlagArray[index] = (byte)this.biomeIDIsOutOfBoundsManagerArray[index].count;
      }

      alertNotificationSound.updateState8();
      return alertNotificationSound;
   }

   @Override
   public void updateState() {
   }

   @Override
   public BlockPos resolveBlockPos(World world2, String text, BlockPos blockPos) {
      return "Stronghold".equals(text) && this.dynamicListEntry != null ? this.dynamicListEntry.resolveBlockPos(world2, blockPos) : null;
   }

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public void applyIChunkProvider(IChunkProvider iChunkProvider, int count, int count2) {
      CustomBlockAdapter.enabled = true;
      int count3 = count * 16;
      int count4 = count2 * 16;
      BlockPos blockPos = new BlockPos(count3, 0, count4);
      BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = this.world.resolveBiomeIDIsOutOfBoundsManager(blockPos.add(16, 0, 16));
      this.random.setSeed(this.world.getTimestamp());
      long value = this.random.nextLong() / 2L * 2L + 1L;
      long value2 = this.random.nextLong() / 2L * 2L + 1L;
      this.random.setSeed((long)count * value + (long)count2 * value2 ^ this.world.getTimestamp());
      boolean flag = false;
      InterpolationCollector interpolationCollector = new InterpolationCollector(count, count2);
      if (this.globalFloatEntry.enabled && this.enabled) {
         this.defaultDoubleEntry.matchesWorld(this.world, this.random, interpolationCollector);
      }

      if (this.globalFloatEntry.enabled2 && this.enabled) {
         flag = this.activeListEntry.matchesWorld(this.world, this.random, interpolationCollector);
      }

      if (this.globalFloatEntry.enabled3 && this.enabled) {
         this.dynamicListEntry.matchesWorld(this.world, this.random, interpolationCollector);
      }

      if (this.globalFloatEntry.enabled4 && this.enabled) {
         this.bezierFilter.matchesWorld(this.world, this.random, interpolationCollector);
      }

      if (this.globalFloatEntry.enabled5 && this.enabled) {
         this.staticListEntry.matchesWorld(this.world, this.random, interpolationCollector);
      }

      if (biomeIDIsOutOfBoundsManager != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager17
         && biomeIDIsOutOfBoundsManager != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager27
         && this.globalFloatEntry.enabled8
         && !flag
         && this.random.nextInt(this.globalFloatEntry.count48) == 0) {
         int count5 = this.random.nextInt(16) + 8;
         int count6 = this.random.nextInt(256);
         int count7 = this.random.nextInt(16) + 8;
         new StandardBlockEntry(Blocks.blockLava2).matchesWorld(this.world, this.random, blockPos.add(count5, count6, count7));
      }

      if (!flag && this.random.nextInt(this.globalFloatEntry.count49 / 10) == 0 && this.globalFloatEntry.enabled9) {
         int count8 = this.random.nextInt(16) + 8;
         int count9 = this.random.nextInt(this.random.nextInt(248) + 8);
         int count10 = this.random.nextInt(16) + 8;
         if (count9 < this.world.getIndexOffset1() || this.random.nextInt(this.globalFloatEntry.count49 / 8) == 0) {
            new StandardBlockEntry(Blocks.blockLava).matchesWorld(this.world, this.random, blockPos.add(count8, count9, count10));
         }
      }

      if (this.globalFloatEntry.enabled10) {
         for (int index = 0; index < this.globalFloatEntry.count50; index++) {
            int count11 = this.random.nextInt(16) + 8;
            int count12 = this.random.nextInt(256);
            int count13 = this.random.nextInt(16) + 8;
            new StandardLoggerEntry().matchesWorld(this.world, this.random, blockPos.add(count11, count12, count13));
         }
      }

      biomeIDIsOutOfBoundsManager.applyWorld(this.world, this.random, new BlockPos(count3, 0, count4));
      InternalSetEntry.applyWorld(this.world, biomeIDIsOutOfBoundsManager, count3 + 8, count4 + 8, 16, 16, this.random);
      BlockPos blockPos2 = blockPos.add(8, 0, 8);

      for (int index2 = 0; index2 < 16; index2++) {
         for (int index3 = 0; index3 < 16; index3++) {
            BlockPos blockPos3 = this.world.resolveBlockPosByBlockPos(blockPos2.add(index2, 0, index3));
            BlockPos blockPos4 = blockPos3.down();
            if (this.world.matchesBlockPos3(blockPos4)) {
               this.world.matchesBlockPos(blockPos4, Blocks.block53.getDefaultState(), 2);
            }

            if (this.world.matchesBlockPos(blockPos3, true)) {
               this.world.matchesBlockPos(blockPos3, Blocks.block74.getDefaultState(), 2);
            }
         }
      }

      CustomBlockAdapter.enabled = false;
   }

   public ExtendedRandomEntry(World world2, long value, boolean flag, String text) {
      this.world = world2;
      this.enabled = flag;
      this.infoManager = world2.getRandomSeedManager().getInfoManager();
      this.random = new Random(value);
      this.clientManagerService = new ClientManagerService(this.random, 16);
      this.clientManagerService2 = new ClientManagerService(this.random, 16);
      this.clientManagerService3 = new ClientManagerService(this.random, 8);
      this.auxiliaryClientManagerService = new AuxiliaryClientManagerService(this.random, 4);
      this.clientManagerService4 = new ClientManagerService(this.random, 10);
      this.clientManagerService5 = new ClientManagerService(this.random, 16);
      this.clientManagerService6 = new ClientManagerService(this.random, 8);
      this.valueArray = new double[825];
      this.factorArray = new float[25];

      for (int index = -2; index <= 2; index++) {
         for (int index2 = -2; index2 <= 2; index2++) {
            float factor = 10.0F / MathHelper.sin((float)(index * index + index2 * index2) + 0.2F);
            this.factorArray[index + 2 + (index2 + 2) * 5] = factor;
         }
      }

      if (text != null) {
         this.globalFloatEntry = AuxiliaryGsonEntry.resolveAuxiliaryGsonEntry(text).getGlobalFloatEntry();
         this.block = this.globalFloatEntry.enabled11 ? Blocks.blockLava : Blocks.blockLava2;
         world2.setIndexOffset1(this.globalFloatEntry.count51);
      }
   }

   @Override
   public AlertNotificationSound resolveAlertNotificationSound(BlockPos blockPos) {
      return this.resolveAlertNotificationSound(blockPos.getX() >> 4, blockPos.getZ() >> 4);
   }

   @Override
   public String getText() {
      return "RandomLevelSource";
   }

   @Override
   public boolean matchesCompositeListener(boolean flag, CompositeListener compositeListener) {
      return true;
   }

   @Override
   public boolean matchesIChunkProvider(IChunkProvider iChunkProvider, AlertNotificationSound alertNotificationSound, int count, int count2) {
      boolean flag = false;
      if (this.globalFloatEntry.enabled5 && this.enabled && alertNotificationSound.getTimestamp() < 3600L) {
         flag |= this.staticListEntry.matchesWorld(this.world, this.random, new InterpolationCollector(count, count2));
      }

      return flag;
   }

   @Override
   public boolean checkIndex(int count, int count2) {
      return true;
   }

   @Override
   public int size() {
      return 0;
   }

   @Override
   public List resolveList(MovementThresholdProperty movementThresholdProperty, BlockPos blockPos) {
      BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = this.world.resolveBiomeIDIsOutOfBoundsManager(blockPos);
      if (this.enabled) {
         if (movementThresholdProperty == MovementThresholdProperty.MONSTER && this.bezierFilter.matchesBlockPosByBlockPos(blockPos)) {
            return this.bezierFilter.getList2();
         }

         if (movementThresholdProperty == MovementThresholdProperty.MONSTER && this.globalFloatEntry.enabled5 && this.staticListEntry.matchesWorld(this.world, blockPos)) {
            return this.staticListEntry.getList2();
         }
      }

      return biomeIDIsOutOfBoundsManager.resolveList(movementThresholdProperty);
   }

   public void applyShortArrayEntry(int count, int count2, ShortArrayEntry shortArrayEntry) {
      this.biomeIDIsOutOfBoundsManagerArray = this.world
         .getInvalidBiomeIdManager()
         .resolveBiomeIDIsOutOfBoundsManagerA(this.biomeIDIsOutOfBoundsManagerArray, count * 4 - 2, count2 * 4 - 2, 10, 10);
      this.applyIndex(count * 4, 0, count2 * 4);

      for (int index = 0; index < 4; index++) {
         int count3 = index * 5;
         int count4 = (index + 1) * 5;

         for (int index2 = 0; index2 < 4; index2++) {
            int count5 = (count3 + index2) * 33;
            int count6 = (count3 + index2 + 1) * 33;
            int count7 = (count4 + index2) * 33;
            int count8 = (count4 + index2 + 1) * 33;

            for (int index3 = 0; index3 < 32; index3++) {
               double offset = 0.125;
               double offset2 = this.valueArray[count5 + index3];
               double offset3 = this.valueArray[count6 + index3];
               double offset4 = this.valueArray[count7 + index3];
               double offset5 = this.valueArray[count8 + index3];
               double offset6 = (this.valueArray[count5 + index3 + 1] - offset2) * offset;
               double offset7 = (this.valueArray[count6 + index3 + 1] - offset3) * offset;
               double offset8 = (this.valueArray[count7 + index3 + 1] - offset4) * offset;
               double offset9 = (this.valueArray[count8 + index3 + 1] - offset5) * offset;

               for (int index4 = 0; index4 < 8; index4++) {
                  double offset10 = 0.25;
                  double offset11 = offset2;
                  double offset12 = offset3;
                  double offset13 = (offset4 - offset2) * offset10;
                  double offset14 = (offset5 - offset3) * offset10;

                  for (int index5 = 0; index5 < 4; index5++) {
                     double offset15 = 0.25;
                     double offset16 = (offset12 - offset11) * offset15;
                     double offset17 = offset11 - offset16;

                     for (int index6 = 0; index6 < 4; index6++) {
                        if ((offset17 += offset16) > 0.0) {
                           shortArrayEntry.applyIBlockState(index * 4 + index5, index3 * 8 + index4, index2 * 4 + index6, Blocks.stone.getDefaultState());
                        } else if (index3 * 8 + index4 < this.globalFloatEntry.count51) {
                           shortArrayEntry.applyIBlockState(index * 4 + index5, index3 * 8 + index4, index2 * 4 + index6, this.block.getDefaultState());
                        }
                     }

                     offset11 += offset13;
                     offset12 += offset14;
                  }

                  offset2 += offset6;
                  offset3 += offset7;
                  offset4 += offset8;
                  offset5 += offset9;
               }
            }
         }
      }
   }

   private void applyIndex(int count, int count2, int count3) {
      this.valueArray6 = this.clientManagerService5
         .resolveDoubleArray(
            this.valueArray6,
            count,
            count3,
            5,
            5,
            (double)this.globalFloatEntry.factor,
            (double)this.globalFloatEntry.factor2,
            (double)this.globalFloatEntry.factor3
         );
      float factor = this.globalFloatEntry.factor4;
      float factor2 = this.globalFloatEntry.factor5;
      this.valueArray3 = this.clientManagerService3
         .resolveDoubleArray(
            this.valueArray3,
            count,
            count2,
            count3,
            5,
            33,
            5,
            (double)(factor / this.globalFloatEntry.factor6),
            (double)(factor2 / this.globalFloatEntry.factor7),
            (double)(factor / this.globalFloatEntry.factor8)
         );
      this.valueArray4 = this.clientManagerService.resolveDoubleArray(this.valueArray4, count, count2, count3, 5, 33, 5, (double)factor, (double)factor2, (double)factor);
      this.valueArray5 = this.clientManagerService2.resolveDoubleArray(this.valueArray5, count, count2, count3, 5, 33, 5, (double)factor, (double)factor2, (double)factor);
      int count4 = 0;
      int count5 = 0;

      for (int index = 0; index < 5; index++) {
         for (int index2 = 0; index2 < 5; index2++) {
            float factor3 = 0.0F;
            float factor4 = 0.0F;
            float factor5 = 0.0F;
            BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = this.biomeIDIsOutOfBoundsManagerArray[index + 2 + (index2 + 2) * 10];

            for (int index3 = -2; index3 <= 2; index3++) {
               for (int index4 = -2; index4 <= 2; index4++) {
                  BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager2 = this.biomeIDIsOutOfBoundsManagerArray[index + index3 + 2 + (index2 + index4 + 2) * 10];
                  float factor6 = this.globalFloatEntry.factor9 + biomeIDIsOutOfBoundsManager2.factor2 * this.globalFloatEntry.factor10;
                  float factor7 = this.globalFloatEntry.factor11 + biomeIDIsOutOfBoundsManager2.factor4 * this.globalFloatEntry.factor12;
                  if (this.infoManager == InfoManager.infoManager && factor6 > 0.0F) {
                     factor6 = 1.0F + factor6 * 2.0F;
                     factor7 = 1.0F + factor7 * 4.0F;
                  }

                  float factor8 = this.factorArray[index3 + 2 + (index4 + 2) * 5] / (factor6 + 2.0F);
                  if (biomeIDIsOutOfBoundsManager2.factor2 > biomeIDIsOutOfBoundsManager.factor2) {
                     factor8 /= 2.0F;
                  }

                  factor3 += factor7 * factor8;
                  factor4 += factor6 * factor8;
                  factor5 += factor8;
               }
            }

            float factor9 = factor3 / factor5;
            float factor10 = factor4 / factor5;
            float factor11 = factor9 * 0.9F + 0.1F;
            float factor12 = (factor10 * 4.0F - 1.0F) / 8.0F;
            double offset = this.valueArray6[count5] / 8000.0;
            if (offset < 0.0) {
               offset = -offset * 0.3;
            }

            double offset2 = offset * 3.0 - 2.0;
            double offset3;
            if (offset2 < 0.0) {
               double offset4 = offset2 / 2.0;
               if (offset4 < -1.0) {
                  offset4 = -1.0;
               }

               offset3 = offset4 / 1.4 / 2.0;
            } else {
               if (offset2 > 1.0) {
                  offset2 = 1.0;
               }

               offset3 = offset2 / 8.0;
            }

            count5++;
            double offset5 = (double)factor12;
            double offset6 = (double)factor11;
            double offset7 = (offset5 + offset3 * 0.2) * (double)this.globalFloatEntry.factor13 / 8.0;
            double offset8 = (double)this.globalFloatEntry.factor13 + offset7 * 4.0;

            for (int index5 = 0; index5 < 33; index5++) {
               double offset9 = ((double)index5 - offset8) * (double)this.globalFloatEntry.factor14 * 128.0 / 256.0 / offset6;
               if (offset9 < 0.0) {
                  offset9 *= 4.0;
               }

               double offset10 = this.valueArray4[count4] / (double)this.globalFloatEntry.factor15;
               double offset11 = this.valueArray5[count4] / (double)this.globalFloatEntry.factor16;
               double offset12 = (this.valueArray3[count4] / 10.0 + 1.0) / 2.0;
               double offset13 = MathHelper.computeValue(offset10, offset11, offset12) - offset9;
               if (index5 > 29) {
                  double offset14 = (double)((float)(index5 - 29) / 3.0F);
                  offset13 = offset13 * (1.0 - offset14) + -10.0 * offset14;
               }

               this.valueArray[count4] = offset13;
               count4++;
            }
         }
      }
   }

   @Override
   public boolean isActiveAsFlag() {
      return true;
   }

   public void applyShortArrayEntry(int count, int count2, ShortArrayEntry shortArrayEntry, BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray2) {
      double offset = 0.03125;
      this.valueArray2 = this.auxiliaryClientManagerService
         .resolveDoubleArray(this.valueArray2, (double)(count * 16), (double)(count2 * 16), 16, 16, offset * 2.0, offset * 2.0, 1.0);

      for (int index = 0; index < 16; index++) {
         for (int index2 = 0; index2 < 16; index2++) {
            biomeIDIsOutOfBoundsManagerArray2[index2 + index * 16].applyWorld(this.world, this.random, shortArrayEntry, count * 16 + index, count2 * 16 + index2, this.valueArray2[index2 + index * 16]);
         }
      }
   }
}
