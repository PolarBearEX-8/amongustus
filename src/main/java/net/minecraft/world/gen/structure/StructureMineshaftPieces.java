package net.minecraft.world.gen.structure;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;

public class StructureMineshaftPieces {
   private static final List CHEST_CONTENT_WEIGHT_LIST = Lists.newArrayList(
      new WeightedRandomChestContent[]{
         new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10),
         new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5),
         new WeightedRandomChestContent(Items.redstone, 0, 4, 9, 5),
         new WeightedRandomChestContent(Items.dye, EnumDyeColor.BLUE.getDyeDamage(), 4, 9, 5),
         new WeightedRandomChestContent(Items.diamond, 0, 1, 2, 3),
         new WeightedRandomChestContent(Items.coal, 0, 3, 8, 10),
         new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15),
         new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 1),
         new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.rail), 0, 4, 8, 1),
         new WeightedRandomChestContent(Items.melon_seeds, 0, 2, 4, 10),
         new WeightedRandomChestContent(Items.pumpkin_seeds, 0, 2, 4, 10),
         new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3),
         new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1)
      }
   );

   private static StructureComponent generateAndAddPiece(StructureComponent structureComponent, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
      if (n4 > 8) {
         return null;
      } else if (Math.abs(n - structureComponent.getBoundingBox().minX) <= 80 && Math.abs(n3 - structureComponent.getBoundingBox().minZ) <= 80) {
         StructureComponent structureComponent2 = createRandomShaftPiece(list, rand, n, n2, n3, facing, n4 + 1);
         if (structureComponent2 != null) {
            list.add(structureComponent2);
            structureComponent2.buildComponent(structureComponent, list, rand);
         }

         return structureComponent2;
      } else {
         return null;
      }
   }

   public static List accessCHEST_CONTENT_WEIGHT_LIST() {
      return CHEST_CONTENT_WEIGHT_LIST;
   }

   private static StructureComponent createRandomShaftPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
      int n5 = rand.nextInt(100);
      if (n5 >= 80) {
         StructureBoundingBox structureBoundingBox = StructureMineshaftPieces.Cross.findCrossing(list, rand, n, n2, n3, facing);
         if (structureBoundingBox != null) {
            return new StructureMineshaftPieces.Cross(n4, rand, structureBoundingBox, facing);
         }
      } else if (n5 >= 70) {
         StructureBoundingBox structureBoundingBox2 = StructureMineshaftPieces.Stairs.findStairs(list, rand, n, n2, n3, facing);
         if (structureBoundingBox2 != null) {
            return new StructureMineshaftPieces.Stairs(n4, rand, structureBoundingBox2, facing);
         }
      } else {
         StructureBoundingBox structureBoundingBox3 = StructureMineshaftPieces.Corridor.findCorridorSize(list, rand, n, n2, n3, facing);
         if (structureBoundingBox3 != null) {
            return new StructureMineshaftPieces.Corridor(n4, rand, structureBoundingBox3, facing);
         }
      }

      return null;
   }

   public static StructureComponent addMineshaftPiece(StructureComponent structureComponent, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
      return generateAndAddPiece(structureComponent, list, rand, n, n2, n3, facing, n4);
   }

   public static void registerStructurePieces() {
      MapGenStructureIO.registerStructureComponent(StructureMineshaftPieces.Corridor.class, "MSCorridor");
      MapGenStructureIO.registerStructureComponent(StructureMineshaftPieces.Cross.class, "MSCrossing");
      MapGenStructureIO.registerStructureComponent(StructureMineshaftPieces.Room.class, "MSRoom");
      MapGenStructureIO.registerStructureComponent(StructureMineshaftPieces.Stairs.class, "MSStairs");
   }

