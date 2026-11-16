package client.util;

import client.setting.CombatFactorProperty;
import client.setting.ExternalOptionType;
import java.util.Random;
import net.minecraft.block.SecondaryBlockEntry;
import net.minecraft.entity.EntityWolf;
import net.minecraft.item.GoldenAxe;
import net.minecraft.item.ItemEntryDispatcher;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class FlowerForestBiomeIDIsOutOfBoundsManager extends BiomeIDIsOutOfBoundsManager {
   public int count7;
   public static final ClientManagerCoordinator clientManagerCoordinator = new ClientManagerCoordinator(false, true);
   public static final ClientManagerCoordinator clientManagerCoordinator2 = new ClientManagerCoordinator(false, false);
   public static final InternalClientManagerController internalClientManagerController = new InternalClientManagerController(false);

   @Override
   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(int count, boolean flag) {
      if (this.count7 == 2) {
         this.count4 = 353825;
         this.count3 = count;
         if (flag) {
            this.count4 = (this.count4 & 16711422) >> 1;
         }

         return this;
      } else {
         return super.resolveBiomeIDIsOutOfBoundsManager(count, flag);
      }
   }

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos) {
      if (this.count7 == 3) {
         for (int index = 0; index < 4; index++) {
            for (int index2 = 0; index2 < 4; index2++) {
               int count = index * 4 + 1 + 8 + random.nextInt(3);
               int count2 = index2 * 4 + 1 + 8 + random.nextInt(3);
               BlockPos blockPos2 = world.resolveBlockPos2(blockPos.add(count, 0, count2));
               if (random.nextInt(20) == 0) {
                  new SecondaryBlockEntry().matchesWorld(world, random, blockPos2);
               } else {
                  ClientManagerResolver clientManagerResolver = this.nextInt(random);
                  clientManagerResolver.updateState();
                  if (clientManagerResolver.matchesWorld(world, random, blockPos2)) {
                     clientManagerResolver.applyWorld(world, random, blockPos2);
                  }
               }
            }
         }
      }

      int count3 = random.nextInt(5) - 3;
      if (this.count7 == 1) {
         count3 += 2;
      }

      for (int index3 = 0; index3 < count3; index3++) {
         int count4 = random.nextInt(3);
         if (count4 == 0) {
            modularItemEntry.setExternalOptionType(ExternalOptionType.SYRINGA);
         } else if (count4 == 1) {
            modularItemEntry.setExternalOptionType(ExternalOptionType.ROSE);
         } else if (count4 == 2) {
            modularItemEntry.setExternalOptionType(ExternalOptionType.PAEONIA);
         }

         for (int index4 = 0; index4 < 5; index4++) {
            int count5 = random.nextInt(16) + 8;
            int count6 = random.nextInt(16) + 8;
            int count8 = random.nextInt(world.resolveBlockPos2(blockPos.add(count5, 0, count6)).getY() + 32);
            if (modularItemEntry.matchesWorld(world, random, new BlockPos(blockPos.getX() + count5, count8, blockPos.getZ() + count6))) {
               break;
            }
         }
      }

      super.applyWorld(world, random, blockPos);
   }

   @Override
   public CombatFactorProperty nextInt(Random random, BlockPos blockPos) {
      if (this.count7 == 1) {
         double doubleItem = MathHelper.getDouble7(
            (1.0 + auxiliaryClientManagerService.computeValue((double)blockPos.getX() / 48.0, (double)blockPos.getZ() / 48.0)) / 2.0, 0.0, 0.9999
         );
         CombatFactorProperty combatFactorProperty = CombatFactorProperty.values()[(int)(doubleItem * (double)CombatFactorProperty.values().length)];
         return combatFactorProperty == CombatFactorProperty.BLUE_ORCHID ? CombatFactorProperty.POPPY : combatFactorProperty;
      } else {
         return super.nextInt(random, blockPos);
      }
   }

   @Override
   public int computeCountByBlockPos(BlockPos blockPos) {
      int count = super.computeCountByBlockPos(blockPos);
      return this.count7 == 3 ? (count & 16711422) + 2634762 >> 1 : count;
   }

   @Override
   public ClientManagerResolver nextInt(Random random) {
      return (ClientManagerResolver)(this.count7 == 3 && random.nextInt(3) > 0
         ? internalClientManagerController
         : (this.count7 != 2 && random.nextInt(5) != 0 ? this.secondaryIBlockStateEntry : clientManagerCoordinator2));
   }

   @Override
   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManagerByIndex(int count) {
      if (this.count == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager4.count) {
         FlowerForestBiomeIDIsOutOfBoundsManager flowerForestBiomeIDIsOutOfBoundsManager = new FlowerForestBiomeIDIsOutOfBoundsManager(count, 1);
         flowerForestBiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager(new CoreFloatEntry(this.factor2, this.factor4 + 0.2F));
         flowerForestBiomeIDIsOutOfBoundsManager.withLabelText("Flower Forest");
         flowerForestBiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager(6976549, true);
         flowerForestBiomeIDIsOutOfBoundsManager.withCount2(8233509);
         return flowerForestBiomeIDIsOutOfBoundsManager;
      } else {
         return (BiomeIDIsOutOfBoundsManager)(this.count != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager22.count
               && this.count != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager20.count
            ? new GoldenAxe(this, count, this)
            : new ItemEntryDispatcher(this, count, this));
      }
   }

   public FlowerForestBiomeIDIsOutOfBoundsManager(int count, int count2) {
      super(count);
      this.count7 = count2;
      this.alreadyDecoratingManager.count9 = 10;
      this.alreadyDecoratingManager.count5 = 2;
      if (this.count7 == 1) {
         this.alreadyDecoratingManager.count9 = 6;
         this.alreadyDecoratingManager.count8 = 100;
         this.alreadyDecoratingManager.count5 = 1;
      }

      this.withCount2(5159473);
      this.resolveBiomeIDIsOutOfBoundsManager(0.7F, 0.8F);
      if (this.count7 == 2) {
         this.count4 = 353825;
         this.count3 = 3175492;
         this.resolveBiomeIDIsOutOfBoundsManager(0.6F, 0.6F);
      }

      if (this.count7 == 0) {
         this.list4.add(new ClassHolder(EntityWolf.class, 5, 4, 4));
      }

      if (this.count7 == 3) {
         this.alreadyDecoratingManager.count9 = -999;
      }
   }
}
