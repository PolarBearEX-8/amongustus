package client.util;

import client.setting.MovementColorProperty;
import client.setting.MovementProperty;
import java.util.Random;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityChicken;
import net.minecraft.entity.EntityOzelot;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class TertiaryIBlockStateEntry extends BiomeIDIsOutOfBoundsManager {
   public boolean enabled3;
   public static final IBlockState iBlockState5 = Blocks.block140.getDefaultState().resolveIBlockState(BlockLog.immutableSetEntry3, MovementProperty.JUNGLE);
   public static final IBlockState iBlockState3 = Blocks.decayableManager2
      .getDefaultState()
      .resolveIBlockState(BlockLeaves.immutableSetEntry, MovementProperty.JUNGLE)
      .resolveIBlockState(DecayableManager.internalImmutableSetEntry, Boolean.FALSE);
   public static final IBlockState iBlockState4 = Blocks.decayableManager2
      .getDefaultState()
      .resolveIBlockState(BlockLeaves.immutableSetEntry, MovementProperty.OAK)
      .resolveIBlockState(DecayableManager.internalImmutableSetEntry, Boolean.FALSE);

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos) {
      super.applyWorld(world, random, blockPos);
      int count = random.nextInt(16) + 8;
      int count2 = random.nextInt(16) + 8;
      int count3 = random.nextInt(world.resolveBlockPos2(blockPos.add(count, 0, count2)).getY() * 2);
      new RotationEngine().matchesWorld(world, random, blockPos.add(count, count3, count2));
      RotationQueue rotationQueue = new RotationQueue();

      for (int index = 0; index < 50; index++) {
         int count4 = random.nextInt(16) + 8;
         int count5 = random.nextInt(16) + 8;
         rotationQueue.matchesWorld(world, random, blockPos.add(count4, 128, count5));
      }
   }

   @Override
   public CurveParser nextIntByRandom(Random random) {
      return random.nextInt(4) == 0 ? new AuxiliaryIBlockStateEntry(MovementColorProperty.FERN) : new AuxiliaryIBlockStateEntry(MovementColorProperty.GRASS);
   }

   @Override
   public ClientManagerResolver nextInt(Random random) {
      return (ClientManagerResolver)(random.nextInt(10) == 0
         ? this.customRandomEntry
         : (
            random.nextInt(2) == 0
               ? new InternalIBlockStateEntry(iBlockState5, iBlockState4)
               : (
                  !this.enabled3 && random.nextInt(3) == 0
                     ? new IBlockStateEntryBridge(false, 10, 20, iBlockState5, iBlockState3)
                     : new SecondaryIBlockStateEntry(false, 4 + random.nextInt(7), iBlockState5, iBlockState3, true)
               )
         ));
   }

   public TertiaryIBlockStateEntry(int count, boolean flag) {
      super(count);
      this.enabled3 = flag;
      if (flag) {
         this.alreadyDecoratingManager.count9 = 2;
      } else {
         this.alreadyDecoratingManager.count9 = 50;
      }

      this.alreadyDecoratingManager.count5 = 25;
      this.alreadyDecoratingManager.count8 = 4;
      if (!flag) {
         this.list3.add(new ClassHolder(EntityOzelot.class, 2, 1, 1));
      }

      this.list4.add(new ClassHolder(EntityChicken.class, 10, 4, 4));
   }
}
