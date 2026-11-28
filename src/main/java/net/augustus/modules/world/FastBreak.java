package net.augustus.modules.world;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.settings.options.FastBreakBlockOption;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.options.FastBreakDedicatedItemOption;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;

public class FastBreak extends Module implements PacketListener {
   public final BooleanValue customBlockHitDelay;
   public final DoubleValue blockHitDelay;
   public final BooleanValue ignoreMiningFatigue;
   public final BooleanValue onlyWhenBlink;
   public final BooleanValue ignoreBeds;
   public final BooleanValue debugBreakSpeed;
   private final StringValue mode;
   private final SettingGroup simpleSettings;
   private final BooleanValue customInAirMultiplier;
   private final DoubleValue inAirMultiplier;
   private final DoubleValue multiplier;
   private final DoubleValue artificialMiningSpeedEffect;
   private final BooleanValue instant;
   private BooleanValue dedicatedItemHandling;
   private SettingGroup dedicatedItems;
   private final HashMap blockOptions;
   private final HashMap dedicatedItemOptions;
   private boolean startedBreakingBlock;

   public FastBreak() {
       super("FastBreak", "Schnellbruch", new Color(52, 152, 219), Categorys.WORLD);
      this.customBlockHitDelay = (BooleanValue)new BooleanValue(this, "CustomBlockHitDelay", false).withDisplayName("CustomBlockBreakDelay");
      DoubleValue blockHitDelaySetting = new DoubleValue(this, "BlockHitDelay", 0.0, 0.0, 5.0, 0);
      BooleanValue customBlockHitDelay2 = this.customBlockHitDelay;
      BooleanValue booleanValue2 = customBlockHitDelay2;
      this.blockHitDelay = blockHitDelaySetting.visibleWhen(() -> booleanValue2.getValue()).named("BlockBreakDelay");
      this.ignoreMiningFatigue = new BooleanValue(this, "IgnoreMiningFatigue", false);
      this.onlyWhenBlink = new BooleanValue(this, "OnlyWhenBlink", false);
      this.ignoreBeds = new BooleanValue(this, "IgnoreBeds", false);
      this.debugBreakSpeed = new BooleanValue(this, "DebugBreakSpeed", false);
      this.mode = new StringValue(this, "Mode", "Simple", new String[]{"Simple", "Expert"});
      this.simpleSettings = new SettingGroup(this, "SimpleSettings", true).visibleWhen(() -> this.isSimpleMode());
      this.customInAirMultiplier = new BooleanValue(this, "CustomInAirMultiplier", false, this.simpleSettings);
      DoubleValue inAirMultiplierSetting = new DoubleValue(this, "InAirMultiplier", 0.2, 0.0, 1.0, 2, this.simpleSettings);
      BooleanValue customInAirMultiplier2 = this.customInAirMultiplier;
      this.inAirMultiplier = inAirMultiplierSetting.visibleWhen(() -> customInAirMultiplier2.getValue());
      this.multiplier = new DoubleValue(this, "Multiplier", 1.0, 1.0, 2.0, 2, this.simpleSettings);
      this.artificialMiningSpeedEffect = new DoubleValue(this, "ArtificialMiningSpeedEffect", 0.0, 0.0, 3.0, 0, this.simpleSettings);
      this.instant = new BooleanValue(this, "Instant", false, this.simpleSettings);
      this.blockOptions = new HashMap();
      this.dedicatedItemOptions = new HashMap();
      this.startedBreakingBlock = false;
      this.populateBlockOptions(this.blockOptions);
      FastBreakBlockOption[] fastBreakBlockOptions = (FastBreakBlockOption[])this.blockOptions.values().toArray(new FastBreakBlockOption[0]);
      Arrays.sort(fastBreakBlockOptions, Comparator.comparing(fastBreakBlockOption -> fastBreakBlockOption.getName()));
      new MultiOptionSetting(this, "BlocksToFastbreak", fastBreakBlockOptions);
      this.blockOptions.values().forEach(obj -> {
         StringValue mode2 = this.mode;
         SettingVisibilityCondition condition4 = () -> {
            return this.mode.getSelectedValue().equals("Expert");
         };
         String str3 = "";
         ((FastBreakBlockOption)obj).initSettings(str3, mode2, (SettingGroup)null, condition4);
      });
      Item[] items = new Item[]{
         Items.wooden_pickaxe,
         Items.stone_pickaxe,
         Items.iron_pickaxe,
         Items.golden_pickaxe,
         Items.diamond_pickaxe,
         Items.wooden_axe,
         Items.stone_axe,
         Items.iron_axe,
         Items.golden_axe,
         Items.diamond_axe,
         Items.wooden_shovel,
         Items.stone_shovel,
         Items.iron_shovel,
         Items.golden_shovel,
         Items.diamond_shovel,
         Items.shears
      };
      this.dedicatedItemHandling = new BooleanValue(this, "DedicatedItemHandling", false);
      SettingGroup dedicatedItemsGroup = new SettingGroup(this, "DedicatedItems", true);
      customBlockHitDelay2 = this.dedicatedItemHandling;
      BooleanValue booleanValue4 = customBlockHitDelay2;
      this.dedicatedItems = dedicatedItemsGroup.visibleWhen(() -> booleanValue4.getValue());

      for (Item item : items) {
         String resourcePath = ((ResourceLocation)Item.itemRegistry.getNameForObject(item)).getResourcePath();
         this.dedicatedItemOptions.put(item, new FastBreakDedicatedItemOption(this, this, resourcePath));
      }

      FastBreakDedicatedItemOption[] options = (FastBreakDedicatedItemOption[])this.dedicatedItemOptions.values().toArray(new FastBreakDedicatedItemOption[0]);
      Arrays.sort(options, Comparator.comparing(FastBreakDedicatedItemOption::getName));
      new MultiOptionSetting(this, "CustomItems", options, this.dedicatedItems);
      this.dedicatedItemOptions.values().forEach(obj3 -> ((FastBreakDedicatedItemOption)obj3).initSettings());
   }

