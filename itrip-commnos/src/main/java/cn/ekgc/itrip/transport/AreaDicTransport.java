package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.pojo.entity.AreaDic;
import javafx.geometry.Pos;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 区域字典传输层接口
 * @author wang
 * @version 3.1.0
 * @since 2019-12-17
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/area")
public interface AreaDicTransport {

	/**
	 * 查询国内、国外的热门城市(1:国内 2:国外)
	 * @param isChina
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryHotCityByIsChina", method = RequestMethod.POST)
	List<AreaDic> getHotCityListByIsChina(@RequestParam Integer isChina) throws Exception;

	/**
	 * 根据城市查询商圈
	 * @param cityId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryTradeAreaByCityId", method = RequestMethod.POST)
	List<AreaDic> getTradeAreaListByCityId(Integer cityId) throws Exception;
}
