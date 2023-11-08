package com.pietrakpasek.medicinedata;

import com.pietrakpasek.medicinedata.model.DTO.CountResult;
import com.pietrakpasek.medicinedata.repositories.ProduktLeczniczyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class ProduktLeczniczyRepositoryUnitTests {

    @Autowired
    private ProduktLeczniczyRepository produktLeczniczyRepository;

    @Test
    void test_countSubstancjaCzynnaTop10()
    {
        Collection<Object[]> objects = produktLeczniczyRepository.countSubstancjaCzynnaTop10();

        assertThat(objects).isNotNull();
        assertThat(objects).isNotEmpty();

        Object[] o = (Object[]) objects.toArray()[0];

        CountResult firstResult = new CountResult((String)o[0], (Long)o[1], (Long)o[2]);
        assertThat(firstResult.name()).isEqualTo(null);
    }

    @Test
    void test_countPostacTop10()
    {
        Collection<CountResult> result = produktLeczniczyRepository.countPostacTop10();

        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(10);

        CountResult firstResult = (CountResult) result.toArray()[0];
        assertThat(firstResult.name()).isEqualTo("Tabletki powlekane");
    }
}
