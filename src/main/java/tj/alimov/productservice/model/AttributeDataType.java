package tj.alimov.productservice.model;

public enum AttributeDataType {
    STRING  ("string"),
    INTEGER ("integer"),
    DOUBLE  ("double"),
    BOOLEAN ("boolean"),
    DATE    ("date");

    final String name;
    AttributeDataType(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public static AttributeDataType getByName(String name){
        switch(name){
            case "string":
                return AttributeDataType.STRING;
            case "integer":
                return AttributeDataType.INTEGER;
            case "double":
                return AttributeDataType.DOUBLE;
            case "boolean":
                return AttributeDataType.BOOLEAN;
            case "date":
                return AttributeDataType.DATE;
            default :
                return AttributeDataType.STRING;
        }
    }
}
