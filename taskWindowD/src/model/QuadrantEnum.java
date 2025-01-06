package model;

public enum QuadrantEnum {
    IMPORTANT_NON_URGENT(1,"important & non-urgent"),
    IMPORTANT_URGENT(2,"important & urgent"),
    UNIMPORTANT_URGENT(3,"unimportant & urgent"),
    UNIMPORTANT_NON_URGENT(4,"unimportant & non-urgent"),
    ;
    final Integer code;

    final String description;
    QuadrantEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString()
    {
        return code+" "+description;
    }

//    public static QuadrantEnum getQuadrantEnum(Integer code)
//    {
//        return switch (code) {
//            case 1 -> {IMPORTANT_NON_URGENT;break;}
//            case 3 -> {UNIMPORTANT_URGENT;break;}
//            case 4 -> {UNIMPORTANT_NON_URGENT;break;}
//            default -> IMPORTANT_URGENT;
//        };
////        switch (code)
////        {
////            case 1 :return IMPORTANT_NON_URGENT;
////            case 3 :return UNIMPORTANT_URGENT;
////            case 4 :return UNIMPORTANT_NON_URGENT;
////            default:return IMPORTANT_URGENT;
////        }
//    }
}
