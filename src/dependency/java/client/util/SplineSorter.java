package client.util;

public enum SplineSorter {
    OK,
    NOT_POSSIBLE_HERE,
    NOT_POSSIBLE_NOW,
    TOO_FAR_AWAY,
    OTHER_PROBLEM,
    NOT_SAFE;

   public static final SplineSorter[] ENUM_VALUES = new SplineSorter[]{OK, NOT_POSSIBLE_HERE, NOT_POSSIBLE_NOW, TOO_FAR_AWAY, OTHER_PROBLEM, NOT_SAFE};

}
