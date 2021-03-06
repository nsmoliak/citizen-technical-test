package is.citizen.technicaltest.utils;

public class TestConstant {
    public static final String CSV_FILE_CONTENT =
            "\uFEFFfirstname,surname,address1,address2,city,state,postcode,countrycode,gender,dateofbirth\n"
            + "Fred,Smith,Customs House,1 Long Street,Glasgow,Glasgow,G10 1AA,UK,M,1-1-1970\n"
            + "Bob,Long,2 Short Street,,Southend,East Sussex,SS0 8BB,UK,M,2-2-1971\n"
            + "Nancy,Bayliss,3 Lombard Street,,Brighton,East Sussex,B23 4CC,GB,F,3-3-1973\n"
            + "Alan,Johnson,Tower House,4 Clements Lane,London,,EC14AA,,M,4-4-1974\n"
            + "Roger,May,5 Watling Ave,,Manchester,Manchester,M10 5DD,UK,M,5-5-1975\n"
            + "Mike,Ward,8 Wrong Rd,,Leeds,West Yorkshire,L11 6AA,UK,M,6-6-1976\n"
            + "Sarah,,1 Long Street,,Glasgow,Glasgow,G10,UK,F,7-7-1977\n"
            + "Linda,James,8 Wrong Road,,Leeds,West Yorkshire,L12 7EE,GB,F,8-8-1978\n"
            + "Helen,Mirren,6 The Bells,,Liverpool,Liverpool,LP10GG,UK,F,9-9-1979\n"
            + "Rebecca,May,4 Clements Ln,,London,London,EC1 4AA,UK,F,10-10-1980\n";

