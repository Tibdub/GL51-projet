package projet.gl51.store


import io.micronaut.context.ApplicationContext
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class ProductControllerSpec extends Specification{
    @Shared @AutoCleanup EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)
    @Shared @AutoCleanup RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())


    MemoryProductStorage storage = new MemoryProductStorage()

    void "Liste vide"() {
        given:
        List<Product> response = client.toBlocking().retrieve(HttpRequest.GET("/store/product"), Argument.listOf(Product).type)

        expect:
        response == []
    }

/*
    void "test create"() {
        setup:
        Product product1 = new Product(name: "stylo", description: "bleu", price: 12, idealTemperature:23)
        List<Product> response = client.toBlocking().retrieve(HttpRequest.GET("/store/product"), Argument.listOf(Product).type)
        String id = product1.getId()

        when:
        String bla = client.toBlocking().retrieve(HttpRequest.POST("/store/product", product1))
        //Product findProduct = client.toBlocking().retrieve(HttpRequest.GET("/store/product/"+id), Argument.of(Product).type)

        then:
        bla != null*
    }*/

}
