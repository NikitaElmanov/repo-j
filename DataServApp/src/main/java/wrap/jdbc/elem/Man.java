package wrap.jdbc.elem;

public class Man {
    private int id;
    private String name;
    private String married;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    @Override
    public String toString() {
        return "Man{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", married='" + married + '\'' +
                "}\n";
    }
}
