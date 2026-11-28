package de.wazed.wrapper.generation;

import de.wazed.wrapper.utils.exceptions.NotFoundException;
import de.wazed.wrapper.utils.exceptions.UnauthorizedException;

public class Generator {
   private static Generator instance;

   public Generator() {
      instance = this;
   }

   public Account generate(String token) throws UnauthorizedException, NotFoundException {
      throw new UnsupportedOperationException("Disabled: this legacy API sends its token over plaintext HTTP.");
   }

   public static Generator getInstance() {
      return instance;
   }
}
