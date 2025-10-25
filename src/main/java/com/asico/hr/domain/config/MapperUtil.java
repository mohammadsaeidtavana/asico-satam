package com.asico.hr.domain.config;

import com.asico.hr.AsicoWebsiteApplication;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author m.tavana
 * @version 1.0
 * @since 2024
 */
@Component
public class MapperUtil {

    private static final Logger logger = LogManager.getLogger(AsicoWebsiteApplication.class);
    private static Mapper mapper;

    private static Mapper getMapper() {
        if (mapper != null) {
            return mapper;
        } else {
            try {
                mapper = new DozerBeanMapper();
                logger.info("not spring dozer");
            } catch (Exception var1) {
                logger.info(var1.toString());
                mapper = null;
            }
        }
        return mapper;
    }


    public static <D, T> D map(final T entity, Class<D> outClass) {
        return getMapper().map(entity, outClass);
    }

    public static <D, T> List<D> mapList(final List<T> entityList, Class<D> outCLass) {
        return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
    }

    public static <D, T> Page<D> mapPage(final Page<T> entityPage, Class<D> outCLass) {
        List<D> list = entityPage.getContent().stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
        return new PageImpl<>(list, entityPage.getPageable(), entityPage.getTotalElements());
    }

    public static <S, D> D map(final S source, D destination) {
        getMapper().map(source, destination);
        return destination;
    }







}
