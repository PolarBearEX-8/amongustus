package augustus.modules.fun;

import client.ClientGlobals;
import client.event.EventPacket;
import client.event.PacketListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.network.NetworkPlayerInfo;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.util.Packet;
import client.util.TabListBotFilter;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.network.play.server.S02PacketChat;

public class FriendFinder extends Module implements PacketListener, TickListener {
    public NumberSetting settingInviteDelay;
    public BooleanSetting settingHideFriendMessage;
    public TimeHelper timer;
    public ArrayList itemList3;

    public FriendFinder() {
        super("FriendFinder", "FreundeFinder", new Color(235, 201, 52), Category.FUN);
        this.settingInviteDelay = new NumberSetting(this, "InviteDelay", 1000.0, 0.0, 10000.0, 0);
        this.settingHideFriendMessage = new BooleanSetting(this, "HideFriendMessage", true);
        this.timer = new TimeHelper();
        this.itemList3 = new ArrayList();
    }

    @Override
    public void onEnable() {
        this.registerEvent(26);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        this.itemList3.clear();
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        if (this.settingHideFriendMessage.isEnabled()) {
            Packet packet2 = packet.getPacket();
            if (!(packet2 instanceof S02PacketChat)) {
                return;
            }

            String text = ((S02PacketChat)packet2).getChatComponent().getString();
            if (!text.contains("Friends") && !text.contains("friends") && !text.contains("Freunde")) {
                return;
            }

            packet.cancel();
        }
    }

    @Override
    public void onRunTick() {
        if (minecraft.getNetHandlerPlayClient().getEntryMap().values().size() > 1) {
            if (this.timer.hasReachedDouble(this.settingInviteDelay.getValue())) {
                Iterator iterator = minecraft.getNetHandlerPlayClient().getEntryMap().values().iterator();

                while (iterator.hasNext()) {
                    String name = ((NetworkPlayerInfo)iterator.next()).getGameProfile().getName();
                    if (TabListBotFilter.checkString2(name)
                        && !name.equals(minecraft.thePlayer.getText())
                        && !ClientGlobals.moduleManager.midClick.checkString2(name)
                        && !this.itemList3.contains(name)) {
                        minecraft.thePlayer.sendChatMessage("/friend add " + name);
                        this.itemList3.add(name);
                        this.timer.reset();
                        return;
                    }
                }
            }
        }
    }
}
