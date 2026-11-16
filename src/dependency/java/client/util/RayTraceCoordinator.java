package client.util;

import client.setting.CombatNumberProperty;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityControl;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class RayTraceCoordinator extends Listener87Entry {
   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.stats.usage";
   }

   public String[] getTextArray() {
      return VanillaRunnable.getVanillaRunnable().getTextArray();
   }

   public List getList() {
      Collection collection = VanillaRunnable.getVanillaRunnable().resolveWorldServer(0).getThePlayerNameManager().valuesAsCollection();
      ArrayList list = Lists.newArrayList();

      for (ScoreboardData scoreboardData : (Iterable<ScoreboardData>) (Iterable) collection) {
         if (!scoreboardData.getHealthHandler().isActive()) {
            list.add(scoreboardData.getLabelText2());
         }
      }

      return list;
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return textArray.length > 0 && textArray[0].equals("entity") && count == 1;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 1) {
         throw new AimDispatcher("commands.stats.usage", new Object[0]);
      } else {
         boolean flag;
         if (textArray[0].equals("entity")) {
            flag = false;
         } else {
            if (!textArray[0].equals("block")) {
               throw new AimDispatcher("commands.stats.usage", new Object[0]);
            }

            flag = true;
         }

         int count;
         if (flag) {
            if (textArray.length < 5) {
               throw new AimDispatcher("commands.stats.block.usage", new Object[0]);
            }

            count = 4;
         } else {
            if (textArray.length < 3) {
               throw new AimDispatcher("commands.stats.entity.usage", new Object[0]);
            }

            count = 2;
         }

         String text = textArray[count++];
         if ("set".equals(text)) {
            if (textArray.length < count + 3) {
               if (count == 5) {
                  throw new AimDispatcher("commands.stats.block.set.usage", new Object[0]);
               }

               throw new AimDispatcher("commands.stats.entity.set.usage", new Object[0]);
            }
         } else {
            if (!"clear".equals(text)) {
               throw new AimDispatcher("commands.stats.usage", new Object[0]);
            }

            if (textArray.length < count + 1) {
               if (count == 5) {
                  throw new AimDispatcher("commands.stats.block.clear.usage", new Object[0]);
               }

               throw new AimDispatcher("commands.stats.entity.clear.usage", new Object[0]);
            }
         }

         CombatNumberProperty combatNumberProperty = CombatNumberProperty.resolveCombatNumberProperty(textArray[count++]);
         if (combatNumberProperty == null) {
            throw new InternalExceptionAdapter("commands.stats.failed", new Object[0]);
         } else {
            World worldObj = iCommandSender.getWorldObj();
            CommandStatsManager commandStatsManager;
            if (flag) {
               BlockPos blockPos = resolveBlockPos(iCommandSender, textArray, 1, false);
               TileEntity tileEntity = worldObj.resolveTileEntity(blockPos);
               if (tileEntity == null) {
                  throw new InternalExceptionAdapter("commands.stats.noCompatibleBlock", new Object[]{blockPos.getX(), blockPos.getY(), blockPos.getZ()});
               }

               if (tileEntity instanceof TileEntityControl) {
                  commandStatsManager = ((TileEntityControl)tileEntity).getCommandStatsManager();
               } else {
                  if (!(tileEntity instanceof TileEntitySign)) {
                     throw new InternalExceptionAdapter("commands.stats.noCompatibleBlock", new Object[]{blockPos.getX(), blockPos.getY(), blockPos.getZ()});
                  }

                  commandStatsManager = ((TileEntitySign)tileEntity).getCommandStatsManager();
               }
            } else {
               commandStatsManager = resolveEntity(iCommandSender, textArray[1]).getCommandStatsManager();
            }

            if ("set".equals(text)) {
               String text2 = textArray[count++];
               String text3 = textArray[count];
               if (text2.length() == 0 || text3.length() == 0) {
                  throw new InternalExceptionAdapter("commands.stats.failed", new Object[0]);
               }

               CommandStatsManager.applyCommandStatsManager(commandStatsManager, combatNumberProperty, text2, text3);
               applyICommandSender(iCommandSender, this, "commands.stats.success", new Object[]{combatNumberProperty.getLabelText(), text3, text2});
            } else if ("clear".equals(text)) {
               CommandStatsManager.applyCommandStatsManager(commandStatsManager, combatNumberProperty, (String)null, (String)null);
               applyICommandSender(iCommandSender, this, "commands.stats.cleared", new Object[]{combatNumberProperty.getLabelText()});
            }

            if (flag) {
               BlockPos blockPos2 = resolveBlockPos(iCommandSender, textArray, 1, false);
               worldObj.resolveTileEntity(blockPos2).processAction5();
            }
         }
      }
   }

   @Override
   public String getText() {
      return "stats";
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1
         ? resolveList(textArray, new String[]{"entity", "block"})
         : (
            textArray.length == 2 && textArray[0].equals("entity")
               ? resolveList(textArray, this.getTextArray())
               : (
                  textArray.length >= 2 && textArray.length <= 4 && textArray[0].equals("block")
                     ? resolveListByTextArray(textArray, 1, blockPos)
                     : (
                        (textArray.length != 3 || !textArray[0].equals("entity")) && (textArray.length != 5 || !textArray[0].equals("block"))
                           ? (
                              (textArray.length != 4 || !textArray[0].equals("entity")) && (textArray.length != 6 || !textArray[0].equals("block"))
                                 ? (
                                    textArray.length == 6 && textArray[0].equals("entity") || textArray.length == 8 && textArray[0].equals("block")
                                       ? resolveList(textArray, this.getList())
                                       : null
                                 )
                                 : resolveList(textArray, CombatNumberProperty.getTextArray())
                           )
                           : resolveList(textArray, new String[]{"set", "clear"})
                     )
               )
         );
   }
}