   private void populateBlockOptions(HashMap map) {

      for (ResourceLocation location : (Iterable<ResourceLocation>)(Block.blockRegistry.getKeys())) {
         String resourcePath = location.getResourcePath();
         map.put(Block.getBlockFromName(resourcePath), new FastBreakBlockOption(this, this, resourcePath));
      }
   }

   public boolean isCustomInAirMultiplierEnabled(Block block) {
      if (this.dedicatedItemHandling.getValue()) {
         FastBreakDedicatedItemOption option = this.getActiveDedicatedItemOption();
         if (option != null && FastBreakDedicatedItemOption.isOptionSelected(option)) {
            return option.hasCustomInAirMultiplier(block);
         }
      }

      return this.mode.getSelectedValue().equals("Simple") ? this.customInAirMultiplier.getValue() : FastBreakBlockOption.getCustomInAirMultiplierSetting(this.getBlockOption(block)).getValue();
   }

   public double getInAirMultiplier(Block block) {
      if (this.dedicatedItemHandling.getValue()) {
         FastBreakDedicatedItemOption option = this.getActiveDedicatedItemOption();
         if (option != null && FastBreakDedicatedItemOption.isOptionSelected(option)) {
            return option.getInAirMultiplier(block);
         }
      }

      return this.mode.getSelectedValue().equals("Simple") ? this.inAirMultiplier.getValue() : FastBreakBlockOption.getInAirMultiplierSetting(this.getBlockOption(block)).getValue();
   }

