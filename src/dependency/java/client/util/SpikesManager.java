package client.util;

import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.block.CustomBlockEntry;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class SpikesManager extends BiomeIDIsOutOfBoundsManager {
   public boolean enabled3;
   public RotationService rotationService = new RotationService();
   public CustomBlockEntry customBlockEntry = new CustomBlockEntry(4);

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos) {
      if (this.enabled3) {
         for (int index = 0; index < 3; index++) {
            int count = random.nextInt(16) + 8;
            int count2 = random.nextInt(16) + 8;
            this.rotationService.matchesWorld(world, random, world.resolveBlockPos2(blockPos.add(count, 0, count2)));
         }

         for (int index2 = 0; index2 < 2; index2++) {
            int count3 = random.nextInt(16) + 8;
            int count4 = random.nextInt(16) + 8;
            this.customBlockEntry.matchesWorld(world, random, world.resolveBlockPos2(blockPos.add(count3, 0, count4)));
         }
      }

      super.applyWorld(world, random, blockPos);
   }

   @Override
   public ClientManagerResolver nextInt(Random random) {
      return new ExtendedIBlockStateEntry(false);
   }

   @Override
   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManagerByIndex(int count) {
      BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = new SpikesManager(count, true)
         .resolveBiomeIDIsOutOfBoundsManager(13828095, true)
         .withLabelText(this.labelText + " Spikes")
         .getBiomeIDIsOutOfBoundsManagerAsBiomeIDIsOutOfBo()
         .resolveBiomeIDIsOutOfBoundsManager(0.0F, 0.5F)
         .resolveBiomeIDIsOutOfBoundsManager(new CoreFloatEntry(this.factor2 + 0.1F, this.factor4 + 0.1F));
      biomeIDIsOutOfBoundsManager.factor2 = this.factor2 + 0.3F;
      biomeIDIsOutOfBoundsManager.factor4 = this.factor4 + 0.4F;
      return biomeIDIsOutOfBoundsManager;
   }

   public SpikesManager(int count, boolean flag) {
      super(count);
      this.enabled3 = flag;
      if (flag) {
         this.iBlockState = Blocks.block61.getDefaultState();
      }

      this.list4.clear();
   }
}
