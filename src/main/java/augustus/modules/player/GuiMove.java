package augustus.modules.player;

import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventMoveInput;
import client.event.EventPacket;
import client.event.EventSprint;
import client.event.MoveEntityListener;
import client.event.MoveInputListener;
import client.event.MoveListener;
import client.event.PacketListener;
import client.event.SprintListener;
import client.event.UpdateStateListener;
import client.gui.InvMoveScreenType;
import client.module.Category;
import client.module.Module;
import client.setting.AutoSoupPerformRespawnType;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.KeyUtils;
import client.util.Packet;
import client.util.SimulatedPlayer;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.Locale;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.network.play.client.C16PacketClientStatus;

public class GuiMove extends Module implements MoveInputListener, MoveListener, UpdateStateListener, PacketListener, MoveEntityListener, SprintListener {
    public MultiSelectSetting settingGuis;
    public BooleanSetting settingNoInvPacket;
    public TimeHelper timer;
    public InvMoveScreenType invMoveScreenType;

    public GuiMove() {
        super("GuiMove", "BenutzeroberflächenBewegung", new Color(11, 143, 125), Category.PLAYER);
        this.settingGuis = new MultiSelectSetting(this, "Guis", InvMoveScreenType.values());
        this.timer = new TimeHelper();
        this.invMoveScreenType = null;
        for (InvMoveScreenType screen : InvMoveScreenType.values()) {
            String text = screen.getText().toLowerCase(Locale.ROOT);
            SettingGroup settingGroup2 = new SettingGroup(this, screen.getText()).getSettingGroup(screen::isSelected);
            InvMoveScreenType.getModeSetting2(
                screen,
                new ModeSetting(this, screen.getText() + "Mode", "Basic", new String[]{"Basic", "Resprint", "Intave"}, settingGroup2)
                    .getModeSetting("Mode")
            );
            InvMoveScreenType.getBooleanSetting5(
                screen,
                (BooleanSetting)new BooleanSetting(this, screen.getText() + "Sneak", false, settingGroup2).getSetting("Sneak in " + text)
            );
            InvMoveScreenType.getBooleanSetting7(
                screen,
                (BooleanSetting)new BooleanSetting(this, screen.getText() + "Jump", false, settingGroup2).getSetting("Jump in " + text)
            );
            if (screen == InvMoveScreenType.INVENTORY) {
                this.settingNoInvPacket = (BooleanSetting)new BooleanSetting(this, "NoInvPacket", false, settingGroup2)
                    .getSetting("No inventory open packet");
            }
            InvMoveScreenType.getBooleanSetting6(
                screen,
                (BooleanSetting)new BooleanSetting(this, screen.getText() + "Rotate", false, settingGroup2).getSetting("Rotate in " + text)
            );
            BooleanSetting sneakSetting = InvMoveScreenType.getBooleanSetting5(screen);
            InvMoveScreenType.getBooleanSetting3(
                screen,
                (BooleanSetting)new BooleanSetting(this, screen.getText() + "RotateOnlyAtClick", false, settingGroup2)
                    .getSetting("Rotate only while click in " + text)
                    .getSetting(sneakSetting::isEnabled)
            );
            InvMoveScreenType.getBooleanSetting4(
                screen,
                (BooleanSetting)new BooleanSetting(this, screen.getText() + "MoveOnlyOnGround", false, settingGroup2)
                    .getSetting("Move only on ground in " + text)
            );
            InvMoveScreenType.getBooleanSetting(
                screen,
                (BooleanSetting)new BooleanSetting(this, screen.getText() + "SneakMotionWhileMovement", false, settingGroup2)
                    .getSetting("Sneak motion while moving in " + text)
            );
            InvMoveScreenType.getModeSetting(
                screen,
                new ModeSetting(
                    this,
                    screen.getText() + "Sprint mode",
                    "None",
                    new String[]{"None", "Legit", "AllDirection", "AllDirectionMotion"},
                    settingGroup2
                ).getModeSetting("Sprint mode")
            );
            InvMoveScreenType.getBooleanSetting2(
                screen,
                (BooleanSetting)new BooleanSetting(this, screen.getText() + "Jump sprint", false, settingGroup2)
                    .getSetting("Sprint jump boost motion")
            );
            InvMoveScreenType.getNumberSetting2(
                screen,
                new NumberSetting(this, screen.getText() + "MotionMultiplierForward", 1.0, 0.0, 2.0, 2, settingGroup2)
                    .getNumberSetting("Motion multiplier forward in " + text)
            );
            InvMoveScreenType.getNumberSetting(
                screen,
                new NumberSetting(this, screen.getText() + "MotionMultiplierDiagonal", 1.0, 0.0, 2.0, 2, settingGroup2)
                    .getNumberSetting("Motion multiplier diagonal in " + text)
            );
        }
    }

