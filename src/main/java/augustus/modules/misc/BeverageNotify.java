package augustus.modules.misc;

import client.Client;
import client.event.EventWorldLoad;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.setting.BeverageNotifyInfoType;
import client.setting.BeverageNotifyModuleTogglesType;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.util.BeverageItemNotification;
import client.util.RayTraceUtil;
import java.awt.Color;
import java.util.HashMap;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPotion;

public class BeverageNotify extends Module implements TickListener, WorldLoadListener {
    public BooleanSetting settingDeadPlayers = new BooleanSetting(this, "DeadPlayers", true);
    public NumberSetting settingNotifyDelay = new NumberSetting(this, "NotifyDelay", 4000.0, 0.0, 10000.0, 0);
    public HashMap entryMap = new HashMap();

    public BeverageNotify() {
        super("BeverageNotify", "GetränkeWarnung", new Color(245, 200, 66), Category.MISC);
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
        this.entryMap.clear();
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.entryMap.clear();
    }

    @Override
    public void onPreTick() {
        minecraft.theWorld
            .itemList7
            .stream()
            .filter(object -> this.checkEntityPlayer((EntityPlayer)object))
            .forEach(object2 -> this.applyEntityPlayer((EntityPlayer)object2));
    }

    private void applyEntityPlayer(EntityPlayer player) {
        StringBuilder sb = new StringBuilder().append("§e").append(player.getText()).append("§r is drinking ");
        String text = player.getHeldItem() != null ? "\"" + player.getHeldItem().getString() + "\"" : "";
        String text2 = sb.append(text).append("!").toString();
        RayTraceUtil.applyString(text2);
        if (Client.getClient().getModuleManager().notifications.isToggled()
            && BeverageNotifyModuleTogglesType.BEVERAGE_NOTIFICATION.isSelected()) {
            BeverageNotifyInfoType beverageNotifyInfoTypeKind = BeverageNotifyInfoType.INFO;
            BeverageItemNotification.updateText("BeverageNotify", text2, beverageNotifyInfoTypeKind);
        }

        HashMap entryMap2 = this.entryMap;
        String text3 = player.getText();
        double currentTime = (double)System.currentTimeMillis();
        entryMap2.put(text3, (long)(currentTime + this.settingNotifyDelay.getValue()));
    }

    private boolean checkEntityPlayer(EntityPlayer player) {
        boolean enabled;
        if ((!player.isDead || this.settingDeadPlayers.isEnabled())
            && !(player instanceof EntityPlayerSP)
            && player.isUsingItem()
            && player.getHeldItem() != null
            && player.getHeldItem().getItem() instanceof ItemPotion) {
            enabled = true;
        } else {
            enabled = false;
        }

        if (enabled) {
            Long time = (Long)this.entryMap.get(player.getText());
            if (time != null) {
                if (time > System.currentTimeMillis()) {
                    return false;
                }

                this.entryMap.remove(player.getText());
            }
        }

        return enabled;
    }
}
