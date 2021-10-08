package repository;

import application.Point;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.*;

@Repository
@Service
public class PointRepositoryCustomImpl implements PointRepositoryCustom{

    @Autowired
    public MongoTemplate mongoTemplate;

    @Override
    public double getMaxPointId(String autoId) {
        Query query = new Query(where("autoId").is(autoId));
        query.with(new Sort(Sort.Direction.DESC, "id"));
        query.limit(1);
        Point maxObject = mongoTemplate.findOne(query, Point.class);
        if (maxObject == null) {
            return 0;
        }
        return maxObject.getId();
    }

    @Override
    public double updatePoint(String autoId, double lat, double lon) {
        Query query1 = new Query(where("id").is(getMaxPointId(autoId)));
        Query query2 = query1.addCriteria(where("autoId").is(autoId));
        Update update = new Update();
        update.set("lat", lat);
        update.set("lon", lon);
        UpdateResult result = this.mongoTemplate.updateFirst(query2, update, Point.class);
        if (result != null) {return result.getModifiedCount();}
        else {return 0;}
    }

}
