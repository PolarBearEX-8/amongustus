package net.minecraft.entity.player;

import augustus.modes.movement.MovementContainerEnderchestMode;
import client.Client;
import client.ClientGlobals;
import client.event.AuxiliaryListener;
import client.event.EventChat;
import client.event.EventChatCallback;
import client.event.EventMoveInput;
import client.event.EventPhaseListener;
import client.event.LocalListener;
import client.event.MoveInputListener;
import client.event.PreRenderProcessor;
import client.module.Module;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.ClientSettingsBlackType;
import client.setting.ClientSettingsOpenUrlType;
import client.setting.CombatModeProperty;
import client.setting.DisablerUndefinedType;
import client.setting.GameSettingsPeacefulType;
import client.setting.ItemNoneType;
import client.setting.RenderKeybindProperty;
import client.setting.TabListCapeType;
import client.setting.WorldSettingsGameType;
import client.util.AchievementTheEnd2Manager;
import client.util.ClickEventActionManager;
import client.util.DuplicateStatIdManager;
import client.util.DuplicateStatIdManagerEntry;
import client.util.FoodLevelManager;
import client.util.HealthHandler;
import client.util.IBlockState;
import client.util.InterpolationDeserializer;
import client.util.InvulnerableManager;
import client.util.Listener63Extension;
import client.util.LvlManager;
import client.util.ScoreboardData;
import client.util.SecondaryClientManager;
import client.util.SkippingEntityWithIdManager;
import client.util.SplineSorter;
import client.util.StatCraftItemManager;
import client.util.ThePlayerNameManager;
import client.util.VanillaRunnable;
import client.util.VectorDispatcher;
import client.util.VectorFormatter;
import client.util.VectorPipeline;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.BaseBlockAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArrow;
import net.minecraft.entity.EntityBoat;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.EntityMonster;
import net.minecraft.entity.EntityPig;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.potion.Potion;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.LockCode;
import net.minecraft.world.World;

public abstract class EntityPlayer extends EntityLivingBase {
   public String text = "";
   public boolean enabled21 = false;
   public InventoryPlayer inventoryPlayer = new InventoryPlayer(this);
   public MovementContainerEnderchestMode movementContainerEnderchestMode = new MovementContainerEnderchestMode();
   public Container container;
   public Container container2;
   public FoodLevelManager foodLevelManager = new FoodLevelManager();
   public int count26;
   public float factor30;
   public float factor47;
   public int count23;
   public double value30;
   public double value28;
   public double value29;
   public double value25;
   public double value27;
   public double value26;
   public boolean state5;
   public BlockPos blockPos2;
   public int indexOffset8;
   public float factor38;
   public float factor31;
   public float factor36;
   public BlockPos blockPos3;
   public boolean state12;
   public BlockPos blockPos4;
   public InvulnerableManager invulnerableManager = new InvulnerableManager();
   public int count25;
   public int count32;
   public float factor39;
   public int indexOffset6;
   public ItemStack itemStack;
   public int entityId2;
   public float scaleFactor4 = 0.1F;
   public float scaleFactor2 = 0.02F;
   public int count24;
   public final GameProfile gameProfile;
   public boolean inWater2 = false;
   public EntityFishHook internalEntityAdapter;

   @Override
   public String getText6() {
      return "game.player.swim";
   }

   public void applyIChatComponentByIChatComponent(IChatComponent iChatComponent) {
   }

   public void applyIInventory(IInventory iInventory) {
   }

   @Override
   public SecondaryClientManager getSecondaryClientManager() {
      return this.getThePlayerNameManager().getScorePlayerTeam(this.getText());
   }

   public void applyWorldSettingsGameType(WorldSettingsGameType worldSettingsGameType) {
   }

   public void applyBlockPos(BlockPos blockPos, boolean flag) {
      if (blockPos != null) {
         this.blockPos3 = blockPos;
         this.state12 = flag;
      } else {
         this.blockPos3 = null;
         this.state12 = false;
      }
   }

   public ThePlayerNameManager getThePlayerNameManager() {
      return this.worldObj.getThePlayerNameManager();
   }

   public void applyDuplicateStatIdManagerByDuplicateStatIdM(DuplicateStatIdManager duplicateStatIdManager) {
      this.updateDuplicateStatIdManager(duplicateStatIdManager, 1);
   }

   public ItemStack getItemStack2() {
      return this.itemStack;
   }

