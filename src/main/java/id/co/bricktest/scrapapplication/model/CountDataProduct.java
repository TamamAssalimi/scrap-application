package id.co.bricktest.scrapapplication.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class CountDataProduct {

    private Long id;
    private String url;
    private String imageUrl;
    private String name;
    private String price;
    private String rating;
    private Shop shop;
}
