package augustus.modules.combat;

import client.Client;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventMotionUpdate;
import client.event.EventPacket;
import client.event.MotionListener;
import client.event.MotionUpdateListener;
import client.event.PacketListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.rotation.RotationManager;
import client.setting.ActionSetting;
import client.setting.AutoPotPotionType;
import client.setting.BooleanSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.SettingContainer;
import client.setting.SettingProvider;
import client.util.BooleanSupplier;
import client.util.Packet;
import client.util.Pair;
import client.util.SimulatedPlayer;
import client.util.SlotManager;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.BlockCache;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C05PacketPlayerLook;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.server.S06PacketUpdateHealth;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class AutoPot extends Module implements TickListener, MotionUpdateListener, MotionListener, ClickListener, PacketListener {
    public ActionSetting settingPitchSpeed;
    public NumberSetting settingHealHearts;
    public BooleanSetting settingInstant;
    public ActionSetting settingTickDelayBeforeClick;
    public RangeSetting settingTickDelayBetweenClick;
    public ActionSetting settingTickDelayAfterClick;
    public BooleanSetting settingHotbarOnly;
    public BooleanSetting settingInInv;
    public BooleanSetting settingNotWhileKillAura;
    public MultiSelectSetting settingPotions;
    public TimeHelper timer;
    public int tickDelay2;
    public ItemPotion itemPotion;
    public ItemStack itemStack;
    public int tickDelay3;
    public int tickDelay;
    public int tickDelay4;
    public int keyCode2;
    public int tickDelay1;
    public boolean enabled2;
    public float motionMultiplier;
    public boolean activeState;
    public float motionMultiplier5;
    public boolean expanded2;

    public AutoPot() {
        super("AutoPot", "AutoTrank", new Color(227, 66, 101), Category.COMBAT);
        this.settingPitchSpeed = new ActionSetting(this, "PitchSpeed", 30.0, 50.0, 0.0, 180.0, 0);
        this.settingHealHearts = new NumberSetting(this, "HealHearts", 14.0, 2.0, 20.0, 0);
        this.settingInstant = new BooleanSetting(this, "Instant", true);
        this.settingTickDelayBeforeClick = new ActionSetting(this, "TickDelayBeforeClick", 0.0, 0.0, 0.0, 7.0, 0).getActionSetting(() -> this.isTickDelayBeforeClickVisible());
        this.settingTickDelayBetweenClick = new RangeSetting(this, "TickDelayBetweenClick", 0.0, 0.0, 0.0, 7.0, 0)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isTickDelayBetweenClickVisible());
        this.settingTickDelayAfterClick = new ActionSetting(this, "TickDelayAfterClick", 1.0, 1.0, 0.0, 7.0, 0).getActionSetting(() -> this.isTickDelayAfterClickVisible());
        this.settingHotbarOnly = new BooleanSetting(this, "HotbarOnly", true);
        this.settingInInv = new BooleanSetting(this, "InInv", false);
        this.settingNotWhileKillAura = new BooleanSetting(this, "NotWhileKillAura", true);
        AutoPotPotionType[] autoPotPotionTypeArray = AutoPotPotionType.values();
        this.settingPotions = new MultiSelectSetting(this, "Potions", autoPotPotionTypeArray);
        this.timer = new TimeHelper();
        this.tickDelay2 = -1;
        this.itemPotion = (ItemPotion)null;
        this.itemStack = (ItemStack)null;
        this.tickDelay3 = 1;
        this.tickDelay = 1;
        this.tickDelay4 = 0;
        this.keyCode2 = 0;
        this.tickDelay1 = 0;
        this.enabled2 = false;
        this.motionMultiplier = 0.0F;
        this.activeState = false;
        this.expanded2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(18);
        this.registerEvent(34);
        this.registerEvent(7, 20);
        this.registerEvent(26);
    }

    @Override
    public void onDisable() {
        this.tickDelay2 = -1;
    }

    @Override
    public void onPreDisable() {
        this.tickDelay2 = -1;
        this.itemPotion = null;
        this.activeState = false;
        this.processAction();
    }

    @Override
    public void onClick(EventClick event) {
        this.tickDelay1++;
        if (!this.settingInstant.isEnabled() && this.shouldThrowPotion() && this.shouldThrowPotion2()) {
            int tickDelay22 = this.tickDelay2;
            int int2 = this.getInt2(tickDelay22);
            if (int2 != this.tickDelay2 - 36) {
                int tickDelay23 = this.tickDelay2;
                this.processAction2(tickDelay23, int2);
                this.tickDelay2 = int2;
            }

            ItemStack itemStack5 = minecraft.thePlayer.getHeldItem();
            if (itemStack5 != null) {
                if (itemStack5.getItem() instanceof ItemPotion) {
                    if (int2 == minecraft.thePlayer.inventoryPlayer.currentItem) {
                        if (!((ItemPotion)itemStack5.getItem()).equals(this.itemPotion)) {
                            return;
                        }

                        if (ClientGlobals.rotationManager.getRotation3().pitch <= 85.0F) {
                            return;
                        }

                        int tickDelay42 = this.tickDelay4;
                        if (tickDelay42 <= this.tickDelay3) {
                            return;
                        }

                        int tickDelay12 = this.tickDelay1;
                        int indexOffset2 = this.settingTickDelayBetweenClick.getIndexOffset2();
                        if (tickDelay12 <= indexOffset2 && this.expanded2) {
                            return;
                        }

                        this.settingTickDelayBetweenClick.recalculate();
                        event.updateInt2(1);
                        event.setState11(true);
                        this.throwPotion();
                        this.keyCode2 = 0;
                        this.expanded2 = true;
                        this.tickDelay1 = 0;
                        this.timer.reset();
                        this.itemPotion = null;
                    }
                }
            }
        } else {
            this.tickDelay1 = 0;
            this.expanded2 = false;
        }
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        if (this.settingInstant.isEnabled()) {
            if (this.shouldThrowPotion()) {
                if (this.shouldThrowPotion2()) {
                    int indexOffset = minecraft.thePlayer.inventoryPlayer.currentItem;
                    if (indexOffset != this.tickDelay2 - 36) {
                        int tickDelay22 = this.tickDelay2;
                        int int2 = this.getInt2(tickDelay22);
                        if (int2 != this.tickDelay2 - 36) {
                            int tickDelay23 = this.tickDelay2;
                            this.processAction2(tickDelay23, int2);
                        }

                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(int2));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C05PacketPlayerLook(minecraft.thePlayer.rotationYaw, 90.0F, minecraft.thePlayer.onGround));
                        minecraft.thePlayer
                        .netHandlerPlayClient
                        .handlePacket(new C08PacketPlayerBlockPlacement(minecraft.thePlayer.inventoryPlayer.processAction3(int2)));
                        this.throwPotion();
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(indexOffset));
                    } else {
                        minecraft.thePlayer
                        .netHandlerPlayClient
                        .handlePacket(new C05PacketPlayerLook(minecraft.thePlayer.rotationYaw, 90.0F, minecraft.thePlayer.onGround));
                        minecraft.thePlayer
                        .netHandlerPlayClient
                        .handlePacket(new C08PacketPlayerBlockPlacement(minecraft.thePlayer.inventoryPlayer.processAction3(this.tickDelay2 - 36)));
                    }

                    this.timer.reset();
                    this.tickDelay2 = -1;
                }
            }
        }
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof S06PacketUpdateHealth) {
            S06PacketUpdateHealth s06PacketUpdateHealth = (S06PacketUpdateHealth)packet2;
            if (s06PacketUpdateHealth.getScaleFactor() < this.motionMultiplier5) {
                this.motionMultiplier = s06PacketUpdateHealth.getScaleFactor();
            }

            this.motionMultiplier5 = s06PacketUpdateHealth.getScaleFactor();
        }
    }

    @Override
    public void onPostMotion() {
        boolean enabled = false;
        boolean completed = false;
        this.tickDelay4++;
        enabled = false;
        if (!((this.getInt3() >= 0) && ((Client.getClient()).getRotationManager().checkModule(this) && ClientGlobals.rotationManager.getRotation3().pitch > 85.0F))) {
            int index = 0;
            if (this.enabled2) {
                index = Math.max(this.tickDelay, this.settingTickDelayBetweenClick.getIndexOffset2());
            } else {
                index = this.tickDelay;
            }
            if (this.keyCode2 >= index) {
                completed = true;
            }
        }
        if (!completed) {
            int int3 = this.getInt3();
            SlotManager.applyModule3(this, 4, int3);
            enabled = true;
        }


        if (enabled != this.activeState) {
            this.processAction();
        }

        this.activeState = enabled;
        this.keyCode2++;
    }

    @Override
    public void onPreTick() {
        if ((this.timer.hasReached(1000L) || this.enabled2) && this.shouldThrowPotion()) {
            boolean enabled = this.settingHotbarOnly.isEnabled();
            Pair pair = this.getPair(enabled);
            this.tickDelay2 = (Integer)pair.getSecond();
            this.itemStack = (ItemStack)pair.getFirst();
            ItemPotion itemPotion2;
            if (this.itemStack == null) {
                itemPotion2 = null;
            } else {
                itemPotion2 = (ItemPotion)this.itemStack.getItem();
            }

            this.itemPotion = itemPotion2;
            this.keyCode2 = 1000;
        }

        if (!this.settingInstant.isEnabled()) {
            if (this.shouldThrowPotion()) {
                if (this.shouldThrowPotion2()) {
                    RotationManager rotationManager = ClientGlobals.rotationManager;
                    float pitch = ClientGlobals.rotationManager.getRotation3().pitch;
                    double double3 = this.settingPitchSpeed.getDouble3();
                    double double6 = this.settingPitchSpeed.getDouble6();
                    float floatEntry = SettingContainer.settingProvider.getFloat(double3, double6);
                    float float5 = SimulatedPlayer.getFloat5(pitch, 90.0F, floatEntry);
                    rotationManager.applyModule(float5, 1, this);
                }
            }
        }
    }

    private void throwPotion() {
        if (this.itemPotion != null && this.itemStack != null) {
            if (!this.itemPotion.processAction2(this.itemStack)) {
                return;
            }

            PotionEffect potionEffect = (PotionEffect)this.itemPotion.getList(this.itemStack).get(0);
            if (potionEffect.getIndexOffset3() != 6) {
                this.enabled2 = false;
            } else {
                int index = (int)(1.0 * (double)(4 << potionEffect.getIndexOffset2()) + 0.5);
                float value = minecraft.thePlayer.getFloat() - this.motionMultiplier;
                this.enabled2 = (double)((int)(value / (float)index)) > 0.85;
                this.motionMultiplier += (float)index;
                System.out.println(this.enabled2);
            }
        }
    }

    private void processAction2(int index, int index2) {
        minecraft.playerController.windowClick(minecraft.thePlayer.container.indexOffset2, index, index2, 2, minecraft.thePlayer);
    }

    private boolean checkCondition(int index) {
        Iterator iterator = minecraft.thePlayer.getCollection().iterator();

        while (iterator.hasNext()) {
            if (((PotionEffect)iterator.next()).getIndexOffset3() == index) {
                return false;
            }
        }

        return true;
    }

    private boolean isTickDelayAfterClickVisible() {
        return !this.settingInstant.isEnabled();
    }

    private boolean checkItemPotion(ItemPotion itemPotion2, PotionEffect potionEffect, boolean enabled) {
        for (int i = enabled ? 36 : 9; i < minecraft.thePlayer.container.itemList2.size(); i++) {
            ItemStack itemStack2 = minecraft.thePlayer.container.getSlot(i).getStack();
            if (itemStack2 != null && itemStack2.getItem() instanceof ItemPotion) {
                ItemPotion itemPotion3 = (ItemPotion)itemStack2.getItem();
                if (itemPotion3 != itemPotion2 && !itemPotion2.getList(itemStack2).isEmpty() && ItemPotion.checkCondition(itemStack2.getIndexOffset())) {
                    PotionEffect potionEffect2 = (PotionEffect)itemPotion3.getList(itemStack2).get(0);
                    int indexOffset3 = potionEffect2.getIndexOffset3();
                    int indexOffset32 = potionEffect.getIndexOffset3();
                    if (indexOffset3 == indexOffset32) {
                        int indexOffset2 = potionEffect.getIndexOffset2();
                        int indexOffset22 = potionEffect2.getIndexOffset2();
                        if (indexOffset2 > indexOffset22) {
                            return false;
                        }

                        int indexOffset = potionEffect.getIndexOffset();
                        int indexOffset4 = potionEffect2.getIndexOffset();
                        if (indexOffset > indexOffset4 && potionEffect.getIndexOffset2() == potionEffect2.getIndexOffset2()) {
                            return false;
                        }

                        return true;
                    }
                }
            }
        }

        return true;
    }

    private int getInt2(int index2) {
        if (index2 >= 0) {
            int size = minecraft.thePlayer.container.itemList2.size();
            if (index2 <= size) {
                if (index2 < 36) {
                    for (int i = 36; i < minecraft.thePlayer.container.itemList2.size(); i++) {
                        ItemStack itemStack2 = minecraft.thePlayer.container.getSlot(i).getStack();
                        if (itemStack2 == null) {
                            return i - 36;
                        }

                        if (itemStack2.getItem() instanceof ItemPotion) {
                            return i - 36;
                        }
                    }

                    return 6;
                }

                return index2 - 36;
            }
        }

        return index2;
    }

    public int getInt3() {
        return this.getInt2(this.tickDelay2);
    }

    private boolean isTickDelayBetweenClickVisible() {
        return !this.settingInstant.isEnabled();
    }

    private void processAction() {
        this.tickDelay3 = SettingContainer.settingProvider
        .getInt(this.settingTickDelayBeforeClick.getDouble3(), this.settingTickDelayBeforeClick.getDouble6());
        this.tickDelay = SettingContainer.settingProvider
        .getInt(this.settingTickDelayAfterClick.getDouble3(), this.settingTickDelayAfterClick.getDouble6());
        this.tickDelay4 = 0;
    }

    private Pair getPair(boolean enabled) {
        for (int i = enabled ? 36 : 9; i < minecraft.thePlayer.container.itemList2.size(); i++) {
            ItemStack itemStack2 = minecraft.thePlayer.container.getSlot(i).getStack();
            if (itemStack2 != null && itemStack2.getItem() instanceof ItemPotion) {
                ItemPotion itemPotion2 = (ItemPotion)itemStack2.getItem();
                List list = itemPotion2.getList(itemStack2);
                if (list != null && !list.isEmpty() && ItemPotion.checkCondition(itemStack2.getIndexOffset())) {
                    PotionEffect potionEffect = (PotionEffect)itemPotion2.getList(itemStack2).get(0);
                    switch (potionEffect.getIndexOffset3()) {
                        case 6:
                            int index = Double.compare((double)minecraft.thePlayer.getFloat2(), this.settingHealHearts.getValue());
                            if (index < 0 && AutoPotPotionType.HEALTH.isSelected() && this.checkItemPotion(itemPotion2, potionEffect, enabled)) {
                                return new Pair(i, itemStack2);
                            }
                            break;
                        case 10:
                            if (!AutoPotPotionType.REGENERATION.isSelected()) {
                                break;
                            }
                        case 1:
                            int index2 = Double.compare((double)minecraft.thePlayer.getFloat2(), this.settingHealHearts.getValue());
                            if ((index2 < 0 || potionEffect.getIndexOffset3() != 10)
                            && (AutoPotPotionType.SPEED.isSelected() || potionEffect.getIndexOffset3() != 1)
                            && this.checkCondition(potionEffect.getIndexOffset3())
                            && this.checkItemPotion(itemPotion2, potionEffect, enabled)) {
                                this.enabled2 = false;
                                return new Pair(i, itemStack2);
                            }
                            break;
                        default:
                            if (!Potion.potionArray[potionEffect.getIndexOffset3()].isState2()
                            && AutoPotPotionType.ALL_POSITIVE.isSelected()
                            && this.checkCondition(potionEffect.getIndexOffset3())
                            && this.checkItemPotion(itemPotion2, potionEffect, enabled)) {
                                this.enabled2 = false;
                                return new Pair(i, itemStack2);
                            }
                    }
                }
            }
        }

        this.enabled2 = false;
        return new Pair(-1, null);
    }

    private boolean shouldThrowPotion() {
        if (minecraft.thePlayer.canExecute6()) {
            return false;
        } else if (minecraft.gameSettings.keyBindUseItem.pressed && !minecraft.playerController.canExecute()) {
            return false;
        } else if (this.settingNotWhileKillAura.isEnabled()
        && ClientGlobals.moduleManager.killAura.isToggled()
        && ClientGlobals.moduleManager.killAura.target != null) {
            return false;
        } else if (BlockCache.canExecute()) {
            return false;
        } else if (minecraft.thePlayer.isUsingItem()) {
            return false;
        } else if (minecraft.isIntegratedServerRunning()) {
            return true;
        } else {
            return !this.settingInInv.isEnabled() ? false : minecraft.currentScreen instanceof GuiInventory;
        }
    }

    private boolean isTickDelayBeforeClickVisible() {
        return !this.settingInstant.isEnabled();
    }

    private boolean shouldThrowPotion2() {
        return this.tickDelay2 <= 0 ? false : this.itemPotion != null;
    }
}
