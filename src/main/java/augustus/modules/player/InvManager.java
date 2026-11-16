package augustus.modules.player;

import client.Client;
import client.event.ClickListener;
import client.event.ClickTickListener;
import client.event.EventClick;
import client.event.EventMove;
import client.event.EventMoveInput;
import client.event.MotionListener;
import client.event.MoveInputListener;
import client.event.MoveListener;
import client.module.Category;
import client.module.Module;
import client.network.CosmeticsPayload;
import client.setting.AutoSoupPerformRespawnType;
import client.setting.BooleanSetting;
import client.setting.ChestStealerSortType;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.util.AnimationTimer;
import client.util.BooleanSupplier;
import client.util.ChestItemPriorityMap;
import client.util.InventoryUtil;
import client.util.MovingObjectType;
import client.util.Pair;
import client.util.SlotManager;
import client.util.SmoothDamper;
import client.util.TimeHelper;
import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCache;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.C16PacketClientStatus;

public class InvManager extends Module implements ClickTickListener, MoveListener, MotionListener, ClickListener, MoveInputListener {
    public String[] soupNames;
    public MultiSelectSetting settingActions;
    public ModeSetting settingMode;
    public RangeSetting settingStartDelay;
    public BooleanSetting settingBlockInputWhileManaging;
    public BooleanSetting settingVisualize;
    public BooleanSetting settingNotWhileScaffold;
    public BooleanSetting settingStopMovement;
    public BooleanSetting settingStopSprinting;
    public SettingGroup settingSort;
    public BooleanSetting settingSortInstant;
    public NumberSetting settingSortMaxMouseMoveSpeed;
    public RangeSetting settingSortMouseMoveRandom;
    public RangeSetting settingSortCPS;
    public RangeSetting settingSortCPSRecalculate;
    public RangeSetting settingSortFailRate;
    public ModeSetting settingSortSequence;
    public SettingGroup settingSlots;
    public ModeSetting settingSlot1;
    public ModeSetting settingSlot2;
    public ModeSetting settingSlot3;
    public ModeSetting settingSlot4;
    public ModeSetting settingSlot5;
    public ModeSetting settingSlot6;
    public ModeSetting settingSlot7;
    public ModeSetting settingSlot8;
    public ModeSetting settingSlot9;
    public SettingGroup settingArmor;
    public BooleanSetting settingNoArmorDrop;
    public BooleanSetting settingOnlyInHotbar;
    public BooleanSetting settingIgnoreDurability;
    public BooleanSetting settingArmorInstant;
    public NumberSetting settingArmorMaxMouseMoveSpeed;
    public RangeSetting settingArmorMouseMoveRandom;
    public RangeSetting settingArmorCPS;
    public RangeSetting settingArmorCPSRecalculate;
    public RangeSetting settingArmorDoubleClickRate;
    public RangeSetting settingArmorFailRate;
    public RangeSetting settingArmorMissClickRate;
    public ModeSetting settingArmorSequence;
    public SettingGroup settingClean;
    public BooleanSetting settingKeepAllArmor;
    public BooleanSetting settingCleanInstant;
    public NumberSetting settingCleanMaxMouseMoveSpeed;
    public RangeSetting settingCleanMouseMoveRandom;
    public RangeSetting settingCleanCPS;
    public RangeSetting settingCleanCPSRecalculate;
    public RangeSetting settingCleanFailRate;
    public RangeSetting settingCleanMissClickRate;
    public ModeSetting settingCleanerSequence;
    public SettingGroup settingItemsToDrop;
    public SettingGroup settingItemStackLimits;
    public NumberSetting settingBlockStackLimit;
    public NumberSetting settingFoodStackLimit;
    public NumberSetting settingWaterBucketLimit;
    public NumberSetting settingLavaBucketLimit;
    public NumberSetting settingThrowablesLimit;
    public SettingGroup settingCraft;
    public NumberSetting settingReCraftIfSoupsLowerThan;
    public BooleanSetting settingCraftInstant;
    public NumberSetting settingCraftMaxMouseMoveSpeed;
    public RangeSetting settingCraftMouseMoveRandom;
    public RangeSetting settingCraftCPS;
    public RangeSetting settingCraftCPSRecalculate;
    public SettingGroup settingArmorHotbar;
    public BooleanSetting settingQSG;
    public RangeSetting settingTickDelayBeforeSwitch;
    public RangeSetting settingTickDelayBeforeClick;
    public RangeSetting settingTickDelayAfter;
    public ModeSetting settingClickMode;
    public TimeHelper timer;
    public TimeHelper timer2;
    public SmoothDamper smoothDamper;
    public AnimationTimer animationTimer;
    public SmoothDamper smoothDamper2;
    public AnimationTimer animationTimer2;
    public SmoothDamper smoothDamper3;
    public AnimationTimer animationTimer3;
    public SmoothDamper smoothDamper4;
    public SmoothDamper smoothDamper5;
    public SmoothDamper smoothDamper6;
    public SmoothDamper smoothDamper7;
    public AnimationTimer animationTimer4;
    public int keyCode2;
    public int tickDelay4;
    public int tickDelay5;
    public int tickDelay;
    public int tickDelay1;
    public boolean activeState8;
    public boolean enabled2;
    public ArrayList itemList3;
    public ArrayList inventorySlotOrder;
    public int tickDelay3;
    public int tickDelay2;
    public int tickDelay7;
    public int tickDelay6;
    public boolean activeState;
    public boolean visible2;
    public boolean expanded2;
    public ChestItemPriorityMap itemPriorityFilter;

