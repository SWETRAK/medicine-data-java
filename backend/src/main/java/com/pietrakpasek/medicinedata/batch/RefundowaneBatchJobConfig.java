package com.pietrakpasek.medicinedata.batch;
import com.pietrakpasek.medicinedata.model.DTO.refundowane.ListaRefundacyjna;
import com.pietrakpasek.medicinedata.model.entities.produkt_leczniczy.ProduktLeczniczy;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
@RequiredArgsConstructor
public class RefundowaneBatchJobConfig {

    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public StaxEventItemReader<ListaRefundacyjna> listaRefundacyjnaItemReader() {
        return new StaxEventItemReaderBuilder<ListaRefundacyjna>()
                .name("itemReader")
                .resource(new ClassPathResource("refundowane.xml"))
                .addFragmentRootElements("OpakowanieLeku")
                .unmarshaller(listaRefundacyjnaMarshaller())
                .build();
    }

    @Bean
    public JpaItemWriter<ProduktLeczniczy> writeDataInTODB() {
        return new JpaItemWriterBuilder<ProduktLeczniczy>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    public ItemProcessor<ListaRefundacyjna, ListaRefundacyjna> itemProcessor() {
        return new CustomItemProcessor();
    }

    @Bean
    public Jaxb2Marshaller listaRefundacyjnaMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(new Class[] { ListaRefundacyjna.class });
        return marshaller;
    }

    public static class CustomItemProcessor implements ItemProcessor<ListaRefundacyjna, ListaRefundacyjna> {
        public ListaRefundacyjna process(ListaRefundacyjna item) {



            return item;
        }
    }
}
