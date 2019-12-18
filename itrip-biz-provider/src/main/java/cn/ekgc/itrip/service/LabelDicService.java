package cn.ekgc.itrip.service;

import cn.ekgc.itrip.pojo.entity.LabelDic;

import java.util.List;

/**
 * 标签字典业务层接口
 * <b>酒店特色</b>
 * @author wang
 * @version 3.1.0
 * @since 2019-12-17
 */
public interface LabelDicService {

	/**
	 * 获取酒店特色(用于查询页列表)
	 * @return
	 * @throws Exception
	 */
	List<LabelDic> getLabelDicList() throws Exception;
}
