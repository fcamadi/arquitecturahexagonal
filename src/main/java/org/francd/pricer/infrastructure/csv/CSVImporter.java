package org.francd.pricer.infrastructure.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.francd.pricer.infrastructure.entity.PriceEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CSVImporter {

    private static final Logger log = LoggerFactory.getLogger(CSVImporter.class);

    public List<PriceEntity> importCSV(String filePath) {

        List<PriceEntity> priceList = new ArrayList<>();
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            mapper.findAndRegisterModules();

            InputStream is = new ClassPathResource(filePath).getInputStream();
            MappingIterator<PriceEntity> values = mapper.readerWithSchemaFor(PriceEntity.class).with(bootstrapSchema).readValues(is);
            priceList = values.readAll();

        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return priceList;
    }
}
