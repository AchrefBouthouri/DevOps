package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.devops_project.controllers.InvoiceController;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.services.Iservices.IInvoiceService;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class InvoiceImplTest {
    private MockMvc mockMvc;

    @Mock
    private IInvoiceService invoiceService;

    @InjectMocks
    private InvoiceController invoiceController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(invoiceController).build();
    }

    @Test
    public void testGetInvoices() throws Exception {
        List<Invoice> invoices = new ArrayList<>();
        // Add test invoices to the list

        when(invoiceService.retrieveAllInvoices()).thenReturn(invoices);

        mockMvc.perform(get("/invoice")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(invoiceService, times(1)).retrieveAllInvoices();
        verifyNoMoreInteractions(invoiceService);
    }

    // Add similar test methods for other controller endpoints (retrieveInvoice, cancelInvoice, getInvoicesBySupplier, assignOperatorToInvoice, getTotalAmountInvoiceBetweenDates).
}