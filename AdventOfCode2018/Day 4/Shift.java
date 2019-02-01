import java.util.Date;
import java.util.List;

public class Shift {
    private Date start;
    private List<Nap> naps;

    public Shift(Date start) {
        this.start = start;
    }

    public List<Nap> getNaps() {
        return naps;
    }

    public Date getStart() {
        return start;
    }

    public void addNap(Nap nap) {
        this.naps.add(nap);
    }

    public long getNapTime(){
        return naps.stream().mapToLong(Nap::length).sum();
    }
}
