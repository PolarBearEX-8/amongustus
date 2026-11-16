package augustus.modules.legit;

import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventKeyInput;
import client.event.KeyInputListener;
import client.event.MotionListener;
import client.module.Category;
import client.module.Module;
import client.setting.RangeSetting;
import client.setting.StringSetting;
import client.util.Pair;
import client.util.SlotManager;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class ThrowPots extends Module implements MotionListener, ClickListener, KeyInputListener {
    public RangeSetting settingDelayBetweenSwitchAndClick;
    public RangeSetting settingDelayToSwitchToOriginalSlot;
    public ArrayList potionQueue;
    public ArrayList itemList3;
    public int tickDelay;
    public int keyCode2;
    public boolean expanded2;

    public ThrowPots() {
        super("ThrowPots", "SchmeißeTränke", new Color(70, 139, 166), Category.LEGIT);
        this.settingDelayBetweenSwitchAndClick = new RangeSetting(this, "DelayBetweenSwitchAndClick", 1.0, 2.0, 0.0, 5.0, 0)
            .getRangeSetting2();
        this.settingDelayToSwitchToOriginalSlot = new RangeSetting(this, "DelayToSwitchToOriginalSlot", 1.0, 2.0, 0.0, 5.0, 0)
            .getRangeSetting2();
        this.potionQueue = new ArrayList();
        this.itemList3 = new ArrayList();
        this.tickDelay = 0;
        this.keyCode2 = 0;
        this.expanded2 = false;
        ArrayList itemList32 = this.itemList3;
        StringSetting stringSetting = new StringSetting(this, "RegenerationPotionKey");
        Pair pair = new Pair(stringSetting, 10);
        itemList32.add(pair);
        ArrayList itemList33 = this.itemList3;
        StringSetting stringSetting2 = new StringSetting(this, "SpeedPotionKey");
        Pair pair2 = new Pair(stringSetting2, 1);
        itemList33.add(pair2);
        ArrayList itemList34 = this.itemList3;
        StringSetting stringSetting3 = new StringSetting(this, "PoisonPotionKey");
        Pair pair3 = new Pair(stringSetting3, 19);
        itemList34.add(pair3);
        ArrayList itemList35 = this.itemList3;
        StringSetting stringSetting4 = new StringSetting(this, "FireResistancePotionKey");
        Pair pair4 = new Pair(stringSetting4, 12);
        itemList35.add(pair4);
        ArrayList itemList36 = this.itemList3;
        StringSetting stringSetting5 = new StringSetting(this, "InstantHealthPotionKey");
        Pair pair5 = new Pair(stringSetting5, 6);
        itemList36.add(pair5);
        ArrayList itemList37 = this.itemList3;
        StringSetting stringSetting6 = new StringSetting(this, "NightVisionPotionKey");
        Pair pair6 = new Pair(stringSetting6, 16);
        itemList37.add(pair6);
        ArrayList itemList38 = this.itemList3;
        StringSetting stringSetting7 = new StringSetting(this, "WeaknessPotionKey");
        Pair pair7 = new Pair(stringSetting7, 18);
        itemList38.add(pair7);
        ArrayList itemList39 = this.itemList3;
        StringSetting stringSetting8 = new StringSetting(this, "StrengthPotionKey");
        Pair pair8 = new Pair(stringSetting8, 5);
        itemList39.add(pair8);
        ArrayList itemList310 = this.itemList3;
        StringSetting stringSetting9 = new StringSetting(this, "SlownessPotionKey");
        Pair pair9 = new Pair(stringSetting9, 2);
        itemList310.add(pair9);
        ArrayList itemList311 = this.itemList3;
        StringSetting stringSetting10 = new StringSetting(this, "JumpBoostPotionKey");
        Pair pair10 = new Pair(stringSetting10, 8);
        itemList311.add(pair10);
        ArrayList itemList312 = this.itemList3;
        StringSetting stringSetting11 = new StringSetting(this, "InstantDamagePotionKey");
        Pair pair11 = new Pair(stringSetting11, 7);
        itemList312.add(pair11);
        ArrayList itemList313 = this.itemList3;
        StringSetting stringSetting12 = new StringSetting(this, "WaterBreathingPotionKey");
        Pair pair12 = new Pair(stringSetting12, 13);
        itemList313.add(pair12);
        ArrayList itemList314 = this.itemList3;
        StringSetting stringSetting13 = new StringSetting(this, "InvisibilityPotionKey");
        Pair pair13 = new Pair(stringSetting13, 14);
        itemList314.add(pair13);
    }

    @Override
    public void onEnable() {
        this.registerEvent(7);
        this.registerEvent(34);
        this.registerEvent(14);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.updateState();
    }

    @Override
    public void onClick(EventClick event) {
        this.tickDelay++;
        this.keyCode2++;
        this.potionQueue.clear();
        this.updateState();
    }

    @Override
    public void onKeyInput(EventKeyInput event) {
        event.getIndex();
        Iterator iterator = this.itemList3.iterator();
        if (iterator.hasNext()) {
            Pair pair = (Pair)iterator.next();
            if (pair != null) {
                this.potionQueue.add(pair);
            }
        }
    }

    @Override
    public void onPostMotion() {
        if (!this.potionQueue.isEmpty()) {
            if (this.canExecute5()) {
                Pair pair = (Pair)this.potionQueue.get(0);
                if (minecraft.thePlayer.inventoryPlayer.currentItem != (Integer)pair.getFirst()) {
                    this.updateState();
                }

                int first = (Integer)pair.getFirst();
                SlotManager.applyModule3(this, 0, first);
            }
        }
    }

    private void updateState() {
        this.keyCode2 = 0;
        this.tickDelay = 0;
        this.expanded2 = false;
    }

    private boolean canExecute5() {
        return minecraft.isIntegratedServerRunning();
    }

    private Pair getPair() {
        minecraft.thePlayer.container.getSlot(36).getStack();
        return null;
    }
}
