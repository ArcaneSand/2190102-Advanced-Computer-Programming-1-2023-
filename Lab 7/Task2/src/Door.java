import java.util.ArrayList;
import java.util.List;

public class Door {
    public String name;

    public Door(String name) {
        this.name = name;
    }

    public void open(){
        System.out.println(String.format("Door %s opened", name));
    }
    public void close(){
        System.out.println(String.format("Door %s closed", name));
    }
}

interface Observer{
    public void registerObserver(Door door);
    public void openAllDoors();
    public void closeAllDoors();
}
class ControlCenter implements Observer{
    public List<Door> DoorList = new ArrayList<>();

    public void registerObserver(Door door) {
        DoorList.add(door);
    }

    public void openAllDoors(){
        for(Door door : DoorList){
            door.open();
        }
    }

    public void closeAllDoors(){
        for(Door door : DoorList){
            door.close();
        }
    }
}