package augustus.modules.misc;

import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.network.NetworkPlayerInfo;
import client.rotation.Rotation;
import client.setting.BooleanSetting;
import client.setting.MidClickFriendSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.util.AutoReportCache;
import client.util.BooleanSupplier;
import client.util.FriendListManager;
import client.util.FriendProfile;
import client.util.FriendRecord;
import client.util.KeyUtils;
import client.util.MovingObjectType;
import client.util.RayTraceUtils;
import java.awt.Color;
import java.util.HashSet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;

public class MidClick extends Module implements ClickListener, TickListener {
    public HashSet friendedPlayers;
    public HashSet entrySet2;
    public NumberSetting settingMidClickDistance;
    public BooleanSetting settingAutoUnfriend;
    public BooleanSetting settingPartyFriend;
    public BooleanSetting settingPartyFriendGomme;
    public BooleanSetting settingReverse;
    public BooleanSetting settingSimple;
    public MidClickFriendSetting settingFriends;
    public boolean enabled2;
    public boolean expanded2;
    public static Setting[] settingArray;

    public MidClick() {
        super("MidClick", "Mittelklick", new Color(76, 78, 76), Category.MISC);
        this.friendedPlayers = new HashSet();
        this.entrySet2 = new HashSet();
        this.settingMidClickDistance = new NumberSetting(this, "MidClickDistance", 4.5, 3.0, 10.0, 2);
        this.settingAutoUnfriend = new BooleanSetting(this, "AutoUnfriend", true);
        this.settingPartyFriend = new BooleanSetting(this, "PartyFriend", true);
        this.settingPartyFriendGomme = (BooleanSetting)new BooleanSetting(this, "PartyFriendGomme", true).getSetting(() -> this.settingPartyFriend.isEnabled());
        this.settingReverse = new BooleanSetting(this, "Reverse", false);
        this.settingSimple = new BooleanSetting(this, "Simple", false);
        this.settingFriends = new MidClickFriendSetting(this, "Friends");
        this.enabled2 = false;
        this.expanded2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(7);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        this.entrySet2.clear();
    }

    @Override
    public void onPreDisable() {
        this.entrySet2.clear();
    }

    @Override
    public void onClick(EventClick event) {
        if (minecraft.gameSettings.keyBindPickBlock.getPressTime() > 0) {
            Rotation rotation3 = ClientGlobals.rotationManager.getRotation3();
            double value = this.settingMidClickDistance.getValue();
            double value2 = this.settingMidClickDistance.getValue();
            MovingObjectPosition movingObjectPosition = RayTraceUtils.getMovingObjectPosition(1.0F, rotation3, value, value2, 0.0);
            if (movingObjectPosition == null) {
                return;
            }

            if (movingObjectPosition.typeOfHit != MovingObjectType.ENTITY) {
                return;
            }

            if (!(movingObjectPosition.entityHit instanceof EntityPlayer)) {
                return;
            }

            if (this.friendedPlayers.contains(movingObjectPosition.entityHit.getText())) {
                this.friendedPlayers.remove(movingObjectPosition.entityHit.getText());
                String text = movingObjectPosition.entityHit.getText();
                this.applyString6(text);
                return;
            }

            if (KeyUtils.checkCondition(minecraft.gameSettings.keyBindSneak.getKeyCode())) {
                String text2 = movingObjectPosition.entityHit.getText();
                this.applyString(text2);
            }

            this.friendedPlayers.add(movingObjectPosition.entityHit.getText());
        }
    }

    @Override
    public void onRunTick() {
        if (!this.expanded2) {
            this.friendedPlayers.addAll(this.settingFriends.getSet());
            this.expanded2 = true;
        }

        if (this.settingPartyFriend.isEnabled()) {
            for (NetworkPlayerInfo networkPlayerInfo : ((Iterable<NetworkPlayerInfo>) minecraft.thePlayer.netHandlerPlayClient.getCollection())) {
                String name = networkPlayerInfo.getGameProfile().getName();
                if (name.equals(minecraft.thePlayer.getGameProfile().getName())) {
                    String text;
                    if (networkPlayerInfo.getChatComponent() != null) {
                        IChatComponent chatComponent = networkPlayerInfo.getChatComponent();
                        text = chatComponent.getString();
                    } else {
                        text = ScorePlayerTeam.getText(networkPlayerInfo.getScorePlayerTeam(), name);
                    }

                    if (this.settingPartyFriendGomme.isEnabled() ? text.contains("§5Party§7") : text.contains("Party") || text.contains("party")) {
                        if (!this.entrySet2.contains(name)) {
                            this.friendedPlayers.add(name);
                            this.entrySet2.add(name);
                        }
                    } else if (this.entrySet2.contains(name)) {
                        this.entrySet2.remove(name);
                        this.friendedPlayers.remove(name);
                    }
                }
            }
        } else {
            for (Object object : this.entrySet2) {
                String text2 = (String)object;
                this.friendedPlayers.remove(text2);
            }

            this.entrySet2.clear();
        }
    }

    public boolean checkEntity(Entity entity) {
        return this.checkString2(entity.getText());
    }

    public void applyString(String text) {
        this.settingFriends.applyObject(text);
    }

    public static void setSettingArray(Setting[] settingArray2) {
        settingArray = settingArray2;
    }

    public void applyString6(String text) {
        this.settingFriends.checkObject2(text);
    }

    public boolean checkString(String text, boolean enabled) {
        boolean enabled3 = false;
        if (this.isToggled()) {
            enabled3 = this.settingReverse.isEnabled() != this.friendedPlayers.contains(text);
        }

        if (enabled) {
            return enabled3;
        } else if (!ClientGlobals.moduleManager.irc.isToggled()) {
            return enabled3;
        } else if (!ClientGlobals.moduleManager.irc.settingClientFriends.isEnabled()) {
            return enabled3;
        } else {
            FriendRecord extendedStringEntry = (FriendRecord)AutoReportCache.entryMap2.get(text);
            if (ClientGlobals.moduleManager.irc.settingYourFriendsOnly.isEnabled() && extendedStringEntry != null) {
                String text2 = extendedStringEntry.getText();
                FriendProfile secondaryStringEntry = (FriendProfile)FriendListManager.getEntryMap().get(text2);
                return secondaryStringEntry == null ? false : secondaryStringEntry.getIndex() > 0;
            } else {
                return extendedStringEntry == null ? enabled3 : true;
            }
        }
    }

    public static Setting[] getSettingArray() {
        return settingArray;
    }

    public boolean checkString2(String text) {
        return this.checkString(text, false);
    }
}
