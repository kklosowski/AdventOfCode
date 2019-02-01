import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Nap {
    Date start;
    Date end;

    public Nap(Date start) {
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public long length() {
        long duration  = start.getTime() - end.getTime();
        return TimeUnit.MILLISECONDS.toMinutes(duration);
    }

    public List<Integer> minutes(){
        for (int i = start.getMinutes(); i < end.getMinutes(); i++) {
            System.out.println(i);
        }
        return null;
    }
}
