package my;

import application.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//@Component("pointRepository")
//@SpringBootApplication
//@ComponentScan({"hello", "service", "queue","controller","repository"})
//@EntityScan({"hello", "service", "queue","controller","repository"})
//@EnableJpaRepositories({"hello", "service", "queue","controller","repository"})

@Service
@Repository
public interface PointRepository extends MongoRepository<Point, Double> {
    Point findByautoId(String autoId);
    List<Point> findByLatLike(double lat);
    List<Point> findByLonLike(double lon);
    List<Point> findByTimeGreaterThan(Date time);
    // Supports native JSON query string
    @Query("{lat:'?0'}")
    List<Point> findCustomByLat(String lat);
    @Query("{lon:'?0'}")
    List<Point> findCustomByLon(String lon);
}
