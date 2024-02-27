package com.pardus.pevents.repo;

import com.pardus.pevents.model.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EventRepoTest {

    @Autowired
    private EventRepo testEventRepo;

    @AfterEach
    void tearDown() {
        testEventRepo.deleteAll();
    }

    @Test
    public void testDeleteEventById(){
        // arrange
        Event testEvent = new Event("TestEvent",null,null,null,null);
        Event savedTestEvent = testEventRepo.save(testEvent);

        //act
        testEventRepo.deleteEventById(savedTestEvent.getId());

        // assert
        Optional<Event> deletedEvent = testEventRepo.findEventById(savedTestEvent.getId());
        assertThat(deletedEvent).isEmpty();
    }

    @Test
    public void testFindEventById(){
        // arrange
        Event testEvent= new Event("Test123",null,null,null,null);
        Event savedEvent=testEventRepo.save(testEvent);

        // act
        Optional<Event> resultEvent = testEventRepo.findEventById(savedEvent.getId());

        // assert
        assertThat(resultEvent.isPresent()).isEqualTo(true);
        assertThat(resultEvent.get().getName()).isEqualTo("Test123");

    }

}
