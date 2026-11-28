package net.augustus.modules.misc;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.utils.audio.ClientSounds;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;

public class MurdererFinder extends Module implements TickListener, LoadWorldListener {
   private final BooleanValue acousticWarning;
   private final DoubleValue volume;
   public final HashSet detectedMurderers;
   private boolean shouldPlayWarningSound;
   public static final ArrayList MURDERER_WEAPONS = new ArrayList<>(
      Arrays.asList(
         Items.iron_sword,
         Item.getItemById(130),
         Items.cooked_chicken,
         Items.bone,
         Items.blaze_rod,
         Items.netherbrick,
         Items.carrot_on_a_stick,
         Items.stone_sword,
         Item.getItemById(19),
         Item.getItemById(32),
         Items.boat,
         Item.getItemById(382),
         Items.golden_pickaxe,
         Items.cooked_beef,
         Items.book,
         Items.apple,
         Items.prismarine_shard,
         Items.quartz,
         Items.diamond_sword,
         Items.name_tag,
         Items.diamond_shovel,
         Item.getByNameOrId("double_plant"),
         Items.pumpkin_pie,
         Items.diamond_hoe,
         Items.carrot,
         Item.getItemById(351),
         Items.fish,
         Items.cooked_fish,
         Items.shears,
         Items.iron_shovel,
         Items.golden_carrot,
         Items.wooden_sword,
         Items.stick,
         Items.stone_shovel,
         Items.cookie,
         Items.diamond_axe,
         Items.golden_sword,
         Items.wooden_axe
      )
   );

   public MurdererFinder() {
       super("MurdererFinder", "MörderFinder", new Color(252, 186, 3), Categorys.MISC);
      this.acousticWarning = (BooleanValue)(new BooleanValue(this, "AcousticWarning", true)).markVisualOnly();
      DoubleValue volumeSetting = new DoubleValue(this, "Volume", 93.0, 0.0, 100.0, 0);
      BooleanValue acousticWarning2 = this.acousticWarning;
      this.volume = (DoubleValue)volumeSetting.visibleWhen(() -> acousticWarning2.getValue()).markVisualOnly();
      this.detectedMurderers = new HashSet();
      this.shouldPlayWarningSound = true;
   }

   @Override
   public void onEnable() {
      this.detectedMurderers.clear();
      this.shouldPlayWarningSound = true;
   }

   @Override
   public void onDisable() {
      this.detectedMurderers.clear();
      this.shouldPlayWarningSound = true;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onTick() {

      for (Entity entity : (Iterable<Entity>)(mc.theWorld.loadedEntityList)) {
         if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            if (player.getHeldItem() != null
               && !this.detectedMurderers.contains(player.getName())
               && player.getHeldItem() != null
               && !this.detectedMurderers.contains(player.getName())) {
               Item item = player.getHeldItem().getItem();
               if (MURDERER_WEAPONS.contains(item) || item instanceof ItemDye) {
                  this.detectedMurderers.add(player.getName());
               }
            }
         }
      }

      if (!this.detectedMurderers.isEmpty() && this.shouldPlayWarningSound && this.acousticWarning.getValue()) {
         mc.getSoundHandler().playSound(PositionedSoundRecord.createWithVolume(ClientSounds.AUGUSTUS_TEXTURE7, (float)(this.volume.getValue() / 100.0)));
      }

      this.shouldPlayWarningSound = this.detectedMurderers.isEmpty();
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.detectedMurderers.clear();
   }

   public boolean isMurderer(String str) {
      return this.isToggled() && this.detectedMurderers.contains(str);
   }



}
