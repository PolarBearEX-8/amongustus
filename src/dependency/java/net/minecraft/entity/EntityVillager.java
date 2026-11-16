package net.minecraft.entity;

import client.event.LocalListener;
import client.event.PostRenderConsumer;
import client.event.PostRenderNotifier;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.LocalOptionType;
import client.util.AdvancedClientManagerAdapter;
import client.util.AltClientManagerAdapter;
import client.util.AuxiliaryArrayListAdapter;
import client.util.AuxiliaryPredicateEntry;
import client.util.BaseClientManagerAdapter;
import client.util.BaseManagerStyleIChatComponentAdapter;
import client.util.ClientManagerHandler;
import client.util.CompositeClientManagerAdapter;
import client.util.CoreClientManagerAdapter;
import client.util.DistanceBuffer;
import client.util.GenericClientManagerAdapter;
import client.util.HUIHRVManager;
import client.util.HitBoxPredictor;
import client.util.InterpolationPool;
import client.util.MobGriefingManager;
import client.util.PathfindManagerAdapter;
import client.util.SpecialClientManagerAdapter;
import client.util.SplineChecker;
import client.util.StatCraftItemManager;
import client.util.SupplementalClientManagerAdapter;
import client.util.TargetAccumulator;
import client.util.TertiaryClientManagerAdapter;
import client.util.UUIDEntryAdapter;
import client.util.VectorPipeline;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.AuxiliaryItemEntryAdapter;
import net.minecraft.item.AuxiliaryItemStackEntry;
import net.minecraft.item.GoldenOre;
import net.minecraft.item.InternalItemStackEntry;
import net.minecraft.item.IronAmulet;
import net.minecraft.item.IronChestplate;
import net.minecraft.item.IronHelmet;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemStackEntry;
import net.minecraft.item.Items;
import net.minecraft.item.LocalItemEntry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityVillager extends UUIDEntryAdapter implements LocalListener, InterpolationPool {
   public int count35;
   public boolean enabled31;
   public boolean enabled29;
   public SplineChecker splineChecker;
   public EntityPlayer entityPlayer2;
   public AuxiliaryArrayListAdapter auxiliaryArrayListAdapter;
   public int count33;
   public boolean enabled30;
   public boolean enabled32;
   public int count31;
   public String labelText2;
   public int count36;
   public int count34;
   public boolean enabled36;
   public boolean enabled33;
   public InventoryLargeChest inventoryLargeChest = new InventoryLargeChest("Items", false, 8);
   public static final PostRenderNotifier[][][][] postRenderNotifierArray = new PostRenderNotifier[][][][]{
      {
            {
                  {
                        new IronAmulet(Items.wheat, new ClientManagerHandler(18, 22)),
                        new IronAmulet(Items.potato, new ClientManagerHandler(15, 19)),
                        new IronAmulet(Items.carrot, new ClientManagerHandler(15, 19)),
                        new AuxiliaryItemStackEntry(Items.bread, new ClientManagerHandler(-4, -2))
                  },
                  {
                        new IronAmulet(Item.processAction2(Blocks.block146), new ClientManagerHandler(8, 13)),
                        new AuxiliaryItemStackEntry(Items.pumpkinPie, new ClientManagerHandler(-3, -2))
                  },
                  {
                        new IronAmulet(Item.processAction2(Blocks.block67), new ClientManagerHandler(7, 12)),
                        new AuxiliaryItemStackEntry(Items.apple, new ClientManagerHandler(-5, -7))
                  },
                  {
                        new AuxiliaryItemStackEntry(Items.cookie, new ClientManagerHandler(-6, -10)),
                        new AuxiliaryItemStackEntry(Items.cake, new ClientManagerHandler(1, 1))
                  }
            },
            {
                  {
                        new IronAmulet(Items.string, new ClientManagerHandler(15, 20)),
                        new IronAmulet(Items.coal, new ClientManagerHandler(16, 24)),
                        new ItemStackEntry(Items.fish, new ClientManagerHandler(6, 6), Items.cookedFish, new ClientManagerHandler(6, 6))
                  },
                  {new InternalItemStackEntry(Items.fishingRod, new ClientManagerHandler(7, 8))}
            },
            {
                  {
                        new IronAmulet(Item.processAction2(Blocks.wool), new ClientManagerHandler(16, 22)),
                        new AuxiliaryItemStackEntry(Items.shears, new ClientManagerHandler(3, 4))
                  },
                  {
                        new AuxiliaryItemStackEntry(new ItemStack(Item.processAction2(Blocks.wool), 1, 0), new ClientManagerHandler(1, 2)),
                        new AuxiliaryItemStackEntry(new ItemStack(Item.processAction2(Blocks.wool), 1, 1), new ClientManagerHandler(1, 2)),
                        new AuxiliaryItemStackEntry(new ItemStack(Item.processAction2(Blocks.wool), 1, 2), new ClientManagerHandler(1, 2)),
                        new AuxiliaryItemStackEntry(new ItemStack(Item.processAction2(Blocks.wool), 1, 3), new ClientManagerHandler(1, 2)),
                        new AuxiliaryItemStackEntry(new ItemStack(Item.processAction2(Blocks.wool), 1, 4), new ClientManagerHandler(1, 2)),
                        new AuxiliaryItemStackEntry(new ItemStack(Item.processAction2(Blocks.wool), 1, 5), new ClientManagerHandler(1, 2)),
                        new AuxiliaryItemStackEntry(new ItemStack(Item.processAction2(Blocks.wool), 1, 6), new ClientManagerHandler(1, 2)),
                        new AuxiliaryItemStackEntry(new ItemStack(Item.processAction2(Blocks.wool), 1, 7), new ClientManagerHandler(1, 2)),
                        new AuxiliaryItemStackEntry(new ItemStack(Item.processAction2(Blocks.wool), 1, 8), new ClientManagerHandler(1, 2)),
                        new AuxiliaryItemStackEntry(new ItemStack(Item.processAction2(Blocks.wool), 1, 9), new ClientManagerHandler(1, 2)),
                        new AuxiliaryItemStackEntry(new ItemStack(Item.processAction2(Blocks.wool), 1, 10), new ClientManagerHandler(1, 2)),
                        new AuxiliaryItemStackEntry(new ItemStack(Item.processAction2(Blocks.wool), 1, 11), new ClientManagerHandler(1, 2)),
                        new AuxiliaryItemStackEntry(new ItemStack(Item.processAction2(Blocks.wool), 1, 12), new ClientManagerHandler(1, 2)),
                        new AuxiliaryItemStackEntry(new ItemStack(Item.processAction2(Blocks.wool), 1, 13), new ClientManagerHandler(1, 2)),
                        new AuxiliaryItemStackEntry(new ItemStack(Item.processAction2(Blocks.wool), 1, 14), new ClientManagerHandler(1, 2)),
                        new AuxiliaryItemStackEntry(new ItemStack(Item.processAction2(Blocks.wool), 1, 15), new ClientManagerHandler(1, 2))
                  }
            },
            {
                  {new IronAmulet(Items.string, new ClientManagerHandler(15, 20)), new AuxiliaryItemStackEntry(Items.arrow, new ClientManagerHandler(-12, -8))},
                  {
                        new AuxiliaryItemStackEntry(Items.bow, new ClientManagerHandler(2, 3)),
                        new ItemStackEntry(Item.processAction2(Blocks.block45), new ClientManagerHandler(10, 10), Items.flint, new ClientManagerHandler(6, 10))
                  }
            }
      },
      {
            {
                  {new IronAmulet(Items.paper, new ClientManagerHandler(24, 36)), new TargetAccumulator()},
                  {
                        new IronAmulet(Items.book, new ClientManagerHandler(8, 10)),
                        new AuxiliaryItemStackEntry(Items.compass, new ClientManagerHandler(10, 12)),
                        new AuxiliaryItemStackEntry(Item.processAction2(Blocks.block8), new ClientManagerHandler(3, 4))
                  },
                  {
                        new IronAmulet(Items.writtenBook, new ClientManagerHandler(2, 2)),
                        new AuxiliaryItemStackEntry(Items.clock, new ClientManagerHandler(10, 12)),
                        new AuxiliaryItemStackEntry(Item.processAction2(Blocks.block68), new ClientManagerHandler(-5, -3))
                  },
                  {new TargetAccumulator()},
                  {new TargetAccumulator()},
                  {new AuxiliaryItemStackEntry(Items.nameTag, new ClientManagerHandler(20, 22))}
            }
      },
      {
            {
                  {new IronAmulet(Items.rottenFlesh, new ClientManagerHandler(36, 40)), new IronAmulet(Items.goldIngot, new ClientManagerHandler(8, 10))},
                  {
                        new AuxiliaryItemStackEntry(Items.redstone, new ClientManagerHandler(-4, -1)),
                        new AuxiliaryItemStackEntry(new ItemStack(Items.dye, 1, LocalOptionType.BLUE.getCount2()), new ClientManagerHandler(-2, -1))
                  },
                  {
                        new AuxiliaryItemStackEntry(Items.enderEye, new ClientManagerHandler(7, 11)),
                        new AuxiliaryItemStackEntry(Item.processAction2(Blocks.block77), new ClientManagerHandler(-3, -1))
                  },
                  {new AuxiliaryItemStackEntry(Items.experienceBottle, new ClientManagerHandler(3, 11))}
            }
      },
      {
            {
                  {new IronAmulet(Items.coal, new ClientManagerHandler(16, 24)), new AuxiliaryItemStackEntry(Items.ironHelmet, new ClientManagerHandler(4, 6))},
                  {
                        new IronAmulet(Items.ironIngot, new ClientManagerHandler(7, 9)),
                        new AuxiliaryItemStackEntry(Items.ironChestplate, new ClientManagerHandler(10, 14))
                  },
                  {
                        new IronAmulet(Items.diamond, new ClientManagerHandler(3, 4)),
                        new InternalItemStackEntry(Items.diamondChestplate, new ClientManagerHandler(16, 19))
                  },
                  {
                        new AuxiliaryItemStackEntry(Items.chainmailBoots, new ClientManagerHandler(5, 7)),
                        new AuxiliaryItemStackEntry(Items.chainmailLeggings, new ClientManagerHandler(9, 11)),
                        new AuxiliaryItemStackEntry(Items.chainmailHelmet, new ClientManagerHandler(5, 7)),
                        new AuxiliaryItemStackEntry(Items.chainmailChestplate, new ClientManagerHandler(11, 15))
                  }
            },
            {
                  {new IronAmulet(Items.coal, new ClientManagerHandler(16, 24)), new AuxiliaryItemStackEntry(Items.ironAxe, new ClientManagerHandler(6, 8))},
                  {
                        new IronAmulet(Items.ironIngot, new ClientManagerHandler(7, 9)),
                        new InternalItemStackEntry(Items.ironSword, new ClientManagerHandler(9, 10))
                  },
                  {
                        new IronAmulet(Items.diamond, new ClientManagerHandler(3, 4)),
                        new InternalItemStackEntry(Items.diamondSword, new ClientManagerHandler(12, 15)),
                        new InternalItemStackEntry(Items.diamondAxe, new ClientManagerHandler(9, 12))
                  }
            },
            {
                  {new IronAmulet(Items.coal, new ClientManagerHandler(16, 24)), new InternalItemStackEntry(Items.ironShovel, new ClientManagerHandler(5, 7))},
                  {
                        new IronAmulet(Items.ironIngot, new ClientManagerHandler(7, 9)),
                        new InternalItemStackEntry(Items.ironPickaxe, new ClientManagerHandler(9, 11))
                  },
                  {
                        new IronAmulet(Items.diamond, new ClientManagerHandler(3, 4)),
                        new InternalItemStackEntry(Items.diamondPickaxe, new ClientManagerHandler(12, 15))
                  }
            }
      },
      {
            {
                  {new IronAmulet(Items.porkchop, new ClientManagerHandler(14, 18)), new IronAmulet(Items.chicken, new ClientManagerHandler(14, 18))},
                  {
                        new IronAmulet(Items.coal, new ClientManagerHandler(16, 24)),
                        new AuxiliaryItemStackEntry(Items.cookedPorkchop, new ClientManagerHandler(-7, -5)),
                        new AuxiliaryItemStackEntry(Items.cookedChicken, new ClientManagerHandler(-8, -6))
                  }
            },
            {
                  {
                        new IronAmulet(Items.leather, new ClientManagerHandler(9, 12)),
                        new AuxiliaryItemStackEntry(Items.leatherLeggings, new ClientManagerHandler(2, 4))
                  },
                  {new InternalItemStackEntry(Items.leatherChestplate, new ClientManagerHandler(7, 12))},
                  {new AuxiliaryItemStackEntry(Items.saddle, new ClientManagerHandler(8, 10))}
            }
      }
   };

   @Override
   public boolean isActive26() {
      return false;
   }

   @Override
   public void applyEntityItem(EntityItem entityItem) {
      ItemStack stack = entityItem.getItemStack();
      Item item = stack.getItem();
      if (this.matchesItem(item)) {
         ItemStack stack2 = this.inventoryLargeChest.resolveItemStack(stack);
         if (stack2 == null) {
            entityItem.updateState9();
         } else {
            stack.animationsToGo = stack2.animationsToGo;
         }
      }
   }

   @Override
   public String getText9() {
      return "mob.villager.death";
   }

   public EntityVillager resolveEntityVillager(UUIDEntryAdapter uUIDEntryAdapter) {
      EntityVillager entityVillager = new EntityVillager(this.worldObj);
      entityVillager.resolvePostRenderConsumer(this.worldObj.resolveGoldenOre(new BlockPos(entityVillager)), (PostRenderConsumer)null);
      return entityVillager;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.applyIndex10(tagCompound.computeCount("Profession"));
      this.count31 = tagCompound.computeCount("Riches");
      this.count36 = tagCompound.computeCount("Career");
      this.count34 = tagCompound.computeCount("CareerLevel");
      this.enabled32 = tagCompound.matchesText("Willing");
      if (tagCompound.matchesText("Offers", 10)) {
         NBTTagCompound nBTTagCompound = tagCompound.getNBTTagCompound("Offers");
         this.auxiliaryArrayListAdapter = new AuxiliaryArrayListAdapter(nBTTagCompound);
      }

      NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Inventory", 10);

      for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
         ItemStack stack = ItemStack.resolveItemStack(extendedLoggerEntry.getNBTTagCompound(index));
         if (stack != null) {
            this.inventoryLargeChest.resolveItemStack(stack);
         }
      }

      this.setEnabled22(true);
      this.updateState49();
   }

   @Override
   public float getHeight2() {
      float factor = 1.62F;
      if (this.isActive16()) {
         factor = (float)((double)factor - 0.81);
      }

      return factor;
   }

   public boolean checkFlag(boolean flag) {
      if (!this.enabled32 && flag && this.isActive33()) {
         boolean flag2 = false;

         for (int index = 0; index < this.inventoryLargeChest.processAction4(); index++) {
            ItemStack stack = this.inventoryLargeChest.processAction3(index);
            if (stack != null) {
               if (stack.getItem() == Items.bread && stack.animationsToGo >= 3) {
                  flag2 = true;
                  this.inventoryLargeChest.getItemStack(index, 3);
               } else if ((stack.getItem() == Items.potato || stack.getItem() == Items.carrot) && stack.animationsToGo >= 12) {
                  flag2 = true;
                  this.inventoryLargeChest.getItemStack(index, 12);
               }
            }

            if (flag2) {
               this.worldObj.applyEntity(this, (byte)18);
               this.enabled32 = true;
               break;
            }
         }
      }

      return this.enabled32;
   }

   @Override
   public IChatComponent getChatComponent() {
      String text = this.getText2();
      if (text != null && text.length() > 0) {
         ChatComponentText chatComponentText = new ChatComponentText(text);
         chatComponentText.getStyleHasParentManager().getStyleHasParentManager(this.getHoverEventActionManager());
         chatComponentText.getStyleHasParentManager().withLabelText(this.getEntityUniqueID().toString());
         return chatComponentText;
      } else {
         if (this.auxiliaryArrayListAdapter == null) {
            this.updateState50();
         }

         String text2 = null;
         switch (this.getCount10()) {
            case 0:
               if (this.count36 == 1) {
                  text2 = "farmer";
               } else if (this.count36 == 2) {
                  text2 = "fisherman";
               } else if (this.count36 == 3) {
                  text2 = "shepherd";
               } else if (this.count36 == 4) {
                  text2 = "fletcher";
               }
               break;
            case 1:
               text2 = "librarian";
               break;
            case 2:
               text2 = "cleric";
               break;
            case 3:
               if (this.count36 == 1) {
                  text2 = "armor";
               } else if (this.count36 == 2) {
                  text2 = "weapon";
               } else if (this.count36 == 3) {
                  text2 = "tool";
               }
               break;
            case 4:
               if (this.count36 == 1) {
                  text2 = "butcher";
               } else if (this.count36 == 2) {
                  text2 = "leather";
               }
         }

         if (text2 != null) {
            BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter("entity.Villager." + text2, new Object[0]);
            baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().getStyleHasParentManager(this.getHoverEventActionManager());
            baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withLabelText(this.getEntityUniqueID().toString());
            return baseManagerStyleIChatComponentAdapter;
         } else {
            return super.getChatComponent();
         }
      }
   }

   @Override
   public boolean matchesItemStack(int count, ItemStack stack) {
      if (super.matchesItemStack(count, stack)) {
         return true;
      } else {
         int count2 = count - 300;
         if (count2 >= 0 && count2 < this.inventoryLargeChest.processAction4()) {
            this.inventoryLargeChest.processAction(count2, stack);
            return true;
         } else {
            return false;
         }
      }
   }

   @Override
   public void setEntityPlayer2(EntityPlayer entityPlayer) {
      this.entityPlayer2 = entityPlayer;
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.5);
   }

   public void applyIndex10(int count) {
      this.dataWatcher.applyTargetByTarget(16, count);
   }

   private void applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType attackEffectsExplosionNormalType) {
      for (int index = 0; index < 5; index++) {
         double offset = this.random.nextGaussian() * 0.02;
         double offset2 = this.random.nextGaussian() * 0.02;
         double offset3 = this.random.nextGaussian() * 0.02;
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               attackEffectsExplosionNormalType,
               this.posX + (double)(this.random.nextFloat() * this.width * 2.0F) - (double)this.width,
               this.posY + 1.0 + (double)(this.random.nextFloat() * this.height),
               this.posZ + (double)(this.random.nextFloat() * this.width * 2.0F) - (double)this.width,
               offset,
               offset2,
               offset3,
               new int[0]
            );
      }
   }

   public InventoryLargeChest getInventoryLargeChest() {
      return this.inventoryLargeChest;
   }

   @Override
   public void processByte(byte byteValue) {
      if (byteValue == 12) {
         this.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.HEART);
      } else if (byteValue == 13) {
         this.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.VILLAGER_ANGRY);
      } else if (byteValue == 14) {
         this.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.VILLAGER_HAPPY);
      } else {
         super.processByte(byteValue);
      }
   }

   @Override
   public UUIDEntryAdapter resolveUUIDEntryAdapter(UUIDEntryAdapter uUIDEntryAdapter) {
      return this.resolveEntityVillager(uUIDEntryAdapter);
   }

   @Override
   public void setAuxiliaryArrayListAdapter(AuxiliaryArrayListAdapter auxiliaryArrayListAdapter2) {
   }

   @Override
   public AuxiliaryArrayListAdapter resolveAuxiliaryArrayListAdapterByEntityPlayer(EntityPlayer player) {
      if (this.auxiliaryArrayListAdapter == null) {
         this.updateState50();
      }

      return this.auxiliaryArrayListAdapter;
   }

   @Override
   public boolean matchesEntityPlayer6(EntityPlayer player) {
      ItemStack slot = player.inventoryPlayer.getCurrentItem();
      if ((slot == null || slot.getItem() != Items.spawnEgg) && this.canExecute() && !this.isActive32() && !this.isActive16()) {
         if (!this.worldObj.state && (this.auxiliaryArrayListAdapter == null || this.auxiliaryArrayListAdapter.size() > 0)) {
            this.setEntityPlayer2(player);
            player.applyLocalListener(this);
         }

         player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager26);
         return true;
      } else {
         return super.matchesEntityPlayer6(player);
      }
   }

   private boolean checkIndex2(int count) {
      boolean flag = this.getCount10() == 0;

      for (int index = 0; index < this.inventoryLargeChest.processAction4(); index++) {
         ItemStack stack = this.inventoryLargeChest.processAction3(index);
         if (stack != null) {
            if (stack.getItem() == Items.bread && stack.animationsToGo >= 3 * count
               || stack.getItem() == Items.potato && stack.animationsToGo >= 12 * count
               || stack.getItem() == Items.carrot && stack.animationsToGo >= 12 * count) {
               return true;
            }

            if (flag && stack.getItem() == Items.wheat && stack.animationsToGo >= 9 * count) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public void applyHUIHRVManager(HUIHRVManager hUIHRVManager) {
      if (!this.worldObj.state && !this.isDead) {
         EntityWitch entityWitch = new EntityWitch(this.worldObj);
         entityWitch.applyDoubleByDouble(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
         entityWitch.resolvePostRenderConsumer(this.worldObj.resolveGoldenOre(new BlockPos(entityWitch)), (PostRenderConsumer)null);
         entityWitch.setEnabled5(this.isActive25());
         if (this.processAction()) {
            entityWitch.setLabelText(this.getText2());
            entityWitch.setEnabled(this.isActive6());
         }

         this.worldObj.matchesEntity(entityWitch);
         this.updateState9();
      }
   }

   @Override
   public String getText3() {
      return this.isActive32() ? "mob.villager.haggle" : "mob.villager.idle";
   }

   private boolean matchesItem(Item item) {
      return item == Items.bread || item == Items.potato || item == Items.carrot || item == Items.wheat || item == Items.wheatSeeds;
   }

   @Override
   public PostRenderConsumer resolvePostRenderConsumer(GoldenOre goldenOre, PostRenderConsumer postRenderConsumer) {
      PostRenderConsumer postRenderConsumer2 = super.resolvePostRenderConsumer(goldenOre, postRenderConsumer);
      this.applyIndex10(this.worldObj.random.nextInt(5));
      this.updateState49();
      return postRenderConsumer2;
   }

   @Override
   public void applyDistanceBuffer(DistanceBuffer distanceBuffer) {
      distanceBuffer.updateState2();
      this.count25 = -this.getCount7();
      this.applyText("mob.villager.yes", this.getFactor(), this.getFactorAsFloat());
      int count = 3 + this.random.nextInt(4);
      if (distanceBuffer.getCount() == 1 || this.random.nextInt(5) == 0) {
         this.count33 = 40;
         this.enabled30 = true;
         this.enabled32 = true;
         if (this.entityPlayer2 != null) {
            this.labelText2 = this.entityPlayer2.getText();
         } else {
            this.labelText2 = null;
         }

         count += 5;
      }

      if (distanceBuffer.getItemStack().getItem() == Items.emerald) {
         this.count31 = this.count31 + distanceBuffer.getItemStack().animationsToGo;
      }

      if (distanceBuffer.isEnabled()) {
         this.worldObj.matchesEntity(new EntityXporb(this.worldObj, this.posX, this.posY + 0.5, this.posZ, count));
      }
   }

   @Override
   public void applyDamageSource(DamageSource damageSource) {
      if (this.splineChecker != null) {
         Entity entity = damageSource.getEntity();
         if (entity != null) {
            if (entity instanceof EntityPlayer) {
               this.splineChecker.computeCount(entity.getText(), -2);
            } else if (entity instanceof VectorPipeline) {
               this.splineChecker.updateState6();
            }
         } else if (this.worldObj.resolveEntityPlayerByEntity(this, 16.0) != null) {
            this.splineChecker.updateState6();
         }
      }

      super.applyDamageSource(damageSource);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("Profession", this.getCount10());
      tagCompound.put("Riches", this.count31);
      tagCompound.put("Career", this.count36);
      tagCompound.put("CareerLevel", this.count34);
      tagCompound.applyText("Willing", this.enabled32);
      if (this.auxiliaryArrayListAdapter != null) {
         tagCompound.applyText("Offers", this.auxiliaryArrayListAdapter.getNBTTagCompound());
      }

      NBTTagList nBTTagList = new NBTTagList();

      for (int index = 0; index < this.inventoryLargeChest.processAction4(); index++) {
         ItemStack stack = this.inventoryLargeChest.processAction3(index);
         if (stack != null) {
            nBTTagList.applyLISTManager(stack.resolveNBTTagCompound(new NBTTagCompound()));
         }
      }

      tagCompound.applyText("Inventory", nBTTagList);
   }

   @Override
   public void applyItemStack(ItemStack stack) {
      if (!this.worldObj.state && this.count25 > -this.getCount7() + 20) {
         this.count25 = -this.getCount7();
         if (stack != null) {
            this.applyText("mob.villager.yes", this.getFactor(), this.getFactorAsFloat());
         } else {
            this.applyText("mob.villager.no", this.getFactor(), this.getFactorAsFloat());
         }
      }
   }

   @Override
   public String getText10() {
      return "mob.villager.hit";
   }

   @Override
   public String getText5() {
      return this.getChatComponent().getString2();
   }

   @Override
   public void applyEntityLivingBase2(EntityLivingBase livingEntity) {
      super.applyEntityLivingBase2(livingEntity);
      if (this.splineChecker != null && livingEntity != null) {
         this.splineChecker.applyEntityLivingBaseByEntityLivingBase(livingEntity);
         if (livingEntity instanceof EntityPlayer) {
            byte byteValue = -1;
            if (this.isActive16()) {
               byteValue = -3;
            }

            this.splineChecker.computeCount(livingEntity.getText(), byteValue);
            if (this.canExecute()) {
               this.worldObj.applyEntity(this, (byte)13);
            }
         }
      }
   }

   @Override
   public EntityPlayer getEntityPlayer2() {
      return this.entityPlayer2;
   }

   public boolean isActive33() {
      return this.checkIndex2(1);
   }

   private void updateState50() {
      PostRenderNotifier[][][] postRenderNotifierMatrix = postRenderNotifierArray[this.getCount10()];
      if (this.count36 != 0 && this.count34 != 0) {
         this.count34++;
      } else {
         this.count36 = this.random.nextInt(postRenderNotifierMatrix.length) + 1;
         this.count34 = 1;
      }

      if (this.auxiliaryArrayListAdapter == null) {
         this.auxiliaryArrayListAdapter = new AuxiliaryArrayListAdapter();
      }

      int count = this.count36 - 1;
      int count2 = this.count34 - 1;
      PostRenderNotifier[][] postRenderNotifierMatrix2 = postRenderNotifierMatrix[count];
      if (count2 >= 0 && count2 < postRenderNotifierMatrix2.length) {
         PostRenderNotifier[] postRenderNotifierArray2 = postRenderNotifierMatrix2[count2];
         int length = postRenderNotifierArray2.length;

         for (int index = 0; index < length; index++) {
            postRenderNotifierArray2[index].applyAuxiliaryArrayListAdapter(this.auxiliaryArrayListAdapter, this.random);
         }
      }
   }

   public void updateState51() {
      this.enabled36 = true;
   }

   @Override
   public void updateState3() {
      if (--this.count35 <= 0) {
         BlockPos blockPos = new BlockPos(this);
         this.worldObj.getWorldCustomProperty().applyBlockPos(blockPos);
         this.count35 = 70 + this.random.nextInt(50);
         this.splineChecker = this.worldObj.getWorldCustomProperty().resolveSplineChecker(blockPos, 32);
         if (this.splineChecker == null) {
            this.updateState47();
         } else {
            BlockPos blockPos2 = this.splineChecker.getBlockPos2();
            this.applyBlockPos(blockPos2, (int)((float)this.splineChecker.getCount() * 1.0F));
            if (this.enabled36) {
               this.enabled36 = false;
               this.splineChecker.applyIndex(5);
            }
         }
      }

      if (!this.isActive32() && this.count33 > 0) {
         this.count33--;
         if (this.count33 <= 0) {
            if (this.enabled30) {
               for (DistanceBuffer distanceBuffer : (Iterable<DistanceBuffer>)(Iterable<?>)this.auxiliaryArrayListAdapter) {
                  if (distanceBuffer.isActive()) {
                     distanceBuffer.applyIndex(this.random.nextInt(6) + this.random.nextInt(6) + 2);
                  }
               }

               this.updateState50();
               this.enabled30 = false;
               if (this.splineChecker != null && this.labelText2 != null) {
                  this.worldObj.applyEntity(this, (byte)14);
                  this.splineChecker.computeCount(this.labelText2, 1);
               }
            }

            this.applyPotionEffectByPotionEffect(new PotionEffect(Potion.potion11.indexOffset3, 200, 0));
         }
      }

      super.updateState3();
   }

   private void updateState49() {
      if (!this.enabled33) {
         this.enabled33 = true;
         if (this.isActive16()) {
            this.goalSetupManager.add(8, new SpecialClientManagerAdapter(this, 0.32));
         } else if (this.getCount10() == 0) {
            this.goalSetupManager.add(6, new MobGriefingManager(this, 0.6));
         }
      }
   }

   public void onEnable(boolean flag) {
      this.enabled32 = flag;
   }

   public int getCount10() {
      return Math.max(this.dataWatcher.computeCount(16) % 5, 0);
   }

   public boolean isActive34() {
      return this.getCount10() == 0 ? !this.checkIndex2(5) : !this.checkIndex2(1);
   }

   @Override
   public void updateState48() {
      if (this.getCount10() == 0) {
         this.goalSetupManager.add(8, new MobGriefingManager(this, 0.6));
      }

      super.updateState48();
   }

   public boolean isEnabled31() {
      return this.enabled31;
   }

   public void setEnabled29(boolean enabled) {
      this.enabled29 = enabled;
   }

   public void setEnabled31(boolean enabled) {
      this.enabled31 = enabled;
   }

   public boolean isActive35() {
      return this.checkIndex2(2);
   }

   public boolean isActive31() {
      for (int index = 0; index < this.inventoryLargeChest.processAction4(); index++) {
         ItemStack stack = this.inventoryLargeChest.processAction3(index);
         if (stack != null && (stack.getItem() == Items.wheatSeeds || stack.getItem() == Items.potato || stack.getItem() == Items.carrot)) {
            return true;
         }
      }

      return false;
   }

   public boolean isActive32() {
      return this.entityPlayer2 != null;
   }

   public boolean isEnabled29() {
      return this.enabled29;
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(16, 0);
   }

   @Override
   public boolean isActive24() {
      return false;
   }

   public EntityVillager(World world) {
      this(world, 0);
   }

   public EntityVillager(World world, int count) {
      super(world);
      this.applyIndex10(count);
      this.applyFloat(0.6F, 1.8F);
      ((PathfindManagerAdapter)this.getPathfindManager()).setEnabled(true);
      ((PathfindManagerAdapter)this.getPathfindManager()).setEnabled3(true);
      this.goalSetupManager.add(0, new SupplementalClientManagerAdapter(this));
      this.goalSetupManager.add(1, new AuxiliaryPredicateEntry(this, EntityZombie.class, 8.0F, 0.6, 0.6));
      this.goalSetupManager.add(1, new CompositeClientManagerAdapter(this));
      this.goalSetupManager.add(1, new IronChestplate(this));
      this.goalSetupManager.add(2, new AltClientManagerAdapter(this));
      this.goalSetupManager.add(3, new TertiaryClientManagerAdapter(this));
      this.goalSetupManager.add(4, new AuxiliaryItemEntryAdapter(this, true));
      this.goalSetupManager.add(5, new CoreClientManagerAdapter(this, 0.6));
      this.goalSetupManager.add(6, new GenericClientManagerAdapter(this));
      this.goalSetupManager.add(7, new LocalItemEntry(this));
      this.goalSetupManager.add(9, new HitBoxPredictor(this, EntityPlayer.class, 3.0F, 1.0F));
      this.goalSetupManager.add(9, new IronHelmet(this));
      this.goalSetupManager.add(9, new AdvancedClientManagerAdapter(this, 0.6));
      this.goalSetupManager.add(10, new BaseClientManagerAdapter(this, EntityMob.class, 8.0F));
      this.setEnabled22(true);
   }
}
