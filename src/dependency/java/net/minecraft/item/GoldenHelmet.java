package net.minecraft.item;

import client.setting.ExternalOptionType;
import client.util.BiomeIDIsOutOfBoundsManager;
import client.util.ClassHolder;
import client.util.ClientManagerResolver;
import client.util.RayTracePipeline;
import client.util.SecondaryClientManagerHandler;
import java.util.Random;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class GoldenHelmet extends BiomeIDIsOutOfBoundsManager {
   public static final SecondaryClientManagerHandler secondaryClientManagerHandler = new SecondaryClientManagerHandler(false);

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos) {
      modularItemEntry.setExternalOptionType(ExternalOptionType.GRASS);

      for (int index = 0; index < 7; index++) {
         int count = random.nextInt(16) + 8;
         int count2 = random.nextInt(16) + 8;
         int count3 = random.nextInt(world.resolveBlockPos2(blockPos.add(count, 0, count2)).getY() + 32);
         modularItemEntry.matchesWorld(world, random, blockPos.add(count, count3, count2));
      }

      super.applyWorld(world, random, blockPos);
   }

   @Override
   public ClientManagerResolver nextInt(Random random) {
      return (ClientManagerResolver)(random.nextInt(5) > 0 ? secondaryClientManagerHandler : this.secondaryIBlockStateEntry);
   }

   @Override
   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManagerByIndex(int count) {
      RayTracePipeline rayTracePipeline = new RayTracePipeline(count, this);
      rayTracePipeline.factor = (this.factor + 1.0F) * 0.5F;
      rayTracePipeline.factor2 = this.factor2 * 0.5F + 0.3F;
      rayTracePipeline.factor4 = this.factor4 * 0.5F + 1.2F;
      return rayTracePipeline;
   }

   public GoldenHelmet(int count) {
      super(count);
      this.list4.add(new ClassHolder(EntityHorse.class, 1, 2, 6));
      this.alreadyDecoratingManager.count9 = 1;
      this.alreadyDecoratingManager.count8 = 4;
      this.alreadyDecoratingManager.count5 = 20;
   }
}
