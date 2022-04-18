package id.co.bricktest.scrapapplication.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import id.co.bricktest.scrapapplication.exception.GeneralException;
import id.co.bricktest.scrapapplication.model.Product;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ConstructorService {

    public File mapperToCsv(List<Product> products, String fileName) {
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.enable(JsonGenerator.Feature.IGNORE_UNKNOWN);
        csvMapper.addMixIn(Product.class, Product.ProductFormat.class);
        CsvSchema schema = csvMapper.schemaFor(Product.class).withHeader();
        try {
            File file = new File(fileName);
            csvMapper.writer(schema).writeValue(file, products);
            return file;
        } catch (IOException | RuntimeException e) {
            throw new GeneralException("500", "Construct Data", "CSV MAPPER", "CSV");
        }
    }
}