    public static final String PERSON_JSON_CONTENT = "[\n"
            + "    {\n"
            + "        \"firstname\": \"Fred\",\n"
            + "        \"surname\": \"Smith\",\n"
            + "        \"city\": \"Glasgow\",\n"
            + "        \"state\": \"Glasgow\",\n"
            + "        \"postcode\": \"G10 1AA\",\n"
            + "        \"gender\": \"M\",\n"
            + "        \"address1\": \"Customs House\",\n"
            + "        \"address2\": \"1 Long Street\",\n"
            + "        \"countrycode\": \"UK\",\n"
            + "        \"dateofbirth\": \"1-1-1970\"\n"
            + "    },\n"
            + "    {\n"
            + "        \"firstname\": \"Bob\",\n"
            + "        \"surname\": \"Long\",\n"
            + "        \"city\": \"Southend\",\n"
            + "        \"state\": \"East Sussex\",\n"
            + "        \"postcode\": \"SS0 8BB\",\n"
            + "        \"gender\": \"M\",\n"
            + "        \"address1\": \"2 Short Street\",\n"
            + "        \"address2\": \"\",\n"
            + "        \"countrycode\": \"UK\",\n"
            + "        \"dateofbirth\": \"2-2-1971\"\n"
            + "    },\n"
            + "    {\n"
            + "        \"firstname\": \"Nancy\",\n"
            + "        \"surname\": \"Bayliss\",\n"
            + "        \"city\": \"Brighton\",\n"
            + "        \"state\": \"East Sussex\",\n"
            + "        \"postcode\": \"B23 4CC\",\n"
            + "        \"gender\": \"F\",\n"
            + "        \"address1\": \"3 Lombard Street\",\n"
            + "        \"address2\": \"\",\n"
            + "        \"countrycode\": \"GB\",\n"
            + "        \"dateofbirth\": \"3-3-1973\"\n"
            + "    },\n"
            + "    {\n"
            + "        \"firstname\": \"Alan\",\n"
            + "        \"surname\": \"Johnson\",\n"
            + "        \"city\": \"London\",\n"
            + "        \"state\": \"\",\n"
            + "        \"postcode\": \"EC14AA\",\n"
            + "        \"gender\": \"M\",\n"
            + "        \"address1\": \"Tower House\",\n"
            + "        \"address2\": \"4 Clements Lane\",\n"
            + "        \"countrycode\": \"\",\n"
            + "        \"dateofbirth\": \"4-4-1974\"\n"
            + "    },\n"
            + "    {\n"
            + "        \"firstname\": \"Roger\",\n"
            + "        \"surname\": \"May\",\n"
            + "        \"city\": \"Manchester\",\n"
            + "        \"state\": \"Manchester\",\n"
            + "        \"postcode\": \"M10 5DD\",\n"
            + "        \"gender\": \"M\",\n"
            + "        \"address1\": \"5 Watling Ave\",\n"
            + "        \"address2\": \"\",\n"
            + "        \"countrycode\": \"UK\",\n"
            + "        \"dateofbirth\": \"5-5-1975\"\n"
            + "    },\n"
            + "    {\n"
            + "        \"firstname\": \"Mike\",\n"
            + "        \"surname\": \"Ward\",\n"
            + "        \"city\": \"Leeds\",\n"
            + "        \"state\": \"West Yorkshire\",\n"
            + "        \"postcode\": \"L11 6AA\",\n"
            + "        \"gender\": \"M\",\n"
            + "        \"address1\": \"8 Wrong Rd\",\n"
            + "        \"address2\": \"\",\n"
            + "        \"countrycode\": \"UK\",\n"
            + "        \"dateofbirth\": \"6-6-1976\"\n"
            + "    },\n"
            + "    {\n"
            + "        \"firstname\": \"Sarah\",\n"
            + "        \"surname\": \"\",\n"
            + "        \"city\": \"Glasgow\",\n"
            + "        \"state\": \"Glasgow\",\n"
            + "        \"postcode\": \"G10\",\n"
            + "        \"gender\": \"F\",\n"
            + "        \"address1\": \"1 Long Street\",\n"
            + "        \"address2\": \"\",\n"
            + "        \"countrycode\": \"UK\",\n"
            + "        \"dateofbirth\": \"7-7-1977\"\n"
            + "    },\n"
            + "    {\n"
            + "        \"firstname\": \"Linda\",\n"
            + "        \"surname\": \"James\",\n"
            + "        \"city\": \"Leeds\",\n"
            + "        \"state\": \"West Yorkshire\",\n"
            + "        \"postcode\": \"L12 7EE\",\n"
            + "        \"gender\": \"F\",\n"
            + "        \"address1\": \"8 Wrong Road\",\n"
            + "        \"address2\": \"\",\n"
            + "        \"countrycode\": \"GB\",\n"
            + "        \"dateofbirth\": \"8-8-1978\"\n"
            + "    },\n"
            + "    {\n"
            + "        \"firstname\": \"Helen\",\n"
            + "        \"surname\": \"Mirren\",\n"
            + "        \"city\": \"Liverpool\",\n"
            + "        \"state\": \"Liverpool\",\n"
            + "        \"postcode\": \"LP10GG\",\n"
            + "        \"gender\": \"F\",\n"
            + "        \"address1\": \"6 The Bells\",\n"
            + "        \"address2\": \"\",\n"
            + "        \"countrycode\": \"UK\",\n"
            + "        \"dateofbirth\": \"9-9-1979\"\n"
            + "    },\n"
            + "    {\n"
            + "        \"firstname\": \"Rebecca\",\n"
            + "        \"surname\": \"May\",\n"
            + "        \"city\": \"London\",\n"
            + "        \"state\": \"London\",\n"
            + "        \"postcode\": \"EC1 4AA\",\n"
            + "        \"gender\": \"F\",\n"
            + "        \"address1\": \"4 Clements Ln\",\n"
            + "        \"address2\": \"\",\n"
            + "        \"countrycode\": \"UK\",\n"
            + "        \"dateofbirth\": \"10-10-1980\"\n"
            + "    }\n"
            + "]";
}
