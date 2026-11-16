package net.minecraft.item;

import client.setting.ClientSettingsBlackType;
import client.setting.ClientSettingsShowTextType;
import client.setting.DisablerUndefinedType;
import client.setting.ItemNoneType;
import client.setting.ItemStackCommonType;
import client.util.AuxiliaryEnchantmentManagerAdapter;
import client.util.EnchantmentManager;
import client.util.HoverEventActionManager;
import client.util.InvalidOperationManager;
import client.util.LISTManager;
import client.util.LvlManager;
import client.util.StatCraftItemManager;
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
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItemframe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public final class ItemStack {
   public static final DecimalFormat decimalFormat = new DecimalFormat("#.###");
   public int stackSize;
   public int animationsToGo;
   public int itemDamage;
   public Item item;
   public NBTTagCompound stackTagCompound;
   public int indexOffset;
   public String text = null;
   public EntityItemframe entityItemframe;
   public Block block;
   public boolean state;
   public Block block2;
   public boolean state2;
   public int indexOffset1;
   public GameProfile gameProfile = null;
   public ResourceLocation resourceLocation = null;

   public int getStackSize() {
      return this.stackSize;
   }

   public boolean matchesBlockByBlock(Block block3) {
      if (block3 == this.block) {
         return this.state;
      } else {
         this.block = block3;
         if (this.canExecute() && this.stackTagCompound.matchesText("CanDestroy", 9)) {
            NBTTagList extendedLoggerEntry = this.stackTagCompound.getExtendedLoggerEntry("CanDestroy", 8);

            for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
               if (Block.getBlockFromName(extendedLoggerEntry.resolveText(index)) == block3) {
                  this.state = true;
                  return true;
               }
            }
         }

         this.state = false;
         return false;
      }
   }

   public static ItemStack resolveItemStack(ItemStack stack) {
      return stack == null ? null : stack.getItemStack();
   }

   public boolean isActive3() {
      return this.item == null
         ? false
         : (this.item.getIndexOffset2() <= 0 ? false : !this.canExecute() || !this.getStackTagCompound().matchesText("Unbreakable"));
   }

   public void applyIndexByIndex(int count) {
      this.indexOffset = count;
      if (this.indexOffset < 0) {
         this.indexOffset = 0;
      }

      this.updateState2();
   }

   public List resolveList(EntityPlayer player, boolean flag) {
      ArrayList list = Lists.newArrayList();
      String string = this.getString();
      if (this.isActive()) {
         string = ClientSettingsBlackType.ITALIC + string;
      }

      String text2 = string + ClientSettingsBlackType.RESET;
      if (flag) {
         String text3 = "";
         if (text2.length() > 0) {
            text2 = text2 + " (";
            text3 = ")";
         }

         int index = Item.getIndex(this.item);
         if (this.processAction2()) {
            text2 = text2 + String.format("#%04d/%d%s", index, this.indexOffset, text3);
         } else {
            text2 = text2 + String.format("#%04d%s", index, text3);
         }
      } else if (!this.isActive() && this.item == Items.filledMap) {
         text2 = text2 + " #" + this.indexOffset;
      }

      list.add(text2);
      int count = 0;
      if (this.canExecute() && this.stackTagCompound.matchesText("HideFlags", 99)) {
         count = this.stackTagCompound.computeCount("HideFlags");
      }

      if ((count & 32) == 0) {
         this.item.processAction(this, player, list, flag);
      }

      if (this.canExecute()) {
         if ((count & 1) == 0) {
            NBTTagList extendedLoggerEntry = this.getExtendedLoggerEntry();
            if (extendedLoggerEntry != null) {
               for (int index2 = 0; index2 < extendedLoggerEntry.getInt(); index2++) {
                  short shortValue = extendedLoggerEntry.getNBTTagCompound(index2).processValue("id");
                  short shortValue2 = extendedLoggerEntry.getNBTTagCompound(index2).processValue("lvl");
                  if (EnchantmentManager.resolveEnchantmentManager(shortValue) != null) {
                     list.add(EnchantmentManager.resolveEnchantmentManager(shortValue).resolveText(shortValue2));
                  }
               }
            }
         }

         if (this.stackTagCompound.matchesText("display", 10)) {
            NBTTagCompound nBTTagCompound = this.stackTagCompound.getNBTTagCompound("display");
            if (nBTTagCompound.matchesText("color", 3)) {
               if (flag) {
                  list.add("Color: #" + Integer.toHexString(nBTTagCompound.computeCount("color")).toUpperCase());
               } else {
                  list.add(ClientSettingsBlackType.ITALIC + I18n.getString2("item.dyed"));
               }
            }

            if (nBTTagCompound.computeByteCodeByText("Lore") == 9) {
               NBTTagList extendedLoggerEntry2 = nBTTagCompound.getExtendedLoggerEntry("Lore", 8);
               if (extendedLoggerEntry2.getInt() > 0) {
                  for (int index3 = 0; index3 < extendedLoggerEntry2.getInt(); index3++) {
                     list.add(ClientSettingsBlackType.DARK_PURPLE + "" + ClientSettingsBlackType.ITALIC + extendedLoggerEntry2.resolveText(index3));
                  }
               }
            }
         }
      }

      Multimap multimap = this.getMultimap();
      if (!multimap.isEmpty() && (count & 2) == 0) {
         list.add("");

         for (Entry entry : (Iterable<Entry>) (Iterable) (multimap.entries())) {
            InvalidOperationManager value = (InvalidOperationManager)entry.getValue();
            double value2 = value.getValue();
            if (value.getUUID() == Item.uuid) {
               value2 += (double)LvlManager.getScaleFactor(this, DisablerUndefinedType.UNDEFINED);
            }

            double offset;
            if (value.getCount() != 1 && value.getCount() != 2) {
               offset = value2;
            } else {
               offset = value2 * 100.0;
            }

            if (value2 > 0.0) {
               list.add(
                  ClientSettingsBlackType.BLUE
                     + I18n.resolveText(
                        "attribute.modifier.plus." + value.getCount(),
                        new Object[]{decimalFormat.format(offset), I18n.getString2("attribute.name." + (String)entry.getKey())}
                     )
               );
            } else if (value2 < 0.0) {
               double offset2 = offset * -1.0;
               list.add(
                  ClientSettingsBlackType.RED
                     + I18n.resolveText(
                        "attribute.modifier.take." + value.getCount(),
                        new Object[]{decimalFormat.format(offset2), I18n.getString2("attribute.name." + (String)entry.getKey())}
                     )
               );
            }
         }
      }

      if (this.canExecute() && this.getStackTagCompound().matchesText("Unbreakable") && (count & 4) == 0) {
         list.add(ClientSettingsBlackType.BLUE + I18n.getString2("item.unbreakable"));
      }

      if (this.canExecute() && this.stackTagCompound.matchesText("CanDestroy", 9) && (count & 8) == 0) {
         NBTTagList extendedLoggerEntry3 = this.stackTagCompound.getExtendedLoggerEntry("CanDestroy", 8);
         if (extendedLoggerEntry3.getInt() > 0) {
            list.add("");
            list.add(ClientSettingsBlackType.GRAY + I18n.getString2("item.canBreak"));

            for (int index4 = 0; index4 < extendedLoggerEntry3.getInt(); index4++) {
               Block blockFromName = Block.getBlockFromName(extendedLoggerEntry3.resolveText(index4));
               if (blockFromName != null) {
                  list.add(ClientSettingsBlackType.DARK_GRAY + blockFromName.getText());
               } else {
                  list.add(ClientSettingsBlackType.DARK_GRAY + "missingno");
               }
            }
         }
      }

      if (this.canExecute() && this.stackTagCompound.matchesText("CanPlaceOn", 9) && (count & 16) == 0) {
         NBTTagList extendedLoggerEntry4 = this.stackTagCompound.getExtendedLoggerEntry("CanPlaceOn", 8);
         if (extendedLoggerEntry4.getInt() > 0) {
            list.add("");
            list.add(ClientSettingsBlackType.GRAY + I18n.getString2("item.canPlace"));

            for (int index5 = 0; index5 < extendedLoggerEntry4.getInt(); index5++) {
               Block blockFromName2 = Block.getBlockFromName(extendedLoggerEntry4.resolveText(index5));
               if (blockFromName2 != null) {
                  list.add(ClientSettingsBlackType.DARK_GRAY + blockFromName2.getText());
               } else {
                  list.add(ClientSettingsBlackType.DARK_GRAY + "missingno");
               }
            }
         }
      }

      if (flag) {
         if (this.isActiveAsFlag()) {
            list.add("Durability: " + (this.processAction() - this.getIndexOffset2()) + " / " + this.processAction());
         }

         list.add(ClientSettingsBlackType.DARK_GRAY + ((ResourceLocation)Item.iterableExtensionAdapter.getObject2(this.item)).toString());
         if (this.canExecute()) {
            list.add(ClientSettingsBlackType.DARK_GRAY + "NBT: " + this.getStackTagCompound().keySet().size() + " tag(s)");
         }
      }

      return list;
   }

   public float processAction(Block block3) {
      return this.getItem().processAction(this, block3);
   }

   public boolean matchesBlock(Block block3) {
      if (block3 == this.block2) {
         return this.state2;
      } else {
         this.block2 = block3;
         if (this.canExecute() && this.stackTagCompound.matchesText("CanPlaceOn", 9)) {
            NBTTagList extendedLoggerEntry = this.stackTagCompound.getExtendedLoggerEntry("CanPlaceOn", 8);

            for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
               if (Block.getBlockFromName(extendedLoggerEntry.resolveText(index)) == block3) {
                  this.state2 = true;
                  return true;
               }
            }
         }

         this.state2 = false;
         return false;
      }
   }

   public ItemStack getItemStack() {
      ItemStack stack = new ItemStack(this.item, this.animationsToGo, this.indexOffset);
      if (this.stackTagCompound != null) {
         stack.stackTagCompound = (NBTTagCompound)this.stackTagCompound.getLISTManager();
      }

      return stack;
   }

   public void applyEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity, EntityPlayer player) {
      if (this.item.processAction(this, livingEntity, player)) {
         player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this.item)]);
      }
   }

   public boolean isActive() {
      return this.stackTagCompound != null
         && this.stackTagCompound.matchesText("display", 10)
         && this.stackTagCompound.getNBTTagCompound("display").matchesText("Name", 8);
   }

   public int processAction() {
      return this.item.getIndexOffset2();
   }

   public NBTTagCompound resolveNBTTagCompound(String text2, boolean flag) {
      if (this.stackTagCompound != null && this.stackTagCompound.matchesText(text2, 10)) {
         return this.stackTagCompound.getNBTTagCompound(text2);
      } else if (flag) {
         NBTTagCompound tagCompound = new NBTTagCompound();
         this.applyText(text2, tagCompound);
         return tagCompound;
      } else {
         return null;
      }
   }

   public static boolean matchesItemStack(ItemStack stack, ItemStack stack2) {
      return stack == null && stack2 == null ? true : (stack != null && stack2 != null ? stack.checkItemStack5(stack2) : false);
   }

   public boolean processAction2() {
      return this.item.isState();
   }

   public NBTTagList getExtendedLoggerEntry() {
      return this.stackTagCompound == null ? null : this.stackTagCompound.getExtendedLoggerEntry("ench", 10);
   }

   public void processAction(World world, EntityPlayer player, int count) {
      this.getItem().processAction(this, world, player, count);
   }

   public boolean matchesRandom(int count, Random random) {
      int count2 = count;
      if (!this.isActive3()) {
         return false;
      } else {
         if (count > 0) {
            int index = LvlManager.getIndex(EnchantmentManager.enchantmentManager1.indexOffset, this);
            int count3 = 0;

            for (int index2 = 0; index > 0 && index2 < count2; index2++) {
               if (AuxiliaryEnchantmentManagerAdapter.matchesItemStack(this, index, random)) {
                  count3++;
               }
            }

            count2 -= count3;
            if (count2 <= 0) {
               return false;
            }
         }

         this.indexOffset += count2;
         return this.indexOffset > this.processAction();
      }
   }

   public boolean processAction3() {
      return this.getItem().processAction2(this);
   }

   public int getIndexOffset() {
      return this.indexOffset;
   }

   public ItemNoneType processAction4() {
      return this.getItem().processAction4(this);
   }

   public void applyIndex(int count) {
      if (!this.canExecute()) {
         this.stackTagCompound = new NBTTagCompound();
      }

      this.stackTagCompound.put("RepairCost", count);
   }

   public GameProfile getGameProfile() {
      return this.gameProfile;
   }

   public EntityItemframe getEntityItemframe() {
      return this.entityItemframe;
   }

   public void processAction2(World world, EntityPlayer player, int count) {
      player.updateDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManagerArray[Item.getIndex(this.item)], count);
      this.item.processAction(this, world, player);
   }

   public String getString() {
      if (this.text != null) {
         return this.text;
      } else {
         this.text = this.getItem().resolveText(this);
         if (this.stackTagCompound != null && this.stackTagCompound.matchesText("display", 10)) {
            NBTTagCompound nBTTagCompound = this.stackTagCompound.getNBTTagCompound("display");
            if (nBTTagCompound.matchesText("Name", 8)) {
               this.text = nBTTagCompound.resolveText("Name");
            }
         }

         return this.text;
      }
   }

   public int processAction5() {
      return this.getItem().getIndexOffset1();
   }

   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      if (tagCompound.matchesText("id", 8)) {
         this.item = Item.getItem(tagCompound.resolveText("id"));
      } else {
         this.item = Item.processAction(tagCompound.processValue("id"));
      }

      this.animationsToGo = tagCompound.computeByteCode("Count");
      this.indexOffset = tagCompound.processValue("Damage");
      if (this.indexOffset < 0) {
         this.indexOffset = 0;
      }

      if (tagCompound.matchesText("tag", 10)) {
         this.stackTagCompound = tagCompound.getNBTTagCompound("tag");
         if (this.item != null) {
            this.item.processAction(this.stackTagCompound);
         }
      }
   }

   public void setItem(Item item2) {
      this.item = item2;
   }

   public static boolean matchesItemStack2(ItemStack stack, ItemStack stack2) {
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

   public void updateState() {
      this.text = null;
      if (this.stackTagCompound != null && this.stackTagCompound.matchesText("display", 10)) {
         NBTTagCompound nBTTagCompound = this.stackTagCompound.getNBTTagCompound("display");
         nBTTagCompound.remove("Name");
         if (nBTTagCompound.isEmpty()) {
            this.stackTagCompound.remove("display");
            if (this.stackTagCompound.isEmpty()) {
               this.processAction((NBTTagCompound)null);
            }
         }
      }
   }

   public boolean isActive2() {
      return !this.getItem().matchesItemStack(this) ? false : !this.canExecute3();
   }

   public boolean processAction(EntityPlayer player, EntityLivingBase livingEntity) {
      return this.item.processAction(this, player, livingEntity);
   }

   public ItemStack resolveItemStack(String text2) {
      this.text = null;
      if (this.stackTagCompound == null) {
         this.stackTagCompound = new NBTTagCompound();
      }

      if (!this.stackTagCompound.matchesText("display", 10)) {
         this.stackTagCompound.applyText("display", new NBTTagCompound());
      }

      this.stackTagCompound.getNBTTagCompound("display").put("Name", text2);
      return this;
   }

   public boolean isActive4() {
      return this.entityItemframe != null;
   }

   public NBTTagCompound resolveNBTTagCompound(NBTTagCompound tagCompound) {
      ResourceLocation object = (ResourceLocation)Item.iterableExtensionAdapter.getObject2(this.item);
      tagCompound.put("id", object == null ? "minecraft:air" : object.toString());
      tagCompound.put("Count", (byte)this.animationsToGo);
      tagCompound.put("Damage", (short)this.indexOffset);
      if (this.stackTagCompound != null) {
         tagCompound.applyText("tag", this.stackTagCompound);
      }

      return tagCompound;
   }

   public int processAction6() {
      return this.getItem().processAction(this);
   }

   public void processAction(NBTTagCompound tagCompound) {
      this.text = null;
      this.stackTagCompound = tagCompound;
   }

   public void applyText(String text2, LISTManager lISTManager) {
      if (this.stackTagCompound == null) {
         this.processAction(new NBTTagCompound());
      }

      this.stackTagCompound.applyText(text2, lISTManager);
   }

   public void applyWorldByWorld(World world, Block block3, BlockPos blockPos, EntityPlayer player) {
      if (this.item.processAction(this, world, block3, blockPos, player)) {
         player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this.item)]);
      }
   }

   private void updateState2() {
      this.indexOffset1 = this.item == null ? -1 : this.item.hashCode() + this.indexOffset;
   }

   public Item getItem() {
      return this.item;
   }

   public void setStackSize(int stackSize2) {
      this.stackSize = stackSize2;
   }

   public boolean processAction2(Block block3) {
      return this.item.processAction(block3);
   }

   public NBTTagCompound getStackTagCompound() {
      return this.stackTagCompound;
   }

   public boolean processAction(ItemStack stack) {
      return this.checkItemStack5(stack);
   }

   public void applyEntityLivingBaseByEntityLivingBase(int count, EntityLivingBase livingEntity) {
      if ((!(livingEntity instanceof EntityPlayer) || !((EntityPlayer)livingEntity).invulnerableManager.enabled2)
         && this.isActive3()
         && this.matchesRandom(count, livingEntity.getRandom())) {
         livingEntity.applyItemStackByItemStack(this);
         this.animationsToGo--;
         if (livingEntity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)livingEntity;
            player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray3[Item.getIndex(this.item)]);
            if (this.animationsToGo == 0 && this.getItem() instanceof ItemBow) {
               player.updateState45();
            }
         }

         if (this.animationsToGo < 0) {
            this.animationsToGo = 0;
         }

         this.indexOffset = 0;
      }
   }

   public boolean canExecute() {
      return this.stackTagCompound != null;
   }

   public int getCount() {
      return this.canExecute() && this.stackTagCompound.matchesText("RepairCost", 3) ? this.stackTagCompound.computeCount("RepairCost") : 0;
   }

   public IChatComponent getIChatComponent() {
      ChatComponentText chatComponentText = new ChatComponentText(this.getString());
      if (this.isActive()) {
         chatComponentText.getStyleHasParentManager().withMemberRef5(Boolean.TRUE);
      }

      IChatComponent iChatComponent = new ChatComponentText("[").appendChatComponent(chatComponentText).resolveIChatComponent("]");
      if (this.item != null) {
         NBTTagCompound tagCompound = new NBTTagCompound();
         this.resolveNBTTagCompound(tagCompound);
         iChatComponent.getStyleHasParentManager()
            .getStyleHasParentManager(new HoverEventActionManager(ClientSettingsShowTextType.SHOW_ITEM, new ChatComponentText(tagCompound.toString())));
         iChatComponent.getStyleHasParentManager().withClientSettingsBlackType(this.processAction8().rarityColor);
      }

      return iChatComponent;
   }

   public boolean matchesItemStack(ItemStack stack) {
      return stack != null && this.item == stack.item && this.indexOffset == stack.indexOffset;
   }

   public String processAction7() {
      return this.item.getText(this);
   }

   public void setGameProfile(GameProfile gameProfile2) {
      this.gameProfile = gameProfile2;
   }

   public void applyEnchantmentManager(EnchantmentManager enchantmentManager, int count) {
      if (this.stackTagCompound == null) {
         this.processAction(new NBTTagCompound());
      }

      if (!this.stackTagCompound.matchesText("ench", 9)) {
         this.stackTagCompound.applyText("ench", new NBTTagList());
      }

      NBTTagList extendedLoggerEntry = this.stackTagCompound.getExtendedLoggerEntry("ench", 10);
      NBTTagCompound tagCompound = new NBTTagCompound();
      tagCompound.put("id", (short)enchantmentManager.indexOffset);
      tagCompound.put("lvl", (short)((byte)count));
      extendedLoggerEntry.applyLISTManager(tagCompound);
   }

   public ItemStackCommonType processAction8() {
      return this.getItem().getItemStackCommonType(this);
   }

   public ResourceLocation getResourceLocation() {
      return this.resourceLocation;
   }

   public Multimap getMultimap() {
      Multimap entry;
      if (this.canExecute() && this.stackTagCompound.matchesText("AttributeModifiers", 9)) {
         entry = HashMultimap.create();
         NBTTagList extendedLoggerEntry = this.stackTagCompound.getExtendedLoggerEntry("AttributeModifiers", 10);

         for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
            NBTTagCompound nBTTagCompound = extendedLoggerEntry.getNBTTagCompound(index);
            InvalidOperationManager invalidOperationManager = SharedMonsterAttributes.resolveInvalidOperationManager(nBTTagCompound);
            if (invalidOperationManager != null && invalidOperationManager.getUUID().getLeastSignificantBits() != 0L && invalidOperationManager.getUUID().getMostSignificantBits() != 0L) {
               entry.put(nBTTagCompound.resolveText("AttributeName"), invalidOperationManager);
            }
         }
      } else {
         entry = this.getItem().processAction5();
      }

      return (Multimap)entry;
   }

   public boolean processAction9() {
      return this.getItem().processAction7();
   }

   public void setResourceLocation(ResourceLocation resourceLocation2) {
      this.resourceLocation = resourceLocation2;
   }

   public int getIndexOffset1() {
      return this.indexOffset1;
   }

   public void setEntityItemframe(EntityItemframe entityItemframe2) {
      this.entityItemframe = entityItemframe2;
   }

   public boolean canExecute2() {
      return this.processAction5() > 1 && (!this.isActive3() || !this.isActiveAsFlag());
   }

   public ItemStack resolveItemStack(int count) {
      ItemStack stack = new ItemStack(this.item, count, this.indexOffset);
      if (this.stackTagCompound != null) {
         stack.stackTagCompound = (NBTTagCompound)this.stackTagCompound.getLISTManager();
      }

      this.animationsToGo -= count;
      return stack;
   }

   public ItemStack processAction(World world, EntityPlayer player) {
      return this.getItem().processAction2(this, world, player);
   }

   private boolean checkItemStack5(ItemStack stack) {
      return this.animationsToGo != stack.animationsToGo
         ? false
         : (
            this.item != stack.item
               ? false
               : (
                  this.indexOffset != stack.indexOffset
                     ? false
                     : (
                        this.stackTagCompound == null && stack.stackTagCompound != null
                           ? false
                           : this.stackTagCompound == null || this.stackTagCompound.equals(stack.stackTagCompound)
                     )
               )
         );
   }

   public static ItemStack resolveItemStack(NBTTagCompound tagCompound) {
      ItemStack stack = new ItemStack();
      stack.applyNBTTagCompound(tagCompound);
      return stack.getItem() != null ? stack : null;
   }

   public boolean matchesEntityPlayerByEntityPlayer(EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      boolean flag = this.getItem().processAction(this, player, world, blockPos, enumFacing, posX, posY, posZ);
      if (flag) {
         player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this.item)]);
      }

      return flag;
   }

   public ItemStack processAction2(World world, EntityPlayer player) {
      return this.getItem().processAction3(this, world, player);
   }

   public void applyWorld(World world, Entity entity, int count, boolean flag) {
      if (this.itemDamage > 0) {
         this.itemDamage--;
      }

      this.item.processAction(this, world, entity, count, flag);
   }

   public boolean isActiveAsFlag() {
      return this.isActive3() && this.indexOffset > 0;
   }

   public int getIndexOffset2() {
      return this.indexOffset;
   }

   public static boolean matchesItemStackByItemStack(ItemStack stack, ItemStack stack2) {
      return stack == null && stack2 == null ? true : (stack != null && stack2 != null ? stack.matchesItemStack(stack2) : false);
   }

   public boolean canExecute3() {
      return this.stackTagCompound != null && this.stackTagCompound.matchesText("ench", 9);
   }

   public ItemStack(Block block3, int count) {
      this(block3, count, 0);
   }

   public ItemStack(Block block3, int count, int count2) {
      this(Item.processAction2(block3), count, count2);
   }

   public ItemStack(Block block3) {
      this(block3, 1);
   }

   public ItemStack(Item item2, int count) {
      this(item2, count, 0);
   }

   public ItemStack(Item item2) {
      this(item2, 1);
   }

   public ItemStack(Item item2, int count, int count2) {
      this.block = null;
      this.state = false;
      this.block2 = null;
      this.state2 = false;
      this.item = item2;
      this.animationsToGo = count;
      this.indexOffset = count2;
      if (this.indexOffset < 0) {
         this.indexOffset = 0;
      }
   }

   private ItemStack() {
      this.block = null;
      this.state = false;
      this.block2 = null;
      this.state2 = false;
   }

   @Override
   public String toString() {
      return this.animationsToGo + "x" + this.item.getString() + "@" + this.indexOffset;
   }
}
