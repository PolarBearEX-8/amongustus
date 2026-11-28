package net.minecraft.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.mojang.authlib.GameProfile;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.HoverEvent;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public final class ItemStack {
   public static final DecimalFormat DECIMALFORMAT = new DecimalFormat("#.###");
   private int slotIndex;
   public int stackSize;
   public int animationsToGo;
   private Item item;
   private NBTTagCompound stackTagCompound;
   private int itemDamage;
   private String cachedDisplayName = null;
   private EntityItemFrame itemFrame;
   private Block canDestroyCacheBlock;
   private boolean canDestroyCacheResult;
   private Block canPlaceOnCacheBlock;
   private boolean canPlaceOnCacheResult;
   private int cachedItemHash;
   private GameProfile cachedSkullProfile = null;
   private ResourceLocation cachedSkullTexture = null;

   public boolean hasDisplayName() {
      return this.stackTagCompound != null && this.stackTagCompound.hasKey("display", 10) && this.stackTagCompound.getCompoundTag("display").hasKey("Name", 8);
   }

   public ItemStack(Block block) {
      this(block, 1);
   }

   public int getSlotIndex() {
      return this.slotIndex;
   }

   public boolean hasTagCompound() {
      return this.stackTagCompound != null;
   }

   public void onPlayerStoppedUsing(World world, EntityPlayer player, int n) {
      this.getItem().onPlayerStoppedUsing(this, world, player, n);
   }

   private boolean isItemStackEqual(ItemStack stack) {
      return this.stackSize != stack.stackSize
         ? false
         : (
            this.item != stack.item
               ? false
               : (
                  this.itemDamage != stack.itemDamage
                     ? false
                     : (
                        this.stackTagCompound == null && stack.stackTagCompound != null
                           ? false
                           : this.stackTagCompound == null || this.stackTagCompound.equals(stack.stackTagCompound)
                     )
               )
         );
   }

   public EnumRarity getRarity() {
      return this.getItem().getRarity(this);
   }

   public Item getItem() {
      return this.item;
   }

   public String getUnlocalizedName() {
      return this.item.getUnlocalizedName(this);
   }

   public NBTTagCompound getTagCompound() {
      return this.stackTagCompound;
   }

   public static boolean areItemStackTagsEqual(ItemStack stack, ItemStack stack2) {
      return stack == null && stack2 == null
         ? true
         : (
            stack == null || stack2 == null
               ? false
               : (
                  stack.stackTagCompound == null && stack2.stackTagCompound != null
                     ? false
                     : stack.stackTagCompound == null || stack.stackTagCompound.equals(stack2.stackTagCompound)
               )
         );
   }

   public ItemStack(Item item2, int n) {
      this(item2, n, 0);
   }

   public void addEnchantment(Enchantment enchantment, int n) {
      if (this.stackTagCompound == null) {
         this.setTagCompound(new NBTTagCompound());
      }

      if (!this.stackTagCompound.hasKey("ench", 9)) {
         this.stackTagCompound.setTag("ench", new NBTTagList());
      }

      NBTTagList tagList = this.stackTagCompound.getTagList("ench", 10);
      NBTTagCompound tag = new NBTTagCompound();
      tag.setShort("id", (short)enchantment.effectId);
      tag.setShort("lvl", (short)((byte)n));
      tagList.appendTag(tag);
   }

   public void clearCustomName() {
      this.cachedDisplayName = null;
      if (this.stackTagCompound != null && this.stackTagCompound.hasKey("display", 10)) {
         NBTTagCompound compoundTag = this.stackTagCompound.getCompoundTag("display");
         compoundTag.removeTag("Name");
         if (compoundTag.hasNoTags()) {
            this.stackTagCompound.removeTag("display");
            if (this.stackTagCompound.hasNoTags()) {
               this.setTagCompound((NBTTagCompound)null);
            }
         }
      }
   }

   public static ItemStack copyItemStack(ItemStack stack) {
      return stack == null ? null : stack.copy();
   }

   public void damageItem(int n, EntityLivingBase entity) {
      if ((!(entity instanceof EntityPlayer) || !((EntityPlayer)entity).capabilities.isCreativeMode) && this.isItemStackDamageable() && this.attemptDamageItem(n, entity.getRNG())) {
         entity.renderBrokenItemStack(this);
         this.stackSize--;
         if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            player.triggerAchievement(StatList.objectBreakStats[Item.getIdFromItem(this.item)]);
            if (this.stackSize == 0 && this.getItem() instanceof ItemBow) {
               player.destroyCurrentEquippedItem();
            }
         }

         if (this.stackSize < 0) {
            this.stackSize = 0;
         }

         this.itemDamage = 0;
      }
   }

   public void setCachedSkullProfile(GameProfile profile) {
      this.cachedSkullProfile = profile;
   }

   public void hitEntity(EntityLivingBase entity, EntityPlayer player) {
      if (this.item.hitEntity(this, entity, player)) {
         player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this.item)]);
      }
   }

   public static boolean areItemsEqual(ItemStack stack, ItemStack stack2) {
      return stack == null && stack2 == null ? true : (stack != null && stack2 != null ? stack.isItemEqual(stack2) : false);
   }

   public boolean getIsItemStackEqual(ItemStack stack) {
      return this.isItemStackEqual(stack);
   }

   public void readFromNBT(NBTTagCompound tag) {
      if (tag.hasKey("id", 8)) {
         this.item = Item.getByNameOrId(tag.getString("id"));
      } else {
         this.item = Item.getItemById(tag.getShort("id"));
      }

      this.stackSize = tag.getByte("Count");
      this.itemDamage = tag.getShort("Damage");
      if (this.itemDamage < 0) {
         this.itemDamage = 0;
      }

      if (tag.hasKey("tag", 10)) {
         this.stackTagCompound = tag.getCompoundTag("tag");
         if (this.item != null) {
            this.item.updateItemStackNBT(this.stackTagCompound);
         }
      }
   }

   public ItemStack useItemRightClick(World world, EntityPlayer player) {
      return this.getItem().onItemRightClick(this, world, player);
   }

   public ItemStack(Item item2, int n, int n2) {
      this.canDestroyCacheBlock = null;
      this.canDestroyCacheResult = false;
      this.canPlaceOnCacheBlock = null;
      this.canPlaceOnCacheResult = false;
      this.item = item2;
      this.stackSize = n;
      this.itemDamage = n2;
      if (this.itemDamage < 0) {
         this.itemDamage = 0;
      }
   }

   public NBTTagList getEnchantmentTagList() {
      return this.stackTagCompound == null ? null : this.stackTagCompound.getTagList("ench", 10);
   }

   public NBTTagCompound getSubCompound(String str, boolean flag) {
      if (this.stackTagCompound != null && this.stackTagCompound.hasKey(str, 10)) {
         return this.stackTagCompound.getCompoundTag(str);
      } else if (flag) {
         NBTTagCompound tag = new NBTTagCompound();
         this.setTagInfo(str, tag);
         return tag;
      } else {
         return null;
      }
   }

   public void updateAnimation(World world, Entity entity, int n, boolean flag) {
      if (this.animationsToGo > 0) {
         this.animationsToGo--;
      }

      this.item.onUpdate(this, world, entity, n, flag);
   }

   public int getMaxDamage() {
      return this.item.getMaxDamage();
   }

   public void setTagInfo(String str, NBTBase nbt) {
      if (this.stackTagCompound == null) {
         this.setTagCompound(new NBTTagCompound());
      }

      this.stackTagCompound.setTag(str, nbt);
   }

   public EnumAction getItemUseAction() {
      return this.getItem().getItemUseAction(this);
   }

   public boolean isItemStackDamageable() {
      return this.item == null ? false : (this.item.getMaxDamage() <= 0 ? false : !this.hasTagCompound() || !this.getTagCompound().getBoolean("Unbreakable"));
   }

   private void updateItemHash() {
      this.cachedItemHash = this.item == null ? -1 : this.item.hashCode() + this.itemDamage;
   }

   public void onCrafting(World world, EntityPlayer player, int n) {
      player.addStat(StatList.objectCraftStats[Item.getIdFromItem(this.item)], n);
      this.item.onCreated(this, world, player);
   }

   public List getTooltip(EntityPlayer player, boolean flag) {
      ArrayList list = Lists.newArrayList();
      String displayName = this.getDisplayName();
      if (this.hasDisplayName()) {
         displayName = EnumChatFormatting.ITALIC.toString() + displayName;
      }

      displayName = displayName + EnumChatFormatting.RESET.toString();
      if (flag) {
         String str = "";
         if (displayName.length() > 0) {
            displayName = displayName + " (";
            str = ")";
         }

         int idFromItem = Item.getIdFromItem(this.item);
         if (this.getHasSubtypes()) {
            displayName = displayName + String.format("#%04d/%d%s", idFromItem, this.itemDamage, str);
         } else {
            displayName = displayName + String.format("#%04d%s", idFromItem, str);
         }
      } else if (!this.hasDisplayName() && this.item == Items.filled_map) {
         displayName = displayName + " #" + this.itemDamage;
      }

      list.add(displayName);
      int n = 0;
      if (this.hasTagCompound() && this.stackTagCompound.hasKey("HideFlags", 99)) {
         n = this.stackTagCompound.getInteger("HideFlags");
      }

      if ((n & 32) == 0) {
         this.item.addInformation(this, player, list, flag);
      }

      if (this.hasTagCompound()) {
         if ((n & 1) == 0) {
            NBTTagList enchantmentTagList = this.getEnchantmentTagList();
            if (enchantmentTagList != null) {
               for (int i = 0; i < enchantmentTagList.tagCount(); i++) {
                  short s = enchantmentTagList.getCompoundTagAt(i).getShort("id");
                  short s2 = enchantmentTagList.getCompoundTagAt(i).getShort("lvl");
                  if (Enchantment.getEnchantmentById(s) != null) {
                     list.add(Enchantment.getEnchantmentById(s).getTranslatedName(s2));
                  }
               }
            }
         }

         if (this.stackTagCompound.hasKey("display", 10)) {
            NBTTagCompound compoundTag = this.stackTagCompound.getCompoundTag("display");
            if (compoundTag.hasKey("color", 3)) {
               if (flag) {
                  list.add("Color: #" + Integer.toHexString(compoundTag.getInteger("color")).toUpperCase());
               } else {
                  list.add(EnumChatFormatting.ITALIC + StatCollector.translateToLocal("item.dyed"));
               }
            }

            if (compoundTag.getTagId("Lore") == 9) {
               NBTTagList tagList = compoundTag.getTagList("Lore", 8);
               if (tagList.tagCount() > 0) {
                  for (int j = 0; j < tagList.tagCount(); j++) {
                     list.add(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.ITALIC + tagList.getStringTagAt(j));
                  }
               }
            }
         }
      }

      Multimap attributeModifiers = this.getAttributeModifiers();
      if (!attributeModifiers.isEmpty() && (n & 2) == 0) {
         list.add("");

         for (Entry entry : (Iterable<Entry>)(attributeModifiers.entries())) {
            AttributeModifier value = (AttributeModifier)entry.getValue();
            double amount = value.getAmount();
            if (value.getID() == Item.itemModifierUUID) {
               amount += (double)EnchantmentHelper.getModifierForCreature(this, EnumCreatureAttribute.UNDEFINED);
            }

            double d;
            if (value.getOperation() != 1 && value.getOperation() != 2) {
               d = amount;
            } else {
               d = amount * 100.0;
            }

            if (amount > 0.0) {
               list.add(
                  EnumChatFormatting.BLUE
                     + StatCollector.translateToLocalFormatted(
                        "attribute.modifier.plus." + value.getOperation(),
                        new Object[]{DECIMALFORMAT.format(d), StatCollector.translateToLocal("attribute.name." + (String)entry.getKey())}
                     )
               );
            } else if (amount < 0.0) {
               d *= -1.0;
               list.add(
                  EnumChatFormatting.RED
                     + StatCollector.translateToLocalFormatted(
                        "attribute.modifier.take." + value.getOperation(),
                        new Object[]{DECIMALFORMAT.format(d), StatCollector.translateToLocal("attribute.name." + (String)entry.getKey())}
                     )
               );
            }
         }
      }

      if (this.hasTagCompound() && this.getTagCompound().getBoolean("Unbreakable") && (n & 4) == 0) {
         list.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("item.unbreakable"));
      }

      if (this.hasTagCompound() && this.stackTagCompound.hasKey("CanDestroy", 9) && (n & 8) == 0) {
         NBTTagList tagList2 = this.stackTagCompound.getTagList("CanDestroy", 8);
         if (tagList2.tagCount() > 0) {
            list.add("");
            list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("item.canBreak"));

            for (int k = 0; k < tagList2.tagCount(); k++) {
               Block blockFromName = Block.getBlockFromName(tagList2.getStringTagAt(k));
               if (blockFromName != null) {
                  list.add(EnumChatFormatting.DARK_GRAY + blockFromName.getLocalizedName());
               } else {
                  list.add(EnumChatFormatting.DARK_GRAY + "missingno");
               }
            }
         }
      }

      if (this.hasTagCompound() && this.stackTagCompound.hasKey("CanPlaceOn", 9) && (n & 16) == 0) {
         NBTTagList tagList3 = this.stackTagCompound.getTagList("CanPlaceOn", 8);
         if (tagList3.tagCount() > 0) {
            list.add("");
            list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("item.canPlace"));

            for (int m = 0; m < tagList3.tagCount(); m++) {
               Block blockFromName2 = Block.getBlockFromName(tagList3.getStringTagAt(m));
               if (blockFromName2 != null) {
                  list.add(EnumChatFormatting.DARK_GRAY + blockFromName2.getLocalizedName());
               } else {
                  list.add(EnumChatFormatting.DARK_GRAY + "missingno");
               }
            }
         }
      }

      if (flag) {
         if (this.isItemDamaged()) {
            list.add("Durability: " + (this.getMaxDamage() - this.getItemDamage()) + " / " + this.getMaxDamage());
         }

         list.add(EnumChatFormatting.DARK_GRAY + ((ResourceLocation)Item.itemRegistry.getNameForObject(this.item)).toString());
         if (this.hasTagCompound()) {
            list.add(EnumChatFormatting.DARK_GRAY + "NBT: " + this.getTagCompound().getKeySet().size() + " tag(s)");
         }
      }

      return list;
   }

   public void setItem(Item item2) {
      this.item = item2;
   }

   public int getMetadata() {
      return this.itemDamage;
   }

   public int getMaxItemUseDuration() {
      return this.getItem().getMaxItemUseDuration(this);
   }

   public ItemStack onItemUseFinish(World world, EntityPlayer player) {
      return this.getItem().onItemUseFinish(this, world, player);
   }

   public boolean hasEffect() {
      return this.getItem().hasEffect(this);
   }

   public void setSlotIndex(int n) {
      this.slotIndex = n;
   }

   public boolean isOnItemFrame() {
      return this.itemFrame != null;
   }

   public ItemStack copy() {
      ItemStack stack = new ItemStack(this.item, this.stackSize, this.itemDamage);
      if (this.stackTagCompound != null) {
         stack.stackTagCompound = (NBTTagCompound)this.stackTagCompound.copy();
      }

      return stack;
   }

   public boolean onItemUse(EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      boolean flag = this.getItem().onItemUse(this, player, world, pos, facing, f, f2, f3);
      if (flag) {
         player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this.item)]);
      }

      return flag;
   }

   public int getCachedItemHash() {
      return this.cachedItemHash;
   }

   public static boolean areItemStacksEqual(ItemStack stack, ItemStack stack2) {
      return stack == null && stack2 == null ? true : (stack != null && stack2 != null ? stack.isItemStackEqual(stack2) : false);
   }

   public boolean canDestroy(Block block) {
      if (block == this.canDestroyCacheBlock) {
         return this.canDestroyCacheResult;
      } else {
         this.canDestroyCacheBlock = block;
         if (this.hasTagCompound() && this.stackTagCompound.hasKey("CanDestroy", 9)) {
            NBTTagList tagList = this.stackTagCompound.getTagList("CanDestroy", 8);

            for (int i = 0; i < tagList.tagCount(); i++) {
               if (Block.getBlockFromName(tagList.getStringTagAt(i)) == block) {
                  this.canDestroyCacheResult = true;
                  return true;
               }
            }
         }

         this.canDestroyCacheResult = false;
         return false;
      }
   }

   public ItemStack(Block block, int n) {
      this(block, n, 0);
   }

   public void setTagCompound(NBTTagCompound tag) {
      this.cachedDisplayName = null;
      this.stackTagCompound = tag;
   }

   private ItemStack() {
      this.canDestroyCacheBlock = null;
      this.canDestroyCacheResult = false;
      this.canPlaceOnCacheBlock = null;
      this.canPlaceOnCacheResult = false;
   }

   public void setCachedSkullTexture(ResourceLocation location) {
      this.cachedSkullTexture = location;
   }

   public void setItemDamage(int n) {
      this.itemDamage = n;
      if (this.itemDamage < 0) {
         this.itemDamage = 0;
      }

      this.updateItemHash();
   }

   public boolean canPlaceOn(Block block) {
      if (block == this.canPlaceOnCacheBlock) {
         return this.canPlaceOnCacheResult;
      } else {
         this.canPlaceOnCacheBlock = block;
         if (this.hasTagCompound() && this.stackTagCompound.hasKey("CanPlaceOn", 9)) {
            NBTTagList tagList = this.stackTagCompound.getTagList("CanPlaceOn", 8);

            for (int i = 0; i < tagList.tagCount(); i++) {
               if (Block.getBlockFromName(tagList.getStringTagAt(i)) == block) {
                  this.canPlaceOnCacheResult = true;
                  return true;
               }
            }
         }

         this.canPlaceOnCacheResult = false;
         return false;
      }
   }

   public int getMaxStackSize() {
      return this.getItem().getItemStackLimit();
   }

   public boolean isItemEnchanted() {
      return this.stackTagCompound != null && this.stackTagCompound.hasKey("ench", 9);
   }

   public ResourceLocation getCachedSkullTexture() {
      return this.cachedSkullTexture;
   }

   public boolean isItemEqual(ItemStack stack) {
      return stack != null && this.item == stack.item && this.itemDamage == stack.itemDamage;
   }

   public String getDisplayName() {
      if (this.cachedDisplayName != null) {
         return this.cachedDisplayName;
      } else {
         this.cachedDisplayName = this.getItem().getItemStackDisplayName(this);
         if (this.stackTagCompound != null && this.stackTagCompound.hasKey("display", 10)) {
            NBTTagCompound compoundTag = this.stackTagCompound.getCompoundTag("display");
            if (compoundTag.hasKey("Name", 8)) {
               this.cachedDisplayName = compoundTag.getString("Name");
            }
         }

         return this.cachedDisplayName;
      }
   }

   public boolean canHarvestBlock(Block block) {
      return this.item.canHarvestBlock(block);
   }

   public boolean isItemEnchantable() {
      return !this.getItem().isItemTool(this) ? false : !this.isItemEnchanted();
   }

   public boolean isStackable() {
      return this.getMaxStackSize() > 1 && (!this.isItemStackDamageable() || !this.isItemDamaged());
   }

   public boolean interactWithEntity(EntityPlayer player, EntityLivingBase entity) {
      return this.item.itemInteractionForEntity(this, player, entity);
   }

   public GameProfile getCachedSkullProfile() {
      return this.cachedSkullProfile;
   }

   public int getRepairCost() {
      return this.hasTagCompound() && this.stackTagCompound.hasKey("RepairCost", 3) ? this.stackTagCompound.getInteger("RepairCost") : 0;
   }

   public float getStrVsBlock(Block block) {
      return this.getItem().getStrVsBlock(this, block);
   }

   public ItemStack setStackDisplayName(String str) {
      this.cachedDisplayName = null;
      if (this.stackTagCompound == null) {
         this.stackTagCompound = new NBTTagCompound();
      }

      if (!this.stackTagCompound.hasKey("display", 10)) {
         this.stackTagCompound.setTag("display", new NBTTagCompound());
      }

      this.stackTagCompound.getCompoundTag("display").setString("Name", str);
      return this;
   }

   public boolean attemptDamageItem(int n, Random rand) {
      if (!this.isItemStackDamageable()) {
         return false;
      } else {
         if (n > 0) {
            int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, this);
            int n2 = 0;

            for (int i = 0; enchantmentLevel > 0 && i < n; i++) {
               if (EnchantmentDurability.negateDamage(this, enchantmentLevel, rand)) {
                  n2++;
               }
            }

            n -= n2;
            if (n <= 0) {
               return false;
            }
         }

         this.itemDamage += n;
         return this.itemDamage > this.getMaxDamage();
      }
   }

   public boolean getHasSubtypes() {
      return this.item.getHasSubtypes();
   }

   public boolean isItemDamaged() {
      return this.isItemStackDamageable() && this.itemDamage > 0;
   }

   public NBTTagCompound writeToNBT(NBTTagCompound tag) {
      ResourceLocation nameForObject = (ResourceLocation)Item.itemRegistry.getNameForObject(this.item);
      tag.setString("id", nameForObject == null ? "minecraft:air" : nameForObject.toString());
      tag.setByte("Count", (byte)this.stackSize);
      tag.setShort("Damage", (short)this.itemDamage);
      if (this.stackTagCompound != null) {
         tag.setTag("tag", this.stackTagCompound);
      }

      return tag;
   }

   public void setItemFrame(EntityItemFrame entityItemFrame) {
      this.itemFrame = entityItemFrame;
   }

   public int getItemDamage() {
      return this.itemDamage;
   }

   public boolean canEditBlocks() {
      return this.getItem().canItemEditBlocks();
   }

   public void setRepairCost(int n) {
      if (!this.hasTagCompound()) {
         this.stackTagCompound = new NBTTagCompound();
      }

      this.stackTagCompound.setInteger("RepairCost", n);
   }

   public EntityItemFrame getItemFrame() {
      return this.itemFrame;
   }

   public ItemStack(Block block, int n, int n2) {
      this(Item.getItemFromBlock(block), n, n2);
   }

   public IChatComponent getChatComponent() {
      ChatComponentText chatComponentText = new ChatComponentText(this.getDisplayName());
      if (this.hasDisplayName()) {
         chatComponentText.getChatStyle().setItalic(Boolean.TRUE);
      }

      IChatComponent component = new ChatComponentText("[").appendSibling(chatComponentText).appendText("]");
      if (this.item != null) {
         NBTTagCompound tag = new NBTTagCompound();
         this.writeToNBT(tag);
         component.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new ChatComponentText(tag.toString())));
         component.getChatStyle().setColor(this.getRarity().rarityColor);
      }

      return component;
   }

   public ItemStack(Item item2) {
      this(item2, 1);
   }

   public Multimap getAttributeModifiers() {
      Multimap multimap;
      if (this.hasTagCompound() && this.stackTagCompound.hasKey("AttributeModifiers", 9)) {
         multimap = HashMultimap.create();
         NBTTagList tagList = this.stackTagCompound.getTagList("AttributeModifiers", 10);

         for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
            AttributeModifier attributeModifier = SharedMonsterAttributes.readAttributeModifierFromNBT(compoundTagAt);
            if (attributeModifier != null && attributeModifier.getID().getLeastSignificantBits() != 0L && attributeModifier.getID().getMostSignificantBits() != 0L) {
               multimap.put(compoundTagAt.getString("AttributeName"), attributeModifier);
            }
         }
      } else {
         multimap = this.getItem().getItemAttributeModifiers();
      }

      return (Multimap)multimap;
   }

   public void onBlockDestroyed(World world, Block block, BlockPos pos, EntityPlayer player) {
      if (this.item.onBlockDestroyed(this, world, block, pos, player)) {
         player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this.item)]);
      }
   }

   public static ItemStack loadItemStackFromNBT(NBTTagCompound tag) {
      ItemStack stack = new ItemStack();
      stack.readFromNBT(tag);
      return stack.getItem() != null ? stack : null;
   }

   @Override
   public String toString() {
      return this.stackSize + "x" + this.item.getUnlocalizedName() + "@" + this.itemDamage;
   }

   public ItemStack splitStack(int n) {
      ItemStack stack = new ItemStack(this.item, n, this.itemDamage);
      if (this.stackTagCompound != null) {
         stack.stackTagCompound = (NBTTagCompound)this.stackTagCompound.copy();
      }

      this.stackSize -= n;
      return stack;
   }
}
