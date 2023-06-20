package example;

import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpStatus.OK;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import io.restassured.common.mapper.TypeRef;
import io.restassured.http.Header;
import io.restassured.http.Headers;

public class RestClient {
	public static String get(String url) {
		return given().get(url).then().statusCode(HttpStatus.OK.value()).log().ifError().extract().asString();
	}

	public static <T> T get(String url, TypeRef<T> typeRef) {
		return get(url, typeRef, OK);
	}

	public static <T> T get(String url, TypeRef<T> typeRef, HttpStatus httpStatus) {
		return given().get(url).then().statusCode(httpStatus.value()).log().ifError().extract().as(typeRef.getType());
	}

	public static <T> T get(String url, Class<T> clazz) {
		return get(url, clazz, OK);
	}

	public static <T> T get(String url, Class<T> clazz, HttpStatus httpStatus) {
		return given().get(url).then().statusCode(httpStatus.value()).log().ifError().extract().as(clazz);
	}

	public static <T> T post(String url, Object body, Class<T> clazz) {
		return post(url, body, clazz, OK);
	}

	public static <T> T post(String url, Object body, Class<T> clazz, HttpStatus httpStatus) {
		return given().body(body).post(url).then().statusCode(httpStatus.value()).log().ifError().extract().as(clazz);
	}

	public static <T> T post(String url, String body, TypeRef<T> typeRef, HttpStatus httpStatus) {
		return given().body(body).post(url).then().statusCode(httpStatus.value()).log().ifError().extract().as(typeRef);
	}

	public static <T> T put(String url, String body, TypeRef<T> typeRef, HttpStatus httpStatus) {
		return given().body(body).put(url).then().statusCode(httpStatus.value()).log().ifError().extract().as(typeRef);
	}

	public static void delete(String url, HttpStatus httpStatus) {
		given().delete(url).then().statusCode(httpStatus.value()).log().ifError();

	}

	private static Headers createHeaders(String token) {
		return new Headers(Arrays.asList(new Header("Authorization", "Bearer " + token),
				new Header("Content-Type", "application/json"), new Header("Accept", "application/json")));
	}
}
