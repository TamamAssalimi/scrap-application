package id.co.bricktest.scrapapplication.service;

import id.co.bricktest.scrapapplication.model.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static id.co.bricktest.scrapapplication.common.RestConstant.*;
import static id.co.bricktest.scrapapplication.common.SeleniumConstant.*;

@Service
@Slf4j
public class RestService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Product> getListProduct(int count) throws IOException {
        List<Product> productList = new ArrayList<>();
        counterPage(count, 1, productList, count);
        return productList;
    }

    private ResponseBody okHttp(String param) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, param);
        Request request = new Request.Builder()
                .url(HOST_URL)
                .method("POST", body)
                .addHeader("Host", "gql.tokopedia.com")
                .addHeader("Cookie", "_UUID_NONLOGIN_=4edb8892e93e44902fdede3fa03788b7; _UUID_NONLOGIN_.sig=DOgekKqyGxmqx4YTaKwuUH12kDQ; _gcl_au=1.1.1897532470.1649825393; DID=3d414b81877391a1d07ced21fb65024212275cf8a50f26a7e3885c8c5310ef5ad3e36300065b4548215aa65cc0d955eb; DID_JS=M2Q0MTRiODE4NzczOTFhMWQwN2NlZDIxZmI2NTAyNDIxMjI3NWNmOGE1MGYyNmE3ZTM4ODVjOGM1MzEwZWY1YWQzZTM2MzAwMDY1YjQ1NDgyMTVhYTY1Y2MwZDk1NWVi47DEQpj8HBSa+/TImW+5JCeuQeRkm5NMpJWZG3hSuFU=; _gid=GA1.2.246143942.1649825394; _UUID_CAS_=819b99e8-c4ba-4949-84e6-8bdd6624752a; _CASE_=722b6d406d2b333b3b3e3d252b68406d2b3339252b656b652b332b436862687b7d6829597c7a687d2b252b6a406d2b33383e3f252b6566676e2b332b2b252b65687d2b332b2b252b794a662b332b2b252b7e406d2b33383b3b38393a3e3c252b7a406d2b3338383c3a393c3e3a252b7a5d70796c2b332b3b612b252b7e617a2b332b5272552b7e687b6c61667c7a6c56606d552b33383b3b38393a3e3c25552b7a6c7b7f606a6c567d70796c552b33552b3b61552b25552b56567d70796c6768646c552b33552b5e687b6c61667c7a6c7a552b742572552b7e687b6c61667c7a6c56606d552b333925552b7a6c7b7f606a6c567d70796c552b33552b383c64552b25552b56567d70796c6768646c552b33552b5e687b6c61667c7a6c7a552b74542b74; __auc=27291c8d1802142685b6e32a1cc; _fbp=fb.1.1649825534017.942849775; hfv_banner=true; bm_sz=26F19299DF46682DC88FB9C48AE37EB3~YAAQ1V9idn1p5x+AAQAA8a/4Jg8MvP/kc3/Q5jsbunijQJRiKddfGzb0YN5U1clNQ3bfA70MYvuUjxdAr+fLkgZ3FUs+ugjY/sOmQ00bz1O1j5wKWaX8j1eWWLaRR2wOEEuyEKePKrVCyFBS5CNeFPMD+ZDCmSOSE3Ql7We2wOXqosGtw+LQ6YzO8RlnI4VqyI5YTRvinuBvXqyoakcKbRNcTPWpHB/Bnevy1giCgHW5/Pp7ItjAm2nesNkz0OxrMNKP7kMvRWJDxOCdrUinomlDNnIHOafVOlwqsGbAVakqleEpNog=~3487556~4276534; ak_bmsc=E5B2CE5BBE1957D7B937CAE88D68347A~000000000000000000000000000000~YAAQ1V9idihq5x+AAQAAOr34Jg9pMDPJocv4IzhCxw3Z6DLtPatG5UnS4ZPB5Y6w09Bt5LKagelpdiC8rziM8hEjRaMEgctQKByBQ3CtSS4b9ShqwvwSlj7l5Q9dKTJDFq3pFtA6BmeWmMC9EzDrDQzwj4aa3IOMTmgNDcVod0tmQ6OYxnEJuikF6cpSk6QeB9cC5wiGwHyeauOW6cXHB0yDkJ5PnQapE7zRHuc9A6+w0uMrqdQyQT7KwnnD5zGhyVNGFp09QQ6f26ZBBYI8FQErfz7YY9Tk+zzliq1jubNoKxt3tLpMD+UK4dyHgNbfmINTP9w7XhG2aMFAr7DCaD00a7hjxRPlhYAYTQcDtZ/4ckLMxQ7qqKXDkWpdE7TLKbQEcYf0kYYcIEnUcbxBemQEWCSAn5Se+//3b25/XP4jWCDktsPCMuUYzDr2zVyZ95y03blQNRZE+YfmJ+AAW0bK6fcg0vOGy0pETAaxqwc3I/3NlpHZRzS+kjbSaSHodZfQAHYywzu1Wh+AxHyZEV73pN5P/A76ET6HVPLv1WE=; _SID_Tokopedia_=gz4yWPSKDvqGqzdxUxKAiExJY2Pdf8E1BIEQS-j30HC0jdPEJ1NnfKJefXZrJAQkAWIzGmIJ6zb9AYTMh259o91HxPPxMVoQDom1x7h5jTxdIEKqg0hjK77TVZ5Ql43c; AMP_TOKEN=%24NOT_FOUND; __asc=0fde7d6e18027212ed7dfbff928; _ga=GA1.2.1286761798.1649825393; _dc_gtm_UA-126956641-6=1; _gat_UA-9801603-1=1; _dc_gtm_UA-9801603-1=1; _ga_70947XW48P=GS1.1.1649923883.6.1.1649924468.57; _abck=19D2E04108143B65D3A659B6C0D1716B~-1~YAAQphFDJLHAAQOAAQAA7HMpJwePGhklLTXY6wzk6Z3KRj4PGxQ3qyUQg0CaMxhGUWYNF6id5tlw6MxfmPC7caDfBpT3ugS1A5OW09KbmGLDeONMrOHW53ZYCH5YHbIawU5T1KJjyo70W2GPqCC/KFQFRnRrtU/LMhtMRxsL04OU/adSwnP6gqW1m2TensBDFxwGsQgecFhfGPxGPoSCRB7J7VJti5zj6UqLgZhmL0l/1sZAgYrQn44W3KbCAO8nFONQ/hG8rHexYbLpwcSp9Eb1W5pF8WZ0tAzxo7RTENWiSNjE7FIM4uNiwpm+yFolWqmZ9R7U8IsV2Xcft2EDH64TImhqkZV6JMfDvIebifHywuBP0x1vREAKCT8qtu5b8Um7D2OLAbJSqG89Hw==~-1~-1~-1; _abck=19D2E04108143B65D3A659B6C0D1716B~-1~YAAQxiE1F3c+JjmAAQAALej6OQfniCTXqNFOcIVVLSbSYozc1gQnpShmcV2vHj7hDyTCaXGFXs2f9h4Dzo8+aTA63mmOii/iBa6CbR1lmv4O1QJx+EWUnp2AUBQ3rzCdnbbqEo1GJe2HL+e+srHQ0R7OhHst43M3AnCsDFAWSnz5udOAJQ4KclwQOgj430hnxiATgTYIlcCmJfTrrzycrA6MC7jGBWwvG9pdcNvOMRFtW2G3HNaodOMHoPnFD2UoLpjVhHIABqq3VhlIkTdrMeq7F86nbNAio8CLqQwucy1PZ3tqvsJltfouKq7vT9+DK/7CP4Cm49SyPl3z4GD7OzDtkP+Wu9HN3KQacN3MXrA6v8na9CwkNinNYito+DkFQ+My3+5ww20gfCwD4w==~-1~-1~-1; bm_sz=410E5F257285FDA9E4126E4C5578A9F4~YAAQxiE1F3g+JjmAAQAALej6OQ/Rde74+rbXNA1f0W58C8dPYpBiW5r9s0MzCLhSXNqiD62/xcKPXqrfuR0wDl3+31SbFq/usopMxycD7nB/3xUSq7ddsSuNxFohNMfqnkvZrq8j0XPwzgTooCYnpZOFwmNxydihCNj3NSZ3kx3VJBn/Mr1D0NugCC3WkhXRDiKt3Q7GLiyv179f6zWhCVkRwGYKeZIpdo5y7rW5kwyXHpoyBvsWaPUV+bGrIn5ayAbt81fVPejW0rL2lGe8uhNDtXrJGVOKowAbJTcT6tp60PI6iyo=~3224626~3753282")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "*/*")
                .addHeader("X-Source", "tokopedia-lite")
                .addHeader("Referer", "https://www.tokopedia.com/p/handphone-tablet/handphone")
                .build();
        ResponseBody response = client.newCall(request).execute().body();
        log.info("response {}", response);
        return response;
    }

    private HttpHeaders setHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HOST_KEY, HOST_VALUE);
        httpHeaders.add(USER_AGENT_KEY, USER_AGENT_VALUE);
        httpHeaders.add(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE);
        httpHeaders.add(ACCEPT_KEY, ACCEPT_VALUE);
        httpHeaders.add(X_SOURCE_KEY, X_SOURCE_VALUE);
        httpHeaders.add(REFERER_KEY, REFERER_VALUE);
        httpHeaders.add("Cookie", "_UUID_NONLOGIN_=4edb8892e93e44902fdede3fa03788b7; _UUID_NONLOGIN_.sig=DOgekKqyGxmqx4YTaKwuUH12kDQ; _gcl_au=1.1.1897532470.1649825393; DID=3d414b81877391a1d07ced21fb65024212275cf8a50f26a7e3885c8c5310ef5ad3e36300065b4548215aa65cc0d955eb; DID_JS=M2Q0MTRiODE4NzczOTFhMWQwN2NlZDIxZmI2NTAyNDIxMjI3NWNmOGE1MGYyNmE3ZTM4ODVjOGM1MzEwZWY1YWQzZTM2MzAwMDY1YjQ1NDgyMTVhYTY1Y2MwZDk1NWVi47DEQpj8HBSa+/TImW+5JCeuQeRkm5NMpJWZG3hSuFU=; _gid=GA1.2.246143942.1649825394; _UUID_CAS_=819b99e8-c4ba-4949-84e6-8bdd6624752a; _CASE_=722b6d406d2b333b3b3e3d252b68406d2b3339252b656b652b332b436862687b7d6829597c7a687d2b252b6a406d2b33383e3f252b6566676e2b332b2b252b65687d2b332b2b252b794a662b332b2b252b7e406d2b33383b3b38393a3e3c252b7a406d2b3338383c3a393c3e3a252b7a5d70796c2b332b3b612b252b7e617a2b332b5272552b7e687b6c61667c7a6c56606d552b33383b3b38393a3e3c25552b7a6c7b7f606a6c567d70796c552b33552b3b61552b25552b56567d70796c6768646c552b33552b5e687b6c61667c7a6c7a552b742572552b7e687b6c61667c7a6c56606d552b333925552b7a6c7b7f606a6c567d70796c552b33552b383c64552b25552b56567d70796c6768646c552b33552b5e687b6c61667c7a6c7a552b74542b74; __auc=27291c8d1802142685b6e32a1cc; _fbp=fb.1.1649825534017.942849775; hfv_banner=true; bm_sz=26F19299DF46682DC88FB9C48AE37EB3~YAAQ1V9idn1p5x+AAQAA8a/4Jg8MvP/kc3/Q5jsbunijQJRiKddfGzb0YN5U1clNQ3bfA70MYvuUjxdAr+fLkgZ3FUs+ugjY/sOmQ00bz1O1j5wKWaX8j1eWWLaRR2wOEEuyEKePKrVCyFBS5CNeFPMD+ZDCmSOSE3Ql7We2wOXqosGtw+LQ6YzO8RlnI4VqyI5YTRvinuBvXqyoakcKbRNcTPWpHB/Bnevy1giCgHW5/Pp7ItjAm2nesNkz0OxrMNKP7kMvRWJDxOCdrUinomlDNnIHOafVOlwqsGbAVakqleEpNog=~3487556~4276534; ak_bmsc=E5B2CE5BBE1957D7B937CAE88D68347A~000000000000000000000000000000~YAAQ1V9idihq5x+AAQAAOr34Jg9pMDPJocv4IzhCxw3Z6DLtPatG5UnS4ZPB5Y6w09Bt5LKagelpdiC8rziM8hEjRaMEgctQKByBQ3CtSS4b9ShqwvwSlj7l5Q9dKTJDFq3pFtA6BmeWmMC9EzDrDQzwj4aa3IOMTmgNDcVod0tmQ6OYxnEJuikF6cpSk6QeB9cC5wiGwHyeauOW6cXHB0yDkJ5PnQapE7zRHuc9A6+w0uMrqdQyQT7KwnnD5zGhyVNGFp09QQ6f26ZBBYI8FQErfz7YY9Tk+zzliq1jubNoKxt3tLpMD+UK4dyHgNbfmINTP9w7XhG2aMFAr7DCaD00a7hjxRPlhYAYTQcDtZ/4ckLMxQ7qqKXDkWpdE7TLKbQEcYf0kYYcIEnUcbxBemQEWCSAn5Se+//3b25/XP4jWCDktsPCMuUYzDr2zVyZ95y03blQNRZE+YfmJ+AAW0bK6fcg0vOGy0pETAaxqwc3I/3NlpHZRzS+kjbSaSHodZfQAHYywzu1Wh+AxHyZEV73pN5P/A76ET6HVPLv1WE=; _SID_Tokopedia_=gz4yWPSKDvqGqzdxUxKAiExJY2Pdf8E1BIEQS-j30HC0jdPEJ1NnfKJefXZrJAQkAWIzGmIJ6zb9AYTMh259o91HxPPxMVoQDom1x7h5jTxdIEKqg0hjK77TVZ5Ql43c; AMP_TOKEN=%24NOT_FOUND; __asc=0fde7d6e18027212ed7dfbff928; _ga=GA1.2.1286761798.1649825393; _dc_gtm_UA-126956641-6=1; _gat_UA-9801603-1=1; _dc_gtm_UA-9801603-1=1; _ga_70947XW48P=GS1.1.1649923883.6.1.1649924468.57; _abck=19D2E04108143B65D3A659B6C0D1716B~-1~YAAQphFDJLHAAQOAAQAA7HMpJwePGhklLTXY6wzk6Z3KRj4PGxQ3qyUQg0CaMxhGUWYNF6id5tlw6MxfmPC7caDfBpT3ugS1A5OW09KbmGLDeONMrOHW53ZYCH5YHbIawU5T1KJjyo70W2GPqCC/KFQFRnRrtU/LMhtMRxsL04OU/adSwnP6gqW1m2TensBDFxwGsQgecFhfGPxGPoSCRB7J7VJti5zj6UqLgZhmL0l/1sZAgYrQn44W3KbCAO8nFONQ/hG8rHexYbLpwcSp9Eb1W5pF8WZ0tAzxo7RTENWiSNjE7FIM4uNiwpm+yFolWqmZ9R7U8IsV2Xcft2EDH64TImhqkZV6JMfDvIebifHywuBP0x1vREAKCT8qtu5b8Um7D2OLAbJSqG89Hw==~-1~-1~-1");
        return httpHeaders;
    }

    private void counterPage(int count, int page, List<Product> productList, int countRequest) throws IOException {
        String queryBody;
        int startRow = (60 * page) - 60;
        if (count > 60) {
            queryBody = REQUEST_BODY.replace("$rowSize", String.valueOf(60))
                    .replace("$page", String.valueOf(page))
                    .replace("$startRow", String.valueOf(startRow));
            parseToProduct(queryBody, productList, countRequest);
            page++;
            count = count - 60;
            counterPage(count, page, productList, countRequest);
        } else {
            queryBody = REQUEST_BODY.replace("$rowSize", String.valueOf(count))
                    .replace("$page", String.valueOf(page))
                    .replace("$startRow", String.valueOf(startRow));
            parseToProduct(queryBody, productList, countRequest);
        }
    }

    private List<Product> parseToProduct(String param, List<Product> productList, int countRequest) throws IOException {
        ResponseBody responseBody = okHttp(param);
        JSONObject root = new JSONObject(responseBody.string());
        JSONObject data = root.getJSONObject("data");
        JSONObject categoryProducts = data.getJSONObject("CategoryProducts");
        JSONArray countdata = categoryProducts.getJSONArray("countdata");
        for (Object object : countdata) {
            JSONObject product = (JSONObject) object;
            JSONObject shop = product.getJSONObject("shop");
            productList.add(Product.builder()
                    .name(null != product.get("name") ? String.valueOf(product.get("name")) : "")
                    .price(null != product.get("price") ? Double.parseDouble(String.valueOf(product.get("price")).split(RUPIAH_SIGN)[1].replace(DOT, EMPTY)) : 0.0)
                    .imageUrl(null != product.get("imageUrl") ? String.valueOf(product.get("imageUrl")) : "")
                    .rating(null != product.get("rating") ? Double.parseDouble(String.valueOf(product.get("rating"))) : 0.0)
                    .merchant(null != shop.get("name") ? String.valueOf(shop.get("name")) : "")
                    .description("NA")
                    .build());
            if (productList.size() == countRequest)
                break;
        }
        return productList;
    }
}
