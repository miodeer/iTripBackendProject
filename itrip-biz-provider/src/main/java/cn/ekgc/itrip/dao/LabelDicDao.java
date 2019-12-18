package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.LabelDic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 标签字典数据持久层接口
 * <b>酒店特色</b>
 * @author wang
 * @version 3.1.0
 * @since 2019-12-17
 */
@Repository
public interface LabelDicDao {

	/**
	 * 获取酒店特色(用于查询页列表)
	 * @return
	 * @throws Exception
	 */
	List<LabelDic> findLabelDicListByQuery(Map<String, Object> queryMap) throws Exception;
}
