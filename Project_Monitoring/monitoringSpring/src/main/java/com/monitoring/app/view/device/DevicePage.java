package com.monitoring.app.view.device;

import java.util.List;

import com.monitoring.app.model.device.DeviceAccessVO;

public class DevicePage {
	private int total;
	private int currentPage;
	private List<DeviceAccessVO> device;
	private int totalPages;
	private int startPage;
	private int endPage;

	public DevicePage(int total, int currentPage, int size, List<DeviceAccessVO> device) {
		this.total = total;
		this.currentPage = currentPage;
		this.device = device;

		if (total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total / size;
			if (total % size > 0) {
				totalPages++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if (modVal == 0)
				startPage -= 5;

			endPage = startPage + 4;
			if (endPage > totalPages)
				endPage = totalPages;
		}
	}
	
	public int getTotal() {
		return total;
	}

	public boolean hasNoDevice() {
		return total == 0;
	}

	public boolean hasDevice() {
		return total > 0;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<DeviceAccessVO> getDevice() {
		return device;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
}
