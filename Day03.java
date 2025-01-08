import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Day03 {
    public static void main(String[] args) {
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd-MM-yyyy hh:mm:ss a",
                Locale.forLanguageTag("id-ID"));
        System.out.println(formatter.format(today));

        System.out.println(today.plusDays(2).getDayOfWeek());
    }
}
