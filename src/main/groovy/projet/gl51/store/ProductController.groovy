package projet.gl51.store

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put


@Controller("/store/product")

class ProductController {

    MemoryProductStorage storage = new MemoryProductStorage();

    @Get("/")
    List<Product> index() {
        storage.all()
    }

    @Get("/{id}")
    Product get(String id) {
        try {
            storage.getByID(id)
        } catch (NoSuchElementException ignored) {
            null
        }
    }

    @Post("/")
    String save(@Body Product productToSave) {
        storage.save(productToSave)
    }

    @Put("/{id}")
    HttpStatus update(String id, @Body Product replacingProduct) {
        storage.update(id, replacingProduct)
        HttpStatus.OK
    }

    @Delete("/{id}")
    HttpStatus delete(String id) {
        storage.delete(id)
        HttpStatus.OK
    }

}
