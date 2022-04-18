package id.co.bricktest.scrapapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@JsonPropertyOrder({"name", "description", "imageLink", "price", "rating", "merchant", "link"})
public class Product {

    @JsonIgnore
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private double rating;
    private String merchant;


    public abstract static class ProductFormat {

        @JsonProperty("Product Name")
        abstract String getName();
        @JsonProperty("Description")
        abstract String getDescription();
        @JsonProperty("Image Url")
        abstract String getImageUrl();
        @JsonProperty("Price")
        abstract String getPrice();
        @JsonProperty("Rating")
        abstract String getRating();
        @JsonProperty("Merchant Name")
        abstract String getMerchant();

    }
}

