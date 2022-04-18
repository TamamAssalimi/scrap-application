package id.co.bricktest.scrapapplication.common;

public class RestConstant {

    public static final String HOST_URL = "https://gql.tokopedia.com/graphql/SearchProductQuery";

    public static final String HOST_KEY = "Host";
    public static final String HOST_VALUE = "gql.tokopedia.com";

    public static final String USER_AGENT_KEY = "User-Agent";
    public static final String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36";

    public static final String CONTENT_TYPE_KEY = "Content-Type";
    public static final String CONTENT_TYPE_VALUE = "application/graphql";

    public static final String ACCEPT_KEY = "accept";
    public static final String ACCEPT_VALUE = "*/*";

    public static final String X_SOURCE_KEY = "X-Source";
    public static final String X_SOURCE_VALUE = "tokopedia-lite";

    public static final String REFERER_KEY = "Referer";
    public static final String REFERER_VALUE = "https://www.tokopedia.com/p/handphone-tablet/handphone";

    public static final String QUERY_BODY = "query SearchProductQuery($params: String, $adParams: String) {\n" +
            "  CategoryProducts: searchProduct(params: $params) {\n" +
            "    countdata: products {\n" +
            "      id\n" +
            "      url\n" +
            "      imageUrl: image_url\n" +
            "      imageUrlLarge: image_url_700\n" +
            "      catId: category_id\n" +
            "      gaKey: ga_key\n" +
            "      countReview: count_review\n" +
            "      discountPercentage: discount_percentage\n" +
            "      preorder: is_preorder\n" +
            "      name\n" +
            "      price\n" +
            "      original_price\n" +
            "      rating\n" +
            "      wishlist\n" +
            "      labels {\n" +
            "        title\n" +
            "        color\n" +
            "        __typename\n" +
            "      }\n" +
            "      badges {\n" +
            "        imageUrl: image_url\n" +
            "        show\n" +
            "        __typename\n" +
            "      }\n" +
            "      shop {\n" +
            "        id\n" +
            "        url\n" +
            "        name\n" +
            "        goldmerchant: is_power_badge\n" +
            "        official: is_official\n" +
            "        reputation\n" +
            "        clover\n" +
            "        location\n" +
            "        __typename\n" +
            "      }\n" +
            "      labelGroups: label_groups {\n" +
            "        position\n" +
            "        title\n" +
            "        type\n" +
            "        __typename\n" +
            "      }\n" +
            "      __typename\n" +
            "    }\n" +
            "    __typename\n" +
            "  }\n" +
            "  displayAdsV3(displayParams: $adParams) {\n" +
            "    data {\n" +
            "      id\n" +
            "      ad_ref_key\n" +
            "      redirect\n" +
            "      sticker_id\n" +
            "      sticker_image\n" +
            "      productWishListUrl: product_wishlist_url\n" +
            "      clickTrackUrl: product_click_url\n" +
            "      shop_click_url\n" +
            "      product {\n" +
            "        id\n" +
            "        name\n" +
            "        wishlist\n" +
            "        image {\n" +
            "          imageUrl: s_ecs\n" +
            "          trackerImageUrl: s_url\n" +
            "          __typename\n" +
            "        }\n" +
            "        url: uri\n" +
            "        relative_uri\n" +
            "        price: price_format\n" +
            "        campaign {\n" +
            "          original_price\n" +
            "          discountPercentage: discount_percentage\n" +
            "          __typename\n" +
            "        }\n" +
            "        wholeSalePrice: wholesale_price {\n" +
            "          quantityMin: quantity_min_format\n" +
            "          quantityMax: quantity_max_format\n" +
            "          price: price_format\n" +
            "          __typename\n" +
            "        }\n" +
            "        count_talk_format\n" +
            "        countReview: count_review_format\n" +
            "        category {\n" +
            "          id\n" +
            "          __typename\n" +
            "        }\n" +
            "        preorder: product_preorder\n" +
            "        product_wholesale\n" +
            "        free_return\n" +
            "        isNewProduct: product_new_label\n" +
            "        cashback: product_cashback_rate\n" +
            "        rating: product_rating\n" +
            "        top_label\n" +
            "        bottomLabel: bottom_label\n" +
            "        __typename\n" +
            "      }\n" +
            "      shop {\n" +
            "        image_product {\n" +
            "          image_url\n" +
            "          __typename\n" +
            "        }\n" +
            "        id\n" +
            "        name\n" +
            "        domain\n" +
            "        location\n" +
            "        city\n" +
            "        tagline\n" +
            "        goldmerchant: gold_shop\n" +
            "        gold_shop_badge\n" +
            "        official: shop_is_official\n" +
            "        lucky_shop\n" +
            "        uri\n" +
            "        owner_id\n" +
            "        is_owner\n" +
            "        badges {\n" +
            "          title\n" +
            "          image_url\n" +
            "          show\n" +
            "          __typename\n" +
            "        }\n" +
            "        __typename\n" +
            "      }\n" +
            "      applinks\n" +
            "      __typename\n" +
            "    }\n" +
            "    template {\n" +
            "      isAd: is_ad\n" +
            "      __typename\n" +
            "    }\n" +
            "    __typename\n" +
            "  }\n" +
            "}\n";

