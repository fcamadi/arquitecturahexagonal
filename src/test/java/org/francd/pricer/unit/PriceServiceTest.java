package org.francd.pricer.unit;

import org.francd.pricer.domain.model.Price;
import org.francd.pricer.domain.port.PricePersistencePort;
import org.francd.pricer.domain.service.PriceService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

    @Mock
    private PricePersistencePort mockRepository;

    @InjectMocks
    private PriceService priceService;


    private List<Price> priceList = new ArrayList<>();
    private static Price price1;
    private static Price price2;
    private static Price price3;
    private static Price price4;

    @BeforeAll
    public static void setup() {
        price1 = new Price(1,1, LocalDateTime.parse("2020-06-14-00.00.00",formatter),
                LocalDateTime.parse("2020-12-31-23.59.59",formatter),1,35455,0,35.5,
                "EUR",LocalDateTime.parse("2020-03-26-14.49.07",formatter),"user1");

        price2 = new Price(2,1, LocalDateTime.parse("2020-06-14-15.00.00",formatter),
                LocalDateTime.parse("2020-06-14-18.30.00",formatter),2,35455,1,25.45,
                "EUR",LocalDateTime.parse("2020-05-26-15.38.22",formatter),"user1");

        price3 = new Price(3,1, LocalDateTime.parse("2020-06-15-00.00.00",formatter),
                LocalDateTime.parse("2020-06-15-11.00.00",formatter),3,35455,1,30.5,
                "EUR",LocalDateTime.parse("2020-05-26-15.38.22",formatter),"user1");

        price4 = new Price(4,1, LocalDateTime.parse("2020-06-15-16.00.00",formatter),
                LocalDateTime.parse("2020-12-31-23.59.59",formatter),4,35455,1,38.95,
                "EUR",LocalDateTime.parse("2020-06-02-10.14.00",formatter),"user1");
    }

    //Test 0
    @Test
    public void test_findAll() {
        priceList.add(price1);
        priceList.add(price2);
        priceList.add(price3);
        priceList.add(price4);

        Mockito.when(mockRepository.findAll()).thenReturn(priceList);
        List<Price> result = priceService.findAll();
        assertEquals(4,result.size());
    }


    //Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    // expected priceList 1
    @Test
    public void test01__2020_06_14_1000_brandId_1_productId_35455() {
        priceList.add(price1);
        Mockito.when(mockRepository.findByApplicationDateBrandIdAndProductId(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(priceList);
        Price result = priceService.findByApplicationDateBrandIdAndProductId(
                LocalDateTime.parse("2020-06-14-10.00.00",formatter),1,35455);
        assertEquals(1,result.getPriceList());
    }

    //Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    // There are two prices: the one with priceList 2 has the highest priority
    @Test
    public void test02__2020_06_14_1600_brandId_1_productId_35455() {
        priceList.add(price1);
        priceList.add(price2);
        Mockito.when(mockRepository.findByApplicationDateBrandIdAndProductId(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(priceList);
        Price result = priceService.findByApplicationDateBrandIdAndProductId(
                LocalDateTime.parse("2020-06-14-16.00.00",formatter),1,35455);
        assertEquals(2,result.getPriceList());
    }

    //Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    // expected priceList 1
    @Test
    public void test03__2020_06_14_2100_brandId_1_productId_35455() {
        priceList.add(price1);
        Mockito.when(mockRepository.findByApplicationDateBrandIdAndProductId(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(priceList);
        Price result = priceService.findByApplicationDateBrandIdAndProductId(
                LocalDateTime.parse("2020-06-14-21.00.00",formatter),1,35455);
        assertEquals(1,result.getPriceList());
    }

    //Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
    // expected priceList 3
    @Test
    public void test04__2020_06_15_1000_brandId_1_productId_35455() {
        priceList.add(price1);
        priceList.add(price3);
        Mockito.when(mockRepository.findByApplicationDateBrandIdAndProductId(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(priceList);
        Price result = priceService.findByApplicationDateBrandIdAndProductId(
                LocalDateTime.parse("2020-06-15-10.00.00",formatter),1,35455);
        assertEquals(3,result.getPriceList());
    }

    //Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
    // There are two prices: the one with priceList 4 has the highest priority
    @Test
    public void test05__2020_06_16_2100_brandId_1_productId_35455() {
        priceList.add(price1);
        priceList.add(price4);
        Mockito.when(mockRepository.findByApplicationDateBrandIdAndProductId(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(priceList);
        Price result = priceService.findByApplicationDateBrandIdAndProductId(
                LocalDateTime.parse("2020-06-16-21.00.00",formatter),1,35455);
        assertEquals(4,result.getPriceList());
    }
}
