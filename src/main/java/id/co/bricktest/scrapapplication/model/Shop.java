package id.co.bricktest.scrapapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Shop {

    private String name;
    private String location;
}