    public InvManager() {
        super("InvManager", "InventarManager", new Color(32, 94, 103), Category.PLAYER);
        String[] textArray = new String[10];
        textArray[0] = "None";
        textArray[1] = "Sword";
        textArray[2] = "Bow";
        textArray[3] = "Rod";
        textArray[4] = "Throwables";
        textArray[5] = "Blocks";
        textArray[6] = "EnderPearls";
        textArray[7] = "Food";
        textArray[8] = "Gapple";
        textArray[9] = "Soups";
        this.soupNames = textArray;
        ChestStealerSortType[] chestStealerSortTypeArray = ChestStealerSortType.values();
        this.settingActions = new MultiSelectSetting(this, "Actions", chestStealerSortTypeArray);
        String[] textArray2 = new String[4];
        textArray2[0] = "OpenInv";
        textArray2[1] = "SpoofInv";
        textArray2[2] = "SpoofInvOther";
        textArray2[3] = "Basic";
        this.settingMode = new ModeSetting(this, "Mode", "OpenInv", textArray2);
        RangeSetting rangeSetting2 = new RangeSetting(this, "StartDelay", 150.0, 200.0, 0.0, 1000.0, 0)
        .getRangeSetting2();
        this.settingStartDelay = rangeSetting2;
        this.settingBlockInputWhileManaging = new BooleanSetting(this, "BlockInputWhileManaging", true);
        this.settingVisualize = new BooleanSetting(this, "Visualize", true);
        this.settingNotWhileScaffold = new BooleanSetting(this, "NotWhileScaffold", true);
        this.settingStopMovement = (BooleanSetting)new BooleanSetting(this, "StopMovement", false).getSetting(() -> this.isStartDelayVisible());
        this.settingStopSprinting = (BooleanSetting)new BooleanSetting(this, "StopSprinting", false).getSetting(() -> this.isStartDelayVisible());
        this.settingSort = new SettingGroup(this, "Sort").getSettingGroup(() -> ChestStealerSortType.SORT.isSelected());
        this.settingSortInstant = (BooleanSetting)new BooleanSetting(this, "SortInstant", false, this.settingSort).getSetting("Instant");
        this.settingSortMaxMouseMoveSpeed = new NumberSetting(this, "SortMaxMouseMoveSpeed", 19.0, 0.0, 200.0, 0, this.settingSort).getNumberSetting("MaxMouseMoveSpeed").getNumberSetting(() -> this.isSortMaxMouseMoveSpeedVisible());
        RangeSetting rangeSetting = new RangeSetting(this, "SortMouseMoveRandom", 0.0, 0.0, -1.0, 2.0, 0, this.settingSort)
        .getRangeSetting2().getRangeSetting("MouseMoveRandom");
        RangeSetting rangeSetting3 = rangeSetting.getRangeSetting(15343, () -> this.isSortMouseMoveRandomVisible());
        this.settingSortMouseMoveRandom = rangeSetting3;
        RangeSetting rangeSetting4 = new RangeSetting(this, "SortCPS", 17.0, 20.0, 0.0, 40.0, 0, this.settingSort);
        this.settingSortCPS = rangeSetting4.getRangeSetting("CPS").getRangeSetting(15343, () -> this.isSortCPSVisible());
        this.settingSortCPSRecalculate = new RangeSetting(this, "SortCPSRecalculate", 6.0, 8.0, 0.0, 40.0, 0, this.settingSort).getRangeSetting2().getRangeSetting("CPSRecalculate").getRangeSetting(15343, () -> this.isSortCPSRecalculateVisible());
        this.settingSortFailRate = new RangeSetting(this, "SortFailRate", 2.0, 5.0, 0.0, 100.0, 0, this.settingSort).getRangeSetting("FailRate").getRangeSetting2().getRangeSetting(15343, () -> this.isSortFailRateVisible());
        String[] textArray3 = new String[3];
        textArray3[0] = "Basic";
        textArray3[1] = "Nearest";
        textArray3[2] = "Random";
        this.settingSortSequence = new ModeSetting(this, "SortSequence", "Nearest", textArray3, this.settingSort);
        this.settingSlots = new SettingGroup(this, "Slots", this.settingSort);
        this.settingSlot1 = new ModeSetting(this, "Slot1", "Sword", this.soupNames, this.settingSlots);
        this.settingSlot2 = new ModeSetting(this, "Slot2", "Bow", this.soupNames, this.settingSlots);
        this.settingSlot3 = new ModeSetting(this, "Slot3", "Throwables", this.soupNames, this.settingSlots);
        this.settingSlot4 = new ModeSetting(this, "Slot4", "Blocks", this.soupNames, this.settingSlots);
        this.settingSlot5 = new ModeSetting(this, "Slot5", "Blocks", this.soupNames, this.settingSlots);
        this.settingSlot6 = new ModeSetting(this, "Slot6", "None", this.soupNames, this.settingSlots);
        this.settingSlot7 = new ModeSetting(this, "Slot7", "None", this.soupNames, this.settingSlots);
        this.settingSlot8 = new ModeSetting(this, "Slot8", "Food", this.soupNames, this.settingSlots);
        this.settingSlot9 = new ModeSetting(this, "Slot9", "Gapple", this.soupNames, this.settingSlots);
        this.settingArmor = new SettingGroup(this, "Armor").getSettingGroup(() -> ChestStealerSortType.PUT_ON_ARMOR.isSelected());
        this.settingNoArmorDrop = new BooleanSetting(this, "NoArmorDrop", false, this.settingArmor);
        this.settingOnlyInHotbar = new BooleanSetting(this, "OnlyInHotbar", false, this.settingArmor);
        this.settingIgnoreDurability = new BooleanSetting(this, "IgnoreDurability", false, this.settingArmor);
        this.settingArmorInstant = (BooleanSetting)new BooleanSetting(this, "ArmorInstant", false, this.settingArmor).getSetting("Instant");
        this.settingArmorMaxMouseMoveSpeed = new NumberSetting(this, "ArmorMaxMouseMoveSpeed", 19.0, 0.0, 200.0, 0, this.settingArmor).getNumberSetting("MaxMouseMoveSpeed").getNumberSetting(() -> this.isArmorMaxMouseMoveSpeedVisible());
        this.settingArmorMouseMoveRandom = new RangeSetting(this, "ArmorMouseMoveRandom", 0.0, 0.0, -1.0, 2.0, 0, this.settingArmor)
        .getRangeSetting2().getRangeSetting("MouseMoveRandom").getRangeSetting(15343, () -> this.isArmorMouseMoveRandomVisible());
        this.settingArmorCPS = new RangeSetting(this, "ArmorCPS", 17.0, 20.0, 0.0, 40.0, 0, this.settingArmor).getRangeSetting("CPS").getRangeSetting(15343, () -> this.isArmorCPSVisible());
        this.settingArmorCPSRecalculate = new RangeSetting(this, "ArmorCPSRecalculate", 6.0, 8.0, 0.0, 40.0, 0, this.settingArmor)
        .getRangeSetting2().getRangeSetting("CPSRecalculate").getRangeSetting(15343, () -> this.isArmorCPSRecalculateVisible());
        this.settingArmorDoubleClickRate = new RangeSetting(this, "ArmorDoubleClickRate", 0.0, 0.0, 0.0, 100.0, 0, this.settingArmor).getRangeSetting("DoubleClickRate").getRangeSetting2().getRangeSetting(15343, () -> this.isArmorDoubleClickRateVisible());
        this.settingArmorFailRate = new RangeSetting(this, "ArmorFailRate", 2.0, 5.0, 0.0, 100.0, 0, this.settingArmor).getRangeSetting("FailRate").getRangeSetting2().getRangeSetting(15343, () -> this.isArmorFailRateVisible());
        this.settingArmorMissClickRate = new RangeSetting(this, "ArmorMissClickRate", 0.0, 0.0, 0.0, 100.0, 0, this.settingArmor)
        .getRangeSetting2().getRangeSetting("MissClickRate").getRangeSetting(15343, () -> this.isArmorMissClickRateVisible());
        String[] textArray4 = new String[3];
        textArray4[0] = "Basic";
        textArray4[1] = "Nearest";
        textArray4[2] = "Random";
        this.settingArmorSequence = new ModeSetting(this, "ArmorSequence", "Nearest", textArray4, this.settingArmor);
        this.settingClean = new SettingGroup(this, "Clean").getSettingGroup(() -> ChestStealerSortType.CLEAN.isSelected());
        this.settingKeepAllArmor = new BooleanSetting(this, "KeepAllArmor", false, this.settingClean);
        this.settingCleanInstant = (BooleanSetting)new BooleanSetting(this, "CleanInstant", false, this.settingClean).getSetting("Instant");
        this.settingCleanMaxMouseMoveSpeed = new NumberSetting(this, "CleanMaxMouseMoveSpeed", 19.0, 0.0, 200.0, 0, this.settingClean).getNumberSetting("MaxMouseMoveSpeed").getNumberSetting(() -> this.isCleanMaxMouseMoveSpeedVisible());
        this.settingCleanMouseMoveRandom = new RangeSetting(this, "CleanMouseMoveRandom", 0.0, 0.0, -1.0, 2.0, 0, this.settingClean)
        .getRangeSetting2().getRangeSetting("MouseMoveRandom").getRangeSetting(15343, () -> this.isCleanMouseMoveRandomVisible());
        this.settingCleanCPS = new RangeSetting(this, "CleanCPS", 17.0, 20.0, 0.0, 40.0, 0, this.settingClean).getRangeSetting("CPS").getRangeSetting(15343, () -> this.isCleanCPSVisible());
        this.settingCleanCPSRecalculate = new RangeSetting(this, "CleanCPSRecalculate", 6.0, 8.0, 0.0, 40.0, 0, this.settingClean)
        .getRangeSetting2().getRangeSetting("CPSRecalculate").getRangeSetting(15343, () -> this.isCleanCPSRecalculateVisible());
        this.settingCleanFailRate = new RangeSetting(this, "CleanFailRate", 10.0, 15.0, 0.0, 100.0, 0, this.settingClean).getRangeSetting("FailRate").getRangeSetting2().getRangeSetting(15343, () -> this.isCleanFailRateVisible());
        this.settingCleanMissClickRate = new RangeSetting(this, "CleanMissClickRate", 0.0, 0.0, 0.0, 100.0, 0, this.settingClean)
        .getRangeSetting2().getRangeSetting("MissClickRate").getRangeSetting(15343, () -> this.isCleanMissClickRateVisible());
        String[] textArray5 = new String[3];
        textArray5[0] = "Basic";
        textArray5[1] = "Nearest";
        textArray5[2] = "Random";
        this.settingCleanerSequence = new ModeSetting(this, "CleanerSequence", "Nearest", textArray5, this.settingClean);
        this.settingItemsToDrop = new SettingGroup(this, "ItemsToDrop", this.settingClean).getSettingGroup(() -> ChestStealerSortType.CLEAN.isSelected());
        this.settingItemStackLimits = new SettingGroup(this, "ItemStackLimits", this.settingClean).getSettingGroup(() -> ChestStealerSortType.CLEAN.isSelected());
        this.settingBlockStackLimit = new NumberSetting(this, "BlockStackLimit", 10.0, 0.0, 20.0, 0, this.settingItemStackLimits);
        this.settingFoodStackLimit = new NumberSetting(this, "FoodStackLimit", 4.0, 0.0, 20.0, 0, this.settingItemStackLimits);
        this.settingWaterBucketLimit = new NumberSetting(this, "WaterBucketLimit", 4.0, 0.0, 20.0, 0, this.settingItemStackLimits);
        this.settingLavaBucketLimit = new NumberSetting(this, "LavaBucketLimit", 4.0, 0.0, 20.0, 0, this.settingItemStackLimits);
        this.settingThrowablesLimit = new NumberSetting(this, "ThrowablesLimit", 5.0, 0.0, 20.0, 0, this.settingItemStackLimits);
        this.settingCraft = new SettingGroup(this, "Craft").getSettingGroup(() -> this.isCraftVisible());
        this.settingReCraftIfSoupsLowerThan = new NumberSetting(this, "ReCraftIfSoupsLowerThan", 4.0, 0.0, 20.0, 0, this.settingCraft);
        this.settingCraftInstant = (BooleanSetting)new BooleanSetting(this, "CraftInstant", false, this.settingCraft).getSetting("Instant");
        this.settingCraftMaxMouseMoveSpeed = new NumberSetting(this, "CraftMaxMouseMoveSpeed", 19.0, 0.0, 200.0, 0, this.settingCraft).getNumberSetting("MaxMouseMoveSpeed").getNumberSetting(() -> this.isCraftMaxMouseMoveSpeedVisible());
        this.settingCraftMouseMoveRandom = new RangeSetting(this, "CraftMouseMoveRandom", 0.0, 0.0, -1.0, 2.0, 0, this.settingCraft)
        .getRangeSetting2().getRangeSetting("MouseMoveRandom").getRangeSetting(15343, () -> this.isCraftMouseMoveRandomVisible());
        this.settingCraftCPS = new RangeSetting(this, "CraftCPS", 17.0, 20.0, 0.0, 40.0, 0, this.settingCraft).getRangeSetting("CPS").getRangeSetting(15343, () -> this.isCraftCPSVisible());
        this.settingCraftCPSRecalculate = new RangeSetting(this, "CraftCPSRecalculate", 6.0, 8.0, 0.0, 40.0, 0, this.settingCraft)
        .getRangeSetting2().getRangeSetting("CPSRecalculate").getRangeSetting(15343, () -> this.isCraftCPSRecalculateVisible());
        this.settingArmorHotbar = new SettingGroup(this, "ArmorHotbar").getSettingGroup(() -> this.isArmorHotbarVisible());
        this.settingQSG = new BooleanSetting(this, "QSG", false, this.settingArmorHotbar);
        this.settingTickDelayBeforeSwitch = new RangeSetting(this, "TickDelayBeforeSwitch", 1.0, 2.0, 0.0, 7.0, 0, this.settingArmorHotbar)
        .getRangeSetting2();
        this.settingTickDelayBeforeClick = new RangeSetting(this, "TickDelayBeforeClick", 1.0, 2.0, 0.0, 7.0, 0, this.settingArmorHotbar)
        .getRangeSetting2();
        this.settingTickDelayAfter = new RangeSetting(this, "TickDelayAfter", 1.0, 2.0, 0.0, 7.0, 0, this.settingArmorHotbar).getRangeSetting2();
        Client.getClient();
        String[] textArray6 = new String[1];
        textArray6[0] = "Basic";
        this.settingClickMode = new ModeSetting(this, "ClickMode", "Basic", textArray6);
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        SmoothDamper smoothDamper8 = new SmoothDamper(20, 0.0);
        this.smoothDamper = smoothDamper8;
        this.animationTimer = new AnimationTimer();
        SmoothDamper smoothDamper9 = new SmoothDamper(20, 0.0);
        this.smoothDamper2 = smoothDamper9;
        this.animationTimer2 = new AnimationTimer();
        this.smoothDamper3 = new SmoothDamper(20, 0.0);
        this.animationTimer3 = new AnimationTimer();
        this.smoothDamper4 = new SmoothDamper(100, 0.0);
        this.smoothDamper5 = new SmoothDamper(100, 0.0);
        this.smoothDamper6 = new SmoothDamper(100, 0.0);
        this.smoothDamper7 = new SmoothDamper(20, 0.0);
        this.animationTimer4 = new AnimationTimer();
        this.keyCode2 = 0;
        this.tickDelay4 = 0;
        this.tickDelay5 = 0;
        this.tickDelay = 0;
        this.tickDelay1 = -1;
        this.activeState8 = false;
        this.enabled2 = false;
        ArrayList arrayList = new ArrayList();
        this.itemList3 = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.inventorySlotOrder = arrayList2;
        this.tickDelay3 = -1;
        this.tickDelay2 = -1;
        this.tickDelay7 = 0;
        this.tickDelay6 = 0;
        this.activeState = false;
        this.visible2 = true;
        this.expanded2 = false;
        this.itemPriorityFilter = new ChestItemPriorityMap(
        this, "Items", this.settingItemsToDrop, () -> canExecute7(), object -> InventoryUtil.checkItem3((Item)object)
        );
    }

