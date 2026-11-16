package client.util;

import client.ClientGlobals;
import client.network.NetworkPlayerInfo;
import java.util.Iterator;

public class CompositeStringEntryAdapter extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (Add or remove friends)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length != 3) {
         this.updateState2();
      } else {
         String text2 = textArray[2];
         if (!TabListBotFilter.checkString2(text2)) {
            String text3 = "Invialid name";
            this.applyText(text3);
         } else {
            if (!textArray[1].equalsIgnoreCase("add") && !textArray[1].equalsIgnoreCase("permaAdd")) {
               if (textArray[1].equalsIgnoreCase("remove")) {
                  if (ClientGlobals.moduleManager.midClick.friendedPlayers.contains(text2)) {
                     ClientGlobals.moduleManager.midClick.friendedPlayers.remove(text2);
                     ClientGlobals.moduleManager.midClick.applyString6(text2);
                     String text4 = "Player §c" + text2 + "§7 succesfully removed.";
                     this.applyText(text4);
                  } else {
                     String text5 = "Player §c" + text2 + "§7 isn't in your friend list.";
                     this.applyText(text5);
                  }
               } else {
                  this.updateState2();
               }
            } else {
               if (ClientGlobals.moduleManager.midClick.friendedPlayers.contains(text2)) {
                  String text6 = "Player §c" + text2 + "§7 is already your friend.";
                  this.applyText(text6);
                  return;
               }

               Iterator iterator = MinecraftAccessor.minecraft.thePlayer.netHandlerPlayClient.getCollection().iterator();

               while (iterator.hasNext()) {
                  String name = ((NetworkPlayerInfo)iterator.next()).getGameProfile().getName();
                  if (!name.equals(MinecraftAccessor.minecraft.thePlayer.getGameProfile().getName()) && text2.equals(name)) {
                     ClientGlobals.moduleManager.midClick.friendedPlayers.add(text2);
                     if (textArray[1].equalsIgnoreCase("permaAdd")) {
                        ClientGlobals.moduleManager.midClick.applyString(text2);
                     }

                     String text7 = "Player §c" + text2 + "§7 succesfully added.";
                     this.applyText(text7);
                     return;
                  }
               }

               String text8 = "Player §c" + text2 + "§7 isn't on the server.";
               this.applyText(text8);
            }
         }
      }
   }

   public void updateState2() {
      short shortValue = 25880;
      short shortValue2 = 28860;
      char character = '\ue61e';
      String text = "§7" + this.getString() + "§7 [§cadd/permaAdd/remove§7] [§2playername§7]";
      this.applyText(text);
   }

   public CompositeStringEntryAdapter() {
      super("friend");
   }
}
