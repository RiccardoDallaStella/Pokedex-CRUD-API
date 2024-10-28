import java.util.List;

public class Pokemon {
    private int id;
    private String name;
    private List<Types> types;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Types> getTypes() {
        return types;
    }

    public static class Types {
        private Type type;

        public Type getType() {
            return type;
        }
    }

    public static class Type {
        private String name;

        public String getName() {
            return name;
        }
    }
}
