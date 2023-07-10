package com.automation.stepdef;

import com.automation.pojos.Person;
import com.automation.utils.ConfigReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class MyStepdefs {
    private String baseUrl = ConfigReader.getProperty("base.url");
    private Response response;
    private Person person;
    private int personId;

    @Given("the person information:")
    public void thePersonInformation(DataTable dataTable) {
        person = new Person();

        // Get the data from the DataTable and fill in the Person object info
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> personData = data.get(0);

        person.setId(Integer.parseInt(personData.get("id")));
        person.setName(personData.get("name"));
        person.setAge(Integer.parseInt(personData.get("age")));
    }

    @When("user sends a POST request to {string}")
    public void userSendsAPOSTRequestTo(String endpoint) {
        String url = baseUrl + endpoint;

        // Send a POST request with the person information
        response = RestAssured.given()
                .contentType("application/json")
                .body(personToJson())
                .post(url);
    }


    @Then("verify response status code is {int}")
    public void verifyResponseStatusCodeIs(int expectedStatusCode) {
        // Assert the response status code matches the expectedStatusCode
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @And("verify response body contains:")
    public void verifyResponseBodyContains(DataTable dataTable) {
        // Convert the DataTable to a map of key-value pairs
        Map<String, String> expectedBody = dataTable.asMap(String.class, String.class);

        // Assert that the response body contains the expected key-value pairs
        for (Map.Entry<String, String> entry : expectedBody.entrySet()) {
            String expectedKey = entry.getKey();
            String expectedValue = entry.getValue();

            Assert.assertEquals(expectedValue, response.jsonPath().getString(expectedKey));
        }
    }
    private String personToJson() {
        // Convert the person object to JSON string
        Person person = new Person();// Get the person object
        return "{\"id\": \"" + person.getId() + "\", \"name\": \"" + person.getName() + "\", \"age\": " + person.getAge() + "}";
    }
    // steps for delete request

    @Given("the person with ID {int} exists")
    public void thePersonWithIDExists(int id) {
        personId = id; //assign id value from feature file
    }

    @Given("the person with ID {int} doesn't exist")
    public void thePersonWithIDDoesnTExist(int id) {
        personId = id;  //assign id value from feature file
    }

    @When("user sends a DELETE request to {string}")
    public void userSendsADELETERequestTo(String endpoint) {
        String url = baseUrl + endpoint.replace("{id}", String.valueOf(personId));

        // Send a DELETE request to delete the person
        response = RestAssured.given()
                .get(url);
    }
    // steps for get request
    @When("user sends a GET request to {string}")
    public void userSendsAGETRequestTo(String endpoint) {
        String url = baseUrl + endpoint.replace("{id}", String.valueOf(personId));

        // Send a GET request to retrieve person information
        response = RestAssured.given()
                .get(url);

    }


    @And("verify response body contains a dummy person object")
    public void verifyResponseBodyContainsADummyPersonObject() {
        //  to verify the response body contains a dummy person object

        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertNotNull(response.jsonPath().getString("name"));
        Assert.assertNotNull(response.jsonPath().getString("age"));
    }

    @And("verify response body contains a list of all persons")
    public void verifyResponseBodyContainsAListOfAllPersons() {
        // to verify the response body contains a list of all persons

        Assert.assertTrue(response.jsonPath().getList("$").size() > 0);
    }
    }


