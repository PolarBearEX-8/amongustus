package net.augustus.modules.player;

import net.minecraft.util.MovingObjectPosition;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ClickListener;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.utils.ActionRateAccumulator;
import net.augustus.events.EventInventoryClose;
import net.augustus.events.listeners.SprintListener;
import net.augustus.events.EventKey;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.EventClick;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.events.listeners.KeyListener;
import net.augustus.utils.TuplePair;
import net.augustus.utils.world.BlockUtil;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.EventMoveInput;
import net.augustus.settings.options.InvManagerActionOption;
import net.augustus.utils.TickCounter;
import net.augustus.Augustus;
import net.augustus.settings.ItemFilterSetting;
import net.augustus.utils.player.InventoryUtil;
import net.augustus.utils.UnaryFunction;
import net.augustus.utils.player.ContainerSlotQuery;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.events.listeners.InventoryCloseListener;
import net.augustus.events.EventSprint;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C16PacketClientStatus;
import org.lwjgl.opengl.Display;

public class InvManager extends Module implements InventoryCloseListener, MoveInputListener, SilentSlotListener, ClickListener, SprintListener, KeyListener {
   private final String[] slotCategories;
   private final MultiOptionSetting actions;
   private final StringValue mode;
   private final RandomizedRangeSettings startDelay;
   private final BooleanValue blockInputWhileManaging;
   public final BooleanValue visualize;
   private final BooleanValue notWhileScaffold;
   private final BooleanValue notWhileKillAuraHasTarget;
   private final BooleanValue notWhileEating;
   private final BooleanValue stopMovement;
   private final BooleanValue stopSprinting;
   private final BooleanValue stopMouseMovement;
   private final BooleanValue stopAllOtherKeyAndMouseInputs;
   private final BooleanValue autoCloseInv;
   private final RandomizedRangeSettings autoCloseInvDelay;
   private final SettingGroup sort;
   private final BooleanValue sortInstant;
   private final DoubleValue sortMaxMouseMoveSpeed;
   private final RandomizedRangeSettings sortMouseMoveRandom;
   private final RandomizedRangeSettings sortCPS;
   private final RandomizedRangeSettings sortCPSRecalculate;
   private final RandomizedRangeSettings sortFailRate;
   private final StringValue sortSequence;
   private final SettingGroup slots;
   private final StringValue slot1;
   private final StringValue slot2;
   private final StringValue slot3;
   private final StringValue slot4;
   private final StringValue slot5;
   private final StringValue slot6;
   private final StringValue slot7;
   private final StringValue slot8;
   private final StringValue slot9;
   private final SettingGroup merge;
   private final BooleanValue mergeNoOverflow;
   private final BooleanValue mergeInstant;
   private final DoubleValue mergeMaxMouseMoveSpeed;
   private final RandomizedRangeSettings mergeMouseMoveRandom;
   private final RandomizedRangeSettings mergeCPS;
   private final RandomizedRangeSettings mergeCPSRecalculate;
   private final RandomizedRangeSettings mergeDoubleClickRate;
   private final RandomizedRangeSettings mergeFailRate;
   private final RandomizedRangeSettings mergeMissClickRate;
   private final StringValue mergeSequence;
   private final SettingGroup armor;
   private final BooleanValue noArmorDrop;
   private final BooleanValue onlyInHotbar;
   public final BooleanValue ignoreDurability;
   private final BooleanValue armorInstant;
   private final DoubleValue armorMaxMouseMoveSpeed;
   private final RandomizedRangeSettings armorMouseMoveRandom;
   private final RandomizedRangeSettings armorCPS;
   private final RandomizedRangeSettings armorCPSRecalculate;
   private final RandomizedRangeSettings armorDoubleClickRate;
   private final RandomizedRangeSettings armorFailRate;
   private final RandomizedRangeSettings armorMissClickRate;
   private final StringValue armorSequence;
   private final SettingGroup clean;
   private final BooleanValue keepAllArmor;
   private final BooleanValue cleanInstant;
   private final DoubleValue cleanMaxMouseMoveSpeed;
   private final RandomizedRangeSettings cleanMouseMoveRandom;
   private final RandomizedRangeSettings cleanCPS;
   private final RandomizedRangeSettings cleanCPSRecalculate;
   private final RandomizedRangeSettings cleanFailRate;
   private final RandomizedRangeSettings cleanMissClickRate;
   private final StringValue cleanerSequence;
   private final SettingGroup itemsToDrop;
   private final SettingGroup itemStackLimits;
   public final DoubleValue blockStackLimit;
   public final DoubleValue foodStackLimit;
   public final DoubleValue waterBucketLimit;
   public final DoubleValue lavaBucketLimit;
   public final DoubleValue throwablesLimit;
   private final SettingGroup craft;
   private final DoubleValue reCraftIfSoupsLowerThan;
   private final BooleanValue craftInstant;
   private final DoubleValue craftMaxMouseMoveSpeed;
   private final RandomizedRangeSettings craftMouseMoveRandom;
   private final RandomizedRangeSettings craftCPS;
   private final RandomizedRangeSettings craftCPSRecalculate;
   private final SettingGroup armorHotbar;
   private final BooleanValue qsg;
   private final RandomizedRangeSettings tickDelayBeforeSwitch;
   private final RandomizedRangeSettings tickDelayBeforeClick;
   private final RandomizedRangeSettings tickDelayAfter;
   private final BooleanValue silentSlotSwitching;
   private final StringValue clickMode;
   private final TimeHelper lastActionTimer;
   private final TimeHelper startDelayTimer;
   private final ActionRateAccumulator armorCpsAccumulator;
   private final TickCounter armorCpsRecalcCounter;
   private final ActionRateAccumulator sortCpsAccumulator;
   private final TickCounter sortCpsRecalcCounter;
   private final ActionRateAccumulator cleanCpsAccumulator;
   private final TickCounter cleanCpsRecalcCounter;
   private final ActionRateAccumulator mergeCpsAccumulator;
   private final TickCounter mergeCpsRecalcCounter;
   private final ActionRateAccumulator doubleClickAccumulator;
   private final ActionRateAccumulator failRateAccumulator;
   private final ActionRateAccumulator missClickAccumulator;
   private final ActionRateAccumulator craftCpsAccumulator;
   private final TickCounter craftCpsRecalcCounter;
   private int armorTicksSinceClick;
   private int mergeTicksSinceClick;
   private int craftTicksSinceClick;
   private int sortTicksSinceClick;
   private int cleanTicksSinceClick;
   private int nextTargetSlot;
   private boolean craftingActive;
   private boolean holdingSwappedArmor;
   private final ArrayList detectedHotbarArmorSlots;
   private final ArrayList pendingHotbarArmorSlots;
   private int activeHotbarArmorSlot;
   private int previousHotbarArmorSlot;
   private int hotbarArmorDetectTick;
   private int hotbarArmorTicks;
   private boolean hotbarArmorClicked;
   private boolean spoofInvIdle;
   private boolean inventorySessionActive;
   private boolean managedActionsPerformed;
   private final ItemFilterSetting items;

