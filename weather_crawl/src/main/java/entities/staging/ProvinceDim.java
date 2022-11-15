package entities.staging;

public class ProvinceDim {
    private int id;
    private String name;
    private String codeName;

    public ProvinceDim(int id, String name, String codeName) {
        this.id = id;
        this.name = name;
        this.codeName = codeName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCodeName() {
        return codeName;
    }


}

