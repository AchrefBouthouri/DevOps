package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OperatorServiceImplTest {

    @Mock
    private OperatorRepository operatorRepository;

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Test
    void retrieveAllOperators() {
        // Créer une liste d'opérateurs pour le test
        List<Operator> operatorList = List.of(
                new Operator(1L, "John", "Doe", "password", null),
                new Operator(2L, "Jane", "Doe", "password", null)
        );

        // Définir le comportement du mock repository
        when(operatorRepository.findAll()).thenReturn(operatorList);

        // Appeler la méthode testée
        List<Operator> result = operatorService.retrieveAllOperators();

        // Vérifier les interactions
        verify(operatorRepository, times(1)).findAll();

        // Effectuer les assertions
        assertNotNull(result);
        assertEquals(operatorList.size(), result.size());
        assertEquals(operatorList.get(0).getFname(), result.get(0).getFname());
        assertEquals(operatorList.get(1).getFname(), result.get(1).getFname());
    }

    @Test
    void addOperator() {
        Operator operator = new Operator(1L, "John", "Doe", "password", null);

        // Définir le comportement du mock repository
        when(operatorRepository.save(any(Operator.class))).thenReturn(operator);

        // Appeler la méthode testée
        Operator result = operatorService.addOperator(operator);

        // Vérifier les interactions
        verify(operatorRepository, times(1)).save(operator);

        // Effectuer les assertions
        assertNotNull(result);
        assertEquals(operator.getFname(), result.getFname());
    }

    @Test
    void deleteOperator() {
        // Appeler la méthode testée
        operatorService.deleteOperator(1L);

        // Vérifier les interactions
        verify(operatorRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateOperator() {
        Operator operator = new Operator(1L, "John", "Doe", "newPassword", null);

        // Définir le comportement du mock repository
        when(operatorRepository.save(any(Operator.class))).thenReturn(operator);

        // Appeler la méthode testée
        Operator result = operatorService.updateOperator(operator);

        // Vérifier les interactions
        verify(operatorRepository, times(1)).save(operator);

        // Effectuer les assertions
        assertNotNull(result);
        assertEquals(operator.getPassword(), result.getPassword());
    }

    @Test
    void retrieveOperator() {
        Operator operator = new Operator(1L, "John", "Doe", "password", null);

        // Définir le comportement du mock repository
        when(operatorRepository.findById(1L)).thenReturn(Optional.of(operator));

        // Appeler la méthode testée
        Operator result = operatorService.retrieveOperator(1L);

        // Vérifier les interactions
        verify(operatorRepository, times(1)).findById(1L);

        // Effectuer les assertions
        assertNotNull(result);
        assertEquals(operator.getFname(), result.getFname());
    }
}
