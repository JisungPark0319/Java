package com.monitoring.app.view.device;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.monitoring.app.model.device.DeviceAccessService;
import com.monitoring.app.model.device.DeviceAccessVO;

@Controller
public class DeviceAccessListController {
	@Autowired
	DeviceAccessService deviceAccessService;
	
	private int size = 10;
	
	@RequestMapping(value="/accesslist.do")
	public String devAccessList(Model model, @RequestParam(value="pageNo", defaultValue = "0") int pageNo) {
		if(pageNo == 0) {
			pageNo = 1;
		}
		int total = deviceAccessService.deviceListCount();
		List<DeviceAccessVO> articleList = deviceAccessService.getDevStateList((pageNo-1)*size, size);
		
		DevicePage devicePage = new DevicePage(total, pageNo, size, articleList);
		
		model.addAttribute("devicePage", devicePage);
		
		return "index.jsp";
	}
}