    public static final String params = "identifier=handphone-tablet_handphone&sc=24&start=0&source=directory&device=desktop&rows=";
    public static final String adParams = "dep_id=24&ob=&ep=product&item=15&src=directory&device=desktop&minimum_item=15&start=0";

    public static final String REQUEST_BODY = "{\"query\":\"query SearchProductQuery($params: String, $adParams: String) {\\r\\n  CategoryProducts: searchProduct(params: $params) {\\r\\n    countdata: products {\\r\\n      id\\r\\n      url\\r\\n      imageUrl: image_url\\r\\n      imageUrlLarge: image_url_700\\r\\n      catId: category_id\\r\\n      gaKey: ga_key\\r\\n      countReview: count_review\\r\\n      discountPercentage: discount_percentage\\r\\n      preorder: is_preorder\\r\\n      name\\r\\n      price\\r\\n      original_price\\r\\n      rating\\r\\n      wishlist\\r\\n      labels {\\r\\n        title\\r\\n        color\\r\\n        __typename\\r\\n      }\\r\\n      badges {\\r\\n        imageUrl: image_url\\r\\n        show\\r\\n        __typename\\r\\n      }\\r\\n      shop {\\r\\n        id\\r\\n        url\\r\\n        name\\r\\n        goldmerchant: is_power_badge\\r\\n        official: is_official\\r\\n        reputation\\r\\n        clover\\r\\n        location\\r\\n        __typename\\r\\n      }\\r\\n      labelGroups: label_groups {\\r\\n        position\\r\\n        title\\r\\n        type\\r\\n        __typename\\r\\n      }\\r\\n      __typename\\r\\n    }\\r\\n    __typename\\r\\n  }\\r\\n  displayAdsV3(displayParams: $adParams) {\\r\\n    data {\\r\\n      id\\r\\n      ad_ref_key\\r\\n      redirect\\r\\n      sticker_id\\r\\n      sticker_image\\r\\n      productWishListUrl: product_wishlist_url\\r\\n      clickTrackUrl: product_click_url\\r\\n      shop_click_url\\r\\n      product {\\r\\n        id\\r\\n        name\\r\\n        wishlist\\r\\n        image {\\r\\n          imageUrl: s_ecs\\r\\n          trackerImageUrl: s_url\\r\\n          __typename\\r\\n        }\\r\\n        url: uri\\r\\n        relative_uri\\r\\n        price: price_format\\r\\n        campaign {\\r\\n          original_price\\r\\n          discountPercentage: discount_percentage\\r\\n          __typename\\r\\n        }\\r\\n        wholeSalePrice: wholesale_price {\\r\\n          quantityMin: quantity_min_format\\r\\n          quantityMax: quantity_max_format\\r\\n          price: price_format\\r\\n          __typename\\r\\n        }\\r\\n        count_talk_format\\r\\n        countReview: count_review_format\\r\\n        category {\\r\\n          id\\r\\n          __typename\\r\\n        }\\r\\n        preorder: product_preorder\\r\\n        product_wholesale\\r\\n        free_return\\r\\n        isNewProduct: product_new_label\\r\\n        cashback: product_cashback_rate\\r\\n        rating: product_rating\\r\\n        top_label\\r\\n        bottomLabel: bottom_label\\r\\n        __typename\\r\\n      }\\r\\n      shop {\\r\\n        image_product {\\r\\n          image_url\\r\\n          __typename\\r\\n        }\\r\\n        id\\r\\n        name\\r\\n        domain\\r\\n        location\\r\\n        city\\r\\n        tagline\\r\\n        goldmerchant: gold_shop\\r\\n        gold_shop_badge\\r\\n        official: shop_is_official\\r\\n        lucky_shop\\r\\n        uri\\r\\n        owner_id\\r\\n        is_owner\\r\\n        badges {\\r\\n          title\\r\\n          image_url\\r\\n          show\\r\\n          __typename\\r\\n        }\\r\\n        __typename\\r\\n      }\\r\\n      applinks\\r\\n      __typename\\r\\n    }\\r\\n    template {\\r\\n      isAd: is_ad\\r\\n      __typename\\r\\n    }\\r\\n    __typename\\r\\n  }\\r\\n}\\r\\n\",\"variables\":{\"params\":\"page=$page&identifier=handphone-tablet_handphone&sc=24&rows=$rowSize&start=$startRow&source=directory&device=desktop\",\"adParams\":\"dep_id=24&ob=&ep=product&item=15&src=directory&device=desktop&minimum_item=15&start=0\"}}";
}
