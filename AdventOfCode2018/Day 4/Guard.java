import java.util.List;

public class Guard {
    private int id;
    private List<Shift> shifts;

    public Guard(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void addShift(Shift shift){
        shifts.add(shift);
    }

    public long getTotalNapTime(){
        return shifts.stream().mapToLong(Shift::getNapTime).sum();
    }
}
