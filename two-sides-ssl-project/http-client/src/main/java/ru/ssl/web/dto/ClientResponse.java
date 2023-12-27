package ru.ssl.web.dto;

import org.springframework.http.HttpStatusCode;

public record ClientResponse(String message, HttpStatusCode statusCode) {
}