public static class Room extends StructureComponent {
      private List roomsLinkedToTheRoom = Lists.newLinkedList();

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
            return false;
         } else {
            this.fillWithBlocks(
               world,
               structureBoundingBox,
               this.boundingBox.minX,
               this.boundingBox.minY,
               this.boundingBox.minZ,
               this.boundingBox.maxX,
               this.boundingBox.minY,
               this.boundingBox.maxZ,
               Blocks.dirt.getDefaultState(),
               Blocks.air.getDefaultState(),
               true
            );
            this.fillWithBlocks(
               world,
               structureBoundingBox,
               this.boundingBox.minX,
               this.boundingBox.minY + 1,
               this.boundingBox.minZ,
               this.boundingBox.maxX,
               Math.min(this.boundingBox.minY + 3, this.boundingBox.maxY),
               this.boundingBox.maxZ,
               Blocks.air.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );

            for (StructureBoundingBox structureBoundingBox2 : (Iterable<StructureBoundingBox>)(this.roomsLinkedToTheRoom)) {
               this.fillWithBlocks(
                  world,
                  structureBoundingBox,
                  structureBoundingBox2.minX,
                  structureBoundingBox2.maxY - 2,
                  structureBoundingBox2.minZ,
                  structureBoundingBox2.maxX,
                  structureBoundingBox2.maxY,
                  structureBoundingBox2.maxZ,
                  Blocks.air.getDefaultState(),
                  Blocks.air.getDefaultState(),
                  false
               );
            }

            this.randomlyRareFillWithBlocks(
               world,
               structureBoundingBox,
               this.boundingBox.minX,
               this.boundingBox.minY + 4,
               this.boundingBox.minZ,
               this.boundingBox.maxX,
               this.boundingBox.maxY,
               this.boundingBox.maxZ,
               Blocks.air.getDefaultState(),
               false
            );
            return true;
         }
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         int componentType2 = this.getComponentType();
         int n = this.boundingBox.getYSize() - 3 - 1;
         if (n <= 0) {
            n = 1;
         }

         int n2 = 0;

         while (n2 < this.boundingBox.getXSize()) {
            n2 += rand.nextInt(this.boundingBox.getXSize());
            if (n2 + 3 > this.boundingBox.getXSize()) {
               break;
            }

            StructureComponent structureComponent2 = StructureMineshaftPieces.addMineshaftPiece(
               structureComponent, list, rand, this.boundingBox.minX + n2, this.boundingBox.minY + rand.nextInt(n) + 1, this.boundingBox.minZ - 1, EnumFacing.NORTH, componentType2
            );
            if (structureComponent2 != null) {
               StructureBoundingBox boundingBox2 = structureComponent2.getBoundingBox();
               this.roomsLinkedToTheRoom
                  .add(new StructureBoundingBox(boundingBox2.minX, boundingBox2.minY, this.boundingBox.minZ, boundingBox2.maxX, boundingBox2.maxY, this.boundingBox.minZ + 1));
            }

            n2 += 4;
         }

         n2 = 0;

         while (n2 < this.boundingBox.getXSize()) {
            n2 += rand.nextInt(this.boundingBox.getXSize());
            if (n2 + 3 > this.boundingBox.getXSize()) {
               break;
            }

            StructureComponent structureComponent3 = StructureMineshaftPieces.addMineshaftPiece(
               structureComponent, list, rand, this.boundingBox.minX + n2, this.boundingBox.minY + rand.nextInt(n) + 1, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, componentType2
            );
            if (structureComponent3 != null) {
               StructureBoundingBox boundingBox3 = structureComponent3.getBoundingBox();
               this.roomsLinkedToTheRoom
                  .add(new StructureBoundingBox(boundingBox3.minX, boundingBox3.minY, this.boundingBox.maxZ - 1, boundingBox3.maxX, boundingBox3.maxY, this.boundingBox.maxZ));
            }

            n2 += 4;
         }

         n2 = 0;

         while (n2 < this.boundingBox.getZSize()) {
            n2 += rand.nextInt(this.boundingBox.getZSize());
            if (n2 + 3 > this.boundingBox.getZSize()) {
               break;
            }

            StructureComponent structureComponent4 = StructureMineshaftPieces.addMineshaftPiece(
               structureComponent, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY + rand.nextInt(n) + 1, this.boundingBox.minZ + n2, EnumFacing.WEST, componentType2
            );
            if (structureComponent4 != null) {
               StructureBoundingBox boundingBox4 = structureComponent4.getBoundingBox();
               this.roomsLinkedToTheRoom
                  .add(new StructureBoundingBox(this.boundingBox.minX, boundingBox4.minY, boundingBox4.minZ, this.boundingBox.minX + 1, boundingBox4.maxY, boundingBox4.maxZ));
            }

            n2 += 4;
         }

         n2 = 0;

         while (n2 < this.boundingBox.getZSize()) {
            n2 += rand.nextInt(this.boundingBox.getZSize());
            if (n2 + 3 > this.boundingBox.getZSize()) {
               break;
            }

            StructureComponent structureComponent5 = StructureMineshaftPieces.addMineshaftPiece(
               structureComponent, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + rand.nextInt(n) + 1, this.boundingBox.minZ + n2, EnumFacing.EAST, componentType2
            );
            if (structureComponent5 != null) {
               StructureBoundingBox boundingBox5 = structureComponent5.getBoundingBox();
               this.roomsLinkedToTheRoom
                  .add(new StructureBoundingBox(this.boundingBox.maxX - 1, boundingBox5.minY, boundingBox5.minZ, this.boundingBox.maxX, boundingBox5.maxY, boundingBox5.maxZ));
            }

            n2 += 4;
         }
      }

      @Override
      public void offset(int n, int n2, int n3) {
         super.offset(n, n2, n3);

         for (StructureBoundingBox structureBoundingBox : (Iterable<StructureBoundingBox>)(this.roomsLinkedToTheRoom)) {
            structureBoundingBox.offset(n, n2, n3);
         }
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         NBTTagList tagList = new NBTTagList();

         for (StructureBoundingBox structureBoundingBox : (Iterable<StructureBoundingBox>)(this.roomsLinkedToTheRoom)) {
            tagList.appendTag(structureBoundingBox.toNBTTagIntArray());
         }

         tag.setTag("Entrances", tagList);
      }

      public Room() {
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         NBTTagList tagList = tag.getTagList("Entrances", 11);

         for (int i = 0; i < tagList.tagCount(); i++) {
            this.roomsLinkedToTheRoom.add(new StructureBoundingBox(tagList.getIntArrayAt(i)));
         }
      }

      public Room(int n, Random rand, int n2, int n3) {
         super(n);
         this.boundingBox = new StructureBoundingBox(n2, 50, n3, n2 + 7 + rand.nextInt(6), 54 + rand.nextInt(6), n3 + 7 + rand.nextInt(6));
      }
   }

   public static class Cross extends StructureComponent {
      private EnumFacing corridorDirection;
      private boolean isMultipleFloors;

      public static StructureBoundingBox findCrossing(List list, Random rand, int n, int n2, int n3, EnumFacing facing) {
         StructureBoundingBox structureBoundingBox = new StructureBoundingBox(n, n2, n3, n, n2 + 2, n3);
         if (rand.nextInt(4) == 0) {
            structureBoundingBox.maxY += 4;
         }

         switch (facing) {
            case NORTH:
               structureBoundingBox.minX = n - 1;
               structureBoundingBox.maxX = n + 3;
               structureBoundingBox.minZ = n3 - 4;
               break;
            case SOUTH:
               structureBoundingBox.minX = n - 1;
               structureBoundingBox.maxX = n + 3;
               structureBoundingBox.maxZ = n3 + 4;
               break;
            case WEST:
               structureBoundingBox.minX = n - 4;
               structureBoundingBox.minZ = n3 - 1;
               structureBoundingBox.maxZ = n3 + 3;
               break;
            case EAST:
               structureBoundingBox.maxX = n + 4;
               structureBoundingBox.minZ = n3 - 1;
               structureBoundingBox.maxZ = n3 + 3;
         }

         return StructureComponent.findIntersecting(list, structureBoundingBox) != null ? null : structureBoundingBox;
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         tag.setBoolean("tf", this.isMultipleFloors);
         tag.setInteger("D", this.corridorDirection.getHorizontalIndex());
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
            return false;
         } else {
            if (this.isMultipleFloors) {
               this.fillWithBlocks(
                  world,
                  structureBoundingBox,
                  this.boundingBox.minX + 1,
                  this.boundingBox.minY,
                  this.boundingBox.minZ,
                  this.boundingBox.maxX - 1,
                  this.boundingBox.minY + 3 - 1,
                  this.boundingBox.maxZ,
                  Blocks.air.getDefaultState(),
                  Blocks.air.getDefaultState(),
                  false
               );
               this.fillWithBlocks(
                  world,
                  structureBoundingBox,
                  this.boundingBox.minX,
                  this.boundingBox.minY,
                  this.boundingBox.minZ + 1,
                  this.boundingBox.maxX,
                  this.boundingBox.minY + 3 - 1,
                  this.boundingBox.maxZ - 1,
                  Blocks.air.getDefaultState(),
                  Blocks.air.getDefaultState(),
                  false
               );
               this.fillWithBlocks(
                  world,
                  structureBoundingBox,
                  this.boundingBox.minX + 1,
                  this.boundingBox.maxY - 2,
                  this.boundingBox.minZ,
                  this.boundingBox.maxX - 1,
                  this.boundingBox.maxY,
                  this.boundingBox.maxZ,
                  Blocks.air.getDefaultState(),
                  Blocks.air.getDefaultState(),
                  false
               );
               this.fillWithBlocks(
                  world,
                  structureBoundingBox,
                  this.boundingBox.minX,
                  this.boundingBox.maxY - 2,
                  this.boundingBox.minZ + 1,
                  this.boundingBox.maxX,
                  this.boundingBox.maxY,
                  this.boundingBox.maxZ - 1,
                  Blocks.air.getDefaultState(),
                  Blocks.air.getDefaultState(),
                  false
               );
               this.fillWithBlocks(
                  world,
                  structureBoundingBox,
                  this.boundingBox.minX + 1,
                  this.boundingBox.minY + 3,
                  this.boundingBox.minZ + 1,
                  this.boundingBox.maxX - 1,
                  this.boundingBox.minY + 3,
                  this.boundingBox.maxZ - 1,
                  Blocks.air.getDefaultState(),
                  Blocks.air.getDefaultState(),
                  false
               );
            } else {
               this.fillWithBlocks(
                  world,
                  structureBoundingBox,
                  this.boundingBox.minX + 1,
                  this.boundingBox.minY,
                  this.boundingBox.minZ,
                  this.boundingBox.maxX - 1,
                  this.boundingBox.maxY,
                  this.boundingBox.maxZ,
                  Blocks.air.getDefaultState(),
                  Blocks.air.getDefaultState(),
                  false
               );
               this.fillWithBlocks(
                  world,
                  structureBoundingBox,
                  this.boundingBox.minX,
                  this.boundingBox.minY,
                  this.boundingBox.minZ + 1,
                  this.boundingBox.maxX,
                  this.boundingBox.maxY,
                  this.boundingBox.maxZ - 1,
                  Blocks.air.getDefaultState(),
                  Blocks.air.getDefaultState(),
                  false
               );
            }

            this.fillWithBlocks(
               world,
               structureBoundingBox,
               this.boundingBox.minX + 1,
               this.boundingBox.minY,
               this.boundingBox.minZ + 1,
               this.boundingBox.minX + 1,
               this.boundingBox.maxY,
               this.boundingBox.minZ + 1,
               Blocks.planks.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );
            this.fillWithBlocks(
               world,
               structureBoundingBox,
               this.boundingBox.minX + 1,
               this.boundingBox.minY,
               this.boundingBox.maxZ - 1,
               this.boundingBox.minX + 1,
               this.boundingBox.maxY,
               this.boundingBox.maxZ - 1,
               Blocks.planks.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );
            this.fillWithBlocks(
               world,
               structureBoundingBox,
               this.boundingBox.maxX - 1,
               this.boundingBox.minY,
               this.boundingBox.minZ + 1,
               this.boundingBox.maxX - 1,
               this.boundingBox.maxY,
               this.boundingBox.minZ + 1,
               Blocks.planks.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );
            this.fillWithBlocks(
               world,
               structureBoundingBox,
               this.boundingBox.maxX - 1,
               this.boundingBox.minY,
               this.boundingBox.maxZ - 1,
               this.boundingBox.maxX - 1,
               this.boundingBox.maxY,
               this.boundingBox.maxZ - 1,
               Blocks.planks.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );

            for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; i++) {
               for (int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; j++) {
                  if (this.getBlockStateFromPos(world, i, this.boundingBox.minY - 1, j, structureBoundingBox).getBlock().getMaterial() == Material.air) {
                     this.setBlockState(world, Blocks.planks.getDefaultState(), i, this.boundingBox.minY - 1, j, structureBoundingBox);
                  }
               }
            }

            return true;
         }
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         int componentType2 = this.getComponentType();
         switch (this.corridorDirection) {
            case NORTH:
               StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, componentType2);
               StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.WEST, componentType2);
               StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.EAST, componentType2);
               break;
            case SOUTH:
               StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, componentType2);
               StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.WEST, componentType2);
               StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.EAST, componentType2);
               break;
            case WEST:
               StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, componentType2);
               StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, componentType2);
               StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.WEST, componentType2);
               break;
            case EAST:
               StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, componentType2);
               StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, componentType2);
               StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.EAST, componentType2);
         }

         if (this.isMultipleFloors) {
            if (rand.nextBoolean()) {
               StructureMineshaftPieces.addMineshaftPiece(
                  structureComponent, list, rand, this.boundingBox.minX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ - 1, EnumFacing.NORTH, componentType2
               );
            }

            if (rand.nextBoolean()) {
               StructureMineshaftPieces.addMineshaftPiece(
                  structureComponent, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ + 1, EnumFacing.WEST, componentType2
               );
            }

            if (rand.nextBoolean()) {
               StructureMineshaftPieces.addMineshaftPiece(
                  structureComponent, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ + 1, EnumFacing.EAST, componentType2
               );
            }

            if (rand.nextBoolean()) {
               StructureMineshaftPieces.addMineshaftPiece(
                  structureComponent, list, rand, this.boundingBox.minX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, componentType2
               );
            }
         }
      }

      public Cross() {
      }

      public Cross(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.corridorDirection = facing;
         this.boundingBox = structureBoundingBox;
         this.isMultipleFloors = structureBoundingBox.getYSize() > 3;
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         this.isMultipleFloors = tag.getBoolean("tf");
         this.corridorDirection = EnumFacing.getHorizontal(tag.getInteger("D"));
      }
   }

   public static class Stairs extends StructureComponent {
      public Stairs() {
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
            return false;
         } else {
            this.fillWithBlocks(world, structureBoundingBox, 0, 5, 0, 2, 7, 1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithBlocks(world, structureBoundingBox, 0, 0, 7, 2, 2, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);

            for (int i = 0; i < 5; i++) {
               this.fillWithBlocks(
                  world, structureBoundingBox, 0, 5 - i - (i < 4 ? 1 : 0), 2 + i, 2, 7 - i, 2 + i, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false
               );
            }

            return true;
         }
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         int componentType2 = this.getComponentType();
         if (this.coordBaseMode != null) {
            switch (this.coordBaseMode) {
               case NORTH:
                  StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, componentType2);
                  break;
               case SOUTH:
                  StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, componentType2);
                  break;
               case WEST:
                  StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.WEST, componentType2);
                  break;
               case EAST:
                  StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.EAST, componentType2);
            }
         }
      }

      public Stairs(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
      }

      public static StructureBoundingBox findStairs(List list, Random rand, int n, int n2, int n3, EnumFacing facing) {
         StructureBoundingBox structureBoundingBox = new StructureBoundingBox(n, n2 - 5, n3, n, n2 + 2, n3);
         switch (facing) {
            case NORTH:
               structureBoundingBox.maxX = n + 2;
               structureBoundingBox.minZ = n3 - 8;
               break;
            case SOUTH:
               structureBoundingBox.maxX = n + 2;
               structureBoundingBox.maxZ = n3 + 8;
               break;
            case WEST:
               structureBoundingBox.minX = n - 8;
               structureBoundingBox.maxZ = n3 + 2;
               break;
            case EAST:
               structureBoundingBox.maxX = n + 8;
               structureBoundingBox.maxZ = n3 + 2;
         }

         return StructureComponent.findIntersecting(list, structureBoundingBox) != null ? null : structureBoundingBox;
      }
   }

   public static class Corridor extends StructureComponent {
      private boolean hasRails;
      private boolean hasSpiders;
      private boolean spawnerPlaced;
      private int sectionCount;

      public static StructureBoundingBox findCorridorSize(List list, Random rand, int n, int n2, int n3, EnumFacing facing) {
         StructureBoundingBox structureBoundingBox = new StructureBoundingBox(n, n2, n3, n, n2 + 2, n3);

         int n4;
         for (n4 = rand.nextInt(3) + 2; n4 > 0; n4--) {
            int n5 = n4 * 5;
            switch (facing) {
               case NORTH:
                  structureBoundingBox.maxX = n + 2;
                  structureBoundingBox.minZ = n3 - (n5 - 1);
                  break;
               case SOUTH:
                  structureBoundingBox.maxX = n + 2;
                  structureBoundingBox.maxZ = n3 + (n5 - 1);
                  break;
               case WEST:
                  structureBoundingBox.minX = n - (n5 - 1);
                  structureBoundingBox.maxZ = n3 + 2;
                  break;
               case EAST:
                  structureBoundingBox.maxX = n + (n5 - 1);
                  structureBoundingBox.maxZ = n3 + 2;
            }

            if (StructureComponent.findIntersecting(list, structureBoundingBox) == null) {
               break;
            }
         }

         return n4 > 0 ? structureBoundingBox : null;
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         this.hasRails = tag.getBoolean("hr");
         this.hasSpiders = tag.getBoolean("sc");
         this.spawnerPlaced = tag.getBoolean("hps");
         this.sectionCount = tag.getInteger("Num");
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         int componentType2 = this.getComponentType();
         int n = rand.nextInt(4);
         if (this.coordBaseMode != null) {
            switch (this.coordBaseMode) {
               case NORTH:
                  if (n <= 1) {
                     StructureMineshaftPieces.addMineshaftPiece(
                        structureComponent, list, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ - 1, this.coordBaseMode, componentType2
                     );
                  } else if (n == 2) {
                     StructureMineshaftPieces.addMineshaftPiece(
                        structureComponent, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, EnumFacing.WEST, componentType2
                     );
                  } else {
                     StructureMineshaftPieces.addMineshaftPiece(
                        structureComponent, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, EnumFacing.EAST, componentType2
                     );
                  }
                  break;
               case SOUTH:
                  if (n <= 1) {
                     StructureMineshaftPieces.addMineshaftPiece(
                        structureComponent, list, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ + 1, this.coordBaseMode, componentType2
                     );
                  } else if (n == 2) {
                     StructureMineshaftPieces.addMineshaftPiece(
                        structureComponent, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ - 3, EnumFacing.WEST, componentType2
                     );
                  } else {
                     StructureMineshaftPieces.addMineshaftPiece(
                        structureComponent, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ - 3, EnumFacing.EAST, componentType2
                     );
                  }
                  break;
               case WEST:
                  if (n <= 1) {
                     StructureMineshaftPieces.addMineshaftPiece(
                        structureComponent, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, this.coordBaseMode, componentType2
                     );
                  } else if (n == 2) {
                     StructureMineshaftPieces.addMineshaftPiece(
                        structureComponent, list, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ - 1, EnumFacing.NORTH, componentType2
                     );
                  } else {
                     StructureMineshaftPieces.addMineshaftPiece(
                        structureComponent, list, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ + 1, EnumFacing.SOUTH, componentType2
                     );
                  }
                  break;
               case EAST:
                  if (n <= 1) {
                     StructureMineshaftPieces.addMineshaftPiece(
                        structureComponent, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, this.coordBaseMode, componentType2
                     );
                  } else if (n == 2) {
                     StructureMineshaftPieces.addMineshaftPiece(
                        structureComponent,
                        list,
                        rand,
                        this.boundingBox.maxX - 3,
                        this.boundingBox.minY - 1 + rand.nextInt(3),
                        this.boundingBox.minZ - 1,
                        EnumFacing.NORTH,
                        componentType2
                     );
                  } else {
                     StructureMineshaftPieces.addMineshaftPiece(
                        structureComponent,
                        list,
                        rand,
                        this.boundingBox.maxX - 3,
                        this.boundingBox.minY - 1 + rand.nextInt(3),
                        this.boundingBox.maxZ + 1,
                        EnumFacing.SOUTH,
                        componentType2
                     );
                  }
            }
         }

         if (componentType2 < 8) {
            if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.SOUTH) {
               for (int i = this.boundingBox.minX + 3; i + 3 <= this.boundingBox.maxX; i += 5) {
                  int n2 = rand.nextInt(5);
                  if (n2 == 0) {
                     StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, i, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, componentType2 + 1);
                  } else if (n2 == 1) {
                     StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, i, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, componentType2 + 1);
                  }
               }
            } else {
               for (int j = this.boundingBox.minZ + 3; j + 3 <= this.boundingBox.maxZ; j += 5) {
                  int n3 = rand.nextInt(5);
                  if (n3 == 0) {
                     StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY, j, EnumFacing.WEST, componentType2 + 1);
                  } else if (n3 == 1) {
                     StructureMineshaftPieces.addMineshaftPiece(structureComponent, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, j, EnumFacing.EAST, componentType2 + 1);
                  }
               }
            }
         }
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
            return false;
         } else {
            int n = this.sectionCount * 5 - 1;
            this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 2, 1, n, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.generateMaybeBox(world, structureBoundingBox, rand, 0.8F, 0, 2, 0, 2, 2, n, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            if (this.hasSpiders) {
               this.generateMaybeBox(world, structureBoundingBox, rand, 0.6F, 0, 0, 0, 2, 1, n, Blocks.web.getDefaultState(), Blocks.air.getDefaultState(), false);
            }

            for (int i = 0; i < this.sectionCount; i++) {
               int n2 = 2 + i * 5;
               this.fillWithBlocks(world, structureBoundingBox, 0, 0, n2, 0, 1, n2, Blocks.oak_fence.getDefaultState(), Blocks.air.getDefaultState(), false);
               this.fillWithBlocks(world, structureBoundingBox, 2, 0, n2, 2, 1, n2, Blocks.oak_fence.getDefaultState(), Blocks.air.getDefaultState(), false);
               if (rand.nextInt(4) == 0) {
                  this.fillWithBlocks(world, structureBoundingBox, 0, 2, n2, 0, 2, n2, Blocks.planks.getDefaultState(), Blocks.air.getDefaultState(), false);
                  this.fillWithBlocks(world, structureBoundingBox, 2, 2, n2, 2, 2, n2, Blocks.planks.getDefaultState(), Blocks.air.getDefaultState(), false);
               } else {
                  this.fillWithBlocks(world, structureBoundingBox, 0, 2, n2, 2, 2, n2, Blocks.planks.getDefaultState(), Blocks.air.getDefaultState(), false);
               }

               this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.1F, 0, 2, n2 - 1, Blocks.web.getDefaultState());
               this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.1F, 2, 2, n2 - 1, Blocks.web.getDefaultState());
               this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.1F, 0, 2, n2 + 1, Blocks.web.getDefaultState());
               this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.1F, 2, 2, n2 + 1, Blocks.web.getDefaultState());
               this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.05F, 0, 2, n2 - 2, Blocks.web.getDefaultState());
               this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.05F, 2, 2, n2 - 2, Blocks.web.getDefaultState());
               this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.05F, 0, 2, n2 + 2, Blocks.web.getDefaultState());
               this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.05F, 2, 2, n2 + 2, Blocks.web.getDefaultState());
               this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.05F, 1, 2, n2 - 1, Blocks.torch.getStateFromMeta(EnumFacing.UP.getIndex()));
               this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.05F, 1, 2, n2 + 1, Blocks.torch.getStateFromMeta(EnumFacing.UP.getIndex()));
               if (rand.nextInt(100) == 0) {
                  this.generateChestContents(
                     world,
                     structureBoundingBox,
                     rand,
                     2,
                     0,
                     n2 - 1,
                     WeightedRandomChestContent.combine(
                        StructureMineshaftPieces.accessCHEST_CONTENT_WEIGHT_LIST(), new WeightedRandomChestContent[]{Items.enchanted_book.getRandom(rand)}
                     ),
                     3 + rand.nextInt(4)
                  );
               }

               if (rand.nextInt(100) == 0) {
                  this.generateChestContents(
                     world,
                     structureBoundingBox,
                     rand,
                     0,
                     0,
                     n2 + 1,
                     WeightedRandomChestContent.combine(
                        StructureMineshaftPieces.accessCHEST_CONTENT_WEIGHT_LIST(), new WeightedRandomChestContent[]{Items.enchanted_book.getRandom(rand)}
                     ),
                     3 + rand.nextInt(4)
                  );
               }

               if (this.hasSpiders && !this.spawnerPlaced) {
                  int yWithOffset = this.getYWithOffset(0);
                  int n3 = n2 - 1 + rand.nextInt(3);
                  int xWithOffset = this.getXWithOffset(1, n3);
                  n3 = this.getZWithOffset(1, n3);
                  BlockPos pos = new BlockPos(xWithOffset, yWithOffset, n3);
                  if (structureBoundingBox.isVecInside(pos)) {
                     this.spawnerPlaced = true;
                     world.setBlockState(pos, Blocks.mob_spawner.getDefaultState(), 2);
                     TileEntity tileEntity = world.getTileEntity(pos);
                     if (tileEntity instanceof TileEntityMobSpawner) {
                        ((TileEntityMobSpawner)tileEntity).getSpawnerBaseLogic().setEntityName("CaveSpider");
                     }
                  }
               }
            }

            for (int j = 0; j <= 2; j++) {
               for (int k = 0; k <= n; k++) {
                  if (this.getBlockStateFromPos(world, j, (byte)-1, k, structureBoundingBox).getBlock().getMaterial() == Material.air) {
                     this.setBlockState(world, Blocks.planks.getDefaultState(), j, (byte)-1, k, structureBoundingBox);
                  }
               }
            }

            if (this.hasRails) {
               for (int m = 0; m <= n; m++) {
                  IBlockState blockStateFromPos2 = this.getBlockStateFromPos(world, 1, -1, m, structureBoundingBox);
                  if (blockStateFromPos2.getBlock().getMaterial() != Material.air && blockStateFromPos2.getBlock().isFullBlock()) {
                     this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.7F, 1, 0, m, Blocks.rail.getStateFromMeta(this.getMetadataWithOffset(Blocks.rail, 0)));
                  }
               }
            }

            return true;
         }
      }

      public Corridor() {
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         tag.setBoolean("hr", this.hasRails);
         tag.setBoolean("sc", this.hasSpiders);
         tag.setBoolean("hps", this.spawnerPlaced);
         tag.setInteger("Num", this.sectionCount);
      }

      @Override
      public boolean generateChestContents(World world, StructureBoundingBox structureBoundingBox, Random rand, int n, int n2, int n3, List list, int n4) {
         BlockPos pos = new BlockPos(this.getXWithOffset(n, n3), this.getYWithOffset(n2), this.getZWithOffset(n, n3));
         if (structureBoundingBox.isVecInside(pos) && world.getBlockState(pos).getBlock().getMaterial() == Material.air) {
            int n5 = rand.nextBoolean() ? 1 : 0;
            world.setBlockState(pos, Blocks.rail.getStateFromMeta(this.getMetadataWithOffset(Blocks.rail, n5)), 2);
            EntityMinecartChest entityMinecartChest = new EntityMinecartChest(
               world, (double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F)
            );
            WeightedRandomChestContent.generateChestContents(rand, list, entityMinecartChest, n4);
            world.spawnEntityInWorld(entityMinecartChest);
            return true;
         } else {
            return false;
         }
      }

      public Corridor(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
         this.hasRails = rand.nextInt(3) == 0;
         this.hasSpiders = !this.hasRails && rand.nextInt(23) == 0;
         if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.SOUTH) {
            this.sectionCount = structureBoundingBox.getXSize() / 5;
         } else {
            this.sectionCount = structureBoundingBox.getZSize() / 5;
         }
      }
   }
}
