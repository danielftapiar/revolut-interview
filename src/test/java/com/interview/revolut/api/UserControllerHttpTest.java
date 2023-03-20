//package com.interview.revolut.api;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class UserControllerHttpTest {
//    @Value(value="${PORT}")
//    private Integer port;
//
//
////    @Autowired
////    private TestRestTemplate restTemplate;
//
////    @Test
////    public void greetingShouldReturnDefaultMessage() throws Exception {
////       this.restTemplate.put("http://localhost:" + port + "/hello/{username}", "testtest2" );
////        assertThat().contains("must match");
////    }
//
//
//    @Test
//    public void givenUserDoesNotExistDenyOnNumberInUsername()
//            throws Exception {
//
//        // Given
//        String name = "testtest28";
////        mvc.perform(MockMvcRequestBuilders
////                .put("http://localhost:"+port+"/hello/" + name)
////                .accept(MediaType.APPLICATION_JSON))
////           .andDo(print())
////                .andExpect(status().is4xxClientError())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.\"username").exists())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.\"username").value("must match \\\"[a-zA-Z]+\\\""));
//
////        HttpUriRequest request = new HttpPut( "http://localhost:"+port+"/hello/" + name );
////
////        // When
////        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
////
////        // Then
////        assertThat(
////                httpResponse.getStatusLine().getStatusCode(),
////                equalTo(HttpStatus.SC_BAD_REQUEST));
//    }
//
//}
