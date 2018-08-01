/**
 * class to represent all the colors
 */
public enum Color {
    black("black"),white("white"),red("red"),green("green"),
    blue("blue"),yellow("yellow"),silver("silver"),gold("gold"),none("-");
    private String value;

    Color(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * get the index of a specific color
     */
    public static int indexOf(Color c){
        switch (c){
            case black:
                return 0;
            case white:
                return 1;
            case red:
                return 2;
            case green:
                return 3;
            case blue:
                return 4;
            case yellow:
                return 5;
            case silver:
                return 6;
            case gold:
                return 7;
            default:
                return -1;
        }
    }
}
