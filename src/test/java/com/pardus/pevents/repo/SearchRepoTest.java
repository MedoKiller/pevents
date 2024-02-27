package com.pardus.pevents.repo;

import com.pardus.pevents.model.City;
import com.pardus.pevents.model.Event;
import com.pardus.pevents.util.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
class SearchRepoTest {

    @Autowired
    SearchRepo searchRepo;

    @Autowired
    EventRepo eventRepo;

    @Autowired
    CityRepo cityRepo;

    private City savedCity1;
    private City savedCity2;

    @BeforeEach
    void setUp() throws ParseException {

        City city1=new City();
        city1.setName("City1");
        savedCity1=cityRepo.save(city1);

        City city2=new City();
        city2.setName("City2");
        savedCity2=cityRepo.save(city2);


        Event event1 = new Event("Test1", DateUtils.parseTimestamp("20.02.2024 12:00"),DateUtils.parseTimestamp("21.02.2024 12:00"),"NE",city1);
        Event event2 = new Event("Test2", DateUtils.parseTimestamp("20.02.2024 12:00"),DateUtils.parseTimestamp("22.02.2024 12:00"),"NE",city2);
        Event event3 = new Event("Test3", DateUtils.parseTimestamp("10.02.2024 12:00"),DateUtils.parseTimestamp("15.02.2024 12:00"),"DA",city1);
        Event event4 = new Event("Test4", DateUtils.parseTimestamp("01.02.2024 12:00"),DateUtils.parseTimestamp("05.02.2024 12:00"),"DA",city2);
        Event event5 = new Event("Test5", DateUtils.parseTimestamp("01.02.2024 12:00"),DateUtils.parseTimestamp("10.02.2024 12:00"),"NE",city1);

        eventRepo.save(event1);
        eventRepo.save(event2);
        eventRepo.save(event3);
        eventRepo.save(event4);
        eventRepo.save(event5);
    }

    @Test
    void doSearch_whenAllParamsNullReturnsAll() {
        // act
        List<Event> foundEvents = searchRepo.doSearch(null, null, null, null, null);

        // assert
        assertThat(foundEvents.size()).isEqualTo(5);
    }

    @Test
    void doSearch_returnWhenFreeEntrance(){
        List<Event> foundEvents = searchRepo.doSearch(null, null, null, "NE", null);

        assertThat(foundEvents.size()).isEqualTo(3);
    }

    @Test
    void doSearch_returnWhenContainsEventName(){
        // '%' is added in SearchService to have contains funcionality in query/search
        List<Event> foundEvents = searchRepo.doSearch("%est1%", null, null, null, null);

        assertThat(foundEvents.size()).isEqualTo(1);
        assertThat(foundEvents).extracting("name").contains("Test1");
    }

    @Test
    void doSearch_dateFromTest() throws ParseException {
        List<Event> foundEvents = searchRepo.doSearch(null, DateUtils.timestampToZonedDT("20.02.2024 12:00"), null, null, null);

        assertThat(foundEvents.size()).isEqualTo(2);
        assertThat(foundEvents).extracting("name").contains("Test1","Test2");
    }

    @Test
    void doSearch_dateToTest() throws ParseException {
        List<Event> foundEvents = searchRepo.doSearch(null, null, DateUtils.timestampToZonedDT("15.02.2024 12:00"), null, null);

        assertThat(foundEvents.size()).isEqualTo(3);
        assertThat(foundEvents).extracting("name").contains("Test3","Test4","Test5");
    }

    @Test
    void doSearch_dateFromDateToTest() throws ParseException {
        List<Event> foundEvents = searchRepo.doSearch(null, DateUtils.timestampToZonedDT("01.02.2024 12:00"), DateUtils.timestampToZonedDT("06.02.2024 12:00"), null, null);

        assertThat(foundEvents.size()).isEqualTo(1);
        assertThat(foundEvents).extracting("name").contains("Test4");
    }

    @Test
    void doSearch_returnWhenCity(){
        List<Event> foundEvents = searchRepo.doSearch(null, null, null, null,List.of(savedCity1.getId()));

        assertThat(foundEvents.size()).isEqualTo(3);
        assertThat(foundEvents).extracting("name").contains("Test1","Test3","Test5");
    }
}