    @Override
    public void onEnable() {
        this.registerEvent(52, 1);
        this.registerEvent(21);
        this.registerEvent(34);
        this.registerEvent(7, 100);
        this.registerEvent(36, 100);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.processChar();
    }

    @Override
    public void onClick(EventClick event) {
        if (minecraft.isIntegratedServerRunning()) {
            if (minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
                if (minecraft.theWorld.getBlockState(minecraft.objectMouseOver.getBlockPos()).getBlock().isState8()) {
                    return;
                }
            }

            if (this.tickDelay3 > -1) {
                int tickDelay62 = this.tickDelay6;
                if (tickDelay62 > this.settingTickDelayBeforeClick.getIndexOffset2() && !minecraft.thePlayer.isUsingItem() && !this.activeState) {
                    event.updateInt2(1);
                    this.activeState = true;
                }

                double offset = (double)this.tickDelay6 - ((double)this.settingTickDelayBeforeClick.getIndexOffset2() + this.settingTickDelayAfter.getDouble3());
                if (offset > 0.0 && this.activeState) {
                    this.updateState7();
                }
            }

            this.tickDelay6++;
        }
    }

    @Override
    public boolean checkCondition() {
        this.applyString4(this.settingMode.getSelected());
        boolean enabled = this.checkCondition15();
        this.animationTimer.processAction();
        this.animationTimer2.processAction();
        this.animationTimer3.processAction();
        this.animationTimer4.processAction();
        return enabled;
    }

    @Override
    public void onMove(EventMove event) {
        if (minecraft.isIntegratedServerRunning() && minecraft.thePlayer.getGuiScreen() != null && this.settingStopMovement.isEnabled()) {
            event.setState(false);
            event.setState4(false);
            event.setScaleFactor7(0.0F);
            event.setScaleFactor2(0.0F);
        }
    }

    @Override
    public void onPostMoveInput(EventMoveInput event) {
        if (minecraft.isIntegratedServerRunning() && minecraft.thePlayer.getGuiScreen() != null && this.settingStopSprinting.isEnabled()) {
            event.setState(false);
            event.setActive5(false);
        }
    }

    @Override
    public void onPostMotion() {
        if (ChestStealerSortType.PUT_ON_ARMOR_HOT_BAR.isSelected()
        && this.settingMode.getSelected().equals("OpenInv")
        && minecraft.isIntegratedServerRunning()) {
            if (minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
                Block block = minecraft.theWorld.getBlockState(minecraft.objectMouseOver.getBlockPos()).getBlock();
                if (block.isState8() || BlockCache.processAction(block)) {
                    return;
                }
            }

            int tickDelay32 = this.tickDelay3;
            this.tickDelay3 = this.getInt2();
            this.tickDelay2 = tickDelay32;
            if (this.tickDelay3 > -1) {
                int tickDelay33 = this.tickDelay3;
                SlotManager.applyModule3(this, 5, tickDelay33);
            }
        } else {
            this.itemList3.clear();
            this.updateState7();
        }
    }

    private boolean checkArrayList(
    ArrayList arrayList,
    ArrayList arrayList2,
    SmoothDamper smoothDamper8,
    RangeSetting rangeSetting,
    RangeSetting rangeSetting2,
    
    RangeSetting rangeSetting3,
    RangeSetting rangeSetting4,
    Container container,
    boolean enabled,
    int x,
    int y,
    int z
    ) {
        if (enabled) {
            arrayList.forEach(object -> ((Runnable)object).run());
            return true;
        } else {
            if (!arrayList2.isEmpty()) {
                int index = arrayList2.size() > 1 ? 1 : 0;
                ItemStack itemStack = (ItemStack)arrayList2.get(index);
                if (itemStack != null) {
                    this.tickDelay1 = itemStack.getStackSize();
                }
            }

            if (this.checkSmoothDamper(this.smoothDamper5, rangeSetting2)) {
                return false;
            } else {
                int index2 = this.settingMode.getSelected().equals("SpoofInv") ? 1 : smoothDamper8.getInt(x);

                while (index2 > 0) {
                    if (arrayList.isEmpty()) {
                        return false;
                    }

                    if (this.checkRangeSetting(rangeSetting3, container)) {
                        index2--;
                    } else {
                        index2 = this.findItemSlot(y, z, container, ((ItemStack)arrayList2.get(0)).getStackSize(), index2, rangeSetting4);
                        if (index2 <= 0) {
                            return false;
                        }

                        Object object2 = arrayList.get(0);
                        Runnable runnable = (Runnable)object2;
                        runnable.run();
                        if (this.checkSmoothDamper(this.smoothDamper4, rangeSetting)) {
                            runnable.run();
                        }

                        arrayList.remove(0);
                        arrayList2.remove(0);
                        index2--;
                    }
                }

                return false;
            }
        }
    }

    private void applyContainer(int index, Container container) {
        this.applyContainer11(index, -1, container);
    }

    private void applyItemStack(ItemStack itemStack, Container container) {
        this.applyItemStack4(itemStack, container);
    }

    private void applyContainer2(int index, int index2, Container container) {
        this.applyContainer11(index, index2, container);
    }

    private void applyContainer3(Container container, int index) {
        ScaledResolution scaledResolution = new ScaledResolution(minecraft);
        int index2 = (scaledResolution.getScaledWidth() - 176) / 2;
        int index3 = (scaledResolution.getScaledHeight() - 166) / 2;
        Slot slot = container.getSlot(index);
        double[] coordOffsets = InventoryUtil.getCoordOffsetArray();
        if (coordOffsets != null && coordOffsets.length >= 2) {
            coordOffsets[0] = (double)(slot.indexOffset + 2 + index2);
            coordOffsets[1] = (double)(slot.indexOffset2 + 2 + index3);
        }
        this.timer.reset();
    }

    private void processChar() {
        this.settingStartDelay.recalculate();
        this.settingArmorCPS.recalculate();
        this.settingArmorCPSRecalculate.recalculate();
        this.settingSortCPS.recalculate();
        this.settingSortCPSRecalculate.recalculate();
        this.settingCleanCPS.recalculate();
        this.settingCleanCPSRecalculate.recalculate();
        this.settingArmorDoubleClickRate.recalculate();
        this.settingCleanMissClickRate.recalculate();
        this.settingArmorMissClickRate.recalculate();
        this.settingSortFailRate.recalculate();
        this.settingArmorFailRate.recalculate();
        this.settingCleanFailRate.recalculate();
        this.settingCraftCPS.recalculate();
        this.settingCraftCPSRecalculate.recalculate();
        this.settingTickDelayBeforeSwitch.recalculate();
        this.settingTickDelayBeforeClick.recalculate();
        this.settingTickDelayAfter.recalculate();
        this.tickDelay1 = -1;
        this.updateState();
    }

    private void applyContainer4(int index, Container container) {
        this.applyContainer8(index, 0, container);
    }

    private void applyItemStack2(ItemStack itemStack, Container container) {
        this.applyItemStack4(itemStack, container);
    }

    private void applyContainer5(int index, int index2, Container container) {
        this.applyContainer11(index, index2, container);
    }

    private void applyContainer6(int index, int index2, Container container) {
        this.applyContainer11(index, index2, container);
    }

