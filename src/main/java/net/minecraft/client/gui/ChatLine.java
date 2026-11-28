package net.minecraft.client.gui;

import net.minecraft.util.IChatComponent;

public class ChatLine {
   private final int updateCounterCreated;
   private final IChatComponent lineString;
   private final int chatLineID;

   public IChatComponent getChatComponent() {
      return this.lineString;
   }

   public int getUpdatedCounter() {
      return this.updateCounterCreated;
   }

   public int getChatLineID() {
      return this.chatLineID;
   }

   public ChatLine(int n, IChatComponent component, int n2) {
      this.lineString = component;
      this.updateCounterCreated = n;
      this.chatLineID = n2;
   }
}
