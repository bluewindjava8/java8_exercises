package ex_05_12;


public class ScheduleItemFormatException extends RuntimeException {


    public ScheduleItemFormatException() {
    }

    public ScheduleItemFormatException(String msg) {
        super(msg);
    }
    
    public ScheduleItemFormatException(Exception cause){
        super(cause);
    }
}