    private boolean checkCondition(boolean enabled) {
        if (ChestStealerSortType.CRAFT.isSelected() && !this.canExecute5()) {
            String selected = this.settingMode.getSelected();
            if (selected.equals("OpenInv") && !enabled) {
                int index = this.tickDelay4 + 1;
                this.tickDelay4 = index;
                Container container = minecraft.thePlayer.container;
                int[] indexOffsetArray = this.getIndexOffsetArray(container);
                if (indexOffsetArray[1] != 0 && indexOffsetArray[2] != 0 && indexOffsetArray[3] != 0) {
                    int index2 = indexOffsetArray[1];
                    int index3 = indexOffsetArray[2];
                    int index4 = indexOffsetArray[3];
                    int index5 = Math.min(index3, index4);
                    int index6 = Math.min(index2, index5);
                    int index7 = indexOffsetArray[0];
                    if (index6 <= 0) {
                        this.activeState8 = false;
                        return true;
                    } else {
                        byte state;
                        if (index6 > index7) {
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
                        int index8 = 1;

                        while (true) {
                            int size = minecraft.thePlayer.container.itemList2.size();
                            if (index8 >= size) {
                                return true;
                            }

                            int index9 = 0;
                            boolean[] conditionArray2 = null;
                            if (index8 != 1) {
                                conditionArray2 = conditionArray;
                                index9 = index8;
                            } else {
                                conditionArray2 = new boolean[]{false, false, false};
                                Container container2 = minecraft.thePlayer.container;
                                index7 = this.getIndexOffsetArray(container2)[0];
                                index9 = index8;
                            }

                            ItemStack itemStack = minecraft.thePlayer.container.getSlot(index9).getStack();
                            int index10 = 0;
                            if (itemStack == null) {
                                conditionArray = conditionArray2;
                                index10 = index7;
                            } else {
                                boolean completed2 = false;
                                Item item3 = itemStack.getItem();
                                if (index9 < 5) {
                                    int index11 = 0;

                                    while (true) {
                                        int length = Array.getLength(itemArray);
                                        if (index11 >= length) {
                                            break;
                                        }

                                        if (itemArray[index11].equals(item3)) {
                                            this.activeState8 = true;
                                            if (conditionArray2 instanceof boolean[]) {
                                                conditionArray2[index11] = true;
                                            } else {
                                                ((byte[])((Object)(conditionArray2)))[index11] = 1;
                                            }
                                        }

                                        index11++;
                                    }
                                }
                                if (index7 > 0) {
                                    double offset = (double)indexOffsetArray[4];
                                    double value = this.settingReCraftIfSoupsLowerThan.getValue();
                                    if ((!Double.isNaN(offset) && !Double.isNaN(value) ? (offset > value ? 1 : (offset == value ? 0 : -1)) : 1) <= 0) {
                                        if (minecraft.thePlayer.inventoryPlayer.getItemStack() == null) {
                                            boolean completed = false;
                                            if (index9 > 8) {
                                                Object object = itemArray[0];
                                                if (!item3.equals(object)
                                                || (conditionArray2 instanceof boolean[] ? (((boolean[])conditionArray2)[0] ? 1 : 0) : ((byte[])((Object)(conditionArray2)))[0]) != 0) {
                                                    Object object2 = itemArray[1];
                                                    if (!item3.equals(object2)
                                                    || (conditionArray2 instanceof boolean[] ? (((boolean[])conditionArray2)[1] ? 1 : 0) : ((byte[])((Object)(conditionArray2)))[1]) != 0) {
                                                        Object object3 = itemArray[2];
                                                        if (!item3.equals(object3)
                                                        || (conditionArray2 instanceof boolean[] ? (((boolean[])conditionArray2)[2] ? 1 : 0) : ((byte[])((Object)(conditionArray2)))[2]) != 0) {
                                                            completed = true;
                                                        }
                                                    }
                                                }
                                                if (!completed) {
                                                    if (this.checkCondition(index9)) {
                                                        return false;
                                                    }
                                                    this.activeState8 = true;
                                                    Container container3 = minecraft.thePlayer.container;
                                                    this.applyContainer15(index9, state, container3);
                                                    index9 = 0;
                                                }
                                            }
                                            if (index9 <= 4) {
                                                index10 = index7;
                                                conditionArray = conditionArray2;
                                            } else if ((conditionArray2 instanceof boolean[] ? (((boolean[])conditionArray2)[0] ? 1 : 0) : ((byte[])((Object)(conditionArray2)))[0]) == 0) {
                                                index10 = index7;
                                                conditionArray = conditionArray2;
                                            } else if ((conditionArray2 instanceof boolean[] ? (((boolean[])conditionArray2)[1] ? 1 : 0) : ((byte[])((Object)(conditionArray2)))[1]) == 0) {
                                                index10 = index7;
                                                conditionArray = conditionArray2;
                                            } else if ((conditionArray2 instanceof boolean[] ? (((boolean[])conditionArray2)[2] ? 1 : 0) : ((byte[])((Object)(conditionArray2)))[2]) == 0) {
                                                index10 = index7;
                                                conditionArray = conditionArray2;
                                            } else {
                                                if (this.checkCondition(0)) {
                                                    return false;
                                                }

                                                Container container4 = minecraft.thePlayer.container;
                                                this.applyContainer11(0, -1, container4);
                                                index10 = index7;
                                                conditionArray = conditionArray2;
                                                index9 = 0;
                                            }
                                            completed2 = true;
                                        }
                                        if (!completed2) {
                                            Item item4 = minecraft.thePlayer.inventoryPlayer.getItemStack().getItem();
                                            int index12 = index9;
                                            int index13 = 0;
                                            while (index13 < 3) {
                                                Object object4 = itemArray[index13];
                                                ItemStack itemStack2;
                                                boolean[] conditionArray3;
                                                Item item5;
                                                if (!item4.equals(object4)) {
                                                    conditionArray3 = conditionArray2;
                                                    itemStack2 = itemStack;
                                                    item5 = item3;
                                                } else {
                                                    int index14 = conditionArray2 instanceof boolean[] ? (((boolean[])conditionArray2)[index13] ? 1 : 0) : ((byte[])((Object)(conditionArray2)))[index13];
                                                    item5 = item3;
                                                    if (index14 == 0) {
                                                        conditionArray3 = conditionArray2;
                                                        int index15 = index13 + 1;
                                                        if (this.checkCondition(index15)) {
                                                            return false;
                                                        }

                                                        this.activeState8 = false;
                                                        int index16 = index13 + 1;
                                                        Container container5 = minecraft.thePlayer.container;
                                                        this.applyContainer15(index16, 0, container5);
                                                        index12 = 0;
                                                        itemStack2 = itemStack;
                                                    } else {
                                                        conditionArray3 = conditionArray2;
                                                        itemStack2 = itemStack;
                                                    }
                                                }

                                                int index17 = index13 + 1;
                                                conditionArray2 = conditionArray3;
                                                itemStack = itemStack2;
                                                item3 = item5;
                                                index13 = index17;
                                            }
                                            conditionArray = conditionArray2;
                                            index10 = index7;
                                            index9 = index12;
                                            completed2 = true;
                                        }
                                    }
                                }
                                if (!completed2) {
                                    if (index9 <= 5) {
                                        index10 = index7;
                                        conditionArray = conditionArray2;
                                    } else {
                                        int state2 = 0;

                                        while (true) {
                                            int length2 = Array.getLength(itemArray);
                                            if (state2 >= length2) {
                                                index10 = index7;
                                                conditionArray = conditionArray2;
                                                break;
                                            }

                                            int index18 = 0;
                                            boolean[] conditionArray4 = null;
                                            Item item6 = null;
                                            boolean[] conditionArray5 = conditionArray2;
                                            int index19 = conditionArray2 instanceof boolean[] ? (((boolean[])conditionArray2)[state2] ? 1 : 0) : ((byte[])((Object)(conditionArray2)))[state2];
                                            index18 = index7;
                                            Item item7 = item3;
                                            boolean completed = false;
                                            if (index19 != 0) {
                                                int index20 = index9;
                                                byte state3 = (byte)state2;
                                                ItemStack itemStack3 = null;
                                                int index21 = -1;
                                                int index22 = 0;
                                                while (index22 < 5) {
                                                    ItemStack itemStack4 = minecraft.thePlayer.container.getSlot(index22).getStack();
                                                    ItemStack itemStack5 = itemStack;
                                                    int index23 = index22;
                                                    int index24;
                                                    boolean[] conditionArray6;
                                                    if (itemStack4 == null) {
                                                        index24 = index18;
                                                        conditionArray6 = conditionArray5;
                                                    } else {
                                                        index24 = index18;
                                                        conditionArray6 = conditionArray5;
                                                        Item item8 = itemStack4.getItem();
                                                        Object object5 = itemArray[state3];
                                                        if (item8.equals(object5)) {
                                                            index21 = index22;
                                                            itemStack3 = itemStack4;
                                                            break;
                                                        }

                                                        itemStack5 = itemStack;
                                                        index23 = index22;
                                                    }

                                                    int index25 = index23 + 1;
                                                    index18 = index24;
                                                    conditionArray5 = conditionArray6;
                                                    itemStack = itemStack5;
                                                    index22 = index25;
                                                }
                                                ItemStack itemStack6 = itemStack;
                                                ItemStack itemStack7 = itemStack3;
                                                int index26 = 0;
                                                if (index21 == 0) {
                                                    itemStack6 = itemStack;
                                                    index26 = index9;
                                                } else {
                                                    index26 = index21;
                                                }
                                                boolean enabled3 = this.checkCondition(index26);
                                                itemStack = itemStack6;
                                                if (enabled3) {
                                                    return false;
                                                }
                                                item6 = item7;
                                                byte state4 = state3;
                                                int state5 = 9;
                                                ItemStack itemStack8 = null;
                                                while (true) {
                                                    int size2 = minecraft.thePlayer.container.itemList2.size();
                                                    if (state5 >= size2) {
                                                        conditionArray4 = conditionArray5;
                                                        index9 = index20;
                                                        state2 = state4;
                                                        { completed = true; break; }
                                                    }
                                                    if (!completed) {
                                                        ItemStack itemStack9 = minecraft.thePlayer.container.getSlot(state5).getStack();
                                                        byte state6 = (byte)0;
                                                        Item item9 = null;
                                                        if (itemStack7 == null) {
                                                            item9 = item6;
                                                            state6 = state4;
                                                        } else {
                                                            if (itemStack9 == null) {
                                                                itemStack8 = itemStack;
                                                                break;
                                                            }

                                                            Item item10 = itemStack9.getItem();
                                                            Item item11 = itemStack7.getItem();
                                                            boolean enabled4 = item10.equals(item11);
                                                            itemStack8 = itemStack;
                                                            if (!enabled4) {
                                                                item9 = item6;
                                                                state6 = state4;
                                                            } else {
                                                                int animationsToGo = itemStack9.animationsToGo;
                                                                int index27 = itemStack7.processAction5();
                                                                if (animationsToGo < index27) {
                                                                    break;
                                                                }

                                                                item9 = item6;
                                                                state6 = state4;
                                                            }
                                                        }
                                                        state5 += 1;
                                                        item6 = item9;
                                                        state4 = state6;
                                                    }
                                                }
                                                if (!completed) {
                                                    Container container6 = minecraft.thePlayer.container;
                                                    this.applyContainer11(index21, -1, container6);
                                                    this.activeState8 = false;
                                                    conditionArray4 = conditionArray5;
                                                    index9 = 0;
                                                    itemStack = itemStack8;
                                                    state2 = state4;
                                                }
                                            } else {
                                                conditionArray4 = conditionArray2;
                                                item6 = item3;
                                            }


                                            state2 += 1;
                                            index7 = index18;
                                            conditionArray2 = conditionArray4;
                                            item3 = item6;
                                        }
                                    }
                                }

                            }

                            index8 = index9 + 1;
                            index7 = index10;
                        }
                    }
                } else {
                    this.activeState8 = false;
                    return true;
                }
            } else {
                this.activeState8 = false;
                return true;
            }
        } else {
            return true;
        }
    }

    public ArrayList getArrayList(String text) {
        ArrayList arrayList = new ArrayList();
        this.settingSlot1.getSelected().equals(text);
        return arrayList;
    }

    public ArrayList getArrayList(Container container, String text) {
switch (text) {
            case "Sword":
                return InventoryUtil.getArrayList(container, 5);
            case "Bow":
                return InventoryUtil.getArrayList3(container, 5);
            case "Rod":
                return InventoryUtil.getArrayList4(container, 5);
            case "Throwables":
                return InventoryUtil.getSortedContainerList(container, 5);
            case "Blocks":
                return InventoryUtil.getArrayList(container, 5);
            case "Food":
                return InventoryUtil.getArrayList((short)0, (char)29691, 1541469196, container, 5);
            case "Gapple":
                return InventoryUtil.getArrayList2(container, 5);
            case "EnderPearls":
                return InventoryUtil.getArrayList2(container, 5);
            case "Soups":
                return InventoryUtil.getArrayList(11913, (short)-4249, 49061, container, 5);
            default:
                return new ArrayList();
        }
    }

    private void applyContainer7(int index, Container container) {
        this.applyContainer8(index, 0, container);
    }

    private void applyItemStack3(int index, ItemStack itemStack, Container container) {
        int stackSize = itemStack.getStackSize();
        container.getSlot(index + 36).getStack();
        switch (this.settingClickMode.getSelected().hashCode()) {
            case 0:
                Client.getClient().getClass();
                minecraft.playerController.windowClick(minecraft.thePlayer.container.indexOffset2, stackSize, index, 2, minecraft.thePlayer);
                this.updateState();
                break;
            case 1:
            case 2:
                minecraft.playerController.windowClick(minecraft.thePlayer.container.indexOffset2, stackSize, index, 2, minecraft.thePlayer);
                this.updateState();
                break;
            default:
                this.updateState();
        }
    }

    private boolean checkRangeSetting(RangeSetting rangeSetting, Container container) {
        return false;
    }

    private void processChar3() {
        if (this.settingMode.getSelected().equals("SpoofInv") || this.settingMode.getSelected().equals("SpoofInvOther")) {
            if (minecraft.thePlayer.getGuiScreen() == null) {
                if (minecraft.currentScreen == null) {
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C16PacketClientStatus(AutoSoupPerformRespawnType.OPEN_INVENTORY_ACHIEVEMENT));
                    minecraft.thePlayer.setGuiScreen(new GuiInventory(minecraft.thePlayer));
                }
            }
        }
    }

