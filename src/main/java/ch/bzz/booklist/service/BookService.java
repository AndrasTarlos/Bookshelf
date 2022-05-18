package ch.bzz.booklist.service;

import ch.bzz.booklist.data.DataHandler;
import ch.bzz.booklist.model.Book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("book")
public class BookService {
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listBooks() {
        List<Book> bookList = DataHandler.getInstance().readAllBooks();
        Response response = Response
                .status(200)
                .entity(bookList)
                .build();
        return response;
    }

    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readBook(@QueryParam("uuid") String bookUUID) {
        if (bookUUID.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")) {
            Book book = DataHandler.getInstance().readBookByUUID(bookUUID);
            Response response = Response
                    .status(200)
                    .entity(book)
                    .build();
            return response;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
