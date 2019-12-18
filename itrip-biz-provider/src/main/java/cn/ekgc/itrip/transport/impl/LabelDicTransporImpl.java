package cn.ekgc.itrip.transport.impl;

import cn.ekgc.itrip.pojo.entity.LabelDic;
import cn.ekgc.itrip.service.LabelDicService;
import cn.ekgc.itrip.transport.LabelDicTranspor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 标签字典传输层接口实现类
 * <b>酒店特色</b>
 * @author wang
 * @version 3.1.0
 * @since 2019-12-17
 */
@RestController("labelDicTranspor")
@RequestMapping("/label")
public class LabelDicTransporImpl implements LabelDicTranspor {
	@Autowired
	private LabelDicService labelDicService;

	/**
	 * 获取酒店特色(用于查询页列表)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<LabelDic> getHotelFeatureList() throws Exception {
		return labelDicService.getLabelDicList();
	}
}
