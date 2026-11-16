package client.util;

import client.event.PreUpdateBroadcaster;
import com.google.common.base.Functions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public abstract class Listener87Entry implements ComparableExtension {
   public static PreUpdateBroadcaster preUpdateBroadcaster;

   public static List resolveListByTextArray(String[] textArray, int count, BlockPos blockPos) {
      if (blockPos == null) {
         return null;
      } else {
         int count2 = textArray.length - 1;
         String text;
         if (count2 == count) {
            text = Integer.toString(blockPos.getX());
         } else if (count2 == count + 1) {
            text = Integer.toString(blockPos.getY());
         } else {
            if (count2 != count + 2) {
               return null;
            }

            text = Integer.toString(blockPos.getZ());
         }

         return Lists.newArrayList(new String[]{text});
      }
   }

   public static List resolveList(String[] textArray, int count, BlockPos blockPos) {
      if (blockPos == null) {
         return null;
      } else {
         int count2 = textArray.length - 1;
         String text;
         if (count2 == count) {
            text = Integer.toString(blockPos.getX());
         } else {
            if (count2 != count + 1) {
               return null;
            }

            text = Integer.toString(blockPos.getZ());
         }

         return Lists.newArrayList(new String[]{text});
      }
   }

   @Override
   public int compareTo(Object entry) {
      return this.computeCount((ComparableExtension)entry);
   }

   public static IChatComponent resolveIChatComponent(List list) {
      ChatComponentText chatComponentText = new ChatComponentText("");

      for (int index = 0; index < list.size(); index++) {
         if (index > 0) {
            if (index == list.size() - 1) {
               chatComponentText.resolveIChatComponent(" and ");
            } else if (index > 0) {
               chatComponentText.resolveIChatComponent(", ");
            }
         }

         chatComponentText.appendChatComponent((IChatComponent)list.get(index));
      }

      return chatComponentText;
   }

   public static EntityPlayerAdapter resolveEntityPlayerAdapter(ICommandSender iCommandSender, String text) throws AimProvider {
      EntityPlayerAdapter entityPlayerAdapter = FovAggregator.resolveEntityPlayerAdapter(iCommandSender, text);
      if (entityPlayerAdapter == null) {
         try {
            entityPlayerAdapter = VanillaRunnable.getVanillaRunnable().getMovingManager().get(UUID.fromString(text));
         } catch (IllegalArgumentException illegalArgumentException) {
         }
      }

      if (entityPlayerAdapter == null) {
         entityPlayerAdapter = VanillaRunnable.getVanillaRunnable().getMovingManager().resolveEntityPlayerAdapter(text);
      }

      if (entityPlayerAdapter == null) {
         throw new AimProvider();
      } else {
         return entityPlayerAdapter;
      }
   }

   public static double computeValue(String text, double offset) throws AimDirector {
      return computeValue(text, offset, Double.MAX_VALUE);
   }

   public static int computeCount(String text, int count, int count2) throws AimDirector {
      int count3 = parseInt(text);
      if (count3 < count) {
         throw new AimDirector("commands.generic.num.tooSmall", new Object[]{count3, count});
      } else if (count3 > count2) {
         throw new AimDirector("commands.generic.num.tooBig", new Object[]{count3, count2});
      } else {
         return count3;
      }
   }

   public static String resolveText(String[] textArray, int count) {
      StringBuilder builder = new StringBuilder();

      for (int index = count; index < textArray.length; index++) {
         if (index > count) {
            builder.append(" ");
         }

         String text = textArray[index];
         builder.append(text);
      }

      return builder.toString();
   }

   public static IChatComponent resolveIChatComponent(ICommandSender iCommandSender, String[] textArray, int count, boolean flag) throws AimProvider {
      ChatComponentText chatComponentText = new ChatComponentText("");

      for (int index = count; index < textArray.length; index++) {
         if (index > count) {
            chatComponentText.resolveIChatComponent(" ");
         }

         Object chatComponentText2 = new ChatComponentText(textArray[index]);
         if (flag) {
            IChatComponent iChatComponent = FovAggregator.resolveIChatComponent(iCommandSender, textArray[index]);
            if (iChatComponent == null) {
               if (FovAggregator.matchesText(textArray[index])) {
                  throw new AimProvider();
               }
            } else {
               chatComponentText2 = iChatComponent;
            }
         }

         chatComponentText.appendChatComponent((IChatComponent)chatComponentText2);
      }

      return chatComponentText;
   }

   public static Controller17 resolveController17(double offset, String text, boolean flag) throws AimDirector {
      return resolveController17(offset, text, -30000000, 30000000, flag);
   }

   public static Entity resolveEntity(ICommandSender iCommandSender, String text) throws AimStack {
      return resolveEntity(iCommandSender, text, Entity.class);
   }

   public static void setPreUpdateBroadcaster(PreUpdateBroadcaster preUpdateBroadcaster2) {
      preUpdateBroadcaster = preUpdateBroadcaster2;
   }

   public static long parseLong(String text) throws AimDirector {
      try {
         return Long.parseLong(text);
      } catch (NumberFormatException numberFormatException) {
         throw new AimDirector("commands.generic.num.invalid", new Object[]{text});
      }
   }

   public static void applyICommandSender(ICommandSender iCommandSender, ComparableExtension comparableExtension, int count, String text, Object[] entryArray) {
      if (preUpdateBroadcaster != null) {
         preUpdateBroadcaster.applyICommandSender(iCommandSender, comparableExtension, count, text, entryArray);
      }
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return false;
   }

   public static String resolveText(Collection collection) {
      return resolveText(collection.toArray(new String[collection.size()]));
   }

   public static IChatComponent resolveIChatComponent(ICommandSender iCommandSender, String[] textArray, int count) throws InternalExceptionAdapter, AimProvider {
      return resolveIChatComponent(iCommandSender, textArray, count, false);
   }

   public static void applyICommandSender(ICommandSender iCommandSender, ComparableExtension comparableExtension, String text, Object[] entryArray) {
      applyICommandSender(iCommandSender, comparableExtension, 0, text, entryArray);
   }

   public static int computeCount(String text, int count) throws AimDirector {
      return computeCount(text, count, Integer.MAX_VALUE);
   }

   public static boolean matchesText(String text, String text2) {
      return text2.regionMatches(true, 0, text, 0, text.length());
   }

   public static Block resolveBlock(ICommandSender iCommandSender, String text) throws AimDirector {
      ResourceLocation location = new ResourceLocation(text);
      if (!Block.iterableExtensionBridge.containsKey(location)) {
         throw new AimDirector("commands.give.block.notFound", new Object[]{location});
      } else {
         Block object = (Block)Block.iterableExtensionBridge.getObject(location);
         if (object == null) {
            throw new AimDirector("commands.give.block.notFound", new Object[]{location});
         } else {
            return object;
         }
      }
   }

   public static Controller17 resolveController17(double offset, String text, int count, int count2, boolean flag) throws AimDirector {
      boolean flag2 = text.startsWith("~");
      if (flag2 && Double.isNaN(offset)) {
         throw new AimDirector("commands.generic.num.invalid", new Object[]{offset});
      } else {
         double offset2 = 0.0;
         if (!flag2 || text.length() > 1) {
            boolean flag3 = text.contains(".");
            if (flag2) {
               text = text.substring(1);
            }

            offset2 += computeValue(text);
            if (!flag3 && !flag2 && flag) {
               offset2 += 0.5;
            }
         }

         if (count != 0 || count2 != 0) {
            if (offset2 < (double)count) {
               throw new AimDirector("commands.generic.double.tooSmall", new Object[]{offset2, count});
            }

            if (offset2 > (double)count2) {
               throw new AimDirector("commands.generic.double.tooBig", new Object[]{offset2, count2});
            }
         }

         return new Controller17(offset2 + (flag2 ? offset : 0.0), offset2, flag2);
      }
   }

   public static Item resolveItem(ICommandSender iCommandSender, String text) throws AimDirector {
      ResourceLocation location = new ResourceLocation(text);
      Item object = (Item)Item.iterableExtensionAdapter.getObject(location);
      if (object == null) {
         throw new AimDirector("commands.give.item.notFound", new Object[]{location});
      } else {
         return object;
      }
   }

   public static double computeValue(double offset, String text, int count, int count2, boolean flag) throws AimDirector {
      boolean flag2 = text.startsWith("~");
      if (flag2 && Double.isNaN(offset)) {
         throw new AimDirector("commands.generic.num.invalid", new Object[]{offset});
      } else {
         double offset2 = flag2 ? offset : 0.0;
         if (!flag2 || text.length() > 1) {
            boolean flag3 = text.contains(".");
            if (flag2) {
               text = text.substring(1);
            }

            offset2 += computeValue(text);
            if (!flag3 && !flag2 && flag) {
               offset2 += 0.5;
            }
         }

         if (count != 0 || count2 != 0) {
            if (offset2 < (double)count) {
               throw new AimDirector("commands.generic.double.tooSmall", new Object[]{offset2, count});
            }

            if (offset2 > (double)count2) {
               throw new AimDirector("commands.generic.double.tooBig", new Object[]{offset2, count2});
            }
         }

         return offset2;
      }
   }

   public static double computeValue(double offset, String text, boolean flag) throws AimDirector {
      return computeValue(offset, text, -30000000, 30000000, flag);
   }

   public static BlockPos resolveBlockPos(ICommandSender iCommandSender, String[] textArray, int count, boolean flag) throws AimDirector {
      BlockPos blockPos = iCommandSender.getBlockPos();
      return new BlockPos(
         computeValue((double)blockPos.getX(), textArray[count], -30000000, 30000000, flag),
         computeValue((double)blockPos.getY(), textArray[count + 1], 0, 256, false),
         computeValue((double)blockPos.getZ(), textArray[count + 2], -30000000, 30000000, flag)
      );
   }

   public static String resolveTextByICommandSender(ICommandSender iCommandSender, String text) throws AimStack {
      try {
         return resolveEntityPlayerAdapter(iCommandSender, text).getText();
      } catch (AimProvider exception) {
         try {
            return resolveEntity(iCommandSender, text).getEntityUniqueID().toString();
         } catch (AimStack exception2) {
            if (FovAggregator.matchesText(text)) {
               throw exception2;
            } else {
               return text;
            }
         }
      }
   }

   public static List resolveList(String[] textArray, String[] textArray2) {
      return resolveList(textArray, Arrays.asList(textArray2));
   }

   public int computeCount(ComparableExtension comparableExtension) {
      return this.getText().compareTo(comparableExtension.getText());
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return null;
   }

   public static List resolveList(String[] textArray, Collection collection) {
      String text = textArray[textArray.length - 1];
      ArrayList list = Lists.newArrayList();
      if (!collection.isEmpty()) {
         for (String text2 : (Iterable<String>)(Iterable<?>)Iterables.transform(collection, Functions.toStringFunction())) {
            if (matchesText(text, text2)) {
               list.add(text2);
            }
         }

         if (list.isEmpty()) {
            for (Object element : collection) {
               if (element instanceof ResourceLocation && matchesText(text, ((ResourceLocation)element).getText1())) {
                  list.add(String.valueOf(element));
               }
            }
         }
      }

      return list;
   }

   public static EntityPlayerAdapter resolveEntityPlayerAdapter(ICommandSender iCommandSender) throws AimProvider {
      if (iCommandSender instanceof EntityPlayerAdapter) {
         return (EntityPlayerAdapter)iCommandSender;
      } else {
         throw new AimProvider("You must specify which player you wish to perform this action on.", new Object[0]);
      }
   }

   public static int parseInt(String text) throws AimDirector {
      try {
         return Integer.parseInt(text);
      } catch (NumberFormatException numberFormatException) {
         throw new AimDirector("commands.generic.num.invalid", new Object[]{text});
      }
   }

   public int getCount() {
      return 4;
   }

   public static boolean matchesText(String text) throws InternalExceptionAdapter {
      if (text.equals("true") || text.equals("1")) {
         return true;
      } else if (!text.equals("false") && !text.equals("0")) {
         throw new InternalExceptionAdapter("commands.generic.boolean.invalid", new Object[]{text});
      } else {
         return false;
      }
   }

   public static long computeTimestamp(String text, long value, long value2) throws AimDirector {
      long value3 = parseLong(text);
      if (value3 < value) {
         throw new AimDirector("commands.generic.num.tooSmall", new Object[]{value3, value});
      } else if (value3 > value2) {
         throw new AimDirector("commands.generic.num.tooBig", new Object[]{value3, value2});
      } else {
         return value3;
      }
   }

   public static List resolveList(ICommandSender iCommandSender, String text) throws AimStack {
      return (List)(FovAggregator.matchesText(text)
         ? FovAggregator.resolveList(iCommandSender, text, Entity.class)
         : Lists.newArrayList(new Entity[]{resolveEntity(iCommandSender, text)}));
   }

   @Override
   public boolean matchesICommandSender(ICommandSender iCommandSender) {
      return iCommandSender.matchesText(this.getCount(), this.getText());
   }

   public static String resolveText(ICommandSender iCommandSender, String text) throws AimProvider {
      try {
         return resolveEntityPlayerAdapter(iCommandSender, text).getText();
      } catch (AimProvider exception) {
         if (FovAggregator.matchesText(text)) {
            throw exception;
         } else {
            return text;
         }
      }
   }

   public static String resolveText(Object[] entryArray) {
      StringBuilder builder = new StringBuilder();

      for (int index = 0; index < entryArray.length; index++) {
         String text = entryArray[index].toString();
         if (index > 0) {
            if (index == entryArray.length - 1) {
               builder.append(" and ");
            } else {
               builder.append(", ");
            }
         }

         builder.append(text);
      }

      return builder.toString();
   }

   public static Entity resolveEntity(ICommandSender iCommandSender, String text, Class clazz) throws AimStack {
      Object entry = FovAggregator.resolveEntity(iCommandSender, text, clazz);
      VanillaRunnable vanillaRunnable = VanillaRunnable.getVanillaRunnable();
      if (entry == null) {
         entry = vanillaRunnable.getMovingManager().resolveEntityPlayerAdapter(text);
      }

      if (entry == null) {
         try {
            UUID uuid = UUID.fromString(text);
            entry = vanillaRunnable.resolveEntityByUUID(uuid);
            if (entry == null) {
               entry = vanillaRunnable.getMovingManager().get(uuid);
            }
         } catch (IllegalArgumentException illegalArgumentException) {
            throw new AimStack("commands.generic.entity.invalidUuid", new Object[0]);
         }
      }

      if (entry != null && clazz.isAssignableFrom(entry.getClass())) {
         return (Entity)entry;
      } else {
         throw new AimStack();
      }
   }

   public static double computeValue(String text, double offset, double offset2) throws AimDirector {
      double offset3 = computeValue(text);
      if (offset3 < offset) {
         throw new AimDirector("commands.generic.double.tooSmall", new Object[]{offset3, offset});
      } else if (offset3 > offset2) {
         throw new AimDirector("commands.generic.double.tooBig", new Object[]{offset3, offset2});
      } else {
         return offset3;
      }
   }

   @Override
   public List asList() {
      return Collections.emptyList();
   }

   public static double computeValue(String text) throws AimDirector {
      try {
         double offset = Double.parseDouble(text);
         if (!Doubles.isFinite(offset)) {
            throw new AimDirector("commands.generic.num.invalid", new Object[]{text});
         } else {
            return offset;
         }
      } catch (NumberFormatException numberFormatException) {
         throw new AimDirector("commands.generic.num.invalid", new Object[]{text});
      }
   }
}
