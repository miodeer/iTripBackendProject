package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.AreaDic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 区域字典数据持久层
 * @author wang
 * @version 3.1.0
 * @since 2019-12-17
 */
@Repository
public interface AreaDicDao {

	/**
	 * 根据查询条件，查找区域列表
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	List<AreaDic> findAreaDisListByQuery(Map<String, Object> queryMap) throws Exception;
}
