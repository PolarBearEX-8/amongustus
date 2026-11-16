package cc.baritone.utils;

import java.util.Optional;

public interface TertiaryListener {
   Optional getOptionalAsOptional();

   SecondaryListener getSecondaryListener();

   TertiaryCustom resolveTertiaryCustom(long value, long value2);

   Optional getOptional();

   boolean isEnabled();
}
