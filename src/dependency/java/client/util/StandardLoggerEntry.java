package client.util;

import client.setting.MovementFactorProperty;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobspawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StandardLoggerEntry extends CurveParser {
   public static final Logger logger = LogManager.getLogger();
   public static final String[] stringArray = new String[]{"Skeleton", "Zombie", "Zombie", "Spider"};
   public static final List list = Lists.newArrayList(
      new ClientManagerAdapter2[]{
         new ClientManagerAdapter2(Items.saddle, 0, 1, 1, 10),
         new ClientManagerAdapter2(Items.ironIngot, 0, 1, 4, 10),
         new ClientManagerAdapter2(Items.bread, 0, 1, 1, 10),
         new ClientManagerAdapter2(Items.wheat, 0, 1, 4, 10),
         new ClientManagerAdapter2(Items.gunpowder, 0, 1, 4, 10),
         new ClientManagerAdapter2(Items.string, 0, 1, 4, 10),
         new ClientManagerAdapter2(Items.bucket, 0, 1, 1, 10),
         new ClientManagerAdapter2(Items.goldenApple, 0, 1, 1, 1),
         new ClientManagerAdapter2(Items.redstone, 0, 1, 4, 10),
         new ClientManagerAdapter2(Items.record13, 0, 1, 1, 4),
         new ClientManagerAdapter2(Items.recordCat, 0, 1, 1, 4),
         new ClientManagerAdapter2(Items.nameTag, 0, 1, 1, 10),
         new ClientManagerAdapter2(Items.goldenHorseArmor, 0, 1, 1, 2),
         new ClientManagerAdapter2(Items.ironHorseArmor, 0, 1, 1, 5),
         new ClientManagerAdapter2(Items.diamondHorseArmor, 0, 1, 1, 1)
      }
   );

   private String nextInt(Random random) {
      return stringArray[random.nextInt(stringArray.length)];
   }

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      int count = random.nextInt(2) + 2;
      int count2 = -count - 1;
      int count3 = count + 1;
      int count4 = random.nextInt(2) + 2;
      int count5 = -count4 - 1;
      int count6 = count4 + 1;
      int count7 = 0;

      for (int index = count2; index <= count3; index++) {
         for (int index2 = -1; index2 <= 4; index2++) {
            for (int index3 = count5; index3 <= count6; index3++) {
               BlockPos blockPos2 = blockPos.add(index, index2, index3);
               boolean flag = world.getBlockState(blockPos2).getBlock().getMaterial().canExecute2();
               if (index2 == -1 && !flag) {
                  return false;
               }

               if (index2 == 4 && !flag) {
                  return false;
               }

               if ((index == count2 || index == count3 || index3 == count5 || index3 == count6) && index2 == 0 && world.isAirBlock(blockPos2) && world.isAirBlock(blockPos2.up())) {
                  count7++;
               }
            }
         }
      }

      if (count7 >= 1 && count7 <= 5) {
         for (int index4 = count2; index4 <= count3; index4++) {
            for (int index5 = 3; index5 >= -1; index5--) {
               for (int index6 = count5; index6 <= count6; index6++) {
                  BlockPos blockPos3 = blockPos.add(index4, index5, index6);
                  if (index4 != count2 && index5 != -1 && index6 != count5 && index4 != count3 && index5 != 4 && index6 != count6) {
                     if (world.getBlockState(blockPos3).getBlock() != Blocks.blockTrappedChest) {
                        world.matchesBlockPos8(blockPos3);
                     }
                  } else if (blockPos3.getY() >= 0 && !world.getBlockState(blockPos3.down()).getBlock().getMaterial().canExecute2()) {
                     world.matchesBlockPos8(blockPos3);
                  } else if (world.getBlockState(blockPos3).getBlock().getMaterial().canExecute2()
                     && world.getBlockState(blockPos3).getBlock() != Blocks.blockTrappedChest) {
                     if (index5 == -1 && random.nextInt(4) != 0) {
                        world.matchesBlockPos(blockPos3, Blocks.block131.getDefaultState(), 2);
                     } else {
                        world.matchesBlockPos(blockPos3, Blocks.block65.getDefaultState(), 2);
                     }
                  }
               }
            }
         }

         for (int index7 = 0; index7 < 2; index7++) {
            for (int index8 = 0; index8 < 3; index8++) {
               int count8 = blockPos.getX() + random.nextInt(count * 2 + 1) - count;
               int coordY = blockPos.getY();
               int count9 = blockPos.getZ() + random.nextInt(count4 * 2 + 1) - count4;
               BlockPos blockPos4 = new BlockPos(count8, coordY, count9);
               if (world.isAirBlock(blockPos4)) {
                  int count10 = 0;

                  for (Object element : MovementFactorProperty.HORIZONTAL) {
                     if (world.getBlockState(blockPos4.offset((EnumFacing)element)).getBlock().getMaterial().canExecute2()) {
                        count10++;
                     }
                  }

                  if (count10 == 1) {
                     world.matchesBlockPos(blockPos4, Blocks.blockTrappedChest.resolveIBlockState3(world, blockPos4, Blocks.blockTrappedChest.getDefaultState()), 2);
                     List list2 = ClientManagerAdapter2.resolveList(list, new ClientManagerAdapter2[]{Items.enchantedBook.resolveClientManagerAdapter2(random)});
                     TileEntity tileEntity = world.resolveTileEntity(blockPos4);
                     if (tileEntity instanceof TileEntityChest) {
                        ClientManagerAdapter2.applyRandom(random, list2, (TileEntityChest)tileEntity, 8);
                     }
                     break;
                  }
               }
            }
         }

         world.matchesBlockPos(blockPos, Blocks.block29.getDefaultState(), 2);
         TileEntity tileEntity2 = world.resolveTileEntity(blockPos);
         if (tileEntity2 instanceof TileEntityMobspawner) {
            ((TileEntityMobspawner)tileEntity2).getPassiveStringEntry().setLabelText(this.nextInt(random));
         } else {
            logger.error("Failed to fetch mob spawner entity at (" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")");
         }

         return true;
      } else {
         return false;
      }
   }
}
