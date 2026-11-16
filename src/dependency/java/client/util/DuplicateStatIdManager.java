package client.util;

import client.event.PreUpdateListener;
import client.setting.ClientSettingsBlackType;
import client.setting.ClientSettingsShowTextType;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class DuplicateStatIdManager {
   public final String labelText;
   public final IChatComponent iChatComponent;
   public boolean enabled;
   public final PreUpdateListener preUpdateListener4;
   public final HealthHandler healthHandler;
   public Class memberRef;
   public static NumberFormat numberFormat = NumberFormat.getIntegerInstance(Locale.US);
   public static PreUpdateListener preUpdateListener5 = new ListenerAdapter18();
   public static DecimalFormat decimalFormat = new DecimalFormat("########0.00");
   public static PreUpdateListener preUpdateListener = new ActiveClientManager();
   public static PreUpdateListener preUpdateListener2 = new SimpleClientManager();
   public static PreUpdateListener preUpdateListener3 = new LocalClientManager();

   public IChatComponent getIChatComponentAsIChatComponent() {
      IChatComponent iChatComponent2 = this.getIChatComponent();
      IChatComponent iChatComponent3 = new ChatComponentText("[").appendChatComponent(iChatComponent2).resolveIChatComponent("]");
      iChatComponent3.getIChat(iChatComponent2.getStyleHasParentManager());
      return iChatComponent3;
   }

   public HealthHandler getHealthHandler() {
      return this.healthHandler;
   }

   public DuplicateStatIdManager getDuplicateStatIdManagerAsDuplicateStatIdM() {
      if (StatCraftItemManager.map.containsKey(this.labelText)) {
         throw new RuntimeException(
            "Duplicate stat id: \""
               + ((DuplicateStatIdManager)StatCraftItemManager.map.get(this.labelText)).iChatComponent
               + "\" and \""
               + this.iChatComponent
               + "\" at id "
               + this.labelText
         );
      } else {
         StatCraftItemManager.list4.add(this);
         StatCraftItemManager.map.put(this.labelText, this);
         return this;
      }
   }

   public static DecimalFormat getDecimalFormat() {
      return decimalFormat;
   }

   public static NumberFormat getNumberFormat() {
      return numberFormat;
   }

   public String resolveText(int count) {
      return this.preUpdateListener4.resolveText(count);
   }

   public IChatComponent getIChatComponent() {
      IChatComponent iChatComponent2 = this.iChatComponent.getIChatComponent();
      iChatComponent2.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.GRAY);
      iChatComponent2.getStyleHasParentManager()
         .getStyleHasParentManager(new HoverEventActionManager(ClientSettingsShowTextType.SHOW_ACHIEVEMENT, new ChatComponentText(this.labelText)));
      return iChatComponent2;
   }

   public boolean isActive() {
      return false;
   }

   public DuplicateStatIdManager withMemberRef(Class clazz) {
      this.memberRef = clazz;
      return this;
   }

   public DuplicateStatIdManager getDuplicateStatIdManager() {
      this.enabled = true;
      return this;
   }

   public Class getMemberRef() {
      return this.memberRef;
   }

   public DuplicateStatIdManager(String text, IChatComponent iChatComponent2) {
      this(text, iChatComponent2, preUpdateListener5);
   }

   public DuplicateStatIdManager(String text, IChatComponent iChatComponent2, PreUpdateListener preUpdateListener6) {
      this.labelText = text;
      this.iChatComponent = iChatComponent2;
      this.preUpdateListener4 = preUpdateListener6;
      this.healthHandler = new AuxiliaryHealthHandlerAdapter(this);
      HealthHandler.map.put(this.healthHandler.getLabelText(), this.healthHandler);
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         DuplicateStatIdManager duplicateStatIdManager = (DuplicateStatIdManager)other;
         return this.labelText.equals(duplicateStatIdManager.labelText);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return "Stat{id="
         + this.labelText
         + ", nameId="
         + this.iChatComponent
         + ", awardLocallyOnly="
         + this.enabled
         + ", formatter="
         + this.preUpdateListener4
         + ", objectiveCriteria="
         + this.healthHandler
         + '}';
   }

   @Override
   public int hashCode() {
      return this.labelText.hashCode();
   }
}