    @Override
    public void onEnable() {
        this.registerEvent(36);
        this.registerEvent(21);
        this.registerEvent(43);
        this.registerEvent(26);
        this.registerEvent(20);
        this.registerEvent(33);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onMoveInput(EventMoveInput event) {
        if (this.checkCondition()) {
            boolean enabled = this.canExecute9();
            event.setActive(enabled);
        }
    }

    @Override
    public void onSprint(EventSprint event) {
        if (this.checkCondition()) {
            event.setActive2(true);
        }
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof C16PacketClientStatus
            && this.settingNoInvPacket != null
            && this.settingNoInvPacket.isEnabled()
            && ((C16PacketClientStatus)packet2).getAutoSoupPerformRespawnType() == AutoSoupPerformRespawnType.OPEN_INVENTORY_ACHIEVEMENT) {
            packet.cancel();
        }
    }

    @Override
    public void onPreMoveInput(EventMoveInput event) {
        if (this.checkCondition()) {
            boolean enabled = this.getString3().equals("AllDirectionMotion");
            event.setActive4(enabled);
        }
    }

    @Override
    public void onMove(EventMove event) {
        if (this.getString4().equals("Resprint")) {
            event.setState4(true);
        }
    }

    @Override
    public void onPostMoveEntity(EventMoveEntity event) {
        if (this.checkCondition()) {
            double offset = SimulatedPlayer.checkCondition(minecraft.thePlayer.rotationYaw)
                ? this.getDouble()
                : this.getDouble2();
            event.setCoordOffset(event.getCoordOffset() * offset);
        }
    }

    @Override
    public void onPostMoveInput(EventMoveInput event) {
        if (this.checkCondition()) {
            boolean enabled = this.getString3().equals("None");
            event.setState(enabled);
            boolean enabled2 = this.getString3().equals("AllDirection");
            event.setActive5(enabled2);
        }
    }

    @Override
    public void onPostUpdateState() {
        this.invMoveScreenType = this.getInvMoveScreenType();
        if (!this.checkCondition()) {
            this.timer.reset();
        } else {
            String text = this.getString4();
switch (text) {
            case "Intave":
                if (this.timer.hasReached(300L)) {
                                if (this.checkCondition10(minecraft.gameSettings.keyBindForward.getKeyCode())) {
                                    minecraft.gameSettings.keyBindForward.pressed = this.checkCondition(
                                        minecraft.gameSettings.keyBindForward.getKeyCode()
                                    );
                                }

                                if (this.checkCondition10(minecraft.gameSettings.keyBindBack.getKeyCode())) {
                                    minecraft.gameSettings.keyBindBack.pressed = this.checkCondition(
                                        minecraft.gameSettings.keyBindBack.getKeyCode()
                                    );
                                }

                                if (this.checkCondition10(minecraft.gameSettings.keyBindRight.getKeyCode())) {
                                    minecraft.gameSettings.keyBindRight.pressed = this.checkCondition(
                                        minecraft.gameSettings.keyBindRight.getKeyCode()
                                    );
                                }

                                if (this.checkCondition10(minecraft.gameSettings.keyBindLeft.getKeyCode())) {
                                    minecraft.gameSettings.keyBindLeft.pressed = this.checkCondition(
                                        minecraft.gameSettings.keyBindLeft.getKeyCode()
                                    );
                                }

                                if (this.checkCondition10(minecraft.gameSettings.keyBindSprint.getKeyCode())) {
                                    minecraft.gameSettings.keyBindSprint.pressed = this.checkCondition(
                                        minecraft.gameSettings.keyBindSprint.getKeyCode()
                                    );
                                }

                                if (this.checkCondition10(minecraft.gameSettings.keyBindJump.getKeyCode())) {
                                    if (this.canExecute5()) {
                                        minecraft.gameSettings.keyBindJump.pressed = this.checkCondition(
                                            minecraft.gameSettings.keyBindJump.getKeyCode()
                                        );
                                    } else {
                                        minecraft.gameSettings.keyBindJump.pressed = false;
                                    }
                                }

                                if (this.canExecute6() && this.checkCondition10(minecraft.gameSettings.keyBindSneak.getKeyCode())) {
                                    minecraft.gameSettings.keyBindSneak.pressed = this.checkCondition(
                                        minecraft.gameSettings.keyBindSneak.getKeyCode()
                                    );
                                }
                            }
                break;
            case "Basic":
                if (this.checkCondition10(minecraft.gameSettings.keyBindForward.getKeyCode())) {
                                minecraft.gameSettings.keyBindForward.pressed = this.checkCondition(
                                    minecraft.gameSettings.keyBindForward.getKeyCode()
                                );
                            }
                if (this.checkCondition10(minecraft.gameSettings.keyBindBack.getKeyCode())) {
                                minecraft.gameSettings.keyBindBack.pressed = this.checkCondition(
                                    minecraft.gameSettings.keyBindBack.getKeyCode()
                                );
                            }
                if (this.checkCondition10(minecraft.gameSettings.keyBindRight.getKeyCode())) {
                                minecraft.gameSettings.keyBindRight.pressed = this.checkCondition(
                                    minecraft.gameSettings.keyBindRight.getKeyCode()
                                );
                            }
                if (this.checkCondition10(minecraft.gameSettings.keyBindLeft.getKeyCode())) {
                                minecraft.gameSettings.keyBindLeft.pressed = this.checkCondition(
                                    minecraft.gameSettings.keyBindLeft.getKeyCode()
                                );
                            }
                if (this.checkCondition10(minecraft.gameSettings.keyBindSprint.getKeyCode())) {
                                minecraft.gameSettings.keyBindSprint.pressed = this.checkCondition(
                                    minecraft.gameSettings.keyBindSprint.getKeyCode()
                                );
                            }
                if (this.checkCondition10(minecraft.gameSettings.keyBindJump.getKeyCode())) {
                                if (this.canExecute5()) {
                                    minecraft.gameSettings.keyBindJump.pressed = this.checkCondition(
                                        minecraft.gameSettings.keyBindJump.getKeyCode()
                                    );
                                } else {
                                    minecraft.gameSettings.keyBindJump.pressed = false;
                                }
                            }
                if (!this.canExecute6()) {
                                return;
                            }
                if (this.checkCondition10(minecraft.gameSettings.keyBindSneak.getKeyCode())) {
                                minecraft.gameSettings.keyBindSneak.pressed = this.checkCondition(
                                    minecraft.gameSettings.keyBindSneak.getKeyCode()
                                );
                            }
        }
        }
    }

