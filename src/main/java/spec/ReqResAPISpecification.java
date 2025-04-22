package spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.filter.log.LogDetail.ALL;
import static utils.BaseTest.BASE_URI;

public class ReqResAPISpecification {

    private ReqResAPISpecification(){
        throw new IllegalStateException("Utility class");
    }

    public static RequestSpecification getSpec(){
        return new RequestSpecBuilder().
                setBaseUri(BASE_URI).
                setContentType(ContentType.JSON).
                log(ALL).
                addFilter(new ResponseLoggingFilter()).
                build();
    }

}