    private boolean checkCondition(boolean enabled3, boolean enabled4) {
        if (!ChestStealerSortType.SORT.isSelected()) {
            return true;
        } else if (this.canExecute5()) {
            return true;
        } else {
            this.tickDelay5++;
            Container container = minecraft.thePlayer.container;
            HashMap hashMap = new HashMap();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            HashMap hashMap2 = new HashMap();
            String[] settingSoups2 = this.soupNames;
            int length = settingSoups2.length;
            int index3 = 0;

            boolean enabled5;
            outerLoop:
            while (true) {
                if (index3 >= length) {
                    if (arrayList3.isEmpty()) {
                        return true;
                    }

                    String selected = this.settingSortSequence.getSelected();

                    for (int index4 : ((Iterable<Integer>) InventoryUtil.getArrayList(container, arrayList3, selected))) {
                        ItemStack itemStack = container.getSlot(index4).getStack();
                        itemStack.setStackSize(index4);
                        int index5 = (Integer)hashMap2.get(index4);
                        arrayList.add(((Runnable)(() -> this.applyItemStack5(index5, itemStack, container))));
                        arrayList2.add(itemStack);
                    }

                    enabled5 = arrayList.isEmpty();
                    break;
                }

                String text = settingSoups2[index3];
                if (enabled4 ? text.equals("Sword") : !text.equals("None") && !text.equals("Sword")) {
                    ArrayList arrayList4 = this.getArrayList(text);
                    Iterator iterator = arrayList4.iterator();
                    if (iterator.hasNext()) {
                        int index6 = (Integer)iterator.next();
                        int index7 = index6;

                        while (true) {
                            if (index7 != -1) {
                                boolean enabled6 = hashMap.containsKey(text);
                                ArrayList arrayList6;
                                if (enabled6) {
                                    arrayList6 = (ArrayList)hashMap.get(text);
                                } else {
                                    arrayList6 = this.getArrayList(container, text);
                                }

                                if (!enabled6) {
                                    hashMap.put(text, arrayList6);
                                }

                                if (!arrayList6.isEmpty()) {
                                    Pair pair = (Pair)arrayList6.get(0);
                                    ArrayList arrayList5 = new ArrayList();

                                    for (Pair pair2 : (Iterable<Pair>) arrayList6) {
                                        if ((Double)pair2.getSecond() >= (Double)pair.getSecond()) {
                                            arrayList5.add(pair2);
                                        }
                                    }

                                    int size = arrayList5.size();
                                    if (size > 1) {
                                        while (size < arrayList5.size()) {
                                            Pair pair3 = (Pair)arrayList5.get(size);
                                            if (!arrayList4.contains((Integer)pair3.getFirst() - 36)) {
                                                pair = (Pair)arrayList5.get(size);
                                                break;
                                            }

                                            size++;
                                        }
                                    }

                                    if (pair != null) {
                                        int index8 = 0;

                                        for (int i = 0; i < arrayList6.size(); i++) {
                                            Pair pair4 = (Pair)arrayList6.get(i);
                                            if (((Double)pair4.getSecond()).equals(pair.getSecond()) && ((Integer)pair4.getFirst()).equals(pair.getFirst())) {
                                                index8 = i;
                                                break;
                                            }
                                        }

                                        arrayList6.remove(index8);
                                        int first = (Integer)pair.getFirst();
                                        double second = (Double)pair.getSecond();
                                        boolean completed = false;
                                        if (first != index6 + 36 && !arrayList4.contains(first + 36)) {
                                            ItemStack itemStack2 = container.getSlot(index6 + 36).getStack();
                                            double coordinate = InventoryUtil.getCoordinate(itemStack2);
                                            if (second <= coordinate) {
                                                int index = InventoryUtil.getIndex(itemStack2, enabled4);
                                                ItemStack itemStack3 = container.getSlot(first).getStack();
                                                if (index == InventoryUtil.getIndex(itemStack3, enabled4)) {
                                                    completed = true;
                                                }
                                            }
                                            if (!completed) {
                                                arrayList3.add(first);
                                                hashMap2.put(first, index6);
                                            }
                                        }

                                    }
                                }
                            }

                            while (!iterator.hasNext()) {
                                if (++index3 >= length) {
                                    if (arrayList3.isEmpty()) {
                                        return true;
                                    }

                                    String selected2 = this.settingSortSequence.getSelected();

                                    for (int index2 : ((Iterable<Integer>) InventoryUtil.getArrayList(container, arrayList3, selected2))) {
                                        ItemStack itemStack4 = container.getSlot(index2).getStack();
                                        itemStack4.setStackSize(index2);
                                        int index9 = (Integer)hashMap2.get(index2);
                                        arrayList.add(((Runnable)(() -> this.applyItemStack5(index9, itemStack4, container))));
                                        arrayList2.add(itemStack4);
                                    }

                                    enabled5 = arrayList.isEmpty();
                                    break outerLoop;
                                }

                                text = settingSoups2[index3];
                                if (enabled4 ? text.equals("Sword") : !text.equals("None") && !text.equals("Sword")) {
                                    arrayList4 = this.getArrayList(text);
                                    iterator = arrayList4.iterator();
                                }
                            }

                            index6 = (Integer)iterator.next();
                            index7 = index6;
                        }
                    }
                }

                index3++;
            }

            if (enabled5) {
                return true;
            } else if (enabled3) {
                this.processChar3();
                return this.settingSortInstant.isEnabled();
            } else {
                SmoothDamper smoothDamper22 = this.smoothDamper2;
                boolean enabled = this.settingSortInstant.isEnabled();
                int indexOffset2 = this.settingSortCPS.getIndexOffset2();
                int tickDelay52 = this.tickDelay5;
                int sortMaxMouseMoveSpeed = (int)this.settingSortMaxMouseMoveSpeed.getDouble2(true);
                return this.checkArrayList(
                arrayList, arrayList2, smoothDamper22, (RangeSetting)null, this.settingSortFailRate, (RangeSetting)null, this.settingSortMouseMoveRandom, container, enabled, indexOffset2, tickDelay52, sortMaxMouseMoveSpeed
                );
            }
        }
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

    private boolean checkCondition13(boolean enabled5) {
        if (!ChestStealerSortType.PUT_ON_ARMOR.isSelected()) {
            return true;
        } else if (this.canExecute5()) {
            return true;
        } else {
            Container container = null;
            ArrayList arrayList3 = null;
            ArrayList arrayList4 = null;
            int index3 = 0;
            byte state = (byte)0;
            boolean completed2 = false;
            this.keyCode2++;
            container = minecraft.thePlayer.container;
            int index4 = this.settingOnlyInHotbar.isEnabled() ? 36 : 9;
            boolean enabled = this.settingIgnoreDurability.isEnabled();
            ArrayList arrayList = InventoryUtil.getArrayList(container, index4, enabled);
            String selected = this.settingArmorSequence.getSelected();
            ArrayList arrayList2 = InventoryUtil.getArrayList(container, arrayList, selected);
            int index5 = (this.settingArmorSequence.getSelected().equals("Nearest") ? 1 : 0);
            arrayList3 = new ArrayList();
            arrayList4 = new ArrayList();
            int state2 = (byte)0;
            if (!this.settingNoArmorDrop.isEnabled()) {
                this.enabled2 = false;
                Iterator iterator = arrayList2.iterator();
                if (iterator.hasNext()) {
                    index5 = (Integer)iterator.next();
                    state2 = 9;
                } else {
                    state2 = 0;
                }
            } else {
                boolean completed = false;
                Iterator iterator2 = arrayList2.iterator();
                outerLoop2: while (true) {
                    if (!iterator2.hasNext()) {
                        boolean enabled6 = false;
                        boolean enabled7 = false;
                        for (int i = 9; i < container.itemList2.size(); i++) {
                            if (container.getSlot(i).getStack() == null) {
                                if (minecraft.thePlayer.inventoryPlayer.getItemStack() != null && this.enabled2) {
                                    int index = i;
                                    final Container container2 = container; arrayList3.add(((Runnable)(() -> ((InvManager)this).applyContainer7(index, (Container)container2))));
                                    arrayList4.add(minecraft.thePlayer.inventoryPlayer.getItemStack());
                                    enabled7 = true;
                                }

                                if (!enabled7) {
                                    enabled6 = true;
                                    break;
                                }

                                enabled7 = false;
                            }
                        }
                        if (!enabled6) {
                            Iterator iterator3 = arrayList2.iterator();
                            if (iterator3.hasNext()) {
                                index5 = (Integer)iterator3.next();
                                state2 = 9;
                                { completed = true; break; }
                            }
                            if (!completed) {
                                break;
                            }
                        }
                        if (!completed) {
                            for (int index6 : (Iterable<Integer>) arrayList2) {
                                if (index6 >= 9) {
                                    int index7 = ((ItemGoldenBoots)container.getSlot(index6).getStack().getItem()).indexOffset3 + 5;
                                    container.getSlot(index7).getStack();
                                }
                            }
                            Iterator iterator4 = arrayList2.iterator();
                            outerLoop:
                            while (true) {
                                if (!iterator4.hasNext()) {
                                    break outerLoop2;
                                }

                                int index8 = (Integer)iterator4.next();
                                if (index8 >= 9) {
                                    ItemStack itemStack = container.getSlot(index8).getStack();
                                    int index9 = ((ItemGoldenBoots)itemStack.getItem()).indexOffset3 + 5;
                                    ItemStack itemStack2 = container.getSlot(index9).getStack();
                                    if (itemStack2 != null) {
                                        final int index10 = index8; final Container container3 = container; arrayList3.add(((Runnable)(() -> ((InvManager)this).applyContainer5(index10, index9, (Container)container3))));
                                        boolean enabled8 = arrayList4.add(itemStack);

                                        while (true) {
                                            if (minecraft.thePlayer.inventoryPlayer.getItemStack() != null && this.enabled2) {
                                                int index11 = index8;
                                                final Container container4 = container; arrayList3.add(((Runnable)(() -> ((InvManager)this).applyContainer14(index11, (Container)container4))));
                                                arrayList4.add(itemStack2);
                                                this.enabled2 = false;
                                            }

                                            while (true) {
                                                if (!iterator4.hasNext()) {
                                                    break outerLoop;
                                                }

                                                index8 = (Integer)iterator4.next();
                                                if (index8 >= 9) {
                                                    ItemStack itemStack3 = container.getSlot(index8).getStack();
                                                    int index12 = ((ItemGoldenBoots)itemStack3.getItem()).indexOffset3 + 5;
                                                    itemStack2 = container.getSlot(index12).getStack();
                                                    if (itemStack2 != null) {
                                                        final int index13 = index8; final Container container5 = container; arrayList3.add(((Runnable)(() -> ((InvManager)this).applyContainer5(index13, index12, (Container)container5))));
                                                        boolean enabled9 = arrayList4.add(itemStack3);
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        int index14 = (Integer)iterator2.next();
                        if (index14 < 9) {
                            continue;
                        }
                        int index15 = ((ItemGoldenBoots)container.getSlot(index14).getStack().getItem()).indexOffset3 + 5;
                        container.getSlot(index15).getStack();
                        while (iterator2.hasNext()) {
                            int index16 = (Integer)iterator2.next();
                            if (index16 >= 9) {
                                int index17 = ((ItemGoldenBoots)container.getSlot(index16).getStack().getItem()).indexOffset3 + 5;
                                container.getSlot(index17).getStack();
                                break;
                            }
                        }
                        boolean enabled10 = false;
                        boolean enabled11 = false;
                        for (int i2 = 9; i2 < container.itemList2.size(); i2++) {
                            if (container.getSlot(i2).getStack() == null) {
                                if (minecraft.thePlayer.inventoryPlayer.getItemStack() != null && this.enabled2) {
                                    int index2 = i2;
                                    final Container container6 = container; arrayList3.add(((Runnable)(() -> ((InvManager)this).applyContainer7(index2, (Container)container6))));
                                    arrayList4.add(minecraft.thePlayer.inventoryPlayer.getItemStack());
                                    enabled11 = true;
                                }

                                if (!enabled11) {
                                    enabled10 = true;
                                    break;
                                }

                                enabled11 = false;
                            }
                        }
                        if (enabled10) {
                            for (int index18 : (Iterable<Integer>) arrayList2) {
                                if (index18 >= 9) {
                                    int index19 = ((ItemGoldenBoots)container.getSlot(index18).getStack().getItem()).indexOffset3 + 5;
                                    container.getSlot(index19).getStack();
                                }
                            }

                            for (int index20 : (Iterable<Integer>) arrayList2) {
                                if (index20 >= 9) {
                                    ItemStack itemStack4 = container.getSlot(index20).getStack();
                                    int index21 = ((ItemGoldenBoots)itemStack4.getItem()).indexOffset3 + 5;
                                    ItemStack itemStack5 = container.getSlot(index21).getStack();
                                    if (itemStack5 != null) {
                                        final Container container7 = container; arrayList3.add(((Runnable)(() -> ((InvManager)this).applyContainer5(index20, index21, (Container)container7))));
                                        arrayList4.add(itemStack4);
                                        if (minecraft.thePlayer.inventoryPlayer.getItemStack() != null && this.enabled2) {
                                            final Container container8 = container; arrayList3.add(((Runnable)(() -> ((InvManager)this).applyContainer14(index20, (Container)container8))));
                                            arrayList4.add(itemStack5);
                                            this.enabled2 = false;
                                        }
                                    }
                                }
                            }
                        } else {
                            Iterator iterator5 = arrayList2.iterator();
                            if (iterator5.hasNext()) {
                                index3 = (Integer)iterator5.next();
                                state = 9;
                                { completed2 = true; break; }
                            }
                        }
                    }
                    if (!completed2) {
                        if (!completed) {
                            index3 = (arrayList3.isEmpty() ? 1 : 0);
                            state = 0;
                            { completed2 = true; break; }
                        }
                    }
                }
                if (!completed2) {
                    if (!completed) {
                        index5 = (arrayList3.isEmpty() ? 1 : 0);
                        state2 = 0;
                    }
                }
            }
            if (!completed2) {
                if (state2 == 0) {
                    if (index5 != 0) {
                        index5 = (this.enabled2 ? 1 : 0);
                    } else {
                        index5 = enabled5 ? 1 : 0;
                    }
                }
                if (index5 != 0) {
                    this.processChar3();
                    return this.settingArmorInstant.isEnabled();
                }
                SmoothDamper smoothDamper8 = this.smoothDamper;
                boolean enabled3 = this.settingArmorInstant.isEnabled();
                int indexOffset2 = this.settingArmorCPS.getIndexOffset2();
                int keyCode22 = this.keyCode2;
                int armorMaxMouseMoveSpeed = (int)this.settingArmorMaxMouseMoveSpeed.getDouble2(true);
                return this.checkArrayList(arrayList3, arrayList4, smoothDamper8, this.settingArmorDoubleClickRate, this.settingArmorFailRate, this.settingArmorMissClickRate, this.settingArmorMouseMoveRandom, container, enabled3, indexOffset2, keyCode22, armorMaxMouseMoveSpeed) & true;
            }


            if (state == 0) {
                if (index3 != 0) {
                    index3 = (this.enabled2 ? 1 : 0);
                } else {
                    index3 = enabled5 ? 1 : 0;
                }
            }

            if (index3 != 0) {
                this.processChar3();
                return this.settingArmorInstant.isEnabled();
            } else {
                SmoothDamper smoothDamper9 = this.smoothDamper;
                boolean enabled4 = this.settingArmorInstant.isEnabled();
                int indexOffset22 = this.settingArmorCPS.getIndexOffset2();
                int keyCode23 = this.keyCode2;
                int armorMaxMouseMoveSpeed2 = (int)this.settingArmorMaxMouseMoveSpeed.getDouble2(true);
                return this.checkArrayList(arrayList3, arrayList4, smoothDamper9, this.settingArmorDoubleClickRate, this.settingArmorFailRate, this.settingArmorMissClickRate, this.settingArmorMouseMoveRandom, container, enabled4, indexOffset22, keyCode23, armorMaxMouseMoveSpeed2) & true;
            }
        }
    }

    private void applyContainer8(int index, int index2, Container container) {
        this.enabled2 = minecraft.thePlayer.inventoryPlayer.getItemStack() == null;
        this.applyContainer3(container, index);
        minecraft.playerController.windowClick(container.indexOffset2, index, index2, 0, minecraft.thePlayer);
        this.updateState();
    }

    private void applyItemStack4(ItemStack itemStack, Container container) {
        if (itemStack != null) {
            int stackSize = itemStack.getStackSize();
            this.applyContainer3(container, stackSize);
            switch (this.settingClickMode.getSelected().hashCode()) {
                case 0:
                    PlayerControllerMP playerController = minecraft.playerController;
                    int indexOffset2 = minecraft.thePlayer.container.indexOffset2;
                    int stackSize2 = itemStack.getStackSize();
                    playerController.windowClick(indexOffset2, stackSize2, 1, 4, minecraft.thePlayer);
                    Client.getClient().getClass();
                    Client.getClient().getClass();
                    this.updateState();
                    break;
                case 1:
                    Client.getClient().getClass();
                    Client.getClient().getClass();
                    this.updateState();
                    break;
                case 2:
                    Client.getClient().getClass();
                    this.updateState();
                    break;
                default:
                    this.updateState();
            }
        }
    }

    private void applyContainer9(int index, int index2, Container container) {
        this.applyContainer11(index, index2, container);
    }

    private void applyContainer10(Container container) {
        if (minecraft.thePlayer.getGuiScreen() != null) {
            if (!this.settingMode.getSelected().equals("SpoofInv")) {
                return;
            }

            minecraft.thePlayer.setGuiScreen(null);
        }
    }

    private void processAction() {
        if (this.animationTimer.checkCondition(this.settingArmorCPSRecalculate.getIndexOffset2())) {
            this.settingArmorCPS.recalculate();
            this.settingArmorCPSRecalculate.recalculate();
            this.animationTimer.processAction3();
        }

        if (this.animationTimer2.checkCondition(this.settingSortCPSRecalculate.getIndexOffset2())) {
            this.settingSortCPS.recalculate();
            this.settingSortCPSRecalculate.recalculate();
            this.animationTimer2.processAction3();
        }

        if (this.animationTimer3.checkCondition(this.settingCleanCPSRecalculate.getIndexOffset2())) {
            this.settingCleanCPS.recalculate();
            this.settingCleanCPSRecalculate.recalculate();
            this.animationTimer3.processAction3();
        }

        int indexOffset2 = this.settingCraftCPSRecalculate.getIndexOffset2();
        if (this.animationTimer4.checkCondition(indexOffset2)) {
            this.settingCraftCPS.recalculate();
            this.settingCraftCPSRecalculate.recalculate();
            this.animationTimer4.processAction3();
        }
    }

    private int getInt2() {
        Container container = minecraft.thePlayer.container;
        boolean enabled = this.settingIgnoreDurability.isEnabled();
        ArrayList arrayList = InventoryUtil.getArrayList(container, 36, enabled);
        ArrayList arrayList2 = new ArrayList();
        Iterator iterator = arrayList.iterator();
        boolean enabled3 = false;
        boolean enabled4 = true;
        if (iterator.hasNext()) {
            enabled3 = (Integer)iterator.next() != 0;
            enabled4 = false;
        }

        if (enabled4) {
            enabled3 = arrayList2.equals(this.itemList3);
        }

        return enabled3 ? -1 : (Integer)this.inventorySlotOrder.get(0);
    }

    private void applyContainer11(int index, int index2, Container container) {
        this.applyContainer3(container, index);
        String selected = this.settingClickMode.getSelected();
switch (selected) {
            case "Basic":
                minecraft.playerController.windowClick(minecraft.thePlayer.container.indexOffset2, index, 0, 1, minecraft.thePlayer);
                break;
            case "Other":
                Client.getClient().getClass();
                minecraft.playerController.windowClick(minecraft.thePlayer.container.indexOffset2, index, 0, 1, minecraft.thePlayer);
                break;
            case "Exploit":
                Client.getClient().getClass();
        }

        this.updateState();
    }

    private boolean checkCondition(int index) {
        return this.settingCraftInstant.isEnabled();
    }

    private ArrayList getArrayList(Container container) {
        ArrayList arrayList2 = InventoryUtil.getHoeSlotList(container, 5);
        boolean enabled = this.settingIgnoreDurability.isEnabled();
        arrayList2.addAll(InventoryUtil.getArrayList(container, 5, enabled));
        arrayList2.addAll(InventoryUtil.getArrayList3(container, 5));
        int index8;
        ArrayList arrayList10;
        if (ChestStealerSortType.checkChestStealerSortType(ChestStealerSortType.SORT)) {
            ArrayList arrayList = this.getArrayList("Sword");
            ArrayList arrayList11;
            if (arrayList.isEmpty()) {
                arrayList11 = InventoryUtil.getArrayList(container, 5);
            } else {
                arrayList11 = InventoryUtil.getArrayList(container, 5, arrayList);
            }

            for (int i = 0; i < Math.max(1, arrayList.size()); i++) {
                int size = arrayList11.size();
                if (i >= size) {
                    break;
                }

                Pair pair = (Pair)arrayList11.get(i);
                arrayList2.add(pair.getFirst());
            }

            ArrayList arrayList3 = this.getArrayList("Bow");
            ArrayList arrayList12;
            if ((char)(arrayList3.isEmpty() ? 1 : 0) != 0) {
                arrayList12 = InventoryUtil.getArrayList3(container, 5);
            } else {
                arrayList12 = InventoryUtil.getArrayList((char)0, container, 5, 938354857, 50143, arrayList3);
            }

            for (int i2 = 0; i2 < Math.max(1, arrayList3.size()); i2++) {
                int size2 = arrayList12.size();
                if (i2 >= size2) {
                    break;
                }

                Pair pair2 = (Pair)arrayList12.get(i2);
                arrayList2.add(pair2.getFirst());
            }

            ArrayList arrayList4 = this.getArrayList("Rod");
            ArrayList arrayList13;
            if (arrayList4.isEmpty()) {
                arrayList13 = InventoryUtil.getArrayList4(container, 5);
            } else {
                arrayList13 = InventoryUtil.getArrayList(container, 5, arrayList4);
            }

            int index = 0;

            while (true) {
                if (index >= Math.max(1, arrayList4.size())) {
                    arrayList10 = new ArrayList();
                    index8 = 9;
                    break;
                }

                int size3 = arrayList13.size();
                if (index >= size3) {
                    arrayList10 = new ArrayList();
                    index8 = 9;
                    break;
                }

                Pair pair3 = (Pair)arrayList13.get(index);
                arrayList2.add(pair3.getFirst());
                index++;
            }
        } else {
            ArrayList arrayList5 = InventoryUtil.getArrayList(container, 5);
            if (!arrayList5.isEmpty()) {
                Pair pair4 = (Pair)arrayList5.get(0);
                arrayList2.add(pair4.getFirst());
            }

            ArrayList arrayList32 = InventoryUtil.getArrayList3(container, 5);
            if (!arrayList32.isEmpty()) {
                Pair pair5 = (Pair)arrayList32.get(0);
                arrayList2.add(pair5.getFirst());
            }

            ArrayList arrayList42 = InventoryUtil.getArrayList4(container, 5);
            if (!arrayList42.isEmpty()) {
                Pair pair6 = (Pair)arrayList42.get(0);
                arrayList2.add(pair6.getFirst());
            }

            arrayList10 = new ArrayList();
            index8 = 9;
        }

        while (index8 < container.itemList2.size()) {
            ItemStack itemStack = container.getSlot(index8).getStack();
            if (itemStack == null) {
                index8++;
            } else if (!arrayList2.contains(index8)) {
                Item item = itemStack.getItem();
                if (this.itemPriorityFilter.checkItem(item)) {
                    arrayList10.add(index8);
                }

                index8++;
            } else {
                index8++;
            }
        }

        ArrayList arrayList6 = InventoryUtil.getArrayList(container, 9);
        double size4 = (double)arrayList6.size();
        if (size4 > this.settingBlockStackLimit.getValue()) {
            int blockStackLimit = (int)this.settingBlockStackLimit.getValue();

            for (int i3 = arrayList6.size(); blockStackLimit < i3; i3 = arrayList6.size()) {
                int first = (Integer)((Pair)arrayList6.get(blockStackLimit)).getFirst();
                if (!arrayList2.contains(first)) {
                    arrayList10.add(first);
                }

                blockStackLimit++;
            }
        }

        ArrayList arrayList7 = InventoryUtil.getArrayList((short)0, (char)29691, 1541469196, container, 9);
        double size5 = (double)arrayList7.size();
        if (size5 > this.settingFoodStackLimit.getValue()) {
            for (int i4 = (int)this.settingFoodStackLimit.getValue(); i4 < arrayList7.size(); i4++) {
                int first2 = (Integer)((Pair)arrayList7.get(i4)).getFirst();
                if (!arrayList2.contains(first2)) {
                    arrayList10.add(first2);
                }
            }
        }

        ArrayList arrayList8 = InventoryUtil.getSortedContainerList(container, 9);
        double size6 = (double)arrayList8.size();
        ArrayList arrayList14;
        if (size6 <= this.settingThrowablesLimit.getValue()) {
            Item[] itemArray = new Item[]{Items.lavaBucket};
            arrayList14 = InventoryUtil.getArrayList(container, 9, itemArray);
        } else {
            int throwablesLimit = (int)this.settingThrowablesLimit.getValue();

            for (int i5 = arrayList8.size(); throwablesLimit < i5; i5 = arrayList8.size()) {
                int first3 = (Integer)((Pair)arrayList8.get(throwablesLimit)).getFirst();
                if (!arrayList2.contains(first3)) {
                    arrayList10.add(first3);
                }

                throwablesLimit++;
            }

            Item[] itemArray2 = new Item[]{Items.lavaBucket};
            arrayList14 = InventoryUtil.getArrayList(container, 9, itemArray2);
        }

        double size7 = (double)arrayList14.size();
        if (!(size7 <= this.settingLavaBucketLimit.getValue())) {
            for (int i6 = (int)this.settingLavaBucketLimit.getValue(); i6 < arrayList14.size(); i6++) {
                int index2 = (Integer)arrayList14.get(i6);
                if (!arrayList2.contains(index2)) {
                    arrayList10.add(index2);
                }
            }
        }

        Item[] itemArray3 = new Item[]{Items.waterBucket};
        ArrayList arrayList9 = InventoryUtil.getArrayList(container, 9, itemArray3);
        double size8 = (double)arrayList9.size();
        if (size8 <= this.settingWaterBucketLimit.getValue()) {
            return arrayList10;
        } else {
            int waterBucketLimit = (int)this.settingWaterBucketLimit.getValue();
            int index3 = waterBucketLimit;

            for (int i7 = arrayList9.size(); waterBucketLimit < i7; i7 = arrayList9.size()) {
                int index4 = (Integer)arrayList9.get(index3);
                if (!arrayList2.contains(index4)) {
                    arrayList10.add(index4);
                }

                waterBucketLimit = ++index3;
            }

            return arrayList10;
        }
    }

    private int findItemSlot(int index, int index2, Container container, int index3, int index4, RangeSetting rangeSetting) {
        if (!this.settingMode.getSelected().equals("SpoofInv")) {
            Slot slot = container.getSlot(index3);
            ScaledResolution scaledResolution = new ScaledResolution(minecraft);
            int index5 = (scaledResolution.getScaledWidth() - 176) / 2;
            int index6 = (scaledResolution.getScaledHeight() - 166) / 2;
            double offset = Math.abs((double)(slot.indexOffset + 2 + index5) - InventoryUtil.getCoordOffsetArray()[0]);
            double offset2 = Math.abs((double)(slot.indexOffset2 + 2 + index6) - InventoryUtil.getCoordOffsetArray()[1]);
            double offset3 = Math.sqrt(offset * offset + offset2 * offset2);
            byte state = (byte)(CosmeticsPayload.checkCondition(1) ? 40 : 0);
            int index7 = (int)(offset3 / (double)(index2 + state));
            if (index7 > 0) {
                index7 += rangeSetting.getIndexOffset2();
            }

            if (index <= index7) {
                return 0;
            } else {
                if (offset3 > 19.0) {
                    rangeSetting.recalculate();
                }

                return index4;
            }
        } else {
            return index4;
        }
    }

    public String[] getStringArray() {
        return this.soupNames;
    }

    private boolean isCleanMaxMouseMoveSpeedVisible() {
        return this.settingCleanInstant.isEnabled();
    }

    private void applyContainer12(int index, Container container) {
        this.applyContainer8(index, 0, container);
    }

    public int findItemSlot() {
        return this.tickDelay1;
    }

    private boolean checkCondition14(boolean enabled3) {
        if (!ChestStealerSortType.CLEAN.isSelected()) {
            return true;
        } else if (this.canExecute5()) {
            return true;
        } else {
            this.tickDelay++;
            Container container = minecraft.thePlayer.container;
            ArrayList arrayList = this.getArrayList(container);
            if (arrayList.isEmpty()) {
                return true;
            } else {
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                String selected = this.settingCleanerSequence.getSelected();

                for (int index : ((Iterable<Integer>) InventoryUtil.getArrayList(container, arrayList, selected))) {
                    ItemStack itemStack = container.getSlot(index).getStack();
                    if (itemStack != null && (!this.settingKeepAllArmor.isEnabled() || !(itemStack.getItem() instanceof ItemGoldenBoots))) {
                        itemStack.setStackSize(index);
                        arrayList3.add(itemStack);
                        arrayList2.add(((Runnable)(() -> this.applyItemStack2(itemStack, container))));
                    }
                }

                if (arrayList2.isEmpty()) {
                    return true;
                } else if (enabled3) {
                    this.processChar3();
                    return this.settingCleanInstant.isEnabled();
                } else {
                    SmoothDamper smoothDamper32 = this.smoothDamper3;
                    boolean enabled = this.settingCleanInstant.isEnabled();
                    int indexOffset2 = this.settingCleanCPS.getIndexOffset2();
                    int tickDelay8 = this.tickDelay;
                    int cleanMaxMouseMoveSpeed = (int)this.settingCleanMaxMouseMoveSpeed.getDouble2(true);
                    return this.checkArrayList(arrayList2, arrayList3, smoothDamper32, (RangeSetting)null, this.settingCleanFailRate, this.settingCleanMissClickRate, this.settingCleanMouseMoveRandom, container, enabled, indexOffset2, tickDelay8, cleanMaxMouseMoveSpeed);
                }
            }
        }
    }

    private void applyContainer13(int index, int index2, Container container) {
        this.applyContainer11(index, index2, container);
    }

    private void updateState7() {
        this.activeState = false;
        this.tickDelay3 = -1;
        this.settingTickDelayBeforeSwitch.recalculate();
        this.settingTickDelayBeforeClick.recalculate();
        this.settingTickDelayAfter.recalculate();
    }

    private boolean checkSmoothDamper(SmoothDamper smoothDamper8, RangeSetting rangeSetting) {
        return rangeSetting == null ? false : smoothDamper8.checkCondition(rangeSetting.getIndexOffset2());
    }

    private void applyContainer14(int index, Container container) {
        this.applyContainer8(index, 0, container);
    }

    private void applyContainer15(int index, int index2, Container container) {
        this.applyContainer3(container, index);
        minecraft.playerController.windowClick(container.indexOffset2, index, index2, 0, minecraft.thePlayer);
        this.updateState();
    }

    public void updateState() {
        this.keyCode2 = 0;
        this.tickDelay5 = 0;
        this.tickDelay = 0;
        this.tickDelay4 = 0;
    }

    private void applyContainer16(int index, Container container) {
        this.applyContainer8(index, 0, container);
    }

    private void applyItemStack5(int index, ItemStack itemStack, Container container) {
        this.applyItemStack3(index, itemStack, container);
    }

    private boolean isArmorMaxMouseMoveSpeedVisible() {
        return this.settingArmorInstant.isEnabled();
    }

    private boolean canExecute5() {
        return this.settingNotWhileScaffold.isEnabled();
    }

    private boolean canExecute6() {
        int index = minecraft.thePlayer.ticksExisted - this.tickDelay7;
        int indexOffset2 = this.settingTickDelayBeforeSwitch.getIndexOffset2();
        return index >= indexOffset2 || this.tickDelay2 > -1 || this.tickDelay3 > -1;
    }

    private boolean isCleanMissClickRateVisible() {
        return this.settingCleanInstant.isEnabled();
    }

    private boolean isCraftVisible() {
        return ChestStealerSortType.checkChestStealerSortType(ChestStealerSortType.CRAFT);
    }

    private boolean isSortMaxMouseMoveSpeedVisible() {
        return this.settingSortInstant.isEnabled();
    }

    private boolean isSortCPSVisible() {
        return this.settingSortInstant.isEnabled();
    }

    private boolean isCraftMaxMouseMoveSpeedVisible() {
        return this.settingCraftInstant.isEnabled();
    }

    private boolean isStartDelayVisible() {
        return this.settingMode.getSelected().equals("SpoofInv");
    }

    private static boolean canExecute7() {
        return ChestStealerSortType.checkChestStealerSortType(ChestStealerSortType.CLEAN);
    }

    private boolean isSortCPSRecalculateVisible() {
        return this.settingSortInstant.isEnabled();
    }

    private boolean isArmorCPSVisible() {
        return this.settingArmorInstant.isEnabled();
    }

    private boolean isArmorFailRateVisible() {
        return this.settingArmorInstant.isEnabled();
    }

    private boolean isArmorMouseMoveRandomVisible() {
        return this.settingArmorInstant.isEnabled();
    }

    private boolean isArmorMissClickRateVisible() {
        return this.settingArmorInstant.isEnabled();
    }

    private boolean isCleanMouseMoveRandomVisible() {
        return this.settingCleanInstant.isEnabled();
    }

    private boolean isSortFailRateVisible() {
        return this.settingSortInstant.isEnabled();
    }

    private boolean isArmorHotbarVisible() {
        return ChestStealerSortType.checkChestStealerSortType(ChestStealerSortType.PUT_ON_ARMOR_HOT_BAR);
    }

    public boolean checkCondition2() {
        return minecraft.theWorld != null ? this.settingBlockInputWhileManaging.isEnabled() : false;
    }

    private boolean isCleanCPSVisible() {
        return this.settingCleanInstant.isEnabled();
    }

    private boolean isCraftCPSRecalculateVisible() {
        return this.settingCraftInstant.isEnabled();
    }

    private boolean isSortMouseMoveRandomVisible() {
        return this.settingSortInstant.isEnabled();
    }

    private boolean isArmorCPSRecalculateVisible() {
        return this.settingArmorInstant.isEnabled();
    }

    private boolean isCleanFailRateVisible() {
        return this.settingCleanInstant.isEnabled();
    }

    private boolean isArmorDoubleClickRateVisible() {
        return this.settingArmorInstant.isEnabled();
    }

    private boolean isCleanCPSRecalculateVisible() {
        return this.settingCleanInstant.isEnabled();
    }

    private boolean isCraftMouseMoveRandomVisible() {
        return this.settingCraftInstant.isEnabled();
    }

    private boolean isCraftCPSVisible() {
        return this.settingCraftInstant.isEnabled();
    }

    private boolean checkCondition15() {
        if (!(minecraft.currentScreen instanceof GuiInventory)
        && !this.settingMode.getSelected().equals("Basic")
        && !this.settingMode.getSelected().equals("SpoofInv")
        && !this.settingMode.getSelected().equals("SpoofInvOther")) {
            this.updateState();
            this.tickDelay1 = -1;
            this.timer2.reset();
            this.expanded2 = false;
            return false;
        } else {
            if (!this.expanded2) {
                this.settingStartDelay.recalculate();
            }

            this.expanded2 = true;
            TimeHelper timer22 = this.timer2;
            long time = (long)this.settingStartDelay.getIndexOffset2();
            if (timer22.hasReached(time) && (minecraft.currentScreen == null || !minecraft.currentScreen.isState())) {
                if (minecraft.thePlayer.inventoryPlayer.getItemStack() != null && !this.activeState8 && !this.enabled2) {
                    return false;
                } else if (this.isStartDelayVisible()) {
                    if (minecraft.currentScreen != null) {
                        this.visible2 = false;
                        return false;
                    } else if (minecraft.thePlayer.getGuiScreen() == null) {
                        return !this.checkCondition(true, true)
                        || !this.checkCondition13(true)
                        || !this.checkCondition(true, false)
                        || !this.checkCondition14(true)
                        || !this.checkCondition(true);
                    } else if (minecraft.thePlayer.getGuiScreen() == null) {
                        this.visible2 = false;
                        return true;
                    } else {
                        if (this.checkCondition(false, true)
                        && this.checkCondition13(false)
                        && this.checkCondition(false, false)
                        && this.checkCondition14(false)
                        && this.checkCondition(false)) {
                            if (this.settingMode.getSelected().equals("SpoofInvOther") && this.visible2) {
                                Container container = minecraft.thePlayer.container;
                                this.applyContainer10(container);
                            }

                            this.visible2 = true;
                        } else {
                            this.visible2 = false;
                        }

                        if (this.settingMode.getSelected().equals("SpoofInv")) {
                            Container container2 = minecraft.thePlayer.container;
                            this.applyContainer10(container2);
                            this.updateState();
                        }

                        this.tickDelay1 = -1;
                        return true;
                    }
                } else if (this.checkCondition(false, true)
                && this.checkCondition13(false)
                && this.checkCondition(false, false)
                && this.checkCondition14(false)
                && this.checkCondition(false)) {
                    this.tickDelay1 = -1;
                    return false;
                } else {
                    this.processAction();
                    return true;
                }
            } else {
                this.updateState();
                return true;
            }
        }
    }
}
