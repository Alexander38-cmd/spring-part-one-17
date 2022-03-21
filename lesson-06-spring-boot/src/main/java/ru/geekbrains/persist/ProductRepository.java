package ru.geekbrains.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findByTitleStartsWith(String prefix);

    @Query("select p " +
            "from Product p " +
            "where ( p.title like CONCAT(:prefix, '%') or :prefix is null ) and " +
            "( p.price >= :minPrice or :minPrice is null ) and " +
            "( p.price <= :maxPrice or :maxPrice is null )")
    List<Product> filterProducts (@Param("prefix") String prefix,
                                  @Param("minPrice") Integer minPrice,
                                  @Param("maxPrice") Integer maxPrice);
}

//    @PersistenceContext
//    private EntityManager em;
//    //Identity Map
//    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();
//    private final AtomicLong identity = new AtomicLong(0);
//
//    @PostConstruct
//    public void init() {
//        this.insert(new Product("Car", 550000));
//        this.insert(new Product("Mobile phone", 90000));
//        this.insert(new Product("TV", 65000));
//    }
//
//
//    public List<Product> findAll() {
//        return new ArrayList<>(productMap.values());
//    }
//
//    public Optional<Product> findById(long id) {
//        return Optional.ofNullable(productMap.get(id));
//    }
//
//    public void insert(Product product) {
//        long id = identity.incrementAndGet();
//        product.setId(id);
//        productMap.put(id, product);
//    }
//
//    public void update(Product product) {
//        productMap.put(product.getId(), product);
//    }
//
//    public void delete(long id) {
//        productMap.remove(id);
//    }
//
//    public void save(Product product) {
//        if (product.getId() == null) {
//            insert(product);
//        } else {
//            update(product);
//        }
//    }
