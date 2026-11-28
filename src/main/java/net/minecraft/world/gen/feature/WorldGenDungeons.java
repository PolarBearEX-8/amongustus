package net.minecraft.world.gen.feature;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorldGenDungeons extends WorldGenerator {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final String[] SPAWNERTYPES = new String[]{"Skeleton", "Zombie", "Zombie", "Spider"};
   private static final List CHESTCONTENT = Lists.newArrayList(
      new WeightedRandomChestContent[]{
         new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 10),
         new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 4, 10),
         new WeightedRandomChestContent(Items.bread, 0, 1, 1, 10),
         new WeightedRandomChestContent(Items.wheat, 0, 1, 4, 10),
         new WeightedRandomChestContent(Items.gunpowder, 0, 1, 4, 10),
         new WeightedRandomChestContent(Items.string, 0, 1, 4, 10),
         new WeightedRandomChestContent(Items.bucket, 0, 1, 1, 10),
         new WeightedRandomChestContent(Items.golden_apple, 0, 1, 1, 1),
         new WeightedRandomChestContent(Items.redstone, 0, 1, 4, 10),
         new WeightedRandomChestContent(Items.record_13, 0, 1, 1, 4),
         new WeightedRandomChestContent(Items.record_cat, 0, 1, 1, 4),
         new WeightedRandomChestContent(Items.name_tag, 0, 1, 1, 10),
         new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 2),
         new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1)
      }
   );

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      int n = rand.nextInt(2) + 2;
      int n2 = -n - 1;
      int n3 = n + 1;
      int n4 = rand.nextInt(2) + 2;
      int n5 = -n4 - 1;
      int n6 = n4 + 1;
      int n7 = 0;

      for (int i = n2; i <= n3; i++) {
         for (int j = -1; j <= 4; j++) {
            for (int k = n5; k <= n6; k++) {
               BlockPos pos2 = pos.add(i, j, k);
               boolean isSolid = world.getBlockState(pos2).getBlock().getMaterial().isSolid();
               if (j == -1 && !isSolid) {
                  return false;
               }

               if (j == 4 && !isSolid) {
                  return false;
               }

               if ((i == n2 || i == n3 || k == n5 || k == n6) && j == 0 && world.isAirBlock(pos2) && world.isAirBlock(pos2.up())) {
                  n7++;
               }
            }
         }
      }

      if (n7 >= 1 && n7 <= 5) {
         for (int m = n2; m <= n3; m++) {
            for (int idx = 3; idx >= -1; idx--) {
               for (int index = n5; index <= n6; index++) {
                  BlockPos pos3 = pos.add(m, idx, index);
                  if (m != n2 && idx != -1 && index != n5 && m != n3 && idx != 4 && index != n6) {
                     if (world.getBlockState(pos3).getBlock() != Blocks.chest) {
                        world.setBlockToAir(pos3);
                     }
                  } else if (pos3.getY() >= 0 && !world.getBlockState(pos3.down()).getBlock().getMaterial().isSolid()) {
                     world.setBlockToAir(pos3);
                  } else if (world.getBlockState(pos3).getBlock().getMaterial().isSolid() && world.getBlockState(pos3).getBlock() != Blocks.chest) {
                     if (idx == -1 && rand.nextInt(4) != 0) {
                        world.setBlockState(pos3, Blocks.mossy_cobblestone.getDefaultState(), 2);
                     } else {
                        world.setBlockState(pos3, Blocks.cobblestone.getDefaultState(), 2);
                     }
                  }
               }
            }
         }

         for (int i2 = 0; i2 < 2; i2++) {
            for (int i3 = 0; i3 < 3; i3++) {
               int n8 = pos.getX() + rand.nextInt(n * 2 + 1) - n;
               int y = pos.getY();
               int n9 = pos.getZ() + rand.nextInt(n4 * 2 + 1) - n4;
               BlockPos pos4 = new BlockPos(n8, y, n9);
               if (world.isAirBlock(pos4)) {
                  int n10 = 0;

                  for (Object obj : EnumFacing.Plane.HORIZONTAL) {
                     if (world.getBlockState(pos4.offset((EnumFacing)obj)).getBlock().getMaterial().isSolid()) {
                        n10++;
                     }
                  }

                  if (n10 == 1) {
                     world.setBlockState(pos4, Blocks.chest.correctFacing(world, pos4, Blocks.chest.getDefaultState()), 2);
                     List list = WeightedRandomChestContent.combine(CHESTCONTENT, new WeightedRandomChestContent[]{Items.enchanted_book.getRandom(rand)});
                     TileEntity tileEntity = world.getTileEntity(pos4);
                     if (tileEntity instanceof TileEntityChest) {
                        WeightedRandomChestContent.generateChestContents(rand, list, (TileEntityChest)tileEntity, 8);
                     }
                     break;
                  }
               }
            }
         }

         world.setBlockState(pos, Blocks.mob_spawner.getDefaultState(), 2);
         TileEntity tileEntity2 = world.getTileEntity(pos);
         if (tileEntity2 instanceof TileEntityMobSpawner) {
            ((TileEntityMobSpawner)tileEntity2).getSpawnerBaseLogic().setEntityName(this.pickMobSpawner(rand));
         } else {
            LOGGER.error("Failed to fetch mob spawner entity at (" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ")");
         }

         return true;
      } else {
         return false;
      }
   }

   private String pickMobSpawner(Random rand) {
      return SPAWNERTYPES[rand.nextInt(SPAWNERTYPES.length)];
   }
}
