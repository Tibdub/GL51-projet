package projet.gl51.store

class MemoryProductStorage implements ProductStorage{

    // ajouter une liste privée

    private List<Product> list = new ArrayList<Product>()

    @Override
    void save(Product p) {
        p.id = UUID.randomUUID().toString()
        list.add(p)
    }

    @Override
    void update(String id, Product p) {
        for(int i=0; i<list.size();i++){
            if(list.get(i).id == id){
                list.get(i).name = p.name
                list.get(i).description = p.description
                list.get(i).price = p.price
                list.get(i).idealTemperature = p.idealTemperature
            }
        }
    }

    @Override
    Product getByID(String id) {
        Product p_sortie = new Product()
        for(int i=0; i<list.size(); i++){
            if(list.get(i).id==id){
                p_sortie.id=list.get(i).id
                p_sortie.name=list.get(i).name
                p_sortie.description=list.get(i).description
                p_sortie.price=list.get(i).price
                p_sortie.idealTemperature=list.get(i).idealTemperature
            }
        }
        if(p_sortie!= null){
            return  p_sortie
        }
        else{
            //throw new NotExistingProductException()
            return null
        }

    }

    @Override
    void delete(String id) {
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).id == id){
                list.remove(i)
            }
        }
    }

    @Override
    List<Product> all() {
        list
    }
}