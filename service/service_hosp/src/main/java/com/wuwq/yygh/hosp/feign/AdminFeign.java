package com.wuwq.yygh.hosp.feign;

import com.wuwq.yygh.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.hosp.feign
 * @ClassName:AdminFeign
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-05-21 20:19
 * @Version: 1.0
 */

@Component
@FeignClient("service-admin")
public interface AdminFeign {

    @DeleteMapping("admin/manager/deleteManagersByOrgCode/{hospCode}")
    Result deleteManagersByHospCode(@PathVariable String hospCode);

}
