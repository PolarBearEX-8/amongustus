package client.util;

import client.setting.CombatFactorProperty;
import client.setting.ExternalOptionType;
import java.util.Random;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class SunflowerPlainsManager extends BiomeIDIsOutOfBoundsManager {
   public boolean enabled3;

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos) {
      if (auxiliaryClientManagerService.computeValue((double)(blockPos.getX() + 8) / 200.0, (double)(blockPos.getZ() + 8) / 200.0) < -0.8) {
         this.alreadyDecoratingManager.count8 = 15;
         this.alreadyDecoratingManager.count5 = 5;
      } else {
         this.alreadyDecoratingManager.count8 = 4;
         this.alreadyDecoratingManager.count5 = 10;
         modularItemEntry.setExternalOptionType(ExternalOptionType.GRASS);

         for (int index = 0; index < 7; index++) {
            int count = random.nextInt(16) + 8;
            int count2 = random.nextInt(16) + 8;
            int count3 = random.nextInt(world.resolveBlockPos2(blockPos.add(count, 0, count2)).getY() + 32);
            modularItemEntry.matchesWorld(world, random, blockPos.add(count, count3, count2));
         }
      }

      if (this.enabled3) {
         modularItemEntry.setExternalOptionType(ExternalOptionType.SUNFLOWER);

         for (int index2 = 0; index2 < 10; index2++) {
            int count4 = random.nextInt(16) + 8;
            int count5 = random.nextInt(16) + 8;
            int count6 = random.nextInt(world.resolveBlockPos2(blockPos.add(count4, 0, count5)).getY() + 32);
            modularItemEntry.matchesWorld(world, random, blockPos.add(count4, count6, count5));
         }
      }

      super.applyWorld(world, random, blockPos);
   }

   @Override
   public CombatFactorProperty nextInt(Random random, BlockPos blockPos) {
      if (auxiliaryClientManagerService.computeValue((double)blockPos.getX() / 200.0, (double)blockPos.getZ() / 200.0) < -0.8) {
         switch (random.nextInt(4)) {
            case 0:
               return CombatFactorProperty.ORANGE_TULIP;
            case 1:
               return CombatFactorProperty.RED_TULIP;
            case 2:
               return CombatFactorProperty.PINK_TULIP;
            case 3:
            default:
               return CombatFactorProperty.WHITE_TULIP;
         }
      } else if (random.nextInt(3) > 0) {
         int count = random.nextInt(3);
         return count == 0 ? CombatFactorProperty.POPPY : (count == 1 ? CombatFactorProperty.HOUSTONIA : CombatFactorProperty.OXEYE_DAISY);
      } else {
         return CombatFactorProperty.DANDELION;
      }
   }

   @Override
   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManagerByIndex(int count) {
      SunflowerPlainsManager sunflowerPlainsManager = new SunflowerPlainsManager(count);
      sunflowerPlainsManager.withLabelText("Sunflower Plains");
      sunflowerPlainsManager.enabled3 = true;
      sunflowerPlainsManager.resolveBiomeIDIsOutOfBoundsManager(9286496);
      sunflowerPlainsManager.count4 = 14273354;
      return sunflowerPlainsManager;
   }

   public SunflowerPlainsManager(int count) {
      super(count);
      this.resolveBiomeIDIsOutOfBoundsManager(0.8F, 0.4F);
      this.resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry8);
      this.list4.add(new ClassHolder(EntityHorse.class, 5, 2, 6));
      this.alreadyDecoratingManager.count9 = -999;
      this.alreadyDecoratingManager.count8 = 4;
      this.alreadyDecoratingManager.count5 = 10;
   }
}
