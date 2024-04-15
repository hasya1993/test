package org.example.app.repository;

import org.example.app.PostgresTestContainer;
import org.example.app.TestApplication;
import org.example.app.entity.Demo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(PostgresTestContainer.class)
@SpringJUnitConfig(TestApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DemoRepositoryTest {
    @Autowired
    private DemoRepository demoRepository;

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
        demo.setCreatedAt(LocalDateTime.now());
        demoRepository.save(demo);

        assertThat(demoRepository.findById(demo.getId()))
                .isPresent()
                .get()
                .satisfies(
                        createDemo -> assertThat(createDemo.getName()).isEqualTo("Test"),
                        createDemo -> assertThat(createDemo.getCreatedAt()).isNotNull());
    }
}