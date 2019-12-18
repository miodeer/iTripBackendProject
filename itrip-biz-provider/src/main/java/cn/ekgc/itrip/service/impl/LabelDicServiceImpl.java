package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.dao.LabelDicDao;
import cn.ekgc.itrip.pojo.entity.LabelDic;
import cn.ekgc.itrip.service.LabelDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 标签字典业务层接口实现类
 * <b>酒店特色</b>
 * @author wang
 * @version 3.1.0
 * @since 2019-12-17
 */
@Service("labelDicService")
@Transactional
public class LabelDicServiceImpl implements LabelDicService {
	@Autowired
	private LabelDicDao labelDicDao;

	/**
	 * 获取酒店特色(用于查询页列表)
	 * @return
	 * @throws Exception
	 */
	public List<LabelDic> getLabelDicList() throws Exception {
		List<LabelDic> labelDicList = labelDicDao.findLabelDicListByQuery(null);
		return labelDicList;
	}
}