   public double getBreakSpeedMultiplier(Block block) {
      if (this.dedicatedItemHandling.getValue()) {
         FastBreakDedicatedItemOption option = this.getActiveDedicatedItemOption();
         if (option != null && FastBreakDedicatedItemOption.isOptionSelected(option)) {
            return option.getSpeedMultiplier(block);
         }
      }

      return this.mode.getSelectedValue().equals("Simple") ? this.multiplier.getValue() : FastBreakBlockOption.getMultiplierSetting(this.getBlockOption(block)).getValue();
   }

   public double getArtificialMiningSpeedEffect(Block block) {
      if (this.dedicatedItemHandling.getValue()) {
         FastBreakDedicatedItemOption option = this.getActiveDedicatedItemOption();
         if (option != null && FastBreakDedicatedItemOption.isOptionSelected(option)) {
            return option.getArtificialHasteEffect(block);
         }
      }

      return this.mode.getSelectedValue().equals("Simple") ? this.artificialMiningSpeedEffect.getValue() : FastBreakBlockOption.getArtificialHasteSetting(this.getBlockOption(block)).getValue();
   }

   public boolean isInstantBreak(Block block) {
      if (this.dedicatedItemHandling.getValue()) {
         FastBreakDedicatedItemOption option = this.getActiveDedicatedItemOption();
         if (option != null && FastBreakDedicatedItemOption.isOptionSelected(option)) {
            return option.isInstantBreak(block);
         }
      }

      return this.mode.getSelectedValue().equals("Simple") ? this.instant.getValue() : FastBreakBlockOption.getInstantBreakSetting(this.getBlockOption(block)).getValue();
   }

   public boolean shouldFastBreakBlock(Block block) {
      if (this.isToggled()) {
         if (this.dedicatedItemHandling.getValue()) {
            FastBreakDedicatedItemOption option = this.getActiveDedicatedItemOption();
            if (option != null && FastBreakDedicatedItemOption.isOptionSelected(option)) {
               return option.isBlockEnabled(block);
            }
         }

         FastBreakBlockOption blockOption = this.getBlockOption(block);
         return blockOption != null && FastBreakBlockOption.isOptionSelected(blockOption);
      } else {
         return false;
      }
   }

   private FastBreakBlockOption getBlockOption(Block block) {
      return (FastBreakBlockOption)this.blockOptions.get(block);
   }

   private Item getHeldItem() {
      ItemStack currentEquippedItem = mc.thePlayer.getCurrentEquippedItem();
      return currentEquippedItem != null ? currentEquippedItem.getItem() : null;
   }

   private FastBreakDedicatedItemOption getActiveDedicatedItemOption() {
      Item heldItem = this.getHeldItem();
      return heldItem != null ? (FastBreakDedicatedItemOption)this.dedicatedItemOptions.get(heldItem) : null;
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
   }

   @Override
   public void onPacketSend(EventPacket event) {
      if (event.getPacket() instanceof C07PacketPlayerDigging) {
         if (((C07PacketPlayerDigging)event.getPacket()).getStatus() == C07PacketPlayerDigging.Action.START_DESTROY_BLOCK) {
            this.startedBreakingBlock = true;
         }
      }
   }

   public void logDebugBreakSpeed(Block block, BlockPos pos) {
      if (this.isToggled() && this.startedBreakingBlock && this.debugBreakSpeed.getValue()) {
         this.startedBreakingBlock = false;
         this.sendChatMessage("BreakSpeed: " + Math.round(block.getPlayerRelativeBlockHardness(mc.thePlayer, mc.thePlayer.entityWorld, pos) * 1000.0F));
      }
   }

   private boolean isSimpleMode() {
      return this.mode.getSelectedValue().equals("Simple");
   }

   public static SettingGroup accessDedicatedItemsSetting(FastBreak fastBreak) {
      return fastBreak.dedicatedItems;
   }

   public static void accessPopulateBlockOptions(FastBreak fastBreak, HashMap map) {
      fastBreak.populateBlockOptions(map);
   }

   public static BooleanValue accessDedicatedItemHandlingSetting(FastBreak fastBreak) {
      return fastBreak.dedicatedItemHandling;
   }





}
