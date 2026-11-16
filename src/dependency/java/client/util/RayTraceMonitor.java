package client.util;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.util.BlockPos;

public class RayTraceMonitor extends Listener87Entry {
   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return count == 2;
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      if (textArray.length == 1) {
         return resolveList(textArray, new String[]{"give", "take"});
      } else if (textArray.length != 2) {
         return textArray.length == 3 ? resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray()) : null;
      } else {
         ArrayList list = Lists.newArrayList();

         for (DuplicateStatIdManager duplicateStatIdManager : (Iterable<DuplicateStatIdManager>)(StatCraftItemManager.list4)) {
            list.add(duplicateStatIdManager.labelText);
         }

         return resolveList(textArray, list);
      }
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.achievement.usage";
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 2) {
         throw new AimDispatcher("commands.achievement.usage", new Object[0]);
      } else {
         DuplicateStatIdManager duplicateStatIdManager = StatCraftItemManager.get(textArray[1]);
         if (duplicateStatIdManager == null && !textArray[1].equals("*")) {
            throw new InternalExceptionAdapter("commands.achievement.unknownAchievement", new Object[]{textArray[1]});
         } else {
            EntityPlayerAdapter entityPlayerAdapter = textArray.length >= 3 ? resolveEntityPlayerAdapter(iCommandSender, textArray[2]) : resolveEntityPlayerAdapter(iCommandSender);
            boolean flag = textArray[0].equalsIgnoreCase("give");
            boolean flag2 = textArray[0].equalsIgnoreCase("take");
            if (flag || flag2) {
               if (duplicateStatIdManager == null) {
                  if (flag) {
                     for (AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter : (Iterable<AuxiliaryDuplicateStatIdManagerAdapter>)(AchievementTheEnd2Manager.list)) {
                        entityPlayerAdapter.applyDuplicateStatIdManagerByDuplicateStatIdM(auxiliaryDuplicateStatIdManagerAdapter);
                     }

                     applyICommandSender(iCommandSender, this, "commands.achievement.give.success.all", new Object[]{entityPlayerAdapter.getText()});
                  } else if (flag2) {
                     for (AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter2 : (Iterable<AuxiliaryDuplicateStatIdManagerAdapter>)(Lists.reverse(AchievementTheEnd2Manager.list))) {
                        entityPlayerAdapter.applyDuplicateStatIdManager(auxiliaryDuplicateStatIdManagerAdapter2);
                     }

                     applyICommandSender(iCommandSender, this, "commands.achievement.take.success.all", new Object[]{entityPlayerAdapter.getText()});
                  }
               } else {
                  if (duplicateStatIdManager instanceof AuxiliaryDuplicateStatIdManagerAdapter) {
                     AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter3 = (AuxiliaryDuplicateStatIdManagerAdapter)duplicateStatIdManager;
                     if (flag) {
                        if (entityPlayerAdapter.getStatFileWriterHandler().matchesAuxiliaryDuplicateStatIdMaByAuxiliaryDuplica(auxiliaryDuplicateStatIdManagerAdapter3)) {
                           throw new InternalExceptionAdapter(
                              "commands.achievement.alreadyHave", new Object[]{entityPlayerAdapter.getText(), duplicateStatIdManager.getIChatComponentAsIChatComponent()}
                           );
                        }

                        ArrayList list;
                        for (list = Lists.newArrayList();
                           auxiliaryDuplicateStatIdManagerAdapter3.auxiliaryDuplicateStatIdManagerAdapter != null
                              && !entityPlayerAdapter.getStatFileWriterHandler()
                                 .matchesAuxiliaryDuplicateStatIdMaByAuxiliaryDuplica(auxiliaryDuplicateStatIdManagerAdapter3.auxiliaryDuplicateStatIdManagerAdapter);
                           auxiliaryDuplicateStatIdManagerAdapter3 = auxiliaryDuplicateStatIdManagerAdapter3.auxiliaryDuplicateStatIdManagerAdapter
                        ) {
                           list.add(auxiliaryDuplicateStatIdManagerAdapter3.auxiliaryDuplicateStatIdManagerAdapter);
                        }

                        for (AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter4 : (Iterable<AuxiliaryDuplicateStatIdManagerAdapter>)(Lists.reverse(list))) {
                           entityPlayerAdapter.applyDuplicateStatIdManagerByDuplicateStatIdM(auxiliaryDuplicateStatIdManagerAdapter4);
                        }
                     } else if (flag2) {
                        if (!entityPlayerAdapter.getStatFileWriterHandler().matchesAuxiliaryDuplicateStatIdMaByAuxiliaryDuplica(auxiliaryDuplicateStatIdManagerAdapter3)) {
                           throw new InternalExceptionAdapter(
                              "commands.achievement.dontHave", new Object[]{entityPlayerAdapter.getText(), duplicateStatIdManager.getIChatComponentAsIChatComponent()}
                           );
                        }

                        ArrayList list2 = Lists.newArrayList(
                           Iterators.filter(AchievementTheEnd2Manager.list.iterator(), new EntityPlayerAdapterFilter(this, entityPlayerAdapter, duplicateStatIdManager))
                        );
                        ArrayList list3 = Lists.newArrayList(list2);

                        for (AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter5 : (Iterable<AuxiliaryDuplicateStatIdManagerAdapter>)(list2)) {
                           AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter6 = auxiliaryDuplicateStatIdManagerAdapter5;

                           boolean flag3;
                           for (flag3 = false; auxiliaryDuplicateStatIdManagerAdapter6 != null; auxiliaryDuplicateStatIdManagerAdapter6 = auxiliaryDuplicateStatIdManagerAdapter6.auxiliaryDuplicateStatIdManagerAdapter) {
                              if (auxiliaryDuplicateStatIdManagerAdapter6 == duplicateStatIdManager) {
                                 flag3 = true;
                              }
                           }

                           if (!flag3) {
                              for (AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter7 = auxiliaryDuplicateStatIdManagerAdapter5; auxiliaryDuplicateStatIdManagerAdapter7 != null; auxiliaryDuplicateStatIdManagerAdapter7 = auxiliaryDuplicateStatIdManagerAdapter7.auxiliaryDuplicateStatIdManagerAdapter) {
                                 list3.remove(auxiliaryDuplicateStatIdManagerAdapter5);
                              }
                           }
                        }

                        for (AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter8 : (Iterable<AuxiliaryDuplicateStatIdManagerAdapter>)(list3)) {
                           entityPlayerAdapter.applyDuplicateStatIdManager(auxiliaryDuplicateStatIdManagerAdapter8);
                        }
                     }
                  }

                  if (flag) {
                     entityPlayerAdapter.applyDuplicateStatIdManagerByDuplicateStatIdM(duplicateStatIdManager);
                     applyICommandSender(
                        iCommandSender, this, "commands.achievement.give.success.one", new Object[]{entityPlayerAdapter.getText(), duplicateStatIdManager.getIChatComponentAsIChatComponent()}
                     );
                  } else if (flag2) {
                     entityPlayerAdapter.applyDuplicateStatIdManager(duplicateStatIdManager);
                     applyICommandSender(
                        iCommandSender, this, "commands.achievement.take.success.one", new Object[]{duplicateStatIdManager.getIChatComponentAsIChatComponent(), entityPlayerAdapter.getText()}
                     );
                  }
               }
            }
         }
      }
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public String getText() {
      return "achievement";
   }
}
