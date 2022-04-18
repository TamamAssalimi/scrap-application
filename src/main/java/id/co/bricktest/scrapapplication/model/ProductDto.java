package id.co.bricktest.scrapapplication.model;


import lombok.Getter;

import java.util.List;

@Getter
public class ProductDto {

    private DataProduct data;

    @Getter
    public class DataProduct {

        private CategoryProducts CategoryProducts;

        @Getter
        public class CategoryProducts {
            private List<CountDataProduct> countdata;
        }
    }

}
