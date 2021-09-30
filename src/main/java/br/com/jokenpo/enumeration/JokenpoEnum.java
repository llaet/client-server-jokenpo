package br.com.jokenpo.enumeration;

public enum JokenpoEnum {

    ROCK("Pedra",0),
    PAPER("Papel",1),
    SCISSOR("Tesoura",2);

    private String moveName;
    private Integer moveCode;

    JokenpoEnum(String moveName, int moveCode) {
        this.moveName = moveName;
        this.moveCode = moveCode;
    }

    public String getMoveName() {
        return moveName;
    }

    public Integer getMoveCode() {
        return this.moveCode;
    }

    public static JokenpoEnum toEnum(int moveCode) {
        for(JokenpoEnum move : JokenpoEnum.values()) {
            if (move.moveCode == moveCode) {
                return move;
            }
        }
        return null;
    }

    public static JokenpoEnum toEnum(String moveName) {
        for(JokenpoEnum move : JokenpoEnum.values()) {
            if (move.moveName.equalsIgnoreCase(moveName)) {
                return move;
            }
        }
        return null;
    }

    public static String toMoveName(int moveCode) {
        for(JokenpoEnum move : JokenpoEnum.values()) {
            if (move.moveCode == moveCode) {
                return move.getMoveName();
            }
        }
        return null;
    }
}
