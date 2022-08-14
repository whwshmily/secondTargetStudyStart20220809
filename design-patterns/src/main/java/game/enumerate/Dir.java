package game.enumerate;

public enum Dir {
    LEFT("left"), RIGHT("right"), UP("up"), DOWN("down");
    private String value;

    Dir(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
