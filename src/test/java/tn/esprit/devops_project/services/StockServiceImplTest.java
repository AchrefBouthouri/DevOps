package tn.esprit.devops_project.services;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockServiceImplTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @Test
    void testAddStock() {
        // Créez un stock de test
        Stock stockToSave = new Stock();
        stockToSave.setIdStock(1L);
        stockToSave.setTitle("Example Stock");

        // Configurez le comportement de stockRepository.save() pour retourner le stock de test
        when(stockRepository.save(stockToSave)).thenReturn(stockToSave);

        // Appelez la méthode de service à tester
        Stock result = stockService.addStock(stockToSave);

        // Vérifiez que le résultat est le même que le stock de test
        assertEquals(stockToSave, result);

        // Vérifiez que la méthode stockRepository.save() a été appelée une fois
        verify(stockRepository, times(1)).save(stockToSave);
    }

    @Test
    void testRetrieveStock() {
        Long stockId = 1L;

        // Créez un stock de test
        Stock stock = new Stock();
        stock.setIdStock(stockId);
        stock.setTitle("Example Stock");

        // Configurez le comportement de stockRepository.findById() pour retourner le stock de test
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        // Appelez la méthode de service à tester
        Stock result = stockService.retrieveStock(stockId);

        // Vérifiez que le résultat est le même que le stock de test
        assertEquals(stock, result);

        // Vérifiez que la méthode stockRepository.findById() a été appelée une fois avec l'ID correct
        verify(stockRepository, times(1)).findById(stockId);
    }

    @Test
    void testRetrieveStock_NotFound() {
        Long stockId = 1L;

        // Configurez le comportement de stockRepository.findById() pour retourner Optional.empty()
        when(stockRepository.findById(stockId)).thenReturn(Optional.empty());

        // Appelez la méthode de service à tester et vérifiez qu'une exception est lancée
        assertThrows(NullPointerException.class, () -> {
            stockService.retrieveStock(stockId);
        });

        // Vérifiez que la méthode stockRepository.findById() a été appelée une fois avec l'ID correct
        verify(stockRepository, times(1)).findById(stockId);
    }

    @Test
    void testRetrieveAllStock() {
        // Créez une liste de stocks de test
        List<Stock> stockList = new ArrayList<>();
        stockList.add(new Stock(1L, "Stock 1", null));
        stockList.add(new Stock(2L, "Stock 2", null));
        stockList.add(new Stock(3L, "Stock 3", null));

        // Configurez le comportement de stockRepository.findAll() pour retourner la liste de stocks de test
        when(stockRepository.findAll()).thenReturn(stockList);

        // Appelez la méthode de service à tester
        List<Stock> result = stockService.retrieveAllStock();

        // Vérifiez que le résultat contient la même liste de stocks de test
        assertEquals(stockList, result);

        // Vérifiez que la méthode stockRepository.findAll() a été appelée une fois
        verify(stockRepository, times(1)).findAll();
    }
}
