package augustus.modules.player;

import client.Client;
import client.ClientGlobals;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.ActionSetting;
import client.setting.AutoSoupPerformRespawnType;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.SettingContainer;
import client.setting.SettingProvider;
import client.util.RayTraceUtil;
import client.util.TimeHelper;
import java.awt.Color;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C16PacketClientStatus;

public class ReverseAutoArmor extends Module implements TickListener {
    public NumberSetting settingStartDelay;
    public ActionSetting settingDelay;
    public BooleanSetting settingDrop;
    public BooleanSetting settingBot;
    public TimeHelper timer;
    public TimeHelper timer2;
    public long timestampMillis;
    public boolean expanded2;

    public ReverseAutoArmor() {
        super("ReverseAutoArmor", "RüstungAusziehen", new Color(252, 186, 3), Category.PLAYER);
        this.settingStartDelay = new NumberSetting(this, "StartDelay", 200.0, 0.0, 400.0, 0);
        this.settingDelay = new ActionSetting(this, "Delay", 70.0, 120.0, 0.0, 400.0, 0);
        this.settingDrop = new BooleanSetting(this, "Drop", false);
        this.settingBot = new BooleanSetting(this, "Bot", false);
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        this.expanded2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        if (minecraft.theWorld != null && Client.getClient().getModuleManager().invManager.isToggled()) {
            RayTraceUtil.applyString("ReverseAutoArmor works when InvManager is disabled!");
        }

        double double3 = this.settingDelay.getDouble3();
        double double6 = this.settingDelay.getDouble6();
        this.timestampMillis = (long)SettingContainer.settingProvider.getInt(double3, double6);
    }

    @Override
    public void onPreTick() {
        if (minecraft.currentScreen instanceof GuiInventory && !ClientGlobals.moduleManager.invManager.isToggled()) {
            TimeHelper timer22 = this.timer2;
            double value = this.settingStartDelay.getValue();
            if (timer22.hasReachedDouble(value)) {
                boolean enabled = true;
                int index = 5;

                while (index < 9) {
                    if (((Slot)minecraft.thePlayer.container.itemList2.get(index)).getStack() != null) {
                        enabled = false;
                        long timestampMillis2 = this.timestampMillis;
                        if (this.timer.hasReached(timestampMillis2)) {
                            double double3 = this.settingDelay.getDouble3();
                            double double6 = this.settingDelay.getDouble6();
                            this.timestampMillis = (long)SettingContainer.settingProvider.getInt(double3, double6);
                            ReverseAutoArmor reverseAutoArmor;
                            if (this.settingDrop.isEnabled()) {
                                minecraft.playerController.windowClick(minecraft.thePlayer.container.indexOffset2, index, 1, 4, minecraft.thePlayer);
                                if (this.timestampMillis != 0L) {
                                    return;
                                }

                                reverseAutoArmor = this;
                            } else {
                                GuiInventory advancedFloatEntry = (GuiInventory)minecraft.currentScreen;
                                Slot slot = advancedFloatEntry.container.getSlot(index);

                                try {
                                    advancedFloatEntry.state2 = true;
                                    advancedFloatEntry.processAction(slot.indexOffset + 2 + advancedFloatEntry.indexOffset1, slot.indexOffset2 + 2 + advancedFloatEntry.indexOffset2, 0);
                                } catch (IOException iOException) {
                                    throw new RuntimeException(iOException);
                                }

                                if (this.timestampMillis != 0L) {
                                    return;
                                }

                                reverseAutoArmor = this;
                            }

                            reverseAutoArmor.timer.reset();
                            index++;
                        } else {
                            index++;
                        }
                    } else {
                        index++;
                    }
                }

                if (this.settingBot.isEnabled() && enabled && this.expanded2) {
                    minecraft.displayGuiScreen((GuiScreen)null);
                    this.expanded2 = false;
                }
            }
        } else {
            this.timer2.reset();
            if (minecraft.currentScreen instanceof GuiInventory && this.expanded2) {
                minecraft.displayGuiScreen((GuiScreen)null);
                this.expanded2 = false;
            }
        }

        if (this.settingBot.isEnabled()) {
            if (minecraft.thePlayer.ticksExisted == 10) {
                if (minecraft.isIntegratedServerRunning()) {
                    int index2 = 5;
                    ItemStack itemStack = ((Slot)minecraft.thePlayer.container.itemList2.get(5)).getStack();

                    while (true) {
                        if (itemStack != null) {
                            minecraft.getNetHandlerPlayClient().handlePacket(new C16PacketClientStatus(AutoSoupPerformRespawnType.OPEN_INVENTORY_ACHIEVEMENT));
                            GuiInventory advancedFloatEntry2 = new GuiInventory(minecraft.thePlayer);
                            minecraft.displayGuiScreen(advancedFloatEntry2);
                            this.expanded2 = true;
                            break;
                        }

                        if (++index2 >= 9) {
                            break;
                        }

                        itemStack = ((Slot)minecraft.thePlayer.container.itemList2.get(index2)).getStack();
                    }
                }
            }
        }
    }
}
