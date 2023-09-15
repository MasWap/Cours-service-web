package org.example.res;

import org.example.models.Book;
import org.example.res.exceptions.BookNotFoundException;
import org.example.res.exceptions.DuplicateBookException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.HashMap;
import java.util.Map;

@Path("books")
public class BookResource {
    private static Map<Integer, Book> booksMap = new HashMap<>();
    private static int nextBookId = 1;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {
        return Response.ok(booksMap.values()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@PathParam("id") int id) {
        Book book = booksMap.get(id);
        if (book != null) {
            return Response.ok(book).build();
        } else {
            throw new BookNotFoundException("Unable to find book with id " + id + ".");
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        if (!book.isFieldsValid()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (booksMap.containsKey(book.getId())) {
            throw new DuplicateBookException("A book with id " + book.getId() + " already exists.");
        }
        book.setId(nextBookId);
        booksMap.put(nextBookId, book);
        nextBookId++;
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("id") int id, Book updatedBook) {
        if (updatedBook == null || !updatedBook.isFieldsValid()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Book book = booksMap.get(id);
        if (book != null) {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            return Response.noContent().build();
        } else {
            throw new BookNotFoundException("Unable to find book with id " + id);
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        Book removedBook = booksMap.remove(id);
        if (removedBook != null) {
            return Response.noContent().build();
        } else {
            throw new BookNotFoundException("Unable to find book with id " + id);
        }
    }
}
