package net.minecraft.entity.passive;

import java.util.Random;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Tuple;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowGolem;
import net.minecraft.entity.ai.EntityAIHarvestFarmland;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIPlay;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITradePlayer;
import net.minecraft.entity.ai.EntityAIVillagerInteract;
import net.minecraft.entity.ai.EntityAIVillagerMate;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.Village;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityVillager extends EntityAgeable implements INpc, IMerchant {
   private int randomTickDivider;
   private boolean isMating;
   private boolean isPlaying;
   public Village villageObj;
   private EntityPlayer buyingPlayer;
   private MerchantRecipeList buyingList;
   private int timeUntilReset;
   private boolean needsInitilization;
   private boolean isWillingToMate;
   private int wealth;
   private String lastBuyingPlayer;
   public int careerId;
   public int careerLevel;
   private boolean isLookingForHome;
   private boolean areAdditionalTasksSet;
   private InventoryBasic villagerInventory = new InventoryBasic("Items", false, 8);
   private static final EntityVillager.ITradeList[][][][] DEFAULT_TRADE_LIST_MAP = new EntityVillager.ITradeList[][][][]{
      {
            {
                  {
                        new EntityVillager.EmeraldForItems(Items.wheat, new EntityVillager.PriceInfo(18, 22)),
                        new EntityVillager.EmeraldForItems(Items.potato, new EntityVillager.PriceInfo(15, 19)),
                        new EntityVillager.EmeraldForItems(Items.carrot, new EntityVillager.PriceInfo(15, 19)),
                        new EntityVillager.ListItemForEmeralds(Items.bread, new EntityVillager.PriceInfo(-4, -2))
                  },
                  {
                        new EntityVillager.EmeraldForItems(Item.getItemFromBlock(Blocks.pumpkin), new EntityVillager.PriceInfo(8, 13)),
                        new EntityVillager.ListItemForEmeralds(Items.pumpkin_pie, new EntityVillager.PriceInfo(-3, -2))
                  },
                  {
                        new EntityVillager.EmeraldForItems(Item.getItemFromBlock(Blocks.melon_block), new EntityVillager.PriceInfo(7, 12)),
                        new EntityVillager.ListItemForEmeralds(Items.apple, new EntityVillager.PriceInfo(-5, -7))
                  },
                  {
                        new EntityVillager.ListItemForEmeralds(Items.cookie, new EntityVillager.PriceInfo(-6, -10)),
                        new EntityVillager.ListItemForEmeralds(Items.cake, new EntityVillager.PriceInfo(1, 1))
                  }
            },
            {
                  {
                        new EntityVillager.EmeraldForItems(Items.string, new EntityVillager.PriceInfo(15, 20)),
                        new EntityVillager.EmeraldForItems(Items.coal, new EntityVillager.PriceInfo(16, 24)),
                        new EntityVillager.ItemAndEmeraldToItem(
                           Items.fish, new EntityVillager.PriceInfo(6, 6), Items.cooked_fish, new EntityVillager.PriceInfo(6, 6)
                        )
                  },
                  {new EntityVillager.ListEnchantedItemForEmeralds(Items.fishing_rod, new EntityVillager.PriceInfo(7, 8))}
            },
            {
                  {
                        new EntityVillager.EmeraldForItems(Item.getItemFromBlock(Blocks.wool), new EntityVillager.PriceInfo(16, 22)),
                        new EntityVillager.ListItemForEmeralds(Items.shears, new EntityVillager.PriceInfo(3, 4))
                  },
                  {
                        new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 0), new EntityVillager.PriceInfo(1, 2)),
                        new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 1), new EntityVillager.PriceInfo(1, 2)),
                        new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 2), new EntityVillager.PriceInfo(1, 2)),
                        new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 3), new EntityVillager.PriceInfo(1, 2)),
                        new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 4), new EntityVillager.PriceInfo(1, 2)),
                        new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 5), new EntityVillager.PriceInfo(1, 2)),
                        new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 6), new EntityVillager.PriceInfo(1, 2)),
                        new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 7), new EntityVillager.PriceInfo(1, 2)),
                        new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 8), new EntityVillager.PriceInfo(1, 2)),
                        new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 9), new EntityVillager.PriceInfo(1, 2)),
                        new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 10), new EntityVillager.PriceInfo(1, 2)),
                        new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 11), new EntityVillager.PriceInfo(1, 2)),
                        new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 12), new EntityVillager.PriceInfo(1, 2)),
                        new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 13), new EntityVillager.PriceInfo(1, 2)),
                        new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 14), new EntityVillager.PriceInfo(1, 2)),
                        new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 15), new EntityVillager.PriceInfo(1, 2))
                  }
            },
            {
                  {
                        new EntityVillager.EmeraldForItems(Items.string, new EntityVillager.PriceInfo(15, 20)),
                        new EntityVillager.ListItemForEmeralds(Items.arrow, new EntityVillager.PriceInfo(-12, -8))
                  },
                  {
                        new EntityVillager.ListItemForEmeralds(Items.bow, new EntityVillager.PriceInfo(2, 3)),
                        new EntityVillager.ItemAndEmeraldToItem(
                           Item.getItemFromBlock(Blocks.gravel), new EntityVillager.PriceInfo(10, 10), Items.flint, new EntityVillager.PriceInfo(6, 10)
                        )
                  }
            }
      },
      {
            {
                  {new EntityVillager.EmeraldForItems(Items.paper, new EntityVillager.PriceInfo(24, 36)), new EntityVillager.ListEnchantedBookForEmeralds()},
                  {
                        new EntityVillager.EmeraldForItems(Items.book, new EntityVillager.PriceInfo(8, 10)),
                        new EntityVillager.ListItemForEmeralds(Items.compass, new EntityVillager.PriceInfo(10, 12)),
                        new EntityVillager.ListItemForEmeralds(Item.getItemFromBlock(Blocks.bookshelf), new EntityVillager.PriceInfo(3, 4))
                  },
                  {
                        new EntityVillager.EmeraldForItems(Items.written_book, new EntityVillager.PriceInfo(2, 2)),
                        new EntityVillager.ListItemForEmeralds(Items.clock, new EntityVillager.PriceInfo(10, 12)),
                        new EntityVillager.ListItemForEmeralds(Item.getItemFromBlock(Blocks.glass), new EntityVillager.PriceInfo(-5, -3))
                  },
                  {new EntityVillager.ListEnchantedBookForEmeralds()},
                  {new EntityVillager.ListEnchantedBookForEmeralds()},
                  {new EntityVillager.ListItemForEmeralds(Items.name_tag, new EntityVillager.PriceInfo(20, 22))}
            }
      },
      {
            {
                  {
                        new EntityVillager.EmeraldForItems(Items.rotten_flesh, new EntityVillager.PriceInfo(36, 40)),
                        new EntityVillager.EmeraldForItems(Items.gold_ingot, new EntityVillager.PriceInfo(8, 10))
                  },
                  {
                        new EntityVillager.ListItemForEmeralds(Items.redstone, new EntityVillager.PriceInfo(-4, -1)),
                        new EntityVillager.ListItemForEmeralds(
                           new ItemStack(Items.dye, 1, EnumDyeColor.BLUE.getDyeDamage()), new EntityVillager.PriceInfo(-2, -1)
                        )
                  },
                  {
                        new EntityVillager.ListItemForEmeralds(Items.ender_eye, new EntityVillager.PriceInfo(7, 11)),
                        new EntityVillager.ListItemForEmeralds(Item.getItemFromBlock(Blocks.glowstone), new EntityVillager.PriceInfo(-3, -1))
                  },
                  {new EntityVillager.ListItemForEmeralds(Items.experience_bottle, new EntityVillager.PriceInfo(3, 11))}
            }
      },
      {
            {
                  {
                        new EntityVillager.EmeraldForItems(Items.coal, new EntityVillager.PriceInfo(16, 24)),
                        new EntityVillager.ListItemForEmeralds(Items.iron_helmet, new EntityVillager.PriceInfo(4, 6))
                  },
                  {
                        new EntityVillager.EmeraldForItems(Items.iron_ingot, new EntityVillager.PriceInfo(7, 9)),
                        new EntityVillager.ListItemForEmeralds(Items.iron_chestplate, new EntityVillager.PriceInfo(10, 14))
                  },
                  {
                        new EntityVillager.EmeraldForItems(Items.diamond, new EntityVillager.PriceInfo(3, 4)),
                        new EntityVillager.ListEnchantedItemForEmeralds(Items.diamond_chestplate, new EntityVillager.PriceInfo(16, 19))
                  },
                  {
                        new EntityVillager.ListItemForEmeralds(Items.chainmail_boots, new EntityVillager.PriceInfo(5, 7)),
                        new EntityVillager.ListItemForEmeralds(Items.chainmail_leggings, new EntityVillager.PriceInfo(9, 11)),
                        new EntityVillager.ListItemForEmeralds(Items.chainmail_helmet, new EntityVillager.PriceInfo(5, 7)),
                        new EntityVillager.ListItemForEmeralds(Items.chainmail_chestplate, new EntityVillager.PriceInfo(11, 15))
                  }
            },
            {
                  {
                        new EntityVillager.EmeraldForItems(Items.coal, new EntityVillager.PriceInfo(16, 24)),
                        new EntityVillager.ListItemForEmeralds(Items.iron_axe, new EntityVillager.PriceInfo(6, 8))
                  },
                  {
                        new EntityVillager.EmeraldForItems(Items.iron_ingot, new EntityVillager.PriceInfo(7, 9)),
                        new EntityVillager.ListEnchantedItemForEmeralds(Items.iron_sword, new EntityVillager.PriceInfo(9, 10))
                  },
                  {
                        new EntityVillager.EmeraldForItems(Items.diamond, new EntityVillager.PriceInfo(3, 4)),
                        new EntityVillager.ListEnchantedItemForEmeralds(Items.diamond_sword, new EntityVillager.PriceInfo(12, 15)),
                        new EntityVillager.ListEnchantedItemForEmeralds(Items.diamond_axe, new EntityVillager.PriceInfo(9, 12))
                  }
            },
            {
                  {
                        new EntityVillager.EmeraldForItems(Items.coal, new EntityVillager.PriceInfo(16, 24)),
                        new EntityVillager.ListEnchantedItemForEmeralds(Items.iron_shovel, new EntityVillager.PriceInfo(5, 7))
                  },
                  {
                        new EntityVillager.EmeraldForItems(Items.iron_ingot, new EntityVillager.PriceInfo(7, 9)),
                        new EntityVillager.ListEnchantedItemForEmeralds(Items.iron_pickaxe, new EntityVillager.PriceInfo(9, 11))
                  },
                  {
                        new EntityVillager.EmeraldForItems(Items.diamond, new EntityVillager.PriceInfo(3, 4)),
                        new EntityVillager.ListEnchantedItemForEmeralds(Items.diamond_pickaxe, new EntityVillager.PriceInfo(12, 15))
                  }
            }
      },
      {
            {
                  {
                        new EntityVillager.EmeraldForItems(Items.porkchop, new EntityVillager.PriceInfo(14, 18)),
                        new EntityVillager.EmeraldForItems(Items.chicken, new EntityVillager.PriceInfo(14, 18))
                  },
                  {
                        new EntityVillager.EmeraldForItems(Items.coal, new EntityVillager.PriceInfo(16, 24)),
                        new EntityVillager.ListItemForEmeralds(Items.cooked_porkchop, new EntityVillager.PriceInfo(-7, -5)),
                        new EntityVillager.ListItemForEmeralds(Items.cooked_chicken, new EntityVillager.PriceInfo(-8, -6))
                  }
            },
            {
                  {
                        new EntityVillager.EmeraldForItems(Items.leather, new EntityVillager.PriceInfo(9, 12)),
                        new EntityVillager.ListItemForEmeralds(Items.leather_leggings, new EntityVillager.PriceInfo(2, 4))
                  },
                  {new EntityVillager.ListEnchantedItemForEmeralds(Items.leather_chestplate, new EntityVillager.PriceInfo(7, 12))},
                  {new EntityVillager.ListItemForEmeralds(Items.saddle, new EntityVillager.PriceInfo(8, 10))}
            }
      }
   };

   public void setMating(boolean flag) {
      this.isMating = flag;
   }

   @Override
   public void onDeath(DamageSource damageSource) {
      if (this.villageObj != null) {
         Entity entity = damageSource.getEntity();
         if (entity != null) {
            if (entity instanceof EntityPlayer) {
               this.villageObj.setReputationForPlayer(entity.getName(), -2);
            } else if (entity instanceof IMob) {
               this.villageObj.endMatingSeason();
            }
         } else {
            if (this.entityWorld.getClosestPlayerToEntity(this, 16.0) != null) {
               this.villageObj.endMatingSeason();
            }
         }
      }

      super.onDeath(damageSource);
   }

   @Override
   public String getDisplayNameString() {
      return this.getDisplayName().getFormattedText();
   }

   public void setPlaying(boolean flag) {
      this.isPlaying = flag;
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficultyInstance, IEntityLivingData entityLivingData) {
      entityLivingData = super.onInitialSpawn(difficultyInstance, entityLivingData);
      this.setProfession(this.entityWorld.rand.nextInt(5));
      this.setAdditionalAItasks();
      return entityLivingData;
   }

   public void setLookingForHome() {
      this.isLookingForHome = true;
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(16, 0);
   }

   public EntityVillager(World world) {
      this(world, 0);
   }

   @Override
   public void onStruckByLightning(EntityLightningBolt entityLightningBolt) {
      if (!this.entityWorld.isRemote && !this.isDead) {
         EntityWitch entityWitch = new EntityWitch(this.entityWorld);
         entityWitch.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
         entityWitch.onInitialSpawn(this.entityWorld.getDifficultyForLocation(new BlockPos(entityWitch)), (IEntityLivingData)null);
         entityWitch.setNoAI(this.isAIDisabled());
         if (this.hasCustomName()) {
            entityWitch.setCustomNameTag(this.getCustomNameTag());
            entityWitch.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
         }

         this.entityWorld.spawnEntityInWorld(entityWitch);
         this.setDead();
      }
   }

   @Override
   public MerchantRecipeList getRecipes(EntityPlayer player) {
      if (this.buyingList == null) {
         this.populateBuyingList();
      }

      return this.buyingList;
   }

   private void populateBuyingList() {
      EntityVillager.ITradeList[][][] tradeListsArrs = DEFAULT_TRADE_LIST_MAP[this.getProfession()];
      if (this.careerId != 0 && this.careerLevel != 0) {
         this.careerLevel++;
      } else {
         this.careerId = this.rand.nextInt(tradeListsArrs.length) + 1;
         this.careerLevel = 1;
      }

      if (this.buyingList == null) {
         this.buyingList = new MerchantRecipeList();
      }

      int n = this.careerId - 1;
      int n2 = this.careerLevel - 1;
      EntityVillager.ITradeList[][] tradeListsArr = tradeListsArrs[n];
      if (n2 >= 0 && n2 < tradeListsArr.length) {

         for (EntityVillager.ITradeList tradeList : tradeListsArr[n2]) {
            tradeList.modifyMerchantRecipeList(this.buyingList, this.rand);
         }
      }
   }

   @Override
   public String getHurtSound() {
      return "mob.villager.hit";
   }

   private void spawnParticles(EnumParticleTypes enumParticleTypes) {
      for (int i = 0; i < 5; i++) {
         double d = this.rand.nextGaussian() * 0.02;
         double d2 = this.rand.nextGaussian() * 0.02;
         double d3 = this.rand.nextGaussian() * 0.02;
         this.entityWorld
            .spawnParticle(
               enumParticleTypes,
               this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width,
               this.posY + 1.0 + (double)(this.rand.nextFloat() * this.height),
               this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width,
               d,
               d2,
               d3,
               new int[0]
            );
      }
   }

   @Override
   public String getDeathSound() {
      return "mob.villager.death";
   }

   public boolean isFarmItemInInventory() {
      for (int i = 0; i < this.villagerInventory.getSizeInventory(); i++) {
         ItemStack stackInSlot = this.villagerInventory.getStackInSlot(i);
         if (stackInSlot != null && (stackInSlot.getItem() == Items.wheat_seeds || stackInSlot.getItem() == Items.potato || stackInSlot.getItem() == Items.carrot)) {
            return true;
         }
      }

      return false;
   }

   public EntityVillager(World world, int n) {
      super(world);
      this.setProfession(n);
      this.setSize(0.6F, 1.8F);
      ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
      ((PathNavigateGround)this.getNavigator()).setAvoidsWater(true);
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6, 0.6));
      this.tasks.addTask(1, new EntityAITradePlayer(this));
      this.tasks.addTask(1, new EntityAILookAtTradePlayer(this));
      this.tasks.addTask(2, new EntityAIMoveIndoors(this));
      this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
      this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
      this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6));
      this.tasks.addTask(6, new EntityAIVillagerMate(this));
      this.tasks.addTask(7, new EntityAIFollowGolem(this));
      this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
      this.tasks.addTask(9, new EntityAIVillagerInteract(this));
      this.tasks.addTask(9, new EntityAIWander(this, 0.6));
      this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
      this.setCanPickUpLoot(true);
   }

   public void setProfession(int n) {
      this.dataWatcher.updateObject(16, n);
   }

   public boolean hasEnoughFoodToBreed() {
      return this.hasEnoughItems(1);
   }

   public boolean wantsMoreFood() {
      return (this.getProfession() == 0) ? !this.hasEnoughItems(5) : !this.hasEnoughItems(1);
   }

   public boolean isTrading() {
      return this.buyingPlayer != null;
   }

   public EntityVillager createChild(EntityAgeable entityAgeable) {
      EntityVillager entityVillager = new EntityVillager(this.entityWorld);
      entityVillager.onInitialSpawn(this.entityWorld.getDifficultyForLocation(new BlockPos(entityVillager)), (IEntityLivingData)null);
      return entityVillager;
   }

   @Override
   public boolean allowLeashing() {
      return false;
   }

   @Override
   public void onGrowingAdult() {
      if (this.getProfession() == 0) {
         this.tasks.addTask(8, new EntityAIHarvestFarmland(this, 0.6));
      }

      super.onGrowingAdult();
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5);
   }

   public boolean canAbondonItems() {
      return this.hasEnoughItems(2);
   }

   private boolean canVillagerPickupItem(Item item) {
      return item == Items.bread || item == Items.potato || item == Items.carrot || item == Items.wheat || item == Items.wheat_seeds;
   }

   @Override
   public boolean replaceItemInInventory(int n, ItemStack stack) {
      if (super.replaceItemInInventory(n, stack)) {
         return true;
      } else {
         int n2 = n - 300;
         if (n2 >= 0 && n2 < this.villagerInventory.getSizeInventory()) {
            this.villagerInventory.setInventorySlotContents(n2, stack);
            return true;
         } else {
            return false;
         }
      }
   }

   @Override
   public boolean canDespawn() {
      return false;
   }

   @Override
   public boolean interact(EntityPlayer player) {
      ItemStack currentItem = player.inventory.getCurrentItem();
      if (!(currentItem != null && currentItem.getItem() == Items.spawn_egg) && this.isEntityAlive() && !this.isTrading() && !this.isChild()) {
         if (!this.entityWorld.isRemote && (this.buyingList == null || this.buyingList.size() > 0)) {
            this.setCustomer(player);
            player.displayVillagerTradeGui(this);
         }

         player.triggerAchievement(StatList.timesTalkedToVillagerStat);
         return true;
      } else {
         return super.interact(player);
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setInteger("Profession", this.getProfession());
      tag.setInteger("Riches", this.wealth);
      tag.setInteger("Career", this.careerId);
      tag.setInteger("CareerLevel", this.careerLevel);
      tag.setBoolean("Willing", this.isWillingToMate);
      if (this.buyingList != null) {
         tag.setTag("Offers", this.buyingList.getRecipiesAsTags());
      }

      NBTTagList tagList = new NBTTagList();

      for (int i = 0; i < this.villagerInventory.getSizeInventory(); i++) {
         ItemStack stackInSlot = this.villagerInventory.getStackInSlot(i);
         if (stackInSlot != null) {
            tagList.appendTag(stackInSlot.writeToNBT(new NBTTagCompound()));
         }
      }

      tag.setTag("Inventory", tagList);
   }

   @Override
   public void updateAITasks() {
      if (--this.randomTickDivider <= 0) {
         BlockPos pos = new BlockPos(this);
         this.entityWorld.getVillageCollection().addToVillagerPositionList(pos);
         this.randomTickDivider = 70 + this.rand.nextInt(50);
         this.villageObj = this.entityWorld.getVillageCollection().getNearestVillage(pos, 32);
         if (this.villageObj == null) {
            this.detachHome();
         } else {
            this.setHomePosAndDistance(this.villageObj.getCenter(), (int)((float)this.villageObj.getVillageRadius() * 1.0F));
            if (this.isLookingForHome) {
               this.isLookingForHome = false;
               this.villageObj.setDefaultPlayerReputation(5);
            }
         }
      }

      if (!this.isTrading() && this.timeUntilReset > 0) {
         this.timeUntilReset--;
         if (this.timeUntilReset <= 0) {
            if (this.needsInitilization) {
               for (MerchantRecipe merchantRecipe : (Iterable<MerchantRecipe>)(this.buyingList)) {
                  if (merchantRecipe.isRecipeDisabled()) {
                     merchantRecipe.increaseMaxTradeUses(this.rand.nextInt(6) + this.rand.nextInt(6) + 2);
                  }
               }

               this.populateBuyingList();
               this.needsInitilization = false;
               if (this.villageObj != null && this.lastBuyingPlayer != null) {
                  this.entityWorld.setEntityState(this, (byte)14);
                  this.villageObj.setReputationForPlayer(this.lastBuyingPlayer, 1);
               }
            }

            this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 0));
         }
      }

      super.updateAITasks();
   }

   @Override
   public void setCustomer(EntityPlayer player) {
      this.buyingPlayer = player;
   }

   public InventoryBasic getVillagerInventory() {
      return this.villagerInventory;
   }

   @Override
   public void setRecipes(MerchantRecipeList merchantRecipeList) {
   }

   @Override
   public void verifySellingItem(ItemStack stack) {
      if (!this.entityWorld.isRemote && this.livingSoundTime > -this.getTalkInterval() + 20) {
         this.livingSoundTime = -this.getTalkInterval();
         if (stack != null) {
            this.playSound("mob.villager.yes", this.getSoundVolume(), this.getSoundPitch());
         } else {
            this.playSound("mob.villager.no", this.getSoundVolume(), this.getSoundPitch());
         }
      }
   }

   @Override
   public String getLivingSound() {
      return this.isTrading() ? "mob.villager.haggle" : "mob.villager.idle";
   }

   public void setIsWillingToMate(boolean flag) {
      this.isWillingToMate = flag;
   }

   @Override
   public EntityPlayer getCustomer() {
      return this.buyingPlayer;
   }

   @Override
   public IChatComponent getDisplayName() {
      String customNameTag = this.getCustomNameTag();
      if (customNameTag != null && customNameTag.length() > 0) {
         ChatComponentText chatComponentText = new ChatComponentText(customNameTag);
         chatComponentText.getChatStyle().setChatHoverEvent(this.getHoverEvent());
         chatComponentText.getChatStyle().setInsertion(this.getUniqueID().toString());
         return chatComponentText;
      } else {
         if (this.buyingList == null) {
            this.populateBuyingList();
         }

         String str = null;
         switch (this.getProfession()) {
            case 0:
               if (this.careerId == 1) {
                  str = "farmer";
               } else if (this.careerId == 2) {
                  str = "fisherman";
               } else if (this.careerId == 3) {
                  str = "shepherd";
               } else if (this.careerId == 4) {
                  str = "fletcher";
               }
               break;
            case 1:
               str = "librarian";
               break;
            case 2:
               str = "cleric";
               break;
            case 3:
               if (this.careerId == 1) {
                  str = "armor";
               } else if (this.careerId == 2) {
                  str = "weapon";
               } else if (this.careerId == 3) {
                  str = "tool";
               }
               break;
            case 4:
               if (this.careerId == 1) {
                  str = "butcher";
               } else if (this.careerId == 2) {
                  str = "leather";
               }
         }

         if (str != null) {
            ChatComponentTranslation chatComponentTranslation = new ChatComponentTranslation("entity.Villager." + str, new Object[0]);
            chatComponentTranslation.getChatStyle().setChatHoverEvent(this.getHoverEvent());
            chatComponentTranslation.getChatStyle().setInsertion(this.getUniqueID().toString());
            return chatComponentTranslation;
         } else {
            return super.getDisplayName();
         }
      }
   }

   @Override
   public float getEyeHeight() {
      float f = 1.62F;
      if (this.isChild()) {
         f = (float)((double)f - 0.81);
      }

      return f;
   }

   public boolean isMating() {
      return this.isMating;
   }

   private void setAdditionalAItasks() {
      if (!this.areAdditionalTasksSet) {
         this.areAdditionalTasksSet = true;
         if (this.isChild()) {
            this.tasks.addTask(8, new EntityAIPlay(this, 0.32));
         } else if (this.getProfession() == 0) {
            this.tasks.addTask(6, new EntityAIHarvestFarmland(this, 0.6));
         }
      }
   }

   public boolean isPlaying() {
      return this.isPlaying;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.setProfession(tag.getInteger("Profession"));
      this.wealth = tag.getInteger("Riches");
      this.careerId = tag.getInteger("Career");
      this.careerLevel = tag.getInteger("CareerLevel");
      this.isWillingToMate = tag.getBoolean("Willing");
      if (tag.hasKey("Offers", 10)) {
         NBTTagCompound compoundTag = tag.getCompoundTag("Offers");
         this.buyingList = new MerchantRecipeList(compoundTag);
      }

      NBTTagList tagList = tag.getTagList("Inventory", 10);

      for (int i = 0; i < tagList.tagCount(); i++) {
         ItemStack stack = ItemStack.loadItemStackFromNBT(tagList.getCompoundTagAt(i));
         if (stack != null) {
            this.villagerInventory.addItem(stack);
         }
      }

      this.setCanPickUpLoot(true);
      this.setAdditionalAItasks();
   }

   @Override
   public EntityAgeable createAgeableChild(EntityAgeable entityAgeable) {
      return this.createChild(entityAgeable);
   }

   @Override
   public void useRecipe(MerchantRecipe merchantRecipe) {
      merchantRecipe.incrementToolUses();
      this.livingSoundTime = -this.getTalkInterval();
      this.playSound("mob.villager.yes", this.getSoundVolume(), this.getSoundPitch());
      int n = 3 + this.rand.nextInt(4);
      if (merchantRecipe.getToolUses() == 1 || this.rand.nextInt(5) == 0) {
         this.timeUntilReset = 40;
         this.needsInitilization = true;
         this.isWillingToMate = true;
         if (this.buyingPlayer != null) {
            this.lastBuyingPlayer = this.buyingPlayer.getName();
         } else {
            this.lastBuyingPlayer = null;
         }

         n += 5;
      }

      if (merchantRecipe.getItemToBuy().getItem() == Items.emerald) {
         this.wealth = this.wealth + merchantRecipe.getItemToBuy().stackSize;
      }

      if (merchantRecipe.getRewardsExp()) {
         this.entityWorld.spawnEntityInWorld(new EntityXPOrb(this.entityWorld, this.posX, this.posY + 0.5, this.posZ, n));
      }
   }

   private boolean hasEnoughItems(int n) {
      boolean flag = this.getProfession() == 0;

      for (int i = 0; i < this.villagerInventory.getSizeInventory(); i++) {
         ItemStack stackInSlot = this.villagerInventory.getStackInSlot(i);
         if (stackInSlot != null) {
            if (stackInSlot.getItem() == Items.bread && stackInSlot.stackSize >= 3 * n
               || stackInSlot.getItem() == Items.potato && stackInSlot.stackSize >= 12 * n
               || stackInSlot.getItem() == Items.carrot && stackInSlot.stackSize >= 12 * n) {
               return true;
            }

            if (flag && stackInSlot.getItem() == Items.wheat && stackInSlot.stackSize >= 9 * n) {
               return true;
            }
         }
      }

      return false;
   }

   public int getProfession() {
      return Math.max(this.dataWatcher.getWatchableObjectInt(16) % 5, 0);
   }

   public boolean getIsWillingToMate(boolean flag) {
      if (!this.isWillingToMate && flag && this.hasEnoughFoodToBreed()) {
         boolean flag2 = false;

         for (int i = 0; i < this.villagerInventory.getSizeInventory(); i++) {
            ItemStack stackInSlot = this.villagerInventory.getStackInSlot(i);
            if (stackInSlot != null) {
               if (stackInSlot.getItem() == Items.bread && stackInSlot.stackSize >= 3) {
                  flag2 = true;
                  this.villagerInventory.decrStackSize(i, 3);
               } else if ((stackInSlot.getItem() == Items.potato || stackInSlot.getItem() == Items.carrot) && stackInSlot.stackSize >= 12) {
                  flag2 = true;
                  this.villagerInventory.decrStackSize(i, 12);
               }
            }

            if (flag2) {
               this.entityWorld.setEntityState(this, (byte)18);
               this.isWillingToMate = true;
               break;
            }
         }
      }

      return this.isWillingToMate;
   }

   @Override
   public void setRevengeTarget(EntityLivingBase entity) {
      super.setRevengeTarget(entity);
      if (this.villageObj != null && entity != null) {
         this.villageObj.addOrRenewAgressor(entity);
         if (entity instanceof EntityPlayer) {
            byte b = -1;
            if (this.isChild()) {
               b = -3;
            }

            this.villageObj.setReputationForPlayer(entity.getName(), b);
            if (this.isEntityAlive()) {
               this.entityWorld.setEntityState(this, (byte)13);
            }
         }
      }
   }

   @Override
   public void updateEquipmentIfNeeded(EntityItem entityItem) {
      ItemStack entityItem2 = entityItem.getEntityItem();
      if (this.canVillagerPickupItem(entityItem2.getItem())) {
         ItemStack stack = this.villagerInventory.addItem(entityItem2);
         if (stack == null) {
            entityItem.setDead();
         } else {
            entityItem2.stackSize = stack.stackSize;
         }
      }
   }

   @Override
   public void handleStatusUpdate(byte b) {
      if (b == 12) {
         this.spawnParticles(EnumParticleTypes.HEART);
      } else if (b == 13) {
         this.spawnParticles(EnumParticleTypes.VILLAGER_ANGRY);
      } else if (b == 14) {
         this.spawnParticles(EnumParticleTypes.VILLAGER_HAPPY);
      } else {
         super.handleStatusUpdate(b);
      }
   }


   public static class PriceInfo extends Tuple {
      public PriceInfo(int n, int n2) {
         super(n, n2);
      }

      public int getPrice(Random rand) {
         return (Integer)this.getFirst() >= (Integer)this.getSecond()
            ? (Integer)this.getFirst()
            : (Integer)this.getFirst() + rand.nextInt((Integer)this.getSecond() - (Integer)this.getFirst() + 1);
      }
   }

   public interface ITradeList {
      void modifyMerchantRecipeList(MerchantRecipeList merchantRecipeList, Random rand);
   }

   public static class EmeraldForItems implements EntityVillager.ITradeList {
      public Item sellItem;
      public EntityVillager.PriceInfo price;

      public EmeraldForItems(Item item, EntityVillager.PriceInfo priceInfo) {
         this.sellItem = item;
         this.price = priceInfo;
      }

      @Override
      public void modifyMerchantRecipeList(MerchantRecipeList merchantRecipeList, Random rand) {
         int n = 1;
         if (this.price != null) {
            n = this.price.getPrice(rand);
         }

         merchantRecipeList.add(new MerchantRecipe(new ItemStack(this.sellItem, n, 0), Items.emerald));
      }
   }

   public static class ListItemForEmeralds implements EntityVillager.ITradeList {
      public ItemStack itemToBuy;
      public EntityVillager.PriceInfo priceInfo;

      public ListItemForEmeralds(Item item, EntityVillager.PriceInfo priceInfo2) {
         this.itemToBuy = new ItemStack(item);
         this.priceInfo = priceInfo2;
      }

      public ListItemForEmeralds(ItemStack stack, EntityVillager.PriceInfo priceInfo2) {
         this.itemToBuy = stack;
         this.priceInfo = priceInfo2;
      }

      @Override
      public void modifyMerchantRecipeList(MerchantRecipeList merchantRecipeList, Random rand) {
         int n = 1;
         if (this.priceInfo != null) {
            n = this.priceInfo.getPrice(rand);
         }

         ItemStack stack;
         ItemStack stack2;
         if (n < 0) {
            stack = new ItemStack(Items.emerald, 1, 0);
            stack2 = new ItemStack(this.itemToBuy.getItem(), -n, this.itemToBuy.getMetadata());
         } else {
            stack = new ItemStack(Items.emerald, n, 0);
            stack2 = new ItemStack(this.itemToBuy.getItem(), 1, this.itemToBuy.getMetadata());
         }

         merchantRecipeList.add(new MerchantRecipe(stack, stack2));
      }
   }

   public static class ItemAndEmeraldToItem implements EntityVillager.ITradeList {
      public ItemStack buyingItemStack;
      public EntityVillager.PriceInfo buyingPriceInfo;
      public ItemStack sellingItemstack;
      public EntityVillager.PriceInfo sellingPriceInfo;

      public ItemAndEmeraldToItem(Item item, EntityVillager.PriceInfo priceInfo, Item item2, EntityVillager.PriceInfo priceInfo2) {
         this.buyingItemStack = new ItemStack(item);
         this.buyingPriceInfo = priceInfo;
         this.sellingItemstack = new ItemStack(item2);
         this.sellingPriceInfo = priceInfo2;
      }

      @Override
      public void modifyMerchantRecipeList(MerchantRecipeList merchantRecipeList, Random rand) {
         int n = 1;
         if (this.buyingPriceInfo != null) {
            n = this.buyingPriceInfo.getPrice(rand);
         }

         int n2 = 1;
         if (this.sellingPriceInfo != null) {
            n2 = this.sellingPriceInfo.getPrice(rand);
         }

         merchantRecipeList.add(
            new MerchantRecipe(
               new ItemStack(this.buyingItemStack.getItem(), n, this.buyingItemStack.getMetadata()),
               new ItemStack(Items.emerald),
               new ItemStack(this.sellingItemstack.getItem(), n2, this.sellingItemstack.getMetadata())
            )
         );
      }
   }

   public static class ListEnchantedBookForEmeralds implements EntityVillager.ITradeList {
      @Override
      public void modifyMerchantRecipeList(MerchantRecipeList merchantRecipeList, Random rand) {
         Enchantment enchantment = Enchantment.enchantmentsBookList[rand.nextInt(Enchantment.enchantmentsBookList.length)];
         int randomIntegerInRange = MathHelper.getRandomIntegerInRange(rand, enchantment.getMinLevel(), enchantment.getMaxLevel());
         ItemStack enchantedItemStack = Items.enchanted_book.getEnchantedItemStack(new EnchantmentData(enchantment, randomIntegerInRange));
         int n = 2 + rand.nextInt(5 + randomIntegerInRange * 10) + 3 * randomIntegerInRange;
         if (n > 64) {
            n = 64;
         }

         merchantRecipeList.add(new MerchantRecipe(new ItemStack(Items.book), new ItemStack(Items.emerald, n), enchantedItemStack));
      }
   }

   public static class ListEnchantedItemForEmeralds implements EntityVillager.ITradeList {
      public ItemStack enchantedItemStack;
      public EntityVillager.PriceInfo priceInfo;

      public ListEnchantedItemForEmeralds(Item item, EntityVillager.PriceInfo priceInfo2) {
         this.enchantedItemStack = new ItemStack(item);
         this.priceInfo = priceInfo2;
      }

      @Override
      public void modifyMerchantRecipeList(MerchantRecipeList merchantRecipeList, Random rand) {
         int n = 1;
         if (this.priceInfo != null) {
            n = this.priceInfo.getPrice(rand);
         }

         ItemStack stack = new ItemStack(Items.emerald, n, 0);
         ItemStack stack2 = new ItemStack(this.enchantedItemStack.getItem(), 1, this.enchantedItemStack.getMetadata());
         stack2 = EnchantmentHelper.addRandomEnchantment(rand, stack2, 5 + rand.nextInt(15));
         merchantRecipeList.add(new MerchantRecipe(stack, stack2));
      }
   }
}
