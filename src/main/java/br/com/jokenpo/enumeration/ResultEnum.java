package main.br.com.jokenpo.enumeration;

public enum ResultEnum {

    LOST(-1),
    DRAW(0),
    WIN(1);

    private Integer result;

    ResultEnum(int result) {
        this.result = result;
    }

    public Integer getResult() {
        return result;
    }
}