    private double getDouble() {
        return this.invMoveScreenType == null ? 1.0 : InvMoveScreenType.getNumberSetting2(this.invMoveScreenType).getValue();
    }

    private boolean checkCondition(int index) {
        try {
            return KeyUtils.checkCondition(index);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean canExecute5() {
        return this.invMoveScreenType != null && InvMoveScreenType.getBooleanSetting6(this.invMoveScreenType).isEnabled();
    }

    private boolean checkCondition10(int index) {
        return index <= 0 ? false : index < 256;
    }

    private double getDouble2() {
        return this.invMoveScreenType == null ? 1.0 : InvMoveScreenType.getNumberSetting(this.invMoveScreenType).getValue();
    }

    private String getString3() {
        return this.invMoveScreenType == null ? "" : InvMoveScreenType.getModeSetting2(this.invMoveScreenType).getSelected();
    }

    private InvMoveScreenType getInvMoveScreenType() {
        if (minecraft.currentScreen == null) {
            return null;
        }
        if (minecraft.currentScreen instanceof GuiInventory) {
            return InvMoveScreenType.INVENTORY;
        }
        if (minecraft.currentScreen instanceof GuiContainer) {
            return InvMoveScreenType.CONTAINER;
        }
        if (minecraft.currentScreen instanceof GuiGameOver) {
            return InvMoveScreenType.DEATH_SCREEN;
        }
        return InvMoveScreenType.OTHER_SCREENS;
    }

    private boolean checkCondition() {
        if (minecraft.currentScreen == null || this.invMoveScreenType == null || minecraft.thePlayer == null) {
            return false;
        }
        if (!minecraft.thePlayer.onGround && this.canExecute9()) {
            return false;
        }
        return this.invMoveScreenType.isSelected();
    }

    private String getString4() {
        return this.invMoveScreenType == null ? "" : InvMoveScreenType.getModeSetting(this.invMoveScreenType).getSelected();
    }

    private boolean canExecute6() {
        return this.invMoveScreenType != null && InvMoveScreenType.getBooleanSetting(this.invMoveScreenType).isEnabled();
    }

    private boolean checkCondition11() {
        return this.invMoveScreenType != null && InvMoveScreenType.getBooleanSetting2(this.invMoveScreenType).isEnabled();
    }

    public boolean canExecute7() {
        return this.canExecute8();
    }

    private boolean canExecute8() {
        return this.invMoveScreenType != null && InvMoveScreenType.getBooleanSetting5(this.invMoveScreenType).isEnabled();
    }

    private boolean checkCondition12() {
        return this.invMoveScreenType != null && InvMoveScreenType.getBooleanSetting3(this.invMoveScreenType).isEnabled();
    }

    private boolean canExecute9() {
        return this.invMoveScreenType != null && InvMoveScreenType.getBooleanSetting4(this.invMoveScreenType).isEnabled();
    }

    public boolean canExecute10() {
        return this.invMoveScreenType != null && InvMoveScreenType.getBooleanSetting7(this.invMoveScreenType).isEnabled();
    }
}
