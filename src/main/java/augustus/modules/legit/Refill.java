package augustus.modules.legit;

import augustus.custom.RuntimeBridge;
import client.event.ClickTickListener;
import client.event.MotionListener;
import client.event.RenderListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.RefillPotionType;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.InventoryUtil;
import client.util.SmoothDamper;
import client.util.TimeHelper;
import client.via.ViaLoadingBase;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class Refill extends Module implements MotionListener, ClickTickListener, RenderListener {
    public BooleanSetting settingAutoOpen;
    public RangeSetting settingAutoOpenDelay;
    public BooleanSetting settingAutoClose;
    public RangeSetting settingAutoCloseDelay;
    public RangeSetting settingStartDelay;
    public MultiSelectSetting settingTypes;
    public BooleanSetting settingBlockInvActions;
    public SettingGroup settingCraftGroup;
    public BooleanSetting settingCraft;
    public NumberSetting settingReCraftIfSoupsLowerThan;
    public BooleanSetting settingCraftInstant;
    public NumberSetting settingMaxMouseMoveSpeedCraft;
    public RangeSetting settingCraftCPS;
    public SettingGroup settingRefillGroup;
    public BooleanSetting settingRefillInstant;
    public NumberSetting settingMaxMouseMoveSpeedRefill;
    public RangeSetting settingRefillCPS;
    public RangeSetting settingMissClickRate;
    public RangeSetting settingFailRate;
    public RangeSetting settingDoubleClickRate;
    public ModeSetting settingSequence;
    public TimeHelper timer;
    public SmoothDamper smoothDamper;
    public SmoothDamper smoothDamper2;
    public SmoothDamper smoothDamper3;
    public int tickDelay;
    public SmoothDamper smoothDamper4;
    public double reachDistance;
    public double reachDistance1;
    public boolean expanded2;
    public TimeHelper timer2;
    public TimeHelper timer3;
    public boolean enabled2;
    public TimeHelper timer4;

    public Refill() {
        super("Refill", "Auffüllen", new Color(66, 203, 245), Category.LEGIT);
        this.settingAutoOpen = new BooleanSetting(this, "AutoOpen", false);
        this.settingAutoOpenDelay = new RangeSetting(this, "AutoOpenDelay", 150.0, 250.0, 0.0, 1000.0, 0)
        .getRangeSetting2().getRangeSetting(15343, () -> this.settingAutoOpen.isEnabled());
        this.settingAutoClose = new BooleanSetting(this, "AutoClose", true);
        this.settingAutoCloseDelay = new RangeSetting(this, "AutoCloseDelay", 150.0, 250.0, 0.0, 1000.0, 0)
        .getRangeSetting2().getRangeSetting(15343, () -> this.settingAutoClose.isEnabled());
        this.settingStartDelay = new RangeSetting(this, "StartDelay", 200.0, 260.0, 0.0, 1000.0, 0)
        .getRangeSetting2();
        RefillPotionType[] refillPotionTypeArray = RefillPotionType.values();
        this.settingTypes = new MultiSelectSetting(this, "Types", refillPotionTypeArray);
        this.settingBlockInvActions = new BooleanSetting(this, "BlockInvActions", false);
        SettingGroup settingGroup = new SettingGroup(this, "CraftGroup", true);
        this.settingCraftGroup = ((SettingGroup)settingGroup.getSetting("Craft")).getSettingGroup(() -> RefillPotionType.SOUPS.isSelected());
        this.settingCraft = new BooleanSetting(this, "Craft", true, this.settingCraftGroup);
        this.settingReCraftIfSoupsLowerThan = new NumberSetting(this, "ReCraftIfSoupsLowerThan", 4.0, 0.0, 20.0, 0, this.settingCraftGroup).getNumberSetting(() -> this.settingCraft.isEnabled());
        this.settingCraftInstant = (BooleanSetting)new BooleanSetting(this, "CraftInstant", false, this.settingCraftGroup).getSetting(() -> this.settingCraft.isEnabled());
        this.settingMaxMouseMoveSpeedCraft = new NumberSetting(this, "MaxMouseMoveSpeedCraft", 19.0, 0.0, 200.0, 0, this.settingCraftGroup).getNumberSetting("MaxMouseMoveSpeed").getNumberSetting(() -> this.isMaxMouseMoveSpeedCraftVisible());
        this.settingCraftCPS = new RangeSetting(this, "CraftCPS", 13.0, 16.0, 0.0, 20.0, 0, this.settingCraftGroup).getRangeSetting2().getRangeSetting(15343, () -> this.isCraftCPSVisible());
        this.settingRefillGroup = (SettingGroup)new SettingGroup(this, "RefillGroup", true).getSetting("Refill");
        this.settingRefillInstant = new BooleanSetting(this, "RefillInstant", false, this.settingRefillGroup);
        this.settingMaxMouseMoveSpeedRefill = new NumberSetting(this, "MaxMouseMoveSpeedRefill", 19.0, 0.0, 200.0, 0, this.settingRefillGroup).getNumberSetting("MaxMouseMoveSpeed").getNumberSetting(() -> this.isMaxMouseMoveSpeedRefillVisible());
        this.settingRefillCPS = new RangeSetting(this, "RefillCPS", 10.0, 15.0, 0.0, 20.0, 0, this.settingRefillGroup).getRangeSetting2().getRangeSetting(15343, () -> this.isRefillCPSVisible());
        this.settingMissClickRate = new RangeSetting(this, "MissClickRate", 5.0, 10.0, 0.0, 100.0, 0, this.settingRefillGroup).getRangeSetting2();
        this.settingFailRate = new RangeSetting(this, "FailRate", 5.0, 10.0, 0.0, 100.0, 0, this.settingRefillGroup).getRangeSetting2();
        this.settingDoubleClickRate = new RangeSetting(this, "DoubleClickRate", 5.0, 10.0, 0.0, 100.0, 0, this.settingRefillGroup).getRangeSetting2();
        String[] textArray = new String[3];
        textArray[0] = "Basic";
        textArray[1] = "Nearest";
        textArray[2] = "Random";
        this.settingSequence = new ModeSetting(this, "Sequence", "Basic", textArray, this.settingRefillGroup);
        this.timer = new TimeHelper();
        this.smoothDamper = new SmoothDamper(100, 0.0);
        this.smoothDamper2 = new SmoothDamper(100, 0.0);
        this.smoothDamper3 = new SmoothDamper(100, 0.0);
        this.tickDelay = -1;
        this.smoothDamper4 = new SmoothDamper(20, 0.0);
        this.reachDistance = 0.0;
        this.reachDistance1 = 0.0;
        this.expanded2 = false;
        this.timer2 = new TimeHelper();
        this.timer3 = new TimeHelper();
        this.enabled2 = true;
        this.timer4 = new TimeHelper();
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(52, 2);
        this.registerEvent(34);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.expanded2 = false;
        this.settingRefillCPS.recalculate();
        this.settingMissClickRate.recalculate();
        this.settingDoubleClickRate.recalculate();
        this.settingFailRate.recalculate();
        this.settingStartDelay.recalculate();
        this.settingAutoCloseDelay.recalculate();
        this.enabled2 = true;
    }

    @Override
    public boolean checkCondition() {
        return RuntimeBridge.canClickInventory();
    }

    @Override
    public void onPostMotion() {
        if (this.settingAutoOpen.isEnabled() && minecraft.currentScreen == null && !minecraft.playerController.processAction2()) {
            int size = RefillPotionType.SOUPS.getList(36, minecraft.thePlayer.container.itemList2.size()).size();
            int size2 = RefillPotionType.HEAL_POTS.getList(36, minecraft.thePlayer.container.itemList2.size()).size();
            int index = RefillPotionType.SOUPS.isSelected() ? RefillPotionType.SOUPS.getList(9, 36).size() : 0;
            int index2 = RefillPotionType.HEAL_POTS.isSelected() ? RefillPotionType.HEAL_POTS.getList(9, 36).size() : 0;
            int index3;
            if (RefillPotionType.SOUPS.isSelected()) {
                Item[] itemArray = new Item[]{Items.bowl};
                int size3 = minecraft.thePlayer.container.itemList2.size();
                Container container = minecraft.thePlayer.container;
                index3 = InventoryUtil.getIndex(container, 0, size3, itemArray);
            } else {
                index3 = 0;
            }

            int index4;
            if (RefillPotionType.SOUPS.isSelected()) {
                Item[] itemArray2 = new Item[]{Item.processAction(40)};
                int size4 = minecraft.thePlayer.container.itemList2.size();
                Container container2 = minecraft.thePlayer.container;
                index4 = InventoryUtil.getIndex(container2, 0, size4, itemArray2);
            } else {
                index4 = 0;
            }

            int index5;
            if (RefillPotionType.SOUPS.isSelected()) {
                Item[] itemArray3 = new Item[]{Item.processAction(39)};
                int size5 = minecraft.thePlayer.container.itemList2.size();
                Container container3 = minecraft.thePlayer.container;
                index5 = InventoryUtil.getIndex(container3, 0, size5, itemArray3);
            } else {
                index5 = 0;
            }

            boolean completed2 = false;
            int index6 = 0;
            boolean completed = false;
            if (size == 0) {
                index6 = index;
                if (index > 0) {
                    completed = true;
                }
                if (!completed) {
                    if (index5 > 0 && index4 > 0 && index3 > 0) {
                        index6 = (this.settingCraft.isEnabled() ? 1 : 0);
                        if (index6 != 0) {
                            completed = true;
                        }
                    }
                }
            }
            if (!completed) {
                if (size2 != 0) {
                    completed2 = true;
                }
                if (!completed2) {
                    index6 = index2;
                    if (index2 <= 0) {
                        completed2 = true;
                    }
                }
            }
            if (!completed2) {
                int index7 = 36;
                while (index7 < minecraft.thePlayer.container.itemList2.size()) {
                    ItemStack itemStack = minecraft.thePlayer.container.getSlot(index7).getStack();
                    if (itemStack != null) {
                        Item bowl = Items.bowl;
                        if (itemStack.getItem() != bowl) {
                            index7++;
                            continue;
                        }
                    }

                    index6 = 1;
                    break;
                }
                if (index6 != 0 && this.timer2.hasReached((long)this.settingAutoOpenDelay.getIndexOffset2())) {
                    ViaLoadingBase.processAction(6105, 60385, (char)21074);
                    minecraft.displayGuiScreen(new GuiInventory(minecraft.thePlayer));
                    this.timer2.reset();
                    this.settingAutoOpenDelay.recalculate();
                }
                return;
            }


            this.timer2.reset();
        } else {
            this.timer2.reset();
        }
    }

    private int getIndex(double offset, Container container, int index, int index2) {
        Slot slot = container.getSlot(index);
        ScaledResolution scaledResolution = new ScaledResolution(minecraft);
        int index3 = (scaledResolution.getScaledWidth() - 176) / 2;
        int index4 = (scaledResolution.getScaledHeight() - this.getIndex(container)) / 2;
        double offset2 = Math.abs((double)(slot.indexOffset + 2 + index3) - InventoryUtil.getCoordOffsetArray()[0]);
        double offset3 = Math.abs((double)(slot.indexOffset2 + 2 + index4) - InventoryUtil.getCoordOffsetArray()[1]);
        double offset4 = Math.sqrt(offset2 * offset2 + offset3 * offset3);
        return (int)(offset == offset4 ? 0.0 : (double)(offset > offset4 ? 1 : -1));
    }

    private void applyContainer(Container container, int index, int index2) {
        minecraft.playerController.windowClick(container.indexOffset2, index, index2, 0, minecraft.thePlayer);
        this.applyContainer2(container, index);
        this.expanded2 = true;
    }

    private int[] getIndexOffsetArray(Container container) {
        int[] countArray = new int[5];
        int index = -1;

        while (true) {
            int size = container.itemList2.size();
            if (index >= size) {
                return countArray;
            }

            if (index == -1) {
                ItemStack itemStack = minecraft.thePlayer.inventoryPlayer.getItemStack();
                if (itemStack == null) {
                    if (index > 8) {
                        countArray[0]++;
                    }
                } else {
                    Item item = itemStack.getItem();
                    if ((byte)(item.equals(Items.bowl) ? 1 : 0) != 0) {
                        countArray[1] += itemStack.animationsToGo;
                    } else if ((byte)(item.equals(Item.processAction(40)) ? 1 : 0) != 0) {
                        countArray[2] += itemStack.animationsToGo;
                    } else if ((byte)(item.equals(Item.processAction(39)) ? 1 : 0) != 0) {
                        countArray[3] += itemStack.animationsToGo;
                    } else if (item.equals(Items.mushroomStew) && index != 0) {
                        countArray[4]++;
                    }
                }

                index++;
            } else {
                ItemStack itemStack2 = container.getSlot(index).getStack();
                if (itemStack2 == null) {
                    if (index > 8) {
                        countArray[0]++;
                    }
                } else {
                    Item item2 = itemStack2.getItem();
                    if ((byte)(item2.equals(Items.bowl) ? 1 : 0) != 0) {
                        countArray[1] += itemStack2.animationsToGo;
                    } else if ((byte)(item2.equals(Item.processAction(40)) ? 1 : 0) != 0) {
                        countArray[2] += itemStack2.animationsToGo;
                    } else if ((byte)(item2.equals(Item.processAction(39)) ? 1 : 0) != 0) {
                        countArray[3] += itemStack2.animationsToGo;
                    } else if (item2.equals(Items.mushroomStew) && index != 0) {
                        countArray[4]++;
                    }
                }

                index++;
            }
        }
    }

    private void updateState() {
        this.reachDistance1 = 0.0;
        this.reachDistance = 0.0;
    }

    private void processAction(int index) {
        this.applyContainer2(minecraft.thePlayer.container, index);

        try {
            Slot slot = minecraft.thePlayer.container.getSlot(index);
            GuiInventory advancedFloatEntry = (GuiInventory)minecraft.currentScreen;
            advancedFloatEntry.state2 = true;
            advancedFloatEntry.processAction(slot.indexOffset + 2 + advancedFloatEntry.indexOffset1, slot.indexOffset2 + 2 + advancedFloatEntry.indexOffset2, 0);
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }

        this.expanded2 = true;
    }

    private void applyContainer2(Container container, int index) {
        this.updateState();
        ScaledResolution scaledResolution = new ScaledResolution(minecraft);
        int index2 = (scaledResolution.getScaledWidth() - 176) / 2;
        int index3 = (scaledResolution.getScaledHeight() - this.getIndex(container)) / 2;
        Slot slot = container.getSlot(index);
        double[] coordOffsets = InventoryUtil.getCoordOffsetArray();
        if (coordOffsets != null && coordOffsets.length >= 2) {
            coordOffsets[0] = (double)(slot.indexOffset + 2 + index2);
            coordOffsets[1] = (double)(slot.indexOffset2 + 2 + index3);
        }
        this.timer4.reset();
    }

    private boolean checkCondition(int index) {
        byte state = ((byte)(this.settingRefillInstant.isEnabled() ? 1 : 0));
        return this.getIndex(this.reachDistance, minecraft.thePlayer.container, index, state) != 0;
    }

    private void processAction4(int index) {
        this.expanded2 = true;
        int index2 = (this.canExecute8() ? 1 : 0);
        if (index2 > 0) {
            this.processAction(index);
            this.tickDelay = index;
            index2--;
        }

        while (true) {
        }
    }

    public boolean canExecute5() {
        return minecraft.theWorld != null ? this.settingBlockInvActions.isEnabled() : false;
    }

    private int getIndex(Container container) {
        return 114 + (container.itemList2.size() - 36) / 9 * 18;
    }

    private boolean checkCondition2(int index) {
        byte state = ((byte)(this.settingCraftInstant.isEnabled() ? 1 : 0));
        return this.getIndex(this.reachDistance1, minecraft.thePlayer.container, index, state) != 0;
    }

    private boolean canExecute6() {
        return this.smoothDamper.checkCondition(this.settingMissClickRate.getIndexOffset2());
    }

    private boolean isCraftCPSVisible() {
        return this.settingCraft.isEnabled();
    }

    private boolean checkCondition6() {
        return this.smoothDamper3.checkCondition(this.settingDoubleClickRate.getIndexOffset2());
    }

    private boolean canExecute7() {
        this.reachDistance1 = this.reachDistance1 + this.settingMaxMouseMoveSpeedCraft.getValue();
        if (!this.checkCondition7()) {
            return true;
        } else {
            this.reachDistance = this.reachDistance + this.settingMaxMouseMoveSpeedRefill.getValue();
            int index3 = 9;

            boolean enabled;
            while (true) {
                if (index3 < 36) {
                    ItemStack itemStack = minecraft.thePlayer.container.getSlot(index3).getStack();
                    boolean enabled3;
                    if (itemStack != null) {
                        if (!itemStack.getItem().equals(Items.bowl)) {
                            index3++;
                            continue;
                        }

                        int animationsToGo = itemStack.animationsToGo;
                        int index4 = itemStack.processAction5();
                        if (animationsToGo >= index4) {
                            index3++;
                            continue;
                        }

                        enabled3 = true;
                    } else {
                        enabled3 = true;
                    }

                    enabled = enabled3;
                    break;
                }

                enabled = false;
                break;
            }

            int index5;
            if (!enabled) {
                index5 = 0;
            } else {
                int index6 = 36;
                if (36 < minecraft.thePlayer.container.itemList2.size()) {
                    ItemStack itemStack2 = minecraft.thePlayer.container.getSlot(index6).getStack();

                    while (true) {
                        if (itemStack2 != null) {
                            if (!itemStack2.getItem().equals(Items.bowl)) {
                                index6++;
                            } else {
                                if (this.checkCondition(index6)) {
                                    return true;
                                }

                                this.processAction4(index6);
                                index6++;
                            }
                        } else {
                            index6++;
                        }

                        if (index6 >= minecraft.thePlayer.container.itemList2.size()) {
                            break;
                        }

                        itemStack2 = minecraft.thePlayer.container.getSlot(index6).getStack();
                    }
                }

                index5 = 0;
            }

            for (int i = 36; i < minecraft.thePlayer.container.itemList2.size(); i++) {
                if (minecraft.thePlayer.container.getSlot(i).getStack() == null) {
                    index5++;
                }
            }

            ArrayList arrayList = new ArrayList();
            int index = index5;
            RefillPotionType[] refillPotionTypeArray = RefillPotionType.values();
            int length = refillPotionTypeArray.length;

            for (int i2 = 0; i2 < length; i2++) {
                for (int index2 : ((Iterable<Integer>) refillPotionTypeArray[i2].getList(9, 36))) {
                    index--;
                    arrayList.add(index2);
                }

                if (index != index5) {
                    break;
                }
            }

            ArrayList arrayList2 = InventoryUtil.getArrayList(
            minecraft.thePlayer.container, arrayList, this.settingSequence.getSelected()
            );
            int index7 = 0;

            boolean enabled4;
            while (true) {
                if (index7 < arrayList2.size()) {
                    if (index5 > 0) {
                        Object object = arrayList2.get(index7);
                        int index8 = (Integer)object;
                        if (this.checkCondition(index8)) {
                            return true;
                        }

                        this.processAction4(index8);
                        index5--;
                        index7++;
                        continue;
                    }

                    enabled4 = this.settingAutoClose.isEnabled();
                    break;
                }

                enabled4 = this.settingAutoClose.isEnabled();
                break;
            }

            if (enabled4 && !minecraft.isIntegratedServerRunning() && this.expanded2) {
                if (this.enabled2) {
                    this.timer3.reset();
                    this.enabled2 = false;
                    this.settingAutoCloseDelay.recalculate();
                }

                if (this.timer3.hasReached((long)this.settingAutoCloseDelay.getIndexOffset2())) {
                    minecraft.thePlayer.updateState5();
                    this.timer3.reset();
                    this.enabled2 = true;
                }

                return true;
            } else {
                return false;
            }
        }
    }

    private boolean isMaxMouseMoveSpeedCraftVisible() {
        return this.settingCraft.isEnabled();
    }

    private boolean isRefillCPSVisible() {
        return this.settingRefillInstant.isEnabled();
    }

    private boolean isMaxMouseMoveSpeedRefillVisible() {
        return this.settingRefillInstant.isEnabled();
    }

    private boolean checkCondition7() {
        if (!RefillPotionType.SOUPS.isSelected()) {
            return true;
        } else if (minecraft.currentScreen instanceof GuiInventory && this.settingCraft.isEnabled()) {
            int[] indexOffsetArray = this.getIndexOffsetArray(minecraft.thePlayer.container);
            if (indexOffsetArray[1] != 0 && indexOffsetArray[2] != 0 && indexOffsetArray[3] != 0) {
                int index8 = Math.min(indexOffsetArray[1], Math.min(indexOffsetArray[2], indexOffsetArray[3]));
                int index9 = indexOffsetArray[0];
                if (index8 <= 0) {
                    return true;
                } else {
                    int index10 = index8 > index9 ? 1 : 0;
                    Item[] itemArray = new Item[]{Items.bowl, Item.processAction(40), Item.processAction(39)};
                    boolean[] conditionArray = new boolean[]{false, false, false};
                    boolean enabled = false;
                    int index11 = 1;

                    while (true) {
                        int size = minecraft.thePlayer.container.itemList2.size();
                        if (index11 >= size) {
                            if (!enabled) {
                                return true;
                            }

                            return false;
                        }

                        if (index11 == 1) {
                            conditionArray = new boolean[]{false, false, false};
                            index9 = this.getIndexOffsetArray(minecraft.thePlayer.container)[0];
                        }

                        ItemStack itemStack = minecraft.thePlayer.container.getSlot(index11).getStack();
                        if (itemStack != null) {
                            Item item = itemStack.getItem();
                            if (index11 < 5) {
                                for (int i = 0; i < itemArray.length; i++) {
                                    if (itemArray[i].equals(item)) {
                                        conditionArray[i] = true;
                                    }
                                }
                            }

                            if (index9 > 0
                            && (double)indexOffsetArray[4] <= this.settingReCraftIfSoupsLowerThan.getValue()) {
                                if (minecraft.thePlayer.inventoryPlayer.getItemStack() != null) {
                                    Item item2 = minecraft.thePlayer.inventoryPlayer.getItemStack().getItem();

                                    for (int i2 = 0; i2 < 3; i2++) {
                                        boolean enabled3 = item2.equals(itemArray[i2]);
                                        if (enabled3 && !conditionArray[i2]) {
                                            if (this.checkCondition2(i2 + 1)) {
                                                return false;
                                            }

                                            this.applyContainer(minecraft.thePlayer.container, i2 + 1, 0);
                                            enabled = true;
                                            index11 = 0;
                                        }
                                    }
                                } else {
                                    if (index11 > 8
                                    && (item.equals(itemArray[0]) && !conditionArray[0] || item.equals(itemArray[1]) && !conditionArray[1] || item.equals(itemArray[2]) && !conditionArray[2])) {
                                        if (this.checkCondition2(index11)) {
                                            return false;
                                        }

                                        this.applyContainer(minecraft.thePlayer.container, index11, index10);
                                        enabled = true;
                                        index11 = 0;
                                    }

                                    if (conditionArray[0] && conditionArray[1] && conditionArray[2]) {
                                        if (this.checkCondition2(0)) {
                                            return false;
                                        }

                                        this.processAction(0);
                                        enabled = true;
                                        index11 = 0;
                                    }
                                }
                            } else if (index11 > 5) {
                                for (int i3 = 0; i3 < itemArray.length; i3++) {
                                    if (conditionArray[i3]) {
                                        ItemStack itemStack2 = null;
                                        int index = 0;

                                        for (int i4 = 0; i4 < 5; i4++) {
                                            ItemStack itemStack3 = minecraft.thePlayer.container.getSlot(i4).getStack();
                                            if (itemStack3 != null && itemStack3.getItem().equals(itemArray[i3])) {
                                                itemStack2 = itemStack3;
                                                index = i4;
                                                break;
                                            }
                                        }

                                        for (int i5 = 9; i5 < minecraft.thePlayer.container.itemList2.size(); i5++) {
                                            ItemStack itemStack4 = minecraft.thePlayer.container.getSlot(i5).getStack();
                                            if (itemStack2 != null
                                            && (itemStack4 == null || itemStack4.getItem().equals(itemStack2.getItem()) && itemStack4.animationsToGo < itemStack2.processAction5())) {
                                                if (this.checkCondition2(index)) {
                                                    return false;
                                                }

                                                this.processAction(index);
                                                enabled = true;
                                                index11 = 0;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        index11++;
                    }
                }
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    private boolean canExecute8() {
        return this.smoothDamper2.checkCondition(this.settingFailRate.getIndexOffset2());
    }
}
