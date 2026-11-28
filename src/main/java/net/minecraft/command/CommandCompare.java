package net.minecraft.command;

import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class CommandCompare extends CommandBase {

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.compare.usage";
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length > 0 && strings.length <= 3
         ? getTabCompletionCoordinate(strings, 0, pos)
         : (
            strings.length > 3 && strings.length <= 6
               ? getTabCompletionCoordinate(strings, 3, pos)
               : (
                  strings.length > 6 && strings.length <= 9
                     ? getTabCompletionCoordinate(strings, 6, pos)
                     : (strings.length == 10 ? getListOfStringsMatchingLastWord(strings, new String[]{"masked", "all"}) : null)
               )
         );
   }

   @Override
   public String getCommandName() {
      return "testforblocks";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 9) {
         throw new WrongUsageException("commands.compare.usage", new Object[0]);
      } else {
         commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
         BlockPos pos = parseBlockPos(commandSender, strings, 0, false);
         BlockPos pos2 = parseBlockPos(commandSender, strings, 3, false);
         BlockPos pos3 = parseBlockPos(commandSender, strings, 6, false);
         StructureBoundingBox structureBoundingBox = new StructureBoundingBox(pos, pos2);
         StructureBoundingBox structureBoundingBox2 = new StructureBoundingBox(pos3, pos3.add(structureBoundingBox.getLength()));
         int n = structureBoundingBox.getXSize() * structureBoundingBox.getYSize() * structureBoundingBox.getZSize();
         if (n > 524288) {
            throw new CommandException("commands.compare.tooManyBlocks", new Object[]{n, 524288});
         } else if (structureBoundingBox.minY >= 0 && structureBoundingBox.maxY < 256 && structureBoundingBox2.minY >= 0 && structureBoundingBox2.maxY < 256) {
            World entityWorld = commandSender.getEntityWorld();
            if (entityWorld.isAreaLoaded(structureBoundingBox) && entityWorld.isAreaLoaded(structureBoundingBox2)) {
               boolean flag = false;
               if (strings.length > 9 && strings[9].equals("masked")) {
                  flag = true;
               }

               n = 0;
               BlockPos pos4 = new BlockPos(structureBoundingBox2.minX - structureBoundingBox.minX, structureBoundingBox2.minY - structureBoundingBox.minY, structureBoundingBox2.minZ - structureBoundingBox.minZ);
               BlockPos.MutableBlockPos pos5 = new BlockPos.MutableBlockPos();
               BlockPos.MutableBlockPos pos6 = new BlockPos.MutableBlockPos();

               for (int i = structureBoundingBox.minZ; i <= structureBoundingBox.maxZ; i++) {
                  for (int j = structureBoundingBox.minY; j <= structureBoundingBox.maxY; j++) {
                     for (int k = structureBoundingBox.minX; k <= structureBoundingBox.maxX; k++) {
                        pos5.set(k, j, i);
                        pos6.set(k + pos4.getX(), j + pos4.getY(), i + pos4.getZ());
                        boolean flag2 = false;
                        IBlockState blockState = entityWorld.getBlockState(pos5);
                        if (!flag || blockState.getBlock() != Blocks.air) {
                           if (blockState == entityWorld.getBlockState(pos6)) {
                              TileEntity tileEntity = entityWorld.getTileEntity(pos5);
                              TileEntity tileEntity2 = entityWorld.getTileEntity(pos6);
                              if (tileEntity != null && tileEntity2 != null) {
                                 NBTTagCompound tag = new NBTTagCompound();
                                 tileEntity.writeToNBT(tag);
                                 tag.removeTag("x");
                                 tag.removeTag("y");
                                 tag.removeTag("z");
                                 NBTTagCompound tag2 = new NBTTagCompound();
                                 tileEntity2.writeToNBT(tag2);
                                 tag2.removeTag("x");
                                 tag2.removeTag("y");
                                 tag2.removeTag("z");
                                 if (!tag.equals(tag2)) {
                                    flag2 = true;
                                 }
                              } else if (tileEntity != null) {
                                 flag2 = true;
                              }
                           } else {
                              flag2 = true;
                           }

                           n++;
                           if (flag2) {
                              throw new CommandException("commands.compare.failed", new Object[0]);
                           }
                        }
                     }
                  }
               }

               commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, n);
               notifyOperators(commandSender, this, "commands.compare.success", new Object[]{n});
            } else {
               throw new CommandException("commands.compare.outOfWorld", new Object[0]);
            }
         } else {
            throw new CommandException("commands.compare.outOfWorld", new Object[0]);
         }
      }
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }
}
