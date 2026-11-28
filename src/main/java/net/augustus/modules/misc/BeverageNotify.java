package net.augustus.modules.misc;

import java.awt.Color;
import java.util.HashMap;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.ui.notifications.NotificationLevel;
import net.augustus.ui.notifications.NotificationManager;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.options.NotificationCategoryOption;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPotion;

public class BeverageNotify extends Module implements TickListener, LoadWorldListener {
   private final BooleanValue deadPlayers;
   private final DoubleValue notifyDelay;
   private final HashMap notifiedCooldowns;

   public BeverageNotify() {
       super("BeverageNotify", "GetränkeWarnung", new Color(245, 200, 66), Categorys.MISC);
      this.deadPlayers = (BooleanValue)(new BooleanValue(this, "DeadPlayers", true)).markVisualOnly();
      this.notifyDelay = (DoubleValue)(new DoubleValue(this, "NotifyDelay", 4000.0, 0.0, 10000.0, 0)).markVisualOnly();
      this.notifiedCooldowns = new HashMap();
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      this.notifiedCooldowns.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onEarlyTick() {
      mc.theWorld.playerEntities.stream().filter(obj -> this.shouldNotifyDrinking((EntityPlayer)obj)).forEach(obj2 -> this.notifyPlayerDrinking((EntityPlayer)obj2));
   }

   private boolean shouldNotifyDrinking(EntityPlayer player) {
      boolean flag = (!player.isDead || this.deadPlayers.getValue())
         && !(player instanceof EntityPlayerSP)
         && player.isUsingItem()
         && player.getCurrentEquippedItem() != null
         && player.getCurrentEquippedItem().getItem() instanceof ItemPotion;
      if (flag) {
         Long val = (Long)this.notifiedCooldowns.get(player.getName());
         if (val != null) {
            if (val > System.currentTimeMillis()) {
               return false;
            }

            this.notifiedCooldowns.remove(player.getName());
         }
      }

      return flag;
   }

   private void notifyPlayerDrinking(EntityPlayer player) {
      String str = "§e"
         + player.getName()
         + "§r is drinking "
         + (player.getCurrentEquippedItem() != null ? "\"" + player.getCurrentEquippedItem().getDisplayName() + "\"" : "")
         + "!";
      this.sendChatMessage(str);
      if (Augustus.getInstance().getModuleManager().notifications.isToggled() && NotificationCategoryOption.BEVERAGE_NOTIFICATION.isSelected()) {
         NotificationLevel info = NotificationLevel.INFO;
         NotificationManager.pushNotification("BeverageNotify", str, info);
      }

      this.notifiedCooldowns.put(player.getName(), (long)((double)System.currentTimeMillis() + this.notifyDelay.getValue()));
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.notifiedCooldowns.clear();
   }

}
