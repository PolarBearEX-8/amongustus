package augustus.modules.legit;

import client.event.ClickListener;
import client.event.EventClick;
import client.event.MotionListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.util.SlotManager;
import java.awt.Color;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;

public class AutoWater extends Module implements ClickListener, MotionListener {
    public BooleanSetting settingAutoSlot;
    public int tickDelay;
    public int keyCode2;
    public BlockPos blockPos;

    public AutoWater() {
        super("AutoWater", "AutomatischesWasser", new Color(66, 135, 245), Category.LEGIT);
        this.settingAutoSlot = new BooleanSetting(this, "AutoSlot", true);
        this.tickDelay = -1;
        this.blockPos = (BlockPos)null;
    }

    @Override
    public void onEnable() {
        this.registerEvent(7);
        this.registerEvent(34);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onClick(EventClick event) {
        if (this.tickDelay > -1) {
            event.updateInt2(1);
            this.keyCode2 = 0;
        }
    }

    @Override
    public void onPostMotion() {
        this.tickDelay = -1;
        this.blockPos = minecraft.objectMouseOver.getBlockPos();
        this.settingAutoSlot.isEnabled();
        int tickDelay2 = this.tickDelay;
        SlotManager.applyModule3(this, 5, tickDelay2);
    }

    private int getInt2(int index) {
        Item item = minecraft.thePlayer.container.getSlot(index).getStack().getItem();
        Item item2 = Item.processAction(326);
        if (item == item2) {
            return Float.compare(minecraft.thePlayer.fallDistance, 1.0F);
        } else {
            return -1;
        }
    }

    private int getInt3() {
        if (36 < minecraft.thePlayer.container.itemList2.size()) {
            minecraft.thePlayer.container.getSlot(36).getStack();
        }

        return -1;
    }
}
