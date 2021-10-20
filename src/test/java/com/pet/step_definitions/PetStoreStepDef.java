package com.pet.step_definitions;


import com.pet.Utilities.BaseUtils;
import com.pet.Utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

public class PetStoreStepDef {
    String baseURI= ConfigurationReader.get ( "URI" );
    Response response=null;
    Map<String,Object> bodyRequest;
    JsonPath jsonPath=null;

    @Given("the user goes to the API")
    public void the_user_goes_to_the_API() {

        BaseUtils.httpRequest ();
    }
    @When("user create new pet information")
    public void user_create_new_pet_information(Map<String,Object>input) {
        bodyRequest=input;
        System.out.println ( "body = " + bodyRequest );
        response=BaseUtils.requestType ( baseURI,"post",null,bodyRequest );
    }



    @Then("the user verifies status code {int}")
    public void the_user_verifies_status_code(int expectedStatus) {



        Assert.assertEquals ( expectedStatus,response.statusCode () );
    }

    @Then("user verifies response has id")
    public void user_verifies_response_has_id() {
        jsonPath=response.jsonPath ();
        System.out.println ( response.prettyPrint () );

       Assert.assertNotNull (jsonPath.getInt ( "id" ) );


    }

    @Then("user verifies created name should be equal to the posted name")
    public void user_verifies_created_name_should_be_equal_to_the_posted_name() {
        Assert.assertEquals (  bodyRequest.get ( "name" ) ,  jsonPath.getString ( "name" ));
    }

    @Then("user verifies Content-type should be {string}")
    public void user_verifies_Content_type_should_be(String expectedContent) {
        Assert.assertEquals ( expectedContent,response.contentType () );
    }

    @Then("user verifies Response header should has a date value")
    public void user_verifies_Response_header_should_has_a_date_value() {
       Assert.assertFalse (response.header ( "date" ).isEmpty ()  );
    }


    @When("the user get request of new pets info")
    public void the_user_get_request_of_new_pets_info() {

        response=BaseUtils.requestType ( baseURI,"get",123,null );
    }

    @When("the user get request of id {int} pets info")
    public void the_user_get_request_of_id_pets_info(int id) {
        response=BaseUtils.requestType ( baseURI,"get",id,null );
        System.out.println ( response.prettyPrint () );
    }


    @Then("the user verifies response body should be equal to the first Task’s response body")
    public void the_user_verifies_response_body_should_be_equal_to_the_first_Task_s_response_body() {
        Map<String,Object> actualPetInfo=response.body().as(Map.class);
        System.out.println (actualPetInfo.toString ());

        Assert.assertEquals ( "Asuman",actualPetInfo.get ( "name" ) );
        Assert.assertEquals (  "available",actualPetInfo.get ( "status" ));


    }
    @When("the user use delete request for deleting")
    public void the_user_use_delete_request_for_deleting() {
        response=BaseUtils.requestType ( baseURI,"delete",123,null );
    }

    @Then("user verifies Message should be equal to “unknown”")
    public void user_verifies_Message_should_be_equal_to_unknown() {

        String expectedResult=response.jsonPath ().getString ( "type" );
        System.out.println (expectedResult);
        Assert.assertEquals ( "unknown",expectedResult );


    }




}
