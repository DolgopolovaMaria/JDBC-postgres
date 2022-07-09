package dob;

public class Curator extends AObject{
    public Curator(int id, String fio) {
        super(id);
        this.fio = fio;
    }

    public Curator(String fio) {
        super(0);
        this.fio = fio;
    }

    private String fio;

    public String getFio() {
        return fio;
    }

    @Override
    public void print(){
        System.out.printf("Curator %s: FIO = %s", getId(), getFio());
        System.out.println();
    }
}
