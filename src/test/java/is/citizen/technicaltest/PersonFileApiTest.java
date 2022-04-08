package is.citizen.technicaltest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import is.citizen.technicaltest.dto.ErrorResponseDto;
import is.citizen.technicaltest.model.PersonModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

import static is.citizen.technicaltest.utils.TestConstant.CSV_FILE_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PersonFileApiTest {
    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    public void testRequestThenSuccess() {
        ResponseEntity<JsonNode> response = executeFileRequest(null, null, null, false);
        List<PersonModel> body = objectMapper.convertValue(response.getBody(), new TypeReference<>() {});
        assertEquals(10, body.size());
        assertEquals("Fred", body.get(0).getFirstname());
        assertEquals("Rebecca", body.get(9).getFirstname());
    }

    @Test
    public void testSortRequestThenSuccess() {
        ResponseEntity<JsonNode> response = executeFileRequest("firstname,ASC", null, null, false);
        List<PersonModel> body = objectMapper.convertValue(response.getBody(), new TypeReference<>() {});
        assertEquals(10, body.size());
        assertEquals("Alan", body.get(0).getFirstname());
        assertEquals("Sarah", body.get(9).getFirstname());
    }

    @Test
    public void testFilterRequestThenSuccess() {
        ResponseEntity<JsonNode> response = executeFileRequest(null, "city,Glasgow", null, false);
        List<PersonModel> body = objectMapper.convertValue(response.getBody(), new TypeReference<>() {});
        assertEquals(2, body.size());
        assertEquals("Glasgow", body.get(0).getCity());
        assertEquals("Glasgow", body.get(1).getCity());
    }

    @Test
    public void testGroupAndNormalizeRequestThenSuccess() {
        ResponseEntity<JsonNode> response = executeFileRequest(null, null, "countryCode", true);
        Map<String, List<PersonModel>> body = objectMapper.convertValue(response.getBody(), new TypeReference<>() {});
        assertEquals(1, body.size());
        List<PersonModel> personModels = body.get("UK");
        assertEquals(10, personModels.size());
        assertEquals("Customs House", personModels.get(6).getSecondAddress());
    }

    @Test
    public void testSortFilterAndGroupRequestWhenDataIsNormalizedThenSuccess() {
        ResponseEntity<JsonNode> response =
                executeFileRequest("firstname,ASC", "city,Glasgow", "countryCode", true);
        Map<String, List<PersonModel>> body = objectMapper.convertValue(response.getBody(), new TypeReference<>() {});
        assertEquals(1, body.size());
        List<PersonModel> personModels = body.get("UK");
        assertEquals(2, personModels.size());
        assertEquals("Customs House", personModels.get(1).getSecondAddress());
    }

    @Test
    public void testRequestWhenSomeParameterIsInvalidThanFailed() {
        ResponseEntity<JsonNode> response =
                executeFileRequest("firs7tname,ASC", null, null, true);
        ErrorResponseDto body = objectMapper.convertValue(response.getBody(), new TypeReference<>() {});
        assertEquals("bad_request", body.getType());
    }

    public ResponseEntity<JsonNode> executeFileRequest(String sort, String filter, String group, boolean normalize) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        MockMultipartFile file = new MockMultipartFile("file", "file.csv", "text/csv", CSV_FILE_CONTENT.getBytes());
        body.add("file", file.getResource());

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8888/api/persons/file")
                .queryParam("normalize", normalize);
        if (sort != null) {
            builder.queryParam("sort", sort);
        }
        if (filter != null) {
            builder.queryParam("filter", filter);
        }
        if (group != null) {
            builder.queryParam("group", group);
        }
        return restTemplate.exchange(builder.build().encode().toUri(),
                HttpMethod.POST, new HttpEntity<>(body, headers), JsonNode.class);
    }
}
