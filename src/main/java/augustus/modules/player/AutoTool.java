package augustus.modules.player;

import client.event.MotionListener;
import client.module.Category;
import client.module.Module;
import client.network.CosmeticsPayload;
import client.setting.BooleanSetting;
import client.setting.RangeSetting;
import client.util.EnchantmentManager;
import client.util.LvlManager;
import client.util.MovingObjectType;
import client.util.SlotManager;
import java.awt.Color;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemGoldenAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.MovingObjectPosition;

public class AutoTool extends Module implements MotionListener {
    public BooleanSetting settingTools;
    public BooleanSetting settingSword;
    public RangeSetting settingDelayBeforeSwitch;
    public RangeSetting settingDelayAtItemSwitch;
    public RangeSetting settingBackSwitchDelay;
    public BooleanSetting settingOnlyAtSneak;
    public BooleanSetting settingSilent;
    public int keyCode2;
    public int tickDelay1;
    public int tickDelay2;
    public int tickDelay3;
    public int tickDelay;
    public boolean expanded2;
    public static boolean activeState;

    public AutoTool() {
        super("AutoTool", "AutoWerkzeug", new Color(237, 237, 0), Category.PLAYER);
        this.settingTools = new BooleanSetting(this, "Tools", true);
        this.settingSword = new BooleanSetting(this, "Sword", false);
        this.settingDelayBeforeSwitch = new RangeSetting(this, "DelayBeforeSwitch", 0.0, 0.0, 0.0, 5.0, 0);
        this.settingDelayAtItemSwitch = new RangeSetting(this, "DelayAtItemSwitch", 0.0, 0.0, 0.0, 5.0, 0);
        this.settingBackSwitchDelay = new RangeSetting(this, "BackSwitchDelay", 2.0, 3.0, 0.0, 20.0, 0);
        this.settingOnlyAtSneak = new BooleanSetting(this, "OnlyAtSneak", false);
        this.settingSilent = new BooleanSetting(this, "Silent", true);
        this.keyCode2 = 0;
        this.tickDelay1 = 0;
        this.tickDelay2 = 0;
        this.tickDelay3 = 0;
        this.expanded2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(34);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        if (minecraft.theWorld != null) {
            this.keyCode2 = -1;
            this.tickDelay1 = 0;
            this.tickDelay2 = 0;
            this.tickDelay3 = 0;
            this.settingDelayBeforeSwitch.recalculate();
            this.settingDelayAtItemSwitch.recalculate();
            this.settingBackSwitchDelay.recalculate();
        }
    }

    @Override
    public void onPostMotion() {
        MovingObjectPosition objectMouseOver = minecraft.objectMouseOver;
        byte state;
        if (objectMouseOver == null) {
            state = -1;
        } else if (!minecraft.playerController.canExecute()) {
            state = -1;
        } else {
            ItemStack itemStack;
            if (this.settingSilent.isEnabled()) {
                itemStack = SlotManager.itemStack;
            } else {
                Container container = minecraft.thePlayer.container;
                int tickDelay4 = this.tickDelay;
                itemStack = container.getSlot(tickDelay4).getStack();
            }

            if (!minecraft.thePlayer.isUsingItem()) {
                state = -1;
            } else {
                MovingObjectType typeOfHit = objectMouseOver.typeOfHit;
                MovingObjectType movingObjectTypeKind = MovingObjectType.ENTITY;
                if (typeOfHit != movingObjectTypeKind) {
                    state = -1;
                } else if (minecraft.gameSettings.keyBindAttack.getPressTime() <= 0 && !minecraft.gameSettings.keyBindAttack.pressed) {
                    state = -1;
                } else {
                    state = -1;
                    int index = 36;

                    while (true) {
                        int size = minecraft.thePlayer.container.itemList2.size();
                        if (index >= size) {
                            break;
                        }

                        ItemStack itemStack2 = minecraft.thePlayer.container.getSlot(index).getStack();
                        if (itemStack2 != null) {
                            this.getCoordinate(itemStack2);
                            this.getCoordinate(itemStack);
                        } else {
                            index++;
                        }
                    }
                }
            }
        }

        byte state2 = ((byte)(CosmeticsPayload.checkCondition(1) ? 1 : 0));
        if (state > 0) {
            if (state2 != 0) {
            } else {
                int indexOffset2 = this.settingDelayAtItemSwitch.getIndexOffset2();
            }

            this.tickDelay3 = this.tickDelay3 + 1;
            state = 0;
        } else {
            this.tickDelay3 = 0;
        }

        int index3;
        if (state != 0) {
            index3 = (minecraft.gameSettings.keyBindSneak.isKeyDown() ? 1 : 0);
            state2 = 0;
        } else {
            index3 = this.tickDelay2;
        }

        int index4 = state2 + 0;
        if (index3 < index4) {
            this.keyCode2 = -1;
        }

        if (this.keyCode2 < 0) {
            this.tickDelay = SlotManager.indexOffset;
        }

        this.tickDelay1 = this.tickDelay1 + 1;
    }

    private double getCoordinate(ItemStack itemStack) {
        double offset = 0.0;
        if (itemStack.getItem() instanceof ItemSword) {
            offset = offset
                + (double)(
                    ((ItemTool)itemStack.getItem()).getScaleFactor()
                        + (float)LvlManager.getIndex(EnchantmentManager.enchantmentManager2.indexOffset, itemStack) * 1.25F
                )
                + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager3.indexOffset, itemStack) / 11.0
                + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager.indexOffset, itemStack) / 11.0
                + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager1.indexOffset, itemStack) / 11.0
                - (double)itemStack.getIndexOffset2() / 10000.0;
        }

        return offset;
    }

    private double getCoordinate(ItemStack itemStack, MovingObjectPosition hitResult) {
        double offset = (double)itemStack.getItem().processAction(itemStack, minecraft.theWorld.getBlockState(hitResult.getBlockPos()).getBlock());
        if (itemStack.getItem() instanceof ItemGoldenAxe) {
            double offset2;
            if ((offset2 = offset - 1.0) == 0.0) {
            } else if (offset2 < 0.0) {
            } else {
            }
        }

        return offset
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager1.indexOffset, itemStack) / 11.0
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager4.indexOffset, itemStack) / 11.0
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager2.indexOffset, itemStack) / 33.0
            - (double)itemStack.getIndexOffset2() / 10000.0;
    }

    private void processShort() {
        this.tickDelay1 = 0;
    }
}