   @Override
   public void applyEntityByEntity(Entity entity, int count) {
      this.applyIndex11(count);
      Collection collection = this.getThePlayerNameManager().resolveCollection(HealthHandler.healthHandler2);
      if (entity instanceof EntityPlayer) {
         this.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager14);
         collection.addAll(this.getThePlayerNameManager().resolveCollection(HealthHandler.healthHandler4));
         collection.addAll(this.resolveCollection(entity));
      } else {
         this.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager9);
      }

      for (ScoreboardData scoreboardData : (Iterable<ScoreboardData>)(Iterable<?>)collection) {
         this.getThePlayerNameManager().getComparatorEntry(this.getText(), scoreboardData).updateState();
      }
   }

   public boolean checkFlag(boolean flag) {
      return (flag || this.foodLevelManager.isActive()) && !this.invulnerableManager.enabled;
   }

   public float computeFactor(Block block) {
      float factor = this.inventoryPlayer.processAction(block);
      if (factor > 1.0F) {
         int count = LvlManager.computeCount11(this);
         ItemStack slot = this.inventoryPlayer.getCurrentItem();
         if (count > 0 && slot != null) {
            factor += (float)(count * count + 1);
         }
      }

      boolean flag = ClientGlobals.moduleManager.fastBreak.checkBlock2(block);
      if (this.checkPotion(Potion.potion28) || flag && ClientGlobals.moduleManager.fastBreak.getCoordinate3(block) > 0.0) {
         int count2 = flag && ClientGlobals.moduleManager.fastBreak.getCoordinate3(block) > 0.0
            ? (int)ClientGlobals.moduleManager.fastBreak.getCoordinate3(block)
            : 0;
         if (this.checkPotion(Potion.potion28)) {
            count2 += this.getPotionEffect(Potion.potion28).getIndexOffset2() + 1;
         }

         factor *= 1.0F + (float)count2 * 0.2F;
      }

      if (this.checkPotion(Potion.potion7) && (!flag || !ClientGlobals.moduleManager.fastBreak.settingIgnoreMiningFatigue.isEnabled())) {
         float factor2;
         switch (this.getPotionEffect(Potion.potion7).getIndexOffset2()) {
            case 0:
               factor2 = 0.3F;
               break;
            case 1:
               factor2 = 0.09F;
               break;
            case 2:
               factor2 = 0.0027F;
               break;
            case 3:
            default:
               factor2 = 8.1E-4F;
         }

         factor *= factor2;
      }

      if (this.matchesMaterial(Material.coreItemEntry1) && !LvlManager.matchesEntityLivingBase(this)) {
         factor /= 5.0F;
      }

      if (!this.onGround) {
         if (flag && ClientGlobals.moduleManager.fastBreak.checkBlock(block)) {
            factor *= (float)ClientGlobals.moduleManager.fastBreak.getCoordinate2(block);
         } else {
            factor /= 5.0F;
         }
      }

      return factor;
   }

   public void applyItemStackByItemStack(ItemStack stack, int count) {
      if (stack.processAction4() == ItemNoneType.DRINK) {
         this.applyText("random.drink", 0.5F, this.worldObj.random.nextFloat() * 0.1F + 0.9F);
      }

      if (stack.processAction4() == ItemNoneType.EAT) {
         for (int index = 0; index < count; index++) {
            Vec3 vec = new Vec3(((double)this.random.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0)
               .resolveVecByFloat(-this.rotationPitch * (float) Math.PI / 180.0F)
               .resolveVec3(-this.rotationYaw * (float) Math.PI / 180.0F);
            double offset = (double)(-this.random.nextFloat()) * 0.6 - 0.3;
            Vec3 vec3 = new Vec3(((double)this.random.nextFloat() - 0.5) * 0.3, offset, 0.6)
               .resolveVecByFloat(-this.rotationPitch * (float) Math.PI / 180.0F)
               .resolveVec3(-this.rotationYaw * (float) Math.PI / 180.0F)
               .getVec312(this.posX, this.posY + (double)this.getHeight2(), this.posZ);
            if (stack.processAction2()) {
               this.worldObj
                  .applyAttackEffectsExplosionNorm(
                     AttackEffectsExplosionNormalType.ITEM_CRACK,
                     vec3.xCoord,
                     vec3.yCoord,
                     vec3.zCoord,
                     vec.xCoord,
                     vec.yCoord + 0.05,
                     vec.zCoord,
                     new int[]{Item.getIndex(stack.getItem()), stack.getIndexOffset()}
                  );
            } else {
               this.worldObj
                  .applyAttackEffectsExplosionNorm(
                     AttackEffectsExplosionNormalType.ITEM_CRACK,
                     vec3.xCoord,
                     vec3.yCoord,
                     vec3.zCoord,
                     vec.xCoord,
                     vec.yCoord + 0.05,
                     vec.zCoord,
                     new int[]{Item.getIndex(stack.getItem())}
                  );
            }
         }

         this.applyText("random.eat", 0.5F + 0.5F * (float)this.random.nextInt(2), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
      }
   }

   @Override
   public int getCountAsIndex() {
      return this.invulnerableManager.enabled ? 0 : 80;
   }

   @Override
   public boolean isActiveAsFlag() {
      return !this.invulnerableManager.state;
   }

   public boolean matchesBlock(Block block) {
      return this.inventoryPlayer.checkBlock(block);
   }

   public static BlockPos resolveBlockPos(World world, BlockPos blockPos, boolean flag) {
      Block block = world.getBlockState(blockPos).getBlock();
      if (block != Blocks.bed) {
         if (!flag) {
            return null;
         } else {
            boolean activeAsFlag = block.isActiveAsFlag();
            boolean activeAsFlag2 = world.getBlockState(blockPos.up()).getBlock().isActiveAsFlag();
            return activeAsFlag && activeAsFlag2 ? blockPos : null;
         }
      } else {
         return BlockBed.resolveBlockPosByWorld(world, blockPos, 0);
      }
   }

   public void applyFlag(boolean flag, boolean flag2, boolean flag3) {
      this.applyFloat(0.6F, 1.8F);
      IBlockState blockState = this.worldObj.getBlockState(this.blockPos2);
      if (this.blockPos2 != null && blockState.getBlock() == Blocks.bed) {
         this.worldObj.matchesBlockPos(this.blockPos2, blockState.resolveIBlockState(BlockBed.internalImmutableSetEntry, Boolean.FALSE), 4);
         BlockPos blockPos = BlockBed.resolveBlockPosByWorld(this.worldObj, this.blockPos2, 0);
         if (blockPos == null) {
            blockPos = this.blockPos2.up();
         }

         this.processDouble2((double)((float)blockPos.getX() + 0.5F), (double)((float)blockPos.getY() + 0.1F), (double)((float)blockPos.getZ() + 0.5F));
      }

      this.state5 = false;
      if (!this.worldObj.state && flag2) {
         this.worldObj.updateState5();
      }

      this.indexOffset8 = flag ? 0 : 100;
      if (flag3) {
         this.applyBlockPos(this.blockPos2, false);
      }
   }

   public MovementContainerEnderchestMode getMovementContainerEnderchestMode() {
      return this.movementContainerEnderchestMode;
   }

   @Override
   public String getText9() {
      return "game.player.die";
   }

   public void applyEntityItem(EntityItem entityItem) {
      this.worldObj.matchesEntity(entityItem);
   }

   @Override
   public void applyFloat3(float factor) {
      float factor2 = factor;
      if (factor < 0.0F) {
         factor2 = 0.0F;
      }

      this.getDataWatcher().applyTargetByTarget(17, factor2);
   }

   public void applyListener63Extension(Listener63Extension listener63Extension) {
   }

   @Override
   public float getHeight2() {
      float factor = 1.62F;
      if (this.isState5()) {
         factor = 0.2F;
      }

      if (this.isSprinting()) {
         factor -= 0.08F;
      }

      return factor;
   }

   public boolean checkTabListCapeType(TabListCapeType tabListCapeType) {
      return (this.getDataWatcher().computeByteCode(10) & tabListCapeType.getCount()) == tabListCapeType.getCount();
   }

   @Override
   public int getCount4() {
      return this.inventoryPlayer.getInt6();
   }

   public GameProfile getGameProfile() {
      return this.gameProfile;
   }

   @Override
   public int nextInt(EntityPlayer player) {
      if (this.worldObj.getDoFireTickManager().matchesText("keepInventory")) {
         return 0;
      } else {
         int count = this.count25 * 7;
         return count > 100 ? 100 : count;
      }
   }

   @Override
   public void updateState21() {
      if (!this.worldObj.state && this.isSprinting()) {
         this.setEntity8((Entity)null);
         this.setEnabled2(false);
      } else {
         double posX = this.posX;
         double posY = this.posY;
         double posZ = this.posZ;
         float yaw = this.rotationYaw;
         float pitch = this.rotationPitch;
         super.updateState21();
         this.factor30 = this.factor47;
         this.factor47 = 0.0F;
         this.applyDouble5(this.posX - posX, this.posY - posY, this.posZ - posZ);
         if (this.ridingEntity instanceof EntityPig) {
            this.rotationPitch = pitch;
            this.rotationYaw = yaw;
            this.scaleFactor8 = ((EntityPig)this.ridingEntity).scaleFactor8;
         }
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.entityUniqueID = resolveUUID(this.gameProfile);
      NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Inventory", 10);
      this.inventoryPlayer.updateExtendedLoggerEntry(extendedLoggerEntry);
      this.inventoryPlayer.currentItem = tagCompound.computeCount("SelectedItemSlot");
      this.state5 = tagCompound.matchesText("Sleeping");
      this.indexOffset8 = tagCompound.processValue("SleepTimer");
      this.factor39 = tagCompound.computeFactor("XpP");
      this.count25 = tagCompound.computeCount("XpLevel");
      this.count32 = tagCompound.computeCount("XpTotal");
      this.indexOffset6 = tagCompound.computeCount("XpSeed");
      if (this.indexOffset6 == 0) {
         this.indexOffset6 = this.random.nextInt();
      }

      this.applyIndex10(tagCompound.computeCount("Score"));
      if (this.state5) {
         this.blockPos2 = new BlockPos(this);
         this.applyFlag(true, true, false);
      }

      if (tagCompound.matchesText("SpawnX", 99) && tagCompound.matchesText("SpawnY", 99) && tagCompound.matchesText("SpawnZ", 99)) {
         this.blockPos3 = new BlockPos(tagCompound.computeCount("SpawnX"), tagCompound.computeCount("SpawnY"), tagCompound.computeCount("SpawnZ"));
         this.state12 = tagCompound.matchesText("SpawnForced");
      }

      this.foodLevelManager.applyNBTTagCompound(tagCompound);
      this.invulnerableManager.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      if (tagCompound.matchesText("EnderItems", 9)) {
         NBTTagList extendedLoggerEntry2 = tagCompound.getExtendedLoggerEntry("EnderItems", 10);
         this.movementContainerEnderchestMode.applyNBTTagList(extendedLoggerEntry2);
      }
   }

   @Override
   public void applyText(String text2, float factor, float factor2) {
      this.worldObj.applyEntityPlayerByEntityPlayer(this, text2, factor, factor2);
   }

   @Override
   public String getText() {
      return this.gameProfile.getName();
   }

   @Override
   public ItemStack getItemStack() {
      return this.inventoryPlayer.getCurrentItem();
   }

   public void updateDuplicateStatIdManager(DuplicateStatIdManager duplicateStatIdManager, int count) {
   }

   public void applyTileEntitySign(TileEntitySign tileEntitySign) {
   }

   @Override
   public void applyItemStack(int count, ItemStack stack) {
      this.inventoryPlayer.armorInventory[count] = stack;
   }

   @Override
   public IChatComponent getChatComponent() {
      ChatComponentText chatComponentText = new ChatComponentText(ScorePlayerTeam.getText(this.getSecondaryClientManager(), this.getText()));
      chatComponentText.getStyleHasParentManager()
         .getStyleHasParentManager(new ClickEventActionManager(ClientSettingsOpenUrlType.SUGGEST_COMMAND, "/msg " + this.getText() + " "));
      chatComponentText.getStyleHasParentManager().getStyleHasParentManager(this.getHoverEventActionManager());
      chatComponentText.getStyleHasParentManager().withLabelText(this.getText());
      return chatComponentText;
   }

   @Override
   public double getValue() {
      return -0.35;
   }

   @Override
   public boolean isActive() {
      return VanillaRunnable.getVanillaRunnable().worldServerArray[0].getDoFireTickManager().matchesText("sendCommandFeedback");
   }

   public EntityItem getEntityItem(boolean flag) {
      return this.resolveEntityItem(
         this.inventoryPlayer
            .getItemStack(
               this.inventoryPlayer.currentItem,
               flag && this.inventoryPlayer.getCurrentItem() != null ? this.inventoryPlayer.getCurrentItem().animationsToGo : 1
            ),
         false,
         true
      );
   }

   @Override
   public boolean matchesItemStack(int count, ItemStack stack) {
      if (count >= 0 && count < this.inventoryPlayer.mainInventory.length) {
         this.inventoryPlayer.processAction(count, stack);
         return true;
      } else {
         int count2 = count - 100;
         if (count2 >= 0 && count2 < this.inventoryPlayer.armorInventory.length) {
            int count3 = count2 + 1;
            if (stack != null && stack.getItem() != null) {
               if (stack.getItem() instanceof ItemGoldenBoots) {
                  if (EntityMob.computeCount(stack) != count3) {
                     return false;
                  }
               } else if (count3 != 4 || stack.getItem() != Items.skull && !(stack.getItem() instanceof ItemBlock)) {
                  return false;
               }
            }

            this.inventoryPlayer.processAction(count2 + this.inventoryPlayer.mainInventory.length, stack);
            return true;
         } else {
            int count4 = count - 200;
            if (count4 >= 0 && count4 < this.movementContainerEnderchestMode.processAction4()) {
               this.movementContainerEnderchestMode.processAction(count4, stack);
               return true;
            } else {
               return false;
            }
         }
      }
   }

   @Override
   public void applyFloat2(float factor) {
      this.inventoryPlayer.updateFloat(factor);
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAdvancedMapEntry().resolveAuxiliaryListenerByExtendedListener(SharedMonsterAttributes.extendedListener).applyDouble(1.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.1F);
   }

   @Override
   public boolean isActive22() {
      return true;
   }

   public FoodLevelManager getFoodLevelManager() {
      return this.foodLevelManager;
   }

   public void applyItemStack(ItemStack stack) {
   }

   @Override
   public void updateState14() {
      if (!this.invulnerableManager.state) {
         super.updateState14();
      }
   }

   public void applyLocalListener(LocalListener localListener) {
   }

   @Override
   public void processFloat2(float factor, float factor2) {
      if (!this.invulnerableManager.state2) {
         if (factor >= 2.0F) {
            this.updateDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManager23, (int)Math.round((double)factor * 100.0));
         }

         super.processFloat2(factor, factor2);
      }
   }

   public void applyItemStack(ItemStack stack, int count) {
      if (stack != this.itemStack) {
         this.itemStack = stack;
         this.entityId2 = count;
         if (!this.worldObj.state) {
            this.setEnabled4(true);
         }
      }
   }

   public void applyFloat4(float factor) {
      if (!this.invulnerableManager.enabled && !this.worldObj.state) {
         this.foodLevelManager.min(factor);
      }
   }

   @Override
   public boolean matchesEntityPlayer4(EntityPlayer player) {
      if (!this.isActive12()) {
         return false;
      } else if (player.isActive23()) {
         return false;
      } else {
         SecondaryClientManager secondaryClientManager = this.getSecondaryClientManager();
         return secondaryClientManager == null || player == null || player.getSecondaryClientManager() != secondaryClientManager || !secondaryClientManager.isEnabled2();
      }
   }

   @Override
   public ItemStack[] getItemStackArray2() {
      return this.inventoryPlayer.armorInventory;
   }

   private void applyDouble5(double posX, double posY, double posZ) {
      if (this.ridingEntity != null) {
         int count = Math.round(MathHelper.sqrt_double(posX * posX + posY * posY + posZ * posZ) * 100.0F);
         if (count > 0) {
            if (this.ridingEntity instanceof EntityMinecart) {
               this.updateDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManager5, count);
               if (this.blockPos4 == null) {
                  this.blockPos4 = new BlockPos(this);
               } else if (this.blockPos4
                     .computeValue(
                        (double)MathHelper.ceiling_double_int(this.posX),
                        (double)MathHelper.ceiling_double_int(this.posY),
                        (double)MathHelper.ceiling_double_int(this.posZ)
                     )
                  >= 1000000.0) {
                  this.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter30);
               }
            } else if (this.ridingEntity instanceof EntityBoat) {
               this.updateDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManager4, count);
            } else if (this.ridingEntity instanceof EntityPig) {
               this.updateDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManager25, count);
            } else if (this.ridingEntity instanceof EntityHorse) {
               this.updateDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManager21, count);
            }
         }
      }
   }

   public ItemStack getHeldItem() {
      return this.inventoryPlayer.getCurrentItem();
   }

   public static UUID resolveUUID(GameProfile gameProfile2) {
      UUID id = gameProfile2.getId();
      if (id == null) {
         id = resolveUUID(gameProfile2.getName());
      }

      return id;
   }

   @Override
   public void processByte(byte byteValue) {
      if (byteValue == 9) {
         this.updateState42();
      } else if (byteValue == 23) {
         this.inWater2 = false;
      } else if (byteValue == 22) {
         this.inWater2 = true;
      } else {
         super.processByte(byteValue);
      }
   }

   public boolean matchesBlockPos(BlockPos blockPos, EnumFacing enumFacing, ItemStack stack) {
      if (this.invulnerableManager.state1) {
         return true;
      } else if (stack == null) {
         return false;
      } else {
         BlockPos blockPos5 = blockPos.offset(enumFacing.getEnumFacing5());
         Block block = this.worldObj.getBlockState(blockPos5).getBlock();
         return stack.matchesBlock(block) || stack.processAction9();
      }
   }

   public static UUID resolveUUID(String text2) {
      return UUID.nameUUIDFromBytes(("OfflinePlayer:" + text2).getBytes(Charsets.UTF_8));
   }

   @Override
   public String getText7() {
      return "game.player.swim.splash";
   }

   public EntityItem resolveEntityItem(ItemStack stack, boolean flag) {
      return this.resolveEntityItem(stack, false, false);
   }

   public boolean matchesEntityPlayer6(EntityPlayer player) {
      SecondaryClientManager secondaryClientManager = this.getSecondaryClientManager();
      SecondaryClientManager secondaryClientManager2 = player.getSecondaryClientManager();
      return secondaryClientManager == null ? true : (!secondaryClientManager.matchesSecondaryClientManager(secondaryClientManager2) ? true : secondaryClientManager.isEnabled());
   }

   public void applyVectorDispatcher(VectorDispatcher vectorDispatcher) {
   }

   public void applyEntity18(Entity entity) {
      if (entity.isActive3() && !entity.matchesEntityByEntity(this)) {
         float doubleItem = (float)this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).getDouble();
         byte byteValue = 0;
         float factor;
         if (entity instanceof EntityLivingBase) {
            factor = LvlManager.getScaleFactor(this.getItemStack(), ((EntityLivingBase)entity).getUNDEAD());
         } else {
            factor = LvlManager.getScaleFactor(this.getItemStack(), DisablerUndefinedType.UNDEFINED);
         }

         if (this instanceof EntityPlayerSP) {
            ((EntityPlayerSP)this).updateState6();
         }

         int count = byteValue + LvlManager.getIndex(this);
         if (this.isRiding()) {
            count++;
         }

         if (doubleItem > 0.0F || factor > 0.0F) {
            boolean flag = this.fallDistance > 0.0F
               && !this.onGround
               && !this.canExecute9()
               && !this.isInWater()
               && !this.checkPotion(Potion.potion16)
               && this.ridingEntity == null
               && entity instanceof EntityLivingBase;
            if (flag && doubleItem > 0.0F) {
               doubleItem *= 1.5F;
            }

            float factor2 = doubleItem + factor;
            boolean flag2 = false;
            int count2 = LvlManager.computeCount3(this);
            if (entity instanceof EntityLivingBase && count2 > 0 && !entity.canExecute2()) {
               flag2 = true;
               entity.applyIndexByIndex(1);
            }

            double motionX = entity.motionX;
            double motionY = entity.motionY;
            double motionZ = entity.motionZ;
            if (entity.matchesDamageSource(DamageSource.resolveDamageSource(this), factor2)) {
               if (count > 0) {
                  entity.applyDouble3(
                     (double)(-MathHelper.abs(this.rotationYaw * (float) Math.PI / 180.0F) * (float)count * 0.5F),
                     0.1,
                     (double)(MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * (float)count * 0.5F)
                  );
                  EventChat event = EventChatCallback.resolveEventChat(RenderKeybindProperty.FIRST);
                  this.motionX = this.motionX * event.getValue();
                  this.motionZ = this.motionZ * event.getValue();
                  this.setState3(event.isEnabled2());
                  ClientGlobals.moduleManager.velocity.keyCode2++;
               } else {
                  EventChat event2 = EventChatCallback.resolveEventChat(RenderKeybindProperty.SECOND);
                  if (event2.isEnabled()) {
                     this.motionX = this.motionX * event2.getValue();
                     this.motionZ = this.motionZ * event2.getValue();
                     this.setState3(event2.isEnabled2());
                     ClientGlobals.moduleManager.velocity.keyCode2++;
                  }
               }

               if (entity instanceof EntityPlayerAdapter && entity.enabled8) {
                  ((EntityPlayerAdapter)entity).auxiliaryListenerAdapter4.applyPacket(new S12PacketEntityVelocity(entity));
                  entity.enabled8 = false;
                  entity.motionX = motionX;
                  entity.motionY = motionY;
                  entity.motionZ = motionZ;
               }

               if (flag) {
                  this.applyEntity17(entity);
               }

               if (factor > 0.0F) {
                  this.applyEntity16(entity);
               }

               if (factor2 >= 18.0F) {
                  this.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter);
               }

               this.applyEntity14(entity);
               if (entity instanceof EntityLivingBase) {
                  LvlManager.applyEntityLivingBase((EntityLivingBase)entity, this);
               }

               LvlManager.applyEntityLivingBase2(this, entity);
               ItemStack stack = this.getHeldItem();
               Object entry = entity;
               if (entity instanceof VectorFormatter) {
                  PreRenderProcessor preRenderProcessor = ((VectorFormatter)entity).preRenderProcessor;
                  if (preRenderProcessor instanceof EntityLivingBase) {
                     entry = (EntityLivingBase)preRenderProcessor;
                  }
               }

               if (stack != null && entry instanceof EntityLivingBase) {
                  stack.applyEntityLivingBaseByEntityLivingBase((EntityLivingBase)entry, this);
                  if (stack.animationsToGo <= 0) {
                     this.updateState45();
                  }
               }

               if (entity instanceof EntityLivingBase) {
                  this.updateDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManager46, Math.round(factor2 * 10.0F));
                  if (count2 > 0) {
                     entity.applyIndexByIndex(count2 * 4);
                  }
               }

               this.applyFloat4(0.3F);
            } else if (flag2) {
               entity.updateState12();
            }
         }
      }
   }

   @Override
   public ItemStack resolveItemStack(int count) {
      return count == 0 ? this.inventoryPlayer.getCurrentItem() : this.inventoryPlayer.armorInventory[count - 1];
   }

   public void setScaleFactor2(float scaleFactor) {
      this.scaleFactor2 = scaleFactor;
   }

   public SplineSorter resolveSplineSorter(BlockPos blockPos) {
      if (!this.worldObj.state) {
         if (this.isState5() || !this.canExecute()) {
            return SplineSorter.OTHER_PROBLEM;
         }

         if (!this.worldObj.secondaryFloatArrayEntry.isActive2()) {
            return SplineSorter.NOT_POSSIBLE_HERE;
         }

         if (this.worldObj.isActive2()) {
            return SplineSorter.NOT_POSSIBLE_NOW;
         }

         if (Math.abs(this.posX - (double)blockPos.getX()) > 3.0
            || Math.abs(this.posY - (double)blockPos.getY()) > 2.0
            || Math.abs(this.posZ - (double)blockPos.getZ()) > 3.0) {
            return SplineSorter.TOO_FAR_AWAY;
         }

         double offset = 8.0;
         double offset2 = 5.0;
         if (!this.worldObj
            .resolveList(
               EntityMonster.class,
               new AxisAlignedBB(
                  (double)blockPos.getX() - offset,
                  (double)blockPos.getY() - offset2,
                  (double)blockPos.getZ() - offset,
                  (double)blockPos.getX() + offset,
                  (double)blockPos.getY() + offset2,
                  (double)blockPos.getZ() + offset
               )
            )
            .isEmpty()) {
            return SplineSorter.NOT_SAFE;
         }
      }

      if (this.isSneaking()) {
         this.setEntity8((Entity)null);
      }

      this.applyFloat(0.2F, 0.2F);
      if (this.worldObj.checkBlockPos12(blockPos)) {
         EnumFacing comparable = (EnumFacing)this.worldObj.getBlockState(blockPos).getComparable(BaseBlockAdapter.autoWalkPath);
         float factor = 0.5F;
         float factor2 = 0.5F;
         switch (InterpolationDeserializer.countArray[comparable.ordinal()]) {
            case 1:
               factor2 = 0.9F;
               break;
            case 2:
               factor2 = 0.1F;
               break;
            case 3:
               factor = 0.1F;
               break;
            case 4:
               factor = 0.9F;
         }

         this.applyEnumFacing(comparable);
         this.processDouble2((double)((float)blockPos.getX() + factor), (double)((float)blockPos.getY() + 0.6875F), (double)((float)blockPos.getZ() + factor2));
      } else {
         this.processDouble2((double)((float)blockPos.getX() + 0.5F), (double)((float)blockPos.getY() + 0.6875F), (double)((float)blockPos.getZ() + 0.5F));
      }

      this.state5 = true;
      this.indexOffset8 = 0;
      this.blockPos2 = blockPos;
      this.motionX = this.motionZ = this.motionY = 0.0;
      if (!this.worldObj.state) {
         this.worldObj.updateState5();
      }

      return SplineSorter.OK;
   }

   public EntityItem resolveEntityItem(ItemStack stack, boolean flag, boolean flag2) {
      if (stack == null) {
         return null;
      } else if (stack.animationsToGo == 0) {
         return null;
      } else {
         double offset = this.posY - 0.3F + (double)this.getHeight2();
         EntityItem entityItem = new EntityItem(this.worldObj, this.posX, offset, this.posZ, stack);
         entityItem.setCount14(40);
         if (flag2) {
            entityItem.setLabelTextByText(this.getText());
         }

         if (flag) {
            float factor = this.random.nextFloat() * 0.5F;
            float factor2 = this.random.nextFloat() * (float) Math.PI * 2.0F;
            entityItem.motionX = (double)(-MathHelper.abs(factor2) * factor);
            entityItem.motionZ = (double)(MathHelper.cos(factor2) * factor);
            entityItem.motionY = 0.2F;
         } else {
            float factor3 = 0.3F;
            entityItem.motionX = (double)(
               -MathHelper.abs(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * factor3
            );
            entityItem.motionZ = (double)(
               MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * factor3
            );
            entityItem.motionY = (double)(-MathHelper.abs(this.rotationPitch / 180.0F * (float) Math.PI) * factor3 + 0.1F);
            float factor4 = this.random.nextFloat() * (float) Math.PI * 2.0F;
            float factor5 = 0.02F * this.random.nextFloat();
            entityItem.motionX = entityItem.motionX + Math.cos((double)factor4) * (double)factor5;
            entityItem.motionY = entityItem.motionY + (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F);
            entityItem.motionZ = entityItem.motionZ + Math.sin((double)factor4) * (double)factor5;
         }

         this.applyEntityItem(entityItem);
         if (flag2) {
            this.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager36);
         }

         return entityItem;
      }
   }

   private Collection resolveCollection(Entity entity) {
      ScorePlayerTeam scorePlayerTeam = this.getThePlayerNameManager().getScorePlayerTeam(this.getText());
      if (scorePlayerTeam != null) {
         int index = scorePlayerTeam.getClientSettingsBlackType().getIndex();
         if (index >= 0 && index < HealthHandler.healthHandlerArray2.length) {
            for (ScoreboardData scoreboardData : (Iterable<ScoreboardData>)(Iterable<?>)this.getThePlayerNameManager().resolveCollection(HealthHandler.healthHandlerArray2[index])) {
               this.getThePlayerNameManager().getComparatorEntry(entity.getText(), scoreboardData).updateState();
            }
         }
      }

      ScorePlayerTeam scorePlayerTeam2 = this.getThePlayerNameManager().getScorePlayerTeam(entity.getText());
      if (scorePlayerTeam2 != null) {
         int index2 = scorePlayerTeam2.getClientSettingsBlackType().getIndex();
         if (index2 >= 0 && index2 < HealthHandler.healthHandlerArray.length) {
            return this.getThePlayerNameManager().resolveCollection(HealthHandler.healthHandlerArray[index2]);
         }
      }

      return Lists.newArrayList();
   }

   public void setScaleFactor4(float scaleFactor) {
      this.scaleFactor4 = scaleFactor;
   }

   private void applyEnumFacing(EnumFacing enumFacing) {
      this.factor38 = 0.0F;
      this.factor36 = 0.0F;
      switch (InterpolationDeserializer.countArray[enumFacing.ordinal()]) {
         case 1:
            this.factor36 = -1.8F;
            break;
         case 2:
            this.factor36 = 1.8F;
            break;
         case 3:
            this.factor38 = 1.8F;
            break;
         case 4:
            this.factor38 = -1.8F;
      }
   }

   public String getText3() {
      return this.text;
   }

   @Override
   public float getScaleFactor6() {
      return Float.valueOf((float)this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).getDouble());
   }

   public void applyEntityPlayer(EntityPlayer player, boolean flag) {
      if (flag) {
         this.inventoryPlayer.applyInventoryPlayer(player.inventoryPlayer);
         this.applyFloat(player.getFloat2());
         this.foodLevelManager = player.foodLevelManager;
         this.count25 = player.count25;
         this.count32 = player.count32;
         this.factor39 = player.factor39;
         this.applyIndex10(player.getCount6());
         this.blockPos = player.blockPos;
         this.positionVec2 = player.positionVec2;
         this.enumFacing = player.enumFacing;
      } else if (this.worldObj.getDoFireTickManager().matchesText("keepInventory")) {
         this.inventoryPlayer.applyInventoryPlayer(player.inventoryPlayer);
         this.count25 = player.count25;
         this.count32 = player.count32;
         this.factor39 = player.factor39;
         this.applyIndex10(player.getCount6());
      }

      this.indexOffset6 = player.indexOffset6;
      this.movementContainerEnderchestMode = player.movementContainerEnderchestMode;
      this.getDataWatcher().applyTargetByTarget(10, player.getDataWatcher().computeByteCode(10));
   }

   @Override
   public int getCount2() {
      return 10;
   }

   @Override
   public void applyDamageSource(DamageSource damageSource) {
      super.applyDamageSource(damageSource);
      this.applyFloat(0.2F, 0.2F);
      this.processDouble2(this.posX, this.posY, this.posZ);
      this.motionY = 0.1F;
      if (this.getText().equals("Notch")) {
         this.resolveEntityItem(new ItemStack(Items.apple, 1), true, false);
      }

      if (!this.worldObj.getDoFireTickManager().matchesText("keepInventory")) {
         this.inventoryPlayer.updateState4();
      }

      if (damageSource != null) {
         this.motionX = (double)(-MathHelper.cos((this.factor17 + this.rotationYaw) * (float) Math.PI / 180.0F) * 0.1F);
         this.motionZ = (double)(-MathHelper.abs((this.factor17 + this.rotationYaw) * (float) Math.PI / 180.0F) * 0.1F);
      } else {
         this.motionX = this.motionZ = 0.0;
      }

      this.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager42);
      this.applyDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManager40);
   }

   @Override
   public String getText10() {
      return "game.player.hurt";
   }

   public boolean matchesLockCode(LockCode lockCode) {
      if (lockCode.isEmpty()) {
         return true;
      } else {
         ItemStack stack = this.getHeldItem();
         return stack != null && stack.isActive() ? stack.getString().equals(lockCode.getLock()) : false;
      }
   }

   @Override
   public float getScaleFactor13() {
      return this.getDataWatcher().computeFactor(17);
   }

   @Override
   public void applyFloat2(float factor, float factor2) {
      double posX = this.posX;
      double posY = this.posY;
      double posZ = this.posZ;
      if (this.invulnerableManager.state && this.ridingEntity == null) {
         double value = this.motionY;
         float factor3 = this.scaleFactor10;
         this.scaleFactor10 = this.invulnerableManager.getFactor() * (float)(this.isRiding() ? 2 : 1);
         super.applyFloat2(factor, factor2);
         this.motionY = value * 0.6;
         this.scaleFactor10 = factor3;
      } else {
         if (this.isInWater()
            && !this.isSneaking()
            && ((Module)ClientGlobals.moduleManager.jesus).isToggled()
            && ClientGlobals.moduleManager.jesus.settingMode.getSelected().equals("1.13")) {
            double posY2 = this.getVec34(this.rotationPitch, this.rotationYaw).yCoord;
            double offset = posY2 < -0.2 ? 0.085 : 0.06;
            if (posY2 <= 0.0
               || this.state31
               || !(this.worldObj.getBlockState(new BlockPos(this.posX, this.posY + 1.0 - 0.1, this.posZ)).getBlock() instanceof BlockLiquid)) {
               this.motionY = this.motionY + (posY2 - this.motionY) * offset;
            }
         }

         super.applyFloat2(factor, factor2);
      }

      this.applyDouble4(this.posX - posX, this.posY - posY, this.posZ - posZ);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("Inventory", this.inventoryPlayer.getExtendedLoggerEntry(new NBTTagList()));
      tagCompound.put("SelectedItemSlot", this.inventoryPlayer.currentItem);
      tagCompound.applyText("Sleeping", this.state5);
      tagCompound.put("SleepTimer", (short)this.indexOffset8);
      tagCompound.put("XpP", this.factor39);
      tagCompound.put("XpLevel", this.count25);
      tagCompound.put("XpTotal", this.count32);
      tagCompound.put("XpSeed", this.indexOffset6);
      tagCompound.put("Score", this.getCount6());
      if (this.blockPos3 != null) {
         tagCompound.put("SpawnX", this.blockPos3.getX());
         tagCompound.put("SpawnY", this.blockPos3.getY());
         tagCompound.put("SpawnZ", this.blockPos3.getZ());
         tagCompound.applyText("SpawnForced", this.state12);
      }

      this.foodLevelManager.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      this.invulnerableManager.applyNBTTagCompound(tagCompound);
      tagCompound.applyText("EnderItems", this.movementContainerEnderchestMode.getNBTTagList());
      ItemStack slot = this.inventoryPlayer.getCurrentItem();
      if (slot != null && slot.getItem() != null) {
         tagCompound.applyText("SelectedItem", slot.resolveNBTTagCompound(new NBTTagCompound()));
      }
   }

   @Override
   public void applyDamageSource(DamageSource damageSource, float factor) {
      float factor2 = factor;
      if (!this.matchesDamageSource(damageSource)) {
         if (!damageSource.canExecute() && this.isBlocking() && factor > 0.0F) {
            factor2 = (1.0F + factor) * 0.5F;
         }

         factor2 = this.computeFactorByDamageSource(damageSource, factor2);
         factor2 = this.computeFactor(damageSource, factor2);
         float factor3 = Math.max(factor2 - this.getScaleFactor13(), 0.0F);
         this.applyFloat3(this.getScaleFactor13() - (factor2 - factor3));
         if (factor3 != 0.0F) {
            this.applyFloat4(damageSource.getFactor());
            float floatItem = this.getFloat2();
            this.applyFloat(this.getFloat2() - factor3);
            this.getGenericManager().applyDamageSource(damageSource, floatItem, factor3);
            if (factor3 < 3.4028235E37F) {
               this.updateDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManager15, Math.round(factor3 * 10.0F));
            }
         }
      }
   }

   @Override
   public ItemStack getItemStack3(int count) {
      return this.inventoryPlayer.armorItemInSlot(count);
   }

   @Override
   public boolean isActive16() {
      return ((Module)Client.getClient().getModuleManager().child).isToggled();
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      float factor2 = factor;
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else if (this.invulnerableManager.enabled && !damageSource.isEnabled4()) {
         return false;
      } else {
         this.indexOffset14 = 0;
         if (this.getFloat2() <= 0.0F) {
            return false;
         } else {
            if (this.isState5() && !this.worldObj.state) {
               this.applyFlag(true, true, false);
            }

            if (damageSource.isEnabled2()) {
               if (this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.PEACEFUL) {
                  factor2 = 0.0F;
               }

               if (this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.EASY) {
                  factor2 = factor2 / 2.0F + 1.0F;
               }

               if (this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.HARD) {
                  factor2 = factor2 * 3.0F / 2.0F;
               }
            }

            if (factor2 == 0.0F) {
               return false;
            } else {
               Entity entity = damageSource.getEntity();
               if (entity instanceof EntityArrow && ((EntityArrow)entity).entity2 != null) {
                  ;
               }

               return super.matchesDamageSource(damageSource, factor2);
            }
         }
      }
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity) {
      if (livingEntity instanceof VectorPipeline) {
         this.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter11);
      }

      DuplicateStatIdManagerEntry duplicateStatIdManagerEntry = (DuplicateStatIdManagerEntry)SkippingEntityWithIdManager.map6.get(SkippingEntityWithIdManager.computeCount(livingEntity));
      if (duplicateStatIdManagerEntry != null) {
         this.applyDuplicateStatIdManagerByDuplicateStatIdM(duplicateStatIdManagerEntry.duplicateStatIdManager2);
      }
   }

   public void applyEntityHorse(EntityHorse entityHorse, IInventory iInventory) {
   }

   public void setText(String text2) {
      this.text = text2;
   }

   public void applyEntity17(Entity entity) {
   }

   @Override
   public String getText5() {
      return ScorePlayerTeam.getText(this.getSecondaryClientManager(), this.getText()) + ClientSettingsBlackType.RESET;
   }

   public void applyDuplicateStatIdManager(DuplicateStatIdManager duplicateStatIdManager) {
   }

   public BlockPos getBlockPos3() {
      return this.blockPos3;
   }

   public void applyDouble4(double posX, double posY, double posZ) {
      if (this.ridingEntity == null) {
         if (this.matchesMaterial(Material.coreItemEntry1)) {
            int count = Math.round(MathHelper.sqrt_double(posX * posX + posY * posY + posZ * posZ) * 100.0F);
            if (count > 0) {
               this.updateDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManager37, count);
               this.applyFloat4(0.015F * (float)count * 0.01F);
            }
         } else if (this.isInWater()) {
            int count2 = Math.round(MathHelper.sqrt_double(posX * posX + posZ * posZ) * 100.0F);
            if (count2 > 0) {
               this.updateDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManager7, count2);
               this.applyFloat4(0.015F * (float)count2 * 0.01F);
            }
         } else if (this.canExecute9()) {
            if (posY > 0.0) {
               this.updateDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManager20, (int)Math.round(posY * 100.0));
            }
         } else if (this.onGround) {
            int count3 = Math.round(MathHelper.sqrt_double(posX * posX + posZ * posZ) * 100.0F);
            if (count3 > 0) {
               this.updateDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManager18, count3);
               if (this.isRiding()) {
                  this.updateDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManager35, count3);
                  this.applyFloat4(0.099999994F * (float)count3 * 0.01F);
               } else {
                  if (this.isSprinting()) {
                     this.updateDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManager11, count3);
                  }

                  this.applyFloat4(0.01F * (float)count3 * 0.01F);
               }
            }
         } else {
            int count4 = Math.round(MathHelper.sqrt_double(posX * posX + posZ * posZ) * 100.0F);
            if (count4 > 25) {
               this.updateDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManager8, count4);
            }
         }
      }
   }

   public boolean matchesEntity8(Entity entity) {
      if (this.isActive23()) {
         if (entity instanceof IInventory) {
            this.applyIInventory((IInventory)entity);
         }

         return false;
      } else {
         ItemStack stack = this.getHeldItem();
         ItemStack stack2 = stack != null ? stack.getItemStack() : null;
         if (!entity.matchesEntityPlayerByEntityPlayer(this)) {
            if (stack != null && entity instanceof EntityLivingBase) {
               if (this.invulnerableManager.enabled2) {
                  stack = stack2;
               }

               if (stack.processAction(this, (EntityLivingBase)entity)) {
                  if (stack.animationsToGo <= 0 && !this.invulnerableManager.enabled2) {
                     this.updateState45();
                  }

                  return true;
               }
            }

            return false;
         } else {
            if (stack != null && stack == this.getHeldItem()) {
               if (stack.animationsToGo <= 0 && !this.invulnerableManager.enabled2) {
                  this.updateState45();
               } else if (stack.animationsToGo < stack2.animationsToGo && this.invulnerableManager.enabled2) {
                  stack.animationsToGo = stack2.animationsToGo;
               }
            }

            return true;
         }
      }
   }

   @Override
   public String resolveText(int count) {
      return count > 4 ? "game.player.hurt.fall.big" : "game.player.hurt.fall.small";
   }

   @Override
   public void updateState38() {
      super.updateState38();
      this.updateState37();
      this.scaleFactor12 = this.rotationYaw;
      this.scaleFactor21 = this.rotationPitch;
   }

   public void setInWater2(boolean inWater) {
      this.inWater2 = inWater;
   }

   public void setEntityId2(int entityId) {
      this.entityId2 = entityId;
   }

   public void applyIndex9(int count) {
      this.count25 -= count;
      if (this.count25 < 0) {
         this.count25 = 0;
         this.factor39 = 0.0F;
         this.count32 = 0;
      }

      this.indexOffset6 = this.random.nextInt();
   }

   public void applyIndex11(int count) {
      int count2 = this.getCount6();
      this.dataWatcher.applyTargetByTarget(18, count2 + count);
   }

   public void updateState3() {
      if (this.itemStack != null) {
         this.itemStack.processAction(this.worldObj, this, this.entityId2);
      }

      this.updateState44();
   }

   public void applyIndex8(int count) {
      this.count25 += count;
      if (this.count25 < 0) {
         this.count25 = 0;
         this.factor39 = 0.0F;
         this.count32 = 0;
      }

      if (count > 0 && this.count25 % 5 == 0 && (float)this.count24 < (float)this.ticksExisted - 100.0F) {
         float factor = this.count25 > 30 ? 1.0F : (float)this.count25 / 30.0F;
         this.worldObj.applyEntityByEntity(this, "random.levelup", factor * 0.75F, 1.0F);
         this.count24 = this.ticksExisted;
      }
   }

   public void applyIndex12(int count) {
      int count2 = count;
      this.applyIndex11(count);
      int count3 = Integer.MAX_VALUE - this.count32;
      if (count > count3) {
         count2 = count3;
      }

      this.factor39 = this.factor39 + (float)count2 / (float)this.getCount7();

      for (this.count32 += count2; this.factor39 >= 1.0F; this.factor39 = this.factor39 / (float)this.getCount7()) {
         this.factor39 = (this.factor39 - 1.0F) * (float)this.getCount7();
         this.applyIndex8(1);
      }
   }

   public void updateState42() {
      if (this.itemStack != null) {
         this.applyItemStackByItemStack(this.itemStack, 16);
         int animationsToGo = this.itemStack.animationsToGo;
         ItemStack stack = this.itemStack.processAction(this.worldObj, this);
         if (stack != this.itemStack || stack != null && stack.animationsToGo != animationsToGo) {
            this.inventoryPlayer.mainInventory[this.inventoryPlayer.currentItem] = stack;
            if (stack.animationsToGo == 0) {
               this.inventoryPlayer.mainInventory[this.inventoryPlayer.currentItem] = null;
            }
         }

         this.updateState44();
      }
   }

   public void updateState4() {
   }

   public void updateState45() {
      this.inventoryPlayer.processAction(this.inventoryPlayer.currentItem, (ItemStack)null);
   }

   public void updateState5() {
      this.container2 = this.container;
   }

   public void applyIndex10(int count) {
      this.dataWatcher.applyTargetByTarget(18, count);
   }

   public void updateState43() {
   }

   public void updateState44() {
      this.itemStack = null;
      this.entityId2 = 0;
      if (!this.worldObj.state) {
         this.setEnabled4(false);
      }
   }

   @Override
   public boolean isActive20() {
      return this.getFloat2() <= 0.0F || this.isState5();
   }

   public boolean onReset() {
      return this.invulnerableManager.state1;
   }

   public int getInt10() {
      return this.isUsingItem() ? this.itemStack.processAction6() - this.entityId2 : 0;
   }

   public int getCount7() {
      return this.count25 >= 30 ? 112 + (this.count25 - 30) * 9 : (this.count25 >= 15 ? 37 + (this.count25 - 15) * 5 : 7 + this.count25 * 2);
   }

   public int getCount6() {
      return this.dataWatcher.computeCount(18);
   }

   public int getIndexOffset8() {
      return this.indexOffset8;
   }

   public int getIndexOffset6() {
      return this.indexOffset6;
   }

   public int getEntityId2() {
      return this.entityId2;
   }

   @Override
   public void updateState22() {
      this.applyFloat(0.6F, 1.8F);
      super.updateState22();
      this.applyFloat(this.getFloat());
      this.indexOffset29 = 0;
   }

   public void applyEntity16(Entity entity) {
   }

   @Override
   public void updateState9() {
      super.updateState9();
      this.container.applyEntityPlayer(this);
      if (this.container2 != null) {
         this.container2.applyEntityPlayer(this);
      }
   }

   public float getFactor2() {
      if (this.blockPos2 != null) {
         IBlockState blockState = this.worldObj.getBlockState(this.blockPos2);
         if (!blockState.containsKey(BaseBlockAdapter.autoWalkPath)) {
            return 0.0F;
         }

         EnumFacing comparable = (EnumFacing)blockState.getComparable(BaseBlockAdapter.autoWalkPath);
         switch (InterpolationDeserializer.countArray[comparable.ordinal()]) {
            case 1:
               return 90.0F;
            case 2:
               return 270.0F;
            case 3:
               return 0.0F;
            case 4:
               return 180.0F;
         }
      }

      return 0.0F;
   }

   @Override
   public boolean isActive13() {
      return true;
   }

   public boolean isState12() {
      return this.state12;
   }

   @Override
   public void updateState11() {
      if (!this.isActive23()) {
         super.updateState11();
      }
   }

   public boolean onDisable() {
      return !Client.getClient().getModuleManager().fixes.canExecute9() && this.inWater2;
   }

   public boolean isActive24() {
      return false;
   }

   public boolean isBlocking() {
      return this.isUsingItem() && this.itemStack.getItem().processAction4(this.itemStack) == ItemNoneType.BLOCK;
   }

   public float getScaleFactor4() {
      return this.scaleFactor4;
   }

   @Override
   public boolean isActive15() {
      return !this.state5 && super.isActive15();
   }

   public abstract boolean isActive23();

   private boolean isActive26() {
      return this.worldObj.getBlockState(this.blockPos2).getBlock() == Blocks.bed;
   }

   @Override
   public boolean isActive8() {
      return !this.invulnerableManager.state;
   }

   public float getScaleFactor2() {
      return this.scaleFactor2;
   }

   public boolean isActive25() {
      return this.state5 && this.indexOffset8 >= 100;
   }

   @Override
   public void updateState() {
      this.noClip = this.isActive23();
      if (this instanceof EntityPlayerSP) {
         EventPhaseListener.updateState13();
      }

      if (this.isActive23()) {
         this.onGround = false;
      }

      if (this.itemStack != null) {
         ItemStack slot = this.inventoryPlayer.getCurrentItem();
         if (slot == this.itemStack) {
            if (this.entityId2 <= 25 && this.entityId2 % 4 == 0) {
               this.applyItemStackByItemStack(slot, 5);
            }

            if (--this.entityId2 == 0 && !this.worldObj.state) {
               this.updateState42();
            }
         } else {
            if (this instanceof EntityPlayerSP) {
               ClientGlobals.moduleManager.killAura.activeState31 = true;
            }

            this.updateState44();
         }
      }

      if (this.count23 > 0) {
         this.count23--;
      }

      if (this.isState5()) {
         this.indexOffset8++;
         if (this.indexOffset8 > 100) {
            this.indexOffset8 = 100;
         }

         if (!this.worldObj.state) {
            if (!this.isActive26()) {
               this.applyFlag(true, true, false);
            } else if (this.worldObj.isActive2()) {
               this.applyFlag(false, true, true);
            }
         }
      } else if (this.indexOffset8 > 0) {
         this.indexOffset8++;
         if (this.indexOffset8 >= 110) {
            this.indexOffset8 = 0;
         }
      }

      if (this instanceof EntityPlayerSP) {
         ((EntityPlayerSP)this).indexOffset12++;
      }

      super.updateState();
      if (!this.worldObj.state && this.container2 != null && !this.container2.matchesEntityPlayer(this)) {
         this.updateState5();
         this.container2 = this.container;
      }

      if (this.canExecute2() && this.invulnerableManager.enabled) {
         this.updateState12();
      }

      this.value30 = this.value25;
      this.value28 = this.value27;
      this.value29 = this.value26;
      double offset = this.posX - this.value25;
      double offset2 = this.posY - this.value27;
      double offset3 = this.posZ - this.value26;
      double offset4 = 10.0;
      if (offset > offset4) {
         this.value30 = this.value25 = this.posX;
      }

      if (offset3 > offset4) {
         this.value29 = this.value26 = this.posZ;
      }

      if (offset2 > offset4) {
         this.value28 = this.value27 = this.posY;
      }

      if (offset < -offset4) {
         this.value30 = this.value25 = this.posX;
      }

      if (offset3 < -offset4) {
         this.value29 = this.value26 = this.posZ;
      }

      if (offset2 < -offset4) {
         this.value28 = this.value27 = this.posY;
      }

      this.value25 += offset * 0.25;
      this.value26 += offset3 * 0.25;
      this.value27 += offset2 * 0.25;
      if (this.ridingEntity == null) {
         this.blockPos4 = null;
      }

      if (!this.worldObj.state) {
         this.foodLevelManager.applyEntityPlayer(this);
         this.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager41);
         if (this.canExecute()) {
            this.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager40);
         }
      }

      double doubleItem = MathHelper.getDouble7(this.posX, -2.9999999E7, 2.9999999E7);
      double doubleItem2 = MathHelper.getDouble7(this.posZ, -2.9999999E7, 2.9999999E7);
      if (doubleItem != this.posX || doubleItem2 != this.posZ) {
         this.processDouble2(doubleItem, this.posY, doubleItem2);
      }
   }

   private void applyEntity22(Entity entity) {
      entity.applyEntityPlayer2(this);
   }

   public boolean isPlayerSleeping() {
      return this.getFloat2() > 0.0F && this.getFloat2() < this.getFloat();
   }

   public float getFactor3() {
      int count = 0;
      ItemStack[] armorInventory = this.inventoryPlayer.armorInventory;
      int length = armorInventory.length;

      for (int index = 0; index < length; index++) {
         if (armorInventory[index] != null) {
            count++;
         }
      }

      return (float)count / (float)this.inventoryPlayer.armorInventory.length;
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(16, (byte)0);
      this.dataWatcher.applyTarget(17, 0.0F);
      this.dataWatcher.applyTarget(18, 0);
      this.dataWatcher.applyTarget(10, (byte)0);
   }

   public boolean isUsingItem() {
      return this.itemStack != null;
   }

   @Override
   public boolean isState5() {
      return this.state5;
   }

   @Override
   public void closeScreen() {
      super.closeScreen();
      this.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager30);
      if (this.isRiding()) {
         this.applyFloat4(0.8F);
      } else {
         this.applyFloat4(0.2F);
      }
   }

   @Override
   public void updateState33() {
      if (this.count26 > 0) {
         this.count26--;
      }

      if (this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.PEACEFUL
         && this.worldObj.getDoFireTickManager().matchesText("naturalRegeneration")) {
         if (this.getFloat2() < this.getFloat() && this.ticksExisted % 20 == 0) {
            this.applyFloatByFloat(1.0F);
         }

         if (this.foodLevelManager.isActive() && this.ticksExisted % 10 == 0) {
            this.foodLevelManager.setCount(this.foodLevelManager.getIndex() + 1);
         }
      }

      this.inventoryPlayer.updateState3();
      this.factor30 = this.factor47;
      super.updateState33();
      AuxiliaryListener auxiliaryListener = this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3);
      if (!this.worldObj.state) {
         auxiliaryListener.applyDouble((double)this.invulnerableManager.getFactor2());
      }

      this.scaleFactor10 = this.scaleFactor2;
      EventMoveInput event = MoveInputListener.resolveEventMoveInput(CombatModeProperty.JUMP_SPRINT);
      if (this.isRiding() || event.isEnabled5()) {
         this.scaleFactor10 = (float)((double)this.scaleFactor10 + (double)this.scaleFactor2 * 0.3);
      }

      this.setScaleFactor6((float)auxiliaryListener.getDouble());
      float factor = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
      float factor2 = (float)(Math.atan(-this.motionY * 0.2F) * 15.0);
      if (factor > 0.1F) {
         factor = 0.1F;
      }

      if (!this.onGround || this.getFloat2() <= 0.0F) {
         factor = 0.0F;
      }

      if (this.onGround || this.getFloat2() <= 0.0F) {
         factor2 = 0.0F;
      }

      this.factor47 = this.factor47 + (factor - this.factor47) * 0.4F;
      this.factor8 = this.factor8 + (factor2 - this.factor8) * 0.8F;
      if (this.getFloat2() > 0.0F && !this.isActive23()) {
         AxisAlignedBB boundingBox;
         if (this.ridingEntity != null && !this.ridingEntity.isDead) {
            boundingBox = this.getBoundingBox2().getAxisAlignedBB4(this.ridingEntity.getBoundingBox2()).offset(1.0, 0.0, 1.0);
         } else {
            boundingBox = this.getBoundingBox2().offset(1.0, 0.5, 1.0);
         }

         List list = this.worldObj.resolveListByEntity(this, boundingBox);

         for (int index = 0; index < list.size(); index++) {
            Entity entity = (Entity)list.get(index);
            if (!entity.isDead) {
               this.applyEntity22(entity);
            }
         }
      }
   }

   public EntityPlayer(World world, GameProfile gameProfile2) {
      super(world);
      this.entityUniqueID = resolveUUID(gameProfile2);
      this.gameProfile = gameProfile2;
      this.container = new ContainerPlayer(this.inventoryPlayer, !world.state, this, false);
      this.container2 = this.container;
      BlockPos blockPos = world.getBlockPos();
      this.applyDoubleByDouble((double)blockPos.getX() + 0.5, (double)(blockPos.getY() + 1), (double)blockPos.getZ() + 0.5, 0.0F, 0.0F);
      this.factor21 = 180.0F;
      this.count3 = 20;
   }
}
