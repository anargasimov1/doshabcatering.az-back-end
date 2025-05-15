package az.doshabcatering.doshabcatering.servise.elasticService;

import az.doshabcatering.doshabcatering.documents.OrdersIndex;
import az.doshabcatering.doshabcatering.repository.elasticRepo.ElasticOrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElasticOrdersService {

    private final ElasticOrderRepo elasticOrderRepo;

    public void saveOrder(OrdersIndex order) {
        elasticOrderRepo.save(order);
    }

    public Page<OrdersIndex> findAllOrders(int num, int size) {
        Pageable pageable = PageRequest.of(num, size);
        return elasticOrderRepo.findAll(pageable);
    }


}
