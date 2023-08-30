package kodlama.io.rentACar.business.abstracts;

import java.util.List;

import kodlama.io.rentACar.business.requests.CreateBrandRequests;
import kodlama.io.rentACar.business.requests.UpdateBrandRequsts;
import kodlama.io.rentACar.business.response.GetAllBrandsResponse;
import kodlama.io.rentACar.business.response.GetByIdBrandResponse;

public interface BrandService {
	List<GetAllBrandsResponse> getAll();
	GetByIdBrandResponse getById(int id);
	void add(CreateBrandRequests createBrandRequests);
	void update(UpdateBrandRequsts updateBrandRequsts);
	void delete(int id);
	

}
