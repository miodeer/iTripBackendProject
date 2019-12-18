package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.pojo.entity.LabelDic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 标签字典传输层接口
 * <b>酒店特色</b>
 * @author wang
 * @version 3.1.0
 * @since 2019-12-17
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/label")
public interface LabelDicTranspor {

	/**
	 * 获取酒店特色(用于查询页列表)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	List<LabelDic> getHotelFeatureList() throws Exception;
}
