package augustus.modules.legit;

import client.event.EventSlotClick;
import client.event.EventSlotClickCallback;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.RangeSetting;
import client.setting.Setting;
import client.util.AnimationTimer;
import client.util.BooleanSupplier;
import client.util.SmoothDamper;
import java.awt.Color;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Mouse;

public class InventoryClicker extends Module implements TickListener, EventSlotClickCallback {
    public RangeSetting settingCPS;
    public RangeSetting settingCPSRecalculateDelay;
    public RangeSetting settingDCPS;
    public RangeSetting settingDCPSRecalculateDelay;
    public SmoothDamper smoothDamper;
    public SmoothDamper smoothDamper2;
    public AnimationTimer animationTimer;
    public AnimationTimer animationTimer2;
    public BooleanSetting settingRightClick;
    public BooleanSetting settingForceClickOnItem;
    public BooleanSetting settingOnlyOnShiftClick;
    public BooleanSetting settingOnlyOnLeftClick;
    public boolean expanded2;

    public InventoryClicker() {
        super("InventoryClicker", "AutomatischesInventarClicken", new Color(119, 234, 247), Category.LEGIT);
        this.settingCPS = new RangeSetting(this, " CPS", 16.0, 17.0, 0.0, 40.0, 0);
        this.settingCPSRecalculateDelay = new RangeSetting(this, " CPS recalculate delay", 3.0, 8.0, 0.0, 30.0, 0)
            .getRangeSetting2();
        this.settingDCPS = new RangeSetting(this, " DCPS", 3.0, 5.0, 0.0, 20.0, 0);
        this.settingDCPSRecalculateDelay = new RangeSetting(this, " DCPS recalculate delay", 3.0, 8.0, 0.0, 30.0, 0)
            .getRangeSetting2();
        this.smoothDamper = new SmoothDamper(20, 0.0);
        this.smoothDamper2 = new SmoothDamper(20, 0.0);
        AnimationTimer animationTimer3 = new AnimationTimer();
        this.animationTimer = animationTimer3;
        this.animationTimer2 = new AnimationTimer();
        this.settingRightClick = new BooleanSetting(this, "RightClick", false);
        this.settingForceClickOnItem = new BooleanSetting(this, "ForceClickOnItem", false);
        this.settingOnlyOnShiftClick = new BooleanSetting(this, "OnlyOnShiftClick", true);
        this.settingOnlyOnLeftClick = (BooleanSetting)new BooleanSetting(this, "OnlyOnLeftClick", true).getSetting(() -> this.settingOnlyOnShiftClick.isEnabled());
        this.expanded2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(51);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onSlotClick(EventSlotClick event) {
        if (minecraft.currentScreen instanceof GuiContainer) {
            event.setActive(true);
        }

        GuiContainer tertiaryGuiScreenAdapter = (GuiContainer)minecraft.currentScreen;
        AnimationTimer animationTimer3 = this.animationTimer;
        int indexOffset2 = this.settingCPSRecalculateDelay.getIndexOffset2();
        byte state = ((byte)(animationTimer3.checkCondition(indexOffset2) ? 1 : 0));
        int y = Mouse.getY();
        Slot slot = this.getSlot(tertiaryGuiScreenAdapter, state, y);
        this.checkTertiaryGuiScreenAdapter(tertiaryGuiScreenAdapter, slot);
    }

    @Override
    public void onTick() {
        this.animationTimer.processAction();
        this.animationTimer2.processAction();
    }

    public void updateState() {
    }

    public void updateState2() {
    }

    private boolean checkTertiaryGuiScreenAdapter(GuiContainer tertiaryGuiScreenAdapter, Slot slot) {
        if (tertiaryGuiScreenAdapter instanceof GuiInventory && slot != null && !this.settingRightClick.isEnabled()) {
            ItemStack itemStack = slot.getStack();
            if (itemStack == null) {
                return false;
            } else {
                for (int i = 36; i < minecraft.thePlayer.container.itemList2.size(); i++) {
                    Slot slot2 = minecraft.thePlayer.container.getSlot(i);
                    if (slot.equals(slot2)) {
                        return false;
                    }
                }

                for (int i2 = 36; i2 < minecraft.thePlayer.container.itemList2.size(); i2++) {
                    ItemStack itemStack2 = minecraft.thePlayer.container.getSlot(i2).getStack();
                    if (itemStack2 == null) {
                        return false;
                    }

                    if (itemStack2.getItem().getClass().equals(itemStack.getItem().getClass()) && itemStack.canExecute2()) {
                        int animationsToGo = itemStack2.animationsToGo;
                        int index = itemStack2.processAction5();
                        if (animationsToGo < index) {
                            return false;
                        }
                    }
                }

                return true;
            }
        } else {
            return false;
        }
    }

    private Slot getSlot(GuiContainer tertiaryGuiScreenAdapter, int index2, int index3) {
        ScaledResolution scaledResolution = new ScaledResolution(minecraft);

        for (int i = 0; i < tertiaryGuiScreenAdapter.container.itemList2.size(); i++) {
            Slot slot = tertiaryGuiScreenAdapter.container.getSlot(i);
            int scaledWidth = scaledResolution.getScaledWidth();
            int scaledHeight = scaledResolution.getScaledHeight();
            int index = index2 * scaledWidth / minecraft.displayWidth;
            int index4 = scaledHeight - index3 * scaledHeight / minecraft.displayHeight - 1;
            if (tertiaryGuiScreenAdapter.checkSlot(slot, index, index4)) {
                return slot;
            }
        }

        return null;
    }
}
