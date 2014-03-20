package com.x.test;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.x.utils.excel.XlsExport;

/**
 * <p>
 * ExcelTest
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author chenkangming
 * @date 2014年3月20日
 * @version 1.0
 **/
public class ExcelTest {
	private static enum ExportFiled {
		ID("编号"), Title("标题");
		// 定义私有变量
		private String nCode;

		// 构造函数，枚举类型只能为私有
		private ExportFiled(String _nCode) {
			this.nCode = _nCode;
		}

		@Override
		public String toString() {
			return String.valueOf(this.nCode);
		}
	}

	public String export(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("Application/excel;charset=UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + new String("中文".getBytes("UTF-8"), "ISO8859-1") + "-" + ".xls");
		XlsExport e = new XlsExport();
		int rowIndex = 0;

		// header
		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.toString());
		}

		List<String> list = new ArrayList<String>();
		list.add("test");

		// ea info
		for (String _info : list) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
				case ID:
					e.setCell(filed.ordinal(), _info);
					break;
				case Title:
					e.setCell(filed.ordinal(), _info);
					break;
				default:
					break;
				}

			}
		}
		e.exportXls(response);
		return null;
	}
}
