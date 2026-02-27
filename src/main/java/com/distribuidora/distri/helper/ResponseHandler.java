package com.distribuidora.distri.helper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

        private ResponseHandler() {}

        // 200 OK
        public static <T> ResponseEntity<ApiResponse<T>> ok(String message, T data) {
            return buildResponse(true, message, data, HttpStatus.OK);
        }

        // 201 CREATED
        public static <T> ResponseEntity<ApiResponse<T>> created(String message, T data) {
            return buildResponse(true, message, data, HttpStatus.CREATED);
        }

        // 400 BAD REQUEST
        public static ResponseEntity<ApiResponse<Object>> badRequest(String message) {
            return buildResponse(false, message, null, HttpStatus.BAD_REQUEST);
        }

        // 404 NOT FOUND
        public static ResponseEntity<ApiResponse<Object>> notFound(String message) {
            return buildResponse(false, message, null, HttpStatus.NOT_FOUND);
        }

        // No autorizado 401
        public static ResponseEntity<ApiResponse<Object>> unauthorized(String message) {
            return buildResponse(false, message, null, HttpStatus.UNAUTHORIZED);
        }

        // Conflicto 409
        public static ResponseEntity<ApiResponse<Object>> conflict(String message) {
            return buildResponse(false, message, null, HttpStatus.CONFLICT);
        }

        // Error interno 500
        public static ResponseEntity<ApiResponse<Object>> internalError(String message) {
            return buildResponse(false, message, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Método privado común
        private static <T> ResponseEntity<ApiResponse<T>> buildResponse(
                boolean success, String message, T data, HttpStatus status) {
            ApiResponse<T> response = new ApiResponse<>(success, message, data);
            return new ResponseEntity<>(response, status);
        }
}


