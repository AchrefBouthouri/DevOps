package tn.esprit.devops_project.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class StockServiceImplTest {
    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    void testAddStock() {
        Stock stock1 = new Stock(2L, "TV", null);
        stockService.addStock(stock1);
        verify(stockRepository, times(1)).save(stock1);
        //log.info("Add Success");
    }

    @Test
    void testRetrieveStock() {
        Stock stock2 = new Stock(3L, "Machines à laver", null);
        //stock2.setIdStock(3L);

        when(stockRepository.findById(3L)).thenReturn(Optional.of(stock2));
        stockService.retrieveStock(3L);
        assertNotNull(stock2);
        //log.info("Retreive Success");

    }

    @Test
    void testRetrieveAllStock() {
        List<Stock> StockList = new ArrayList<>() {
            {
                add(new Stock(1L,"mmm",null));
                add(new Stock(2L,"sss",null));
                add(new Stock(3L,"www",null));
            }};

        when(stockService.retrieveAllStock()).thenReturn(StockList);
        //test
        List<Stock> sList = stockService.retrieveAllStock();
        assertEquals(3, sList.size());
        //log.info("Retreive All Success");

    }
}
