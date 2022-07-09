package dob;

import java.util.List;

public abstract class AObject {

    private int id;

    public AObject(int id){
        this.id = id;
    }

    public AObject() {
    }

    public abstract void print();

    public static void printList(List<AObject> list){
        for (AObject o: list){
            o.print();
        }
    }

    public int getId(){
        return id;
    }
}
