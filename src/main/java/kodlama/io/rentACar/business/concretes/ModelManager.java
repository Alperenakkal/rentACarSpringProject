package kodlama.io.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.ModelService;
import kodlama.io.rentACar.business.requests.CreateModelRequests;
import kodlama.io.rentACar.business.response.GetAllModelsResponse;
import kodlama.io.rentACar.core.utilites.mapper.ModelMapperServices;
import kodlama.io.rentACar.dataAccess.abstracts.ModelRepository;
import kodlama.io.rentACar.entities.concretes.Model;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ModelManager implements ModelService{
	private ModelRepository modelRepository;
	private ModelMapperServices modelMapperServices;
	@Override
	public List<GetAllModelsResponse> getAll() {
		List<Model> models = modelRepository.findAll();		
		List<GetAllModelsResponse> modelsResponse=models.stream()
				.map(brand->this.modelMapperServices.forResponse()
				.map(brand,GetAllModelsResponse.class)).collect(Collectors.toList());
				
		// iş kuralları
		return modelsResponse;
	}
	@Override
	public void add(CreateModelRequests createModelRequests) {
		Model model =this.modelMapperServices.forRequest().map(createModelRequests,Model.class);
		this.modelRepository.save(model);
		
	}
	

}
