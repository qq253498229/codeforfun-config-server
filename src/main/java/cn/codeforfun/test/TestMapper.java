package cn.codeforfun.test;

import cn.codeforfun.generator.model.Application;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TestMapper {

    List<Application> selectAll(@Param("pageable") Pageable pageable);
}
