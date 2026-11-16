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
import java.awt.Color;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import net.minecraft.network.play.server.S02PacketChat;

public class PartySpammer extends Module implements PacketListener, TickListener {
    public NumberSetting settingInviteDelay;
    public BooleanSetting settingHidePartyMessage;
    public Collection collection;

    public PartySpammer() {
        super("PartySpammer", "FeierNachrichtenÜberschütter", new Color(204, 230, 122), Category.FUN);
        this.settingInviteDelay = new NumberSetting(this, "InviteDelay", 1000.0, 0.0, 4000.0, 0);
        this.settingHidePartyMessage = new BooleanSetting(this, "HidePartyMessage", true);
        this.collection = new LinkedList();
    }

    @Override
    public void onEnable() {
        this.registerEvent(26);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        this.collection.clear();
    }

    @Override
    public void onPreDisable() {
        if (minecraft.thePlayer != null) {
            this.collection = minecraft.getNetHandlerPlayClient().getEntryMap().values();
        }
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        if (this.settingHidePartyMessage.isEnabled()) {
            Packet packet2 = packet.getPacket();
            if (!(packet2 instanceof S02PacketChat)) {
                return;
            }

            S02PacketChat s02PacketChat = (S02PacketChat)packet2;
            if (!s02PacketChat.getChatComponent().getString().contains("Party") && !s02PacketChat.getChatComponent().getString().contains("party")) {
                return;
            }

            packet.cancel();
        }
    }

    @Override
    public void onRunTick() {
        if (minecraft.getNetHandlerPlayClient().getEntryMap().values().size() > 1) {
            if (minecraft.thePlayer.ticksExisted % (int)Math.max(1.0, this.settingInviteDelay.getValue() / 50.0 * 2.0) == 0) {
                if (this.collection.isEmpty()) {
                    this.collection = minecraft.getNetHandlerPlayClient().getEntryMap().values();
                }

                Iterator iterator = this.collection.iterator();

                while (true) {
                    if (!iterator.hasNext()) {
                        break;
                    }

                    NetworkPlayerInfo networkPlayerInfo = (NetworkPlayerInfo)iterator.next();
                    if (networkPlayerInfo != null && networkPlayerInfo.getGameProfile() != null) {
                        String name = networkPlayerInfo.getGameProfile().getName();
                        if (name != null
                            && name.length() >= 3
                            && !name.contains("§")
                            && !name.contains("#")
                            && !name.contains(" ")
                            && name.length() < 17) {
                            if (!name.equals(minecraft.thePlayer.getText()) && !ClientGlobals.moduleManager.midClick.checkString2(name)) {
                                minecraft.thePlayer.sendChatMessage("/party invite " + name);
                                iterator.remove();
                                break;
                            }

                            iterator.remove();
                        }
                    }
                }
            }

            if (minecraft.thePlayer.ticksExisted % (int)Math.max(1.0, this.settingInviteDelay.getValue() / 50.0) == 0) {
                minecraft.thePlayer.sendChatMessage("/party leave");
            }
        }
    }
}
