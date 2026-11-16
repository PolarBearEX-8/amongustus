package augustus.modules.combat;

import augustus.custom.RuntimeBridge;
import client.event.ClickListener;
import client.event.ClickTickListener;
import client.event.EventAutoSoupListener;
import client.event.EventClick;
import client.event.EventPacket;
import client.event.MotionListener;
import client.event.PacketListener;
import client.module.Category;
import client.module.Module;
import client.setting.ActionSetting;
import client.setting.AutoSoupPerformRespawnType;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.Setting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.InventoryUtil;
import client.util.Packet;
import client.util.SlotManager;
import client.util.SmoothDamper;
import client.util.TimeHelper;
import client.via.ViaLoadingBase;
import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S0DPacketCollectItem;

public class AutoSoup extends Module implements ClickListener, ClickTickListener, MotionListener, PacketListener, EventAutoSoupListener {
    public ActionSetting settingHealth;
    public RangeSetting settingTickDelayAfterClosingGui;
    public ActionSetting settingTickDelayBeforeSwitch;
    public ActionSetting settingTickDelayBeforeClick;
    public BooleanSetting settingDrop;
    public BooleanSetting settingInventoryDrop;
    public NumberSetting settingDropBowlsIfYouHaveMoreThan;
    public ActionSetting settingTickDelayDrop;
    public ActionSetting settingTickDelayAfter;
    public BooleanSetting settingSilent;
    public BooleanSetting settingRandom;
    public BooleanSetting settingStopClickingAtSouping;
    public SettingGroup settingDeprecated;
    public BooleanSetting settingOpenInv;
    public BooleanSetting settingAutoOpen;
    public RangeSetting settingAutoOpenDelay;
    public RangeSetting settingStartDelay;
    public BooleanSetting settingFill;
    public RangeSetting settingFillDelay;
    public BooleanSetting settingCraft;
    public NumberSetting settingReCraftIfSoupsLowerThan;
    public BooleanSetting settingCraftInstant;
    public RangeSetting settingCraftCPS;
    public BooleanSetting settingAutoClose;
    public RangeSetting settingAutoCloseDelay;
    public ArrayList itemList3;
    public boolean activeState9;
    public boolean activeState10;
    public boolean activeState8;
    public boolean activeState12;
    public int keyCode2;
    public int tickDelay1;
    public boolean expanded2;
    public int tickDelay5;
    public int tickDelay6;
    public int tickDelay3;
    public int tickDelay4;
    public int tickDelay;
    public int tickDelay7;
    public int tickDelay2;
    public float motionMultiplier;
    public boolean activeState11;
    public boolean activeState;
    public SmoothDamper smoothDamper;
    public TimeHelper timer;
    public TimeHelper timer2;
    public boolean enabled2;
    public TimeHelper timer3;
    public TimeHelper timer4;
    public boolean visible2;

