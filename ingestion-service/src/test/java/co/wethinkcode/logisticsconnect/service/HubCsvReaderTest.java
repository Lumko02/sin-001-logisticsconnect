package co.wethinkcode.logisticsconnect.service;

import co.wethinkcode.logisticsconnect.model.Hub;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HubCsvReaderTest {

    @Test
    void shouldReadAndCleanHubData() throws IOException {
        HubCsvReader reader = new HubCsvReader();

        List<Hub> hubs = reader.readHubs();

        assertFalse(hubs.isEmpty());

        Hub firstHub = hubs.get(0);

        assertEquals("H-500", firstHub.getHubId());
        assertEquals("Gauteng", firstHub.getProvince());
        assertEquals("Johannesburg Central", firstHub.getSortingCenter());
        assertTrue(firstHub.isActive());
    }

    @Test
    void shouldNormalizeLowercaseHubId() throws IOException {
        HubCsvReader reader = new HubCsvReader();

        List<Hub> hubs = reader.readHubs();

        Hub secondHub = hubs.get(1);

        assertEquals("H-501", secondHub.getHubId());
        assertTrue(secondHub.isActive());
    }

    @Test
    void shouldConvertZeroToInactive() throws IOException {
        HubCsvReader reader = new HubCsvReader();

        List<Hub> hubs = reader.readHubs();

        Hub thirdHub = hubs.get(2);

        assertEquals("H-502", thirdHub.getHubId());
        assertEquals("Gauteng", thirdHub.getProvince());
        assertFalse(thirdHub.isActive());
    }
}