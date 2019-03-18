package ns.feigndemo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class Book {
    Long ID;
    String Title;
    String description;
    /*Long pageCount;
    String excerpt;
    LocalDate publishDate;*/

}
