package augustus.modules.player;

import client.Client;
import client.ClientGlobals;
import client.event.RenderListener;
import client.event.TickListener;
import client.gui.CustomGuiScreen;
import client.module.Category;
import client.module.Module;
import client.network.CosmeticsPayload;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ChestStealerSortType;
import client.setting.ColorSetting;
import client.setting.ItemListSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.setting.ShaderOptions;
import client.util.AnimationTimer;
import client.util.BooleanSupplier;
import client.util.ChestItemPriorityMap;
import client.util.InventoryUtil;
import client.util.MovementHandler;
import client.util.RandomUtil;
import client.util.RunnableTask;
import client.util.SmoothDamper;
import client.util.TimeHelper;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemGoldenAxe;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemGoldenPickaxe;
import net.minecraft.item.ItemGoldenShovel;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ChestStealer extends Module implements TickListener, RenderListener {
    public ModeSetting settingMode;
    public RangeSetting settingStartDelay;
    public BooleanSetting settingInstant;
    public NumberSetting settingMaxMouseMoveSpeed;
    public RangeSetting settingMouseMoveRandom;
    public RangeSetting settingCPS;
    public RangeSetting settingCPSRecalculate;
    public RangeSetting settingDoubleClickRate;
    public RangeSetting settingFailRate;
    public RangeSetting settingMissClickRate;
    public RangeSetting settingCPSNew;
    public RangeSetting settingCPSRecalculateNew;
    public RangeSetting settingDoubleClickRateNew;
    public RangeSetting settingSideStopTicks;
    public RangeSetting settingEmptySlotFailRate;
    public BooleanSetting settingRemoveEmptyRows;
    public BooleanSetting settingClickWindow;
    public BooleanSetting settingIntelligent;
    public SettingGroup settingItemBlackList;
    public BooleanSetting settingAutoSort;
    public BooleanSetting settingAutoClose;
    public BooleanSetting settingAutoCloseIfInvFull;
    public RangeSetting settingCloseDelay;
    public RangeSetting settingChestOpenTime;
    public BooleanSetting settingVisualize;
    public ModeSetting settingWhereToSteal;
    public ModeSetting settingSequence;
    public ModeSetting settingClickMode;
    public BooleanSetting settingToggleStorageESP;
    public BooleanSetting settingToggleChestAura;
    public ItemListSetting settingChestStorage;
    public BooleanSetting settingSilent;
    public BooleanSetting settingStealIndicator;
    public ColorSetting settingColor;
    public BooleanSetting settingBackGround;
    public ColorSetting settingBackGroundColor;
    public ShaderOptions settingBackGroundColor2;
    public boolean activeState;
    public int tickDelay;
    public int tickDelay3;
    public TimeHelper timer;
    public TimeHelper timer2;
    public TimeHelper timer3;
    public SmoothDamper smoothDamper;
    public SmoothDamper smoothDamper2;
    public SmoothDamper smoothDamper3;
    public SmoothDamper smoothDamper4;
    public AnimationTimer animationTimer;
    public AnimationTimer animationTimer2;
    public AnimationTimer animationTimer3;
    public AnimationTimer animationTimer4;
    public SmoothDamper smoothDamper5;
    public SmoothDamper smoothDamper6;
    public SmoothDamper smoothDamper7;
    public ArrayList chestSlotOrder;
    public ArrayList itemList5;
    public ArrayList itemList3;
    public ArrayList itemList4;
    public ChestItemPriorityMap itemPriorityFilter;
    public int tickDelay1;
    public int keyCode2;
    public int tickDelay2;
    public boolean enabled2;
    public boolean expanded2;

    public ChestStealer() {
        super("ChestStealer", "TruhenAusbeutung", new Color(196, 184, 249, 255), Category.PLAYER);
        String[] textArray = new String[2];
        textArray[0] = "Default";
        textArray[1] = "Experimental";
        this.settingMode = new ModeSetting(this, "Mode", "Default", textArray);
        RangeSetting rangeSetting2 = new RangeSetting(this, "StartDelay", 125.0, 175.0, 0.0, 1000.0, 0)
        .getRangeSetting2();
        this.settingStartDelay = rangeSetting2;
        this.settingInstant = new BooleanSetting(this, "Instant", false);
        this.settingMaxMouseMoveSpeed = new NumberSetting(this, "MaxMouseMoveSpeed", 19.0, 0.0, 200.0, 0).getNumberSetting(() -> this.isMaxMouseMoveSpeedVisible());
        RangeSetting rangeSetting = new RangeSetting(this, "MouseMoveRandom", 0.0, 0.0, -1.0, 2.0, 0);
        RangeSetting rangeSetting3 = rangeSetting.getRangeSetting(15343, () -> this.isMouseMoveRandomVisible());
        this.settingMouseMoveRandom = rangeSetting3;
        this.settingCPS = new RangeSetting(this, "CPS", 17.0, 20.0, 0.0, 40.0, 0).getRangeSetting(15343, () -> this.isCPSVisible());
        this.settingCPSRecalculate = new RangeSetting(this, "CPSRecalculate", 6.0, 8.0, 0.0, 40.0, 0)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isCPSRecalculateVisible());
        this.settingDoubleClickRate = new RangeSetting(this, "DoubleClickRate", 5.0, 10.0, 0.0, 100.0, 0)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isDoubleClickRateVisible());
        this.settingFailRate = new RangeSetting(this, "FailRate", 5.0, 10.0, 0.0, 100.0, 0).getRangeSetting2().getRangeSetting(15343, () -> this.isFailRateVisible());
        this.settingMissClickRate = new RangeSetting(this, "MissClickRate", 0.0, 0.0, 0.0, 100.0, 0)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isMissClickRateVisible());
        this.settingCPSNew = new RangeSetting(this, "CPSNew", 15.0, 20.0, 0.0, 40.0, 0).getRangeSetting2().getRangeSetting("CPS").getRangeSetting(15343, () -> this.isCPSNewVisible());
        this.settingCPSRecalculateNew = new RangeSetting(this, "CPSRecalculateNew", 6.0, 8.0, 0.0, 60.0, 0).getRangeSetting("CPSRecalculate").getRangeSetting2().getRangeSetting(15343, () -> this.isCPSRecalculateNewVisible());
        this.settingDoubleClickRateNew = new RangeSetting(this, "DoubleClickRateNew", 6.0, 12.0, 0.0, 100.0, 0)
        .getRangeSetting2().getRangeSetting("DoubleClickRate").getRangeSetting(15343, () -> this.isDoubleClickRateNewVisible());
        this.settingSideStopTicks = new RangeSetting(this, "SideStopTicks", 3.0, 6.0, 0.0, 20.0, 0)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isSideStopTicksVisible());
        this.settingEmptySlotFailRate = new RangeSetting(this, "EmptySlotFailRate", 5.0, 10.0, 0.0, 100.0, 0)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isEmptySlotFailRateVisible());
        this.settingRemoveEmptyRows = (BooleanSetting)new BooleanSetting(this, "RemoveEmptyRows", true).getSetting(() -> this.isEmptySlotFailRateVisible2());
        this.settingClickWindow = new BooleanSetting(this, "ClickWindow", true);
        this.settingIntelligent = new BooleanSetting(this, "Intelligent", true);
        this.settingItemBlackList = new SettingGroup(this, "ItemBlackList").getSettingGroup(() -> this.settingIntelligent.isEnabled());
        this.settingAutoSort = (BooleanSetting)new BooleanSetting(this, "AutoSort", true).getSetting(() -> this.isItemBlackListVisible());
        this.settingAutoClose = new BooleanSetting(this, "AutoClose", true);
        this.settingAutoCloseIfInvFull = (BooleanSetting)new BooleanSetting(this, "AutoCloseIfInvFull", false).getSetting(() -> this.settingAutoClose.isEnabled());
        this.settingCloseDelay = new RangeSetting(this, "CloseDelay", 50.0, 50.0, 0.0, 1000.0, 0)
        .getRangeSetting2().getRangeSetting(15343, () -> this.settingAutoClose.isEnabled());
        this.settingChestOpenTime = new RangeSetting(this, "ChestOpenTime", 160.0, 260.0, 0.0, 1000.0, 0)
        .getRangeSetting2().getRangeSetting(15343, () -> this.settingAutoClose.isEnabled());
        this.settingVisualize = (BooleanSetting)new BooleanSetting(this, "Visualize", true).getSetting(() -> this.isChestOpenTimeVisible());
        String[] textArray2 = new String[3];
        textArray2[0] = "Always";
        textArray2[1] = "ChestsOnly";
        textArray2[2] = "CustomChest";
        this.settingWhereToSteal = new ModeSetting(this, "WhereToSteal", "ChestsOnly", textArray2);
        String[] textArray3 = new String[3];
        textArray3[0] = "Basic";
        textArray3[1] = "Nearest";
        textArray3[2] = "Random";
        this.settingSequence = new ModeSetting(this, "Sequence", "Nearest", textArray3).getModeSetting(() -> this.isSequenceVisible());
        Client.getClient();
        String[] textArray4 = new String[1];
        textArray4[0] = "Basic";
        this.settingClickMode = new ModeSetting(this, "ClickMode", "Basic", textArray4).getModeSetting(() -> this.isSequenceVisible());
        this.settingToggleStorageESP = new BooleanSetting(this, "ToggleStorageESP", false);
        this.settingToggleChestAura = new BooleanSetting(this, "ToggleChestAura", false);
        ArrayList arrayList = new ArrayList();
        this.settingChestStorage = new ItemListSetting(this, "ChestStorage", arrayList);
        this.settingSilent = new BooleanSetting(this, "Silent", false);
        this.settingStealIndicator = (BooleanSetting)new BooleanSetting(this, "StealIndicator", true).getSetting(() -> this.settingSilent.isEnabled());
        this.settingColor = (ColorSetting)new ColorSetting(this, "Color", Color.WHITE).getSetting(() -> this.isColorVisible2());
        this.settingBackGround = (BooleanSetting)new BooleanSetting(this, "BackGround", true).getSetting(() -> this.isColorVisible());
        Color color = new Color(0, 0, 0, 100);
        this.settingBackGroundColor = (ColorSetting)new ColorSetting(this, "BackGroundColor", color).getSetting(() -> this.isBackGroundColorVisible2());
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color black = Color.black;
        this.settingBackGroundColor2 = (ShaderOptions)shaderOptions.getShaderOptions(black).getSetting(() -> this.isBackGroundColorVisible());
        this.activeState = false;
        this.tickDelay = -1;
        this.tickDelay3 = 0;
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        this.timer3 = new TimeHelper();
        SmoothDamper smoothDamper8 = new SmoothDamper(100, 0.0);
        this.smoothDamper = smoothDamper8;
        this.smoothDamper2 = new SmoothDamper(100, 0.0);
        this.smoothDamper3 = new SmoothDamper(100, 0.0);
        this.smoothDamper4 = new SmoothDamper(20, 0.0);
        this.animationTimer = new AnimationTimer();
        this.animationTimer2 = new AnimationTimer();
        this.animationTimer3 = new AnimationTimer();
        this.animationTimer4 = new AnimationTimer();
        this.smoothDamper5 = new SmoothDamper(20, 0.0);
        this.smoothDamper6 = new SmoothDamper(20, 0.0);
        this.smoothDamper7 = new SmoothDamper(100, 0.0);
        ArrayList arrayList2 = new ArrayList();
        this.chestSlotOrder = arrayList2;
        ArrayList arrayList3 = new ArrayList();
        this.itemList5 = arrayList3;
        ArrayList arrayList4 = new ArrayList();
        this.itemList3 = arrayList4;
        ArrayList arrayList5 = new ArrayList();
        this.itemList4 = arrayList5;
        this.itemPriorityFilter = new ChestItemPriorityMap(
        this, "Items", this.settingItemBlackList, () -> this.settingIntelligent.isEnabled(), object -> this.getBoolean((Item)object)
        );
        this.tickDelay1 = 0;
        this.keyCode2 = 0;
        this.tickDelay2 = 0;
        this.enabled2 = false;
        this.expanded2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(40, 10);
        this.registerEvent(32, -2);
    }

    @Override
    public void onDisable() {
        this.processBoolean2(false);
        this.tickDelay = -1;
        this.itemList3.clear();
    }

    @Override
    public void onPreDisable() {
        this.processBoolean2(true);
        this.updateState6();
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        if (this.settingSilent.isEnabled()) {
            String text = "Stealing (" + this.tickDelay2 + "/" + this.keyCode2 + ")";
            boolean enabled = this.settingBackGroundColor2.canExecute();
            boolean enabled3 = this.settingBackGroundColor2.canExecute2();
            MovementHandler.processBoolean(enabled, enabled3);
            MovementHandler.applyRunnableTask(false, renderPass -> this.applyScaledResolution2(text, scaledResolution, renderPass));
        }

        RenderUtils.processAction();
    }

    @Override
    public void onPreTick() {
        this.updateState2();
        this.animationTimer.processAction();
        this.animationTimer2.processAction();
        this.animationTimer3.processAction();
        this.animationTimer4.processAction();
    }

    private boolean isFailRateVisible() {
        return this.settingInstant.isEnabled();
    }

    private boolean isMaxMouseMoveSpeedVisible() {
        return this.settingInstant.isEnabled();
    }

    private void applyCustomGuiScreen(int index, CustomGuiScreen customGuiScreen) {
        Slot slot = customGuiScreen.container.getSlot(index);
        Container container = customGuiScreen.container;
        this.applyContainer(container, index);

        try {
            String selected = this.settingClickMode.getSelected();
switch (selected) {
            case "Basic":
                customGuiScreen.state2 = true;
                customGuiScreen.processAction(slot.indexOffset + 2 + customGuiScreen.indexOffset1, slot.indexOffset2 + 2 + customGuiScreen.indexOffset2, 0);
                break;
            case "Other":
                Client.getClient().getClass();
                break;
            case "Exploit":
                Client.getClient().getClass();
        }

            this.updateState();
        } catch (IOException iOException) {
        }
    }

    private void applyArrayList(
    ArrayList arrayList,
    ArrayList arrayList2,
    SmoothDamper smoothDamper8,
    RangeSetting rangeSetting,
    
    
    RangeSetting rangeSetting2,
    RangeSetting rangeSetting3,
    Container container,
    CustomGuiScreen customGuiScreen,
    
    boolean enabled,
    int index,
    int index2
    ) {
        if (enabled) {
            arrayList.forEach(object -> ((Runnable)object).run());
        } else {
            if (!arrayList2.isEmpty()) {
                int index3 = arrayList2.size() > 1 ? 1 : 0;
                this.tickDelay = (Integer)arrayList2.get(index3);
            }

            if (!this.checkSmoothDamper(this.smoothDamper2, rangeSetting2)) {
                int intEntry = smoothDamper8.getInt(index);
                boolean enabled3 = false;

                while (intEntry > 0 && !arrayList.isEmpty()) {
                    if (this.checkRangeSetting(rangeSetting3, container, customGuiScreen)) {
                        intEntry--;
                    } else {
                        intEntry = this.findItemSlot((double)index2, container, (Integer)arrayList2.get(0), intEntry);
                        if (intEntry <= 0) {
                            break;
                        }

                        Runnable runnable = (Runnable)arrayList.get(0);
                        runnable.run();
                        enabled3 = true;
                        if (this.checkSmoothDamper(this.smoothDamper, rangeSetting)) {
                            runnable.run();
                        }

                        arrayList.remove(0);
                        arrayList2.remove(0);
                        intEntry--;
                    }
                }

                if (enabled3 && this.settingSequence.getSelected().equals("Random")) {
                    this.itemList4 = InventoryUtil.getArrayList(
                    container,
                    
                    this.getArrayList2(customGuiScreen),
                    this.settingSequence.getSelected(),
                    this.findItemSlot(container)
                    );
                }
            }
        }
    }

    private ArrayList getArrayList(int index, Container container) {
        ItemStack itemStack = container.getSlot(index).getStack();
        ArrayList arrayList = new ArrayList();
        int index2 = itemStack.processAction5() - 1;
        if (index2 >= container.itemList2.size() - 36) {
            container.getSlot(index2).getStack();
            return arrayList;
        } else {
            return arrayList;
        }
    }

    private ArrayList getArrayList(ItemStack itemStack, Container container) {
        if (itemStack.processAction5() <= 1) {
            return new ArrayList();
        } else {
            ArrayList arrayList = new ArrayList();
            int index = container.itemList2.size() - 36;
            if (index < container.itemList2.size()) {
                container.getSlot(index).getStack();
            }

            return arrayList;
        }
    }

    private void applyContainer(Container container, int index) {
        ScaledResolution scaledResolution = new ScaledResolution(minecraft);
        int index2 = (scaledResolution.getScaledWidth() - 176) / 2;
        int index3 = (scaledResolution.getScaledHeight() - this.findItemSlot(container)) / 2;
        Slot slot = container.getSlot(index);
        double[] coordOffsetArray = InventoryUtil.getCoordOffsetArray();
        if (coordOffsetArray != null && coordOffsetArray.length >= 2) {
            coordOffsetArray[0] = (double)(slot.indexOffset + 2 + index2);
            coordOffsetArray[1] = (double)(slot.indexOffset2 + 2 + index3);
        }
    }

    private void applyCustomGuiScreen2(int index, int index2, CustomGuiScreen customGuiScreen) {
        minecraft.thePlayer.container.getSlot(index + 36).getStack();
        switch (this.settingClickMode.getSelected().hashCode()) {
            case 0:
                Client.getClient().getClass();
                minecraft.playerController.windowClick(customGuiScreen.container.indexOffset2, index2, index, 2, minecraft.thePlayer);
                this.updateState();
                break;
            case 1:
            case 2:
                minecraft.playerController.windowClick(customGuiScreen.container.indexOffset2, index2, index, 2, minecraft.thePlayer);
                this.updateState();
                break;
            default:
                this.updateState();
        }
    }

    private boolean checkItem(Item item) {
        return item instanceof ItemSword;
    }

    private void updateState6() {
        this.settingCPS.recalculate();
        this.settingCPSRecalculate.recalculate();
        this.settingMissClickRate.recalculate();
        this.settingDoubleClickRate.recalculate();
        this.settingFailRate.recalculate();
        this.settingStartDelay.recalculate();
        this.settingCloseDelay.recalculate();
        this.settingChestOpenTime.recalculate();
        this.settingCPSNew.recalculate();
        this.settingCPSRecalculateNew.recalculate();
        this.settingEmptySlotFailRate.recalculate();
        this.settingSideStopTicks.recalculate();
        this.settingDoubleClickRateNew.recalculate();
        this.tickDelay = -1;
        this.tickDelay1 = 0;
        this.expanded2 = false;
        this.enabled2 = false;
        this.itemList5.clear();
        this.chestSlotOrder.clear();
        this.updateState();
    }

    private void applyCustomGuiScreen3(int index, CustomGuiScreen customGuiScreen) {
        this.applyCustomGuiScreen9(index, customGuiScreen);
    }

    private void applyCustomGuiScreen4(CustomGuiScreen customGuiScreen) {
        Container container = customGuiScreen.container;
        ArrayList arrayList6 = InventoryUtil.getArrayList6(this.getArrayList(customGuiScreen));
        Collections.reverse(arrayList6);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator iterator = arrayList6.iterator();
        if (iterator.hasNext()) {
            int index = (Integer)iterator.next();
            arrayList.add(((Runnable)(() -> ((ChestStealer)this).applyCustomGuiScreen3(index, (CustomGuiScreen)customGuiScreen))));
            arrayList2.add(index);
            SmoothDamper smoothDamper52 = this.smoothDamper5;
            int indexOffset2 = this.settingCPSNew.getIndexOffset2();
            smoothDamper52.getInt(indexOffset2);
        } else {
            this.checkArrayList(arrayList);
        }

        if (this.settingInstant.isEnabled()) {
            byte state = ((byte)(arrayList.isEmpty() ? 1 : 0));
            container.getSlot(state).getStack();
        }

        this.updateState4();
    }

    private boolean isEmptySlotFailRateVisible() {
        return this.isSequenceVisible();
    }

    private boolean isMissClickRateVisible() {
        return this.settingInstant.isEnabled();
    }

    private boolean isCPSRecalculateNewVisible() {
        return this.isSequenceVisible();
    }

    private boolean isCPSVisible() {
        return this.settingInstant.isEnabled();
    }

    private boolean checkRangeSetting(RangeSetting rangeSetting, Container container, CustomGuiScreen customGuiScreen) {
        return false;
    }

    private void applyScaledResolution2(String text, ScaledResolution scaledResolution, RenderPass renderPass) {
        int index = minecraft.fontRendererObj.getIndex(text);
        int index3 = scaledResolution.getScaledWidth() / 2 - index / 2;
        int index4 = scaledResolution.getScaledHeight() / 2 - 4;
        byte state = ((byte)(this.settingBackGround.isEnabled() ? 1 : 0));
        int index5 = index3 - 2;
        int index6 = index4 - 2;
        int index7 = index3 + index + 1;
        int index8 = index4 + 9;
        int index2 = RandomUtil.getIndex(renderPass, state);
        RenderUtils.processAction(index5, index6, index7, index8, this.settingBackGroundColor2.getIndex(renderPass, index2));
        if (renderPass == RenderPass.NONE || !this.settingBackGround.isEnabled() && renderPass == RenderPass.SHADOW) {
            minecraft.fontRendererObj.getIndex(text, index3, index4, this.settingColor.getInt12(0));
        }
    }

    private void applyCustomGuiScreen5(int index, CustomGuiScreen customGuiScreen) {
        this.applyCustomGuiScreen(index, customGuiScreen);
    }

    private ArrayList getArrayList(CustomGuiScreen customGuiScreen) {
        Container container = customGuiScreen.container;
        ArrayList arrayList5 = InventoryUtil.getArrayList5(0, container.itemList2.size() - 36);
        arrayList5.removeAll(this.itemList5);
        if (this.settingRemoveEmptyRows.isEnabled()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();

            for (Object object : arrayList5) {
                int index = (Integer)object;
                if (index % 9 == 0) {
                    if (arrayList2.size() == 9) {
                        arrayList.addAll(arrayList2);
                    }

                    arrayList2.clear();
                }

                if (customGuiScreen.container.getSlot(index).getStack() == null) {
                    arrayList2.add(index);
                }
            }

            arrayList5.removeAll(arrayList);
        }

        if (!this.settingIntelligent.isEnabled()) {
            return arrayList5;
        } else {
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList32 = InventoryUtil.getArrayList3(container, 0);

            for (Object object2 : arrayList5) {
                int index2 = (Integer)object2;
                ItemStack itemStack = container.getSlot(index2).getStack();
                if (itemStack == null
                || this.checkItemStack2(itemStack, container)
                && this.checkItemStack(itemStack)
                && (!this.itemPriorityFilter.checkItem(itemStack.getItem()) || arrayList32.contains(index2))) {
                    arrayList3.add(index2);
                }
            }

            return arrayList3;
        }
    }

    public boolean checkCustomGuiScreen(CustomGuiScreen customGuiScreen) {
        this.settingWhereToSteal.getSelected();
        return customGuiScreen.iInventory2 instanceof InventoryLargeChest;
    }

    private void updateState2() {
        this.updateState3();
        if (!(minecraft.currentScreen instanceof CustomGuiScreen)) {
            this.timer3.reset();
            this.timer.reset();
            this.timer2.reset();
            this.tickDelay = -1;
            this.updateState();
            this.expanded2 = false;
            this.itemList3.clear();
        } else {
            CustomGuiScreen customGuiScreen = (CustomGuiScreen)minecraft.currentScreen;
            if (this.checkCustomGuiScreen(customGuiScreen)) {
                TimeHelper timer4 = this.timer;
                long time2 = (long)this.settingStartDelay.getIndexOffset2();
                if (timer4.hasReached(time2)) {
                    if (!minecraft.currentScreen.isState()) {
                        if (minecraft.thePlayer.inventoryPlayer.getItemStack() == null) {
                            if (this.settingIntelligent.isEnabled()) {
                                ArrayList arrayList = new ArrayList();
                                Iterator iterator = customGuiScreen.container.itemList2.iterator();

                                while (iterator.hasNext()) {
                                    ItemStack itemStack = ((Slot)iterator.next()).getStack();
                                    if (itemStack != null && itemStack.getItem() instanceof ItemGoldenBoots) {
                                        arrayList.add(itemStack);
                                    }
                                }

                                for (int i = 0; i < 9; i++) {
                                    ItemStack itemStack2 = minecraft.thePlayer.container.getSlot(i).getStack();
                                    if (itemStack2 != null && itemStack2.getItem() instanceof ItemGoldenBoots) {
                                        arrayList.add(itemStack2);
                                    }
                                }

                                boolean enabled;
                                if (ClientGlobals.moduleManager.invManager.isToggled()
                                && ChestStealerSortType.PUT_ON_ARMOR.isSelected()
                                && ClientGlobals.moduleManager.invManager.settingIgnoreDurability.isEnabled()) {
                                    enabled = true;
                                } else {
                                    enabled = false;
                                }

                                this.itemList3 = InventoryUtil.getArrayList2(arrayList, enabled);
                            }

                            if (this.isSequenceVisible()) {
                                this.applyCustomGuiScreen7(customGuiScreen);
                                return;
                            }

                            this.applyCustomGuiScreen4(customGuiScreen);
                        }
                    }
                }
            }
        }
    }

    private boolean isDoubleClickRateNewVisible() {
        return this.isSequenceVisible();
    }

    private void processBoolean2(boolean enabled) {
        if (this.settingToggleStorageESP.isEnabled()) {
            ClientGlobals.moduleManager.chestAura.setActive(enabled);
        }
    }

    private void updateState3() {
    }

    public boolean checkItemStack(ItemStack itemStack) {
        if (!this.checkItemStack3(itemStack)) {
            return true;
        } else {
            if (0 < minecraft.thePlayer.container.itemList2.size()) {
                ItemStack itemStack2 = minecraft.thePlayer.container.getSlot(0).getStack();
                if (itemStack2 != null) {
                    Item item = itemStack2.getItem();
                    Item item2 = itemStack.getItem();
                    if (item2 instanceof ItemSword
                    && item instanceof ItemSword
                    && InventoryUtil.getCoordinate(itemStack) <= InventoryUtil.getCoordinate(itemStack2)) {
                        return false;
                    }

                    if (item2 instanceof ItemBow
                    && item instanceof ItemBow
                    && InventoryUtil.getCoordinate(itemStack) <= InventoryUtil.getCoordinate(itemStack2)) {
                        return false;
                    }

                    if (item2 instanceof ItemGoldenBoots && item instanceof ItemGoldenBoots) {
                        if (!this.itemList3.contains(itemStack)) {
                            return false;
                        }

                        int indexOffset3 = ((ItemGoldenBoots)item).indexOffset3;
                        if (((ItemGoldenBoots)item2).indexOffset3 == indexOffset3
                        && InventoryUtil.getCoordinate(itemStack2) == InventoryUtil.getCoordinate(itemStack)) {
                            return false;
                        }
                    }

                    if (item2 instanceof ItemFishingRod
                    && item instanceof ItemFishingRod
                    && InventoryUtil.getCoordinate(itemStack) <= InventoryUtil.getCoordinate(itemStack2)) {
                        return false;
                    }

                    if (item2 instanceof ItemGoldenAxe
                    && item instanceof ItemGoldenAxe
                    && InventoryUtil.getCoordinate(itemStack, true) < InventoryUtil.getCoordinate(itemStack2, true)) {
                        return false;
                    }

                    if (item2 instanceof ItemGoldenPickaxe
                    && item instanceof ItemGoldenPickaxe
                    && InventoryUtil.getCoordinate(itemStack, true) < InventoryUtil.getCoordinate(itemStack2, true)) {
                        return false;
                    }

                    if (item2 instanceof ItemGoldenShovel
                    && item instanceof ItemGoldenShovel
                    && InventoryUtil.getCoordinate(itemStack, true) < InventoryUtil.getCoordinate(itemStack2, true)) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    private void applyCustomGuiScreen6(int index, int index2, CustomGuiScreen customGuiScreen) {
        this.applyCustomGuiScreen2(index, index2, customGuiScreen);
    }

    public int findItemSlot() {
        return this.tickDelay;
    }

    private void applyCustomGuiScreen7(CustomGuiScreen customGuiScreen) {
        this.tickDelay3++;
        Container container = customGuiScreen.container;
        if (!this.settingSequence.getSelected().equals("Random")) {
            ;
        }

        ArrayList arrayList2 = this.getArrayList2(customGuiScreen);
        String selected = this.settingSequence.getSelected();
        int index = this.findItemSlot(container);
        ArrayList arrayList = InventoryUtil.getArrayList(container, arrayList2, selected, index);
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        Iterator iterator = arrayList.iterator();
        ChestStealer chestStealer;
        if (iterator.hasNext()) {
            iterator.next();
            boolean enabled = this.settingIntelligent.isEnabled();
            if (enabled) {
                return;
            }

            this.expanded2 = true;
            chestStealer = this;
        } else {
            chestStealer = this;
        }

        SmoothDamper smoothDamper42 = this.smoothDamper4;
        boolean enabled3 = this.settingInstant.isEnabled();
        int indexOffset2 = this.settingCPS.getIndexOffset2();
        int tickDelay32 = this.tickDelay3;
        chestStealer.applyArrayList(arrayList3, arrayList4, smoothDamper42, this.settingDoubleClickRate, this.settingFailRate, this.settingMissClickRate, container, customGuiScreen, enabled3, indexOffset2, tickDelay32);
        this.updateState4();
    }

    private boolean isMouseMoveRandomVisible() {
        return this.settingInstant.isEnabled();
    }

    private boolean checkArrayList(ArrayList arrayList) {
        boolean empty = arrayList.isEmpty();
        if (this.settingAutoClose.isEnabled() && this.settingAutoCloseIfInvFull.isEnabled()) {
            boolean enabled = true;

            for (int i = 9; i < minecraft.thePlayer.container.itemList2.size(); i++) {
                if (minecraft.thePlayer.container.getSlot(i).getStack() == null) {
                    enabled = false;
                    break;
                }
            }

            if (!empty) {
                empty = enabled;
            }
        }

        if (empty && this.settingAutoClose.isEnabled() && this.expanded2) {
            if (this.timer2.hasReached((long)this.settingCloseDelay.getIndexOffset2())
            && this.timer3.hasReached((long)this.settingChestOpenTime.getIndexOffset2())) {
                minecraft.thePlayer.updateState5();
            }

            return true;
        } else {
            this.timer2.reset();
            return false;
        }
    }

    private void applyCustomGuiScreen8(int index, CustomGuiScreen customGuiScreen) {
        this.applyCustomGuiScreen(index, customGuiScreen);
    }

    private void updateState4() {
        if (this.animationTimer.checkCondition(this.settingCPSRecalculate.getIndexOffset2())) {
            this.settingCPS.recalculate();
            this.settingCPSRecalculate.recalculate();
            this.animationTimer.processAction3();
        }

        int indexOffset2 = this.settingCPSRecalculateNew.getIndexOffset2();
        if (this.animationTimer2.checkCondition(indexOffset2)) {
            this.settingCPSNew.recalculate();
            this.settingCPSRecalculateNew.recalculate();
            this.animationTimer2.processAction3();
        }
    }

    public boolean checkItemStack2(ItemStack itemStack, Container container) {
        byte state = ((byte)(this.checkItemStack3(itemStack) ? 1 : 0));
        if (state < container.itemList2.size() - 36) {
            ItemStack itemStack2 = container.getSlot(state).getStack();
            Item item = itemStack2.getItem();
            itemStack2.getItem();
            return item instanceof ItemSword;
        } else {
            return true;
        }
    }

    private int findItemSlot(double offset, Container container, int index, int index2) {
        Slot slot = container.getSlot(index);
        ScaledResolution scaledResolution = new ScaledResolution(minecraft);
        int index3 = (scaledResolution.getScaledWidth() - 176) / 2;
        int index4 = (scaledResolution.getScaledHeight() - this.findItemSlot(container)) / 2;
        double offset2 = Math.abs((double)(slot.indexOffset + 2 + index3) - InventoryUtil.getCoordOffsetArray()[0]);
        double offset3 = Math.abs((double)(slot.indexOffset2 + 2 + index4) - InventoryUtil.getCoordOffsetArray()[1]);
        double offset4 = Math.sqrt(offset2 * offset2 + offset3 * offset3);
        byte state = ((byte)(CosmeticsPayload.checkCondition(1) ? 1 : 0));
        double double2 = this.settingMaxMouseMoveSpeed.getDouble2(true);
        return (int)(offset4 / (double2 + (double)state));
    }

    private boolean checkSmoothDamper(SmoothDamper smoothDamper8, RangeSetting rangeSetting) {
        return rangeSetting == null ? false : smoothDamper8.checkCondition(rangeSetting.getIndexOffset2());
    }

    private ArrayList getArrayList2(CustomGuiScreen customGuiScreen) {
        Container container = customGuiScreen.container;
        int index = container.itemList2.size() - 36;
        ArrayList arrayList = InventoryUtil.getArrayList(container, 0, index);
        if (!this.settingIntelligent.isEnabled()) {
            return arrayList;
        } else {
            ArrayList arrayList2 = new ArrayList();
            InventoryUtil.getArrayList3(container, 0);
            Iterator iterator = arrayList.iterator();
            if (iterator.hasNext()) {
                Object object = iterator.next();
                int index2 = (Integer)object;
                ItemStack itemStack = container.getSlot(index2).getStack();
                this.checkItemStack2(itemStack, container);
                return arrayList2;
            } else {
                return arrayList2;
            }
        }
    }

    private String getText(ItemStack itemStack) {
        return itemStack.getItem() instanceof ItemSword ? "Blocks" : "";
    }

    private Boolean getBoolean(Item item) {
        return this.checkItem(item);
    }

    private void applyCustomGuiScreen9(int index, CustomGuiScreen customGuiScreen) {
        Slot slot = customGuiScreen.container.getSlot(index);
        this.applyContainer(customGuiScreen.container, index);

        try {
            customGuiScreen.state2 = true;
            customGuiScreen.processAction(slot.indexOffset + 2 + customGuiScreen.indexOffset1, slot.indexOffset2 + 2 + customGuiScreen.indexOffset2, 0);
            this.updateState();
        } catch (IOException iOException) {
        }
    }

    public void updateState() {
        this.tickDelay3 = 0;
    }

    private int findItemSlot(Container container) {
        return 114 + (container.itemList2.size() - 36) / 9 * 18;
    }

    private boolean checkItemStack3(ItemStack itemStack) {
        return this.checkItem(itemStack.getItem());
    }

    private boolean isSequenceVisible() {
        return this.settingMode.getSelected().equals("Default");
    }

    private boolean isItemBlackListVisible() {
        return this.settingIntelligent.isEnabled() ? Client.getClient().getModuleManager().invManager.isToggled() : false;
    }

    private boolean isSideStopTicksVisible() {
        return this.isSequenceVisible();
    }

    private boolean isColorVisible() {
        return this.settingSilent.isEnabled();
    }

    private boolean isBackGroundColorVisible() {
        return this.settingSilent.isEnabled();
    }

    private boolean isChestOpenTimeVisible() {
        return this.settingSilent.isEnabled();
    }

    private boolean isEmptySlotFailRateVisible2() {
        return this.isSequenceVisible();
    }

    private boolean isCPSRecalculateVisible() {
        return this.settingInstant.isEnabled();
    }

    private boolean isCPSNewVisible() {
        return this.isSequenceVisible();
    }

    private boolean isDoubleClickRateVisible() {
        return this.settingInstant.isEnabled();
    }

    private boolean isBackGroundColorVisible2() {
        return this.settingSilent.isEnabled();
    }

    private boolean isColorVisible2() {
        return this.settingSilent.isEnabled();
    }
}
