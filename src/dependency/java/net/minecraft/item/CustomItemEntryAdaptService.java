package net.minecraft.item;

import java.util.Random;
import net.minecraft.util.EnumFacing;

public interface CustomItemEntryAdaptService {
   CustomItemEntryAdapter resolveCustomItemEntryAdapter(EnumFacing enumFacing, EnumFacingController enumFacingController, Random random);

   boolean matchesEnumFacingController(EnumFacingController enumFacingController);
}
