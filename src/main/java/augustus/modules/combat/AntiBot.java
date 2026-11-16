package augustus.modules.combat;

import augustus.anticheat.checks.AntiBotCheck;
import client.event.EventPacket;
import client.event.PacketListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.network.NetworkPlayerInfo;
import client.setting.BooleanSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.util.BooleanSupplier;
import client.util.Packet;
import client.util.RayTraceUtil;
import client.util.TabListBotFilter;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class AntiBot extends Module implements PacketListener, TickListener {
    public HashSet detectedBots;
    public HashSet entrySet2;
    public List itemList;
    public List itemList3;
    public List itemList4;
    public List itemList5;
    public List itemList6;
    public MultiSelectSetting settingMode;
    public NumberSetting settingTicksExisted;
    public BooleanSetting settingDistanceCheck;
    public NumberSetting settingCheckDistance;
    public BooleanSetting settingBlockHits;
    public BooleanSetting settingDebug;
    public static int[] intValueArray;

    public AntiBot() {
        super("AntiBot", "GegenFakespieler", Color.BLUE, Category.COMBAT);
        this.detectedBots = new HashSet();
        this.entrySet2 = new HashSet();
        this.itemList = new ArrayList();
        this.itemList3 = new ArrayList();
        this.itemList4 = new CopyOnWriteArrayList();
        this.itemList5 = new ArrayList();
        this.itemList6 = new ArrayList();
        AntiBotCheck[] antiBotCheckArray = AntiBotCheck.values();
        this.settingMode = new MultiSelectSetting(this, "Mode", antiBotCheckArray);
        this.settingTicksExisted = new NumberSetting(this, "TicksExisted", 20.0, 1.0, 100.0, 0).getNumberSetting(() -> AntiBotCheck.TICKS_EXISTED.isSelected());
        this.settingDistanceCheck = (BooleanSetting)new BooleanSetting(this, "DistanceCheck", true).getSetting(() -> isBot());
        this.settingCheckDistance = new NumberSetting(this, "CheckDistance", 10.0, 6.0, 40.0, 0).getNumberSetting(() -> this.isCheckDistanceVisible());
        this.settingBlockHits = new BooleanSetting(this, "BlockHits", true);
        this.settingDebug = new BooleanSetting(this, "Debug", false);
    }

    @Override
    public void onEnable() {
        this.registerEvent(26);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        this.updateState();
    }

    @Override
    public void onPreDisable() {
        this.updateState();
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof S07PacketRespawn) {
            this.updateState();
        }

        if (packet2 instanceof S29PacketSoundEffect && minecraft.theWorld != null) {
            S29PacketSoundEffect s29PacketSoundEffect = (S29PacketSoundEffect)packet2;
            if (!s29PacketSoundEffect.getText().contains("step")) {
                return;
            }

            EntityPlayer entityPlayer = minecraft.theWorld.getClosestPlayer(s29PacketSoundEffect.getDouble(), s29PacketSoundEffect.getDouble2(), s29PacketSoundEffect.getDouble3(), 1.0);
            if (entityPlayer != null && !this.itemList4.contains(entityPlayer)) {
                this.itemList4.add(entityPlayer);
            }
        }
    }

    @Override
    public void onRunTick() {
        boolean completed = false;
        int size = this.detectedBots.size();
        Iterator iterator = minecraft.theWorld.loadedEntityList.iterator();
        boolean enabled = false;
        if (iterator.hasNext()) {
            Entity entity = (Entity)iterator.next();
            Entity entity2 = entity;
            while (true) {
                if (entity instanceof EntityPlayer) {
                    if (entity2 != minecraft.thePlayer) {
                        if (minecraft.thePlayer.ticksExisted > 5) {
                            EntityPlayer player = (EntityPlayer)entity2;
                            if (this.checkEntityPlayer(player)) {
                                this.detectedBots.add(entity2);
                            } else if (this.entrySet2.contains(entity2)) {
                                this.detectedBots.remove(entity2);
                                this.entrySet2.remove(entity2);
                            }
                        }
                    }
                }
                if (!iterator.hasNext()) {
                    int size2 = this.detectedBots.size();
                    if (size2 <= size) {
                        { completed = true; break; }
                    }
                    if (!completed) {
                        enabled = this.settingDebug.isEnabled();
                        break;
                    }
                }
                if (!completed) {
                    entity = (Entity)iterator.next();
                    entity2 = entity;
                }
            }
        } else {
            if (this.detectedBots.size() <= size) {
                this.detectedBots.removeIf(object -> checkEntity3((Entity)object));
                this.entrySet2.removeIf(object2 -> checkEntity((Entity)object2));
                return;
            }

            enabled = this.settingDebug.isEnabled();
        }
        if (!completed) {
            if (enabled) {
                RayTraceUtil.applyString("Removed bot :)");
            }
        }


        this.detectedBots.removeIf(object3 -> checkEntity3((Entity)object3));
        this.entrySet2.removeIf(object4 -> checkEntity((Entity)object4));
    }

    private boolean checkEntityPlayer(EntityPlayer player) {
        NetworkPlayerInfo networkPlayerInfo = minecraft.getNetHandlerPlayClient().getNetworkPlayerInfo(player.getEntityUniqueID());
        if (networkPlayerInfo != null) {
            if (AntiBotCheck.ADVANCED.isSelected()) {
                if (minecraft.thePlayer.ticksExisted <= 110) {
                    this.applyEntityPlayer(player);
                } else if (player.getText2() == "") {
                    return true;
                }
            }

            if (AntiBotCheck.ILLEGAL_PITCH.isSelected() && Math.abs(player.rotationPitch) > 90.0F) {
                return true;
            } else if (AntiBotCheck.NULL_TEAM.isSelected() && player.getSecondaryClientManager() == null) {
                return true;
            } else if (AntiBotCheck.NEGATIVE_HEALTH.isSelected() && player.getFloat2() < 0.0F) {
                return true;
            } else {
                if (AntiBotCheck.INVALID_ENTITY_ID.isSelected()) {
                    if (player.getEntityId() < 0) {
                        return true;
                    }

                    if (player.getEntityId() > 999999999) {
                        return true;
                    }
                }

                if (AntiBotCheck.INTAVE.isSelected()) {
                    ScorePlayerTeam scorePlayerTeam = networkPlayerInfo.getScorePlayerTeam();
                    if (scorePlayerTeam != null && scorePlayerTeam.getText2().equals("")) {
                        return true;
                    }
                }

                if (AntiBotCheck.HYPIXEL.isSelected() && networkPlayerInfo.getIndexOffset3() != 1) {
                    return true;
                } else if (AntiBotCheck.ZERO_PING.isSelected() && player.ticksExisted > 5 && networkPlayerInfo.getIndexOffset3() == 0) {
                    return true;
                } else {
                    boolean enabled = AntiBotCheck.TAB_LIST.isSelected();
                    if (AntiBotCheck.INVALID_NAME.isSelected()) {
                        return !TabListBotFilter.checkString4(player.getText());
                    } else {
                        if (AntiBotCheck.SWING.isSelected()) {
                            if (player.state2 && !this.itemList3.contains(player)) {
                                this.itemList3.add(player);
                            }

                            if (!this.itemList3.contains(player) && this.checkEntityPlayer4(player)) {
                                this.applyEntityPlayer(player);
                                return true;
                            }
                        }

                        if (AntiBotCheck.NEVER_ON_GROUND.isSelected()) {
                            if (this.checkEntityPlayer2(player) && !this.itemList.contains(player)) {
                                this.itemList.add(player);
                            }

                            if (!this.itemList.contains(player) && this.checkEntityPlayer4(player)) {
                                this.applyEntityPlayer(player);
                                return true;
                            }
                        }

                        if (AntiBotCheck.ITEM.isSelected()) {
                            if (player.getItemStack() != null && !this.itemList5.contains(player)) {
                                this.itemList5.add(player);
                            }

                            if (!this.itemList5.contains(player) && this.checkEntityPlayer4(player)) {
                                this.applyEntityPlayer(player);
                                return true;
                            }
                        }

                        if (AntiBotCheck.ARMOR.isSelected()) {
                            if (this.checkEntityPlayer3(player) && !this.itemList6.contains(player)) {
                                this.itemList6.add(player);
                            }

                            if (!this.itemList6.contains(player) && this.checkEntityPlayer4(player)) {
                                this.applyEntityPlayer(player);
                                return true;
                            }
                        }

                        if (AntiBotCheck.SOUND.isSelected() && !this.itemList4.contains(player) && this.checkEntityPlayer4(player)) {
                            this.applyEntityPlayer(player);
                            return true;
                        } else if (AntiBotCheck.TICKS_EXISTED.isSelected() && (double)player.ticksExisted < this.settingTicksExisted.getValue()) {
                            this.applyEntityPlayer(player);
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            }
        } else {
            if (AntiBotCheck.ADVANCED.isSelected()) {
                if (minecraft.thePlayer.ticksExisted <= 110) {
                    this.applyEntityPlayer(player);
                } else if (player.getText2() == "") {
                    return true;
                }
            }

            if (AntiBotCheck.ILLEGAL_PITCH.isSelected() && Math.abs(player.rotationPitch) > 90.0F) {
                return true;
            } else if (AntiBotCheck.NULL_TEAM.isSelected() && player.getSecondaryClientManager() == null) {
                return true;
            } else if (AntiBotCheck.NEGATIVE_HEALTH.isSelected() && player.getFloat2() < 0.0F) {
                return true;
            } else {
                if (AntiBotCheck.INVALID_ENTITY_ID.isSelected()) {
                    if (player.getEntityId() < 0) {
                        return true;
                    }

                    if (player.getEntityId() > 999999999) {
                        return true;
                    }
                }

                boolean enabled2 = AntiBotCheck.INTAVE.isSelected();
                boolean enabled3 = AntiBotCheck.HYPIXEL.isSelected();
                if (AntiBotCheck.ZERO_PING.isSelected() && player.ticksExisted > 5) {
                }

                if (AntiBotCheck.TAB_LIST.isSelected() && player.ticksExisted > 20) {
                    this.applyEntityPlayer(player);
                    return true;
                } else if (AntiBotCheck.INVALID_NAME.isSelected()) {
                    return !TabListBotFilter.checkString4(player.getText());
                } else {
                    if (AntiBotCheck.SWING.isSelected()) {
                        if (player.state2 && !this.itemList3.contains(player)) {
                            this.itemList3.add(player);
                        }

                        if (!this.itemList3.contains(player) && this.checkEntityPlayer4(player)) {
                            this.applyEntityPlayer(player);
                            return true;
                        }
                    }

                    if (AntiBotCheck.NEVER_ON_GROUND.isSelected()) {
                        if (this.checkEntityPlayer2(player) && !this.itemList.contains(player)) {
                            this.itemList.add(player);
                        }

                        if (!this.itemList.contains(player) && this.checkEntityPlayer4(player)) {
                            this.applyEntityPlayer(player);
                            return true;
                        }
                    }

                    if (AntiBotCheck.ITEM.isSelected()) {
                        if (player.getItemStack() != null && !this.itemList5.contains(player)) {
                            this.itemList5.add(player);
                        }

                        if (!this.itemList5.contains(player) && this.checkEntityPlayer4(player)) {
                            this.applyEntityPlayer(player);
                            return true;
                        }
                    }

                    if (AntiBotCheck.ARMOR.isSelected()) {
                        if (this.checkEntityPlayer3(player) && !this.itemList6.contains(player)) {
                            this.itemList6.add(player);
                        }

                        if (!this.itemList6.contains(player) && this.checkEntityPlayer4(player)) {
                            this.applyEntityPlayer(player);
                            return true;
                        }
                    }

                    if (AntiBotCheck.SOUND.isSelected() && !this.itemList4.contains(player) && this.checkEntityPlayer4(player)) {
                        this.applyEntityPlayer(player);
                        return true;
                    } else if (AntiBotCheck.TICKS_EXISTED.isSelected() && (double)player.ticksExisted < this.settingTicksExisted.getValue()) {
                        this.applyEntityPlayer(player);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    public static int[] getIntValueArray() {
        return intValueArray;
    }

    private static boolean checkEntity(Entity entity) {
        return !minecraft.theWorld.loadedEntityList.contains(entity);
    }

    private void updateState() {
        this.detectedBots.clear();
        this.entrySet2.clear();
        this.itemList.clear();
        this.itemList5.clear();
        this.itemList6.clear();
        this.itemList4.clear();
        this.itemList3.clear();
    }

    public boolean checkEntity2(Entity entity) {
        return !this.isToggled() ? false : this.detectedBots.contains(entity);
    }

    private void applyEntityPlayer(EntityPlayer player) {
        this.entrySet2.add(player);
    }

    private boolean checkEntityPlayer2(EntityPlayer player) {
        return !player.worldObj.getCollidingBoundingBoxes(player, player.getBoundingBox2().expand(0.0, -0.01, 0.0)).isEmpty();
    }

    private boolean isCheckDistanceVisible() {
        return !this.settingDistanceCheck.isEnabled() ? false : this.settingDistanceCheck.isVisible();
    }

    private boolean checkEntityPlayer3(EntityPlayer player) {
        if (player.inventoryPlayer.armorInventory[3] != null) {
            return true;
        } else if (player.inventoryPlayer.armorInventory[2] != null) {
            return true;
        } else {
            return player.inventoryPlayer.armorInventory[1] != null ? true : player.inventoryPlayer.armorInventory[0] != null;
        }
    }

    private boolean checkEntityPlayer4(EntityPlayer player) {
        return !this.settingDistanceCheck.isEnabled()
        || (double)minecraft.thePlayer.getDistanceSqToEntity(player) < this.settingCheckDistance.getValue();
    }

    private static boolean isBot() {
        return AntiBotCheck.checkAntiBotCheck(AntiBotCheck.NEVER_ON_GROUND)
        || AntiBotCheck.SWING.isSelected()
        || AntiBotCheck.ITEM.isSelected()
        || AntiBotCheck.ARMOR.isSelected()
        || AntiBotCheck.SOUND.isSelected();
    }

    private static boolean checkEntity3(Entity entity) {
        return !minecraft.theWorld.loadedEntityList.contains(entity);
    }

    public static void setIntValueArray(int[] countArray) {
        intValueArray = countArray;
    }
}
