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
import net.minecraft.item.ItemEnderPearl;

public class EnderPearlNotify extends Module implements TickListener, WorldLoadListener {
    public BooleanSetting settingDeadPlayers = new BooleanSetting(this, "DeadPlayers", true);
    public NumberSetting settingNotifyDelay = new NumberSetting(this, "NotifyDelay", 4000.0, 0.0, 10000.0, 0);
    public HashMap entryMap = new HashMap();

    public EnderPearlNotify() {
        super("EnderPearlNotify", "PerlenWarnung", new Color(66, 224, 245), Category.MISC);
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

    private boolean checkEntityPlayer(EntityPlayer player) {
        boolean enabled;
        if ((!player.isDead || this.settingDeadPlayers.isEnabled())
            && !(player instanceof EntityPlayerSP)
            && player.getHeldItem() != null
            && player.getHeldItem().getItem() instanceof ItemEnderPearl) {
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

    private void applyEntityPlayer(EntityPlayer player) {
        String text = "§e" + player.getText() + "§r has Enderpearl in hand!";
        RayTraceUtil.applyString(text);
        if (Client.getClient().getModuleManager().notifications.isToggled()
            && BeverageNotifyModuleTogglesType.ENDER_PEARL_NOTIFICATION.isSelected()) {
            BeverageNotifyInfoType beverageNotifyInfoTypeKind = BeverageNotifyInfoType.INFO;
            BeverageItemNotification.updateText("EnderPearlNotify", text, beverageNotifyInfoTypeKind);
        }

        HashMap entryMap2 = this.entryMap;
        String text2 = player.getText();
        double currentTime = (double)System.currentTimeMillis();
        entryMap2.put(text2, (long)(currentTime + this.settingNotifyDelay.getValue()));
    }
}
