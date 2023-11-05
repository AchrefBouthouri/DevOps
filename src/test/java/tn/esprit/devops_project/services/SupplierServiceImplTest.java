package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
class SupplierServiceImplTest {

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllSuppliers() {
        List<Supplier> supplierList = new ArrayList<>();
        supplierList.add(new Supplier(1L, "S1", "Supplier 1", null, null));
        supplierList.add(new Supplier(2L, "S2", "Supplier 2", null, null));
        supplierList.add(new Supplier(3L, "S3", "Supplier 3", null, null));

        Mockito.when(supplierRepository.findAll()).thenReturn(supplierList);

        List<Supplier> result = supplierService.retrieveAllSuppliers();

        assertEquals(supplierList, result);
    }

    @Test
    void addSupplier() {
        Supplier supplierToSave = new Supplier(1L, "S1", "Supplier 1", null, null);

        Mockito.when(supplierRepository.save(supplierToSave)).thenReturn(supplierToSave);

        Supplier result = supplierService.addSupplier(supplierToSave);

        assertEquals(supplierToSave, result);
    }

    @Test
    void updateSupplier() {
        Supplier supplierToUpdate = new Supplier(1L, "S1", "Supplier 1", null, null);

        Mockito.when(supplierRepository.save(supplierToUpdate)).thenReturn(supplierToUpdate);

        Supplier result = supplierService.updateSupplier(supplierToUpdate);

        assertEquals(supplierToUpdate, result);
    }

    @Test
    void deleteSupplier() {
        Long supplierId = 1L;

        supplierService.deleteSupplier(supplierId);

        Mockito.verify(supplierRepository).deleteById(supplierId);
    }

    @Test
    void retrieveSupplier() {
        Long supplierId = 1L;
        Supplier supplier = new Supplier(supplierId, "S1", "Supplier 1", null, null);

        Mockito.when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));

        Supplier result = supplierService.retrieveSupplier(supplierId);

        assertEquals(supplier, result);
    }

    @Test
    void retrieveSupplier_NotFound() {
        Long supplierId = 1L;

        Mockito.when(supplierRepository.findById(supplierId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            supplierService.retrieveSupplier(supplierId);
        });
    }



}
