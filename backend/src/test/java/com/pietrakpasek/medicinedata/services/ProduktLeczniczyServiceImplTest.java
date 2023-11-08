package com.pietrakpasek.medicinedata.services;

import com.pietrakpasek.medicinedata.exceptions.EmptyPageException;
import com.pietrakpasek.medicinedata.model.DTO.*;
import com.pietrakpasek.medicinedata.model.entities.produkt_leczniczy.ProduktLeczniczy;
import com.pietrakpasek.medicinedata.model.mappers.ProduktLeczniczyDTOMapper;
import com.pietrakpasek.medicinedata.repositories.ProduktLeczniczyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProduktLeczniczyServiceImplTest {

    @Mock
    private ProduktLeczniczyRepository repository;

    @Mock
    private ProduktLeczniczyDTOMapper mapper;

    @InjectMocks
    private ProduktLeczniczyServiceImpl produktLeczniczyService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProduktLeczniczyPage_EmptyListException() {
        int page = 0;
        String sortBy = "null";
        boolean isAscending = true;

        when(repository.findAllBy(any(PageRequest.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        assertThrows(EmptyPageException.class, () -> produktLeczniczyService.getAllProduktLeczniczyPage(page, sortBy, isAscending));
    }

    @Test
    public void testGetAllProduktLeczniczyPage_Success() {
        int page = 0;
        String sortBy = "null";
        boolean isAscending = true;

        ProduktLeczniczy produktLeczniczy = new ProduktLeczniczy();

        ProduktLeczniczyDTO produktLeczniczyDTO = new ProduktLeczniczyDTO(
                produktLeczniczy.getId(),
                "nazwaProduktu",
                "rodzajPreparatu",
                "nazwaPowszechnieStosowana",
                "moc",
                "postac",
                "podmiotOdpowiedzialny",
                "typProcedury",
                "numerPozwolenia",
                "waznoscPozwolenia",
                "kodATC",
                Collections.singletonList(new OpakowanieDTO(1, "jednostkaWielkosci", "EAN", "kategoriaDostepnosci", "tak", "numerEU", "dystrybutorRownolegly")),
                Collections.singletonList(new SubstancjaCzynnaDTO("test")),
                true
        );
        List<ProduktLeczniczy> produktyList = Collections.singletonList(produktLeczniczy);
        Page<ProduktLeczniczy> produktyPage = new PageImpl<>(produktyList);

        when(mapper.produktLeczniczyToProduktLeczniczyDTO(any())).thenReturn(produktLeczniczyDTO);
        when(repository.findAllBy(any(PageRequest.class))).thenReturn(produktyPage);

        Page<ProduktLeczniczyDTO> result = produktLeczniczyService.getAllProduktLeczniczyPage(page, sortBy, isAscending);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.getNumberOfElements());
    }

    @Test
    public void testGetAllProduktLeczniczyPage_AscendingOrder() {
        int page = 0;
        String sortBy = "nazwaProduktu";
        boolean isAscending = true;

        ProduktLeczniczy produktLeczniczy1 = new ProduktLeczniczy();
        ProduktLeczniczy produktLeczniczy2 = new ProduktLeczniczy();

        ProduktLeczniczyDTO produktLeczniczyDTO1 = new ProduktLeczniczyDTO(
                produktLeczniczy1.getId(),
                "nazwaProduktu",
                "rodzajPreparatu",
                "nazwaPowszechnieStosowana",
                "moc",
                "postac",
                "podmiotOdpowiedzialny",
                "typProcedury",
                "numerPozwolenia",
                "waznośćPozwolenia",
                "kodATC",
                Collections.singletonList(new OpakowanieDTO(1, "jednostkaWielkosci", "EAN", "kategoriaDostepnosci", "tak", "numerEU", "dystrybutorRownolegly")),
                Collections.singletonList(new SubstancjaCzynnaDTO("test")),
                true
        );

        ProduktLeczniczyDTO produktLeczniczyDTO2 = new ProduktLeczniczyDTO(
                produktLeczniczy2.getId(),
                "znazwaProduktu",
                "rodzajPreparatu",
                "nazwaPowszechnieStosowana",
                "moc",
                "postac",
                "podmiotOdpowiedzialny",
                "typProcedury",
                "numerPozwolenia",
                "waznoscPozwolenia",
                "kodATC",
                Collections.singletonList(new OpakowanieDTO(1, "jednostkaWielkosci", "EAN", "kategoriaDostepnosci", "tak", "numerEU", "dystrybutorRownolegly")),
                Collections.singletonList(new SubstancjaCzynnaDTO("test")),
                true
        );

        List<ProduktLeczniczy> produktyList = new ArrayList<>();
        produktyList.add(produktLeczniczy1);
        produktyList.add(produktLeczniczy2);

        Page<ProduktLeczniczy> produktyPage = new PageImpl<>(produktyList);

        when(mapper.produktLeczniczyToProduktLeczniczyDTO(any())).thenReturn(produktLeczniczyDTO1, produktLeczniczyDTO2);
        when(repository.findAllBy(any(PageRequest.class))).thenReturn(produktyPage);

        Page<ProduktLeczniczyDTO> result = produktLeczniczyService.getAllProduktLeczniczyPage(page, sortBy, isAscending);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.getNumberOfElements());
        assertEquals("nazwaProduktu", result.getContent().get(0).nazwaProduktu());
        assertEquals("znazwaProduktu", result.getContent().get(1).nazwaProduktu());
    }
    @Test
    public void testGetAllProduktLeczniczyPage_DescendingOrder() {
        int page = 0;
        String sortBy = "nazwaProduktu";
        boolean isAscending = false;

        ProduktLeczniczy produktLeczniczy1 = new ProduktLeczniczy();
        ProduktLeczniczy produktLeczniczy2 = new ProduktLeczniczy();

        ProduktLeczniczyDTO produktLeczniczyDTO1 = new ProduktLeczniczyDTO(
                produktLeczniczy1.getId(),
                "anazwaProduktu",
                "rodzajPreparatu",
                "nazwaPowszechnieStosowana",
                "moc",
                "postac",
                "podmiotOdpowiedzialny",
                "typProcedury",
                "numerPozwolenia",
                "waznośćPozwolenia",
                "kodATC",
                Collections.singletonList(new OpakowanieDTO(1, "jednostkaWielkosci", "EAN", "kategoriaDostepnosci", "tak", "numerEU", "dystrybutorRownolegly")),
                Collections.singletonList(new SubstancjaCzynnaDTO("test")),
                true
        );

        ProduktLeczniczyDTO produktLeczniczyDTO2 = new ProduktLeczniczyDTO(
                produktLeczniczy2.getId(),
                "nazwaProduktu",
                "rodzajPreparatu",
                "nazwaPowszechnieStosowana",
                "moc",
                "postac",
                "podmiotOdpowiedzialny",
                "typProcedury",
                "numerPozwolenia",
                "waznoscPozwolenia",
                "kodATC",
                Collections.singletonList(new OpakowanieDTO(1, "jednostkaWielkosci", "EAN", "kategoriaDostepnosci", "tak", "numerEU", "dystrybutorRownolegly")),
                Collections.singletonList(new SubstancjaCzynnaDTO("test")),
                true
        );

        List<ProduktLeczniczy> produktyList = new ArrayList<>();
        produktyList.add(produktLeczniczy1);
        produktyList.add(produktLeczniczy2);

        Page<ProduktLeczniczy> produktyPage = new PageImpl<>(produktyList);

        when(mapper.produktLeczniczyToProduktLeczniczyDTO(any())).thenReturn(produktLeczniczyDTO1, produktLeczniczyDTO2);
        when(repository.findAllBy(any(PageRequest.class))).thenReturn(produktyPage);

        Page<ProduktLeczniczyDTO> result = produktLeczniczyService.getAllProduktLeczniczyPage(page, sortBy, isAscending);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.getNumberOfElements());
        assertEquals("anazwaProduktu", result.getContent().get(0).nazwaProduktu());
        assertEquals("nazwaProduktu", result.getContent().get(1).nazwaProduktu());
    }
}

