package id.co.bricktest.scrapapplication.controller;

import id.co.bricktest.scrapapplication.exception.GeneralException;
import id.co.bricktest.scrapapplication.model.Product;
import id.co.bricktest.scrapapplication.service.ConstructorService;
import id.co.bricktest.scrapapplication.service.RestService;
import id.co.bricktest.scrapapplication.service.SeleniumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/v1/products")
@Slf4j
public class ProductController {

    @Autowired
    private ConstructorService constructorService;
    @Autowired
    private SeleniumService seleniumService;
    @Autowired
    private RestService restService;

    @GetMapping(value = "/handphone")
    public ResponseEntity downloadListProductMobilePhone(@RequestParam int count, @RequestParam(required = false) boolean rest) throws IOException {
        String filename = "LIST_PRODUCT_MOBILE_PHONE_" + System.currentTimeMillis() + ".csv";
        try {
            List<Product> products;
            if (rest) products = restService.getListProduct(count);
            else products = seleniumService.scrapWebTarget(count);
            File file = constructorService.mapperToCsv(products, filename);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + filename)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(new FileSystemResource(file));
        } catch (GeneralException e) {
            log.error("downloadListProductMobilePhone {}", e);
            return ResponseEntity.accepted().body(e);
        }
    }
}
