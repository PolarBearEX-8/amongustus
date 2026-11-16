package client.util;

import net.minecraft.util.IChatComponent;

public class IChatComponentEntry {
   public IChatComponent iChatComponent;
   public GameProfileArrayHolder gameProfileArrayHolder;
   public DampenerDeserializer dampenerDeserializer;
   public String labelText;

   public DampenerDeserializer getDampenerDeserializer() {
      return this.dampenerDeserializer;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public void setGameProfileArrayHolder(GameProfileArrayHolder gameProfileArrayHolder2) {
      this.gameProfileArrayHolder = gameProfileArrayHolder2;
   }

   public IChatComponent getIChatComponent() {
      return this.iChatComponent;
   }

   public void setDampenerDeserializer(DampenerDeserializer dampenerDeserializer2) {
      this.dampenerDeserializer = dampenerDeserializer2;
   }

   public void setIChatComponent(IChatComponent iChatComponent2) {
      this.iChatComponent = iChatComponent2;
   }

   public GameProfileArrayHolder getGameProfileArrayHolder() {
      return this.gameProfileArrayHolder;
   }

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }
}
