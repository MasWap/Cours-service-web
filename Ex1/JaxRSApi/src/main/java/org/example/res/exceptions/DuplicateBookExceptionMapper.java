package org.example.res.exceptions;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DuplicateBookExceptionMapper implements ExceptionMapper<DuplicateBookException> {

    @Override
    public Response toResponse(DuplicateBookException exception) {
        ErrorResponse errorResponse = new ErrorResponse(Response.Status.CONFLICT.getStatusCode(), exception.getMessage());

        return Response.status(Response.Status.CONFLICT)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
