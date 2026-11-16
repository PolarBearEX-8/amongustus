package client.event;

import client.setting.ClientSettingsBlackType;
import client.setting.CombatNumberProperty;
import client.util.AimDispatcher;
import client.util.BaseManagerStyleIChatComponentAdapter;
import client.util.ComparableExtension;
import client.util.FovAggregator;
import client.util.InternalExceptionAdapter;
import client.util.Listener87Entry;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandsGenericUsageListener implements ICommandManager {
   public static final Logger logger = LogManager.getLogger();
   public final Map map = Maps.newHashMap();
   public final Set set = Sets.newHashSet();

   public boolean matchesICommandSender(ICommandSender iCommandSender, String[] textArray, ComparableExtension comparableExtension, String text) {
      try {
         comparableExtension.applyICommandSender(iCommandSender, textArray);
         return true;
      } catch (AimDispatcher exception) {
         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter(
            "commands.generic.usage", new Object[]{new BaseManagerStyleIChatComponentAdapter(exception.getMessage(), exception.getObjectArray())}
         );
         baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.RED);
         iCommandSender.applyIChatComponent(baseManagerStyleIChatComponentAdapter);
      } catch (InternalExceptionAdapter exception2) {
         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter2 = new BaseManagerStyleIChatComponentAdapter(exception2.getMessage(), exception2.getObjectArray());
         baseManagerStyleIChatComponentAdapter2.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.RED);
         iCommandSender.applyIChatComponent(baseManagerStyleIChatComponentAdapter2);
      } catch (Throwable throwable) {
         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter3 = new BaseManagerStyleIChatComponentAdapter("commands.generic.exception", new Object[0]);
         baseManagerStyleIChatComponentAdapter3.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.RED);
         iCommandSender.applyIChatComponent(baseManagerStyleIChatComponentAdapter3);
         logger.warn("Couldn't process command: '" + text + "'");
      }

      return false;
   }

   public ComparableExtension resolveComparableExtension(ComparableExtension comparableExtension) {
      this.map.put(comparableExtension.getText(), comparableExtension);
      this.set.add(comparableExtension);

      for (String text : (java.lang.Iterable<String>) (comparableExtension.asList())) {
         ComparableExtension comparableExtension2 = (ComparableExtension)this.map.get(text);
         if (comparableExtension2 == null || !comparableExtension2.getText().equals(text)) {
            this.map.put(text, comparableExtension);
         }
      }

      return comparableExtension;
   }

   private static String[] arraycopy(String[] textArray) {
      String[] textArray2 = new String[textArray.length - 1];
      System.arraycopy(textArray, 1, textArray2, 0, textArray.length - 1);
      return textArray2;
   }

   @Override
   public Map getMap() {
      return this.map;
   }

   private int computeCount(ComparableExtension comparableExtension, String[] textArray) {
      if (comparableExtension == null) {
         return -1;
      } else {
         for (int index = 0; index < textArray.length; index++) {
            if (comparableExtension.matchesTextArray(textArray, index) && FovAggregator.matchesTextByText(textArray[index])) {
               return index;
            }
         }

         return -1;
      }
   }

   @Override
   public List resolveList(ICommandSender iCommandSender) {
      ArrayList list = Lists.newArrayList();

      for (ComparableExtension comparableExtension : (java.lang.Iterable<ComparableExtension>) (this.set)) {
         if (comparableExtension.matchesICommandSender(iCommandSender)) {
            list.add(comparableExtension);
         }
      }

      return list;
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String text, BlockPos blockPos) {
      String[] textArray = text.split(" ", -1);
      String text2 = textArray[0];
      if (textArray.length == 1) {
         ArrayList list = Lists.newArrayList();

         for (Entry entry : (java.lang.Iterable<Entry>) (this.map.entrySet())) {
            if (Listener87Entry.matchesText(text2, (String)entry.getKey()) && ((ComparableExtension)entry.getValue()).matchesICommandSender(iCommandSender)) {
               list.add(entry.getKey());
            }
         }

         return list;
      } else {
         if (textArray.length > 1) {
            ComparableExtension comparableExtension = (ComparableExtension)this.map.get(text2);
            if (comparableExtension != null && comparableExtension.matchesICommandSender(iCommandSender)) {
               return comparableExtension.resolveList(iCommandSender, arraycopy(textArray), blockPos);
            }
         }

         return null;
      }
   }

   @Override
   public int computeCount(ICommandSender iCommandSender, String text) {
      text = text.trim();
      if (text.startsWith("/")) {
         text = text.substring(1);
      }

      String[] textArray = text.split(" ");
      String text2 = textArray[0];
      textArray = arraycopy(textArray);
      ComparableExtension comparableExtension = (ComparableExtension)this.map.get(text2);
      int count = this.computeCount(comparableExtension, textArray);
      int count2 = 0;
      if (comparableExtension == null) {
         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter("commands.generic.notFound", new Object[0]);
         baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.RED);
         iCommandSender.applyIChatComponent(baseManagerStyleIChatComponentAdapter);
      } else if (comparableExtension.matchesICommandSender(iCommandSender)) {
         if (count > -1) {
            List list = FovAggregator.resolveList(iCommandSender, textArray[count], Entity.class);
            String text3 = textArray[count];
            iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_ENTITIES, list.size());

            for (Entity entity : (java.lang.Iterable<Entity>) (list)) {
               textArray[count] = entity.getEntityUniqueID().toString();
               if (this.matchesICommandSender(iCommandSender, textArray, comparableExtension, text)) {
                  count2++;
               }
            }

            textArray[count] = text3;
         } else {
            iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_ENTITIES, 1);
            if (this.matchesICommandSender(iCommandSender, textArray, comparableExtension, text)) {
               count2++;
            }
         }
      } else {
         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter2 = new BaseManagerStyleIChatComponentAdapter("commands.generic.permission", new Object[0]);
         baseManagerStyleIChatComponentAdapter2.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.RED);
         iCommandSender.applyIChatComponent(baseManagerStyleIChatComponentAdapter2);
      }

      iCommandSender.applyCombatNumberProperty(CombatNumberProperty.SUCCESS_COUNT, count2);
      return count2;
   }
}
