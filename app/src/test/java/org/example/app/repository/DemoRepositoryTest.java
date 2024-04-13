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
        Demo demo = demoRepository.findById(1L).orElse(null);
        assertThat(demo).isNotNull();
    }

    @Test
    public void testCreateAndGet() {
        Demo demo = new Demo();
        demo.setName("Test");
        demoRepository.save(demo);

        Demo createDemo = demoRepository.findById(demo.getId()).orElse(null);
        assertThat(createDemo).isNotNull();
        assertThat(createDemo.getName()).isEqualTo("Test");
    }
}