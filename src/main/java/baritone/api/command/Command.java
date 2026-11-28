package baritone.api.command;

import baritone.api.IBaritone;
import baritone.api.utils.IPlayerContext;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Command implements ICommand {
   public IBaritone baritone;
   public IPlayerContext ctx;
   public final List names;

   @Override
   public final List getNames() {
      return this.names;
   }

   public Command(IBaritone baritone2, String[] strings) {
      this.names = Collections.unmodifiableList(Stream.of(strings).map(str -> str.toLowerCase(Locale.US)).collect(Collectors.toList()));
      this.baritone = baritone2;
      this.ctx = baritone2.getPlayerContext();
   }
}