    public AutoSoup() {
        super("AutoSoup", "AutoSuppe", new Color(19, 178, 110), Category.COMBAT);
        this.settingHealth = new ActionSetting(this, "Health", 11.0, 14.0, 1.0, 20.0, 0);
        this.settingTickDelayAfterClosingGui = new RangeSetting(this, "TickDelayAfterClosingGui", 2.0, 3.0, 1.0, 20.0, 0)
        .getRangeSetting2();
        this.settingTickDelayBeforeSwitch = new ActionSetting(this, "TickDelayBeforeSwitch", 1.0, 2.0, 0.0, 7.0, 0);
        this.settingTickDelayBeforeClick = new ActionSetting(this, "TickDelayBeforeClick", 1.0, 2.0, 0.0, 7.0, 0);
        this.settingDrop = new BooleanSetting(this, "Drop", true);
        this.settingInventoryDrop = (BooleanSetting)new BooleanSetting(this, "InventoryDrop", false).getSetting(() -> this.settingDrop.isEnabled());
        this.settingDropBowlsIfYouHaveMoreThan = new NumberSetting(this, "DropBowlsIfYouHaveMoreThan", 0.0, 0.0, 64.0, 0).getNumberSetting(() -> this.settingDrop.isEnabled());
        this.settingTickDelayDrop = new ActionSetting(this, "TickDelayDrop", 1.0, 2.0, 0.0, 7.0, 0).getActionSetting(() -> this.settingDrop.isEnabled());
        this.settingTickDelayAfter = new ActionSetting(this, "TickDelayAfter", 1.0, 2.0, 0.0, 7.0, 0);
        this.settingSilent = new BooleanSetting(this, "Silent", true);
        this.settingRandom = new BooleanSetting(this, "Random", true);
        this.settingStopClickingAtSouping = new BooleanSetting(this, "StopClickingAtSouping", false);
        this.settingDeprecated = new SettingGroup(this, "Deprecated");
        this.settingOpenInv = new BooleanSetting(this, "OpenInv", true, this.settingDeprecated);
        this.settingAutoOpen = new BooleanSetting(this, "AutoOpen", false, this.settingDeprecated);
        this.settingAutoOpenDelay = new RangeSetting(this, "AutoOpenDelay", 150.0, 250.0, 0.0, 1000.0, 0, this.settingDeprecated)
        .getRangeSetting2().getRangeSetting(15343, () -> this.settingAutoOpen.isEnabled());
        this.settingStartDelay = new RangeSetting(this, "StartDelay", 150.0, 200.0, 0.0, 1000.0, 0, this.settingDeprecated).getRangeSetting2();
        this.settingFill = new BooleanSetting(this, "Fill", true, this.settingDeprecated);
        this.settingFillDelay = new RangeSetting(this, "FillDelay", 50.0, 100.0, 0.0, 500.0, 0, this.settingDeprecated).getRangeSetting2().getRangeSetting(15343, () -> this.settingFill.isEnabled());
        this.settingCraft = new BooleanSetting(this, "Craft", true, this.settingDeprecated);
        this.settingReCraftIfSoupsLowerThan = new NumberSetting(this, "ReCraftIfSoupsLowerThan", 4.0, 0.0, 20.0, 0, this.settingDeprecated).getNumberSetting(() -> this.settingCraft.isEnabled());
        this.settingCraftInstant = (BooleanSetting)new BooleanSetting(this, "CraftInstant", false, this.settingDeprecated).getSetting(() -> this.settingCraft.isEnabled());
        this.settingCraftCPS = new RangeSetting(this, "CraftCPS", 13.0, 16.0, 0.0, 20.0, 0, this.settingDeprecated).getRangeSetting2().getRangeSetting(15343, () -> this.isCraftCPSVisible());
        this.settingAutoClose = (BooleanSetting)new BooleanSetting(this, "AutoClose", true, this.settingDeprecated).getSetting(() -> this.settingFill.isEnabled());
        this.settingAutoCloseDelay = new RangeSetting(this, "AutoCloseDelay", 150.0, 250.0, 0.0, 1000.0, 0, this.settingDeprecated)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isAutoCloseDelayVisible());
        this.itemList3 = new ArrayList();
        this.activeState9 = false;
        this.activeState10 = false;
        this.activeState8 = true;
        this.activeState12 = true;
        this.expanded2 = false;
        this.tickDelay5 = 0;
        this.tickDelay6 = 0;
        this.tickDelay3 = 1;
        this.tickDelay4 = 1;
        this.tickDelay = 1;
        this.tickDelay7 = 1;
        this.tickDelay2 = 13;
        this.motionMultiplier = 0.0F;
        this.activeState11 = false;
        this.activeState = false;
        this.smoothDamper = new SmoothDamper(20, 0.0);
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        this.enabled2 = false;
        this.timer3 = new TimeHelper();
        this.timer4 = new TimeHelper();
        this.visible2 = true;
    }

    @Override
    public void onEnable() {
        this.registerEvent(7, 20);
        this.registerEvent(52, 3);
        this.registerEvent(34);
        this.registerEvent(26);
        this.registerEvent(9);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.settingFillDelay.recalculate();
        this.keyCode2 = -1;
        this.eatSoup2();
        this.activeState8 = true;
        this.activeState11 = false;
        this.enabled2 = false;
        this.settingTickDelayAfterClosingGui.recalculate();
    }

    @Override
    public void onClick(EventClick event) {
        if (!minecraft.playerController.processAction2()) {
            boolean enabled;
            if (event.isState3() || event.isState2() || !SlotManager.checkModule(this) && SlotManager.isChangingSlot()) {
                enabled = true;
            } else {
                enabled = false;
            }

            this.activeState = enabled;
            if (SlotManager.checkModule(this) && this.settingStopClickingAtSouping.isEnabled()) {
                event.setState2(true);
            }

            if (minecraft.isIntegratedServerRunning() && !this.activeState) {
                boolean enabled3 = false;
                if (this.keyCode2 > -1) {
                    if (this.tickDelay5 > this.tickDelay4 && !minecraft.thePlayer.isUsingItem() && !this.activeState9) {
                        if (this.settingStopClickingAtSouping.isEnabled()) {
                            event.updateState();
                            event.setState1(false);
                            event.setState2(false);
                            event.setState3(true);
                        }

                        event.updateInt2(1);
                        event.setRunnable2(() -> this.eatSoup5());
                        enabled3 = true;
                        this.activeState9 = true;
                    }

                    if (!enabled3) {
                        int index = this.shouldEatSoup3() ? this.tickDelay : 0;
                        if (index == 0
                        && this.tickDelay5 > this.tickDelay4 + index
                        && this.shouldEatSoup3()
                        && this.activeState9
                        && !this.activeState10) {
                            if (this.settingInventoryDrop.isEnabled()) {
                                int index2 = this.keyCode2 + 36;
                                minecraft.playerController.windowClick(minecraft.thePlayer.container.indexOffset2, index2, 1, 4, minecraft.thePlayer);
                            } else {
                                minecraft.thePlayer.getEntityItem(true);
                            }

                            this.activeState10 = true;
                        }

                        if (this.tickDelay5 > this.tickDelay4 + index + this.tickDelay7
                        && this.activeState9
                        && (this.activeState10 || !this.shouldEatSoup3())) {
                            this.eatSoup2();
                        }

                        this.tickDelay5++;
                    }
                }

                if (!enabled3) {
                    this.tickDelay5++;
                }
            }
        }
    }

    @Override
    public boolean checkCondition() {
        if (minecraft.playerController.processAction2()) {
            return false;
        } else {
            if (minecraft.isIntegratedServerRunning()) {
                this.timer2.reset();
                this.enabled2 = false;
                this.visible2 = true;
            }

            if (minecraft.currentScreen != null && minecraft.guiScreen == null) {
                this.settingTickDelayAfterClosingGui.recalculate();
            }

            if (!RuntimeBridge.canClickInventory() && this.settingOpenInv.isEnabled()) {
                return false;
            } else {
                if (!(minecraft.currentScreen instanceof GuiInventory)) {
                    if (this.settingOpenInv.isEnabled()) {
                        return false;
                    }

                    if (!minecraft.isIntegratedServerRunning()) {
                        return false;
                    }

                    if (!this.settingFill.isEnabled()) {
                        return false;
                    }
                }

                if (!this.settingFill.isEnabled() && !this.settingCraft.isEnabled()) {
                    return false;
                } else if (!this.timer2.hasReached((long)this.settingStartDelay.getIndexOffset2())) {
                    return true;
                } else {
                    this.settingStartDelay.recalculate();
                    if (!this.checkCondition3()) {
                        return true;
                    } else if (!this.shouldEatSoup2()) {
                        return true;
                    } else if (this.settingFill.isEnabled() && this.settingAutoClose.isEnabled() && minecraft.currentScreen != null && this.enabled2) {
                        if (this.visible2) {
                            this.timer4.reset();
                            this.visible2 = false;
                            this.settingAutoCloseDelay.recalculate();
                        }

                        if (this.timer4.hasReached((long)this.settingAutoCloseDelay.getIndexOffset2())) {
                            minecraft.thePlayer.updateState5();
                            this.timer4.reset();
                            this.visible2 = true;
                        }

                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof S07PacketRespawn) {
            this.activeState11 = true;
            this.eatSoup2();
        }

        if (packet2 instanceof S0DPacketCollectItem) {
            this.activeState11 = true;
        }
    }

    @Override
    public void onPostMotion() {
        if (!minecraft.playerController.processAction2()) {
            if (this.activeState11 && minecraft.thePlayer != null && minecraft.isIntegratedServerRunning() && minecraft.thePlayer.ticksExisted > 50) {
                minecraft.thePlayer.getFloat2();
            }

            AutoSoup autoSoup;
            if (this.settingAutoOpen.isEnabled() && minecraft.isIntegratedServerRunning()) {
                boolean enabled5 = false;
                int index = 36;
                if (36 < minecraft.thePlayer.container.itemList2.size()) {
                    ItemStack itemStack = minecraft.thePlayer.container.getSlot(index).getStack();

                    while (true) {
                        if (itemStack != null && itemStack.getItem().equals(Items.mushroomStew)) {
                            enabled5 = true;
                            break;
                        }

                        index++;
                        if (index >= minecraft.thePlayer.container.itemList2.size()) {
                            break;
                        }

                        itemStack = minecraft.thePlayer.container.getSlot(index).getStack();
                    }
                }

                int index2 = 0;
                int index3 = 0;
                int index4 = 0;
                int index5 = 0;
                int index6 = 0;

                while (true) {
                    if (index6 >= minecraft.thePlayer.container.itemList2.size()) {
                        if (!enabled5 && (index5 > 0 || index4 > 0 && index3 > 0 && index2 > 0)) {
                            TimeHelper timer32 = this.timer3;
                            long time2 = (long)this.settingAutoOpenDelay.getIndexOffset2();
                            if (timer32.hasReached(time2)) {
                                ViaLoadingBase.processAction(6105, 60385, (char)0);
                                GuiInventory advancedFloatEntry = new GuiInventory(minecraft.thePlayer);
                                minecraft.displayGuiScreen(advancedFloatEntry);
                                this.timer3.reset();
                                this.settingAutoOpenDelay.recalculate();
                            }
                        } else {
                            this.timer3.reset();
                        }

                        this.keyCode2 = this.getInt2();
                        if (this.keyCode2 <= -1) {
                            if (this.expanded2) {
                                this.expanded2 = false;
                                int tickDelay12 = this.tickDelay1;
                                boolean enabled = this.settingSilent.isEnabled();
                                SlotManager.applyModule(this, 5, tickDelay12, enabled);
                            }

                            if (SlotManager.indexOffset < 0) {
                                return;
                            }

                            if (SlotManager.indexOffset < 9) {
                                this.tickDelay1 = SlotManager.indexOffset;
                            }

                            return;
                        }

                        this.expanded2 = true;
                        autoSoup = this;
                        break;
                    }

                    ItemStack itemStack2 = minecraft.thePlayer.container.getSlot(index6).getStack();
                    if (itemStack2 != null) {
                        Item item = itemStack2.getItem();
                        if (item.equals(Items.bowl)) {
                            index2 += itemStack2.animationsToGo;
                        } else if (item.equals(Item.processAction(40))) {
                            index3 += itemStack2.animationsToGo;
                        } else if (item.equals(Item.processAction(39))) {
                            index4 += itemStack2.animationsToGo;
                        } else if (item.equals(Items.mushroomStew)) {
                            index5++;
                        }
                    }

                    index6++;
                }
            } else {
                this.timer3.reset();
                this.keyCode2 = this.getInt2();
                if (this.keyCode2 <= -1) {
                    boolean expanded22 = this.expanded2;
                    if (expanded22) {
                        this.expanded2 = false;
                        int tickDelay13 = this.tickDelay1;
                        boolean enabled3 = this.settingSilent.isEnabled();
                        SlotManager.applyModule(this, 5, tickDelay13, enabled3);
                    }

                    if (SlotManager.indexOffset < 0) {
                        return;
                    }

                    if (SlotManager.indexOffset < 9) {
                        this.tickDelay1 = SlotManager.indexOffset;
                    }

                    return;
                }

                this.expanded2 = true;
                autoSoup = this;
            }

            int keyCode22 = this.keyCode2;
            boolean enabled4 = this.settingSilent.isEnabled();
            SlotManager.applyModule(autoSoup, 5, keyCode22, enabled4);
        }
    }

    @Override
    public void eatSoup() {
        if (!minecraft.playerController.processAction2()) {
            if (minecraft.isIntegratedServerRunning()) {
                if (this.keyCode2 > -1) {
                    if (!this.activeState) {
                        int index = this.shouldEatSoup3() ? this.tickDelay : 0;
                        if (index > 0) {
                            if (this.tickDelay5 > this.tickDelay4 + index) {
                                if (this.shouldEatSoup3()) {
                                    if (this.activeState9) {
                                        if (!this.activeState10) {
                                            if (this.settingInventoryDrop.isEnabled()) {
                                                int index2 = this.keyCode2 + 36;
                                                minecraft.playerController.windowClick(minecraft.thePlayer.container.indexOffset2, index2, 1, 4, minecraft.thePlayer);
                                            } else {
                                                minecraft.thePlayer.getEntityItem(true);
                                            }

                                            this.activeState10 = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void eatSoup2() {
        this.tickDelay2 = SettingContainer.settingProvider
        .getInt(this.settingHealth.getDouble3(), this.settingHealth.getDouble6());
        this.tickDelay4 = SettingContainer.settingProvider
        .getInt(
        this.settingTickDelayBeforeClick.getDouble3(), this.settingTickDelayBeforeClick.getDouble6()
        );
        this.tickDelay7 = SettingContainer.settingProvider
        .getInt(this.settingTickDelayAfter.getDouble3(), this.settingTickDelayAfter.getDouble6());
        this.tickDelay = SettingContainer.settingProvider
        .getInt(this.settingTickDelayDrop.getDouble3(), this.settingTickDelayDrop.getDouble6());
        this.tickDelay3 = SettingContainer.settingProvider
        .getInt(
        this.settingTickDelayBeforeSwitch.getDouble3(), this.settingTickDelayBeforeSwitch.getDouble6()
        );
        this.activeState9 = false;
        this.activeState10 = false;
        this.itemList3.clear();
        this.activeState8 = true;
        this.activeState12 = true;
        this.keyCode2 = -1;
    }

    private void eatSoup5() {
        minecraft.rightClickMouse();
        int index = (this.shouldEatSoup3() ? 1 : 0);
        if (index != 0) {
            index = this.tickDelay;
        }

        if (index == 0) {
            int index2 = this.tickDelay4 + index;
            if (this.tickDelay5 > index2 && this.shouldEatSoup3() && this.activeState9 && !this.activeState10) {
                if (this.settingInventoryDrop.isEnabled()) {
                    minecraft.playerController.windowClick(minecraft.thePlayer.container.indexOffset2, this.keyCode2 + 36, 1, 4, minecraft.thePlayer);
                } else {
                    minecraft.thePlayer.getEntityItem(true);
                }

                this.activeState10 = true;
            }
        }

        int tickDelay52 = this.tickDelay5;
        int index3 = this.tickDelay4 + index + this.tickDelay7;
        if (tickDelay52 > index3 && this.activeState9 && (this.activeState10 || !this.shouldEatSoup3())) {
            this.eatSoup2();
        }

        this.tickDelay5++;
    }

    private void processAction2(int index, int index2) {
        this.eatSoup4();
        this.timer.reset();
        this.settingFillDelay.recalculate();
        minecraft.playerController.windowClick(minecraft.thePlayer.container.indexOffset2, index, index2 - 36, 2, minecraft.thePlayer);
        this.eatSoup3();
        this.enabled2 = true;
    }

    private void processAction(int index) {
        this.eatSoup4();
        if (minecraft.currentScreen instanceof GuiInventory) {
            try {
                Slot slot = minecraft.thePlayer.container.getSlot(index);
                GuiInventory advancedFloatEntry = (GuiInventory)minecraft.currentScreen;
                advancedFloatEntry.state2 = true;
                advancedFloatEntry.processAction(slot.indexOffset + 2 + advancedFloatEntry.indexOffset1, slot.indexOffset2 + 2 + advancedFloatEntry.indexOffset2, 0);
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        } else {
            minecraft.playerController.windowClick(minecraft.thePlayer.container.indexOffset2, index, 0, 1, minecraft.thePlayer);
        }

        this.enabled2 = true;
        this.timer.reset();
        this.settingFillDelay.recalculate();
        this.eatSoup3();
    }

    private void applyContainer(Container container, int index, int index2) {
        minecraft.playerController.windowClick(container.indexOffset2, index, index2, 0, minecraft.thePlayer);
        this.timer.reset();
        this.enabled2 = true;
    }

    private void eatSoup3() {
        if (minecraft.thePlayer.getGuiScreen() != null) {
            if (this.settingOpenInv.isEnabled()) {
                return;
            }

            if (!minecraft.isIntegratedServerRunning()) {
                return;
            }

            minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C0DPacketCloseWindow(minecraft.thePlayer.container.indexOffset2));
            minecraft.thePlayer.setGuiScreen(null);
        }
    }

    private int[] getIndexOffsetArray(Container container) {
        int[] countArray = new int[5];
        int index = -1;

        while (true) {
            ItemStack itemStack;
            if (index < container.itemList2.size()) {
                itemStack = index == -1 ? minecraft.thePlayer.inventoryPlayer.getItemStack() : container.getSlot(index).getStack();
            } else {
                itemStack = container.getSlot(index).getStack();
            }

            if (itemStack == null) {
                if (index > 8) {
                    countArray[0]++;
                    continue;
                }
            } else {
                Item item = itemStack.getItem();
                if (item.equals(Items.bowl)) {
                    countArray[1] += itemStack.animationsToGo;
                    continue;
                }

                if (item.equals(Item.processAction(40)) && index != 0) {
                    countArray[4]++;
                }
            }

            index++;
        }
    }

    private int getInt2() {
        if (minecraft.thePlayer.getFloat2() != this.motionMultiplier) {
            this.activeState12 = true;
            this.tickDelay6 = minecraft.thePlayer.ticksExisted;
        }

        this.motionMultiplier = minecraft.thePlayer.getFloat2();
        boolean enabled = minecraft.currentScreen != null;
        if (!enabled && this.itemList3.isEmpty()) {
            enabled = this.activeState11;
        }

        if (enabled) {
            this.activeState11 = false;
            this.eatSoup2();
            this.keyCode2 = -1;
        }

        boolean integratedServerRunning = minecraft.isIntegratedServerRunning();
        if (integratedServerRunning) {
            int int3 = minecraft.getInt3();
            int indexOffset2 = this.settingTickDelayAfterClosingGui.getIndexOffset2();
            integratedServerRunning = int3 >= indexOffset2;
        }

        if (integratedServerRunning) {
            integratedServerRunning = minecraft.thePlayer.getFloat2() < (float)this.tickDelay2;
        }

        if (integratedServerRunning) {
            integratedServerRunning = this.activeState8;
        }

        if (integratedServerRunning) {
            integratedServerRunning = !this.activeState9;
        }

        if (integratedServerRunning) {
            integratedServerRunning = this.checkCondition2();
        }

        if (integratedServerRunning) {
            this.activeState12 = false;
            this.itemList3.clear();
            int index = 36;

            while (true) {
                Container container = minecraft.thePlayer.container;
                if (index >= container.itemList2.size()) {
                    if (this.settingRandom.isEnabled()) {
                        Collections.shuffle(this.itemList3);
                    }

                    this.tickDelay5 = 0;
                    if (!this.itemList3.isEmpty()) {
                        this.activeState8 = false;
                    }
                    break;
                }

                ItemStack itemStack = minecraft.thePlayer.container.getSlot(index).getStack();
                if (itemStack != null && itemStack.getItem().equals(Items.mushroomStew)) {
                    this.itemList3.add(index - 36);
                }

                index++;
            }
        }

        return this.itemList3.isEmpty() ? -1 : (Integer)this.itemList3.get(0);
    }

    private boolean shouldEatSoup() {
        if (this.settingCraftInstant.isEnabled()) {
            return false;
        } else {
            boolean enabled = this.smoothDamper.checkCondition(this.settingCraftCPS.getIndexOffset2());
            if (enabled) {
                this.settingCraftCPS.recalculate();
            }

            return !enabled;
        }
    }

    private void eatSoup4() {
        if (!this.settingOpenInv.isEnabled()) {
            if (minecraft.thePlayer.getGuiScreen() != null) {
                return;
            }

            if (!minecraft.isIntegratedServerRunning()) {
                return;
            }

            minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C16PacketClientStatus(AutoSoupPerformRespawnType.OPEN_INVENTORY_ACHIEVEMENT));
            minecraft.thePlayer.setGuiScreen(new GuiInventory(minecraft.thePlayer));
        }
    }

    private boolean checkCondition2() {
        return minecraft.thePlayer.ticksExisted - this.tickDelay6 < this.tickDelay3 ? false : this.activeState12;
    }

    private boolean shouldEatSoup2() {
        if (!this.settingFill.isEnabled()) {
            return true;
        } else {
            TimeHelper timer5 = this.timer;
            long time2 = (long)this.settingFillDelay.getIndexOffset2();
            if (!timer5.hasReached(time2)) {
                return false;
            } else {
                int index2 = 9;

                outerLoop:
                while (index2 < 36) {
                    ItemStack itemStack = minecraft.thePlayer.container.getSlot(index2).getStack();
                    if (itemStack == null) {
                    } else {
                        if (!itemStack.getItem().equals(Items.bowl)) {
                            index2++;
                            continue;
                        }

                        if (itemStack.animationsToGo >= itemStack.processAction5()) {
                            index2++;
                            continue;
                        }

                    }

                    int index3 = 36;
                    if (index3 < minecraft.thePlayer.container.itemList2.size()) {
                        for (ItemStack i = minecraft.thePlayer.container.getSlot(index3).getStack();
                        i == null || !i.getItem().equals(Items.bowl);
                        i = minecraft.thePlayer.container.getSlot(index3).getStack()
                        ) {
                            index3++;
                            if (index3 >= minecraft.thePlayer.container.itemList2.size()) {
                                break outerLoop;
                            }
                        }

                        this.processAction(index3);
                        int index = Double.compare(this.settingFillDelay.getDouble9(), 0.0);
                        if (index != 0) {
                            return false;
                        }

                        if (this.settingFillDelay.getDouble2() != 0.0) {
                            return false;
                        }
                    }
                    break;
                }

                int index4 = 0;
                int index5 = 36;

                while (true) {
                    if (index5 >= minecraft.thePlayer.container.itemList2.size()) {
                        int index6 = 0;
                        if (index4 > 0) {
                            do {
                                ItemStack itemStack2 = minecraft.thePlayer.container.getSlot(index6).getStack();
                                if (itemStack2 != null) {
                                    if (itemStack2.getItem().equals(Items.mushroomStew)) {
                                        this.processAction(index6);
                                        index4--;
                                        if (this.settingFillDelay.getDouble9() != 0.0) {
                                            return false;
                                        }

                                        if (this.settingFillDelay.getDouble2() != 0.0) {
                                            return false;
                                        }

                                        index6++;
                                    } else {
                                        index6++;
                                    }
                                } else {
                                    index6++;
                                }

                                if (index6 >= 36) {
                                    return true;
                                }
                            } while (index4 > 0);

                            return true;
                        }

                        return true;
                    }

                    if (minecraft.thePlayer.container.getSlot(index5).getStack() == null) {
                        index4++;
                    }

                    index5++;
                }
            }
        }
    }

    private boolean isCraftCPSVisible() {
        return !this.settingCraft.isEnabled() ? false : !this.settingCraftInstant.isEnabled();
    }

    private boolean checkCondition3() {
        if (minecraft.currentScreen instanceof GuiInventory && this.settingCraft.isEnabled()) {
            Container container = minecraft.thePlayer.container;
            int[] indexOffsetArray = this.getIndexOffsetArray(container);
            if (indexOffsetArray[1] != 0 && indexOffsetArray[2] != 0 && indexOffsetArray[3] != 0) {
                int index3 = indexOffsetArray[1];
                int index4 = indexOffsetArray[2];
                int index5 = indexOffsetArray[3];
                int index6 = Math.min(index4, index5);
                int index7 = Math.min(index3, index6);
                int index8 = indexOffsetArray[0];
                if (index7 <= 0) {
                    return true;
                } else {
                    byte state;
                    if (index7 > index8) {
                        state = 1;
                    } else {
                        state = 0;
                    }

                    Item[] itemArray = new Item[3];
                    Item bowl = Items.bowl;
                    itemArray[0] = bowl;
                    Item item = Item.processAction(40);
                    itemArray[1] = item;
                    Item item2 = Item.processAction(39);
                    itemArray[2] = item2;
                    boolean[] conditionArray = new boolean[]{false, false, false};
                    int index9 = index8;
                    boolean enabled = false;
                    int index10 = 1;

                    while (true) {
                        int size = minecraft.thePlayer.container.itemList2.size();
                        if (index10 >= size) {
                            boolean enabled3;
                            if (!enabled) {
                                enabled3 = true;
                            } else {
                                enabled3 = false;
                            }

                            return enabled3 & true;
                        }

                        int index11 = 0;
                        int index12 = 0;
                        boolean completed4 = false;
                        boolean enabled4 = false;
                        int index13 = 0;
                        boolean completed3 = false;
                        if (index10 == 1) {
                            boolean[] conditionArray2 = new boolean[]{false, false, false};
                            Container container2 = minecraft.thePlayer.container;
                            int[] indexOffsetArray2 = this.getIndexOffsetArray(container2);
                            int index14 = indexOffsetArray2[0];
                            ItemStack itemStack = minecraft.thePlayer.container.getSlot(index10).getStack();
                            enabled4 = enabled;
                            int index15 = index10;
                            index12 = index14;
                            ItemStack itemStack2 = itemStack;
                            if (itemStack == null) {
                                index13 = index10;
                                conditionArray = conditionArray2;
                                completed3 = true;
                            }
                            if (!completed3) {
                                Item item3 = itemStack.getItem();
                                if (index10 < 5) {
                                    int index16 = 0;

                                    while (true) {
                                        int length = Array.getLength(itemArray);
                                        if (index16 >= length) {
                                            break;
                                        }

                                        if (itemArray[index16].equals(item3)) {
                                            if (conditionArray2 instanceof boolean[]) {
                                                conditionArray2[index16] = true;
                                            } else {
                                                ((byte[])((Object)(conditionArray2)))[index16] = 1;
                                            }

                                        }

                                        index16++;
                                    }
                                }
                                int index17 = 0;
                                int index18 = 0;
                                if (index14 <= 0) {
                                    index17 = index10;
                                    conditionArray = conditionArray2;
                                    index18 = index14;
                                } else {
                                    double offset = (double)indexOffsetArray[4];
                                    double value = this.settingReCraftIfSoupsLowerThan.getValue();
                                    if ((!Double.isNaN(offset) && !Double.isNaN(value) ? (offset > value ? 1 : (offset == value ? 0 : -1)) : 1) <= 0) {
                                        if (minecraft.thePlayer.inventoryPlayer.getItemStack() == null) {
                                            if (index10 <= 8) {
                                                conditionArray = conditionArray2;
                                            } else {
                                                boolean completed2 = false;
                                                boolean completed = false;
                                                Object object = itemArray[0];
                                                if (item3.equals(object)) {
                                                    conditionArray = conditionArray2;
                                                    if ((conditionArray2 instanceof boolean[] ? (((boolean[])conditionArray2)[0] ? 1 : 0) : ((byte[])((Object)(conditionArray2)))[0])
                                                    == 0) {
                                                        completed = true;
                                                    }
                                                }
                                                if (!completed) {
                                                    Object object2 = itemArray[1];
                                                    if (item3.equals(object2)) {
                                                        conditionArray = conditionArray2;
                                                        if ((conditionArray2 instanceof boolean[] ? (((boolean[])conditionArray2)[1] ? 1 : 0) : ((byte[])((Object)(conditionArray2)))[1])
                                                        == 0) {
                                                            completed = true;
                                                        }
                                                    }
                                                    if (!completed) {
                                                        Object object3 = itemArray[2];
                                                        if (!item3.equals(object3)) {
                                                            conditionArray = conditionArray2;
                                                            completed2 = true;
                                                        }
                                                        if (!completed2) {
                                                            conditionArray = conditionArray2;
                                                            if ((conditionArray2 instanceof boolean[] ? (((boolean[])conditionArray2)[2] ? 1 : 0) : ((byte[])((Object)(conditionArray2)))[2]) != 0) {
                                                                completed2 = true;
                                                            }
                                                        }
                                                    }
                                                }
                                                if (!completed2) {
                                                    boolean enabled5 = this.shouldEatSoup();
                                                    if (enabled5) {
                                                        return false;
                                                    }
                                                    Container container3 = minecraft.thePlayer.container;
                                                    this.applyContainer(container3, index10, state);
                                                    enabled4 = true;
                                                    index15 = 0;
                                                }

                                            }
                                            if ((conditionArray instanceof boolean[] ? (((boolean[])conditionArray)[0] ? 1 : 0) : ((byte[])((Object)(conditionArray)))[0]) == 0) {
                                                index13 = index15;
                                            } else {
                                                int index19 = conditionArray instanceof boolean[] ? (((boolean[])conditionArray)[1] ? 1 : 0) : ((byte[])((Object)(conditionArray)))[1];
                                                if (index19 == 0) {
                                                    index13 = index15;
                                                } else {
                                                    int index20 = conditionArray instanceof boolean[] ? (((boolean[])conditionArray)[2] ? 1 : 0) : ((byte[])((Object)(conditionArray)))[2];
                                                    if (index20 == 0) {
                                                        index13 = index15;
                                                        index12 = index14;
                                                    } else {
                                                        boolean enabled6 = this.shouldEatSoup();
                                                        index12 = index14;
                                                        if (enabled6) {
                                                            return false;
                                                        }

                                                        this.processAction(0);
                                                        enabled4 = true;
                                                        index13 = 0;
                                                    }
                                                }
                                            }
                                            completed3 = true;
                                        }
                                        if (!completed3) {
                                            Item item4 = minecraft.thePlayer.inventoryPlayer.getItemStack().getItem();
                                            int index21 = index10;
                                            int state2 = 0;
                                            while (state2 < 3) {
                                                Object object4 = itemArray[state2];
                                                boolean enabled7 = item4.equals(object4);
                                                boolean[] conditionArray3 = conditionArray2;
                                                int index22;
                                                ItemStack itemStack3;
                                                int index23;
                                                if (!enabled7) {
                                                    index22 = index21;
                                                    index23 = index12;
                                                    itemStack3 = itemStack2;
                                                } else {
                                                    int index24 = conditionArray2 instanceof boolean[]
                                                    ? (((boolean[])conditionArray2)[state2] ? 1 : 0)
                                                    : ((byte[])((Object)(conditionArray2)))[state2];
                                                    if (index24 == 0) {
                                                        boolean enabled8 = this.shouldEatSoup();
                                                        if (enabled8) {
                                                            return false;
                                                        }

                                                        Container container4 = minecraft.thePlayer.container;
                                                        int index25 = state2 + 1;
                                                        Container container5 = container4;
                                                        this.applyContainer(container5, index25, 0);
                                                        enabled4 = true;
                                                        index22 = 0;
                                                        conditionArray3 = conditionArray2;
                                                        index23 = index12;
                                                        itemStack3 = itemStack2;
                                                    } else {
                                                        index22 = index21;
                                                        index23 = index12;
                                                        itemStack3 = itemStack2;
                                                    }
                                                }

                                                state2 += 1;
                                                index21 = index22;
                                                conditionArray2 = conditionArray3;
                                                index12 = index23;
                                                itemStack2 = itemStack3;
                                            }
                                            index13 = index21;
                                            conditionArray = conditionArray2;
                                            completed3 = true;
                                        }
                                    }
                                    if (!completed3) {
                                        index17 = index10;
                                        conditionArray = conditionArray2;
                                        index18 = index14;
                                    }
                                }
                                if (!completed3) {
                                    int index26 = index17;
                                    index12 = index18;
                                    if (index17 <= 5) {
                                        index13 = index17;
                                        completed3 = true;
                                    }
                                    if (!completed3) {
                                        int index27 = index18;
                                        int index28 = 0;
                                        while (true) {
                                            int length2 = Array.getLength(itemArray);
                                            if (index28 >= length2) {
                                                index13 = index26;
                                                index12 = index27;
                                                { completed3 = true; break; }
                                            }
                                            if (!completed3) {
                                                ItemStack itemStack4 = null;
                                                boolean enabled9 = false;
                                                int index29 = 0;
                                                boolean[] conditionArray4 = null;
                                                int index30 = 0;
                                                int index31 = conditionArray instanceof boolean[] ? (((boolean[])conditionArray)[index28] ? 1 : 0) : ((byte[])((Object)(conditionArray)))[index28];
                                                conditionArray4 = conditionArray;
                                                index29 = index28;
                                                boolean completed = false;
                                                if (index31 != 0) {
                                                    boolean enabled10 = this.shouldEatSoup();
                                                    if (enabled10) {
                                                        break;
                                                    }
                                                    boolean enabled11 = enabled4;
                                                    index30 = index26;
                                                    boolean[] conditionArray5 = conditionArray;
                                                    int index32 = index27;
                                                    itemStack4 = itemStack2;
                                                    int index33 = index28;
                                                    Object object5 = null;
                                                    int index34 = 0;
                                                    int index35 = 0;
                                                    int index36 = 0;
                                                    ItemStack itemStack5 = null;
                                                    boolean enabled12 = false;
                                                    while (true) {
                                                        if (index35 >= 5) {
                                                            enabled12 = enabled11;
                                                            index36 = index33;
                                                            itemStack5 = (ItemStack)object5;
                                                            break;
                                                        }

                                                        ItemStack itemStack6 = minecraft.thePlayer.container.getSlot(index35).getStack();
                                                        int index37;
                                                        Object object6;
                                                        int index38;
                                                        if (itemStack6 == null) {
                                                            object6 = object5;
                                                            index37 = index34;
                                                            index38 = index35;
                                                        } else {
                                                            index38 = index35;
                                                            itemStack5 = itemStack6;
                                                            Item item5 = itemStack6.getItem();
                                                            Object object7 = itemArray[index33];
                                                            if (item5.equals(object7)) {
                                                                enabled12 = enabled11;
                                                                index36 = index33;
                                                                index34 = index35;
                                                                break;
                                                            }

                                                            object6 = object5;
                                                            index37 = index34;
                                                        }

                                                        index35 = index38 + 1;
                                                        object5 = object6;
                                                        index34 = index37;
                                                    }
                                                    int state3 = 9;
                                                    int index39 = 0;
                                                    ItemStack itemStack7 = null;
                                                    int index40 = 0;
                                                    int index41 = 0;
                                                    while (true) {
                                                        int size2 = minecraft.thePlayer.container.itemList2.size();
                                                        if (state3 >= size2) {
                                                            enabled9 = enabled12;
                                                            conditionArray4 = conditionArray5;
                                                            index27 = index32;
                                                            index29 = index36;
                                                            { completed = true; break; }
                                                        }
                                                        if (!completed) {
                                                            ItemStack itemStack8 = minecraft.thePlayer.container.getSlot(state3).getStack();
                                                            boolean enabled13 = false;
                                                            int index42 = 0;
                                                            if (itemStack5 == null) {
                                                                enabled13 = enabled12;
                                                                index42 = index30;
                                                            } else {
                                                                index40 = index34;
                                                                if (itemStack8 == null) {
                                                                    index39 = index32;
                                                                    itemStack7 = itemStack4;
                                                                    index41 = index36;
                                                                    break;
                                                                }

                                                                Item item6 = itemStack8.getItem();
                                                                Item item7 = itemStack5.getItem();
                                                                boolean enabled14 = item6.equals(item7);
                                                                enabled13 = enabled12;
                                                                index42 = index30;
                                                                if (!enabled14) {
                                                                } else {
                                                                    int animationsToGo = itemStack8.animationsToGo;
                                                                    index40 = index34;
                                                                    int index43 = itemStack5.processAction5();
                                                                    if (animationsToGo < index43) {
                                                                        index39 = index32;
                                                                        itemStack7 = itemStack4;
                                                                        index41 = index36;
                                                                        break;
                                                                    }

                                                                    enabled13 = enabled12;
                                                                    index42 = index30;
                                                                }
                                                            }
                                                            state3 += 1;
                                                            enabled12 = enabled13;
                                                            index30 = index42;
                                                        }
                                                    }
                                                    if (!completed) {
                                                        this.processAction(index40);
                                                        enabled9 = true;
                                                        index30 = 0;
                                                        conditionArray4 = conditionArray5;
                                                        index27 = index39;
                                                        itemStack4 = itemStack7;
                                                        index29 = index41;
                                                    }
                                                } else {
                                                    enabled9 = enabled4;
                                                    index30 = index26;
                                                    itemStack4 = itemStack2;
                                                }
                                                int index44 = index29 + 1;
                                                enabled4 = enabled9;
                                                index26 = index30;
                                                conditionArray = conditionArray4;
                                                itemStack2 = itemStack4;
                                                index28 = index44;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            ItemStack itemStack9 = minecraft.thePlayer.container.getSlot(index10).getStack();
                            if (itemStack9 == null) {
                                completed4 = true;
                            }
                            if (!completed4) {
                                Item item8 = itemStack9.getItem();
                                if (index10 < 5) {
                                    int index45 = 0;

                                    while (true) {
                                        int length3 = Array.getLength(itemArray);
                                        if (index45 >= length3) {
                                            break;
                                        }

                                        if (itemArray[index45].equals(item8)) {
                                            if (conditionArray instanceof boolean[]) {
                                                conditionArray[index45] = true;
                                            } else {
                                                ((byte[])((Object)(conditionArray)))[index45] = 1;
                                            }
                                        }

                                        index45++;
                                    }
                                }
                                int index46 = 0;
                                if (index9 <= 0) {
                                    index46 = index10;
                                } else {
                                    double offset2 = (double)indexOffsetArray[4];
                                    double value2 = this.settingReCraftIfSoupsLowerThan.getValue();
                                    if ((!Double.isNaN(offset2) && !Double.isNaN(value2) ? (offset2 > value2 ? 1 : (offset2 == value2 ? 0 : -1)) : 1) <= 0) {
                                        if (minecraft.thePlayer.inventoryPlayer.getItemStack() == null) {
                                            boolean completed = false;
                                            if (index10 > 8) {
                                                Object object8 = itemArray[0];
                                                if (!item8.equals(object8)
                                                || (conditionArray instanceof boolean[] ? (((boolean[])conditionArray)[0] ? 1 : 0) : ((byte[])((Object)(conditionArray)))[0]) != 0) {
                                                    Object object9 = itemArray[1];
                                                    if (!item8.equals(object9)
                                                    || (conditionArray instanceof boolean[] ? (((boolean[])conditionArray)[1] ? 1 : 0) : ((byte[])((Object)(conditionArray)))[1]) != 0) {
                                                        Object object10 = itemArray[2];
                                                        if (!item8.equals(object10)
                                                        || (conditionArray instanceof boolean[] ? (((boolean[])conditionArray)[2] ? 1 : 0) : ((byte[])((Object)(conditionArray)))[2])
                                                        != 0) {
                                                            completed = true;
                                                        }
                                                    }
                                                }
                                                if (!completed) {
                                                    if (this.shouldEatSoup()) {
                                                        return false;
                                                    }
                                                    Container container6 = minecraft.thePlayer.container;
                                                    this.applyContainer(container6, index10, state);
                                                    enabled = true;
                                                    index10 = 0;
                                                }
                                            }
                                            if ((conditionArray instanceof boolean[] ? (((boolean[])conditionArray)[0] ? 1 : 0) : ((byte[])((Object)(conditionArray)))[0]) != 0) {
                                                int index47 = conditionArray instanceof boolean[] ? (((boolean[])conditionArray)[1] ? 1 : 0) : ((byte[])((Object)(conditionArray)))[1];
                                                if (index47 == 0) {
                                                } else {
                                                    int index48 = conditionArray instanceof boolean[] ? (((boolean[])conditionArray)[2] ? 1 : 0) : ((byte[])((Object)(conditionArray)))[2];
                                                    if (index48 == 0) {
                                                    } else {
                                                        if (this.shouldEatSoup()) {
                                                            return false;
                                                        }

                                                        this.processAction(0);
                                                        enabled = true;
                                                        index10 = 0;
                                                    }
                                                }
                                            }
                                            completed4 = true;
                                        }
                                        if (!completed4) {
                                            Item item9 = minecraft.thePlayer.inventoryPlayer.getItemStack().getItem();
                                            int state4 = 0;
                                            while (true) {
                                                if (state4 >= 3) {
                                                    { completed4 = true; break; }
                                                }
                                                if (!completed4) {
                                                    Object object11 = itemArray[state4];
                                                    int index49 = 0;
                                                    ItemStack itemStack10 = null;
                                                    if (!item9.equals(object11)) {
                                                        index49 = index10;
                                                        itemStack10 = itemStack9;
                                                    } else {
                                                        int index50 = conditionArray instanceof boolean[]
                                                        ? (((boolean[])conditionArray)[state4] ? 1 : 0)
                                                        : ((byte[])((Object)(conditionArray)))[state4];
                                                        index49 = index10;
                                                        if (index50 == 0) {
                                                            boolean enabled15 = this.shouldEatSoup();
                                                            if (enabled15) {
                                                                return false;
                                                            }

                                                            Container container7 = minecraft.thePlayer.container;
                                                            int index51 = state4 + 1;
                                                            Container container8 = container7;
                                                            this.applyContainer(container8, index51, 0);
                                                            enabled = true;
                                                            index49 = 0;
                                                            itemStack10 = itemStack9;
                                                        } else {
                                                            itemStack10 = itemStack9;
                                                        }
                                                    }
                                                    state4 += 1;
                                                    index10 = index49;
                                                    itemStack9 = itemStack10;
                                                }
                                            }
                                        }
                                    }
                                    if (!completed4) {
                                        index46 = index10;
                                    }
                                }
                                if (!completed4) {
                                    index10 = index46;
                                    if (index46 <= 5) {
                                        completed4 = true;
                                    }
                                    if (!completed4) {
                                        int index52 = index46;
                                        int index53 = 0;
                                        while (true) {
                                            int length4 = Array.getLength(itemArray);
                                            if (index53 >= length4) {
                                                index10 = index52;
                                                { completed4 = true; break; }
                                            }
                                            if (!completed4) {
                                                int index54 = conditionArray instanceof boolean[] ? (((boolean[])conditionArray)[index53] ? 1 : 0) : ((byte[])((Object)(conditionArray)))[index53];
                                                boolean[] conditionArray6 = conditionArray;
                                                int index55 = index53;
                                                ItemStack itemStack11 = null;
                                                boolean enabled16 = false;
                                                int index56 = 0;
                                                if (index54 == 0) {
                                                    index56 = index9;
                                                    enabled16 = enabled;
                                                    itemStack11 = itemStack9;
                                                } else {
                                                    boolean completed = false;
                                                    enabled16 = enabled;
                                                    boolean enabled17 = this.shouldEatSoup();
                                                    if (enabled17) {
                                                        break;
                                                    }
                                                    int index57 = index9;
                                                    int index58 = index52;
                                                    itemStack11 = itemStack9;
                                                    int index59 = index53;
                                                    Object object12 = null;
                                                    int index60 = 0;
                                                    int index61 = 0;
                                                    int index62 = 0;
                                                    ItemStack itemStack12 = null;
                                                    int index63 = 0;
                                                    while (true) {
                                                        if (index61 >= 5) {
                                                            index63 = index57;
                                                            index62 = index59;
                                                            itemStack12 = (ItemStack)object12;
                                                            break;
                                                        }

                                                        ItemStack itemStack13 = minecraft.thePlayer.container.getSlot(index61).getStack();
                                                        boolean enabled18 = enabled16;
                                                        int index64;
                                                        Object object13;
                                                        int index65;
                                                        if (itemStack13 == null) {
                                                            object13 = object12;
                                                            index64 = index60;
                                                            index65 = index61;
                                                        } else {
                                                            index65 = index61;
                                                            itemStack12 = itemStack13;
                                                            Item item10 = itemStack13.getItem();
                                                            Object object14 = itemArray[index59];
                                                            if (item10.equals(object14)) {
                                                                index63 = index57;
                                                                index62 = index59;
                                                                index60 = index61;
                                                                break;
                                                            }

                                                            enabled18 = enabled16;
                                                            object13 = object12;
                                                            index64 = index60;
                                                        }

                                                        index61 = index65 + 1;
                                                        enabled16 = enabled18;
                                                        object12 = object13;
                                                        index60 = index64;
                                                    }
                                                    int state5 = 9;
                                                    int index66 = 0;
                                                    boolean[] conditionArray7 = null;
                                                    ItemStack itemStack14 = null;
                                                    int index67 = 0;
                                                    int index68 = 0;
                                                    while (true) {
                                                        int size3 = minecraft.thePlayer.container.itemList2.size();
                                                        if (state5 >= size3) {
                                                            index56 = index63;
                                                            index52 = index58;
                                                            index55 = index62;
                                                            { completed = true; break; }
                                                        }
                                                        if (!completed) {
                                                            ItemStack itemStack15 = minecraft.thePlayer.container.getSlot(state5).getStack();
                                                            int index69 = 0;
                                                            boolean[] conditionArray8 = null;
                                                            boolean enabled19 = false;
                                                            if (itemStack12 == null) {
                                                                index69 = index63;
                                                                conditionArray8 = conditionArray6;
                                                                enabled19 = enabled16;
                                                            } else {
                                                                enabled19 = enabled16;
                                                                index67 = index60;
                                                                if (itemStack15 == null) {
                                                                    index66 = index63;
                                                                    conditionArray7 = conditionArray6;
                                                                    itemStack14 = itemStack11;
                                                                    index68 = index62;
                                                                    break;
                                                                }

                                                                Item item11 = itemStack15.getItem();
                                                                Item item12 = itemStack12.getItem();
                                                                boolean enabled20 = item11.equals(item12);
                                                                index69 = index63;
                                                                conditionArray8 = conditionArray6;
                                                                if (!enabled20) {
                                                                } else {
                                                                    int animationsToGo2 = itemStack15.animationsToGo;
                                                                    index67 = index60;
                                                                    int index70 = itemStack12.processAction5();
                                                                    if (animationsToGo2 < index70) {
                                                                        index66 = index63;
                                                                        conditionArray7 = conditionArray6;
                                                                        itemStack14 = itemStack11;
                                                                        index68 = index62;
                                                                        break;
                                                                    }

                                                                    enabled19 = enabled16;
                                                                }
                                                            }
                                                            state5 += 1;
                                                            index63 = index69;
                                                            conditionArray6 = conditionArray8;
                                                            enabled16 = enabled19;
                                                        }
                                                    }
                                                    if (!completed) {
                                                        this.processAction(index67);
                                                        index56 = index66;
                                                        conditionArray6 = conditionArray7;
                                                        enabled16 = true;
                                                        index52 = 0;
                                                        itemStack11 = itemStack14;
                                                        index55 = index68;
                                                    }

                                                }
                                                int index71 = index55 + 1;
                                                index9 = index56;
                                                conditionArray = conditionArray6;
                                                enabled = enabled16;
                                                itemStack9 = itemStack11;
                                                index53 = index71;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!completed4) {
                            if (!completed3) {
                                return false;
                            }
                            index11 = index13 + 1;
                            enabled = enabled4;
                        } else {
                            index11 = index10 + 1;
                            index12 = index9;
                        }

                        index9 = index12;
                        index10 = index11;
                    }
                }
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    private boolean shouldEatSoup3() {
        if (!this.settingDrop.isEnabled()) {
            return false;
        } else {
            Container container = minecraft.thePlayer.container;
            int size = minecraft.thePlayer.container.itemList2.size();
            Item[] itemArray = new Item[]{Items.bowl};
            double offset = (double)InventoryUtil.getIndex(container, 0, size, itemArray);
            return offset >= Math.max(0.0, this.settingDropBowlsIfYouHaveMoreThan.getValue());
        }
    }

    private boolean isAutoCloseDelayVisible() {
        return !this.settingAutoClose.isEnabled() ? false : this.settingFill.isEnabled();
    }
}
