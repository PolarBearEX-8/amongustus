package net.minecraft.util;

import java.util.List;
import net.minecraft.command.CommandException;
import net.minecraft.command.EntityNotFoundException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import net.minecraft.entity.Entity;

public class ChatComponentProcessor {
   public static IChatComponent processComponent(ICommandSender commandSender, IChatComponent component, Entity entity) throws CommandException {
      Object obj = null;
      if (component instanceof ChatComponentScore) {
         ChatComponentScore chatComponentScore = (ChatComponentScore)component;
         String name = chatComponentScore.getName();
         if (PlayerSelector.hasArguments(name)) {
            List list = PlayerSelector.matchEntities(commandSender, name, Entity.class);
            if (list.size() != 1) {
               throw new EntityNotFoundException();
            }

            name = ((Entity)list.get(0)).getName();
         }

         obj = entity != null && name.equals("*")
            ? new ChatComponentScore(entity.getName(), chatComponentScore.getObjective())
            : new ChatComponentScore(name, chatComponentScore.getObjective());
         ((ChatComponentScore)obj).setValue(chatComponentScore.getUnformattedTextForChat());
      } else if (component instanceof ChatComponentSelector) {
         obj = PlayerSelector.matchEntitiesToChatComponent(commandSender, ((ChatComponentSelector)component).getSelector());
         if (obj == null) {
            obj = new ChatComponentText("");
         }
      } else if (component instanceof ChatComponentText) {
         obj = new ChatComponentText(((ChatComponentText)component).getChatComponentText_TextValue());
      } else {
         if (!(component instanceof ChatComponentTranslation)) {
            return component;
         }

         Object[] formatArgs = ((ChatComponentTranslation)component).getFormatArgs();

         for (int i = 0; i < formatArgs.length; i++) {
            Object obj2 = formatArgs[i];
            if (obj2 instanceof IChatComponent) {
               formatArgs[i] = processComponent(commandSender, (IChatComponent)obj2, entity);
            }
         }

         obj = new ChatComponentTranslation(((ChatComponentTranslation)component).getKey(), formatArgs);
      }

      ChatStyle chatStyle = component.getChatStyle();
      if (chatStyle != null) {
         ((IChatComponent)obj).setChatStyle(chatStyle.createShallowCopy());
      }

      for (IChatComponent component2 : (Iterable<IChatComponent>)(component.getSiblings())) {
         ((IChatComponent)obj).appendSibling(processComponent(commandSender, component2, entity));
      }

      return (IChatComponent)obj;
   }
}
