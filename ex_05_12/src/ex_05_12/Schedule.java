
package ex_05_12;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;


public class Schedule {
    private List<String> lines;
    
    public Schedule(Path configFilePath){
        try {
            lines = Files.readAllLines(configFilePath, StandardCharsets.UTF_8);
            
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public Stream<ScheduleItem> getScheduleItems(){
        //return null;
        return lines.stream().map(ScheduleItem::of);
    }
    

    
    static public class ScheduleItem{
        private String itemLine;
        private String itemName;
        private Instant itemInstant;
        
        
        public static ScheduleItem of(String itemLine){
            try{
                String[] itemParts = itemLine.split("---");
                if(itemParts.length != 3){
                    throw new InvalidParameterException("itemLine format error. xxx---xxxx---xxx is expected.");
                }

                ScheduleItem item = new ScheduleItem();
                item.itemLine = itemLine;
                item.itemName = itemParts[0];

                LocalDateTime localDateTime = LocalDateTime.parse(itemParts[2], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth(),
                                                localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond(), localDateTime.getNano(),
                                                ZoneId.of(itemParts[1]));
                //zonedDateTime.withSecond(0);//Omit the second part.

                item.itemInstant = zonedDateTime.toInstant();

                return item;
            }catch(RuntimeException e){
                throw new ScheduleItemFormatException(e);
            }
        }
        
        public String getName(){
            return itemName;
        }
        
        public Instant getInstant(){
            return itemInstant;
        }
        
        @Override
        public String toString(){
            return itemLine;
        }
        
        public boolean willStartAt(Instant instant){
            ZonedDateTime itemTokyoDateTime = itemInstant.atZone(ZoneId.of("Asia/Tokyo"));
            ZonedDateTime instantTokyoDateTime = instant.atZone(ZoneId.of("Asia/Tokyo"));
//            System.out.println("t1 :" + itemTokyoDateTime);
//            System.out.println("t2 :" + instantTokyoDateTime);            
            return itemTokyoDateTime.getYear() == instantTokyoDateTime.getYear()
                    && itemTokyoDateTime.getMonthValue() == instantTokyoDateTime.getMonthValue()
                    && itemTokyoDateTime.getDayOfMonth()== instantTokyoDateTime.getDayOfMonth()
                    && itemTokyoDateTime.getHour() == instantTokyoDateTime.getHour()
                    && itemTokyoDateTime.getMinute() == instantTokyoDateTime.getMinute();
                    
        }
    }
}

