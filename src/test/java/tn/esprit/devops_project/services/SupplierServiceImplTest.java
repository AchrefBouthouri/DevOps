package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SupplierServiceImplTest {

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllSuppliers() {
        // Créez une liste de fournisseurs de test
        List<Supplier> supplierList = new ArrayList<>();
        supplierList.add(new Supplier(1L, "S1", "Supplier 1", null, null));
        supplierList.add(new Supplier(2L, "S2", "Supplier 2", null, null));
        supplierList.add(new Supplier(3L, "S3", "Supplier 3", null, null));

        // Configurez le comportement de supplierRepository.findAll() pour retourner la liste de fournisseurs de test
        when(supplierRepository.findAll()).thenReturn(supplierList);

        // Appelez la méthode de service à tester
        List<Supplier> result = supplierService.retrieveAllSuppliers();

        // Vérifiez que le résultat contient la même liste de fournisseurs de test
        assertEquals(supplierList, result);

        // Vérifiez que la méthode supplierRepository.findAll() a été appelée une fois
        verify(supplierRepository, times(1)).findAll();
    }

    @Test
    void testAddSupplier() {
        // Créez un fournisseur de test
        Supplier supplierToSave = new Supplier(1L, "S1", "Supplier 1", null, null);

        // Configurez le comportement de supplierRepository.save() pour retourner le fournisseur de test
        when(supplierRepository.save(supplierToSave)).thenReturn(supplierToSave);

        // Appelez la méthode de service à tester
        Supplier result = supplierService.addSupplier(supplierToSave);

        // Vérifiez que le résultat est le même que le fournisseur de test
        assertEquals(supplierToSave, result);

        // Vérifiez que la méthode supplierRepository.save() a été appelée une fois
        verify(supplierRepository, times(1)).save(supplierToSave);
    }

    @Test
    void testUpdateSupplier() {
        // Créez un fournisseur de test à mettre à jour
        Supplier supplierToUpdate = new Supplier(1L, "S1", "Supplier 1", null, null);

        // Configurez le comportement de supplierRepository.save() pour retourner le fournisseur mis à jour
        when(supplierRepository.save(supplierToUpdate)).thenReturn(supplierToUpdate);

        // Appelez la méthode de service à tester
        Supplier result = supplierService.updateSupplier(supplierToUpdate);

        // Vérifiez que le résultat est le même que le fournisseur mis à jour
        assertEquals(supplierToUpdate, result);

        // Vérifiez que la méthode supplierRepository.save() a été appelée une fois
        verify(supplierRepository, times(1)).save(supplierToUpdate);
    }

    @Test
    void testDeleteSupplier() {
        Long supplierId = 1L;

        // Appelez la méthode de service à tester pour supprimer un fournisseur
        supplierService.deleteSupplier(supplierId);

        // Vérifiez que la méthode supplierRepository.deleteById() a été appelée une fois avec l'ID correct
        verify(supplierRepository, times(1)).deleteById(supplierId);
    }

    @Test
    void testRetrieveSupplier() {
        Long supplierId = 1L;
        Supplier supplier = new Supplier(supplierId, "S1", "Supplier 1", null, null);

        // Configurez le comportement de supplierRepository.findById() pour retourner le fournisseur de test
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));

        // Appelez la méthode de service à tester
        Supplier result = supplierService.retrieveSupplier(supplierId);

        // Vérifiez que le résultat est le même que le fournisseur de test
        assertEquals(supplier, result);

        // Vérifiez que la méthode supplierRepository.findById() a été appelée une fois avec l'ID correct
        verify(supplierRepository, times(1)).findById(supplierId);
    }

    @Test
    void testRetrieveSupplier_NotFound() {
        Long supplierId = 1L;

        // Configurez le comportement de supplierRepository.findById() pour retourner Optional.empty()
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.empty());

        // Appelez la méthode de service à tester et vérifiez qu'une exception est lancée
        assertThrows(IllegalArgumentException.class, () -> {
            supplierService.retrieveSupplier(supplierId);
        });

        // Vérifiez que la méthode supplierRepository.findById() a été appelée une fois avec l'ID correct
        verify(supplierRepository, times(1)).findById(supplierId);
    }
}
