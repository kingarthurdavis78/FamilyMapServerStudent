package service;
import dao.DataAccessException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClearServiceTest {


    // TODO: 3/5/2020  add tests for clear service

    @BeforeEach
    public void setUp() throws DataAccessException {
    }

    @Test
    public void clear() {
        ClearService clearService = new ClearService();
        clearService.clear();


    }

}
