package org.example.app.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.app.PostgresTestContainer;
import org.example.app.TestApplication;
import org.example.app.entity.Demo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(PostgresTestContainer.class)
@SpringJUnitConfig(TestApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DemoRepositoryTest {
    @Autowired
    private DemoRepository demoRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    public void testEntityWithIdOneExists() {
        assertThat(demoRepository.findById(1L))
                .isPresent()
                .get()
                .satisfies(
                        demo -> assertThat(demo.getName()).isEqualTo("Sasha"),
                        demo -> assertThat(demo.getCreatedAt()).isNotNull());
    }

    @Test
    public void testCreateAndGet() {
        Demo demo = new Demo();
        demo.setName("Test");
        demoRepository.save(demo);

        em.flush();
        em.clear();

        assertThat(demoRepository.findById(demo.getId()))
                .isPresent()
                .get()
                .satisfies(
                        createDemo -> assertThat(createDemo.getName()).isEqualTo("Test"),
                        createDemo -> assertThat(createDemo.getCreatedAt()).isNotNull());
    }
}