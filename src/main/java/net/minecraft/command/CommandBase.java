package net.minecraft.command;

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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public abstract class CommandBase implements ICommand {
   private static IAdminCommand theAdmin;

   public static Entity getEntity(ICommandSender commandSender, String str, Class clazz) throws EntityNotFoundException {
      Object obj = PlayerSelector.matchOneEntity(commandSender, str, clazz);
      MinecraftServer server = MinecraftServer.getServer();
      if (obj == null) {
         obj = server.getConfigurationManager().getPlayerByUsername(str);
      }

      if (obj == null) {
         try {
            UUID uuid = UUID.fromString(str);
            obj = server.getEntityFromUuid(uuid);
            if (obj == null) {
               obj = server.getConfigurationManager().getPlayerByUUID(uuid);
            }
         } catch (IllegalArgumentException e) {
            throw new EntityNotFoundException("commands.generic.entity.invalidUuid", new Object[0]);
         }
      }

      if (obj != null && clazz.isAssignableFrom(obj.getClass())) {
         return (Entity)obj;
      } else {
         throw new EntityNotFoundException();
      }
   }

   public static double parseDouble(String str) throws NumberInvalidException {
      try {
         double d = Double.parseDouble(str);
         if (!Doubles.isFinite(d)) {
            throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{str});
         } else {
            return d;
         }
      } catch (NumberFormatException e) {
         throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{str});
      }
   }

   public static String getPlayerName(ICommandSender commandSender, String str) throws PlayerNotFoundException {
      try {
         return getPlayer(commandSender, str).getName();
      } catch (PlayerNotFoundException e) {
         if (PlayerSelector.hasArguments(str)) {
            throw e;
         } else {
            return str;
         }
      }
   }

   @Override
   public boolean canCommandSenderUseCommand(ICommandSender commandSender) {
      return commandSender.canCommandSenderUseCommand(this.getRequiredPermissionLevel(), this.getCommandName());
   }

   public static void setAdminCommander(IAdminCommand adminCommand) {
      theAdmin = adminCommand;
   }

   public static List getTabCompletionCoordinate(String[] strings, int n, BlockPos pos) {
      if (pos == null) {
         return null;
      } else {
         int n2 = strings.length - 1;
         String str;
         if (n2 == n) {
            str = Integer.toString(pos.getX());
         } else if (n2 == n + 1) {
            str = Integer.toString(pos.getY());
         } else {
            if (n2 != n + 2) {
               return null;
            }

            str = Integer.toString(pos.getZ());
         }

         return Lists.newArrayList(new String[]{str});
      }
   }

   public static EntityPlayerMP getPlayer(ICommandSender commandSender, String str) throws PlayerNotFoundException {
      EntityPlayerMP entityPlayerMP = PlayerSelector.matchOnePlayer(commandSender, str);
      if (entityPlayerMP == null) {
         try {
            entityPlayerMP = MinecraftServer.getServer().getConfigurationManager().getPlayerByUUID(UUID.fromString(str));
         } catch (IllegalArgumentException e) {
         }
      }

      if (entityPlayerMP == null) {
         entityPlayerMP = MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(str);
      }

      if (entityPlayerMP == null) {
         throw new PlayerNotFoundException();
      } else {
         return entityPlayerMP;
      }
   }

   public static CommandBase.CoordinateArg parseCoordinate(double d, String str, boolean flag) throws NumberInvalidException {
      return parseCoordinate(d, str, -30000000, 30000000, flag);
   }

   public static EntityPlayerMP getCommandSenderAsPlayer(ICommandSender commandSender) throws PlayerNotFoundException {
      if (commandSender instanceof EntityPlayerMP) {
         return (EntityPlayerMP)commandSender;
      } else {
         throw new PlayerNotFoundException("You must specify which player you wish to perform this action on.", new Object[0]);
      }
   }

   public static int parseInt(String str) throws NumberInvalidException {
      try {
         return Integer.parseInt(str);
      } catch (NumberFormatException e) {
         throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{str});
      }
   }

   public static long parseLong(String str) throws NumberInvalidException {
      try {
         return Long.parseLong(str);
      } catch (NumberFormatException e) {
         throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{str});
      }
   }

   public static int parseInt(String str, int n) throws NumberInvalidException {
      return parseInt(str, n, Integer.MAX_VALUE);
   }

   public static void notifyOperators(ICommandSender commandSender, ICommand command, String str, Object[] array) {
      notifyOperators(commandSender, command, 0, str, array);
   }

   @Override
   public int compareTo(Object other) {
      return this.compareTo((ICommand)other);
   }

   public static List getListOfStringsMatchingLastWord(String[] strings, Collection items) {
      String str = strings[strings.length - 1];
      ArrayList list = Lists.newArrayList();
      if (!items.isEmpty()) {
         for (String str2 : (Iterable<String>)(Iterables.transform(items, Functions.toStringFunction()))) {
            if (doesStringStartWith(str, str2)) {
               list.add(str2);
            }
         }

         if (list.isEmpty()) {
            for (Object obj : items) {
               if (obj instanceof ResourceLocation && doesStringStartWith(str, ((ResourceLocation)obj).getResourcePath())) {
                  list.add(String.valueOf(obj));
               }
            }
         }
      }

      return list;
   }

   public static CommandBase.CoordinateArg parseCoordinate(double d, String str, int n, int n2, boolean flag) throws NumberInvalidException {
      boolean flag2 = str.startsWith("~");
      if (flag2 && Double.isNaN(d)) {
         throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{d});
      } else {
         double d2 = 0.0;
         if (!flag2 || str.length() > 1) {
            boolean flag3 = str.contains(".");
            if (flag2) {
               str = str.substring(1);
            }

            d2 += parseDouble(str);
            if (!flag3 && !flag2 && flag) {
               d2 += 0.5;
            }
         }

         if (n != 0 || n2 != 0) {
            if (d2 < (double)n) {
               throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[]{d2, n});
            }

            if (d2 > (double)n2) {
               throw new NumberInvalidException("commands.generic.double.tooBig", new Object[]{d2, n2});
            }
         }

         return new CommandBase.CoordinateArg(d2 + (flag2 ? d : 0.0), d2, flag2);
      }
   }

   public static IChatComponent join(List list) {
      ChatComponentText chatComponentText = new ChatComponentText("");

      for (int i = 0; i < list.size(); i++) {
         if (i > 0) {
            if (i == list.size() - 1) {
               chatComponentText.appendText(" and ");
            } else if (i > 0) {
               chatComponentText.appendText(", ");
            }
         }

         chatComponentText.appendSibling((IChatComponent)list.get(i));
      }

      return chatComponentText;
   }

   public static double parseDouble(double d, String str, boolean flag) throws NumberInvalidException {
      return parseDouble(d, str, -30000000, 30000000, flag);
   }

   public static List getTabCompletionCoordinateXZ(String[] strings, int n, BlockPos pos) {
      if (pos == null) {
         return null;
      } else {
         int n2 = strings.length - 1;
         String str;
         if (n2 == n) {
            str = Integer.toString(pos.getX());
         } else {
            if (n2 != n + 1) {
               return null;
            }

            str = Integer.toString(pos.getZ());
         }

         return Lists.newArrayList(new String[]{str});
      }
   }

   @Override
   public List getCommandAliases() {
      return Collections.emptyList();
   }

   public static int parseInt(String str, int n, int n2) throws NumberInvalidException {
      int n3 = parseInt(str);
      if (n3 < n) {
         throw new NumberInvalidException("commands.generic.num.tooSmall", new Object[]{n3, n});
      } else if (n3 > n2) {
         throw new NumberInvalidException("commands.generic.num.tooBig", new Object[]{n3, n2});
      } else {
         return n3;
      }
   }

   public static String buildString(String[] strings, int n) {
      StringBuilder sb = new StringBuilder();

      for (int i = n; i < strings.length; i++) {
         if (i > n) {
            sb.append(" ");
         }

         sb.append(strings[i]);
      }

      return sb.toString();
   }

   public static void notifyOperators(ICommandSender commandSender, ICommand command, int n, String str, Object[] array) {
      if (theAdmin != null) {
         theAdmin.notifyOperators(commandSender, command, n, str, array);
      }
   }

   public static List getListOfStringsMatchingLastWord(String[] strings, String[] strings2) {
      return getListOfStringsMatchingLastWord(strings, Arrays.asList(strings2));
   }

   public static double parseDouble(String str, double d) throws NumberInvalidException {
      return parseDouble(str, d, Double.MAX_VALUE);
   }

   public static String getEntityName(ICommandSender commandSender, String str) throws EntityNotFoundException {
      try {
         return getPlayer(commandSender, str).getName();
      } catch (PlayerNotFoundException e) {
         try {
            return getEntity(commandSender, str).getUniqueID().toString();
         } catch (EntityNotFoundException e2) {
            if (PlayerSelector.hasArguments(str)) {
               throw e2;
            } else {
               return str;
            }
         }
      }
   }

   public static Block getBlockByText(ICommandSender commandSender, String str) throws NumberInvalidException {
      ResourceLocation location = new ResourceLocation(str);
      if (!Block.blockRegistry.containsKey(location)) {
         throw new NumberInvalidException("commands.give.block.notFound", new Object[]{location});
      } else {
         Block object = (Block)Block.blockRegistry.getObject(location);
         if (object == null) {
            throw new NumberInvalidException("commands.give.block.notFound", new Object[]{location});
         } else {
            return object;
         }
      }
   }

   public static IChatComponent getChatComponentFromNthArg(ICommandSender commandSender, String[] strings, int n, boolean flag) throws PlayerNotFoundException {
      ChatComponentText chatComponentText = new ChatComponentText("");

      for (int i = n; i < strings.length; i++) {
         if (i > n) {
            chatComponentText.appendText(" ");
         }

         Object obj = new ChatComponentText(strings[i]);
         if (flag) {
            IChatComponent component = PlayerSelector.matchEntitiesToChatComponent(commandSender, strings[i]);
            if (component == null) {
               if (PlayerSelector.hasArguments(strings[i])) {
                  throw new PlayerNotFoundException();
               }
            } else {
               obj = component;
            }
         }

         chatComponentText.appendSibling((IChatComponent)obj);
      }

      return chatComponentText;
   }

   public int compareTo(ICommand other) {
      return this.getCommandName().compareTo(other.getCommandName());
   }

   public int getRequiredPermissionLevel() {
      return 4;
   }

   public static boolean parseBoolean(String str) throws CommandException {
      if (str.equals("true") || str.equals("1")) {
         return true;
      } else if (!str.equals("false") && !str.equals("0")) {
         throw new CommandException("commands.generic.boolean.invalid", new Object[]{str});
      } else {
         return false;
      }
   }

   public static List getEntityList(ICommandSender commandSender, String str) throws EntityNotFoundException {
      return (List)(PlayerSelector.hasArguments(str)
         ? PlayerSelector.matchEntities(commandSender, str, Entity.class)
         : Lists.newArrayList(new Entity[]{getEntity(commandSender, str)}));
   }

   public static boolean doesStringStartWith(String str, String str2) {
      return str2.regionMatches(true, 0, str, 0, str.length());
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return false;
   }

   public static String joinNiceStringFromCollection(Collection items) {
      return joinNiceString(items.toArray(new String[items.size()]));
   }

   public static BlockPos parseBlockPos(ICommandSender commandSender, String[] strings, int n, boolean flag) throws NumberInvalidException {
      BlockPos position = commandSender.getPosition();
      return new BlockPos(
         parseDouble((double)position.getX(), strings[n], -30000000, 30000000, flag),
         parseDouble((double)position.getY(), strings[n + 1], 0, 256, false),
         parseDouble((double)position.getZ(), strings[n + 2], -30000000, 30000000, flag)
      );
   }

   public static String joinNiceString(Object[] array) {
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < array.length; i++) {
         String str = array[i].toString();
         if (i > 0) {
            if (i == array.length - 1) {
               sb.append(" and ");
            } else {
               sb.append(", ");
            }
         }

         sb.append(str);
      }

      return sb.toString();
   }

   public static Entity getEntity(ICommandSender commandSender, String str) throws EntityNotFoundException {
      return getEntity(commandSender, str, Entity.class);
   }

   public static double parseDouble(double d, String str, int n, int n2, boolean flag) throws NumberInvalidException {
      boolean flag2 = str.startsWith("~");
      if (flag2 && Double.isNaN(d)) {
         throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{d});
      } else {
         double d2 = flag2 ? d : 0.0;
         if (!flag2 || str.length() > 1) {
            boolean flag3 = str.contains(".");
            if (flag2) {
               str = str.substring(1);
            }

            d2 += parseDouble(str);
            if (!flag3 && !flag2 && flag) {
               d2 += 0.5;
            }
         }

         if (n != 0 || n2 != 0) {
            if (d2 < (double)n) {
               throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[]{d2, n});
            }

            if (d2 > (double)n2) {
               throw new NumberInvalidException("commands.generic.double.tooBig", new Object[]{d2, n2});
            }
         }

         return d2;
      }
   }

   public static IChatComponent getChatComponentFromNthArg(ICommandSender commandSender, String[] strings, int n) throws CommandException, PlayerNotFoundException {
      return getChatComponentFromNthArg(commandSender, strings, n, false);
   }

   public static long parseLong(String str, long l, long l2) throws NumberInvalidException {
      long l3 = parseLong(str);
      if (l3 < l) {
         throw new NumberInvalidException("commands.generic.num.tooSmall", new Object[]{l3, l});
      } else if (l3 > l2) {
         throw new NumberInvalidException("commands.generic.num.tooBig", new Object[]{l3, l2});
      } else {
         return l3;
      }
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return null;
   }

   public static double parseDouble(String str, double d, double d2) throws NumberInvalidException {
      double d3 = parseDouble(str);
      if (d3 < d) {
         throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[]{d3, d});
      } else if (d3 > d2) {
         throw new NumberInvalidException("commands.generic.double.tooBig", new Object[]{d3, d2});
      } else {
         return d3;
      }
   }

   public static Item getItemByText(ICommandSender commandSender, String str) throws NumberInvalidException {
      ResourceLocation location = new ResourceLocation(str);
      Item object = (Item)Item.itemRegistry.getObject(location);
      if (object == null) {
         throw new NumberInvalidException("commands.give.item.notFound", new Object[]{location});
      } else {
         return object;
      }
   }


   public static class CoordinateArg {
      private final double result;
      private final double amount;
      private final boolean isRelative;

      public boolean isRelative() {
         return this.isRelative;
      }

      public double getAmount() {
         return this.amount;
      }

      public CoordinateArg(double d, double d2, boolean flag) {
         this.result = d;
         this.amount = d2;
         this.isRelative = flag;
      }

      public double getResult() {
         return this.result;
      }
   }
}
