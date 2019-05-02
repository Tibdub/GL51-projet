package projet.gl51.store

//import projet.gl51.exeption.NotExistingProductException
import spock.lang.Specification


class MemoryProductStorageTest extends Specification {

    ProductStorage store = new MemoryProductStorage()


    void "test empty list"() {
        expect:
        store.all() == []
    }




    def "adding a product returns the product in the list"() {

        setup:
        store.save(new Product(name: "myproduct"))

        when:
        def listSortie = store.all()

        then:
        listSortie.size() == 1
        listSortie.first().name == 'myproduct'
    }


//TODO : a modifier !
    def "adding a product will create a new ID"(){
        setup:
        store.save(new Product())

        when:
        def listSortie = store.all()

        then:
        System.out.println(listSortie.first().id)
        listSortie.first().id != null
    }


    def "deleting a product will remove it from the list"(){
        setup:
        Product p = new Product()
        store.save(p)
        String id_product = p.id

        when:
        def listSortie = store.all()
        store.delete(id_product)

        then:
        listSortie.size() == 0

    }

    def "modifying a product will change it n the list"(){
        setup:
        Product p1 = new Product(name: "phone", description: "androide", price: 327, idealTemperature: 25)
        store.save(p1)
        Product p2 = new Product(name: "nokia", description: "gsm", price: 322, idealTemperature: 20)

        when:
        def listSortie = store.all()
        store.update(p1.id, p2)

        then:
        listSortie.first().name == "nokia"
        listSortie.first().description == "gsm"
        listSortie.first().price == 322
        listSortie.first().idealTemperature == 20
    }

    def "getting a product by its ID will return it if it exist"(){
        setup:
        Product p = new Product(name: "Paraluie", description: "par la pluie", price: 24, idealTemperature: 8)
        store.save(p)

        when:
        Product p_sortie = store.getByID(p.id)

        then:
        p_sortie.id == p.id
        p_sortie.idealTemperature == p.idealTemperature
        p_sortie.price == p.price
        p_sortie.description == p.description
        p_sortie.name == p.name

    }

    /*A Finir
    def "getting a product by its ID will throw NotExistingProductExeption if it does not exist"(){
        when:
        store.getByID("hvtfty55")

        then:
        thrown new NotExistingProductException()
    }
     */
}