package com.example.transports;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.protobuf.util.JsonFormat;
import org.junit.jupiter.api.Test;

class NextDeparturesJsonMappingTest {

    @Test
    void parsesTypicalNextDeparturesPayload() throws Exception {
        String json = """
                {
                  "requestTime": 1785869160,
                  "stopDepartures": [
                    {
                      "stopId": "15212852765862963824",
                      "countryIso": "GBR",
                      "countryUrl": "uk",
                      "stopName": "Daisy Hill Church",
                      "urlStopName": "Daisy-Hill-Church",
                      "stopLat": 53.534886,
                      "stopLon": -2.517827,
                      "stopDesc": "o/s",
                      "stopCode": "MANJWMTM",
                      "departureList": [
                        {
                          "tripId": "1-ab1c6cdba56dd500729e",
                          "routeId": "3210394135-3025775907",
                          "countryIso": "GBR",
                          "countryUrl": "uk",
                          "routeShortName": "607",
                          "routeLongName": "Wigan - Bolton",
                          "urlRouteShortName": "607",
                          "routeDesc": null,
                          "routeColor": "3183E9",
                          "routeTextColor": "000000",
                          "routeType": "bus",
                          "tripHeadsign": "Wigan via Hindley",
                          "departureTime": "19:54:00",
                          "date": "2026-08-04",
                          "agencyId": "3737762901-2835720063"
                        }
                      ],
                      "agencies": [
                        {
                          "agencyId": "3737762901-2835720063",
                          "countryIso": "GBR",
                          "countryUrl": "uk",
                          "agencyName": "Bee Network",
                          "agencyUrl": "https://beenetwork.com",
                          "agencyPhone": "01612441000"
                        }
                      ]
                    }
                  ],
                  "alerts": [
                    {
                      "id": "4b2d1c34a14b",
                      "origin": "API",
                      "cause": "MAINTENANCE",
                      "effect": "DETOUR",
                      "header": "Market Street, Hindley - Roadworks",
                      "description": "Due to roadworks",
                      "url": "",
                      "validFrom": "2026-07-30T11:27:00+01:00",
                      "validUntil": "2026-08-31T23:59:00+01:00",
                      "operator": "3737762901-2835720063",
                      "countryIso": "GBR",
                      "countryUrl": "uk",
                      "informedEntity": [
                        {
                          "routeId": "3210394135-3025775907",
                          "countryIso": "GBR",
                          "countryUrl": "uk"
                        }
                      ]
                    }
                  ],
                  "localDate": "2026-08-04",
                  "localTime": "19:46:00",
                  "regionName": "uk_ireland",
                  "imperial": true,
                  "processingTimeMs": 14.61
                }
                """;

        NextDeparturesResponse.Builder builder = NextDeparturesResponse.newBuilder();
        JsonFormat.parser().ignoringUnknownFields().merge(json, builder);

        NextDeparturesResponse response = builder.build();

        assertEquals(1, response.getStopDeparturesCount());
        StopDeparture stopDeparture = response.getStopDepartures(0);
        assertEquals("15212852765862963824", stopDeparture.getStopId());
        assertEquals("GBR", stopDeparture.getCountryIso());
        assertEquals("uk", stopDeparture.getCountryUrl());
        assertEquals("Daisy Hill Church", stopDeparture.getStopName());
        assertEquals("Daisy-Hill-Church", stopDeparture.getUrlStopName());
        assertEquals(53.534886, stopDeparture.getStopLat());
        assertEquals(-2.517827, stopDeparture.getStopLon());
        assertEquals("o/s", stopDeparture.getStopDesc());
        assertEquals("MANJWMTM", stopDeparture.getStopCode());

        assertEquals(1, stopDeparture.getDepartureListCount());
        DepartureItem departure = stopDeparture.getDepartureList(0);
        assertEquals("1-ab1c6cdba56dd500729e", departure.getTripId());
        assertEquals("3210394135-3025775907", departure.getRouteId());
        assertEquals("GBR", departure.getCountryIso());
        assertEquals("uk", departure.getCountryUrl());
        assertEquals("607", departure.getRouteShortName());
        assertEquals("Wigan - Bolton", departure.getRouteLongName());
        assertEquals("607", departure.getUrlRouteShortName());
        assertEquals("3183E9", departure.getRouteColor());
        assertEquals("000000", departure.getRouteTextColor());
        assertEquals("bus", departure.getRouteType());
        assertEquals("Wigan via Hindley", departure.getTripHeadsign());
        assertEquals("19:54:00", departure.getDepartureTime());
        assertEquals("2026-08-04", departure.getDate());
        assertEquals("3737762901-2835720063", departure.getAgencyId());
        assertFalse(departure.hasRouteDesc());

        assertEquals(1, stopDeparture.getAgenciesCount());
        Agency agency = stopDeparture.getAgencies(0);
        assertEquals("3737762901-2835720063", agency.getAgencyId());
        assertEquals("GBR", agency.getCountryIso());
        assertEquals("uk", agency.getCountryUrl());
        assertEquals("Bee Network", agency.getAgencyName());
        assertEquals("https://beenetwork.com", agency.getAgencyUrl());
        assertEquals("01612441000", agency.getAgencyPhone());

        assertEquals(1, response.getAlertsCount());
        Alert alert = response.getAlerts(0);
        assertEquals("4b2d1c34a14b", alert.getId());
        assertEquals("API", alert.getOrigin());
        assertEquals("MAINTENANCE", alert.getCause());
        assertEquals("DETOUR", alert.getEffect());
        assertEquals("Market Street, Hindley - Roadworks", alert.getHeader());
        assertEquals("Due to roadworks", alert.getDescription());
        assertEquals("", alert.getUrl());
        assertEquals("2026-07-30T11:27:00+01:00", alert.getValidFrom());
        assertEquals("2026-08-31T23:59:00+01:00", alert.getValidUntil());
        assertEquals("3737762901-2835720063", alert.getOperator());
        assertEquals("GBR", alert.getCountryIso());
        assertEquals("uk", alert.getCountryUrl());

        assertEquals(1, alert.getInformedEntityCount());
        InformedEntity informedEntity = alert.getInformedEntity(0);
        assertEquals("3210394135-3025775907", informedEntity.getRouteId());
        assertEquals("GBR", informedEntity.getCountryIso());
        assertEquals("uk", informedEntity.getCountryUrl());

        assertEquals(1785869160L, response.getRequestTime());
        assertEquals("2026-08-04", response.getLocalDate());
        assertEquals("19:46:00", response.getLocalTime());
        assertTrue(response.getImperial());
        assertEquals("uk_ireland", response.getRegionName());
        assertEquals(14.61, response.getProcessingTimeMs());
    }
}
