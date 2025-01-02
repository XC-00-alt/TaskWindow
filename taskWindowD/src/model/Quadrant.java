package model;

public enum Quadrant {
    IMPORTANT_NON_URGENT(1,"important & non-urgent"),
    IMPORTANT_URGENT(2,"important & urgent"),
    UNIMPORTANT_URGENT(3,"unimportant & urgent"),
    UNIMPORTANT_NON_URGENT(4,"unimportant & non-urgent"),
    ;
    final Integer code;

    final String description;
    Quadrant(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
