package client.util;

import client.setting.CombatAlphaProperty;
import client.setting.CombatFactorProperty;
import client.setting.MovementColorProperty;
import client.setting.MovementThresholdProperty;
import client.setting.StandardOptionType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.BlockSand;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityBat;
import net.minecraft.entity.EntityChicken;
import net.minecraft.entity.EntityCow;
import net.minecraft.entity.EntityCreeper;
import net.minecraft.entity.EntityEnderman;
import net.minecraft.entity.EntityPig;
import net.minecraft.entity.EntityRabbit;
import net.minecraft.entity.EntitySheep;
import net.minecraft.entity.EntitySkeleton;
import net.minecraft.entity.EntitySlime;
import net.minecraft.entity.EntitySpider;
import net.minecraft.entity.EntitySquid;
import net.minecraft.entity.EntityWitch;
import net.minecraft.entity.EntityZombie;
import net.minecraft.item.GoldenHelmet;
import net.minecraft.item.GoldenHoe;
import net.minecraft.item.ModularItemEntry;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BiomeIDIsOutOfBoundsManager {
   public static final Logger logger = LogManager.getLogger();
   public static final CoreFloatEntry coreFloatEntry12 = new CoreFloatEntry(0.1F, 0.2F);
   public static final CoreFloatEntry coreFloatEntry4 = new CoreFloatEntry(-0.5F, 0.0F);
   public static final CoreFloatEntry coreFloatEntry6 = new CoreFloatEntry(-1.0F, 0.1F);
   public static final CoreFloatEntry coreFloatEntry11 = new CoreFloatEntry(-1.8F, 0.1F);
   public static final CoreFloatEntry coreFloatEntry8 = new CoreFloatEntry(0.125F, 0.05F);
   public static final CoreFloatEntry coreFloatEntry13 = new CoreFloatEntry(0.2F, 0.2F);
   public static final CoreFloatEntry coreFloatEntry5 = new CoreFloatEntry(0.45F, 0.3F);
   public static final CoreFloatEntry coreFloatEntry2 = new CoreFloatEntry(1.5F, 0.025F);
   public static final CoreFloatEntry coreFloatEntry = new CoreFloatEntry(1.0F, 0.5F);
   public static final CoreFloatEntry coreFloatEntry10 = new CoreFloatEntry(0.0F, 0.025F);
   public static final CoreFloatEntry coreFloatEntry3 = new CoreFloatEntry(0.1F, 0.8F);
   public static final CoreFloatEntry coreFloatEntry7 = new CoreFloatEntry(0.2F, 0.3F);
   public static final CoreFloatEntry coreFloatEntry9 = new CoreFloatEntry(-0.2F, 0.1F);
   public static final BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray = new BiomeIDIsOutOfBoundsManager[256];
   public static final Set set = Sets.newHashSet();
   public static final Map map = Maps.newHashMap();
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager28 = new TargetDeserializer(0)
      .resolveBiomeIDIsOutOfBoundsManager(112)
      .withLabelText("Ocean")
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry6);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager35 = new SunflowerPlainsManager(1)
      .resolveBiomeIDIsOutOfBoundsManager(9286496)
      .withLabelText("Plains");
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager17 = new BiomeIDIsOutOfBoundsManagerAdapter(2)
      .resolveBiomeIDIsOutOfBoundsManager(16421912)
      .withLabelText("Desert")
      .getBiomeIDIsOutOfBoundsManager()
      .resolveBiomeIDIsOutOfBoundsManager(2.0F, 0.0F)
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry8);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager21 = new InternalBiomeIDIsOutOfBoundsManagerAdapter(3, false)
      .resolveBiomeIDIsOutOfBoundsManager(6316128)
      .withLabelText("Extreme Hills")
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry)
      .resolveBiomeIDIsOutOfBoundsManager(0.2F, 0.3F);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager4 = new FlowerForestBiomeIDIsOutOfBoundsManager(4, 0)
      .resolveBiomeIDIsOutOfBoundsManager(353825)
      .withLabelText("Forest");
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager15 = new MegaSpruceTaigaManager(5, 0)
      .resolveBiomeIDIsOutOfBoundsManager(747097)
      .withLabelText("Taiga")
      .withCount2(5159473)
      .resolveBiomeIDIsOutOfBoundsManager(0.25F, 0.8F)
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry13);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager16 = new TargetEvaluator(6)
      .resolveBiomeIDIsOutOfBoundsManager(522674)
      .withLabelText("Swampland")
      .withCount2(9154376)
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry9)
      .resolveBiomeIDIsOutOfBoundsManager(0.8F, 0.9F);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager37 = new TargetValidator(7)
      .resolveBiomeIDIsOutOfBoundsManager(255)
      .withLabelText("River")
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry4);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager38 = new AuxiliaryBiomeIDIsOutOfBoundsManagerAdapter(8)
      .resolveBiomeIDIsOutOfBoundsManager(16711680)
      .withLabelText("Hell")
      .getBiomeIDIsOutOfBoundsManager()
      .resolveBiomeIDIsOutOfBoundsManager(2.0F, 0.0F);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager3 = new FovProfiler(9)
      .resolveBiomeIDIsOutOfBoundsManager(8421631)
      .withLabelText("The End")
      .getBiomeIDIsOutOfBoundsManager();
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager26 = new TargetDeserializer(10)
      .resolveBiomeIDIsOutOfBoundsManager(9474208)
      .withLabelText("FrozenOcean")
      .getBiomeIDIsOutOfBoundsManagerAsBiomeIDIsOutOfBo()
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry6)
      .resolveBiomeIDIsOutOfBoundsManager(0.0F, 0.5F);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager12 = new TargetValidator(11)
      .resolveBiomeIDIsOutOfBoundsManager(10526975)
      .withLabelText("FrozenRiver")
      .getBiomeIDIsOutOfBoundsManagerAsBiomeIDIsOutOfBo()
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry4)
      .resolveBiomeIDIsOutOfBoundsManager(0.0F, 0.5F);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager14 = new SpikesManager(12, false)
      .resolveBiomeIDIsOutOfBoundsManager(16777215)
      .withLabelText("Ice Plains")
      .getBiomeIDIsOutOfBoundsManagerAsBiomeIDIsOutOfBo()
      .resolveBiomeIDIsOutOfBoundsManager(0.0F, 0.5F)
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry8);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager36 = new SpikesManager(13, false)
      .resolveBiomeIDIsOutOfBoundsManager(10526880)
      .withLabelText("Ice Mountains")
      .getBiomeIDIsOutOfBoundsManagerAsBiomeIDIsOutOfBo()
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry5)
      .resolveBiomeIDIsOutOfBoundsManager(0.0F, 0.5F);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager32 = new TargetTransformer(14)
      .resolveBiomeIDIsOutOfBoundsManager(16711935)
      .withLabelText("MushroomIsland")
      .resolveBiomeIDIsOutOfBoundsManager(0.9F, 1.0F)
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry7);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager8 = new TargetTransformer(15)
      .resolveBiomeIDIsOutOfBoundsManager(10486015)
      .withLabelText("MushroomIslandShore")
      .resolveBiomeIDIsOutOfBoundsManager(0.9F, 1.0F)
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry10);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager11 = new FovScheduler(16)
      .resolveBiomeIDIsOutOfBoundsManager(16440917)
      .withLabelText("Beach")
      .resolveBiomeIDIsOutOfBoundsManager(0.8F, 0.4F)
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry10);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager27 = new BiomeIDIsOutOfBoundsManagerAdapter(17)
      .resolveBiomeIDIsOutOfBoundsManager(13786898)
      .withLabelText("DesertHills")
      .getBiomeIDIsOutOfBoundsManager()
      .resolveBiomeIDIsOutOfBoundsManager(2.0F, 0.0F)
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry5);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager25 = new FlowerForestBiomeIDIsOutOfBoundsManager(18, 0)
      .resolveBiomeIDIsOutOfBoundsManager(2250012)
      .withLabelText("ForestHills")
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry5);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager31 = new MegaSpruceTaigaManager(19, 0)
      .resolveBiomeIDIsOutOfBoundsManager(1456435)
      .withLabelText("TaigaHills")
      .withCount2(5159473)
      .resolveBiomeIDIsOutOfBoundsManager(0.25F, 0.8F)
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry5);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager29 = new InternalBiomeIDIsOutOfBoundsManagerAdapter(20, true)
      .resolveBiomeIDIsOutOfBoundsManager(7501978)
      .withLabelText("Extreme Hills Edge")
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry.getCoreFloatEntry())
      .resolveBiomeIDIsOutOfBoundsManager(0.2F, 0.3F);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager23 = new TertiaryIBlockStateEntry(21, false)
      .resolveBiomeIDIsOutOfBoundsManager(5470985)
      .withLabelText("Jungle")
      .withCount2(5470985)
      .resolveBiomeIDIsOutOfBoundsManager(0.95F, 0.9F);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager40 = new TertiaryIBlockStateEntry(22, false)
      .resolveBiomeIDIsOutOfBoundsManager(2900485)
      .withLabelText("JungleHills")
      .withCount2(5470985)
      .resolveBiomeIDIsOutOfBoundsManager(0.95F, 0.9F)
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry5);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager2 = new TertiaryIBlockStateEntry(23, true)
      .resolveBiomeIDIsOutOfBoundsManager(6458135)
      .withLabelText("JungleEdge")
      .withCount2(5470985)
      .resolveBiomeIDIsOutOfBoundsManager(0.95F, 0.8F);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager39 = new TargetDeserializer(24)
      .resolveBiomeIDIsOutOfBoundsManager(48)
      .withLabelText("Deep Ocean")
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry11);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager41 = new TargetSerializer(25)
      .resolveBiomeIDIsOutOfBoundsManager(10658436)
      .withLabelText("Stone Beach")
      .resolveBiomeIDIsOutOfBoundsManager(0.2F, 0.3F)
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry3);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager34 = new FovScheduler(26)
      .resolveBiomeIDIsOutOfBoundsManager(16445632)
      .withLabelText("Cold Beach")
      .resolveBiomeIDIsOutOfBoundsManager(0.05F, 0.3F)
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry10)
      .getBiomeIDIsOutOfBoundsManagerAsBiomeIDIsOutOfBo();
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager22 = new FlowerForestBiomeIDIsOutOfBoundsManager(27, 2)
      .withLabelText("Birch Forest")
      .resolveBiomeIDIsOutOfBoundsManager(3175492);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager20 = new FlowerForestBiomeIDIsOutOfBoundsManager(28, 2)
      .withLabelText("Birch Forest Hills")
      .resolveBiomeIDIsOutOfBoundsManager(2055986)
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry5);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = new FlowerForestBiomeIDIsOutOfBoundsManager(29, 3)
      .resolveBiomeIDIsOutOfBoundsManager(4215066)
      .withLabelText("Roofed Forest");
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager19 = new MegaSpruceTaigaManager(30, 0)
      .resolveBiomeIDIsOutOfBoundsManager(3233098)
      .withLabelText("Cold Taiga")
      .withCount2(5159473)
      .getBiomeIDIsOutOfBoundsManagerAsBiomeIDIsOutOfBo()
      .resolveBiomeIDIsOutOfBoundsManager(-0.5F, 0.4F)
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry13)
      .withCount4(16777215);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager18 = new MegaSpruceTaigaManager(31, 0)
      .resolveBiomeIDIsOutOfBoundsManager(2375478)
      .withLabelText("Cold Taiga Hills")
      .withCount2(5159473)
      .getBiomeIDIsOutOfBoundsManagerAsBiomeIDIsOutOfBo()
      .resolveBiomeIDIsOutOfBoundsManager(-0.5F, 0.4F)
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry5)
      .withCount4(16777215);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager33 = new MegaSpruceTaigaManager(32, 1)
      .resolveBiomeIDIsOutOfBoundsManager(5858897)
      .withLabelText("Mega Taiga")
      .withCount2(5159473)
      .resolveBiomeIDIsOutOfBoundsManager(0.3F, 0.8F)
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry13);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager6 = new MegaSpruceTaigaManager(33, 1)
      .resolveBiomeIDIsOutOfBoundsManager(4542270)
      .withLabelText("Mega Taiga Hills")
      .withCount2(5159473)
      .resolveBiomeIDIsOutOfBoundsManager(0.3F, 0.8F)
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry5);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager13 = new InternalBiomeIDIsOutOfBoundsManagerAdapter(34, true)
      .resolveBiomeIDIsOutOfBoundsManager(5271632)
      .withLabelText("Extreme Hills+")
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry)
      .resolveBiomeIDIsOutOfBoundsManager(0.2F, 0.3F);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager7 = new GoldenHelmet(35)
      .resolveBiomeIDIsOutOfBoundsManager(12431967)
      .withLabelText("Savanna")
      .resolveBiomeIDIsOutOfBoundsManager(1.2F, 0.0F)
      .getBiomeIDIsOutOfBoundsManager()
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry8);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager24 = new GoldenHelmet(36)
      .resolveBiomeIDIsOutOfBoundsManager(10984804)
      .withLabelText("Savanna Plateau")
      .resolveBiomeIDIsOutOfBoundsManager(1.0F, 0.0F)
      .getBiomeIDIsOutOfBoundsManager()
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry2);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager5 = new GGTNManager(37, false, false)
      .resolveBiomeIDIsOutOfBoundsManager(14238997)
      .withLabelText("Mesa");
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager9 = new GGTNManager(38, false, true)
      .resolveBiomeIDIsOutOfBoundsManager(11573093)
      .withLabelText("Mesa Plateau F")
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry2);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager30 = new GGTNManager(39, false, false)
      .resolveBiomeIDIsOutOfBoundsManager(13274213)
      .withLabelText("Mesa Plateau")
      .resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry2);
   public static final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager10 = biomeIDIsOutOfBoundsManager28;
   public static final AuxiliaryClientManagerService auxiliaryClientManagerService2;
   public static final AuxiliaryClientManagerService auxiliaryClientManagerService;
   public static final ModularItemEntry modularItemEntry;
   public String labelText;
   public int count3;
   public int count4;
   public IBlockState iBlockState = Blocks.blockGrass.getDefaultState();
   public IBlockState iBlockState2 = Blocks.block115.getDefaultState();
   public int count2 = 5169201;
   public float factor2;
   public float factor4;
   public float factor;
   public float factor3;
   public int count5;
   public AlreadyDecoratingManager alreadyDecoratingManager;
   public List list3;
   public List list4;
   public List list2;
   public List list;
   public boolean enabled2;
   public boolean enabled;
   public final int count;
   public SecondaryIBlockStateEntry secondaryIBlockStateEntry;
   public CustomRandomEntry customRandomEntry;
   public AuxiliaryClientManagerProcessor auxiliaryClientManagerProcessor;

   public float getFactor() {
      return 0.1F;
   }

   public AlreadyDecoratingManager getAlreadyDecoratingManager() {
      return new AlreadyDecoratingManager();
   }

   public BiomeIDIsOutOfBoundsManager withCount2(int count6) {
      this.count2 = count6;
      return this;
   }

   public static BiomeIDIsOutOfBoundsManager[] getBiomeIDIsOutOfBoundsManagerArray() {
      return biomeIDIsOutOfBoundsManagerArray;
   }

   public final int getCount() {
      return (int)(this.factor3 * 65536.0F);
   }

   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(int count6, boolean flag) {
      this.count3 = count6;
      if (flag) {
         this.count4 = (count6 & 16711422) >> 1;
      } else {
         this.count4 = count6;
      }

      return this;
   }

   public CombatFactorProperty nextInt(Random random, BlockPos blockPos) {
      return random.nextInt(3) > 0 ? CombatFactorProperty.DANDELION : CombatFactorProperty.POPPY;
   }

   public void applyWorld(World world, Random random, BlockPos blockPos) {
      this.alreadyDecoratingManager.applyWorldByWorld(world, random, this, blockPos);
   }

   public final float getFactor3() {
      return this.factor3;
   }

   public boolean isActive() {
      return this.isEnabled2();
   }

   public int computeCount(BlockPos blockPos) {
      double offset = (double)MathHelper.clamp_float(this.computeFactor(blockPos), 0.0F, 1.0F);
      double offset2 = (double)MathHelper.clamp_float(this.getFactor3(), 0.0F, 1.0F);
      return CoreIntArrayEntry.computeCount(offset, offset2);
   }

   public StandardOptionType getOCEAN() {
      return (double)this.factor < 0.2 ? StandardOptionType.COLD : ((double)this.factor < 1.0 ? StandardOptionType.MEDIUM : StandardOptionType.WARM);
   }

   public final void applyWorld2(World world, Random random, ShortArrayEntry shortArrayEntry, int count6, int count7, double offset) {
      int indexOffset = world.getIndexOffset1();
      IBlockState iBlockState3 = this.iBlockState;
      IBlockState iBlockState4 = this.iBlockState2;
      int count8 = -1;
      int count9 = (int)(offset / 3.0 + 3.0 + random.nextDouble() * 0.25);
      int count10 = count6 & 15;
      int count11 = count7 & 15;
      AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

      for (int index = 255; index >= 0; index--) {
         if (index <= random.nextInt(5)) {
            shortArrayEntry.applyIBlockState(count11, index, count10, Blocks.block95.getDefaultState());
         } else {
            IBlockState blockState = shortArrayEntry.resolveIBlockState(count11, index, count10);
            if (blockState.getBlock().getMaterial() == Material.coreItemEntry) {
               count8 = -1;
            } else if (blockState.getBlock() == Blocks.stone) {
               if (count8 == -1) {
                  if (count9 <= 0) {
                     iBlockState3 = null;
                     iBlockState4 = Blocks.stone.getDefaultState();
                  } else if (index >= indexOffset - 4 && index <= indexOffset + 1) {
                     iBlockState3 = this.iBlockState;
                     iBlockState4 = this.iBlockState2;
                  }

                  if (index < indexOffset && (iBlockState3 == null || iBlockState3.getBlock().getMaterial() == Material.coreItemEntry)) {
                     if (this.computeFactor(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count6, index, count7)) < 0.15F) {
                        iBlockState3 = Blocks.block53.getDefaultState();
                     } else {
                        iBlockState3 = Blocks.blockLava2.getDefaultState();
                     }
                  }

                  count8 = count9;
                  if (index >= indexOffset - 1) {
                     shortArrayEntry.applyIBlockState(count11, index, count10, iBlockState3);
                  } else if (index < indexOffset - 7 - count9) {
                     iBlockState3 = null;
                     iBlockState4 = Blocks.stone.getDefaultState();
                     shortArrayEntry.applyIBlockState(count11, index, count10, Blocks.block45.getDefaultState());
                  } else {
                     shortArrayEntry.applyIBlockState(count11, index, count10, iBlockState4);
                  }
               } else if (count8 > 0) {
                  count8--;
                  shortArrayEntry.applyIBlockState(count11, index, count10, iBlockState4);
                  if (count8 == 0 && iBlockState4.getBlock() == Blocks.blockSand) {
                     count8 = random.nextInt(4) + Math.max(0, index - 63);
                     iBlockState4 = iBlockState4.getComparable(BlockSand.immutableSetEntry) == CombatAlphaProperty.RED_SAND
                        ? Blocks.block119.getDefaultState()
                        : Blocks.block136.getDefaultState();
                  }
               }
            }
         }
      }
   }

   public final float computeFactor(BlockPos blockPos) {
      if (blockPos.getY() > 64) {
         float factor5 = (float)(auxiliaryClientManagerService2.computeValue((double)blockPos.getX() * 1.0 / 8.0, (double)blockPos.getZ() * 1.0 / 8.0) * 4.0);
         return this.factor - (factor5 + (float)blockPos.getY() - 64.0F) * 0.05F / 30.0F;
      } else {
         return this.factor;
      }
   }

   public CurveParser nextIntByRandom(Random random) {
      return new AuxiliaryIBlockStateEntry(MovementColorProperty.GRASS);
   }

   public BiomeIDIsOutOfBoundsManager getBiomeIDIsOutOfBoundsManager2() {
      return this.resolveBiomeIDIsOutOfBoundsManagerByIndex(this.count + 128);
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(int count6) {
      this.resolveBiomeIDIsOutOfBoundsManager(count6, false);
      return this;
   }

   public BiomeIDIsOutOfBoundsManager withLabelText(String text) {
      this.labelText = text;
      return this;
   }

   public static BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager2(int count6) {
      return resolveBiomeIDIsOutOfBoundsManager(count6, (BiomeIDIsOutOfBoundsManager)null);
   }

   public void applyWorld(World world, Random random, ShortArrayEntry shortArrayEntry, int count6, int count7, double offset) {
      this.applyWorld2(world, random, shortArrayEntry, count6, count7, offset);
   }

   public List resolveList(MovementThresholdProperty movementThresholdProperty) {
      switch (SupplementalIntArrayEntry.countArray[movementThresholdProperty.ordinal()]) {
         case 1:
            return this.list3;
         case 2:
            return this.list4;
         case 3:
            return this.list2;
         case 4:
            return this.list;
         default:
            return Collections.emptyList();
      }
   }

   public BiomeIDIsOutOfBoundsManager withCount4(int count6) {
      this.count4 = count6;
      return this;
   }

   public boolean isActive2() {
      return this.isEnabled2() ? false : this.enabled;
   }

   public final BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(CoreFloatEntry coreFloatEntry14) {
      this.factor2 = coreFloatEntry14.factor;
      this.factor4 = coreFloatEntry14.factor2;
      return this;
   }

   public int computeCountByBlockPos(BlockPos blockPos) {
      double offset = (double)MathHelper.clamp_float(this.computeFactor(blockPos), 0.0F, 1.0F);
      double offset2 = (double)MathHelper.clamp_float(this.getFactor3(), 0.0F, 1.0F);
      return DefaultIntArrayEntry.computeCount(offset, offset2);
   }

   public Class getClassAsClass() {
      return this.getClass();
   }

   public ClientManagerResolver nextInt(Random random) {
      return (ClientManagerResolver)(random.nextInt(10) == 0 ? this.customRandomEntry : this.secondaryIBlockStateEntry);
   }

   public boolean isActiveAsFlag() {
      return this.factor3 > 0.85F;
   }

   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(float factor5, float factor6) {
      if (factor5 > 0.1F && factor5 < 0.2F) {
         throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
      } else {
         this.factor = factor5;
         this.factor3 = factor6;
         return this;
      }
   }

   public int computeCount(float factor5) {
      float factor6 = factor5 / 3.0F;
      factor6 = MathHelper.clamp_float(factor6, -1.0F, 1.0F);
      return MathHelper.computeCount(0.62222224F - factor6 * 0.05F, 0.5F + factor6 * 0.1F, 1.0F);
   }

   public BiomeIDIsOutOfBoundsManager getBiomeIDIsOutOfBoundsManager() {
      this.enabled = false;
      return this;
   }

   public BiomeIDIsOutOfBoundsManager getBiomeIDIsOutOfBoundsManagerAsBiomeIDIsOutOfBo() {
      this.enabled2 = true;
      return this;
   }

   public static BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(int count6, BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager42) {
      if (count6 >= 0 && count6 <= biomeIDIsOutOfBoundsManagerArray.length) {
         BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager43 = biomeIDIsOutOfBoundsManagerArray[count6];
         return biomeIDIsOutOfBoundsManager43 == null ? biomeIDIsOutOfBoundsManager42 : biomeIDIsOutOfBoundsManager43;
      } else {
         logger.warn("Biome ID is out of bounds: " + count6 + ", defaulting to 0 (Ocean)");
         return biomeIDIsOutOfBoundsManager28;
      }
   }

   public boolean matchesBiomeIDIsOutOfBoundsManage(BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager42) {
      return biomeIDIsOutOfBoundsManager42 == this ? true : (biomeIDIsOutOfBoundsManager42 == null ? false : this.getClassAsClass() == biomeIDIsOutOfBoundsManager42.getClassAsClass());
   }

   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManagerByIndex(int count6) {
      return new GoldenHoe(count6, this);
   }

   public BiomeIDIsOutOfBoundsManager(int count6) {
      this.factor2 = coreFloatEntry12.factor;
      this.factor4 = coreFloatEntry12.factor2;
      this.factor = 0.5F;
      this.factor3 = 0.5F;
      this.count5 = 16777215;
      this.list3 = Lists.newArrayList();
      this.list4 = Lists.newArrayList();
      this.list2 = Lists.newArrayList();
      this.list = Lists.newArrayList();
      this.enabled = true;
      this.secondaryIBlockStateEntry = new SecondaryIBlockStateEntry(false);
      this.customRandomEntry = new CustomRandomEntry(false);
      this.auxiliaryClientManagerProcessor = new AuxiliaryClientManagerProcessor();
      this.count = count6;
      biomeIDIsOutOfBoundsManagerArray[count6] = this;
      this.alreadyDecoratingManager = this.getAlreadyDecoratingManager();
      this.list4.add(new ClassHolder(EntitySheep.class, 12, 4, 4));
      this.list4.add(new ClassHolder(EntityRabbit.class, 10, 3, 3));
      this.list4.add(new ClassHolder(EntityPig.class, 10, 4, 4));
      this.list4.add(new ClassHolder(EntityChicken.class, 10, 4, 4));
      this.list4.add(new ClassHolder(EntityCow.class, 8, 4, 4));
      this.list3.add(new ClassHolder(EntitySpider.class, 100, 4, 4));
      this.list3.add(new ClassHolder(EntityZombie.class, 100, 4, 4));
      this.list3.add(new ClassHolder(EntitySkeleton.class, 100, 4, 4));
      this.list3.add(new ClassHolder(EntityCreeper.class, 100, 4, 4));
      this.list3.add(new ClassHolder(EntitySlime.class, 100, 4, 4));
      this.list3.add(new ClassHolder(EntityEnderman.class, 10, 1, 4));
      this.list3.add(new ClassHolder(EntityWitch.class, 5, 1, 1));
      this.list2.add(new ClassHolder(EntitySquid.class, 10, 4, 4));
      this.list.add(new ClassHolder(EntityBat.class, 10, 8, 8));
   }

   static {
      biomeIDIsOutOfBoundsManager35.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager17.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager4.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager15.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager16.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager14.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager23.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager2.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager19.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager7.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager24.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager5.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager9.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager30.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager22.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager20.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager33.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager21.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager13.getBiomeIDIsOutOfBoundsManager2();
      biomeIDIsOutOfBoundsManager33.resolveBiomeIDIsOutOfBoundsManagerByIndex(biomeIDIsOutOfBoundsManager6.count + 128).withLabelText("Redwood Taiga Hills M");

      for (BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager42 : biomeIDIsOutOfBoundsManagerArray) {
         if (biomeIDIsOutOfBoundsManager42 != null) {
            if (map.containsKey(biomeIDIsOutOfBoundsManager42.labelText)) {
               throw new Error(
                  "Biome \""
                     + biomeIDIsOutOfBoundsManager42.labelText
                     + "\" is defined as both ID "
                     + ((BiomeIDIsOutOfBoundsManager)map.get(biomeIDIsOutOfBoundsManager42.labelText)).count
                     + " and "
                     + biomeIDIsOutOfBoundsManager42.count
               );
            }

            map.put(biomeIDIsOutOfBoundsManager42.labelText, biomeIDIsOutOfBoundsManager42);
            if (biomeIDIsOutOfBoundsManager42.count < 128) {
               set.add(biomeIDIsOutOfBoundsManager42);
            }
         }
      }

      set.remove(biomeIDIsOutOfBoundsManager38);
      set.remove(biomeIDIsOutOfBoundsManager3);
      set.remove(biomeIDIsOutOfBoundsManager26);
      set.remove(biomeIDIsOutOfBoundsManager29);
      auxiliaryClientManagerService2 = new AuxiliaryClientManagerService(new Random(1234L), 1);
      auxiliaryClientManagerService = new AuxiliaryClientManagerService(new Random(2345L), 1);
      modularItemEntry = new ModularItemEntry();
   }
}
