package kodlama.io.rentACar.core.utilites.mapper;

import org.modelmapper.ModelMapper;

public interface ModelMapperServices {
	ModelMapper forResponse();
	ModelMapper forRequest();
}
