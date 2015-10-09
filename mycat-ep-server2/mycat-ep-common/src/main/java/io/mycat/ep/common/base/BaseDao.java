package io.mycat.ep.common.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseDao<T,Q>{
	
    int countByExample(Q example);

    int deleteByExample(Q example);

    int deleteByPrimaryKey(Object id);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExampleWithBLOBs(Q example);

    List<T> selectByExample(Q example);

    T selectByPrimaryKey(Object id);

    int updateByExampleSelective(@Param("record") T record, @Param("example") Q example);

    int updateByExampleWithBLOBs(@Param("record") T record, @Param("example") Q example);

    int updateByExample(@Param("record") T record, @Param("example") Q example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKeyWithBLOBs(T record);

    int updateByPrimaryKey(T record);
	
}
