package com.monitoring.app.view.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monitoring.app.model.device.DeviceService;
import com.monitoring.app.model.device.DeviceAccessService;
import com.monitoring.app.model.device.DeviceAccessVO;
import com.monitoring.app.model.device.DeviceVO;

@Controller
public class DeviceInfoGetController {
	@Autowired
	DeviceService deviceService;
	@Autowired
	DeviceAccessService deviceAccessService;
	
	@RequestMapping(value = "/getDevice.do")
	public String getDeivceInfo(DeviceVO vo, Model model) {
		DeviceVO device = deviceService.getDeviceInfo(vo);
		DeviceAccessVO access = deviceAccessService.getDevIdState(vo.getId());
		
		model.addAttribute("device", device);
		model.addAttribute("access", access);
		return "monitoring.jsp";
	}
}
