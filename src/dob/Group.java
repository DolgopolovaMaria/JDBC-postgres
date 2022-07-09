package dob;

public class Group extends AObject {
    public Group(int id, String name, int id_curator) {
        super(id);
        this.name = name;
        this.id_curator = id_curator;
    }

    public Group(String name, int id_curator) {
        this.name = name;
        this.id_curator = id_curator;
    }

    private String name;

    private int id_curator;

    public String getName() {
        return name;
    }

    public int getId_curator(){
        return id_curator;
    }

    @Override
    public void print(){
        System.out.printf("Group %s: name = %s, curator = %s", getId(), getName(), getId_curator());
        System.out.println();
    }
}
