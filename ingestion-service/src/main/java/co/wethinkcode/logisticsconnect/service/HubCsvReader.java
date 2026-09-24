package co.wethinkcode.logisticsconnect.service;

import co.wethinkcode.logisticsconnect.model.Hub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HubCsvReader {

    public List<Hub> readHubs() throws IOException {
        List<Hub> hubs = new ArrayList<>();

        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("hubs-global.csv");

        if (inputStream == null) {
            throw new IOException("hubs-global.csv not found");
        }

        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(inputStream))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                String hubId = values[0].trim().toUpperCase();
                String province = cleanProvince(values[1]);
                String sortingCenter = values[2].trim();
                boolean active = cleanActive(values[3]);

                hubs.add(new Hub(
                        hubId,
                        province,
                        sortingCenter,
                        active
                ));
            }
        }

        return hubs;
    }

    private String cleanProvince(String province) {
        String cleaned = province.trim();

        if (cleaned.equalsIgnoreCase("gauteng")) {
            return "Gauteng";
        }

        return cleaned;
    }

    private boolean cleanActive(String active) {
        String cleaned = active.trim().toLowerCase();

        return cleaned.equals("y")
                || cleaned.equals("yes")
                || cleaned.equals("true")
                || cleaned.equals("1");
    }
}