package com.pietrakpasek.medicinedata.services;

import com.pietrakpasek.medicinedata.model.DTO.CountResult;
import com.pietrakpasek.medicinedata.repositories.OpakowanieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class OpakowanieServiceImplTest {

    @Mock
    private OpakowanieRepository opakowanieRepository;

    @InjectMocks
    private OpakowanieServiceImpl opakowanieService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testCountKategoriaDostepnosci_EmptyList() {
        when(opakowanieRepository.countKategoriaDostepnosci()).thenReturn(Collections.emptyList());

        assertThrows(NullPointerException.class, () -> opakowanieService.countKategoriaDostepnosci());
    }

    @Test
    public void testCountKategoriaDostepnosci_NonEmptyList() {
        CountResult countResult = new CountResult("Nazwa", 10L, 20L);
        List<CountResult> countResults = Collections.singletonList(countResult);

        when(opakowanieRepository.countKategoriaDostepnosci()).thenReturn(countResults);

        List<CountResult> result = opakowanieService.countKategoriaDostepnosci();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Nazwa", result.get(0).name());
        assertEquals(10L, result.get(0).value());
        assertEquals(20L, result.get(0).value2());
    }

    @Test
    public void testListAllSortParameters_ReturnsAll() {
        List<String> expectedFieldNames = List.of("wielkosc", "jednostkaWielkosci", "kodEAN", "kategoriaDostepnosci", "skasowane", "numerEU", "dystrybutorRownolegly");

        List<String> result = opakowanieService.listAllSortParameters();
        assertNotNull(result);
        assertEquals(expectedFieldNames.size(), result.size());
        assertTrue(result.containsAll(expectedFieldNames));
    }
}

