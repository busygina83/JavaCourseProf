package repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
@Repository
public interface PointRepositoryCustom {
    public double getMaxPointId(String autoId);
    public double updatePoint(String autoId, double lat, double lon);
}
