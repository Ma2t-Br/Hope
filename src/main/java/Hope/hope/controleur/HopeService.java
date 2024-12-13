package Hope.hope.controleur;

import Hope.hope.model.DataHope;
import Hope.hope.model.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HopeService {

    private final DataRepository dataRepository;

    @Autowired
    public HopeService(DataRepository dataRepository){
        this.dataRepository = dataRepository;
    }

    public List<DataHope> getAllMainData(){
        return dataRepository.findAll();
    }
}
