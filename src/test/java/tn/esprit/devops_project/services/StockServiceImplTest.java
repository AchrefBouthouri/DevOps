package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StockServiceImplTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addStock() {
        Stock stockToSave = new Stock();
        stockToSave.setIdStock(1L);
        stockToSave.setTitle("Example Stock");

        when(stockRepository.save(stockToSave)).thenReturn(stockToSave);

        Stock result = stockService.addStock(stockToSave);

        assertEquals(stockToSave, result);
    }

    @Test
    void retrieveStock() {
        Long stockId = 1L;
        Stock stock = new Stock();
        stock.setIdStock(stockId);
        stock.setTitle("Example Stock");

        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        Stock result = stockService.retrieveStock(stockId);

        assertEquals(stock, result);
    }

    @Test
    void retrieveStock_NotFound() {
        Long stockId = 1L;

        when(stockRepository.findById(stockId)).thenReturn(Optional.empty());

        // Utilisez assertThrows pour vérifier qu'une exception est lancée lorsque le stock n'est pas trouvé
        assertThrows(NullPointerException.class, () -> stockService.retrieveStock(stockId));
    }

    @Test
    void retrieveAllStock() {
        List<Stock> stockList = new ArrayList<>();
        Stock stock1 = new Stock();
        stock1.setIdStock(1L);
        stock1.setTitle("Stock 1");
        stockList.add(stock1);

        Stock stock2 = new Stock();
        stock2.setIdStock(2L);
        stock2.setTitle("Stock 2");
        stockList.add(stock2);

        when(stockRepository.findAll()).thenReturn(stockList);

        List<Stock> result = stockService.retrieveAllStock();

        assertEquals(stockList, result);
    }
}
