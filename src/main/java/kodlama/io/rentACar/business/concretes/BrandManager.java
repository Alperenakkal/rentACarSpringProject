package kodlama.io.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequests;
import kodlama.io.rentACar.business.requests.UpdateBrandRequsts;
import kodlama.io.rentACar.business.response.GetAllBrandsResponse;
import kodlama.io.rentACar.business.response.GetByIdBrandResponse;
import kodlama.io.rentACar.business.rules.BrandBusinessRules;
import kodlama.io.rentACar.core.utilites.mapper.ModelMapperServices;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
	private BrandRepository brandRepository;
	private ModelMapperServices modelMapperServices;
	private BrandBusinessRules brandBusinessRules;
	
	

	@Override
	public List<GetAllBrandsResponse> getAll() {
		List<Brand> brands = brandRepository.findAll();		
		List<GetAllBrandsResponse> brandsResponse=brands.stream()
				.map(brand->this.modelMapperServices.forResponse()
				.map(brand,GetAllBrandsResponse.class)).collect(Collectors.toList());
				
		// iş kuralları
		return brandsResponse;
	}

	@Override
	public void add(CreateBrandRequests createBrandRequests) {
		this.brandBusinessRules.checkIfBrandNameExists(createBrandRequests.getName());
		Brand brand =this.modelMapperServices.forRequest().map(createBrandRequests,Brand.class);
		this.brandRepository.save(brand);

	}

	@Override
	public GetByIdBrandResponse getById(int id) {
		Brand brand=this.brandRepository.findById(id).orElseThrow();
		GetByIdBrandResponse response=this.modelMapperServices
				.forResponse().map(brand, GetByIdBrandResponse.class);
		
		return response;
	}

	@Override
	public void update(UpdateBrandRequsts updateBrandRequsts) {
		Brand brand=this.modelMapperServices.forRequest().map(updateBrandRequsts, Brand.class);
		this.brandRepository.save(brand);
		
	}

	@Override
	public void delete(int id) {
		this.brandRepository.deleteById(id);
		
	}

}