   public InvManager() {
       super("InvManager", "InventarManager", new Color(32, 94, 103), Categorys.PLAYER);
      this.slotCategories = new String[]{
         "None",
         "Sword",
         "Bow",
         "Rod",
         "Pickaxe",
         "Axe",
         "Shovel",
         "Shear",
         "Throwables",
         "Blocks",
         "EnderPearls",
         "Food",
         "Gapple",
         "Soups",
         "Helmet",
         "Chestplate",
         "Trousers",
         "Boots"
      };
      this.actions = new MultiOptionSetting(this, "Actions", InvManagerActionOption.values());
      this.mode = new StringValue(this, "Mode", "OpenInv", new String[]{"OpenInv", "SpoofInvOther", "SpoofInv", "Basic"});
      this.startDelay = (new RandomizedRangeSettings(this, "StartDelay", 150.0, 200.0, 0.0, 1000.0, 0)).withoutNotInARow();
      this.blockInputWhileManaging = new BooleanValue(this, "BlockInputWhileManaging", true);
      this.visualize = new BooleanValue(this, "Visualize", true);
      this.notWhileScaffold = new BooleanValue(this, "NotWhileScaffold", true);
      this.notWhileKillAuraHasTarget = new BooleanValue(this, "NotWhileKillAuraHasTarget", true);
      this.notWhileEating = (BooleanValue)new BooleanValue(this, "NotWhileEating", true).withVisibility(() -> this.isSpoofInvMode());
      this.stopMovement = (BooleanValue)new BooleanValue(this, "StopMovement", false).withVisibility(() -> this.isSpoofInvMode());
      this.stopSprinting = (BooleanValue)new BooleanValue(this, "StopSprinting", false).withVisibility(() -> this.isSpoofInvMode());
      this.stopMouseMovement = (BooleanValue)new BooleanValue(this, "StopMouseMovement", false).withVisibility(() -> this.isSpoofInvMode());
      this.stopAllOtherKeyAndMouseInputs = (BooleanValue)new BooleanValue(this, "StopAllOtherKeyAndMouseInputs", false).withVisibility(() -> this.isSpoofInvMode());
      this.autoCloseInv = (BooleanValue)new BooleanValue(this, "AutoCloseInv", false).withVisibility(() -> this.isAutoCloseInvVisible());
      RandomizedRangeSettings randomizedRangeSettings = new RandomizedRangeSettings(this, "AutoCloseInvDelay", 100.0, 200.0, 0.0, 1000.0, 0);
      SettingVisibilityCondition condition7 = () -> {
         return !this.isSpoofInvMode() && this.autoCloseInv.getValue();
      };
      this.autoCloseInvDelay = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(condition7);
      this.sort = (SettingGroup)new SettingGroup(this, "Sort").visibleWhen(InvManagerActionOption.SORT::isSelected).markVisualOnly();
      this.sortInstant = (BooleanValue)new BooleanValue(this, "SortInstant", false, this.sort).withDisplayName("Instant");
      this.sortMaxMouseMoveSpeed = new DoubleValue(this, "SortMaxMouseMoveSpeed", 19.0, 0.0, 200.0, 0, this.sort).named("MaxMouseMoveSpeed").visibleWhen(() -> this.isSortMaxMouseMoveSpeedVisible());
      this.sortMouseMoveRandom = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "SortMouseMoveRandom", 0.0, 0.0, -1.0, 2.0, 0, this.sort)).withoutNotInARow().withDisplayNamePrefix("MouseMoveRandom")).withVisibility(() -> this.isSortMouseMoveRandomVisible());
      this.sortCPS = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(this, "SortCPS", 17.0, 20.0, 0.0, 40.0, 0, this.sort).withDisplayNamePrefix("CPS")).withVisibility(() -> this.isSortCpsVisible());
      this.sortCPSRecalculate = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "SortCPSRecalculate", 6.0, 8.0, 0.0, 40.0, 0, this.sort)).withoutNotInARow().withDisplayNamePrefix("CPSRecalculate")).withVisibility(() -> this.isSortCpsRecalculateVisible());
      this.sortFailRate = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(this, "SortFailRate", 2.0, 5.0, 0.0, 100.0, 0, this.sort).withDisplayNamePrefix("FailRate")).withoutNotInARow().withVisibility(() -> this.isSortFailRateVisible());
      this.sortSequence = new StringValue(this, "SortSequence", "Nearest", new String[]{"Basic", "Nearest", "Random"}, this.sort);
      this.slots = (SettingGroup)(new SettingGroup(this, "Slots", this.sort)).markVisualOnly();
      this.slot1 = (StringValue)(new StringValue(this, "Slot1", "Sword", this.slotCategories, this.slots)).markVisualOnly();
      this.slot2 = (StringValue)(new StringValue(this, "Slot2", "Bow", this.slotCategories, this.slots)).markVisualOnly();
      this.slot3 = (StringValue)(new StringValue(this, "Slot3", "Throwables", this.slotCategories, this.slots)).markVisualOnly();
      this.slot4 = (StringValue)(new StringValue(this, "Slot4", "Blocks", this.slotCategories, this.slots)).markVisualOnly();
      this.slot5 = (StringValue)(new StringValue(this, "Slot5", "Blocks", this.slotCategories, this.slots)).markVisualOnly();
      this.slot6 = (StringValue)(new StringValue(this, "Slot6", "Pickaxe", this.slotCategories, this.slots)).markVisualOnly();
      this.slot7 = (StringValue)(new StringValue(this, "Slot7", "Axe", this.slotCategories, this.slots)).markVisualOnly();
      this.slot8 = (StringValue)(new StringValue(this, "Slot8", "Food", this.slotCategories, this.slots)).markVisualOnly();
      this.slot9 = (StringValue)(new StringValue(this, "Slot9", "Gapple", this.slotCategories, this.slots)).markVisualOnly();
      this.merge = new SettingGroup(this, "Merge").visibleWhen(InvManagerActionOption.MERGE::isSelected);
      this.mergeNoOverflow = (BooleanValue)new BooleanValue(this, "MergeNoOverflow", true, this.merge).withDisplayName("NoOverflow");
      this.mergeInstant = (BooleanValue)new BooleanValue(this, "MergeInstant", false, this.merge).withDisplayName("Instant");
      DoubleValue mergeMaxMouseMoveSpeed2 = new DoubleValue(this, "MergeMaxMouseMoveSpeed", 19.0, 0.0, 200.0, 0, this.merge).named("MaxMouseMoveSpeed");
      SettingVisibilityCondition condition15 = () -> {
         return !this.mergeInstant.getValue();
      };
      this.mergeMaxMouseMoveSpeed = mergeMaxMouseMoveSpeed2.visibleWhen(condition15);
      this.mergeMouseMoveRandom = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "MergeMouseMoveRandom", 0.0, 0.0, -1.0, 2.0, 0, this.merge)).withoutNotInARow().withDisplayNamePrefix("MouseMoveRandom")).withVisibility(() -> this.isMergeMouseMoveRandomVisible());
      this.mergeCPS = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(this, "MergeCPS", 17.0, 20.0, 0.0, 40.0, 0, this.merge).withDisplayNamePrefix("CPS")).withVisibility(() -> this.isMergeCpsVisible());
      this.mergeCPSRecalculate = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "MergeCPSRecalculate", 6.0, 8.0, 0.0, 40.0, 0, this.merge)).withoutNotInARow().withDisplayNamePrefix("CPSRecalculate")).withVisibility(() -> this.isMergeCpsRecalculateVisible());
      this.mergeDoubleClickRate = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(this, "MergeDoubleClickRate", 0.0, 0.0, 0.0, 100.0, 0, this.merge).withDisplayNamePrefix("DoubleClickRate")).withoutNotInARow().withVisibility(() -> this.isMergeDoubleClickRateVisible());
      RandomizedRangeSettings randomizedRangeSettings19 = ((RandomizedRangeSettings)new RandomizedRangeSettings(this, "MergeFailRate", 10.0, 15.0, 0.0, 100.0, 0, this.merge).withDisplayNamePrefix("FailRate")).withoutNotInARow();
      SettingVisibilityCondition condition20 = () -> {
         return !this.mergeInstant.getValue();
      };
      this.mergeFailRate = (RandomizedRangeSettings)randomizedRangeSettings19.withVisibility(condition20);
      this.mergeMissClickRate = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "MergeMissClickRate", 0.0, 0.0, 0.0, 100.0, 0, this.merge)).withoutNotInARow().withDisplayNamePrefix("MissClickRate")).withVisibility(() -> this.isMergeMissClickRateVisible());
      this.mergeSequence = new StringValue(this, "MergeSequence", "Nearest", new String[]{"Basic", "Nearest", "Random"}, this.merge);
      this.armor = new SettingGroup(this, "Armor").visibleWhen(InvManagerActionOption.PUT_ON_ARMOR::isSelected);
      this.noArmorDrop = new BooleanValue(this, "NoArmorDrop", false, this.armor);
      this.onlyInHotbar = new BooleanValue(this, "OnlyInHotbar", false, this.armor);
      this.ignoreDurability = new BooleanValue(this, "IgnoreDurability", false, this.armor);
      this.armorInstant = (BooleanValue)new BooleanValue(this, "ArmorInstant", false, this.armor).withDisplayName("Instant");
      this.armorMaxMouseMoveSpeed = new DoubleValue(this, "ArmorMaxMouseMoveSpeed", 19.0, 0.0, 200.0, 0, this.armor).named("MaxMouseMoveSpeed").visibleWhen(() -> this.isArmorMaxMouseMoveSpeedVisible());
      this.armorMouseMoveRandom = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "ArmorMouseMoveRandom", 0.0, 0.0, -1.0, 2.0, 0, this.armor)).withoutNotInARow().withDisplayNamePrefix("MouseMoveRandom")).withVisibility(() -> this.isArmorMouseMoveRandomVisible());
      RandomizedRangeSettings randomizedRangeSettings25 = (RandomizedRangeSettings)new RandomizedRangeSettings(this, "ArmorCPS", 17.0, 20.0, 0.0, 40.0, 0, this.armor).withDisplayNamePrefix("CPS");
      SettingVisibilityCondition condition25 = () -> {
         return !this.armorInstant.getValue();
      };
      this.armorCPS = (RandomizedRangeSettings)randomizedRangeSettings25.withVisibility(condition25);
      this.armorCPSRecalculate = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "ArmorCPSRecalculate", 6.0, 8.0, 0.0, 40.0, 0, this.armor)).withoutNotInARow().withDisplayNamePrefix("CPSRecalculate")).withVisibility(() -> this.isArmorCpsRecalculateVisible());
      this.armorDoubleClickRate = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(this, "ArmorDoubleClickRate", 0.0, 0.0, 0.0, 100.0, 0, this.armor).withDisplayNamePrefix("DoubleClickRate")).withoutNotInARow().withVisibility(() -> this.isArmorDoubleClickRateVisible());
      RandomizedRangeSettings randomizedRangeSettings31 = ((RandomizedRangeSettings)new RandomizedRangeSettings(this, "ArmorFailRate", 2.0, 5.0, 0.0, 100.0, 0, this.armor).withDisplayNamePrefix("FailRate")).withoutNotInARow();
      SettingVisibilityCondition condition28 = () -> {
         return !this.armorInstant.getValue();
      };
      this.armorFailRate = (RandomizedRangeSettings)randomizedRangeSettings31.withVisibility(condition28);
      this.armorMissClickRate = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "ArmorMissClickRate", 0.0, 0.0, 0.0, 100.0, 0, this.armor)).withoutNotInARow().withDisplayNamePrefix("MissClickRate")).withVisibility(() -> this.isArmorMissClickRateVisible());
      this.armorSequence = new StringValue(this, "ArmorSequence", "Nearest", new String[]{"Basic", "Nearest", "Random"}, this.armor);
      this.clean = new SettingGroup(this, "Clean").visibleWhen(InvManagerActionOption.CLEAN::isSelected);
      this.keepAllArmor = new BooleanValue(this, "KeepAllArmor", false, this.clean);
      this.cleanInstant = (BooleanValue)new BooleanValue(this, "CleanInstant", false, this.clean).withDisplayName("Instant");
      DoubleValue cleanMaxMouseMoveSpeed2 = new DoubleValue(this, "CleanMaxMouseMoveSpeed", 19.0, 0.0, 200.0, 0, this.clean).named("MaxMouseMoveSpeed");
      SettingVisibilityCondition condition31 = () -> {
         return !this.cleanInstant.getValue();
      };
      this.cleanMaxMouseMoveSpeed = cleanMaxMouseMoveSpeed2.visibleWhen(condition31);
      this.cleanMouseMoveRandom = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "CleanMouseMoveRandom", 0.0, 0.0, -1.0, 2.0, 0, this.clean)).withoutNotInARow().withDisplayNamePrefix("MouseMoveRandom")).withVisibility(() -> this.isCleanMouseMoveRandomVisible());
      this.cleanCPS = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(this, "CleanCPS", 17.0, 20.0, 0.0, 40.0, 0, this.clean).withDisplayNamePrefix("CPS")).withVisibility(() -> this.isCleanCpsVisible());
      this.cleanCPSRecalculate = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "CleanCPSRecalculate", 6.0, 8.0, 0.0, 40.0, 0, this.clean)).withoutNotInARow().withDisplayNamePrefix("CPSRecalculate")).withVisibility(() -> this.isCleanCpsRecalculateVisible());
      this.cleanFailRate = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(this, "CleanFailRate", 10.0, 15.0, 0.0, 100.0, 0, this.clean).withDisplayNamePrefix("FailRate")).withoutNotInARow().withVisibility(() -> this.isCleanFailRateVisible());
      this.cleanMissClickRate = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "CleanMissClickRate", 0.0, 0.0, 0.0, 100.0, 0, this.clean)).withoutNotInARow().withDisplayNamePrefix("MissClickRate")).withVisibility(() -> this.isCleanMissClickRateVisible());
      this.cleanerSequence = new StringValue(this, "CleanerSequence", "Nearest", new String[]{"Basic", "Nearest", "Random"}, this.clean);
      this.itemsToDrop = new SettingGroup(this, "ItemsToDrop", this.clean).visibleWhen(InvManagerActionOption.CLEAN::isSelected);
      this.itemStackLimits = new SettingGroup(this, "ItemStackLimits", this.clean).visibleWhen(InvManagerActionOption.CLEAN::isSelected);
      this.blockStackLimit = new DoubleValue(this, "BlockStackLimit", 10.0, 0.0, 20.0, 0, this.itemStackLimits);
      this.foodStackLimit = new DoubleValue(this, "FoodStackLimit", 4.0, 0.0, 20.0, 0, this.itemStackLimits);
      this.waterBucketLimit = new DoubleValue(this, "WaterBucketLimit", 4.0, 0.0, 20.0, 0, this.itemStackLimits);
      this.lavaBucketLimit = new DoubleValue(this, "LavaBucketLimit", 4.0, 0.0, 20.0, 0, this.itemStackLimits);
      this.throwablesLimit = new DoubleValue(this, "ThrowablesLimit", 5.0, 0.0, 20.0, 0, this.itemStackLimits);
      this.craft = new SettingGroup(this, "Craft").visibleWhen(() -> this.isCraftVisible());
      this.reCraftIfSoupsLowerThan = new DoubleValue(this, "ReCraftIfSoupsLowerThan", 4.0, 0.0, 20.0, 0, this.craft);
      this.craftInstant = (BooleanValue)new BooleanValue(this, "CraftInstant", false, this.craft).withDisplayName("Instant");
      this.craftMaxMouseMoveSpeed = new DoubleValue(this, "CraftMaxMouseMoveSpeed", 19.0, 0.0, 200.0, 0, this.craft).named("MaxMouseMoveSpeed").visibleWhen(() -> this.isCraftMaxMouseMoveSpeedVisible());
      this.craftMouseMoveRandom = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "CraftMouseMoveRandom", 0.0, 0.0, -1.0, 2.0, 0, this.craft)).withoutNotInARow().withDisplayNamePrefix("MouseMoveRandom")).withVisibility(() -> this.isCraftMouseMoveRandomVisible());
      this.craftCPS = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(this, "CraftCPS", 17.0, 20.0, 0.0, 40.0, 0, this.craft).withDisplayNamePrefix("CPS")).withVisibility(() -> this.isCraftCpsVisible());
      RandomizedRangeSettings randomizedRangeSettings49 = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "CraftCPSRecalculate", 6.0, 8.0, 0.0, 40.0, 0, this.craft)).withoutNotInARow().withDisplayNamePrefix("CPSRecalculate");
      SettingVisibilityCondition condition43 = () -> {
         return !this.craftInstant.getValue();
      };
      this.craftCPSRecalculate = (RandomizedRangeSettings)randomizedRangeSettings49.withVisibility(condition43);
      this.armorHotbar = new SettingGroup(this, "ArmorHotbar").visibleWhen(() -> this.isArmorHotbarVisible());
      this.qsg = new BooleanValue(this, "QSG", false, this.armorHotbar);
      this.tickDelayBeforeSwitch = (new RandomizedRangeSettings(this, "TickDelayBeforeSwitch", 1.0, 2.0, 0.0, 7.0, 0, this.armorHotbar)).withoutNotInARow();
      this.tickDelayBeforeClick = (new RandomizedRangeSettings(this, "TickDelayBeforeClick", 1.0, 2.0, 0.0, 7.0, 0, this.armorHotbar)).withoutNotInARow();
      this.tickDelayAfter = (new RandomizedRangeSettings(this, "TickDelayAfter", 1.0, 2.0, 0.0, 7.0, 0, this.armorHotbar)).withoutNotInARow();
      this.silentSlotSwitching = new BooleanValue(this, "SilentSlotSwitching", true, this.armorHotbar);
      this.clickMode = new StringValue(this, "ClickMode", "Basic", new String[]{"Basic"});
      this.lastActionTimer = new TimeHelper();
      this.startDelayTimer = new TimeHelper();
      this.armorCpsAccumulator = new ActionRateAccumulator(20, 0.0);
      this.armorCpsRecalcCounter = new TickCounter();
      this.sortCpsAccumulator = new ActionRateAccumulator(20, 0.0);
      this.sortCpsRecalcCounter = new TickCounter();
      this.cleanCpsAccumulator = new ActionRateAccumulator(20, 0.0);
      this.cleanCpsRecalcCounter = new TickCounter();
      this.mergeCpsAccumulator = new ActionRateAccumulator(20, 0.0);
      this.mergeCpsRecalcCounter = new TickCounter();
      this.doubleClickAccumulator = new ActionRateAccumulator(100, 0.0);
      this.failRateAccumulator = new ActionRateAccumulator(100, 0.0);
      this.missClickAccumulator = new ActionRateAccumulator(100, 0.0);
      this.craftCpsAccumulator = new ActionRateAccumulator(20, 0.0);
      this.craftCpsRecalcCounter = new TickCounter();
      this.armorTicksSinceClick = 0;
      this.mergeTicksSinceClick = 0;
      this.craftTicksSinceClick = 0;
      this.sortTicksSinceClick = 0;
      this.cleanTicksSinceClick = 0;
      this.nextTargetSlot = -1;
      this.craftingActive = false;
      this.holdingSwappedArmor = false;
      this.detectedHotbarArmorSlots = new ArrayList();
      this.pendingHotbarArmorSlots = new ArrayList();
      this.activeHotbarArmorSlot = -1;
      this.previousHotbarArmorSlot = -1;
      this.hotbarArmorDetectTick = 0;
      this.hotbarArmorTicks = 0;
      this.hotbarArmorClicked = false;
      this.spoofInvIdle = true;
      this.inventorySessionActive = false;
      this.managedActionsPerformed = false;
      this.items = new ItemFilterSetting(this, "Items", this.itemsToDrop, () -> InvManagerActionOption.isOptionSelected(InvManagerActionOption.CLEAN), obj -> InventoryUtil.isCleanableItem((Item)obj));
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(52, 1);
      this.toggleEventSubscription(21, 100000);
      this.toggleEventSubscription(34);
      this.toggleEventSubscription(7, 100);
      this.toggleEventSubscription(36, 100);
      this.toggleEventSubscription(14);
   }

   @Override
   public void onKeyboardKey(EventKey event) {
      if (this.isKeyBlockedWhileSpoofing(event.getKeyCode())) {
         event.cancel();
      }
   }

   @Override
   public void onMouseKey(EventKey event) {
      if (this.shouldBlockNonMovementInputs()) {
         event.cancel();
      }
   }

   @Override
   public boolean onPreInventoryClose() {
      this.setSuffix(this.mode.getSelectedValue());
      boolean flag = this.processInventoryManagement();
      this.armorCpsRecalcCounter.increment();
      this.sortCpsRecalcCounter.increment();
      this.cleanCpsRecalcCounter.increment();
      this.craftCpsRecalcCounter.increment();
      return flag;
   }

   @Override
   public void onPostInventoryClose(EventInventoryClose event) {
      if (!mc.isNotInGui()) {
         if (this.autoCloseInv.getValue() && !this.isSpoofInvMode() && this.managedActionsPerformed) {
            if (event.canCloseAfterDelay((double)this.autoCloseInvDelay.getLastIntValue())) {
               mc.thePlayer.closeScreen();
            }
         }
      }
   }

   @Override
   public void onSprint(EventSprint event) {
      if (mc.currentScreen == null && mc.previousScreen == null && this.isSpoofInvMode() && mc.thePlayer.getSpoofedScreen() != null && this.stopSprinting.getValue()) {
         event.setSprintKeyDown(false);
         event.setForceSprint(false);
      }
   }

   @Override
   public void onPostSilentSlot() {
      if (InvManagerActionOption.PUT_ON_ARMOR_HOT_BAR.isSelected() && (this.isSpoofInvMode() || this.mode.getSelectedValue().equals("OpenInv")) && mc.isNotInGui()) {
         if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            Block block = mc.theWorld.getBlockState(mc.objectMouseOver.getBlockPos()).getBlock();
            if (block.hasTileEntity() || BlockUtil.isInteractableBlock(block)) {
               return;
            }
         }

         int activeHotbarArmorSlot2 = this.activeHotbarArmorSlot;
         this.activeHotbarArmorSlot = this.findHotbarArmorSlot();
         this.previousHotbarArmorSlot = activeHotbarArmorSlot2;
         if (this.activeHotbarArmorSlot > -1) {
            SilentSlotManager.requestSlot(this, (byte)5, this.activeHotbarArmorSlot, this.silentSlotSwitching.getValue());
         }
      } else {
         this.detectedHotbarArmorSlots.clear();
         this.resetHotbarArmorState();
      }
   }

   private int findHotbarArmorSlot() {
      Container inventoryContainer = mc.thePlayer.inventoryContainer;
      ArrayList list = InventoryUtil.findBestArmorSetExcludingEquipped(inventoryContainer, (byte)36, this.ignoreDurability.getValue());
      ArrayList list2 = new ArrayList();

      for (int n : (Iterable<Integer>)(list)) {
         if (n >= 36) {
            if (inventoryContainer.getSlot(((ItemArmor)inventoryContainer.getSlot(n).getStack().getItem()).armorType + 5).getStack() == null || this.qsg.getValue()) {
               list2.add(n - 36);
            }
         }
      }

      if (!list2.equals(this.detectedHotbarArmorSlots)) {
         this.hotbarArmorDetectTick = mc.thePlayer.ticksExisted;
         this.detectedHotbarArmorSlots.clear();
         this.detectedHotbarArmorSlots.addAll(list2);
      }

      if (this.isHotbarSwitchDelayElapsed() && !this.hotbarArmorClicked && !this.pendingHotbarArmorSlots.equals(list2)) {
         this.pendingHotbarArmorSlots.clear();
         this.pendingHotbarArmorSlots.addAll(list2);
         this.hotbarArmorTicks = 0;
      }

      return this.pendingHotbarArmorSlots.isEmpty() ? -1 : (Integer)this.pendingHotbarArmorSlots.get(0);
   }

   private boolean isHotbarSwitchDelayElapsed() {
      return (mc.thePlayer.ticksExisted - this.hotbarArmorDetectTick) >= this.tickDelayBeforeSwitch.getLastIntValue() || this.previousHotbarArmorSlot > -1 || this.activeHotbarArmorSlot > -1;
   }

   private void resetHotbarArmorState() {
      this.hotbarArmorClicked = false;
      this.activeHotbarArmorSlot = -1;
      this.tickDelayBeforeSwitch.nextValue();
      this.tickDelayBeforeClick.nextValue();
      this.tickDelayAfter.nextValue();
   }

   @Override
   public void onClick(EventClick event) {
      if (mc.isNotInGui()
         && (
            mc.objectMouseOver == null
               || mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK
               || !mc.theWorld.getBlockState(mc.objectMouseOver.getBlockPos()).getBlock().hasTileEntity()
         )) {
         if (this.activeHotbarArmorSlot > -1) {
            if (this.hotbarArmorTicks > this.tickDelayBeforeClick.getLastIntValue() && !mc.thePlayer.isUsingItem() && !this.hotbarArmorClicked) {
               event.addRightClicks((byte)1);
               this.hotbarArmorClicked = true;
            }

            if ((double)this.hotbarArmorTicks > (double)this.tickDelayBeforeClick.getLastIntValue() + this.tickDelayAfter.getLastValue() && this.hotbarArmorClicked) {
               this.resetHotbarArmorState();
            }
         }

         this.hotbarArmorTicks++;
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (mc.currentScreen == null && mc.previousScreen == null && this.isSpoofInvMode() && mc.thePlayer.getSpoofedScreen() != null && this.stopMovement.getValue()) {
         event.setJump(false);
         event.setSneak(false);
         event.setMoveForward(0.0F);
         event.setMoveStrafe(0.0F);
      }
   }

   private boolean processInventoryManagement() {
      if (!(mc.currentScreen instanceof GuiInventory)
         && !this.mode.getSelectedValue().equals("Basic")
         && !this.mode.getSelectedValue().equals("SpoofInv")
         && !this.mode.getSelectedValue().equals("SpoofInvOther")) {
         if (mc.previousScreen != null) {
            this.autoCloseInvDelay.nextValue();
         }

         this.resetActionTickCounters();
         this.nextTargetSlot = -1;
         this.startDelayTimer.reset();
         this.inventorySessionActive = false;
         this.managedActionsPerformed = false;
         return false;
      } else {
         if (!this.inventorySessionActive) {
            this.startDelay.nextValue();
         }

         this.inventorySessionActive = true;
         if (this.startDelayTimer.reached((long)this.startDelay.getLastIntValue()) && (mc.currentScreen == null || !mc.currentScreen.isAllowNativeMouseClick())) {
            if (mc.thePlayer.inventory.getItemStack() != null && !this.craftingActive && !this.holdingSwappedArmor) {
               return false;
            } else if (this.isSpoofInvMode()) {
               if (mc.currentScreen != null) {
                  this.spoofInvIdle = false;
                  return false;
               } else if (mc.thePlayer.getSpoofedScreen() == null) {
                  if (this.processSortAction(true, true)) {
                     if (this.processArmorAction(true)) {
                        if (this.processSortAction(true, false)) {
                           if (this.processMergeAction(true)) {
                              if (this.processCleanAction(true)) {
                                 if (this.processCraftAction(true)) {
                                    return false;
                                 }
                              }
                           }
                        }
                     }
                  }

                  return true;
               } else if (mc.thePlayer.getSpoofedScreen() == null) {
                  this.spoofInvIdle = false;
                  return true;
               } else {
                  if (this.processSortAction(false, true)) {
if (this.processArmorAction(false)) {
if (this.processSortAction(false, false)) {
if (this.processMergeAction(false)) {
if (this.processCleanAction(false)) {
if (this.processCraftAction(false)) {
if (this.mode.getSelectedValue().equals("SpoofInvOther") && this.spoofInvIdle) {
                                          this.closeSpoofedInventory(mc.thePlayer.inventoryContainer);
                                       }

                                       this.spoofInvIdle = true;
} else {
this.spoofInvIdle = false;
}
} else {
this.spoofInvIdle = false;
}
} else {
this.spoofInvIdle = false;
}
} else {
this.spoofInvIdle = false;
}
} else {
this.spoofInvIdle = false;
}
} else {
this.spoofInvIdle = false;
}

                  if (this.mode.getSelectedValue().equals("SpoofInv")) {
                     this.closeSpoofedInventory(mc.thePlayer.inventoryContainer);
                     this.resetActionTickCounters();
                  }

                  this.nextTargetSlot = -1;
                  return true;
               }
            } else {
               if (true) {
                  if (this.processSortAction(false, true)) {
                     if (this.processArmorAction(false)) {
                        if (this.processSortAction(false, false)) {
                           if (this.processMergeAction(false)) {
                              if (this.processCleanAction(false)) {
                                 if (this.processCraftAction(false)) {
                                    this.nextTargetSlot = -1;
                                    return false;
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               this.managedActionsPerformed = true;
               this.updateCpsRecalculation();
               return true;
            }
         } else {
            this.resetActionTickCounters();
            return true;
         }
      }
   }

   private boolean processCleanAction(boolean flag) {
      if (InvManagerActionOption.CLEAN.isSelected() && !this.isActionPaused()) {
         this.cleanTicksSinceClick++;
         Container inventoryContainer = mc.thePlayer.inventoryContainer;
         ArrayList list = this.collectSlotsToDrop(inventoryContainer);
         if (list.isEmpty()) {
            return true;
         } else {
            ArrayList<Runnable> list2 = new ArrayList<>();
            ArrayList list3 = new ArrayList();

            for (int n : (Iterable<Integer>)(InventoryUtil.sortSlotsByMode(inventoryContainer, list, this.cleanerSequence.getSelectedValue()))) {
               ItemStack stack = inventoryContainer.getSlot(n).getStack();
               if (stack != null) {
                  if (this.keepAllArmor.getValue()) {
                     if (stack.getItem() instanceof ItemArmor) {
                        continue;
                     }
                  }

                  stack.setSlotIndex(n);
                  list3.add(stack);
                  list2.add(() -> {
                     this.dropItemStack(stack, inventoryContainer);
                  });
               }
            }

            if (list2.isEmpty()) {
               return true;
            } else if (flag) {
               this.openSpoofedInventory();
               return this.cleanInstant.getValue();
            } else {
               ActionRateAccumulator cleanCpsAccumulator2 = this.cleanCpsAccumulator;
               RandomizedRangeSettings cleanFailRate2 = this.cleanFailRate;
               RandomizedRangeSettings cleanMissClickRate2 = this.cleanMissClickRate;
               RandomizedRangeSettings cleanMouseMoveRandom2 = this.cleanMouseMoveRandom;
               boolean value = this.cleanInstant.getValue();
               int lastIntValue = this.cleanCPS.getLastIntValue();
               int cleanTicksSinceClick2 = this.cleanTicksSinceClick;
               int value2 = (int)this.cleanMaxMouseMoveSpeed.getValue(true);
               return this.executeActionBatch(list2, list3, cleanCpsAccumulator2, (RandomizedRangeSettings)null, cleanFailRate2, cleanMissClickRate2, cleanMouseMoveRandom2, inventoryContainer, value, lastIntValue, cleanTicksSinceClick2, value2);
            }
         }
      } else {
         return true;
      }
   }

   private int[] countSoupCraftingItems(Container container) {
      int[] ints = new int[5];

      for (int i = -1; i < container.inventorySlots.size(); i++) {
         ItemStack stack = i == -1 ? mc.thePlayer.inventory.getItemStack() : container.getSlot(i).getStack();
         if (stack == null) {
            if (i > 8) {
               ints[0]++;
            }
         } else {
            Item item = stack.getItem();
            if (item.equals(Items.bowl)) {
               ints[1] += stack.stackSize;
            } else if (item.equals(Item.getItemById(40))) {
               ints[2] += stack.stackSize;
            } else if (item.equals(Item.getItemById(39))) {
               ints[3] += stack.stackSize;
            } else if (item.equals(Items.mushroom_stew) && i != 0) {
               ints[4]++;
            }
         }
      }

      return ints;
   }

   private boolean processCraftAction(boolean flag) {
      if (!InvManagerActionOption.CRAFT.isSelected() || this.isActionPaused()) {
         return true;
      } else if (this.mode.getSelectedValue().equals("OpenInv") && !flag) {
         this.craftTicksSinceClick++;
         int[] ints = this.countSoupCraftingItems(mc.thePlayer.inventoryContainer);
         if (ints[1] != 0 && ints[2] != 0 && ints[3] != 0) {
            int n = Math.min(ints[1], Math.min(ints[2], ints[3]));
            int n2 = ints[0];
            if (n <= 0) {
               this.craftingActive = false;
               return true;
            } else {
               int n3 = n > n2 ? 1 : 0;
               Item[] items2 = new Item[]{Items.bowl, Item.getItemById(40), Item.getItemById(39)};
               boolean[] flags = new boolean[]{false, false, false};

               for (int i = 1; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
                  if (i == 1) {
                     flags = new boolean[]{false, false, false};
                     n2 = this.countSoupCraftingItems(mc.thePlayer.inventoryContainer)[0];
                  }

                  ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                  if (stack != null) {
                     Item item = stack.getItem();
                     if (i < 5) {
                        for (int j = 0; j < items2.length; j++) {
                           if (items2[j].equals(item)) {
                              this.craftingActive = true;
                              flags[j] = true;
                           }
                        }
                     }

                     if (n2 > 0 && (double)ints[4] <= this.reCraftIfSoupsLowerThan.getValue()) {
                        if (mc.thePlayer.inventory.getItemStack() == null) {
                           if (i > 8 && (item.equals(items2[0]) && !flags[0] || item.equals(items2[1]) && !flags[1] || item.equals(items2[2]) && !flags[2])
                              )
                            {
                              if (this.isCraftClickBlocked(i)) {
                                 return false;
                              }

                              this.craftingActive = true;
                              this.clickSlot(i, n3, mc.thePlayer.inventoryContainer);
                              i = 0;
                           }

                           if (i > 4 && flags[0] && flags[1] && flags[2]) {
                              if (this.isCraftClickBlocked((byte)0)) {
                                 return false;
                              }

                              this.shiftClickSlot((byte)0, (byte)-1, mc.thePlayer.inventoryContainer);
                              i = 0;
                           }
                        } else {
                           Item item2 = mc.thePlayer.inventory.getItemStack().getItem();

                           for (int k = 0; k < 3; k++) {
                              if (item2.equals(items2[k]) && !flags[k]) {
                                 if (this.isCraftClickBlocked(k + 1)) {
                                    return false;
                                 }

                                 this.craftingActive = false;
                                 this.clickSlot(k + 1, (byte)0, mc.thePlayer.inventoryContainer);
                                 i = 0;
                              }
                           }
                        }
                     } else if (i > 5) {
                        for (int m = 0; m < items2.length; m++) {
                           if (flags[m]) {
                              ItemStack stack2 = null;
                              int n8 = -1;

                              for (int idx = 0; idx < 5; idx++) {
                                 ItemStack stack3 = mc.thePlayer.inventoryContainer.getSlot(idx).getStack();
                                 if (stack3 != null && stack3.getItem().equals(items2[m])) {
                                    stack2 = stack3;
                                    n8 = idx;
                                    break;
                                 }
                              }

                              if (this.isCraftClickBlocked(n8 != 0 ? n8 : i)) {
                                 return false;
                              }

                              for (int index = 9; index < mc.thePlayer.inventoryContainer.inventorySlots.size(); index++) {
                                 ItemStack stack4 = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
                                 if (stack2 != null && (stack4 == null || stack4.getItem().equals(stack2.getItem()) && stack4.stackSize < stack2.getMaxStackSize())) {
                                    this.shiftClickSlot(n8, (byte)-1, mc.thePlayer.inventoryContainer);
                                    this.craftingActive = false;
                                    i = 0;
                                    break;
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               return true;
            }
         } else {
            this.craftingActive = false;
            return true;
         }
      } else {
         this.craftingActive = false;
         return true;
      }
   }

   private boolean isCraftClickBlocked(int n) {
      if (this.craftInstant.getValue()) {
         return false;
      } else {
         int n2 = this.craftCpsAccumulator.accumulate(this.craftCPS.getLastIntValue());
         if (n2 <= 0) {
            return true;
         } else {
            int craftTicksSinceClick2 = this.craftTicksSinceClick;
            int value = (int)this.craftMaxMouseMoveSpeed.getValue(true);
            RandomizedRangeSettings craftMouseMoveRandom2 = this.craftMouseMoveRandom;
            n2 = this.filterClicksByMouseSpeed(craftTicksSinceClick2, value, mc.thePlayer.inventoryContainer, n, n2, craftMouseMoveRandom2);
            return n2 <= 0;
         }
      }
   }

   private boolean processSortAction(boolean flag, boolean flag2) {
      if (InvManagerActionOption.SORT.isSelected() && !this.isActionPaused()) {
         this.sortTicksSinceClick++;
         Container inventoryContainer = mc.thePlayer.inventoryContainer;
         HashMap map = new HashMap();
         ArrayList<Runnable> list = new ArrayList<>();
         ArrayList list2 = new ArrayList();
         ArrayList list3 = new ArrayList();
         HashMap map2 = new HashMap();

         for (String str : this.slotCategories) {
            if (flag2 ? str.equals("Sword") : !str.equals("None") && !str.equals("Sword")) {
               ArrayList hotbarSlotsForCategory = this.getHotbarSlotsForCategory(str);

               for (int n : (Iterable<Integer>)(hotbarSlotsForCategory)) {
                  if (n != -1) {
                     boolean flag3 = map.containsKey(str);
                     List list4 = flag3 ? (List)map.get(str) : this.getRankedSlotsForCategory(inventoryContainer, str);
                     if (!flag3) {
                        map.put(str, list4);
                     }

                     if (!list4.isEmpty()) {
                        TuplePair tuplePair = (TuplePair)list4.get(0);
                        ArrayList list5 = new ArrayList();

                        for (TuplePair tuplePair2 : (Iterable<TuplePair>)(list4)) {
                           if ((Double)tuplePair2.getFirst() >= (Double)tuplePair.getFirst()) {
                              list5.add(tuplePair2);
                           }
                        }

                        if (list5.size() > 1) {
                           for (int i = 0; i < list5.size(); i++) {
                              if (!hotbarSlotsForCategory.contains((Integer)((TuplePair)list5.get(i)).getSecond() - 36)) {
                                 tuplePair = (TuplePair)list5.get(i);
                                 break;
                              }
                           }
                        }

                        if (tuplePair != null) {
                           int n2 = 0;

                           for (int j = 0; j < list4.size(); j++) {
                              TuplePair tuplePair3 = (TuplePair)list4.get(j);
                              if (((Double)tuplePair3.getFirst()).equals(tuplePair.getFirst()) && ((Integer)tuplePair3.getSecond()).equals(tuplePair.getSecond())) {
                                 n2 = j;
                                 break;
                              }
                           }

                           list4.remove(n2);
                           int second = (Integer)tuplePair.getSecond();
                           double first = (Double)tuplePair.getFirst();
                           if (second != n + 36 && !hotbarSlotsForCategory.contains(second + 36)) {
                              ItemStack stack = inventoryContainer.getSlot(n + 36).getStack();
                              if (!(first <= InventoryUtil.getItemScore(stack, !flag2)) || InventoryUtil.getItemCategoryPriority(stack, flag2) != InventoryUtil.getItemCategoryPriority(inventoryContainer.getSlot(second).getStack(), flag2)
                                 )
                               {
                                 list3.add(second);
                                 map2.put(second, n);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (list3.isEmpty()) {
            return true;
         } else {

            for (int n3 : (Iterable<Integer>)(InventoryUtil.sortSlotsByMode(inventoryContainer, list3, this.sortSequence.getSelectedValue()))) {
               ItemStack stack2 = inventoryContainer.getSlot(n3).getStack();
               stack2.setSlotIndex(n3);
               int n4 = (Integer)map2.get(n3);
               list.add(() -> this.executeSortSwap(n4, stack2, inventoryContainer));
               list2.add(stack2);
            }

            if (list.isEmpty()) {
               return true;
            } else if (flag) {
               this.openSpoofedInventory();
               return this.sortInstant.getValue();
            } else {
               ActionRateAccumulator sortCpsAccumulator2 = this.sortCpsAccumulator;
               RandomizedRangeSettings sortFailRate2 = this.sortFailRate;
               RandomizedRangeSettings sortMouseMoveRandom2 = this.sortMouseMoveRandom;
               boolean value = this.sortInstant.getValue();
               int lastIntValue = this.sortCPS.getLastIntValue();
               int sortTicksSinceClick2 = this.sortTicksSinceClick;
               int value2 = (int)this.sortMaxMouseMoveSpeed.getValue(true);
               return this.executeActionBatch(list, list2, sortCpsAccumulator2, (RandomizedRangeSettings)null, sortFailRate2, (RandomizedRangeSettings)null, sortMouseMoveRandom2, inventoryContainer, value, lastIntValue, sortTicksSinceClick2, value2);
            }
         }
      } else {
         return true;
      }
   }

   private boolean processMergeAction(boolean flag) {
      if (InvManagerActionOption.MERGE.isSelected() && !this.isActionPaused()) {
         this.mergeTicksSinceClick++;
         Container inventoryContainer = mc.thePlayer.inventoryContainer;
         List list = this.findMergeableSlots(inventoryContainer, (byte)8);
         if (list.isEmpty()) {
            return true;
         } else {
            ArrayList<Runnable> list2 = new ArrayList<>();
            ArrayList list3 = new ArrayList();

            for (int n : (Iterable<Integer>)(InventoryUtil.sortSlotsByMode(inventoryContainer, list, this.mergeSequence.getSelectedValue()))) {
               ItemStack stack = inventoryContainer.getSlot(n).getStack();
               if (stack != null) {
                  stack.setSlotIndex(n);
                  list3.add(stack);
                  list2.add(() -> this.shiftClickMergeSlot(n, inventoryContainer));
               }
            }

            if (list2.isEmpty()) {
               return true;
            } else if (flag) {
               this.openSpoofedInventory();
               return this.cleanInstant.getValue();
            } else {
               ActionRateAccumulator mergeCpsAccumulator2 = this.mergeCpsAccumulator;
               RandomizedRangeSettings mergeDoubleClickRate2 = this.mergeDoubleClickRate;
               return this.executeActionBatch(list2, list3, mergeCpsAccumulator2, mergeDoubleClickRate2, this.mergeFailRate, this.mergeMissClickRate, this.mergeMouseMoveRandom, inventoryContainer, this.mergeInstant.getValue(), this.mergeCPS.getLastIntValue(), this.mergeTicksSinceClick, (int)this.mergeMaxMouseMoveSpeed.getValue(true));
            }
         }
      } else {
         return true;
      }
   }

   private List findMergeableSlots(Container container, int n) {
      HashMap map = new HashMap();
      ArrayList list = new ArrayList();

      for (int i = n; i < container.inventorySlots.size(); i++) {
         ItemStack stack = container.getSlot(i).getStack();
         if (stack != null) {
            map.put(i, stack.getMaxStackSize() - stack.stackSize);
         } else {
            map.put(i, 0);
         }
      }

      this.collectMergeCandidates(container, n, map, list, false);
      if (!this.mergeNoOverflow.getValue()) {
         this.collectMergeCandidates(container, n, map, list, true);
      }

      return list;
   }

   private void collectMergeCandidates(Container container, int n, Map map, List list, boolean flag) {

      for (int i = n; i < 36; i++) {
         ItemStack stack = container.getSlot(i).getStack();
         if (stack != null && stack.getMaxStackSize() > 1 && !list.contains(i)) {
            Item item = stack.getItem();
            AtomicInteger atomicInteger = new AtomicInteger(stack.stackSize);
            ContainerSlotQuery containerSlotQuery = ContainerSlotQuery.of(container);
            containerSlotQuery = containerSlotQuery.fromSlot((byte)36);
            BiPredicate biPredicate = (obj, obj2) -> {
               if (atomicInteger.get() <= 0) {
                  return false;
               } else if (obj != null && obj != stack && !list.contains(obj2)) {
                  if (((ItemStack)obj).getItem().equals(item) && ((ItemStack)obj).getDisplayName().equals(stack.getDisplayName())) {
                     int n2 = (Integer)map.get(obj2);
                     atomicInteger.addAndGet(-n2);
                     return true;
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            };
            List list2 = containerSlotQuery.filterStackAndSlot(biPredicate).toSlotIndexList();
            if ((flag ? atomicInteger.get() != stack.stackSize : atomicInteger.get() <= 0) && !list2.isEmpty()) {
               int n3 = (Integer)list2.get(list2.size() - 1);
               map.put(n3, atomicInteger.get() <= 0 ? -atomicInteger.get() : 0);

               for (int j = 0; j < list2.size() - 1; j++) {
                  map.put(list2.get(j), 0);
               }

               list.add(i);
            }
         }
      }
   }

   private boolean processArmorAction(boolean flag) {
      if (InvManagerActionOption.PUT_ON_ARMOR.isSelected() && !this.isActionPaused()) {
         this.armorTicksSinceClick++;
         Container inventoryContainer = mc.thePlayer.inventoryContainer;
         List list3 = InventoryUtil.sortSlotsByMode(inventoryContainer, InventoryUtil.findBestArmorSetIncludingEquipped(inventoryContainer, this.onlyInHotbar.getValue() ? 36 : 9, this.ignoreDurability.getValue()), this.armorSequence.getSelectedValue());
         boolean flag2 = this.armorSequence.getSelectedValue().equals("Nearest");
         ArrayList<Runnable> list4 = new ArrayList<>();
         ArrayList list5 = new ArrayList();
         if (this.noArmorDrop.getValue()) {
            for (int n3 : (Iterable<Integer>)(list3)) {
               if (n3 >= 9) {
                  ItemStack stack = inventoryContainer.getSlot(n3).getStack();
                  int n4 = ((ItemArmor)stack.getItem()).armorType + 5;
                  if (inventoryContainer.getSlot(n4).getStack() == null) {
                     list4.add(() -> this.equipArmorIntoEmptySlot(n3, n4, inventoryContainer));
                     list5.add(stack);
                  }
               }
            }

            boolean flag3 = false;
            boolean flag4 = false;

            for (int i = 9; i < inventoryContainer.inventorySlots.size(); i++) {
               if (inventoryContainer.getSlot(i).getStack() == null) {
                  if (mc.thePlayer.inventory.getItemStack() != null && this.holdingSwappedArmor) {
                     int n7 = i;
                     list4.add(() -> {
                        this.pickupOrPlaceSlot(n7, (byte)0, inventoryContainer);
                     });
                     list5.add(mc.thePlayer.inventory.getItemStack());
                     flag4 = true;
                  }

                  if (!flag4) {
                     flag3 = true;
                     break;
                  }

                  flag4 = false;
               }
            }

            if (!flag3) {
               for (int n8 : (Iterable<Integer>)(list3)) {
                  if (n8 >= 9) {
                     ItemStack stack4 = inventoryContainer.getSlot(n8).getStack();
                     int n9 = ((ItemArmor)stack4.getItem()).armorType + 5;
                     ItemStack stack5 = inventoryContainer.getSlot(n9).getStack();
                     if (stack5 != null) {
                        list4.add(() -> this.pickupOldArmorSlot(n9, inventoryContainer));
                        list5.add(stack5);
                        list4.add(() -> this.equipNewArmorWhileFull(n8, n9, inventoryContainer));
                        list5.add(stack4);
                        list4.add(() -> this.placeOldArmorIntoSourceSlot(n8, inventoryContainer));
                        list5.add(stack5);
                        this.holdingSwappedArmor = true;
                     }
                  }
               }
            } else {
               for (int n12 : (Iterable<Integer>)(list3)) {
                  if (n12 >= 9) {
                     int n13 = ((ItemArmor)inventoryContainer.getSlot(n12).getStack().getItem()).armorType + 5;
                     ItemStack stack7 = inventoryContainer.getSlot(n13).getStack();
                     if (stack7 != null) {
                        list4.add(() -> this.unequipOldArmorSlot(n13, inventoryContainer));
                        list5.add(stack7);
                     }
                  }
               }

               for (int n15 : (Iterable<Integer>)(list3)) {
                  if (n15 >= 9) {
                     ItemStack stack8 = inventoryContainer.getSlot(n15).getStack();
                     int n16 = ((ItemArmor)stack8.getItem()).armorType + 5;
                     ItemStack stack9 = inventoryContainer.getSlot(n16).getStack();
                     if (stack9 != null) {
                        list4.add(() -> {
                           this.shiftClickSlot(n15, n16, inventoryContainer);
                        });
                        list5.add(stack8);
                        if (mc.thePlayer.inventory.getItemStack() != null && this.holdingSwappedArmor) {
                           list4.add(() -> this.placeSwappedArmorIntoSlot(n15, inventoryContainer));
                           list5.add(stack9);
                           this.holdingSwappedArmor = false;
                        }
                     }
                  }
               }
            }
         } else {
            this.holdingSwappedArmor = false;

            for (int n19 : (Iterable<Integer>)(list3)) {
               if (n19 >= 9) {
                  ItemStack stack10 = inventoryContainer.getSlot(n19).getStack();
                  int n20 = ((ItemArmor)stack10.getItem()).armorType + 5;
                  ItemStack stack11 = inventoryContainer.getSlot(n20).getStack();
                  if (stack11 != null) {
                     stack11.setSlotIndex(n20);
                     list4.add(() -> {
                        this.dropItemStack(stack11, inventoryContainer);
                     });
                     list5.add(stack10);
                  }

                  if (!flag2) {
                     list4.add(() -> this.equipArmorDirect(n19, n20, inventoryContainer));
                     list5.add(stack10);
                  }
               }
            }

            if (flag2) {
               for (int n22 : (Iterable<Integer>)(list3)) {
                  if (n22 >= 9) {
                     ItemStack stack12 = inventoryContainer.getSlot(n22).getStack();
                     int n23 = ((ItemArmor)stack12.getItem()).armorType + 5;
                     list4.add(() -> this.equipArmorNearest(n22, n23, inventoryContainer));
                     stack12.setSlotIndex(n22);
                     list5.add(stack12);
                  }
               }
            }
         }

         if (list4.isEmpty()) {
            boolean flag5 = true;
            if (this.holdingSwappedArmor && mc.thePlayer.inventory.getItemStack() != null) {
               for (int j = 9; j < inventoryContainer.inventorySlots.size(); j++) {
                  if (inventoryContainer.getSlot(j).getStack() == null && mc.thePlayer.inventory.getItemStack() != null && this.holdingSwappedArmor) {
                     int n25 = j;
                     list4.add(() -> this.placeHeldArmorIntoSlot(n25, inventoryContainer));
                     list5.add(mc.thePlayer.inventory.getItemStack());
                     flag5 = false;
                     break;
                  }
               }
            }

            if (flag5) {
               return true;
            }
         }

         if (flag) {
            this.openSpoofedInventory();
            return this.armorInstant.getValue();
         } else {
            ActionRateAccumulator armorCpsAccumulator2 = this.armorCpsAccumulator;
            RandomizedRangeSettings armorDoubleClickRate2 = this.armorDoubleClickRate;
            return this.executeActionBatch(list4, list5, armorCpsAccumulator2, armorDoubleClickRate2, this.armorFailRate, this.armorMissClickRate, this.armorMouseMoveRandom, inventoryContainer, this.armorInstant.getValue(), this.armorCPS.getLastIntValue(), this.armorTicksSinceClick, (int)this.armorMaxMouseMoveSpeed.getValue(true));
         }
      } else {
         return true;
      }
   }

   private boolean executeActionBatch(
      ArrayList list,
      ArrayList list2,
      ActionRateAccumulator actionRateAccumulator,
      RandomizedRangeSettings randomizedRangeSettings,
      RandomizedRangeSettings randomizedRangeSettings2,
      RandomizedRangeSettings randomizedRangeSettings3,
      RandomizedRangeSettings randomizedRangeSettings4,
      Container container,
      boolean flag,
      int n,
      int n2,
      int n3
   ) {
      if (flag) {
         list.forEach(obj -> ((Runnable)obj).run());
         return true;
      } else {
         if (!list2.isEmpty()) {
            ItemStack stack = (ItemStack)list2.get(list2.size() > 1 ? 1 : 0);
            if (stack != null) {
               this.nextTargetSlot = stack.getSlotIndex();
            }
         }

         if (this.rollAccumulator(this.failRateAccumulator, randomizedRangeSettings2)) {
            return false;
         } else {
            int n4 = this.mode.getSelectedValue().equals("SpoofInv") ? 1 : actionRateAccumulator.accumulate(n);

            while (n4 > 0 && !list.isEmpty()) {
               if (this.tryMissClick(randomizedRangeSettings3, container)) {
                  n4--;
               } else {
                  n4 = this.filterClicksByMouseSpeed(n2, n3, container, ((ItemStack)list2.get(0)).getSlotIndex(), n4, randomizedRangeSettings4);
                  if (n4 <= 0) {
                     break;
                  }

                  Runnable runnable = (Runnable)list.get(0);
                  runnable.run();
                  if (this.rollAccumulator(this.doubleClickAccumulator, randomizedRangeSettings)) {
                     runnable.run();
                  }

                  list.remove(0);
                  list2.remove(0);
                  n4--;
               }
            }

            return false;
         }
      }
   }

   private boolean tryMissClick(RandomizedRangeSettings randomizedRangeSettings, Container container) {
      if (InventoryUtil.getLastClickedSlot() > 0 && InventoryUtil.getLastClickedSlot() < container.inventorySlots.size()) {
         Slot slot = container.getSlot(InventoryUtil.getLastClickedSlot());
         int xDisplayPosition = slot.xDisplayPosition;
         int yDisplayPosition = slot.yDisplayPosition;
         double maxvalue = Double.MAX_VALUE;
         int n = -1;

         for (int i = 5; i < container.inventorySlots.size(); i++) {
            if (container.getSlot(i).getStack() == null && i != InventoryUtil.getLastClickedSlot() && i != n) {
               Slot slot10 = container.getSlot(i);
               int n2 = xDisplayPosition - slot10.xDisplayPosition;
               int n3 = yDisplayPosition - slot10.yDisplayPosition;
               double d = Math.sqrt((double)(n2 * n2 + n3 * n3));
               if (d < maxvalue) {
                  maxvalue = d;
                  n = i;
               }
            }
         }

         if (maxvalue < 36.0) {
            if (this.rollAccumulator(this.missClickAccumulator, randomizedRangeSettings)) {
               this.shiftClickSlot(n, (byte)-1, container);
               return true;
            }
         }
      }

      return false;
   }

   private int filterClicksByMouseSpeed(int n, int n2, Container container, int n3, int n4, RandomizedRangeSettings randomizedRangeSettings) {
      if (this.mode.getSelectedValue().equals("SpoofInv")) {
         return n4;
      } else {
         Slot slot = container.getSlot(n3);
         ScaledResolution sr = new ScaledResolution(mc);
         int n5 = (sr.getScaledWidth() - 176) / 2;
         int n6 = (sr.getScaledHeight() - 166) / 2;
         double d = Math.abs((double)(slot.xDisplayPosition + 2 + n5) - InventoryUtil.getLastCursorPos().x);
         double d2 = Math.abs((double)(slot.yDisplayPosition + 2 + n6) - InventoryUtil.getLastCursorPos().y);
         double d3 = Math.sqrt(d * d + d2 * d2);
         int n7 = (int)(d3 / (double)n2);
         if (n7 > 0) {
            n7 += randomizedRangeSettings.getLastIntValue();
         }

         if (n > n7) {
            if (d3 > 19.0) {
               randomizedRangeSettings.nextValue();
            }

            return n4;
         } else {
            return 0;
         }
      }
   }

   private boolean rollAccumulator(ActionRateAccumulator actionRateAccumulator, RandomizedRangeSettings randomizedRangeSettings) {
      if (randomizedRangeSettings == null) {
         return false;
      } else {
         boolean shouldTrigger = actionRateAccumulator.shouldTrigger(randomizedRangeSettings.getLastIntValue());
         if (shouldTrigger) {
            randomizedRangeSettings.nextValue();
         }

         return shouldTrigger;
      }
   }

   private void markBestCategorySlotsToKeep(String str, Set set, Supplier supplier, UnaryFunction unaryFunction) {
      ArrayList hotbarSlotsForCategory = this.getHotbarSlotsForCategory(str);
      List list = hotbarSlotsForCategory.isEmpty() ? (List)supplier.get() : (List)unaryFunction.apply(hotbarSlotsForCategory);

      for (int i = 0; i < Math.max(1, hotbarSlotsForCategory.size()) && i < list.size(); i++) {
         set.add(((TuplePair)list.get(i)).getSecond());
      }
   }

   private List markBestArmorSlotToKeep(String str, Container container, int n, Set set, List list) {
      if (!this.getHotbarSlotsForCategory(str).isEmpty()) {
         if (list == null) {
            list = InventoryUtil.findBestArmorSetExcludingEquipped(container, (byte)9, this.ignoreDurability.getValue());
         }

         int n2 = this.findArmorSlotByType(container, (List)list, n);
         if (n2 >= 0) {
            set.add(n2);
         }
      }

      return (List)list;
   }

   private ArrayList collectSlotsToDrop(Container container) {
      HashSet set = new HashSet(InventoryUtil.getBestToolSlots(container, (byte)5));
      set.addAll(InventoryUtil.findBestArmorSetExcludingEquipped(container, (byte)5, this.ignoreDurability.getValue()));
      set.addAll(InventoryUtil.getGoodPotionSlots(container, (byte)5));
      if (InvManagerActionOption.isOptionSelected(InvManagerActionOption.SORT)) {
         Supplier supplier = () -> getRankedSwords(container);
         UnaryFunction unaryFunction = obj -> {
            return InventoryUtil.getSortedWeapons(container, (byte)5, (ArrayList)obj);
         };
         Supplier supplier2 = supplier;
         this.markBestCategorySlotsToKeep("Sword", set, supplier2, unaryFunction);
         supplier = () -> {
            return InventoryUtil.getSortedBows(container, (byte)5);
         };
         UnaryFunction unaryFunction2 = obj2 -> getRankedBowsWithPreferred(container, (ArrayList)obj2);
         Supplier supplier3 = supplier;
         this.markBestCategorySlotsToKeep("Bow", set, supplier3, unaryFunction2);
         supplier = () -> getRankedRods(container);
         UnaryFunction unaryFunction3 = obj3 -> {
            return InventoryUtil.getSortedFishingRods(container, (byte)5, (ArrayList)obj3);
         };
         Supplier supplier4 = supplier;
         this.markBestCategorySlotsToKeep("Rod", set, supplier4, unaryFunction3);
         supplier = () -> getRankedPickaxes(container);
         UnaryFunction unaryFunction4 = obj4 -> getRankedPickaxesWithPreferred(container, (ArrayList)obj4);
         Supplier supplier5 = supplier;
         this.markBestCategorySlotsToKeep("Pickaxe", set, supplier5, unaryFunction4);
         supplier = () -> {
            return this.getRankedAxes(container, (byte)5);
         };
         UnaryFunction unaryFunction5 = obj5 -> this.getRankedAxesWithPreferred(container, (ArrayList)obj5);
         Supplier supplier6 = supplier;
         this.markBestCategorySlotsToKeep("Axe", set, supplier6, unaryFunction5);
         supplier = () -> getRankedShovels(container);
         UnaryFunction unaryFunction6 = obj6 -> getRankedShovelsWithPreferred(container, (ArrayList)obj6);
         Supplier supplier7 = supplier;
         this.markBestCategorySlotsToKeep("Shovel", set, supplier7, unaryFunction6);
         supplier = () -> getRankedShears(container);
         UnaryFunction unaryFunction7 = obj7 -> getRankedShearsWithPreferred(container, (ArrayList)obj7);
         Supplier supplier8 = supplier;
         this.markBestCategorySlotsToKeep("Shear", set, supplier8, unaryFunction7);
         Object obj8 = null;
         obj8 = this.markBestArmorSlotToKeep("Helmet", container, (byte)0, set, (List)obj8);
         obj8 = this.markBestArmorSlotToKeep("Chestplate", container, (byte)1, set, (List)obj8);
         obj8 = this.markBestArmorSlotToKeep("Trousers", container, (byte)2, set, (List)obj8);
         obj8 = this.markBestArmorSlotToKeep("Boots", container, (byte)3, set, (List)obj8);
      } else {
         ArrayList sortedWeapons = InventoryUtil.getSortedWeapons(container, (byte)5);
         if (!sortedWeapons.isEmpty()) {
            set.add(((TuplePair)sortedWeapons.get(0)).getSecond());
         }

         ArrayList sortedBows = InventoryUtil.getSortedBows(container, (byte)5);
         if (!sortedBows.isEmpty()) {
            set.add(((TuplePair)sortedBows.get(0)).getSecond());
         }

         ArrayList sortedFishingRods = InventoryUtil.getSortedFishingRods(container, (byte)5);
         if (!sortedFishingRods.isEmpty()) {
            set.add(((TuplePair)sortedFishingRods.get(0)).getSecond());
         }
      }

      ArrayList list = new ArrayList();

      for (int i = 9; i < container.inventorySlots.size(); i++) {
         ItemStack stack = container.getSlot(i).getStack();
         if (stack != null && !set.contains(i)) {
            Item item = stack.getItem();
            if (this.items.isItemAllowed(item)) {
               list.add(i);
            }
         }
      }

      int n;
      int n2;
      int n3;
      int n4;
      int n5;
      if (InvManagerActionOption.SORT.isSelected()) {
         int value2 = (int)this.lavaBucketLimit.getValue();
         n3 = Math.max(value2, this.countHotbarSlotsForCategory("LavaBucket"));
         value2 = (int)this.waterBucketLimit.getValue();
         n4 = Math.max(value2, this.countHotbarSlotsForCategory("WaterBucket"));
         value2 = (int)this.throwablesLimit.getValue();
         n5 = Math.max(value2, this.countHotbarSlotsForCategory("Throwables"));
         value2 = (int)this.blockStackLimit.getValue();
         n = Math.max(value2, this.countHotbarSlotsForCategory("Blocks"));
         value2 = (int)this.foodStackLimit.getValue();
         n2 = Math.max(value2, this.countHotbarSlotsForCategory("Food"));
      } else {
         n3 = (int)this.lavaBucketLimit.getValue();
         n4 = (int)this.waterBucketLimit.getValue();
         n5 = (int)this.throwablesLimit.getValue();
         n = (int)this.blockStackLimit.getValue();
         n2 = (int)this.foodStackLimit.getValue();
      }

      ArrayList sortedBlocks = InventoryUtil.getSortedBlocks(container, (byte)9);
      if (sortedBlocks.size() > n) {
         for (int j = n; j < sortedBlocks.size(); j++) {
            int second = (Integer)((TuplePair)sortedBlocks.get(j)).getSecond();
            if (!set.contains(second)) {
               list.add(second);
            }
         }
      }

      ArrayList sortedFood = InventoryUtil.getSortedFood(container, (byte)9);
      if (sortedFood.size() > n2) {
         for (int k = n2; k < sortedFood.size(); k++) {
            int second2 = (Integer)((TuplePair)sortedFood.get(k)).getSecond();
            if (!set.contains(second2)) {
               list.add(second2);
            }
         }
      }

      ArrayList sortedThrowables = InventoryUtil.getSortedThrowables(container, (byte)9);
      if (sortedThrowables.size() > n5) {
         for (int m = n5; m < sortedThrowables.size(); m++) {
            int second3 = (Integer)((TuplePair)sortedThrowables.get(m)).getSecond();
            if (!set.contains(second3)) {
               list.add(second3);
            }
         }
      }

      ContainerSlotQuery containerSlotQuery = ContainerSlotQuery.of(container);
      containerSlotQuery = containerSlotQuery.fromSlot((byte)9);
      List list2 = containerSlotQuery.filterItemExact(Items.lava_bucket).toSlotIndexList();
      if (list2.size() > n3) {
         for (int idx = n3; idx < list2.size(); idx++) {
            int n6 = (Integer)list2.get(idx);
            if (!set.contains(n6)) {
               list.add(n6);
            }
         }
      }

      containerSlotQuery = ContainerSlotQuery.of(container);
      containerSlotQuery = containerSlotQuery.fromSlot((byte)9);
      List list3 = containerSlotQuery.filterItemExact(Items.water_bucket).toSlotIndexList();
      if (list3.size() > n4) {
         for (int index = n4; index < list3.size(); index++) {
            int n7 = (Integer)list3.get(index);
            if (!set.contains(n7)) {
               list.add(n7);
            }
         }
      }

      return list;
   }

   private boolean isSpoofInvMode() {
      return this.mode.getSelectedValue().equals("SpoofInv") || this.mode.getSelectedValue().equals("SpoofInvOther");
   }

   private void closeSpoofedInventory(Container container) {
      if (mc.thePlayer.getSpoofedScreen() != null && this.isSpoofInvMode()) {
         if (mc.currentScreen == null) {
            mc.thePlayer.sendQueue.addToSendQueue(new C0DPacketCloseWindow(container.windowId));
            mc.thePlayer.setSpoofedScreen(null);
            this.restoreInputsAfterSpoofClose();
         }
      }
   }

   private void openSpoofedInventory() {
      if (this.isSpoofInvMode() && mc.thePlayer.getSpoofedScreen() == null && mc.currentScreen == null) {
         mc.thePlayer.sendQueue.addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT));
         mc.thePlayer.setSpoofedScreen(new GuiInventory(mc.thePlayer));
         this.unpressNonMovementKeys();
      }
   }

   private void restoreInputsAfterSpoofClose() {
      if (Display.isActive()) {
         if (this.stopMouseMovement.getValue()) {
            mc.mouseHelper.resetMouseDelta();
         }

         if (this.stopAllOtherKeyAndMouseInputs.getValue()) {
            mc.leftClickCounter = (short)10000;
         }
      }
   }

   private void unpressNonMovementKeys() {
      if (this.stopAllOtherKeyAndMouseInputs.getValue()) {
         HashSet set = Sets.newHashSet(
            new Integer[]{
               mc.gameSettings.keyBindForward.getKeyCode(),
               mc.gameSettings.keyBindLeft.getKeyCode(),
               mc.gameSettings.keyBindRight.getKeyCode(),
               mc.gameSettings.keyBindBack.getKeyCode(),
               mc.gameSettings.keyBindJump.getKeyCode(),
               mc.gameSettings.keyBindSneak.getKeyCode()
            }
         );
         KeyBinding.unPressKeysExcept(set);
      }
   }

   public boolean shouldBlockNonMovementInputs() {
      return this.isToggled() && this.isSpoofInvMode() && mc.thePlayer != null && mc.currentScreen == null && mc.thePlayer.getSpoofedScreen() != null && this.stopAllOtherKeyAndMouseInputs.getValue();
   }

   public boolean isKeyBlockedWhileSpoofing(int n) {
      GameSettings gameSettings = mc.gameSettings;
      return this.shouldBlockNonMovementInputs()
         && n != gameSettings.keyBindForward.getKeyCode()
         && n != gameSettings.keyBindLeft.getKeyCode()
         && n != gameSettings.keyBindRight.getKeyCode()
         && n != gameSettings.keyBindBack.getKeyCode()
         && n != gameSettings.keyBindJump.getKeyCode()
         && n != gameSettings.keyBindSneak.getKeyCode();
   }

   public boolean shouldStopMouseMovement() {
      return this.isToggled() && mc.thePlayer.getSpoofedScreen() != null && mc.currentScreen == null && this.isSpoofInvMode() && this.stopMouseMovement.getValue();
   }

   private void swapItemToHotbar(int n, ItemStack stack, Container container) {
      if (stack != null) {
         int slotIndex = stack.getSlotIndex();
         ItemStack stack2 = container.getSlot(n + 36).getStack();
         if (stack2 != null) {
            stack2.setSlotIndex(slotIndex);
         }

         this.updateVirtualCursor(container, slotIndex);
         switch (this.clickMode.getSelectedValue()) {
            case "Exploit":
               break;
            case "Basic":
            case "Other":
               mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, slotIndex, n, 2, mc.thePlayer);
         }

         this.resetActionTickCounters();
      }
   }

   private void dropItemStack(ItemStack stack, Container container) {
      if (stack != null) {
         this.updateVirtualCursor(container, stack.getSlotIndex());
         switch (this.clickMode.getSelectedValue()) {
            case "Basic":
               mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, stack.getSlotIndex(), 1, 4, mc.thePlayer);
               break;
            case "Other":
               break;
            case "Exploit":
         }

         this.resetActionTickCounters();
      }
   }

   private void pickupOrPlaceSlot(int n, int n2, Container container) {
      this.holdingSwappedArmor = mc.thePlayer.inventory.getItemStack() == null;
      this.updateVirtualCursor(container, n);
      mc.playerController.windowClick(container.windowId, n, n2, 0, mc.thePlayer);
      this.resetActionTickCounters();
   }

   private void clickSlot(int n, int n2, Container container) {
      this.updateVirtualCursor(container, n);
      mc.playerController.windowClick(container.windowId, n, n2, 0, mc.thePlayer);
      this.resetActionTickCounters();
   }

   private void shiftClickSlot(int n, int n2, Container container) {
      this.updateVirtualCursor(container, n);
      switch (this.clickMode.getSelectedValue()) {
         case "Basic":
            mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, n, 0, 1, mc.thePlayer);
            break;
         case "Other":
            mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, n, 0, 1, mc.thePlayer);
            break;
         case "Exploit":
      }

      this.resetActionTickCounters();
   }

   private void updateVirtualCursor(Container container, int n) {
      ScaledResolution sr = new ScaledResolution(mc);
      int n2 = (sr.getScaledWidth() - 176) / 2;
      int n3 = (sr.getScaledHeight() - 166) / 2;
      Slot slot = container.getSlot(n);
      InventoryUtil.setLastCursorPos((double)(slot.xDisplayPosition + 2 + n2), (double)(slot.yDisplayPosition + 2 + n3));
      InventoryUtil.setLastClickedSlot(n);
      this.lastActionTimer.reset();
   }

   private boolean isActionPaused() {
      return this.notWhileScaffold.getValue() && BlockUtil.isScaffoldActiveAndValid()
         || this.notWhileKillAuraHasTarget.getValue() && ClientManagers.moduleManager.killAura.target != null
         || this.isSpoofInvMode() && mc.thePlayer.isEating() && this.notWhileEating.getValue()
         || this.activeHotbarArmorSlot != -1 && InvManagerActionOption.isOptionSelected(InvManagerActionOption.PUT_ON_ARMOR_HOT_BAR) && (this.mode.getSelectedValue().equals("OpenInv") || this.isSpoofInvMode());
   }

   public void resetActionTickCounters() {
      this.armorTicksSinceClick = 0;
      this.mergeTicksSinceClick = 0;
      this.sortTicksSinceClick = 0;
      this.cleanTicksSinceClick = 0;
      this.craftTicksSinceClick = 0;
   }

   private void updateCpsRecalculation() {
      TickCounter armorCpsRecalcCounter2 = this.armorCpsRecalcCounter;
      if (armorCpsRecalcCounter2.hasReached(this.armorCPSRecalculate.getLastIntValue())) {
         this.armorCPS.nextValue();
         this.armorCPSRecalculate.nextValue();
         this.armorCpsRecalcCounter.reset();
      }

      armorCpsRecalcCounter2 = this.sortCpsRecalcCounter;
      if (armorCpsRecalcCounter2.hasReached(this.sortCPSRecalculate.getLastIntValue())) {
         this.sortCPS.nextValue();
         this.sortCPSRecalculate.nextValue();
         this.sortCpsRecalcCounter.reset();
      }

      armorCpsRecalcCounter2 = this.cleanCpsRecalcCounter;
      if (armorCpsRecalcCounter2.hasReached(this.cleanCPSRecalculate.getLastIntValue())) {
         this.cleanCPS.nextValue();
         this.cleanCPSRecalculate.nextValue();
         this.cleanCpsRecalcCounter.reset();
      }

      armorCpsRecalcCounter2 = this.craftCpsRecalcCounter;
      if (armorCpsRecalcCounter2.hasReached(this.craftCPSRecalculate.getLastIntValue())) {
         this.craftCPS.nextValue();
         this.craftCPSRecalculate.nextValue();
         this.craftCpsRecalcCounter.reset();
      }

      armorCpsRecalcCounter2 = this.mergeCpsRecalcCounter;
      if (armorCpsRecalcCounter2.hasReached(this.mergeCPSRecalculate.getLastIntValue())) {
         this.mergeCPS.nextValue();
         this.mergeCPSRecalculate.nextValue();
         this.mergeCpsRecalcCounter.reset();
      }
   }

   private void resetRandomizers() {
      this.startDelay.nextValue();
      this.armorCPS.nextValue();
      this.armorCPSRecalculate.nextValue();
      this.sortCPS.nextValue();
      this.sortCPSRecalculate.nextValue();
      this.cleanCPS.nextValue();
      this.cleanCPSRecalculate.nextValue();
      this.armorDoubleClickRate.nextValue();
      this.cleanMissClickRate.nextValue();
      this.armorMissClickRate.nextValue();
      this.sortFailRate.nextValue();
      this.armorFailRate.nextValue();
      this.cleanFailRate.nextValue();
      this.craftCPS.nextValue();
      this.craftCPSRecalculate.nextValue();
      this.tickDelayBeforeSwitch.nextValue();
      this.tickDelayBeforeClick.nextValue();
      this.tickDelayAfter.nextValue();
      this.nextTargetSlot = -1;
      this.resetActionTickCounters();
   }

   public ArrayList getHotbarSlotsForCategory(String str) {
      ArrayList list = new ArrayList();
      if (this.slot1.getSelectedValue().equals(str)) {
         list.add(0);
      }

      if (this.slot2.getSelectedValue().equals(str)) {
         list.add(1);
      }

      if (this.slot3.getSelectedValue().equals(str)) {
         list.add(2);
      }

      if (this.slot4.getSelectedValue().equals(str)) {
         list.add(3);
      }

      if (this.slot5.getSelectedValue().equals(str)) {
         list.add(4);
      }

      if (this.slot6.getSelectedValue().equals(str)) {
         list.add(5);
      }

      if (this.slot7.getSelectedValue().equals(str)) {
         list.add(6);
      }

      if (this.slot8.getSelectedValue().equals(str)) {
         list.add(7);
      }

      if (this.slot9.getSelectedValue().equals(str)) {
         list.add(8);
      }

      return list;
   }

   public int countHotbarSlotsForCategory(String str) {
      int n = 0;
      if (this.slot1.getSelectedValue().equals(str)) {
         n++;
      }

      if (this.slot2.getSelectedValue().equals(str)) {
         n++;
      }

      if (this.slot3.getSelectedValue().equals(str)) {
         n++;
      }

      if (this.slot4.getSelectedValue().equals(str)) {
         n++;
      }

      if (this.slot5.getSelectedValue().equals(str)) {
         n++;
      }

      if (this.slot6.getSelectedValue().equals(str)) {
         n++;
      }

      if (this.slot7.getSelectedValue().equals(str)) {
         n++;
      }

      if (this.slot8.getSelectedValue().equals(str)) {
         n++;
      }

      if (this.slot9.getSelectedValue().equals(str)) {
         n++;
      }

      return n;
   }

   public List getRankedSlotsForCategory(Container container, String str) {
      ArrayList list = null;
      if (str.equals("Helmet") || str.equals("Chestplate") || str.equals("Trousers") || str.equals("Boots")) {
         list = InventoryUtil.findBestArmorSetExcludingEquipped(container, (byte)9, this.ignoreDurability.getValue());
      }

      switch (str) {
         case "Sword":
            return InventoryUtil.getSortedWeapons(container, (byte)5);
         case "Bow":
            return InventoryUtil.getSortedBows(container, (byte)5);
         case "Rod":
            return InventoryUtil.getSortedFishingRods(container, (byte)5);
         case "Throwables":
            return InventoryUtil.getSortedThrowables(container, (byte)5);
         case "Blocks":
            return InventoryUtil.getSortedBlocks(container, (byte)5);
         case "Food":
            return InventoryUtil.getSortedFood(container, (byte)5);
         case "Gapple":
            return InventoryUtil.getSortedGoldenApples(container, (byte)5);
         case "EnderPearls":
            return InventoryUtil.getSortedEnderPearls(container, (byte)5);
         case "Soups":
            return InventoryUtil.getSortedMushroomStews(container, (byte)5);
         case "Pickaxe":
            return InventoryUtil.getSortedPickaxes(container, (byte)5);
         case "Axe":
            return this.getRankedAxes(container, (byte)5);
         case "Shovel":
            return InventoryUtil.getSortedShovels(container, (byte)5);
         case "Shear":
            return InventoryUtil.getSortedShears(container, (byte)5);
         case "Helmet":
            return this.getBestArmorEntry(container, list, (byte)0);
         case "Chestplate":
            return this.getBestArmorEntry(container, list, (byte)1);
         case "Trousers":
            return this.getBestArmorEntry(container, list, (byte)2);
         case "Boots":
            return this.getBestArmorEntry(container, list, (byte)3);
         case "LavaBucket":
            return InventoryUtil.getSortedLavaBuckets(container, (byte)5);
         case "WaterBucket":
            return InventoryUtil.getSortedWaterBuckets(container, (byte)5);
         default:
            return new ArrayList();
      }
   }

   private List getRankedAxesExcludingSwords(Container container, int n, ArrayList list) {
      int n2 = this.countHotbarSlotsForCategory("Sword");
      int n3 = this.countHotbarSlotsForCategory("Axe");
      if (n2 > 0 && n3 > 0) {
         ArrayList sortedAxes = InventoryUtil.getSortedAxes(container, n, list);
         ArrayList sortedWeapons = InventoryUtil.getSortedWeapons(container, n, this.getHotbarSlotsForCategory("Sword"));

         for (int i = 0; i < n2 && i < sortedWeapons.size(); i++) {
            TuplePair tuplePair = (TuplePair)sortedWeapons.get(i);
            ItemStack stack = mc.thePlayer.inventoryContainer.getSlot((Integer)tuplePair.getSecond()).getStack();
            if (stack != null && stack.getItem() instanceof ItemAxe) {
               for (int j = sortedAxes.size() - 1; j >= 0; j--) {
                  if (Objects.equals(((TuplePair)sortedAxes.get(j)).getSecond(), tuplePair.getSecond())) {
                     sortedAxes.remove(j);
                  }
               }
            }
         }

         return sortedAxes;
      } else {
         return InventoryUtil.getSortedAxes(container, (byte)5, list);
      }
   }

   private List getRankedAxes(Container container, int n) {
      int n2 = this.countHotbarSlotsForCategory("Sword");
      int n3 = this.countHotbarSlotsForCategory("Axe");
      if (n2 > 0 && n3 > 0) {
         ArrayList sortedAxes = InventoryUtil.getSortedAxes(container, n);
         ArrayList sortedWeapons = InventoryUtil.getSortedWeapons(container, n);

         for (int i = 0; i < n2 && i < sortedWeapons.size(); i++) {
            TuplePair tuplePair = (TuplePair)sortedWeapons.get(i);
            ItemStack stack = mc.thePlayer.inventoryContainer.getSlot((Integer)tuplePair.getSecond()).getStack();
            if (stack != null && stack.getItem() instanceof ItemAxe) {
               for (int j = sortedAxes.size() - 1; j >= 0; j--) {
                  if (Objects.equals(((TuplePair)sortedAxes.get(j)).getSecond(), tuplePair.getSecond())) {
                     sortedAxes.remove(j);
                  }
               }
            }
         }

         return sortedAxes;
      } else {
         return InventoryUtil.getSortedAxes(container, (byte)5);
      }
   }

   private ArrayList getBestArmorEntry(Container container, List list, int n) {
      int n2 = this.findArmorSlotByType(container, list, n);
      return n2 >= 0 ? Lists.newArrayList(new TuplePair[]{new TuplePair(5.0, n2)}) : new ArrayList();
   }

   private int findArmorSlotByType(Container container, List list, int n) {

      for (int i = 0; i < list.size(); i++) {
         int n2 = (Integer)list.get(i);
         ItemStack stack = container.getSlot(n2).getStack();
         if (stack != null) {
            Item item = stack.getItem();
            if (item instanceof ItemArmor) {
               if (((ItemArmor)item).armorType == n) {
                  return n2;
               }
            }
         }
      }

      return -1;
   }

   public boolean isBlockingGuiInput() {
      if (mc.thePlayer != null && mc.theWorld != null && this.blockInputWhileManaging.getValue()) {
         if (!this.lastActionTimer.reached(500L) && mc.currentScreen instanceof GuiInventory) {
            return true;
         }
      }

      return false;
   }

   public int getNextTargetSlot() {
      return this.nextTargetSlot;
   }

   public String[] getSlotCategories() {
      return this.slotCategories;
   }

   private static List getRankedShearsWithPreferred(Container container, ArrayList list) {
      return InventoryUtil.getSortedShears(container, (byte)5, list);
   }

   private static List getRankedShears(Container container) {
      return InventoryUtil.getSortedShears(container, (byte)5);
   }

   private static List getRankedShovelsWithPreferred(Container container, ArrayList list) {
      return InventoryUtil.getSortedShovels(container, (byte)5, list);
   }

   private static List getRankedShovels(Container container) {
      return InventoryUtil.getSortedShovels(container, (byte)5);
   }

   private List getRankedAxesWithPreferred(Container container, ArrayList list) {
      return this.getRankedAxesExcludingSwords(container, (byte)5, list);
   }

   private static List getRankedPickaxesWithPreferred(Container container, ArrayList list) {
      return InventoryUtil.getSortedPickaxes(container, (byte)5, list);
   }

   private static List getRankedPickaxes(Container container) {
      return InventoryUtil.getSortedPickaxes(container, (byte)5);
   }

   private static List getRankedRods(Container container) {
      return InventoryUtil.getSortedFishingRods(container, (byte)5);
   }

   private static List getRankedBowsWithPreferred(Container container, ArrayList list) {
      return InventoryUtil.getSortedBows(container, (byte)5, list);
   }

   private static List getRankedSwords(Container container) {
      return InventoryUtil.getSortedWeapons(container, (byte)5);
   }

   private void placeHeldArmorIntoSlot(int n, Container container) {
      this.pickupOrPlaceSlot(n, (byte)0, container);
   }

   private void equipArmorNearest(int n, int n2, Container container) {
      this.shiftClickSlot(n, n2, container);
   }

   private void equipArmorDirect(int n, int n2, Container container) {
      this.shiftClickSlot(n, n2, container);
   }

   private void placeOldArmorIntoSourceSlot(int n, Container container) {
      this.pickupOrPlaceSlot(n, (byte)0, container);
   }

   private void equipNewArmorWhileFull(int n, int n2, Container container) {
      this.shiftClickSlot(n, n2, container);
   }

   private void pickupOldArmorSlot(int n, Container container) {
      this.pickupOrPlaceSlot(n, (byte)0, container);
   }

   private void placeSwappedArmorIntoSlot(int n, Container container) {
      this.pickupOrPlaceSlot(n, (byte)0, container);
   }

   private void unequipOldArmorSlot(int n, Container container) {
      this.shiftClickSlot(n, (byte)-1, container);
   }

   private void equipArmorIntoEmptySlot(int n, int n2, Container container) {
      this.shiftClickSlot(n, n2, container);
   }

   private void shiftClickMergeSlot(int n, Container container) {
      this.shiftClickSlot(n, (byte)-1, container);
   }

   private void executeSortSwap(int n, ItemStack stack, Container container) {
      this.swapItemToHotbar(n, stack, container);
   }

   private boolean isArmorHotbarVisible() {
      return InvManagerActionOption.isOptionSelected(InvManagerActionOption.PUT_ON_ARMOR_HOT_BAR) && (this.mode.getSelectedValue().equals("OpenInv") || this.isSpoofInvMode());
   }

   private boolean isCraftCpsVisible() {
      return !this.craftInstant.getValue();
   }

   private boolean isCraftMouseMoveRandomVisible() {
      return !this.craftInstant.getValue();
   }

   private boolean isCraftMaxMouseMoveSpeedVisible() {
      return !this.craftInstant.getValue();
   }

   private boolean isCraftVisible() {
      return InvManagerActionOption.isOptionSelected(InvManagerActionOption.CRAFT) && this.mode.getSelectedValue().equals("OpenInv");
   }

   private boolean isCleanMissClickRateVisible() {
      return !this.cleanInstant.getValue();
   }

   private boolean isCleanFailRateVisible() {
      return !this.cleanInstant.getValue();
   }

   private boolean isCleanCpsRecalculateVisible() {
      return !this.cleanInstant.getValue();
   }

   private boolean isCleanCpsVisible() {
      return !this.cleanInstant.getValue();
   }

   private boolean isCleanMouseMoveRandomVisible() {
      return !this.cleanInstant.getValue();
   }

   private boolean isArmorMissClickRateVisible() {
      return !this.armorInstant.getValue();
   }

   private boolean isArmorDoubleClickRateVisible() {
      return !this.armorInstant.getValue();
   }

   private boolean isArmorCpsRecalculateVisible() {
      return !this.armorInstant.getValue();
   }

   private boolean isArmorMouseMoveRandomVisible() {
      return !this.armorInstant.getValue();
   }

   private boolean isArmorMaxMouseMoveSpeedVisible() {
      return !this.armorInstant.getValue();
   }

   private boolean isMergeMissClickRateVisible() {
      return !this.mergeInstant.getValue();
   }

   private boolean isMergeDoubleClickRateVisible() {
      return !this.mergeInstant.getValue();
   }

   private boolean isMergeCpsRecalculateVisible() {
      return !this.mergeInstant.getValue();
   }

   private boolean isMergeCpsVisible() {
      return !this.mergeInstant.getValue();
   }

   private boolean isMergeMouseMoveRandomVisible() {
      return !this.mergeInstant.getValue();
   }

   private boolean isSortFailRateVisible() {
      return !this.sortInstant.getValue();
   }

   private boolean isSortCpsRecalculateVisible() {
      return !this.sortInstant.getValue();
   }

   private boolean isSortCpsVisible() {
      return !this.sortInstant.getValue();
   }

   private boolean isSortMouseMoveRandomVisible() {
      return !this.sortInstant.getValue();
   }

   private boolean isSortMaxMouseMoveSpeedVisible() {
      return !this.sortInstant.getValue();
   }

   private boolean isAutoCloseInvVisible() {
      return !this.isSpoofInvMode();
   }


}
