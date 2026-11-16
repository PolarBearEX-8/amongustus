package augustus.modules.player;

import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventPacket;
import client.event.MotionListener;
import client.event.PacketListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.RangeSetting;
import client.util.InventoryUtil;
import client.util.SlotManager;
import client.util.TimeHelper;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.server.S07PacketRespawn;

public class AutoEat extends Module implements ClickListener, PacketListener, MotionListener {
    public TimeHelper eatTimer;
    public int keyCode2;
    public int tickDelay;
    public RangeSetting settingHunger;
    public BooleanSetting settingUseGapple;
    public BooleanSetting settingNotWhileKillAura;
    public BooleanSetting settingNotWhileScaffold;

    public AutoEat() {
        super("AutoEat", "AutomatischEssen", new Color(252, 186, 3), Category.PLAYER);
        this.eatTimer = new TimeHelper();
        this.keyCode2 = -1;
        this.tickDelay = -1;
        this.settingHunger = new RangeSetting(this, "Hunger", 20.0, 20.0, 0.0, 20.0, 0).getRangeSetting2();
        this.settingUseGapple = new BooleanSetting(this, "UseGapple", false);
        this.settingNotWhileKillAura = new BooleanSetting(this, "NotWhileKillAura", false);
        this.settingNotWhileScaffold = new BooleanSetting(this, "NotWhileScaffold", true);
    }

    @Override
    public void onEnable() {
        this.registerEvent(7);
        this.registerEvent(26);
        this.registerEvent(34);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.settingHunger.recalculate();
    }

    @Override
    public void onClick(EventClick event) {
        if (minecraft.thePlayer.inventoryPlayer.currentItem == this.keyCode2 || this.keyCode2 <= -1) {
            if (this.keyCode2 >= 0 && minecraft.isIntegratedServerRunning()) {
                event.setState11(true);
            } else if (this.tickDelay >= 0) {
                this.settingHunger.recalculate();
            }
        }
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        if (packet.getPacket() instanceof S07PacketRespawn) {
            this.eatTimer.reset();
        }
    }

    @Override
    public void onPostMotion() {
        this.tickDelay = this.keyCode2;
        this.keyCode2 = -1;
        if (this.canExecute5()) {
            double offset = -100.0;
            double offset2 = -100.0;
            int index = -1;
            int index2 = -1;
            int index3 = 0;
            ItemStack itemStack = minecraft.thePlayer.container.getSlot(36).getStack();

            while (true) {
                if (itemStack != null) {
                    Item item = itemStack.getItem();

                    outerLoop:
                    while (true) {
                        if (item instanceof ItemFood) {
                            ItemFood itemFood = (ItemFood)itemStack.getItem();
                            double coordinate = InventoryUtil.getCoordinate(itemStack);
                            if (itemFood.equals(Items.goldenApple)) {
                                if (coordinate > offset2) {
                                    offset2 = coordinate;
                                    index2 = index3;
                                }
                            } else {
                                if (coordinate > offset) {
                                    offset = coordinate;
                                    index = index3;
                                }
                            }
                        }

                        while (++index3 < 9) {
                            itemStack = minecraft.thePlayer.container.getSlot(index3 + 36).getStack();
                            if (itemStack != null) {
                                item = itemStack.getItem();
                                continue outerLoop;
                            }
                        }

                        int index4;
                        if (index == -1 && this.settingUseGapple.isEnabled()) {
                            index4 = index2;
                        } else {
                            index4 = index;
                        }

                        this.keyCode2 = index4;
                        if (!this.eatTimer.hasReached(250L)) {
                            this.keyCode2 = -1;
                        }

                        if (this.keyCode2 == -1) {
                            return;
                        }

                        int keyCode22 = this.keyCode2;
                        SlotManager.applyModule3(this, 3, keyCode22);
                        return;
                    }
                }

                if (++index3 >= 9) {
                    int index5;
                    if (index == -1 && this.settingUseGapple.isEnabled()) {
                        index5 = index2;
                    } else {
                        index5 = index;
                    }

                    this.keyCode2 = index5;
                    if (!this.eatTimer.hasReached(250L)) {
                        this.keyCode2 = -1;
                    }

                    if (this.keyCode2 == -1) {
                        return;
                    }

                    int keyCode23 = this.keyCode2;
                    SlotManager.applyModule3(this, 3, keyCode23);
                    break;
                }

                itemStack = minecraft.thePlayer.container.getSlot(index3 + 36).getStack();
            }
        }
    }

    private boolean canExecute5() {
        int index = minecraft.thePlayer.getFoodLevelManager().getIndex();
        int indexOffset2 = this.settingHunger.getIndexOffset2();
        if (index >= indexOffset2) {
            return false;
        } else if (minecraft.thePlayer.ticksExisted <= 20) {
            return false;
        } else if (!minecraft.isIntegratedServerRunning()) {
            return false;
        } else if (ClientGlobals.moduleManager.killAura.isToggled()
        && ClientGlobals.moduleManager.killAura.target != null
        && this.settingNotWhileKillAura.isEnabled()) {
            return false;
        } else {
            return !ClientGlobals.moduleManager.scaffold.isToggled() ? true : !this.settingNotWhileScaffold.isEnabled();
        }
    }
}
