package cc.baritone.utils;

import java.io.IOException;

public interface ServiceProvider {
   boolean isActive() throws UnexpectedCharacterException, IOException;

   boolean isActive2() throws UnexpectedCharacterException, IOException;

   boolean isActive4() throws UnexpectedCharacterException, IOException;

   boolean matchesText(String text) throws UnexpectedCharacterException, IOException;

   boolean matchesTarget(Object entry) throws UnexpectedCharacterException, IOException;

   boolean isActiveAsFlag() throws UnexpectedCharacterException, IOException;

   void updateState2() throws UnexpectedCharacterException, IOException;

   void updateState() throws UnexpectedCharacterException, IOException;

   boolean isActive3() throws UnexpectedCharacterException, IOException;
}
