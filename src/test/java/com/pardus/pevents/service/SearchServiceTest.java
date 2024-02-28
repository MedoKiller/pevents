package com.pardus.pevents.service;

import com.pardus.pevents.model.Search;
import com.pardus.pevents.repo.SearchRepo;
import com.pardus.pevents.util.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    SearchRepo searchRepo;

    @InjectMocks
    SearchService searchService;

    @Test
    void testDoSearch() {
        // arrange
        Search testSearch=new Search();
        testSearch.setName("Name");
        String searchName=(testSearch.getName()!=null) ? "%"+testSearch.getName().toLowerCase()+"%" : null;

        testSearch.setDateFrom(DateUtils.timestampToZonedDT("01.01.2024 12:00"));
        testSearch.setDateTo(DateUtils.timestampToZonedDT("02.01.2024 12:00"));
        testSearch.setFreeEntrance("NE");
        testSearch.setCityIds(List.of(1L,2L,3L));

        // act
        searchService.doSearch(testSearch);

        //verifying that repo was called with exact arguments from testSearch obj
        verify(searchRepo).doSearch(eq(searchName), eq(testSearch.getDateFrom()), eq(testSearch.getDateTo()), eq("NE"), eq(List.of(1L, 2L,3L)));
    }
}