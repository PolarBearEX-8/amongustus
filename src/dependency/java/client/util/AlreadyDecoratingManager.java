package client.util;

import client.setting.CombatFactorProperty;
import client.setting.CustomOptionType;
import java.util.Random;
import net.minecraft.block.BlockStone;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExtendedBlockEntry;
import net.minecraft.block.InternalBlockEntry;
import net.minecraft.block.SecondaryBlockEntry;
import net.minecraft.block.TertiaryBlockEntry;
import net.minecraft.block.material.Material;
import net.minecraft.item.IndirectItemEntry;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class AlreadyDecoratingManager {
   public World world;
   public Random random;
   public BlockPos blockPos;
   public GlobalFloatEntry globalFloatEntry;
   public CurveParser curveParser5 = new ExtendedBlockEntry(4);
   public CurveParser curveParser = new TertiaryBlockEntry(Blocks.blockSand, 7);
   public CurveParser curveParser7 = new TertiaryBlockEntry(Blocks.block45, 6);
   public CurveParser curveParser14;
   public CurveParser curveParser9;
   public CurveParser curveParser15;
   public CurveParser curveParser11;
   public CurveParser curveParser17;
   public CurveParser curveParser4;
   public CurveParser curveParser18;
   public CurveParser curveParser10;
   public CurveParser curveParser16;
   public CurveParser curveParser13;
   public CurveParser curveParser12;
   public IndirectItemEntry indirectItemEntry = new IndirectItemEntry(Blocks.immutableSetEntry2Entry, CombatFactorProperty.DANDELION);
   public CurveParser curveParser8 = new AuxiliaryClientManagerController(Blocks.secondaryBlockAdapter2);
   public CurveParser curveParser20 = new AuxiliaryClientManagerController(Blocks.secondaryBlockAdapter);
   public CurveParser curveParser6 = new SecondaryBlockEntry();
   public CurveParser curveParser3 = new RotationSystem();
   public CurveParser curveParser2 = new RotationProvider();
   public CurveParser curveParser19 = new FactoryClientManager();
   public int count3;
   public int count9;
   public int count8 = 2;
   public int count5 = 1;
   public int count;
   public int count2;
   public int count4;
   public int count11;
   public int count7 = 1;
   public int count6 = 3;
   public int count12 = 1;
   public int count10;
   public boolean enabled = true;

   public void applyCurveParser(int count13, CurveParser curveParser21, int count14, int count15) {
      for (int index = 0; index < count13; index++) {
         BlockPos blockPos2 = this.blockPos
            .add(this.random.nextInt(16), this.random.nextInt(count15) + this.random.nextInt(count15) + count14 - count15, this.random.nextInt(16));
         curveParser21.matchesWorld(this.world, this.random, blockPos2);
      }
   }

   public void applyBiomeIDIsOutOfBoundsManage(BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager) {
      this.updateState();

      for (int index = 0; index < this.count6; index++) {
         int count13 = this.random.nextInt(16) + 8;
         int count14 = this.random.nextInt(16) + 8;
         this.curveParser.matchesWorld(this.world, this.random, this.world.resolveBlockPos(this.blockPos.add(count13, 0, count14)));
      }

      for (int index2 = 0; index2 < this.count12; index2++) {
         int count15 = this.random.nextInt(16) + 8;
         int count16 = this.random.nextInt(16) + 8;
         this.curveParser5.matchesWorld(this.world, this.random, this.world.resolveBlockPos(this.blockPos.add(count15, 0, count16)));
      }

      for (int index3 = 0; index3 < this.count7; index3++) {
         int count17 = this.random.nextInt(16) + 8;
         int count18 = this.random.nextInt(16) + 8;
         this.curveParser7.matchesWorld(this.world, this.random, this.world.resolveBlockPos(this.blockPos.add(count17, 0, count18)));
      }

      int count19 = this.count9;
      if (this.random.nextInt(10) == 0) {
         count19++;
      }

      for (int index4 = 0; index4 < count19; index4++) {
         int count20 = this.random.nextInt(16) + 8;
         int count21 = this.random.nextInt(16) + 8;
         ClientManagerResolver clientManagerResolver = biomeIDIsOutOfBoundsManager.nextInt(this.random);
         clientManagerResolver.updateState();
         BlockPos blockPos2 = this.world.resolveBlockPos2(this.blockPos.add(count20, 0, count21));
         if (clientManagerResolver.matchesWorld(this.world, this.random, blockPos2)) {
            clientManagerResolver.applyWorld(this.world, this.random, blockPos2);
         }
      }

      for (int index5 = 0; index5 < this.count10; index5++) {
         int count22 = this.random.nextInt(16) + 8;
         int count23 = this.random.nextInt(16) + 8;
         this.curveParser6.matchesWorld(this.world, this.random, this.world.resolveBlockPos2(this.blockPos.add(count22, 0, count23)));
      }

      for (int index6 = 0; index6 < this.count8; index6++) {
         int count24 = this.random.nextInt(16) + 8;
         int count25 = this.random.nextInt(16) + 8;
         int count26 = this.world.resolveBlockPos2(this.blockPos.add(count24, 0, count25)).getY() + 32;
         if (count26 > 0) {
            int count27 = this.random.nextInt(count26);
            BlockPos blockPos3 = this.blockPos.add(count24, count27, count25);
            CombatFactorProperty combatFactorProperty = biomeIDIsOutOfBoundsManager.nextInt(this.random, blockPos3);
            ImmutableSetEntry2Entry immutableSetEntry2Entry = combatFactorProperty.getCombatProfileProperty().getImmutableSetEntry2Entry();
            if (immutableSetEntry2Entry.getMaterial() != Material.coreItemEntry) {
               this.indirectItemEntry.applyImmutableSetEntry2Entry(immutableSetEntry2Entry, combatFactorProperty);
               this.indirectItemEntry.matchesWorld(this.world, this.random, blockPos3);
            }
         }
      }

      for (int index7 = 0; index7 < this.count5; index7++) {
         int count28 = this.random.nextInt(16) + 8;
         int count29 = this.random.nextInt(16) + 8;
         int count30 = this.world.resolveBlockPos2(this.blockPos.add(count28, 0, count29)).getY() * 2;
         if (count30 > 0) {
            int count31 = this.random.nextInt(count30);
            biomeIDIsOutOfBoundsManager.nextIntByRandom(this.random).matchesWorld(this.world, this.random, this.blockPos.add(count28, count31, count29));
         }
      }

      for (int index8 = 0; index8 < this.count; index8++) {
         int count32 = this.random.nextInt(16) + 8;
         int count33 = this.random.nextInt(16) + 8;
         int count34 = this.world.resolveBlockPos2(this.blockPos.add(count32, 0, count33)).getY() * 2;
         if (count34 > 0) {
            int count35 = this.random.nextInt(count34);
            new RotationBuilder().matchesWorld(this.world, this.random, this.blockPos.add(count32, count35, count33));
         }
      }

      for (int index9 = 0; index9 < this.count3; index9++) {
         int count36 = this.random.nextInt(16) + 8;
         int count37 = this.random.nextInt(16) + 8;
         int count38 = this.world.resolveBlockPos2(this.blockPos.add(count36, 0, count37)).getY() * 2;
         if (count38 > 0) {
            int count39 = this.random.nextInt(count38);
            BlockPos blockPos4 = this.blockPos.add(count36, count39, count37);

            while (blockPos4.getY() > 0) {
               BlockPos blockPos5 = blockPos4.down();
               if (!this.world.isAirBlock(blockPos5)) {
                  break;
               }

               blockPos4 = blockPos5;
            }

            this.curveParser19.matchesWorld(this.world, this.random, blockPos4);
         }
      }

      for (int index10 = 0; index10 < this.count2; index10++) {
         if (this.random.nextInt(4) == 0) {
            int count40 = this.random.nextInt(16) + 8;
            int count41 = this.random.nextInt(16) + 8;
            BlockPos blockPos6 = this.world.resolveBlockPos2(this.blockPos.add(count40, 0, count41));
            this.curveParser8.matchesWorld(this.world, this.random, blockPos6);
         }

         if (this.random.nextInt(8) == 0) {
            int count42 = this.random.nextInt(16) + 8;
            int count43 = this.random.nextInt(16) + 8;
            int count44 = this.world.resolveBlockPos2(this.blockPos.add(count42, 0, count43)).getY() * 2;
            if (count44 > 0) {
               int count45 = this.random.nextInt(count44);
               BlockPos blockPos7 = this.blockPos.add(count42, count45, count43);
               this.curveParser20.matchesWorld(this.world, this.random, blockPos7);
            }
         }
      }

      if (this.random.nextInt(4) == 0) {
         int count46 = this.random.nextInt(16) + 8;
         int count47 = this.random.nextInt(16) + 8;
         int count48 = this.world.resolveBlockPos2(this.blockPos.add(count46, 0, count47)).getY() * 2;
         if (count48 > 0) {
            int count49 = this.random.nextInt(count48);
            this.curveParser8.matchesWorld(this.world, this.random, this.blockPos.add(count46, count49, count47));
         }
      }

      if (this.random.nextInt(8) == 0) {
         int count50 = this.random.nextInt(16) + 8;
         int count51 = this.random.nextInt(16) + 8;
         int count52 = this.world.resolveBlockPos2(this.blockPos.add(count50, 0, count51)).getY() * 2;
         if (count52 > 0) {
            int count53 = this.random.nextInt(count52);
            this.curveParser20.matchesWorld(this.world, this.random, this.blockPos.add(count50, count53, count51));
         }
      }

      for (int index11 = 0; index11 < this.count4; index11++) {
         int count54 = this.random.nextInt(16) + 8;
         int count55 = this.random.nextInt(16) + 8;
         int count56 = this.world.resolveBlockPos2(this.blockPos.add(count54, 0, count55)).getY() * 2;
         if (count56 > 0) {
            int count57 = this.random.nextInt(count56);
            this.curveParser3.matchesWorld(this.world, this.random, this.blockPos.add(count54, count57, count55));
         }
      }

      for (int index12 = 0; index12 < 10; index12++) {
         int count58 = this.random.nextInt(16) + 8;
         int count59 = this.random.nextInt(16) + 8;
         int count60 = this.world.resolveBlockPos2(this.blockPos.add(count58, 0, count59)).getY() * 2;
         if (count60 > 0) {
            int count61 = this.random.nextInt(count60);
            this.curveParser3.matchesWorld(this.world, this.random, this.blockPos.add(count58, count61, count59));
         }
      }

      if (this.random.nextInt(32) == 0) {
         int count62 = this.random.nextInt(16) + 8;
         int count63 = this.random.nextInt(16) + 8;
         int count64 = this.world.resolveBlockPos2(this.blockPos.add(count62, 0, count63)).getY() * 2;
         if (count64 > 0) {
            int count65 = this.random.nextInt(count64);
            new RotationDirector().matchesWorld(this.world, this.random, this.blockPos.add(count62, count65, count63));
         }
      }

      for (int index13 = 0; index13 < this.count11; index13++) {
         int count66 = this.random.nextInt(16) + 8;
         int count67 = this.random.nextInt(16) + 8;
         int count68 = this.world.resolveBlockPos2(this.blockPos.add(count66, 0, count67)).getY() * 2;
         if (count68 > 0) {
            int count69 = this.random.nextInt(count68);
            this.curveParser2.matchesWorld(this.world, this.random, this.blockPos.add(count66, count69, count67));
         }
      }

      if (this.enabled) {
         for (int index14 = 0; index14 < 50; index14++) {
            int count70 = this.random.nextInt(16) + 8;
            int count71 = this.random.nextInt(16) + 8;
            int count72 = this.random.nextInt(248) + 8;
            if (count72 > 0) {
               int count73 = this.random.nextInt(count72);
               BlockPos blockPos8 = this.blockPos.add(count70, count73, count71);
               new InternalBlockEntry(Blocks.blockFlowingLava).matchesWorld(this.world, this.random, blockPos8);
            }
         }

         for (int index15 = 0; index15 < 20; index15++) {
            int count74 = this.random.nextInt(16) + 8;
            int count75 = this.random.nextInt(16) + 8;
            int count76 = this.random.nextInt(this.random.nextInt(this.random.nextInt(240) + 8) + 8);
            BlockPos blockPos9 = this.blockPos.add(count74, count76, count75);
            new InternalBlockEntry(Blocks.blockFlowingLava2).matchesWorld(this.world, this.random, blockPos9);
         }
      }
   }

   public void applyCurveParserByCurveParser(int count13, CurveParser curveParser21, int count14, int count15) {
      int count16 = count14;
      int count17 = count15;
      if (count15 < count14) {
         count16 = count15;
         count17 = count14;
      } else if (count15 == count14) {
         if (count14 < 255) {
            count17 = count15 + 1;
         } else {
            count16 = count14 - 1;
         }
      }

      for (int index = 0; index < count13; index++) {
         BlockPos blockPos2 = this.blockPos.add(this.random.nextInt(16), this.random.nextInt(count17 - count16) + count16, this.random.nextInt(16));
         curveParser21.matchesWorld(this.world, this.random, blockPos2);
      }
   }

   public void applyWorldByWorld(World world2, Random random2, BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager, BlockPos blockPos2) {
      if (this.world != null) {
         throw new RuntimeException("Already decorating");
      } else {
         this.world = world2;
         String labelText = world2.getRandomSeedManager().getLabelText2();
         if (labelText != null) {
            this.globalFloatEntry = AuxiliaryGsonEntry.resolveAuxiliaryGsonEntry(labelText).getGlobalFloatEntry();
         } else {
            this.globalFloatEntry = AuxiliaryGsonEntry.resolveAuxiliaryGsonEntry("").getGlobalFloatEntry();
         }

         this.random = random2;
         this.blockPos = blockPos2;
         this.curveParser14 = new IBlockStateEntry(Blocks.block115.getDefaultState(), this.globalFloatEntry.count29);
         this.curveParser9 = new IBlockStateEntry(Blocks.block45.getDefaultState(), this.globalFloatEntry.count24);
         this.curveParser15 = new IBlockStateEntry(
            Blocks.stone.getDefaultState().resolveIBlockState(BlockStone.immutableSetEntry, CustomOptionType.GRANITE), this.globalFloatEntry.count4
         );
         this.curveParser11 = new IBlockStateEntry(
            Blocks.stone.getDefaultState().resolveIBlockState(BlockStone.immutableSetEntry, CustomOptionType.DIORITE), this.globalFloatEntry.count
         );
         this.curveParser17 = new IBlockStateEntry(
            Blocks.stone.getDefaultState().resolveIBlockState(BlockStone.immutableSetEntry, CustomOptionType.ANDESITE), this.globalFloatEntry.count33
         );
         this.curveParser4 = new IBlockStateEntry(Blocks.block89.getDefaultState(), this.globalFloatEntry.count17);
         this.curveParser18 = new IBlockStateEntry(Blocks.block103.getDefaultState(), this.globalFloatEntry.count46);
         this.curveParser10 = new IBlockStateEntry(Blocks.block121.getDefaultState(), this.globalFloatEntry.count10);
         this.curveParser16 = new IBlockStateEntry(Blocks.block25.getDefaultState(), this.globalFloatEntry.count21);
         this.curveParser13 = new IBlockStateEntry(Blocks.block44.getDefaultState(), this.globalFloatEntry.count31);
         this.curveParser12 = new IBlockStateEntry(Blocks.block47.getDefaultState(), this.globalFloatEntry.count25);
         this.applyBiomeIDIsOutOfBoundsManage(biomeIDIsOutOfBoundsManager);
         this.world = null;
         this.random = null;
      }
   }

   public void updateState() {
      this.applyCurveParserByCurveParser(this.globalFloatEntry.count27, this.curveParser14, this.globalFloatEntry.count30, this.globalFloatEntry.count3);
      this.applyCurveParserByCurveParser(this.globalFloatEntry.count40, this.curveParser9, this.globalFloatEntry.count18, this.globalFloatEntry.count23);
      this.applyCurveParserByCurveParser(this.globalFloatEntry.count44, this.curveParser11, this.globalFloatEntry.count35, this.globalFloatEntry.count19);
      this.applyCurveParserByCurveParser(this.globalFloatEntry.count9, this.curveParser15, this.globalFloatEntry.count13, this.globalFloatEntry.count14);
      this.applyCurveParserByCurveParser(this.globalFloatEntry.count20, this.curveParser17, this.globalFloatEntry.count11, this.globalFloatEntry.count43);
      this.applyCurveParserByCurveParser(this.globalFloatEntry.count22, this.curveParser4, this.globalFloatEntry.count38, this.globalFloatEntry.count28);
      this.applyCurveParserByCurveParser(this.globalFloatEntry.count36, this.curveParser18, this.globalFloatEntry.count7, this.globalFloatEntry.count47);
      this.applyCurveParserByCurveParser(this.globalFloatEntry.count32, this.curveParser10, this.globalFloatEntry.count2, this.globalFloatEntry.count42);
      this.applyCurveParserByCurveParser(this.globalFloatEntry.count41, this.curveParser16, this.globalFloatEntry.count16, this.globalFloatEntry.count45);
      this.applyCurveParserByCurveParser(this.globalFloatEntry.count8, this.curveParser13, this.globalFloatEntry.count39, this.globalFloatEntry.count12);
      this.applyCurveParser(this.globalFloatEntry.count15, this.curveParser12, this.globalFloatEntry.count5, this.globalFloatEntry.count37);
   }
}
