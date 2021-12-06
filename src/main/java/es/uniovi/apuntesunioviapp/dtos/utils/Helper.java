package es.uniovi.apuntesunioviapp.dtos.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;


@PropertySource("classpath:config.properties")
public class Helper {
    private static ModelMapper mapper;

    private static ModelMapper getMapper() {
        if (mapper == null) {
            mapper = new ModelMapper();
        }
        return mapper;
    }

    public static <D> D convert(Object source, Class<D> target) {
        return getMapper().map(source, target);
    }

    public static <T, D> List<D> convert(List<T> list, Class<D> target) {
        return list.stream().map(element -> getMapper().map(element, target)).collect(Collectors.toList());
    }

    public static <T, D> Page<D> convert(Page<T> page, Class<D> target) {
        return page.map(element -> getMapper().map(element, target));
    }
}
