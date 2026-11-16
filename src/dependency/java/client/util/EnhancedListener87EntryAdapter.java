package client.util;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.util.BlockPos;

public class EnhancedListener87EntryAdapter extends Listener87Entry {
   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.trigger.usage";
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      if (textArray.length == 1) {
         ThePlayerNameManager thePlayerNameManager = VanillaRunnable.getVanillaRunnable().resolveWorldServer(0).getThePlayerNameManager();
         ArrayList list = Lists.newArrayList();

         for (ScoreboardData scoreboardData : (Iterable<ScoreboardData>) (Iterable) thePlayerNameManager.valuesAsCollection()) {
            if (scoreboardData.getHealthHandler() == HealthHandler.healthHandler6) {
               list.add(scoreboardData.getLabelText2());
            }
         }

         return resolveList(textArray, (String[])list.toArray(new String[list.size()]));
      } else {
         return textArray.length == 2 ? resolveList(textArray, new String[]{"add", "set"}) : null;
      }
   }

   @Override
   public int getCount() {
      return 0;
   }

   @Override
   public String getText() {
      return "trigger";
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 3) {
         throw new AimDispatcher("commands.trigger.usage", new Object[0]);
      } else {
         EntityPlayerAdapter entityPlayerAdapter;
         if (iCommandSender instanceof EntityPlayerAdapter) {
            entityPlayerAdapter = (EntityPlayerAdapter)iCommandSender;
         } else {
            Entity entity = iCommandSender.getEntity();
            if (!(entity instanceof EntityPlayerAdapter)) {
               throw new InternalExceptionAdapter("commands.trigger.invalidPlayer", new Object[0]);
            }

            entityPlayerAdapter = (EntityPlayerAdapter)entity;
         }

         ThePlayerNameManager thePlayerNameManager = VanillaRunnable.getVanillaRunnable().resolveWorldServer(0).getThePlayerNameManager();
         ScoreboardData scoreboardData = thePlayerNameManager.get(textArray[0]);
         if (scoreboardData != null && scoreboardData.getHealthHandler() == HealthHandler.healthHandler6) {
            int count = parseInt(textArray[2]);
            if (!thePlayerNameManager.matchesText(entityPlayerAdapter.getText(), scoreboardData)) {
               throw new InternalExceptionAdapter("commands.trigger.invalidObjective", new Object[]{textArray[0]});
            } else {
               ComparatorEntry comparatorEntry = thePlayerNameManager.getComparatorEntry(entityPlayerAdapter.getText(), scoreboardData);
               if (comparatorEntry.isEnabled()) {
                  throw new InternalExceptionAdapter("commands.trigger.disabled", new Object[]{textArray[0]});
               } else {
                  if ("set".equals(textArray[1])) {
                     comparatorEntry.applyIndex(count);
                  } else {
                     if (!"add".equals(textArray[1])) {
                        throw new InternalExceptionAdapter("commands.trigger.invalidMode", new Object[]{textArray[1]});
                     }

                     comparatorEntry.applyIndexByIndex(count);
                  }

                  comparatorEntry.setEnabled(true);
                  if (entityPlayerAdapter.internalWorldEntry.isActiveAsFlag()) {
                     applyICommandSender(iCommandSender, this, "commands.trigger.success", new Object[]{textArray[0], textArray[1], textArray[2]});
                  }
               }
            }
         } else {
            throw new InternalExceptionAdapter("commands.trigger.invalidObjective", new Object[]{textArray[0]});
         }
      }
   }
}
