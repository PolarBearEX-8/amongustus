package augustus.modules.render;

import client.event.EventPacket;
import client.event.PacketListener;
import client.module.Category;
import client.module.Module;
import client.setting.AntiStrikeChatType;
import client.setting.BooleanSetting;
import client.setting.MultiSelectSetting;
import client.util.Packet;
import client.util.RayTraceUtil;
import client.util.TabListBotFilter;
import java.awt.Color;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.network.play.server.S45PacketTitle;

public class AntiStrike extends Module implements PacketListener {
    public MultiSelectSetting settingComponentsToHide = new MultiSelectSetting(this, "Components to hide", AntiStrikeChatType.values());
    public BooleanSetting settingReplaceServerName = new BooleanSetting(this, "Replace server name", true);
    public BooleanSetting settingRemoveColorCodes = new BooleanSetting(this, "Remove color codes", false);
    public String displayName3 = "";
    public String displayName2 = "";
    public String name2 = "";
    public String displayName1 = "";
    public static int[] intValueArray;

    public AntiStrike() {
        super("AntiStrike", "GegenStrike", new Color(82, 119, 222), Category.RENDER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(26);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        RayTraceUtil.applyString("Please rejoin the server for full Protection");
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof S45PacketTitle && AntiStrikeChatType.TITLE.isSelected()) {
            packet.cancel();
        }

        if (packet2 instanceof S02PacketChat && this.canExecute12()) {
            S02PacketChat s02PacketChat = (S02PacketChat)packet2;
            packet.cancel();
            if (s02PacketChat.getByteIndex() == 2) {
                packet.cancel();
            }
        }

        if (packet2 instanceof S33PacketUpdateSign) {
            if (AntiStrikeChatType.SIGNS.isSelected()) {
                packet.cancel();
            }
        }
    }

    public static void setIntValueArray(int[] countArray) {
        intValueArray = countArray;
    }

    public static int[] getIntValueArray() {
        return intValueArray;
    }

    public void applyString(String text) {
        String text2 = text;
        if (text != null) {
            String[] textArray = text.split(" ");
            if (textArray.length > 1) {
                text2 = textArray[0];
            }

            text2 = TabListBotFilter.getString2(text2);
            String[] textArray2 = text2.split("\\.");
            if (textArray2.length > 1) {
                this.displayName1 = TabListBotFilter.getText(textArray2);
            } else {
                this.displayName1 = "";
            }

            this.displayName2 = text2;
        }
    }

    public void applyString6(String text) {
        String text2 = text;
        if (text != null) {
            String[] textArray = text.split(" ");
            if (textArray.length > 1) {
                text2 = textArray[0];
            }

            text2 = TabListBotFilter.getString2(text2);
            String[] textArray2 = text2.split("\\.");
            if (textArray2.length > 1) {
                this.name2 = TabListBotFilter.getText(textArray2);
            } else {
                this.name2 = "";
            }

            this.displayName3 = text2;
        }
    }

    public String getText2() {
        return this.displayName2;
    }

    public boolean canExecute5() {
        return !AntiStrikeChatType.BOOKS.isSelected() ? false : this.isToggled();
    }

    public boolean canExecute6() {
        return !AntiStrikeChatType.checkAntiStrikeChatType(AntiStrikeChatType.ITEM_NAMES) ? false : this.isToggled();
    }

    public String getName2() {
        return this.name2;
    }

    public String getText3() {
        return this.displayName1;
    }

    public boolean canExecute7() {
        return !AntiStrikeChatType.checkAntiStrikeChatType(AntiStrikeChatType.TAB_LIST) ? false : this.isToggled();
    }

    public String getDisplayName2() {
        return this.displayName3;
    }

    public boolean canExecute8() {
        return !AntiStrikeChatType.MAPS.isSelected() ? false : this.isToggled();
    }

    public boolean canExecute9() {
        return !AntiStrikeChatType.checkAntiStrikeChatType(AntiStrikeChatType.NAME_TAGS) ? false : this.isToggled();
    }

    public boolean checkCondition() {
        return !AntiStrikeChatType.checkAntiStrikeChatType(AntiStrikeChatType.SCOREBOARD) ? false : this.isToggled();
    }

    public boolean canExecute10() {
        return !AntiStrikeChatType.CHEST_NAMES.isSelected() ? false : this.isToggled();
    }

    public boolean canExecute11() {
        return !AntiStrikeChatType.checkAntiStrikeChatType(AntiStrikeChatType.TOOL_TIPS) ? false : this.isToggled();
    }

    public boolean canExecute12() {
        return !AntiStrikeChatType.checkAntiStrikeChatType(AntiStrikeChatType.CHAT) ? false : this.isToggled();
    }
}
