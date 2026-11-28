package net.minecraft.command;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandHandler implements ICommandManager {
   private static final Logger LOGGER = LogManager.getLogger();
   private final Map commandMap = Maps.newHashMap();
   private final Set commandSet = Sets.newHashSet();

   @Override
   public int executeCommand(ICommandSender commandSender, String str) {
      str = str.trim();
      if (str.startsWith("/")) {
         str = str.substring(1);
      }

      String[] strings = str.split(" ");
      String str2 = strings[0];
      strings = dropFirstString(strings);
      ICommand command = (ICommand)this.commandMap.get(str2);
      int usernameIndex = this.getUsernameIndex(command, strings);
      int n = 0;
      if (command == null) {
         ChatComponentTranslation chatComponentTranslation = new ChatComponentTranslation("commands.generic.notFound", new Object[0]);
         chatComponentTranslation.getChatStyle().setColor(EnumChatFormatting.RED);
         commandSender.addChatMessage(chatComponentTranslation);
      } else if (command.canCommandSenderUseCommand(commandSender)) {
         if (usernameIndex > -1) {
            List list = PlayerSelector.matchEntities(commandSender, strings[usernameIndex], Entity.class);
            String str3 = strings[usernameIndex];
            commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_ENTITIES, list.size());

            for (Entity entity : (Iterable<Entity>)(list)) {
               strings[usernameIndex] = entity.getUniqueID().toString();
               if (this.tryExecute(commandSender, strings, command, str)) {
                  n++;
               }
            }

            strings[usernameIndex] = str3;
         } else {
            commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_ENTITIES, 1);
            if (this.tryExecute(commandSender, strings, command, str)) {
               n++;
            }
         }
      } else {
         ChatComponentTranslation chatComponentTranslation2 = new ChatComponentTranslation("commands.generic.permission", new Object[0]);
         chatComponentTranslation2.getChatStyle().setColor(EnumChatFormatting.RED);
         commandSender.addChatMessage(chatComponentTranslation2);
      }

      commandSender.setCommandStat(CommandResultStats.Type.SUCCESS_COUNT, n);
      return n;
   }

   public ICommand registerCommand(ICommand command) {
      this.commandMap.put(command.getCommandName(), command);
      this.commandSet.add(command);

      for (String str : (Iterable<String>)(command.getCommandAliases())) {
         ICommand command2 = (ICommand)this.commandMap.get(str);
         if (command2 == null || !command2.getCommandName().equals(str)) {
            this.commandMap.put(str, command);
         }
      }

      return command;
   }

   @Override
   public List getTabCompletionOptions(ICommandSender commandSender, String str, BlockPos pos) {
      String[] strings = str.split(" ", -1);
      String str2 = strings[0];
      if (strings.length == 1) {
         ArrayList list = Lists.newArrayList();

         for (Entry entry : (Iterable<Entry>)(this.commandMap.entrySet())) {
            if (CommandBase.doesStringStartWith(str2, (String)entry.getKey()) && ((ICommand)entry.getValue()).canCommandSenderUseCommand(commandSender)) {
               list.add(entry.getKey());
            }
         }

         return list;
      } else {
         if (strings.length > 1) {
            ICommand command = (ICommand)this.commandMap.get(str2);
            if (command != null && command.canCommandSenderUseCommand(commandSender)) {
               return command.addTabCompletionOptions(commandSender, dropFirstString(strings), pos);
            }
         }

         return null;
      }
   }

   @Override
   public Map getCommands() {
      return this.commandMap;
   }

   private int getUsernameIndex(ICommand command, String[] strings) {
      if (command == null) {
         return -1;
      } else {
         for (int i = 0; i < strings.length; i++) {
            if (command.isUsernameIndex(strings, i) && PlayerSelector.matchesMultiplePlayers(strings[i])) {
               return i;
            }
         }

         return -1;
      }
   }

   public boolean tryExecute(ICommandSender commandSender, String[] strings, ICommand command, String str) {
      try {
         command.processCommand(commandSender, strings);
         return true;
      } catch (WrongUsageException e) {
         ChatComponentTranslation chatComponentTranslation = new ChatComponentTranslation(
            "commands.generic.usage", new Object[]{new ChatComponentTranslation(e.getMessage(), e.getErrorObjects())}
         );
         chatComponentTranslation.getChatStyle().setColor(EnumChatFormatting.RED);
         commandSender.addChatMessage(chatComponentTranslation);
      } catch (CommandException e2) {
         ChatComponentTranslation chatComponentTranslation2 = new ChatComponentTranslation(e2.getMessage(), e2.getErrorObjects());
         chatComponentTranslation2.getChatStyle().setColor(EnumChatFormatting.RED);
         commandSender.addChatMessage(chatComponentTranslation2);
      } catch (Throwable e3) {
         ChatComponentTranslation chatComponentTranslation3 = new ChatComponentTranslation("commands.generic.exception", new Object[0]);
         chatComponentTranslation3.getChatStyle().setColor(EnumChatFormatting.RED);
         commandSender.addChatMessage(chatComponentTranslation3);
         LOGGER.warn("Couldn't process command: '" + str + "'");
      }

      return false;
   }

   private static String[] dropFirstString(String[] strings) {
      String[] strings2 = new String[strings.length - 1];
      System.arraycopy(strings, 1, strings2, 0, strings.length - 1);
      return strings2;
   }

   @Override
   public List getPossibleCommands(ICommandSender commandSender) {
      ArrayList list = Lists.newArrayList();

      for (ICommand command : (Iterable<ICommand>)(this.commandSet)) {
         if (command.canCommandSenderUseCommand(commandSender)) {
            list.add(command);
         }
      }

      return list;
   }
}
